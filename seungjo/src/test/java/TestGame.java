import org.nhnacademy.tip.Game;

public class TestGame {
    public static void main(String[] args) {
        Game game = new Game(1200, 800);

        game.setVisible(true);
        game.run();
    }
}
