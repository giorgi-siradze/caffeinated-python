public class Main {
    public static void main(String[] args) {
        // This method is for main code (interpreter itself) testing, NOT for algorithms.
        String code = """
            
            """;

        MinimalInterpreter interpreter = new MinimalInterpreter();
        interpreter.eval(code);

        // Algorithms should be called below.
//        sumOfNNumbers(); // Works as intended
//        factorial(); // Works as intended
//        gcd(); // Works as intended
//        reverseNumber(); // Works as intended
//        isPrime(); // Works as intended
//        isPalindrome(); // Works as intended
//        largestDigit(); // Works as intended
//        sumOfDigits(); // Works as intended
//        multiplicationTable(); // Works as intended
//        NthFibonacci(); // Works as intended
    }

    // Task 1
    // Description: Calculate the sum of the first N natural numbers using loop
    // Contributor: Giorgi Siradze
    public static void sumOfNNumbers() {
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
    }

    // Task 2
    // Description: Compute the factorial of a given number N
    // Contributor: Giorgi Siradze
    public static void factorial() {
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
    }

    // Task 3
    // Description: Find the greatest common divisor (GCD) of two integers using the Euclidean algorithm
    // Contributor: Ana Narmania
    public static void gcd() {
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
    }

    // Task 4
    // Description: Reverse the digits of an integer
    // Contributor: Ana Narmania
    public static void reverseNumber() {
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
    }


    // Task 5
    // Description: Determine if a given number N is a prime number
    // Contributor: Nini Phkhakadze
    public static void isPrime() {
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
    }

    // Task 6
    // Description: Check if an integer reads the same backward and forward
    // Contributor: Nini Phkhakadze
    public static void isPalindrome() {
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
    }

    // Task 7
    // Description: Identify the largest digit in a given integer
    // Contributor: Nini Phkhakadze
    public static void largestDigit() {
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
    }

    // Task 8
    // Description: Calculate the sum of the digits of a number
    // Contributor: Tatia Tkeshelashvili
    public static void sumOfDigits() {
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
    }

    // Task 9
    // Description: Generate and print the multiplication table for a given number up to 10
    // Contributor: Tatia Tkeshelashvili
    public static void multiplicationTable() {
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
    }

    // Task 10
    // Description: Compute the Nth Fibonacci number using iteration or recursion
    // Contributor: Tatia Tkeshelashvili
    public static void NthFibonacci() {
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
    }
}
