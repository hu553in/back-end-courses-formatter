package it.sevenbits.homework.formatter;

import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.writer.IWriter;
import it.sevenbits.homework.io.writer.WriterException;
import it.sevenbits.homework.lexer.ILexer;
import it.sevenbits.homework.lexer.LexerException;
import it.sevenbits.homework.lexer.factory.ILexerFactory;
import it.sevenbits.homework.lexer.factory.LexerFactory;
import it.sevenbits.homework.lexer.factory.LexerFactoryException;
import it.sevenbits.homework.lexer.token.IToken;
import java.util.Objects;

/**
 * Class that formats Java source code. Input is performed using {@link IWriter} and {@link ILexer} instances,
 * output is performed using {@link IWriter} instance.
 */
public class Formatter implements IFormatter {
    private static final String SINGLE_INDENT = "    ";
    private final ILexerFactory lexerFactory;

    /**
     * Class constructor that initializes {@link #lexerFactory} with new {@link LexerFactory} instance.
     */
    public Formatter() {
        lexerFactory = new LexerFactory();
    }

    /**
     * Method that returns {@link String} instance which contains required indent
     * in accordance with current nesting level.
     *
     * @param nestingLevel Level of nesting in source code.
     *
     * @return {@link String} instance that contains (4 * nesting level) spaces.
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
     * Method that performs formatting of Java source code that is stored in lexical tokens
     * which are provided by ILexer instance.
     *
     * @param reader {@link IReader} instance that is passed to {@link #lexerFactory}
     *               as an argument for ILexer instance creation.
     * @param writer {@link IWriter} instance that provides data writing.
     *
     * @throws FormatterException Exception that can be thrown during the method work.
     */
    @Override
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        if (reader == null) {
            throw new FormatterException("\"reader\" argument is null");
        }

        if (writer == null) {
            throw new FormatterException("\"writer\" argument is null");
        }

        ILexer lexer;
        try {
            lexer = lexerFactory.createLexer(reader);
        } catch (LexerFactoryException e) {
            throw new FormatterException("Unable to create ILexer instance", e);
        }

        short nestingLevel = 0;
        String lastWrittenLexeme = null;

        while (lexer.hasMoreTokens()) {
            IToken token;
            try {
                token = lexer.readToken();
            } catch (LexerException e) {
                throw new FormatterException("Unable to read token from ILexer instance", e);
            }

            try {
                if (Objects.equals(token.getName(), "OPENING_CURLY_BRACE")) {
                    if (Objects.equals(lastWrittenLexeme, "\n") || lastWrittenLexeme == null) {
                        writer.write(getIndent(nestingLevel));
                    } else if (!Objects.equals(lastWrittenLexeme, " ")) {
                        writer.write(" ");
                    }

                    nestingLevel++;

                    writer.write(token.getLexeme());
                    lastWrittenLexeme = token.getLexeme();

                    if (lexer.hasMoreTokens()) {
                        writer.write("\n");
                        lastWrittenLexeme = "\n";
                    }
                } else if (Objects.equals(token.getName(), "CLOSING_CURLY_BRACE")) {
                    nestingLevel--;

                    if (!Objects.equals(lastWrittenLexeme, "\n") && lastWrittenLexeme != null) {
                        writer.write("\n");
                    }

                    writer.write(getIndent(nestingLevel));
                    writer.write(token.getLexeme());
                    lastWrittenLexeme = token.getLexeme();

                    if (lexer.hasMoreTokens()) {
                        writer.write("\n");
                        lastWrittenLexeme = "\n";
                    }
                } else if (Objects.equals(token.getName(), "SEMICOLON")) {
                    writer.write(token.getLexeme());
                    lastWrittenLexeme = token.getLexeme();

                    if (lexer.hasMoreTokens()) {
                        writer.write("\n");
                        lastWrittenLexeme = "\n";
                    }
                } else if (Objects.equals(token.getName(), "NEWLINE")) {
                    if (!Objects.equals(lastWrittenLexeme, token.getLexeme())) {
                        writer.write(token.getLexeme());
                        lastWrittenLexeme = token.getLexeme();
                    }
                } else if (Objects.equals(token.getName(), "WHITESPACE")) {
                    if (!Objects.equals(lastWrittenLexeme, token.getLexeme()) &&
                        !Objects.equals(lastWrittenLexeme, "\n") &&
                        lastWrittenLexeme != null &&
                        !lastWrittenLexeme.endsWith(token.getLexeme())) {
                        writer.write(token.getLexeme());
                        lastWrittenLexeme = token.getLexeme();
                    }
                } else if (Objects.equals(token.getName(), "OTHER")) {
                    if (Objects.equals(lastWrittenLexeme, "\n")) {
                        writer.write(getIndent(nestingLevel));
                    }

                    writer.write(token.getLexeme());
                    lastWrittenLexeme = token.getLexeme();
                }
            } catch (WriterException e) {
                throw new FormatterException("Unable to write to IWriter instance", e);
            }
        }
    }
}
