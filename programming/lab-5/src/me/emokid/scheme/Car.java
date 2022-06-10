package me.emokid.scheme;

import me.emokid.IO.UserInputReader;
import me.emokid.command.ExceptionMessage;
import me.emokid.json.JSONMap;
import me.emokid.scheme.exception.IllegalBoundsException;
import me.emokid.utils.MessageType;
import me.emokid.utils.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Objects;

public class Car {

    /**
     * {@link #Car}'s name
     * Not {@code null}
     */

    private String name;

    /**
     * {@link #Car}'s cool status
     */

    private boolean cool;

    /**
     * {@link #Car}'s fields are filling by user line by line input
     *
     * @param userInputReader is needs for user input reading
     */

    public Car(UserInputReader userInputReader) {
        setValuesByUserInput(userInputReader);
    }

    /**
     * Fill all {@link #Car}'s fields by JSONMap
     *
     * @param jsonMap is using to fill all fields
     * @throws NullPointerException   if some field can't be {@code null}, but {@code null} is given
     * @throws IllegalBoundsException if some field is getting out of bounds value
     * @throws NumberFormatException  if not possible to parse number from JSON map
     */

    public Car(JSONMap jsonMap) throws NullPointerException, IllegalBoundsException, NumberFormatException {
        setValuesByJSONObject(jsonMap);
    }

    /**
     * Fill all {@link #Car}'s fields by JSONMap
     *
     * @param jsonMap is using to fill all fields
     * @throws NullPointerException   if some field can't be {@code null}, but {@code null} is given
     * @throws IllegalBoundsException if some field is getting out of bounds value
     * @throws NumberFormatException  if not possible to parse number from JSON map
     */

    private void setValuesByJSONObject(JSONMap jsonMap) throws NullPointerException, IllegalBoundsException, NumberFormatException {

        Map<String, Object> unpackedJSONObject = jsonMap.getData();

        for (Field field : getClass().getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            String fieldName = field.getName();
            String JSONFieldValue;
            if (unpackedJSONObject.get(fieldName) == null) {
                JSONFieldValue = null;
            }
            else {
                JSONFieldValue = unpackedJSONObject.get(fieldName).toString();
            }

            switch (fieldName) {
                case "name":
                    setName(JSONFieldValue);
                    break;
                case "cool":
                    setCool(Utils.parseBoolean(JSONFieldValue));
                    break;
            }
        }
    }

    /**
     * Fill some {@link #Car}'s fields from line by line user input
     *
     * @param userInputReader is needs for user input reading
     */

    private void setValuesByUserInput(UserInputReader userInputReader) {
        for (Field field : getClass().getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            String fieldName = field.getName();

            while (true) {
                try {
                    int defaultDepth = 1;
                    String userInput = userInputReader.inputField(defaultDepth, field);
                    switch (fieldName) {
                        case "name":
                            setName(userInput);
                            break;
                        case "cool":
                            setCool(Utils.parseBoolean(userInput));
                            break;
                    }
                    break;
                } catch (NullPointerException e) {
                    Utils.print(MessageType.ERROR, ExceptionMessage.COULD_BE_NULL);
                } catch (NumberFormatException e) {
                    Utils.print(MessageType.ERROR, ExceptionMessage.CANT_PARSE);
                } catch (Exception e) {
                    Utils.print(MessageType.ERROR, e.getMessage());
                }
            }
        }
    }

    /**
     * Nice printing for all {@link #Car}'s fields
     */

    public void print() {
        for (Field field : getClass().getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            String fieldName = field.getName();

            int defaultDepth = 1;
            switch (fieldName) {
                case "name":
                    Utils.printField(defaultDepth, field, getName());
                    break;
                case "cool":
                    Utils.printField(defaultDepth, field, String.valueOf(isCool()));
                    break;
            }
        }
    }

    /**
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * Check {@link #name} value on {@code null} and sets
     *
     * @param name name to set
     * @throws NullPointerException if name is {@code null}
     */

    public void setName(String name) throws NullPointerException {
        if (name == null) {
            throw new NullPointerException(ExceptionMessage.COULD_BE_NULL);
        }
        this.name = name;
    }

    /**
     * @return cool
     */

    public boolean isCool() {
        return cool;
    }

    /**
     * Check {@link #cool} value on {@code null} and sets
     *
     * @param cool cool to set
     * @throws NullPointerException if cool is {@code null}
     */

    public void setCool(Boolean cool) {
        if (cool == null) {
            throw new NullPointerException(ExceptionMessage.COULD_BE_NULL);
        }
        this.cool = cool;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", cool=" + cool +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Car car = (Car) obj;
        return Objects.equals(car.name, this.name) &&
                car.isCool() == this.isCool();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cool);
    }
}