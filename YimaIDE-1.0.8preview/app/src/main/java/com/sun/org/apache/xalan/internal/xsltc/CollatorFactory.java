package com.sun.org.apache.xalan.internal.xsltc;

import java.text.Collator;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface CollatorFactory {
    Collator getCollator(String str, String str2);

    Collator getCollator(Locale locale);
}
