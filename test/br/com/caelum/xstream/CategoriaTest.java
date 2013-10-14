package br.com.caelum.xstream;

import static org.junit.Assert.*;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.TreeMarshaller.CircularReferenceException;

public class CategoriaTest {

	@Test
	public void deveVerificarOResultadoDeCiclos() {
		Categoria esporte = new Categoria(null, "esporte");
		Categoria futebol = new Categoria(esporte, "futebol");
		Categoria geral = new Categoria(futebol, "geral");
		esporte.setPai(geral);
		
		XStream xStream = new XStream();
		xStream.setMode(XStream.ID_REFERENCES);
		xStream.alias("categoria", Categoria.class);
		
		String xmlEsperado = "<categoria id=\"1\">\n" +
		        "  <pai id=\"2\">\n" +
		        "    <pai id=\"3\">\n" +
		        "      <pai reference=\"1\"/>\n" +
		        "      <nome>futebol</nome>\n" +
		        "    </pai>\n" +
		        "    <nome>geral</nome>\n" +
		        "  </pai>\n" +
		        "  <nome>esporte</nome>\n" +
		        "</categoria>";
		
		assertEquals(xmlEsperado, xStream.toXML(esporte));
	}
	
	@Test(expected=CircularReferenceException.class)
	public void naoDeveVerificarOResultadoDeCiclos() {
		Categoria esporte = new Categoria(null, "esporte");
		Categoria futebol = new Categoria(esporte, "futebol");
		Categoria geral = new Categoria(futebol, "geral");
		esporte.setPai(geral);
		
		XStream xStream = new XStream();
		xStream.setMode(XStream.NO_REFERENCES);
		xStream.alias("categoria", Categoria.class);
		
		String xmlEsperado = "<categoria id=\"1\">\n" +
		        "  <pai id=\"2\">\n" +
		        "    <pai id=\"3\">\n" +
		        "      <pai reference=\"1\"/>\n" +
		        "      <nome>futebol</nome>\n" +
		        "    </pai>\n" +
		        "    <nome>geral</nome>\n" +
		        "  </pai>\n" +
		        "  <nome>esporte</nome>\n" +
		        "</categoria>";
		
		assertEquals(xmlEsperado, xStream.toXML(esporte));
	}
	
}
