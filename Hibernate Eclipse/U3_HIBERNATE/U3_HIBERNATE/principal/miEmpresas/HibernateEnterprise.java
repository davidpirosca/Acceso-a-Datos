package miEmpresas;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateEnterprise {

	private static SessionFactory sf; // this SessionFactory will be created once and used for all the connections
	private static Productos p;

	HibernateEnterprise() {// constructor
		// sf = HibernateUtil.getSessionFactory();
		sf = new Configuration().configure().buildSessionFactory(); // also works
	}

	public void close() {
		sf.close();
	}

	public void addProduct(String name, double price) {
		Session session = sf.openSession();// session es la variable que tiene el método
											// save para guardar productos

		Transaction tx = null;
		// create the product with the parameters in the method
		Productos p = new Productos();
		p.setNombre(name);
		p.setPrecio(price);
		// keep it in the database=session.save(p)
		try {
			System.out.println("======================================");
			System.out.printf("Insertando la Fila en la Base de Datos: %s, %s\n", name, price);
			System.out.println("======================================");
			tx = session.beginTransaction();
			session.save(p);// we INSERT p into the table PRODUCTS
			tx.commit();// if session.save doesnt produce an exception, we commit; the transaction
		} catch (Exception e) {// if there is any exception, we "rollback" and close safely
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	public void showProducts() {
		Session session = sf.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List allproducts = session.createQuery("From Productos").list();

			Iterator it = allproducts.iterator();
			System.out.println("======================================");
			System.out.println("Buscando Productos...");
			System.out.println("======================================");
			while (it.hasNext()) {
				// for (Iterator iterator = allproducts.iterator(); iterator.hasNext();){
				Productos p = (Productos) it.next();
				System.out.println("======================================");
				System.out.println("Id: " + p.getId());
				System.out.println("Nombre: " + p.getNombre());
				System.out.println("Precio: " + p.getPrecio());
				System.out.println("======================================");
			}
			tx.commit();
			System.out.println("======================================");
			System.out.println("Finalizada la Busqueda...");
			System.out.println("======================================");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public Productos findProductById(int id) {
		Session session = sf.openSession();
		Transaction tx = null;
		Productos p = new Productos();

		try {
			System.out.println("======================================");
			System.out.println("Cargando Producto de la Base de Datos...");
			System.out.println("======================================");
			tx = session.beginTransaction();
			p = (Productos) session.load(Productos.class, id);
			tx.commit();
			System.out.println("======================================");
			System.out.println("Producto con ID -> " + id);
			System.out.println("Su Nombre es -> " + p.getNombre());
			System.out.println("======================================");
		} catch (ObjectNotFoundException e) {
			if (tx != null) {
				System.out.println(e);
				System.out.println("Product not found");
			}
		} catch (Exception e) {
			if (tx != null) {
				System.out.println(e);
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return p;
	}

	public void deleteProductById(int id) {
		Productos p = new Productos();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			System.out.println("======================================");
			System.out.println("Buscando Producto con ID -> " + id);
			System.out.println("======================================");
			tx = session.beginTransaction();
			p = (Productos) session.get(Productos.class, id);

			if (p != null) {
				System.out.println("======================================");
				System.out.println("Borrando Producto de la Base de Datos...");
				System.out.println("======================================");

				session.delete(p);
				tx.commit();

				System.out.println("======================================");
				System.out.printf(
						"Producto Borrado de la Base de Datos ..." + "\n ID -> %s\n Nombre -> %s\n Precio -> %s",
						p.getId(), p.getNombre(), p.getPrecio());
				System.out.println("\n======================================");
			} else {
				System.out.println("======================================");
				System.out.println("No Se Encontro Ningun Producto con ID -> " + id);
				System.out.println("======================================");
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	public void updateProductById(int id, String newName, double newPrice) {
		Productos p = new Productos();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			System.out.println("======================================");
			System.out.println("Modificando el Producto de la Base de Datos...");
			System.out.println("Con los Siguientes Datos...");
			System.out.println("ID -> " + id);
			System.out.println("Nombre -> " + newName);
			System.out.println("Precio -> " + newPrice);
			System.out.println("======================================");
			tx = session.beginTransaction();
			p = (Productos) session.load(Productos.class, id);// we load the product
			System.out.println("======================================");
			System.out.println("Datos del Producto en la Base de Datos...");
			System.out.println("======================================");
			System.out.printf(" ID -> %s\n Nombre -> %s\n Precio -> %s",p.getId(), p.getNombre(), p.getPrecio());
			System.out.println("\n======================================");
			p.setPrecio(newPrice);// we change the properties
			p.setNombre(newName);
			session.update(p);// we update the values in the database
			tx.commit();
			System.out.println("======================================");
			System.out.println("Producto Modificado");
			System.out.println("======================================");
			System.out.printf("Datos del Producto Modificado..." + "\n ID -> %s\n Nombre -> %s\n Precio -> %s",
					p.getId(), p.getNombre(), p.getPrecio());
			System.out.println("\n======================================");
		} catch (Exception e) {
			System.out.println("======================================");
			System.out.println("No Se Encontro el Producto con ID -> " + id);
			System.out.println("======================================");
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}
}