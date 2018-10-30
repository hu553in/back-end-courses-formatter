package it.sevenbits.formatter.io;

import java.io.IOException;

public interface IWriter {
    void write(int character) throws IOException;

    void write(String string) throws IOException;
}
