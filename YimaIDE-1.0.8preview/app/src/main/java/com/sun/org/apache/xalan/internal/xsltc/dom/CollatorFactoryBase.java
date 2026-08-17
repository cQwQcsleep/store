package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.CollatorFactory;
import java.text.Collator;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CollatorFactoryBase implements CollatorFactory {
    public static final Locale DEFAULT_LOCALE = Locale.getDefault();
    public static final Collator DEFAULT_COLLATOR = Collator.getInstance();

    @Override // com.sun.org.apache.xalan.internal.xsltc.CollatorFactory
    public Collator getCollator(Locale locale) {
        return locale == DEFAULT_LOCALE ? DEFAULT_COLLATOR : Collator.getInstance(locale);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.CollatorFactory
    public Collator getCollator(String str, String str2) {
        return Collator.getInstance(new Locale(str, str2));
    }
}
