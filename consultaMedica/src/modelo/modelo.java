package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import sql.conexion;

public class modelo extends conexion{
    
    //metodo boolean agregar Comuna
    public boolean agregarComuna(int idComuna, String nombre){
        
        String query = "INSERT INTO consultamedica.comuna(idComuna,nombre)"
                + "VALUES('"+ idComuna
                + "','" + nombre
                + ") ;";
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
    
    //metodo para modificar comuna
    public boolean modificarComuna(
            String idComuna,
            String nombre) {
        
        String query = "UPDATE consultamedica.comuna SET "
                + "nombre='" + nombre
                + "' WHERE idComuna='" + idComuna
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
    
    //metodo para eliminar comuna
    public boolean eliminarComuna(int idComuna) {
        String query = "DELETE FROM consultamedica.comuna WHERE idComuna =" + idComuna + ";";
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
    
    //metodo para listar comunas para poblar comboboxes
    public ArrayList poblarComboComunas(){
       ArrayList<String> comunasList = new ArrayList<String>();
       String query = "SELECT nombre FROM comuna";
       try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                String comunaRow = res.getString("comunasList");
                comunasList.add(comunaRow);
            }
            res.close();
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
       return comunasList;
    }
    
    //metodo para listar comunas
    public DefaultTableModel listarComunas() {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int num_registros = 0;
        String[] columNames = {"ID Comuna", "Nombre"};
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(*) as numregistros FROM consultamedica.comuna;");
            ResultSet res = pstm.executeQuery();
            res.next();
            num_registros = res.getInt("numregistros");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Object[][] data = new String[num_registros][2];
        String query = "SELECT * FROM consultamedica.comuna";
        
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("idComuna");
                data[i][1] = res.getString("nombre");
                i++;
            }
            res.close();
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
    }
    
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
                + "'," + donanteSQL
                + ") ;";
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

    //metodo para buscar paciente por rut o listar todos los pacientes
    public DefaultTableModel buscarPaciente(String rut) {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int num_registros = 0;
        String[] columNames = {"Rut", "Nombre", "Género", "Edad", "Dirección", "Ciudad", "Isapre", "Donante"};
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(*) as numregistros FROM consultamedica.paciente;");
            ResultSet res = pstm.executeQuery();
            res.next();
            num_registros = res.getInt("numregistros");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Object[][] data = new String[num_registros][8];
        String query;
        if (rut.equals("")){
            query = "SELECT * FROM consultamedica.paciente";
        } else {
            query = "SELECT * FROM consultamedica.paciente WHERE rut='"+rut+"';";
        }
        
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
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
    
    //----------------------------------------------------------------------------------------------
    //Validacion
    //----------------------------------------------------------------------------------------------
    
    //metodo que valida si rut existe
    public boolean rutExiste(String rut){
        int num_registros;
        String query = "SELECT count(*) as numregistros FROM consultamedica.paciente WHERE "+rut+";";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            ResultSet res = pstm.executeQuery();
            res.next();
            num_registros = res.getInt("numregistros");
            res.close();
            if (num_registros==1){ //evaluar si queremos listar mas de un rut repetido
                return true;
            } 
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    //metodo para validar que comuna existe
    public boolean comunaExiste(String idComuna){
        int num_registros;
        String query = "SELECT count(*) as numregistros FROM consultamedica.paciente WHERE "+idComuna+";";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            ResultSet res = pstm.executeQuery();
            res.next();
            num_registros = res.getInt("numregistros");
            res.close();
            if (num_registros==1){ //evaluar si queremos listar mas de un rut repetido
                return true;
            } 
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
