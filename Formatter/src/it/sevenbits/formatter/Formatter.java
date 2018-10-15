package it.sevenbits.formatter;

import java.util.List;

public class Formatter {
    private String getIndent(short nestingLevel) {
        if (nestingLevel == 0) {
            return "";
        }

        String singleIndent = "    ";
        StringBuilder resultIndent = new StringBuilder();

        for (int iterator = 0; iterator < nestingLevel; iterator++) {
            resultIndent.append(singleIndent);
        }

        return resultIndent.toString();
    }

    public String format(List<Character> text) {
        StringBuilder formattedText = new StringBuilder();
        short nestingLevel = 0;
        boolean isNewLine = true;
        int iterator = 0;

        while (iterator < text.size()) {
            switch (text.get(iterator)) {
                case '{':
                    nestingLevel++;

                    if (!formattedText.toString().endsWith(" ")) {
                        formattedText.append(' ');
                    }

                    formattedText.append(text.get(iterator)).append('\n');

                    isNewLine = true;
                    iterator++;
                    break;
                case '}':
                    nestingLevel--;

                    if (!formattedText.toString().endsWith("\n")) {
                        formattedText.append('\n');
                    }

                    formattedText.append(getIndent(nestingLevel)).append(text.get(iterator)).append('\n');

                    isNewLine = true;
                    iterator++;
                    break;
                case ';':
                    formattedText.append(text.get(iterator)).append('\n');

                    isNewLine = true;
                    iterator++;
                    break;
                case '\n':
                    iterator++;
                    break;
                case ' ':
                    if (!isNewLine) {
                        formattedText.append(text.get(iterator));
                    }

                    while (iterator < text.size() && text.get(iterator) == ' ') {
                        iterator++;
                    }
                    break;
                default:
                    if (formattedText.toString().endsWith("\n")) {
                        formattedText.append(getIndent(nestingLevel));
                    }

                    formattedText.append(text.get(iterator));
                    isNewLine = false;

                    iterator++;
                    break;
            }
        }

        return formattedText.toString();
    }
}
