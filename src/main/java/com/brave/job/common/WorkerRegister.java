package com.brave.job.common;

import com.brave.config.ClientConfiguration;
import com.brave.util.IpUtil;
import com.brave.util.JobUtil;
import com.brave.vo.JobProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author junzhang
 */
@Slf4j
public class WorkerRegister {

    @Autowired ClientConfiguration clientConfiguration;
    @Autowired JobUtil jobUtil;

    /**
     * @param path
     * @param value
     */
    public void register(String path,String value){
        clientConfiguration.setNodeData(path,value);
    }

    /**
     * @param jobName
     */
    public void registerLog(String jobName) {
        JobProperty jobProperty = JobUtil.JOB_NODE_MAP.get(jobName);
        String exeDispatcherPath = jobProperty.getLock() + "/" + IpUtil.getLocalIP() + "-" + jobUtil.getPort() +"-worker";

        try {
            ClientConfiguration.curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(exeDispatcherPath);
        } catch (Exception e) {
            log.info("add job {} log exeception:{}",exeDispatcherPath,e);
        }
    }

    /**
     * @param jobName
     */
    public void unRegisterLog(String jobName) {
        JobProperty jobProperty = JobUtil.JOB_NODE_MAP.get(jobName);
        String exeDispatcherPath = jobProperty.getLock() + "/" + IpUtil.getLocalIP() + "-" + jobUtil.getPort() +"-worker";
        try {
            ClientConfiguration.curatorFramework.delete().forPath(exeDispatcherPath);
        } catch (Exception e) {
            log.info("delete job {} log exeception:{}",exeDispatcherPath,e);
        }
    }
}
