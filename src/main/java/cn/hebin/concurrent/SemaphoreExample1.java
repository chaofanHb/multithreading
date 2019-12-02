package cn.hebin.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量（限制流量）
 * 在应用场景上，限制线程并发数是为了性能考虑，而加锁是为了安全而考虑。
 * @author Administrator
 *
 */
public class SemaphoreExample1 {
	private final static int threadCount = 20;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();
       // 每次最多三个线程获取许可
        final Semaphore semaphore = new Semaphore(1);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    semaphore.acquire(); // 获取一个许可
                    test(threadNum);
                    semaphore.release(); // 释放一个许可
                    
                    /*
                    semaphore.acquire(3); // 获取多个许可
                    test(threadNum);
                    semaphore.release(3); // 释放多个许可
                     */
                    
                    /*if (semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) { // 尝试获取一个许可
                        test(threadNum);
                        semaphore.release(); // 释放一个许可
                    }*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        System.out.println(threadNum);
        Thread.sleep(100);
    }
}
