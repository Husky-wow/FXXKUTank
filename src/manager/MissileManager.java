package manager;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import entity.Missile;

public class MissileManager {
	
	/**
	 * 存放子弹对象的集合
	 */
	public static Vector<Missile> missilesList = new Vector<>();
	
	/**
	 * 炮弹移动执行线程池
	 */
	public static ExecutorService misslesMoveExecutor = Executors.newCachedThreadPool();
}
