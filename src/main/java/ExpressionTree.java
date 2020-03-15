import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;

public class ExpressionTree {

    private Node root;
    private StringBuilder temp;
    private BufferedWriter out;
    private String infix;
    private int rCount;

    public ExpressionTree(String[] e) throws IOException {
        constructTree(e);
        temp = new StringBuilder();
        out = new BufferedWriter(new FileWriter("three.txt", true));
        rCount = 0;
        out.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("LLL dd, yyyy HH:mm:ss")) + "\nPostfix Expression: ");
        for (String s : e) {
            out.write(s + " ");
        }
        out.write("\n");
        inOrder(root);
        infix = temp.toString();
        out.write("Infix Expression: " + infix + "\n\n");
        out.flush();
        out.close();

    }

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*");
    }

    private void constructTree(String[] expression) {
        Stack<Node> holder = new Stack<>();
        Node parent, left, right;

        for (String token : expression) {
            if (!isOperator(token)) {
                parent = new OperandNode(token);
                holder.push(parent);
            } else {
                parent = new OperatorNode(token);
                right = holder.pop();
                left = holder.pop();

                parent.setLeft(left);
                parent.setRight(right);

                holder.push(parent);
            }
        }
        root = holder.peek();
        holder.pop();
    }

    private void inOrder(Node n) throws IOException {
        if (n != null) {
            if (n.isOperator()) {
                temp.append("(");
                //System.out.println(n.getData() + " "+n.getLeft().getData() + " " + n.getRight().getData());
            }
            inOrder(n.getLeft());
            temp.append(n.getData());
            inOrder(n.getRight());
            if (n.isOperator()) {
                //System.out.println(n.getThreeAdd() + " R"+rCount +" "+n.getLeft().getData() + " " + n.getRight().getData());
                out.write(n.getThreeAdd() + " R" + rCount + " " + n.getLeft().getData() + " " + n.getRight().getData() + "\n");
                n.setData("R" + rCount);
                rCount++;
            }
            if (n.isOperator()) {
                temp.append(")");
            }
        }
    }

    public String getInfix() {
        return infix;
    }
}
