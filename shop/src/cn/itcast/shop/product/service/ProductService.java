package cn.itcast.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.product.dao.ProductDao;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

/**
 * ҵ������Ʒ����
 * @author Administrator
 *
 */
@Transactional
public class ProductService {
 //ע��ProductDao
	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
     //��ҳ��������Ʒ�Ĳ�ѯ
	public List<Product> findHot() {
		// TODO Auto-generated method stub
		return productDao.findHot();
	}
    //��ҳ�ϲ�ѯ������Ʒ
	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}
  //������Ʒ��ID��ѯ��Ʒ
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findByPid(pid);
	}
   //����һ�������cid���з�ҳ��ѯ��Ʒ

	public PageBean<Product> findByCidPage(Integer cid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean=new PageBean<Product>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��¼��
		int limit=8;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount=0;
		totalCount=productDao.findCount(cid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit +1;
		}
		pageBean.setTotalPage(totalPage);
		//ÿҳ��ʾ���ݼ���
		//���Ŀ�ʼ
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByCidPage(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
       //���ݶ��������ѯ���з�ҳ����Ʒ
	public PageBean<Product> findByCsidPage(Integer csid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean=new PageBean<Product>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ûҳ��
		int limit=8;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount=0;
		totalCount=productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit +1;
		}
		pageBean.setTotalPage(totalPage);
		//ÿҳ��ʾ�����ݼ���
		//���Ŀ�ʼ
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByCsidPage(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
}
