package pt.impresa.liferay.content.service.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import pt.impresa.liferay.content.service.ClpSerializer;
import pt.impresa.liferay.content.service.ImpresaContentLocalServiceUtil;
import pt.impresa.liferay.content.service.ImpresaContentServiceUtil;


public class ClpMessageListener extends BaseMessageListener {
    public static String getServletContextName() {
        return ClpSerializer.getServletContextName();
    }

    @Override
    protected void doReceive(Message message) throws Exception {
        String command = message.getString("command");
        String servletContextName = message.getString("servletContextName");

        if (command.equals("undeploy") &&
                servletContextName.equals(getServletContextName())) {
            ImpresaContentLocalServiceUtil.clearService();

            ImpresaContentServiceUtil.clearService();
        }
    }
}
