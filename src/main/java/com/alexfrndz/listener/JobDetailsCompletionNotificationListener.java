package com.alexfrndz.listener;

import com.alexfrndz.utils.IndexUtils;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobDetailsCompletionNotificationListener extends JobExecutionListenerSupport {

    public static final String ES_HOST = "localdocker";
    public static final Integer ES_PORT = 9300;
    public static final String ES_INDEX = "commonjobs";
    public static final String ES_TYPE = "jobs";

    private static final Logger log = LoggerFactory.getLogger(JobDetailsCompletionNotificationListener.class);

    private final Client elasticsearchClient;

    @Autowired
    public JobDetailsCompletionNotificationListener(Client elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    @Autowired
    private com.sun.jersey.api.client.Client jerseyClient;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
        }
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("String loading Spring batch");
        String[] indexes = new String[1];
        indexes[0] = ES_INDEX;
        IndicesExistsResponse indicesExistsResponse = elasticsearchClient.admin().indices().exists(new IndicesExistsRequest().indices(indexes)).actionGet();
        if (indicesExistsResponse.isExists()) {
            DeleteIndexResponse delete = elasticsearchClient.admin().indices().delete(new DeleteIndexRequest(ES_INDEX)).actionGet();
            if (delete.isAcknowledged()) {
                Boolean isIndexCreated = createIndex();
                if (isIndexCreated) {
                    createType();
                }
            }
        } else {
            Boolean isIndexCreated = createIndex();
            if (isIndexCreated) {
                createType();
            }
        }


    }

    public Boolean createIndex() {
        String settings = "{\n" +
                "  \"analysis\": {\n" +
                "    \"char_filter\": {\n" +
                "       \"replace\": {\n" +
                "        \"type\": \"mapping\",\n" +
                "        \"mappings\": [\n" +
                "          \"&=> and \"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    \"filter\": {\n" +
                "      \"word_delimiter\" : {\n" +
                "        \"type\" : \"word_delimiter\",\n" +
                "        \"split_on_numerics\" : false,\n" +
                "        \"split_on_case_change\" : true,\n" +
                "        \"generate_word_parts\" : true,\n" +
                "        \"generate_number_parts\" : true,\n" +
                "        \"catenate_all\" : true,\n" +
                "        \"preserve_original\":true,\n" +
                "        \"catenate_numbers\":true\n" +
                "      }\n" +
                "    },\n" +
                "    \"analyzer\": {\n" +
                "      \"default\": {\n" +
                "        \"type\": \"custom\",\n" +
                "        \"char_filter\": [\n" +
                "          \"html_strip\",\n" +
                "          \"replace\"\n" +
                "        ],\n" +
                "        \"tokenizer\": \"whitespace\",\n" +
                "        \"filter\": [\n" +
                "            \"lowercase\",\n" +
                "            \"word_delimiter\"\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        log.info(settings);

        WebResource resource = jerseyClient.resource("http://" + ES_HOST + ":9200/" + ES_INDEX);
        ClientResponse post = resource
                .type("application/json")
                .post(ClientResponse.class, settings);
        if (post.getStatus() == 200) {
            log.info("Index Created");
            return true;
        } else {
            return false;
        }
    }

    private boolean createType() {
        try {
            XContentBuilder mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("properties")
                    .startObject("id")
                    .field("type", "integer")
                    .field("index", "not_analyzed")
                    .endObject()
                    .startObject("lat")
                    .field("type", "float")
                    .endObject()
                    .startObject("state")
                    .field("type", "multi_field")
                    .field("fields", IndexUtils.getMultiField("state"))
                    .endObject()
                    .startObject("city")
                    .field("type", "multi_field")
                    .field("fields", IndexUtils.getMultiField("city"))
                    .endObject()
                    .startObject("country")
                    .field("type", "multi_field")
                    .field("fields", IndexUtils.getMultiField("country"))
                    .endObject()
                    .startObject("position")
                    .field("type", "multi_field")
                    .startObject("fields")
                    .startObject("raw")
                    .field("type", "string")
                    .field("index", "not_analyzed")
                        .endObject()
                        .startObject("value")
                    .field("type", "string")
                    .field("analyzer", "default")
                    .field("index", "analyzed")
                    .endObject()
                    .endObject()
                    .endObject()
                    .startObject("description")
                    .field("type", "multi_field")
                    .field("fields", IndexUtils.getMultiField("description"))
                    .endObject()
                    .startObject("organization_name")
                    .field("type", "multi_field")
                    .field("fields", IndexUtils.getMultiField("organization_name"))
                    .endObject()
                    .startObject("start_date")
                    .field("type", "string")
                    .endObject()
                    .startObject("end_date")
                    .field("type", "string")
                    .endObject()
                    .startObject("category")
                    .field("type", "multi_field")
                    .field("fields", IndexUtils.getMultiField("category"))
                    .endObject()
                    .startObject("salary_min")
                    .field("type", "float")
                    .endObject()
                    .startObject("salary_currency")
                    .field("type", "string")
                    .endObject()
                    .startObject("work_type")
                    .field("type", "string")
                    .endObject()
                    .startObject("cover_url")
                    .field("type", "string")
                    .endObject()
                    .startObject("created_date")
                    .field("type", "date")
                    .field("format", "strict_date_optional_time||epoch_millis")
                    .endObject()
                    .startObject("suggest")
                    .field("type", "completion")
                    .field("payloads", true)
                    .endObject()
                    .endObject()
                    .endObject();

            String types = mapping.prettyPrint().string();
            log.info(types);
            WebResource resource = jerseyClient.resource("http://" + ES_HOST + ":9200/" + ES_INDEX + "/" + ES_TYPE + "/_mappings");
            ClientResponse post = resource
                    .type("application/json")
                    .post(ClientResponse.class, types);
            if (post.getStatus() == 200) {
                log.info("Type Created");
                return true;
            } else {
                log.info("Type Created");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }
}

