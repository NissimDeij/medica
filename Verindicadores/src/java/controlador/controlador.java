package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import vista.vistaIndicadores;

public class controlador implements ActionListener{
    //vistas
    private vistaIndicadores vista1;
    //webIndicadores: Clase a cargo de conectar y traer valores desde la web para indicador seleccionado
    private WebIndicadores webInd = new WebIndicadores();
    
    public enum accion {
        cmbIndicadores, //combo indicadores
        btnSalir, //terminar programa
    };
    
    public controlador (JFrame padre){
        this.vista1 = (vistaIndicadores) padre;
    }
        
    public void iniciar(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this.vista1);
            this.vista1.setLocationRelativeTo(null);
            this.vista1.setTitle("Indicadores Económicos");
            this.vista1.cmbIndicadores.removeAllItems();
            this.vista1.cmbIndicadores.addItem("");
            this.vista1.cmbIndicadores.addItem("dolar");
            this.vista1.cmbIndicadores.addItem("utm");
            this.vista1.cmbIndicadores.addItem("uf");
            this.vista1.cmbIndicadores.addItem("euro");
            this.vista1.cmbIndicadores.addItem("ipc");
            this.vista1.lblMain.setText("");
            this.vista1.lblDMax.setText("");
            this.vista1.lblDMin.setText("");
            this.vista1.lblVMax.setText("");
            this.vista1.lblVMin.setText("");
            this.vista1.tblLista.setVisible(false);
            
            //this.vista1.tblTrabajadores.setModel(this.modelo.ListarTrabajadores()); //actualiza JTable
            this.vista1.setVisible(true);
            
        } catch (UnsupportedLookAndFeelException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        
        //escuchamos los botones
        //boton salir
        this.vista1.btnSalir.setActionCommand("btnSalir");
        this.vista1.btnSalir.addActionListener(this);
        //desplegable
        this.vista1.cmbIndicadores.setActionCommand("cmbIndicadores");
        this.vista1.cmbIndicadores.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (accion.valueOf(e.getActionCommand())) {
            case btnSalir:
                System.exit(0);
                break;
                
            case cmbIndicadores:
                String indicador = this.vista1.cmbIndicadores.getSelectedItem().toString();
                if(!indicador.equals("")){
                    try{
                        this.vista1.lblVMax.setText("");
                        this.vista1.lblVMin.setText("");
                        this.vista1.lblDMax.setText("");
                        this.vista1.lblDMin.setText("");
                        String[][] lista = webInd.SetArreglo(webInd.GetIndicador(indicador),indicador);
                        webInd.calculateMaxMin(lista, indicador);
                        this.vista1.tblLista.setModel(this.webInd.listaIndicadores(lista));
                        this.vista1.tblLista.setVisible(true);
                        this.vista1.lblMain.setText("Valores para " + indicador);
                        this.vista1.lblVMax.setText(this.webInd.getmVMax());
                        this.vista1.lblVMin.setText(this.webInd.getmVMin());
                        this.vista1.lblDMax.setText(this.webInd.getmDMax());
                        this.vista1.lblDMin.setText(this.webInd.getmDMin());
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "No fue posible obtener información para el indicador " + indicador);
                        return;
                    }
                }
                
                break;
        
                
        }
    }
    
}
