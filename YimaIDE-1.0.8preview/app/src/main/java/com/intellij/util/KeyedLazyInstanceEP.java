package com.intellij.util;

import com.intellij.serviceContainer.BaseKeyedLazyInstance;
import com.intellij.util.xmlb.annotations.Attribute;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class KeyedLazyInstanceEP<T> extends BaseKeyedLazyInstance<T> implements KeyedLazyInstance<T> {

    @Attribute("implementationClass")
    public String implementationClass;

    @Attribute("key")
    public String key;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/intellij/util/KeyedLazyInstanceEP", "getKey"));
    }

    public String getImplementationClassName() {
        return this.implementationClass;
    }

    public String getKey() {
        String str = this.key;
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        return str;
    }
}
