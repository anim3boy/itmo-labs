package me.emokid.json;

import me.emokid.command.ExceptionMessage;
import me.emokid.json.exception.UndetectableJSONTypeException;
import me.emokid.scheme.HumanBeing;
import me.emokid.scheme.MetaData;
import me.emokid.utils.Utils;

import java.util.Map;
import java.util.TreeMap;

import static me.emokid.scheme.MetaData.metaDataKey;

public class JSONUtils {

    /**
     * Regex string whitelist for input characters
     */

    private static final String INPUT_CHARACTERS_WHITELIST_REGEX = "[_а-яА-Яa-zA-Z0-9 ,.!?@#$%^&*(){}\\[\\]:;\\-/]*";

    /**
     * Double numbers delimiter constant
     */

    private static final String NUMBER_DELIMITER = ".";

    /**
     * Double numbers delimiter regex string
     */

    private static final String NUMBER_DELIMITER_REGEX = "\\.";

    /**
     * Nulls sequence regex string
     */

    private static final String NULL_NUMBERS_SEQUENCE_REGEX = "0+";

    /**
     * Detects JSON type of value
     *
     * @param text source string
     * @return {@code JSONType} of value
     * @throws UndetectableJSONTypeException if type of value cannot be found
     */

    public static JSONType detectTypeOfValue(String text) throws UndetectableJSONTypeException {
        text = text.trim();
        if (text.charAt(0) == JSONConstant.STRING_PREFIX && text.charAt(text.length() - 1) == JSONConstant.STRING_POSTFIX)
            return JSONType.STRING;
        if (text.charAt(0) == JSONConstant.OBJECT_PREFIX && text.charAt(text.length() - 1) == JSONConstant.OBJECT_POSTFIX)
            return JSONType.JSON_OBJECT;
        if (text.charAt(0) == JSONConstant.ARRAY_PREFIX && text.charAt(text.length() - 1) == JSONConstant.ARRAY_POSTFIX)
            return JSONType.JSON_ARRAY;
        if (text.equals(JSONConstant.BOOLEAN_TRUE_VALUE) || text.equals(JSONConstant.BOOLEAN_FALSE_VALUE))
            return JSONType.BOOLEAN;
        if (text.equals(JSONConstant.NULL_VALUE)) return JSONType.NULL;
        if (Utils.isNumeric(text)) return JSONType.NUMBER;

        throw new UndetectableJSONTypeException(ExceptionMessage.WRONG_JSON_TYPE);
    }

    /**
     * Parses JSON number to Java Integer
     *
     * @param string source string
     * @return parsed integer
     * @throws NumberFormatException if source string cannot be parsed
     */

    public static int parseJSONNumberToInt(String string) throws NumberFormatException {
        if (string.contains(NUMBER_DELIMITER)) {
            String[] decomposedNumber = string.split(NUMBER_DELIMITER_REGEX);
            String beforeDot = decomposedNumber[0];
            String afterDot = decomposedNumber[1];
            if (!afterDot.matches(NULL_NUMBERS_SEQUENCE_REGEX)) {
                throw new NumberFormatException();
            }
            string = beforeDot;
        }
        return Integer.parseInt(string);
    }

    /**
     * Parses JSON number to Java Long
     *
     * @param string source string
     * @return parsed long
     * @throws NumberFormatException if source string cannot be parsed
     */

    public static long parseJSONNumberToLong(String string) throws NumberFormatException {
        if (string.contains(NUMBER_DELIMITER)) {
            String[] decomposedNumber = string.split(NUMBER_DELIMITER_REGEX);
            String beforeDot = decomposedNumber[0];
            String afterDot = decomposedNumber[1];
            if (!afterDot.matches(NULL_NUMBERS_SEQUENCE_REGEX)) {
                throw new NumberFormatException();
            }
            string = beforeDot;
        }
        return Long.parseLong(string);
    }

    /**
     * Converts JSON objects to java prototypes
     *
     * @param jsonMap source {@code JSONMap} object
     * @return processed map
     * @throws IllegalStateException if {@code JSONMap} don't contains meta data
     */

    public static Map<String, Object> convertJSONToJavaObjects(JSONMap jsonMap) {
        Map<String, Object> m = jsonMap.getData();
        Map<String, Object> resultMap = new TreeMap<>();
        boolean metaDataCorruptedFlag = true;
        for (String key : m.keySet()) {
            if (key.equals(metaDataKey)) {
                resultMap.put(key, new MetaData(jsonMap.getJSONMap(key)));
                metaDataCorruptedFlag = false;
            } else {
                resultMap.put(key, new HumanBeing((JSONMap) m.get(key)));
            }
        }
        if (metaDataCorruptedFlag) {
            throw new IllegalStateException(ExceptionMessage.META_DATA_CORRUPTED);
        }
        return resultMap;
    }

    /**
     * Detects dangerous characters
     *
     * @param raw source {@code String}
     * @return raw if dangerous characters not founds
     * @throws IllegalArgumentException if raw contains dangerous characters
     */

    public static String JSONEscapeString(String raw) {
        if (!raw.matches(INPUT_CHARACTERS_WHITELIST_REGEX)) {
            throw new IllegalArgumentException(ExceptionMessage.ILLEGAL_SYMBOL);
        }
        return raw;
    }
}
