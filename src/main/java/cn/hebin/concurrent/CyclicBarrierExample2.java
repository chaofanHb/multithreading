package cn.hebin.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 循环栅栏
 * @author Administrator
 *
 */
public class CyclicBarrierExample2 {
	 private static CyclicBarrier barrier = new CyclicBarrier(5);

	    public static void main(String[] args) throws Exception {

	        ExecutorService executor = Executors.newCachedThreadPool();

	        for (int i = 0; i < 10; i++) {
	            final int threadNum = i;
	            Thread.sleep(1000);
	            executor.execute(() -> {
	                try {
	                    race(threadNum);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            });
	        }
	        executor.shutdown();
	    }

	    private static void race(int threadNum) throws Exception {
	        Thread.sleep(1000);
	        System.out.println(threadNum + " is ready");
	        try {
	            barrier.await(2000, TimeUnit.MILLISECONDS);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println(threadNum + " continue");
	    }
}
