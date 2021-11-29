package com.gdw888.racecondition;

public class Counter {
	private volatile long counter;
	
	public Counter() {
		this.counter = 0;
	}
	
	public synchronized long incAndGet() {
		this.counter++;
		return this.counter;
	}
	
	public long get() {
		return this.counter;
	}
}
