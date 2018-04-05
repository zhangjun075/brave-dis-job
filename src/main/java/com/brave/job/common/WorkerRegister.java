package com.brave.job.common;

import com.brave.config.ClientConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author junzhang
 */
public class WorkerRegister {

    @Autowired ClientConfiguration clientConfiguration;

    /**
     * @param path
     * @param value
     */
    public void register(String path,String value){
        clientConfiguration.setNodeData(path,value);
    }
}
