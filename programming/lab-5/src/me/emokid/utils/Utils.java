package me.emokid.utils;


import me.emokid.IO.IOConstants;
import me.emokid.command.ExceptionMessage;
import me.emokid.json.JSONUtils;
import me.emokid.scheme.Mood;
import me.emokid.utils.exception.EnvironmentVariableNotFoundException;

import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;

public final class Utils {

    /**
     * User input prefix
     */

    private static final String USER_INPUT_PREFIX = "> ";

    /**
     * Tab constant
     */

    private static final String TAB = "    ";

    /**
     * List prefix emoji
     */

    private static final String PART_OF_LIST_PREFIX = "\uD83D\uDCD8";

    /**
     * Header prefix emoji
     */

    private static final String HEADER_PREFIX = "\uD83D\uDCD7";

    /**
     * Error prefix emoji
     */

    private static final String ERROR_PREFIX = "\uD83D\uDCD5";

    /**
     * Max id constant
     */

    private static final int MAX_ID = 1337;

    /**
     * Checks if String is numeric
     * that draw the image will incrementally paint on the screen.
     *
     * @param  str  source String
     * @return      {@code true} if string is numeric else {@code false}
     */

    public static boolean isNumeric(String str){
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Simple random int generator
     *
     * @param  min  minimal int value
     * @param  max  maximal int value
     *
     * @return      random int in round
     */

    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Generates random id using {@link #getRandomNumber(int, int)} ()}
     *
     * @return      random id
     */

    public static int generateRandomId() {
        return getRandomNumber(0, MAX_ID);
    }

    /**
     * Prints some string with {@link #USER_INPUT_PREFIX} like if it was user input
     *
     * @param  input  string to print
     */

    public static void fakeInput(String input) {
        System.out.println(USER_INPUT_PREFIX + input);
    }

    /**
     * Prints {@link #USER_INPUT_PREFIX}
     */

    public static void fakeInput() {
        System.out.print(USER_INPUT_PREFIX);
    }

    /**
     * @return user friendly DateTimeFormatter
     */

    public static DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm:ss");
    }

    /**
     * Prints some objects with null padding
     *
     *  @param  mt  type of message
     *  @param  objects  some objects to print
     */

    public static void print(MessageType mt, Object... objects) {
        print(0, true, mt, objects);
    }

    /**
     * Prints some objects with user padding with ln
     *
     *  @param  padding  print padding
     *  @param  mt  type of message
     *  @param  objects  some objects to print
     */

    public static void print(int padding, MessageType mt, Object... objects) {
        print(padding, true, mt, objects);
    }

    /**
     * Prints some objects with user padding and with user ln
     *
     *  @param  padding  print padding
     *  @param  isLn  print new line after or not
     *  @param  mt  type of message
     *  @param  objects  some objects to print
     */

    public static void print(int padding, boolean isLn, MessageType mt, Object... objects) {
        StringBuilder output = new StringBuilder();
        if (mt == MessageType.PART_OF_LIST) output.append(PART_OF_LIST_PREFIX);
        if (mt == MessageType.ERROR) output.append(ERROR_PREFIX);
        if (mt == MessageType.HEADER) output.append(HEADER_PREFIX);
        output.append(" ");
        for (Object obj : objects) {
            output.append(obj);
            output.append(" ");
        }
        output = new StringBuilder(output.toString().trim());
        for (int i = 0; i < padding; i++) output.insert(0, TAB);
        if (isLn) System.out.println(output);
        else System.out.print(output);
    }

    /**
     * @param obj   source object
     *
     * @return      full class name of object
     */

    public static String getFullClassName(Object obj) {
        return obj.getClass().getName();
    }

    /**
     * @param obj   source object
     *
     * @return      class name of object without package name
     */

    public static String getLastPartOfClassName(Object obj) {
        return obj.getClass().getSimpleName();
    }

    /**
     * Prints new line
     */

    public static void ln() {
        System.out.println();
    }

    /**
     * Print data field
     *
     *  @param  padding  print padding
     *  @param  field  data field needs to print
     *  @param  value  data fields's value
     */

    public static void printField(int padding, Field field, String value) {
        print(padding, MessageType.PART_OF_LIST, field.getName(), "(" + field.getType().getSimpleName() + "):", value);
    }

    /**
     * Modificated int parser
     *
     * @param  string  source string
     *
     * @return      return {@code null} if string is {@code null} else parsed int
     *
     * @throws      NumberFormatException if string couldn't be parsed
     *
     */

    public static Integer parseInt(String string) throws NumberFormatException{
        if (string == null) return null;
        else return JSONUtils.parseJSONNumberToInt(string);
    }

    /**
     * Modificated long parser
     *
     * @param  string  source string
     *
     * @return      return {@code null} if string is {@code null} else parsed long
     *
     * @throws      NumberFormatException if string couldn't be parsed
     *
     */

    public static Long parseLong(String string) throws NumberFormatException{
        if (string == null) return null;
        else return JSONUtils.parseJSONNumberToLong(string);
    }

    /**
     * Modificated boolean parser
     *
     * @param  string  source string
     *
     * @return      return {@code null} if string is {@code null} else parsed boolean
     *
     * @throws      NumberFormatException if string couldn't be parsed
     *
     */

    public static Boolean parseBoolean(String string) throws NumberFormatException{
        if (string == null) return null;
        if (string.equalsIgnoreCase("true")) return true;
        if (string.equalsIgnoreCase("false")) return false;
        throw new NumberFormatException();
    }

    /**
     * Modificated float parser
     *
     * @param  string  source string
     *
     * @return      return {@code null} if string is {@code null} else parsed float
     *
     * @throws      NumberFormatException if string couldn't be parsed
     *
     */

    public static Float parseFloat(String string) throws NumberFormatException{
        if (string == null) {
            return null;
        }
        else return Float.parseFloat(string);
    }

    /**
     * Modificated double parser
     *
     * @param  string  source string
     *
     * @return      return {@code null} if string is {@code null} else parsed double
     *
     * @throws      NumberFormatException if string couldn't be parsed
     *
     */

    public static Double parseDouble(String string) throws NumberFormatException{
        if (string == null) return null;
        else return Double.parseDouble(string);
    }

    /**
     * Modificated enum parser
     *
     * @param  string  source string
     *
     * @return      return {@code null} if string is {@code null} else parsed enum
     *
     * @throws      IllegalArgumentException if string couldn't be parsed
     *
     */

    public static Mood parseEnum(String string) throws IllegalArgumentException{
        if (string == null) {
            return null;
        }
        else {
            try{
                return Mood.valueOf(string.toUpperCase());
            }
            catch (IllegalArgumentException e){
                throw new IllegalArgumentException(ExceptionMessage.ENUM_NOT_FOUND);
            }
        }
    }

    /**
      * @return operable JSON file path from system environment variable
     */

    public static String getFilePathEnvironmentVariable(){
        String path = System.getenv(IOConstants.ENVIRONMENT_VARIABLE_NAME);
        if(path == null){
            throw new EnvironmentVariableNotFoundException();
        }
        return path;
    }

    /**
     * Calls system.exit(0)
     */
    public static void exit(){
        System.exit(0);
    }
}
