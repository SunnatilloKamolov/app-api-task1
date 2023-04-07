package uz.pdp.appapitask1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapitask1.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);
}
