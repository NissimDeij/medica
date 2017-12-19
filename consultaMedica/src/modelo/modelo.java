package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import sql.conexion;

public class modelo extends conexion{
    
    //metodo para agregar paciente
    public boolean agregarPaciente(
            String rut,
            String nombre,
            String genero,
            int edad,
            String direccion,
            String ciudad,
            String isapre,
            boolean donante) {
        
        int donanteSQL;
        if (donante){
            donanteSQL=1;
        } else {
            donanteSQL=0;
        }
        String query = "INSERT INTO consultamedica.paciente("
                + "rut,nombre,genero,edad,direccion,ciudad,isapre,donante)"
                + "VALUES('"+ rut
                + "','" + nombre
                + "','" + genero
                + "','" + edad
                + "','" + direccion
                + "','" + ciudad
                + "','" + isapre
                + "','" + donanteSQL
                + "') ;";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    //metodo para buscar un unico paciente ya ingresado
    public String[] buscarPaciente(int rut){
        String[] arreglo = new String[8];
        String query="SELECT * FROM consultamedica.paciente WHERE rut="+rut+";";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            ResultSet res = pstm.executeQuery();
            res.next();
            res.close();
            while (res.next()) {
                arreglo[0] = res.getString("rut");
                arreglo[1] = res.getString("nombre");
                arreglo[2] = res.getString("genero");
                arreglo[3] = res.getString("edad");
                arreglo[4] = res.getString("direccion");
                arreglo[0] = res.getString("ciudad");
                arreglo[5] = res.getString("isapre");
                if (res.getString("donante").equals("1")){
                    arreglo[6] = "Sí";
                } else if (res.getString("donante").equals("0")){
                    arreglo[6] = "No";
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return arreglo;
    }
    
    
    
    //metodo para listar pacientes
    public DefaultTableModel ListarPacientes() {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"Rut", "Nombre", "Género", "Edad", "Dirección", "Ciudad", "Isapre", "Donante"};
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(*) as total FROM consultamedica.paciente;");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Object[][] data = new String[registros][8];
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM consultamedica.paciente;");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("rut");
                data[i][1] = res.getString("nombre");
                data[i][2] = res.getString("genero");
                data[i][3] = res.getString("edad");
                data[i][4] = res.getString("direccion");
                data[i][5] = res.getString("ciudad");
                data[i][6] = res.getString("isapre");
                if (res.getString("donante").equals("1")){
                    data[i][7] = "Sí";
                } else if (res.getString("donante").equals("0")){
                    data[i][7] = "No";
                }
                i++;
            }
            res.close();
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
    }

    //metodo para modificar un trabajador
    public boolean modificarPaciente(
            String rut,
            String nombre,
            String genero,
            int edad,
            String direccion,
            String ciudad,
            String isapre,
            boolean donante) {
        int donanteSQL;
        if (donante){
            donanteSQL=1;
        } else {
            donanteSQL=0;
        }
        
        String query = "UPDATE consultamedica.paciente SET "
                + "nombre='" + nombre
                + "', genero='" + genero
                + "', edad='" + edad
                + "', direccion='" + direccion
                + "', ciudad='" + ciudad
                + "', isapre='" + isapre
                + "', donante='" + donanteSQL
                + "' WHERE rut='" + rut
                + "' ;";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    //metodo para eliminar trabajador
    public boolean eliminarPaciente(int rut) {
        String query = "DELETE FROM consultamedica.paciente WHERE rut =" + rut + ";";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
}
