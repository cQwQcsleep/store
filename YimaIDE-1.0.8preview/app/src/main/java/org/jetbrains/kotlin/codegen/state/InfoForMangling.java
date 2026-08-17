package org.jetbrains.kotlin.codegen.state;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqNameUnsafe;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/state/InfoForMangling;", Argument.Delimiters.none, "fqName", "Lorg/jetbrains/kotlin/name/FqNameUnsafe;", "isValue", Argument.Delimiters.none, "isNullable", "<init>", "(Lorg/jetbrains/kotlin/name/FqNameUnsafe;ZZ)V", "getFqName", "()Lorg/jetbrains/kotlin/name/FqNameUnsafe;", "()Z", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InfoForMangling {
    private final FqNameUnsafe fqName;
    private final boolean isNullable;
    private final boolean isValue;

    public InfoForMangling(FqNameUnsafe fqNameUnsafe, boolean z, boolean z2) {
        fqNameUnsafe.getClass();
        this.fqName = fqNameUnsafe;
        this.isValue = z;
        this.isNullable = z2;
    }

    public final FqNameUnsafe getFqName() {
        return this.fqName;
    }

    /* JADX INFO: renamed from: isNullable, reason: from getter */
    public final boolean getIsNullable() {
        return this.isNullable;
    }

    /* JADX INFO: renamed from: isValue, reason: from getter */
    public final boolean getIsValue() {
        return this.isValue;
    }
}
