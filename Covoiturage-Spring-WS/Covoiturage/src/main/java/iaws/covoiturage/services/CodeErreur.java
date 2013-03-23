package iaws.covoiturage.services;

public enum CodeErreur {
	Ok(-1), Mail_Use(100), Mail_Inv(110), OSMAdr_Inv(200);
	private final int code;
	private CodeErreur(int code) {
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
}
//
//public enum Rotation_enum{Rot_90(90), Rot_180(180), Rot_270(270);
//private final int valeur;
//Rotation_enum(int val) {
//	this.valeur =val;
//}
//
//public int getValeur(){
//	return valeur;
//}
//
//};