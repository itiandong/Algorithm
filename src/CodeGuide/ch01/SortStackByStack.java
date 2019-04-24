package CodeGuide.ch01;

import java.util.Stack;

// < 5 4 3 2 1]  \\ < 1 4 3 5 2]
public class SortStackByStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(5);
        stack.push(3);
        stack.push(4);
        stack.push(1);

        stack = new SortStackByStack().sortStack(stack);

        // assert 5 4 3 2 1
        while (!stack.isEmpty()) System.out.print(stack.pop() + " ");
    }



    Stack<Integer> sortStack(Stack<Integer> stack) {
        Stack<Integer> aux = new Stack<>();
        while (!stack.isEmpty()) {
            int e = stack.pop();
            if (aux.isEmpty() || e >= aux.peek()) {
                aux.push(e);
            } else {
                int a = aux.peek();
                while (e < a){
                    stack.push(aux.pop());
                    if(aux.isEmpty()) {
                        break;
                    }
                    a = aux.peek();
                }
                aux.push(e);
            }
        }

        return aux;


    }


}
