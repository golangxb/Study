import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch向下计数，计数到0 执行main方法
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatchDemo = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "过完了");
                downLatchDemo.countDown();
            }, Week.foreachWeek(i).getWeeks()).start();

        }
        downLatchDemo.await();
        System.out.println(Thread.currentThread().getName() + "放假了");


    }


}
