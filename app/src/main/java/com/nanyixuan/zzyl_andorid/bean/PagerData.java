package com.nanyixuan.zzyl_andorid.bean;

import java.util.List;

/**
 * Created by YixuanNan on 2017/3/23.
 *
 * @author YixuanNan
 */

public class PagerData<T> {

    /**
     * content : {}
     * last : true
     * totalPages : 1
     * totalElements : 2
     * number : 0
     * size : 10
     * first : true
     * sort : {}
     * numberOfElements : 2
     */

    private T content;
    private boolean last;
    private int totalPages;
    private int totalElements;
    private int number;
    private int size;
    private boolean first;
    private List<SortData> sort;
    private int numberOfElements;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public List<SortData> getSort() {
        return sort;
    }

    public void setSort(List<SortData> sort) {
        this.sort = sort;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    @Override
    public String toString() {
        return "PagerData{" +
                "content=" + content +
                ", last=" + last +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", number=" + number +
                ", size=" + size +
                ", first=" + first +
                ", sort=" + sort +
                ", numberOfElements=" + numberOfElements +
                '}';
    }
}
