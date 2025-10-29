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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
    // layout constants
    int calendarAreaWidth = 850;
    int calendarAreaHeight = 550;
    int tabHeight = 720;
    // notifications panel geometry (we'll use these values so we can avoid overlap)
    int notificationsX = 920;
    int notificationsWidth = 360;
    int sideGap = 20; // gap between calendar area and other UI elements
    int leftMargin = 30;

    // available horizontal area to place calendarArea without overlapping notifications
    int availableLeft = leftMargin;
    int availableRight = notificationsX - sideGap;
    int availableWidth = Math.max(0, availableRight - availableLeft);

    // if calendarArea is wider than available space, shrink it to fit (keeping some padding)
    if (calendarAreaWidth > availableWidth) {
        calendarAreaWidth = Math.max(100, availableWidth - 10);
    }

    // center calendarArea inside the available left region and center vertically in the tab
    int calendarAreaX = availableLeft + (availableWidth - calendarAreaWidth) / 2;
    // nudge up slightly so it feels visually centered (negative moves up)
    int verticalBias = -20;
    int calendarAreaY = Math.max(20, (tabHeight - calendarAreaHeight) / 2 + verticalBias);
    calendarArea.setBounds(calendarAreaX, calendarAreaY, calendarAreaWidth, calendarAreaHeight);
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

    // compute centered starting X and Y inside calendarArea for the day-grid
    int dayWidth = 100;
    int dayHeight = 90;
    int hSpacing = 15; // horizontal space between day panels
    int vSpacing = 15; // vertical space between rows
    int cols = 7;
    int totalGridWidth = cols * dayWidth + (cols - 1) * hSpacing;
    int startX = (calendarArea.getWidth() - totalGridWidth) / 2;
    int rows = (viewCalendar.length + cols - 1) / cols;
    int totalGridHeight = rows * dayHeight + (rows - 1) * vSpacing;
    int startY = (calendarArea.getHeight() - totalGridHeight) / 2;
    int x = startX, y = startY;
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
            day.setBounds(x, y, dayWidth, dayHeight);
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

            x += dayWidth + hSpacing;
            if (i % cols == 0 && i != 0) {
                x = startX;
                y += dayHeight + vSpacing;
            }
        }
        
    JPanel notifications = new JPanel();
    notifications.setLayout(null);
    // Position notifications using the constants so calendarArea won't overlap
    // keep notifications full tab height (do not move/resize it)
    notifications.setBounds(notificationsX, 0, notificationsWidth, tabHeight);
    notifications.setOpaque(true);
    notifications.setBackground(Color.white);

    // Add the NextTransactionsPanel instance and align it vertically with calendarArea
    NextTransactionsPanel nextPanel = new NextTransactionsPanel(viewController);
    // inner padding inside notifications panel
    int notifInnerLeft = 30;
    int notifInnerRight = 30;
    int nextPanelWidth = notificationsWidth - notifInnerLeft - notifInnerRight; // 360 - 60 = 300
    // bottom padding inside nextPanel so its content won't touch the bottom edge
    int nextPanelBottomPadding = 20;
    // button layout constants (used to compute nextPanel height so the last button aligns with calendarArea bottom)
    // reduce space between the two action buttons for a tighter stack
    int buttonGap = 8;
    double buttonHeightDouble = 62.45;
    int preferredButtonHeight = (int) Math.round(buttonHeightDouble);
    // compute group height (two buttons stacked) and center that block vertically to the calendar
    int buttonGapLocal = buttonGap; // reuse
    int groupHeight = 2 * preferredButtonHeight + buttonGapLocal;
    // desired Y to center the group in calendarArea (not used when aligning to calendar bottom)
    // align top of nextPanel with the calendarArea's top (calendarAreaY is in parent coords; notifications Y is 0)
    int nextPanelY = Math.max(10, calendarAreaY);
    // ensure the group fits inside the notifications area vertically; if not, reduce button height
    int availableForGroup = tabHeight - nextPanelY - 20; // leave small margin
    int buttonHeight;
    if (groupHeight > availableForGroup) {
        // reduce button height proportionally
        int reducedButtonHeight = Math.max(40, (availableForGroup - buttonGapLocal) / 2);
        buttonHeight = reducedButtonHeight;
        groupHeight = 2 * buttonHeight + buttonGapLocal;
    } else {
        buttonHeight = preferredButtonHeight;
    }
    // set nextPanel height to the size requested (408) and ensure it fits inside notifications
    int requestedNextPanelHeight = 408;
    int minNextPanelHeight = 100;
    int maxNextPanelHeight = Math.max(minNextPanelHeight, tabHeight - nextPanelY - (2 * buttonGap + 2 * buttonHeight) - 10);
    int nextPanelHeight = Math.max(minNextPanelHeight, Math.min(requestedNextPanelHeight, maxNextPanelHeight));
    nextPanel.setBounds(notifInnerLeft, nextPanelY, nextPanelWidth, nextPanelHeight);
    // apply internal bottom padding
    nextPanel.setBorder(new EmptyBorder(0, 0, nextPanelBottomPadding, 0));
    notifications.add(nextPanel);

    // Add action buttons to container (outside NextTransactionsPanel), aligned with nextPanel
    JButton addIncomeBtn = new JButton("Add new Income");
    // Place buttons directly below NextTransactionsPanel and centered within the notifications inner width
    int buttonWidth = nextPanelWidth - 20; // leave 10px padding each side
    int buttonX = notifInnerLeft + 10;
    // position the buttons so that the 'Add Expense' button aligns with the bottom of calendarArea
    int addExpenseY = calendarAreaY + calendarAreaHeight - buttonHeight;
    int addIncomeY = addExpenseY - buttonHeight - buttonGapLocal;

    // if the computed addIncomeY would overlap the nextPanel, shrink nextPanelHeight to make room
    // increase minimum gap between nextPanel bottom and the top button so there's
    // more separation between the transactions list and the buttons
    int minGapBetweenNextPanelAndButtons = 30;
    int overlap = (nextPanelY + nextPanelHeight + minGapBetweenNextPanelAndButtons) - addIncomeY;
    if (overlap > 0) {
        int newNextPanelHeight = Math.max(minNextPanelHeight, nextPanelHeight - overlap);
        nextPanelHeight = newNextPanelHeight;
        nextPanel.setBounds(notifInnerLeft, nextPanelY, nextPanelWidth, nextPanelHeight);
        // recompute add positions after shrinking
        addExpenseY = calendarAreaY + calendarAreaHeight - buttonHeight;
        addIncomeY = addExpenseY - buttonHeight - buttonGapLocal;
    }

    addIncomeBtn.setBounds(buttonX, addIncomeY, buttonWidth, buttonHeight);
    addIncomeBtn.setFont(new Font("Lexend", Font.PLAIN, 16));
    addIncomeBtn.setForeground(Color.BLACK);
    // Aplicar estilo FlatLaf
    FlatLafStyleManager.applySuccessButtonStyle(addIncomeBtn);
    addIncomeBtn.addActionListener(e -> nextPanel.openCreateTransactionDialog(false));
    notifications.add(addIncomeBtn);

    JButton addExpenseBtn = new JButton("Add new Expense");
    addExpenseBtn.setBounds(buttonX, addExpenseY, buttonWidth, buttonHeight);
    addExpenseBtn.setFont(new Font("Lexend", Font.PLAIN, 16));
    addExpenseBtn.setForeground(Color.BLACK);
    // Aplicar estilo FlatLaf
    FlatLafStyleManager.applyDangerButtonStyle(addExpenseBtn);
    addExpenseBtn.addActionListener(e -> nextPanel.openCreateTransactionDialog(true));
    notifications.add(addExpenseBtn);

    // Ensure buttons are on top visually
    notifications.setComponentZOrder(addExpenseBtn, 0);
    notifications.setComponentZOrder(addIncomeBtn, 0);
    notifications.setComponentZOrder(nextPanel, 2);

    add(notifications);
        
    parentView.setViewCalendar(viewCalendar);
        parentView.paintTransactionsInCalendarTab(viewCalendar);
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