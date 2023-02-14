package cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
}