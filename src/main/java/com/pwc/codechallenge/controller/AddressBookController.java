package com.pwc.codechallenge.controller;

import com.pwc.codechallenge.entity.AddressBook;
import com.pwc.codechallenge.entity.Person;
import com.pwc.codechallenge.repository.AddressBookRepository;
import com.pwc.codechallenge.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AddressBookController extends BaseController {

    @Autowired
    AddressBookRepository addressBookRepository;

    @Autowired
    AddressBookService addressBookService;


    /**
     * Get all Address and Person data in the database
     * @return
     */
    @GetMapping("/getAllDetails")
    public List<AddressBook> getAllDetails() {
        return addressBookRepository.findAll();
    }

    /**
     * Get Address book by name
     * @param name
     * @return
     */
    @GetMapping("/getAddressBookByName")
    public AddressBook getAllByName(@RequestParam(value = "name", defaultValue = "test") String name) {
        return addressBookRepository.findAddressBookByName(name);
    }

    /**
     * Add an address book to the database
     * @param addressBook
     * @return
     */
    @PostMapping("/addAddressBook")
    public AddressBook createAddressBook(@RequestBody @Valid AddressBook addressBook) {
        return addressBookRepository.save(addressBook);
    }

    /**
     * Get list of names between two address books
     * @param addressBookName1
     * @param addressBookName2
     * @return Addressbook
     */
    @GetMapping("/getUniqueNamesFromAddressBooks")
    public AddressBook getUniqueNamesFromAddressBooks(@RequestParam(value = "addressBookName1", defaultValue = "test") String addressBookName1,
                                                            @RequestParam(value = "addressBookName2", defaultValue = "test1") String addressBookName2) {
        return addressBookService.mergeAddressBooks(addressBookName1, addressBookName2,  Person.BY_NAME);
    }

    /**
     * Get List of unique numbers between two address books
     * @param addressBookName1
     * @param addressBookName2
     * @return
     */
    @GetMapping("/getUniqueNumbersFromAddressBooks")
    public AddressBook getUniqueNumbersFromAddressBooks(@RequestParam(value = "addressBookName1", defaultValue = "test") String addressBookName1,
                                                      @RequestParam(value = "addressBookName2", defaultValue = "test1") String addressBookName2) {
        return addressBookService.mergeAddressBooks(addressBookName1, addressBookName2, Person.BY_PHONE);
    }

}
