package cn.hebin.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
/**
 * ForkJoinPool可以看做是一个并行线程池，即多个线程（线程数一般等于CPU核数），并行的处理一个大任务。
 * @author Administrator
 *
 */
public class ForkJoinPoolExamlple {
	public static void main(String[] args) {
		//ForkJoinPool.commonPool();
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		//执行任务
		Integer sum = forkJoinPool.invoke(new CountRecursiveTask(1, 100));
		System.out.println(Runtime.getRuntime().availableProcessors());
		System.out.println(sum);
	}
}

/**
 * 100求和
 * @author Administrator
 *
 */
class CountRecursiveTask extends RecursiveTask<Integer> {
	private static final long serialVersionUID = 1L;

	// 达到子任务直接计算的阈值
	private int Th = 15;

	private int start;
	private int end;

	public CountRecursiveTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		if (this.end - this.start < Th) {
			// 如果小于阈值，直接调用最小任务的计算方法
			return count();
		} else {
			// fork 2 tasks:Th = 15
			// 如果仍大于阈值，则继续拆分为2个子任务，分别调用fork方法。
			// 这里可以根据情况拆成n个子任务
			int middle = (end + start) / 2;
			CountRecursiveTask left = new CountRecursiveTask(start, middle);
			System.out.println("start:" + start + ";middle:" + middle + ";end:" + end);
			//在当前任务正在运行的池中异步执行此任务
			left.fork();
			CountRecursiveTask right = new CountRecursiveTask(middle + 1, end);
			right.fork();
			// 最后一定要记得fork()结果(如果需要结果的话)
			//返回计算结果。
			return left.join() + right.join();
		}
	}

	private int count() {
		int sum = 0;
		for (int i = start; i <= end; i++) {
			sum += i;
		}
		return sum;
	}
}
