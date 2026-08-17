package com.reandroid.arsc.chunk.xml;

import com.reandroid.common.Namespace;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ResXmlNamespace extends Namespace {
    int getLineNumber();

    int getUriReference();

    boolean isUnused();

    void setLineNumber(int i);

    void setPrefix(String str);

    void setUri(String str);
}
