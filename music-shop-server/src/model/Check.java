package model;

import controller.IModel;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Checks database table.
 * 
 */
@Entity
@Table(name="Checks")
@NamedQuery(name="Checks.findAll", query="SELECT c FROM Check c")
public class Check implements Serializable, IModel {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CHECKS_CHECKID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CHECKS_CHECKID_GENERATOR")
	@Column(name="CheckID")
	private int checkID;

	@Column(name="PurchaseAmount")
	private int purchaseAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="PurchaseDate")
	private Date purchaseDate;

	@Column(name="PurchaseType")
	private byte purchaseType;

	@Column(name="PurchaseValue")
	private double purchaseValue;

	//bi-directional many-to-one association to Disk
	@ManyToOne
	@JoinColumn(name="DiskID")
	private Disk disk;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="ClientID")
	private Client client;

	public Check() {
	}

	public int getCheckID() {
		return this.checkID;
	}

	public void setCheckID(int checkID) {
		this.checkID = checkID;
	}

	public int getPurchaseAmount() {
		return this.purchaseAmount;
	}

	public void setPurchaseAmount(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public byte getPurchaseType() {
		return this.purchaseType;
	}

	public void setPurchaseType(byte purchaseType) {
		this.purchaseType = purchaseType;
	}

	public double getPurchaseValue() {
		return this.purchaseValue;
	}

	public void setPurchaseValue(double purchaseValue) {
		this.purchaseValue = purchaseValue;
	}

	public Disk getDisk() {
		return this.disk;
	}

	public void setDisk(Disk disk) {
		this.disk = disk;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public Integer getId() {
		return checkID;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[] {"CheckID", "PurchaseAmount", "PurchaseDate", 
				"PurchaseType", "PurchaseValue"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[] {checkID, purchaseAmount, purchaseDate, purchaseType, purchaseValue};
	}

	@Override
	public void updateWith(Object mask) {
		
	}

}