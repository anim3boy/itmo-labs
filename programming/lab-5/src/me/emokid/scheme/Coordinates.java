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

public class Coordinates {

    /**
     * X axis value
     * Cannot be null
     */

    private Long x;

    /**
     * Y axis value
     */

    private double y;

    /**
     * {@link #Coordinates}'s fields are filling by user line by line input
     *
     * @param userInputReader is needs for user input reading
     */

    public Coordinates(UserInputReader userInputReader) {
        setValuesByUserInput(userInputReader);
    }

    /**
     * Fill all {@link #Coordinates}'s fields by JSONMap
     *
     * @param jsonMap is using to fill all fields
     * @throws NullPointerException   if some field can't be {@code null}, but {@code null} is given
     * @throws IllegalBoundsException if some field is getting out of bounds value
     * @throws NumberFormatException  if not possible to parse number from JSON map
     */

    public Coordinates(JSONMap jsonMap) throws NullPointerException, IllegalBoundsException, NumberFormatException {
        setValuesByJSONObject(jsonMap);
    }

    /**
     * Fill all {@link #Coordinates}'s fields by JSONMap
     *
     * @param jsonMap is using to fill all fields
     * @throws NullPointerException   if some field can't be {@code null}, but {@code null} is given
     * @throws IllegalBoundsException if some field is getting out of bounds value
     * @throws NumberFormatException  if not possible to parse number from JSON map
     */

    private void setValuesByJSONObject(JSONMap jsonMap) throws NullPointerException, IllegalBoundsException, NumberFormatException {
        for (Field field : getClass().getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) continue;

            String fieldName = field.getName();

            switch (fieldName) {
                case "x":
                    setX(jsonMap.getNumber(fieldName).toLong());
                    break;
                case "y":
                    setY(jsonMap.getNumber(fieldName).toDouble());
                    break;
            }
        }
    }

    /**
     * Fill some {@link #Coordinates}'s fields from line by line user input
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
                        case "x":
                            setX(Utils.parseLong(userInput));
                            break;
                        case "y":
                            setY(Utils.parseDouble(userInput));
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
     * Nice printing for all {@link #Coordinates}'s fields
     */

    public void print() {
        for (Field field : getClass().getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            String fieldName = field.getName();

            int defaultDepth = 1;
            switch (fieldName) {
                case "x":
                    Utils.printField(defaultDepth, field, String.valueOf(getX()));
                    break;
                case "y":
                    Utils.printField(defaultDepth, field, String.valueOf(getY()));
                    break;
            }
        }
    }

    /**
     * @return x
     */

    public Long getX() {
        return x;
    }

    /**
     * Check {@link #x} value on {@code null} and sets
     *
     * @param x x to set
     * @throws NullPointerException if x is {@code null}
     */

    public void setX(Long x) {
        if (x == null) {
            throw new NullPointerException(ExceptionMessage.COULD_BE_NULL);
        }
        this.x = x;
    }

    /**
     * @return y
     */

    public double getY() {
        return y;
    }

    /**
     * Check {@link #y} value on {@code null} and sets
     *
     * @param y y to set
     * @throws NullPointerException if y is {@code null}
     */

    public void setY(Double y) {
        if (y == null) {
            throw new NullPointerException(ExceptionMessage.COULD_BE_NULL);
        }
        this.y = y;
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        Coordinates coordinates = (Coordinates) obj;
        if (coordinates == null) {
            return false;
        }
        return Objects.equals(coordinates.x, this.x) &&
                Objects.equals(coordinates.y, this.y);
    }

    @Override
    public String toString() {
        return "{x=" + x +
                ", y=" + y +
                '}';
    }


}
