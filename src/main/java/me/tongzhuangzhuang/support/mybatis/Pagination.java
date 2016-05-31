package me.tongzhuangzhuang.support.mybatis;

import org.apache.ibatis.session.RowBounds;

import java.util.List;

public class Pagination<T> implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int total = 0; // 总条数
	int page = 1; // 当前页
	int totalPage = 0; // 总页数
	int offset = 0; // 偏移量
	int limit = 1; // 每页条数
	
	List<T> result;
	
	public Pagination(int page, int limit) {
		this.page = page;
		this.limit = limit;
	}

	public Pagination(int page, int limit, int total) {
		this.page = page;
		this.limit = limit;
		this.total = total;
		count();
	}

	public static Pagination newInstance(int page, int limit) {
		return new Pagination(page, limit);
	}

	public static Pagination newInstance(int page, int limit, int total) {
		return new Pagination(page, limit, total);
	}

	private Pagination count() {
		totalPage = total / limit + (total % limit == 0 ? 0 : 1);
		offset = (page - 1) * limit;
		return this;
	}

	public RowBounds getRowBounds() {
		count();
		return new RowBounds(this.offset, this.limit);
	}

	public static RowBounds newRowBounds(int page, int limit){
		return Pagination.newInstance(page, limit).getRowBounds();
	}
	
	public static RowBounds newRowBounds(int page, int limit, int total){
		return Pagination.newInstance(page, limit, total).getRowBounds();
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		count();
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getOffset() {
		count();
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

}
