package com.gdw888.threadpool;

public class App {
	public static void main(String args[]) throws InterruptedException {
		MyThreadPool threadpool = new MyThreadPool(2,10);
		
		for (int i = 0 ; i < 100; i++) {
			final int j = i;
			threadpool.execute(()->{System.out.println("main:"+j);});
		}

		threadpool.waitUntilAllTaskFinished();
		System.out.println("STOPPING");
		threadpool.stop();
	}
}
