package org.jetbrains.kotlin.load.kotlin;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u0086\u0002J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005H\u0086\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder$Result$KotlinClass;", "Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder$Result;", "kotlinJvmBinaryClass", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass;", "byteContent", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass;[B)V", "getKotlinJvmBinaryClass", "()Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass;", "getByteContent", "()[B", "component1", "component2", "org.jetbrains.kotlin:deserialization.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinClassFinder$Result$KotlinClass extends KotlinClassFinder.Result {
    private final byte[] byteContent;
    private final KotlinJvmBinaryClass kotlinJvmBinaryClass;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinClassFinder$Result$KotlinClass(KotlinJvmBinaryClass kotlinJvmBinaryClass, byte[] bArr) {
        super((DefaultConstructorMarker) null);
        kotlinJvmBinaryClass.getClass();
        this.kotlinJvmBinaryClass = kotlinJvmBinaryClass;
        this.byteContent = bArr;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final KotlinJvmBinaryClass getKotlinJvmBinaryClass() {
        return this.kotlinJvmBinaryClass;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final byte[] getByteContent() {
        return this.byteContent;
    }

    public final byte[] getByteContent() {
        return this.byteContent;
    }

    public final KotlinJvmBinaryClass getKotlinJvmBinaryClass() {
        return this.kotlinJvmBinaryClass;
    }

    public /* synthetic */ KotlinClassFinder$Result$KotlinClass(KotlinJvmBinaryClass kotlinJvmBinaryClass, byte[] bArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(kotlinJvmBinaryClass, (i & 2) != 0 ? null : bArr);
    }
}
