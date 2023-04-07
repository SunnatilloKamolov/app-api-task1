package uz.pdp.appapitask1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapitask1.entity.Department;
import uz.pdp.appapitask1.payload.ApiResponse;
import uz.pdp.appapitask1.payload.DepartmentDto;
import uz.pdp.appapitask1.service.DepartmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping
    List<Department> getDepartments() {
        List<Department> departments = departmentService.getDepartments();
        return departments;
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@Valid @PathVariable Integer id) {
        Department departmentById = departmentService.getDepartmentById(id);
        return departmentById;
    }

    @PostMapping
    public ApiResponse addDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        ApiResponse apiResponse = departmentService.addDepartment(departmentDto);
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse editDepartment(@Valid @PathVariable Integer id,@RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.editDepartment(id, departmentDto);
        return apiResponse;
    }@DeleteMapping("/{id}")
    public ApiResponse deleteDepartment(@Valid @PathVariable Integer id){
        ApiResponse apiResponse = departmentService.deleteDepartment(id);
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
