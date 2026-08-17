package org.jetbrains.kotlin.fir.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/diagnostics/ConeCannotInferReceiverParameterType;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeCannotInferType;", "reason", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "readableDescriptionAsTypeConstructor", "getReadableDescriptionAsTypeConstructor", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeCannotInferReceiverParameterType extends ConeCannotInferType {
    private final String reason;

    public /* synthetic */ ConeCannotInferReceiverParameterType(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "Cannot infer type for receiver parameter" : str);
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReadableDescriptionAsTypeConstructor() {
        return "Unknown type for receiver parameter";
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return this.reason;
    }

    public ConeCannotInferReceiverParameterType(String str) {
        str.getClass();
        this.reason = str;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ConeCannotInferReceiverParameterType() {
        String str = null;
        this(str, 1, str);
    }
}
