package it.sevenbits.formatter.io;

import java.io.EOFException;
import java.io.IOException;

public class StringReader implements IReader {
    private String string;
    private int currentIndex;

    public StringReader(final String string) {
        this.string = string;
        currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex <= string.length() - 1;
    }

    @Override
    public int read() throws IOException {
        if (!hasNext()) {
            throw new EOFException("Reached the end of string.");
        }

        char result = string.charAt(currentIndex);
        currentIndex++;
        return result;
    }
}
