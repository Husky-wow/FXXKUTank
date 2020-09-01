package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import entity.Boom;
import entity.Boss;
import entity.EnemyTank;
import entity.Missile;
import entity.PlayerTank;
import manager.BoomManager;
import manager.EnemyTankManager;
import manager.MissileManager;
import view.util.MapUtil;

public class TankLev1Jpanel extends JPanel {

	private static final long serialVersionUID = 581795466706348387L;

	private Boss boss;

	private PlayerTank player = PlayerTank.getInstance();

	/**
	 * 游戏的状态: 0正常游戏 1玩家赢了 2玩家失败
	 */
	public static int gameState = MapUtil.GAME_NOMRAL;

	public TankLev1Jpanel() {
		// 设置面板的背景颜色
		setBackground(Color.BLACK);
		boss = new Boss(14, 23);

		// 不断进行绘制
		new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(16);
						repaint();

						if (gameState != MapUtil.GAME_NOMRAL) {// 游戏结束

							Thread.sleep(1000);// 进入到阻塞，不进行刷新，等待

							return;// 结束当前线程

						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();

		EnemyTankManager.creatEnemy();

	}

	@Override
	protected void paintComponent(Graphics g) {
		// 不能省略
		super.paintComponent(g);

		if (gameState == MapUtil.GAME_NOMRAL) {
			// 绘制地图
			MapUtil.drawMap(g, this);
			// 绘制
			boss.draw(g, this);
			paintMissles(g);
			if (player.HP > 0) {
				player.draw(g, this);
			}
			paintEnemy(g);
			paintBooms(g);
		} else if (gameState == MapUtil.GAME_WIN) {
			// 游戏胜利
			g.drawImage(MapUtil.win, 0, 0, 30 << 5, 26 << 5, 0, 0, 800, 600, this);
		} else if (gameState == MapUtil.GAME_LOSE) {
			g.drawImage(MapUtil.loser, 0, 0, 30 << 5, 26 * MapUtil.Y_SCAL_RATIO, 0, 0, 248, 160, this);
		}

	}

	/**
	 * 遍历绘制地方坦克
	 * @param g
	 */
	private void paintEnemy(Graphics g) {
		if (!EnemyTankManager.enemyList.isEmpty()) {
			for (EnemyTank enemy : EnemyTankManager.enemyList) {
				if (enemy.HP > 0) {
					enemy.draw(g, this);
				}
			}
		}
	}

	/**
	 * 遍历绘制地方坦克
	 * @param g
	 */
	private void paintMissles(Graphics g) {
		if (!MissileManager.missilesList.isEmpty()) {
			for (Missile m : MissileManager.missilesList) {
				if (m.isExist) {
					m.draw(g, this);
				}
			}
		}
	}

	private void paintBooms(Graphics g) {
		if (!BoomManager.boomsList.isEmpty()) {
			for (Boom boom : BoomManager.boomsList) {
				if (boom.isExist) {
					boom.draw(g, this);
				}
			}
		}
	}

}
