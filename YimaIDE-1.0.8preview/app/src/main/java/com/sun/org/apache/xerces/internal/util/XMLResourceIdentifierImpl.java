package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLResourceIdentifierImpl implements XMLResourceIdentifier {
    protected String fBaseSystemId;
    protected String fExpandedSystemId;
    protected String fLiteralSystemId;
    protected String fNamespace;
    protected String fPublicId;

    public XMLResourceIdentifierImpl(String str, String str2, String str3, String str4) {
        setValues(str, str2, str3, str4, null);
    }

    public void clear() {
        this.fPublicId = null;
        this.fLiteralSystemId = null;
        this.fBaseSystemId = null;
        this.fExpandedSystemId = null;
        this.fNamespace = null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier
    public String getBaseSystemId() {
        return this.fBaseSystemId;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier
    public String getExpandedSystemId() {
        return this.fExpandedSystemId;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier
    public String getLiteralSystemId() {
        return this.fLiteralSystemId;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier
    public String getNamespace() {
        return this.fNamespace;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier
    public String getPublicId() {
        return this.fPublicId;
    }

    public int hashCode() {
        String str = this.fPublicId;
        int iHashCode = str != null ? str.hashCode() : 0;
        String str2 = this.fLiteralSystemId;
        if (str2 != null) {
            iHashCode += str2.hashCode();
        }
        String str3 = this.fBaseSystemId;
        if (str3 != null) {
            iHashCode += str3.hashCode();
        }
        String str4 = this.fExpandedSystemId;
        if (str4 != null) {
            iHashCode += str4.hashCode();
        }
        String str5 = this.fNamespace;
        return str5 != null ? iHashCode + str5.hashCode() : iHashCode;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier
    public void setBaseSystemId(String str) {
        this.fBaseSystemId = str;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier
    public void setExpandedSystemId(String str) {
        this.fExpandedSystemId = str;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier
    public void setLiteralSystemId(String str) {
        this.fLiteralSystemId = str;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier
    public void setNamespace(String str) {
        this.fNamespace = str;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier
    public void setPublicId(String str) {
        this.fPublicId = str;
    }

    public void setValues(String str, String str2, String str3, String str4, String str5) {
        this.fPublicId = str;
        this.fLiteralSystemId = str2;
        this.fBaseSystemId = str3;
        this.fExpandedSystemId = str4;
        this.fNamespace = str5;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String str = this.fPublicId;
        if (str != null) {
            stringBuffer.append(str);
        }
        stringBuffer.append(':');
        String str2 = this.fLiteralSystemId;
        if (str2 != null) {
            stringBuffer.append(str2);
        }
        stringBuffer.append(':');
        String str3 = this.fBaseSystemId;
        if (str3 != null) {
            stringBuffer.append(str3);
        }
        stringBuffer.append(':');
        String str4 = this.fExpandedSystemId;
        if (str4 != null) {
            stringBuffer.append(str4);
        }
        stringBuffer.append(':');
        String str5 = this.fNamespace;
        if (str5 != null) {
            stringBuffer.append(str5);
        }
        return stringBuffer.toString();
    }

    public void setValues(String str, String str2, String str3, String str4) {
        setValues(str, str2, str3, str4, null);
    }

    public XMLResourceIdentifierImpl() {
    }

    public XMLResourceIdentifierImpl(String str, String str2, String str3, String str4, String str5) {
        setValues(str, str2, str3, str4, str5);
    }
}
