public class Main {
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
}
