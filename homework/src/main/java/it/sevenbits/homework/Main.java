package it.sevenbits.homework;

import it.sevenbits.homework.formatter.Formatter;
import it.sevenbits.homework.formatter.FormatterException;
import it.sevenbits.homework.io.reader.FileReader;
import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.reader.ReaderException;
import it.sevenbits.homework.io.reader.StringReader;
import it.sevenbits.homework.io.writer.FileWriter;
import it.sevenbits.homework.io.writer.IWriter;
import it.sevenbits.homework.io.writer.WriterException;
import it.sevenbits.homework.lexer.ILexer;
import it.sevenbits.homework.lexer.factory.ILexerFactory;
import it.sevenbits.homework.lexer.factory.LexerFactory;
import it.sevenbits.homework.lexer.factory.LexerFactoryException;

/**
 * Main class of application.
 */
public final class Main {
    /**
     * Main entry point of application.
     *
     * @param args Command-line arguments.
     */
    public static void main(final String[] args) {
        if (args.length != 2) {
            System.err.println("Incorrect number of command-line arguments!\n\n" +
                               "Expected 2 arguments:\n\n" +
                               "1) Path to input file\n" +
                               "2) Path to output file");
            return;
        }

        final Formatter formatter = new Formatter();

        try (
                IReader reader = new FileReader(args[0]);
                IWriter writer = new FileWriter(args[1])
        ) {
            formatter.format(reader, writer);
        } catch (ReaderException | WriterException | FormatterException e) {
            System.err.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    /**
     * A private constructor created to exclude the possibility of creating instances of this class.
     */
    private Main() {
    }
}
