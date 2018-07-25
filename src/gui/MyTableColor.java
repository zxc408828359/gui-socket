package gui;


import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author han
 * 调用该类的makeFace()方法可以将表格奇偶行设置不同的颜色
 *
 */
public class MyTableColor {
    public static void makeFace(JTable table) {
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table,
                                                               Object value, boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
                    if (row % 2 == 0)
                        setBackground(Color.white); // 设置奇数行底色
                    else if (row % 2 == 1)
                        setBackground(new Color(224, 255, 255)); // 设置偶数行底色
                    return super.getTableCellRendererComponent(table, value,
                            isSelected, hasFocus, row, column);
                }
            };

            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
