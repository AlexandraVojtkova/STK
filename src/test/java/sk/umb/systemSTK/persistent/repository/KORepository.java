package sk.umb.systemSTK.persistent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sk.umb.systemSTK.persistent.entity.KOEntity;
import sk.umb.systemSTK.persistent.entity.TKEntity;

import java.util.Date;
import java.util.Optional;

public interface KORepository extends JpaRepository<KOEntity, Long>, JpaSpecificationExecutor<KOEntity> {
    Optional<KOEntity> findByDate(Date date);

    Optional<KOEntity> findByControlType(String controlType);

    Optional<KOEntity> findByCategory(String category);

    Optional<KOEntity> findByTechnicianId(Long technicianId);

    Optional<KOEntity> findByPrice(int price);
}
