package iaws.covoiturage.domain.nomenclature;

public class Email {

	private String email;


	public Email(String email) {
		super();
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	@Override
	public boolean equals(Object obj) {
		boolean rest = false;
		if(obj instanceof Email){
			Email emailParam = (Email) obj;
			if(emailParam.equals(email)){
				rest = true;
			}
		}
		return rest;
	}
	
	
	
	
}
