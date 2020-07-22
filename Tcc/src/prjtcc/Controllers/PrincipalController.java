/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjtcc.Controllers;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import prjtcc.PrjTCC;
import prjtcc.classes.Converter;
import prjtcc.classes.Funcionario;
import prjtcc.model.LoginComands;

/**
 * FXML Controller class
 *
 * @author Jadir
 */
public class PrincipalController implements Initializable {
    
    @FXML
    private Button btnUser, btnUpdate, btnEditUser;
    
    @FXML
    private ImageView imageUser, imageUpdate;

    @FXML
    private Label lblNomeUsuario;

    @FXML
    private Pane Container;
    
    @FXML
    private AnchorPane Account;
    
     @FXML
    private TextField txtUsuario, txtSenha;
    
    private static File file = null;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       Account.setVisible(false);
       PrjTCC.addOnChangeListeners(new PrjTCC.onChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("principal"))
                    lblNomeUsuario.setText(userData.toString());
                if (Funcionario.getFoto() == null) {
                    
                } else {
                    renderImage(Funcionario.getFoto());
                }
            }
        });
    }    
    
    @FXML
   public void btnUserClick(ActionEvent event) throws IOException, Exception {
       if (Account.visibleProperty().equals(true)) {
           return;
       }
      Account.setVisible(true);
      if (Funcionario.getFoto() != null) {
          imageUpdate.setImage(renderImageReturn(Funcionario.getFoto()));
      }
      
      txtUsuario.setText(Funcionario.getNome());
      txtSenha.setText(Funcionario.getSenha());
      lblNomeUsuario.setText(Funcionario.getNome());
    }
   
   private void renderImage(BufferedImage image) {
        WritableImage wr = null;
                  wr = new WritableImage(image.getWidth(), image.getHeight());
                PixelWriter pw = wr.getPixelWriter();
                for (int x = 0; x < image.getWidth(); x++) {
                        for (int y = 0; y < image.getHeight(); y++) {
                          pw.setArgb(x, y, image.getRGB(x, y));
            }
                   Funcionario.setFoto(image);
                   imageUser.setImage(wr);
                   lblNomeUsuario.setText(Funcionario.getNome());
        }
   }
   
   private Image renderImageReturn(BufferedImage image) {
        WritableImage wr = null;
                  wr = new WritableImage(image.getWidth(), image.getHeight());
                PixelWriter pw = wr.getPixelWriter();
                for (int x = 0; x < image.getWidth(); x++) {
                        for (int y = 0; y < image.getHeight(); y++) {
                          pw.setArgb(x, y, image.getRGB(x, y));
            }
                        Funcionario.setFoto(image);
                        imageUser.setImage(wr);
                        lblNomeUsuario.setText(Funcionario.getNome());
                        
        }
                return wr;
   }
   
   @FXML
   public void updateUser(ActionEvent event) {
       byte[] imagem_byte = null;
        try {
            if (file != null) {
            BufferedImage image = ImageIO.read(file);
            imagem_byte = Converter.imageToBytes(image, file);
            
             LoginComands login = new LoginComands();
             boolean res = login.updateUser(txtUsuario.getText(),txtSenha.getText(), imagem_byte);
             if (res){
                 showMessage(AlertType.INFORMATION, "Usuário atualizado com sucesso!");
                 renderImage(image);
                 Funcionario.setFoto(image);
                 lblNomeUsuario.setText(Funcionario.getNome());
             } 
             else {
                 showMessage(AlertType.WARNING, "Usuário já existe");
              }
            }
            
            else {
                LoginComands login = new LoginComands();
             boolean res = login.updateUser(txtUsuario.getText(),txtSenha.getText());
             if (res){
                 showMessage(AlertType.INFORMATION, "Usuário atualizado com sucesso!");
                 lblNomeUsuario.setText(Funcionario.getNome());
             } 
             else {
                 showMessage(Alert.AlertType.WARNING, "Usuário já existe!");
              }
             
            }
        } catch (IOException ex) {
            //Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            showMessage(AlertType.ERROR, "Ocorreu algum erro, tente novamente!");
        }
   }
   
   @FXML
   public void changeImage(ActionEvent event) throws FileNotFoundException, IOException {
       FileChooser chooser = new FileChooser();
       chooser.getExtensionFilters().add(new ExtensionFilter("Imagens","*.jpg", "*.png", "*.jpeg"));
       file = chooser.showOpenDialog(new Stage());
       if (file!=null)
            imageUpdate.setImage(new Image("file:///"+file.getAbsolutePath()));
   }
   
   void showMessage(AlertType type, String message) { 
       Alert alert = new Alert(type);
       alert.setContentText(message);
       alert.show();
   }
   
   @FXML
    private void handleClickUser(ActionEvent event) {
        if (Account.isVisible()) {
            return;
        } else {
            Account.setVisible(true);
            if (Funcionario.getFoto() == null) {
                
            } else {
                imageUpdate.setImage(renderImageReturn(Funcionario.getFoto()));
            }
            txtUsuario.setText(Funcionario.getNome());
            txtSenha.setText(Funcionario.getSenha());
            lblNomeUsuario.setText(Funcionario.getNome());
        }
    }
    
}
