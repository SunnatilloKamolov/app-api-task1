package uz.pdp.appapitask1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appapitask1.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByCorpName(String corpName);
    boolean existsByCorpNameAndIdNot(String corpName, Integer id);
}
