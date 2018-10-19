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

        while (textIterator < text.length()) {
            Character character = text.charAt(textIterator);

            switch (character) {
                case '{':
                    nestingLevel++;

                    if (!formattedText.toString().endsWith(" ") && !formattedText.toString().endsWith("\n")) {
                        formattedText.append(' ');
                    }

                    formattedText.append(character).append('\n');
                    break;
                case '}':
                    nestingLevel--;

                    if (!formattedText.toString().endsWith("\n")) {
                        formattedText.append('\n');
                    }

                    formattedText.append(getIndent(nestingLevel)).append(character).append('\n');
                    break;
                case ';':
                    formattedText.append(character).append('\n');
                    break;
                case '\n':
                    break;
                case ' ':
                    if (!formattedText.toString().endsWith("\n")) {
                        formattedText.append(character);
                    }

                    while (true) {
                        textIterator++;

                        if (textIterator >= text.length()) {
                            break;
                        }

                        if (text.charAt(textIterator) != ' ') {
                            textIterator--;
                            break;
                        }
                    }

                    break;
                default:
                    if (formattedText.toString().endsWith("\n")) {
                        formattedText.append(getIndent(nestingLevel));
                    }

                    formattedText.append(character);
                    break;
            }

            textIterator++;
        }

        return formattedText.toString();
    }
}
