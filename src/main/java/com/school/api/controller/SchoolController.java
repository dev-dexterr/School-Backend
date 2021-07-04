package com.school.api.controller;

import com.school.api.model.Room;
import com.school.api.model.Lecture;
import com.school.api.model.Student;
import com.school.api.model.Fee;
import com.school.api.model.request.RoomCreationRequest;
import com.school.api.model.request.LectureCreationRequest;
import com.school.api.model.request.RoomFeeRequest;
import com.school.api.model.request.StudentCreationRequest;
import com.school.api.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

//@RestController
//@RequestMapping(value = "/api/school")

//@CrossOrigin("*")

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/school")
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping("/room")
    public ResponseEntity readBooks(@RequestParam(required = false) String name) {
        if (name == null) {
            return ResponseEntity.ok(schoolService.readRoom());
        }
        return ResponseEntity.ok(schoolService.readRoom(name));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<Room> readRoom (@PathVariable Long roomId) {
        return ResponseEntity.ok(schoolService.readRoom(roomId));
    }

    @PostMapping("/room")
    public ResponseEntity<Room> createRoom (@RequestBody RoomCreationRequest request) {
        return ResponseEntity.ok(schoolService.createRoom(request));
    }

    @PatchMapping("/room/{roomId}")
    public ResponseEntity<Room> updateRoom (@PathVariable("roomId") Long roomId, @RequestBody RoomCreationRequest request) {
        return ResponseEntity.ok(schoolService.updateRoom(roomId, request));
    }

    @PostMapping("/lecture")
    public ResponseEntity<Lecture> createLecture (@RequestBody LectureCreationRequest request) {
        return ResponseEntity.ok(schoolService.createLecture(request));
    }

    @GetMapping("/lecture")
    public ResponseEntity<List<Lecture>> readLectures () {
        return ResponseEntity.ok(schoolService.readLecture());
    }

    @GetMapping("/fee")
    public ResponseEntity<List<Fee>> readFees(){
        return ResponseEntity.ok(schoolService.readFee());
    }

    @DeleteMapping("/room/{roomId}")
    public ResponseEntity<Void> deleteRoom (@PathVariable Long roomId) {
        schoolService.deleteRoom(roomId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/lecture/{lectureId}")
    public ResponseEntity<Void> deleteLecture (@PathVariable Long lectureId){
        schoolService.deleteLecture(lectureId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId){
        schoolService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/room/fee/{feeId}")
    public ResponseEntity<Void> deleteFee(@PathVariable Long feeId){
        schoolService.deleteFee(feeId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent (@RequestBody StudentCreationRequest request) {
        return ResponseEntity.ok(schoolService.createStudent(request));
    }
    @GetMapping("/student")
    public ResponseEntity<List<Student>> readStudents () {
        return ResponseEntity.ok(schoolService.readStudent());
    }
    @PatchMapping("/student/{studentId}")
    public ResponseEntity<Student> updateStudent (@RequestBody StudentCreationRequest request, @PathVariable Long studentId) {
        return ResponseEntity.ok(schoolService.updateStudent(studentId, request));
    }

    @PatchMapping("/lecture/{lectureId}")
    public ResponseEntity<Lecture> updateLecture(@RequestBody LectureCreationRequest req, @PathVariable Long lectureId){
        return ResponseEntity.ok(schoolService.updateLecture(lectureId , req));
    }

    @PostMapping("/room/fee")
    public ResponseEntity<List<String>> feeARoom(@RequestBody RoomFeeRequest roomFeeRequests) {
        return ResponseEntity.ok(schoolService.feeARoom(roomFeeRequests));
    }


}
