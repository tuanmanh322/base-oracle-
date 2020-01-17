package com.mockapi.mockapi.repository.impl;

import com.mockapi.mockapi.model.Employee;
import com.mockapi.mockapi.repository.EmployeeDAO;
import com.mockapi.mockapi.util.DataUtils;
import com.mockapi.mockapi.util.HibernateUtil;
import com.mockapi.mockapi.util.PageBuilder;
import com.mockapi.mockapi.util.SQLBuilder;
import com.mockapi.mockapi.web.dto.EmployeeDTO;
import com.mockapi.mockapi.web.dto.request.SearchEmployeeRequest;
import com.mockapi.mockapi.web.dto.response.resp.SearchRequestResponse;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class dEmployeeImpl implements EmployeeDAO {
    private static final Logger log = LoggerFactory.getLogger(dEmployeeImpl.class);
    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<SearchRequestResponse> getListByParams(SearchEmployeeRequest request) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        session.beginTransaction();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(SQLBuilder.getSqlQueryById(SQLBuilder.SQL_MODULE_EMPLOYEES, "employeesearch"));
            if (!DataUtils.isNullOrEmpty(request.getUsername())) {
                sb.append(" AND e.username LIKE :p_userName ");
            }
            if (!DataUtils.isNullOrEmpty(request.getFullName())) {
                sb.append(" AND e.fullname LIKE :p_fullName ");
            }
            if (!DataUtils.isNullOrEmpty(request.getUserType())) {
                sb.append(" AND d.department_name LIKE :p_departmentName");
            }

            SQLQuery query = session.createSQLQuery(sb.toString());
            if (!DataUtils.isNullOrEmpty(request.getUsername())) {
                query.setParameter("p_userName", "%" +
                        request.getUsername().trim()
                                .replace("\\", "\\\\")
                                .replaceAll("%", "\\%")
                                .replaceAll("_", "\\_")
                        + "%");
            }
            if (!DataUtils.isNullOrEmpty(request.getFullName())) {
                query.setParameter("p_fullName", "%" +
                        request.getFullName().trim()
                                .replace("\\", "\\\\")
                                .replaceAll("%", "\\%")
                                .replaceAll("_", "\\_")
                        + "%");
            }
            if (!DataUtils.isNullOrEmpty(request.getUserType())) {
                query.setParameter("p_departmentName", "%" +
                        request.getUserType().trim()
                                .replace("\\", "\\\\")
                                .replaceAll("%", "\\%")
                                .replaceAll("_", "\\_")
                        + "%");
            }
            query.addScalar("id", new LongType());
            query.addScalar("username", new StringType());
            query.addScalar("Email", new StringType());
            query.addScalar("created_date", new DateType());
            query.addScalar("fullName", new StringType());
            query.addScalar("is_actived", StandardBasicTypes.BOOLEAN);
            query.addScalar("last_access",new DateType());
            query.addScalar("phone_number", new IntegerType());
            query.addScalar("userType", new StringType());
            query.addScalar("role_name", new StringType());
            query.addScalar("department_name", new StringType());
            query.addScalar("leader_id", new LongType());

            query.setResultTransformer(Transformers.aliasToBean(SearchRequestResponse.class));
            int count = 0;
            List<SearchRequestResponse> dtos = query.list();
            if (dtos.size() > 0) {
                count = query.list().size();
            }

            if (request.getPage() != null && request.getPageSize() != null) {
                Pageable pageable = PageBuilder.buildPageable(request);
                if (pageable != null) {
                    query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
                    query.setMaxResults(pageable.getPageSize());
                }
                List<SearchRequestResponse> data = query.list();

                Page<SearchRequestResponse> dataPage = new PageImpl<>(data, pageable, count);
                return dataPage;
            }
            transaction.commit();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            transaction.rollback();
        } finally {
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
            query.setParameter("username", username);
            query.addScalar("username", new StringType());
            EmployeeDTO dto = (EmployeeDTO) query.getSingleResult();
            transaction.commit();
            return dto;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Employee resetPW() {
        return null;
    }

    @Override
    public void deleteRoleEmp(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        session.beginTransaction();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(SQLBuilder.getSqlQueryById(SQLBuilder.SQL_MODULE_EMP_ROLE, "deleteRoleEmp"));
            sb.append(" AND  e.id =:id");
            SQLQuery query = session.createSQLQuery(sb.toString());
            query.addScalar("id", new LongType());
            query.executeUpdate();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public Employee findByUsername1(String username) {
        Session session = em.unwrap(Session.class);
        try {
            String sql = "from EMPLOYEE e where e.username = :username";
            TypedQuery<Employee> query = session.createQuery(sql,Employee.class);
            query.setParameter("username",username);
            Employee employee;
            employee = query.setMaxResults(1).getSingleResult();
            return employee;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public void setActive(Employee employee) {
        Session session = em.unwrap(Session.class);
        Transaction transaction = session.beginTransaction();

        try {
            String sql = "UPDATE EMPLOYEE SET EMPLOYEE .is_actived = 1 " +
                    " INNER JOIN CONFIRMATION_TOKEN C ON EMPLOYEE.ID = C.EMPLOYEE_ID " +
                    "WHERE EMPLOYEE.ID =:id";
            TypedQuery<Employee> query = session.createQuery(sql,Employee.class);
            query.setParameter("id",employee.getId());
            session.update(employee);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
