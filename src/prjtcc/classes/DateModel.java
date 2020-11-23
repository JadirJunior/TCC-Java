package prjtcc.classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class DateModel {
    private static HashMap<Integer,String> diasAno = new HashMap<Integer,String>();
    private static HashMap<Integer,String> mesesAno = new HashMap<Integer,String>();
    
    private static List<String> datas = new ArrayList<String>();
    private static List<String> meses = new ArrayList<String>();
    
    public static List<String> getLastSevenDays(String day) {
        datas.clear();
        diasAno.put(1, "31");
        diasAno.put(2, "28");
        diasAno.put(3, "31");
        diasAno.put(4, "30");
        diasAno.put(5, "31");
        diasAno.put(6, "30");
        diasAno.put(7, "31");
        diasAno.put(8, "31");
        diasAno.put(9, "30");
        diasAno.put(10, "31");
        diasAno.put(11, "30");
        diasAno.put(12, "31");
        for (int i =0; i<7;i++) {
            int dia = Integer.parseInt(day)-i;
            if (dia<0) {
                int mesAnterior = Calendar.MONTH-1<0 ? 12 : Calendar.MONTH-1;
                int ultimoDiaMes = Integer.parseInt(diasAno.get(mesAnterior));
                Integer dayMonth = ultimoDiaMes-dia;
                datas.add(dayMonth.toString());
            } else {
                datas.add(dia+"");
            }
        }
        return datas;
    }
    
    private static void loadMeses() {
        meses.clear();
        mesesAno.clear();
        mesesAno.put(1, "Janeiro");
        mesesAno.put(2, "Fevereiro");
        mesesAno.put(3, "Março");
        mesesAno.put(4, "Abril");
        mesesAno.put(5, "Maio");
        mesesAno.put(6, "Junho");
        mesesAno.put(7, "Julho");
        mesesAno.put(8, "Agosto");
        mesesAno.put(9, "Setembro");
        mesesAno.put(10, "Outubro");
        mesesAno.put(11, "Novembro");
        mesesAno.put(12, "Dezembro");
        mesesAno.put(0, "Dezembro");
        mesesAno.put(-1, "Novembro");
        mesesAno.put(-2, "Outubro");
        mesesAno.put(-3, "Setembro");
        mesesAno.put(-4, "Agosto");
        mesesAno.put(-5, "Julho");
        mesesAno.put(-6, "Junho");
        mesesAno.put(-7, "Maio");
        mesesAno.put(-8, "Abril");
        mesesAno.put(-9, "Março");
        mesesAno.put(-10, "Fevereiro");
        mesesAno.put(-11, "Janeiro");
    }
    
    public static int getMes(String name) {
        int val = 1;
        switch (name) {
            case "Jan":
                val = 1;
                break;
            case "Fev":
                val = 2;
                break;
            case "Mar":
                val = 3;
                break;
            case "Abr":
                val = 4;
                break;
            case "Mai":
                val = 5;
                break;
            case "Jun":
                val = 6;
                break;
            case "Jul":
                val = 7;
                break;
            case "Ago":
                val = 8;
                break;
            case "Set":
                val = 9;
                break;
            case "Oct":
                val = 10;
                break;
            case "Nov":
                val = 11;
                break;
            case "Dez":
                val = 12;
                break;
                
        }
        return val;
    }
    
    
    public static List<String> getLastSixMouths(String nameMonth) {
        loadMeses();
        for (int i = 0; i<6;i++) { 
            Calendar c = Calendar.getInstance();
            Integer mes = getMes(nameMonth)-i;
            meses.add(mesesAno.get(mes));
        }
        
        return meses;
    }
    
    public static List<String> getLastTwoMouth(String nameMonth) {
        loadMeses();
        for (int i = 0; i<2;i++) { 
            Calendar c = Calendar.getInstance();
            Integer mes = getMes(nameMonth)-i;
            meses.add(mesesAno.get(mes));
        }
        
        return meses;
    }
    
    public static List<String> getLastYear(String nameMonth) {
        loadMeses();
        for (int i = 0; i<12;i++) { 
            Calendar c = Calendar.getInstance();
            Integer mes = getMes(nameMonth)-i;
            meses.add(mesesAno.get(mes));
        }
        
        return meses;
    }
}
