package cn.itcast.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.utils.PageBean;

/**
 * 订单模块的业务层
 * @author Administrator
 *
 */
@Transactional
public class OrderService {
	//注入dao
	private OrderDao orderDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	//保存订单业务层代码

	public void save(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}
	//我的订单业务层代码

	public PageBean<Order> findOrderByUid(Integer uid, Integer page) {
		// TODO Auto-generated method stub
		PageBean pageBean=new PageBean<Order>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每一页显示的记录数
		int limit=3;
		pageBean.setLimit(limit);
		//设置总的记录数
		int totalCount=0;
		totalCount=orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if(totalCount % limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每一页显示的集合
		int begin=(page - 1)*limit;
		List<Order> list=orderDao.findBypageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
  //根据订单id查询订单
	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findByOid(oid);
	}
	
	//修改订单
    public void update(Order currOrder) {
		// TODO Auto-generated method stub
		orderDao.update(currOrder);
	}

}
