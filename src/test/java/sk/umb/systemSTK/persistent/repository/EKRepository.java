package sk.umb.systemSTK.persistent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sk.umb.systemSTK.persistent.entity.EKEntity;

public interface EKRepository extends JpaRepository<EKEntity, Long>, JpaSpecificationExecutor<EKEntity> {


}
