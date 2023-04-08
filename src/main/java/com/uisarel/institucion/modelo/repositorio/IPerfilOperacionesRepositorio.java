package com.uisarel.institucion.modelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.modelo.entidades.Operaciones;
import com.uisarel.institucion.modelo.entidades.Perfil;
import com.uisarel.institucion.modelo.entidades.PerfilOperaciones;

@Repository
@Component
public interface IPerfilOperacionesRepositorio extends JpaRepository<PerfilOperaciones, Integer>{

	public PerfilOperaciones findByFkPerfil(Perfil fkPerfil);
	public PerfilOperaciones findByFkOperaciones(Operaciones fkOperaciones);
}
