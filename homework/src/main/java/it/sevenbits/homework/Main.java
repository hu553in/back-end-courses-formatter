package it.sevenbits.homework;

import it.sevenbits.homework.formatter.Formatter;
import it.sevenbits.homework.formatter.FormatterException;
import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.reader.ReaderException;
import it.sevenbits.homework.io.writer.IWriter;
import it.sevenbits.homework.io.reader.StringReader;
import it.sevenbits.homework.io.writer.StringWriter;
import it.sevenbits.homework.io.writer.WriterException;

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
        final Formatter formatter = new Formatter();
        final IWriter stringWriter = new StringWriter();
        final IReader stringReader = new StringReader("class HelloWorld{public static void main(String[] "
                                                      + "args){System.out.println(\"Hello World!\");}}");

        try {
            formatter.format(stringReader, stringWriter);
        } catch (FormatterException e) {
            System.err.println(e.getMessage());
            return;
        }

        System.out.println(stringWriter.toString());

        try {
            stringWriter.close();
        } catch (WriterException e) {
            System.err.println("WriterException instance was thrown by StringWriter.close method.");
            return;
        }

        try {
            stringReader.close();
        } catch (ReaderException e) {
            System.err.println("ReaderException instance was thrown by StringReader.close method.");
        }
    }

    /**
     * A private constructor created to exclude the possibility of creating instances of this class.
     */
    private Main() {
    }
}
