package cn.itcast.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * 二级分类管理的Dao层的类
 * 
 * @author Administrator
 * 
 */
public class CategorySecondDao extends HibernateDaoSupport {
	// Dao层查询二级分类个数的方法
	public int findCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// Dao层分页查询二级分类的方法
	public List<CategorySecond> findByPage(int begin, int limit) {
		// TODO Auto-generated method stubet
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<CategorySecond>(hql, null, begin,
						limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// Dao层保存二级分类
	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(categorySecond);
	}

	// Dao层根据二级分类id来查询二级分类
	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
       return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}
     //Dao层删除二级分类
	public void delete(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(categorySecond);
	}
   //Dao层修改二级记录
	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(categorySecond);
	}

}
