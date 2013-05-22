package client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ogrenci")
public class Person {

	@XmlElement(name = "ogrNo")
	private String _id;

	@XmlElement(name = "dogumTar")
	private String _date;

	@XmlElement(name = "adSoyad")
	private String _name;

	public Person() {

	}

	public Person(String id, String name, String date) {
		_id = id;
		_name = name;
		_date = date;
	}

	public Person(Command c) {
		_id = c.personId();
		_name = c.person();
		_date = c.date();
	}

	@Override
	public String toString() {
		return _id + "\t" + _name + "\t" + _date;
	}

	public String id() {
		return _id;
	}

	public void id(String id) {
		_id = id;
	}

	public String name() {
		return _name;
	}

	public void name(String name) {
		_name = name;
	}

	public String date() {
		return _date;
	}

	public void date(String date) {
		_date = date;
	}

}
