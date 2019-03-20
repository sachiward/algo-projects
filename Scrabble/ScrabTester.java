import java.util.ArrayList;

public class ScrabTester implements ScrabbleAI {
    /**
     * The GateKeeper through which this Incrementalist accesses the Board.
     */
    private GateKeeper gateKeeper;
    private static final boolean[] ALL_TILES = {true, true, true, true, true, true, true};

    @Override
    public void setGateKeeper(GateKeeper gateKeeper) {
        this.gateKeeper = gateKeeper;
    }

    @Override
    public ScrabbleMove chooseMove() {
        if (gateKeeper.getSquare(Location.CENTER) == Board.DOUBLE_WORD_SCORE) {
            return firstMove();
        }
        return makeMove();
    }

    private ScrabbleMove firstMove() {
        ArrayList<Character> hand = gateKeeper.getHand();
        String bestWord = null;
        int bestScore = -1;
        for (int i = 0; i < hand.size(); i++) {
            for (int j = 0; j < hand.size(); j++) {
                for (int k = 0; k < hand.size(); k++) {
                    for (int l = 0; l < hand.size(); l++) {
                        for (int m = 0; m < hand.size(); m++) {
                            for (int n = 0; n < hand.size(); n++) {
                                if (i != j && k != j && k != l && i != k && i != l && j != l && m != i && m != j && m != k && n != i && n != j && n != k) {
                                    try {
                                        char a = hand.get(i);
                                        if (a == '_') {
                                            a = 'E'; // This could be improved slightly by trying all possibilities for the blank
                                        }
                                        char b = hand.get(j);
                                        if (b == '_') {
                                            b = 'E'; // This could be improved slightly by trying all possibilities for the blank
                                        }
                                        char c = hand.get(k);
                                        if (c == '_') {
                                            c = 'E'; // This could be improved slightly by trying all possibilities for the blank
                                        }
                                        char d = hand.get(l);
                                        if (d == '_') {
                                            d = 'E'; // This could be improved slightly by trying all possibilities for the blank
                                        }
                                        char e = hand.get(m);
                                        if (e == ' ') {
                                            e = 'E';
                                        }
                                        String word = "" + a + b + c + d + e;
                                        gateKeeper.verifyLegality(word, Location.CENTER, Location.HORIZONTAL);
                                        int score = gateKeeper.score(word, Location.CENTER, Location.HORIZONTAL);
                                        if (score > bestScore) {
                                            bestScore = score;
                                            bestWord = word;
                                        }

                                    } catch (IllegalMoveException e) {

                                    }

                                }

                            }

                        }
                    }
                }
                if (bestScore > -1) {
                    return new PlayWord(bestWord, Location.CENTER, Location.HORIZONTAL);
                }
                for (int m = 0; m < hand.size(); m++) {
                    for (int n = 0; n < hand.size(); n++) {
                        for (int o = 0; o < hand.size(); o++) {
                            if (m != n && n != o && m != o) {
                                try {
                                    char a = hand.get(m);
                                    if (a == '_') {
                                        a = 'E'; // This could be improved slightly by trying all possibilities for the blank
                                    }
                                    char b = hand.get(n);
                                    if (b == '_') {
                                        b = 'E'; // This could be improved slightly by trying all possibilities for the blank
                                    }
                                    char c = hand.get(o);
                                    if (c == '_') {
                                        c = 'E'; // This could be improved slightly by trying all possibilities for the blank
                                    }
                                    String word = "" + m + n + o;
                                    gateKeeper.verifyLegality(word, Location.CENTER, Location.HORIZONTAL);
                                    int score = gateKeeper.score(word, Location.CENTER, Location.HORIZONTAL);
                                    if (score > bestScore) {
                                        bestScore = score;
                                        bestWord = word;
                                    }
                                } catch (IllegalMoveException e) {
                                    //
                                }
                            }

                        }

                    }
                }


            }
        }
        if (bestScore > -1) {
            return new PlayWord(bestWord, Location.CENTER, Location.HORIZONTAL);
        }
        return new ExchangeTiles(ALL_TILES);
    }

