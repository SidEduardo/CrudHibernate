
package com.dao;

import com.data.Conexion;

import com.idao.IEstudianteDao;
import com.modelos.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EstudianteDaoJDBC implements IEstudianteDao{

    Conexion con;
    Connection mcon;
    
    public EstudianteDaoJDBC() {
        con=new Conexion();
    }
    
    
    
    @Override
    public List<Estudiante> listar() {
        
       List<Estudiante> lista=new ArrayList<Estudiante>();
       
       String sql="select * from listado";
       
        try {
            con.conectar();
            mcon=con.getJdbcConnection();
            
            Statement st= mcon.createStatement();
           
            ResultSet r=st.executeQuery(sql);
            
            //se recorre el result set, que es como
            //una pequeña base de datos
            while (r.next()) {                
                Estudiante e=new Estudiante();
                e.setId(r.getInt("id"));
                e.setNombre(r.getString("nombre"));
                e.setEdad(r.getInt("edad"));
                e.setDireccion(r.getString("direccion"));
                e.setGenero(r.getString("genero"));
                
                //se agrega a la lista el estudiante
                lista.add(e);
            }
            
            con.desconectar();
            mcon.close();
            
            return lista;
        } catch (Exception e) {
            return null;
        }
       
    }

  
    public Estudiante obtenerPorId(Integer id) {
       Estudiante e=new Estudiante();
       
       String sql="select * from listado where id=?";
       
        try {
            con.conectar();
            mcon=con.getJdbcConnection();
            
            PreparedStatement pst= mcon.prepareStatement(sql);
            pst.setInt(1, id);
           
            
            ResultSet r=pst.executeQuery();
            
            //se recorre el result set, que es como
            //una pequeña base de datos
            if(r.next()) {                
             
                e.setId(r.getInt("id"));
                e.setNombre(r.getString("nombre"));
                e.setEdad(r.getInt("edad"));
                e.setDireccion(r.getString("direccion"));
                e.setGenero(r.getString("genero"));
            }
            
            con.desconectar();
            mcon.close();
            
            return e;
        } catch (SQLException ex) {
            return null;
        }
        
    }

    @Override
    public Boolean guardar(Estudiante estudiante) {
        String sql="Insert into listado(nombre,edad,direccion,genero) values(?,?,?,?)";
        
        try {
            con.conectar();
            mcon=con.getJdbcConnection();
            
            PreparedStatement pst= mcon.prepareStatement(sql);
            //paso de valores a la SQL
            pst.setString(1, estudiante.getNombre());
            pst.setInt(2, estudiante.getEdad());
            pst.setString(3, estudiante.getDireccion());
            pst.setString(4, estudiante.getGenero());
            
            Boolean filasAfectadas=pst.executeUpdate()>0;    
            
            pst.close();
            con.desconectar();
            mcon.close();
            
            System.out.println("registro guardado");
            
            return filasAfectadas; 
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean modificar(Estudiante estudiante) {
         String sql="Update listado set nombre=?,edad=?,direccion=?,genero=? where id=?";
        
        try {
            con.conectar();
            mcon=con.getJdbcConnection();
            
            PreparedStatement pst= mcon.prepareStatement(sql);
            //paso de valores a la SQL
            pst.setString(1, estudiante.getNombre());
            pst.setInt(2, estudiante.getEdad());
            pst.setString(3, estudiante.getDireccion());
            pst.setString(4, estudiante.getGenero());
            pst.setInt(5, estudiante.getId());
            
            Boolean filasAfectadas=pst.executeUpdate()>0;    
            
            pst.close();
            con.desconectar();
            mcon.close();
            
            System.out.println("registro modificado");
            
            return filasAfectadas; 
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean eliminar(Estudiante estudiante) {
     String sql="delete from listado where id=?";
        
        try {
            con.conectar();
            mcon=con.getJdbcConnection();
            
            PreparedStatement pst= mcon.prepareStatement(sql);
            //paso de valores a la SQL
          
            pst.setInt(1, estudiante.getId());
            
            Boolean filasAfectadas=pst.executeUpdate()>0;    
            
            pst.close();
            con.desconectar();
            mcon.close();
            
            System.out.println("registro eliminado");
            
            return filasAfectadas; 
        } catch (Exception e) {
            return false;
        }   
    }
    
}
