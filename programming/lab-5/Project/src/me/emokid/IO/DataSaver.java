package me.emokid.IO;

import me.emokid.json.JSONMap;

import java.io.FileWriter;
import java.io.IOException;


public class DataSaver implements Saver{


    /**
     * Data collection needs to save
     */

    private final JSONMap jsonMap;
    private FileWriter fileWriter;

    /**
     * Sets JSON map
     * @param jsonMap JSON map to sets
     */

    public DataSaver(JSONMap jsonMap, FileWriter fileWriter) {
        this.jsonMap = jsonMap;
        this.fileWriter = fileWriter;
    }

    /**
     * Saves JSON map
     *
     * @throws IOException something went wrong
     */

    public void save() throws IOException {
        fileWriter.write(this.jsonMap.toString());
        fileWriter.flush();
        fileWriter.close();
    }
}
