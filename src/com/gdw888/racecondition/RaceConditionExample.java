package com.gdw888.racecondition;

public class RaceConditionExample {

	public static void main(String args[]) throws InterruptedException {
		Counter counter = new Counter();
		
		Thread thread1 = new Thread(getRunnable(counter, "Thread1:"));
		Thread thread2 = new Thread(getRunnable(counter, "Thread2:"));
		
		thread1.start();
		thread2.start();
		
		
		thread1.join();
		thread2.join();
	}
	
	public static Runnable getRunnable(Counter counter, String msg) {
		return ()->{
			
			for (int i = 0; i < 1_000_000; i++) {
				counter.incAndGet();
			}
			
			System.out.println(msg + ":" + counter.get());
		};
	}
}
