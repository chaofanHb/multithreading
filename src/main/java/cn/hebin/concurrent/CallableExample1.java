package cn.hebin.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import cn.hebin.entry.Data;

/**
 * 使用submit方法还有一个特点就是，他的异常可以在主线程中catch到。而使用execute方法执行任务是捕捉不到异常的(没有详细异常提示)。
 * @author Administrator
 *
 */
public class CallableExample1 {
	static ExecutorService executor = Executors.newFixedThreadPool(2);
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("提交任务之前 " + getStringDate());
		Future<String> future = executor.submit(()->{
			Thread.sleep(3000);
			System.out.println("calld方法执行了");
			return "call方法返回值";
		});
		//Thread.sleep(3000);
		System.out.println("提交任务之后，获取结果之前 " + getStringDate());
		//运行到future.get()的时候就阻塞住了，一直等到任务执行完毕，拿到了返回的返回值，主线程才会继续运行。
		System.out.println("获取返回值: " + future.get());
		System.out.println("获取到结果之后 " + getStringDate());
		
		runable();
	}

	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	
	public static void runable() throws Exception {
		Data data = new Data();
		Future<Data> future = executor.submit(new ThreadTestRunable(data), data);
		System.out.println("返回的结果 name: " + future.get(1, TimeUnit.SECONDS).getName()+", sex: "+future.get(1, TimeUnit.SECONDS).getSex());
		System.out.println("原来的Data name: " + data.getName()+", sex: "+data.getSex());
	}
}
