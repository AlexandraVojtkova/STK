package sk.umb.systemSTK.persistent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sk.umb.systemSTK.persistent.entity.TechnicianEntity;

public interface TechnicianRepository extends JpaRepository<TechnicianEntity, Long>, JpaSpecificationExecutor<TechnicianEntity> {
}
