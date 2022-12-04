package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Student;
import peaksoft.repository.StudentRepository;
import peaksoft.service.StudentService;

import java.io.IOException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public List<Student> getAllStudents(Long groupId) {
        return studentRepository.getAllStudents(groupId);
    }

    @Override
    public List<Student> getStudentList() {
        return studentRepository.getStudentList();
    }

    @Override
    public void addStudent(Long id, Student student) {
        studentRepository.addStudent(id,student);

    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public void updateStudent(Student student, Long id) {
        studentRepository.updateStudent(student,id);

    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteStudent(id);

    }

    @Override
    public void assignStudent(Long groupId, Long studentId) throws IOException {
        studentRepository.assignStudent(groupId,studentId);

    }
}
