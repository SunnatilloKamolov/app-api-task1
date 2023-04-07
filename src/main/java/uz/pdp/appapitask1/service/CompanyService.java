package uz.pdp.appapitask1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appapitask1.entity.Address;
import uz.pdp.appapitask1.entity.Company;
import uz.pdp.appapitask1.payload.ApiResponse;
import uz.pdp.appapitask1.payload.CompanyDto;
import uz.pdp.appapitask1.repository.AddressRepository;
import uz.pdp.appapitask1.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    public List<Company> getCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

    public Company getCompanyById(@PathVariable Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent())
            return optionalCompany.get();
        return null;
    }

    public ApiResponse addCompany(@RequestBody CompanyDto companyDto) {
        boolean existsByCorpName = companyRepository.existsByCorpName(companyDto.getCorpName());
        if (existsByCorpName)
            return new ApiResponse("Bunday kompaniya mavjud", false);
        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);
        Company company = new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(savedAddress);
        companyRepository.save(company);
        return new ApiResponse("Kompaniya qo'shildi", true);
    }

    public ApiResponse editCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto) {
        boolean existsByCorpNameAndIdNot = companyRepository.existsByCorpNameAndIdNot(companyDto.getCorpName(), id);
        if (existsByCorpNameAndIdNot)
            return new ApiResponse("Bunday corpName li kompaniya mavjud", false);
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("Bunday id li kompaniya mavjud emas", false);
        Company company = optionalCompany.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        Address address = company.getAddress();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        addressRepository.save(address);
        companyRepository.save(company);
        return new ApiResponse("Kompaniya tahrirlandi", true);
    }

    public ApiResponse deleteCompany(@PathVariable Integer id) {
        try {
            companyRepository.deleteById(id);
            return new ApiResponse("Kompaniya o'chirildi", true);
        }
        catch (Exception e){
            return new ApiResponse("Bunday kompaniya mavjud emas",false);
        }
    }

}
