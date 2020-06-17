package mng.data;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import mng.model.Renter;
import mng.model.RenterUnit;

public interface PropertyRepository extends JpaRepository<RenterUnit, Long> {

	Page<RenterUnit> findByRenter(Renter renter,Pageable pageable);
}
