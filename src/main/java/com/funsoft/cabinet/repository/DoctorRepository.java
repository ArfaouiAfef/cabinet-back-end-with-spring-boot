package com.funsoft.cabinet.repository;

import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Speciality;
import org.hibernate.type.SpecialOneToOneType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    public List<Doctor> findBySpeciality(Speciality speciality);
    //select d from Doctor d where d.firstName = :firstName JPQL----> SQL
    public List<Doctor> findByFirstname(String firstname);
    //select d from Doctor d where d.firstName = :firstName OR d.lastName = :name JPQL--->SQL
    public List<Doctor> findByFirstnameOrLastname(String firstname,String lastname);

    @Query(value = "SELECT d FROM Doctor d WHERE d.speciality = :spec AND (d.firstname LIKE :pseudo OR d.lastname LIKE :pseudo)")
    public List<Doctor> search(@Param("spec") Speciality speciality, @Param("pseudo") String pseudo);
}
