
  package gui;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


  /**
     * @author zakisoft.com
     *
     */
    public class ZPanel extends javax.swing.JComponent implements ActionListener {

        private static final long serialVersionUID = 1L;
        private BufferedImage image;

        private static final int ANIMATION_FRAMES = 100;
        private static final int ANIMATION_INTERVAL = 10;

        private static int frameIndex=100;
        // 时钟
        private Timer timer;

        public BufferedImage getImage() {
            return image;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }

        private int imgWidth;
        private int imgHeight;

        public int getImgWidth() {
            return imgWidth;
        }

        public void setImgWidth(int imgWidth) {
            this.imgWidth = imgWidth;
        }

        public int getImgHeight() {
            return imgHeight;
        }

        public void setImgHeight(int imgHeight) {
            this.imgHeight = imgHeight;
        }

        public ZPanel() {
        }

        public void setImagePath(String imgPath) {
            // 该方法不推荐使用，该方法是懒加载，图像并不加载到内存，当拿图像的宽和高时会返回-1；
            // image = Toolkit.getDefaultToolkit().getImage(imgPath);
            try {
                // 该方法会将图像加载到内存，从而拿到图像的详细信息。
                image = ImageIO.read(new FileInputStream(imgPath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setImgWidth(image.getWidth(this));
            setImgHeight(image.getHeight(this));
        }

        public void paintComponent(Graphics g) {
            int x = 0;
            int y = 0;
            if (null == image) {
                return;
            }
            g.drawImage(image, x, y, image.getWidth(null), image.getHeight(null),
                    null);
        }

        public void paint(Graphics g) {
            if (isAnimating()) {
                // 根据当前帧显示当前透明度的内容组件
                float alpha = (float) frameIndex / (float) ANIMATION_FRAMES;
                Graphics2D g2d = (Graphics2D) g;
                g2d.setComposite(AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, alpha));
                // Renderer渲染机制
                super.paint(g2d);
            } else {
                // 如果是第一次，启动动画时钟
                frameIndex = 100;
                timer = new Timer(ANIMATION_INTERVAL, this);
                timer.start();
            }
        }

        // 判断当前是否正在进行动画
        private boolean isAnimating() {
            return timer != null && timer.isRunning();
        }

        // 关闭时钟，重新初始化
        private void closeTimer() {
            if (isAnimating()) {
                frameIndex = 1;
                timer.stop();
                timer = null;
            }
        }

        // 动画时钟处理事件
        public void actionPerformed(ActionEvent e) {
            // 前进一帧
            frameIndex--;
            if (frameIndex <= 1)
                // 最后一帧，关闭动画
                closeTimer();
            else
                // 更新当前一帧
                repaint();
        }



    public static void main(String[] args)throws Exception {
        JFrame jFrame=new JFrame();
        jFrame.setSize(222,222);
        ZPanel zPanel=new ZPanel();
        zPanel.setImagePath("blue.png");
        zPanel.setImgHeight(222);
        zPanel.setImgWidth(222);
        zPanel.setSize(222,222);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(zPanel);
        jFrame.setVisible(true);

        /*
        JButton button=new JButton();
        button.setText("cxw");
        button.setSize(50,50);
        button.setVisible(true);
        JFrame jFrame=new JFrame();
        jFrame.setSize(222,222);

        GlassBox glassBox=new GlassBox();
        glassBox.setSize(100,100);
        glassBox.setVisible(true);
glassBox.setToolTipText("ccc");
        glassBox.add(button);
        jFrame.add(glassBox);

        jFrame.setVisible(true);*/
    }

}
