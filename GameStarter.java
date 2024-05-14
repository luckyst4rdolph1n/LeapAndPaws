public class GameStarter {

    public static void main(String[] args) {
        GameFrame gf = new GameFrame(800, 600);
        gf.connectToServer();
        gf.createPlayers();
        gf.setUpGUI();
    }
    
}
