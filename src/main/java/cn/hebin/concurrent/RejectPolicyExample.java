package cn.hebin.concurrent;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程池策略，系统提供有四种策略
	AbortPolicy   如果线程池队列满了丢掉这个任务并且抛出RejectedExecutionException异常。
	DiscardPolicy  如果线程池队列满了，会直接丢掉这个任务并且不会有任何异常。
	DiscardOldestPolicy  丢弃最老的。也就是说如果队列满了，会将最早进入队列的任务删掉腾出空间，再尝试加入队列。
	CallerRunsPolicy  如果添加到线程池失败，那么主线程会自己去执行该任务，不会等待线程池中的线程去执行。
 * @author Administrator
 *
 */
public class RejectPolicyExample implements RejectedExecutionHandler{

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		//Sender是我的Runnable类，里面有message字段
        System.out.println(executor.getPoolSize());
		
	}

}
