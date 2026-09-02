package com.nexushr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.entity.Performance;
import com.nexushr.service.PerformanceService;

@RestController
@RequestMapping("/performance")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @PostMapping("/save")
    public Performance savePerformance(@RequestBody Performance performance) {
        return performanceService.savePerformance(performance);
    }

    @GetMapping("/all")
    public List<Performance> getAllPerformances() {
        return performanceService.getAllPerformances();
    }

    @GetMapping("/{id}")
    public Performance getPerformanceById(@PathVariable Long id) {
        return performanceService.getPerformanceById(id);
    }

    @PutMapping("/update/{id}")
    public Performance updatePerformance(
            @PathVariable Long id,
            @RequestBody Performance performance) {

        return performanceService.updatePerformance(id, performance);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePerformance(@PathVariable Long id) {
        return performanceService.deletePerformance(id);
    }
}