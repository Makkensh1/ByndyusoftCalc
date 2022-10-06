package calculator;
import validator.Validator;

import java.util.Iterator;
import java.util.List;

public class Calculator {

    public static float calculate(String input) {
        List<Integer> operationIndexes = StringConverter.getOperationIndex(input);
        List<Float> arguments = StringConverter.getArguments(input, operationIndexes);
        List<MathOperation> operations = StringConverter.getMathOperations(input, operationIndexes);
        Iterator<Float> argIter = arguments.iterator();
        float result = 0;
        if (operations.size() == 0) {
            result = Float.valueOf(input);
        } else {
            for (int i = 0; i < operations.size(); i++) {
                if (i == 0) {
                    result = operations.get(i).getResultOfMathOperation(argIter.next(), argIter.next());
                } else {
                    result = operations.get(i).getResultOfMathOperation(result, argIter.next());
                }
            }
        }
        return result;
    }


    public static float calculateAll(String input) {
        float result = 0;
        while (Validator.checkBracketExist(input)) {
            input = StringConverter.getParenthesizedExpression(input);
        }
        result = calculate(input);
        return result;
    }


}
