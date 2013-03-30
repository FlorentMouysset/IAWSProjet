package iaws.covoiturage.services;

public class ExceptionAdresseInvalide extends Exception {
 
	
	
	/** 
	* Crée une nouvelle instance de ExceptionAdresseInvalide 
	*/  
	public ExceptionAdresseInvalide() {}  
	
	/** 
	* Crée une nouvelle instance de ExceptionAdresseInvalide 
	* @param message Le message détaillant exception 
	*/  
	public ExceptionAdresseInvalide(String message) {  
		super(message); 
	}  
	/** 
	* Crée une nouvelle instance de ExceptionAdresseInvalide 
	* @param cause L'exception à l'origine de cette exception 
	*/  
	public ExceptionAdresseInvalide(Throwable cause) {  
		super(cause); 
	}  
	/** 
	* Crée une nouvelle instance de ExceptionAdresseInvalide 
	* @param message Le message détaillant exception 
	* @param cause L'exception à l'origine de cette exception 
	*/  
	public ExceptionAdresseInvalide(String message, Throwable cause) {  
		super(message, cause); 
	} 
	
	
}
