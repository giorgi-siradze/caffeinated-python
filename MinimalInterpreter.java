import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimalInterpreter {
    private final Map<String, Integer> numberVariables = new HashMap<>(); // to store numbers
    private final Map<String, String> stringVariables = new HashMap<>(); // to store strings
    private final Map<String, Boolean> booleanVariables = new HashMap<>(); // to store booleans

    public void eval(String code) {
        String[] lines = code.split("\n"); // Split by lines

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();

            // Skip empty lines and full-line comments
            if (line.isEmpty() || line.startsWith("#")) continue;

            // If the comment doesn't start with #, it must be an inline comment
            // In this case, create a substring of `line` before # (cut everything after #, including #)
            if (line.contains("#")) {
                line = line.substring(0, line.indexOf("#")).trim();
            }


            if (line.startsWith("print")) { // if it starts with print, then print
                handlePrint(line);
            } else if (line.contains("while")) {
                i = handleWhileLoop(lines, i);
            } else if (line.contains("if")) {
                i = handleIfElse(lines, i);
            } else if (line.contains("=")) { // Handle variable assignment
                handleAssignment(line);
            }
        }
    }

    private void handleAssignment(String line) {
        String[] parts = line.split("(?<![=<>!])=(?!=)"); // split variable assignment by assignment operator into variables
        String varName = parts[0].trim(); // getting variable name by the first component of parts
        String expression = parts[1].trim(); // get assigned expression

        // while reading string like 'anna' to replace ' with whitespace, so it will just print anna
        if ((expression.startsWith("'") && expression.endsWith("'")) || (expression.startsWith("\"") && expression.endsWith("\""))) {
            String value = expression.replaceAll("'","").replaceAll("\"", "");
            stringVariables.put(varName, value); // storing variable name and value into a map
            return;
        }

        // Handle direct boolean assignments
        if (expression.equalsIgnoreCase("True") || expression.equalsIgnoreCase("False")) {
            booleanVariables.put(varName, Boolean.parseBoolean(expression));
            return;
        }

        // Handle boolean expressions
        if (expression.matches(".*(\\band\\b|\\bor\\b|<|>|==|!=|<=|>=).*")) {
            boolean value = evalBool(expression); // Evaluate the boolean expression
            booleanVariables.put(varName, value); // Store the result
            return;
        }


        int value = evaluateExpression(expression); // evaluating expression and then assigning a value to a variable name

        numberVariables.put(varName, value);
    }

    // For number expressions
    private int evaluateExpression(String expression) {
        String[] operands = expression.split("[+\\-*/%]"); // split expression using operators
        int result = 0;

        try {
            result = Integer.parseInt(operands[0].trim()); // Try parsing the first operand as a number
        } catch (NumberFormatException e) {
            if (numberVariables.containsKey(operands[0].trim())) {
                result = numberVariables.get(operands[0].trim()); // Retrieve the value of the variable
            } else {
                throw new IllegalArgumentException("Undefined variable: " + operands[0].trim());
            }
        }

        // Iterate through the expression and apply operators
        int operatorIndex = 0;
        for (int i = 1; i < operands.length; i++) {
            int nextOperand = 0;  // at first, it is zero, but then gets assigned a value from below code
            char operator = expression.charAt(expression.indexOf(operands[i-1]) + operands[i-1].length()); // determining next operator

            try {
                nextOperand = Integer.parseInt(operands[i].trim());
            } catch (NumberFormatException e) {
                if (numberVariables.containsKey(operands[i].trim())) {
                    nextOperand = numberVariables.get(operands[i].trim());
                } else {
                    throw new IllegalArgumentException("Undefined variable: " + operands[i].trim());
                }
            }

            // division by zero for `/` and `%`
            if ((operator == '/' || operator == '%') && nextOperand == 0) {
                throw new ArithmeticException("Division by zero is not allowed.");
            }
            // determining operators and then evaluating it
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


    private String evalString(String expression) {
        String[] stringParts = expression.split("[+\\-,]"); // split expression
        StringBuilder builder = new StringBuilder(); // use string builder to create a string
        for (String s : stringParts){
            if (s.startsWith("'")&&!s.trim().equals("'")){ // to remove quotations, just replace all of it with whitespace and then trim by it
                builder.append(s.replaceAll("'", "").trim());
            } else if (stringVariables.containsKey(s.trim())){ // if  there is a variable in stringVariables that has a value then append its value to a builder so you cn print that value for example if we have test = 'anna, and print(test), it will evaluate test and find its value in string variables and print anna and not test
                builder.append(stringVariables.get(s.trim()));
            } else if (s.trim().equals("'")){ // single space added to builder
                builder.append(" ");
            } else {//// If the string is not a quote, treat it as a variable name and append its value from numberVariables
                builder.append(numberVariables.get(s.trim()));
            }
        }
        return builder.toString();
    }


    private int handleIfElse(String[] lines, int i) {
        // Extract the condition from the current "if" statement.
        String condition = lines[i].trim().substring(2);
        condition = condition.substring(0, condition.indexOf(":")).trim();

        // Evaluate the boolean result of the condition.
        boolean conditionResult = evalBool(condition);
        int j = i + 1;

        // Find the range of lines that belong to the "if" block by checking for indented lines.
        while (j < lines.length && lines[j].startsWith("    ")) {
            if (lines[j].contains("else")) break; // Stop if the "else" block is encountered.
            j++;
        }

        // Execute the "if" block, if the condition is true.
        if (conditionResult) {
            eval(String.join("\n", Arrays.copyOfRange(lines, i + 1, j)));
        }

        // Check if there is an "else" block after the "if".
        if (j < lines.length && lines[j].trim().startsWith("else")) {
            int k = j + 1;

            // Find the range of lines that belong to the "else" block by checking for indented lines.
            while (k < lines.length && lines[k].startsWith("    ")) {
                k++;
            }

            // Execute the "else" block if the condition is false.
            if (!conditionResult) {
                eval(String.join("\n", Arrays.copyOfRange(lines, j + 1, k)));
            }

            // Update the index to the last line of the "else" block.
            j = k;
        }

        // Return the index of the last line processed in the "if-else" construct.
        return j - 1;
    }


    private int handleWhileLoop(String[] lines, int i) {
        // Extract the condition from the "while" line
        String condition = lines[i].substring(5).trim();
        condition = condition.substring(0, condition.indexOf(":")).trim();

        // Find the block of code belonging to the loop
        int j = i + 1;

        while (j < lines.length && (lines[j].startsWith("    ") || lines[j].isEmpty())) {
            j++; // Identify the full block of the while loop
        }

        // Repeat the block as long as the condition is true
        while (evalBool(condition)) {
            int k = i + 1;

            while (k < j) {
                String line = lines[k].trim();

                // Check for nested if-else blocks
                if (line.startsWith("if ")) {
                    k = handleIfElse(lines, k); // Process the nested if-else block
                } else if (!line.isEmpty()) {
                    eval(line); // Process other statements
                }

                k++;
            }
        }

        return j - 1; // Return the index of the last line of the loop
    }


    private boolean evalBool(String expression) {
        // If it catches a boolean variable, it simply returns it
        if (booleanVariables.containsKey(expression.trim())) {
            return booleanVariables.get(expression.trim());
        }

        boolean result = true;

        // Handle logical operators
        if (expression.contains("and") || expression.contains("or")) {
            String[] parts = expression.split("and|or");
            result = evalBool(parts[0].trim());

            for (int i = 0; i < parts.length - 1; i++) {
                int startIndex = expression.indexOf(parts[i]) + parts[i].length();
                int endIndex = expression.indexOf(parts[i + 1]);

                if (startIndex < 0 || endIndex < 0 || startIndex > endIndex) {
                    throw new IllegalArgumentException("Invalid indices in boolean expression parsing: " +
                            "start=" + startIndex + ", end=" + endIndex +
                            ", expression='" + expression + "'");
                }

                String operator = expression.substring(startIndex, endIndex).trim();
                if (!operator.equals("and") && !operator.equals("or")) {
                    throw new IllegalArgumentException("Unknown operator: '" + operator + "'");
                }

                result = switch (operator) {
                    case "and" -> result && evalBool(parts[i + 1].trim());
                    case "or" -> result || evalBool(parts[i + 1].trim());
                    default -> result;
                };
            }

            return result;
        }

        // Handle comparison operators
        String[] stringParts = expression.split("\\s*(==|!=|<=|>=|<|>)\\s*");

        if (stringParts.length != 2) {
            throw new IllegalArgumentException("Malformed comparison in expression: " + expression);
        }

        String left = stringParts[0].trim();
        String right = stringParts[1].trim();
        String operator = expression.substring(expression.indexOf(left) + left.length(), expression.indexOf(right)).trim();

        return switch (operator) {
            case "==" -> evaluateExpression(left) == evaluateExpression(right);
            case "!=" -> evaluateExpression(left) != evaluateExpression(right);
            case "<" -> evaluateExpression(left) < evaluateExpression(right);
            case ">" -> evaluateExpression(left) > evaluateExpression(right);
            case "<=" -> evaluateExpression(left) <= evaluateExpression(right);
            case ">=" -> evaluateExpression(left) >= evaluateExpression(right);
            default -> throw new IllegalArgumentException("Unknown comparison operator: '" + operator + "'");
        };
    }


    // checks if expression contains any string variables
    // then if no string variable is found returns false, if it is found  returns true.
    private boolean containsStringVariable(String expression) {
        String[] stringParts = expression.split("[+\\-,]");
        for (String s : stringParts){ // splits the expression into parts based on operators , to iterate through every part.
            if (stringVariables.containsKey(s.trim())) return true;
        } //checks if the part exists as a key in the stringVariables map
        return false;
    }

    // method that handles "print" command in the program
    private void handlePrint(String line) {
        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();

        // Handles String
        if ((varName.startsWith("'") && varName.endsWith("'")) || (varName.startsWith("\"") && varName.endsWith("\""))) {
            // It's a string literal, print it as is
            System.out.println(varName.substring(1, varName.length() - 1));  // Remove the surrounding quotes
            return;
        }

        // Handles String variable
        if (stringVariables.containsKey(varName)) {
            System.out.println(stringVariables.get(varName));
            return; //// Retrieve and print the value of the string variable
        }

        // Handle boolean variables and expressions
        if (booleanVariables.containsKey(varName) || varName.matches(".*(\\band\\b|\\bor\\b|<|>|==|!=|<=|>=).*")) {
            boolean value = booleanVariables.containsKey(varName) ? booleanVariables.get(varName) : evalBool(varName);
            System.out.println(value);
            return;
        }

        // Handles String concatenation
        if ((varName.contains("+") || varName.contains(",")) && containsStringVariable(varName)) {
            System.out.println(evalString(varName));
            return; //If it's a concatenated string expression, evaluate and print its result
        }

        String toPrint = String.valueOf(evaluateExpression(varName)); // numeric expressions
        // Evaluate the numeric expression and print the result
        System.out.println(toPrint);

    }
}
