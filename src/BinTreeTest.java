import java.util.Stack;

public class BinTreeTest {

    /**
     * LeetCode 上二叉树的定义
     * 为了方便，加入 visit() 方法，访问一个节点；equal() 方法，从数的角度判断二叉树是否相等。
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        void visit() {
            System.out.print(val + " ");
        }
        boolean equal(TreeNode n) {
/*            if (n == null) return false;
            if (left == null && right == null) return n.val == val && n.left == null && n.right == null;
            if (left == null && right != null) return n.val == val && n.left == null && right.equal(n.right);
            if (left != null && right == null) return n.val == val && n.right == null && left.equal(n.left);
            if (left != null && right != null) return n.val == val && left.equal(n.left) && right.equal(n.right);*/

            if (n == null) return false;
            if (left == null && right == null) return n.val == val && n.left == null && n.right == null;
            if (left == null) return n.val == val && n.left == null && right.equal(n.right);
            if (right == null) return n.val == val && n.right == null && left.equal(n.left);
            return n.val == val && left.equal(n.left) && right.equal(n.right);
        }
    }

    /**
     * 通过[前后]序和中序序列构造二叉树，返回根节点。
     */
    public static TreeNode reConstruction(int[] preOrder, int[] inOrder, int[] postOrder) {
        class reConstructions {
            TreeNode reConstructionByPostAndIn(int[] postOrder, int[] inOrder, int lo1, int lo2, int len) {
                if (len == 0) return null;

                int rootVal = postOrder[lo1 + len - 1];
                TreeNode node = new TreeNode(rootVal);
                int leftLen = 0;
                for (int i = lo2; inOrder[i] != rootVal; ++i, ++leftLen);
                node.left = reConstructionByPostAndIn(postOrder, inOrder, lo1, lo2, leftLen);
                node.right = reConstructionByPostAndIn(postOrder, inOrder, lo1 + leftLen, lo2 + leftLen + 1, len - leftLen - 1);
                return node;
            }
            TreeNode reConstructionByPreAndIn(int[] preOrder, int[] inOrder, int lo1, int lo2, int len) {
                if (len == 0) return null;

                int rootVal = preOrder[lo1];
                TreeNode node = new TreeNode(rootVal);
                int leftLen = 0;
                for (int i = lo2; inOrder[i] != rootVal ; ++i, ++leftLen);
                node.left = reConstructionByPreAndIn(preOrder, inOrder, lo1 + 1, lo2, leftLen);
                node.right = reConstructionByPreAndIn(preOrder, inOrder, lo1 + leftLen + 1, lo2 + leftLen + 1, len - leftLen - 1);
                return node;
            }
        }
        reConstructions tools = new reConstructions();
        if (inOrder == null || !(preOrder == null && postOrder != null || preOrder != null && postOrder == null)) {
            throw new IllegalArgumentException("只支持通过中序序列和前、后序序列中的一个重构二叉树。");
        }
        TreeNode root = null;
        if (postOrder == null) {
            if (preOrder.length != inOrder.length) {
                throw new IllegalArgumentException("前、中序遍历序列长度不同!");
            }
            root = tools.reConstructionByPreAndIn(preOrder, inOrder, 0, 0, inOrder.length);
        } else {
            if (postOrder.length != inOrder.length) {
                throw new IllegalArgumentException("后、中序遍历序列长度不同!");
            }
            root = tools.reConstructionByPostAndIn(postOrder, inOrder, 0, 0, postOrder.length);
        }
        return root;
    }

    /**
     * 先序递归版
      */
    static void preOrderRecur(TreeNode node) {
        if(node == null) return;
        node.visit();
        preOrderRecur(node.left);
        preOrderRecur(node.right);
    }

    /**
     * 中序递归版
     */
    static void inOrderRecur(TreeNode node) {
        if(node == null) return;

        inOrderRecur(node.left);
        node.visit();
        inOrderRecur(node.right);
    }

    /**
     * 后序递归版
     */
    static void postOrderRecur(TreeNode node) {
        if(node == null) return;

        postOrderRecur(node.left);
        postOrderRecur(node.right);
        node.visit();
    }

    /**
     * 先序非递归版 - 1
     * 注意右先入栈
     * 不能推广到其他遍历算法
     */
    static void preOrder1(TreeNode node) {
        if (node == null) return;

        Stack<TreeNode> aux = new Stack<>();
        aux.push(node);

        while (!aux.isEmpty()) {
            TreeNode tmp = aux.pop();
            tmp.visit();
            if (tmp.right != null) aux.push(tmp.right);
            if (tmp.left != null) aux.push(tmp.left);
        }
    }

    /**
     * 先序非递归版 - 2
     * 以左链的方法抽象
     */
    static void preOrder2(TreeNode node) {
        if (node == null) return;

        Stack<TreeNode> aux = new Stack<>();
        while (true) { // 遍历左子树，
            while (node != null) {
                node.visit();
                if (node.right != null) {
                    aux.push(node.right);
                }
                node = node.left;
            }
            if (aux.isEmpty()) break;
            node = aux.pop();
        }
    }

    /**
     * 中序非递归版 - 1
     */
    static void inOrder1(TreeNode node) {
        if (node == null) return;

        TreeNode curr = node;
        Stack<TreeNode> stack = new Stack<>();
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
     * 中序非递归版 - 2
     */
    static void inOrder2(TreeNode node) {
        if (node == null) return;

        Stack<TreeNode> aux = new Stack<>();
        while (true) {
            while (node != null) {
                aux.push(node);
                node = node.left;
            }
            if (aux.isEmpty()) break;
            node = aux.pop();
            node.visit();
            node = node.right;
        }

    }

    /**
     * 后序非递归版，方法1：使用两个栈。
     */
    static void postOrder1(TreeNode node) {
        if (node == null) return;

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(node);
        while (!s1.isEmpty()) {
            TreeNode curr = s1.pop();
            s2.push(curr);
            if (curr.left != null) s1.push(curr.left);
            if (curr.right != null) s1.push(curr.right);
        }

        while (!s2.isEmpty()) s2.pop().visit();
    }

    /**
     * 后序非递归版，方法2：使用一个栈。
     */
    static void postOrder2(TreeNode node) {
        if (node == null) return;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode h = node; // 最近一次消费的节点
        TreeNode c = null; // 栈顶元素
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
        BinTreeTest btt = new BinTreeTest();
        int[] preOrder1 = {1, 2, 4, 5, 3, 6, 7};
        int[] inOrder1 = {4, 2, 5, 1, 6, 3, 7};
        int[] postOrder1 = {4, 5, 2, 6, 7, 3, 1};
        int[] preOrder2 = {1, 3, 5, 4, 2, 7, 6};
        int[] inOrder2 = {5, 3, 4, 1, 7, 2, 6};
        TreeNode tree1 = btt.reConstruction(preOrder1, inOrder1, null);
        TreeNode tree2 = btt.reConstruction(null, inOrder1, postOrder1);
        TreeNode tree3 = btt.reConstruction(preOrder2, inOrder2, null);
        assert tree1.equal(tree2);
        preOrderRecur(tree1);
        System.out.println();
        inOrderRecur(tree1);
        System.out.println();
        postOrderRecur(tree1);
        System.out.println();
        System.out.println(tree1.equal(tree3));
        System.out.println();

        preOrderRecur(tree1);System.out.println();
        preOrder1(tree1);System.out.println();
        preOrder2(tree1);System.out.println();
        System.out.println();
        inOrderRecur(tree1);System.out.println();
        inOrder1(tree1);System.out.println();
        inOrder2(tree1);System.out.println();
        System.out.println();
        postOrderRecur(tree1);System.out.println();
        postOrder1(tree1);System.out.println();
        postOrder2(tree1);System.out.println();
        System.out.println();


    }


}
