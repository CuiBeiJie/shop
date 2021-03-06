package cn.itcast.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * 商品的持久层
 * @author Administrator
 *
 */
public class ProductDao extends HibernateDaoSupport {
  //首页上查询热门商品
	public List<Product> findHot() {
		// TODO Auto-generated method stub
		//使用离线条件查询
				DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
				//查询热门商品，条件就是is_hot =1
				criteria.add(Restrictions.eq("is_hot", 1));
				//倒序排序输出
				criteria.addOrder(Order.desc("pdate"));
				//执行查询
				List<Product> list=this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
				return list;
			
	}
   //首页上查询最新商品
	public List<Product> findNew() {
		//使用离线条件查询
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		//倒序排序输出
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria,0, 10);
		// TODO Auto-generated method stub
		return list;
	}
	 //根据商品的ID查询商品
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Product.class, pid);
		
	}
	//根据分类的id查询商品的个数
	public int findCount(Integer cid) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Product p where p.categorySecond.category.cid=?";
		List<Long> list=this.getHibernateTemplate().find(hql, cid);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//根据分类的id查询商品集合
	public List<Product> findByCidPage(Integer cid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql="select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		//分页的另一种写法
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		 if(list!=null && list.size()>0){
			 return list;
		 }
		return null;
	}
	//根据二级分类id来查询商品的个数
	public int findCountCsid(Integer csid) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list=this.getHibernateTemplate().find(hql, csid);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//根据二级分类id来查询商品的集合
	public List<Product> findByCsidPage(Integer csid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql="select p from Product p join p.categorySecond cs where cs.csid=?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		 if(list!=null && list.size()>0){
			 return list;
		 }
		return null;
	}
	
	

}
