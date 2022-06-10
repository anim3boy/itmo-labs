package me.emokid.IO;

import me.emokid.command.Command;
import me.emokid.command.CommandProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ScriptLoader implements Loader<List<Command>>{

    /**
     * Path where loader takes file
     */

    private final String path;

    /**
     * Command collection for parsing
     */

    private final Map<String, Command> commandCollection;

    /**
     * Sets path and command collection
     * @param path path to set
     * @param commandCollection command collection to set
     */

    public ScriptLoader(String path, Map<String, Command> commandCollection) {
        this.path = path;
        this.commandCollection = commandCollection;
    }

    /**
     * Loads data from file to list of commands
     * @return command list
     * @throws FileNotFoundException if file not found
     */

    public List<Command> load() throws FileNotFoundException {
        List<Command> commands = new ArrayList<>();
        Scanner sc;
        sc = new Scanner(new File(path));
        while (sc.hasNextLine()) {
            CommandProcessor.parseCommand(sc.nextLine(), this.commandCollection);
        }
        return commands;
    }
}
