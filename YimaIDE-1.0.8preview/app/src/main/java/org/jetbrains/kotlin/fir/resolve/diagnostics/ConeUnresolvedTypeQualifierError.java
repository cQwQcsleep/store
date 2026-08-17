package org.jetbrains.kotlin.fir.resolve.diagnostics;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedTypeQualifierError;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\f¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeUnresolvedTypeQualifierError;", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeUnresolvedError;", "qualifiers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirQualifierPart;", "<init>", "(Ljava/util/List;)V", "getQualifiers", "()Ljava/util/List;", "qualifier", Argument.Delimiters.none, "getQualifier", "()Ljava/lang/String;", "reason", "getReason", "readableDescriptionAsTypeConstructor", "getReadableDescriptionAsTypeConstructor", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeUnresolvedTypeQualifierError implements ConeUnresolvedError {
    private final List<FirQualifierPart> qualifiers;

    /* JADX WARN: Multi-variable type inference failed */
    public ConeUnresolvedTypeQualifierError(List<? extends FirQualifierPart> list) {
        list.getClass();
        this.qualifiers = list;
    }

    public static CharSequence a(FirQualifierPart firQualifierPart) {
        firQualifierPart.getClass();
        String strAsString = firQualifierPart.getName().asString();
        strAsString.getClass();
        return strAsString;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedError
    public String getQualifier() {
        return CollectionsKt.joinToString$default(this.qualifiers, ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: uq2
            public final Object invoke(Object obj) {
                return ConeUnresolvedTypeQualifierError.a((FirQualifierPart) obj);
            }
        }, 30, (Object) null);
    }

    public final List<FirQualifierPart> getQualifiers() {
        return this.qualifiers;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReadableDescriptionAsTypeConstructor() {
        return "Unresolved qualified name: " + getQualifier();
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return "Symbol not found for " + getQualifier();
    }
}
