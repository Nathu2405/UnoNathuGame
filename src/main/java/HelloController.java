package com.uno.uno_backend;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/game")  // Grouping game endpoints under /game
public class HelloController {

    private UnoGame game;

    @GetMapping("/start-game")
    public String startGame() {
        game = new UnoGame();
        return "Game Started";
    }

    @GetMapping("/hand")
    public List<String> getCurrentPlayerHand() {
        if(game == null) return List.of("Game not started");

        List<String> handStrings = new ArrayList<>();
        for (Card c : game.getCurrentPlayerHand()) {
            handStrings.add(c.toString());
        }
        return handStrings;
    }

    @PostMapping("/play")
    public String playCard(@RequestParam("color") String color, @RequestParam("value") String value) {
        if(game == null) return "Game not started";

        try {
            Card.Color c = Card.Color.valueOf(color.toUpperCase());
            Card.Value v = Card.Value.valueOf(value.toUpperCase());

            if(v != Card.Value.WILD) {
                c = Card.Color.valueOf(color.toUpperCase());
            }

            Card cardToPlay = new Card(c, v);

            boolean success = game.playCard(cardToPlay);

            return success ? "Played: " + cardToPlay : "Invalid move or card not in hand.";
        } catch(IllegalArgumentException e) {
            return "Invalid color/value.";
        }
    }

    @GetMapping("/draw-card")
    public String drawCard() {
        game.drawCardForCurrentPlayer();
        return "Card Drawn";
    }

    @GetMapping("/top-card")
    public String getTopCard(){
        if(game == null) return "Game not started";

        Card top = game.getTopCard();
        return top.toString();
    }

    @GetMapping("/end-turn")
    public String endTurn() {
        if(game == null) return "Game not started";

        game.nextTurn();
        return "Turn ended. It's now " + game.getCurrentPlayer().getName() + "'s turn.";
    }

    @GetMapping("/current-player")
    public String currentPlayer() {
        if(game == null) return "Game not started";
        return game.getCurrentPlayer().getName();
    }

    @GetMapping("/winner")
    public String getWinner() {

        return game.isGameOver() ? "Winner: " + game.getWinnerName() : "No winner yet.";
    }

}
