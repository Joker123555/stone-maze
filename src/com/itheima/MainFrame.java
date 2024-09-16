package com.itheima;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    //用两个常量来记录空白色块的位置
    private static int row;
    private static int col;

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
        //1.初始化界面
        initFrame();
        //4.打乱图片的顺序
        initRandom();
        //2.初始化界面图片
        initImage();
        //3.初始化系统操作条，有两个操作：1、退出游戏，2、重启游戏
        initMenu();
        //5、给当前窗口绑定上下左右按键事件。
        initKeyPressEvent();

        //必须在最后设置窗口是否可见
        this.setVisible(true);
    }

    private void initKeyPressEvent() {

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //绑定上下左右按键事件
                switch (e.getKeyCode()) {
                    //用户点击了上键
                    case KeyEvent.VK_UP:
                        switchAndMove(Direction.UP);
                        break;
                    //用户点击了下键
                    case KeyEvent.VK_DOWN:
                        switchAndMove(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        switchAndMove(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        switchAndMove(Direction.RIGHT);
                        break;
                }
            }
        });
    }

    //设计一个方法，用来实现图片的交换和移动
    private void switchAndMove(Direction direction){
        //用枚举类来实现
        switch (direction){
            case UP:
                //向上移动
                //用空白色块的位置进行上交换
                if(row<imageData.length-1){
                    //当前空白色块的位置 row col
                    //要交换色块的位置 row+1 col
                    int temp = imageData[row + 1][col];
                    imageData[row + 1][col] = imageData[row][col];
                    imageData[row][col] = temp;
                    //更新空白色块的位置
                    row++;
                }
                break;
            case DOWN:
                //向下移动
                if(row>0){
                    //当前空白色块的位置 row col
                    //要交换色块的位置 row-1 col
                    int temp = imageData[row - 1][col];
                    imageData[row - 1][col] = imageData[row][col];
                    imageData[row][col] = temp;
                    //更新空白色块的位置
                    row--;
                }
                break;
            case LEFT:
                //向左移动
                if(col<imageData.length-1){
                    //当前空白色块的位置 row col
                    //要交换色块的位置 row col+1
                    int temp = imageData[row][col + 1];
                    imageData[row][col + 1] = imageData[row][col];
                    imageData[row][col] = temp;
                    //更新空白色块的位置
                    col++;
                }
                break;
            case RIGHT:
                //向右移动
                if(col>0){
                    //当前空白色块的位置 row col
                    //要交换色块的位置 row col-1
                    int temp = imageData[row][col - 1];
                    imageData[row][col - 1] = imageData[row][col];
                    imageData[row][col] = temp;
                    //更新空白色块的位置
                    col--;
                }
                break;
        }
        //刷新界面
        initImage();
    }

    private void initRandom() {
        for (int i = 0; i < imageData.length; i++) {
            for (int j = 0; j < imageData[i].length; j++) {
                //随机获取两行两列的数据，让他们交换
                int i1 = (int) (Math.random() * imageData.length);
                int j1 = (int) (Math.random() * imageData.length);
                int i2 = (int) (Math.random() * imageData.length);
                int j2 = (int) (Math.random() * imageData.length);

                //让它们交换
                int temp = imageData[i1][j1];
                imageData[i1][j1] = imageData[i2][j2];
                imageData[i2][j2] = temp;
            }
        }

        //交换完成后记录空白色块的位置
        OUT:
        for (int i = 0; i < imageData.length; i++) {
            for (int j = 0; j < imageData[i].length; j++) {
                if (imageData[i][j] == 0) {
                    row = i;
                    col = j;
                    break OUT;
                }
            }
        }

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
        //先清空界面
        this.getContentPane().removeAll();
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
        //再重新刷新新的图层
        this.repaint();

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
