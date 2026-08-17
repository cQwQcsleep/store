package org.jdom2;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface NamespaceAware {
    List<Namespace> getNamespacesInScope();

    List<Namespace> getNamespacesInherited();

    List<Namespace> getNamespacesIntroduced();
}
