package com.cognixia.jump.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    public Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET payment_card = :paymentCard WHERE user.id = :userId", nativeQuery = true)
    public int updatePayment(@Param(value="paymentCard") String paymentCard,@Param(value="userId") int userId);
}
