package com.cjlu.newspublish.interceptor;

import com.cjlu.newspublish.actions.BaseAction;
import com.cjlu.newspublish.actions.impl.AdminAction;
import com.cjlu.newspublish.actions.impl.LoginAction;
import com.cjlu.newspublish.actions.impl.RegAction;
import com.cjlu.newspublish.models.security.Admin;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		BaseAction baseAction = (BaseAction) invocation.getAction();
		// �����RegAction��LoginAction�������
		if (baseAction instanceof RegAction
				|| baseAction instanceof AdminAction
				|| baseAction instanceof LoginAction) {
			return invocation.invoke();
		} else {
			Admin admin = (Admin) invocation.getInvocationContext()
					.getSession().get("admin");
			if (admin == null) {
				// ���adminΪ�գ�����ת����¼ҳ��
				return "login";
			} else {
				// if (baseAction instanceof UserAware) {
				// ((UserAware) baseAction).setUser(user);
				// }
				// admin��Ϊ�գ�����
				return invocation.invoke();
			}
		}

	}

}
