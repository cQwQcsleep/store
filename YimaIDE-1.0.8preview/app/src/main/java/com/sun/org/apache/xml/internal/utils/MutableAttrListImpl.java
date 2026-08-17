package com.sun.org.apache.xml.internal.utils;

import java.io.Serializable;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MutableAttrListImpl extends AttributesImpl implements Serializable {
    static final long serialVersionUID = 6289452013442934470L;

    public MutableAttrListImpl() {
    }

    @Override // org.xml.sax.helpers.AttributesImpl
    public void addAttribute(String str, String str2, String str3, String str4, String str5) {
        if (str == null) {
            str = "";
        }
        String str6 = str;
        int index = getIndex(str3);
        if (index >= 0) {
            setAttribute(index, str6, str2, str3, str4, str5);
        } else {
            super.addAttribute(str6, str2, str3, str4, str5);
        }
    }

    public void addAttributes(Attributes attributes) {
        MutableAttrListImpl mutableAttrListImpl;
        int length = attributes.getLength();
        int i = 0;
        while (i < length) {
            String uri = attributes.getURI(i);
            if (uri == null) {
                uri = "";
            }
            String str = uri;
            String localName = attributes.getLocalName(i);
            String qName = attributes.getQName(i);
            int index = this.getIndex(str, localName);
            if (index >= 0) {
                mutableAttrListImpl = this;
                mutableAttrListImpl.setAttribute(index, str, localName, qName, attributes.getType(i), attributes.getValue(i));
            } else {
                mutableAttrListImpl = this;
                mutableAttrListImpl.addAttribute(str, localName, qName, attributes.getType(i), attributes.getValue(i));
            }
            i++;
            this = mutableAttrListImpl;
        }
    }

    public boolean contains(String str) {
        return getValue(str) != null;
    }

    public MutableAttrListImpl(Attributes attributes) {
        super(attributes);
    }
}
