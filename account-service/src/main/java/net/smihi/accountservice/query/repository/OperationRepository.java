package net.smihi.accountservice.query.repository;

import net.smihi.accountservice.query.entites.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OperationRepository  extends JpaRepository<Operation,Integer> {
    List<Operation> findByAccount_Id(String id);
}
