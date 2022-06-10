package me.emokid.IO;

import java.io.FileNotFoundException;

public interface Loader<E> {

    /**
     * Loads some data
     * @return
     * @throws FileNotFoundException if file not found
     */
    E load() throws FileNotFoundException;
}
