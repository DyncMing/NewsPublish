package com.cjlu.newspublish.actions.impl;

import java.util.ArrayList;
import java.util.List;

import javassist.bytecode.stackmap.TypeData.ClassName;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cjlu.newspublish.actions.BaseAction;
import com.cjlu.newspublish.constant.WebConstant;
import com.cjlu.newspublish.models.Page;
import com.cjlu.newspublish.models.security.Right;
import com.cjlu.newspublish.services.RightService;

@Controller
@Scope("prototype")
public class RightAction extends BaseAction<Right> {

	private static final long serialVersionUID = -757699664708338343L;
	@Autowired
	private RightService rightService;
	private List<Right> allRights = new ArrayList<Right>();
	private static Logger logger = Logger.getLogger(ClassName.class);
	private Page<Right> p = null;
	private Integer page;
	private int pageSize = WebConstant.PAGE_SIZE;

	/**
	 * ��������Ȩ��
	 */
	public String batchUpdateRights() {
		rightService.batchSaveRight(allRights);
		return "to_RightAction_findAllRights";
	}

	/**
	 * ɾ��Ȩ��
	 */
	public String deleteRight() {
		Right right = rightService.getEntity(model.getId());
		rightService.deleteEntity(right);
		logger.info("ɾ��Ȩ�ޣ�" + right.getRightName());
		return "to_RightAction_findAllRights";
	}

	/**
	 * �༭Ȩ��
	 */
	public String editRight() {
		model = rightService.getEntity(model.getId());
		return "to_editRightPage";
	}

	/**
	 * ��������Ȩ��
	 */
	public String saveOrUpdateRight() {
		rightService.saveOrUpdateRight(model);
		logger.info("��������Ȩ�ޣ�" + model.getRightName());
		return "to_RightAction_findAllRights";
	}

	/**
	 * ��ѯ�����е�Ȩ��
	 */
	public String findAllRights() {
		p = new Page<Right>();
		if (page == null) {
			p = rightService.listAllRightPage(1, pageSize);
		} else {
			p = rightService.listAllRightPage(page, pageSize);
		}
		requestMap.put("page", p);
		return "to_rightListPage";
	}

	/**
	 * ��ת������Ȩ�޵�ҳ��
	 */
	public String toAddRightPage() {
		return "to_addRightPage";
	}

	public List<Right> getAllRights() {
		return allRights;
	}

	public void setAllRights(List<Right> allRights) {
		this.allRights = allRights;
	}
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
