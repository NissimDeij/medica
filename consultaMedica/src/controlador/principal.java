package controlador;

import vista.vistaPrincipal;

public class principal {

    public static void main(String[] args) {
        
          vistaPrincipal p = new vistaPrincipal();
          controlador c = new controlador(p);
          
          c.iniciar();
    }
    
}
