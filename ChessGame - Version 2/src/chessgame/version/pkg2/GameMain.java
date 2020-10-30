package chessgame.version.pkg2;

import java.io.IOException;

public class GameMain {
    public static void main(String[] args) throws IOException {
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);
        model.addObserver(view);
    }
}