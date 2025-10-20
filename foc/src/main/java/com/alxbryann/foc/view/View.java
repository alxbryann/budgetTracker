package com.alxbryann.foc.view;

import com.alxbryann.foc.model.Controller;
import com.alxbryann.foc.view.tabbed.TabbedForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author barr2
 */
public final class View extends JFrame {

    private ViewController viewController;
    private JPanel[] viewCalendar;
    private int clickedDay;
    private CalendarTab calendarTab;
    private CustomTitleBar customTitleBar;

    public View(Controller controller) {
        viewController = new ViewController();
        viewController.setController(controller);
        viewController.assignFoToDays();
        viewController.assignIncomesToDays();
        viewController.setView(this);
        
        // Configurar ventana sin decoración nativa
        setUndecorated(true);
        setSize(1280, 720);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color color = new Color(101, 164, 118);
        getContentPane().setBackground(color);

        // Crear un contenedor principal para las pestañas
        JPanel mainTabbedContainer = new JPanel() {
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
        mainTabbedContainer.setLayout(null);
        Color color2 = new Color(194, 206, 197);
        mainTabbedContainer.setBackground(color2);
        
        // Crear la barra de título personalizada
        customTitleBar = new CustomTitleBar(this, mainTabbedContainer);
        customTitleBar.setTitle("BudgetTracker");
        
        // Instalar drawer
        Drawer.getInstance().install(this);
        
        // Crear la pestaña del calendario
        calendarTab = new CalendarTab(viewController, this);
        customTitleBar.addTab("Calendario", calendarTab);
        customTitleBar.showTabbed(true);

        // Agregar componentes a la ventana
        add(customTitleBar, BorderLayout.NORTH);
        add(mainTabbedContainer, BorderLayout.CENTER);
        
        setVisible(true);
    }

    public void paintFOsInView() {
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
    }

    public void paintINsInView() {
        ArrayList<Object[]> daysToPaint = viewController.paintINs();
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

        paintRepetitiveIncome();
    }

    public void paintRepetitiveFO() {
        ArrayList<Object[]> daysToPaint = viewController.paintRepetitiveFinancialObligations();
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
            } else {
                JLabel plus = new JLabel("+");
                plus.setFont(new Font("Lexend", Font.BOLD, 30));
                plus.setBounds(65, 6, 50, 30);
                plus.setForeground(Color.white);
                tempDay.add(plus);
            }
        }
    }

    public void paintRepetitiveIncome() {
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
        }
    }

    public int getDayClicked() {
        return clickedDay;
    }

    public void setClickedDay(int clickedDay) {
        this.clickedDay = clickedDay;
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
        viewController.deleteAllFinancialObligations();
        viewController.deleteAllIncomes();
        viewController.assignFoToDays();
        viewController.assignIncomesToDays();
        paintFOsInView();
        paintINsInView();
        paintRepetitiveFO();
        paintRepetitiveIncome();
    }

    public void paintFOsInCalendarTab(JPanel[] calendarDays) {
        ArrayList<Object[]> daysToPaint = viewController.paintFOs();
        paintDaysWithData(daysToPaint, calendarDays);
        paintRepetitiveFOInCalendarTab(calendarDays);
    }

    public void paintINsInCalendarTab(JPanel[] calendarDays) {
        ArrayList<Object[]> daysToPaint = viewController.paintINs();
        paintDaysWithData(daysToPaint, calendarDays);
        paintRepetitiveIncomeInCalendarTab(calendarDays);
    }

    private void paintDaysWithData(ArrayList<Object[]> daysToPaint, JPanel[] calendarDays) {
        for (int i = 0; i < daysToPaint.size(); i += 3) {
            Object day = daysToPaint.get(i);
            Object rgb = daysToPaint.get(i + 1);
            int intDay = (Integer) day;
            JPanel tempDay = calendarDays[intDay];
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
    }

    private void paintRepetitiveFOInCalendarTab(JPanel[] calendarDays) {
        ArrayList<Object[]> daysToPaint = viewController.paintRepetitiveFinancialObligations();
        paintRepetitiveDataInCalendarTab(daysToPaint, calendarDays);
    }

    private void paintRepetitiveIncomeInCalendarTab(JPanel[] calendarDays) {
        ArrayList<Object[]> daysToPaint = viewController.paintRepetitiveIncomes();
        paintRepetitiveDataInCalendarTab(daysToPaint, calendarDays);
    }

    private void paintRepetitiveDataInCalendarTab(ArrayList<Object[]> daysToPaint, JPanel[] calendarDays) {
        for (int i = 0; i < daysToPaint.size(); i += 3) {
            Object day = daysToPaint.get(i);
            Object rgb = daysToPaint.get(i + 1);
            int intDay = (Integer) day;
            JPanel tempDay = calendarDays[intDay];
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
        }
    }
    
    // Métodos para acceder a la barra de título personalizada
    public CustomTitleBar getCustomTitleBar() {
        return customTitleBar;
    }
    
    public boolean addTab(String title, TabbedForm component) {
        return customTitleBar.addTab(title, component);
    }
    
    public void removeTab(TabbedForm tab) {
        customTitleBar.removeTab(tab);
    }
    
    public void removeTabAt(int index) {
        customTitleBar.removeTabAt(index);
    }
    
    public void removeAllTabs() {
        customTitleBar.removeAllTabbed();
    }
    
    public void showTabs(boolean show) {
        customTitleBar.showTabbed(show);
    }
    
    public String[] getTabNames() {
        return customTitleBar.getTabName();
    }
    
    public int getSelectedTabIndex() {
        return customTitleBar.getTabSelectedIndex();
    }
    
    public void selectTabByTitle(String title) {
        customTitleBar.selectTabByTitle(title);
    }
}
