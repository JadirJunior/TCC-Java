package prjtcc.Controllers;

import animatefx.animation.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import prjtcc.PrjTCC;
import prjtcc.classes.Dialog;
import prjtcc.classes.StaticKeys;
import prjtcc.model.Funcionario;
import prjtcc.model.LoginComands;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane anchPane;
    
    @FXML
    private CheckBox check;
    
    @FXML
    private StackPane stack;
    
    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtSenha;
    
    @FXML
    private Hyperlink btnCadastro;
     
    @FXML
    private Button btnClose;
    
    LoginComands login = new LoginComands();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if (login.manterConectado()) {   
            PrjTCC.changeScreen("principal", StaticKeys.getNome());
            return;
        }
        new ZoomIn(anchPane).play();
        PrjTCC.addOnChangeListeners(new PrjTCC.onChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
            if (login.manterConectado() && PrincipalController.loggout == false) {
               if (check.isSelected()) {
                    StaticKeys.setManter_conectado(true);
                }
            PrjTCC.changeScreen("principal", StaticKeys.getNome());
            return;
        }
                if (newScreen.equals("login"))
                    new ZoomIn(anchPane).play();
                
                txtUsuario.setText("");
                txtSenha.setText("");
            }
        });
        stack.toBack();
    }
    
    @FXML
    public void abrirCadastro(ActionEvent event) {
        PrjTCC.changeScreen("cadastro");
    }
    
    @FXML
    public void btnLogin(ActionEvent event) throws IOException {
        LoginComands login= new LoginComands();
        if (txtUsuario.getText().equals("") || txtSenha.getText().equals("")) {
            return;
        }
        boolean res = login.session(new Funcionario(txtUsuario.getText(), txtSenha.getText()));
       if (res) {
           if (check.isSelected()) {
                StaticKeys.setManter_conectado(true);
                System.out.println("Alow");
            }
           Dialog.showDialog("Login","Bem vindo, " + StaticKeys.getNome(), "Ok!", true, stack);
       } else {
           Dialog.showDialog("Login", "Usuário ou senha Incorretos!", "Ok!", false, stack);
       }
    }
    
    @FXML
    public void btnClose(ActionEvent event) {
        PrjTCC.getStageThis().close();
    }
    
}
