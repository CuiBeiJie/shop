package cn.itcast.shop.product.action;

import java.util.List;




import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	      //用于接收数据的模型驱动       
	    private Product product=new Product();
         //注入商品的Service
   		private ProductService productService;
         //接收一级分类
   		private Integer cid;
   		//接收二级分类
   		private Integer csid;
   		//注入一级分类的Service
   		private CategoryService categoryService;
   		//接收当前页数
   		private int page;
		public void setPage(int page) {
			this.page = page;
		}


		public void setCategoryService(CategoryService categoryService) {
			this.categoryService = categoryService;
		}


		public Integer getCid() {
			return cid;
		}

        
		public Integer getCsid() {
			return csid;
		}


		public void setCsid(Integer csid) {
			this.csid = csid;
		}


		public void setCid(Integer cid) {
			this.cid = cid;
		}


		@Override
		public Product getModel() {
			// TODO Auto-generated method stub
			return product;
		}
		
		
		public ProductService getProductService() {
			return productService;
		}


		public void setProductService(ProductService productService) {
			this.productService = productService;
		}


		//根据商品的Id进行查询商品：执行方法
		public String findByPid(){
		    product= productService.findByPid(product.getPid());
			return "findByPid";
			
		}
		
		//根据分类的id来查询商品
		public String findByCid(){
			//List<Category> clist=categoryService.findAll();
			PageBean<Product> pagebean=productService.findByCidPage(cid,page);
			//将pageBean存入到值栈中
			ActionContext.getContext().getValueStack().set("pageBean", pagebean);
			return "findByCid";
			
		}
		//根据二级分类id查询商品
		public String findByCsid(){
		     PageBean<Product> pagebean=productService.findByCsidPage(csid,page);
		     ActionContext.getContext().getValueStack().set("pageBean", pagebean);
			return "findByCsid";
			
		}
           
}
