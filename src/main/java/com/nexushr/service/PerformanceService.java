package com.nexushr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.entity.Performance;
import com.nexushr.repository.PerformanceRepository;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    public Performance savePerformance(Performance performance) {
        return performanceRepository.save(performance);
    }

    public List<Performance> getAllPerformances() {
        return performanceRepository.findAll();
    }

    public Performance getPerformanceById(Long id) {
        return performanceRepository.findById(id).orElse(null);
    }

    public Performance updatePerformance(Long id, Performance performance) {

        Performance existingPerformance =
                performanceRepository.findById(id).orElse(null);

        if (existingPerformance != null) {

            existingPerformance.setEmployeeId(performance.getEmployeeId());
            existingPerformance.setReviewPeriod(performance.getReviewPeriod());
            existingPerformance.setRating(performance.getRating());
            existingPerformance.setComments(performance.getComments());
            existingPerformance.setStatus(performance.getStatus());
            existingPerformance.setReviewDate(performance.getReviewDate());

            return performanceRepository.save(existingPerformance);
        }

        return null;
    }

    public String deletePerformance(Long id) {

        performanceRepository.deleteById(id);

        return "Performance Deleted Successfully";
    }
}