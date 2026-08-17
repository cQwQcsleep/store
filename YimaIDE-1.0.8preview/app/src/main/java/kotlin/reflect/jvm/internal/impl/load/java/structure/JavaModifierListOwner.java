package kotlin.reflect.jvm.internal.impl.load.java.structure;

import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface JavaModifierListOwner extends JavaElement {
    Visibility getVisibility();

    boolean isAbstract();

    boolean isFinal();

    boolean isStatic();
}
