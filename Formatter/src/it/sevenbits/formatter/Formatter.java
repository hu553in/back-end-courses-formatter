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

        final Character SEMICOLON = ';',
                        WHITESPACE = ' ',
                        OPENING_CURVE_BRACE = '{',
                        CLOSING_CURVE_BRACE = '}',
                        NEW_LINE = '\n';

        while (textIterator < text.length()) {
            Character character = text.charAt(textIterator);

            if (character == OPENING_CURVE_BRACE) {
                nestingLevel++;

                if (!formattedText.toString().endsWith(WHITESPACE.toString()) &&
                    !formattedText.toString().endsWith(NEW_LINE.toString())) {
                    formattedText.append(WHITESPACE);
                }

                formattedText.append(character).append(NEW_LINE);
            } else if (character == CLOSING_CURVE_BRACE) {
                nestingLevel--;

                if (!formattedText.toString().endsWith(NEW_LINE.toString())) {
                    formattedText.append(NEW_LINE);
                }

                formattedText.append(getIndent(nestingLevel)).append(character).append(NEW_LINE);
            } else if (character == SEMICOLON) {
                formattedText.append(character).append(NEW_LINE);
            } else if (character == WHITESPACE) {
                if (!formattedText.toString().endsWith(NEW_LINE.toString())) {
                    formattedText.append(character);
                }

                while (++textIterator < text.length()) {
                    if (text.charAt(textIterator) != WHITESPACE) {
                        textIterator--;
                        break;
                    }
                }
            } else if (character != NEW_LINE) {
                if (formattedText.toString().endsWith(NEW_LINE.toString())) {
                    formattedText.append(getIndent(nestingLevel));
                }

                formattedText.append(character);
            }

            textIterator++;
        }

        return formattedText.toString();
    }
}
