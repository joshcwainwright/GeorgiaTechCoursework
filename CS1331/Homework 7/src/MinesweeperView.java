import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * Minesweeper View Class.
 * @author josh w
 * @version 1
 */
public class MinesweeperView extends Application {
    private ComboBox diff;
    private TextArea nameInput;
    private Stage tempStage;

    @Override
    /**
     * start method.
     */
    public void start(Stage stage) {
        stage.setTitle("Minesweeper");
        tempStage = stage;

        //start
        BorderPane startBp = new BorderPane();
        Scene start = new Scene(startBp, 275, 150);

        ////Welcome
        Text welcome = new Text("Start Menu");
        welcome.setFont(new Font("Veranda", 20));
        BorderPane welcomeBp = new BorderPane();
        welcomeBp.setCenter(welcome);
        startBp.setTop(welcomeBp);

        ////diffuculty select/name
        BorderPane middleBp = new BorderPane();
        BorderPane diffBp = new BorderPane();
        BorderPane nameBp = new BorderPane();
        diff = new ComboBox();
        diff.setPrefSize(150, 10);
        diff.getItems().add("Easy");
        diff.getItems().add("Medium");
        diff.getItems().add("Hard");
        Text diffText = new Text("Difficulty Select:");
        diffText.setFont(new Font("Veranda", 15));
        BorderPane.setAlignment(diffText, Pos.CENTER);
        diffBp.setLeft(diffText);
        BorderPane.setAlignment(diff, Pos.CENTER);
        diffBp.setRight(diff);
        middleBp.setTop(diffBp);
        nameInput = new TextArea();
        nameInput.setPrefSize(150, 15);
        nameInput.setWrapText(true);
        nameInput.setFont(new Font("Veranda", 15));
        BorderPane.setAlignment(nameInput, Pos.CENTER);
        nameBp.setRight(nameInput);
        Text name = new Text("Name:");
        name.setFont(new Font("Veranda", 15));
        BorderPane.setAlignment(name, Pos.CENTER);
        nameBp.setLeft(name);
        middleBp.setBottom(nameBp);
        startBp.setCenter(middleBp);

        ////Start Button
        Button startButton = new Button("START");
        startButton.setFont(new Font("Veranda", 20));
        startButton.setOnAction(a -> {
            if (diff.getValue() != null && !(nameInput.getText().trim().isEmpty())) {
                playMinesweeper(stage, calcDiff(), nameInput.getText());
            }
        });
        BorderPane.setAlignment(startButton, Pos.CENTER);
        startBp.setBottom(startButton);
        stage.setScene(start);
        stage.show();

        //lost screen
        BorderPane lost = new BorderPane();
        Scene lostGame = new Scene(lost, 200, 75);
        String lostString = String.format("You Lost, %s", nameInput.getText());
        Text lostText = new Text(lostString);
        lostText.setFont(new Font("Veranda", 20));
        BorderPane.setAlignment(lostText, Pos.CENTER);
        lost.setTop(lostText);
        Button newGame = new Button("New Game");
        newGame.setPrefSize(150, 50);
        newGame.setFont(new Font("Veranda", 20));
        BorderPane.setAlignment(newGame, Pos.CENTER);
        lost.setBottom(newGame);
    }

    /**
     * main method.
     * @param args path for lib
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * calcDiff method that determines difficulty from dropdown box.
     * @return difficulty enum
     */
    private Difficulty calcDiff() {
        if (diff.getValue() == null) {
            return Difficulty.EASY;
        } else if (diff.getValue().equals("Easy")) {
            return Difficulty.EASY;
        } else if (diff.getValue().equals("Medium")) {
            return Difficulty.MEDIUM;
        } else {
            return Difficulty.HARD;
        }
    }

    /**
     * Make Grid method that creates button grid.
     * @param game Minesweeper game
     * @return Grid Pane of buttons
     */
    private GridPane makeGrid(MinesweeperGame game) {
        GridPane buttonGrid = new GridPane();
        for (int r = 0; r < 15; r++) {
            for (int c = 0; c < 15; c++) {
                Button click = new Button("X");
                click.setPrefSize(50, 50);
                click.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        int x = buttonGrid.getColumnIndex(click);
                        int y = buttonGrid.getRowIndex(click);
                        Tile[] reveal = game.check(y, x);
                        if (reveal == null) {
                            return;
                        } else if (reveal.length == 1 && reveal[0].isMine()) {
                            lostGame();
                        } else {
                            for (Tile tile: reveal) {
                                String tileText = String.format("%d", tile.getBorderingMines());
                                Button newBut = new Button(tileText);
                                newBut.setPrefSize(50, 50);
                                buttonGrid.add(newBut, tile.getX(), tile.getY());
                                if (game.isWon()) {
                                    wonGame();
                                }
                            }
                        }
                    }
                });
                buttonGrid.add(click, c, r);
            }
        }
        return buttonGrid;
    }

    /**
     * playMinesweeper method that initiates game scene.
     * @param stage main stage
     * @param difficulty enum of difficulty
     * @param name String of name
     */
    private void playMinesweeper(Stage stage, Difficulty difficulty, String name) {
        MinesweeperGame mine = new MinesweeperGame(difficulty);
        BorderPane gameBp = new BorderPane();
        Scene game = new Scene(gameBp, 450, 450);
        GridPane grid = makeGrid(mine);
        gameBp.setCenter(grid);
        stage.setScene(game);
        stage.show();
    }

    /**
     * lost game method.
     */
    private void lostGame() {
        BorderPane lost = new BorderPane();
        Scene lostGame = new Scene(lost, 200, 75);
        String lostString = String.format("You Lost, %s", nameInput.getText());
        Text lostText = new Text(lostString);
        lostText.setFont(new Font("Veranda", 20));
        BorderPane.setAlignment(lostText, Pos.CENTER);
        lost.setTop(lostText);
        Button newGame = new Button("New Game");
        newGame.setPrefSize(150, 50);
        newGame.setFont(new Font("Veranda", 20));
        newGame.setOnAction(new LoadHandler());
        BorderPane.setAlignment(newGame, Pos.CENTER);
        lost.setBottom(newGame);
        tempStage.setScene(lostGame);
        tempStage.show();
    }

    /**
     * won game method
     */
    private void wonGame() {
        BorderPane won = new BorderPane();
        Scene wonGame = new Scene(won, 200, 75);
        String wonString = String.format("Congratulations, %s", nameInput.getText());
        Text wonText = new Text(wonString);
        wonText.setFont(new Font("Veranda", 20));
        BorderPane.setAlignment(wonText, Pos.CENTER);
        won.setTop(wonText);
        Button newGame = new Button("New Game");
        newGame.setPrefSize(150, 50);
        newGame.setFont(new Font("Veranda", 20));
        newGame.setOnAction(new LoadHandler());
        BorderPane.setAlignment(newGame, Pos.CENTER);
        won.setBottom(newGame);
        tempStage.setScene(wonGame);
        tempStage.show();
    }

    /**
     * loadhandler for new game.
     */
    private class LoadHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent a) {
            start(tempStage);
        }
    }
}