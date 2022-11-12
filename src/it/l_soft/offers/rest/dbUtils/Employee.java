package it.l_soft.offers.rest.dbUtils;

public class Employee extends DBInterface
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8565107359617709696L;
	
	protected int idEmployee = 0;
	protected String firstName = "";
	protected String middleName = "";
	protected String lastName = "";
	protected String country = "";
	protected String email = "";
	protected String mobilePhone = "";
	protected String deskPhone = "";
	protected int role = 0;
	protected boolean active = false;
	protected String token = "";
	protected String password = "";
	protected String clientSecure = "";
 
	private void setNames()
	{
		tableName = "employees";
		idColName = "idEmployee";
	}

	public Employee()
	{
		setNames();
	}

	public Employee(DBConnection conn, int id) throws Exception
	{
		getEmployee(conn, id);
	}

	public void getEmployee(DBConnection conn, int id) throws Exception
	{
		setNames();
		String sql = "SELECT * " +
					 "FROM " + tableName + " " +
					 "WHERE " + idColName + " = " + id;
		this.populateObject(conn, sql, this);
	}

	public static Employee getByToken(DBConnection conn, String token) throws Exception
	{
		Employee e = new Employee();
		String sql = "SELECT * " +
					 "FROM employees " +
					 "WHERE token = '" + token + "'";
		e.populateObject(conn, sql, Employee.class);
		return e;
	}

	public static Employee getByEMail(DBConnection conn, String email) throws Exception
	{
		Employee e = new Employee();
		String sql = "SELECT * " +
					 "FROM employees " +
					 "WHERE email = '" + email + "'";
		e.populateObject(conn, sql, Employee.class);
		return e;
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getDeskPhone() {
		return deskPhone;
	}

	public void setDeskPhone(String deskPhone) {
		this.deskPhone = deskPhone;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientSecure() {
		return clientSecure;
	}

	public void setClientSecure(String clientSecure) {
		this.clientSecure = clientSecure;
	}
	
}

