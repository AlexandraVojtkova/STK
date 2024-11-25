package sk.umb.systemSTK.persistent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sk.umb.systemSTK.persistent.entity.EKEntity;

import java.util.Date;
import java.util.Optional;

public interface EKRepository extends JpaRepository<EKEntity, Long>, JpaSpecificationExecutor<EKEntity> {
    Optional<EKEntity> findByDate(Date date);

    Optional<EKEntity> findByControlType(String controlType);

    Optional<EKEntity> findByEvaluationOfVehicle(String evaluationOfVehicle);

    Optional<EKEntity> findByECV(String ecv);

    Optional<EKEntity> findByCategory(String category);

    Optional<EKEntity> findByBrand(String brand);

    Optional<EKEntity> findByModel(String model);

    Optional<EKEntity> findBySystemOfEmissions(String systemOfEmissions);

    Optional<EKEntity> findByTechnicianId(Long technicianId);

    Optional<EKEntity> findByPrice(int price);

}
