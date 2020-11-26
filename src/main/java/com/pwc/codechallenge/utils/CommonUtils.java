package com.pwc.codechallenge.utils;

import com.pwc.codechallenge.entity.AddressBook;
import com.pwc.codechallenge.entity.Person;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CommonUtils {

    public static Person[] getPersonDetailsFromAddressBook(AddressBook addressBook, Comparator comparatorType) {
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
