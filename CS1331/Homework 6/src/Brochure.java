import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;

/**
 * Brochure Class.
 * @author Joshua Waiwnright
 * @version 1
 */
public class Brochure extends Application {
    /**
     * Main Method.
     * @param args main args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    /**
     * start class.
     */
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("TravelBrochure");
        BorderPane border = new BorderPane();
        Rectangle blueRect = new Rectangle(0, 0, 300, 100);
        blueRect.setFill(Color.BLUE);
        Text title = new Text("WELCOME TO OOP-TOPIA!");
        title.setFill(Color.WHITE);
        StackPane stack = new StackPane();
        stack.getChildren().addAll(blueRect, title);
        Image putt = new Image("putt.jpg", true);
        ImageView puttView = new ImageView(putt);
        Text puttDes = new Text("Oop-Topia has the best putt putt courses in the entire world!");
        puttDes.setFill(Color.BLACK);
        puttDes.setFont(Font.font("Veranda", 25));
        Circle left = new Circle(0, 0, 100);
        left.setFill(Color.ORANGE);
        Circle right = new Circle(0, 0, 100);
        right.setFill(Color.GREEN);
        BorderPane.setAlignment(puttDes, Pos.CENTER);
        BorderPane.setAlignment(stack, Pos.CENTER);
        BorderPane.setAlignment(puttView, Pos.CENTER);
        BorderPane.setAlignment(left, Pos.CENTER);
        BorderPane.setAlignment(right, Pos.CENTER);
        border.setBottom(puttDes);
        border.setTop(stack);
        border.setCenter(puttView);
        border.setLeft(left);
        border.setRight(right);
        Scene scene = new Scene(border, 750, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
