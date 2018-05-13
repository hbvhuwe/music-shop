package model;

import java.io.Serializable;
import javax.persistence.*;

import controller.IModel;

import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the Composition database table.
 * 
 */
@Entity
@Table(name = "Composition")
@NamedQuery(name="Composition.findAll", query="SELECT c FROM Composition c")
public class Composition implements Serializable, IModel {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COMPOSITION_COMPOSITIONID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMPOSITION_COMPOSITIONID_GENERATOR")
	@Column(name="CompositionID")
	private int compositionID;

	@Column(name="Duration")
	private Time duration;

	@Column(name="Name")
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="PresentDate")
	private Date presentDate;

	//bi-directional many-to-one association to Disk
	@ManyToOne
	@JoinColumn(name="DiskID")
	private Disk disk;

	public Composition() {
	}

	public int getCompositionID() {
		return this.compositionID;
	}

	public void setCompositionID(int compositionID) {
		this.compositionID = compositionID;
	}

	public Time getDuration() {
		return this.duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
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

	public Disk getDisk() {
		return this.disk;
	}

	public void setDisk(Disk disk) {
		this.disk = disk;
	}

	@Override
	public Integer getId() {
		return compositionID;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[] {"CompositionID", "Duration", "Name", "PresentDate"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[] {compositionID, duration, name, presentDate};
	}

	@Override
	public void updateWith(Object mask) {
		this.name = ((Composition) mask).name;
		this.duration = ((Composition) mask).duration;
	}

}