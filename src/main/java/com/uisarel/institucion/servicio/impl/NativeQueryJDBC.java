package com.uisarel.institucion.servicio.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uisarel.institucion.dto.DtoGroupGradoNivel;
import com.uisarel.institucion.dto.DtoMenuLogin;
import com.uisarel.institucion.modelo.entidades.PeriodoEscolar;
import com.uisarel.institucion.modelo.entidades.Personal;
import com.uisarel.institucion.modelo.repositorio.IPeriodoEscolarRepositorio;
import com.uisarel.institucion.servicio.IPersonalServicio;

@Repository
public class NativeQueryJDBC {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private IPeriodoEscolarRepositorio repoPeriodoEscolar;

	@Autowired
	private IPersonalServicio srvPersonal;

	@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * @author CodeBluePeru
	 * @apiNote LISTA DE GRUPO DE GRADOS POR NIVEL
	 * @param nivelEscolar
	 * @return
	 */
	public List<DtoGroupGradoNivel> onListarGrupoGradoNivelPeriodoEscolar(String nivelEscolar) {
		PeriodoEscolar periodo = repoPeriodoEscolar.findByEstadoOrderByEstadoAsc("APERTURADO").get(0);

		List<DtoGroupGradoNivel> lista = this.jdbcTemplate.query("select g.grado_descripcion, "
				+ "(SELECT COUNT(*) FROM estudiante e WHERE e.id_periodo_escolar = " + periodo.getIdPeriodoEscolar()
				+ " and e.nivel_escolar = g.tipo_grado " + " and e.id_grado = g.id_grado) total "
				+ "  from grado g where g.tipo_grado ='" + nivelEscolar + "' ORDER BY g.grado_descripcion ASC",
				new retornarGrupoGrado());
		return lista;
	}

	private static final class retornarGrupoGrado implements RowMapper<DtoGroupGradoNivel> {

		@Override
		public DtoGroupGradoNivel mapRow(ResultSet rs, int rowNum) throws SQLException {
			DtoGroupGradoNivel e = new DtoGroupGradoNivel();
			try {
				e.setGrado_descripcion(rs.getString("grado_descripcion"));
				e.setTotal(rs.getInt("total"));
			} catch (Exception ex) {
				System.out.println("ERROR! LISTAR GROUP " + e);
				ex.printStackTrace();
			}
			return e;
		}

	}

	/**
	 * @author CodeBluePeru
	 * @apiNote CONTEO DE ALUMNOS POR DOCENTE SEGUN GRADO Y SECCION
	 * @return
	 */
	public int onTotalAlumnoDocente() {
		int total = 0;
		PeriodoEscolar periodo = repoPeriodoEscolar.findByEstadoOrderByEstadoAsc("APERTURADO").get(0);
		Personal person = srvPersonal.onListarPersonalAll().get(0);
		total = jdbcTemplate.queryForObject("select count(*) cantidad from estudiante where id_periodo_escolar =   "
				+ periodo.getIdPeriodoEscolar()
				+ "	and id_grado IN (SELECT id_grado FROM grados_docente WHERE id_personal = " + person.getIdPersonal()
				+ ")  " + "	and id_seccion IN (select id_seccion from seccion_docente WHERE id_personal = "
				+ person.getIdPersonal() + " )", Integer.class);

		return total;
	}

	/**
	 * @author CodeBluePeru
	 * @apiNote DEBIDO A QUE LAS RELACIONES SON INFINITY JSON SE DECIDIO HACER NATIVO
	 * @param tipoMenu
	 * @param codMenu
	 * @param perfil
	 * @return
	 */
	public List<DtoMenuLogin> onListarMenuLoginUsuario(String tipoMenu, int codMenu, String perfil) {
		String sql = "SELECT mn.nombre,mn.icono,mn.url,mn.orden,mn.estado,mn.fk_padre as grupo, "
				+ " p.nombre as perfil, mn.id_menu FROM perfil_menu pm, menu mn, perfil p WHERE pm.fk_menu = mn.id_menu "
				+ " and pm.fk_pefil = p.id_perfil and p.nombre = '" + perfil + "'";
		if (tipoMenu.compareTo("MP") == 0) {
			sql += " and mn.fk_padre is null ";
		} else {
			sql += " and mn.fk_padre =  " + codMenu;
		}
		sql += "  order by mn.orden asc ";
		return jdbcTemplate.query(sql, (rs, rowNum) -> mapToDtoMenuLogin(rs));
	}

	private DtoMenuLogin mapToDtoMenuLogin(ResultSet rs) throws SQLException {
		return new DtoMenuLogin(rs.getString("nombre"), rs.getString("icono"), rs.getString("url"),
				rs.getString("orden"), rs.getString("estado"), rs.getString("grupo"), rs.getString("perfil"), rs.getInt("id_menu"));
	}
	
	/**
	 * @author CodeBluePeru
	 * @param ruta
	 * @param perfil
	 * @return
	 */
	public int onValidarPermisoRuta(String ruta,String perfil) {		
		return jdbcTemplate.queryForObject("SELECT count(*) FROM perfil_menu pm, menu mn, perfil p WHERE pm.fk_menu = mn.id_menu "
				+ " and pm.fk_pefil = p.id_perfil  and mn.url = '"+ruta+"' and p.nombre = '"+perfil+"'", Integer.class);
	}

}
