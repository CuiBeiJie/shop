package cn.itcast.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.category.dao.CategoryDao;
import cn.itcast.shop.category.vo.Category;

/**
 * 一级分类的业务层对象
 * 
 * @author Administrator
 * 
 */
@Transactional
public class CategoryService {
	// 注入dao
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * 业务层查询所有一级分类
	 * 
	 * @return
	 */
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	// 业务层保存一级分类的方法

	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}

	// 业务层根据一级分类id查询一级分类
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}

	// 业务层删除一级分类
	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryDao.delete(category);
	}
    // 业务层修改一级分类
	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}

}
