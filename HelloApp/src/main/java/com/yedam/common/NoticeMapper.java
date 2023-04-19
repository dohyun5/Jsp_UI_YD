package com.yedam.common;

import java.util.List;

import com.yedam.domain.Employee;

public interface NoticeMapper {
	public Employee getEmp(int empId);
	public List<Employee> empList();
	public int delEmp(int empId);//mapper.xml과 같아야함.
	public int addEmp(Employee emp);
	
	
}
