package app.core.services;

import app.core.entities.Student;
import app.core.entities.Teacher;
import app.core.exception.SystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminService extends ClientService{

    @Override
    public String login() {
        return null;
    }

    public void addTeacher(Teacher teacher) throws SystemException {
if(teacherRepo.existsByPhone(teacher.getPhone())){
    throw new SystemException("מס' הטלפון קיים במערכת, לא ניתן לבצע רישום נוסף");
        }
        teacherRepo.save(teacher);
    }

    public void updateTeacher(Teacher teacher) throws SystemException {
        Teacher teacherFromData = teacherRepo.findById(teacher.getId()).orElseThrow(()->new SystemException("מורה זה לא קיים במערכת"));
        if(teacherRepo.existsByPhone(teacher.getPhone()) && !teacher.getPhone().equals(teacherFromData.getPhone())) {
            throw new SystemException("מס' הטלפון קיים במערכת, לא ניתן לעדכן");
        }
        teacherFromData.setFirstName(teacher.getFirstName());
        teacherFromData.setLastName(teacher.getLastName());
        teacherFromData.setPhone(teacher.getPhone());
        teacherFromData.setPassword(teacher.getPassword());
        teacherFromData.setNumClass(teacher.getNumClass());
        teacherRepo.saveAndFlush(teacherFromData);
        }

    public void deleteTeacher(int teacherId) throws SystemException {
        if(!teacherRepo.existsById(teacherId)){
            throw new SystemException("מורה לא קיים במערכת, לא ניתן למחוק");
        }
        teacherRepo.deleteById(teacherId);
    }
    public List<Teacher> getAllTeachers() throws SystemException {
     return teacherRepo.findAll();
      }
    public Teacher getOneTeacher(int teacherId) throws SystemException {
        return teacherRepo.findById(teacherId).orElseThrow(()->new SystemException("מורה לא קיים במערכת"));
    }


    public void addStudent(Student student) throws SystemException {
        if(studentRepo.existsByStudentId(student.getStudentId())){
            throw new SystemException("תלמיד קיים במערכת, לא ניתן לבצע רישום נוסף");
        }
        studentRepo.save(student);
    }

    public void updateStudent(Student student) throws SystemException {
        Student studentFromData = studentRepo.findById(student.getId()).orElseThrow(()->new SystemException("תלמיד זה לא קיים במערכת"));
        if(studentRepo.existsByStudentId(student.getStudentId()) && !student.getStudentId().equals(studentFromData.getStudentId())) {
            throw new SystemException("מס' ת.ז. קיים במערכת, לא ניתן לעדכן");
        }
        studentFromData.setStudentId(student.getStudentId());
        studentFromData.setFirstName(student.getFirstName());
        studentFromData.setLastName(student.getLastName());
        studentFromData.setPhone(student.getPhone());
        studentFromData.setNumClass(student.getNumClass());
        studentFromData.setPickupAddress(student.getPickupAddress());
        studentFromData.setNumBus(student.getNumBus());
        studentRepo.saveAndFlush(studentFromData);
    }

    public void deleteStudent(int idStudent) throws SystemException {
        if(!studentRepo.existsById(idStudent)){
            throw new SystemException("תלמיד לא קיים במערכת, לא ניתן למחוק");
        }
        studentRepo.deleteById(idStudent);
    }
    public List<Student> getAllStudents() throws SystemException {
        return studentRepo.findAll();
    }

    public Student getOneStudent(int idStudent) throws SystemException {
        return studentRepo.findById(idStudent).orElseThrow(()->new SystemException("תלמיד לא קיים במערכת"));
    }
    public List<Student> getAllStudentsByClass(int numClass) throws SystemException {
        return studentRepo.findAllByNumClass(numClass);
    }
    //////////////////??????????????????????/////////////
    public void isStudentNotTravel(int idStudent){
        studentRepo.isNotTravel(idStudent);
    }

    public void isStudentTravel(int idStudent){
        studentRepo.isTravel(idStudent);
    }

    public void whatCause(String cause ,int idStudent){
        studentRepo.whatCause(cause ,idStudent);
    }

    public void whichHour(String hour ,int idStudent){
        studentRepo.whichHour(hour ,idStudent);
    }

    public List<Student> getAllStudentsToTravelByBus(int numBus){
        return studentRepo.findAllByNumBus(numBus);
    }


}
