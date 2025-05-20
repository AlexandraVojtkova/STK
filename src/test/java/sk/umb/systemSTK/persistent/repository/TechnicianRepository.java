package sk.umb.systemSTK.persistent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sk.umb.systemSTK.persistent.entity.TechnicianEntity;

import java.util.List;

public interface TechnicianRepository extends JpaRepository<TechnicianEntity, Long>, JpaSpecificationExecutor<TechnicianEntity> {
    List<TechnicianEntity> findByNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String name, String lastName);

}
