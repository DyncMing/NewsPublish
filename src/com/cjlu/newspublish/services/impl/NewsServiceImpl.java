package com.cjlu.newspublish.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.cjlu.newspublish.daos.impl.AdminDaoImpl;
import com.cjlu.newspublish.daos.impl.CommentDaoImpl;
import com.cjlu.newspublish.daos.impl.NewsDaoImpl;
import com.cjlu.newspublish.daos.impl.StateDaoImpl;
import com.cjlu.newspublish.daos.impl.VisitorCounterDaoImpl;
import com.cjlu.newspublish.models.News;
import com.cjlu.newspublish.models.Page;
import com.cjlu.newspublish.models.State;
import com.cjlu.newspublish.models.security.Admin;
import com.cjlu.newspublish.services.NewsService;
import com.cjlu.newspublish.utils.SearchIndexUtils;

@Service("newsService")
public class NewsServiceImpl extends BaseServiceImpl<News> implements
		NewsService {

	@Autowired
	private NewsDaoImpl newsDao;
	@Autowired
	private CommentDaoImpl commentDao;
	@Autowired
	private AdminDaoImpl adminDao;
	@Autowired
	private VisitorCounterDaoImpl visitorCounterDao;
	@Autowired
	private StateDaoImpl stateDao;
	@Autowired
	private TaskExecutor taskExecutor;

	private News news = null;
	private Admin admin = null;
	private State state = null;
	private Page<News> page = null;

	@Override
	public Page<News> getNews(Integer id, int pageNo, int pageSize) {
		page = newsDao.getNews(id, pageNo, pageSize);
		return page;
	}

	@Override
	public News getViewNews(Integer id) {
		news = newsDao.getEntity(id);
		return news;
	}

	@Override
	public void deleteNews(Integer id) {
		news = this.getEntity(id);
		commentDao.deleteCommentByNewsId(id);
		visitorCounterDao.deleteCounterByNewsId(id);
		this.deleteEntity(news);
	}

	@Override
	public void saveNews(News model, Integer adminId) {
		admin = adminDao.getEntity(adminId);
		state = stateDao.getEntity(3);
		if (model.getId() != null && model.getId() > 0) {
			model.setAdmin(admin);
		} else if (model.getId() == null) {
			model.setCreateTime(new Date());
			model.setAdmin(admin);
			model.setCount(0);
			model.setState(state);
		}
		this.saveOrUpdateEntity(model);
	}

	// ���е����ŷ�ҳ����
	@Override
	public Page<News> listAllNewsPage(int pageNo, int pageSize) {
		page = newsDao.listAllNewsPage(pageNo, pageSize);
		if (page.getList() != null && page.getList().size() > 0) {
			for (News news : page.getList()) {
				news.getNewsType().getTypeName();
				news.getAdmin().getUsername();
				news.getState().getStateName();
			}
		}
		return page;
	}

	// δͨ�������ŷ�ҳ����
	@Override
	public Page<News> listAllNotPassedNewsPage(int pageNo, int pageSize) {
		page = newsDao.listAllNotPassedNewsPage(pageNo, pageSize);
		if (page.getList() != null && page.getList().size() > 0) {
			for (News news : page.getList()) {
				news.getNewsType().getTypeName();
				news.getAdmin().getUsername();
				news.getState().getStateName();
			}
		}
		return page;
	}

	@Override
	public List<News> getHotNews() {
		return newsDao.geHotNews();
	}

	@Override
	public List<News> getRecentNews(int id, int maxResult) {
		return newsDao.getRecentNews(id, maxResult);
	}

	@Override
	public void createNewsIndex(final News model) {
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				SearchIndexUtils.createIndex(model);
			}
		});
	}
}
