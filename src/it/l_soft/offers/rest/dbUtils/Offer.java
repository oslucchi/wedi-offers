package it.l_soft.offers.rest.dbUtils;

import java.util.ArrayList;
import java.util.Date;

public class Offer extends DBInterface
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6127692352674331545L;

	public final int ACTIVE = 1;
	public final int REPLACED = 2;
	public final int RELEASED = 3;
	public final int ACCEPTED = 4;
	public final int REJECTED = 5;
	public final int PARTIALLY_SUPPLIED = 6;
	public final int SUPPLIED = 7;
	
	protected int idOffer;
	protected int idProject;
	protected int revision;
	protected String description;
	protected double value;
	protected String currency;
	protected Date issued;
	protected Date expires;
	protected int status;
	
	private void setNames()
	{
		tableName = "offers";
		idColName = "idOffer";
	}

	public Offer()
	{
		setNames();
	}

	public Offer(DBConnection conn, int id) throws Exception
	{
		getOffer(conn, id);
	}

	public void getOffer(DBConnection conn, int id) throws Exception
	{
		setNames();
		String sql = "SELECT * " +
					 "FROM " + tableName + " " +
					 "WHERE " + idColName + " = " + id;
		this.populateObject(conn, sql, this);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Offer> getOffersByProject(DBConnection conn, int idProject) throws Exception
	{
		String sql = "SELECT * " +
					 "FROM offers " +
					 "WHERE idProject = " + idProject + " " +
					 "ORDER BY revision DESC";
		return (ArrayList<Offer>) populateCollection(conn, sql, Offer.class);
	}

	public int getIdOffer() {
		return idOffer;
	}

	public void setIdOffer(int idOffer) {
		this.idOffer = idOffer;
	}

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public int getRevision() {
		return revision;
	}

	public void setRevision(int revision) {
		this.revision = revision;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getIssued() {
		return issued;
	}

	public void setIssued(Date issued) {
		this.issued = issued;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}	
}

