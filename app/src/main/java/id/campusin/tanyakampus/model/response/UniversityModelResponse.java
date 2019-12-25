package id.campusin.tanyakampus.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UniversityModelResponse {

    @SerializedName("current_page")
    private int currentPage;
    @SerializedName("first_page_url")
    private String firstPageUrl;
    @SerializedName("from")
    private int from;
    @SerializedName("last_page")
    private int lastPage;
    @SerializedName("last_page_url")
    private String lastPageUrl;
    @SerializedName("next_page_url")
    private String nextPageUrl;
    @SerializedName("per_page")
    private int perPage;
    @SerializedName("to")
    private String to;
    @SerializedName("total")
    private int total;
    @SerializedName("path")
    private String path;
    @SerializedName("data")
    private List<DataUniversityResponse> data;

    public List<DataUniversityResponse> getData() {
        return data;
    }

    public void setData(List<DataUniversityResponse> data) {
        this.data = data;
    }

    public String getFirstPageUrl() {
        return firstPageUrl;
    }

    public void setFirstPageUrl(String firstPageUrl) {
        this.firstPageUrl = firstPageUrl;
    }



    public String getLastPageUrl() {
        return lastPageUrl;
    }

    public void setLastPageUrl(String lastPageUrl) {
        this.lastPageUrl = lastPageUrl;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
}




