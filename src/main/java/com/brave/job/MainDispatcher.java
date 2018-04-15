package com.brave.job;

import com.brave.job.common.Dispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class MainDispatcher extends Dispatcher {

    @Value("${demo1.switcher}")
    public String switcher;

    public String jobName = "demo1";



    @Scheduled(cron = "${demo1.cron}")
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
        List<Integer> ids = Arrays.asList(1,2,3,4,5,6);
        writeWorkerData(jobName,ids);
    }
}
