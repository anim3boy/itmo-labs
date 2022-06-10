package me.emokid.IO;

import me.emokid.json.JSONMap;

import java.util.Scanner;


public class DataLoader implements Loader<JSONMap>{

    /**
     * Scanner object
     */

    private final Scanner scanner;

    /**
     * Sets scanner object
     * @param scanner scanner object to sets
     */

    public DataLoader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Loads JSON map with scanner
     * @return JSON map
     */

    public JSONMap load(){
        StringBuilder text = new StringBuilder();
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine());
        }
        return new JSONMap(text.toString());
    }

}


