package com.alxbryann.foc.view;

import com.alxbryann.foc.view.tabbed.PanelTabbed;
import com.alxbryann.foc.view.tabbed.TabbedForm;
import com.alxbryann.foc.view.tabbed.TabbedItem;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class CustomTitleBar extends JPanel {

    private static final int TITLE_BAR_HEIGHT = 40;
    private static final Color TITLE_BAR_COLOR = Color.BLACK;
    private static final Color BUTTON_HOVER_COLOR = new Color(60, 120, 100);
    private static final Color CLOSE_BUTTON_HOVER_COLOR = new Color(232, 17, 35);

    private JFrame parentFrame;
    private PanelTabbed panelTabbed;
    private JPanel body;
    private JButton drawerButton;
    private JButton minimizeButton;
    private JButton closeButton;
    private JLabel titleLabel;
    private JScrollPane tabbedScrollPane;

    private Point initialClick;
    private boolean isDragging = false;

    private final int LIMIT = 6;
    private final boolean REMOVE_WHEN_LIMIT = false;

    public CustomTitleBar(JFrame frame, JPanel bodyPanel) {
        this.parentFrame = frame;
        this.body = bodyPanel;

        initializeComponents();
        setupLayout();
        setupEventListeners();
        setupDragFunctionality();
    }

    private void initializeComponents() {
        setPreferredSize(new Dimension(0, TITLE_BAR_HEIGHT));
        setBackground(TITLE_BAR_COLOR);
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(TITLE_BAR_COLOR);
        leftPanel.setOpaque(false);

        JPanel drawerLogoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        drawerLogoPanel.setBackground(TITLE_BAR_COLOR);
        drawerLogoPanel.setOpaque(false);

        drawerButton = createDrawerButton();

        JLabel logoLabel = createLogoLabel();

        drawerLogoPanel.add(logoLabel);
        drawerLogoPanel.add(drawerButton);

        panelTabbed = new PanelTabbed();
        panelTabbed.setBackground(TITLE_BAR_COLOR);
        panelTabbed.putClientProperty(FlatClientProperties.STYLE,
                "background:" + String.format("#%06X", TITLE_BAR_COLOR.getRGB() & 0xFFFFFF) + ";" +
                        "foreground:white;" +
                        "margin:0,0,0,0");

        tabbedScrollPane = createTabbedScrollPane(panelTabbed);

        leftPanel.add(drawerLogoPanel, BorderLayout.WEST);
        leftPanel.add(tabbedScrollPane, BorderLayout.CENTER);

        titleLabel = new JLabel("");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JPanel rightPanel = createWindowControlsPanel();

        add(leftPanel, BorderLayout.WEST);
        add(titleLabel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    private void setupLayout() {
        // Eliminar padding inferior para que las tabs queden pegadas abajo
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    private void setupEventListeners() {
        // Configurar listeners para los botones de control
        minimizeButton.addActionListener(e -> {
            minimizeWindow();
        });
        closeButton.addActionListener(e -> {
            closeWindow();
        });
    }

    private void setupDragFunctionality() {
        MouseAdapter dragAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    initialClick = e.getPoint();
                    isDragging = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDragging = false;
            }
        };

        MouseMotionAdapter dragMotionAdapter = new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging && initialClick != null) {
                    Point currentLocation = parentFrame.getLocation();
                    int newX = currentLocation.x + e.getX() - initialClick.x;
                    int newY = currentLocation.y + e.getY() - initialClick.y;
                    parentFrame.setLocation(newX, newY);
                }
            }
        };

        addMouseListener(dragAdapter);
        addMouseMotionListener(dragMotionAdapter);
        titleLabel.addMouseListener(dragAdapter);
        titleLabel.addMouseMotionListener(dragMotionAdapter);
    }

    private JButton createDrawerButton() {
        ImageIcon rawIcon = new ImageIcon(getClass().getResource("/img/sidebar_white.png"));
        Image scaledImage = rawIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon sideBarIdIcon = new ImageIcon(scaledImage);
        JButton button = new JButton();
        button.setIcon(sideBarIdIcon);
        button.setPreferredSize(new Dimension(40, TITLE_BAR_HEIGHT));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBackground(TITLE_BAR_COLOR);
        button.setForeground(Color.WHITE);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BUTTON_HOVER_COLOR);
                button.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setContentAreaFilled(false);
            }
        });

        button.addActionListener(e -> {
            Drawer.getInstance().showDrawer();
        });

        return button;
    }

    private JLabel createLogoLabel() {
        try {
            ImageIcon logoIcon = new ImageIcon(getClass().getClassLoader().getResource("img/logo.png"));

            Image img = logoIcon.getImage();
            Image scaledImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImg);

            JLabel logoLabel = new JLabel(scaledIcon);
            logoLabel.setPreferredSize(new Dimension(35, TITLE_BAR_HEIGHT));
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            logoLabel.setVerticalAlignment(SwingConstants.CENTER);

            return logoLabel;
        } catch (Exception e) {
            JLabel logoLabel = new JLabel("FOC");
            logoLabel.setPreferredSize(new Dimension(35, TITLE_BAR_HEIGHT));
            logoLabel.setForeground(Color.WHITE);
            logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            logoLabel.setVerticalAlignment(SwingConstants.CENTER);
            return logoLabel;
        }
    }

    private JScrollPane createTabbedScrollPane(Component component) {
        JScrollPane scroll = new JScrollPane(component);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setViewportBorder(BorderFactory.createEmptyBorder());
        scroll.setBackground(TITLE_BAR_COLOR);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getViewport().setBorder(null);

        scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scroll.getHorizontalScrollBar().setUnitIncrement(10);

        return scroll;
    }

    private JPanel createWindowControlsPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panel.setBackground(TITLE_BAR_COLOR);
        panel.setOpaque(false);

        // Botón minimizar
        minimizeButton = createWindowControlButton("−", BUTTON_HOVER_COLOR);
        minimizeButton.setToolTipText("Minimizar ventana");

        // Botón cerrar
        closeButton = createWindowControlButton("", CLOSE_BUTTON_HOVER_COLOR);
        // Establecer icono personalizado para el botón cerrar (recurso en /img/x.png)
        ImageIcon closeIcon = loadScaledIcon("/img/x.png", 40, 40);
        if (closeIcon != null) {
            closeButton.setIcon(closeIcon);
        } else {
            // Fallback al texto si el recurso no está disponible
            closeButton.setText("✕");
        }
        closeButton.setToolTipText("Cerrar aplicación");

        panel.add(minimizeButton);
        panel.add(closeButton);

        return panel;
    }

    private ImageIcon loadScaledIcon(String resourcePath, int width, int height) {
        try {
            java.net.URL url = getClass().getResource(resourcePath);
            if (url == null) {
                return null;
            }
            ImageIcon icon = new ImageIcon(url);
            Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } catch (Exception ex) {
            return null;
        }
    }

    private JButton createWindowControlButton(String text, Color hoverColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(50, TITLE_BAR_HEIGHT));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBackground(TITLE_BAR_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
                button.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setContentAreaFilled(false);
            }
        });

        return button;
    }

    private void minimizeWindow() {
        parentFrame.setState(JFrame.ICONIFIED);
    }

    private void closeWindow() {
        boolean canClose = true;
        for (Component comp : panelTabbed.getComponents()) {
            if (comp instanceof TabbedItem) {
                TabbedForm form = ((TabbedItem) comp).getComponent();
                if (!(form instanceof CalendarTab) && !form.formClose()) {
                    canClose = false;
                    break;
                }
            }
        }
        if (canClose) {
            parentFrame.dispose();
            System.exit(0);
        }
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    public String getTitle() {
        return titleLabel.getText();
    }

    public boolean addTab(String title, TabbedForm component) {
        if (LIMIT != -1 && panelTabbed.getComponentCount() >= LIMIT) {
            if (REMOVE_WHEN_LIMIT) {
                panelTabbed.remove(0);
            } else {
                return false;
            }
        }

        TabbedItem item = new TabbedItem(title, component);
        item.setTitleBar(this);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (Component comp : panelTabbed.getComponents()) {
                    if (comp instanceof TabbedItem && comp != item) {
                        ((TabbedItem) comp).setSelected(false);
                    }
                }
                showForm(item.getComponent());
            }
        });

        for (Component comp : panelTabbed.getComponents()) {
            if (comp instanceof TabbedItem) {
                ((TabbedItem) comp).setSelected(false);
            }
        }

        panelTabbed.addTab(item);
        showForm(component);
        item.setSelected(true);

        SwingUtilities.invokeLater(() -> {
            JScrollBar horizontalScrollBar = tabbedScrollPane.getHorizontalScrollBar();
            horizontalScrollBar.setValue(horizontalScrollBar.getMaximum());
        });

        return true;
    }

    public void removeTab(TabbedItem tab) {
        if (tab.getComponent().formClose()) {
            int index = panelTabbed.getComponentZOrder(tab);
            boolean removedCurrentView = index == getTabSelectedIndex();

            if (tab.isSelected()) {
                body.removeAll();
                body.revalidate();
                body.repaint();
            }

            panelTabbed.remove(tab);
            panelTabbed.revalidate();
            panelTabbed.repaint();

            if (removedCurrentView) {
                int selectedIndex = Math.min(index, panelTabbed.getComponentCount() - 1);
                if (selectedIndex >= 0) {
                    TabbedItem item = (TabbedItem) panelTabbed.getComponent(selectedIndex);
                    item.setSelected(true);
                    showForm(item.getComponent());
                }
            }
        }
    }

    public void removeTabAt(int index) {
        Component com = panelTabbed.getComponent(index);
        if (com instanceof TabbedItem) {
            removeTab((TabbedItem) com);
        }
    }

    public void removeTab(TabbedForm tab) {
        for (Component com : panelTabbed.getComponents()) {
            if (com instanceof TabbedItem) {
                TabbedForm form = ((TabbedItem) com).getComponent();
                if (form == tab) {
                    removeTab((TabbedItem) com);
                    break;
                }
            }
        }
    }

    public void removeAllTabbed() {
        panelTabbed.removeAll();
        panelTabbed.repaint();
        panelTabbed.revalidate();
        body.removeAll();
        body.revalidate();
        body.repaint();
    }

    public void showTabbed(boolean show) {
        tabbedScrollPane.setVisible(show);
        if (!show) {
            Drawer.getInstance().closeDrawer();
        }
    }

    public String[] getTabName() {
        List<String> list = new ArrayList<>();
        for (Component com : panelTabbed.getComponents()) {
            if (com instanceof TabbedItem) {
                String name = ((TabbedItem) com).getTabbedName();
                list.add(name);
            }
        }
        return list.toArray(new String[0]);
    }

    public int getTabSelectedIndex() {
        for (Component com : panelTabbed.getComponents()) {
            if (com instanceof TabbedItem) {
                if (((TabbedItem) com).isSelected()) {
                    return panelTabbed.getComponentZOrder(com);
                }
            }
        }
        return -1;
    }

    public void selectTabByTitle(String title) {
        // Primero deseleccionar todas las pestañas
        for (Component com : panelTabbed.getComponents()) {
            if (com instanceof TabbedItem) {
                ((TabbedItem) com).setSelected(false);
            }
        }

        // Luego seleccionar la pestaña con el título específico
        for (Component com : panelTabbed.getComponents()) {
            if (com instanceof TabbedItem) {
                TabbedItem item = (TabbedItem) com;
                if (item.getTabbedName().equals(title)) {
                    item.setSelected(true);
                    showForm(item.getComponent());
                    break;
                }
            }
        }
    }

    public void showForm(TabbedForm component) {
        body.removeAll();
        body.add(component);
        body.repaint();
        body.revalidate();
        panelTabbed.repaint();
        panelTabbed.revalidate();
        component.formOpen();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Pintar fondo de la barra de título
        g2d.setColor(TITLE_BAR_COLOR);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
    }
}