    private ScrabbleMove makeMove() {
        ArrayList<Character> hand = gateKeeper.getHand();
        PlayWord bestMove = null;
        int bestScore = -1;
        for (int i = 0; i < hand.size(); i++) {
            for (int j = 0; j < hand.size(); j++) {
                for (int k = 0; k < hand.size(); k++) {
                    char c = hand.get(i);
                    char d = hand.get(j);
                    char p = hand.get(k);
                    if (c == '_') {
                        c = 'A'; // This could be improved slightly by trying all possibilities for the blank
                    }
                    for (String word : new String[]{" " + c + d + p, " " + c + p + d, " " + d + p + c, " " + d + c + p, " " + p + d + c, " " + p + c + d, d + " " + p + c, d + " " + c + p, d + p + " " + c, d + p + c + " ", d + c + p + " ", d + c + " " + p, c + " " + p + d, c + " " + d + p, c + d + p + " ", c + d + " " + p, c + p + " " + d, c + p + d + " ", p + " " + c + d, p + " " + d + c, p + d + " " + c, p + d + c + " ", p + c + " " + d, p + c + d + " "}) {
                        for (int row = 0; row < Board.WIDTH; row++) {
                            for (int col = 0; col < Board.WIDTH; col++) {
                                Location location = new Location(row, col);
                                for (Location direction : new Location[]{Location.HORIZONTAL, Location.VERTICAL}) {
                                    try {
                                        gateKeeper.verifyLegality(word, location, direction);
                                        int score = gateKeeper.score(word, location, direction);
                                        if (score > bestScore) {
                                            bestScore = score;
                                            bestMove = new PlayWord(word, location, direction);
                                        }
                                    } catch (IllegalMoveException e) {
                                        // It wasn't legal; go on to the next one
                                    }
                                }
                            }
                        }
                    }
                    for (String word : new String[]{" " + c + d, " " + d + c, d + " " + c, c + " " + d, d + c + " ", c + d + " "}) {
                        for (int row = 0; row < Board.WIDTH; row++) {
                            for (int col = 0; col < Board.WIDTH; col++) {
                                Location location = new Location(row, col);
                                for (Location direction : new Location[]{Location.HORIZONTAL, Location.VERTICAL}) {
                                    try {
                                        gateKeeper.verifyLegality(word, location, direction);
                                        int score = gateKeeper.score(word, location, direction);
                                        if (score > bestScore) {
                                            bestScore = score;
                                            bestMove = new PlayWord(word, location, direction);
                                        }
                                    } catch (IllegalMoveException e) {
                                        // It wasn't legal; go on to the next one
                                    }
                                }
                            }
                        }
                    }
                    for (String word : new String[]{" " + " " + " " + " " + " " + " " + c, " " + " " + " " + " " + " " + c + " ", " " + " " + " " + " " + c + " " + " ", " " + " " + " " + c + " " + " " + " ", " " + " " + c + " " + " " + " " + " ", c + " " + " " + " " + " " + " " + " ", c + " " + " " + " " + " " + " " + " "}) {
                        for (int row = 0; row < Board.WIDTH; row++) {
                            for (int col = 0; col < Board.WIDTH; col++) {
                                Location location = new Location(row, col);
                                for (Location direction : new Location[]{Location.HORIZONTAL, Location.VERTICAL}) {
                                    try {
                                        gateKeeper.verifyLegality(word, location, direction);
                                        int score = gateKeeper.score(word, location, direction);
                                        if (score > bestScore) {
                                            bestScore = score;
                                            bestMove = new PlayWord(word, location, direction);
                                        }
                                    } catch (IllegalMoveException e) {
                                        // It wasn't legal; go on to the next one
                                    }
                                }
                            }
                        }
                    }
                    for (String word : new String[]{" " + " " + " " + " " + c + " ", " " + " " + " " + c + " " + " ", " " + c + " " + " " + " " + " ", c + " " + " " + " " + " " + " ", " " + " " + " " + " " + " " + c, " " + " " + c + " " + " " + " "}) {
                        for (int row = 0; row < Board.WIDTH; row++) {
                            for (int col = 0; col < Board.WIDTH; col++) {
                                Location location = new Location(row, col);
                                for (Location direction : new Location[]{Location.HORIZONTAL, Location.VERTICAL}) {
                                    try {
                                        gateKeeper.verifyLegality(word, location, direction);
                                        int score = gateKeeper.score(word, location, direction);
                                        if (score > bestScore) {
                                            bestScore = score;
                                            bestMove = new PlayWord(word, location, direction);
                                        }
                                    } catch (IllegalMoveException e) {
                                        // It wasn't legal; go on to the next one
                                    }
                                }
                            }
                        }
                    }
                    for (String word : new String[]{" " + " " + " " + c + " ", " " + " " + c + " " + " ", " " + c + " " + " " + " ", c + " " + " " + " " + " ", " " + " " + " " + " " + c}) {
                        for (int row = 0; row < Board.WIDTH; row++) {
                            for (int col = 0; col < Board.WIDTH; col++) {
                                Location location = new Location(row, col);
                                for (Location direction : new Location[]{Location.HORIZONTAL, Location.VERTICAL}) {
                                    try {
                                        gateKeeper.verifyLegality(word, location, direction);
                                        int score = gateKeeper.score(word, location, direction);
                                        if (score > bestScore) {
                                            bestScore = score;
                                            bestMove = new PlayWord(word, location, direction);
                                        }
                                    } catch (IllegalMoveException e) {
                                        // It wasn't legal; go on to the next one
                                    }
                                }
                            }
                        }
                    }
                    for (String word : new String[]{" " + c + " " + " ", " " + " " + c + " ", c + " " + " " + " ", c + " " + " " + " "}) {
                        for (int row = 0; row < Board.WIDTH; row++) {
                            for (int col = 0; col < Board.WIDTH; col++) {
                                Location location = new Location(row, col);
                                for (Location direction : new Location[]{Location.HORIZONTAL, Location.VERTICAL}) {
                                    try {
                                        gateKeeper.verifyLegality(word, location, direction);
                                        int score = gateKeeper.score(word, location, direction);
                                        if (score > bestScore) {
                                            bestScore = score;
                                            bestMove = new PlayWord(word, location, direction);
                                        }
                                    } catch (IllegalMoveException e) {
                                        // It wasn't legal; go on to the next one
                                    }
                                }
                            }
                        }
                    }
                    for (String word : new String[]{" " + c + " ", " " + " " + c, c + " " + " "}) {
                        for (int row = 0; row < Board.WIDTH; row++) {
                            for (int col = 0; col < Board.WIDTH; col++) {
                                Location location = new Location(row, col);
                                for (Location direction : new Location[]{Location.HORIZONTAL, Location.VERTICAL}) {
                                    try {
                                        gateKeeper.verifyLegality(word, location, direction);
                                        int score = gateKeeper.score(word, location, direction);
                                        if (score > bestScore) {
                                            bestScore = score;
                                            bestMove = new PlayWord(word, location, direction);
                                        }
                                    } catch (IllegalMoveException e) {
                                        // It wasn't legal; go on to the next one
                                    }
                                }
                            }
                        }
                    }
                    for (String word : new String[]{c + " ", " " + c}) {
                        for (int row = 0; row < Board.WIDTH; row++) {
                            for (int col = 0; col < Board.WIDTH; col++) {
                                Location location = new Location(row, col);
                                for (Location direction : new Location[]{Location.HORIZONTAL, Location.VERTICAL}) {
                                    try {
                                        gateKeeper.verifyLegality(word, location, direction);
                                        int score = gateKeeper.score(word, location, direction);
                                        if (score > bestScore) {
                                            bestScore = score;
                                            bestMove = new PlayWord(word, location, direction);
                                        }
                                    } catch (IllegalMoveException e) {
                                        // It wasn't legal; go on to the next one
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (bestMove != null) {
            return bestMove;
        }
        return new ExchangeTiles(ALL_TILES);
    }
}