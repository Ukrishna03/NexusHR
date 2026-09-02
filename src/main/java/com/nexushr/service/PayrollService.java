package com.nexushr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.entity.Payroll;
import com.nexushr.repository.PayrollRepository;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    public Payroll savePayroll(Payroll payroll) {
        return payrollRepository.save(payroll);
    }

    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    public Payroll getPayrollById(Long id) {
        return payrollRepository.findById(id).orElse(null);
    }

    public Payroll updatePayroll(Long id, Payroll payroll) {

        Payroll existingPayroll =
                payrollRepository.findById(id).orElse(null);

        if (existingPayroll != null) {

            existingPayroll.setEmployeeId(payroll.getEmployeeId());
            existingPayroll.setBasicSalary(payroll.getBasicSalary());
            existingPayroll.setAllowances(payroll.getAllowances());
            existingPayroll.setDeductions(payroll.getDeductions());
            existingPayroll.setNetSalary(payroll.getNetSalary());
            existingPayroll.setPaymentDate(payroll.getPaymentDate());
            existingPayroll.setStatus(payroll.getStatus());

            return payrollRepository.save(existingPayroll);
        }

        return null;
    }

    public String deletePayroll(Long id) {

        payrollRepository.deleteById(id);

        return "Payroll Deleted Successfully";
    }
}