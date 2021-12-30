package ru.job4j.collection.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User userFirst = new User("Alex", 2,
                new GregorianCalendar(2017, Calendar.JANUARY, 25));
        User userSecond = new User("Alex", 2,
                new GregorianCalendar(2017, Calendar.JANUARY, 25));
        Map<User, Object> map = new HashMap<>();
        map.put(userFirst, new Object());
        map.put(userSecond, new Object());
        System.out.println(map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", children=" + children
                + ", birthday=" + birthday.getTime() + '}';
    }
}
