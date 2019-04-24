package CodeGuide.ch01;

import java.util.Stack;

/**
 * 只使用栈操作和递归逆转一个栈
 */


public class ReverseAStack {


    Integer getAndRemoveLastElement(Stack<Integer> stack) {
        Integer e = stack.pop();
        if (stack.isEmpty())
            return e;
        Integer last = getAndRemoveLastElement(stack);
        stack.push(e);
        return last;
    }

    // < 1, 2, 3, 4 ]
    // < 4, 3, 2, 1 ] push 1, 2, 3, 4
    void reverse(Stack<Integer> stack) {
        if(stack.isEmpty()) return;

        Integer e = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(e);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);

        new ReverseAStack().reverse(stack);

        // assert 4 3 2 1
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}

