package com.school.api.service;

import com.school.api.model.*;
import com.school.api.model.request.RoomFeeRequest;
import com.school.api.model.request.RoomCreationRequest;
import com.school.api.model.request.StudentCreationRequest;
import com.school.api.model.request.LectureCreationRequest;
import com.school.api.repository.FeeRepository;
import com.school.api.repository.RoomRepository;
import com.school.api.repository.LectureRepository;
import com.school.api.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final FeeRepository feeRepository;
    private final RoomRepository roomRepository;
    private final LectureRepository lectureRepository;
    private final StudentRepository studentRepository;

    public Room readRoom(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isPresent()) {
            return room.get();
        }
        throw new EntityNotFoundException("Cant find any room under given ID");
    }

    public List<Room> readRoom(){
        return roomRepository.findAll();
    }

    public Room readRoom(String name) {
        Optional<Room> room = roomRepository.findByName(name);
        if (room.isPresent()) {
            return room.get();
        }
        throw new EntityNotFoundException("Cant find any room under given Name");
    }

    public Room createRoom(RoomCreationRequest room) {
        Optional<Lecture> lecture = lectureRepository.findById(room.getLectureId());
        if (!lecture.isPresent()) {
            throw new EntityNotFoundException("Lecture Not Found");
        }
        Room roomToCreate = new Room();
        BeanUtils.copyProperties(room, roomToCreate);
        roomToCreate.setLecture(lecture.get());
        return roomRepository.save(roomToCreate);
    }

    public void deleteRoom(Long id){
        roomRepository.deleteById(id);
    }

    public void deleteLecture(Long id){
        lectureRepository.deleteById(id);
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }
    public void deleteFee(Long id){
        feeRepository.deleteById(id);
    }

    public Student createStudent(StudentCreationRequest request) {
        Student student = new Student();
        BeanUtils.copyProperties(request, student);
        student.setStatus(StudentStatus.ACTIVE);
        return studentRepository.save(student);
    }

    public Student updateStudent (Long id, StudentCreationRequest request) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()) {
            throw new EntityNotFoundException("Student not present in the database");
        }
        Student student = optionalStudent.get();
        student.setLastName(request.getLastName());
        student.setFirstName(request.getFirstName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());
        student.setMobile(request.getMobile());
        return studentRepository.save(student);
    }

    public Lecture updateLecture (Long id, LectureCreationRequest req){
        Optional<Lecture> optionalLecture = lectureRepository.findById(id);
        if(!optionalLecture.isPresent()){
            throw new EntityNotFoundException("Lecture not Presend in the Database");
        }
        Lecture lecture = optionalLecture.get();
        lecture.setFirstName(req.getFirstName());
        lecture.setLastName(req.getLastName());
        return lectureRepository.save(lecture);
    }

    public Lecture createLecture (LectureCreationRequest request) {
        Lecture lecture = new Lecture();
        BeanUtils.copyProperties(request, lecture);
        return lectureRepository.save(lecture);
    }

    public List<String> feeARoom (RoomFeeRequest request) {

        Optional<Student> studentForId = studentRepository.findById(request.getStudentId());
        if (!studentForId.isPresent()) {
            throw new EntityNotFoundException("Student not present in the database");
        }

        Student student = studentForId.get();
        if (student.getStatus() != StudentStatus.ACTIVE) {
            throw new RuntimeException("Student is not active to proceed a lending.");
        }

        List<String> RoomApprovedToStudy = new ArrayList<>();

        request.getRoomIds().forEach(roomId -> {

            Optional<Room> roomForId = roomRepository.findById(roomId);
            if (!roomForId.isPresent()) {
                throw new EntityNotFoundException("Cant find any Room under given ID");
            }

            Optional<Fee> studiedRoom = feeRepository.findByRoomAndStatus(roomForId.get(), FeeStatus.PAID);
            if (!studiedRoom.isPresent()) {
                RoomApprovedToStudy.add(roomForId.get().getName());
                Fee fee = new Fee();
                fee.setStudent(studentForId.get());
                fee.setRoom(roomForId.get());
                fee.setStatus(FeeStatus.PAID);
                fee.setStartOn(Instant.now());
                fee.setDueOn(Instant.now().plus(30, ChronoUnit.DAYS));
                feeRepository.save(fee);
            }

        });
        return RoomApprovedToStudy;
    }

    public List<Lecture> readLecture() {
        return lectureRepository.findAll();
    }

    public List<Fee> readFee(){
        return feeRepository.findAll();
    }

    public Room updateRoom(Long roomId, RoomCreationRequest request) {
        Optional<Lecture> lecture = lectureRepository.findById(request.getLectureId());
        if (!lecture.isPresent()) {
            throw new EntityNotFoundException("Lecture Not Found");
        }
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (!optionalRoom.isPresent()) {
            throw new EntityNotFoundException("Room Not Found");
        }
        Room room = optionalRoom.get();
        room.setName(request.getName());
        room.setLecture(lecture.get());
        return roomRepository.save(room);
    }

    public List<Student> readStudent() {
        return studentRepository.findAll();
    }
}
