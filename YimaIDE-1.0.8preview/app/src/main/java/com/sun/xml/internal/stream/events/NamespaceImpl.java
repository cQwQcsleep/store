package com.sun.xml.internal.stream.events;

import javax.xml.namespace.QName;
import javax.xml.stream.events.Namespace;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NamespaceImpl extends AttributeImpl implements Namespace {
    public NamespaceImpl(String str) {
        super("xmlns", "http://www.w3.org/2000/xmlns/", "", str, (String) null);
        init();
    }

    @Override // com.sun.xml.internal.stream.events.DummyEvent
    public int getEventType() {
        return 13;
    }

    public String getNamespaceURI() {
        return getValue();
    }

    public String getPrefix() {
        QName name = getName();
        if (name != null) {
            return name.getLocalPart();
        }
        return null;
    }

    @Override // com.sun.xml.internal.stream.events.AttributeImpl
    public void init() {
        setEventType(13);
    }

    public boolean isDefaultNamespaceDeclaration() {
        QName name = getName();
        return name != null && name.getLocalPart().equals("");
    }

    @Override // com.sun.xml.internal.stream.events.DummyEvent
    public boolean isNamespace() {
        return true;
    }

    public void setNamespaceURI(String str) {
        setValue(str);
    }

    public void setPrefix(String str) {
        if (str == null) {
            setName(new QName("http://www.w3.org/2000/xmlns/", "", "xmlns"));
        } else {
            setName(new QName("http://www.w3.org/2000/xmlns/", str, "xmlns"));
        }
    }

    public NamespaceImpl() {
        init();
    }

    public NamespaceImpl(String str, String str2) {
        super("xmlns", "http://www.w3.org/2000/xmlns/", str, str2, (String) null);
        init();
    }
}
