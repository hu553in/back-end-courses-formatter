package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.formatter.util.IndentProvider;
import it.sevenbits.formatter.io.reader.IReader;
import it.sevenbits.formatter.io.writer.IWriter;
import it.sevenbits.formatter.io.writer.WriterException;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.LexerException;
import it.sevenbits.formatter.lexer.SimpleLexer;
import it.sevenbits.formatter.lexer.token.IToken;

import java.util.Objects;

/**
 * Class that formats Java source code. Input is performed using {@link IWriter} and {@link ILexer} instances,
 * output is performed using {@link IWriter} instance.
 */
public class SimpleFormatter implements IFormatter {
    /**
     * Method that performs formatting of Java source code that is stored in lexical tokens
     * which are provided by {@link ILexer} instance.
     *
     * @param reader {@link IReader} instance that provides data reading.
     * @param writer {@link IWriter} instance that provides data writing.
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

        final ILexer lexer;

        try {
            lexer = new SimpleLexer(reader);
        } catch (LexerException e) {
            throw new FormatterException("Unable to create lexer instance", e);
        }

        short nestingLevel = 0;
        String lastWrittenLexeme = null;

        while (lexer.hasMoreTokens()) {
            IToken token;

            try {
                token = lexer.readToken();
            } catch (LexerException e) {
                throw new FormatterException("Unable to read token from lexer", e);
            }

            try {
                if (Objects.equals(token.getName(), "OPENING_CURLY_BRACE")) {
                    if (Objects.equals(lastWrittenLexeme, "\n") || lastWrittenLexeme == null) {
                        writer.write(IndentProvider.getFourSpacesIndent(nestingLevel));
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

                    writer.write(IndentProvider.getFourSpacesIndent(nestingLevel));
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
                    if (
                            !Objects.equals(lastWrittenLexeme, token.getLexeme()) &&
                                    !Objects.equals(lastWrittenLexeme, "\n") &&
                                    lastWrittenLexeme != null &&
                                    !lastWrittenLexeme.endsWith(token.getLexeme())
                    ) {
                        writer.write(token.getLexeme());
                        lastWrittenLexeme = token.getLexeme();
                    }
                } else if (Objects.equals(token.getName(), "OTHER")) {
                    if (Objects.equals(lastWrittenLexeme, "\n")) {
                        writer.write(IndentProvider.getFourSpacesIndent(nestingLevel));
                    }

                    writer.write(token.getLexeme());
                    lastWrittenLexeme = token.getLexeme();
                }
            } catch (WriterException e) {
                throw new FormatterException("Unable to write to writer", e);
            }
        }
    }
}
