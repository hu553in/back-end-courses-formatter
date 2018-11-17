package it.sevenbits.homework.formatter;

import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.writer.IWriter;
import it.sevenbits.homework.io.reader.ReaderException;
import it.sevenbits.homework.io.writer.WriterException;

/**
 * An interface that declares functionality of class that formats Java source code. Reading and writing are performed
 * using it.sevenbits.homework.io.reader.IReader and it.sevenbits.homework.io.writer.IWriter interfaces.
 */
public interface IFormatter {
    /**
     * Method that performs formatting of Java source code.
     *
     * @param in Instance of IReader interface by which the data is read.
     * @param out Instance of IWriter interface by which the data is written.
     *
     * @throws ReaderException Exception that can be thrown by methods of instances of IReader interfaces.
     * @throws WriterException Exception that can be thrown by methods of instances of IWriter interfaces.
     */
    void format(IReader in, IWriter out) throws ReaderException, WriterException;
}
