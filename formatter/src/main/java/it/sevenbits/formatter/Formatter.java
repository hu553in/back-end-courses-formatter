package it.sevenbits.formatter;

import it.sevenbits.formatter.io.IReader;
import it.sevenbits.formatter.io.IWriter;
import java.io.IOException;

public class Formatter {
    private static final char CHAR_SEMICOLON = ';',
                              CHAR_WHITESPACE = ' ',
                              CHAR_OPENING_CURLY_BRACE = '{',
                              CHAR_CLOSING_CURLY_BRACE = '}',
                              CHAR_NEWLINE = '\n',
                              CHAR_TAB = '\t',
                              CHAR_NULL = '\u0000';

    private static final String SINGLE_INDENT = "    ";

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

    public void format(final IReader reader, final IWriter writer) throws IOException {
        short nestingLevel = 0;
        boolean shouldRead = true;

        char lastWrittenChar = CHAR_NULL,
             lastReadChar = CHAR_NULL;

        while (reader.hasNext() || !shouldRead) {
            if (shouldRead) {
                lastReadChar = (char) reader.read();
            } else {
                shouldRead = true;
            }

            if (lastReadChar == CHAR_OPENING_CURLY_BRACE) {
                nestingLevel++;

                if (lastWrittenChar != CHAR_WHITESPACE &&
                    lastWrittenChar != CHAR_NEWLINE &&
                    lastWrittenChar != CHAR_NULL) {
                    writer.write(CHAR_WHITESPACE);
                }

                writer.write(lastReadChar);

                if (reader.hasNext()) {
                    lastWrittenChar = CHAR_NEWLINE;
                    writer.write(lastWrittenChar);
                }
            } else if (lastReadChar == CHAR_CLOSING_CURLY_BRACE) {
                nestingLevel--;

                if (lastWrittenChar != CHAR_NEWLINE && lastWrittenChar != CHAR_NULL) {
                    writer.write(CHAR_NEWLINE);
                }

                writer.write(getIndent(nestingLevel));
                writer.write(lastReadChar);

                if (reader.hasNext()) {
                    lastWrittenChar = CHAR_NEWLINE;
                    writer.write(lastWrittenChar);
                }
            } else if (lastReadChar == CHAR_SEMICOLON) {
                writer.write(lastReadChar);

                if (reader.hasNext()) {
                    lastWrittenChar = CHAR_NEWLINE;
                    writer.write(lastWrittenChar);
                }
            } else if (lastReadChar == CHAR_WHITESPACE) {
                if (lastWrittenChar != CHAR_NEWLINE && lastWrittenChar != CHAR_NULL && reader.hasNext()) {
                    writer.write(lastReadChar);
                    lastWrittenChar = lastReadChar;
                }

                while (reader.hasNext()) {
                    lastReadChar = (char) reader.read();

                    if (lastReadChar != CHAR_WHITESPACE) {
                        shouldRead = false;
                        break;
                    }
                }
            } else if (lastReadChar != CHAR_NEWLINE && lastReadChar != CHAR_TAB) {
                if (lastWrittenChar == CHAR_NEWLINE) {
                    writer.write(getIndent(nestingLevel));
                }

                writer.write(lastReadChar);
                lastWrittenChar = lastReadChar;
            }
        }
    }
}
