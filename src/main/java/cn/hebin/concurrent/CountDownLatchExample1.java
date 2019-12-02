package cn.hebin.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 倒计时器
 * @author Administrator
 *
 */
public class CountDownLatchExample1 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        //threadCount减到0时执行countDownLatch.await();后面的代码
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                	//threadCount--
                    countDownLatch.countDown();
                }
            });
        }
        
        //countDownLatch.await(10, TimeUnit.MILLISECONDS); 超过指定时间跳过
        countDownLatch.await();
        System.out.println("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        Thread.sleep(100);
        System.out.println(threadNum);
        Thread.sleep(100);
    }
}
