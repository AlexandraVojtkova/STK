package sk.umb.systemSTK.persistent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EK extends JpaRepository<sk.umb.systemSTK.persistent.entity.EK, Long>, JpaSpecificationExecutor<sk.umb.systemSTK.persistent.entity.EK> {

}
