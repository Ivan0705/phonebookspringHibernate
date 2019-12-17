package ru.academits.dao;

import ru.academits.model.Contact;

import java.util.List;

public interface ContactDao extends GenericDao<Contact, Long> {
    List<Contact> getAllContacts(String filterQuery);

    List<Contact> findByPhone(String phone);
    List<Contact> find(String query);
}
