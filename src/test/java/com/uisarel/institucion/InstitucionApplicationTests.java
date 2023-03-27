package com.uisarel.institucion;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.uisarel.institucion.modelo.entidades.Operaciones;
import com.uisarel.institucion.modelo.entidades.Perfil;
import com.uisarel.institucion.modelo.entidades.PerfilOperaciones;
import com.uisarel.institucion.servicio.ILoginServicio;
import com.uisarel.institucion.servicio.IOperacionesServicio;
import com.uisarel.institucion.servicio.IPerfilOperacionesServicio;
import com.uisarel.institucion.servicio.IPerfilServicio;
import com.uisarel.institucion.servicio.IUsuarioPerfilServicio;
import com.uisarel.institucion.servicio.IUsuarioServicio;




@SpringBootTest
class InstitucionApplicationTests {
	
	@Autowired
	IUsuarioServicio servicioUsuario;
	@Autowired
	IPerfilServicio servicioPerfil;
	@Autowired
	IOperacionesServicio servicioOperaciones;
	@Autowired
	ILoginServicio servicioLogin;
	@Autowired
	IUsuarioPerfilServicio servicioUsuarioPerfil;
	@Autowired
	IPerfilOperacionesServicio servicioPefilOperaciones;
	
	@Test
	void contextLoads() {
		
		//Usuario nuevoUsuario = new Usuario();
		//Perfil nuevoPerfil = new Perfil();
		//PerfilOperaciones nuevoOperaciones = new PerfilOperaciones();
		//PerfilOperaciones nuevoOperaciones2 = new PerfilOperaciones();
		//PerfilOperaciones nuevoOperaciones3 = new PerfilOperaciones();
		//PerfilOperaciones nuevoOperaciones4 = new PerfilOperaciones();
		//Sesion nuevoLogin = new Sesion();
		//UsuarioPerfil nuevoUsuarioPerfil = new UsuarioPerfil();		
		//PerfilOperaciones nuevoPefilOperaciones = new PerfilOperaciones();
		
		/*//nuevoUsuario.setNombre("Maria");
		//nuevoUsuario.setApellido("Chunchun");
		//nuevoUsuario.setCorreo("daniel@outlook.com");
		//nuevoUsuario.setEstado("1");
		nuevoUsuario.setFechaCreacionContrasenia(new Date());
		//nuevoUsuario.setSexoUsuario("Masculino");
		servicioUsuario.insertarUsuario(nuevoUsuario);		*/
		
		Operaciones nuevoOperacione = new Operaciones();
		nuevoOperacione.setNombre("Actualizar");
		Perfil nuevoPerfil = new Perfil();
		nuevoPerfil.setNombre("Docente");
		
		
		//Perfil nuevoPerfil= servicioPerfil.buscarPerfilId(2);
		PerfilOperaciones nuevoOperaciones = new PerfilOperaciones();
		PerfilOperaciones nuevoOperaciones2 =new PerfilOperaciones();
		PerfilOperaciones nuevoPerfile = new PerfilOperaciones();
		PerfilOperaciones nuevoPerfil2 = new PerfilOperaciones();
				
		List<PerfilOperaciones>perfOpera = new ArrayList<>();
		//List<PerfilOperaciones>perfOpera2 = new ArrayList<>();
		
		//nuevoOperaciones.setFkOperaciones (nuevoOperacione );
		//nuevoOperaciones2.setFkOperaciones(nuevoOperacione );
		nuevoPerfile.setFkPerfil(nuevoPerfil);
		nuevoPerfil2.setFkPerfil(nuevoPerfil);
		
		perfOpera.add(nuevoOperaciones);
		perfOpera.add(nuevoOperaciones2);
		perfOpera.add(nuevoPerfil2);
		perfOpera.add(nuevoPerfil2);

		servicioPerfil.insertarPerfil(nuevoPerfil);
		servicioOperaciones.insertarOperaciones(nuevoOperacione);
		//servicioPefilOperaciones.insertarPefilOperaciones(nuevoOperaciones);
		//servicioPefilOperaciones.insertarPefilOperaciones(nuevoOperaciones2);
		//servicioPefilOperaciones.insertarPefilOperaciones(nuevoOperaciones3);
		//servicioPefilOperaciones.insertarPefilOperaciones(nuevoOperaciones4);
		//servicioPefilOperaciones.insertarPefilOperacionesTODOS(perfOpera);
		
		//servicioPefilOperaciones.insertarPefilOperaciones(nuevoOperaciones);
		//servicioPefilOperaciones.insertarPefilOperaciones(nuevoOperaciones2);
		//servicioPefilOperaciones.insertarPefilOperaciones(nuevoOperaciones3);
		//servicioPefilOperaciones.insertarPefilOperaciones(nuevoOperaciones4);
		
		//nuevoPerfil.setListaPerfilOperaciones(perfOpera);
		//nuevoOperacione.setListaPerfilOperaciones(perfOpera);
			
			
			//nuevoOperacione.setListaPerfilOperaciones(perfOpera);
			//servicioPefilOperaciones.insertarPefilOperacionesTODOS(perfOpera);
	
			

			
	
		
		
		//nuevoLogin.setUltimoInicioSecion(new Date());
		//nuevoLogin.setFkUsuario(nuevoUsuario);
		//servicioLogin.insertarLogin(nuevoLogin);
		
		//nuevoUsuarioPerfil.setFkUsuario(nuevoUsuario);
		//nuevoUsuarioPerfil.setFkPerfil(nuevoPerfil);
		//servicioUsuarioPerfil.insertarUsuaPerf(nuevoUsuarioPerfil);
		
		
		
		
		/*List<UsuarioPerfil> lista = servicioUsuarioPerfil.listarPorUsuariosPerfil("administrador");
		for(UsuarioPerfil tmp: lista) {
			System.out.println(tmp.getFkPerfil());
		}*/	
		
		/*List<UsuarioPerfil> lista= servicioUsuarioPerfil.listaUsuarioPerfil();
		for(UsuarioPerfil tmp: lista) {
			System.out.println(tmp.getEstado()+"-"+tmp.getFkUsuario());
		}*/
		
		/*List<Usuario> lista= servicioUsuario.listaUsuario();
		for(Usuario tmp: lista) {
			System.out.println(tmp.getApellidos()+"-");
		}*/
		
		/*List<UsuarioPerfil> lista= servicioUsuarioPerfil.listarPorUsuarioPerfil("activo");
		for(UsuarioPerfil tmp: lista) {
			System.out.println(tmp.getEstado()+"--"+tmp.getFechaCreacionPerfil()+"--"+tmp.getFkPerfil() +"--"+tmp.getFkUsuario());
		}*/
		
		/*List<UsuarioPerfil> lista= servicioUsuarioPerfil.listaUsuarioPerfilInn("alumno");
		for(UsuarioPerfil tmp: lista) {
			System.out.println(tmp.getEstado()+"--");
		}*/
		
		/*List<UsuarioPerfil> lista= servicioUsuarioPerfil.listaUsuarioPerfil();
		for(UsuarioPerfil tmp: lista) {
			System.out.println(tmp.getEstado()+"-"+tmp.getFkPerfil()+"--"+tmp.getFkUsuario());
		}*/
				
		
		/*List<Usuario> lista= servicioUsuario.listaUsuario();
		for(Usuario tmp: lista) {
			System.out.println(tmp);
		}*/
		
		/*List<Perfil> listaPer= servicioPerfil.listaPerfil();
		for(Perfil tmp: listaPer) {
			System.out.println(tmp);
		}*/
		
		/*List<Usuario> lista= servicioUsuario.listaUsuario();
		for(Usuario tmp: lista) {
			System.out.println(tmp);
		}*/
		
	}
}
