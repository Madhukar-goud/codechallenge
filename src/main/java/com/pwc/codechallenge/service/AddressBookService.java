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

    /**
     * This method has the main logic for merging two address books.
     * It assumes the two arrays that are passed are sorted.
     * It loops through both the arrays and updates it to third array while removing any common elements
     * We can pass in phone number comparator or name comparator and it removes accordingly
     * @param personArray1
     * @param personArray2
     * @param comparatorType
     * @return List of Person
     */
    @LogExecutionTime
    public List<Person> mergeAddressBooks(Person[] personArray1, Person[] personArray2, Comparator<Person> comparatorType) {
        int totalSize = personArray1.length + personArray2.length;
        Person[] allPersons = new Person[totalSize];
        int counterArray1 = 0;
        int counterArray2 = 0;
        for (int counterAuxArray = 0; counterAuxArray < totalSize; counterAuxArray++) {
            if (counterArray1 > personArray1.length-1 &&  counterArray2 > personArray2.length-1) {
                break;
            } else if (counterArray1 > personArray1.length-1) {
                allPersons[counterAuxArray] = personArray2[counterArray2++];
            } else if ( counterArray2 > personArray2.length-1) {
                allPersons[counterAuxArray] = personArray1[counterArray1++];
            } else if (less(personArray2[counterArray2], personArray1[counterArray1], comparatorType) > 0) {
                allPersons[counterAuxArray] = personArray1[counterArray1++];
            } else if (less(personArray2[counterArray2], personArray1[counterArray1], comparatorType) < 0) {
                allPersons[counterAuxArray] = personArray2[counterArray2++];
            } else {
                counterArray1++;
                counterArray2++;
            }
        }
        return Arrays.stream(allPersons).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * Based on comparator type, the objects get compared and less of the two can be identified
     * @param person1
     * @param person2
     * @param comparatorType
     * @return
     */
    public int less(Person person1, Person person2, Comparator<Person> comparatorType) {
        return  comparatorType.compare(person1, person2);
    }

    /**
     * Fetches data from the database for each address book name and does the required null checks and processing
     * @param addressBookName1
     * @param addressBookName2
     * @param comparatorType
     * @return AddressBook with List of Persons
     */
    @LogExecutionTime
    public AddressBook mergeAddressBooks(String addressBookName1, String addressBookName2, Comparator<Person> comparatorType) {
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
