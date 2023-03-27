package com.uisarel.institucion.utils;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class ConstantApp {

	public static String ROL_LOGIN = "";

	static {
		@SuppressWarnings("unchecked")
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		for (Iterator<SimpleGrantedAuthority> iterator = authorities.iterator(); iterator.hasNext();) {
			SimpleGrantedAuthority simpleGrantedAuthority = (SimpleGrantedAuthority) iterator.next();
			ROL_LOGIN = simpleGrantedAuthority.toString();
		}
	}
}
