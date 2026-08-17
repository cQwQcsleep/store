package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeStubTypeForTypeVariableInSubtyping;", "Lorg/jetbrains/kotlin/fir/types/ConeStubType;", "constructor", "Lorg/jetbrains/kotlin/fir/types/ConeStubTypeConstructor;", "isMarkedNullable", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeStubTypeConstructor;Z)V", "variable", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;", "(Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;Z)V", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeStubTypeForTypeVariableInSubtyping extends ConeStubType {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ConeStubTypeForTypeVariableInSubtyping(ConeTypeVariable coneTypeVariable, boolean z) {
        this(new ConeStubTypeConstructor(coneTypeVariable, true, false, 4, null), z);
        coneTypeVariable.getClass();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeStubTypeForTypeVariableInSubtyping(ConeStubTypeConstructor coneStubTypeConstructor, boolean z) {
        super(coneStubTypeConstructor, z, null);
        coneStubTypeConstructor.getClass();
    }
}
