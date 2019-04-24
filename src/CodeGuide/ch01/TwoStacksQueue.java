package CodeGuide.ch01;

import java.util.Stack;

public class TwoStacksQueue {
    Stack<Integer> stackPush;
    Stack<Integer> stackPop;

    TwoStacksQueue() {
        stackPop = new Stack<>();
        stackPush = new Stack<>();
    }

    void enqueue( int e) {
        stackPush.push(e);
    }


    int dequeue() {
        move();
        return stackPop.pop();

    }

    int peek() {
        move();
        return stackPop.peek();
    }

    void move() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        } else if (stackPop.isEmpty()) {
            while(!stackPush.isEmpty()) stackPop.push(stackPush.pop());
        }
    }

    boolean isEmpty() {
        return stackPush.isEmpty() && stackPop.isEmpty();
    }

    public static void main(String[] args) {
        TwoStacksQueue q = new TwoStacksQueue();
        q.enqueue(1);
        q.enqueue(3);
        q.enqueue(5);
        q.enqueue(7);
        q.dequeue();
        q.dequeue();
        while (!q.isEmpty()) {
            System.out.print(q.dequeue() + " ");
        }
    }

}
