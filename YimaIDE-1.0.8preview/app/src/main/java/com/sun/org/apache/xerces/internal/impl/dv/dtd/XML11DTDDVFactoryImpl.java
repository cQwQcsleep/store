package com.sun.org.apache.xerces.internal.impl.dv.dtd;

import com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XML11DTDDVFactoryImpl extends DTDDVFactoryImpl {
    static Map<String, DatatypeValidator> XML11BUILTINTYPES;

    static {
        HashMap map = new HashMap();
        map.put("XML11ID", new XML11IDDatatypeValidator());
        XML11IDREFDatatypeValidator xML11IDREFDatatypeValidator = new XML11IDREFDatatypeValidator();
        map.put("XML11IDREF", xML11IDREFDatatypeValidator);
        map.put("XML11IDREFS", new ListDatatypeValidator(xML11IDREFDatatypeValidator));
        XML11NMTOKENDatatypeValidator xML11NMTOKENDatatypeValidator = new XML11NMTOKENDatatypeValidator();
        map.put("XML11NMTOKEN", xML11NMTOKENDatatypeValidator);
        map.put("XML11NMTOKENS", new ListDatatypeValidator(xML11NMTOKENDatatypeValidator));
        XML11BUILTINTYPES = Collections.unmodifiableMap(map);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.dtd.DTDDVFactoryImpl, com.sun.org.apache.xerces.internal.impl.dv.DTDDVFactory
    public DatatypeValidator getBuiltInDV(String str) {
        return XML11BUILTINTYPES.get(str) != null ? XML11BUILTINTYPES.get(str) : DTDDVFactoryImpl.fBuiltInTypes.get(str);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.dtd.DTDDVFactoryImpl, com.sun.org.apache.xerces.internal.impl.dv.DTDDVFactory
    public Map<String, DatatypeValidator> getBuiltInTypes() {
        HashMap map = new HashMap(DTDDVFactoryImpl.fBuiltInTypes);
        map.putAll(XML11BUILTINTYPES);
        return map;
    }
}
