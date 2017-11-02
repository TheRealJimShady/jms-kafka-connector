package com.github.bikeholik.kafka.connector.ffmq;

import net.timewalker.ffmq3.FFMQConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * This class can be resolved by the JmsConnectorConfig, and will return a ConnectionFactory for an FFMQ JMS implementation.
 *
 * @author Jim Smith
 */
@Configuration
public class FFMQConnectionFactoryConfig {
    @Bean
    ConnectionFactory ffmqConnectionFactory() {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, FFMQConstants.JNDI_CONTEXT_FACTORY);
        env.put(Context.PROVIDER_URL, "tcp://localhost:10002");
        Context context = null;
        ConnectionFactory factory = null;
        try {
            context = new InitialContext(env);
            factory = (ConnectionFactory)context.lookup(FFMQConstants.JNDI_CONNECTION_FACTORY_NAME);
        } catch (NamingException e) {e.printStackTrace();}
        return factory;
    }
}
