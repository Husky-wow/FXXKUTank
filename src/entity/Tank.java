package entity;

import interfaces.Element;

public abstract class Tank extends Element {
	
	/**
	 * 坦克的生命值
	 */
	public int HP;
	
	protected abstract boolean isKeepingMoving(int x, int y);
}
