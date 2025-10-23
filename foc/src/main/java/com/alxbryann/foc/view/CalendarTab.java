package com.alxbryann.foc.view;

import com.alxbryann.foc.view.tabbed.TabbedForm;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author alxbryann
 */
public class CalendarTab extends TabbedForm {
    
    private ViewController viewController;
    private JPanel[] viewCalendar;
    private View parentView;
    
    public CalendarTab(ViewController viewController, View parentView) {
        this.viewController = viewController;
        this.parentView = parentView;
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(null);
        setBackground(new Color(101, 164, 118)); 
        setPreferredSize(new Dimension(1280, 720));
        setBounds(0, 0, 1280, 720);
        JPanel calendarArea = new JPanel() {
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
            protected void paintBorder(Graphics g) {}

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        calendarArea.setLayout(null);
        calendarArea.setBounds(30, 90, 850, 550);
        calendarArea.setBackground(new Color(194, 206, 197));
        add(calendarArea);
        
        MouseListener dayClickListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel clickedPanel = (JPanel) e.getSource();
                int clickedDay = (int) clickedPanel.getClientProperty("dayNumber") - 1;
                parentView.setClickedDay(clickedDay);
                
                DetailPerDayTab detailPerDayTab = new DetailPerDayTab(viewController);
                String tabTitle = detailPerDayTab.getTabTitle();
                
                String[] existingTabs = parentView.getTabNames();
                boolean tabExists = false;
                for (String existingTab : existingTabs) {
                    if (existingTab.equals(tabTitle)) {
                        tabExists = true;
                        break;
                    }
                }
                
                if (!tabExists) {
                    parentView.addTab(tabTitle, detailPerDayTab);
                } else {
                    parentView.selectTabByTitle(tabTitle);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
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
                protected void paintBorder(Graphics g) {}

                @Override
                public boolean isOpaque() {
                    return false;
                }
            };
            day.setBounds(x, y, 100, 90);
            day.setOpaque(true);
            calendarArea.add(day);
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
        
        JPanel notifications = new JPanel();
        notifications.setLayout(null);
        notifications.setBounds(920, 0, 360, 720); 
        notifications.setOpaque(true);
        notifications.setBackground(Color.white);
        notifications.add(new NextPaymentsPanel(viewController));
        notifications.add(new NextIncomesPanel(viewController));
        add(notifications);
        
        parentView.setViewCalendar(viewCalendar);
        parentView.paintFOsInCalendarTab(viewCalendar);
        parentView.paintINsInCalendarTab(viewCalendar);
    }
    
    @Override
    public void formOpen() {
        super.formOpen();
        revalidate();
        repaint();
    }
    
    @Override
    public boolean formClose() {
        return false;
    }
    
    public JPanel[] getViewCalendar() {
        return viewCalendar;
    }
}