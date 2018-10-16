package it.sevenbits.formatter;

import java.util.List;
import java.util.ListIterator;

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
        ListIterator<Character> textIterator = text.listIterator();

        while (textIterator.hasNext()) {
            Character character = textIterator.next();

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
                        if (!textIterator.hasNext() || textIterator.next() != ' ') {  // TODO: how about 'endless' stream of whitespaces?
                            textIterator.previous();
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
        }

        return formattedText.toString();
    }
}
