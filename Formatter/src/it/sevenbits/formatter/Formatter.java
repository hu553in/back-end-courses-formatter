package it.sevenbits.formatter;

public class Formatter {
    private String getIndent(short nestingLevel) {
        if (nestingLevel == 0) {
            return "";
        }

        String singleIndent = "    ";
        StringBuilder resultIndent = new StringBuilder();

        for (short iterator = 0; iterator < nestingLevel; iterator++) {
            resultIndent.append(singleIndent);
        }

        return resultIndent.toString();
    }

    public String format(String text) {
        StringBuilder formattedText = new StringBuilder();
        short nestingLevel = 0;
        int textIterator = 0;

        final Character CHAR_SEMICOLON = ';',
                        CHAR_WHITESPACE = ' ',
                        CHAR_OPENING_CURLY_BRACE = '{',
                        CHAR_CLOSING_CURLY_BRACE = '}',
                        CHAR_NEW_LINE = '\n';

        while (textIterator < text.length()) {
            Character character = text.charAt(textIterator);

            if (character == CHAR_OPENING_CURLY_BRACE) {
                nestingLevel++;

                if (!formattedText.toString().endsWith(CHAR_WHITESPACE.toString()) &&
                    !formattedText.toString().endsWith(CHAR_NEW_LINE.toString())) {
                    formattedText.append(CHAR_WHITESPACE);
                }

                formattedText.append(character).append(CHAR_NEW_LINE);
            } else if (character == CHAR_CLOSING_CURLY_BRACE) {
                nestingLevel--;

                if (!formattedText.toString().endsWith(CHAR_NEW_LINE.toString())) {
                    formattedText.append(CHAR_NEW_LINE);
                }

                formattedText.append(getIndent(nestingLevel)).append(character).append(CHAR_NEW_LINE);
            } else if (character == CHAR_SEMICOLON) {
                formattedText.append(character).append(CHAR_NEW_LINE);
            } else if (character == CHAR_WHITESPACE) {
                if (!formattedText.toString().endsWith(CHAR_NEW_LINE.toString())) {
                    formattedText.append(character);
                }

                while (++textIterator < text.length()) {
                    if (text.charAt(textIterator) != CHAR_WHITESPACE) {
                        textIterator--;
                        break;
                    }
                }
            } else if (character != CHAR_NEW_LINE) {
                if (formattedText.toString().endsWith(CHAR_NEW_LINE.toString())) {
                    formattedText.append(getIndent(nestingLevel));
                }

                formattedText.append(character);
            }

            textIterator++;
        }

        return formattedText.toString();
    }
}
