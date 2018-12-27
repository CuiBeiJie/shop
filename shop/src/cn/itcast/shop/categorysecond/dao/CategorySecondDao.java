package cn.itcast.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * ������������Dao�����
 * 
 * @author Administrator
 * 
 */
public class CategorySecondDao extends HibernateDaoSupport {
	// Dao���ѯ������������ķ���
	public int findCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// Dao���ҳ��ѯ��������ķ���
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

	// Dao�㱣���������
	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(categorySecond);
	}

	// Dao����ݶ�������id����ѯ��������
	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
       return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}
     //Dao��ɾ����������
	public void delete(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(categorySecond);
	}
   //Dao���޸Ķ�����¼
	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(categorySecond);
	}

}
