package model;

import java.io.Serializable;
import javax.persistence.*;

import controller.IModel;

import java.util.List;


/**
 * The persistent class for the Client database table.
 * 
 */
@Entity
@Table(name = "Client")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable, IModel {
	@Override
	public String toString() {
		return "Client [clientID=" + clientID + ", discount=" + discount + ", name=" + name + ", checks=" + checks
				+ "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CLIENT_CLIENTID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENT_CLIENTID_GENERATOR")
	@Column(name="ClientID")
	private int clientID;

	@Column(name="Discount")
	private double discount;

	@Column(name="Name")
	private String name;

	//bi-directional many-to-one association to Check
	@OneToMany(mappedBy="client")
	private List<Check> checks;

	public Client() {
	}

	public int getClientID() {
		return this.clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Check> getChecks() {
		return this.checks;
	}

	public void setChecks(List<Check> checks) {
		this.checks = checks;
	}

	public Check addCheck(Check check) {
		getChecks().add(check);
		check.setClient(this);

		return check;
	}

	public Check removeCheck(Check check) {
		getChecks().remove(check);
		check.setClient(null);

		return check;
	}

	@Override
	public Integer getId() {
		return clientID;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[] {"ClientID", "Discount", "Name"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[] {clientID, discount, name};
	}

	@Override
	public void updateWith(Object mask) {
		// TODO Auto-generated method stub
		
	}

}