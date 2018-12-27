package cn.itcast.shop.categorysecond.adminaction;

import java.util.List;


import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��̨������������Action
 * @author Administrator
 *
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
    //ģ����������
	private CategorySecond categorySecond = new CategorySecond();
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}
   //ע����������Service
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	//����page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//ע��һ�������Service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//��ѯ���ж�������
	public String findAll(){
		PageBean<CategorySecond> pageBean=categorySecondService.findAll(page);
		//��PageBean�����ݱ��浽ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
		
	}
	//��ת�����ҳ��
	public String addPage(){
		//��ѯ����һ������
		List<Category> cList=categoryService.findAll();
		//��������ʾ��ҳ��������б���
		ActionContext.getContext().getValueStack().set("cList", cList);
		//ҳ����ת
		return "addPage";
		
	}
	
	//�����������
	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess";	
	}
	//ɾ����������
	public String delete(){
		//���Ҫ����ɾ�����Ȳ�ѯ��ɾ��������cascade
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
		
	}
	//�༭��������
	public String edit(){
		//��Ҫ���ݶ�������id��ѯ�����������
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		//��ѯ����һ������
		List<Category> cList = categoryService.findAll();
		//�浽ֵջ��
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
		
	}
	//�޸Ķ�������ķ���
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
		
	}
}
