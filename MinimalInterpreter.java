
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimalInterpreter {
    private final Map<String, Double> numberVariables = new HashMap<>(); // to store numbers
    private final Map<String, String> stringVariables = new HashMap<>(); //to store strings
    private final Map<String, Boolean> booleanVariables = new HashMap<>(); //to store booleans

    public void eval(String code) {
        String[] lines = code.split("\n"); // Split by lines
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue; //if line is a whitespace, skip and continue to other components

            // Handle variable assignment and also check if it is not a logical not operation != and == too
            if (line.contains("=")&&(!line.contains("!")||line.contains("=="))) {
                handleAssignment(line);
            }else if (line.startsWith("print")){
                handlePrint(line); //if it starts with print, then print
            }
        }
    }

    private void handleAssignment(String line) {
        String[] parts = line.split("="); //split variable assigment by assigment operator into varables
        String varName = parts[0].trim(); //getting variable name by the first component of parts
        String expression = parts[1].trim(); // get assigned expressionn

        if (expression.startsWith("'")){ //while reading string like 'anna' to replace ' with whitespace so it will just print anna
            String value = expression.replaceAll("'","");
            stringVariables.put(varName,value); //storing variable name and value into a map
            return;
        }

        if (expression.equals("True")){
            booleanVariables.put(varName, true); //assign boolean true to a variable
            return;
        }

        if (expression.equals("False")){
            booleanVariables.put(varName, false); //assign boolean false to a variable
            return;
        }

        double value = evaluateExpression(expression); //evaluating expression and then assigning a value to a variable name
        numberVariables.put(varName, value);

    }

    //for number expressions
    private double evaluateExpression(String expression){
        String[] operands = expression.split("[+\\-*/%]"); //splint expression using operators
        double result = 0;

        try{

            result = (double) Integer.parseInt(operands[0]);// if it is already a number an not x = 6, just 6, then assign it to result and just print it
        }catch (NumberFormatException e){
            result = numberVariables.get(operands[0]); //if it is assigment then get a variable name
        }


        // Iterate through the expression and apply operators
        int operatorIndex = 0;
        for (int i = 1; i < operands.length; i++) {
            double nextOperand = 0;  //at first it is zero, but then gets assigned a value from below code
            char operator = expression.charAt(expression.indexOf(operands[i-1 ]) + operands[i-1].length()); //determining next operator
            try{
                //same here if it just a number an not a vaariable, just parse it as a number
                nextOperand = Integer.parseInt(operands[i].trim());
            }catch (NumberFormatException e){
                nextOperand = numberVariables.get(operands[i]); // and if it is a variable get a value from a map
            }

            //determinig operators and then evaluating it
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

    private String evalString(String expression){
        String[] stringParts = expression.split("[+\\-,]"); //split expression
        StringBuilder builder = new StringBuilder(); //use string buider to create a string
        for (String s : stringParts){
            if (s.startsWith("'")&&!s.trim().equals("'")){ //to remove quotations, just replace all of it with whitesplace and then trim by it
                 builder.append(s.replaceAll("'", "").trim());
            }else if (stringVariables.containsKey(s.trim())){//if  there is a variable in stringVariables that has a value then append its value to a builder so you cn print that value for example if we have test = 'anna, and print(test), it will evaluate test and find its value in string variables and print anna and not test
                builder.append(stringVariables.get(s.trim()));
            }else if (s.trim().equals("'")){//single space added to builder
                builder.append(" ");
            }else{//// If the string is not a quote, treat it as a variable name and append its value from numberVariables
                builder.append(numberVariables.get(s.trim()));
            }
        }
        return builder.toString();
    }



    private int handleIfElse(String[] lines, int i) {
        String condition = lines[i].substring(2).trim();
        condition = condition.substring(0, condition.indexOf(":")).trim();

        boolean conditionResult = evalBool(condition);
        int j = i + 1;
        while (j < lines.length && lines[j].startsWith("    ")) {
            j++;
        }

        if (conditionResult) {
            eval(String.join("\n", Arrays.copyOfRange(lines, i + 1, j)));
        } else if (j < lines.length && lines[j].trim().startsWith("else")) {
            int k = j + 1;
            while (k < lines.length && lines[k].startsWith("    ")) {
                k++;
            }
            eval(String.join("\n", Arrays.copyOfRange(lines, j + 1, k)));
            j = k;
        }

        return j - 1;
    }


    private boolean evalBool(String expression){
        if (booleanVariables.containsKey(expression.trim())) return booleanVariables.get(expression.trim());
        boolean result = true;
        if (expression.contains("&")||expression.contains("|")){

            String[] parts = expression.split("[&|]");
            result = evalBool(parts[0].trim());
            for (int i = 0; i <parts.length-1 ; i++) {
                parts[i]=parts[i].trim();
                String operator = String.valueOf(expression.charAt(expression.indexOf(parts[i ]) + parts[i].length())).trim();
                if (parts[i].charAt(0)=='!'){
                    switch (operator){
                        case "&": result= result&&!evalBool(parts[i+1]);break;
                        case "|": result= result||!evalBool(parts[i+1]);break;
                    }
                }
                else {

                    switch (operator) {
                        case "&":
                            result = result && evalBool(parts[i + 1]);
                            break;
                        case "|":
                            result = result || evalBool(parts[i + 1]);
                            break;
                    }
                }
            }

            return result;


        }
        String[] stringParts = expression.split("!=|==|<|>|<=|>=");



        for (int i = 0; i <stringParts.length-1 ; i++) {
            stringParts[i]=stringParts[i].trim();
            String operator = String.valueOf(expression.charAt(expression.indexOf(stringParts[i ]) + stringParts[i].length())).trim();

            switch (operator){
                case "=" : result=evaluateExpression(stringParts[i])==evaluateExpression(stringParts[i+1]); break ;
                case "<" : result= evaluateExpression(stringParts[i])<evaluateExpression(stringParts[i+1]);break;
                case ">" : result = evaluateExpression(stringParts[i])>evaluateExpression(stringParts[i+1]);break;
                case "!" : result = evaluateExpression(stringParts[i])!=evaluateExpression(stringParts[i+1]);break;
                case "<=" : result= evaluateExpression(stringParts[i])<=evaluateExpression(stringParts[i+1]);break;
                case ">=" : result = evaluateExpression(stringParts[i])>=evaluateExpression(stringParts[i+1]);break;
            }
        }

        return result;

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
     // checks if expression contains any string variables
private boolean containsStringVariable(String expression){
    String[] stringParts = expression.split("[+\\-,]");
    for (String s : stringParts){ //splits the expression into parts based on operators , to iterate through every part.
        if (stringVariables.containsKey(s.trim())) return true;
    }//checks if the part exists as a key in the stringVariables map
    return false;
}

// then if no string variable is found returns false, if it is found  returns true.
// mathod that handles  "print" command in the program
private void handlePrint(String line) {
    String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
    if (stringVariables.containsKey(varName)){
        System.out.println(stringVariables.get(varName));
        return;// // Retrieve and print the value of the string variable
    }
    if (booleanVariables.containsKey(varName)||varName.contains("&")||varName.contains("|")
            ||varName.contains("<")||varName.contains(">")||varName.contains("==")||varName.contains("!=")){
        System.out.println(evalBool(varName));
        return;// If it's a boolean variable or contains boolean expressions, evaluate and print its value
    }
    if ((varName.contains("+")||varName.contains(","))&&containsStringVariable(varName)){
        System.out.println(evalString(varName));
        return;//If it's a concatenated string expression, evaluate and print its result
    }
    String toPrint = String.valueOf(evaluateExpression(varName));// numeric expressions
    // Evaluate the numeric expression and print the result
    System.out.println(toPrint);

}

}



