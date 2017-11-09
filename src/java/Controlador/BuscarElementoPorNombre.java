package Controlador;

import DAO.DaoElementos;
import Modelo.Elemento;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;


public class BuscarElementoPorNombre extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                DaoElementos dao;
        try {
            int etiqueta= Integer.valueOf(request.getParameter("etiqueta"));
            dao = new DaoElementos();
            ArrayList<Elemento> c = new ArrayList<Elemento>();
            c = dao.listarPorArea(etiqueta);
            String json = new Gson().toJson(c);
            response.setContentType("application/json;");
            response.getWriter().write(json);
        } catch (URISyntaxException ex) {
            Logger.getLogger(BuscarElementoPorNombre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BuscarElementoPorNombre.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
      @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    int etiqueta= Integer.valueOf(request.getParameter("etiqueta"));/*Número de identificación del libro o el elemento*/
    String nombre=request.getParameter("nombre");/*Nombre del libro o del elemento*/
    
    Elemento c=new Elemento();
        try {
         
            DaoElementos d=new DaoElementos();
            c.setEtiqueta(etiqueta);
            c.setNombre(nombre);
            d.insertar(c);
            
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(BuscarElementoPorNombre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BuscarElementoPorNombre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(BuscarElementoPorNombre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BuscarElementoPorNombre.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }

}