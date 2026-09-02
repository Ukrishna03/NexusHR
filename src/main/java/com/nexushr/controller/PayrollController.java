package com.nexushr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.entity.Payroll;
import com.nexushr.service.PayrollService;

@RestController
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @PostMapping("/save")
    public Payroll savePayroll(@RequestBody Payroll payroll) {
        return payrollService.savePayroll(payroll);
    }

    @GetMapping("/all")
    public List<Payroll> getAllPayrolls() {
        return payrollService.getAllPayrolls();
    }

    @GetMapping("/{id}")
    public Payroll getPayrollById(@PathVariable Long id) {
        return payrollService.getPayrollById(id);
    }

    @PutMapping("/update/{id}")
    public Payroll updatePayroll(
            @PathVariable Long id,
            @RequestBody Payroll payroll) {

        return payrollService.updatePayroll(id, payroll);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePayroll(@PathVariable Long id) {
        return payrollService.deletePayroll(id);
    }
}