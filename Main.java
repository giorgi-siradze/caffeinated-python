public class Main {
    public static void main(String[] args) {
        // This method is for main code testing, NOT for algorithms.
        String code = """
            n = 1
            x = 0
            # while x < 5:
            #    if x % 2 == 0:
            #        print("Even")
            #    else:
            #        print("Odd")
            #        
            #    print(x)
            #    x = x + 1
            
            # bool = True
            # bool1 = n == x
            # print(n == x)
            """;

        MinimalInterpreter interpreter = new MinimalInterpreter();
        interpreter.eval(code);

        // Algorithms should be called below.
//        sumOfNNumbers(); // Works as intended
//        factorial(); // Works as intended
//        gcd(); // Works as intended
//        reverseNumber(); // Works as intended
//        isPrime(); // Works as intended
        isPalindrome(); // Doesn't work as intended, needs revision
//        largestDigit(); // Doesn't work as intended, needs revision
//        sumOfDigits(); // Works as intended
//        multiplicationTable(); // Works as intended
//        NthFibonacci(); // Works as intended
    }

    // Task 1
    // Contributor: Giorgi Siradze
    public static void sumOfNNumbers() {
        String code = """
                n = 10
                sum = 0
                while n != 0:
                    sum = sum + n
                    n = n - 1.0
                print(sum)
                """;

        MinimalInterpreter interpreter = new MinimalInterpreter();
        interpreter.eval(code);
    }

    // Task 2
    // Contributor: Giorgi Siradze
    public static void factorial() {
        String code = """
            n = 5
            result = 1
            while n > 1:
                result = result * n
                n = n - 1
            print(result)
            """;

        MinimalInterpreter interpreter = new MinimalInterpreter();
        interpreter.eval(code);
    }

    // Task 3
    // Contributor: Ana Narmania
    public static void gcd() {
        String code = """
                a = 30
                b = 15
                while b != 0:
                      temp = b
                      b = a % b
                      a = temp
                print(a)
                """;

        MinimalInterpreter interpreter = new MinimalInterpreter();
        interpreter.eval(code);
    }

    // Task 4
    // Contributor: Ana Narmania
    public static void reverseNumber() {
        String code = """
                a = 1234
                reversed_a = 0
                while a != 0:
                      remainder = a % 10
                      a = a - remainder
                      a = a / 10
                      reversed_a = reversed_a * 10 + remainder
                print(reversed_a)
                """;

        MinimalInterpreter interpreter = new MinimalInterpreter();
        interpreter.eval(code);
    }


    // Task 5
    // Contributor: Nini Phkhakadze
    public static void isPrime() {
        String code = """
            n = 17
            prime = True

            if n < 2:
                prime = False

            i = 2
        
            while i * i <= n:
                if n % i == 0:
                    prime = False
                    
                i = i + 1

            print(prime)
            """;

        MinimalInterpreter interpreter = new MinimalInterpreter();
        interpreter.eval(code);
    }

    // Task 6
    // Contributor: Nini Phkhakadze
    public static void isPalindrome() {
//        String code = """
//                n = 121
//                original = n
//                reversed_n = 0
//                while n > 0:
//                    remainder = n % 10
//                    reversed_n = reversed_n * 10 + remainder
//                    n = n - remainder
//                    n = n / 10
//                print(original == reversed_n)
//                """;
//
//        String code = """
//                n = 121
//                original = n
//                reversed = 0
//                digit = 0
//
//                while n > 1:
//                    digit = n % 10
//                    print("digit:")
//                    print(digit)
//                    reversed = reversed * 10 + digit
//                    print("reversed:")
//                    print(reversed)
//                    n = n / 10
//                    print("n:")
//                    print(n)
//
//                print(original == reversed)
//                """;

        String code = """
                n = 121
                original = n
                reversed_num = 0
            
                while n > 0:
                    # Extract the last digit
                    remainder = n % 10
                    # Append it to reversed_num
                    reversed_num = reversed_num * 10 + remainder
                    # Remove the last digit from the number
                    n = n / 10
            
                # Check if the original number is the same as the reversed number
                
                print(original)
                print(reversed_num)
                
                original_temp = original
                reversed_num_temp = reversed_num
                
                if original_temp == reversed_num_temp:
                    print("true")
                
                
                """;


        MinimalInterpreter interpreter = new MinimalInterpreter();
        interpreter.eval(code);
    }

    // Task 7
    // Contributor: Nini Phkhakadze
    public static void largestDigit() {
        String code = """
            n = 3247
            largest_digit = 0
            largest_more_digit = 0
            digit_more_largest = 0
            while n != 0:
                digit = n % 10
                print(digit)
                largest_more_digit = largest_digit > digit
                print(largest_more_digit)
                digit_more_largest = digit > largest_digit
                print(digit_more_largest)
                largest_digit = largest_digit * largest_more_digit + digit * digit_more_largest
                print(largest_digit)
                n = n / 10
            print(largest_digit)
            """;

        MinimalInterpreter interpreter = new MinimalInterpreter();
        interpreter.eval(code);
    }

    // Task 8
    // Contributor: Tatia Tkeshelashvili
    public static void sumOfDigits() {
        String code = """
                number = 1234
                total = 0
                digit = 0
                while number > 0:
                    digit = number % 10
                    total = total + digit
                    number = number - digit
                    number = number / 10
                print(total)
                """;

        MinimalInterpreter interpreter = new MinimalInterpreter();
        interpreter.eval(code);
    }

    // Task 9
    // Contributor: Tatia Tkeshelashvili
    public static void multiplicationTable() {
        String code = """
                n = 1
                p = 5
                while n <= 10:
                    print(p * n)
                    n = n + 1
                """;

        MinimalInterpreter interpreter = new MinimalInterpreter();
        interpreter.eval(code);
    }

    // Task 10
    // Contributor: Tatia Tkeshelashvili
    public static void NthFibonacci() {
        String code = """
                N = 10
                first = 0
                second = 1
                count = 1
                while count < N:
                    first = first + second
                    second = first - second
                    count = count + 1
                print(first)
                """;

        MinimalInterpreter interpreter = new MinimalInterpreter();
        interpreter.eval(code);
    }
}
