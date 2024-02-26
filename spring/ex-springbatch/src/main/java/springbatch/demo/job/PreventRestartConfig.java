package springbatch.demo.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class PreventRestartConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job PreventRestartJob() {
        return new JobBuilder("PreventRestartJob", jobRepository)
                .start(preventStep1())
                .next(preventStep2())
                .preventRestart()
                .build();
    }

    @Bean
    public Step preventStep1() {
        return new StepBuilder("preventStep1", jobRepository)
                .tasklet(((contribution, chunkContext) -> {
                    System.out.println(" >>> step1 executed");
                    return RepeatStatus.FINISHED;
                }), transactionManager)
                .build();
    }

    @Bean
    public Step preventStep2() {
        return new StepBuilder("preventStep2", jobRepository)
                .tasklet(((contribution, chunkContext) -> {
                    throw new RuntimeException("step2 was failed");
//                    System.out.println(" >>> step2 executed");
//                    return RepeatStatus.FINISHED;
                }), transactionManager)
                .build();
    }
}
