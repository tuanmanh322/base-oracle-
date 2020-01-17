package com.mockapi.mockapi.model;



import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
/*
 Save token
 */
@Entity(name = "CONFIRMATION_TOKEN")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "TOKEN_ID_SEQ")
    @SequenceGenerator(name = "TOKEN_ID_SEQ",sequenceName = "AUTO_INCRE_SEQ_TOKEN",initialValue = 1,allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @Column(name = "TOKEN", nullable = false, unique = true)
    private String token;

    @Column(name = "DATETIME_CREATED", nullable = false)
    private Date datetimeCreated;

    @Column(name = "USED", nullable = false)
    private boolean used;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;


    public ConfirmationToken() {
    }

    public ConfirmationToken(Employee employee) {
        this.token = UUID.randomUUID().toString();
        this.datetimeCreated = new Date();
        this.used = false;
        this.employee = employee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDatetimeCreated() {
        return datetimeCreated;
    }

    public void setDatetimeCreated(Date datetimeCreated) {
        this.datetimeCreated = datetimeCreated;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConfirmationToken)) return false;
        ConfirmationToken that = (ConfirmationToken) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
