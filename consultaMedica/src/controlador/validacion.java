package controlador;

import sql.conexion;

public class validacion extends conexion{
    
    public static boolean validarRut(String rut){
        String rut1;
        String rut2 = "";
        try {
            rut = rut.toLowerCase();
            rut = rut.replace(".",""); //escapa el punto
            rut = rut.replace(",",""); //escapa la coma
            rut = rut.replace("-",""); //escapa el guion
            rut = rut.replace("_",""); //escapa el guion bajo
            rut = rut.replace(" ",""); //escapa el espacio
            
            String dv1 = rut.substring(rut.length()); //captura como String el digito verificador
            rut1 = rut.substring(0,rut.length()); //quitamos el digito verificador
            
            for (int i=1;i<=rut1.length();i++){ //para invertir el orden de los digitos del rut
                rut2 = rut1.substring(i) + rut2;   
            }
            int mult=2; //multiplicador
            int suma=0; //almacenará la sumatoria
            for (int i=1;i<=rut1.length();i++){
                suma = suma + mult*Integer.parseInt(rut1.substring(i)); //suma cada producto entre el rut invertido y el multiplicador
                if (rut1.length()==7){ //si se alcanzó la séptima posición resetea el multiplicador
                    mult = 1; //es 1 porque 2 lineas mas abajo se le suma 1 en el m++
                }
                mult++;
            }
            int resto = suma%11; //determina el resto del cuociente entre suma y 11
            String dv2 = String.valueOf(11-resto); ///le sustrae el resto al 11
            
            if (dv2.equals("11")){
                dv2 = "0";
            } else if (dv2.equals("10")){
                dv2 = "K";
            }
            
            if (dv1.equals("dv2")){ //si todo el calculo coincide con el DV original, devuelve true, si no false
                return true;
            }
        }catch (java.lang.NumberFormatException e){
        }catch (Exception e){    
        }
        return false;
    }
    
    
}
