package com.yedam.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class SampleExe {
	public static void main(String[] args) {

		SqlSessionFactory sqlSessionFactory = com.yedam.common.DataSource.getInstance();
//		try (SqlSession session = sqlSessionFactory.openSession()) {
//			  Employee emp = session.selectOne("com.yedam.common.NoticeMapper.getEmp", 100);
//			  System.out.println(emp);
//			}
//		try (SqlSession session = sqlSessionFactory.openSession()) {
//			  //Employee emp = session.selectList("com.yedam.common.NoticeMapper.empList", 100);
//			  List<Employee> emp = session.selectList("com.yedam.common.NoticeMapper.empList",100);
//			  System.out.println(emp);
//			}
//		try (SqlSession session = sqlSessionFactory.openSession(true)) {
//			  session.delete("com.yedam.common.NoticeMapper.delEmp",225);
//			  List<Employee> emp = session.selectList("com.yedam.common.NoticeMapper.empList");
//			  System.out.println(emp);
//			}
		try (SqlSession session = sqlSessionFactory.openSession(true)) {//openSession(true) => 자동commit
			Employee empl = new Employee();
			empl.setEmployeeId(355);
			empl.setLastName("dod2o");
			empl.setFirstName("da2da");
			empl.setEmail("do121@sad.com");
			empl.setJobId("IT_PROG");
			session.insert("com.yedam.common.NoticeMapper.addEmp",empl);
			List<Employee> emp = session.selectList("com.yedam.common.NoticeMapper.empList");
			System.out.println(emp);
		}

	}
}
