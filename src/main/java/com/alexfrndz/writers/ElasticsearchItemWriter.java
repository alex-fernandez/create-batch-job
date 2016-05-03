package com.alexfrndz.writers;

import com.alexfrndz.listener.JobDetailsCompletionNotificationListener;
import com.alexfrndz.pojo.JobDetails;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ElasticsearchItemWriter implements ItemWriter<JobDetails> {

    @Autowired
    public Client elasticsearchClient;

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void write(List<? extends JobDetails> list) throws Exception {
        BulkRequestBuilder requestBuilder = elasticsearchClient.prepareBulk();
        for (JobDetails jobDetails: list) {
            XContentBuilder sourceBuilder = XContentFactory.jsonBuilder().startObject()
                    .field("lat", jobDetails.getLat())
                    .field("lng", jobDetails.getLng())
                    .field("city", jobDetails.getCity())
                    .field("state", jobDetails.getState())
                    .field("country", jobDetails.getCountry())
                    .field("position", jobDetails.getPosition())
                    .field("description", jobDetails.getDescription())
                    .field("organization_name", jobDetails.getOrganizationName())
                    .field("cover_url", jobDetails.getCoverUrl())
                    .field("start_date", jobDetails.getStartDate())
                    .field("end_date", jobDetails.getEndDate())
                    .field("category", jobDetails.getCategory())
                    .field("salary_min", jobDetails.getSalaryMin())
                    .field("salary_max", jobDetails.getSalaryMax())
                    .field("sub_category", jobDetails.getSubCategory())
                    .field("work_type", jobDetails.getWorkType())
                    .field("created_date", new Date().getTime());
            IndexRequest request = new IndexRequest(JobDetailsCompletionNotificationListener.ES_INDEX, JobDetailsCompletionNotificationListener.ES_TYPE)
                    .id(jobDetails.getId()).source(sourceBuilder);
            requestBuilder.add(request);
        }
        BulkResponse bulkResponse = requestBuilder.execute().actionGet();
        int items = bulkResponse.getItems().length;
        log.info("Indexed {} items", items);
    }
}


