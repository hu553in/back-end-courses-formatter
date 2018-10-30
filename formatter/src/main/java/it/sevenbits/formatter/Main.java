package it.sevenbits.formatter;

import it.sevenbits.formatter.io.IReader;
import it.sevenbits.formatter.io.IWriter;
import it.sevenbits.formatter.io.StringReader;
import it.sevenbits.formatter.io.StringWriter;
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

        StringBuilder resultStringBuilder = new StringBuilder();
        IWriter stringWriter = new StringWriter(resultStringBuilder);

        Formatter formatter = new Formatter();

        try {
            formatter.format(stringReader, stringWriter);
        } catch (IOException e) {
            System.err.println("An instance of IOException was thrown during formatting code.");
            return;
        }

        System.out.println(resultStringBuilder.toString());
    }

    /**
     * A private constructor created to exclude the possibility of creating instances of this class.
     */
    private Main() {
    }
}
