package com.sun.org.apache.xpath.internal;

import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import javax.xml.transform.SourceLocator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XPathFactory {
    XPath create(String str, SourceLocator sourceLocator, PrefixResolver prefixResolver, int i);
}
