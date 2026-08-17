package org.jetbrains.kotlin.fir.resolve.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeTypeMismatch;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "lowerType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "upperType", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getLowerType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getUpperType", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeMismatch implements ConeDiagnostic {
    private final ConeKotlinType lowerType;
    private final ConeKotlinType upperType;

    public ConeTypeMismatch(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        this.lowerType = coneKotlinType;
        this.upperType = coneKotlinType2;
    }

    public final ConeKotlinType getLowerType() {
        return this.lowerType;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return "Type mismatch: expected " + this.upperType + ", actual " + this.lowerType;
    }

    public final ConeKotlinType getUpperType() {
        return this.upperType;
    }
}
