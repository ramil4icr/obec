package cz.nigol.obec.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import javax.persistence.JoinColumn;

@NamedQueries({
@NamedQuery(name=BudgetFulfillment.GET_ALL, query="SELECT b FROM BudgetFulfillment b ORDER BY b.createdAt DESC"),
})
@Entity
@Table(name="OB_BUDGET_FULFILLMENT")
public class BudgetFulfillment implements Serializable {
    private static final long serialVersionUID = -4378072789330339015L;

    public static final String GET_ALL = "BudgetFulfillment.GET_ALL";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="INCOME_PLAN")
    private BigDecimal incomePlan;

    @Column(name="INCOME_REAL")
    private BigDecimal incomeReal;

    @Column(name="EXPENDITURE_PLAN")
    private BigDecimal expenditurePlan;

    @Column(name="EXPENDITURE_REAL")
    private BigDecimal expenditureReal;

    @Column(name="YEAR")
    private int year;

    @Column(name="CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User createdBy;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getIncomePlan() {
        return incomePlan;
    }

    public void setIncomePlan(BigDecimal incomePlan) {
        this.incomePlan = incomePlan;
    }

    public BigDecimal getIncomeReal() {
        return incomeReal;
    }

    public void setIncomeReal(BigDecimal incomeReal) {
        this.incomeReal = incomeReal;
    }

    public BigDecimal getExpenditurePlan() {
        return expenditurePlan;
    }

    public void setExpenditurePlan(BigDecimal expenditurePlan) {
        this.expenditurePlan = expenditurePlan;
    }

    public BigDecimal getExpenditureReal() {
        return expenditureReal;
    }

    public void setExpenditureReal(BigDecimal expenditureReal) {
        this.expenditureReal = expenditureReal;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User user) {
        this.createdBy = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BudgetFulfillment)) return false;
        BudgetFulfillment budgetFulfillment = (BudgetFulfillment) o;
        return id == budgetFulfillment.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

