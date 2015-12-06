
package org.mule.modules.anypoint.exchange.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.anypoint.exchange.AnypointExchangeConnector;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * A <code>AnypointExchangeConnectorProcessAdapter</code> is a wrapper around {@link AnypointExchangeConnector } that enables custom processing strategies.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.7.1", date = "2015-12-06T10:38:20-03:00", comments = "Build UNNAMED.2613.77421cc")
public class AnypointExchangeConnectorProcessAdapter
    extends AnypointExchangeConnectorLifecycleInjectionAdapter
    implements ProcessAdapter<AnypointExchangeConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, AnypointExchangeConnectorCapabilitiesAdapter> getProcessTemplate() {
        final AnypointExchangeConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,AnypointExchangeConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, AnypointExchangeConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, AnypointExchangeConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
