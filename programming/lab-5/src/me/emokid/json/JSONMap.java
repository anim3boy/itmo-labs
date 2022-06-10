package me.emokid.json;

import me.emokid.scheme.MetaData;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import static me.emokid.json.JSONUtils.detectTypeOfValue;

public class JSONMap implements JSONContainableObject<Map<String, Object>, String> {

    /**
     * Core JSON data object
     */

    private final Map<String, Object> data;

    /**
     * Sets {@link #JSONMap}'s core data object by raw text
     *
     * @param text raw text
     * @throws IllegalArgumentException if raw cannot be parsed
     */

    public JSONMap(String text) throws IllegalArgumentException {
        JSONMap jsonMap;
        try {
            jsonMap = parse(text);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        data = jsonMap.getData();
    }

    /**
     * Sets {@link #JSONMap}'s core data
     *
     * @param data core data object
     */

    public JSONMap(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * @return data
     */

    public Map<String, Object> getData() {
        return data;
    }

    /**
     * @return {@code JSONMap} object by list key
     * @throws NoSuchElementException if element cannot be found
     */

    public JSONMap getJSONMap(String key) {
        if (getData().get(key) == null) {
            throw new NoSuchElementException();
        }
        return (JSONMap) getData().get(key);
    }

    /**
     * @return {@code String} object by list key
     * @throws NoSuchElementException if element cannot be found
     */

    public String getString(String key) {
        if (getData().get(key) == null) {
            throw new NoSuchElementException();
        }
        return (String) getData().get(key);
    }

    /**
     * @return {@code Double} object by list key
     * @throws NoSuchElementException if element cannot be found
     */

    public JSONNumber getNumber(String key) {
        if (getData().get(key) == null) {
            throw new NoSuchElementException();
        }
        return (JSONNumber) getData().get(key);
    }

    /**
     * @return {@code JSONList} object by list key
     * @throws NoSuchElementException if element cannot be found
     */

    public JSONList getJSONList(String key) {
        if (getData().get(key) == null) {
            throw new NoSuchElementException();
        }
        return (JSONList) getData().get(key);
    }

    /**
     * @return {@code Boolean} object by list key
     * @throws NoSuchElementException if element cannot be found
     */

    public Boolean getBoolean(String key) {
        if (getData().get(key) == null) {
            throw new NoSuchElementException();
        }
        return (boolean) getData().get(key);
    }

    /**
     * Parses raw text to {@code JSONMap}
     *
     * @param text raw text
     * @throws IllegalArgumentException if raw text cannot be parsed
     */

    private JSONMap parse(String text) throws IllegalArgumentException {
        TreeMap<String, Object> data = new TreeMap<>();
        boolean isEscaping = false;
        char currentlyEscapingPrefix = 0;
        int nestingDepth = 0;
        // unpacking
        text = text.trim();
        text = text.substring(1, text.length() - 1).trim();
        text += ",";
        String buffer = "";

        String key = "";
        String value = "";

        for (int index = 0; index < text.length(); index++) {
            char sym = text.charAt(index);
            if (!isEscaping && String.valueOf(sym).matches("\\s")) continue;

            if (!isEscaping) {
                if (sym == JSONConstant.KEY_VALUE_SEPARATOR) {
                    key = buffer.substring(1, buffer.length() - 1);
                    buffer = "";
                    continue;
                }
                if (sym == JSONConstant.ELEMENTS_SEPARATOR) {
                    value = buffer;
                    buffer = "";

                    JSONType typeOfValue = detectTypeOfValue(value);
                    if (typeOfValue == JSONType.JSON_OBJECT) data.put(key, parse(value));
                    else if (typeOfValue == JSONType.JSON_ARRAY) data.put(key, new JSONList(value));
                    else if (typeOfValue == JSONType.NUMBER) data.put(key, new JSONNumber(value));
                    else if (typeOfValue == JSONType.STRING) {
                        value = value.substring(1, value.length() - 1);
                        data.put(key, value);
                    } else if (typeOfValue == JSONType.NULL) data.put(key, null);
                    else if (typeOfValue == JSONType.BOOLEAN) {
                        if (value.equals(JSONConstant.BOOLEAN_TRUE_VALUE)) data.put(key, true);
                        else data.put(key, false);
                    }

                    //todo exception

                    continue;
                }
            }

            if (isEscaping && sym == currentlyEscapingPrefix && (currentlyEscapingPrefix == JSONConstant.OBJECT_PREFIX || currentlyEscapingPrefix == JSONConstant.ARRAY_PREFIX)) {
                nestingDepth++;
            }
            if (!isEscaping && (sym == JSONConstant.STRING_PREFIX || sym == JSONConstant.OBJECT_PREFIX || sym == JSONConstant.ARRAY_PREFIX)) {
                isEscaping = true;
                currentlyEscapingPrefix = sym;
            } else if (isEscaping && ((currentlyEscapingPrefix == JSONConstant.STRING_POSTFIX && sym == JSONConstant.STRING_POSTFIX) || (currentlyEscapingPrefix == JSONConstant.OBJECT_PREFIX && sym == JSONConstant.OBJECT_POSTFIX) || (currentlyEscapingPrefix == JSONConstant.ARRAY_PREFIX && sym == JSONConstant.ARRAY_POSTFIX))) {
                if (nestingDepth > 0) nestingDepth--;
                else isEscaping = false;
            }
            buffer += sym;
        }
        return new JSONMap(data);
    }


    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(JSONConstant.OBJECT_PREFIX);
        for (Map.Entry<String, Object> pair : data.entrySet()) {
            // key
            Object value = pair.getValue();
            String key = pair.getKey();
            output.append(JSONConstant.STRING_PREFIX);
            output.append(key);
            output.append(JSONConstant.STRING_POSTFIX);
            output.append(JSONConstant.KEY_VALUE_SEPARATOR);
            //value

            try {
                output.append(valueToString(value));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            output.append(JSONConstant.ELEMENTS_SEPARATOR);
        }

        output = new StringBuilder(output.substring(0, output.length() - 1));
        output.append(JSONConstant.OBJECT_POSTFIX);
        return output.toString();
    }

    /**
     * Converts java object to JSON representation
     *
     * @param value source object
     * @throws IllegalAccessException if something went wrong until getting fiels value
     */

    private String valueToString(Object value) throws IllegalAccessException {
        StringBuilder output = new StringBuilder();

        if (value instanceof String) {
            output.append(JSONConstant.STRING_PREFIX);
            output.append(value);
            output.append(JSONConstant.STRING_POSTFIX);
        } else if (value instanceof Boolean) {
            output.append((boolean) value);
        } else if ((value instanceof Integer) ||
                value instanceof Float ||
                value instanceof Long ||
                value instanceof Double) {
            output.append(value);
        } else if (value.getClass().isEnum()) {
            output.append(JSONConstant.STRING_PREFIX);
            output.append(value);
            output.append(JSONConstant.STRING_POSTFIX);
        } else if (value instanceof MetaData) {
            output.append(JSONConstant.OBJECT_PREFIX);
            output.append(JSONConstant.STRING_PREFIX);
            output.append("date");
            output.append(JSONConstant.STRING_POSTFIX);
            output.append(JSONConstant.KEY_VALUE_SEPARATOR);
            output.append(JSONConstant.STRING_PREFIX);
            output.append(((MetaData) value).getDate().toString());
            output.append(JSONConstant.STRING_POSTFIX);
            output.append(JSONConstant.OBJECT_POSTFIX);
        } else if (value instanceof LocalDateTime) {
            output.append(JSONConstant.STRING_PREFIX);
            output.append(value);
            output.append(JSONConstant.STRING_POSTFIX);
        } else {
            output.append(JSONConstant.OBJECT_PREFIX);
            for (Field field : value.getClass().getDeclaredFields()) {
                String key = field.getName();
                field.setAccessible(true);
                Object _value = field.get(value);
                output.append(JSONConstant.STRING_PREFIX);
                output.append(key);
                output.append(JSONConstant.STRING_POSTFIX);
                output.append(JSONConstant.KEY_VALUE_SEPARATOR);
                output.append(valueToString(_value));

                output.append(JSONConstant.ELEMENTS_SEPARATOR);
            }
            output = new StringBuilder(output.substring(0, output.length() - 1));
            output.append(JSONConstant.OBJECT_POSTFIX);
        }

        return output.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return this.getData() == ((JSONMap) obj).getData();
    }
}