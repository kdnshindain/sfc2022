package price.fuel.pf2.common.domain;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * 페이지용  공통 member
 * 필수 setting 값 : dataCnt
 * 선택 setting 값 : curPages, perSize
 */
public class Page{

	private static final Logger logger = LoggerFactory.getLogger(Page.class);
	
	private long dataCnt=0;		//	데이터 개수
	private long totPages=0;	//	모든 페이지
	private long curPages=1;	//	현재 페이지
	private long perSize=5;		//	페이지당 데이터 건 수
	
	/**
	 * 작성자 : 김동우, 2016. 2. 16.
	 * 변경이력 : 
	 * 기능 : getter/setter
	 */

	public long getDataCnt() {
		return dataCnt;
	}
	public void setDataCnt(long dataCnt) {
		this.dataCnt = dataCnt;
	}
	public long getTotPages() {
		if(dataCnt>0) totPages = (long) (Math.floor(dataCnt/perSize) + Math.ceil(dataCnt%perSize));
		return totPages;
	}
	public void setTotPages(long totPages) {
		this.totPages = totPages;
	}
	public long getCurPages() {
		return curPages;
	}
	public void setCurPages(long curPages) {
		this.curPages = curPages;
	}
	public long getPerSize() {
		return perSize<=0?5:perSize;
	}
	public void setPerSize(long perSize) {
		this.perSize = perSize;
	}
	
	/**
	 * 
	 * 작성자 : 김동우, 2016. 2. 17.
	 * 변경이력 : 
	 * 기능 : 출력할 페이지를 list로 반환
	 */
	public List<Long> getPages(){
		
		long curr = this.getCurPages()==0?1:this.getCurPages();
		long tot = this.getTotPages()==0?1:this.getTotPages();
		long persize = this.getPerSize();
		
		long stPage = ((long) Math.floor((curr-1)/persize)+1)*persize - persize + 1;
		long edPage = ((long) Math.ceil((curr-1)/persize)+1)*persize; if(edPage > tot) edPage = tot;
		
		List<Long> pages = new ArrayList<Long>();
		for(long i=stPage ; i<=edPage ; i++){
			pages.add(i);
		}
		
		return pages;
	}

}