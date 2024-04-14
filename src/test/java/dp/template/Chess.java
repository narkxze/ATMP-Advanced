package dp.template;

public class Chess extends Game {
    @Override
    void endPlay() {
        System.out.println("Chess Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Chess Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Chess Game Started. Enjoy the game!");
    }
}
