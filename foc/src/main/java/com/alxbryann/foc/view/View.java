package com.alxbryann.foc.view;

import com.alxbryann.foc.model.Controller;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author barr2
 */
public final class View extends JFrame {

    private ViewController viewController;
    private JPanel[] viewCalendar;
    private final JPanel notifications;
    private int clickedDay;

    public View(Controller controller) {

        viewController = new ViewController();
        viewController.setController(controller);
        viewController.setView(this);
        setUndecorated(false);
        setSize(1280, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color color = new Color(101, 164, 118);
        getContentPane().setBackground(color);

        JPanel titleContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            }
        };
        titleContainer.setBounds(0, 17, 408, 41);
        titleContainer.setOpaque(true);
        color = new Color(41, 130, 75);
        titleContainer.setBackground(color);
        JLabel title = new JLabel("BudgetTracker");
        title.setFont(new Font("Braah One", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        titleContainer.add(title);
        add(titleContainer);
        JPanel calendary = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        calendary.setLayout(null);
        calendary.setBounds(30, 90, 850, 550);
        color = new Color(194, 206, 197);
        calendary.setBackground(color);

        MouseListener dayClickListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel clickedPanel = (JPanel) e.getSource();
                clickedDay = (int) clickedPanel.getClientProperty("dayNumber") - 1;
                DetailPerDay detailPerDay = new DetailPerDay(viewController);
                detailPerDay.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        viewCalendar = new JPanel[viewController.getDaysInCurrentMonth()];

        int x = 30, y = 10;
        for (int i = 1; i < viewCalendar.length + 1; i++) {
            JPanel day = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                    g2.dispose();
                }

                @Override
                protected void paintBorder(Graphics g) {
                }

                @Override
                public boolean isOpaque() {
                    return false;
                }
            };
            day.setBounds(x, y, 100, 90);
            day.setOpaque(true);
            calendary.add(day);
            day.setBackground(new Color(212, 215, 213, 255));
            day.setLayout(null);
            JLabel numberDay = new JLabel(String.valueOf(i));
            numberDay.setFont(new Font("Lexend", Font.BOLD, 30));
            numberDay.setBounds(10, 6, 50, 30);
            numberDay.setForeground(Color.white);
            day.add(numberDay);
            day.putClientProperty("dayNumber", i);
            day.addMouseListener(dayClickListener);
            viewCalendar[i - 1] = day;
            viewCalendar[i - 1].putClientProperty("painted", "false");

            x += 115;
            if (i % 7 == 0 && i != 0) {
                x = 30;
                y += 105;
            }
        }

