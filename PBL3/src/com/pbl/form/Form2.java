/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbl.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class Form2 extends JPanel{
    private static Form2 currentInstance;
    private MainForm mainForm;
    private JPanel tasksPanel;
    public Form2(MainForm frame) {
       
        this(frame, LocalDate.now());
    }
    public static Form2 getInstance() {
        return currentInstance;
    }
    
    
     public Form2(MainForm frame, LocalDate selectedDay) {
        this.mainForm = frame;
         currentInstance = this;
      
        setPreferredSize(new Dimension(900, 500));
        setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        setLayout(new GridBagLayout());
        setBackground(new Color(199, 215, 251));
        

        LocalDate date = LocalDate.now();
        String dateString = date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, EEEE"));
        JLabel todayLabel = new JLabel(dateString);
        todayLabel.setFont(new Font("Helvetica", Font.BOLD, 40));
        todayLabel.setForeground(Color.decode("#4D2508"));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(5, 15, 5, 15);
        add(todayLabel, constraints);

        // Add calendar to the left side of the panel
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        
        tasksPanel = new JPanel(new BorderLayout());
           Color backgroundColor = new Color(199, 215, 251);
        tasksPanel.setBackground(backgroundColor);
        add(new Calendar(date.getYear(), date.getMonthValue(), selectedDay, frame, this), constraints);
        
        

        // Add task to the right side of the panel
        constraints.gridx = 1;
        constraints.gridy = 1;
          add(tasksPanel, constraints);
    }
      public void updateTasks(LocalDate selectedDay) {
        tasksPanel.removeAll();
        tasksPanel.add(new Tasks(selectedDay, mainForm, tasksPanel), BorderLayout.CENTER);
        tasksPanel.revalidate();
        tasksPanel.repaint();
    }
       public void onTaskUpdated(LocalDate date) {
        updateTasks(date);  
    }
}
