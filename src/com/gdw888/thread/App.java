package com.gdw888.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class App {
	
	public static void main(String args[]) {
		MyRunnable myRunnable = new MyRunnable();
		Thread thread = new Thread(myRunnable);
		
		thread.start();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		myRunnable.doStop();
		
		System.out.println("Hello world");
		
		Lock myLock = new ReentrantLock();
		myLock.lock();
		
		try {
			myLock.tryLock(10, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(((ReentrantLock) myLock).isLocked());
		myLock.unlock();
		System.out.println(((ReentrantLock) myLock).isLocked());
		myLock.unlock();
		System.out.println(((ReentrantLock) myLock).isLocked());
		myLock.unlock();
		System.out.println(((ReentrantLock) myLock).isLocked());
		
		ReadWriteLock rwLock = new ReentrantReadWriteLock();
		
		Lock readLock = rwLock.readLock();
		Condition condition = readLock.newCondition();
		Condition condition2 = readLock.newCondition();
		
	}
	
}
