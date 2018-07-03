package com.brave.job;

import com.brave.annotation.JobName;
import com.brave.job.common.WorkerRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class Demo2Worker extends WorkerRegister {

    @JobName(name = "demo2")
    public String jobName;

    @Override
    public void run(@NotNull String ids) {
        try {
            TimeUnit.SECONDS.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //处理列表，取得最大和最小的id，处理的时候，后台需要最好幂等的控制，否则不能用这种分片算法
        List<Integer> idl = processItem(ids);

        int minId = idl.stream().min(Integer::compareTo).orElse(0);
        int maxId = idl.stream().max(Integer::compareTo).orElse(0);
        log.info("{} the min:{},and the max is:{}",jobName,minId,maxId);
    }


    /**
     * 把worker现成注册上去。
     */
    @PostConstruct
    public void init() {
        super.init_1(jobName);
    }
}
