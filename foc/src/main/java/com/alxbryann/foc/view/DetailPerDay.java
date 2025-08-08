package com.alxbryann.foc.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author alxbryann
 */
public class DetailPerDay extends JFrame{
    
    private ViewController viewController;
    
    public DetailPerDay(ViewController viewController) {
        this.viewController = viewController;
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(null);
        setBounds(45, 50, 1280, 720);
        setBackground(new Color(204, 168, 109, 255));

        JLabel titleNextIncomes = new JLabel("Next Incomes");
        titleNextIncomes.setFont(new Font("Lexend", Font.PLAIN, 22));
        titleNextIncomes.setBounds(55, 10, 180, 30);
        titleNextIncomes.setForeground(Color.BLACK);

        //incomesContainer = new JPanel();
        //incomesContainer.setLayout(null);
        //incomesContainer.setBounds(20, 30, 300, 130);
        //incomesContainer.setOpaque(false);

        //add(incomesContainer);
        add(titleNextIncomes);

        RoundedButton show = new RoundedButton("Show more", 30);
        show.setBounds(50, 175, 180, 30);
        show.setBackground(new Color(86, 60, 16));
        show.setForeground(Color.WHITE);
        show.setFont(new Font("Lexend", Font.PLAIN, 15));
        add(show);

        RoundedButton addNewIncomes = new RoundedButton("Add new incomes", 30);
        addNewIncomes.setBounds(50, 210, 180, 30);
        addNewIncomes.setBackground(new Color(86, 60, 16));
        addNewIncomes.setForeground(Color.WHITE);
        addNewIncomes.setFont(new Font("Lexend", Font.PLAIN, 15));
        //addNewIncomes.addActionListener(e -> createNewIncomeDialog());
        add(addNewIncomes);
    }

}
