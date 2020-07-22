/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjtcc;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Jadir
 */
public class Cadastro extends Application {
    
    private static Stage stageThis;
    private double xOffSet = 0;
    private double yOffSet = 0;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastro.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.initStyle(StageStyle.DECORATED.UNDECORATED);
        
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
                xOffSet = event.getSceneX();
                yOffSet = event.getSceneY();
                
            }
        });
        
        
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            stage.setX(event.getScreenX()-xOffSet);
            stage.setY(event.getScreenY()-yOffSet);
        }
    });
        stage.setScene(scene);
        stage.show();
        setStageThis(stage);
    }

    public static Stage getStageThis() {
        return stageThis;
    }

    public static void setStageThis(Stage stageThis) {
        Cadastro.stageThis = stageThis;
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
