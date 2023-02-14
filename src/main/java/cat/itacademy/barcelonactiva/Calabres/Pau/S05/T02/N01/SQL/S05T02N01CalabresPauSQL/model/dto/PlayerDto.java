package cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.dto;

import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlayerDto {
    private int player_id;
    private String name;
    private String email;
    private LocalDateTime createDateTime;
    private float exitpercent;
    private Role role;
    public PlayerDto(String name) {
        this.name = name;
    }
}
