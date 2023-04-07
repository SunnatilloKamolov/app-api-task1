package uz.pdp.appapitask1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapitask1.entity.Worker;
import uz.pdp.appapitask1.payload.ApiResponse;
import uz.pdp.appapitask1.payload.WorkerDto;
import uz.pdp.appapitask1.repository.WorkerRepository;
import uz.pdp.appapitask1.service.WorkerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @GetMapping
     public   List<Worker> getWorkers() {
        List<Worker> workers = workerService.getWorkers();
        return workers;
    }

    @GetMapping("/{id}")
    public Worker getWorkerById(@PathVariable Integer id){
        Worker worker = workerService.getWorkerById(id);
        return worker;
    }@PostMapping
    public ApiResponse addWorker(@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ApiResponse editWorker(@PathVariable Integer id,@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.editWorker(id, workerDto);
        return apiResponse;
    }@DeleteMapping("/{id}")
    public ApiResponse deleteWorker(@PathVariable Integer id){
        ApiResponse apiResponse = workerService.deleteWorker(id);
        return  apiResponse;
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
