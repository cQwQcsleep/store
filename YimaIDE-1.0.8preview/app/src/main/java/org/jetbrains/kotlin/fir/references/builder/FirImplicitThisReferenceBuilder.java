package org.jetbrains.kotlin.fir.references.builder;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.references.impl.FirImplicitThisReference;
import org.jetbrains.kotlin.fir.symbols.impl.FirThisOwnerSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0010\u001a\u00020\u0011R \u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fÊ\u0001\u0002\b\u0013¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/references/builder/FirImplicitThisReferenceBuilder;", Argument.Delimiters.none, "<init>", "()V", "boundSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirThisOwnerSymbol;", "getBoundSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirThisOwnerSymbol;", "setBoundSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirThisOwnerSymbol;)V", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "setDiagnostic", "(Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;)V", "build", "Lorg/jetbrains/kotlin/fir/references/FirThisReference;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirImplicitThisReferenceBuilder {
    private FirThisOwnerSymbol<?> boundSymbol;
    private ConeDiagnostic diagnostic;

    public final FirThisReference build() {
        return new FirImplicitThisReference(this.boundSymbol, this.diagnostic);
    }

    public final FirThisOwnerSymbol<?> getBoundSymbol() {
        return this.boundSymbol;
    }

    public final ConeDiagnostic getDiagnostic() {
        return this.diagnostic;
    }

    public final void setBoundSymbol(FirThisOwnerSymbol<?> firThisOwnerSymbol) {
        this.boundSymbol = firThisOwnerSymbol;
    }

    public final void setDiagnostic(ConeDiagnostic coneDiagnostic) {
        this.diagnostic = coneDiagnostic;
    }
}
