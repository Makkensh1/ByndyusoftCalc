import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringConverter {

    private static Pattern operationsPattern = Pattern.compile("[+\\-*/]");
    private static Pattern startBracket = Pattern.compile("[(]");
    private static Pattern endBracket = Pattern.compile("[)]");

    public static List<Integer> getOperationIndex(String inputString) {
        Matcher matcher = operationsPattern.matcher(inputString);
        List<Integer> operatorsIndex = new ArrayList<>();
        while (matcher.find()) {
            operatorsIndex.add(matcher.start());
        }
        return operatorsIndex;
    }

    public static String getParenthesizedExpression(String inputString) {
        Matcher matcher = startBracket.matcher(inputString);
        List<Integer> openBracketIndex = new ArrayList<>();
        while (matcher.find()) {
            openBracketIndex.add(matcher.end());
        }
        int lastOpenBracketIndex = openBracketIndex.size() - 1;
        String subString = inputString.substring(openBracketIndex.get(lastOpenBracketIndex));
        matcher = endBracket.matcher(subString);
        matcher.find();
        String expressionInBracket = subString.substring(0, matcher.start());
        Float expressionResult = Calculator.calculate(expressionInBracket);
        expressionInBracket = "(" + expressionInBracket + ")";
        String result = inputString.replace(expressionInBracket, expressionResult.toString());
        return result;
    }

    public static List<Float> getArguments(String inputString, List<Integer> operationIndexes) {
        List<Float> arguments = new ArrayList<>();
        inputString = inputString.replace(',', '.');
        int lastIndex = operationIndexes.size() - 1;
        for (int i = 0; i < operationIndexes.size(); i++) {
            Float argument;
            if (i == 0 && i == lastIndex) {
                argument = Float.valueOf(inputString.substring(0, operationIndexes.get(i)));
                arguments.add(argument);
                argument = Float.valueOf(inputString.substring(operationIndexes.get(i) + 1));
                arguments.add(argument);
            }
            if (i == 0) {
                argument = Float.valueOf(inputString.substring(0, operationIndexes.get(i)));
                arguments.add(argument);
            } else if (i == lastIndex) {
                argument = Float.valueOf(inputString.substring(operationIndexes.get(i - 1) + 1, operationIndexes.get(i)));
                arguments.add(argument);
                argument = Float.valueOf(inputString.substring(operationIndexes.get(i) + 1));
                arguments.add(argument);
            } else {
                argument = Float.valueOf(inputString.substring(operationIndexes.get(i - 1) + 1, operationIndexes.get(i)));
                arguments.add(argument);
            }
        }
        return arguments;
    }

    public static List<MathOperation> getMathOperations(String inputString, List<Integer> operationsIndex) {
        List<MathOperation> operations = new ArrayList<>();
        for (int i = 0; i < operationsIndex.size(); i++) {
            String operation = String.valueOf(inputString.charAt(operationsIndex.get(i)));
            if (MathOperation.ADDITION.getOperationName().equals(operation)) {
                operations.add(MathOperation.ADDITION);
            } else if (MathOperation.DIVISION.getOperationName().equals(operation)) {
                operations.add(MathOperation.DIVISION);
            } else if (MathOperation.MULTIPLICATION.getOperationName().equals(operation)) {
                operations.add(MathOperation.MULTIPLICATION);
            } else if (MathOperation.SUBTRACTION.getOperationName().equals(operation)) {
                operations.add(MathOperation.SUBTRACTION);
            }
        }
        return operations;
    }
}

