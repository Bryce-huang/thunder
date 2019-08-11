package fun.bryce.log4j2;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author bryce
 * 2019/7/3 15:50
 */
@Component
public class ApplicationStartedEventListener implements GenericApplicationListener
{

    public static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 10;

    private static Class<?>[] EVENT_TYPES = {ApplicationStartingEvent.class, ApplicationEnvironmentPreparedEvent.class, ApplicationPreparedEvent.class, ContextClosedEvent.class, ApplicationFailedEvent.class};

    private static Class<?>[] SOURCE_TYPES = {SpringApplication.class, ApplicationContext.class};



    @Override
    public void onApplicationEvent(ApplicationEvent event)
    {
        if (event instanceof ApplicationEnvironmentPreparedEvent)
        {
            ConfigurableEnvironment envi = ((ApplicationEnvironmentPreparedEvent) event).getEnvironment();

            String app = envi.getProperty("spring.application.name");
            String LOGSTASH_HOST = envi.getProperty("LOGSTASH_HOST");
            String LOGSTASH_PORT = envi.getProperty("LOGSTASH_PORT");
            MDC.put("app", app);
            MDC.put("LOGSTASH_HOST", LOGSTASH_HOST);
            MDC.put("LOGSTASH_PORT", LOGSTASH_PORT);


        }

    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.core.Ordered#getOrder()
     */
    @Override
    public int getOrder()
    {
        // TODO Auto-generated method stub
        return DEFAULT_ORDER;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.context.event.GenericApplicationListener#
     * supportsEventType(org.springframework.core.ResolvableType)
     */
    @Override
    public boolean supportsEventType(ResolvableType resolvableType)
    {
        return isAssignableFrom(resolvableType.getRawClass(), EVENT_TYPES);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType)
    {
        return isAssignableFrom(sourceType, SOURCE_TYPES);
    }

    private boolean isAssignableFrom(Class<?> type, Class<?>... supportedTypes)
    {
        if (type != null)
        {
            for (Class<?> supportedType : supportedTypes)
            {
                if (supportedType.isAssignableFrom(type))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
