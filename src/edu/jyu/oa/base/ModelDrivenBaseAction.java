package edu.jyu.oa.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ModelDriven;

import edu.jyu.oa.base.BaseAction;

public class ModelDrivenBaseAction<T> extends BaseAction implements ModelDriven<T> {

	// ===================== 对ModelDriven的支持 ====================

	protected T model;

	public ModelDrivenBaseAction() {
		System.out.println("----------> BaseAction.BaseAction()");
		try {
			// 通过反射获取T的真是类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

}
