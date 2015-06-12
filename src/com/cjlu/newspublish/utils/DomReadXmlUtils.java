package com.cjlu.newspublish.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

public final class DomReadXmlUtils {

	private DomReadXmlUtils() {

	}

	/**
	 * 
	 * @param xml
	 * @return
	 * @throws DocumentException
	 * ��xmlת��ΪList<String>
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public static List<String> readStringXml(String xml) throws DocumentException {
		List<String> strs = new ArrayList<String>();
		Document document = null;
		document = DocumentHelper.parseText(xml); // ���ַ���תΪXML
		Element rootElt = document.getRootElement(); // ��ȡ���ڵ�
		System.out.println("���ڵ㣺" + rootElt.getName());// �õ����ڵ������
		Element element = rootElt.element("Body").element("getWeatherResponse").element("getWeatherResult");
		Iterator iter = element.elementIterator("string");
        while (iter.hasNext()) {
            Element recordEle = (Element) iter.next();
            String str = recordEle.getStringValue();
            strs.add(str);
            System.out.println(str);
	}
		return strs;
	}

}
