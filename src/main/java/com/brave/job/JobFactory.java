package com.brave.job;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

/**
 * @author junzhang
 */
@Component
public class JobFactory implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException {
        if(JobFactory.applicationContext == null ) {
            JobFactory.applicationContext = applicationContext;
        }
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    public void process(String data,String path) {
        MainWorker mainWorker = null;
        try {
            mainWorker = (MainWorker) getBean(Class.forName(path));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        mainWorker.work(data);
    }

}
