package com.skivvy.register.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Identity {
private String idName;
private String idNumber;
public String getIdName() {
	return idName;
}
public void setIdName(String idName) {
	this.idName = idName;
}
public String getIdNumber() {
	return idNumber;
}
public void setIdNumber(String idNumber) {
	this.idNumber = idNumber;
}

}
