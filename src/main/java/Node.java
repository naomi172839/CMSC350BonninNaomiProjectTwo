public abstract class Node {

    private String data;
    private Node left;
    private Node right;

    public Node(String data) {
        this.data = data;
        left = null;
        right = null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public abstract boolean isOperator();

    public abstract String getThreeAdd();
}
