package it.l_soft.offers.rest.dbUtils;

import java.util.ArrayList;
import java.util.Date;

public class Action extends DBInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7909858682784332921L;
	
	protected int idAction;
	protected int idOffer;
	protected String type;
	protected Date timestamp;
	protected Date reminder;
	
	private void setNames()
	{
		tableName = "actions";
		idColName = "idAction";
	}

	public Action()
	{
		setNames();
	}

	public Action(DBConnection conn, int id) throws Exception
	{
		getAction(conn, id);
	}

	public void getAction(DBConnection conn, int id) throws Exception
	{
		setNames();
		String sql = "SELECT * " +
					 "FROM " + tableName + " " +
					 "WHERE " + idColName + " = " + id;
		this.populateObject(conn, sql, this);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Action> getActionByOffer(DBConnection conn, int idOffer) throws Exception
	{
		String sql = "SELECT * " +
					 "FROM actions " +
					 "WHERE idOffer = " + idOffer + " " +
					 "ORDER BY timestamp DESC";
		return (ArrayList<Action>) populateCollection(conn, sql, Action.class);
	}

	public int getIdAction() {
		return idAction;
	}

	public void setIdAction(int idAction) {
		this.idAction = idAction;
	}

	public int getIdOffer() {
		return idOffer;
	}

	public void setIdOffer(int idOffer) {
		this.idOffer = idOffer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}
}
