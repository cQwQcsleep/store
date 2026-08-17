package org.jetbrains.kotlin.fir.declarations.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"modifiersRepresentation", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "getModifiersRepresentation$annotations", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;)V", "getModifiersRepresentation", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;)I", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeclarationStatusImplKt {
    public static final int getModifiersRepresentation(FirDeclarationStatus firDeclarationStatus) {
        firDeclarationStatus.getClass();
        if (firDeclarationStatus instanceof FirDeclarationStatusImpl) {
            return ((FirDeclarationStatusImpl) firDeclarationStatus).getRawFlags$org_jetbrains_kotlin_tree();
        }
        b88.a("Generating modifier representations for ", Reflection.getOrCreateKotlinClass(firDeclarationStatus.getClass()).getSimpleName(), " is not supported");
        return 0;
    }

    public static /* synthetic */ void getModifiersRepresentation$annotations(FirDeclarationStatus firDeclarationStatus) {
    }
}
