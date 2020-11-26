package com.pwc.codechallenge.utils;

import com.pwc.codechallenge.entity.AddressBook;
import com.pwc.codechallenge.entity.Person;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CommonUtils {

    /**
     * Validate the Address book and Person objects and sort them based on Comparator type
     * @param addressBook
     * @param comparatorType
     * @return Array Of Person
     */
    public static Person[] getPersonDetailsFromAddressBook(AddressBook addressBook, Comparator<Person> comparatorType) {
        if (addressBook != null && !CollectionUtils.isEmpty(addressBook.getPersons())) {
            List<Person> personList = addressBook.getPersons();
            Collections.sort(personList, comparatorType);
            Person[] person = new Person[personList.size()];
            personList.toArray(person);
            return person;
        }
        return null;
    }

}
