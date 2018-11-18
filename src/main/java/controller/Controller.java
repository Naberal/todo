package controller;

import model.Item;
import service.Service;
import service.ServiceImp;

import java.util.List;

/**
 * Class controller who checks the data while saving and updating to their uniqueness.
 * Other methods are intermediaries.
 *
 * @author Khmelyar Volodymyr
 */
public class Controller {
    private Service service;

    public Controller(Service service) {
        if (service == null) {
            this.service = new ServiceImp();
        }
        this.service = service;
    }

    /**
     * Mediator method for transferring data list.
     *
     * @return data list
     */
    public List<Item> getAll() {
        List<Item> list = service.getAll();
        return list;
    }

    /**
     * Method of checking input data for uniqueness
     *
     * @param item-input data
     * @return true if data unique, false if data not unique
     */
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

    /**
     * Method of checking data for uniqueness when updated
     *
     * @param item-updated data
     * @return true if data unique, false if data not unique
     */
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

    /**
     * Mediator method in data deletion
     *
     * @param item - data to delete
     */
    public void delete(Item item) {
        service.delete(item);
    }
}
