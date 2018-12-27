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
	      //���ڽ������ݵ�ģ������       
	    private Product product=new Product();
         //ע����Ʒ��Service
   		private ProductService productService;
         //����һ������
   		private Integer cid;
   		//���ն�������
   		private Integer csid;
   		//ע��һ�������Service
   		private CategoryService categoryService;
   		//���յ�ǰҳ��
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


		//������Ʒ��Id���в�ѯ��Ʒ��ִ�з���
		public String findByPid(){
		    product= productService.findByPid(product.getPid());
			return "findByPid";
			
		}
		
		//���ݷ����id����ѯ��Ʒ
		public String findByCid(){
			//List<Category> clist=categoryService.findAll();
			PageBean<Product> pagebean=productService.findByCidPage(cid,page);
			//��pageBean���뵽ֵջ��
			ActionContext.getContext().getValueStack().set("pageBean", pagebean);
			return "findByCid";
			
		}
		//���ݶ�������id��ѯ��Ʒ
		public String findByCsid(){
		     PageBean<Product> pagebean=productService.findByCsidPage(csid,page);
		     ActionContext.getContext().getValueStack().set("pageBean", pagebean);
			return "findByCsid";
			
		}
           
}
