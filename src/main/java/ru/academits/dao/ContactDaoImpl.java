package ru.academits.dao;

import org.springframework.stereotype.Repository;
import ru.academits.model.Contact;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ContactDaoImpl extends GenericDaoImpl<Contact, Long> implements ContactDao {
    public ContactDaoImpl() {
        super(Contact.class);
    }

    @Override
    public List<Contact> getAllContacts(String filterQuery) {
        return findAll();
    }

    @Override
    public List<Contact> findByPhone(String phone) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> cq = cb.createQuery(clazz);

        Root<Contact> root = cq.from(clazz);

        cq.where(cb.equal(root.get("phone"), phone));

        CriteriaQuery<Contact> select = cq.select(root);
        TypedQuery<Contact> q = entityManager.createQuery(select);

        return q.getResultList();
    }

    @Override
    public List<Contact> find(String query) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> cq = cb.createQuery(clazz);

        Root<Contact> root = cq.from(clazz);

        cq.where(cb.or(
                cb.equal(root.get("phone"), query),
                cb.equal(root.get("firstName"), query),
                cb.equal(root.get("lastName"), query)
        ));

        CriteriaQuery<Contact> select = cq.select(root);
        TypedQuery<Contact> q = entityManager.createQuery(select);

        return q.getResultList();
    }
}
