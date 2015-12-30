/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.agent.demo.model;

import java.io.Serializable;

/**
 * Employee definition.
 *
 * Code generated by Apache Ignite Schema Import utility: 08/24/2015.
 */
public class Employee implements Serializable {
    /** */
    private static final long serialVersionUID = 0L;

    /** Value for employeeId. */
    private int employeeId;

    /** Value for firstName. */
    private String firstName;

    /** Value for lastName. */
    private String lastName;

    /** Value for email. */
    private String email;

    /** Value for phoneNumber. */
    private String phoneNumber;

    /** Value for hireDate. */
    private java.sql.Date hireDate;

    /** Value for job. */
    private String job;

    /** Value for salary. */
    private Double salary;

    /** Value for managerId. */
    private Integer managerId;

    /** Value for departmentId. */
    private Integer departmentId;

    /**
     * Empty constructor.
     */
    public Employee() {
        // No-op.
    }

    /**
     * Full constructor.
     */
    public Employee(
        int employeeId,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        java.sql.Date hireDate,
        String job,
        Double salary,
        Integer managerId,
        Integer departmentId
    ) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.job = job;
        this.salary = salary;
        this.managerId = managerId;
        this.departmentId = departmentId;
    }

    /**
     * Gets employeeId.
     *
     * @return Value for employeeId.
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets employeeId.
     *
     * @param employeeId New value for employeeId.
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Gets firstName.
     *
     * @return Value for firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets firstName.
     *
     * @param firstName New value for firstName.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets lastName.
     *
     * @return Value for lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets lastName.
     *
     * @param lastName New value for lastName.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return Value for email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email New value for email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets phoneNumber.
     *
     * @return Value for phoneNumber.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phoneNumber.
     *
     * @param phoneNumber New value for phoneNumber.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets hireDate.
     *
     * @return Value for hireDate.
     */
    public java.sql.Date getHireDate() {
        return hireDate;
    }

    /**
     * Sets hireDate.
     *
     * @param hireDate New value for hireDate.
     */
    public void setHireDate(java.sql.Date hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * Gets job.
     *
     * @return Value for job.
     */
    public String getJob() {
        return job;
    }

    /**
     * Sets job.
     *
     * @param job New value for job.
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * Gets salary.
     *
     * @return Value for salary.
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * Sets salary.
     *
     * @param salary New value for salary.
     */
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    /**
     * Gets managerId.
     *
     * @return Value for managerId.
     */
    public Integer getManagerId() {
        return managerId;
    }

    /**
     * Sets managerId.
     *
     * @param managerId New value for managerId.
     */
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    /**
     * Gets departmentId.
     *
     * @return Value for departmentId.
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Sets departmentId.
     *
     * @param departmentId New value for departmentId.
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /** {@inheritDoc} */
    @Override public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Employee))
            return false;

        Employee that = (Employee)o;

        if (employeeId != that.employeeId)
            return false;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null)
            return false;

        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null)
            return false;

        if (email != null ? !email.equals(that.email) : that.email != null)
            return false;

        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null)
            return false;

        if (hireDate != null ? !hireDate.equals(that.hireDate) : that.hireDate != null)
            return false;

        if (job != null ? !job.equals(that.job) : that.job != null)
            return false;

        if (salary != null ? !salary.equals(that.salary) : that.salary != null)
            return false;

        if (managerId != null ? !managerId.equals(that.managerId) : that.managerId != null)
            return false;

        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null)
            return false;

        return true;
    }

    /** {@inheritDoc} */
    @Override public int hashCode() {
        int res = employeeId;

        res = 31 * res + (firstName != null ? firstName.hashCode() : 0);

        res = 31 * res + (lastName != null ? lastName.hashCode() : 0);

        res = 31 * res + (email != null ? email.hashCode() : 0);

        res = 31 * res + (phoneNumber != null ? phoneNumber.hashCode() : 0);

        res = 31 * res + (hireDate != null ? hireDate.hashCode() : 0);

        res = 31 * res + (job != null ? job.hashCode() : 0);

        res = 31 * res + (salary != null ? salary.hashCode() : 0);

        res = 31 * res + (managerId != null ? managerId.hashCode() : 0);

        res = 31 * res + (departmentId != null ? departmentId.hashCode() : 0);

        return res;
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return "Employee [employeeId=" + employeeId +
            ", firstName=" + firstName +
            ", lastName=" + lastName +
            ", email=" + email +
            ", phoneNumber=" + phoneNumber +
            ", hireDate=" + hireDate +
            ", job=" + job +
            ", salary=" + salary +
            ", managerId=" + managerId +
            ", departmentId=" + departmentId +
            "]";
    }
}
