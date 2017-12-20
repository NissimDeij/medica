package controlador;

import sql.conexion;

public class validacion extends conexion{
    
    public static boolean validarRut(String rut){
        String rut1;
        String rut2 = "";
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".",""); //escapa el punto
            rut = rut.replace(",",""); //escapa la coma
            rut = rut.replace("-",""); //escapa el guion
            rut = rut.replace("_",""); //escapa el guion bajo
            rut = rut.replace(" ",""); //escapa el espacio
            
            String dv1 = rut.substring( rut.length()-1 ); //captura como String el digito verificador
            rut1 = rut.substring(0,rut.length()-1); //quitamos el digito verificador
            
            for (int i=0;i<=rut1.length()-1;i++){ //para invertir el orden de los digitos del rut
                rut2 = rut1.charAt(i) + rut2;   
            }
            
            int mult=2; //multiplicador
            int suma=0; //almacenará la sumatoria
            for (int i=0;i<=rut2.length()-1;i++){
                suma = suma + mult*Integer.parseInt(String.valueOf(rut2.charAt(i))); //suma cada producto entre el rut invertido y el multiplicador
                if (i==5){ //si se alcanzó la séptima posición resetea el multiplicador
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
            
            if (dv1.equals(dv2)){ //si todo el calculo coincide con el DV original, devuelve true, si no false
                return true;
            }
        }catch (java.lang.NumberFormatException e){
        }catch (Exception e){    
        }
        return false;
    }
    
    public static boolean validarLargoString(String varchar){
        if (varchar.length() <= 60) {
            return true;
        }
        return false;
    }

    public static boolean esNum(String varchar){
        try {
            int x = Integer.parseInt(varchar);
            if (x>=0){
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    
    public String formatRut(String rut){
        String rut1 = "";
        try {
            rut1 = rut.toUpperCase();
            rut1 = rut.replace(".",""); //escapa el punto
            rut1 = rut.replace(",",""); //escapa la coma
            rut1 = rut.replace("-",""); //escapa el guion
            rut1 = rut.replace("_",""); //escapa el guion bajo
            rut1 = rut.replace(" ",""); //escapa el espacio
            
            
            return rut1;
        } catch (java.lang.NumberFormatException e){
        } catch (Exception e){
        }
        return rut1;
    }
}
