package uz.pdp.appapitask1.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    @NotNull(message = "fullName bo'sh bo'lmasligi kerak ")
    private String fullName;
    @NotNull(message = "phoneNumber bo'sh bo'lmasligi kerak ")
    private String phoneNumber;
    @NotNull(message = "street bo'sh bo'lmasligi kerak ")
    private String street;
    @NotNull(message = "homeNumber bo'sh bo'lmasligi kerak ")
    private String homeNumber;
    @NotNull(message = "name bo'sh bo'lmasligi kerak ")
    private String name;
}
