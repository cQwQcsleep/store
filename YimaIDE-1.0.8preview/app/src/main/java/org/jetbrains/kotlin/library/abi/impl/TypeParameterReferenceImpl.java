package org.jetbrains.kotlin.library.abi.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.library.abi.AbiClassifierReference;
import org.jetbrains.kotlin.library.abi.ExperimentalLibraryAbiReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007Ê\u0001\u0002\b\t¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/impl/TypeParameterReferenceImpl;", "Lorg/jetbrains/kotlin/library/abi/AbiClassifierReference$TypeParameterReference;", "tag", "", "<init>", "(Ljava/lang/String;)V", "getTag", "()Ljava/lang/String;", "org.jetbrains.kotlin:kotlin-util-klib-abi", "Lorg/jetbrains/kotlin/library/abi/ExperimentalLibraryAbiReader;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@ExperimentalLibraryAbiReader
public final class TypeParameterReferenceImpl implements AbiClassifierReference.TypeParameterReference {
    private final String tag;

    public TypeParameterReferenceImpl(String str) {
        str.getClass();
        this.tag = str;
    }

    @Override // org.jetbrains.kotlin.library.abi.AbiClassifierReference.TypeParameterReference
    public String getTag() {
        return this.tag;
    }
}
