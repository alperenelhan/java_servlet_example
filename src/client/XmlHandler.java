package client;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlHandler<T> {

	public XmlHandler() {
	}

	/**
	 * Morph object into XML string
	 * 
	 * @param t
	 *            instance of T class
	 * @param clazz
	 *            class of t
	 * @return XML string
	 * @throws Exception
	 */
	public String marshal(T t, Class<T> clazz) throws Exception {
		JAXBContext context = JAXBContext.newInstance(clazz);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter sw = new StringWriter();
		marshaller.marshal(t, sw);
		return sw.toString();
	}

	/**
	 * Build object from given XML String
	 * 
	 * @param xmlStr
	 *            XML string of an object
	 * @param clazz
	 *            class name of the xmlStr
	 * @return object that represents XML
	 * @throws Exception
	 */
	public T unmarshal(String xmlStr, Class<T> clazz) throws Exception {
		JAXBContext context = JAXBContext.newInstance(clazz);
		StringReader sr = new StringReader(xmlStr);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (T) unmarshaller.unmarshal(sr);
	}
}
