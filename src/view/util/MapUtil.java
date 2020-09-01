package view.util;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapUtil {
	
	public static boolean isDraw = true;
	
	/**
	 * 图片纵向缩放比
	 */
	public static final int Y_SCAL_RATIO = 25;
	
	/**
	 * Image对象
	 */
	public static Image border = null;
	public static final int BORDER = 0;
	
	/**
	 * 空白
	 */
	public static final int NOTHING = 1;
	

	/**
	 * 草
	 */
	public static Image grass = null;
	public static final int GRASS = 2;

	/**
	 * 砖头
	 */
	public static Image wall = null;
	public static final int WALL = 3;

	/**
	 * 铁
	 */
	public static Image steels = null;
	public static final int STEELS = 4;

	/**
	 * 水
	 */
	public static Image water = null;
	public static final int WATER = 5;

	/**
	 * 冰
	 */
	public static Image ice = null;
	public static final int ICE = 6;
	
	/**
	 * 土
	 */
	public static Image soil = null;
	public static final int SOIL = 7;

	public static Image boss = null;

	public static Image player = null;

	public static Image icon = null;

	public static Image enemy = null;

	public static Image star = null;

	public static Image missile = null;

	public static Image boom = null;

	public static Image equipment = null;

	public static Image succeed = null;

	public static Image loser = null;
	
	public static Image win = null;
	
	public static Image enemy_1 = null;

	public static Image enemy_2 = null;

	public static Image enemy_3 = null;
	
	public static Image enemy_4 = null;

	public static Image enemy_5 = null;
	
	/**
	 * 玩家的方向,由玩家素材图片的排列决定
	 * 	当前素材由imangeY的值决定
	 */
	public static final int PLAY_DIR_UP = 0;
	public static final int PLAY_DIR_DOWN = 3;
	public static final int PLAY_DIR_LEFT = 1;
	public static final int PLAY_DIR_RIGHT = 2;
	
	/**
	 * 敌方的方向,由素材图片的排列决定
	 * 	当前素材由imangeY的值决定
	 */
	public static final int ENEMY_DIR_UP = 3;
	public static final int ENEMY_DIR_DOWN = 0;
	public static final int ENEMY_DIR_LEFT = 1;
	public static final int ENEMY_DIR_RIGHT = 2;
	
	/**
	 * 子弹的方向,由子弹素材图片的排列决定
	 * 	当前素材由imangeX的值决定
	 */
	public static final int MISSILE_DIR_UP = 3;
	public static final int MISSILE_DIR_DOWN = 1;
	public static final int MISSILE_DIR_LEFT = 2;
	public static final int MISSILE_DIR_RIGHT = 0;
	
	
	/**
	 * 游戏的状态
	 */
	public static final int GAME_NOMRAL=0;
	public static final int GAME_LOSE=2;
	public static final int GAME_WIN=1;

	/**
	 * 地图
	 */
	public static final int[][] MAP = {
			// 0 ~ 4行
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 0 },
			{ 0, 2, 2, 2, 2, 2, 2, 1, 1, 3, 3, 1, 1, 3, 3, 3, 3, 1, 3, 3, 1, 1, 1, 4, 4, 1, 1, 1, 1, 0 },
			{ 0, 2, 2, 2, 2, 2, 2, 1, 1, 3, 3, 1, 1, 3, 3, 3, 3, 1, 3, 3, 1, 1, 1, 4, 4, 1, 1, 1, 1, 0 },
			// 5 ~ 9行
			{ 0, 2, 2, 6, 6, 1, 1, 1, 1, 3, 3, 1, 1, 1, 4, 4, 1, 1, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
			{ 0, 2, 2, 6, 6, 1, 4, 4, 1, 3, 3, 1, 1, 1, 4, 4, 1, 1, 3, 3, 1, 1, 1, 6, 6, 6, 6, 1, 1, 0 },
			{ 0, 2, 2, 6, 6, 1, 4, 4, 1, 1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 1, 1, 1, 6, 6, 6, 6, 1, 1, 0 },
			{ 0, 1, 1, 5, 5, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 5, 5, 2, 2, 2, 2, 2, 2, 3, 3, 0 },
			{ 0, 1, 1, 5, 5, 1, 1, 3, 3, 3, 3, 4, 4, 3, 3, 3, 3, 1, 1, 5, 5, 2, 2, 2, 2, 2, 2, 3, 3, 0 },
			// 10 ~ 14行
			{ 0, 1, 1, 5, 5, 1, 1, 3, 3, 3, 3, 4, 4, 3, 3, 3, 3, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 3, 3, 0 },
			{ 0, 1, 1, 5, 5, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 3, 3, 0 },
			{ 0, 1, 1, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 1, 2, 2, 2, 1, 1, 0 },
			{ 0, 1, 1, 3, 3, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 1, 2, 2, 2, 1, 1, 0 },
			{ 0, 1, 1, 3, 3, 1, 1, 2, 2, 2, 1, 4, 4, 4, 4, 4, 4, 4, 1, 3, 3, 3, 1, 1, 2, 2, 2, 1, 1, 0 },
			// 15 ~ 19 行
			{ 0, 1, 1, 3, 3, 1, 1, 2, 2, 2, 1, 4, 4, 4, 4, 4, 4, 4, 1, 3, 3, 3, 1, 1, 2, 2, 2, 1, 1, 0 },
			{ 0, 4, 3, 3, 4, 4, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 0 },
			{ 0, 4, 3, 3, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 0 },
			{ 0, 1, 3, 3, 2, 2, 1, 3, 3, 1, 1, 1, 1, 1, 4, 1, 1, 1, 3, 3, 3, 2, 2, 3, 3, 3, 4, 4, 4, 0 },
			{ 0, 1, 3, 3, 2, 2, 1, 3, 3, 3, 1, 3, 3, 1, 1, 1, 1, 1, 3, 3, 3, 2, 2, 3, 3, 3, 4, 4, 4, 0 },
			// 20 ~ 24行
			{ 0, 1, 3, 3, 2, 2, 1, 3, 3, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 0 },
			{ 0, 1, 3, 3, 2, 2, 1, 3, 3, 1, 1, 1, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 0 },
			{ 0, 1, 3, 3, 2, 2, 1, 3, 3, 1, 1, 1, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 0 },
			{ 0, 1, 3, 3, 4, 4, 1, 1, 1, 1, 1, 1, 3, 3, 8, 8, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 0 },
			{ 0, 1, 3, 3, 4, 4, 1, 1, 1, 1, 1, 1, 3, 3, 8, 8, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 0 },
			// 25
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	static {
		try {
			border = ImageIO.read(new File("img/gray.png"));
			grass = ImageIO.read(new File("img/grass.png"));
			wall = ImageIO.read(new File("img/wall.gif"));
			steels = ImageIO.read(new File("img/steels.png"));
			water = ImageIO.read(new File("img/water1.jpg"));
			ice = ImageIO.read(new File("img/ice.png"));
			soil = ImageIO.read(new File("img/soil.gif"));
			boss = ImageIO.read(new File("img/boss.gif"));
			player = ImageIO.read(new File("img/play_1.gif"));
			icon = ImageIO.read(new File("img/icon.png"));
			enemy = ImageIO.read(new File("img/tanks.bmp"));
			star = ImageIO.read(new File("img/star.bmp"));
			missile = ImageIO.read(new File("img/bullet.png"));
			boom = ImageIO.read(new File("img/boom.png"));
			equipment = ImageIO.read(new File("img/boss2.gif"));
			succeed = ImageIO.read(new File("img/win.bmp"));
			loser = ImageIO.read(new File("img/game_over.bmp"));
			win = ImageIO.read(new File("img/win.png"));
			enemy_1 = ImageIO.read(new File("img/enemy_1.png"));
			enemy_2 = ImageIO.read(new File("img/enemy_2.png"));
			enemy_3 = ImageIO.read(new File("img/enemy_3.png"));
			enemy_4 = ImageIO.read(new File("img/enemy_4.png"));
			enemy_5 = ImageIO.read(new File("img/enemy_5.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void drawMap(Graphics g, JPanel jp) {
		for (int y = 0; y < MapUtil.MAP.length; y++) {// 行

			for (int x = 0; x < MapUtil.MAP[y].length; x++) {
				/*
				 * x, y单元格开始位置，x, y各加1为单元格结束位置
				 * 该格子上放置的图片
				 */
				switch (MAP[y][x]) {
				case BORDER:
					g.drawImage(border, x << 5, y * Y_SCAL_RATIO, (x + 1) << 5, (y + 1) * Y_SCAL_RATIO, 0, 0, 74, 78, jp);
					break;
				case GRASS:
					g.drawImage(grass, x << 5, y * Y_SCAL_RATIO, (x + 1) << 5, (y + 1) * Y_SCAL_RATIO, 0, 0, 87, 83, jp);
					break;
				case WALL:
					g.drawImage(wall, x << 5, y * Y_SCAL_RATIO, (x + 1) << 5, (y + 1) * Y_SCAL_RATIO, 0, 0, 32, 32, jp);
					break;
				case STEELS:
					g.drawImage(steels, x << 5, y * Y_SCAL_RATIO, (x + 1) << 5, (y + 1) * Y_SCAL_RATIO, 0, 0, 32, 32, jp);
					break;
				case WATER:
					g.drawImage(water, x << 5, y * Y_SCAL_RATIO, (x + 1) << 5, (y + 1) * Y_SCAL_RATIO, 0, 0, 32, 32, jp);
					break;
				case ICE:
					g.drawImage(ice, x << 5, y * Y_SCAL_RATIO, (x + 1) << 5, (y + 1) * Y_SCAL_RATIO, 0, 0, 32, 32, jp);
					break;
				case SOIL:
					g.drawImage(soil, x << 5, y * Y_SCAL_RATIO, (x + 1) << 5, (y + 1) * Y_SCAL_RATIO, 0, 0, 32, 32, jp);
					break;
				default:
					break;
				}

			}
		}
	}
}
