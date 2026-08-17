package org.jetbrains.kotlin.fir.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/diagnostics/ConeCannotInferTypeParameterType;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeCannotInferType;", "typeParameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "reason", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;Ljava/lang/String;)V", "getTypeParameter", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "getReason", "()Ljava/lang/String;", "readableDescriptionAsTypeConstructor", "getReadableDescriptionAsTypeConstructor", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeCannotInferTypeParameterType extends ConeCannotInferType {
    private final String reason;
    private final FirTypeParameterSymbol typeParameter;

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ConeCannotInferTypeParameterType(FirTypeParameterSymbol firTypeParameterSymbol, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            str = "Cannot infer type for parameter " + firTypeParameterSymbol.getName();
        }
        this(firTypeParameterSymbol, str);
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReadableDescriptionAsTypeConstructor() {
        return "Unknown type for type parameter " + this.typeParameter.getName();
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return this.reason;
    }

    public final FirTypeParameterSymbol getTypeParameter() {
        return this.typeParameter;
    }

    public ConeCannotInferTypeParameterType(FirTypeParameterSymbol firTypeParameterSymbol, String str) {
        firTypeParameterSymbol.getClass();
        str.getClass();
        this.typeParameter = firTypeParameterSymbol;
        this.reason = str;
    }
}
