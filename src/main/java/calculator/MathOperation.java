public enum MathOperation {
    ADDITION("+") {
        @Override
        float getResultOfMathOperation(float firstNum, float secondNum) {
            return firstNum + secondNum;
        }
    },
    DIVISION("/") {
        @Override
        float getResultOfMathOperation(float firstNum, float secondNum) {
            return firstNum / secondNum;
        }
    },
    MULTIPLICATION("*") {
        @Override
        float getResultOfMathOperation(float firstNum, float secondNum) {
            return firstNum * secondNum;
        }
    },
    SUBTRACTION("-") {
        @Override
        float getResultOfMathOperation(float firstNum, float secondNum) {
            return firstNum - secondNum;
        }
    };


    String operationName;

    MathOperation(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationName() {
        return operationName;
    }

    abstract float getResultOfMathOperation(float firstNum, float secondNum);


}
