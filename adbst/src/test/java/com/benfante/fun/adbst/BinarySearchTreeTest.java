package com.benfante.fun.adbst;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {

    private BinarySearchTree bst;
    private Set<BSTNode> examinated;

    @Before
    public void setup() {
        bst = new BinarySearchTree();
        examinated = new HashSet<>();
    }
    
    @Test
    public void testEmpty() {
        assertNull(bst.root);
    }

    @Test
    public void testInsertSingleElement() {
        bst.insert(1);
        assertNotNull(bst.root);
        assertEquals(1, bst.root.data);
        assertNull(bst.root.left);
        assertNull(bst.root.rigth);
        validateBst();
        assertEquals(1, examinated.size());
    }

    @Test
    public void testInsertSimpleSequence() {
        bst.insert(1);
        bst.insert(2);
        bst.insert(3);
        bst.insert(4);
        bst.insert(5);
        bst.insert(6);
        bst.insert(7);
        bst.insert(8);
        bst.insert(9);
        bst.insert(10);
        bst.insert(11);
        assertEquals(11, bst.root.data);
        validateBst();
        assertEquals(11, examinated.size());
    }

    @Test
    public void testInsertSimpleInvertedSequence() {
        bst.insert(11);
        bst.insert(10);
        bst.insert(9);
        bst.insert(8);
        bst.insert(7);
        bst.insert(6);
        bst.insert(5);
        bst.insert(4);
        bst.insert(3);
        bst.insert(2);
        bst.insert(1);
        assertEquals(1, bst.root.data);
        validateBst();
        assertEquals(11, examinated.size());
    }

    @Test
    public void testInsertAlternateElements() {
        bst.insert(5);
        bst.insert(3);
        bst.insert(4);
        assertEquals(4, bst.root.data);
        validateBst();
        assertEquals(3, examinated.size());
    }

    @Test
    public void testInsertDuplicatedElements() {
        bst.insert(5);
        bst.insert(4);
        bst.insert(5);
        assertEquals(5, bst.root.data);
        validateBst();
        assertEquals(2, examinated.size());
    }

    private void validateBst() {
        validateBst(bst.root);
    }

    private void validateBst(BSTNode root) {
        if (root != null) {
            assertFalse("Error, circular reference ("+root.data+")", examinated.contains(root));
            examinated.add(root);
            if (root.left != null) {
                validateLeftBranch(root, root.left);
                validateBst(root.left);
            }
            if (root.rigth != null) {
                validateRightBranch(root, root.rigth);
                validateBst(root.rigth);
            }
        }
    }

    private void validateLeftBranch(BSTNode root, BSTNode current) {
        if (current != null) {
            assertTrue("An element in the left branch is not lower than its root: "+current.data+" >= "+root.data, current.data < root.data);
            if (current.left != null) {
                validateLeftBranch(root, current.left);
            }
            if (current.rigth != null) {
                validateLeftBranch(root, current.rigth);
            }
        }
    }

    private void validateRightBranch(BSTNode root, BSTNode current) {
        if (current != null) {
            assertTrue("An element in the right branch is not higher than its root: "+current.data+" <= "+root.data, current.data > root.data);
            if (current.left != null) {
                validateRightBranch(root, current.left);
            }
            if (current.rigth != null) {
                validateRightBranch(root, current.rigth);
            }
        }
    }

}