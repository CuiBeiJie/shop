package cn.itcast.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.category.dao.CategoryDao;
import cn.itcast.shop.category.vo.Category;

/**
 * һ�������ҵ������
 * 
 * @author Administrator
 * 
 */
@Transactional
public class CategoryService {
	// ע��dao
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * ҵ����ѯ����һ������
	 * 
	 * @return
	 */
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	// ҵ��㱣��һ������ķ���

	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}

	// ҵ������һ������id��ѯһ������
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}

	// ҵ���ɾ��һ������
	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryDao.delete(category);
	}
    // ҵ����޸�һ������
	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}

}
