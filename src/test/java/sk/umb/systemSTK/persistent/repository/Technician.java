package sk.umb.systemSTK.persistent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface Technician extends JpaRepository<sk.umb.systemSTK.persistent.entity.Technician, Long>, JpaSpecificationExecutor<sk.umb.systemSTK.persistent.entity.Technician> {
}
