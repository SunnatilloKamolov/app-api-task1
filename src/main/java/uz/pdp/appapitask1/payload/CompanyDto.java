package uz.pdp.appapitask1.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotNull(message = "corpName bo'sh bo'lmasligi kerak")
    private String corpName;
    @NotNull(message = "directorName bo'sh bo'lmasligi kerak")
    private String directorName;
    @NotNull(message = "street bo'sh bo'lmasligi kerak")
    private String street;
    @NotNull(message = "homeNumber bo'sh bo'lmasligi kerak")
    private String homeNumber;
}
