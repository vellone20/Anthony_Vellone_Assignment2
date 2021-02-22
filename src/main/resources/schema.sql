create table team(

    id LONG PRIMARY Key AUTO_INCREMENT,
    teamName varchar(50),
    continent varchar(50),
    gamesPlayed int(4),
    gamesWon int(4),
    gamesDrawn int(4),
    gamesLost int(4),
    points int(4)
);