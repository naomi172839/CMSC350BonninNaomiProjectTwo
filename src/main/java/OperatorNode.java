public class OperatorNode extends Node {

    private boolean operator;
    private String threeAdd;

    public OperatorNode(String data) {
        super(data);
        operator = true;
        threeAdd = toAssembly(data);
    }

    public boolean isOperator() {
        return operator;
    }

    private String toAssembly(String data) {
        switch (data) {
            case ("+"):
                return "ADD";
            case ("-"):
                return "SUB";
            case ("*"):
                return "MUL";
            case ("/"):
                return "DIV";
            default:
                return "ERR";
        }

    }

    public String getThreeAdd() {
        return threeAdd;
    }
}
