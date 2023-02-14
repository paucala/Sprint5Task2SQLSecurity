package cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.controllers;

import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.dto.LoginDto;
import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.dto.RegisterDto;
import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.service.AuthenticationService;
import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.service.DiceGameServiceImp;
import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.dto.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//Aquesta classe es l'encarregada de fer el registre i el login dels usuaris
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;
    private final DiceGameServiceImp diceGameServiceImp;
//torna ok si el registre ha sigut correcte, si el nom ja està en ús torna un avís (i'm used), si el nom escollit és anonim
// el crea si es el primer i si no fa el login del jugador anonim (tothom que jugui en anonim jugara amb el mateix jugador)
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterDto request
    ) {
        if (request.getName().equals("ANONIMUS") && diceGameServiceImp.existingPlayerbyName("ANONIMUS")) {
            return ResponseEntity.ok(service.authenticate(new LoginDto(request.getEmail(), request.getPassword())));
        } else if (service.register(request) != null){
            return ResponseEntity.ok(service.register(request));
        } else {
            return new ResponseEntity<>(HttpStatus.IM_USED);
        }

    }
    //genera un token a partir d'un jugador i contrasenya, en el cas dels jugadors Anonims els login es fa amb una contrasenya buida
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody LoginDto request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
