package model;

import java.io.Serializable;
import javax.persistence.*;

import controller.IModel;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Disk database table.
 * 
 */
@Entity
@Table(name = "Disk")
@NamedQuery(name="Disk.findAll", query="SELECT d FROM Disk d")
public class Disk implements Serializable, IModel {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DISK_DISKID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DISK_DISKID_GENERATOR")
	@Column(name="DiskID")
	private int diskID;

	@Column(name="Amount")
	private int amount;

	@Column(name="Name")
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="PresentDate")
	private Date presentDate;

	@Column(name="Price")
	private double price;

	//bi-directional many-to-one association to Check
	@OneToMany(mappedBy="disk")
	private List<Check> checks;

	//bi-directional many-to-one association to Composition
	@OneToMany(mappedBy="disk")
	private List<Composition> compositions;

	//bi-directional many-to-one association to Group
	@ManyToOne
	@JoinColumn(name="GroupID")
	private Group group;

	public Disk() {
	}

	public int getDiskID() {
		return this.diskID;
	}

	public void setDiskID(int diskID) {
		this.diskID = diskID;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPresentDate() {
		return this.presentDate;
	}

	public void setPresentDate(Date presentDate) {
		this.presentDate = presentDate;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Check> getChecks() {
		return this.checks;
	}

	public void setChecks(List<Check> checks) {
		this.checks = checks;
	}

	public Check addCheck(Check check) {
		getChecks().add(check);
		check.setDisk(this);

		return check;
	}

	public Check removeCheck(Check check) {
		getChecks().remove(check);
		check.setDisk(null);

		return check;
	}

	public List<Composition> getCompositions() {
		return this.compositions;
	}

	public void setCompositions(List<Composition> compositions) {
		this.compositions = compositions;
	}

	public Composition addComposition(Composition composition) {
		getCompositions().add(composition);
		composition.setDisk(this);

		return composition;
	}

	public Composition removeComposition(Composition composition) {
		getCompositions().remove(composition);
		composition.setDisk(null);

		return composition;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public Integer getId() {
		return diskID;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[] {"DiskID", "Amount", "Name", "PresentDate", "Price"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[] {diskID, amount, name, presentDate, price};
	}

	@Override
	public void updateWith(Object mask) {
		this.name = ((Disk) mask).name;
		this.amount = ((Disk) mask).amount;
		this.price = ((Disk) mask).price;
	}

}