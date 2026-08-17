package org.jetbrains.kotlin.fir.diagnostics;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/diagnostics/ConeAmbiguousSuper;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "candidateTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Ljava/util/List;)V", "getCandidateTypes", "()Ljava/util/List;", "reason", Argument.Delimiters.none, "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeAmbiguousSuper implements ConeDiagnostic {
    private final List<ConeKotlinType> candidateTypes;

    public ConeAmbiguousSuper(List<? extends ConeKotlinType> list) {
        list.getClass();
        this.candidateTypes = list;
    }

    public final List<ConeKotlinType> getCandidateTypes() {
        return this.candidateTypes;
    }

    @Override // org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
    public String getReason() {
        return "Ambiguous supertype";
    }
}
