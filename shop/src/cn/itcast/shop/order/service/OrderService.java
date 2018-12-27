package cn.itcast.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.utils.PageBean;

/**
 * ����ģ���ҵ���
 * @author Administrator
 *
 */
@Transactional
public class OrderService {
	//ע��dao
	private OrderDao orderDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	//���涩��ҵ������

	public void save(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}
	//�ҵĶ���ҵ������

	public PageBean<Order> findOrderByUid(Integer uid, Integer page) {
		// TODO Auto-generated method stub
		PageBean pageBean=new PageBean<Order>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿһҳ��ʾ�ļ�¼��
		int limit=3;
		pageBean.setLimit(limit);
		//�����ܵļ�¼��
		int totalCount=0;
		totalCount=orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage=0;
		if(totalCount % limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//����ÿһҳ��ʾ�ļ���
		int begin=(page - 1)*limit;
		List<Order> list=orderDao.findBypageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
  //���ݶ���id��ѯ����
	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findByOid(oid);
	}
	
	//�޸Ķ���
    public void update(Order currOrder) {
		// TODO Auto-generated method stub
		orderDao.update(currOrder);
	}

}
