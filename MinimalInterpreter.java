import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MinimalInterpreter {
    private final Map<String, Integer> numberVariables = new HashMap<>();   // To store numbers
    private final Map<String, String> stringVariables = new HashMap<>();    // To store strings
    private final Map<String, Boolean> booleanVariables = new HashMap<>();  // To store booleans

    private static final Pattern SINGULAR_EQUALS_PATTERN = Pattern.compile("(?<![=!<>+\\-*/%])=(?!=)");
    private static final Pattern ASSIGNMENT_OPERATOR_PATTERN = Pattern.compile("[+\\-*/%]=");


    public void eval(String code) {
        String[] lines = code.split("\n"); // Split by lines

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();

            // `x = 10;` -> `x = 10` (ensures that the code runs even with `;` at the end of the line)
            if (line.endsWith(";")) {
                line = line.substring(0, line.length() - 1);
            }

            // Skip empty lines and full-line comments
            if (line.isEmpty() || line.startsWith("#")) continue;

            // Remove inline comments (anything after `#`)
            int commentIndex = line.indexOf("#");
            if (commentIndex != -1) {  // indexOf() returns -1 if not found
                line = line.substring(0, commentIndex).trim();
            }

            // Reuse Matchers
            Matcher singularEqualsMatcher = SINGULAR_EQUALS_PATTERN.matcher(line);
            Matcher assignmentOperatorMatcher = ASSIGNMENT_OPERATOR_PATTERN.matcher(line);

            if (line.startsWith("print")) {  // If it starts with print, then print
                handlePrint(line);
            } else if (line.startsWith("while")) {
                i = handleWhileLoop(lines, i);
            } else if (line.startsWith("if")) {
                i = handleIfElse(lines, i);
            } else if (singularEqualsMatcher.find()) {  // Handle variable assignment for a singular "=" (e.g. `x = 10`)
                handleAssignment(line);
            } else if (assignmentOperatorMatcher.find()) {
                handleAssignmentOperator(line);
            }
        }
    }


    private void handleAssignment(String line) {
        String[] parts = line.split("(?<![=<>!])=(?!=)");  // Split variable assignment by assignment operator into variables
        String varName = parts[0].trim();     // Getting variable name by the first component of parts
        String expression = parts[1].trim();  // Get assigned expression

        // While reading string like 'anna' to replace ' with whitespace, so it will just print anna
        if ((expression.startsWith("'") && expression.endsWith("'")) || (expression.startsWith("\"") && expression.endsWith("\""))) {
            String value = expression.replaceAll("'","").replaceAll("\"", "");
            stringVariables.put(varName, value);  // Storing variable name and value into a map
            return;
        }

        // Handle direct boolean assignments
        if (expression.equalsIgnoreCase("True") || expression.equalsIgnoreCase("False")) {
            booleanVariables.put(varName, Boolean.parseBoolean(expression));
            return;
        }

        // Handle boolean expressions
        if (expression.matches(".*(\\band\\b|\\bor\\b|<|>|==|!=|<=|>=).*")) {
            boolean value = evalBool(expression);  // Evaluate the boolean expression
            booleanVariables.put(varName, value);  // Store the result
            return;
        }


        int value = evaluateExpression(expression);  // Evaluating expression and then assigning a value to a variable name

        numberVariables.put(varName, value);
    }


    private void handleAssignmentOperator(String line) {
        Pattern pattern = Pattern.compile("[+\\-*/%]=");
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            String operator = matcher.group();  // Extracts the matched operator
            String[] arr = line.split(Pattern.quote(operator), 2);  // Split by operator

            String variable = arr[0].trim();  // Extract variable
            String value = arr[1].trim();     // Extract value

            // If variable is a number, handle assignment based on an operator
            if (numberVariables.containsKey(variable)) {
                // Handle different cases
                switch (operator) {
                    case "+=":
                        numberVariables.put(variable, numberVariables.get(variable) + Integer.parseInt(value));
                        break;
                    case "-=":
                        numberVariables.put(variable, numberVariables.get(variable) - Integer.parseInt(value));
                        break;
                    case "*=":
                        numberVariables.put(variable, numberVariables.get(variable) * Integer.parseInt(value));
                        break;
                    case "/=":
                        if (value.equals("0")) {
                            throw new ArithmeticException("ZeroDivisionError: division by zero");
                        }
                        numberVariables.put(variable, numberVariables.get(variable) / Integer.parseInt(value));
                        break;
                    case "%=":
                        numberVariables.put(variable, numberVariables.get(variable) % Integer.parseInt(value));
                        break;
                    default:
                        System.out.println("Unknown operator.");
                }
            // If variable is a string, concatenate if `+=`, else throw an error
            } else if (stringVariables.containsKey(variable)) {
                // Handle correct `str += "str"` case
                if (operator.equals("+=") && ((value.startsWith("\"") && value.endsWith("\"")) || (value.startsWith("'") && value.endsWith("'")))) {
                    value = value.substring(1, value.length() - 1);
                    stringVariables.put(variable, stringVariables.get(variable) + value);
                } else {
                    throw new IllegalArgumentException("Error: Most likely either unsupported operator or a mixed string brackets");
                }
            } else {
                throw new IllegalArgumentException("NameError: name '" + variable + "' is not defined");
            }
        }
    }


    // For number expressions
    private int evaluateExpression(String expression) {
        String[] operands = expression.split("[+\\-*/%]");  // Split expression using operators
        int result = 0;

        try {
            result = Integer.parseInt(operands[0].trim());  // Try parsing the first operand as a number
        } catch (NumberFormatException e) {
            if (numberVariables.containsKey(operands[0].trim())) {
                result = numberVariables.get(operands[0].trim());  // Retrieve the value of the variable
            } else {
                throw new IllegalArgumentException("Undefined variable: " + operands[0].trim());
            }
        }

        // Iterate through the expression and apply operators
        int operatorIndex = 0;
        for (int i = 1; i < operands.length; i++) {
            int nextOperand = 0;  // At first, it is zero, but then gets assigned a value from below code
            char operator = expression.charAt(expression.indexOf(operands[i-1]) + operands[i-1].length());  // determining next operator

            try {
                nextOperand = Integer.parseInt(operands[i].trim());
            } catch (NumberFormatException e) {
                if (numberVariables.containsKey(operands[i].trim())) {
                    nextOperand = numberVariables.get(operands[i].trim());
                } else {
                    throw new IllegalArgumentException("Undefined variable: " + operands[i].trim());
                }
            }

            // Division by zero for `/` and `%`
            if ((operator == '/' || operator == '%') && nextOperand == 0) {
                throw new ArithmeticException("ZeroDivisionError: division by zero");
            }
            // Determining operators and then evaluating it
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


    // String evaluation, also handles concatenation
    private String evalString(String expression) {
        // First split on commas to handle comma-separated parts
        String[] commaParts = expression.split(",");
        StringBuilder result = new StringBuilder();

        // Process each comma-delimited part
        for (int i = 0; i < commaParts.length; i++) {
            String commaPart = commaParts[i].trim();
            // Now split each part by plus sign
            String[] plusParts = commaPart.split("\\+");
            StringBuilder partBuilder = new StringBuilder();

            for (String rawPart : plusParts) {
                String part = rawPart.trim();
                // If it's a quoted literal, remove the quotes
                if ((part.startsWith("'") && part.endsWith("'")) ||
                        (part.startsWith("\"") && part.endsWith("\""))) {
                    part = part.substring(1, part.length() - 1);
                    partBuilder.append(part);
                } else if (stringVariables.containsKey(part)) {
                    partBuilder.append(stringVariables.get(part));
                } else if (numberVariables.containsKey(part)) {
                    partBuilder.append(numberVariables.get(part));
                } else if (!part.isEmpty()) {
                    throw new IllegalArgumentException("Unrecognized string component: " + part);
                }
            }
            // Append the evaluated comma part to the final result.
            result.append(partBuilder);
            // If it's not the last comma-separated part, append a space.
            if (i < commaParts.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
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
            if (lines[j].contains("else")) break;  // Stop if the "else" block is encountered.
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
            j++;  // Identify the full block of the while loop
        }

        // Repeat the block as long as the condition is true
        while (evalBool(condition)) {
            int k = i + 1;

            while (k < j) {
                String line = lines[k].trim();

                // Check for nested if-else blocks
                if (line.startsWith("if ")) {
                    k = handleIfElse(lines, k);  // Process the nested if-else block
                } else if (!line.isEmpty()) {
                    eval(line);  // Process other statements
                }

                k++;
            }
        }

        return j - 1;  // Return the index of the last line of the loop
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


    // Checks if expression contains any string variables
    // Then if no string variable is found returns false, if it is found  returns true.
    private boolean containsStringVariable(String expression) {
        String[] stringParts = expression.split("[+|,]");

        for (String part : stringParts) {  // Splits the expression into parts based on operators, to iterate through every part.
            if (stringVariables.containsKey(part.trim())) {
                return true;  // Checks if the part exists as a key in the stringVariables map
            }
        }

        return false;
    }


    // Method that handles "print" command in the program
    private void handlePrint(String line) {
        // Extract the content between parentheses.
        String printBody = line.substring(line.indexOf('(') + 1, line.lastIndexOf(')')).trim();
        boolean containsConcatenationOperator = containsConcatenationOperator(printBody);

        // If the printBody is surrounded by quotes, it's either a string literal or a string concatenation
        if (isString(printBody)) {
            // Check for concatenation operator outside quotes
            if (!containsConcatenationOperator) {
                System.out.println(printBody.substring(1, printBody.length() - 1));
            } else {
                System.out.println(evalString(printBody));
            }
            return;
        }

        // Next, if the printBody matches a variable name, check in all variable maps.
        if (numberVariables.containsKey(printBody)) {
            System.out.println(numberVariables.get(printBody));
            return;
        }
        if (booleanVariables.containsKey(printBody)) {
            System.out.println(booleanVariables.get(printBody));
            return;
        }
        if (stringVariables.containsKey(printBody) && !containsConcatenationOperator) {
            System.out.println(stringVariables.get(printBody));
            return;
        }

        // If the printBody isn't surrounded by quotes, yet it contains concatenation operator(s),
        // it will most likely be a `string + variable` concatenation or vice versa.
        if (containsConcatenationOperator) {
            // Split a string with + and ,
            String[] concatParts = printBody.split("[+,]");
            StringBuilder finalString = new StringBuilder();  // This will be printed after appends of different parts
            boolean isStringConcatenation = true;  // To make sure every `part` is a valid string

            // For every concatenation part, convert it into appendable string and append it to `finalString`
            for (String part : concatParts) {
                part = part.trim();

                // If part is a string, remove quotes and append it
                if (isString(part)) {
                    part = part.substring(1, part.length() - 1);
                    finalString.append(part);
                }
                // If part is a variable string, append its value
                else if (stringVariables.containsKey(part)) {
                    finalString.append(evalString(part));
                }
                // If it's neither, do not print it
                else {
                    isStringConcatenation = false;
                }
            }

            // Print it only if it's a string concatenation
            if (isStringConcatenation) {
                System.out.println(finalString);
                return;
            }
        }

        // Otherwise, try to evaluate as a numeric expression.
        try {
            // Evaluate as a numeric expression.
            String numericResult = String.valueOf(evaluateExpression(printBody));
            System.out.println(numericResult);
            return;
        } catch (Exception e) {
            // If it fails, try to evaluate as a boolean expression.
            try {
                boolean boolResult = evalBool(printBody);
                System.out.println(boolResult);
                return;
            } catch (Exception ex) {
                // If nothing matches, then throw an error.
                throw new IllegalArgumentException("Unable to evaluate print expression: " + printBody);
            }
        }
    }


    // Check if it's a string (surrounded by quotes)
    private boolean isString(String line) {
        return (line.startsWith("'") && line.endsWith("'")) ||
                (line.startsWith("\"") && line.endsWith("\""));
    }


    // Helper method: checks if there is a top-level plus or comma outside any quotes.
    private boolean containsConcatenationOperator(String expr) {
        boolean inSingleQuote = false;
        boolean inDoubleQuote = false;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '\'' && !inDoubleQuote) {
                inSingleQuote = !inSingleQuote;
            } else if (c == '\"' && !inSingleQuote) {
                inDoubleQuote = !inDoubleQuote;
            } else if (!inSingleQuote && !inDoubleQuote) {
                if (c == '+' || c == ',') {
                    return true;
                }
            }
        }
        return false;
    }
}
