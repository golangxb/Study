import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone{
    private static  Lock lock = new ReentrantLock();
    public static void sendSMS(){
        lock.lock();
        try {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"发送短信");
        }finally {
            lock.unlock();
        }
    }
    public synchronized void  sendQQ(){
        try {
            System.out.println(Thread.currentThread().getName()+"发送QQ");
        }finally {
        }
    }
    public void openPhone(){


            System.out.println(Thread.currentThread().getName()+"手机开机");

    }



}

/**
 * 1 一般访问，请问先打印短信还是邮件？  SMS
 * 2 短信暂停4秒钟，请问先打印短信还是邮件？ SMS 因为有锁，锁的是对象 ，共用一个对象
 * 3 新增普通开机方法， 请问先打印短信还是开机？ 手机开机 因为手机开机是普通方法，和锁着的没有任何关系
 * 4 有两部手机，请问先打印短信还是邮件？ QQ 因为两部手机互不干涉
 * 5 静态同步方法，1部手机，请问先打印短信还是邮件？ SMS 因为静态锁的是整个Class类，不论是否为不同的对象，先进去，直接锁全局，爱谁谁
 * 6 静态同步方法，2部手机，请问先打印短信还是邮件？  SMS 因为静态锁的是整个Class类，不论是否为不同的对象，先进去，直接锁全局，爱谁谁
 * 7 一个普通同步方法，一个静态同步方法，1部手机，请问先打印短信还是邮件？ Email 因为各自锁各自的东西，互不干涉
 * 8 一个普通同步方法，一个静态同步方法，2部手机，请问先打印短信还是邮件？    Email 因为各自锁各自的东西，互不干涉
 */

public class Lock8 {

    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone1 = new Phone();
        new Thread(()->{phone.sendSMS();},"A").start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{phone1.sendQQ();},"B").start();


    }


}
