package cn.itcast.shop.index.action;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页访问的Action
 * @author Administrator
 *
 */

public class IndexAction extends ActionSupport{
	//注入一级分类的service
	private CategoryService categoryService;
	//注入商品的service
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
	 * 执行的访问首页的方法 
	 */
	public String execute(){
		//查询所有一级分类的集合
		List<Category> cList=categoryService.findAll();
		//将一级分类存入Session范围
		ActionContext.getContext().getSession().put("cList", cList);
		//查询热门商品
		List<Product> hlist=productService.findHot();
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("hList", hlist);
		//查询最新商品
		List<Product> nlist=productService.findNew();
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("nList", nlist);
		return "index";
	}

}
