package com.sun.org.apache.xerces.internal.impl.dv;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.DTDDVFactoryImpl;
import com.sun.org.apache.xerces.internal.impl.dv.dtd.XML11DTDDVFactoryImpl;
import com.sun.org.apache.xerces.internal.utils.ObjectFactory;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DTDDVFactory {
    private static final String DEFAULT_FACTORY_CLASS = "com.sun.org.apache.xerces.internal.impl.dv.dtd.DTDDVFactoryImpl";
    private static final String XML11_DATATYPE_VALIDATOR_FACTORY = "com.sun.org.apache.xerces.internal.impl.dv.dtd.XML11DTDDVFactoryImpl";

    public static final DTDDVFactory getInstance(String str) throws DVFactoryException {
        try {
            if (DEFAULT_FACTORY_CLASS.equals(str)) {
                return new DTDDVFactoryImpl();
            }
            return XML11_DATATYPE_VALIDATOR_FACTORY.equals(str) ? new XML11DTDDVFactoryImpl() : (DTDDVFactory) ObjectFactory.newInstance(str, true);
        } catch (ClassCastException unused) {
            throw new DVFactoryException("DTD factory class " + str + " does not extend from DTDDVFactory.");
        }
    }

    public abstract DatatypeValidator getBuiltInDV(String str);

    public abstract Map<String, DatatypeValidator> getBuiltInTypes();

    public static final DTDDVFactory getInstance() throws DVFactoryException {
        return getInstance(DEFAULT_FACTORY_CLASS);
    }
}
