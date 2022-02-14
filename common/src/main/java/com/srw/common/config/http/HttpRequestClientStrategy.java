package com.srw.common.config.http;

import com.srw.common.constant.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class HttpRequestClientStrategy implements InitializingBean {

    private final ApplicationContext applicationContext;
    private Map<Long, RestTemplate> restTemplateMap;

    @Override
    public void afterPropertiesSet() {
        restTemplateMap = new HashMap<Long, RestTemplate>(4) {
            private static final long serialVersionUID = -3409064599340900782L;

            {
                put(Constants.TEN_SECONDS, applicationContext.getBean("restTemplate", RestTemplate.class));
                put(Constants.THIRTY_SECONDS, applicationContext.getBean("restTemplate30", RestTemplate.class));
                put(Constants.SIXTY_SECONDS, applicationContext.getBean("restTemplate60", RestTemplate.class));
                put(Constants.ONE_HUNDRED_SECONDS, applicationContext.getBean("restTemplate100", RestTemplate.class));
            }
        };

    }

    public RestTemplate getRestTemplate(Long timeout) {
        return restTemplateMap.computeIfAbsent(timeout, time -> restTemplateMap.get(getLatest(time, restTemplateMap.keySet())));
    }

    private long getLatest(Long time, Set<Long> longSet) {
        return longSet.stream().sorted(Comparator.comparingInt(Long::intValue)).filter(x -> x >= time).findFirst().orElse(Constants.ONE_HUNDRED_SECONDS);
    }

}
