package com.pwc.codechallenge.service;

import com.pwc.codechallenge.entity.AddressBook;
import com.pwc.codechallenge.entity.Person;
import com.pwc.codechallenge.repository.AddressBookRepository;
import com.pwc.codechallenge.utils.CommonUtils;
import com.pwc.codechallenge.utils.LogExecutionTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AddressBookService {


    AddressBookRepository addressBookRepository;

    @Autowired
    public AddressBookService(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }

    @LogExecutionTime
    public List<Person> mergeAddressBooks(Person[] personArray1, Person[] personArray2, Comparator comparatorType) {
        int totalSize = personArray1.length + personArray2.length;
        Person[] allPersons = new Person[totalSize];
        int i = 0;
        int j = 0;
        for (int k = 0; k < totalSize; k++) {
            if (i > personArray1.length-1 &&  j > personArray2.length-1) {
                break;
            } else if (i > personArray1.length-1) {
                allPersons[k] = personArray2[j++];
            } else if ( j > personArray2.length-1) {
                allPersons[k] = personArray1[i++];
            } else if (less(personArray2[j], personArray1[i], comparatorType) > 0) {
                allPersons[k] = personArray1[i++];
            } else if (less(personArray2[j], personArray1[i], comparatorType) < 0) {
                allPersons[k] = personArray2[j++];
            } else {
                i++;
                j++;
            }
        }
        return Arrays.stream(allPersons).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public int less(Person person1, Person person2, Comparator comparator) {
        return  comparator.compare(person1, person2);
    }

    @LogExecutionTime
    public AddressBook mergeAddressBooks(String addressBookName1, String addressBookName2, Comparator comparatorType) {
        AddressBook addressBooks1 = addressBookRepository.findAddressBookByName(addressBookName1);
        AddressBook addressBooks2 = addressBookRepository.findAddressBookByName(addressBookName2);
        Person[] person1 = CommonUtils.getPersonDetailsFromAddressBook(addressBooks1, comparatorType);
        Person[] person2 = CommonUtils.getPersonDetailsFromAddressBook(addressBooks2, comparatorType);
        if (person1 == null) {
            return addressBooks2;
        }
        if (person2 == null) {
            return addressBooks1;
        }
        return new AddressBook(new Random().nextInt(), addressBookName1 +"-" + addressBookName2,
                mergeAddressBooks(person1, person2, comparatorType));
    }
}
