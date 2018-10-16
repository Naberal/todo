package view;

import model.Item;
import service.Service;
import service.ServiceImp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Window {
    private JFrame frame = new JFrame("todo");
    private Container contentPane = frame.getContentPane();
    Service<Item> service = new ServiceImp();
    List<Item> all = service.getAll();

    public void frame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paint();
        frame.setVisible(true);
        frame.setLocation(400, 100);
        frame.setSize(500, 500);
    }

    private void paint() {
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        table();
        contentPane.add(addField());
        contentPane.add(addButton(addField()));
    }

    private void table() {
        for (int i = 0; i < all.size(); i++) {
            Item item = all.get(i);
            JTextField textField = new JTextField(item.getItem());
            textField.setAutoscrolls(true);
            JButton button = new JButton("update");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    item.setItem(textField.getText());
                    service.update(item);
                }
            });
            JButton button1 = new JButton("delete");
            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    service.delete(item);
                }
            });
            contentPane.add(textField);
            contentPane.add(button);
            contentPane.add(button1);
        }
    }

    private JTextField addField() {
        JTextField textField = new JTextField("Write new item");
        textField.setAutoscrolls(true);
        return textField;
    }

    private JButton addButton(JTextField textField) {
        JButton button = new JButton("Save item");
        button.setAutoscrolls(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                service.save(new Item(textField.getText()));
                contentPane.removeAll();
                paint();
                contentPane.revalidate();
                contentPane.repaint();
            }
        });
        return button;
    }
}
