package CodeGuide.ch01;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CatAndDogQueue {

    static long count = 0;

    private Queue<PetEnterQueue> dog = new LinkedList<>();
    private Queue<PetEnterQueue> cat = new LinkedList<>();

    void add(Pet e) {
        if (e instanceof Dog) {
            dog.add(new PetEnterQueue(e, count++));
        } else {
            cat.add(new PetEnterQueue(e, count++));
        }
    }

    void pollALl() {
        while (!dog.isEmpty() && !cat.isEmpty()) {
            if (dog.peek().getCount() < cat.peek().getCount()) dog.poll().print();
            else cat.poll().print();
        }

        if(!dog.isEmpty()) pollDog();
        else if(!cat.isEmpty()) pollCat();
        else System.out.println();


    }

    void pollDog() {
        while (!dog.isEmpty()) {
            dog.poll().print();
        }
        System.out.println();
    }

    void pollCat() {
        while (!cat.isEmpty()) {
            cat.poll().print();
        }
        System.out.println();
    }

    boolean isEmpty() {
        return dog.isEmpty() && cat.isEmpty();
    }

    boolean isDogEmpty() {
        return dog.isEmpty();
    }

    boolean isCatEmpty() {
        return cat.isEmpty();
    }

    public static void main(String[] args) {

        Random rand = new Random();
        CatAndDogQueue queue = new CatAndDogQueue();

        int n;

        n = 10;
        System.out.println("Origin queue: ");
        while (n-- > 0) {
            if (rand.nextBoolean()) {
                queue.add(new Cat());
                System.out.print("cat ");
            } else {
                queue.add(new Dog());
                System.out.print("dog ");
            }
        }
        System.out.println();
        queue.pollALl();


        n = 10;
        System.out.println("Origin queue: ");
        while (n-- > 0) {
            if (rand.nextBoolean()) {
                queue.add(new Cat());
                System.out.print("cat ");
            } else {
                queue.add(new Dog());
                System.out.print("dog ");
            }
        }
        System.out.println();
        queue.pollCat();
        queue.pollDog();





    }

}

class PetEnterQueue {
    private Pet pet;
    private long count;

    public PetEnterQueue(Pet aPet, long cnt) {
        pet = aPet;
        count = cnt;
    }

    public Pet getPet() {
        return pet;
    }

    public long getCount() {
        return count;
    }

    public String getType() {
        return pet.getType();
    }

    public void print() {
        //System.out.print(getType() + "-" + getPet() + ":" + getCount() + " ");
        System.out.print(getType() + ":" + getCount() + " ");
    }
}


class Pet {
    private String type;
    public Pet(String t) {
        type = t;
    }
    public String getType() {
        return type;
    }
}

class Dog extends Pet{
    public Dog(){
        super("dog");
    }
}

class Cat extends Pet {
    public Cat(){
        super("cat");
    }
}