package com.alxbryann.foc.view;

import com.alxbryann.foc.model.Controller;
import com.alxbryann.foc.view.tabbed.TabbedForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Taskbar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author alxbryann
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
        viewController.copyRepetitiveTransactionsForThisMonth();
        viewController.setView(this);
        
        setUndecorated(true);
        setSize(1280, 720);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        configureApplicationIcon();

        Color color = new Color(122, 168, 144);
        getContentPane().setBackground(color);

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
        
        customTitleBar = new CustomTitleBar(this, mainTabbedContainer);
        
        Drawer.getInstance().install(this);
        
        calendarTab = new CalendarTab(viewController, this);
        customTitleBar.addTab("Calendar", calendarTab);
        customTitleBar.showTabbed(true);

        add(customTitleBar, BorderLayout.NORTH);
        add(mainTabbedContainer, BorderLayout.CENTER);
        viewController.assignTransactionToDays();
        setVisible(true);
    }

    public int getDayClicked() {
        return clickedDay;
    }

    public void setClickedDay(int clickedDay) {
        this.clickedDay = clickedDay;
    }

    public void setViewCalendar(JPanel[] viewCalendar) {
        this.viewCalendar = viewCalendar;
    }

    public JPanel[] getViewCalendar() {
        return viewCalendar;
    }

    public void clearViewCalendar() {
        if (viewCalendar == null) {
            return;
        }
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

    public void paintTransactionsInCalendarTab(JPanel[] calendarDays) {
        ArrayList<Object[]> daysToPaint = viewController.getDaysToPaint();
        paintDaysWithData(daysToPaint, calendarDays);
        paintRepetitiveTransactionsInCalendarTab(calendarDays);
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

    private void paintRepetitiveTransactionsInCalendarTab(JPanel[] calendarDays) {
        List<HashMap<String, Object>> daysToPaint = viewController.getListOfRepetitiveTransactionsInCurrentMonth();
        paintRepetitiveDataInCalendarTab(daysToPaint, calendarDays);
    }

    private void paintRepetitiveDataInCalendarTab(List<HashMap<String, Object>> daysToPaint, JPanel[] calendarDays) {
        if (daysToPaint == null) {
            return;
        }
        for (HashMap<String, Object> map : daysToPaint) {
            if (map == null) continue;
            Object dayObj = map.get("day");
            Object rgbObj = map.get("rgb");
            Object nameObj = map.get("name");
            if (dayObj == null || rgbObj == null || nameObj == null) continue;

            int intDay;
            if (dayObj instanceof Integer) {
                // The model stores day as 1-based (e.g., 1..31). View calendar index is 0-based.
                intDay = ((Integer) dayObj) - 1;
            } else {
                try {
                    intDay = Integer.parseInt(dayObj.toString()) - 1;
                } catch (Exception e) {
                    continue;
                }
            }

            if (intDay < 0 || intDay >= calendarDays.length) continue;

            JPanel tempDay = calendarDays[intDay];
            if (!"true".equals(tempDay.getClientProperty("painted"))) {
                String strRgb = rgbObj.toString();
                try {
                    int red = Integer.parseInt(strRgb.substring(0, strRgb.indexOf(",")));
                    strRgb = strRgb.substring(strRgb.indexOf(",") + 2);
                    int green = Integer.parseInt(strRgb.substring(0, strRgb.indexOf(",")));
                    strRgb = strRgb.substring(strRgb.indexOf(",") + 2);
                    int blue = Integer.parseInt(strRgb);
                    String strName = nameObj.toString();
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
                    } else if (strName.length() > 10) {
                        nameJLabel.setFont(new Font("Lexend", Font.PLAIN, 10));
                    } else {
                        nameJLabel.setFont(new Font("Lexend", Font.PLAIN, 14));
                    }
                    nameJLabel.setForeground(Color.BLACK);
                    nameJLabel.setHorizontalAlignment(JLabel.CENTER);
                    nameJLabel.setBounds(0, 40, 100, 50);
                    tempDay.add(nameJLabel);
                    tempDay.putClientProperty("painted", "true");
                } catch (Exception ex) {
                    // If rgb parsing fails, skip this entry
                    continue;
                }
            } else {
                JLabel plus = new JLabel("+");
                plus.setFont(new Font("Lexend", Font.BOLD, 30));
                plus.setBounds(65, 6, 50, 30);
                plus.setForeground(Color.white);
                tempDay.add(plus);
            }
        }
    }

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
    
    private void configureApplicationIcon() {
        try {
            ImageIcon logoIcon = new ImageIcon(getClass().getClassLoader().getResource("./img/logo.png"));
            Image logoImage = logoIcon.getImage();
            
            setIconImage(logoImage);
            
            List<Image> iconImages = new ArrayList<>();
            iconImages.add(logoImage.getScaledInstance(16, 16, Image.SCALE_SMOOTH));
            iconImages.add(logoImage.getScaledInstance(32, 32, Image.SCALE_SMOOTH));
            iconImages.add(logoImage.getScaledInstance(48, 48, Image.SCALE_SMOOTH));
            iconImages.add(logoImage.getScaledInstance(64, 64, Image.SCALE_SMOOTH));
            iconImages.add(logoImage.getScaledInstance(128, 128, Image.SCALE_SMOOTH));
            iconImages.add(logoImage.getScaledInstance(256, 256, Image.SCALE_SMOOTH));
            
            setIconImages(iconImages);
            
            if (Taskbar.isTaskbarSupported()) {
                Taskbar taskbar = Taskbar.getTaskbar();
                if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {
                    taskbar.setIconImage(logoImage);
                }
            }
            
            // Configurar propiedades del sistema para macOS
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "FOC");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", "FOC - Financial Obligation Calendar");
            
        } catch (Exception e) {
            System.err.println("No se pudo cargar el icono de la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
