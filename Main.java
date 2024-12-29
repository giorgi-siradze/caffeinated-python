public class Main {
    public static void main(String[] args) {
      
      // This method is for main code testing, NOT for algorithms.
//      String code = """
//                """;
//
//        MinimalInterpreter interpreter2 = new MinimalInterpreter();
//        interpreter2.eval(code);

        // Algorithms should be called below.
        gcd(); // Works as intended
        reverseAnumber(); // Works as intended
        sumOfDigits(); // Works as intended
        multiplicationTable(); // Works as intended
        NthFibonacci(); // Works as intended
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

        MinimalInterpreter interpreter2 = new MinimalInterpreter();
        interpreter2.eval(code);
    }

    // Task 4
    // Contributor: Ana Narmania
    public static void reverseAnumber() {
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

        MinimalInterpreter interpreter2 = new MinimalInterpreter();
        interpreter2.eval(code);
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

        MinimalInterpreter interpreter2 = new MinimalInterpreter();
        interpreter2.eval(code);
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

        MinimalInterpreter interpreter2 = new MinimalInterpreter();
        interpreter2.eval(code);
    }

    // Task 10
    // Contributor: Tatia Tkeshelashvili
    public static void NthFibonacci()
    {
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

        MinimalInterpreter interpreter2 = new MinimalInterpreter();
        interpreter2.eval(code);
    }

}
