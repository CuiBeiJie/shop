package cn.itcast.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * ��Ʒ�ĳ־ò�
 * @author Administrator
 *
 */
public class ProductDao extends HibernateDaoSupport {
  //��ҳ�ϲ�ѯ������Ʒ
	public List<Product> findHot() {
		// TODO Auto-generated method stub
		//ʹ������������ѯ
				DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
				//��ѯ������Ʒ����������is_hot =1
				criteria.add(Restrictions.eq("is_hot", 1));
				//�����������
				criteria.addOrder(Order.desc("pdate"));
				//ִ�в�ѯ
				List<Product> list=this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
				return list;
			
	}
   //��ҳ�ϲ�ѯ������Ʒ
	public List<Product> findNew() {
		//ʹ������������ѯ
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		//�����������
		criteria.addOrder(Order.desc("pdate"));
		//ִ�в�ѯ
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria,0, 10);
		// TODO Auto-generated method stub
		return list;
	}
	 //������Ʒ��ID��ѯ��Ʒ
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Product.class, pid);
		
	}
	//���ݷ����id��ѯ��Ʒ�ĸ���
	public int findCount(Integer cid) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Product p where p.categorySecond.category.cid=?";
		List<Long> list=this.getHibernateTemplate().find(hql, cid);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//���ݷ����id��ѯ��Ʒ����
	public List<Product> findByCidPage(Integer cid, int begin, int limit) {
		// TODO Auto-generated method stub
		String hql="select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		//��ҳ����һ��д��
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		 if(list!=null && list.size()>0){
			 return list;
		 }
		return null;
	}
	//���ݶ�������id����ѯ��Ʒ�ĸ���
	public int findCountCsid(Integer csid) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list=this.getHibernateTemplate().find(hql, csid);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//���ݶ�������id����ѯ��Ʒ�ļ���
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