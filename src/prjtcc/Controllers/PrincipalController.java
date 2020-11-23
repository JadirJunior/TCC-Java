package prjtcc.Controllers;

import animatefx.animation.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import prjtcc.PrjTCC;
import prjtcc.classes.CharData;
import prjtcc.classes.ChartModel;
import prjtcc.classes.Converter;
import prjtcc.classes.Dialog;
import prjtcc.classes.StaticKeys;
import prjtcc.model.Funcionario;
import prjtcc.model.LoginComands;

public class PrincipalController implements Initializable {
    
    @FXML
    private Button btnUser, btnUpdate, btnEditUser, btnAcessos;
    
    @FXML
    private ImageView imageUser, imageUpdate;

    @FXML
    private Label lblNomeUsuario;

    @FXML
    private Pane Container;
    
    @FXML
    private AnchorPane Account, PanelChartAcessos;
    
    @FXML
    private TextField txtUsuario, txtSenha;
     
    @FXML
    private AreaChart<?, ?> AcessosChart;
    
    
    @FXML
    private ChoiceBox<Object> choiceBox;
    
    @FXML
    private AnchorPane anchPane;
    
    @FXML
    private StackPane stack;
    
    private static File file = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Account.setVisible(false);
       PanelChartAcessos.setVisible(false);
       PrjTCC.addOnChangeListeners(new PrjTCC.onChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("principal")) {
                    Account.setVisible(false);
                    PanelChartAcessos.setVisible(false);
                    lblNomeUsuario.setText(userData.toString());
                    new ZoomIn(anchPane).play();
                }
                    
                if (StaticKeys.getFoto() == null) {
                    
                } else {
                    renderImage(StaticKeys.getFoto());
                }
            }
        });
    }  
    
    @FXML
    public void ProdutosVendidosClick(ActionEvent event) {
        loadChart("Produtos", "Produtos Vendidos", AcessosChart, choiceBox, CharData.PRODUTOS_VENDIDOS);
    }
    
    public void loadChart(String legend, String title, AreaChart<?,?> chart, ChoiceBox<Object> choice, CharData data) {
        if (PanelChartAcessos.visibleProperty().equals(true)) 
            return;
        
        new ZoomOut(Account).play();
        Account.toBack();
        Account.setVisible(false);
        new ZoomIn(PanelChartAcessos).play();
        PanelChartAcessos.setVisible(true);
        AcessosChart.getData().clear();
        new ChartModel(legend, title, chart, choice, data);
    }
    
    @FXML
    public void ChartUsuariosNovos(ActionEvent event) {
        loadChart("Usuários", "Usuários Cadastrados", AcessosChart, choiceBox, CharData.USUARIOS_CADASTRADOS);
    }
    
    @FXML
    public void ChartProdutosAdicionados(ActionEvent event) {
        loadChart("Produtos", "Produtos Adicionados", AcessosChart, choiceBox, CharData.PRODUTOS_ADICIONADOS);
    }
    
    @FXML
    public void AcessosClick(ActionEvent event) {
        loadChart("Acessos", "Quantidade de Acessos", AcessosChart, choiceBox, CharData.ACESSOS);
    }
    
   @FXML
   public void btnUserClick(ActionEvent event) throws IOException, Exception {
       if (Account.visibleProperty().equals(true)) {
           return;
       }
       
      new ZoomOut(PanelChartAcessos).play();
      PanelChartAcessos.toBack();
      new ZoomIn(Account).play();
      Account.setVisible(true);
      Account.toFront();
      AcessosChart.getData().clear();
      if (StaticKeys.getFoto() != null) {
          imageUpdate.setImage(renderImageReturn(StaticKeys.getFoto()));
      }
      
      txtUsuario.setText(StaticKeys.getNome());
      txtSenha.setText(StaticKeys.getSenha());
      lblNomeUsuario.setText(StaticKeys.getNome());
    }
   
   private void renderImage(BufferedImage image) {
        WritableImage wr = null;
                  wr = new WritableImage(image.getWidth(), image.getHeight());
                PixelWriter pw = wr.getPixelWriter();
                for (int x = 0; x < image.getWidth(); x++) {
                        for (int y = 0; y < image.getHeight(); y++) {
                          pw.setArgb(x, y, image.getRGB(x, y));
                        }
                   StaticKeys.setFoto(image);
                   imageUser.setImage(wr);
                   lblNomeUsuario.setText(StaticKeys.getNome());
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
                        StaticKeys.setFoto(image);
                        imageUser.setImage(wr);
                        lblNomeUsuario.setText(StaticKeys.getNome());
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
             boolean res = login.updateUser(new Funcionario(txtUsuario.getText(),txtSenha.getText()), imagem_byte);
             if (res){
                 stack.toFront();
                 Dialog.showDialog("Usuário", "Usuário Atualizado com sucesso", "Ok!", false, stack);
                 renderImage(image);
                 StaticKeys.setFoto(image);
                 lblNomeUsuario.setText(StaticKeys.getNome());
             } 
             else {
                 Dialog.showDialog("Usuário", "Usuário já existe!", "Ok!", false, stack);
              }
            }
            
            else {
             LoginComands login = new LoginComands();
             boolean res = login.updateUser(new Funcionario(txtUsuario.getText(),txtSenha.getText()));
             if (res){
                 Dialog.showDialog("Usuário", "Usuário Atualizado com sucesso", "Ok!", false, stack);
                 lblNomeUsuario.setText(StaticKeys.getNome());
             } 
             else {
                 Dialog.showDialog("Usuário", "Usuário já existe!", "Ok!", false, stack);
              }
             
            }
        } catch (IOException ex) {
            Dialog.showDialog("Erro!", "Ocorreu um erro, tente novamente!", "Ok!", false, stack);
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
            new ZoomOut(PanelChartAcessos).play();
            PanelChartAcessos.toBack();
            PanelChartAcessos.setVisible(false);
            stack.toBack();
            new ZoomIn(Account).play();
            Account.setVisible(true);
            AcessosChart.getData().clear();
            if (StaticKeys.getFoto() != null) 
                 imageUpdate.setImage(renderImageReturn(StaticKeys.getFoto()));
            
            txtUsuario.setText(StaticKeys.getNome());
            txtSenha.setText(StaticKeys.getSenha());
            lblNomeUsuario.setText(StaticKeys.getNome());
        }
    }
    
    @FXML
    public void onLoggout(ActionEvent event) {
        StaticKeys.resetKeys();
        new ZoomOut(anchPane).play();
        PrjTCC.changeScreen("login");
    }
    
}
