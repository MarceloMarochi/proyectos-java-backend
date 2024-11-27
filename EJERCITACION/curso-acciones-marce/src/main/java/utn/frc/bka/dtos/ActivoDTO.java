package utn.frc.bka.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivoDTO {
    private Long activoId;
    private String nombreActivo;

    @Override
    public String toString() {
        return "ActivoDTO{" +
                "activoId=" + activoId +
                ", nombreActivo='" + nombreActivo + '\'' +
                '}';
    }
}
