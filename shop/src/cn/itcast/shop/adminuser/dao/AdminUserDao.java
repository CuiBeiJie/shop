package cn.itcast.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.adminuser.vo.AdminUser;

/**
 * ��̨��¼��DAO��
 * @author Administrator
 *
 */
public class AdminUserDao extends HibernateDaoSupport{
     //DAO�е�¼�ķ���
	public AdminUser login(AdminUser adminUser) {
		// TODO Auto-generated method stub
		String hql="from AdminUser where username = ? and password = ?";
		List<AdminUser> list=this.getHibernateTemplate().find(hql, adminUser.getUsername(),adminUser.getPassword());
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
