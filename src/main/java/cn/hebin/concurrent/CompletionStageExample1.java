package cn.hebin.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
/**
 * 产出型阶段
 * supplyAsync表示异步完成函数
 * @author Administrator
 *
 */
//阶段完成例子
public class CompletionStageExample1 {
	public static void main(String[] args) throws Exception {
		thenApply();
		thenCombine();
		applyToEither();
		System.out.println("===============");
	}
	
    public static void thenApply() throws InterruptedException, ExecutionException {
        CompletableFuture<String> stage = CompletableFuture.supplyAsync(() -> "hello");

        String result = stage.thenApply(s -> s + " world").join();
        System.out.println(result);
    }

    /*thenCombine会在supplyAsync()执行完和thenCombine第一个函数参数执行完后，
    	执行第二个函数参数,并将前两个的执行结果作为参数传入到第二个函数参数中*/
    public static void thenCombine() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("第一个执行完");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("第二个执行完");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result);
    }

    //applyToEither会在supplyAsync()和applyToEither第一个函数参数中谁先执行完  就获取哪个的结果并作为参数传入到第二个函数参数中
    public static void applyToEither() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Tom";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "John";
        }), s -> "hello " + s).join();
        System.out.println(result);
    }
}
