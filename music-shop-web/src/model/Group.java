package model;

import java.io.Serializable;
import javax.persistence.*;

import controller.IModel;

import java.util.List;


/**
 * The persistent class for the Groups database table.
 * 
 */
@Entity
@Table(name="Groups")
@NamedQuery(name="Group.findAll", query="SELECT g FROM Group g")
public class Group implements Serializable, IModel {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GROUPS_GROUPID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GROUPS_GROUPID_GENERATOR")
	@Column(name="GroupID")
	private int groupID;

	@Column(name="Musician")
	private String musician;

	@Column(name="Name")
	private String name;

	@Column(name="Style")
	private String style;

	//bi-directional many-to-one association to Disk
	@OneToMany(mappedBy="group")
	private List<Disk> disks;

	public Group() {
	}

	public int getGroupID() {
		return this.groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public String getMusician() {
		return this.musician;
	}

	public void setMusician(String musician) {
		this.musician = musician;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public List<Disk> getDisks() {
		return this.disks;
	}

	public void setDisks(List<Disk> disks) {
		this.disks = disks;
	}

	public Disk addDisk(Disk disk) {
		getDisks().add(disk);
		disk.setGroup(this);

		return disk;
	}

	public Disk removeDisk(Disk disk) {
		getDisks().remove(disk);
		disk.setGroup(null);

		return disk;
	}

	@Override
	public Integer getId() {
		return groupID;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[] {"GroupID", "Musician", "Name", "Style"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[] {groupID, musician, name, style};
	}

	@Override
	public void updateWith(Object mask) {
		this.name = ((Group) mask).name;
		this.style = ((Group) mask).style;
	}

}