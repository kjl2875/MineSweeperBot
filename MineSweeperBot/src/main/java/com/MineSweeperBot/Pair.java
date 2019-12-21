package com.MineSweeperBot;

public class Pair<T, T2> {
	public T first;
	public T2 second;

	public Pair() {

	}

	public Pair(T first, T2 second) {
		this.first = first;
		this.second = second;
	}
	
	@Override
	public String toString()  {
		return String.format("(%s,%s)", first.toString(),second.toString());
	}
}
