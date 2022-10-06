package calculator;

import java.StringConverter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static Pattern numbersPattern = Pattern.compile("[^\\d,.()+\\-*/]");
    private static Pattern startBrackets = Pattern.compile("[()]");

    private static Pattern startPattern = Pattern.compile("^[\\d(](.*)");

    private static Pattern brackets = Pattern.compile("^[^()\\n]*+(\\((?>[^()\\n]|(\\?1))*+\\)[^()\\n]*+)++$|^[^()]+?$");

    public static void validateCharMissing(String inputString) {
        Matcher matcher = numbersPattern.matcher(inputString);
        if (matcher.find()) {
            throw new IllegalArgumentException("Ќе верный формат данных, уберите символы не €вл€ющиес€ математическими операторами в строке: " + inputString);
        }
    }

    public static void validateBracketCorrectSet(String inputString) {
        Matcher matcher = brackets.matcher(inputString);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Ќе верно расставлены скобки в строке: ");
        }
    }

    public static boolean checkBracketExist(String inputString) {
        Matcher matcher = startBrackets.matcher(inputString);
        return matcher.find() ? true : false;
    }

    public static void validateStringCorrectStarts(String inputString) {
        Matcher matcher = startPattern.matcher(inputString);
        if (matcher.find() == false) {
            throw new IllegalArgumentException("—трока не должна начинатьс€ с математического оператора");
        }

    }
    public static void validateMathOperations(String inputString) {
        List<Integer> operationIndex = StringConverter.getOperationIndex(inputString);
        for (int i = 1; i < operationIndex.size(); i++) {
            if (operationIndex.get(i-1)+1==operationIndex.get(i)){
                throw new IllegalArgumentException("Ќе верно введены данные, математические операторы не должны следовать друг за другом");
            }

        }
    }

}
