package com.mockapi.mockapi.repository.impl;

import com.mockapi.mockapi.model.Employee;
import com.mockapi.mockapi.repository.EmployeeDAO;
import com.mockapi.mockapi.util.HibernateUtil;
import com.mockapi.mockapi.web.dto.EmployeeDTO;
import com.mockapi.mockapi.web.dto.request.SearchEmployeeRequest;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public class dEmployeeImpl implements EmployeeDAO {
    private static final Logger log = LoggerFactory.getLogger(dEmployeeImpl.class);

    @Override
    public Page<EmployeeDTO> getListByParams(SearchEmployeeRequest searchEmployeeRequest) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        session.beginTransaction();
        try {
                StringBuilder sb = new StringBuilder();
                sb.append("select ");


            SQLQuery query = session.createSQLQuery(sb.toString());
        }catch (Exception ex){
            log.error(ex.getMessage(),ex);
            transaction.rollback();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public EmployeeDTO findByUsername(String username) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        session.beginTransaction();
        try {
            StringBuilder sb = new StringBuilder();
//            sb.append("select E.USERNAME from EMPLOYEE E");
            sb.append("SELECT E.ID,E.ADDRESS,E.BIRTHDAY,E.CREATED_DATE,E.DEPARTMENT_ID,E.EDUCATION,E.EMAIL,E.FACEBOOK,E.FACULTY,E.FULLNAME,E.GRADUATED_YEAR,E.IMAGE_URL,E.IS_ACTIVED,E.IS_LEADER,E.IS_MANAGER,E.LAST_ACCESS,E.LAST_ACCESS,E.PASSWORD,E.PHONE_NUMBER,E.POSITION_ID,E.SKYPE_ACCOUNT,E.TEAM_ID,E.UNIVERSITY,E.USER_TYPE,E.USERNAME FROM EMPLOYEE E");
            sb.toString().toUpperCase();
            sb.append(" WHERE E.USERNAME = :username");
            SQLQuery query = session.createSQLQuery(sb.toString());
            query.setParameter("username",username);
            query.addScalar("username",new StringType());
            EmployeeDTO dto = (EmployeeDTO) query.getSingleResult();
            transaction.commit();
            return dto;
        }catch (Exception ex){
            log.error(ex.getMessage(),ex);
            transaction.rollback();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Employee resetPW() {
        return null;
    }
}
