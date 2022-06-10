package me.emokid.IO;

import java.io.IOException;

public interface Saver {
    /**
     * saves some data
     * @throws IOException if something went wrong
     */
    void save() throws IOException;
}
