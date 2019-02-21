import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * Create with IntelliJ IDEA。
 * User : Lyhang
 * Data : 2019-02-21
 * Time : 11:41
 **/

/**
 * 读写锁
 */
class ReadWriteDemo {

    private Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();

    public void write(String key, Object value) {
        rrwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在写。。");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写完成。。");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rrwl.writeLock().unlock();
        }

    }

    public Object read(String key) {
        rrwl.readLock().lock();
        Object o = null;
        try {
            System.out.println(Thread.currentThread().getName() + "正在读。。");
            o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读完成。。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rrwl.readLock().unlock();
        }

       return o;
    }


}

public class ReadWriteClock {

    public static void main(String[] args) {

       /* ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(1,5,1L,
                TimeUnit.SECONDS,);*/
        ReadWriteDemo red = new ReadWriteDemo();
        for (int i = 0; i < 5; i++) {
            int num = i;
            new Thread(() -> {
                red.write(num + "", num);

            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            int num = i;
            new Thread(() -> {
                red.read(num + "");

            }, String.valueOf(i)).start();
        }


    }


}
