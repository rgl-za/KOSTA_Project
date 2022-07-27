package com.project.configuration;

import com.project.domain.RecommendDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<RecommendDTO> reader(){
        JdbcCursorItemReader<RecommendDTO> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT u.USERNO, h.PNUM, 1 AS PREFER\n" +
                "FROM USERS u, HEART h  \n" +
                "WHERE u.USERID = h.USERID \n" +
                "GROUP BY u.USERNO, h.PNUM");
        reader.setRowMapper(new RowMapper<RecommendDTO>() {
            @Override
            public RecommendDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                RecommendDTO recommendDTO = new RecommendDTO();
                recommendDTO.setUserNo(rs.getInt("userNo"));
                recommendDTO.setPnum(rs.getInt("pnum"));
                recommendDTO.setPrefer(rs.getInt("prefer"));
                return recommendDTO;
            }
        });
        return reader;
    }

    @Bean
    public FlatFileItemWriter<RecommendDTO> writer(){
        FlatFileItemWriter<RecommendDTO> writer = new FlatFileItemWriter<RecommendDTO>();
        
        writer.setResource(new FileSystemResource("/Users/jihyeonjeong/KOSTA_Project/data/recommend"));
        DelimitedLineAggregator<RecommendDTO> aggregator = new DelimitedLineAggregator<>();
        BeanWrapperFieldExtractor<RecommendDTO> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"userNo", "pnum", "prefer"});
        aggregator.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(aggregator);
        return writer;
    }

    @Bean
    public Step executeStep(){
        return stepBuilderFactory.get("executeStep").<RecommendDTO, RecommendDTO> chunk(10).reader(reader()).writer(writer())
                .build();
    }

    @Bean
    public Job processJob(){
        return jobBuilderFactory.get("processJob").incrementer(new RunIdIncrementer()).flow(executeStep()).end()
                .build();
    }
}
