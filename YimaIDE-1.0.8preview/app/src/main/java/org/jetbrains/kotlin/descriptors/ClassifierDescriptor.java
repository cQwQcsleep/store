package org.jetbrains.kotlin.descriptors;

import org.jetbrains.kotlin.mpp.ClassifierSymbolMarker;
import org.jetbrains.kotlin.types.SimpleType;
import org.jetbrains.kotlin.types.TypeConstructor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface ClassifierDescriptor extends DeclarationDescriptorNonRoot, ClassifierSymbolMarker {
    SimpleType getDefaultType();

    @Override // 
    /* JADX INFO: renamed from: getOriginal, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    ClassifierDescriptor mo86getOriginal();

    TypeConstructor getTypeConstructor();
}
