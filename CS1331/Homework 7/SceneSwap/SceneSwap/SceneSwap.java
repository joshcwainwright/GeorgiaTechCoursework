import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

import javafx.scene.control.Alert;

public class SceneSwap extends Application {
    
    private Scene ON_SCENE;
    private Scene OFF_SCENE;
    
    @Override
    public void start(Stage primaryStage) {
        createOnScene(primaryStage);
        createOffScene(primaryStage);
        
        primaryStage.setScene(OFF_SCENE);
        primaryStage.setTitle("SceneSwap - OFF Scene");
        primaryStage.show();
        
        Alert error = new Alert(Alert.AlertType.ERROR, "This is the content text.");
        error.setTitle("An ERROR has occurred :(");
        error.showAndWait();
    }

    /* Private helper to create the "light on" scene
     * Need to pass in a Stage for the MouseEvent handler.
     */
    private void createOnScene(Stage stage) {
        if(ON_SCENE == null) {
            VBox box = new VBox();
            ImageView bulb = new ImageView("images/On_Bulb.png");
            ImageView lightSwitch = new ImageView("images/LS_On.jpg");
            lightSwitch.setOnMouseClicked(e -> {
                    stage.setScene(OFF_SCENE);
                    stage.setTitle("SceneSwap - OFF Scene");
                });
            box.setBackground(new Background(new BackgroundFill(
                                                                Color.WHITE,
                                                                null,
                                                                null)));
            box.getChildren().addAll(bulb, lightSwitch);
            box.setAlignment(Pos.CENTER);
            ON_SCENE = new Scene(box, 300, 300);
        }
    }
    
    /* Private helper to create the "light off" scene
     * Need to pass in a Stage for the MouseEvent handler.
     */
    private void createOffScene(Stage stage) {
        if(OFF_SCENE == null) {
            VBox box = new VBox();
            ImageView bulb = new ImageView("images/Off_Bulb.png");
            ImageView lightSwitch = new ImageView("images/LS_Off.jpg");
            lightSwitch.setOnMouseClicked(e -> {
                    stage.setScene(ON_SCENE);
                    stage.setTitle("SceneSwap - ON Scene");
                });
            box.setBackground(new Background(new BackgroundFill(
                                                                Color.DARKGREY,
                                                                null,
                                                                null)));
            box.getChildren().addAll(bulb, lightSwitch);
            box.setAlignment(Pos.CENTER);
            OFF_SCENE = new Scene(box, 300, 300);
        }
    }
}
