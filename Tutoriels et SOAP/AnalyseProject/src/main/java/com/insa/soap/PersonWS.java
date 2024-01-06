package com.insa.soap;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName="manager")

public class PersonWS {
	public ArrayList<Person> personList = new ArrayList<Person>();
	@WebMethod(operationName="add")
	public void addPerson(@WebParam(name="Personne") Person p) {
		personList.add(p);
	}
	@WebMethod(operationName="fecthAll")
	public ArrayList<Person> getPersonList(){
		return personList;}
	@WebMethod(operationName="clearAll")
	public void clearList() {
		personList.clear();
	}
	
}
