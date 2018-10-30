package it.sevenbits.formatter.io;

import java.io.IOException;

public class StringWriter implements IWriter {
    private StringBuilder stringBuilder;

    public StringWriter(final StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public void write(final int character) throws IOException {
        stringBuilder.append((char) character);
    }

    @Override
    public void write(final String string) throws IOException {
        stringBuilder.append(string);
    }
}
