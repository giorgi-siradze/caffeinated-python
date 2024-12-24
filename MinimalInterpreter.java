
import java.util.HashMap;
import java.util.Map;

public class MinimalInterpreter {
    private final Map<String, Integer> variables = new HashMap<>(); // Variable storage

    public void eval(String code) {
        String[] lines = code.split("\n"); // Split by statement terminator
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            // Handle variable assignment
            if (line.contains("=")) {
                handleAssignment(line);
            }else if (line.startsWith("print")){
                handlePrint(line);
            }
        }
    }

    private void handleAssignment(String line) {
        String[] parts = line.split("=");
        String varName = parts[0].trim();
        String expression = parts[1].trim();
        int value = evaluateExpression(expression);
        variables.put(varName, value);
//        String[] numbers = expression.split("\\+");
//        int value = Integer.parseInt(numbers[0].trim()) + Integer.parseInt(numbers[1].trim());
        variables.put(varName, value);
    }

    private int evaluateExpression(String expression){
        String[] operands = expression.split("[+\\-*/%]");
        int result = 0;


        // Iterate through the expression and apply operators
        int operatorIndex = 0;
        for (int i = 1; i < operands.length; i++) {
            char operator = expression.charAt(expression.indexOf(operands[i - 1]) + operands[i - 1].length());
            int nextOperand = Integer.parseInt(operands[i].trim());

            switch (operator) {
                case '+': result += nextOperand; break;
                case '-': result -= nextOperand; break;
                case '*': result *= nextOperand; break;
                case '/': result /= nextOperand; break;
                case '%': result %= nextOperand; break;

            }
        }
        return result;
    }

    private void handlePrint(String line) {
        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
        String toPrint = String.valueOf(evaluateExpression(varName));
        System.out.println(variables.get(varName));

    }





    public static void main(String[] args) {
        MinimalInterpreter interpreter = new MinimalInterpreter();

        // Example program: Calculate and print the sum of 10 and 20
        String program = """
           x=5
           y=6
           print(x+y)
        """;

        interpreter.eval(program);
    }
}
