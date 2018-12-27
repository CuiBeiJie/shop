package cn.itcast.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * 订单模块的持久层代码
 * @author Administrator
 *
 */
public class OrderDao extends HibernateDaoSupport{
   //DAO层保存订单方法
	public void save(Order order) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(order);
	}
	
	//DAO层我的订单个数查询

	public int findByCountUid(Integer uid) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Order o where o.user.uid=?";
		List<Long> list=this.getHibernateTemplate().find(hql, uid);
		if(list !=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//DAO层我的订单的查询
   public List<Order> findBypageUid(Integer uid, int begin, int limit) {
		// TODO Auto-generated method stub
	   String hql="from Order o where o.user.uid=? order by ordertime desc";
	   List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		return list;
	}
   //DAO根据订单id查询订单
public Order findByOid(Integer oid) {
	// TODO Auto-generated method stub
	return this.getHibernateTemplate().get(Order.class, oid);
}
   //修改订单
public void update(Order currOrder) {
	// TODO Auto-generated method stub
	this.getHibernateTemplate().update(currOrder);
}

}
