package cn.itcast.shop.user.action;

import java.io.IOException;


import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.user.vo.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	//接收验证码
	private String checkcode;
	//模型驱动使用的对象
	private User user=new User();
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	//注入UserService
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	/**
	 * 跳转到注册页面的执行方法
	 */
  public String registPage(){
	  
	return "registPage";
	  
  }
  /**
   * AJAX进行异步校验用户名的执行方法
 * @throws IOException 
   */
  public String findByName() throws IOException{
	  //调用service查询
	  System.out.println("我进来了findByName");
	  System.out.println(user.getUsername());
	  User existUser=userService.findByUsername(user.getUsername());
	  //获得response对象，向页面输出
	  HttpServletResponse response= ServletActionContext.getResponse();
	  response.setContentType("text/html;charset=UTF-8");
	  //判断
	  if(existUser !=null){
		  //查询到用户：用户名已经存在
		  response.getWriter().println("<font color='red'>用户名已经存在</font>");
	  }else{
		  //没查到该用户：用户名可以使用
		  response.getWriter().println("<font color='green'>用户名可以使用</font>");
	  }
	  return NONE;
	  
  }
    /**
     * 用户注册的方法
     */
    public String regist(){
    	//判断验证码程序
    	//从session中获得验证码的随机值
    	String code=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
    	if(!checkcode.equalsIgnoreCase(code)){
    		this.addActionError("验证码输入错误！");
    		return "checkcodeFail";
    	}
    	userService.save(user);
    	this.addActionMessage("已经注册成功请去邮箱激活");
		return "msg";
    	
    }
    /**
     * 用户激活
     */
     public String active(){
    	 //根据激活码查询是否存在该用户
    	User existUser=userService.findByCode(user.getCode());
    	//判断
    	if(existUser ==null){
    		//激活失败
    		this.addActionMessage("激活失败：激活码错误！");
    	}else{
    		//激活成功
    		//修改用户状态
    		existUser.setState(1);
    		existUser.setCode(null);
    		userService.update(existUser);
    		this.addActionMessage("激活成功！请去登录");
    	}
		return "msg";
    	 
     }
     
     //跳转到登录界面的方法
     public String loginPage(){
		return "loginPage";
    	 
     }
     
     //登录的方法
     
     public String login(){
    	//判断验证码程序
     	//从session中获得验证码的随机值
     	String code=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
     	if(!checkcode.equalsIgnoreCase(code)){
     		this.addActionError("验证码输入错误！");
     		return "checkLogincodeFail";
     	}
    	 
    	 //模型驱动自动接收用户名和密码
    	 User existUser=userService.login(user);
    	 
    	 
    	 if(existUser ==null){
    		 //登录失败
    		 this.addActionError("登录失败：用户名错误或者密码错误或者用户未激活！");
    		 return "loginError";
    		 
    	 }else{
    		 //登录成功
    		 //将用户的信息存入到session中
    		 ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
    		 //页面跳转
    		 return "loginSuccess";
    		 
    	 }
    	 
     }
     
     
     //退出的方法
     
     public String quit(){
    	 //销毁session
    	 ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
    	 
     }
     
}
