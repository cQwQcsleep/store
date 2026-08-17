package com.sun.org.apache.xml.internal.serialize;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
public class ElementState {
    public boolean afterComment;
    public boolean afterElement;
    public boolean doCData;
    public boolean empty;
    public boolean inCData;
    public String localName;
    public String namespaceURI;
    public Map<String, String> prefixes;
    public boolean preserveSpace;
    public String rawName;
    public boolean unescaped;
}
