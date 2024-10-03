package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Task_Component extends JPanel implements ActionListener {
    private final JCheckBox checkBox;
    private final JTextPane taskField;

    public JTextPane getTaskField() {
        return taskField;
    }

    //this panel is used so that we can make updates to the task component panel detailing tasks
    private final JPanel parentPanel;

    public Task_Component(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        //task field
        taskField = new JTextPane();
        taskField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taskField.setPreferredSize(Common_Constants.TASKFILD_SIZE);
        taskField.setContentType("text/html");
        taskField.addFocusListener(new FocusListener() {
            //indicate witch task field is currently being edited
            @Override
            public void focusGained(FocusEvent e) {
                taskField.setBackground(Color.WHITE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                taskField.setBackground(null);
            }
        });

        //checkbox
        checkBox = new JCheckBox();
        checkBox.setPreferredSize(Common_Constants.CHECKBOX_SIZE);
        checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkBox.addActionListener(this);

        //delete button
        JButton deleteButton = new JButton("X");
        deleteButton.setPreferredSize(Common_Constants.DELETE_BUTTON_SIZE);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(this);

        //add to this taskcomponent
        add(checkBox);
        add(taskField);
        add(deleteButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkBox.isSelected()){
            //replace all html tags to empty string to grab the main text
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            //add strikethrough text
            taskField.setText("<html><s>" + taskText + "</s></html>");
        } else if (!checkBox.isSelected()) {
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            taskField.setText(taskText);
        }

        if(e.getActionCommand().equalsIgnoreCase("X")){
            //delete this comennt frome the parent panel
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();

        }
    }
}