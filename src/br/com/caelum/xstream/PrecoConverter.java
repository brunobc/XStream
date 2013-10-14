package br.com.caelum.xstream;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.SingleValueConverter;

public class PrecoConverter implements SingleValueConverter {

	@Override
	public boolean canConvert(Class type) {
		return type.isAssignableFrom(Double.class);
	}

	@Override
	public Object fromString(String objeto) {
		String valor = (String) objeto;
		Locale brasil = new Locale("pt","br");
	    NumberFormat formatador = NumberFormat.getCurrencyInstance(brasil);
	    try {
			return formatador.parse(valor);
		} catch (ParseException e) {
			throw new ConversionException(e);
		}
	}

	@Override
	public String toString(Object objeto) {
		Double valor = (Double) objeto;
		Locale brasil = new Locale("pt","br");
	    NumberFormat formatador = NumberFormat.getCurrencyInstance(brasil);
		return formatador.format(valor);
	}

}
