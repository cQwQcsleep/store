package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\n\u0010\u0005\u001a\u00020\u0006H\u0096\u0080\u0004R\u0015\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00018F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousInitializerSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "<init>", "()V", "toString", Argument.Delimiters.none, "containingDeclarationSymbol", "getContainingDeclarationSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnonymousInitializerSymbol extends FirBasedSymbol<FirAnonymousInitializer> {
    public final FirBasedSymbol<?> getContainingDeclarationSymbol() {
        return getFir().getContainingDeclarationSymbol();
    }

    public String toString() {
        return Reflection.getOrCreateKotlinClass(FirAnonymousInitializerSymbol.class).getSimpleName() + " <init>";
    }
}
