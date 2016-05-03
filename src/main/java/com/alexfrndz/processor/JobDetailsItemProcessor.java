package com.alexfrndz.processor;


import com.alexfrndz.pojo.JobDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class JobDetailsItemProcessor implements ItemProcessor<JobDetails, JobDetails> {

    private static final Logger log = LoggerFactory.getLogger(JobDetailsItemProcessor.class);

    @Override
    public JobDetails process(final JobDetails jobDetails) throws Exception {
        final JobDetails transformedJobDetails = jobDetails;
       // log.info("Converting (" + jobDetails + ") into (" + transformedJobDetails + ")");
        return transformedJobDetails;
    }

}