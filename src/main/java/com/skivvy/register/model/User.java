package com.skivvy.register.model;

import com.skivvy.register.Types.CookingStyle;
import com.skivvy.register.Types.Religion;
import com.skivvy.register.Types.WorkingCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data @NoArgsConstructor
@Document(collection = "users")
@ApiModel(description = "All Details of the User.")
public class User {
	private String id;
	private String maidId;
	private String name;
	private Integer age;
	private String mobile;
	private Religion religion;
	private Boolean isPartTime;
	private CookingStyle cookingStyle;
	private List<WorkingCategory> workCategories;
	private List<WorkingHour> workingHours;
	private List<Address> workLocations;
	private Address localAddress;
	private Address permanentAddress;
	private List<Identity> identities;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaidId() {
		return maidId;
	}
	public void setMaidId(String maidId) {
		this.maidId = maidId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Religion getReligion() {
		return religion;
	}
	public void setReligion(Religion religion) {
		this.religion = religion;
	}
	public Boolean getIsPartTime() {
		return isPartTime;
	}
	public void setIsPartTime(Boolean isPartTime) {
		this.isPartTime = isPartTime;
	}
	public CookingStyle getCookingStyle() {
		return cookingStyle;
	}
	public void setCookingStyle(CookingStyle cookingStyle) {
		this.cookingStyle = cookingStyle;
	}
	public List<WorkingCategory> getWorkCategories() {
		return workCategories;
	}
	public void setWorkCategories(List<WorkingCategory> workCategories) {
		this.workCategories = workCategories;
	}
	public List<WorkingHour> getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(List<WorkingHour> workingHours) {
		this.workingHours = workingHours;
	}
	public List<Address> getWorkLocations() {
		return workLocations;
	}
	public void setWorkLocations(List<Address> workLocations) {
		this.workLocations = workLocations;
	}
	public Address getLocalAddress() {
		return localAddress;
	}
	public void setLocalAddress(Address localAddress) {
		this.localAddress = localAddress;
	}
	public Address getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public List<Identity> getIdentities() {
		return identities;
	}
	public void setIdentities(List<Identity> identities) {
		this.identities = identities;
	}

}
