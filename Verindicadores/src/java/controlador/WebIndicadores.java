
package controlador;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class WebIndicadores {
    private double vMax=0;
    private double vMin=0;
    private double dMax=0;
    private double dMin=0;
    private String[][] indicadores;
    private String mVMax="";
    private String mVMin="";
    private String mDMax="";
    private String mDMin="";
    
    //Metodo que extrae la información desde web para el indicador recibido como parámetro
    public JsonObject GetIndicador(String indicador)throws MalformedURLException, IOException{
        URL url = new URL("http://mindicador.cl/api"+"/"+indicador);
        InputStream is = url.openStream();
        JsonReader rdr = Json.createReader(is);
        JsonObject obj = rdr.readObject();
        return obj;
    }
    
    
    public String[][] SetArreglo(JsonObject obj, String indicador){
        double val1=0;
        double val2=0;
        double diff=0;
        
        //Creando el array que guardará la información        
        indicadores = new String[30][3];
        for (int i = 0; i<30; i++){
            indicadores[i][0]= String.valueOf(obj.getJsonArray("serie").getJsonObject(i).get("fecha")).substring(1, 11);
            indicadores[i][1]= String.valueOf(obj.getJsonArray("serie").getJsonObject(i).get("valor"));
            indicadores[i][2] = "0";
            if(i>0){
                val1 = Double.parseDouble(String.valueOf(indicadores[i-1][1]));
                val2 = Double.parseDouble(String.valueOf(indicadores[i][1]));
                diff = Math.round((val1 - val2)*100.0) / 100.0;
                if(i==1){
                    dMax = Math.abs(diff);
                    dMin = Math.abs(10000000);
                }
                indicadores[i-1][2] = String.valueOf(Math.abs(diff));
            }
        }
        return indicadores;
    }

    public void calculateMaxMin(String[][] arr, String indicador) {
        double tVal = 0;
        vMin=0;
        vMax=0;
        dMin=0;
        dMax=0;
        
        for(int i=0; i<30; i++){
            if(i==0){
                vMin=Double.parseDouble(arr[i][1]);
                vMax=Double.parseDouble(arr[i][1]);
                dMin=Math.abs(Double.parseDouble(arr[i][2]));
                dMax=Math.abs(Double.parseDouble(arr[i][2]));
                mVMax = "<html>El valor máximo de " + indicador + " se registró el día<BR>" + arr[i][0] + " y es de " + arr[i][1] + "</html>"; 
                mVMin = "<html>El valor mínimo de " + indicador + " se registró el día<BR>" + arr[i][0] + " y es de " + arr[i][1] + "</html>";
                mDMax = "<html>La variación máxima de " + indicador + " se registró el día<BR>" + arr[i][0] + " y es de " + dMax + "</html>";
                mDMin = "<html>La variación mínima de " + indicador + " se registró el día<BR>" + arr[i][0] + " y es de " + String.valueOf(dMin) + "</html>";
            }else{
                
                tVal = Double.parseDouble(arr[i][1]);
                
                if(tVal>vMax){
                    vMax = tVal;
                    mVMax = "<html>El valor máximo de " + indicador + " se registró el día<BR>" + arr[i][0] + " y es de " + arr[i][1] + "</html>"; 
                }
                if(tVal<vMin){
                    vMin = tVal;
                    mVMin = "<html>El valor mínimo de " + indicador + " se registró el día<BR>" + arr[i][0] + " y es de " + arr[i][1] + "</html>";
                }
                
                tVal = Math.abs(Double.parseDouble(arr[i][2]));
                
                if(tVal>dMax){
                    dMax = tVal;
                    mDMax = "<html>La variación máxima de " + indicador + " se registró el día<BR>" + arr[i][0] + " y es de " + dMax + "</html>";
                }
                
                if(tVal<dMin && i<29){
                    dMin = tVal;
                    mDMin = "<html>La variación mínima de " + indicador + " se registró el día<BR>" + arr[i][0] + " y es de " + String.valueOf(dMin) + "</html>";
                }                  
            }
        }
    }

    
    public String getmVMax() {
        return mVMax;
    }

    public String getmVMin() {
        return mVMin;
    }

    public String getmDMax() {
        return mDMax;
    }

    public String getmDMin() {
        return mDMin;
    }
    
    
    public DefaultTableModel listaIndicadores(String[][] lista) {
        DefaultTableModel tablemodel = new DefaultTableModel();
        String[] columNames = {"Fecha", "Valor", "Diferencia"};
        tablemodel.setDataVector(lista, columNames);
        //Object[][] data = lista;
        return tablemodel;
    }
    
    
}
