package uz.pdp.appapitask1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appapitask1.entity.Company;
import uz.pdp.appapitask1.entity.Department;
import uz.pdp.appapitask1.payload.ApiResponse;
import uz.pdp.appapitask1.payload.DepartmentDto;
import uz.pdp.appapitask1.repository.CompanyRepository;
import uz.pdp.appapitask1.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    public Department getDepartmentById(@PathVariable Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent())
            return optionalDepartment.get();
        return null;
    }

    public ApiResponse addDepartment(@RequestBody DepartmentDto departmentDto) {
        boolean existsByName = departmentRepository.existsByName(departmentDto.getName());
        if (existsByName)
            return new ApiResponse("Bunday department mavjud", false);
        Company company = new Company();
        company.setCorpName(departmentDto.getCorpName());
        company.setDirectorName(departmentDto.getDirectorName());
        Company savedCompany = companyRepository.save(company);
        Department department = new Department();
        department.setName(department.getName());
        department.setCompany(savedCompany);
        departmentRepository.save(department);
        return new ApiResponse("Department qo'shildi", true);
    }
    public ApiResponse editDepartment(@PathVariable Integer id,@RequestBody DepartmentDto departmentDto){
        boolean existsByNameAndIdNot = departmentRepository.existsByNameAndIdNot(departmentDto.getName(), id);
        if (existsByNameAndIdNot)
            return new ApiResponse("Bunday name li department mavjud",false);
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if(!optionalDepartment.isPresent())
            return new ApiResponse("Bunday id li department mavjud emas",false);
        Department department=optionalDepartment.get();
        department.setName(departmentDto.getName());
        Company company=department.getCompany();
        company.setCorpName(departmentDto.getCorpName());
        company.setDirectorName(departmentDto.getDirectorName());
        departmentRepository.save(department);
        companyRepository.save(company);
        return new ApiResponse("Department tahrirlandi",true);

    }
    public ApiResponse deleteDepartment(@PathVariable Integer id){
        try {
            departmentRepository.deleteById(id);
            return new ApiResponse("Department o'chirildi",true);
        }
        catch (Exception e){
            return new ApiResponse("Department topilmadi",false);
        }
    }
}
