import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MinimalInterpreterTest {
    /// Task 1
    @Test
    void task1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            String code = """
                input = 10
                sum = 0
                
                while input != 0:
                    sum = sum + input
                    input -= 1
                
                print(sum)  # Should return 55
                """;

            MinimalInterpreter interpreter = new MinimalInterpreter();
            interpreter.eval(code);

            // DEBUG PRINT: Show what was printed
//            System.out.println("Actual output:\n" + outContent.toString());

            // Normalize output for consistent comparison
            String actualOutput = outContent.toString().replace("\r\n", "\n").trim();

            // Expected output
            String expectedOutput = """
                55
                """.trim();

            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Reset System.out
            System.setOut(originalOut);
        }
    }

    /// Task 2
    @Test
    void task2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            String code = """
                input = 5
                result = 1
                
                while input > 1:
                    result = result * input
                    input = input - 1
                
                print(result)  # Should return 120
                """;

            MinimalInterpreter interpreter = new MinimalInterpreter();
            interpreter.eval(code);

            // DEBUG PRINT: Show what was printed
//            System.out.println("Actual output:\n" + outContent.toString());

            // Normalize output for consistent comparison
            String actualOutput = outContent.toString().replace("\r\n", "\n").trim();

            // Expected output
            String expectedOutput = """
                120
                """.trim();

            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Reset System.out
            System.setOut(originalOut);
        }
    }

    /// Task 3
    @Test
    void task3() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            String code = """
                # `a` and `b` are input variables
                a = 30
                b = 15
                
                while b != 0:
                    temp = b
                    b = a % b
                    a = temp
                print(a)  # Should return 15
                """;

            MinimalInterpreter interpreter = new MinimalInterpreter();
            interpreter.eval(code);

            // DEBUG PRINT: Show what was printed
//            System.out.println("Actual output:\n" + outContent.toString());

            // Normalize output for consistent comparison
            String actualOutput = outContent.toString().replace("\r\n", "\n").trim();

            // Expected output
            String expectedOutput = """
                15
                """.trim();

            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Reset System.out
            System.setOut(originalOut);
        }
    }

    /// Task 4
    @Test
    void task4() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            String code = """
                input = 1234;
                reversed = 0;
                while input != 0:
                      remainder = input % 10
                      input = input - remainder
                      input /= 10
                      reversed = reversed * 10 + remainder

                print(reversed)  # Should return 4321
                """;

            MinimalInterpreter interpreter = new MinimalInterpreter();
            interpreter.eval(code);

            // DEBUG PRINT: Show what was printed
//            System.out.println("Actual output:\n" + outContent.toString());

            // Normalize output for consistent comparison
            String actualOutput = outContent.toString().replace("\r\n", "\n").trim();

            // Expected output
            String expectedOutput = """
                4321
                """.trim();

            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Reset System.out
            System.setOut(originalOut);
        }
    }

    /// Task 5
    @Test
    void task5() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            String code = """
                input = 19
                prime = True;
    
                if input < 2:
                    prime = False
    
                i = 2
            
                while i * i <= input:
                    if input % i == 0:
                        prime = False
                
                    i += 1
    
                print(prime)  # Should return true
                """;

            MinimalInterpreter interpreter = new MinimalInterpreter();
            interpreter.eval(code);

            // DEBUG PRINT: Show what was printed
//            System.out.println("Actual output:\n" + outContent.toString());

            // Normalize output for consistent comparison
            String actualOutput = outContent.toString().replace("\r\n", "\n").trim();

            // Expected output
            String expectedOutput = """
                true
                """.trim();

            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Reset System.out
            System.setOut(originalOut);
        }
    }

    /// Task 6
    @Test
    void task6() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            String code = """
                input = 12321
                
                # `o` is used for storing an original `input`
                o = input
                
                # `r` stores the reversed variant of `input`
                r = 0
            
                while input > 0:
                    # Extract the last digit
                    remainder = input % 10
                    # Append it to reversed_num
                    r = r * 10 + remainder
                    # Remove the last digit from the number
                    input /= 10
            
                # Check if the original number is the same as the reversed number
                if o == r:
                    print("true")
                else:
                    print("false")
                
                # Should return true
                """;

            MinimalInterpreter interpreter = new MinimalInterpreter();
            interpreter.eval(code);

            // DEBUG PRINT: Show what was printed
