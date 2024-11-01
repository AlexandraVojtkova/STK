package sk.umb.systemSTK.persistent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TK extends JpaRepository<sk.umb.systemSTK.persistent.entity.TK, Long>, JpaSpecificationExecutor<sk.umb.systemSTK.persistent.entity.TK> {
}
