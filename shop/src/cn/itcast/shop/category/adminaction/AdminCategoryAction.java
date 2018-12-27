package cn.itcast.shop.category.adminaction;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��̨һ����������Action
 * @author Administrator
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
    //ģ������ʹ�õ���
	private Category category=new Category();
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	//ע��Service
	private CategoryService categoryService;
    public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//��ִ̨�в�ѯ����һ������ķ���
	public String findAll(){
		//��ѯ����һ������
		List<Category> cList=categoryService.findAll();
		//�����ϵ����ݴ���ҳ��
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
		
	}
	
	//��̨����һ������ķ���
	public String save(){
		//����service���б���
		categoryService.save(category);
		//ҳ����ת
		return "saveSuccess";
		
	}
	//��̨ɾ��һ������ķ���
	public String delete(){
		//����һ�������cid������ʹ��ģ��������ɾ��һ�����࣬ͬʱɾ��������������ȸ���id��ѯ�ڽ���ɾ��
		category = categoryService.findByCid(category.getCid());
		//ɾ��
		categoryService.delete(category);
		//ҳ����ת
		return "deleteSuccess";
		
	}
	//��̨�༭һ������ķ���
	public String edit(){
		//��ѯһ������
		category=categoryService.findByCid(category.getCid());
		//ҳ����ת
		return "editSuccess";
		
	}
	//��̨�޸�һ������ķ���
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
		
	}
}
