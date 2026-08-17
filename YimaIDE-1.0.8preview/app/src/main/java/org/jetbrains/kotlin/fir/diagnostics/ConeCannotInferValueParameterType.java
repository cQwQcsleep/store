package org.jetbrains.kotlin.fir.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000f¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/diagnostics/ConeCannotInferValueParameterType;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeCannotInferType;", "valueParameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "reason", Argument.Delimiters.none, "isTopLevelLambda", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;Ljava/lang/String;Z)V", "getValueParameter", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "()Z", "_reason", "getReason", "()Ljava/lang/String;", "readableDescriptionAsTypeConstructor", "getReadableDescriptionAsTypeConstructor", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeCannotInferValueParameterType extends ConeCannotInferType {
    private final String _reason;
    private final boolean isTopLevelLambda;
    private final FirValueParameterSymbol valueParameter;

    public /* synthetic */ ConeCannotInferValueParameterType(FirValueParameterSymbol firValueParameterSymbol, String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firValueParameterSymbol, (i & 2) != 0 ? null : str, (i & 4) != 0 ? false : z);
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReadableDescriptionAsTypeConstructor() {
        FirValueParameterSymbol firValueParameterSymbol = this.valueParameter;
        return "Unknown type for value parameter ".concat(firValueParameterSymbol != null ? String.valueOf(firValueParameterSymbol.getName()) : "it");
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        String str = this._reason;
        if (str != null) {
            return str;
        }
        FirValueParameterSymbol firValueParameterSymbol = this.valueParameter;
        return "Cannot infer type for parameter ".concat(firValueParameterSymbol != null ? String.valueOf(firValueParameterSymbol.getName()) : "it");
    }

    public final FirValueParameterSymbol getValueParameter() {
        return this.valueParameter;
    }

    /* JADX INFO: renamed from: isTopLevelLambda, reason: from getter */
    public final boolean getIsTopLevelLambda() {
        return this.isTopLevelLambda;
    }

    public ConeCannotInferValueParameterType(FirValueParameterSymbol firValueParameterSymbol, String str, boolean z) {
        this.valueParameter = firValueParameterSymbol;
        this.isTopLevelLambda = z;
        this._reason = str;
    }
}
