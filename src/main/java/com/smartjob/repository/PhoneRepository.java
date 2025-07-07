package com.smartjob.repository;

import com.smartjob.model.Phone;
import com.smartjob.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,String> {
}
