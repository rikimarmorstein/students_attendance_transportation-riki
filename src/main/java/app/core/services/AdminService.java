package app.core.services;

import app.core.auth.JwtUtil;
import app.core.auth.UserCredentials;
import app.core.entities.School;
import app.core.exception.SystemException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.login.LoginException;
import java.util.List;

@Service
@Transactional
public class AdminService extends ClientService{

    @Getter
    @Value("${admin-phone}")
    private String phone;

    @Getter
    @Value("${admin-password}")
    private String password;
    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public String login(UserCredentials userCredentials) throws SystemException  {
        if (!(userCredentials.getPhone().equalsIgnoreCase(this.phone)
                && userCredentials.getPassword().equals(this.password))) {
            throw new SystemException("טלפון/מייל שגוי!");
        }
        userCredentials.setId(-1);
        userCredentials.setName("ADMIN");
        return this.jwtUtil.generateToken(userCredentials);
    }

    public void addSchool(School school, int adminId) throws SystemException {
if(schoolRepo.existsBySchoolName(school.getSchoolName())){
    throw new SystemException("שם בית ספר קיים במערכת, לא ניתן לבצע רישום נוסף");
        }
        schoolRepo.save(school);
    }

    public void updateSchool(School school, int adminId) throws SystemException {
        School schoolFromData = schoolRepo.findById(school.getId()).orElseThrow(()->new SystemException("בית ספר זה לא קיים במערכת"));
        if(schoolRepo.existsByPhone(school.getPhone()) && !school.getPhone().equals(schoolFromData.getPhone())) {
            throw new SystemException("מס' הטלפון קיים במערכת, לא ניתן לעדכן");
        }if(schoolRepo.existsBySchoolName(school.getSchoolName()) && !school.getSchoolName().equals(schoolFromData.getSchoolName())) {
            throw new SystemException("שם בית הספר קיים במערכת, לא ניתן לעדכן");
        }
        schoolFromData.setSchoolName(school.getSchoolName());
        schoolFromData.setAddress(school.getAddress());
        schoolFromData.setPhone(school.getPhone());
        schoolFromData.setPassword(school.getPassword());

        schoolRepo.saveAndFlush(schoolFromData);
        }

    public void deleteSchool(int schoolId, int adminId) throws SystemException {
        if(!schoolRepo.existsById(schoolId)){
            throw new SystemException("בית ספר לא קיים במערכת, לא ניתן למחוק");
        }
        schoolRepo.deleteById(schoolId);
    }
    public List<School> getAllSchools(int adminId) throws SystemException {
     return schoolRepo.findAll();
      }
    public School getOneSchool(int schoolId, int adminId) throws SystemException {
        return schoolRepo.findById(schoolId).orElseThrow(()->new SystemException("בית ספר לא קיים במערכת"));
    }



}
