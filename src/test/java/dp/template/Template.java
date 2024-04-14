package dp.template;

public class Template {
    public static void main(String[] args) {
        Game game = new Football();
        game.play();
        System.out.println();
        game = new Chess();
        game.play();
    }
}
