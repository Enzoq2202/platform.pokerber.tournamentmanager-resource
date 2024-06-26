package insper.store.tournamentmanager;

import java.util.List;
import java.util.stream.Collectors;

public class TournamentManagerParser {

    // Converte TournamentManagerIn (DTO de entrada) para TournamentManagerModel (Entidade)
    public static TournamentManagerModel toModel(TournamentManagerIn tournamentIn) {
        return TournamentManagerModel.builder()
                .name(tournamentIn.name())
                .startTime(tournamentIn.startTime())
                .description(tournamentIn.description())
                .build();
    }

    // Converte TournamentManagerModel (Entidade) para TournamentManagerOut (DTO de saída)
    public static TournamentManagerOut toOut(TournamentManagerModel tournamentModel) {
        List<PlayerOut> playerOuts = tournamentModel.getPlayers().stream()
                                                     .map(player -> new PlayerOut(player.getId(), player.getName()))
                                                     .collect(Collectors.toList());
        return new TournamentManagerOut(
            tournamentModel.id(),
            tournamentModel.name(),
            tournamentModel.startTime(),
            tournamentModel.description(),
            playerOuts
        );
    }
    
    
    
    // Converte Player (Entidade) para PlayerOut (DTO de saída)
    public static PlayerOut toOut(Player player) {
        return PlayerOut.builder()
                .playerId(player.getId())
                .playerName(player.getName())
                .build();
    }

    // Se necessário, você poderia também incluir uma conversão de PlayerIn para Player
    // Este método seria útil se você precisar criar uma entidade Player a partir de um DTO
    public static Player toModel(PlayerIn playerIn, TournamentManagerModel tournament) {
        return Player.builder()
                .id(playerIn.playerId())
                .tournament(tournament)  // Assume que você já tem a entidade Tournament carregada
                .build();
    }

}
