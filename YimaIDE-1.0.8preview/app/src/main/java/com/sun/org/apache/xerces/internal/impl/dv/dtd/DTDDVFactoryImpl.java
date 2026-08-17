package com.sun.org.apache.xerces.internal.impl.dv.dtd;

import com.sun.org.apache.xerces.internal.impl.dv.DTDDVFactory;
import com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTDDVFactoryImpl extends DTDDVFactory {
    static final Map<String, DatatypeValidator> fBuiltInTypes;

    static {
        HashMap map = new HashMap();
        map.put("string", new StringDatatypeValidator());
        map.put(SchemaSymbols.ATTVAL_ID, new IDDatatypeValidator());
        IDREFDatatypeValidator iDREFDatatypeValidator = new IDREFDatatypeValidator();
        map.put(SchemaSymbols.ATTVAL_IDREF, iDREFDatatypeValidator);
        map.put(SchemaSymbols.ATTVAL_IDREFS, new ListDatatypeValidator(iDREFDatatypeValidator));
        ENTITYDatatypeValidator eNTITYDatatypeValidator = new ENTITYDatatypeValidator();
        map.put(SchemaSymbols.ATTVAL_ENTITY, new ENTITYDatatypeValidator());
        map.put(SchemaSymbols.ATTVAL_ENTITIES, new ListDatatypeValidator(eNTITYDatatypeValidator));
        map.put(SchemaSymbols.ATTVAL_NOTATION, new NOTATIONDatatypeValidator());
        NMTOKENDatatypeValidator nMTOKENDatatypeValidator = new NMTOKENDatatypeValidator();
        map.put(SchemaSymbols.ATTVAL_NMTOKEN, nMTOKENDatatypeValidator);
        map.put(SchemaSymbols.ATTVAL_NMTOKENS, new ListDatatypeValidator(nMTOKENDatatypeValidator));
        fBuiltInTypes = Collections.unmodifiableMap(map);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.DTDDVFactory
    public DatatypeValidator getBuiltInDV(String str) {
        return fBuiltInTypes.get(str);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.DTDDVFactory
    public Map<String, DatatypeValidator> getBuiltInTypes() {
        return new HashMap(fBuiltInTypes);
    }
}
