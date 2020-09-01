package manager;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import entity.EnemyTank;
import view.util.MapUtil;

public class EnemyTankManager {

	public static Vector<EnemyTank> enemyList = new Vector<>();
	
	public static ExecutorService enemyAnimationExecutor = Executors.newFixedThreadPool(15);
	
	public static ExecutorService enemyAutoMoveExecutor = Executors.newFixedThreadPool(15);
	
	public static ExecutorService enemyAutoFireExecutor = Executors.newFixedThreadPool(15);

	public static void creatEnemy() {
		for (int i = 0; i < 3; i++) {
			for (int j = 1; j <= 5; j++) {
				switch (j) {
				case 1:
					enemyList.add(new EnemyTank((int)(Math.random()*28+1), 1, MapUtil.enemy_1, j));
					break;
				case 2:
					enemyList.add(new EnemyTank((int)(Math.random()*28+1), 1, MapUtil.enemy_2, j));
					break;
				case 3:
					enemyList.add(new EnemyTank((int)(Math.random()*28+1), 1, MapUtil.enemy_3, j));
					break;
				case 4:
					enemyList.add(new EnemyTank((int)(Math.random()*28+1), 1, MapUtil.enemy_4, j));
					break;
				case 5:
					enemyList.add(new EnemyTank((int)(Math.random()*28+1), 1, MapUtil.enemy_5, j));
					break;
				default:
					break;
				}
			}
		}
	}

}
