package app.core.services;

import app.core.entities.School;
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

    public void addSchool(School school) throws SystemException {
if(schoolRepo.existsBySchoolName(school.getSchoolName())){
    throw new SystemException("שם בית ספר קיים במערכת, לא ניתן לבצע רישום נוסף");
        }
        schoolRepo.save(school);
    }

    public void updateSchool(School school) throws SystemException {
        School schoolFromData = schoolRepo.findById(school.getId()).orElseThrow(()->new SystemException("בית ספר זה לא קיים במערכת"));
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

    public void deleteSchool(int schoolId) throws SystemException {
        if(!schoolRepo.existsById(schoolId)){
            throw new SystemException("בית ספר לא קיים במערכת, לא ניתן למחוק");
        }
        schoolRepo.deleteById(schoolId);
    }
    public List<School> getAllSchools() throws SystemException {
     return schoolRepo.findAll();
      }
    public School getOneSchool(int schoolId) throws SystemException {
        return schoolRepo.findById(schoolId).orElseThrow(()->new SystemException("בית ספר לא קיים במערכת"));
    }



}
