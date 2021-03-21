package test.java;


import aboudproject.BinaryTree;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeTest {

    @Test
    private void ShouldAddNode() {
        BinaryTree testTree = new BinaryTree(50);
        assertEquals(50, testTree.getRoot().getValue());
    }

    @Test
    private void ShouldFindNode() {
        BinaryTree testTree = new BinaryTree(50);
        testTree.addNode(55);
        testTree.addNode(10);
        testTree.addNode(60);
        assertTrue(testTree.containsNode(60));
        assertTrue(testTree.containsNode(10));
        assertTrue(testTree.containsNode(55));
        assertTrue(testTree.containsNode(50));
    }

    @Test
    private void ShouldConvertToArrayList() {
        BinaryTree testTree = new BinaryTree(33);
        testTree.addNode(new int[]{5, 35, 1, 4, 20, 17, 31, 99});
        ArrayList<Integer> testArr = new ArrayList<>();
        testTree.convertToArrayList(testTree.getRoot(), testArr);
        assertArrayEquals(new Integer[]{1, 4, 5, 17, 20, 31, 33, 35, 99}, testArr.toArray());
    }
}
