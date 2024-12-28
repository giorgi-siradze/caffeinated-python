import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimalInterpreter {
    private final Map<String, Double> numberVariables = new HashMap<>(); // to store numbers
    private final Map<String, String> stringVariables = new HashMap<>(); //to store strings
    private final Map<String, Boolean> booleanVariables = new HashMap<>(); //to store booleans

    public void eval(String code) {
        String[] lines = code.split("\n"); // Split by lines

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();

            if (line.isEmpty()) continue; // Skip empty lines



            // Handle variable assignment and also check if it is not a logical not operation != and == too
            if (line.contains("=") && (!line.contains("!") && !line.contains("==") && !line.contains("<") && !line.contains(">"))) {
                handleAssignment(line);
            } else if (line.startsWith("print")) {
                handlePrint(line); // if it starts with print, then print
            } else if (line.contains("while")) {
                i = handleWhileLoop(lines, i);
            } else if(line.contains("if")){
                i = handleIfElse(lines,i);
            }

        }
    }

    private void handleAssignment(String line) {
        String[] parts = line.split("="); // split variable assignment by assignment operator into variables
        String varName = parts[0].trim(); // getting variable name by the first component of parts
        String expression = parts[1].trim(); // get assigned expression

        if ((expression.startsWith("'") && expression.endsWith("'")) || (expression.startsWith("\"") && expression.endsWith("\""))) { // while reading string like 'anna' to replace ' with whitespace so it will just print anna
            String value = expression.replaceAll("'","").replaceAll("\"", "");
            stringVariables.put(varName, value); // storing variable name and value into a map
            return;
        }

        if (expression.equals("True")) {
            booleanVariables.put(varName, true); // assign boolean true to a variable
            return;
        }

        if (expression.equals("False")) {
            booleanVariables.put(varName, false); // assign boolean false to a variable
            return;
        }

        double value = evaluateExpression(expression); // evaluating expression and then assigning a value to a variable name
        numberVariables.put(varName, value);

    }

    //for number expressions
    private double evaluateExpression(String expression) {
        String[] operands = expression.split("[+\\-*/%]"); // split expression using operators
        double result = 0;

        try {
            result = Double.parseDouble(operands[0].trim()); // Try parsing the first operand as a number
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
            double nextOperand = 0;  // at first, it is zero, but then gets assigned a value from below code
            char operator = expression.charAt(expression.indexOf(operands[i-1]) + operands[i-1].length()); // determining next operator

            try {
                nextOperand = Double.parseDouble(operands[i].trim());
            } catch (NumberFormatException e) {
                if (numberVariables.containsKey(operands[i].trim())) {
                    nextOperand = numberVariables.get(operands[i].trim());
                } else {
                    throw new IllegalArgumentException("Undefined variable: " + operands[i].trim());
                }
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

        // Execute the "if" block if the condition is true.
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
        while (j < lines.length && (lines[j].startsWith("    ") || lines[j].equals(""))) {
            j++;
        }

        // Repeat the block as long as the condition is true
        while (evalBool(condition)) {
            eval(String.join("\n", Arrays.copyOfRange(lines, i + 1, j)));
        }

        return j - 1; // Return the index of the last line of the loop
    }

    private boolean evalBool(String expression) {
        if (booleanVariables.containsKey(expression.trim())) return booleanVariables.get(expression.trim());
        boolean result = true;

        if (expression.contains("and") || expression.contains("or")) {
            String[] parts = expression.split("and||or");
            result = evalBool(parts[0].trim());

            for (int i = 0; i < parts.length-1; i++) {
                parts[i] = parts[i].trim();
                String operator = expression.substring(expression.indexOf(parts[i])+parts[i].length(),expression.indexOf(parts[i+1])).trim();
                if (parts[i].charAt(0) == '!'){
                    result = switch (operator) {
                        case "and" -> result && !evalBool(parts[i + 1]);
                        case "or" -> result || !evalBool(parts[i + 1]);
                        default -> result;
                    };
                } else {
                    result = switch (operator) {
                        case "and" -> result && evalBool(parts[i + 1]);
                        case "or" -> result || evalBool(parts[i + 1]);
                        default -> result;
                    };
                }
            }

            return result;
        }

        String[] stringParts = expression.split("!=|==|<=|>=|<|>");

        for (int i = 0; i < stringParts.length-1; i++) {
            stringParts[i] = stringParts[i].trim();
            int endIndex = 0;
            String operator = expression.substring(expression.indexOf(stringParts[i])+stringParts[i].length(),expression.indexOf(stringParts[i+1])).trim();

            result = switch (operator) {
                case "==" -> evaluateExpression(stringParts[i]) == evaluateExpression(stringParts[i + 1]);
                case "<" -> evaluateExpression(stringParts[i]) < evaluateExpression(stringParts[i + 1]);
                case ">" -> evaluateExpression(stringParts[i]) > evaluateExpression(stringParts[i + 1]);
                case "!=" -> evaluateExpression(stringParts[i]) != evaluateExpression(stringParts[i + 1]);
                case "<=" -> evaluateExpression(stringParts[i]) <= evaluateExpression(stringParts[i + 1]);
                case ">=" -> evaluateExpression(stringParts[i]) >= evaluateExpression(stringParts[i + 1]);
                default -> result;
            };
        }

        return result;
    }


    // checks if expression contains any string variables
    private boolean containsStringVariable(String expression) {
        String[] stringParts = expression.split("[+\\-,]");
        for (String s : stringParts){ // splits the expression into parts based on operators , to iterate through every part.
            if (stringVariables.containsKey(s.trim())) return true;
        } //checks if the part exists as a key in the stringVariables map
        return false;
    }

    // then if no string variable is found returns false, if it is found  returns true.
    // method that handles  "print" command in the program
    private void handlePrint(String line) {
        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();

        // Handles String
        if ((varName.startsWith("'") && varName.endsWith("'")) || (varName.startsWith("\"") && varName.endsWith("\""))) {
            // It's a string literal, print it as is
            System.out.println(varName.substring(1, varName.length() - 1));  // Remove the surrounding quotes
            return;
        }

        // Handles Variable
        if (stringVariables.containsKey(varName)){
            System.out.println(stringVariables.get(varName));
            return; //// Retrieve and print the value of the string variable
        }

        if (booleanVariables.containsKey(varName) || varName.contains("&") || varName.contains("|")
                || varName.contains("<") || varName.contains(">") || varName.contains("==") || varName.contains("!=")) {
            System.out.println(evalBool(varName));
            return; // If it's a boolean variable or contains boolean expressions, evaluate and print its value
        }
        if ((varName.contains("+") || varName.contains(",")) && containsStringVariable(varName)) {
            System.out.println(evalString(varName));
            return; //If it's a concatenated string expression, evaluate and print its result
        }
        String toPrint = String.valueOf(evaluateExpression(varName)); // numeric expressions
        // Evaluate the numeric expression and print the result
        System.out.println(toPrint);

    }
}
