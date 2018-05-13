package controller;

import javax.swing.table.TableModel;

public interface IController {

	void createDB();

	TableModel getModel(String className);
	void add(Object obj);
	void edit(int id, Object obj);
	void delete(int id, String className);
	public TableModel query();

}