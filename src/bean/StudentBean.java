package bean;

public class StudentBean {
	private String id;
	private String name;
	private String sex;
	private String dept_name;
	private int tol_cred;
	private String completed;
	public String getCompleted() {
		return completed;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public int getTol_cred() {
		return tol_cred;
	}
	public void setTol_cred(int tol_cred) {
		this.tol_cred = tol_cred;
	}
}
