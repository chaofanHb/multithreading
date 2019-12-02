package cn.hebin.concurrent;

import java.util.concurrent.CompletableFuture;
/**
 * 消费型阶段
 * thenAccept没有返回值也不会主动抛出异常
 * 因为没有返回值， 所以不会影响主线程
 * @author Administrator
 *
 */
public class CompletionStageExample2 {
	public static void main(String[] args) {
		thenAccept();
		//thenAcceptBoth();
		acceptEither();
	}

    public static void thenAccept(){
        CompletableFuture.supplyAsync(() -> {return "hello";}).thenAccept(s -> {System.out.println(s+" world");});
    }

    public static void thenAcceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> System.out.println(s1 + " " + s2));

        while (true){} //等待打印出结果
    }

    public static void acceptEither() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello john";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello tom";
        }), System.out::println);

        while (true){} //等待打印出结果
    }
}
