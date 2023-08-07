package category.notice.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import category.notice.model.vo.Notice;

public class NoticeDAO {
	
	public String generatePageNavi(int currentPage) {	// 전체 게시물, 범위에 따른 html 태그 만들기
			int totalCount = 17;			// 전체 게시물의 갯수
			int recordCountPerPage = 5;		// 한 페이지에 보여줄 게시물의 갯수
			int naviTotalCount = 0;			// 범위의 총 갯수
			if(totalCount % recordCountPerPage > 0) {
				naviTotalCount = (totalCount / recordCountPerPage) + 1;
			} else {
				naviTotalCount = totalCount / recordCountPerPage;
			}
			int naviCountPerPage = 3;		// 한 페이지에 보여줄 범위의 갯수
			int startNavi = ((currentPage - 1)/naviCountPerPage) * naviCountPerPage + 1;
			int endNavi = startNavi + naviCountPerPage -1;
			
			if(endNavi > naviTotalCount) {
				endNavi = naviTotalCount;	// 마지막 범위의 값이 총 범위의 갯수보다 커지지 않게 설정
			}
			
			boolean needPrev = true;		// 이전 값
			boolean needNext = true;		// 다음 값
			
			if(startNavi == 1) {			// startNavi 가 1이면 needPrev가 뜨지않게
				needPrev = false;
			}
			if(endNavi == naviTotalCount) {	// 마지막 범위의 값이 총 범위의 갯수이면 needNext가 뜨지않게
				needNext = false;
			}
			
			StringBuilder result = new StringBuilder();
			
			if(needPrev) {
				result.append("<a href='/notice/list.do?currentPage="+(startNavi-1)+"'> < </a> ");
			}
			for(int i = startNavi; i <= endNavi; i++) {
				result.append("<a href='/notice/list.do?currentPage=" + i + "'>" + i + "</a>&nbsp;&nbsp;");	// 범위 만드는 html 태그  // &nbsp; 는 띄어쓰기
			}
			if(needNext) {
				result.append("<a href='/notice/list.do?currentPage="+(endNavi+1)+"'> > </a>");
			}
			return result.toString();
		}
	
	public List<Notice> selectNoticeList(SqlSession session, int currentPage) {
		int limit = 5;
		int offset = (currentPage-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeList", null, rowBounds);	// WHERE 가 없어서 null 을 입력
		return nList;
	}

	public Notice selectOneByNo(SqlSession session, int noticeNo) {
		Notice notice = session.selectOne("NoticeMapper.selectOneByNo", noticeNo);
		return notice;
	}

	public int insertNotice(SqlSession session, Notice notice) {
		int result = session.insert("NoticeMapper.insertNotice", notice);
		return result;
	}

	public int updateNotice(SqlSession session, Notice notice) {
		int result = session.insert("NoticeMapper.updateNotice", notice);
		return result;
	}

	public int deleteNoticeByNo(SqlSession session, int noticeNo) {
		int result = session.delete("NoticeMapper.deleteNotice", noticeNo);
		return result;
	}

}
