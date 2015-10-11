package com.kalah.standalone;

import com.kalah.model.KalahMap;
import com.kalah.model.Player;

import java.util.Scanner;

public class MainLogic {

    /**
     * Prepare the Kala Board.
     */
    private KalahMap<Long, Integer> kalaMap = new KalahMap<>();

    /**
     * Player 1.
     */
    private static Player player1 = new Player("P1");

    /**
     * Player 2.
     */
    private static Player player2 = new Player("P2");


    /**
     * Fills the board
     * @param pitNumber
     * @param currentPlayer
     * @return
     */
    public boolean fillPits(Long pitNumber, Player currentPlayer) {
        boolean repeatPlayerTurn = false;

        Integer pitValue = kalaMap.get(--pitNumber);
        kalaMap.put(pitNumber++, 0);
        for (int counter = 1; counter <= pitValue.intValue(); counter++) {
            if (currentPlayer.equals(player1)) {
                if (pitNumber == 13) {
                    pitNumber++;
                }
            } else {
                if (pitNumber == 6) {
                    pitNumber++;
                }
            }
            pitNumber = pitNumber % 14;
            kalaMap.put(pitNumber, kalaMap.get(pitNumber) + 1);
            pitNumber++;
        }

        if (6 == --pitNumber || 13 == pitNumber) {
            repeatPlayerTurn = true;
        } else {
            repeatPlayerTurn = false;
        }

        // Check if the last stone was filled in an empty pit.
        Player tmpPlayer = getPlayerByPitNumber(pitNumber);
        if (null != tmpPlayer && kalaMap.get(pitNumber) == 1) {
            Long oppositeIndex = 12 - pitNumber;
            Integer tmpStones = 1 + kalaMap.get(oppositeIndex);
            kalaMap.put(pitNumber, 0);
            kalaMap.put(oppositeIndex, 0);

            if (tmpPlayer.equals(player1) && currentPlayer.equals(tmpPlayer)) {
                // Take all stones from P2's pit & current pit
                kalaMap.put(6L, tmpStones + kalaMap.get(6));
            } else if (tmpPlayer.equals(player2) && currentPlayer.equals(tmpPlayer)) {
                // Take all stones from P1's pit & current pit
                kalaMap.put(13L, tmpStones + kalaMap.get(13));
            }
        }

        System.out.println(kalaMap);
        return repeatPlayerTurn;
    }

    /**
     * Identify where the last stone was placed. Is it in Player1's side? or Player 2's side.
     * @param pitNumber
     * @return
     */
    public Player getPlayerByPitNumber(Long pitNumber) {
        if (pitNumber >= 0 && pitNumber < 6) {
            return player1;
        } else if (pitNumber > 6 && pitNumber < 13) {
            return player2;
        } else {
            return null;
        }
    }

    /**
     * Checks if the Game is over
     *
     * @return
     */
    public boolean isGameOver() {
        boolean status = false;
        if (kalaMap.getPlayer1TotalStones() == 0 || kalaMap.getPlayer2TotalStones() == 0) {
            status = true;
        }
        return status;
    }

    /**
     * Rename this method to main to run it individually.
     * If you want to run the project, make sure you rename this back to main2 or any other name other than main.
     * @param args
     */
    public static void main2(String[] args) {

        MainLogic board = new MainLogic();


        Scanner inputScanner = new Scanner(System.in);
        Player currentPlayer = player1;
        boolean gameOver = false;
        while (!gameOver) {
            System.out.println("Enter pit number for player "
                    + currentPlayer.getName());
            boolean playAgain = board.fillPits(inputScanner.nextLong(), currentPlayer);
            if (board.isGameOver()) {
                gameOver = true;
            }
            if (!playAgain) {
                if (currentPlayer.equals(player1)) {
                    currentPlayer = player2;
                } else {
                    currentPlayer = player1;
                }
            }
        }

    }
}
