package com.sports.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sports.dao.IStudentDao;
import com.sports.dao.proxy.DepartmentDaoProxy;
import com.sports.dao.proxy.MajorClassDaoProxy;
import com.sports.dao.proxy.NationDaoProxy;
import com.sports.entity.Student;
import com.sports.entity.StudentView;
import com.sports.util.DatabaseConnection;

public class StudentDaoImpl implements IStudentDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private int result = 0;

	public StudentDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int doCreate(Student student) throws Exception {

		String sql = "insert into student(stuNumber,stuPassword,stuName,stuSex,stuIdentification,stuIsGat,stuPhone,departmentId,majorId,gradeNow,nationMark,birthday,address,politics) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getStuNumber());
			pstmt.setString(2, student.getStuPassword());
			pstmt.setString(3, student.getStuName());
			pstmt.setString(4, student.getStuSex());
			pstmt.setString(5, student.getStuIdentification());
			pstmt.setString(6, student.getStuIsGat());
			pstmt.setString(7, student.getStuPhone());
			pstmt.setInt(8, student.getDepartmentId());
			pstmt.setInt(9, student.getMajorId());
			pstmt.setString(10, student.getGradeNow());
			pstmt.setInt(11, student.getNationMark());
			pstmt.setString(12, student.getBithday());
			pstmt.setString(13, student.getAddress());
			pstmt.setString(14, student.getPolitics());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 DatabaseConnection.closePreparedStatement(pstmt);
			 DatabaseConnection.closeConnection(conn);
		}
		return result;
	}

	@Override
	public int doDelete(int id) throws Exception {

		String sql = "delete from student where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return result;
	}

	@Override
	public int doUpdate(int id, Student student) throws Exception {

		String sql = "update student set stuNumber=?,stuPassword=?,stuName=?,stuSex=?,stuIdentification=?,stuIsGat=?,stuPhone=?,departmentId=?,majorId=?,gradeNow=?,nationMark=?,birthday=?,address=?,politics=? where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getStuNumber());
			pstmt.setString(2, student.getStuPassword());
			pstmt.setString(3, student.getStuName());
			pstmt.setString(4, student.getStuSex());
			pstmt.setString(5, student.getStuIdentification());
			pstmt.setString(6, student.getStuIsGat());
			pstmt.setString(7, student.getStuPhone());
			pstmt.setInt(8, student.getDepartmentId());
			pstmt.setInt(9, student.getMajorId());
			pstmt.setString(10, student.getGradeNow());
			pstmt.setInt(11, student.getNationMark());
			pstmt.setString(12, student.getBithday());
			pstmt.setString(13, student.getAddress());
			pstmt.setString(14, student.getPolitics());
			pstmt.setInt(15, id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return result;
	}

	@Override
	public List<Student> findAll() throws Exception {

		List<Student> studentList = new ArrayList<Student>();
		Student student = null;
		String sql = "select * from student";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt(1));
				student.setStuNumber(rs.getString(2));
				student.setStuPassword(rs.getString(3));
				student.setStuName(rs.getString(4));
				student.setStuSex(rs.getString(5));
				student.setStuIdentification(rs.getString(6));
				student.setStuIsGat(rs.getString(7));
				student.setStuPhone(rs.getString(8));
				student.setDepartmentId(rs.getInt(9));
				student.setMajorId(rs.getInt(10));
				student.setGradeNow(rs.getString(11));
				student.setNationMark(rs.getInt(12));
				student.setBithday(rs.getString(13));
				student.setAddress(rs.getString(14));
				student.setPolitics(rs.getString(15));
				studentList.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return studentList;
	}

	@Override
	public List<StudentView> findAllV() throws Exception {
		
		return null;
	}

	@Override
	public Student findById(int id) throws Exception {

		Student student = null;
		String sql = "select * from student where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt(1));
				student.setStuNumber(rs.getString(2));
				student.setStuPassword(rs.getString(3));
				student.setStuName(rs.getString(4));
				student.setStuSex(rs.getString(5));
				student.setStuIdentification(rs.getString(6));
				student.setStuIsGat(rs.getString(7));
				student.setStuPhone(rs.getString(8));
				student.setDepartmentId(rs.getInt(9));
				student.setMajorId(rs.getInt(10));
				student.setGradeNow(rs.getString(11));
				student.setNationMark(rs.getInt(12));
				student.setBithday(rs.getString(13));
				student.setAddress(rs.getString(14));
				student.setPolitics(rs.getString(15));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return student;
	}

	@Override
	public StudentView findByIdV(int id) throws Exception {
		
		StudentView sv = null;
		String sql = "select * from student where id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sv = new StudentView();
				sv.setId(rs.getInt(1));
				sv.setStuNumber(rs.getString(2));
				sv.setStuPassword(rs.getString(3));
				sv.setStuName(rs.getString(4));
				sv.setStuSex(rs.getString(5));
				sv.setStuIdentification(rs.getString(6));
				sv.setStuIsGat(rs.getString(7));
				sv.setStuPhone(rs.getString(8));
				
				//部门转换
				int depId=rs.getInt(9);
				DepartmentDaoProxy ddp=new DepartmentDaoProxy();
				sv.setDepartmentName(ddp.findById(depId).getDepartmentName());
				
				//专业转换
				int majId=rs.getInt(10);
				MajorClassDaoProxy mcdp=new MajorClassDaoProxy();
				sv.setMajorClassName(mcdp.findById(majId).getMajorClassName());
				
				sv.setGradeNow(rs.getString(11));
				
				//民族转换
				int nationId=rs.getInt(12);
				NationDaoProxy ndp=new NationDaoProxy();
				sv.setNationName(ndp.getNameById(nationId));
				
				sv.setBirthday(rs.getString(13));
				sv.setAddress(rs.getString(14));
				sv.setPolitics(rs.getString(15));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return sv;
	}

	@Override
	public Student findByStuNumber(String stuNumber) throws Exception {

		Student student = null;
		String sql = "select * from student where stuNumber=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stuNumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt(1));
				student.setStuNumber(rs.getString(2));
				student.setStuPassword(rs.getString(3));
				student.setStuName(rs.getString(4));
				student.setStuSex(rs.getString(5));
				student.setStuIdentification(rs.getString(6));
				student.setStuIsGat(rs.getString(7));
				student.setStuPhone(rs.getString(8));
				student.setDepartmentId(rs.getInt(9));
				student.setMajorId(rs.getInt(10));
				student.setGradeNow(rs.getString(11));
				student.setNationMark(rs.getInt(12));
				student.setBithday(rs.getString(13));
				student.setAddress(rs.getString(14));
				student.setPolitics(rs.getString(15));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return student;
	}

	@Override
	public StudentView findByStuNumberV(String stuNumber) throws Exception {
		StudentView studentView = null;
		String sql = "select * from student where stuNumber=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stuNumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				studentView = new StudentView();
				studentView.setId(rs.getInt(1));
				studentView.setStuNumber(rs.getString(2));
				studentView.setStuPassword(rs.getString(3));
				studentView.setStuName(rs.getString(4));
				studentView.setStuSex(rs.getString(5));
				studentView.setStuIdentification(rs.getString(6));
				studentView.setStuIsGat(rs.getString(7));
				studentView.setStuPhone(rs.getString(8));
				
				//得到具体名称
				int departmentId=rs.getInt(9);
				DepartmentDaoProxy ddp=new DepartmentDaoProxy();
				studentView.setDepartmentName(ddp.findById(departmentId).getDepartmentName());
				
				//取得具体名称
				int majorClassId=rs.getInt(10);
				MajorClassDaoProxy mcdp=new MajorClassDaoProxy();
				studentView.setMajorClassName(mcdp.findById(majorClassId).getMajorClassName());
				
				studentView.setGradeNow(rs.getString(11));
				
				//获得具体民族名称
				int nationId=rs.getInt(12);
				NationDaoProxy ndp=new NationDaoProxy();
				studentView.setNationName(ndp.getNameById(nationId));
				
				studentView.setBirthday(rs.getString(13));
				studentView.setAddress(rs.getString(14));
				studentView.setPolitics(rs.getString(15));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return studentView;
	}

	@Override
	public List<Student> findByFourCondition(String stuNumber, String stuName,
			int stuMajId, String stuGradeNow) throws Exception {

		List<Student> studentList = new ArrayList<Student>();
		Student student = null;
		String sql = "select * from student where stuNumber like ? and stuName like ? and majorId=? and gradeNow=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + stuNumber + "%");
			pstmt.setString(2, "%" + stuName + "%");
			pstmt.setInt(3, stuMajId);
			pstmt.setString(4, stuGradeNow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt(1));
				student.setStuNumber(rs.getString(2));
				student.setStuPassword(rs.getString(3));
				student.setStuName(rs.getString(4));
				student.setStuSex(rs.getString(5));
				student.setStuIdentification(rs.getString(6));
				student.setStuIsGat(rs.getString(7));
				student.setStuPhone(rs.getString(8));
				student.setDepartmentId(rs.getInt(9));
				student.setMajorId(rs.getInt(10));
				student.setGradeNow(rs.getString(11));
				student.setNationMark(rs.getInt(12));
				student.setBithday(rs.getString(13));
				student.setAddress(rs.getString(14));
				student.setPolitics(rs.getString(15));
				studentList.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return studentList;
	}

	@Override
	public List<Student> findByThreeCondition(String stuNumber, String stuName,
			String stuGradeNow) throws Exception {
		List<Student> studentList = new ArrayList<Student>();
		Student student = null;
		String sql = "select * from student where stuNumber like ? and stuName like ? and gradeNow=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + stuNumber + "%");
			pstmt.setString(2, "%" + stuName + "%");
			pstmt.setString(3, stuGradeNow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt(1));
				student.setStuNumber(rs.getString(2));
				student.setStuPassword(rs.getString(3));
				student.setStuName(rs.getString(4));
				student.setStuSex(rs.getString(5));
				student.setStuIdentification(rs.getString(6));
				student.setStuIsGat(rs.getString(7));
				student.setStuPhone(rs.getString(8));
				student.setDepartmentId(rs.getInt(9));
				student.setMajorId(rs.getInt(10));
				student.setGradeNow(rs.getString(11));
				student.setNationMark(rs.getInt(12));
				student.setBithday(rs.getString(13));
				student.setAddress(rs.getString(14));
				student.setPolitics(rs.getString(15));
				studentList.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.closeResultSet(rs);
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return studentList;
	}

	@Override
	public int doChangePassword(String stuNumber, String stuPassword)
			throws Exception {
		
		String sql="update student set stuPassword=? where stuNumber=?";
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, stuPassword);
			pstmt.setString(2, stuNumber);
			result=pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DatabaseConnection.closePreparedStatement(pstmt);
			DatabaseConnection.closeConnection(conn);
		}
		return result;
	}

}
