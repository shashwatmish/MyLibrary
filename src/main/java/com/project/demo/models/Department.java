package com.project.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department 
{
	private int deptid;
	private String deptname;
	private String description;
	private String establishedyear;
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEstablishedyear() {
		return establishedyear;
	}
	public void setEstablishedyear(String establishedyear) {
		this.establishedyear = establishedyear;
	}
	@Override
	public String toString() {
		return "Department [deptid=" + deptid + ", deptname=" + deptname + ", description=" + description
				+ ", establishedYear=" + establishedyear + "]";
	}
	
	
}
