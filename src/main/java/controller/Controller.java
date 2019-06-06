package controller;

import model.Item;
import service.ServiceImp;

import java.util.List;

public class Controller {
    private ServiceImp service = new ServiceImp();

    public List<Item> getAll() {
        List<Item> list = service.getAll();
        return list;
    }

    public boolean save(Item item) {
        for (Item i : getAll()) {
            if (item.getItem().equals(i.getItem())) {
                return false;
            } else {
                service.save(item);
            }
        }
        return true;
    }

    public boolean update(Item item) {
        for (Item i : getAll()) {
            if (item.getItem().equals(i.getItem())) {
                return true;
            } else {
                service.update(item);

            }
        }
        return false;
    }

    public void delete(Item item) {
        service.delete(item);
    }
}
