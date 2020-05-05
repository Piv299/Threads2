import java.io.IOException;
import static java.lang.Thread.sleep;


public class Threads2 {
    // Потоки 2 Поляков Илья БАСО-02-18

    static boolean ready=false;
    public static void main(String[] args) {
        System.out.println("Program started!");
        DataQueue queue = new DataQueue();

        Thread prod = new Thread(new Producers(queue));
        Thread cons = new Thread(new Consumers(queue));

        prod.start();
        cons.start();

        while (true) {
            try {
                if (System.in.read() == 'q') {
                    ready=true;
                    while (queue.getSize()!=0){
                        try{sleep(300);}
                        catch (InterruptedException e){};
                    }
                    System.out.println("Finish");
                    System.exit(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
