package it.sevenbits.homework.formatter;

import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.writer.IWriter;

/**
 * An interface that declares functionality of class that formats Java source code. Reading and writing are performed
 * using it.sevenbits.homework.io.reader.IReader and it.sevenbits.homework.io.writer.IWriter interfaces.
 */
public interface IFormatter {
    /**
     * Method that performs formatting of Java source code.
     *
     * @param reader Instance of IReader interface by which the data is read.
     * @param writer Instance of IWriter interface by which the data is written.
     *
     * @throws FormatterException Exception that may be thrown during the method work.
     */
    void format(IReader reader, IWriter writer) throws FormatterException;
}
