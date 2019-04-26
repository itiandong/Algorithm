package CodeGuide.ch02;

import java.util.Stack;

class Node {
    int value;
    Node left;
    Node right;

    void visit() {
        System.out.print(value + " ");
    }

    Node(int value) {
        this.value = value;
    }
}




public class TravelBinTree {

    /**
     * 先序递归版
     */
    static void preOrderRecur(Node node) {
        if(node == null) return;

        node.visit();
        preOrderRecur(node.left);
        preOrderRecur(node.right);
    }

    /**
     * 中序递归版
     */
    static void inOrderRecur(Node node) {
        if(node == null) return;

        inOrderRecur(node.left);
        node.visit();
        inOrderRecur(node.right);
    }

    /**
     * 后序递归版
     */
    static void postOrderRecur(Node node) {
        if(node == null) return;

        postOrderRecur(node.left);
        postOrderRecur(node.right);
        node.visit();
    }

    /**
     * 前序非递归版
     */
    static void preOrder(Node node) {
        if (node == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node tmp = stack.pop();
            tmp.visit();
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
        }
    }

    /**
     * 中序非递归版
     */
    static void inOrder(Node node) {
        if (node == null) return;

        Node curr = node;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || curr != null) { // stack 非空，或者当前值非空
            if (curr != null) { // ，当前值非空，则遍历并将其所有左后代依次压栈。
                stack.push(curr);
                curr = curr.left;
            } else { // 否则，
                curr = stack.pop(); // 弹出消费最后压栈的节点
                curr.visit();
                curr = curr.right; // 并将目光转向右子树（此时栈可能为空）
            }
        }

    }

    /**
     * 后序非递归版，方法1：使用两个栈。
     */
    static void postOrder1(Node node) {
        if (node == null) return;

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(node);
        while (!s1.isEmpty()) {
            Node curr = s1.pop();
            s2.push(curr);
            if (curr.left != null) s1.push(curr.left);
            if (curr.right != null) s1.push(curr.right);
        }

        while (!s2.isEmpty()) s2.pop().visit();
    }

    /**
     * 后序非递归版，方法2：使用一个栈。
     */
    static void postOrder2(Node node) {
        if (node == null) return;

        Stack<Node> stack = new Stack<>();
        Node h = node; // 最近一次消费的节点
        Node c = null; // 栈顶元素
        stack.push(h);
        while (!stack.isEmpty()) {
            c = stack.peek(); // 每次，取栈顶元素，
            if (c.left != null && h != c.left && h != c.right) { // c 有左孩子。若 c 的左孩子没访问（即左孩子或者右孩子没有刚刚被访问过），
                stack.push(c.left); // 那么，将左孩子压栈，
            } else if (c.right != null && h != c.right) { // 否则左孩子已经访问过，若 c 有右孩子，且右孩子没有被刚刚访问过，
                stack.push(c.right); // 右孩子压栈
            } else { // 若左右孩子都访问过，
                stack.pop().visit(); // 则访问 c
                h = c;
            }
        }
    }


    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        preOrderRecur(root);
        System.out.println();
        preOrder(root);

        System.out.println();
        System.out.println();

        inOrderRecur(root);
        System.out.println();
        inOrder(root);

        System.out.println();
        System.out.println();

        postOrderRecur(root);
        System.out.println();
        postOrder1(root);
        System.out.println();
        postOrder2(root);

    }

}
