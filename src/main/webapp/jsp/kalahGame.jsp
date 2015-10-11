<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

    <head>
        <title>:: Welcome to Kalah game ::</title>

        <link rel="stylesheet" href="/css/kalah.css"/>
        <script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="/js/kalah.js"></script>
    </head>

    <h1>
        Welcome to Kalah game
    </h1>

    <c:choose>
        <c:when test="${not empty gameOver}">
            ${gameOver}
        </c:when>

        <c:otherwise>

            Next player: ${currentPlayer.name}

            <div class="kalah-board">
                <div class="player2-kalah">
                    ${kalahBoard[13]}
                </div>
                <div class="kalah-pits">
                    <div class="player-2">
                        <div class="pit" pitNumber="13">${kalahBoard[12]}</div>
                        <div class="pit" pitNumber="12">${kalahBoard[11]}</div>
                        <div class="pit" pitNumber="11">${kalahBoard[10]}</div>
                        <div class="pit" pitNumber="10">${kalahBoard[9]}</div>
                        <div class="pit" pitNumber="9">${kalahBoard[8]}</div>
                        <div class="pit" pitNumber="8">${kalahBoard[7]}</div>
                    </div>
                    <div class="player-1">
                        <div class="pit" pitNumber="1">${kalahBoard[0]}</div>
                        <div class="pit" pitNumber="2">${kalahBoard[1]}</div>
                        <div class="pit" pitNumber="3">${kalahBoard[2]}</div>
                        <div class="pit" pitNumber="4">${kalahBoard[3]}</div>
                        <div class="pit" pitNumber="5">${kalahBoard[4]}</div>
                        <div class="pit" pitNumber="6">${kalahBoard[5]}</div>
                    </div>
                </div>
                <div class="player1-kalah">
                    ${kalahBoard[6]}
                </div>
            </div>
        </c:otherwise>
    </c:choose>

</html>