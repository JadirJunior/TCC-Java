package prjtcc.Controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import prjtcc.Cadastro;
import prjtcc.PrjTCC;
import prjtcc.classes.Funcionario;
import prjtcc.model.LoginComands;


public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtSenha;
    
     @FXML
    private Hyperlink btnCadastro;
     
     @FXML
    private Button btnClose;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void abrirCadastro(ActionEvent event) {
        Cadastro prj = new Cadastro();
        PrjTCC.getStageThis().close();
         try {
             prj.start(new Stage());
         } catch (Exception ex) {
             Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    @FXML
    public void btnLogin(ActionEvent event) throws IOException {
        LoginComands login= new LoginComands();
        if (txtUsuario.getText().equals("") || txtSenha.getText().equals("")) {
            return;
        }
        boolean res = login.session(txtUsuario.getText(), txtSenha.getText());
       if (res) {
           alert(Alert.AlertType.INFORMATION, "Bem vindo, " + Funcionario.getNome());
           PrjTCC.changeScreen("principal", Funcionario.getNome());

       } else {
           alert(Alert.AlertType.INFORMATION, "Usuário ou senha incorretos!");
       }
    }
    
    
     
    
    
    private void alert(AlertType type, String message) {
        Alert alert= new Alert(type);
        alert.setHeaderText(message);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
    @FXML
    public void btnClose(ActionEvent event) {
        PrjTCC.getStageThis().close();
    }
    
}