//            System.out.println("Actual output:\n" + outContent.toString());

            // Normalize output for consistent comparison
            String actualOutput = outContent.toString().replace("\r\n", "\n").trim();

            // Expected output
            String expectedOutput = """
                true
                """.trim();

            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Reset System.out
            System.setOut(originalOut);
        }
    }

    /// Task 7
    @Test
    void task7() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            String code = """
                input = 739182
                largest = 0
                digit = 0
            
                while input > 0:
                    digit = input % 10
                    if digit > largest:
                        largest = digit
    
                    input = input / 10
            
                print("Largest digit:")
                print(largest)
                
                # Should return 9
                """;

            MinimalInterpreter interpreter = new MinimalInterpreter();
            interpreter.eval(code);

            // DEBUG PRINT: Show what was printed
//            System.out.println("Actual output:\n" + outContent.toString());

            // Normalize output for consistent comparison
            String actualOutput = outContent.toString().replace("\r\n", "\n").trim();

            // Expected output
            String expectedOutput = """
                Largest digit:
                9
                """.trim();

            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Reset System.out
            System.setOut(originalOut);
        }
    }

    /// Task 8
    @Test
    void task8() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            String code = """
                input = 1234
                total = 0
                digit = 0
                
                while input > 0:
                    # retrieve the last digit of an input
                    digit = input % 10
                
                    # add that last digit to `total`
                    total = total + digit
                
                    # this essentially removes the last digit
                    input = input / 10
                
                print(total)  # Should return 10
                """;

            MinimalInterpreter interpreter = new MinimalInterpreter();
            interpreter.eval(code);

            // DEBUG PRINT: Show what was printed
//            System.out.println("Actual output:\n" + outContent.toString());

            // Normalize output for consistent comparison
            String actualOutput = outContent.toString().replace("\r\n", "\n").trim();

            // Expected output
            String expectedOutput = """
                10
                """.trim();

            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Reset System.out
            System.setOut(originalOut);
        }
    }

    /// Task 9
    @Test
    void task9() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            String code = """
                n = 1
                # "input" as in for which number it should generate a multiplication table
                input = 5
                
                # change the right side of the equation (10) if you want to change the quantity of numbers in a table
                while n <= 10:
                    print(input * n)
                    n = n + 1
                
                # Should return 5, 10, 15, ... , 50 with \\n instead of semicolons
                """;

            MinimalInterpreter interpreter = new MinimalInterpreter();
            interpreter.eval(code);

            // DEBUG PRINT: Show what was printed
//            System.out.println("Actual output:\n" + outContent.toString());

            // Normalize output for consistent comparison
            String actualOutput = outContent.toString().replace("\r\n", "\n").trim();

            // Expected output
            String expectedOutput = """
                5
                10
                15
                20
                25
                30
                35
                40
                45
                50
                """.trim();

            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Reset System.out
            System.setOut(originalOut);
        }
    }

    /// Task 10
    @Test
    void task10() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            String code = """
                input = 10
                
                first = 0
                second = 1
                count = 1
                
                while count < input:
                    first = first + second
                    second = first - second
                    count = count + 1
                
                print(first)  # Should return 34
                """;

            MinimalInterpreter interpreter = new MinimalInterpreter();
            interpreter.eval(code);

            // DEBUG PRINT: Show what was printed
//            System.out.println("Actual output:\n" + outContent.toString());

            // Normalize output for consistent comparison
            String actualOutput = outContent.toString().replace("\r\n", "\n").trim();

            // Expected output
            String expectedOutput = """
                34
                """.trim();

            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Reset System.out
            System.setOut(originalOut);
        }
    }

    // Common test
    @Test
    void testEvalPrintStatements() {
        // Redirect System.out to capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            String code = """
                str = "a";
                print(str)
                str += 'b';
                print(str)
                x = 10
                y = x + 5
                print(y);
                """;

            MinimalInterpreter interpreter = new MinimalInterpreter();
            interpreter.eval(code);

            // DEBUG PRINT: Show what was printed
//            System.out.println("Actual output:\n" + outContent.toString());

            // Normalize output for consistent comparison
            String actualOutput = outContent.toString().replace("\r\n", "\n").trim();

            // Expected output
            String expectedOutput = """
                a
                ab
                15
                """.trim();

            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Reset System.out
            System.setOut(originalOut);
        }
    }

    // Exception Test
//    @Test
//    void testEvalPrintdStatements() {
//        // Redirect System.out to capture output
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        PrintStream originalOut = System.out;
//        System.setOut(new PrintStream(outContent));
//
//        String code = """
//                x = 10 = 5
//                """;
//
//        MinimalInterpreter interpreter = new MinimalInterpreter();
//
//        assertThrows(RuntimeException.class, () -> {
//            interpreter.eval(code);
//        });
//    }
}
