package it.l_soft.offers.rest.dbUtils;

import java.util.ArrayList;
import java.util.Date;

public class Project extends DBInterface
{
	public static final int STATUS_ACTIVE = 1;
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6127692352674331545L;

    protected int idProject;
    protected String name;
    protected String customerProjectReference;
    protected int idCustomerBranch;
    protected int idReferent;
    protected String description;
    protected String country; // country where the project is located
    protected int status;
	protected Date created;
    protected Date terminated;
    protected String locale; // country owning the project

    protected String customerName;
    protected String customerBranchName;
    protected String referentName;
 
	private void setNames()
	{
		tableName = "projects";
		idColName = "idProject";
	}

	public Project()
	{
		setNames();
	}

	public Project(DBConnection conn, int id) throws Exception
	{
		getProject(conn, id);
	}

	public void getProject(DBConnection conn, int id) throws Exception
	{
		setNames();
		String sql = "SELECT * " +
					 "FROM " + tableName + " " +
					 "WHERE " + idColName + " = " + id;
		this.populateObject(conn, sql, this);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Project> getProjectByStatus(DBConnection conn, int status) throws Exception
	{
		String sql = "SELECT p.*, c.companyName customerName, cb.branchName customerBranchName, " +
					 "       r.lastName referentName " +
					 "FROM projects p INNER JOIN customerReferents r ON " +
					 "        (p.idCustomerReferent = r.idCustomerReferent) " +
					 "     INNER JOIN customerBranches cb ON " +
					 "        (p.idCustomerBranch = cb.idCustomerBranch) " +
					 "     INNER JOIN customers c ON " +
					 "        (c.idCustomer = cb.idCustomer) " +
					 "WHERE status = " + status;
		ArrayList<Project> projectsVisible = new ArrayList<Project>();
//		for(Project prj : (ArrayList<Project>) populateCollection(conn, sql, Project.class))
//		{
//			if ((e == null) ||
//				((prj.getLocale().compareTo(e.getCountry()) != 0) && (e.getRole() >= Roles.COUNTRY_MANAGEMENT)) ||
//				((prj.getLocale().compareTo(e.getCountry()) == 0) && (e.getRole() >= Roles.SALES_INTERNAL)))
//			{
//				projectsVisible.add(prj);				
//			}
//		}
//		return projectsVisible;
		return (ArrayList<Project>) populateCollection(conn, sql, Project.class);
	}
	
	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomerProjectReference() {
		return customerProjectReference;
	}

	public void setCustomerProjectReference(String customerProjectReference) {
		this.customerProjectReference = customerProjectReference;
	}

	public int getIdCustomerBranch() {
		return idCustomerBranch;
	}

	public void setIdCustomerBranch(int idCustomerBranch) {
		this.idCustomerBranch = idCustomerBranch;
	}

	public int getIdReferent() {
		return idReferent;
	}

	public void setIdReferent(int idReferent) {
		this.idReferent = idReferent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getTerminated() {
		return terminated;
	}

	public void setTerminated(Date terminated) {
		this.terminated = terminated;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerBranchName() {
		return customerBranchName;
	}

	public void setCustomerBranchName(String customerBranchName) {
		this.customerBranchName = customerBranchName;
	}

	public String getReferentName() {
		return referentName;
	}

	public void setReferentName(String referentName) {
		this.referentName = referentName;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
}

