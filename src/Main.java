/**
 * Created by logan on 10/16/2017.
 */
public class Main {
    public static void main(String args[]) {
        String test1 = "1 / (3 + 2 + (9 * 11))";
        String test2 = "11 + ((1 / 2) * ((3 + -5) - 45)) + -23";
        Node node = new Node();
        Node node2 = node.CreateTree(test2);
        node2.infixPrint();
    }
}
