package cn.itcast.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.category.vo.Category;

/**
 * 一级分类持久层对象
 * 
 * @author Administrator
 * 
 */
public class CategoryDao extends HibernateDaoSupport {
	// Dao层查询所有一级分类的方法
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		String hql = "from Category";
		List<Category> clist = this.getHibernateTemplate().find(hql);
		return clist;
	}

	// Dao层保存一级分类的方法
	public void save(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(category);
	}

	// Dao层根据一级分类id查询一级分类
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	// Dao层删除一级分类
	public void delete(Category category) {
		// TODO Auto-generated method stub
       this.getHibernateTemplate().delete(category);
	}
    // Dao层修改一级分类
	public void update(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(category);
	}

}
