package com.jbk.product.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.jbk.product.config.HibernateUtility;
import com.jbk.product.entity.Product;

public class ProductDao {

	private SessionFactory sessionFactory;

	public ProductDao() {
		sessionFactory = HibernateUtility.getSession();
	}

	public String saveProduct(Product product) {
		Session session = null;

		boolean flag = false;

		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		if (flag) {
			return "product saved";
		} else {
			return "Not saved";
		}

	}

	public Product getProduct(int productId) {
		Session session = null;

		Product product = null;

		try {
			session = sessionFactory.openSession();
			product = session.get(Product.class, productId);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return product;

	}

	public String deleteProduct(int productId) {
		Session session = null;
		boolean flag = false;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Product product = session.load(Product.class, productId);
			session.delete(product);
			transaction.commit();
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		if (flag) {
			return "product deleted";
		} else {
			return "Failed to delete";
		}

	}

	public String updateProduct(int productId, String name) {
		Session session = null;
		boolean flag = false;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Product product = session.load(Product.class, productId);
			product.setProductName(name);
			session.update(product);
			transaction.commit();
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		if (flag) {
			return "product updated";
		} else {
			return "Failed to update";
		}

	}

	public List<Product> getAllProduct(int key) {
		Session session = null;
		List<Product> list = null;
		switch (key) {
		case 1:
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(Product.class);
				criteria.addOrder(Order.asc("productId"));
				Criterion criterion1 = Restrictions.le("productPrice", 500d);
				Criterion criterion2 = Restrictions.gt("productId", 3);
				Conjunction conjunction = Restrictions.conjunction();
				conjunction.add(criterion2);
				conjunction.add(criterion1);
				criteria.add(conjunction);
//				criteria.add(Restrictions.not(criterion1));
//				criteria.setFirstResult(1);
//				criteria.setMaxResults(3);
//				criteria.add(criterion1);
				list = criteria.list();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (session != null) {
					session.close();
				}
			}

			return list;
		case 2:
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(Product.class);
				criteria.addOrder(Order.desc("productId"));
//				criteria.setFirstResult(1);
//				criteria.setMaxResults(3);
				list = criteria.list();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (session != null) {
					session.close();
				}
			}

			return list;
		case 3:
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(Product.class);
				criteria.addOrder(Order.asc("productPrice"));
//				criteria.setFirstResult(1);
//				criteria.setMaxResults(3);
				list = criteria.list();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (session != null) {
					session.close();
				}
			}

			return list;
		case 4:
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(Product.class);
				criteria.addOrder(Order.desc("productPrice"));
//				criteria.setFirstResult(1);
//				criteria.setMaxResults(3);
				list = criteria.list();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (session != null) {
					session.close();
				}
			}

			return list;

		default:
			return null;
		}

	}

}
