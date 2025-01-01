# Overview

This project is a minimalistic Python-like interpreter implemented in Java. It supports basic variable declarations, assignments, conditional statements, while loops, and the print function. The interpreter can evaluate expressions involving integers, booleans, and strings, enabling simple program execution.

<br>

# Features

1\. **Variable Declarations and Assignments**:

- Supports integer, string, and boolean variables.

- Syntax: `variable_name = value`

- Example: `x = 10`, `name = 'Anna'`, `isValid = True`

2\. **Print Statement**:

- Prints variables, literals, or expressions.

- Syntax: `print(expression)`

- Example: `print(x)`, `print(x + y + 4)`, `print('Hello')`, `print(x == y)`

3\. **Boolean Expressions**:

- Logical operators: `and`, `or`

- Comparison operators: `<`, `>`, `<=`, `>=`, `==`, `!=`

- Example: `print(x == 1 and y == 2 and x < 2)`

4\. **Control Flow**:

- **If-Else Statements**:

  - Supports conditions with indentation for blocks.

  - Syntax:

    ```python
    if condition:
      # Code block
    else:
      # Code block
    ```

  - Syntax for nested If-Else:

    ```python
    if x == 4:
        if y == 7:
            print(y)
        # This space cannot be used for a code, otherwise it MIGHT not work properly in SOME scenarios
            if x == 4 and y == 7:
                print("wow")
        # This line too
        print(x)
      # 
    ```

    To elaborate further, without extra space after If-Else, when it is nested, it may cause some issues.

    Let's take this example:

      ```python
      x = 1
      y = 2
      if x == 1:
          if y == 100:
              print(y)
          else:
              print("zoo")
          print("this")
          print(x)
      ```

    This causes the program not to execute `print("this")` and `print(x)` even though it should.

    Upon more testing, `else` block doesn't execute when the condition (`y == 100` in this case) is false.

- **While Loops**:

   - Repeats a block of code while a condition is true.

   - Syntax:

      ```python
      while condition:
          # Code block
      ```

      Just like above, it is recommended that you skip a line or use it for commenting only when using If-Else inside While Loop (even without Else part), otherwise it might not execute some limes or even go into an infinity loop.
    
5\. **Expression Evaluation**:

- Arithmetic operations: `+`, `-`, `*`, `/`, `%`

- String concatenation with `+` or `,`

6\. **Comments**:

- Lines starting with `#` are treated as comments.

- Example: `# This is a comment`

  However, `x = 10 # This is a comment` will also get ignored since it includes `#`

<br>

# Class and Methods

## `MinimalInterpreter`

This is the main class containing methods to parse and evaluate Python-like code.

<br>

## Attributes

- `numberVariables` - Map to store integer variables.

- `stringVariables` - Map to store string variables.

- `booleanVariables` - Map to store boolean variables.

<br>

## Methods

`void eval(String code)`

**Parses and evaluates a block of code.**

- Input: Multi-line Python-like code as a string.

- Functionality:

   - Splits code into lines.

   - Handles comments, variable assignments, print statements, if-else, and while loops.

<br>

`void handleAssignment(String line)`

**Processes variable assignments.**

- **Input**: A single line of code containing `=`.

- **Functionality**:

   - Identifies variable type and stores the value in the appropriate map.

   - Supports string literals, boolean values, and numeric expressions.

<br>

`int evaluateExpression(String expression)`

**Evaluates arithmetic expressions.**

- **Input**: An arithmetic expression as a string.

- **Output**: Evaluated integer result.

- **Functionality**:

  - Supports addition, subtraction, multiplication, division, and modulus.

  - Checks for division by zero and undefined variables.

<br>

`boolean evalBool(String expression)`

**Evaluates boolean expressions.**

- **Input**: A boolean expression as a string.

- **Output**: Evaluated boolean result.

- Functionality:

  - Handles logical and comparison operators.

  - Processes nested expressions.

<br>

`int handleIfElse(String[] lines, int i)`

**Processes `if-else` blocks.**

- **Input**: Array of lines and the current index.

