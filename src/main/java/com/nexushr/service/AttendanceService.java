package com.nexushr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.entity.Attendance;
import com.nexushr.repository.AttendanceRepository;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // Save Attendance
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    // Get All Attendance
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    // Get Attendance By Id
    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id).orElse(null);
    }

    // Update Attendance
    public Attendance updateAttendance(Long id, Attendance attendance) {

        Attendance existingAttendance = attendanceRepository.findById(id).orElse(null);

        if (existingAttendance != null) {

            existingAttendance.setEmployeeId(attendance.getEmployeeId());
            existingAttendance.setAttendanceDate(attendance.getAttendanceDate());
            existingAttendance.setCheckInTime(attendance.getCheckInTime());
            existingAttendance.setCheckOutTime(attendance.getCheckOutTime());
            existingAttendance.setStatus(attendance.getStatus());

            return attendanceRepository.save(existingAttendance);
        }

        return null;
    }
   
    // Delete Attendance
    public String deleteAttendance(Long id) {

        attendanceRepository.deleteById(id);

        return "Attendance Deleted Successfully";
    }

}