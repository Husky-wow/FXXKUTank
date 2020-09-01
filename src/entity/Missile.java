package entity;

import java.awt.Graphics;

import javax.swing.JPanel;

import interfaces.AutoMove;
import interfaces.Drawable;
import interfaces.Element;
import manager.BoomManager;
import manager.GameManager;
import manager.EnemyTankManager;
import manager.MissileManager;
import view.TankLev1Jpanel;
import view.util.MapUtil;

public class Missile extends Element implements Drawable, AutoMove {

	/**
	 * 坦克的移动方向
	 */
	private int dir;

	/**
	 * 子弹的发射者是不是玩家
	 */
	private boolean isPlayer;

	/**
	 * 子弹是否存在
	 */
	public boolean isExist = true;

	public Missile(int x, int y, int dir, boolean isPlayer) {
		this.x = x;
		this.y = y;
		this.imageX = 0;
		this.imageY = 0;
		this.image = MapUtil.missile;
		this.dir = dir;
		this.isPlayer = isPlayer;
		// 设置子弹方向
		setDir(isPlayer);
		autoMove();
	}

	@Override
	public void draw(Graphics g, JPanel jp) {
		// System.out.println("子弹绘制");
		g.drawImage(this.image, x << 5, y * MapUtil.Y_SCAL_RATIO, (x + 1) << 5, (y + 1) * MapUtil.Y_SCAL_RATIO,
				this.imageX << 5, this.imageY << 5, (this.imageX + 1) << 5, (this.imageY + 1) << 5, jp);
	}

	@Override
	public void autoMove() {
		MissileManager.misslesMoveExecutor.submit(new Runnable() {
			
			@Override
			public void run() {
				while (isExist) {
					try {
						Thread.sleep(150);
						moveByDir();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	/**
	 * 设置子弹方向
	 */
	private void setDir(boolean isPlayer) {
		if (isPlayer) {
			switch (dir) {
				case MapUtil.PLAY_DIR_UP:
					imageX = MapUtil.MISSILE_DIR_UP;
					dir = MapUtil.MISSILE_DIR_UP;
					break;
				case MapUtil.PLAY_DIR_DOWN:
					imageX = MapUtil.MISSILE_DIR_DOWN;
					dir = MapUtil.MISSILE_DIR_DOWN;
					break;
				case MapUtil.PLAY_DIR_LEFT:
					imageX = MapUtil.MISSILE_DIR_LEFT;
					dir = MapUtil.MISSILE_DIR_LEFT;
					break;
				case MapUtil.PLAY_DIR_RIGHT:
					imageX = MapUtil.MISSILE_DIR_RIGHT;
					dir = MapUtil.MISSILE_DIR_RIGHT;
					break;
				default:
					break;
			}
		} else {
			switch (dir) {
				case MapUtil.ENEMY_DIR_UP:
					imageX = MapUtil.MISSILE_DIR_UP;
					dir = MapUtil.MISSILE_DIR_UP;
					break;
				case MapUtil.ENEMY_DIR_DOWN:
					imageX = MapUtil.MISSILE_DIR_DOWN;
					dir = MapUtil.MISSILE_DIR_DOWN;
					break;
				case MapUtil.ENEMY_DIR_LEFT:
					imageX = MapUtil.MISSILE_DIR_LEFT;
					dir = MapUtil.MISSILE_DIR_LEFT;
					break;
				case MapUtil.ENEMY_DIR_RIGHT:
					imageX = MapUtil.MISSILE_DIR_RIGHT;
					dir = MapUtil.MISSILE_DIR_RIGHT;
					break;
				default:
					break;
			}
		}
	}

	/**
	 * 设置子弹方向
	 */
	private void moveByDir() {
		switch (dir) {
			case MapUtil.MISSILE_DIR_UP:
				isKeepExist(x, y - 1);
				y--;
				break;
			case MapUtil.MISSILE_DIR_DOWN:
				isKeepExist(x, y + 1);
				y++;
				break;
			case MapUtil.MISSILE_DIR_LEFT:
				isKeepExist(x - 1, y);
				x--;
				break;
			case MapUtil.MISSILE_DIR_RIGHT:
				isKeepExist(x + 1, y);
				x++;
				break;
			default:
				break;
		}
	}

	/**
	 * 判断子弹在该位置能否继续存活
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private void isKeepExist(int x, int y) {
		if (MapUtil.MAP[y][x] == MapUtil.BORDER || MapUtil.MAP[y][x] == MapUtil.WALL
				|| MapUtil.MAP[y][x] == MapUtil.STEELS || MapUtil.MAP[y][x] == MapUtil.SOIL) {
			isExist = false;
			BoomManager.boomsList.add(new Boom(x, y));
			return;
		}
		
		if (isHitElement(x, y)) {
			isExist = false;
			return;
		}
	}
	
	/**
	 * 子弹在当前位置是否能够集中坦克
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isHitElement(int x, int y) {
		if (isPlayer) {
			/*
			 * 击中敌方
			 */
			for (EnemyTank enemy : EnemyTankManager.enemyList) {
				if (enemy.HP > 0 && enemy.x == x && enemy.y == y) {
					if (enemy.HP > 1) {
						enemy.HP--;
					} else if (enemy.HP == 1) {
						BoomManager.boomsList.add(new Boom(x, y));
						enemy.HP = 0;
						GameManager.getInstance().enemyDeadCount++;
					}
					return true;
				}
			}
			/*
			 *  子弹相碰
			 */
			for (Missile missile : MissileManager.missilesList) {
				if (missile.isExist && missile.isPlayer == false && missile.x == x && missile.y == y) {
					BoomManager.boomsList.add(new Boom(x, y));
					missile.isExist = false;
					this.isExist = false;
					return true;
				}
			}
			
		} else {
			/*
			 *  敌方子弹击中玩家
			 */
			if (this.x ==PlayerTank.getInstance().x  && this.y ==PlayerTank.getInstance().y) {
				PlayerTank.getInstance().HP--;
				if (PlayerTank.getInstance().HP <= 0) {
					TankLev1Jpanel.gameState=MapUtil.GAME_LOSE;
					return true;
				}
			}
		}
		
		/**
		 * 击中Boss,允许自杀
		 */
		if(this.x >= 14 && this.x <=15  && this.y >= 23 && this.y <= 24 ){
			//一切结束，游戏失败
			TankLev1Jpanel.gameState=MapUtil.GAME_LOSE;
			GameManager.getInstance().endGame();
			return true;
		}
		
		return false;
	}

}
