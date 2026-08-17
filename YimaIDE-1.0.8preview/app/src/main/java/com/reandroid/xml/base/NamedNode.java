package com.reandroid.xml.base;

import com.reandroid.common.Namespace;
import com.reandroid.utils.StringsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface NamedNode {
    String getName();

    default String getName(boolean z) {
        String name = getName();
        if (z) {
            String prefix = getPrefix();
            if (!StringsUtil.isEmpty(prefix)) {
                return prefix + ":" + name;
            }
        }
        return name;
    }

    Namespace getNamespace();

    default String getPrefix() {
        Namespace namespace = getNamespace();
        if (namespace != null) {
            return namespace.getPrefix();
        }
        return null;
    }

    default String getUri() {
        Namespace namespace = getNamespace();
        if (namespace != null) {
            return namespace.getUri();
        }
        return null;
    }

    void setName(String str);

    void setNamespace(Namespace namespace);
}
