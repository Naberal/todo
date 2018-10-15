package view;

import model.Item;
import service.Service;
import service.ServiceImp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
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
        contentPane.add(table(), BorderLayout.NORTH);
        contentPane.add(addField(), BorderLayout.CENTER);
        contentPane.add(addButton(addField()), BorderLayout.SOUTH);
    }

    private JScrollPane table() {
        Class Item = Item.class;
        Field[] declaredFields = Item.getDeclaredFields();
        Object[] column = new Object[declaredFields.length + 1];
        for (int i = 1; i < declaredFields.length; i++) {
            column[i - 1] = declaredFields[i].getName();
        }
        column[declaredFields.length - 1] = "update";
        column[declaredFields.length] = "delete";
        Object[][] rows = new Object[all.size()][(declaredFields.length + 1)];
        for (int i = 0; i < all.size(); i++) {
            rows[i] = new Object[]{all.get(i).getItem(), "update", "delete"};
        }
        JTable table = new JTable(rows, column);//todo
        table.setAutoCreateColumnsFromModel(true);

        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
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
                service.save(new Item(textField.getText()));//todo
                contentPane.removeAll();
                paint();
                contentPane.revalidate();
                contentPane.repaint();
            }
        });
        return button;
    }
}