        add(calendary);
        notifications = new JPanel();
        notifications.setLayout(null);
        notifications.setBounds(920, 0, 400, 720);
        notifications.setOpaque(true);
        notifications.setBackground(Color.white);
        notifications.add(new NextIncomesPanel(viewController));
        add(notifications);
        paintTransactionsInView();
        setVisible(true);
    }

    public void paintTransactionsInView() {/* 
    ArrayList<Object[]> daysToPaint = viewController.paintFOs();
        for (int i = 0; i < daysToPaint.size(); i += 3) {
            Object day = daysToPaint.get(i);
            Object rgb = daysToPaint.get(i + 1);
            int intDay = (Integer) day;
            JPanel tempDay = viewCalendar[intDay];
            if (!tempDay.getClientProperty("painted").equals("true")) {
                String strRgb = (String) rgb;
                int red = Integer.parseInt(strRgb.substring(0, strRgb.indexOf(",")));
                strRgb = strRgb.substring(strRgb.indexOf(",") + 2);
                int green = Integer.parseInt(strRgb.substring(0, strRgb.indexOf(",")));
                strRgb = strRgb.substring(strRgb.indexOf(",") + 2);
                int blue = Integer.parseInt(strRgb);
                Object name = daysToPaint.get(i + 2);
                String strName = (String) name;
                tempDay.setBackground(new Color(red, green, blue));
                tempDay.putClientProperty("painted", "true");
                JLabel nameJLabel = new JLabel(strName);
                if (strName.length() <= 7) {
                    nameJLabel.setFont(new Font("Lexend", Font.PLAIN, 20));
                } else {
                    if (strName.length() > 7) {
                        nameJLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
                    } else {
                        if (strName.length() > 10) {
                            nameJLabel.setFont(new Font("Lexend", Font.PLAIN, 10));
                        }
                    }
                }

                nameJLabel.setForeground(Color.BLACK);
                nameJLabel.setHorizontalAlignment(JLabel.CENTER);
                nameJLabel.setBounds(0, 40, 100, 50);
                tempDay.add(nameJLabel);
            } else {
                JLabel plus = new JLabel("+");
                plus.setFont(new Font("Lexend", Font.BOLD, 30));
                plus.setBounds(65, 6, 50, 30);
                plus.setForeground(Color.white);
                tempDay.add(plus);
            }

        }

        paintRepetitiveFO();
        */
    }

    public void paintRepetitiveIncome() {/* 
        ArrayList<Object[]> daysToPaint = viewController.paintRepetitiveIncomes();
        for (int i = 0; i < daysToPaint.size(); i += 3) {
            Object day = daysToPaint.get(i);
            Object rgb = daysToPaint.get(i + 1);
            int intDay = (Integer) day;
            JPanel tempDay = viewCalendar[intDay];
            if (!tempDay.getClientProperty("painted").equals("true")) {
                String strRgb = (String) rgb;
                int red = Integer.parseInt(strRgb.substring(0, strRgb.indexOf(",")));
                strRgb = strRgb.substring(strRgb.indexOf(",") + 2);
                int green = Integer.parseInt(strRgb.substring(0, strRgb.indexOf(",")));
                strRgb = strRgb.substring(strRgb.indexOf(",") + 2);
                int blue = Integer.parseInt(strRgb);
                Object name = daysToPaint.get(i + 2);
                String strName = (String) name;
                tempDay.removeAll();
                tempDay.revalidate();
                tempDay.repaint();
                tempDay.setBackground(new Color(red, green, blue));
                JLabel numberDay = new JLabel(String.valueOf(tempDay.getClientProperty("dayNumber")));
                numberDay.setFont(new Font("Lexend", Font.BOLD, 30));
                numberDay.setBounds(10, 6, 50, 30);
                numberDay.setForeground(Color.white);
                tempDay.add(numberDay);
                JLabel nameJLabel = new JLabel(strName);
                if (strName.length() <= 7) {
                    nameJLabel.setFont(new Font("Lexend", Font.PLAIN, 20));
                } else {
                    if (strName.length() > 7) {
                        nameJLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
                    } else {
                        if (strName.length() > 10) {
                            nameJLabel.setFont(new Font("Lexend", Font.PLAIN, 10));
                        }
                    }
                }

                nameJLabel.setForeground(Color.BLACK);
                nameJLabel.setHorizontalAlignment(JLabel.CENTER);
                nameJLabel.setBounds(0, 40, 100, 50);
                tempDay.add(nameJLabel);
                tempDay.putClientProperty("painted", "true");
            } else {
                JLabel plus = new JLabel("+");
                plus.setFont(new Font("Lexend", Font.BOLD, 30));
                plus.setBounds(65, 6, 50, 30);
                plus.setForeground(Color.white);
                tempDay.add(plus);
            }
        }*/
    }

    public int getDayClicked() {
        return clickedDay;
    }

    public void clearViewCalendar() {
        for (int i = 0; i < viewCalendar.length; i++) {
            JPanel day = viewCalendar[i];

            day.removeAll();
            day.revalidate();
            day.repaint();

            day.setBackground(new Color(212, 215, 213, 255));

            JLabel numberDay = new JLabel(String.valueOf(day.getClientProperty("dayNumber")));
            numberDay.setFont(new Font("Lexend", Font.BOLD, 30));
            numberDay.setBounds(10, 6, 50, 30);
            numberDay.setForeground(Color.white);
            day.add(numberDay);

            day.putClientProperty("painted", "false");
        }
    }

    public void updateViewCalendar() {
        clearViewCalendar();
    }
}
