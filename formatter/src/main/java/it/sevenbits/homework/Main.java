package it.sevenbits.homework;

import it.sevenbits.homework.formatter.Formatter;
import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.writer.IWriter;
import it.sevenbits.homework.io.reader.StringReader;
import it.sevenbits.homework.io.writer.StringWriter;
import java.io.IOException;

/**
 * Main class of application.
 */
public final class Main {
    /**
     * Main entry point of application.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        IReader stringReader = new StringReader("class HelloWorld{public static void main(String[] " +
                                                "args){System.out.println(\"Hello World!\");}}");
        IWriter stringWriter = new StringWriter();
        Formatter formatter = new Formatter();

        try {
            formatter.format(stringReader, stringWriter);
        } catch (IOException e) {
            System.err.println("An instance of IOException was thrown during formatting code.");
            return;
        }

        System.out.println(stringWriter.toString());
    }

    /**
     * A private constructor created to exclude the possibility of creating instances of this class.
     */
    private Main() {
    }
}
