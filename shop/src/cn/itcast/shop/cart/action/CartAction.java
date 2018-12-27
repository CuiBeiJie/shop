package cn.itcast.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport{
	//接收数量
	private Integer count;
	//接收商品id
	private Integer pid;
	//注入商品的Service
	private ProductService productService;
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

		//将购物项添加到购物车中的方法
	     public String addCart(){
	    	//封装CartItem对象
	    	 CartItem cartItem=new CartItem();
	    	 //设置数量
	    	 cartItem.setCount(count);
	    	 //设置商品
	    	 Product product=productService.findByPid(pid);
	    	 cartItem.setProduct(product);
	    	 //将购物项添加到购物车
	    	 //购物车存到session中
	    	 Cart cart=getCart();
	    	 cart.addCart(cartItem);
			return "addCart";
	    	 
	     }
	     //获得购物车方法：从session中获得购物车

		private Cart getCart() {
			// TODO Auto-generated method stub
			Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
			if(cart==null){
				cart=new Cart();
				ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
			}
			return cart;
		}
		//清空购物车
		public String clearCart(){
			//获得session里的购物车对象
			Cart cart=getCart();
			//清空对象
			cart.clearCart();
			return "clearCart";
			
		}
		//移除购物项
		public String deleteCart(){
			//从session中获得购物车对象
			Cart cart=getCart();
			cart.removeCart(pid);
			return "removeCart";
			
		}
       //我的购物车
		public String myCart(){
			return "myCart";
			
		}
}
