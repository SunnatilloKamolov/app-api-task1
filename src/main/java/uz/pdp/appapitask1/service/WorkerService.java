package uz.pdp.appapitask1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appapitask1.entity.Department;
import uz.pdp.appapitask1.entity.Worker;
import uz.pdp.appapitask1.payload.ApiResponse;
import uz.pdp.appapitask1.payload.WorkerDto;
import uz.pdp.appapitask1.repository.DepartmentRepository;
import uz.pdp.appapitask1.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Worker> getWorkers() {
        List<Worker> workers = workerRepository.findAll();
        return workers;
    }

    public Worker getWorkerById(@PathVariable Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isPresent())
            return optionalWorker.get();
        return null;
    }

    public ApiResponse addWorker(@RequestBody WorkerDto workerDto) {
        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new ApiResponse("Bunday Worker mavjud", false);
        Department department = new Department();
        department.setName(department.getName());
        Department savedDepartment = departmentRepository.save(department);
        Worker worker = new Worker();
        worker.setFullName(worker.getFullName());
        worker.setPhoneNumber(worker.getPhoneNumber());
        worker.setDepartment(savedDepartment);
        workerRepository.save(worker);
        return new ApiResponse("Worker qo'shildi", true);
    }

    public ApiResponse editWorker(@PathVariable Integer id, @RequestBody WorkerDto workerDto) {
        boolean existsByPhoneNumberAndIdNot = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);
        if (existsByPhoneNumberAndIdNot)
            return new ApiResponse("Bunday telefon raqamli worker mavjud", false);
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new ApiResponse("Bunday id li worker mavjud emas", false);
        Worker worker = optionalWorker.get();
        worker.setFullName(workerDto.getFullName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        Department department = new Department();
        department.setName(workerDto.getName());
        workerRepository.save(worker);
        departmentRepository.save(department);
        return new ApiResponse("Worker tahrirlandi", true);
    }public ApiResponse deleteWorker(@PathVariable Integer id){
        try {
            workerRepository.deleteById(id);
            return new ApiResponse("Worker o'chirildi",true);
        }
        catch (Exception e){
            return new ApiResponse("Worker topilmadi",false);
    }}
}
