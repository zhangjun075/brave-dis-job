package com.brave.job.common;

import com.brave.config.ClientConfiguration;
import jdk.internal.instrumentation.ClassInstrumentation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author junzhang
 */
public interface Worker {
    void work(String ids);


}
