package uz.pdp.appapitask1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapitask1.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
