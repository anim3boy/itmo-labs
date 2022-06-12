package me.emokid.command;

import me.emokid.IO.DataSaver;
import me.emokid.IO.ScriptLoader;
import me.emokid.IO.UserInputReader;
import me.emokid.json.JSONMap;
import me.emokid.scheme.HumanBeing;
import me.emokid.scheme.MetaData;
import me.emokid.utils.MessageType;
import me.emokid.utils.Utils;
import me.emokid.utils.exception.EnvironmentVariableNotFoundException;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static me.emokid.scheme.MetaData.metaDataKey;


public class CommandProcessor {

    /**
     * just data collection
     */

    private Map<String, Object> dataCollection;
    private UserInputReader userInputReader;

    /**
     * just command collection
     */

    private final Map<String, Command> commandCollection;

    /**
     * list contains history
     */

    private final List<String> commandHistory;


    /**
     * sets {@link #dataCollection}, loads default commands with {@link #loadDefaultCommandPack()}
     * and initialize {@link #commandCollection}
     *
     * @param collection source data collection
     */

    public CommandProcessor(Map<String, Object> collection, UserInputReader userInputReader) {
        this.dataCollection = validate(collection);
        this.userInputReader = userInputReader;
        this.commandCollection = loadDefaultCommandPack();
        this.commandHistory = new ArrayList<>();
    }

    /**
     * loads default commands with {@link #loadDefaultCommandPack()}
     * initialize {@link #commandCollection} and {@link #dataCollection}
     */
    public CommandProcessor(UserInputReader userInputReader) {
        this.commandHistory = new ArrayList<>();
        this.commandCollection = loadDefaultCommandPack();
        this.userInputReader = userInputReader;
        this.dataCollection = generateEmptyDataCollection();

    }

    /**
     * @return default command pack by framework developers
     */

