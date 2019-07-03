
package com.idao;

import com.modelos.Estudiante;
import java.util.List;

public interface IEstudianteDao {
    
    //METODOS PARA OBTENER REGISTROS
    public List<Estudiante> listar();
    public Estudiante obtenerPorId(Integer id);
    
    //METODOS PARA OPERACIONES IMPORTANTES PARA EL CRUD
    public Boolean guardar(Estudiante estudiante);
    public Boolean modificar(Estudiante estudiante);
    public Boolean eliminar(Estudiante estudiante);
}
