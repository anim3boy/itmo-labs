package me.emokid.json;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static me.emokid.json.JSONUtils.detectTypeOfValue;

public class JSONList implements JSONContainableObject<List<Object>, Integer> {

    /**
     * Core JSON data object
     */

    private final List<Object> data;

    /**
     * Sets {@link #JSONList}'s core data object by raw text
     *
     * @param text raw text
     * @throws IllegalArgumentException if raw cannot be parsed
     */

    public JSONList(String text) throws IllegalArgumentException {
        data = parse(text).getData();
    }

    /**
     * Sets {@link #JSONList}'s core data
     *
     * @param data core data object
     */

    public JSONList(ArrayList<Object> data) {
        this.data = data;
    }

    /**
     * @return data
     */

    public List<Object> getData() {
        return data;
    }

    /**
     * @return {@code JSONMap} object by list id
     * @throws NoSuchElementException if element cannot be found
     */

    public JSONMap getJSONMap(Integer id) {
        if (getData().get(id) == null) throw new NoSuchElementException();
        return (JSONMap) getData().get(id);
    }

    /**
     * @return {@code String} object by list id
     * @throws NoSuchElementException if element cannot be found
     */

    public String getString(Integer id) {
        if (getData().get(id) == null) throw new NoSuchElementException();
        return (String) getData().get(id);
    }

    /**
     * @return {@code Double} object by list id
     * @throws NoSuchElementException if element cannot be found
     */

    public JSONNumber getNumber(Integer id) {
        if (getData().get(id) == null) throw new NoSuchElementException();
        return (JSONNumber) getData().get(id);
    }

    /**
     * @return {@code JSONList} object by list id
     * @throws NoSuchElementException if element cannot be found
     */

    public JSONList getJSONList(Integer id) {
        if (getData().get(id) == null) throw new NoSuchElementException();
        return (JSONList) getData().get(id);
    }

    /**
     * @return {@code Boolean} object by list id
     * @throws NoSuchElementException if element cannot be found
     */

    public Boolean getBoolean(Integer id) {
        if (getData().get(id) == null) throw new NoSuchElementException();
        return (boolean) getData().get(id);
    }


    /**
     * Parses raw text to {@code JSONList}
     *
     * @param text raw text
     * @throws IllegalArgumentException if raw text cannot be parsed
     */

    private JSONList parse(String text) throws IllegalArgumentException {

        ArrayList<Object> data = new ArrayList<>();
        boolean isEscaping = false;
        char currentlyEscapingPrefix = 0;
        int nestingDepth = 0;

        text = text.trim();
        text = text.substring(1, text.length() - 1).trim();
        text += ",";
        StringBuilder buffer = new StringBuilder();

        String value = "";

        for (int index = 0; index < text.length(); index++) {
            char sym = text.charAt(index);
            if (!isEscaping && String.valueOf(sym).matches("\\s")) continue;

            if (!isEscaping) {
                if (sym == JSONConstant.ELEMENTS_SEPARATOR) {
                    value = buffer.toString();
                    buffer = new StringBuilder();
                    JSONType typeOfValue = detectTypeOfValue(value);
                    switch (Objects.requireNonNull(typeOfValue)) {
                        case JSON_OBJECT:
                            data.add(parse(value));
                            break;
                        case JSON_ARRAY:
                            data.add(new JSONList(value));
                            break;
                        case NUMBER:
                            data.add(Double.parseDouble(value));
                            break;
                        case NULL:
                            data.add(null);
                            break;
                        case BOOLEAN:
                            if (value.equals(JSONConstant.BOOLEAN_TRUE_VALUE)) data.add(true);
                            else data.add(false);
                            break;
                        case STRING:
                            value = value.substring(1, value.length() - 1);
                            data.add(value);
                            break;
                    }
                    continue;
                }
            }

            if (isEscaping && sym == currentlyEscapingPrefix &&
                    (currentlyEscapingPrefix == JSONConstant.OBJECT_PREFIX || currentlyEscapingPrefix == JSONConstant.ARRAY_PREFIX)) {
                nestingDepth++;
            }
            if (!isEscaping && (sym == JSONConstant.STRING_PREFIX || sym == JSONConstant.OBJECT_PREFIX || sym == JSONConstant.ARRAY_PREFIX)) {
                isEscaping = true;
                currentlyEscapingPrefix = sym;
            } else if (isEscaping && ((currentlyEscapingPrefix == JSONConstant.STRING_POSTFIX && sym == JSONConstant.STRING_POSTFIX) ||
                    (currentlyEscapingPrefix == JSONConstant.OBJECT_PREFIX && sym == JSONConstant.OBJECT_POSTFIX) ||
                    (currentlyEscapingPrefix == JSONConstant.ARRAY_PREFIX && sym == JSONConstant.ARRAY_POSTFIX))) {
                if (nestingDepth > 0) nestingDepth--;
                else isEscaping = false;
            }
            buffer.append(sym);
        }
        return new JSONList(data);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return this.getData() == ((JSONList) obj).getData();
    }

}
