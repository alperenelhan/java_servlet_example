package webService;

import java.util.ArrayList;
import java.util.HashMap;

import client.XmlHandler;
import client.Person;
import client.Command;

public class Operations {

	private Command _c;
	private HashMap<String, Person> people;
	XmlHandler<Command> handler;

	public Operations() {
		people = new HashMap<String, Person>();
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

	private String read() throws Exception {
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

	private String update() throws Exception {
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

		// Create object from XML string
		_c = handler.unmarshal(c, Command.class);

		// Execute CRUD operations
		if ("ekle".equals(_c.name())) {
			return create();
		} else if ("gunle".equals(_c.name())) {
			return update();
		} else if ("sil".equals(_c.name())) {
			return delete();
		} else if ("listele".equals(_c.name())) {
			return read();
		} else {
			return null;
		}
	}

}
