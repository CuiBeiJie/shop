package cn.itcast.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.product.dao.ProductDao;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

/**
 * 业务层的商品操作
 * @author Administrator
 *
 */
@Transactional
public class ProductService {
 //注入ProductDao
	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
     //首页上热门商品的查询
	public List<Product> findHot() {
		// TODO Auto-generated method stub
		return productDao.findHot();
	}
    //首页上查询最新商品
	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}
  //根据商品的ID查询商品
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findByPid(pid);
	}
   //根据一级分类的cid带有分页查询商品

	public PageBean<Product> findByCidPage(Integer cid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean=new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页记录数
		int limit=8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount=0;
		totalCount=productDao.findCount(cid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit +1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示数据集合
		//从哪开始
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByCidPage(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
       //根据二级分类查询带有分页的商品
	public PageBean<Product> findByCsidPage(Integer csid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean=new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置没页数
		int limit=8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount=0;
		totalCount=productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit +1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示的数据集合
		//从哪开始
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByCsidPage(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
}
