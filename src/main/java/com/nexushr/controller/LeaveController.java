package com.nexushr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.entity.Leave;
import com.nexushr.service.LeaveService;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/save")
    public Leave saveLeave(@RequestBody Leave leave) {
        return leaveService.saveLeave(leave);
    }

    @GetMapping("/all")
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @GetMapping("/{id}")
    public Leave getLeaveById(@PathVariable Long id) {
        return leaveService.getLeaveById(id);
    }

    @PutMapping("/update/{id}")
    public Leave updateLeave(@PathVariable Long id,
                             @RequestBody Leave leave) {
        return leaveService.updateLeave(id, leave);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLeave(@PathVariable Long id) {
        return leaveService.deleteLeave(id);
    }
}