/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjtcc;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
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
public class PrjTCC extends Application {
    
    private static Stage stageThis;
    private static Scene PrincipalScene;
    private static Scene LoginScene;
    private double xOffSet = 0;
    private double yOffSet = 0;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent login = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        LoginScene  = new Scene(login);
        
        Parent Principal = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));
       PrincipalScene = new Scene(Principal);
        
        stage.initStyle(StageStyle.DECORATED.UNDECORATED);
        
        login.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
                xOffSet = event.getSceneX();
                yOffSet = event.getSceneY();
                
            }
        });
        
        login.setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            stage.setX(event.getScreenX()-xOffSet);
            stage.setY(event.getScreenY()-yOffSet);
        }
    });
        
        Principal.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
                xOffSet = event.getSceneX();
                yOffSet = event.getSceneY();
                
            }
        });
        
        Principal.setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            stage.setX(event.getScreenX()-xOffSet);
            stage.setY(event.getScreenY()-yOffSet);
        }
    });
        
        stage.setScene(LoginScene);
        stage.show();
        setStageThis(stage);
    }

    public static Stage getStageThis() {
        return stageThis;
    }

    public static void setStageThis(Stage stageThis) {
        PrjTCC.stageThis = stageThis;
    }
    
    public static void changeScreen(String src, Object userData) {
        switch (src) {
            case "login": 
                stageThis.setScene(LoginScene);
                notifyAllListeners(src, userData);
                break;
                
            case "principal":
                stageThis.setScene(PrincipalScene);
                notifyAllListeners(src, userData);
                break;
        }
    }
    
    public static void changeScreen(String src) {
        switch (src) {
            case "login": 
                stageThis.setScene(LoginScene);
                notifyAllListeners(src, null);
                break;
                
            case "principal":
                stageThis.setScene(PrincipalScene);
                notifyAllListeners(src, null);
                break;
        }
    }
    
    private static List<onChangeScreen> listeners = new ArrayList<>();
    
   
    public static interface onChangeScreen {
        void onScreenChanged(String newScreen, Object userData);
    }
    
    
    public static void addOnChangeListeners(onChangeScreen listener) {
        listeners.add(listener); 
    }
    
    
    public static void notifyAllListeners(String newScreen, Object userData) {
        for (onChangeScreen l: listeners) {
            l.onScreenChanged(newScreen, userData);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
