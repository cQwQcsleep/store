package com.sun.xml.internal.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.namespace.QName;
import javax.xml.stream.events.Attribute;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AttributeImpl extends DummyEvent implements Attribute {
    private String fAttributeType;
    private boolean fIsSpecified;
    private String fNonNormalizedvalue;
    private QName fQName;
    private String fValue;

    public AttributeImpl(QName qName, String str, String str2, String str3, boolean z) {
        this.fAttributeType = "CDATA";
        init();
        this.fQName = qName;
        this.fValue = str;
        if (str3 != null && !str3.isEmpty()) {
            this.fAttributeType = str3;
        }
        this.fNonNormalizedvalue = str2;
        this.fIsSpecified = z;
    }

    @Override // javax.xml.stream.events.Attribute
    public String getDTDType() {
        return this.fAttributeType;
    }

    @Override // javax.xml.stream.events.Attribute
    public QName getName() {
        return this.fQName;
    }

    public String getNonNormalizedValue() {
        return this.fNonNormalizedvalue;
    }

    @Override // javax.xml.stream.events.Attribute
    public String getValue() {
        return this.fValue;
    }

    public void init() {
        setEventType(10);
    }

    @Override // javax.xml.stream.events.Attribute
    public boolean isSpecified() {
        return this.fIsSpecified;
    }

    public void setAttributeType(String str) {
        this.fAttributeType = str;
    }

    public void setName(QName qName) {
        this.fQName = qName;
    }

    public void setNonNormalizedValue(String str) {
        this.fNonNormalizedvalue = str;
    }

    public void setSpecified(boolean z) {
        this.fIsSpecified = z;
    }

    public void setValue(String str) {
        this.fValue = str;
    }

    public String toString() {
        if (this.fQName.getPrefix() == null || this.fQName.getPrefix().length() <= 0) {
            return this.fQName.getLocalPart() + "='" + this.fValue + "'";
        }
        return this.fQName.getPrefix() + ":" + this.fQName.getLocalPart() + "='" + this.fValue + "'";
    }

    @Override // com.sun.xml.internal.stream.events.DummyEvent
    public void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write(toString());
    }

    public AttributeImpl(String str, String str2) {
        this.fAttributeType = "CDATA";
        init();
        this.fQName = new QName(str);
        this.fValue = str2;
    }

    public AttributeImpl(String str, String str2, String str3) {
        this(str, null, str2, str3, null, null, false);
    }

    public AttributeImpl(String str, String str2, String str3, String str4, String str5) {
        this(str, str2, str3, str4, null, str5, false);
    }

    public AttributeImpl(String str, String str2, String str3, String str4, String str5, String str6, boolean z) {
        this(new QName(str2, str3, str), str4, str5, str6, z);
    }

    public AttributeImpl() {
        this.fAttributeType = "CDATA";
        init();
    }
}
