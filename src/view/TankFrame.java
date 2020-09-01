package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import entity.PlayerTank;
import view.util.MapUtil;

public class TankFrame extends JFrame {

	private static final long serialVersionUID = 784492008137015460L;

	public TankFrame() {
		// 设置标题
		setTitle("FXXK U TANK");
		
		// 设置标题图片
		setIconImage(MapUtil.icon);
		// 页面的大小为30 * 26个格子，每个格子的宽高是32*Y_SCAL_RATIO
		setSize(31 << 5, 28 * MapUtil.Y_SCAL_RATIO);
		// 屏幕的宽度
		Dimension scree = Toolkit.getDefaultToolkit().getScreenSize();
		// 屏宽
		int w = (int) scree.width - (30 << 5);
		// 居中
		setLocation(w / 2, 10);
		// 将面板加入到框架中
		setContentPane(new TankLev1Jpanel());
		
		//框架可以对键盘事件进行监听
		addKeyListener(PlayerTank.getInstance());

		setVisible(true);
		// 可以进行关闭
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
