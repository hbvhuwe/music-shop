package controller;

import java.util.List;
import javax.persistence.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Check;
import model.Client;
import model.Composition;
import model.Disk;

public class JpaController implements IController {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("music-shop");

	@Override
	public void createDB() {
	}

	@Override
	public TableModel getModel(String className) {
		try {
			Class<?> clazz = Class.forName("model." + className);
			IModel obj = (IModel) clazz.newInstance();
			String[] header = obj.getTableHeaders();

			List<Client> list = getObjectList(clazz);
			if (list == null || list.size() == 0)
				return new DefaultTableModel(null, header);
			Object[][] array = new Object[list.size()][header.length];

			int i = 0;
			for (Object s : list)
				array[i++] = ((IModel) s).getTableRowData();

			return new DefaultTableModel(array, header);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public void add(Object obj) {
		Class<? extends Object> clazz = obj.getClass();
		if (exist((IModel) obj))
			return;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LaboratoryWork02");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void edit(int id, Object obj) {
		Class<? extends Object> clazz = obj.getClass();
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Object updObj = em.find(clazz, id);
			((IModel) updObj).updateWith(obj);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(int id, String className) {
		EntityManager em = emf.createEntityManager();
		try {
			Class<?> clazz = Class.forName("model." + className);
			Object delObj = em.find(clazz, id);
			em.getTransaction().begin();
			em.remove(delObj);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public TableModel query() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select c from Client c order by c.name");
		List<Client> list = query.getResultList();

		String[][] arr = new String[list.size()][3];

		int i = 0;
		for (Client tmp : list) {
			System.out.println(tmp);
			arr[i][0] = tmp.getName();
			arr[i][1] = String.valueOf(tmp.getClientID());
			arr[i][2] = String.valueOf(tmp.getDiscount());
			++i;
		}
		return new DefaultTableModel(arr, new String[] { "Name", "ID", "Discount" });
	}

	public List<Client> getObjectList(Class<?> clazz) {
		EntityManager em = emf.createEntityManager();
		String queryName = clazz.getSimpleName() + ".findAll";
		List<Client> list = em.createNamedQuery(queryName).getResultList();

		em.close();
		return list;
	}

	public boolean exist(IModel obj) {
		Class<?> clazz = obj.getClass();
		List<Client> list = getObjectList(clazz);

		if (list != null && list.size() != 0)
			for (Object current : list)
				if (current.equals(obj))
					return true;
		return false;
	}

	public TableModel doQuery1() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select a from Disk a where a.group.groupID = ?1");
		q.setParameter(1, 3);
		List<Disk> list = q.getResultList();
		String[][] arr = new String[list.size()][3];
		int i = 0;
		for (Disk m : list) {
			arr[i][0] = String.valueOf(m.getId());
			arr[i][1] = m.getName();
			arr[i++][2] = String.valueOf(m.getAmount());
		}
		DefaultTableModel model = new DefaultTableModel(arr, new String[] { "Id", "Name", "Amount" });
		return model;
	}

	public TableModel doQuery2() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select a from Composition a where a.disk.diskID = ?1");
		q.setParameter(1, 1);
		List<Composition> list = q.getResultList();
		String[][] arr = new String[list.size()][3];
		int i = 0;
		for (Composition m : list) {
			arr[i][0] = String.valueOf(m.getId());
			arr[i][1] = m.getName();
			arr[i++][2] = m.getDuration().toString();
		}
		DefaultTableModel model = new DefaultTableModel(arr, new String[] { "Id", "Name", "Duration" });
		return model;
	}
}
