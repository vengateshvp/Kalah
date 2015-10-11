package com.kalah.service;

import com.kalah.model.KalahMap;
import com.kalah.model.Player;
import org.springframework.stereotype.Service;

@Service
public class KalahService {

    /**
     * Prepare the Kala Board.
     */
    private static KalahMap<Long, Integer> kalaMap = new KalahMap<>();

    private static Player player1 = new Player("P1");

    private static Player player2 = new Player("P2");

    private static Player currentPlayer = player1;

    private static boolean gameOver = false;

    /**
     * Play by moving stones.
     *
     * @param pitNumber
     */
    public void play(Long pitNumber) {
        boolean playAgain = fillPits(pitNumber, currentPlayer);
        if (isGameOver()) {
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

    /**
     * Fills the board
     *
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
            

            if (tmpPlayer.equals(player1) && currentPlayer.equals(tmpPlayer)) {
                // Take all stones from P2's pit & current pit
            	Long oppositeIndex = 12 - pitNumber;
                Integer tmpStones = 1 + kalaMap.get(oppositeIndex);
                kalaMap.put(pitNumber, 0);
                kalaMap.put(oppositeIndex, 0);
                kalaMap.put(6L, tmpStones + kalaMap.get(6L));
            } else if (tmpPlayer.equals(player2) && currentPlayer.equals(tmpPlayer)) {
                // Take all stones from P1's pit & current pit
            	Long oppositeIndex = 12 - pitNumber;
                Integer tmpStones = 1 + kalaMap.get(oppositeIndex);
                kalaMap.put(pitNumber, 0);
                kalaMap.put(oppositeIndex, 0);
                kalaMap.put(13L, tmpStones + kalaMap.get(13L));
            }
        }

        System.out.println(kalaMap);
        return repeatPlayerTurn;
    }

    /**
     * Identify where the last stone was placed. Is it in Player1's side? or Player 2's side.
     *
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
     * Get the current player.
     *
     * @return
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Get the latest Kalah board.
     *
     * @return
     */
    public KalahMap getKalahBoard() {
        return kalaMap;
    }

    /**
     * Checks if the Game is over.
     *
     * @return
     */
   /* public boolean isGameOver() {
        boolean status = false;
        if (kalaMap.getPlayer1TotalStones() == 0 || kalaMap.getPlayer2TotalStones() == 0) {
            status = true;
        }
        return status;
    }*/
    
    public boolean isGameOver() {
        boolean status = false;
        if (kalaMap.getPlayer1TotalStones().intValue() == 0 || kalaMap.getPlayer2TotalStones().intValue() == 0) {
            status = true;
            pushPitValuesToKalahs();
            
        }
        return status;
    }
    
    /**
     * Move respective players pit stones to kalah after game over.
     */
    private void pushPitValuesToKalahs(){
    	for(Long pitNumber = 0L; pitNumber < 6; pitNumber++){
    		kalaMap.put(6L, kalaMap.get(6L) + kalaMap.get(pitNumber));
    		kalaMap.put(pitNumber, 0);
    	}
    	
    	for(Long pitNumber = 7L; pitNumber < 13; pitNumber++){
    		kalaMap.put(13L, kalaMap.get(13L) + kalaMap.get(pitNumber));
    		kalaMap.put(pitNumber, 0);
    	}
    }
    
}
