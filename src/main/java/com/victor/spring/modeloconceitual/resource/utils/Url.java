package com.victor.spring.modeloconceitual.resource.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Url {
	public static List<Integer> convertToIntegerList(String numeros){
		List<String> listaString = Arrays.asList(numeros.split(","));
		List<Integer> listaInteiros = new ArrayList<Integer>();
		
		for(String item : listaString) {
			listaInteiros.add(Integer.parseInt(item));
		}
		return listaInteiros;
	}

	
	public static String decodeParam(String param){
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
