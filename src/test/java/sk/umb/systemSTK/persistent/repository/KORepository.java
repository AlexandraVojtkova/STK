package sk.umb.systemSTK.persistent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sk.umb.systemSTK.persistent.entity.KOEntity;

public interface KORepository extends JpaRepository<KOEntity, Long>, JpaSpecificationExecutor<KOEntity> {
}
