package br.com.rodrigo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJvmExitCodeMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dgrodrigo on 17/07/16.
 */
public class App {

    public static void main(String[] args) {
        String[] springConfig = {"spring/batch/jobs/job-hello-world.xml"};

        ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);


        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("CalcularIdadePessoas");

        try{

            JobParameters jobParameters = new JobParametersBuilder().addString("nome", "Job1").toJobParameters();

            SimpleJvmExitCodeMapper mapper = new SimpleJvmExitCodeMapper();

            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
            System.out.println("Exit Status: " + jobExecution);

            String status = jobExecution.getExitStatus().getExitCode();
            Integer returnCode = mapper.intValue(status);
            System.exit(returnCode);

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Done");

    }

}
