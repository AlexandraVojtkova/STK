package sk.umb.systemSTK.persistent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sk.umb.systemSTK.persistent.entity.TKEntity;

public interface TKRepository extends JpaRepository<TKEntity, Long>, JpaSpecificationExecutor<TKEntity> {
}
