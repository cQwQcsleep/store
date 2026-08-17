package org.jetbrains.kotlin.descriptors;

import java.util.List;
import org.jetbrains.kotlin.mpp.ClassLikeSymbolMarker;
import org.jetbrains.kotlin.mpp.ClassifierSymbolMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface ClassifierDescriptorWithTypeParameters extends ClassifierDescriptor, DeclarationDescriptorWithVisibility, MemberDescriptor, Substitutable<ClassifierDescriptorWithTypeParameters>, ClassLikeSymbolMarker, ClassifierSymbolMarker {
    List<TypeParameterDescriptor> getDeclaredTypeParameters();

    boolean isInner();
}
