package ru.academits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.academits.converter.ContactDtoToContactConverter;
import ru.academits.converter.ContactToContactDtoConverter;
import ru.academits.dto.ContactDto;
import ru.academits.model.ContactValidation;
import ru.academits.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/phoneBook/rpc/api/v1")
public class PhoneBookController {
    private static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

    private final ContactService contactService;
    private final ContactDtoToContactConverter contactDtoToContactConverter;
    private final ContactToContactDtoConverter contactToContactDtoConverter;

    public PhoneBookController(ContactService contactService, ContactDtoToContactConverter contactDtoToContactConverter, ContactToContactDtoConverter contactToContactDtoConverter) {
        this.contactService = contactService;
        this.contactDtoToContactConverter = contactDtoToContactConverter;
        this.contactToContactDtoConverter = contactToContactDtoConverter;
    }

    @RequestMapping(value = "getAllContacts", method = RequestMethod.GET)
    @ResponseBody
    public List<ContactDto> getAllContacts(String filter) {
        logger.info("called method getAllContacts");
        return contactToContactDtoConverter.convert(contactService.getAllContacts(filter));
    }

    @RequestMapping(value = "addContact", method = RequestMethod.POST)
    @ResponseBody
    public ContactValidation addContact(@RequestBody ContactDto contact) {
        return contactService.addContact(contactDtoToContactConverter.convert(contact));
    }

    @RequestMapping(value = "deleteContact", method = RequestMethod.POST)
    @ResponseBody
    public void deleteContact(Long id) {
        contactService.deleteContact(id);
    }
}


