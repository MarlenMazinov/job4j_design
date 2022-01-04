package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        Map<Integer, String> map = new HashMap<>();
        current.forEach(elem -> map.put(elem.getId(), elem.getName()));
        previous.forEach(elem -> {
            if (map.containsKey(elem.getId())) {
                if (!elem.getName().equals(map.get(elem.getId()))) {
                    rsl.setChanged(rsl.getChanged() + 1);
                }
                map.remove(elem.getId());
            } else {
                rsl.setDeleted(rsl.getDeleted() + 1);
            }
        });
        rsl.setAdded(rsl.getAdded() + map.size());
        return rsl;
    }
}