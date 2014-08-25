import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by Vera_Sidarovich on 7/14/2014.
 */
public class Test {
    public static void main(String... args) {

        Double value = new Double(1.0);
        Long value1 = Long.parseLong(value.toString());


       Integer rootNode = 1;

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        queue.add(5);
        queue.add(2);
        queue.add(7);
        queue.add(8);
        queue.add(9);
        queue.add(rootNode);
       System.out.println(queue.poll());

        Iterator<Integer> iterator = queue.iterator();

        while (iterator.hasNext()) {
            System.out.println(queue.poll());
        }

   /*     System.out.print(queue);

        queue.remove(rootNode);
        System.out.print(queue);*/
    }
}
