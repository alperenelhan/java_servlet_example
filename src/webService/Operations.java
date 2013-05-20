package webService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import client.XmlHandler;
import client.Person;
import client.Command;

public class Operations {
	
	private Command _c;
	private HashMap<String, Person> people;
	XmlHandler<Command> handler;
	
	public Operations(){
		people = new HashMap<String,Person>();
		handler = new XmlHandler<>();
	}
	
	private String create() throws Exception {
		Person p = new Person(_c);
		Command c = new Command();
		if (people.containsKey(p.id())) {
			c.response("Öğrenci numarası sisteme önceden kaydedilmiştir");
		} else {
			people.put(p.id(), p);
			c.response("Ekleme işlemi tamamlanmıştır");
		}	
		
		return handler.marshal(c, Command.class);
	}
	
	private String read() throws Exception{
		Person p = new Person(_c);
		Command c = new Command();
		ArrayList<Person> ps = new ArrayList<Person>();
		if (people.size() == 0) {
			c.response("Sistemde öğrenci bulunmamaktadır");
		} else if (p.id() != null) {
			if (!people.containsKey(p.id())) {
				c.response("Öğrenci numarası sistemde bulunmamaktadır");
			} else {
				ps.add(people.get(p.id()));
				c.data(ps);
			}
		} else if (p.id() == null) {
			ps = new ArrayList<Person>(people.values());
			c.data(ps);
		}
		
		return handler.marshal(c, Command.class);
		
	}
	
	private String update() throws Exception{
		Person p = new Person(_c);
		Command c = new Command();
		if (!people.containsKey(p.id())) {
			c.response("Öğrenci numarası sistemde bulunmamaktadır");
		} else {
			Person person = people.get(p.id());
			person.date(p.date());
			person.name(p.name());
			c.response("Günleme işlemi tamamlanmıştır");
		}
		
		return handler.marshal(c, Command.class);
	}
	
	private String delete() throws Exception {
		Person p = new Person(_c);
		Command c = new Command();
		if (!people.containsKey(p.id())) {
			c.response("Öğrenci numarası sistemde bulunmamaktadır");
		} else {
			people.remove(p.id());
			c.response("Silme işlemi tamamlanmıştır");
		}
		
		return handler.marshal(c, Command.class);
	}
	
	public String exec(String c) throws Exception {
		
		_c = handler.unmarshal(c, Command.class);
		switch (_c.name()) {
			case "ekle":
				return create();
				
			case "gunle":
				return update();
				
			case "sil":
				return delete();
				
			case "listele":
				return read();
				
			default:
				return null;
		}
	}

}
