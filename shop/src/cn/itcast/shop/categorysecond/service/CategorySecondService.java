package cn.itcast.shop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.categorysecond.dao.CategorySecondDao;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageBean;

@Transactional
public class CategorySecondService {
	//ע���������Dao
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
   //ҵ����ҳ��ѯ��������ķ���
	public PageBean<CategorySecond> findAll(Integer page) {
		// TODO Auto-generated method stub
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��¼��
		int limit=10;
		pageBean.setLimit(limit);
		// �����ܵļ�¼��
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		int totalPage = 0;
		if( totalCount % limit == 0){
			totalPage = totalCount/limit;
		}
		else{
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//����ÿҳ����
		int begin = (page - 1)*limit;
		 List<CategorySecond> list=categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		 return pageBean;
	}
	//ҵ��㱣���������
	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.save(categorySecond);
	}
	//ҵ�����ݶ�������id��ѯ��������
	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
		return categorySecondDao.findByCsid(csid);
	}
	//ҵ���ɾ����������
	public void delete(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.delete(categorySecond);
	}
	//ҵ����޸Ķ�������ķ���
	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.update(categorySecond);
	}

}
