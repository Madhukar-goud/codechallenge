package com.pwc.codechallenge.controller;

import com.pwc.codechallenge.entity.AddressBook;
import com.pwc.codechallenge.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddressBookControllerTest extends AbstractTest {

    @Test
    public void a_test_addAddressBook() throws Exception {
        String uri = "/addAddressBook";
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

        String inputJson = super.mapToJson(addressBook);
        log.info("URL ==> " + uri);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        AddressBook addressBookResponse = super.mapFromJson(content, AddressBook.class);
        List<Person> personListResponse = addressBookResponse.getPersons();

        assertEquals(addressBookResponse.getPersons().size(), 4);
        assertEquals(personListResponse.get(0).getName(), "Madhukar");
        assertEquals(personListResponse.get(1).getName(), "Sehwag");
        assertEquals(personListResponse.get(2).getName(), "Tendulkar");
        assertEquals(personListResponse.get(3).getName(), "Sachin");

        inputJson = super.mapToJson(addressBook2);
        mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
    }

    @Test
    @Sql(scripts = {"/testdata.sql"})
    public void b_test_getUniqueNamesFromAddressBooks() throws Exception {
        String uri = "/getUniqueNamesFromAddressBooks";

        List<Person> personList = new ArrayList<>();
        AddressBook addressBook = new AddressBook(1, "test-test1" , personList);
        personList.add(new Person(1, "Name1", "0447500439", addressBook));
        personList.add(new Person(2, "Name5", "0447500434", addressBook));
        personList.add(new Person(3, "Name6", "0147500433", addressBook));
        personList.add(new Person(4, "Name7", "0247500432", addressBook));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).param("addressBookName1", "test")
                .param("addressBookName2", "test1")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        log.info("Content is ==> " + content);
        AddressBook addressBookResponse = super.mapFromJson(content, AddressBook.class);
        assertEquals(addressBookResponse.getName(), addressBook.getName());
        assertEquals(addressBookResponse.getPersons().size(), addressBook.getPersons().size());
        assertEquals(addressBookResponse.getPersons().get(0).getName(), addressBook.getPersons().get(0).getName());
        assertEquals(addressBookResponse.getPersons().get(1).getName(), addressBook.getPersons().get(1).getName());
        assertEquals(addressBookResponse.getPersons().get(2).getName(), addressBook.getPersons().get(2).getName());
        assertEquals(addressBookResponse.getPersons().get(3).getName(), addressBook.getPersons().get(3).getName());

    }

    @Test
    public void c_test_getAllByName() throws Exception {
        String uri = "/getAddressBookByName";
        List<Person> personList = new ArrayList<>();
        AddressBook addressBook = new AddressBook(1, "test" , personList);
        personList.add(new Person(1, "Name1", "0237588464", addressBook));
        personList.add(new Person(2, "Name2", "0137588465", addressBook));
        personList.add(new Person(3, "Name3", "0337588466", addressBook));
        personList.add(new Person(4, "Name4", "0437588467", addressBook));
        personList.add(new Person(5, "Name5", "0537588464", addressBook));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).param("name", "test")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        log.info("Content is ==> " + content);
        AddressBook addressBookResponse = super.mapFromJson(content, AddressBook.class);
        assertEquals(addressBookResponse.getName(), addressBook.getName());
        assertEquals(addressBookResponse.getPersons().size(), addressBook.getPersons().size());
        assertEquals(addressBookResponse.getPersons().get(0).getName(), addressBook.getPersons().get(0).getName());
        assertEquals(addressBookResponse.getPersons().get(1).getName(), addressBook.getPersons().get(1).getName());
        assertEquals(addressBookResponse.getPersons().get(2).getName(), addressBook.getPersons().get(2).getName());
        assertEquals(addressBookResponse.getPersons().get(3).getName(), addressBook.getPersons().get(3).getName());

    }
}
