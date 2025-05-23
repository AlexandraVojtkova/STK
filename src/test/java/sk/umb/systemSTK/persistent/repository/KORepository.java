package sk.umb.systemSTK.persistent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sk.umb.systemSTK.persistent.entity.KOEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface KORepository extends JpaRepository<KOEntity, Long>, JpaSpecificationExecutor<KOEntity> {
    List<KOEntity> findByVINKO(String vin);

    Optional<KOEntity> findByDate(Date date);

    Optional<KOEntity> findByControlType(String controlType);

    Optional<KOEntity> findByCategory(String category);

    List<KOEntity> findByTechnicianIdentifier_IdentifierAndTechnicianIdentifier_ControlType(String identifier, String controlType);

    Optional<KOEntity> findByPrice(int price);
}
