package com.alexfrndz.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "lat",
        "lng",
        "city",
        "state",
        "country",
        "position",
        "description",
        "organization_name",
        "cover_url",
        "start_date",
        "end_date",
        "category",
        "salary_min",
        "salary_max",
        "salary_currency",
        "sub_category",
        "work_type"
})
public class JobDetails {

    @JsonProperty("id")
    private String id;
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lng")
    private String lng;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("country")
    private String country;
    @JsonProperty("position")
    private String position;
    @JsonProperty("description")
    private String description;
    @JsonProperty("organization_name")
    private String organizationName;
    @JsonProperty("cover_url")
    private String coverUrl;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("category")
    private String category;
    @JsonProperty("salary_min")
    private String salaryMin;
    @JsonProperty("salary_max")
    private String salaryMax;
    @JsonProperty("salary_currency")
    private String salaryCurrency;
    @JsonProperty("sub_category")
    private String subCategory;
    @JsonProperty("work_type")
    private String workType;

    /**
     * @return The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The lat
     */
    @JsonProperty("lat")
    public String getLat() {
        return lat;
    }

    /**
     * @param lat The lat
     */
    @JsonProperty("lat")
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * @return The lng
     */
    @JsonProperty("lng")
    public String getLng() {
        return lng;
    }

    /**
     * @param lng The lng
     */
    @JsonProperty("lng")
    public void setLng(String lng) {
        this.lng = lng;
    }

    /**
     * @return The city
     */
    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The state
     */
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     * @param state The state
     */
    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return The country
     */
    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return The position
     */
    @JsonProperty("position")
    public String getPosition() {
        return position;
    }

    /**
     * @param position The position
     */
    @JsonProperty("position")
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The organizationName
     */
    @JsonProperty("organization_name")
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * @param organizationName The organization_name
     */
    @JsonProperty("organization_name")
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    /**
     * @return The coverUrl
     */
    @JsonProperty("cover_url")
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * @param coverUrl The cover_url
     */
    @JsonProperty("cover_url")
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    /**
     * @return The startDate
     */
    @JsonProperty("start_date")
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate The start_date
     */
    @JsonProperty("start_date")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return The endDate
     */
    @JsonProperty("end_date")
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate The end_date
     */
    @JsonProperty("end_date")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return The category
     */
    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return The salaryMin
     */
    @JsonProperty("salary_min")
    public String getSalaryMin() {
        return salaryMin;
    }

    /**
     * @param salaryMin The salary_min
     */
    @JsonProperty("salary_min")
    public void setSalaryMin(String salaryMin) {
        this.salaryMin = salaryMin;
    }

    /**
     * @return The salaryMax
     */
    @JsonProperty("salary_max")
    public String getSalaryMax() {
        return salaryMax;
    }

    /**
     * @param salaryMax The salary_max
     */
    @JsonProperty("salary_max")
    public void setSalaryMax(String salaryMax) {
        this.salaryMax = salaryMax;
    }

    /**
     * @return The salaryCurrency
     */
    @JsonProperty("salary_currency")
    public String getSalaryCurrency() {
        return salaryCurrency;
    }

    /**
     * @param salaryCurrency The salary_currency
     */
    @JsonProperty("salary_currency")
    public void setSalaryCurrency(String salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }

    /**
     * @return The subCategory
     */
    @JsonProperty("sub_category")
    public String getSubCategory() {
        return subCategory;
    }

    /**
     * @param subCategory The sub_category
     */
    @JsonProperty("sub_category")
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    /**
     * @return The workType
     */
    @JsonProperty("work_type")
    public String getWorkType() {
        return workType;
    }

    /**
     * @param workType The work_type
     */
    @JsonProperty("work_type")
    public void setWorkType(String workType) {
        this.workType = workType;
    }

}