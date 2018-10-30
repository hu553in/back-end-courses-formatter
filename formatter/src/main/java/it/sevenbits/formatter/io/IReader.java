package it.sevenbits.formatter.io;

import java.io.IOException;

public interface IReader {
    boolean hasNext();

    int read() throws IOException;
}
