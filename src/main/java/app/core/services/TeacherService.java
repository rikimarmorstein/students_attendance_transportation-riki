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
public class TeacherService extends  ClientService{

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public String login(UserCredentials userCredentials)  throws LoginException {
        if (teacherRepo.existsByPhoneAndPassword(userCredentials.getPhone(), userCredentials.getPassword())) {
            Teacher teacher = teacherRepo.findByPhoneAndPassword(userCredentials.getPhone(),
                    userCredentials.getPassword());

            userCredentials.setId(teacher.getId());

            userCredentials.setName(teacher.getFirstName()+ " "+ teacher.getLastName());
            return this.jwtUtil.generateToken(userCredentials);
        }
        throw new LoginException("טלפון וסיסמא שגויים!");
    }

    public void updateTeacher(Teacher teacher , int teacherId) throws SystemException {
        System.out.println(teacher.getId());
        Teacher teacherFromData = teacherRepo.findById(teacher.getId()).orElseThrow(()->new SystemException("מורה זה לא קיים במערכת"));
//        if (schoolId != teacherFromData.getSchool().getId()){
//            throw new SystemException("המורה לא נמצא בבית הספר הזה , לא ניתן לעדכן אותו");
//        }
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

    public Teacher getTeacherDetails(int teacherId) throws SystemException {
        Teacher teacherFromData = teacherRepo.findById(teacherId)
                .orElseThrow(() -> new SystemException("מורה זה אינו קיים במערכת"));
        return teacherFromData;
    }


    public List<Student> getAllStudents (int schoolId){
      return  this.studentRepo.findAllBySchoolId(schoolId);
    }
    public List<Student> getAllStudentsByClass (int numClass, int schoolId){
      return  this.studentRepo.findAllByNumClassAndSchoolId(numClass, schoolId);
    }

    public List<Student> getAllStudentsToTravel(int schoolId){
        return this.studentRepo.findAllBySchoolIdAndIsTravelTrue(schoolId);
    }
    public List<Student> getAllStudentsToTravelByBus(int schoolId, int numBus){
        return this.studentRepo.findAllBySchoolIdAndNumBusAndIsTravelTrue(schoolId, numBus);
    }
    public Student getOneStudent (int studentId) throws SystemException {
      return  this.studentRepo.findById(studentId).orElseThrow(() ->new SystemException("התלמיד אינו קיים במערכת"));
    }

    public boolean isStudentTravel (int studentId) throws SystemException {
        Student studentFromDb = this.studentRepo.findById(studentId).orElseThrow(() -> new SystemException("התלמיד אינו קיים במערכת"));
        return studentFromDb.isTravel();
    }
}
