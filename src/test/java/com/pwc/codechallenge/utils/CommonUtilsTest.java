package com.pwc.codechallenge.utils;

import com.pwc.codechallenge.entity.AddressBook;
import com.pwc.codechallenge.entity.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class CommonUtilsTest {

    AddressBook addressBook;

    @Before
    public void setup() {
        List<Person> personList = new ArrayList<>();
        addressBook = new AddressBook(1, "Madhukar" , personList);
        personList.add(new Person(1, "Madhukar", "0447500439", addressBook));
        personList.add(new Person(2, "Sehwag", "0447500434", addressBook));
        personList.add(new Person(3, "Tendulkar", "0447500433", addressBook));
        personList.add(new Person(4, "Sachin", "0447500432", addressBook));
    }

    @Test
    public void testBasic_getPersonDetailsFromAddressBook() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "Madhukar", "0447500439", addressBook));
        addressBook = new AddressBook(1, "Madhukar" , personList);
        Person[] person = CommonUtils.getPersonDetailsFromAddressBook(addressBook, Person.BY_NAME);
        assertEquals(person.length, 1);
    }

    @Test
    public void testNull_getPersonDetailsFromAddressBook() {
        Person[] person = CommonUtils.getPersonDetailsFromAddressBook(null, Person.BY_NAME);
        assertNull(person);
    }

    @Test
    public void testEmpty_getPersonDetailsFromAddressBook() {
        List<Person> personList = new ArrayList<>();
        addressBook = new AddressBook(1, "Madhukar" , personList);
        Person[] person = CommonUtils.getPersonDetailsFromAddressBook(addressBook, Person.BY_NAME);
        assertNull(person);
    }

    @Test
    public void testSortByName_getPersonDetailsFromAddressBook() {
        Person[] person = CommonUtils.getPersonDetailsFromAddressBook(addressBook, Person.BY_NAME);
        assertEquals(person.length, 4);
        assertEquals(person[0].getName(), "Madhukar");
        assertEquals(person[1].getName(), "Sachin");
        assertEquals(person[2].getName(), "Sehwag");
        assertEquals(person[3].getName(), "Tendulkar");
    }

    @Test
    public void testSortByNumber_getPersonDetailsFromAddressBook() {
        Person[] person = CommonUtils.getPersonDetailsFromAddressBook(addressBook, Person.BY_PHONE);
        assertEquals(person.length, 4);
        assertEquals(person[0].getName(), "Sachin");
        assertEquals(person[1].getName(), "Tendulkar");
        assertEquals(person[2].getName(), "Sehwag");
        assertEquals(person[3].getName(), "Madhukar");
    }
}
