package com.nexushr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.entity.Leave;
import com.nexushr.repository.LeaveRepository;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    public Leave saveLeave(Leave leave) {
        return leaveRepository.save(leave);
    }

    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public Leave getLeaveById(Long id) {
        return leaveRepository.findById(id).orElse(null);
    }

    public Leave updateLeave(Long id, Leave leave) {

        Leave existingLeave = leaveRepository.findById(id).orElse(null);

        if (existingLeave != null) {

            existingLeave.setEmployeeId(leave.getEmployeeId());
            existingLeave.setFromDate(leave.getFromDate());
            existingLeave.setToDate(leave.getToDate());
            existingLeave.setLeaveType(leave.getLeaveType());
            existingLeave.setReason(leave.getReason());
            existingLeave.setStatus(leave.getStatus());

            return leaveRepository.save(existingLeave);
        }

        return null;
    }

    public String deleteLeave(Long id) {

        leaveRepository.deleteById(id);

        return "Leave Deleted Successfully";
    }
}