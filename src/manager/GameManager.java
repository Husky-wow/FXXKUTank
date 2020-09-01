package manager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import view.TankLev1Jpanel;
import view.util.MapUtil;

public class GameManager {

	/**
	 * 单例
	 */
	private static GameManager instance = new GameManager();

	/**
	 * Boss动画绘制线程
	 */
	public ExecutorService bossAnimationExectutor = Executors.newSingleThreadExecutor();

	/**
	 * 死亡坦克计数器
	 */
	public int enemyDeadCount = 0;

	public ScheduledExecutorService gameWinCheckTimer;

	private GameManager() {
		gameWinCheckTimer = Executors.newSingleThreadScheduledExecutor();
		/**
		 * 每隔 1s监测死亡坦克数
		 */
		gameWinCheckTimer.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				if (enemyDeadCount >= 15) {
					TankLev1Jpanel.gameState = MapUtil.GAME_WIN;
				}
			}
		}, 1, 1, TimeUnit.SECONDS);
	}

	public static GameManager getInstance() {
		return instance;
	}

	/**
	 * 游戏结束，回收资源
	 */
	public void endGame() {
		BoomManager.boomsList.clear();
		BoomManager.boomAnimatonExecutor.shutdownNow();

		EnemyTankManager.enemyList.clear();
		EnemyTankManager.enemyAnimationExecutor.shutdownNow();
		EnemyTankManager.enemyAutoFireExecutor.shutdownNow();
		EnemyTankManager.enemyAutoMoveExecutor.shutdownNow();

		MissileManager.missilesList.clear();
		MissileManager.misslesMoveExecutor.shutdownNow();

		gameWinCheckTimer.shutdownNow();

		playBgMusic();

	}

	/**
	 * 播放背景音乐
	 */
	private void playBgMusic() {
		File musicPath = new File("img/start.wav");
		if (musicPath.exists()) {
			try {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
