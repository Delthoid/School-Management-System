/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Preloader;
import javafx.application.Preloader.ProgressNotification;
import javafx.application.Preloader.StateChangeNotification;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Simple Preloader Using the ProgressBar Control
 *
 * @author delth
 */
public class NewFXPreloader extends Preloader {
    
    private Stage preloaderStage;
    private Scene scene;
    
    SplashScreenController splash = new SplashScreenController();
    
    public NewFXPreloader()
    {
        
    }
    
    @Override
    public void init() throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
        scene = new Scene(root);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.preloaderStage = stage;
        
        //Set preloader stage scene and show stage
        preloaderStage.setScene(scene);
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.show();
    }
    
    @Override
    public void handleStateChangeNotification(StateChangeNotification scn) {
        StateChangeNotification.Type type = scn.getType();
        switch(type)
        {
            case BEFORE_START:
                //Called after init and before start is called
                System.out.println("BEFORE START");
                preloaderStage.hide();
                break;
        }
    }
    
    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        if(pn instanceof ProgressNotification){
            splash.progressbar.setProgress(pn.getProgress());
        }
    }    
    
}
