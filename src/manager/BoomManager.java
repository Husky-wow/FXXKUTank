package manager;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import entity.Boom;

public class BoomManager {
	
	/**
	 * 存放子弹对象的集合
	 */
	public static volatile Vector<Boom> boomsList = new Vector<>();
	
	/**
	 * Boom类动画执行线程
	 * animation()方法中线程
	 * Boom的数量不太好确认，因此这里使用newCachedThreadPool()来获取线程池
	 */
	public static ExecutorService boomAnimatonExecutor = Executors.newCachedThreadPool();
}
