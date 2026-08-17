package com.sun.org.apache.xerces.internal.xs;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface PSVIProvider {
    AttributePSVI getAttributePSVI(int i);

    AttributePSVI getAttributePSVIByName(String str, String str2);

    ElementPSVI getElementPSVI();
}
