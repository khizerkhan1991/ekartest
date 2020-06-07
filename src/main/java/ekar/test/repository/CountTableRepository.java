package ekar.test.repository;

import ekar.test.model.CountTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountTableRepository extends
        JpaRepository<CountTable,Integer> {
}
