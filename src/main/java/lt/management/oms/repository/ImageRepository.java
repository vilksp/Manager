package lt.management.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.management.oms.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
