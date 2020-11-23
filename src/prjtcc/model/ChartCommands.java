package prjtcc.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import prjtcc.classes.CharData;
import prjtcc.classes.DateModel;
import prjtcc.classes.Prazo;

public class ChartCommands {
   Conexao con;
   Statement statement;
   public boolean Erro = false;
   HashMap<String,String> values = new HashMap<String,String>();
   private CharData data;
   
   public ChartCommands(CharData data) {
       this.data = data;
   }
   
   
   
   public List<Integer> getProdutosAdicionados(Prazo prazo) {
       Calendar c = Calendar.getInstance();
       String diaSemana = c.getTime().toString().split(" ")[2];
       String Mes = c.getTime().toString().split(" ")[1];
       if (prazo.equals(prazo.UMA_SEMANA)) {
           List<String> datas = DateModel.getLastSevenDays(diaSemana);
           List<Integer> values = getDataByDay(datas);
           return values;
       }
       
       else if (prazo.equals(prazo.UM_MES)) {
           List<String> datas = DateModel.getLastTwoMouth(Mes);
           List<Integer> values = getDataByMonth(datas);
           return values;
       }
       
       else if (prazo.equals(prazo.SEIS_MESES)) {
           List<String> datas = DateModel.getLastSixMouths(Mes);
           List<Integer> values = getDataByMonth(datas);
           return values;
       }
       
       else {
           List<String> datas = DateModel.getLastYear(Mes);
           List<Integer> values = getDataByMonth(datas);
           return values;
       }
   }
   
   private List<Integer> getDataByMonth(List<String> months) {
       ResultSet res = null;
       con = new Conexao();
       List<Integer> registrosAno = new ArrayList<Integer>();
       try {
           for (String month : months) {
               String query = "";
               if (data.equals(CharData.PRODUTOS_ADICIONADOS)) {
                    query = "SELECT COUNT(*) AS REGISTROS FROM PRODUTO WHERE MES = ?";
               } else if (data.equals(CharData.PRODUTOS_VENDIDOS)) {
                    query = "SELECT COUNT(*) AS REGISTROS FROM PRODUTOS_VENDIDOS WHERE MES = ?";
               } else if (data.equals(CharData.ACESSOS)) {
                    query = "SELECT COUNT(*) AS REGISTROS FROM ACESSOS WHERE MES = ?";
               } else if (data.equals(CharData.USUARIOS_CADASTRADOS)) {
                    query = "SELECT COUNT(*) AS REGISTROS FROM USUARIO WHERE MES = ?";
               }
                
                PreparedStatement pst = con.getConexao().prepareStatement(query);
                pst.setString(1, month);
                res = pst.executeQuery();
                if (res.next()) {
                    registrosAno.add(res.getInt("REGISTROS"));
                }
           }
           return registrosAno;
           
       } catch (Exception e) {
           System.out.println(e);
           return new ArrayList<Integer>();
       }
   }
   
   private List<Integer> getDataByDay(List<String> days) {
       ResultSet res = null;
       con = new Conexao();
       List<Integer> registrosSemana = new ArrayList<Integer>();
       try {
           for (String day : days) {
               String query = "";
               if (data.equals(CharData.PRODUTOS_ADICIONADOS)) {
                    query = "SELECT COUNT(*) AS REGISTROS FROM PRODUTO WHERE DIAMES = ?";
               } else if (data.equals(CharData.PRODUTOS_VENDIDOS)) {
                    query = "SELECT COUNT(*) AS REGISTROS FROM PRODUTOS_VENDIDOS WHERE DIAMES = ?";
               } else if (data.equals(CharData.ACESSOS)) {
                    query = "SELECT COUNT(*) AS REGISTROS FROM ACESSOS WHERE DIA_MES = ?";
               } else if (data.equals(CharData.USUARIOS_CADASTRADOS)) {
                    query = "SELECT COUNT(*) AS REGISTROS FROM USUARIO WHERE DIAMES = ?";
               }
                PreparedStatement pst = con.getConexao().prepareStatement(query);
                pst.setInt(1, Integer.parseInt(day));
                res = pst.executeQuery();
                if (res.next()) {
                    registrosSemana.add(res.getInt("REGISTROS"));
                }
           }
           return registrosSemana;
           
       } catch (Exception e) {
           System.out.println(e);
           return new ArrayList<Integer>();
       }
   }
    
}
