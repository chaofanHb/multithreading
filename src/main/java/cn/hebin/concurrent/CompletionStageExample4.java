package cn.hebin.concurrent;

import java.util.concurrent.CompletableFuture;

/**
 * 
 * @author Administrator
 *
 */
public class CompletionStageExample4 {
	
	public static void main(String[] args) {
		handle();
	}

	//类似thenCombine
    public static void thenCompose(){
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCompose(s -> CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s + " world";
        })).join();

        System.out.println(result);
    }
    
    //不论阶段正常还是异常完成的消耗型：
    public static void thenCombine(){
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            
            return "hello ";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("return world...");  //会执行
            return "world";
        }), (s1, s2) -> {
            String s = s1 + " " + s2;   //并不会执行
            System.out.println("combine result :"+s); //并不会执行
            return s;
        }).whenComplete((s, t) -> {
            System.out.println("current result is :" +s);
            if(t != null){
                System.out.println("阶段执行过程中存在异常：");
                t.printStackTrace();
            }
        }).join();
        
        System.out.println("final result:"+result); //并不会执行
    }
    //不论阶段正常还是异常完成的产出型
    public static void handle() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //出现异常
            if (1 == 3) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "Tom";
        }).handle((s, t) -> {
            if (t != null) { //出现异常了
                return "John";
            }
            return s; //这里也可以对正常结果进行转换
        }).join();
        System.out.println(result);
    }
    
    public static void exceptionally() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).exceptionally(e -> {
            e.printStackTrace(); //e肯定不会null
            return "hello world"; //补偿返回
        }).join();
        System.out.println(result); //打印hello world
    }
}
