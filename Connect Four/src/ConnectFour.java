public class ConnectFour {

    public static void main(String[] args) {
        int state = 1;
        boolean useGui = true;//sets if we want the cli or gui.
        
        if (useGui) {
            gui Gui = new gui();
            while (state != -1) {//checks if program is in quitting stage
                switch (state) {
                    case 1://runtime
                        Gui.updateBoard();
                        if (Gui.getHasWon()) {	
                            state = 2;
                        } else if (Gui.getHasDraw()) {
                            state = 3;
                        } else if (Gui.getNewGame()) {
                            Gui = new gui();
                            state = 0;
                        }
                        break;
                    case 2://endgame with winner
                        Gui.showWon();
                        if (Gui.getQuit()) {
                            state = -1;
                        } else  if (Gui.getNewGame()) {
                            Gui = new gui();
                            state = 1;
                        }
                        break;
                    case 3://endgame with drawgame
                        Gui.showDraw();
                        if (Gui.getQuit()) {
                            state = -1;
                        } else if (Gui.getNewGame()) {
                            Gui = new gui();
                            state = 1;
                        }
                        break;
                }
            }
        } else {
            cli Cli = new cli();
            while (state != -1) {//checks if program is in quitting stage
                switch (state) {
                    case 0:
                        Cli.runtime();
                        if (Cli.getHasWon()) {
                            state = 1;
                        } else if (Cli.getHasDraw()) {
                            state = 2;
                        }
                        break;
                    case 1://prints endgame with winner
                        Cli.showWin();
                        if (Cli.getQuit()) {
                            state = -1;
                        } else if (Cli.getNewGame()) {
                            Cli = new cli();
                            state = 0;
                        }
                        break;
                    case 2://prints end game with draw game
                        Cli.showDraw();
                        if (Cli.getQuit()) {
                            state = -1;
                        } else if (Cli.getNewGame()) {
                            Cli = new cli();
                            state = 0;
                        }
                        break;
                }
            }
        }
    }
}
