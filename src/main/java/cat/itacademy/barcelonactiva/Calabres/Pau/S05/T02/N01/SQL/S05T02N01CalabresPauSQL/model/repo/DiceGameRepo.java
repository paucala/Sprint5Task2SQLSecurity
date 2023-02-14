package cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.repo;


import cat.itacademy.barcelonactiva.Calabres.Pau.S05.T02.N01.SQL.S05T02N01CalabresPauSQL.model.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiceGameRepo extends JpaRepository<Game, Integer> {
}
