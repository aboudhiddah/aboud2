
package aboudproject;

public class main {

    public static void main(String[] args) {
        BinaryTree testTree = new BinaryTree(50);
        testTree.addNode(55);
        testTree.addNode(10);
        testTree.addNode(60);
        testTree.addNode(51);
        System.out.println(testTree.deleteNode(50));
        System.out.println(testTree.getRoot());
    }
}