package com.itheima;
import javax.swing.*;

public class MainFrame extends JFrame {
    //用一个常量来存放图片的路径
    private final String ImagePath = "src/image/";
    //初始化一个二维数组，用来存放石头图片
    private int[][] imageData = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    //主界面
    public MainFrame() {
        //初始化界面
        initFrame();
        //初始化界面图片
        initImage();
        //初始化系统操作条，有两个操作：1、退出游戏，2、重启游戏
        initMenu();

        //必须在最后设置窗口是否可见
        this.setVisible(true);
    }

    private void initMenu() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("系统");
        JMenuItem jMenuItem1 = new JMenuItem("退出游戏");
        JMenuItem jMenuItem2 = new JMenuItem("重启游戏");
        jMenu.add(jMenuItem1);
        //添加退出游戏的事件监听器
        jMenuItem1.addActionListener(e -> System.exit(0));
        jMenu.add(jMenuItem2);
        jMenuBar.add(jMenu);
        this.setJMenuBar(jMenuBar);
    }

    private void initImage() {
        //设置用一个二维数组设置石头图片
        for (int i = 0; i < imageData.length; i++) {
            for (int j = 0; j < imageData[i].length; j++) {
                //获取当前图片的编号
                String imageId = imageData[i][j] + ".png";
                //根据编号获取图片
                ImageIcon imageIcon = new ImageIcon(ImagePath + imageId);
                // 创建一个JLabel对象，用来显示图片
                JLabel jLabel = new JLabel(imageIcon);
                // 设置图片的位置
                jLabel.setBounds(22 + j * 100, 60 + i * 100, 100, 100);
                this.add(jLabel);

            }
        }
        //初始化背景图片
        ImageIcon imageIcon = new ImageIcon(ImagePath + "background.png");
        JLabel jLabel = new JLabel(imageIcon);
        jLabel.setBounds(0, 0, 450, 484);
        this.add(jLabel);

    }

    private void initFrame() {
        //初始化窗口的大小等一系列信息
        this.setTitle("石头迷阵 V 1.0 Jane");
        //设置窗口的大小
        this.setSize(465, 580);
        //设置窗口的位置
        this.setLocationRelativeTo(null);
        //设置窗口的关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置布局方式为绝对位置定位
        this.setLayout(null);
        //

    }
}
