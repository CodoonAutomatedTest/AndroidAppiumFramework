/**
 * 
 */
package com.codoon.common.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiaoq 2017年9月8日
 */
public class ReadXml {
	private Document doc;
	private Map<String, List> params = new HashMap<String, List>();
	private Map<String, String> tips = new HashMap<String, String>();

	public ReadXml() {
		tips.put("test001", "测试点: 发feed可以调起手机相册");
		tips.put("test002", "测试点: 发feed可以调起手机相机拍照");
		tips.put("test003", "测试点: 广告banner正确显示且可以跳转");
		tips.put("test004", "测试点: 1.热门话题正确显示，点击更多进入话题列表页 2.话题列表页点击话题进入话题详情页");
		tips.put("test005", "测试点: 讨论专区，点击更多，进入讨论列表");
		tips.put("test006", "测试点: 推荐达人 关注");
		tips.put("test007", "测试点: 推荐达人 取消关注");
		tips.put("test008", "测试点: 推荐达人点击更多 进入热门用户页面");
		tips.put("test009", "测试点: 精选动态跳转feed详情");
		tips.put("test010", "测试点: 精选动态加载更多");
		tips.put("test011", "测试点: 发布一张图+文字feed+话题+定位");
		tips.put("test012", "测试点: 发布九张图+文字feed+话题+定位");
		tips.put("test013", "测试点: 查看feed大图");
		tips.put("test014", "测试点: 推荐用户 关注");
		tips.put("test015", "测试点: 推荐用户 取消关注");
		tips.put("test016", "测试点: 推荐用户 换一批");
		tips.put("test017", "测试点: 附近动态 点击更多进入附近动态页面");
		tips.put("test018", "测试点: feed点赞，取消点赞");
		tips.put("test019", "测试点: feed评论");
		tips.put("test020", "测试点: feed收藏");
		tips.put("test021", "测试点: feed举报");
		tips.put("test022", "测试点: 1.feed回收评论 2.点赞评论");
	}

	public void document() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder document = factory.newDocumentBuilder();
			doc = document.parse("test-output/testng-results.xml");
		} catch (Exception e) {
		}
	}

	public Map<String, List> showInfo() {
		try {
			NodeList list = doc.getElementsByTagName("test-method");
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				Element testcase = (Element) node;
				if (!hasDigit(testcase.getAttribute("name"))) {
					continue;
				}
				List<String> list1 = new LinkedList<String>();
				list1.add(tips.get(testcase.getAttribute("name")));
				list1.add(testcase.getAttribute("status"));
				list1.add(testcase.getAttribute("duration-ms"));

				params.put(testcase.getAttribute("name"), list1);
				NodeList lists = testcase.getChildNodes();
				for (int j = 0; j < lists.getLength(); j++) {
					Node nodes = lists.item(j);
					if (nodes.getNodeType() == Node.ELEMENT_NODE) {
						Element elen = (Element) nodes;
						System.out.println("\t" + elen.getAttribute("name"));
					}
				}
			}
		} catch (Exception e) {
		}
		return params;
	}

	public Map<String, String> getSummryInfo() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			NodeList statusList = doc.getElementsByTagName("testng-results");
			for (int i = 0; i < statusList.getLength(); i++) {
				Node node = statusList.item(i);
				Element testcase = (Element) node;
//				System.out.println("Total: "+testcase.getAttribute("total"));
//				System.out.println("Passed: "+testcase.getAttribute("passed"));
//				System.out.println("Failed: "+testcase.getAttribute("failed"));
//				System.out.println("Skipped: "+testcase.getAttribute("skipped"));
				map.put("Total",testcase.getAttribute("total"));
				map.put("Passed",testcase.getAttribute("passed"));
				map.put("Failed",testcase.getAttribute("failed"));
				map.put("Skipped",testcase.getAttribute("skipped"));
			}
			NodeList timeList = doc.getElementsByTagName("suite");
			for (int i = 0; i < timeList.getLength(); i++) {
				Node node = timeList.item(i);
				Element testcase = (Element) node;  //started-at
//				System.out.println("time begin: "+testcase.getAttribute("started-at"));
//				System.out.println("time end: "+testcase.getAttribute("finished-at"));
				map.put("Start",testcase.getAttribute("started-at"));
				map.put("Finish",testcase.getAttribute("finished-at"));
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return map;
	}

	public String  parseEmailContext(){
		Map map = this.getSummryInfo();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime);
        String logo = "TestReport-"+dateString;
//		System.out.printf("本轮测试结果，共执行用例: %s 条, 通过用例: %s 条, 失败用例: %s 条, 跳过用例: %s 条",map.get("Total"),map.get("Passed"),map.get("Failed"),map.get("Skipped"));
		String str = "<h1>测试结论:</h1><p>Hi,all</br>&nbsp;&nbsp;&nbsp;&nbsp;本轮测试开始运行于: "+map.get("Start")+"；测试结束于: "+map.get("Finish")+"。 </br>&nbsp;&nbsp;&nbsp;&nbsp;结论如下，共执行用例: "+map.get("Total")+" 条, 通过用例: "+map.get("Passed")+" 条, 失败用例: "+map.get("Failed")+" 条, 跳过用例: "+map.get("Skipped")+" 条<a href=\"http://192.168.9.15:8000/"+logo+"\">&nbsp;&nbsp;&nbsp;&nbsp;点击查看报告详情</a></p><p>（PS: 查看详情报告的超链接 请必须在<b>公司网络环境中</b>打开，重要的事情说三遍！说三遍！说三遍！！）</p>";
// String str = "本轮测试结果，共执行用例: %s 条, 通过用例: %s 条, 失败用例: %s 条, 跳过用例: %s 条",;
		return str;
	}

	private static boolean hasDigit(String content) {

		boolean flag = false;
		Pattern p = Pattern.compile(".*\\d+.*");
		Matcher m = p.matcher(content);
		if (m.matches())
			flag = true;
		return flag;
	}

	public static void main(String[] args) {
		ReadXml dome = new ReadXml();
		dome.document();
		dome.parseEmailContext();
//		Map<String,List> map = dome.getSummryInfo();
//		Map<String,List> map = dome.showInfo();
//		System.out.println(map.size());
//		for(String key: map.keySet()){
//			System.out.println("key= "+ key + " and value= " + map.get(key));
//		}
	}
}
