package entity;

import java.awt.Graphics;

import javax.swing.JPanel;

import interfaces.Animating;
import interfaces.Drawable;
import interfaces.Element;
import manager.BoomManager;
import view.util.MapUtil;

public class Boom extends Element implements Drawable, Animating {

	public boolean isExist = true;

	//private Boom boom = this;
	
	public Boom(int x, int y) {
		this.x = x;
		this.y = y;
		this.image = MapUtil.boom;
		this.imageX = 0;
		this.imageY = 0;
		animation();
	}

	@Override
	public void animation() {
		BoomManager.boomAnimatonExecutor.submit(new Runnable() {
			
			@Override
			public void run() {
				/*String name = Thread.currentThread().getName();
				System.out.println(name + "执行" + this);*/
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(300);
						imageX++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 砖墙变土墙
					if (i == 5) {
						if (MapUtil.MAP[y][x] == MapUtil.WALL) {
							MapUtil.MAP[y][x] = MapUtil.SOIL;
						} else if (MapUtil.MAP[y][x] == MapUtil.SOIL) {
							MapUtil.MAP[y][x] = MapUtil.NOTHING;
						}
					}
				}
				isExist = false;
			}
		});
	}

	@Override
	public void draw(Graphics g, JPanel jp) {
		g.drawImage(this.image, x << 5, y * MapUtil.Y_SCAL_RATIO, (x + 1) << 5, (y + 1) * MapUtil.Y_SCAL_RATIO,
				this.imageX * 192, 0, (this.imageX + 1) * 192, 192, jp);
	}

}
