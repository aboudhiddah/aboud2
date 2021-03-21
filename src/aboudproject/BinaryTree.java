package aboudproject;

import java.util.ArrayList;

public class BinaryTree {
    /**
     * Вариант 12 — бинарное дерево поиска.
     * Хранит целые числа в виде бинарного дерева поиска.
     * Дерево не может содержать одно и то же число более одного раза.
     * <p>
     * Методы: добавление числа, удаление числа, поиск числа в дереве,
     * определение соседей числа в дереве (предок, левый потомок, правый потомок).
     */
    private Node root;

    public BinaryTree(int value) {
        root = new Node(value);
    }

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(int value, int[] nodeValues) {
        root = new Node(value);
        for (int nodeValue : nodeValues) {
            addNode(nodeValue);
        }
    }

    public Node getRoot() {
        return root;
    }

    public boolean addNode(int value) {
        if (root == null) {
            root = new Node(value);
            return true;
        }
        Node localRoot = root;

        while (true) {
            if (value == localRoot.getValue()) {
                return false;
            } else if (value < localRoot.getValue()) {
                if (localRoot.getLeft() == null) {
                    localRoot.setLeft(new Node(value));
                    return true;
                } else {
                    localRoot = localRoot.getLeft();
                }
            } else {
                if (localRoot.getRight() == null) {
                    localRoot.setRight(new Node(value));
                    return true;
                } else {
                    localRoot = localRoot.getRight();
                }
            }
        }
    }

    public void addNode(int[] values) {
        for (int value : values) {
            addNode(value);
        }
    }

    private Node findNode(int value) {
        //Эта функция нужна для поиска Node в findFatherNode и deleteNode.
        // Функция containsNode делает то же самое, но возвращает boolean.
        Node localRoot = root;

        while (true) {
            if (value == localRoot.getValue()) {
                return localRoot;
            } else if (value < localRoot.getValue()) {
                if (localRoot.getLeft() == null) {
                    return null;
                } else {
                    localRoot = localRoot.getLeft();
                }
            } else {
                if (localRoot.getRight() == null) {
                    return null;
                } else {
                    localRoot = localRoot.getRight();
                }
            }
        }
    }

    public boolean containsNode(int value) {
        Node nodeToCheck = findNode(value);
        return nodeToCheck != null;
    }

    private Node findFatherNode(int value) {
        if (value == root.getValue()) {
            return null;
        }
        Node localRoot = root;
        Node fatherRoot = localRoot;

        while (true) {
            if (value == localRoot.getValue()) {
                return fatherRoot;
            } else if (value < localRoot.getValue()) {
                if (localRoot.getLeft() == null) {
                    return null;
                } else {
                    fatherRoot = localRoot;
                    localRoot = localRoot.getLeft();
                }
            } else {
                if (localRoot.getRight() == null) {
                    return null;
                } else {
                    fatherRoot = localRoot;
                    localRoot = localRoot.getRight();
                }
            }
        }
    }

    public Node[] getNeighbours(int value) {
        // 0 - Предок, 1 - Младший потомок, 2 - Старший потомок;
        Node currentNode = findNode(value);
        if (currentNode == null) { // Если Node не найден - возвращает [null, null, null]
            return new Node[] {null, null, null};
        }
        Node[] neighbours = new Node[3];
        neighbours[0] = findFatherNode(value);
        neighbours[1] = currentNode.getLeft();
        neighbours[2] = currentNode.getRight();
        return neighbours;
    }

    public boolean deleteNode(int value) {
        Node nodeToDelete = findNode(value);
        if (nodeToDelete == null) {
            return false;
        }
        Node father = findFatherNode(value);
        boolean fatherFlag = false;
        if (father == null) {
            father = nodeToDelete;
            fatherFlag = true;
        }

        if (nodeToDelete.isLeaf()) {
            if (fatherFlag) {
                root = null;
            } else if (father.getLeft() == nodeToDelete) {
                father.setLeft(null);
            } else {
                father.setRight(null);
            }
            return true;
        } else if (nodeToDelete.getLeft() != null && nodeToDelete.getRight() != null) {
            Node nodeToReplace;
            if (fatherFlag) {
                nodeToReplace = father.getRight();
                while (nodeToReplace.getLeft() != null) {
                    nodeToReplace = nodeToReplace.getLeft();
                }
                int valueToReplace = nodeToReplace.getValue();
                deleteNode(valueToReplace);
                root.setValue(valueToReplace);
            } else if (father.getLeft() == nodeToDelete) {
                nodeToReplace = nodeToDelete.getRight();
                while (nodeToReplace.getLeft() != null) {
                    nodeToReplace = nodeToReplace.getLeft();
                }
                int valueToReplace = nodeToReplace.getValue();
                deleteNode(valueToReplace);
                nodeToDelete.setValue(valueToReplace);
            } else {
                nodeToReplace = nodeToDelete.getLeft();
                while (nodeToReplace.getRight() != null) {
                    nodeToReplace = nodeToReplace.getRight();
                }
                int valueToReplace = nodeToReplace.getValue();
                deleteNode(valueToReplace);
                nodeToDelete.setValue(valueToReplace);
            }
            return true;
        } else if (nodeToDelete.getRight() != null) {
            if (father.getLeft() == nodeToDelete) {
                father.setLeft(nodeToDelete.getRight());
            } else {
                father.setRight(nodeToDelete.getRight());
            }
            return true;
        } else {
            if (father.getLeft() == nodeToDelete) {
                father.setLeft(nodeToDelete.getLeft());
            } else {
                father.setRight(nodeToDelete.getLeft());
            }
            return true;
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