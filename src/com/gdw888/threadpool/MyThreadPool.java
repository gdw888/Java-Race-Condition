package com.gdw888.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyThreadPool {
	
	private BlockingQueue<Runnable> taskQueue = null;
	private List<MyRunnable> runnables = new ArrayList<>();
	private volatile boolean isStopped = false;
	
	public MyThreadPool(int numThread, int numQueue) {
		
		taskQueue = new ArrayBlockingQueue<>(numQueue);
		
		for (int i = 0; i < numThread; i++) {
			runnables.add(new MyRunnable(taskQueue));
		}
		
		for (MyRunnable runnable : runnables) {
			new Thread(runnable).start();
		}
	}
	
	public synchronized void execute(Runnable task) throws InterruptedException {
		if(isStopped)
			new IllegalStateException("ThreadPool is stopped");
		//System.out.println("Count:"+count++);
		this.taskQueue.put(task);
	}
	
	public synchronized void stop() {
		isStopped = true;

		for (MyRunnable runnable : runnables) {
			runnable.doStop();
		}
	}
	
	public void waitUntilAllTaskFinished() {
		while(taskQueue.size() > 0) {
			System.out.println("Queue size:"+taskQueue.size());
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
