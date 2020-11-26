package com.pwc.codechallenge.service;

import com.pwc.codechallenge.entity.AddressBook;
import com.pwc.codechallenge.entity.Person;
import com.pwc.codechallenge.repository.AddressBookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddressBookServiceTest {

    @Mock
    AddressBookRepository addressBookRepository;

    AddressBookService addressBookService;

    @Before
    public void setup() {
        List<Person> personList = new ArrayList<>();
        AddressBook addressBook = new AddressBook(1, "Madhukar" , personList);
        personList.add(new Person(1, "Madhukar", "0447500439", addressBook));
        personList.add(new Person(2, "Sehwag", "0447500434", addressBook));
        personList.add(new Person(3, "Tendulkar", "0147500433", addressBook));
        personList.add(new Person(4, "Sachin", "0247500432", addressBook));

        List<Person> personList2 = new ArrayList<>();
        AddressBook addressBook2 = new AddressBook(1, "Maddy" , personList2);
        personList2.add(new Person(1, "Allan", "0447500439", addressBook2));
        personList2.add(new Person(2, "Warner", "0447500434", addressBook2));
        personList2.add(new Person(3, "Tendulkar", "0447500433", addressBook2));
        personList2.add(new Person(4, "Sachin", "0447500432", addressBook2));
        MockitoAnnotations.openMocks(this);
        addressBookService = new AddressBookService(addressBookRepository);
        when(addressBookRepository.findAddressBookByName("test1")).thenReturn(addressBook);
        when(addressBookRepository.findAddressBookByName("test2")).thenReturn(addressBook2);
        when(addressBookRepository.findAddressBookByName("test3")).thenReturn(null);
        when(addressBookRepository.findAddressBookByName("test4")).thenReturn(new AddressBook());

    }

    @Test
    public void testBasic_mergeAddressBooksByName() {
        AddressBook addressBook = addressBookService.mergeAddressBooks("test1", "test2", Person.BY_NAME);
        List<Person> personList = addressBook.getPersons();
        assertEquals(addressBook.getPersons().size(), 4);
        assertEquals(personList.get(0).getName(), "Allan");
        assertEquals(personList.get(1).getName(), "Madhukar");
        assertEquals(personList.get(2).getName(), "Sehwag");
        assertEquals(personList.get(3).getName(), "Warner");
    }

    @Test
    public void testBasic_mergeAddressBooksByPhonenum() {
        AddressBook addressBook = addressBookService.mergeAddressBooks("test1", "test2", Person.BY_PHONE);
        List<Person> personList = addressBook.getPersons();
        assertEquals(addressBook.getPersons().size(), 4);
        assertEquals(personList.get(0).getName(), "Tendulkar");
        assertEquals(personList.get(1).getName(), "Sachin");
        assertEquals(personList.get(2).getName(), "Sachin");
        assertEquals(personList.get(3).getName(), "Tendulkar");
    }

    @Test
    public void testNullAddress_mergeAddressBooksByPhonenum() {
        AddressBook addressBook = addressBookService.mergeAddressBooks("test3", "test2", Person.BY_NAME);
        List<Person> personList = addressBook.getPersons();
        assertEquals(addressBook.getPersons().size(), 4);
        assertEquals(personList.get(0).getName(), "Allan");
        assertEquals(personList.get(1).getName(), "Sachin");
        assertEquals(personList.get(2).getName(), "Tendulkar");
        assertEquals(personList.get(3).getName(), "Warner");
    }

    @Test
    public void testNullAddress2_mergeAddressBooksByPhonenum() {
        AddressBook addressBook = addressBookService.mergeAddressBooks("test2", "test3", Person.BY_NAME);
        List<Person> personList = addressBook.getPersons();
        assertEquals(addressBook.getPersons().size(), 4);
        assertEquals(personList.get(0).getName(), "Allan");
        assertEquals(personList.get(1).getName(), "Sachin");
        assertEquals(personList.get(2).getName(), "Tendulkar");
        assertEquals(personList.get(3).getName(), "Warner");
    }

    @Test
    public void testEmptyAddress_mergeAddressBooksByPhonenum() {
        AddressBook addressBook = addressBookService.mergeAddressBooks("test4", "test2", Person.BY_NAME);
        List<Person> personList = addressBook.getPersons();
        assertEquals(addressBook.getPersons().size(), 4);
        assertEquals(personList.get(0).getName(), "Allan");
        assertEquals(personList.get(1).getName(), "Sachin");
        assertEquals(personList.get(2).getName(), "Tendulkar");
        assertEquals(personList.get(3).getName(), "Warner");
    }

    @Test
    public void testBothEmpty_mergeAddressBooksByPhonenum() {
        AddressBook addressBook = addressBookService.mergeAddressBooks("test4", "test4", Person.BY_NAME);
        List<Person> personList = addressBook.getPersons();
        assertNull(personList);
    }

    @Test
    public void testBothNull_mergeAddressBooksByPhonenum() {
        AddressBook addressBook = addressBookService.mergeAddressBooks("test3", "test3", Person.BY_NAME);
        assertNull(addressBook);
    }
}
