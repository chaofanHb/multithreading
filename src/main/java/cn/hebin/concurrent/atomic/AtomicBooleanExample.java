package cn.hebin.concurrent.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample {
    public static void main(String[] args) {
        AtomaticTest atomatic1 = new AtomaticTest("bar1");
        AtomaticTest atomatic2 = new AtomaticTest("bar2");
        new Thread(atomatic1).start();
        new Thread(atomatic2).start();
    }
}
class AtomaticTest implements Runnable {
	 
    private String name;
 
    private static AtomicBoolean exists = new AtomicBoolean(false);
 
    public AtomaticTest(String name) {
        this.name = name;
    }
 
    @Override
    public void run() {
        if(exists.compareAndSet(false, true)) {
            System.out.println(name + ":enter");
            try {
            	//对Thread.sleep方法的包装
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ":leave");
            exists.set(false);
        }else{
            System.out.println(name +":give up");
        }
    }
}