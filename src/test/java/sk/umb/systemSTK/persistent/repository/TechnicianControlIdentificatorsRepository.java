package sk.umb.systemSTK.persistent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sk.umb.systemSTK.persistent.entity.TechnicianControlIdentificatorsEntity;

import java.util.Optional;

public interface TechnicianControlIdentificatorsRepository extends JpaRepository<TechnicianControlIdentificatorsEntity, Long>, JpaSpecificationExecutor<TechnicianControlIdentificatorsEntity> {
    Optional<TechnicianControlIdentificatorsEntity> findByIdentifier(String identifier);
}
