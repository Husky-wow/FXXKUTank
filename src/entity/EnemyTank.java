package entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import interfaces.Animating;
import interfaces.AutoMove;
import interfaces.Drawable;
import manager.EnemyTankManager;
import manager.MissileManager;
import view.util.MapUtil;

public class EnemyTank extends Tank implements Drawable, Animating, AutoMove {

	/**
	 * EnemyTnak动画图片的列数
	 */
	private final int ANIMATION_COL = 4;

	public EnemyTank(int x, int y, Image image, int HP) {
		this.x = x;
		this.y = y;
		this.image = image;
		this.imageX = 0;
		this.imageY = 0;
		this.HP = HP;
		animation();
		autoMove();
		autoFire();
	}

	@Override
	public void draw(Graphics g, JPanel jp) {
		g.drawImage(this.image, x << 5, y * MapUtil.Y_SCAL_RATIO, (x + 1) << 5, (y + 1) * MapUtil.Y_SCAL_RATIO,
				this.imageX << 5, this.imageY << 5, (this.imageX + 1) << 5, (this.imageY + 1) << 5, jp);
		/*
		 * 如果坦克所在地方有草, 放大草并覆盖
		 */
		if (MapUtil.MAP[y][x] == MapUtil.GRASS) {
			g.drawImage(MapUtil.grass, (x << 5) - 5, (y * MapUtil.Y_SCAL_RATIO) - 5, ((x + 1) << 5) + 5,
					((y + 1) * MapUtil.Y_SCAL_RATIO) + 5, 0, 0, 87, 83, jp);
		}
	}

	@Override
	public void animation() {
		EnemyTankManager.enemyAnimationExecutor.submit(new Runnable() {
			
			@Override
			public void run() {
				while (HP > 0) {
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
		});
	}

	@Override
	public void autoMove() {		
		EnemyTankManager.enemyAutoMoveExecutor.submit(new Runnable() {
			
			@Override
			public void run() {
				while (HP > 0) {
					try {
						Thread.sleep(1000);
						switch (imageY) {
							case MapUtil.ENEMY_DIR_UP:
								if (isKeepingMoving(x, y - 1)) {
									y--;
								} else {
									imageY = (int) (Math.random() * 4);
								}
								break;
							case MapUtil.ENEMY_DIR_DOWN:
								if (isKeepingMoving(x, y + 1)) {
									y++;
								} else {
									imageY = (int) (Math.random() * 4);
								}
								break;
							case MapUtil.ENEMY_DIR_LEFT:
								if (isKeepingMoving(x - 1, y)) {
									x--;
								} else {
									imageY = (int) (Math.random() * 4);
								}
								break;
							case MapUtil.ENEMY_DIR_RIGHT:
								if (isKeepingMoving(x + 1, y)) {
									x++;
								} else {
									imageY = (int) (Math.random() * 4);
								}
								break;
							default:
								break;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

	}

	@Override
	protected boolean isKeepingMoving(int x, int y) {
		if (MapUtil.MAP[y][x] == MapUtil.NOTHING || MapUtil.MAP[y][x] == MapUtil.GRASS
				|| MapUtil.MAP[y][x] == MapUtil.ICE) {

			return true;
		}

		return false;
	}
	
	private void autoFire() {
		
		EnemyTankManager.enemyAutoFireExecutor.submit(new Runnable() {
			
			@Override
			public void run() {
				while (HP > 0) {
					try {
						Thread.sleep(3000);
						MissileManager.missilesList.add(new Missile(x, y, imageY, false));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	
	
	

}
