package aboudproject;

import java.security.InvalidKeyException;
import java.util.ArrayList;

public class BinaryTree {
    /** Вариант 12 — бинарное дерево поиска.
     * Хранит целые числа в виде бинарного дерева поиска.
     * Дерево не может содержать одно и то же число более одного раза.
     *
     * Методы: добавление числа, удаление числа, поиск числа в дереве,
     * определение соседей числа в дереве (предок, левый потомок, правый потомок).
     */
    private final Node root;


    public BinaryTree(int value) {
        root = new Node(value);
    }

    public BinaryTree(int value, int[] nodeValues) throws InvalidKeyException {
        root = new Node(value);
        for (int nodeValue: nodeValues) {
            addNode(nodeValue);
        }
    }

    public Node getRoot() {
        return root;
    }

    public void addNode(int value) throws InvalidKeyException {
        Node localRoot = root;
        while (true) {
            if (value == localRoot.getValue()) {
                throw new InvalidKeyException("Value " + localRoot.getValue() + " is already exist");
            } else if (value < localRoot.getValue()) {
                if (localRoot.getLeft() == null) {
                    localRoot.setLeft(new Node(value));
                    return;
                } else {
                    localRoot = localRoot.getLeft();
                }
            } else {
                if (localRoot.getRight() == null) {
                    localRoot.setRight(new Node(value));
                    return;
                } else {
                    localRoot = localRoot.getRight();
                }
            }
        }
    }

    public void addNode(int[] values) throws InvalidKeyException {
        for (int value: values) {
            addNode(value);
        }
    }

    public Node findNode(int value) throws InvalidKeyException {
        Node localRoot = root;
        Node fatherRoot = localRoot; // Нужно для определения потомка

        while (true) {
            if (value == localRoot.getValue()) {
                return localRoot;
            } else if (value < localRoot.getValue()) {
                if (localRoot.getLeft() == null) {
                    throw new InvalidKeyException("Value " + localRoot.getValue() + " is not found");
                } else {
                    localRoot = localRoot.getLeft();
                }
            } else {
                if (localRoot.getRight() == null) {
                    throw new InvalidKeyException("Value " + localRoot.getValue() + " is not found");
                } else {
                    localRoot = localRoot.getRight();
                }
            }
        }
    }

    private Node findFather(int value) throws InvalidKeyException {
        Node localRoot = root;
        Node fatherRoot = localRoot;

        while (true) {
            if (value == localRoot.getValue()) {
                return fatherRoot;
            } else if (value < localRoot.getValue()) {
                if (localRoot.getLeft() == null) {
                    throw new InvalidKeyException("Value " + localRoot.getValue() + " is not found");
                } else {
                    fatherRoot = localRoot;
                    localRoot = localRoot.getLeft();
                }
            } else {
                if (localRoot.getRight() == null) {
                    throw new InvalidKeyException("Value " + localRoot.getValue() + " is not found");
                } else {
                    fatherRoot = localRoot;
                    localRoot = localRoot.getRight();
                }
            }
        }
    }

    public int[] getNeighbours(int value) throws InvalidKeyException {
        int[] neighbours = new int[3];
        // 0 - Предок, 1 - Младший потомок, 2 - Старший потомок;
        Node currentNode = findNode(value);
        neighbours[0] = findFather(value).getValue();
        neighbours[1] = currentNode.getLeft().getValue();
        neighbours[2] = currentNode.getRight().getValue();
        return neighbours;
    }

    public void deleteNode(int value) throws InvalidKeyException {
        Node nodeToDelete = findNode(value);
        Node father = findFather(value);
        if (nodeToDelete.isLeaf()) {
            if (father.getLeft() == nodeToDelete) {
                father.setLeft(null);
            } else {
                father.setRight(null);
            }
        } else if (nodeToDelete.getLeft() != null && nodeToDelete.getRight() != null) {
            if (father.getLeft() == nodeToDelete) {
                Node nodeToReplace = nodeToDelete.getRight();
                while (nodeToReplace.getLeft() != null) {
                    nodeToReplace = nodeToReplace.getLeft();
                }
                int value2 = nodeToReplace.getValue();
                deleteNode(value2);
                nodeToDelete.setValue(value2);
            } else {
                Node nodeToReplace = nodeToDelete.getLeft();
                while (nodeToReplace.getRight() != null) {
                    nodeToReplace = nodeToReplace.getRight();
                }
                int value2 = nodeToReplace.getValue();
                deleteNode(value2);
                nodeToDelete.setValue(value2);
            }
        } else if (nodeToDelete.getRight() != null) {
            if (father.getLeft() == nodeToDelete) {
                father.setLeft(nodeToDelete.getRight());
            } else {
                father.setRight(nodeToDelete.getRight());
            }
        } else {
            if (father.getLeft() == nodeToDelete) {
                father.setLeft(nodeToDelete.getLeft());
            } else {
                father.setRight(nodeToDelete.getLeft());
            }
        }
    }

    public void convertToArrayList(Node root, ArrayList<Integer> retArr) {
        if (root != null) {
            convertToArrayList(root.getLeft(), retArr);
            retArr.add(root.getValue());
            convertToArrayList(root.getRight(), retArr);
        }
    }
   
}