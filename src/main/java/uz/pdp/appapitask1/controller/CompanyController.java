package uz.pdp.appapitask1.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapitask1.entity.Company;
import uz.pdp.appapitask1.payload.ApiResponse;
import uz.pdp.appapitask1.payload.CompanyDto;
import uz.pdp.appapitask1.service.CompanyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping
    public List<Company> getCompanies() {
        List<Company> companies = companyService.getCompanies();
        return companies;
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id) {
        Company company = companyService.getCompanyById(id);
        return company;
    }

    @PostMapping
    public ApiResponse addCompany(@Valid @RequestBody CompanyDto companyDto) {
        ApiResponse apiResponse = companyService.addCompany(companyDto);
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ApiResponse editCompany(@Valid @RequestBody CompanyDto companyDto,@PathVariable Integer id){
        ApiResponse apiResponse = companyService.editCompany(id, companyDto);
        return apiResponse;
    }
    @DeleteMapping("/id")
    public ApiResponse deleteCompany(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.deleteCompany(id);
        return apiResponse;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
