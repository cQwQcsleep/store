package org.w3c.dom;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DOMConfiguration {
    boolean canSetParameter(String str, Object obj);

    Object getParameter(String str) throws DOMException;

    DOMStringList getParameterNames();

    void setParameter(String str, Object obj) throws DOMException;
}
