package interfaces;

import java.awt.Image;

public abstract class Element {
	/**
	 * 角色 x 轴坐标
	 */
	public int x;
	
	/**
	 * 角色 y 轴坐标
	 */
	public int y;
	
	/**
	 * 角色图片 x 轴坐标
	 */
	protected int imageX;
	
	/**
	 * 角色图片 y 轴坐标
	 */
	protected int imageY;
	
	protected Image image;
}
