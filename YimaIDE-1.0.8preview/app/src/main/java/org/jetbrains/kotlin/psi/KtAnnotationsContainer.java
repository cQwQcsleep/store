package org.jetbrains.kotlin.psi;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface KtAnnotationsContainer extends KtElement {
    List<KtAnnotationEntry> getAnnotationEntries();

    List<KtAnnotation> getAnnotations();
}
