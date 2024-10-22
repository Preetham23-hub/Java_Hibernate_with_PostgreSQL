package com.hibernate.dbtest.dao;

import com.hibernate.dbtest.entity.Subject;
import com.hibernate.dbtest.hibernateconfig.HibernateUtil;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SubjectDAO {

    @Transactional
    public void saveSubject(Subject subject) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(subject);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Subject> getAllSubjects() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Subject", Subject.class).list();
        }
    }
}
