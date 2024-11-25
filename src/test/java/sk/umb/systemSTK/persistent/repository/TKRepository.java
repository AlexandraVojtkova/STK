package sk.umb.systemSTK.persistent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sk.umb.systemSTK.persistent.entity.EKEntity;
import sk.umb.systemSTK.persistent.entity.TKEntity;

import java.util.Date;
import java.util.Optional;

public interface TKRepository extends JpaRepository<TKEntity, Long>, JpaSpecificationExecutor<TKEntity> {
    Optional<TKEntity> findByDate(Date date);

    Optional<TKEntity> findByControlType(String controlType);

    Optional<TKEntity> findByEvaluationOfVehicle(String evaluationOfVehicle);

    Optional<TKEntity> findByECV(String ecv);

    Optional<TKEntity> findByCategory(String category);

    Optional<TKEntity> findByBrand(String brand);

    Optional<TKEntity> findByModel(String model);

    Optional<TKEntity> findByTechnicianId(Long technicianId);

    Optional<TKEntity> findByPrice(int price);
}
