package com.gdw888.threadpool;

import java.util.concurrent.BlockingQueue;

public class MyRunnable implements Runnable{
	private BlockingQueue<Runnable> taskQueue = null;
	private volatile boolean		stopSinal = false;
	private Thread 					currentThread = null;

	public MyRunnable(BlockingQueue<Runnable> taskQueue) {
		this.taskQueue = taskQueue;
		this.stopSinal = false;
	}
	
	@Override
	public void run() {
		currentThread = Thread.currentThread(); 
		while(!isStopped()) {
			try {
				Runnable runnable = taskQueue.take();
				runnable.run();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}
	
	public synchronized void doStop() {
		this.stopSinal = true;
		currentThread.interrupt();
	}
	
	private synchronized boolean isStopped() {
		return stopSinal;
	}
}
