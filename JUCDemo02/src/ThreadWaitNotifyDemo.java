import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Share{
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int number = 0;
    public  void inc() throws Exception {
        lock.lock();

        try{
            while(number!=0){

                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();

        }finally {
            lock.unlock();
        }


    }
    public  void dec()throws Exception{
        lock.lock();

        try{
            while(number==0){

                condition.await();

            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();


        }finally {
            lock.unlock();
        }


    }


}
public class ThreadWaitNotifyDemo {

    public static void main(String[] args) {

        Share share = new Share();
        new Thread(()->{

            for (int i = 0; i < 10; i++) {
                try {
                    share.inc();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        },"A").start();

        new Thread(()->{

            try {
                for (int i = 0; i < 10; i++) {
                    try {
                        share.dec();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"B").start();new Thread(()->{

            for (int i = 0; i < 10; i++) {
                try {
                    share.inc();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        },"D").start();

        new Thread(()->{

            try {
                for (int i = 0; i < 10; i++) {
                    try {
                        share.dec();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"C").start();



    }


}
