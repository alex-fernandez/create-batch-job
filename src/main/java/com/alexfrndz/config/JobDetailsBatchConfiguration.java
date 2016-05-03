package com.alexfrndz.config;

import com.alexfrndz.listener.JobDetailsCompletionNotificationListener;
import com.alexfrndz.pojo.JobDetails;
import com.alexfrndz.processor.JobDetailsItemProcessor;
import com.alexfrndz.writers.ElasticsearchItemWriter;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.Client;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
@Import({AppConfig.class})
@Slf4j
public class JobDetailsBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public Client elasticsearchClient;

    @Bean
    public FlatFileItemReader<JobDetails> reader() {
        FlatFileItemReader<JobDetails> reader = new FlatFileItemReader<JobDetails>();
        reader.setResource(new ClassPathResource("job.csv"));
        final String fields = "id,lat,lng,city,state,country,position,description,organization_name,cover_url,start_date,end_date,category,salary_min,salary_max,salary_currency,sub_category,work_type";
        final String[] split = fields.split("\\s*,\\s*");
        log.info(split.toString());
        reader.setLineMapper(new DefaultLineMapper<JobDetails>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(split);
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<JobDetails>() {{
                setTargetType(JobDetails.class);
            }});
        }});
        return reader;
    }

    @Bean
    public JobDetailsItemProcessor processor() {
        return new JobDetailsItemProcessor();
    }

    @Bean
    public ElasticsearchItemWriter writer() {
        ElasticsearchItemWriter writer = new ElasticsearchItemWriter();
        return writer;
    }


    @Bean
    public JobExecutionListener listener() {
        return new JobDetailsCompletionNotificationListener(elasticsearchClient);
    }

    @Bean
    public Job importJobsJob() {
        return jobBuilderFactory.get("importJobsJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("importJobDetailsJobStep1")
                .<JobDetails, JobDetails>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

}