package entity;

import java.util.List;

/**
 * @ClassName PageResult
 * @Description TODO
 * @Author Silence
 * @Date 2019/7/31 16:33
 * @Version 1.0
 **/
public class PageResult<T> {
    private long total;
    private List<T> rows;

    public PageResult() {
    }

    /**
     *
     * @param total 总记录数
     * @param rows 查询结果
     */
    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
