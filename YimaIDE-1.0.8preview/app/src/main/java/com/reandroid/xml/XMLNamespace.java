package com.reandroid.xml;

import com.reandroid.common.Namespace;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLNamespace implements Namespace, Cloneable {
    private String prefix;
    private String uri;

    public XMLNamespace(String str, String str2) {
        this.uri = str;
        this.prefix = str2;
    }

    public static boolean looksNamespace(String str, String str2) {
        return str2 != null && str.startsWith("xmlns:");
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getUri() {
        return this.uri;
    }

    public boolean isEqual(String str, String str2) {
        return Objects.equals(str, getUri()) && Objects.equals(str2, getPrefix());
    }

    public XMLNamespace newCopy(XMLNode xMLNode) {
        XMLNamespace xMLNamespace = new XMLNamespace(getUri(), getPrefix());
        if (xMLNode instanceof XMLElement) {
            ((XMLElement) xMLNode).addNamespace(xMLNamespace);
        }
        return xMLNamespace;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public void setUri(String str) {
        this.uri = str;
    }
}
