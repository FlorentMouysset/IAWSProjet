package exceptionsOSMServices;

@SuppressWarnings("serial")
public class ExceptionInternalError extends Exception {

	
	
	/** 
	* Crée une nouvelle instance de ExceptionInternalError 
	*/  
	public ExceptionInternalError() {}  
	
	/** 
	* Crée une nouvelle instance de ExceptionInternalError 
	* @param message Le message détaillant exception 
	*/  
	public ExceptionInternalError(String message) {  
		super(message); 
	}  
	/** 
	* Crée une nouvelle instance de ExceptionInternalError 
	* @param cause L'exception à l'origine de cette exception 
	*/  
	public ExceptionInternalError(Throwable cause) {  
		super(cause); 
	}  
	/** 
	* Crée une nouvelle instance de ExceptionInternalError 
	* @param message Le message détaillant exception 
	* @param cause L'exception à l'origine de cette exception 
	*/  
	public ExceptionInternalError(String message, Throwable cause) {  
		super(message, cause); 
	} 
	
	
}