- **Output**: Updated index after processing the block.

- **Functionality**:

  - Evaluates the condition and executes the corresponding block.

<br>

`int handleWhileLoop(String[] lines, int i)`

**Processes `while` loops.**

- **Input**: Array of lines and the current index.

- **Output**: Updated index after processing the block.

- **Functionality**:

  - Repeats the block of code as long as the condition is true.

<br>

`void handlePrint(String line)`

**Processes the `print` statement.**

- **Input**: A single line of code containing print.

- **Functionality**:

  - Prints string literals, variables, or evaluated expressions.

<br>

`String evalString(String expression)`

**Evaluates string expressions.**

- **Input**: A string concatenation expression.

- **Output**: Concatenated result.

- **Functionality**:

  - Resolves variables and literals, supporting concatenation with `+` or `,`.

<br>

`boolean containsStringVariable(String expression)`

**Checks if an expression contains string variables.**

- **Input**: A string expression.

- **Output**: Boolean indicating presence of string variables.

<br>

## Limitations

- No support for advanced Python features (e.g., functions, classes, imports).

- Although error handling works for many cases, it doesn't catch every single error.

- Comments after code on the same line (e.g., x = 10 # comment) are not supported.

- Only supports integer arithmetic; floating-point numbers are not handled.


<br>


# Overview of `Main.java` File

The `Main.java` file serves as the primary entry point for testing and executing the functionality of the custom Python interpreter implemented in this project. The file demonstrates how to utilize the `MinimalInterpreter` class to evaluate Python-like code strings, test various algorithms, and validate the interpreter's capabilities.

<br>

## Purpose

The `Main.java file` has two main purposes:

1\. **Interpreter Testing**: Includes Python-like code snippets to evaluate and verify the correctness of the interpreter.

2\. **Algorithm Testing**: Provides implementations of various algorithms, each written as Python-like code snippets and executed through the interpreter to ensure it can handle diverse logical and computational tasks.

<br>

## Structure

The file is structured as follows:

1\. **Interpreter Testing**:

- The `main` method contains a sample code snippet to test basic Python-like syntax, including variable assignment, conditional statements, and arithmetic operations.

- Example code:

    ```java
    String code = """
        x = 10
        y = 20
        if x < y:
            print('x is less than y')
        else:
            print('x is greater than or equal to y')
    
        z = x + y
        print(z)
        """;
    MinimalInterpreter interpreter = new MinimalInterpreter();
    interpreter.eval(code);
    ```

2\. **Algorithm Testing**:

- Several algorithms are implemented as static methods, each containing Python-like code for tasks such as:

  - Calculating the sum of the first N natural numbers.

  - Computing factorials.

  - Finding the greatest common divisor (GCD) using the Euclidean algorithm.

  - Reversing digits of an integer.

  - Checking if a number is prime.

  - Determining if a number is a palindrome.

  - Identifying the largest digit in an integer.

  - Summing the digits of a number.

  - Generating multiplication tables.

  - Computing the Nth Fibonacci number.

- Each method instantiates the `MinimalInterpreter` class and passes the code string to the `eval` method.

3\. Contributor Information:

- Each algorithm includes a description and the contributor's name, acknowledging their contribution to the project.

<br>

## Example Algorithm Method

Here is an example of one algorithm method included in the file:

**Task 1: Sum of N Numbers**

```java
public static void sumOfNNumbers() {
    String code = """
            input = 10
            sum = 0

            while input != 0:
                sum = sum + input
                input = input - 1

            print(sum)
            """;

    MinimalInterpreter interpreter = new MinimalInterpreter();
    interpreter.eval(code);
}
```

<br>

## Notes for Usage

- The `main` method should primarily be used for interpreter testing and debugging.

- Individual algorithms can be executed independently by uncommenting their respective method calls in the `main` method.

- Ensure the `MinimalInterpreter` class is correctly implemented and available in the same project to execute the code strings effectively.

This file showcases the versatility of the `MinimalInterpreter` and its ability to process Python-like syntax, making it a robust tool for educational and testing purposes.
