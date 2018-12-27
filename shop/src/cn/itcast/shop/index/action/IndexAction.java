package cn.itcast.shop.index.action;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ��ҳ���ʵ�Action
 * @author Administrator
 *
 */

public class IndexAction extends ActionSupport{
	//ע��һ�������service
	private CategoryService categoryService;
	//ע����Ʒ��service
	private ProductService productService;
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * ִ�еķ�����ҳ�ķ��� 
	 */
	public String execute(){
		//��ѯ����һ������ļ���
		List<Category> cList=categoryService.findAll();
		//��һ���������Session��Χ
		ActionContext.getContext().getSession().put("cList", cList);
		//��ѯ������Ʒ
		List<Product> hlist=productService.findHot();
		//���浽ֵջ��
		ActionContext.getContext().getValueStack().set("hList", hlist);
		//��ѯ������Ʒ
		List<Product> nlist=productService.findNew();
		//���浽ֵջ��
		ActionContext.getContext().getValueStack().set("nList", nlist);
		return "index";
	}

}
