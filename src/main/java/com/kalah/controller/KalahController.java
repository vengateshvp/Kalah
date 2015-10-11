package com.kalah.controller;

import com.kalah.model.KalahMap;
import com.kalah.service.KalahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"kalaMap", "currentPlayer" , "gameOver"})
public class KalahController {

    @Autowired
    KalahService kalahService;

    /**
     * Prepare the Kala Board.
     */
    private KalahMap<Long, Integer> kalaMap = new KalahMap<>();

    @RequestMapping("/")
    public String loadKalahBoard(@RequestParam(value = "pitNumber", required = false) Long pitNumber, ModelMap map) {

    	map.addAttribute("gameOver", "In Progress..." );
        if (null != pitNumber) {
            // Make moves in the board only when there is a valid Pit Number received int he request.
            kalahService.play(pitNumber);

            if (kalahService.isGameOver()) {
            	KalahMap<Long, Integer> kalahMap = kalahService.getKalahBoard();
            	String gameStatus = "Tie";
            	if (kalahMap.get(6L).intValue() > kalahMap.get(13L).intValue() ){
            		gameStatus = "Player1 is Winner...";
            	}
            	else if (kalahMap.get(6L).intValue() < kalahMap.get(13L).intValue() ){
            		gameStatus = "Player2 is Winner...";
            	}
                map.addAttribute("gameOver", "Game Over !!! "+ gameStatus );
            }
        }

        // Setting required values to be shown on the screen
        map.addAttribute("kalahBoard", kalahService.getKalahBoard());

        map.addAttribute("currentPlayer", kalahService.getCurrentPlayer());

        return "kalahGame";
    }
}
