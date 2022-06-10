package me.emokid;

import me.emokid.IO.DataLoader;
import me.emokid.IO.Loader;
import me.emokid.IO.UserInputReader;
import me.emokid.command.CommandProcessor;
import me.emokid.command.Command;
import me.emokid.command.ExceptionMessage;
import me.emokid.command.Label;
import me.emokid.json.JSONMap;
import java.nio.*;
import me.emokid.utils.exception.EnvironmentVariableNotFoundException;
import me.emokid.utils.MessageType;
import me.emokid.utils.Utils;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static me.emokid.json.JSONUtils.convertJSONToJavaObjects;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {

        // check if environment variable is not set
        String path = null;
        try {
            path = Utils.getFilePathEnvironmentVariable();
        } catch (EnvironmentVariableNotFoundException e) {
            Utils.print(MessageType.ERROR, ExceptionMessage.ENVIRONMENT_VARIABLE_BOT_FOUND);
            Utils.exit();
        }
        // check if file not exists
        if (!new File(path).exists()) {
            Utils.print(MessageType.ERROR, ExceptionMessage.WORKING_FILE_NOT_FOUND);
            Utils.exit();
        }
        // print that file is exists
        Utils.print(MessageType.HEADER, Label.ENVIRONMENT_VARIABLE_IS_FOUND, path);

        UserInputReader userInputScanner = new UserInputReader(new Scanner(System.in));
        CommandProcessor commandProcessor;
        try {
            Loader<JSONMap> dataLoader = new DataLoader(new Scanner(new File(path)));
            commandProcessor = new CommandProcessor(convertJSONToJavaObjects(dataLoader.load()), userInputScanner);
            Utils.print(MessageType.HEADER, Label.DATA_IS_LOADED_SUCCESSFUL);
        } catch (FileNotFoundException e) {
            Utils.print(MessageType.ERROR, ExceptionMessage.CANT_LOAD_DATA);
            commandProcessor = new CommandProcessor(userInputScanner);
        } catch (IllegalArgumentException e) {
            Utils.print(MessageType.ERROR, ExceptionMessage.CANT_PARSE_JSON);
            commandProcessor = new CommandProcessor(userInputScanner);
        }

        while (true) {
            try {
                Utils.fakeInput();
                Command command = commandProcessor.parseCommand(userInputScanner.input());
                commandProcessor.executeCommand(command);
            } catch (Exception e) {
                // CTRL+D
                if(e instanceof NoSuchElementException && e.getMessage().equals("No line found")){
                    Utils.exit();
                }
                Utils.print(MessageType.ERROR, e.getMessage());
            }
        }
    }
}
