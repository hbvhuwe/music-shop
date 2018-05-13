package controller;

public interface IModel {
	public Integer getId();
	public String[] getTableHeaders();
	public Object[] getTableRowData();
	public void updateWith(Object mask);
}
