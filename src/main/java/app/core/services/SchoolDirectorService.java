package app.core.services;

import app.core.auth.JwtUtil;
import app.core.auth.UserCredentials;
import app.core.entities.School;
import app.core.entities.Student;
import app.core.entities.Teacher;
import app.core.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.LoginException;
import java.util.List;
@Service
@Transactional
public class SchoolDirectorService extends ClientService{
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(UserCredentials userCredentials) throws LoginException {
        if (schoolRepo.existsByPhoneAndPassword(userCredentials.getPhone(), userCredentials.getPassword())) {
            School school = schoolRepo.findByPhoneAndPassword(userCredentials.getPhone(),
                    userCredentials.getPassword());
            userCredentials.setId(school.getId());
            userCredentials.setName(school.getSchoolName());
            return this.jwtUtil.generateToken(userCredentials);
        }
        throw new LoginException("טלפון וסיסמא שגויים!");
    }


    public void updateSchool(School school , int schoolId) throws SystemException {
        School schoolFromData = schoolRepo.findById(school.getId()).orElseThrow(()->new SystemException("בית ספר זה לא קיים במערכת"));
        if(school.getId() != schoolId){
            throw new SystemException("הינך מנסה לערוך בית ספר אחר. באפשרותך לערוך רק את הפרטים שלך");
        }
        if(schoolRepo.existsByPhone(school.getPhone()) && !school.getPhone().equals(schoolFromData.getPhone())) {
            throw new SystemException("מס' הטלפון קיים במערכת, לא ניתן לעדכן");
        }if(schoolRepo.existsBySchoolName(school.getSchoolName()) && !school.getSchoolName().equals(schoolFromData.getSchoolName())) {
            throw new SystemException("שם בית הספר קיים במערכת, לא ניתן לעדכן");
        }
        schoolFromData.setSchoolName(school.getSchoolName());
        schoolFromData.setAddress(school.getAddress());
        schoolFromData.setPhone(school.getPhone());

        schoolRepo.saveAndFlush(schoolFromData);
    }


    public void addTeacher(Teacher teacher) throws SystemException {
        if(teacherRepo.existsByPhone(teacher.getPhone())){
            throw new SystemException("מס' הטלפון קיים במערכת, לא ניתן לבצע רישום נוסף");
        }
        teacherRepo.save(teacher);
    }

    public void updateTeacher(Teacher teacher , int schoolId) throws SystemException {
        Teacher teacherFromData = teacherRepo.findById(teacher.getId()).orElseThrow(()->new SystemException("מורה זה לא קיים במערכת"));
        if (schoolId != teacherFromData.getSchool().getId()){
            throw new SystemException("המורה לא נמצא בבית הספר הזה , לא ניתן לעדכן אותו");
        }
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

    public void deleteTeacher(int teacherId , int schoolId) throws SystemException {
        Teacher teacherFromDb = teacherRepo.findById(teacherId).orElseThrow(() -> new SystemException("מורה לא קיים במערכת"));
        if (schoolId != teacherFromDb.getSchool().getId()){
            throw new SystemException("המורה לא נמצא בבית הספר הזה , לא ניתן למחוק אותו");
        }
        if(!teacherRepo.existsById(teacherId)){
            throw new SystemException("מורה לא קיים במערכת, לא ניתן למחוק");
        }
        teacherRepo.deleteById(teacherId);
    }
    public List<Teacher> getAllTeachers(int schoolId) throws SystemException {
        return teacherRepo.findAllBySchoolId(schoolId);
    }
    public Teacher getOneTeacher(int teacherId , int schoolId) throws SystemException {
       Teacher teacher= teacherRepo.findById(teacherId).orElseThrow(() -> new SystemException("מורה לא קיים במערכת"));
        if (teacher.getSchool().getId() != schoolId){
            throw new SystemException("מורה לא קיים בבית ספר זה.");
        }
        return teacher;
    }


    public void addStudent(Student student) throws SystemException {
        if(studentRepo.existsByStudentId(student.getStudentId())){
            throw new SystemException("תלמיד קיים במערכת, לא ניתן לבצע רישום נוסף");
        }
        studentRepo.save(student);
    }

    public void updateStudent(Student student, int schoolId) throws SystemException {
        Student studentFromData = studentRepo.findById(student.getId()).orElseThrow(()->new SystemException("תלמיד זה לא קיים במערכת"));
        if (schoolId != studentFromData.getSchool().getId()){
            throw new SystemException("התלמיד לא נמצא בבית הספר הזה , לא ניתן לעדכן אותו");
        }
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
        studentFromData.setPassword(student.getPassword());

        studentRepo.saveAndFlush(studentFromData);
    }

    public void deleteStudent(int studentId, int schoolId) throws SystemException {
        Student studentFromDb = studentRepo.findById(studentId).orElseThrow(() -> new SystemException("תלמיד לא קיים במערכת"));
        if (schoolId != studentFromDb.getSchool().getId()){
            throw new SystemException("התלמיד לא נמצא בבית הספר הזה , לא ניתן למחוק אותו");
        }
        if(!studentRepo.existsById(studentId)){
            throw new SystemException("תלמיד לא קיים במערכת, לא ניתן למחוק");
        }
        studentRepo.deleteById(studentId);
    }
    public List<Student> getAllStudents(int schoolId) throws SystemException {
        return studentRepo.findAllBySchoolId(schoolId);
    }

    public Student getOneStudent(int studentId , int schoolId) throws SystemException {
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new SystemException("תלמיד לא קיים במערכת"));
        if (student.getSchool().getId() != schoolId){
            throw new SystemException("תלמיד לא קיים בבית ספר זה.");
        }
        return student;
    }
    public List<Student> getAllStudentsByClass(int numClass ,int schoolId) throws SystemException {
        return studentRepo.findAllByNumClassAndSchoolId(numClass, schoolId);
    }

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
