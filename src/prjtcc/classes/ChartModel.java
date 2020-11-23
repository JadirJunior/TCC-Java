package prjtcc.classes;

import java.util.Calendar;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import prjtcc.model.ProdutosCommands;

public class ChartModel {
    
    private AreaChart<?,?> areaChart;
    private ChoiceBox<Object> choiceBox;
    private CharData data;
    
    public ChartModel(String legend, String title, AreaChart<?,?> chart, ChoiceBox box, CharData data) {
        this.choiceBox = box;
        choiceBox.getItems().clear();
        this.areaChart = chart;
        areaChart.setTitle(title);
        choiceBox.getItems().add("Uma Semana"); 
        choiceBox.getItems().add("Um Mês"); 
        choiceBox.getItems().add("Seis Meses"); 
        choiceBox.getItems().add("Um Ano");
        this.data = data;
        setSeriesProdutosData(legend);
    }
    
    private void setValues(XYChart.Series series,Object...list) {
        series.getData().clear();
        for (Object item : list) {
            series.getData().add(item);
        }
    }
    
    private void setSeriesProdutosData(String serieName) {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName(serieName);
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> { 
            Calendar c = Calendar.getInstance();
            String diaSemana = c.getTime().toString().split(" ")[2];
            String Mes = c.getTime().toString().split(" ")[1];
            series1.getData().clear();
            switch (new_val.intValue()) {
                case 0:
                    List<String> datasSemana = DateModel.getLastSevenDays(diaSemana);
                    List<Integer> valores = new ProdutosCommands(data).getProdutosAdicionados(Prazo.UMA_SEMANA);
                    setValues(series1, 
                        new XYChart.Data(datasSemana.get(6), valores.get(6)),
                        new XYChart.Data(datasSemana.get(5), valores.get(5)),
                        new XYChart.Data(datasSemana.get(4), valores.get(4)),
                        new XYChart.Data(datasSemana.get(3), valores.get(3)),
                        new XYChart.Data(datasSemana.get(2), valores.get(2)),
                        new XYChart.Data(datasSemana.get(1), valores.get(1)),
                        new XYChart.Data(datasSemana.get(0), valores.get(0))
                        );
                    break;
                    
                case 1:
                    List<String> dataMesAnt = DateModel.getLastTwoMouth(Mes);
                    List<Integer> valores2 = new ProdutosCommands(data).getProdutosAdicionados(Prazo.UM_MES);
                    setValues(series1, 
                            new XYChart.Data(dataMesAnt.get(1), valores2.get(1)),
                            new XYChart.Data(dataMesAnt.get(0), valores2.get(0))
                            );
                    break;
                
                case 2:
                    List<String> dataSeisMeses = DateModel.getLastSixMouths(Mes);
                    List<Integer> valores3 = new ProdutosCommands(data).getProdutosAdicionados(Prazo.SEIS_MESES);
                setValues(series1, 
                        new XYChart.Data(dataSeisMeses.get(5), valores3.get(5)),
                        new XYChart.Data(dataSeisMeses.get(4), valores3.get(4)),
                        new XYChart.Data(dataSeisMeses.get(3), valores3.get(3)),
                        new XYChart.Data(dataSeisMeses.get(2), valores3.get(2)),
                        new XYChart.Data(dataSeisMeses.get(1), valores3.get(1)),
                        new XYChart.Data(dataSeisMeses.get(0), valores3.get(0))
                        );
                    break;
                    
                case 3:
                    List<String> datas = DateModel.getLastYear(Mes);
                    List<Integer> valores4 = new ProdutosCommands(data).getProdutosAdicionados(Prazo.UM_ANO);
                setValues(series1, 
                        new XYChart.Data(datas.get(11), valores4.get(11)),
                        new XYChart.Data(datas.get(10), valores4.get(10)),
                        new XYChart.Data(datas.get(9), valores4.get(9)),
                        new XYChart.Data(datas.get(8), valores4.get(8)),
                        new XYChart.Data(datas.get(7), valores4.get(7)),
                        new XYChart.Data(datas.get(6), valores4.get(6)),
                        new XYChart.Data(datas.get(5), valores4.get(5)),
                        new XYChart.Data(datas.get(4), valores4.get(4)),
                        new XYChart.Data(datas.get(3), valores4.get(3)),
                        new XYChart.Data(datas.get(2), valores4.get(2)),
                        new XYChart.Data(datas.get(1), valores4.get(1)),
                        new XYChart.Data(datas.get(0), valores4.get(0))
                        );
                    break;
            }
        });
        areaChart.getData().add(series1);
    }
}
