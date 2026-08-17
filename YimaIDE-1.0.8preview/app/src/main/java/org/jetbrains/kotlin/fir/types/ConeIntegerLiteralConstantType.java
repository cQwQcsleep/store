package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeIntegerLiteralConstantType;", "Lorg/jetbrains/kotlin/fir/types/ConeIntegerLiteralType;", "value", "", "isUnsigned", "", "isMarkedNullable", "<init>", "(JZZ)V", "getValue", "()J", "org.jetbrains.kotlin:cones"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ConeIntegerLiteralConstantType extends ConeIntegerLiteralType {
    private final long value;

    public ConeIntegerLiteralConstantType(long j, boolean z, boolean z2) {
        super(z, z2, (DefaultConstructorMarker) null);
        this.value = j;
    }

    public final long getValue() {
        return this.value;
    }
}
