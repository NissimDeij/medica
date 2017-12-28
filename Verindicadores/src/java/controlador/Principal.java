package controlador;

import vista.vistaIndicadores;

public class Principal {
    public static void main(String[] args){
        vistaIndicadores p = new vistaIndicadores();
        controlador c = new controlador(p);
        c.iniciar();
    }
}