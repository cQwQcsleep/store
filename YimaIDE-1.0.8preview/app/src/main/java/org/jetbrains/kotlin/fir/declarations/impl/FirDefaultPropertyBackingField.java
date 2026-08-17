package org.jetbrains.kotlin.fir.declarations.impl;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/impl/FirDefaultPropertyBackingField;", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirBackingFieldImpl;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "isVar", Argument.Delimiters.none, "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "status", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;Lorg/jetbrains/kotlin/KtSourceElement;Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;ZLorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDefaultPropertyBackingField extends FirBackingFieldImpl {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirDefaultPropertyBackingField(FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, KtSourceElement ktSourceElement, List<FirAnnotation> list, FirTypeRef firTypeRef, boolean z, FirPropertySymbol firPropertySymbol, FirDeclarationStatus firDeclarationStatus, FirResolvePhase firResolvePhase) {
        super(ktSourceElement, firResolvePhase, firModuleData, firDeclarationOrigin, new FirDeclarationAttributes(), firTypeRef, StandardNames.BACKING_FIELD, z, !z, new FirBackingFieldSymbol(), firPropertySymbol, null, FirBuilderDslKt.toMutableOrEmpty(list), firDeclarationStatus, null);
        firModuleData.getClass();
        firDeclarationOrigin.getClass();
        list.getClass();
        firTypeRef.getClass();
        firPropertySymbol.getClass();
        firDeclarationStatus.getClass();
        firResolvePhase.getClass();
    }

    public /* synthetic */ FirDefaultPropertyBackingField(FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, KtSourceElement ktSourceElement, List list, FirTypeRef firTypeRef, boolean z, FirPropertySymbol firPropertySymbol, FirDeclarationStatus firDeclarationStatus, FirResolvePhase firResolvePhase, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firModuleData, firDeclarationOrigin, ktSourceElement, list, firTypeRef, z, firPropertySymbol, firDeclarationStatus, (i & 256) != 0 ? FirResolvePhase.RAW_FIR : firResolvePhase);
    }
}
