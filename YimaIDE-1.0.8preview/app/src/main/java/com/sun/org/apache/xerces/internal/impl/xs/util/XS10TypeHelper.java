package com.sun.org.apache.xerces.internal.impl.xs.util;

import com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl;
import com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XS10TypeHelper {
    private XS10TypeHelper() {
    }

    public static String getSchemaTypeName(XSTypeDefinition xSTypeDefinition) {
        return xSTypeDefinition instanceof XSSimpleTypeDefinition ? ((XSSimpleTypeDecl) xSTypeDefinition).getTypeName() : ((XSComplexTypeDecl) xSTypeDefinition).getTypeName();
    }
}
