package com.brave.job;

import com.brave.job.common.Worker;
import com.brave.util.JobUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author junzhang
 */
@Slf4j
@Component
public class MainWorker implements Worker {


    @Autowired JobUtil jobUtil;
    @Value("${server.port}")
    private String port;

    private int id;

    public String jobName = "demo1";

    private void run(@NotNull String ids) {
        //处理列表，取得最大和最小的id，处理的时候，后台需要最好幂等的控制，否则不能用这种分片算法
        List<Integer> idl = processItem(ids);

        int minId = idl.stream().min(Integer::compareTo).orElse(0);
        int maxId = idl.stream().max(Integer::compareTo).orElse(0);
        log.info("the min:{},and the max is:{}",minId,maxId);
    }


    @Override public void work(String ids) {
        run(ids);
    }
}

