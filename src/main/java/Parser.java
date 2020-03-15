import java.util.ArrayList;

public class Parser {

    private String[] parsedExpression;
    private String initialExpression;

    public Parser(String e) throws RuntimeException {
        this.initialExpression = e;
        parse();
    }

    private void parse() throws RuntimeException {
        ArrayList<String> expression = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        int dCount = 0, oCount = 0;

        for (char token : initialExpression.toCharArray()) {
            if (Character.toString(token).matches("\\d")) {
                temp.append(token);
                continue;
            }
            if (Character.toString(token).matches("\\s")) {
                if (!temp.toString().isEmpty()) {
                    expression.add(temp.toString());
                    temp = new StringBuilder();
                    dCount++;
                }
                continue;
            }
            if (Character.toString(token).matches("[+\\-*/]")) {
                if (!temp.toString().isEmpty()) {
                    expression.add(temp.toString());
                    temp = new StringBuilder();
                    dCount++;
                }
                expression.add(Character.toString(token));
                oCount++;
            } else {
                throw new RuntimeException(Character.toString(token));
            }

        }
        if (dCount - oCount != 1) {
            throw new RuntimeException("Invalid Operator to Operand Ratio");
        }
        parsedExpression = expression.toArray(new String[0]);
    }

    public String[] getParsedExpression() {
        return parsedExpression;
    }
}
