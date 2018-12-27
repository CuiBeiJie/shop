package cn.itcast.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.category.vo.Category;

/**
 * һ������־ò����
 * 
 * @author Administrator
 * 
 */
public class CategoryDao extends HibernateDaoSupport {
	// Dao���ѯ����һ������ķ���
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		String hql = "from Category";
		List<Category> clist = this.getHibernateTemplate().find(hql);
		return clist;
	}

	// Dao�㱣��һ������ķ���
	public void save(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(category);
	}

	// Dao�����һ������id��ѯһ������
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	// Dao��ɾ��һ������
	public void delete(Category category) {
		// TODO Auto-generated method stub
       this.getHibernateTemplate().delete(category);
	}
    // Dao���޸�һ������
	public void update(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(category);
	}

}
