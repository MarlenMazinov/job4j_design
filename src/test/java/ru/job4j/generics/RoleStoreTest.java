package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRolenameIsVillian() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Villian"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Villian"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Villian"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRolenameIsVillian() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Villian"));
        store.add(new Role("1", "GoodGuy"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Villian"));
    }

    @Test
    public void whenReplaceThenRolenameIsGoodGuy() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Villian"));
        store.replace("1", new Role("1", "GoodGuy"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("GoodGuy"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Villian"));
        store.replace("10", new Role("10", "GoodGuy"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Villian"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Villian"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRolenameIsVillian() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Villian"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Villian"));
    }
}
