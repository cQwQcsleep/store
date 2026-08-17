package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import org.xml.sax.AttributeList;
import org.xml.sax.ext.Attributes2;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class AttributesProxy implements AttributeList, Attributes2 {
    private XMLAttributes fAttributes;

    public AttributesProxy(XMLAttributes xMLAttributes) {
        this.fAttributes = xMLAttributes;
    }

    public XMLAttributes getAttributes() {
        return this.fAttributes;
    }

    @Override // org.xml.sax.Attributes
    public int getIndex(String str, String str2) {
        boolean zEquals = str.equals(XMLSymbols.EMPTY_STRING);
        XMLAttributes xMLAttributes = this.fAttributes;
        return zEquals ? xMLAttributes.getIndex(null, str2) : xMLAttributes.getIndex(str, str2);
    }

    @Override // org.xml.sax.AttributeList, org.xml.sax.Attributes
    public int getLength() {
        return this.fAttributes.getLength();
    }

    @Override // org.xml.sax.Attributes
    public String getLocalName(int i) {
        return this.fAttributes.getLocalName(i);
    }

    @Override // org.xml.sax.AttributeList
    public String getName(int i) {
        return this.fAttributes.getQName(i);
    }

    @Override // org.xml.sax.Attributes
    public String getQName(int i) {
        return this.fAttributes.getQName(i);
    }

    @Override // org.xml.sax.Attributes
    public String getType(String str, String str2) {
        boolean zEquals = str.equals(XMLSymbols.EMPTY_STRING);
        XMLAttributes xMLAttributes = this.fAttributes;
        return zEquals ? xMLAttributes.getType(null, str2) : xMLAttributes.getType(str, str2);
    }

    @Override // org.xml.sax.Attributes
    public String getURI(int i) {
        String uri = this.fAttributes.getURI(i);
        return uri != null ? uri : XMLSymbols.EMPTY_STRING;
    }

    @Override // org.xml.sax.Attributes
    public String getValue(String str, String str2) {
        boolean zEquals = str.equals(XMLSymbols.EMPTY_STRING);
        XMLAttributes xMLAttributes = this.fAttributes;
        return zEquals ? xMLAttributes.getValue(null, str2) : xMLAttributes.getValue(str, str2);
    }

    @Override // org.xml.sax.ext.Attributes2
    public boolean isDeclared(int i) {
        if (i >= 0 && i < this.fAttributes.getLength()) {
            return Boolean.TRUE.equals(this.fAttributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_DECLARED));
        }
        y0e.a(i);
        return false;
    }

    @Override // org.xml.sax.ext.Attributes2
    public boolean isSpecified(int i) {
        if (i >= 0 && i < this.fAttributes.getLength()) {
            return this.fAttributes.isSpecified(i);
        }
        y0e.a(i);
        return false;
    }

    public void setAttributes(XMLAttributes xMLAttributes) {
        this.fAttributes = xMLAttributes;
    }

    @Override // org.xml.sax.Attributes
    public int getIndex(String str) {
        return this.fAttributes.getIndex(str);
    }

    @Override // org.xml.sax.AttributeList, org.xml.sax.Attributes
    public String getType(String str) {
        return this.fAttributes.getType(str);
    }

    @Override // org.xml.sax.AttributeList, org.xml.sax.Attributes
    public String getValue(String str) {
        return this.fAttributes.getValue(str);
    }

    @Override // org.xml.sax.AttributeList, org.xml.sax.Attributes
    public String getType(int i) {
        return this.fAttributes.getType(i);
    }

    @Override // org.xml.sax.AttributeList, org.xml.sax.Attributes
    public String getValue(int i) {
        return this.fAttributes.getValue(i);
    }

    @Override // org.xml.sax.ext.Attributes2
    public boolean isSpecified(String str) {
        int index = getIndex(str);
        if (index != -1) {
            return this.fAttributes.isSpecified(index);
        }
        w01.a(str);
        return false;
    }

    @Override // org.xml.sax.ext.Attributes2
    public boolean isSpecified(String str, String str2) {
        int index = getIndex(str, str2);
        if (index != -1) {
            return this.fAttributes.isSpecified(index);
        }
        w01.a(str2);
        return false;
    }

    @Override // org.xml.sax.ext.Attributes2
    public boolean isDeclared(String str) {
        int index = getIndex(str);
        if (index != -1) {
            return Boolean.TRUE.equals(this.fAttributes.getAugmentations(index).getItem(Constants.ATTRIBUTE_DECLARED));
        }
        w01.a(str);
        return false;
    }

    @Override // org.xml.sax.ext.Attributes2
    public boolean isDeclared(String str, String str2) {
        int index = getIndex(str, str2);
        if (index != -1) {
            return Boolean.TRUE.equals(this.fAttributes.getAugmentations(index).getItem(Constants.ATTRIBUTE_DECLARED));
        }
        w01.a(str2);
        return false;
    }
}
