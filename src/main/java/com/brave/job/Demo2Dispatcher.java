package com.brave.job;

import com.brave.annotation.JobName;
import com.brave.job.common.Dispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class Demo2Dispatcher extends Dispatcher {

    @Value("${demo2.switcher}")
    public String switcher;

    @JobName(name = "demo2")
    public String jobName;



    @Scheduled(cron = "${demo2.cron}")
    public void process() {
        if(switcher == null || "off".equals(switcher)){
            log.info("分布式任务调度关闭。");
            return;
        }
        run(jobName);

    }

    /**
     * 代扣任务获取和分片
     */
    @Override
    public void dispatchWork() {
        //获取id列表
        List<Integer> ids = Arrays.asList(100,101,102,104,105,106);
        writeWorkerData(jobName,ids);
    }
}
