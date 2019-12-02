package cn.hebin.concurrent;

import cn.hebin.entry.Data;

public class ThreadTestRunable implements Runnable {
	Data data;

	public ThreadTestRunable(Data data) {
		this.data = data;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			System.out.println("线程 执行:");
			data.setName("新名字");
			data.setSex("新性别");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
