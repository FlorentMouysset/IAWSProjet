package iaws.covoiturage.services;

@SuppressWarnings("serial")
public class ExceptionMailDejaUtil extends Exception{


	
	/** 
	* Crée une nouvelle instance de ExceptionMailDejaUtil 
	*/  
	public ExceptionMailDejaUtil() {}  
	
	/** 
	* Crée une nouvelle instance de ExceptionMailDejaUtil 
	* @param message Le message détaillant exception 
	*/  
	public ExceptionMailDejaUtil(String message) {  
		super(message); 
	}  
	/** 
	* Crée une nouvelle instance de ExceptionMailDejaUtil 
	* @param cause L'exception à l'origine de cette exception 
	*/  
	public ExceptionMailDejaUtil(Throwable cause) {  
		super(cause); 
	}  
	/** 
	* Crée une nouvelle instance de ExceptionMailDejaUtil 
	* @param message Le message détaillant exception 
	* @param cause L'exception à l'origine de cette exception 
	*/  
	public ExceptionMailDejaUtil(String message, Throwable cause) {  
		super(message, cause); 
	} 
	
}
