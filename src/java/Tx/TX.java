/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tx;

import Modelo.Elemento;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author LabingXEON
 */
public class TX {
    private Connection conexion;
    public TX() {
        conexion = Util.conexion.getConnection();
    }
    


    public boolean Insertar(Elemento elm) throws IllegalArgumentException, IllegalAccessException{
    
    boolean resultado = false;
        try {
            String c="";
            String consulta = null;
            Field[] f=elm.getClass().getDeclaredFields();
            for (int i = 0; i < f.length; i++) {
                
            c=c + f[i].getName()+":"+f[i].get(elm)+";";
            }
            
            
            //1.Establecer la consulta
            for (int i = 0; i < f.length; i++) {
                c=(String) f[i].get(elm);
                System.out.println(c);
            
             consulta = "insert into"+ elm.getClass()+" values "+c;
            }
            
            //2. Crear el PreparedStament
            
            PreparedStatement statement = this.conexion.prepareStatement(consulta);
            //----------------------------


            //3. Hacer la ejecucion
            resultado = statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultado;
    
    }
    
    public void Create_Statement(Elemento elm) throws IllegalArgumentException, IllegalAccessException{
        Field[] f=elm.getClass().getDeclaredFields();
        
        for (int i = 0; i <= f.length; i++) {
            System.out.println(  f[i].getName()+":"+f[i].get(elm));
        }
        
    
    
    }
    
    
    public ArrayList<Elemento> listarPorArea(int etiqueta) {
        ArrayList<Elemento> respuesta = new ArrayList();
        Elemento elm =new Elemento();
        Field[] f=elm.getClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
                String consulta = "select * from "+elm.getClass()+" where etiqueta = '" + etiqueta + "'";
        System.out.println(consulta);
        try {

            Statement statement
                    = this.conexion.createStatement();

            ResultSet resultado
                    = statement.executeQuery(consulta);

            while (resultado.next()) {
                Elemento elme = new Elemento();
                elme.setEtiqueta(resultado.getInt("etiqueta"));
                elme.setNombre(resultado.getString("nombre"));
                respuesta.add(elme);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }       
        }
    

        return respuesta;
    }
    
    
}