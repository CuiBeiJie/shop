package cn.itcast.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * ����ģ��ĳ־ò����
 * @author Administrator
 *
 */
public class OrderDao extends HibernateDaoSupport{
   //DAO�㱣�涩������
	public void save(Order order) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(order);
	}
	
	//DAO���ҵĶ���������ѯ

	public int findByCountUid(Integer uid) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Order o where o.user.uid=?";
		List<Long> list=this.getHibernateTemplate().find(hql, uid);
		if(list !=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//DAO���ҵĶ����Ĳ�ѯ
   public List<Order> findBypageUid(Integer uid, int begin, int limit) {
		// TODO Auto-generated method stub
	   String hql="from Order o where o.user.uid=? order by ordertime desc";
	   List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		return list;
	}
   //DAO���ݶ���id��ѯ����
public Order findByOid(Integer oid) {
	// TODO Auto-generated method stub
	return this.getHibernateTemplate().get(Order.class, oid);
}
   //�޸Ķ���
public void update(Order currOrder) {
	// TODO Auto-generated method stub
	this.getHibernateTemplate().update(currOrder);
}

}
