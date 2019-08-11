package fun.bryce.logback;

import lombok.Builder;
import lombok.Data;
import net.logstash.logback.encoder.org.apache.commons.lang3.builder.ToStringBuilder;
import net.logstash.logback.encoder.org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author bryce
 * 2019/7/4 15:33
 */
@Data
@Builder
public class Log
{
    private String user;
    private String pwd;
    private String index;


    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
