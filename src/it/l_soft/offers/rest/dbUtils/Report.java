package it.l_soft.offers.rest.dbUtils;

import java.util.ArrayList;
import java.util.Date;

public class Report extends DBInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2809301883872774969L;
	
	protected int idReport;
	protected int idOffer;
	protected int idAction;
	protected Date timestamp;
	protected String report;
	

	private void setNames()
	{
		tableName = "reports";
		idColName = "idReport";
	}

	public Report()
	{
		setNames();
	}

	public Report(DBConnection conn, int id) throws Exception
	{
		getReport(conn, id);
	}

	public void getReport(DBConnection conn, int id) throws Exception
	{
		setNames();
		String sql = "SELECT * " +
					 "FROM " + tableName + " " +
					 "WHERE " + idColName + " = " + id;
		this.populateObject(conn, sql, this);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Report> getReportByOffer(DBConnection conn, int idOffer) throws Exception
	{
		String sql = "SELECT * " +
					 "FROM reports " +
					 "WHERE idOffer = " + idOffer + " " +
					 "ORDER BY timestamp DESC";
		return (ArrayList<Report>) populateCollection(conn, sql, Report.class);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Report> getReportByAction(DBConnection conn, int idAction) throws Exception
	{
		String sql = "SELECT * " +
					 "FROM reports " +
					 "WHERE idAction = " + idAction + " " +
					 "ORDER BY timestamp DESC";
		return (ArrayList<Report>) populateCollection(conn, sql, Report.class);
	}

	public int getIdReport() {
		return idReport;
	}

	public void setIdReport(int idReport) {
		this.idReport = idReport;
	}

	public int getIdOffer() {
		return idOffer;
	}

	public void setIdOffer(int idOffer) {
		this.idOffer = idOffer;
	}

	public int getIdAction() {
		return idAction;
	}

	public void setIdAction(int idAction) {
		this.idAction = idAction;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}
}
