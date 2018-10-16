package view;

import controller.Controller;
import model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * User Interface class
 * At first time use swing
 *
 * @author Khmelyar Volodymer
 */
public class Window {
    private JFrame frame = new JFrame("todo");
    private Container contentPane = frame.getContentPane();
    private Controller controller = new Controller();
    private List<Item> all = controller.getAll();

    public void frame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        add();
        for (int i = 0; i < all.size(); i++) {
            table(all.get(i));
        }
        frame.setVisible(true);
        frame.setLocation(400, 100);
        frame.setSize(500, 500);
    }

    private void table(Item item) {
        JTextField textField = new JTextField(item.getItem());
        textField.setAutoscrolls(true);
        JButton update = new JButton("update");
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                item.setItem(textField.getText());
                boolean update = controller.update(item);
                if (update) {
                    textField.setText(item.getItem());
                }
            }
        });
        JButton delete = new JButton("delete");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.delete(item);
                contentPane.remove(textField);
                contentPane.remove(update);
                contentPane.remove(delete);
                updateFrame();
            }
        });
        contentPane.add(textField);
        contentPane.add(update);
        contentPane.add(delete);
    }

    private void add() {
        JTextField textField = new JTextField("Write new item");
        textField.setAutoscrolls(true);
        JButton button = new JButton("Save item");
        button.setAutoscrolls(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Item item = new Item(textField.getText());
                boolean save = controller.save(item);
                textField.setText("Write new item");
                if (save) {
                    table(item);
                }
                updateFrame();
            }
        });
        contentPane.add(textField);
        contentPane.add(button);
    }

    private void updateFrame() {
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }
}
