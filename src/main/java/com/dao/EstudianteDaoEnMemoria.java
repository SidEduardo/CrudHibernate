/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.data.Data;
import com.idao.IEstudianteDao;
import com.modelos.Estudiante;
import java.util.List;
import sun.rmi.runtime.Log;

/**
 *
 * @author HP
 */
public class EstudianteDaoEnMemoria implements IEstudianteDao {

    @Override
    public List<Estudiante> listar() {

        return Data.listaEstudiantes;
    }

    @Override
    public Estudiante obtenerPorId(Integer id) {
        return Data.listaEstudiantes.get(id);
    }

    @Override
    public Boolean guardar(Estudiante estudiante) {
        try {
            Data.listaEstudiantes.add(estudiante);
            return true;
        } catch (Exception e) {
            System.err.println("No se guardo el registro de estudiante");
            return false;
        }
    }

    @Override
    public Boolean modificar(Estudiante estudiante) {
      
        try {
            Data.listaEstudiantes.set(estudiante.getId(),estudiante);
            return true;
        } catch (Exception e) {
            System.err.println("No se modifico el registro de estudiante");
            return false;
        }
    }

    @Override
    public Boolean eliminar(Estudiante estudiante) {
        try {
            Data.listaEstudiantes.remove(estudiante);
            return true;
        } catch (Exception e) {
            System.err.println("No se elimino el registro de estudiante");
            return false;
        }
    }

}
