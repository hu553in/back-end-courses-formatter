package it.sevenbits.homework.formatter;

import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.writer.IWriter;

/**
 * An interface that declares functionality of class that formats Java source code. Reading and writing are performed
 * using {@link it.sevenbits.homework.io.reader.IReader} and {@link it.sevenbits.homework.io.writer.IWriter} interfaces.
 */
public interface IFormatter {
    /**
     * Method that performs formatting of Java source code.
     *
     * @param reader Instance of {@link it.sevenbits.homework.io.reader.IReader} interface by which the data is read.
     * @param writer Instance of {@link it.sevenbits.homework.io.writer.IWriter} interface by which the data is written.
     *
     * @throws FormatterException Exception that can be thrown during the method work.
     */
    void format(IReader reader, IWriter writer) throws FormatterException;
}
