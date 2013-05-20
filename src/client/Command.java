package client;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ogrenciIslemleri")
public class Command {
	
	@XmlElement(name = "islem")
	private String _name;
	
	@XmlElement(name = "ogrNo")
	private String _personId;
	
	@XmlElement(name = "dogumTar")
	private String _date;
	
	@XmlElement(name = "adSoyad")
	private String _person;
	
	@XmlElement(name = "cevap")
	private String _response;
	
	@XmlElement(name = "ogrenci")
	private ArrayList<Person> _data;
	
	public Command() {
		_data = new ArrayList<Person>();
	}
	
	public Command(String personId){
		_data = new ArrayList<Person>();
		_personId = personId;
	}
	
	public Command(String personId, String person, String date) {
		_data = new ArrayList<Person>();
		_person = person;
		_personId = personId;
		_date = date;
	}
	
	@Override
	public String toString() {
		String str = "";
		if (_name != null) {
			str = _name; 
		}
		if (_personId != null) {
			str += " " + _personId; 
		}
		if (_person != null) {
			str += " " + _person;
		}
		if (_date != null) {
			str += " " + _date;
		}
		if (_response != null) {
			str += " " + _response; 
		}
		if (_data != null && _data.size() != 0) {
			for (Person p : _data) {
				str += p + "\n";
			}
		}
		return str.trim();
	}
	

	public String name() {
		return _name;
	}

	public void name(String name) {
		_name = name;
	}
	
	public String personId() {
		return _personId;
	}

	public void personId(String personId) {
		_personId = personId;
	}
	
	public String date() {
		return _date;
	}

	public void date(String date) {
		_date = date;
	}
	
	public ArrayList<Person> data() {
		return _data;
	}

	public void data(ArrayList<Person> data) {
		_data = data;
	}
	
	public String person() {
		return _person;
	}

	public void person(String person) {
		_person = person;
	}

	public String response() {
		return _response;
	}

	public void response(String response) {
		_response = response;
	}
}
