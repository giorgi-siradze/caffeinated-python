public class Main {
    public static void main(String[] args) {
        String code = """
                n = 5
                i = 1
                while n >=1:
                          i = i * n
                          n = n - 1
                print(i)
                """;

        MinimalInterpreter interpreter2 = new MinimalInterpreter();
        interpreter2.eval(code);
    }
}
