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

    /**
     *
     * @param ids
     * @return
     */
    default List<Integer> processItem(@NotNull String ids) {
        List<Integer> result = new ArrayList<>();

        String[] idr = ids.replace("[","").replace("]","").split(",");
        if(idr.length == 0 || idr == null) {
            return null;
        }
        Arrays.stream(idr).forEach(id -> {
            result.add(Integer.parseInt(id));
        });
        return result;
    }
}
