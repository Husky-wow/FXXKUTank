package entity;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import interfaces.Animating;
import interfaces.Drawable;
import manager.MissileManager;
import view.util.MapUtil;

/**
 * 玩家
 * 	其方向由同imageY的值
 * @author xuyifan
 *
 */
public class PlayerTank extends Tank implements Drawable, Animating, KeyListener {

	/**
	 * 单例 - 饿汉
	 */
	public static PlayerTank instance = new PlayerTank();

	/**
	 * Player动画图片的列数
	 */
	private final int ANIMATION_COL = 3;

	private PlayerTank() {
		this.x = 11;
		this.y = 24;
		this.imageX = 0;
		this.imageY = 0;
		this.image = MapUtil.player;
		this.HP = 3;
		animation();
	}

	public static PlayerTank getInstance() {
		return instance;
	}

	@Override
	public void draw(Graphics g, JPanel jp) {
		g.drawImage(this.image,
				x << 5, y * MapUtil.Y_SCAL_RATIO, (x + 1) << 5, (y + 1) * MapUtil.Y_SCAL_RATIO,
				this.imageX << 5, this.imageY << 5,
				(this.imageX + 1) << 5, (this.imageY + 1) << 5,
				jp);
		/*
		 * 如果坦克所在地方有草, 放大草并覆盖
		 */
		if (MapUtil.MAP[y][x] == MapUtil.GRASS) {
			g.drawImage(MapUtil.grass,
					(x << 5) - 5, (y * MapUtil.Y_SCAL_RATIO) - 5,
					((x + 1) << 5) + 5, ((y + 1) * MapUtil.Y_SCAL_RATIO) + 5,
					0, 0, 87, 83,
					jp);
		}
	}

	@Override
	public void animation() {
		// 绘制动画的原理就是图片不断变化
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (MapUtil.isDraw) {
					try {
						Thread.sleep(100);
						imageX++;
						if (imageX == ANIMATION_COL) {
							imageX = 0;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "Player1_animation_THread").start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (imageY != MapUtil.PLAY_DIR_UP) {
				imageY = MapUtil.PLAY_DIR_UP;
				// 这里return的意思是，先换方向，在移动
				return;
			}
			if (isKeepingMoving(x, y - 1)) {
				y--;
			}
			break;
		case KeyEvent.VK_DOWN:
			if (imageY != MapUtil.PLAY_DIR_DOWN) {
				imageY = MapUtil.PLAY_DIR_DOWN;
				return;
			}
			if (isKeepingMoving(x, y + 1)) {
				y++;
			}
			break;
		case KeyEvent.VK_LEFT:
			if (imageY != MapUtil.PLAY_DIR_LEFT) {
				imageY = MapUtil.PLAY_DIR_LEFT;
				return;
			}
			if (isKeepingMoving(x - 1, y)) {
				x--;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (imageY != MapUtil.PLAY_DIR_RIGHT) {
				imageY = MapUtil.PLAY_DIR_RIGHT;
				return;
			}
			if (isKeepingMoving(x + 1, y)) {
				x++;
			}
			break;
		/*case KeyEvent.VK_SPACE:
			//System.out.println("发射");
			MissileManager.missilesList.add(new Missile(x, y, imageY, true));
			break;*/
		default:
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			MissileManager.missilesList.add(new Missile(x, y, imageY, true));
		}
	}

	/**
	 * 判断能不能移动到x和y位置标示的点
	 * @param x x轴坐标, 二维坐标第二维
	 * @param y y轴坐标, 二维坐标第一维
	 * @return 返回结果
	 */
	@Override
	protected boolean isKeepingMoving(int x, int y) {
		if (MapUtil.MAP[y][x] == MapUtil.NOTHING
				|| MapUtil.MAP[y][x] == MapUtil.GRASS
				|| MapUtil.MAP[y][x] == MapUtil.ICE) {
			
			return true;
		}
		
		return false;
	}

}
