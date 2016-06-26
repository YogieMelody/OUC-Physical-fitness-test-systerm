package com.sports.dao.proxy;

import java.util.ArrayList;
import java.util.List;

import com.sports.dao.INoticeDao;
import com.sports.dao.IStudentDao;
import com.sports.dao.Impl.NoticeDaoImpl;
import com.sports.dao.Impl.StudentDaoImpl;
import com.sports.entity.Student;
import com.sports.entity.StudentView;
import com.sports.util.DatabaseConnection;

public class StudentDaoProxy implements IStudentDao {

	private DatabaseConnection dbc = null;
	private IStudentDao dao = null;
	private int result = 0;

	public StudentDaoProxy() throws Exception {
		this.dbc = new DatabaseConnection();
		this.dao = new StudentDaoImpl(this.dbc.getConnection());
	}

	@Override
	public int doCreate(Student student) throws Exception {

		try {
			result = this.dao.doCreate(student);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int doDelete(int id) throws Exception {

		try {
			result = this.dao.doDelete(id);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int doUpdate(int id, Student student) throws Exception {

		try {
			result = this.dao.doUpdate(id, student);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public List<Student> findAll() throws Exception {

		List<Student> studentList = new ArrayList<Student>();

		try {
			studentList = this.dao.findAll();
		} catch (Exception e) {
			throw e;
		}
		return studentList;
	}

	@Override
	public List<StudentView> findAllV() throws Exception {
		
		List<StudentView> studentViewList=new ArrayList<StudentView>();
		
		try{
			studentViewList=this.dao.findAllV();
		}catch(Exception e){
			throw e;
		}
		return studentViewList;
	}

	@Override
	public Student findById(int id) throws Exception {

		Student student = null;

		try {
			student = this.dao.findById(id);
		} catch (Exception e) {
			throw e;
		}
		return student;
	}

	@Override
	public StudentView findByIdV(int id) throws Exception {
		
		StudentView sv=null;
		
		try{
			sv=this.dao.findByIdV(id);
		}catch(Exception e){
			throw e;
		}
		return sv;
	}

	@Override
	public Student findByStuNumber(String stuNumber) throws Exception {

		Student student = null;

		try {
			student = this.dao.findByStuNumber(stuNumber);
		} catch (Exception e) {
			throw e;
		}
		return student;
	}

	@Override
	public StudentView findByStuNumberV(String stuNumber) throws Exception {
		
		StudentView studentView=null;
		try{
			studentView=this.dao.findByStuNumberV(stuNumber);
		}catch(Exception e){
			throw e;
		}
		return studentView;
	}

	@Override
	public List<Student> findByFourCondition(String stuNumber, String stuName,
			int stuMajId, String stuGradeNow) throws Exception {

		List<Student> studentList = new ArrayList<Student>();

		try {
			studentList = this.dao.findByFourCondition(stuNumber, stuName,
					stuMajId, stuGradeNow);
		} catch (Exception e) {
			throw e;
		}
		return studentList;
	}

	@Override
	public List<Student> findByThreeCondition(String stuNumber, String stuName,
			String stuGradeNow) throws Exception {
		
		List<Student> studentList=new ArrayList<Student>();
		
		try{
			studentList=this.dao.findByThreeCondition(stuNumber, stuName, stuGradeNow);
		}catch(Exception e){
			throw e;
		}
		return studentList;
	}

	@Override
	public int doChangePassword(String stuNumber, String stuPassword)
			throws Exception {
		
		try {
			result = this.dao.doChangePassword(stuNumber, stuPassword);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

}
