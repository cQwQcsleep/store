package com.sun.org.apache.xml.internal.serializer;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xalan.internal.utils.ObjectFactory;
import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import com.sun.org.apache.xml.internal.serializer.utils.WrappedRuntimeException;
import java.util.Properties;
import org.xml.sax.ContentHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class SerializerFactory {
    private SerializerFactory() {
    }

    public static Serializer getSerializer(Properties properties) {
        try {
            String property = properties.getProperty(Constants.ATTRNAME_OUTPUT_METHOD);
            if (property == null) {
                throw new IllegalArgumentException(Utils.messages.createMessage(MsgKey.ER_FACTORY_PROPERTY_MISSING, new Object[]{Constants.ATTRNAME_OUTPUT_METHOD}));
            }
            String property2 = properties.getProperty(OutputPropertiesFactory.S_KEY_CONTENT_HANDLER);
            if (property2 == null && (property2 = OutputPropertiesFactory.getDefaultMethodProperties(property).getProperty(OutputPropertiesFactory.S_KEY_CONTENT_HANDLER)) == null) {
                throw new IllegalArgumentException(Utils.messages.createMessage(MsgKey.ER_FACTORY_PROPERTY_MISSING, new Object[]{OutputPropertiesFactory.S_KEY_CONTENT_HANDLER}));
            }
            Object objNewInstance = ObjectFactory.findProviderClass(property2, true).getConstructor(null).newInstance(null);
            if (objNewInstance instanceof SerializationHandler) {
                Serializer serializer = (Serializer) objNewInstance;
                serializer.setOutputFormat(properties);
                return serializer;
            }
            if (!(objNewInstance instanceof ContentHandler)) {
                throw new Exception(Utils.messages.createMessage("ER_SERIALIZER_NOT_CONTENTHANDLER", new Object[]{property2}));
            }
            SerializationHandler serializationHandler = (SerializationHandler) ObjectFactory.findProviderClass(SerializerConstants.DEFAULT_SAX_SERIALIZER, true).getConstructor(null).newInstance(null);
            serializationHandler.setContentHandler((ContentHandler) objNewInstance);
            serializationHandler.setOutputFormat(properties);
            return serializationHandler;
        } catch (Exception e) {
            throw new WrappedRuntimeException(e);
        }
    }
}
