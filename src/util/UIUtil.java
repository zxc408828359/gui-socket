package util;

import javax.swing.*;

public class UIUtil {
    public UIUtil() {
    }

    public static void run(final JFrame jFrame, final int width, final int height) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jFrame.setSize(width, height);
            }
        });
    }
}