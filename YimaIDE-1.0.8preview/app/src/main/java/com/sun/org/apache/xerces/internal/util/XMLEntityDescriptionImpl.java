package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.impl.XMLEntityDescription;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLEntityDescriptionImpl extends XMLResourceIdentifierImpl implements XMLEntityDescription {
    protected String fEntityName;

    public XMLEntityDescriptionImpl(String str, String str2, String str3, String str4, String str5) {
        setDescription(str, str2, str3, str4, str5);
    }

    @Override // com.sun.org.apache.xerces.internal.util.XMLResourceIdentifierImpl
    public void clear() {
        super.clear();
        this.fEntityName = null;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLEntityDescription
    public String getEntityName() {
        return this.fEntityName;
    }

    @Override // com.sun.org.apache.xerces.internal.util.XMLResourceIdentifierImpl
    public int hashCode() {
        int iHashCode = super.hashCode();
        String str = this.fEntityName;
        return str != null ? iHashCode + str.hashCode() : iHashCode;
    }

    public void setDescription(String str, String str2, String str3, String str4, String str5) {
        setDescription(str, str2, str3, str4, str5, null);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLEntityDescription
    public void setEntityName(String str) {
        this.fEntityName = str;
    }

    @Override // com.sun.org.apache.xerces.internal.util.XMLResourceIdentifierImpl
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String str = this.fEntityName;
        if (str != null) {
            stringBuffer.append(str);
        }
        stringBuffer.append(':');
        String str2 = this.fPublicId;
        if (str2 != null) {
            stringBuffer.append(str2);
        }
        stringBuffer.append(':');
        String str3 = this.fLiteralSystemId;
        if (str3 != null) {
            stringBuffer.append(str3);
        }
        stringBuffer.append(':');
        String str4 = this.fBaseSystemId;
        if (str4 != null) {
            stringBuffer.append(str4);
        }
        stringBuffer.append(':');
        String str5 = this.fExpandedSystemId;
        if (str5 != null) {
            stringBuffer.append(str5);
        }
        stringBuffer.append(':');
        String str6 = this.fNamespace;
        if (str6 != null) {
            stringBuffer.append(str6);
        }
        return stringBuffer.toString();
    }

    public XMLEntityDescriptionImpl() {
    }

    public XMLEntityDescriptionImpl(String str, String str2, String str3, String str4, String str5, String str6) {
        setDescription(str, str2, str3, str4, str5, str6);
    }

    public void setDescription(String str, String str2, String str3, String str4, String str5, String str6) {
        this.fEntityName = str;
        setValues(str2, str3, str4, str5, str6);
    }
}
