package me.emokid.command;

import java.io.IOException;

import static me.emokid.scheme.MetaData.metaDataKey;


public class Command implements Cloneable{

    /**
     * Command description
     *
     */

    private final String description;

    /**
     * Command name
     *
     */

    private final String name;

    /**
     * Command argument
     *
     */

    private String arg;

    /**
     * Sets command name and command description
     *
     * @param name          command name
     * @param description   command description
     */

    public Command(String name, String description) {
        this.description = description;
        this.name = name;
    }

    /**
     * @return arg
     *
     * @throws IllegalArgumentException if argument cannot be found
     * @throws SecurityException if argument equals {@code metaDataKey}
     */

    public String getArg() throws IllegalArgumentException, SecurityException{
        if (arg == null) {
            throw new IllegalArgumentException(ExceptionMessage.ARG_NOT_FOUND);
        }
        if (arg.equals(metaDataKey)) {
            throw new SecurityException(ExceptionMessage.RESERVED_VALUE);
        }
        return this.arg;
    }

    /**
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * @return description
     */

    public String getDescription() {
        return description;
    }

    /**
     * sets arg
     *
     * @param arg text arg
     *
     */

    public void acceptArg(String arg) {
        this.arg = arg;
    }

    /**
     * sets what's command doing
     *
     */

    public void execute() throws IllegalAccessException, IOException, NoSuchFieldException {

    }

    @Override
    public String toString() {
        return getDescription();
    }

    @Override
    public Command clone() {
        try {
            return (Command) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
