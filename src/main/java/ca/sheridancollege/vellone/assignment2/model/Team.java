package ca.sheridancollege.vellone.assignment2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team implements Serializable {

    private Long id;
    private String teamName;
    private String continent;
    private int gamesPlayed = 0;
    private int gamesWon = 0;
    private int gamesDrawn = 0;
    private int gamesLost = 0;
    private int points = 0;

}
