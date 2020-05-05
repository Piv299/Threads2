import java.util.ArrayDeque;
import java.util.Queue;

public class DataQueue {
    private static Queue<Integer> queue;
    public static boolean working=true;
    public DataQueue(){
        queue = new ArrayDeque<>(200);
    }
    public synchronized void get() throws NullPointerException {
        if (Threads2.ready){working=false;notifyAll();}
        while (working && queue.size()>0 && !Threads2.ready) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }

        queue.remove();
        if (queue.size() <= 80 && !Threads2.ready) {
            working=true;
            notifyAll();
        }
    }

    public synchronized void add(int value) {
        if (Threads2.ready){working=false;notifyAll();}
        while (queue.size()>=100 || !working) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        queue.offer(value);
        if (queue.size() == 100) {
            working=false;
            notifyAll();
        }
    }

    public  int getSize(){
        return queue.size();
    }
}