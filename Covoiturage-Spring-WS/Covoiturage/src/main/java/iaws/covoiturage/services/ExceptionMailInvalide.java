package iaws.covoiturage.services;

@SuppressWarnings("serial")
public class ExceptionMailInvalide extends Exception {

	
	
	/** 
	* Crée une nouvelle instance de ExceptionMailInvalide 
	*/  
	public ExceptionMailInvalide() {}  
	
	/** 
	* Crée une nouvelle instance de ExceptionMailInvalide 
	* @param message Le message détaillant exception 
	*/  
	public ExceptionMailInvalide(String message) {  
		super(message); 
	}  
	/** 
	* Crée une nouvelle instance de ExceptionMailInvalide 
	* @param cause L'exception à l'origine de cette exception 
	*/  
	public ExceptionMailInvalide(Throwable cause) {  
		super(cause); 
	}  
	/** 
	* Crée une nouvelle instance de ExceptionMailInvalide 
	* @param message Le message détaillant exception 
	* @param cause L'exception à l'origine de cette exception 
	*/  
	public ExceptionMailInvalide(String message, Throwable cause) {  
		super(message, cause); 
	} 
	
	
}
