package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.io.reader.IReader;
import it.sevenbits.formatter.io.writer.IWriter;

/**
 * An interface that declares functionality for Java source code formatting. Reading and writing are performed
 * using {@link IReader} and {@link IWriter} interfaces.
 */
public interface IFormatter {
    /**
     * Method that performs formatting of Java source code.
     *
     * @param reader {@link IReader} instance that provides data reading.
     * @param writer {@link IWriter} instance that provides data writing.
     * @throws FormatterException Exception that can be thrown during the method work.
     */
    void format(IReader reader, IWriter writer) throws FormatterException;
}
