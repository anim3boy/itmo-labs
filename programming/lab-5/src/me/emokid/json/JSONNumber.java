package me.emokid.json;

import me.emokid.utils.Utils;

public class JSONNumber {

    /**
     * Core string object
     */

    private final String value;

    /**
     * Sets code string
     * @param value string to set
     */

    public JSONNumber(String value) {
        this.value = value;
    }

    /**
     * Converts core string to int
     * @return int
     * @throws NumberFormatException if something went wrong
     */

    public Integer toInt() throws NumberFormatException{
        return Utils.parseInt(this.value);
    }

    /**
     * Converts core string to long
     * @return long
     * @throws NumberFormatException if something went wrong
     */

    public Long toLong() throws NumberFormatException{
        return Utils.parseLong(this.value);
    }

    /**
     * Converts core string to double
     * @return double
     * @throws NumberFormatException if something went wrong
     */

    public Double toDouble() throws NumberFormatException{
        return Utils.parseDouble(this.value);
    }

    /**
     * Converts core string to float
     * @return float
     * @throws NumberFormatException if something went wrong
     */

    public Float toFloat() throws NumberFormatException{
        return Utils.parseFloat(this.value);
    }
}