    private Map<String, Command> loadDefaultCommandPack() {
        Map<String, Command> commandCollection = new HashMap<>();
        //help
        commandCollection.put(CommandWord.HELP, new Command(CommandWord.HELP, Label.HELP_DESCRIPTION) {
            public void execute() {
                Utils.print(MessageType.HEADER, Label.HELP_HEADER);
                for (String key : commandCollection.keySet()) {
                    Utils.print(MessageType.PART_OF_LIST, key, commandCollection.get(key));
                }
            }
        });
        //info
        commandCollection.put(CommandWord.INFO, new Command(CommandWord.INFO, Label.INFO_DESCRIPTION) {
            public void execute() {
                Utils.print(MessageType.HEADER, Label.INFO_HEADER);
                Utils.print(MessageType.PART_OF_LIST, Label.INFO_PREFIX1, Utils.getLastPartOfClassName(dataCollection));
                Utils.print(MessageType.PART_OF_LIST, Label.INFO_PREFIX2, ((MetaData) dataCollection.get(metaDataKey)).getDate().format(Utils.getDateFormatter()));
                Utils.print(MessageType.PART_OF_LIST, Label.INFO_PREFIX3, dataCollection.size() - 1);
            }
        });
        //show
        commandCollection.put(CommandWord.SHOW, new Command(CommandWord.SHOW, Label.SHOW_DESCRIPTION) {
            public void execute() {
                boolean empty = true;
                Utils.print(MessageType.HEADER, Label.SHOW_HEADER);
                for (String key : dataCollection.keySet()) {
                    if (key.equals(metaDataKey)) {
                        continue;
                    }
                    empty = false;
                    Utils.print(MessageType.HEADER, key);
                    ((HumanBeing) dataCollection.get(key)).print();
                }
                if (empty) {
                    Utils.print(MessageType.ERROR, Label.SHOW_IF_EMPTY);
                }
            }
        });
        //history
        commandCollection.put(CommandWord.HISTORY, new Command(CommandWord.HISTORY, Label.HISTORY_DESCRIPTION) {
            public void execute() {
                int historySize = getCommandHistory().size();
                if (historySize != 0) {
                    Utils.print(MessageType.HEADER, Label.HISTORY_HEADER);
                    for (String name : getCommandHistory().subList((historySize >= 5 ? historySize - 5 : 0), historySize)) {
                        Utils.print(MessageType.PART_OF_LIST, name);
                    }
                } else {
                    Utils.print(MessageType.ERROR, Label.HISTORY_IF_EMPTY);
                }

            }
        });
        //clear
        commandCollection.put(CommandWord.CLEAR, new Command(CommandWord.CLEAR, Label.CLEAR_DESCRIPTION) {
            public void execute() {
                clearDataCollection();
                Utils.print(MessageType.HEADER, Label.SUCCESSFUL);
            }
        });
        //remove_key
        commandCollection.put(CommandWord.REMOVE_KEY, new Command(CommandWord.REMOVE_KEY, Label.REMOVE_KEY_DESCRIPTION) {
            public void execute() {
                removeByKey(getArg());
                Utils.print(MessageType.HEADER, Label.SUCCESSFUL);
            }
        });
        //insert
        commandCollection.put(CommandWord.INSERT, new Command(CommandWord.INSERT, Label.INSERT_DESCRIPTION) {
            public void execute() {
                String name = getArg();
                if (dataCollection.containsKey(name)) {
                    throw new KeyAlreadyExistsException(ExceptionMessage.VALUE_ALREADY_EXISTS);
                }
                addRecord(name, new HumanBeing(generateUniqueRandomId(), userInputReader));
                Utils.print(MessageType.HEADER, Label.SUCCESSFUL);
            }
        });
        //exit
        commandCollection.put(CommandWord.EXIT, new Command(CommandWord.EXIT, Label.EXIT_DESCRIPTION) {
            public void execute() {
                Utils.exit();
            }
        });
        //update
        commandCollection.put(CommandWord.UPDATE, new Command(CommandWord.UPDATE, Label.UPDATE_DESCRIPTION) {
            public void execute() {
                int id;
                try {
                    id = Utils.parseInt(getArg());
                } catch (NumberFormatException e) {
                    throw new NumberFormatException(ExceptionMessage.CANT_PARSE_ARG);
                }
                boolean flag = false;
                for (String key : dataCollection.keySet()) {
                    if (key.equals(metaDataKey)) {
                        continue;
                    }
                    if (((HumanBeing) dataCollection.get(key)).getId().equals(id)) {
                        HumanBeing humanBeing = new HumanBeing(id, userInputReader);
                        humanBeing.setCreationDate(((HumanBeing) dataCollection.get(key)).getCreationDate());
                        updateRecord(key, humanBeing);
                        Utils.print(MessageType.HEADER, Label.SUCCESSFUL);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    throw new NoSuchElementException(ExceptionMessage.VALUE_NOT_EXISTS);
                }
            }
        });
        //execute_script
        commandCollection.put(CommandWord.EXECUTE_SCRIPT, new Command(CommandWord.EXECUTE_SCRIPT, Label.EXECUTE_SCRIPT_DESCRIPTION) {
            public void execute() throws IOException, IllegalAccessException, NoSuchFieldException {
                ScriptLoader scriptLoader = new ScriptLoader(getArg(), commandCollection);

                List<Command> commandList = null;
                try {
                    commandList = scriptLoader.load();
                } catch (FileNotFoundException e) {
                    Utils.print(MessageType.ERROR, ExceptionMessage.FILE_NOT_FOUND);
                    return;
                }
                for (Command command : commandList) {
                    Utils.fakeInput(command.getName());
                    executeCommand(command);
                }
            }
        });
        //save
        commandCollection.put(CommandWord.SAVE, new Command(CommandWord.SAVE, Label.SAVE_DESCRIPTION) {
            public void execute() throws IOException {
                String path = null;
                try {
                    path = Utils.getFilePathEnvironmentVariable();
                } catch (EnvironmentVariableNotFoundException e) {
                    Utils.print(MessageType.ERROR, ExceptionMessage.ENVIRONMENT_VARIABLE_BOT_FOUND);
                    return;
                }
                DataSaver dataSaver = new DataSaver(new JSONMap(dataCollection), new FileWriter(path));
                try {
                    dataSaver.save();
                    Utils.print(MessageType.HEADER, Label.SUCCESSFUL);
                } catch (Exception e) {
                    Utils.print(MessageType.ERROR, ExceptionMessage.WORKING_FILE_NOT_FOUND);
                }
            }
        });
        //remove_greater
        commandCollection.put(CommandWord.REMOVE_GREATER, new Command(CommandWord.REMOVE_GREATER, Label.REMOVE_GREATER_DESCRIPTION) {
            public void execute() {
                String markerName = getArg();
                List<HumanBeing> recordsList = new ArrayList<>();
                Map<Integer, String> keyByIdPair = new HashMap<>();
                for (String key : dataCollection.keySet()) {
                    if (key.equals(metaDataKey)) {
                        continue;
                    }
                    HumanBeing humanBeing = (HumanBeing) dataCollection.get(key);
                    recordsList.add(humanBeing);
                    keyByIdPair.put(humanBeing.getId(), key);
                }
                Collections.sort(recordsList);
                boolean needToRemoveFlag = false;
                for (HumanBeing record : recordsList) {
                    String key = keyByIdPair.get(record.getId());
                    if (needToRemoveFlag) {
                        removeByKey(key);
                    }
                    if (key.equals(markerName)) {
                        needToRemoveFlag = true;
                    }
                }
                if (!needToRemoveFlag) {
                    throw new NoSuchElementException(ExceptionMessage.VALUE_NOT_EXISTS);
                }
                Utils.print(MessageType.HEADER, Label.SUCCESSFUL);
            }
        });
        //print_descending
        commandCollection.put(CommandWord.PRINT_DESCENDING, new Command(CommandWord.PRINT_DESCENDING, Label.PRINT_DESCENDING_DESCRIPTION) {
            public void execute() {
                List<HumanBeing> recordsList = new ArrayList<>();
                Map<Integer, String> keyByIdPair = new HashMap<>();
                for (String key : dataCollection.keySet()) {
                    if (key.equals(metaDataKey)) {
                        continue;
                    }
                    HumanBeing humanBeing = (HumanBeing) dataCollection.get(key);
                    recordsList.add(humanBeing);
                    keyByIdPair.put(humanBeing.getId(), key);
                }
                if (recordsList.size() == 0) {
                    Utils.print(MessageType.ERROR, Label.PRINT_DESCENDING_IF_EMPTY);
                    return;
                }
                recordsList.sort(Collections.reverseOrder());
                Utils.print(MessageType.HEADER, Label.PRINT_DESCENDING_HEADER);
                for (HumanBeing record : recordsList) {
                    Utils.print(MessageType.HEADER, keyByIdPair.get(record.getId()));
                    record.print();
                }
            }
        });
        //filter_contains_soundtrack_name
        commandCollection.put(CommandWord.FILTER_CONTAINS_SOUNDTRACK_NAME, new Command(CommandWord.FILTER_CONTAINS_SOUNDTRACK_NAME, Label.FILTER_CONTAINS_SOUNDTRACK_NAME_DESCRIPTION) {
            public void execute() {
                String soundtrackName = getArg();
                for (String key : dataCollection.keySet()) {
                    if (key.equals(metaDataKey)) {
                        continue;
                    }
                    HumanBeing humanBeing = (HumanBeing) dataCollection.get(key);
                    if ((humanBeing).getSoundtrackName().contains(soundtrackName)) {
                        Utils.print(MessageType.HEADER, key);
                        humanBeing.print();
                    }
                }
            }
        });
        //remove_all_by_minutes_of_waiting
        commandCollection.put(CommandWord.REMOVE_ALL_BY_MINUTES_OF_WAITING, new Command(CommandWord.REMOVE_ALL_BY_MINUTES_OF_WAITING, Label.REMOVE_ALL_BY_MINUTES_OF_WAITING_DESCRIPTION) {
            public void execute() {
                float minutesOfWaiting;
                try {
                    minutesOfWaiting = Utils.parseFloat(getArg());
                } catch (NumberFormatException e) {
                    throw new NumberFormatException(ExceptionMessage.CANT_PARSE);
                }
                List<String> keysToRemove = new ArrayList<>();
                for (String key : dataCollection.keySet()) {
                    if (key.equals(metaDataKey)) {
                        continue;
                    }
                    if (((HumanBeing) dataCollection.get(key)).getMinutesOfWaiting() == minutesOfWaiting) {
                        keysToRemove.add(key);
                    }
                }
                for (String key : keysToRemove) {
                    removeByKey(key);
                }
                Utils.print(MessageType.HEADER, Label.SUCCESSFUL);
            }
        });
        //replace_if_lowe
        commandCollection.put(CommandWord.REPLACE_IF_LOWE, new Command(CommandWord.REPLACE_IF_LOWE, Label.REPLACE_IF_LOWE_DESCRIPTION) {
            public void execute() {
                String key = getArg();
                if (dataCollection.get(key) == null){
                    throw new NoSuchElementException(ExceptionMessage.VALUE_NOT_EXISTS);
                }
                HumanBeing newLine = new HumanBeing(generateUniqueRandomId(), userInputReader);
                HumanBeing oldLine = (HumanBeing) dataCollection.get(key);
                if (newLine.compareTo(oldLine) < 0) {
                    updateRecord(key, newLine);
                }
                Utils.print(MessageType.HEADER, Label.SUCCESSFUL);
            }
        });
        return commandCollection;
    }

    /**
     * Generates unique random id
     *
     * @return unuque random id
     */

    private int generateUniqueRandomId() {
        int randomId;
        inf:
        while (true) {
            randomId = Utils.generateRandomId();
            for (String key : dataCollection.keySet()) {
                if (key.equals(metaDataKey)) {
                    continue;
                }
                Object obj = dataCollection.get(key);
                if (randomId == ((HumanBeing) obj).getId()) {
                    continue inf;
                }
            }
            break;
        }
        return randomId;
    }

    /**
     * Adds record to data collection
     *
     * @param key record with be accessible by this key
     * @param obj object to add
     * @throws SecurityException         if user tried to add something by reserved keys
     * @throws KeyAlreadyExistsException if user tried to add something by already exists key
     */

    private void addRecord(String key, Object obj) throws SecurityException, KeyAlreadyExistsException {
        if (key.equals(metaDataKey)) {
            throw new SecurityException(ExceptionMessage.RESERVED_VALUE);
        }
        if (dataCollection.containsKey(key)) {
            throw new KeyAlreadyExistsException(ExceptionMessage.VALUE_ALREADY_EXISTS);
        }
        dataCollection.put(key, obj);
    }

    /**
     * Updates records in data collection
     *
     * @param key        replacing will be on this key
     * @param humanBeing object to update
     * @throws SecurityException      if user tried to update something by reserved keys
     * @throws NoSuchElementException if user tried to update something by still not created key
     */

    private void updateRecord(String key, HumanBeing humanBeing) throws SecurityException, NoSuchElementException {
        if (key.equals(metaDataKey)) {
            throw new SecurityException(ExceptionMessage.RESERVED_VALUE);
        }
        if (!dataCollection.containsKey(key)) {
            throw new NoSuchElementException(ExceptionMessage.VALUE_NOT_EXISTS);
        }
        dataCollection.replace(key, humanBeing);
    }

    /**
     * Updates records in data collection
     *
     * @param key removing will be on this key
     * @throws SecurityException      if user tried to remove something by reserved keys
     * @throws NoSuchElementException if user tried to remove something by still not created key
     */

    private void removeByKey(String key) throws SecurityException, NoSuchElementException {
        if (key.equals(metaDataKey)) {
            throw new SecurityException(ExceptionMessage.RESERVED_VALUE);
        }
        if (!dataCollection.containsKey(key)) {
            throw new NoSuchElementException(ExceptionMessage.ELEMENT_NOT_FOUNT);
        }
        dataCollection.remove(key);
    }

    /**
     * Generates empty data collection
     *
     * @return still created {@code dataCollection}
     */

    private Map<String, Object> generateEmptyDataCollection() {
        dataCollection = new TreeMap<>();
        dataCollection.put(metaDataKey, new MetaData());
        return dataCollection;
    }

    /**
     * Clears not reserved {@link #dataCollection}'s fields
     */

    private void clearDataCollection() {
        MetaData metaData = (MetaData) dataCollection.get(metaDataKey);
        dataCollection = new TreeMap<>();
        dataCollection.put(metaDataKey, metaData);
    }

    /**
     * Validates {@link #dataCollection}
     *
     * @param dataCollection field needs to validate
     */

    private Map<String, Object> validate(Map<String, Object> dataCollection) {
        // todo
        return dataCollection;
    }

    /**
     * Parses command by raw user input
     *
     * @param raw raw user input
     * @return parsed command
     * @throws NoSuchElementException if cannot to find inputted command
     */

    public Command parseCommand(String raw) throws NoSuchElementException {
        String[] splitted = raw.split("\\s+");
        String commandName = splitted[0];
        if (commandCollection.get(commandName) == null) {
            throw new NoSuchElementException(ExceptionMessage.WRONG_COMMAND);
        }
        Command command = commandCollection.get(commandName).clone();
        if (splitted.length > 1) {
            String arg = splitted[1];
            command.acceptArg(arg);
        }
        return command;
    }

    /**
     * Parses command by raw user input statically
     *
     * @param raw raw user input
     * @return parsed command
     * @throws NoSuchElementException if cannot to find inputted command
     */

    public static Command parseCommand(String raw, Map<String, Command> commandCollection) throws NoSuchElementException {
        String[] splitted = raw.split("\\s+");
        String commandName = splitted[0];
        Command command = commandCollection.get(commandName);
        if (command == null) {
            throw new NoSuchElementException(ExceptionMessage.CANT_PARSE_COMMAND);
        }
        if (splitted.length > 1) {
            String arg = splitted[1];
            command.acceptArg(arg);
        }
        return command;
    }

    /**
     * Adds line to command history
     *
     * @param name command's name
     */

    private void addToCommandHistory(String name) {
        commandHistory.add(name);
    }

    /**
     * @return commandHistory
     */

    private List<String> getCommandHistory() {
        return commandHistory;
    }

    /**
     * Executes command
     */

    public void executeCommand(Command command) throws NoSuchFieldException, IllegalAccessException, IOException {
        command.execute();
        addToCommandHistory(command.getName());
    }
}
