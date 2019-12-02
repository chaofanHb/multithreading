package cn.hebin.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletionService可以将线程执行结果统一用队列收集，读取最后剩下未执行完线程才会阻塞主线程
 * @author Administrator
 *
 */
public class CompletionServiceExample1 {
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool);
		for (int j = 1; j <= 5; j++) {

	        final int index = j;
	        completionService.submit(new Callable<Integer>() {
	            @Override
	            public Integer call() throws Exception {
	                //第三个线程睡眠等待
	                if (index == 3) {
	                    java.lang.Thread.sleep(3000l);
	                }
	                return index;
	            }
	        });
	    }
	    threadPool.shutdown();

	    for (int i = 0; i < 5; i++) {
	        try {
	            System.out.println("线程:"+completionService.take().get()+" 任务执行结束:");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	    }
	}
}
