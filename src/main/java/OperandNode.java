public class OperandNode extends Node {

    private boolean operator;

    public OperandNode(String data) {
        super(data);
        operator = false;
    }

    public boolean isOperator() {
        return operator;
    }

    @Override
    public String getThreeAdd() {
        return "ERR";
    }

    public int toInt() {
        return Integer.parseInt(this.getData());
    }
}
