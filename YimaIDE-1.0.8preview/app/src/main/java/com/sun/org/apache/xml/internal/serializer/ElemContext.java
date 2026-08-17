package com.sun.org.apache.xml.internal.serializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ElemContext {
    final int m_currentElemDepth;
    ElemDesc m_elementDesc;
    String m_elementLocalName;
    String m_elementName;
    String m_elementURI;
    boolean m_isCdataSection;
    boolean m_isRaw;
    private ElemContext m_next;
    final ElemContext m_prev;
    boolean m_startTagOpen;

    private ElemContext(ElemContext elemContext) {
        this.m_elementDesc = null;
        this.m_elementLocalName = null;
        this.m_elementName = null;
        this.m_elementURI = null;
        this.m_isRaw = false;
        this.m_startTagOpen = false;
        this.m_prev = elemContext;
        this.m_currentElemDepth = elemContext.m_currentElemDepth + 1;
    }

    public final ElemContext pop() {
        return this.m_prev;
    }

    public final ElemContext push(String str, String str2, String str3) {
        ElemContext elemContext = this.m_next;
        if (elemContext == null) {
            elemContext = new ElemContext(this);
            this.m_next = elemContext;
        }
        elemContext.m_elementName = str3;
        elemContext.m_elementLocalName = str2;
        elemContext.m_elementURI = str;
        elemContext.m_isCdataSection = false;
        elemContext.m_startTagOpen = true;
        return elemContext;
    }

    public final ElemContext push() {
        ElemContext elemContext = this.m_next;
        if (elemContext == null) {
            elemContext = new ElemContext(this);
            this.m_next = elemContext;
        }
        elemContext.m_startTagOpen = true;
        return elemContext;
    }

    public ElemContext() {
        this.m_elementDesc = null;
        this.m_elementLocalName = null;
        this.m_elementName = null;
        this.m_elementURI = null;
        this.m_isRaw = false;
        this.m_startTagOpen = false;
        this.m_prev = this;
        this.m_currentElemDepth = 0;
    }
}
