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
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        table();
        add();
        frame.setVisible(true);
        frame.setLocation(400, 100);
        frame.setSize(500, 500);
    }

    private void table() {
        for (int i = 0; i < all.size(); i++) {
            Item item = all.get(i);
            JTextField textField = new JTextField(item.getItem());
            textField.setAutoscrolls(true);
            JButton update = new JButton("update");
            update.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    item.setItem(textField.getText());
                    service.update(item);
                }
            });
            JButton delete = new JButton("delete");
            delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    service.delete(item);
                    contentPane.remove(textField);
                    contentPane.remove(update);
                    contentPane.remove(delete);
                    frame.invalidate();
                    frame.validate();
                    frame.repaint();
                }
            });
            contentPane.add(textField);
            contentPane.add(update);
            contentPane.add(delete);
        }
    }

    private void add() {
        JTextField textField = new JTextField("Write new item");
        textField.setAutoscrolls(true);
        JButton button = new JButton("Save item");
        button.setAutoscrolls(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Item item = new Item(textField.getText());
                service.save(item);
                contentPane.removeAll();
                table();
                add();
                frame.invalidate();
                frame.validate();
                frame.repaint();
            }
        });
        contentPane.add(textField);
        contentPane.add(button);
    }
}
