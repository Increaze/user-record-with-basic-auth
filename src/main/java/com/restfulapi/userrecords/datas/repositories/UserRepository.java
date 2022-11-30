package com.restfulapi.userrecords.datas.repositories;

import com.restfulapi.userrecords.datas.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByFirstname(String firstname, Pageable pageable);
}
