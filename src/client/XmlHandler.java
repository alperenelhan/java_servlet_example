package client;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlHandler<T> {
	
	public XmlHandler() {
		
	}
	
	public String marshal(T t, Class<T> clazz) throws Exception {
		JAXBContext context = JAXBContext.newInstance(clazz);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter sw = new StringWriter();
		marshaller.marshal(t, sw);
		return sw.toString();
	}
	
	public T unmarshal(String c, Class<T> clazz) throws Exception {
		JAXBContext context = JAXBContext.newInstance(clazz);
		StringReader sr = new StringReader(c);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (T) unmarshaller.unmarshal(sr);
	}
}
