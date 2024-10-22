package com.hibernate.dbtest.dao;

import com.hibernate.dbtest.entity.Student;
import com.hibernate.dbtest.entity.Subject;
import com.hibernate.dbtest.hibernateconfig.HibernateUtil;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class StudentDAO {

    // CREATE or UPDATE operation
    @Transactional
    public Student saveStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Set<Subject> attachedSubjects = new HashSet<>();
            for (Subject subject : student.getSubjects()) {
                Subject existingSubject = findSubjectByName(session, subject.getName());
                if (existingSubject != null) {
                    attachedSubjects.add(existingSubject);
                } else {
                    attachedSubjects.add(subject);
                }
            }
            student.setSubjects(attachedSubjects);
            var response = session.merge(student);
            transaction.commit();
            return response;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error Saving student", e.getCause());
        }
    }

    private Subject findSubjectByName(Session session, String name) {
        Query<Subject> query = session.createQuery("FROM Subject WHERE name = :name", Subject.class);
        query.setParameter("name", name);
        return query.uniqueResult();
    }

    // READ operation to fetch a Student by ID
    @Transactional
    public Student getStudentById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            var response = session.get(Student.class, id);
            transaction.commit();
            return response;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Delete Student failed", e.getCause());
        }
    }

    // DELETE operation to delete a Student by ID
    @Transactional
    public void deleteStudent(long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.remove(student);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Delete Student failed", e.getCause());
        }
    }

    // To fetch all Students by Hibernate query
    @Transactional
    public List<Student> getAllStudents() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Student> query = session.createQuery("FROM Student", Student.class);
            var response = query.getResultList();
            transaction.commit();
            return response;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error finding all students", e.getCause());
        }
    }

    // To fetch all Students by native sql query
    @Transactional
    public List<Student> findAllStudents() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "SELECT * FROM student";
            NativeQuery<Student> query = session.createNativeQuery(sql, Student.class);
            var response = query.getResultList();
            transaction.commit();
            return response;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Error finding all students", e.getCause());
        }
    }
}





