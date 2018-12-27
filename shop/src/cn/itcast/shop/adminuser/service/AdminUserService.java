package cn.itcast.shop.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.adminuser.dao.AdminUserDao;
import cn.itcast.shop.adminuser.vo.AdminUser;

/**
 * ��̨����ҵ�����
 * @author Administrator
 *
 */
@Transactional
public class AdminUserService {
     
	//ע��Dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
 
	//ҵ����¼�ķ���
	public AdminUser login(AdminUser adminUser) {
		// TODO Auto-generated method stub
		return adminUserDao.login(adminUser);
	}
	
}
