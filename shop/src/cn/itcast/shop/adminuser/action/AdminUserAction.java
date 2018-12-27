package cn.itcast.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.service.AdminUserService;
import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台用户管理的Action
 * @author Administrator
 *
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
    //模型驱动接受的对象
	private AdminUser adminUser=new AdminUser();
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}
	//注入Service层
	private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
    /**
     * 后台登录的方法
     */
	public String login(){
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if(existAdminUser == null){
			//登录失败
			this.addActionError("亲！你的用户名或密码错误！");
			return "loginFail";
		}
		else{
			//登陆成功
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
		
		
	}
}
