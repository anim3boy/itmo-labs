package me.emokid.IO;

import me.emokid.json.JSONUtils;
import me.emokid.utils.MessageType;
import me.emokid.utils.Utils;

import java.lang.reflect.Field;
import java.util.Scanner;

public class UserInputReader {

    /**
     * Scanner object
     */
    private final Scanner scanner;

    /**
     * Sets scanner object
     * @param scanner scanner to set
     */

    public UserInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads user input while he writes commands
     * @return escaped user input
     */

    public String input() {
        return JSONUtils.JSONEscapeString(scanner.nextLine());
    }

    /**
     * Reads user input while he writes data fields values
     * @param padding padding depth
     * @param field field to set
     * @return user input
     */

    public String inputField(int padding, Field field) {
        Utils.print(padding, false, MessageType.PART_OF_LIST, field.getName(), "(" + field.getType().getSimpleName() + ")");
        if (field.getType().isEnum()) {
            StringBuilder enumOutput = new StringBuilder();
            enumOutput.append(" [");
            for (Object str : field.getType().getEnumConstants()){
                enumOutput.append(str).append(", ");
            }
            enumOutput = new StringBuilder(enumOutput.substring(0, enumOutput.length() - 2));
            enumOutput.append("]");
            System.out.print(enumOutput);
        }
        System.out.print(": ");
        String input = JSONUtils.JSONEscapeString(scanner.nextLine());
        if (input.isEmpty()){
            return null;
        }
        return input.trim();
    }
}
