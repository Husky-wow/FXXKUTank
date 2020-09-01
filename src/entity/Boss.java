package entity;

import java.awt.Graphics;

import javax.swing.JPanel;

import interfaces.Animating;
import interfaces.Drawable;
import interfaces.Element;
import manager.GameManager;
import view.util.MapUtil;

public class Boss extends Element implements Drawable, Animating {

	public Boss(int x, int y) {
		super();
		this.y = y;
		this.x = x;
		animation();
	}

	/**
	 * 绘制boss
	 * 
	 * @param g
	 * @param jp
	 */
	@Override
	public void draw(Graphics g, JPanel jp) {
		g.drawImage(MapUtil.boss,
				this.x << 5, this.y * MapUtil.Y_SCAL_RATIO,
				(this.x + 2) << 5, (this.y + 2) * MapUtil.Y_SCAL_RATIO,
				0, imageY * 34,
				41, (imageY + 1) * 34,
				jp);
	}

	/**
	 * 动画的效果
	 */
	@Override
	public void animation() {
		GameManager.getInstance().bossAnimationExectutor.submit(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
						imageY++;
						if (imageY == 12) {
							imageY = 0;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}
