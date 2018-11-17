package it.sevenbits.homework.formatter;

import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.writer.IWriter;
import it.sevenbits.homework.io.reader.ReaderException;
import it.sevenbits.homework.io.writer.WriterException;

/**
 * A class that formats Java source code. Reading and writing are performed
 * using it.sevenbits.homework.io.reader.IReader and it.sevenbits.homework.io.writer.IWriter interfaces.
 */
public class Formatter implements IFormatter {
    private static final char CHAR_SEMICOLON = ';',
                              CHAR_WHITESPACE = ' ',
                              CHAR_OPENING_CURLY_BRACE = '{',
                              CHAR_CLOSING_CURLY_BRACE = '}',
                              CHAR_NEWLINE = '\n',
                              CHAR_TAB = '\t',
                              CHAR_NULL = '\u0000';

    private static final String SINGLE_INDENT = "    ";

    /**
     * Private method that return instance of String that contains required indent according to nesting level.
     *
     * @param nestingLevel Level of nesting in source code.
     *
     * @return Instance of String that contains (4 * nesting level) spaces.
     */
    private String getIndent(final short nestingLevel) {
        if (nestingLevel == 0) {
            return "";
        }

        StringBuilder resultIndent = new StringBuilder();

        for (short iterator = 0; iterator < nestingLevel; iterator++) {
            resultIndent.append(SINGLE_INDENT);
        }

        return resultIndent.toString();
    }

    /**
     * Method that performs formatting of Java source code.
     *
     * @param in Instance of IReader interface by which the data is read.
     * @param out Instance of IWriter interface by which the data is written.
     *
     * @throws ReaderException Exception that can be thrown by methods of instances of IReader interfaces.
     * @throws WriterException Exception that can be thrown by methods of instances of IWriter interfaces.
     */
    @Override
    public void format(final IReader in, final IWriter out) throws ReaderException, WriterException {
        short nestingLevel = 0;
        boolean shouldRead = true;

        char lastWrittenChar = CHAR_NULL,
             lastReadChar = CHAR_NULL;

        while (in.hasNext() || !shouldRead) {
            if (shouldRead) {
                lastReadChar = (char) in.read();
            } else {
                shouldRead = true;
            }

            if (lastReadChar == CHAR_OPENING_CURLY_BRACE) {
                if (lastWrittenChar == CHAR_NEWLINE ||
                        lastWrittenChar == CHAR_NULL) {
                    out.write(getIndent(nestingLevel));
                } else if (lastWrittenChar != CHAR_WHITESPACE) {
                    out.write(CHAR_WHITESPACE);
                }

                out.write(lastReadChar);
                nestingLevel++;

                if (in.hasNext()) {
                    lastWrittenChar = CHAR_NEWLINE;
                    out.write(lastWrittenChar);
                }
            } else if (lastReadChar == CHAR_CLOSING_CURLY_BRACE) {
                nestingLevel--;

                if (lastWrittenChar != CHAR_NEWLINE && lastWrittenChar != CHAR_NULL) {
                    out.write(CHAR_NEWLINE);
                }

                out.write(getIndent(nestingLevel));
                out.write(lastReadChar);

                if (in.hasNext()) {
                    lastWrittenChar = CHAR_NEWLINE;
                    out.write(lastWrittenChar);
                }
            } else if (lastReadChar == CHAR_SEMICOLON) {
                out.write(lastReadChar);

                if (in.hasNext()) {
                    lastWrittenChar = CHAR_NEWLINE;
                    out.write(lastWrittenChar);
                }
            } else if (lastReadChar == CHAR_WHITESPACE) {
                if (lastWrittenChar != CHAR_NEWLINE && lastWrittenChar != CHAR_NULL && in.hasNext()) {
                    out.write(lastReadChar);
                    lastWrittenChar = lastReadChar;
                }

                while (in.hasNext()) {
                    lastReadChar = (char) in.read();

                    if (lastReadChar != CHAR_WHITESPACE) {
                        shouldRead = false;
                        break;
                    }
                }
            } else if (lastReadChar != CHAR_NEWLINE && lastReadChar != CHAR_TAB) {
                if (lastWrittenChar == CHAR_NEWLINE) {
                    out.write(getIndent(nestingLevel));
                }

                out.write(lastReadChar);
                lastWrittenChar = lastReadChar;
            }
        }
    }
}
