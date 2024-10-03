package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bieda_To_Do_Gui extends JFrame implements ActionListener {

    private JPanel taskComponentPanel;

    public bieda_To_Do_Gui() {
        super("Bieda To Do Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(Common_Constants.GUI_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        addGuiComponent();
    }

    private void addGuiComponent() {
        // Banner text
        JLabel bannerLabel = new JLabel("Bieda To Do list");
        bannerLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        bannerLabel.setBounds(
                (Common_Constants.GUI_SIZE.width - bannerLabel.getPreferredSize().width) / 2,
                15,
                Common_Constants.BANNER_SIZE.width,
                Common_Constants.BANNER_SIZE.height
        );

        // Task panel
        // taskPanel will act as the container for the taskComponentPanel
        // taskComponentPanel will store all of the taskComponents
        JPanel taskPanel = new JPanel();

        // taskComponentPanel
        taskComponentPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);

        // Add scrolling to the task panel
        JScrollPane scrollPane = getjScrollPane(taskPanel);

        // Change the speed of the scroll bar
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);

        // Add task button
        JButton addTaskButton = new JButton("Add task");
        bannerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addTaskButton.setBounds(
                -5,
                Common_Constants.GUI_SIZE.height - 88,
                Common_Constants.ADDTASK_BUTTON_SIZE.width,
                Common_Constants.ADDTASK_BUTTON_SIZE.height
        );
        addTaskButton.addActionListener(this);

        // Add frame
        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);
    }

    private static JScrollPane getjScrollPane(JPanel taskPanel) {
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(
                8,
                70,
                Common_Constants.TASKPANEL_SIZE.width,
                Common_Constants.TASKPANEL_SIZE.height
        );
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setMaximumSize(Common_Constants.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equalsIgnoreCase("Add Task")) {
            // Create task component
            Task_Component taskComponent = new Task_Component(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            // Make the previous task appear disabled
            if (taskComponentPanel.getComponentCount() > 1) {
                Task_Component previousTask = (Task_Component) taskComponentPanel.getComponent(
                        taskComponentPanel.getComponentCount() - 2);
                previousTask.getTaskField().setBackground(null);
            }

            // Make the task field request focus after creation
            taskComponent.getTaskField().requestFocus();
            repaint();
            revalidate();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new bieda_To_Do_Gui().setVisible(true);
        });
    }
}
