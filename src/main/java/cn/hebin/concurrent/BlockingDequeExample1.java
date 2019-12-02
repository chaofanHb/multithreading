package cn.hebin.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
/**
 * 双端队列
 * @author Administrator
 *
 */
public class BlockingDequeExample1 {
	static List<String> list = null;
	static BlockingDeque<String> deque = new LinkedBlockingDeque<String>(10);
	static {
		list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		deque.addAll(list);
	} 
	
	
	
	public static void main(String[] args) {
		System.out.println(deque.offer("6"));
		deque.offerFirst("7");
		deque.offerLast("8");
		System.out.println(deque.poll());
		deque.stream().forEach((item) ->{
			System.out.println(item);
		});
	}
}
