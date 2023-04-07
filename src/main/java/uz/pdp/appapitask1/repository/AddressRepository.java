package uz.pdp.appapitask1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapitask1.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
