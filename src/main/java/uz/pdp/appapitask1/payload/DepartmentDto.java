package uz.pdp.appapitask1.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    @NotNull
    private String name;
    @NotNull
    private String corpName;
    @NotNull
    private String directorName;
    @NotNull
    private String address;
}
