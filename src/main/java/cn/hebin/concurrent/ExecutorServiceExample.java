package cn.hebin.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {
	/*
	 1.工作线程的创建数量几乎没有限制(其实也有限制的,数目为Interger. MAX_VALUE), 这样可灵活的往线程池中添加线程。
	 2.如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程。
	 3.在使用CachedThreadPool时，一定要注意控制任务的数量，否则，由于大量线程同时运行，很有会造成系统瘫痪*/
	ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	
	/*FixedThreadPool是一个典型且优秀的线程池，它具有线程池提高程序效率和节省创建线程时所耗的开销的优点。
	 * 但是，在线程池空闲时，即线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源。*/
	ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
	
	/*单线程化线程池(newSingleThreadExecutor)的优点，串行执行所有任务。
	如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。*/
	ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
	
	/*创建一个定长的线程池，而且支持定时的以及周期性的任务执行，支持定时及周期性任务执行。*/
	static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
	
	static {
		//表示延迟1秒后每3秒执行一次
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			   public void run() {
			    System.out.println("delay 1 seconds, and excute every 3 seconds");
			   }
			  }, 1, 3, TimeUnit.SECONDS);
	}
}
