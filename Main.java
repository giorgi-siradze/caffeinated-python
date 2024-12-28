public class Main {
    public static void main(String[] args) {
      
      
      String code = """
                """;

        MinimalInterpreter interpreter2 = new MinimalInterpreter();
        interpreter2.eval(code);
    }


    // Task 1
    public static void sumOfDigits() {
        String code = """
                number = 1234
                total = 0
                digit = 0
                while number > 0:
                    digit = number % 10
                    total += digit
                    number = number - digit
                    number = number / 10
                print(total)
                """;

        MinimalInterpreter interpreter2 = new MinimalInterpreter();
        interpreter2.eval(code);
    }

    // Task 9
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
