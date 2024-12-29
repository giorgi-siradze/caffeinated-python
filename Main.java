public class Main {
    public static void main(String[] args) {
      // This method is for main code testing, NOT for algorithms.
      String code = """
                a = 10
                b = 10
                print(a == b)
                """;

        MinimalInterpreter interpreter = new MinimalInterpreter();
        interpreter.eval(code);

        // Algorithms should be called below.
//        sumOfNNumbers(); // Works as intended
//        factorial(); // Works as intended
//        gcd(); // Works as intended
//        reverseNumber(); // Works as intended, but returns Double
//        isPrime(); // Doesn't work as intended, needs revision
//        isPalindrome(); // Doesn't work as intended, needs revision
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
            n = 14
            is_prime = 1
            i = 2
            while i * i <= n:
                is_prime = is_prime * (n % i != 0)
                i = i + 1
            is_prime = is_prime * (n > 1)
            print(is_prime == 1)
            """;
//
//        String code = """
//            n = 13
//            prime = True
//
//            if n < 2:
//                prime = False
//
//            i = 2
//            while i * i <= n:
//                if n % i == 0:
//                    prime = False
//                i = i + 1
//
//            print(prime)
//            """;

//        String code = """
//                n = 19
//                prime = True
//
//                if n < 2:
//                    prime = False
//
//                i = 2
//                while prime and i < n:
//                    if n % i == 0:
//                        prime = False
//                    i = i + 1
//
//                print(prime)
//                """;

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

//        String code = """
//                n = 121
//                original = n
//                reversed = 0
//                digit = 0
//
//                while n > 0:
//                    digit = n % 10
//                    reversed = reversed * 10 + digit
//                    n = n - digit
//                    n = n / 10
//                print(original == reversed)
//                """;

        MinimalInterpreter interpreter = new MinimalInterpreter();
//        interpreter.eval(code);
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
                
                largest_more_digit = largest_digit > digit
                print(largest_digit > digit)
                print(largest_more_digit)
                digit_more_largest = digit > largest_digit
                print(digit_more_largest)
                largest_digit = largest_digit * largest_more_digit + digit * digit_more_largest
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
