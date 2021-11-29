package com.gdw888.thread;

public class MyRunnable implements Runnable{

	private boolean isStopped = false;
	private Thread currentThread = null;
	private int counter = 0;
	
	@Override
	public void run() {
		currentThread = Thread.currentThread();
		while (!isStopped) {
			try {
				currentThread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			System.out.println(counter++);
		}
		
	}
	
	public synchronized void doStop() {
		isStopped = true;
		currentThread.interrupt();
	}
	
	private synchronized boolean isStopped() {
		return this.isStopped;
	}
	
}
