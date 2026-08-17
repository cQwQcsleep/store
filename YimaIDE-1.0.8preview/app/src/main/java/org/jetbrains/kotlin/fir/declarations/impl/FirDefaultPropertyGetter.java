package org.jetbrains.kotlin.fir.declarations.impl;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001BW\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015B\u0083\u0001\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001d\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u001d\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u001f¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/impl/FirDefaultPropertyGetter;", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirDefaultPropertyAccessor;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "propertyTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "status", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertyAccessorSymbol;", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertyAccessorSymbol;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;)V", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "effectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "isInline", Argument.Delimiters.none, "isOverride", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/descriptors/Visibility;Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lorg/jetbrains/kotlin/descriptors/Modality;Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;ZZLorg/jetbrains/kotlin/fir/symbols/impl/FirPropertyAccessorSymbol;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;)V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDefaultPropertyGetter extends FirDefaultPropertyAccessor {
    public /* synthetic */ FirDefaultPropertyGetter(KtSourceElement ktSourceElement, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirTypeRef firTypeRef, Visibility visibility, FirPropertySymbol firPropertySymbol, Modality modality, EffectiveVisibility effectiveVisibility, boolean z, boolean z2, FirPropertyAccessorSymbol firPropertyAccessorSymbol, FirResolvePhase firResolvePhase, FirDeclarationAttributes firDeclarationAttributes, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, firModuleData, firDeclarationOrigin, firTypeRef, visibility, firPropertySymbol, modality, (i & 128) != 0 ? null : effectiveVisibility, (i & 256) != 0 ? false : z, (i & 512) != 0 ? false : z2, (i & 1024) != 0 ? new FirPropertyAccessorSymbol() : firPropertyAccessorSymbol, (i & 2048) != 0 ? FirResolvePhase.RAW_FIR : firResolvePhase, (i & 4096) != 0 ? new FirDeclarationAttributes() : firDeclarationAttributes);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirDefaultPropertyGetter(KtSourceElement ktSourceElement, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirTypeRef firTypeRef, FirPropertySymbol firPropertySymbol, FirDeclarationStatus firDeclarationStatus, FirPropertyAccessorSymbol firPropertyAccessorSymbol, FirResolvePhase firResolvePhase, FirDeclarationAttributes firDeclarationAttributes) {
        super(ktSourceElement, firModuleData, firDeclarationOrigin, firTypeRef, new ArrayList(), firPropertySymbol, true, firDeclarationStatus, firPropertyAccessorSymbol, firResolvePhase, firDeclarationAttributes);
        firModuleData.getClass();
        firDeclarationOrigin.getClass();
        firTypeRef.getClass();
        firPropertySymbol.getClass();
        firDeclarationStatus.getClass();
        firPropertyAccessorSymbol.getClass();
        firResolvePhase.getClass();
        firDeclarationAttributes.getClass();
    }

    public /* synthetic */ FirDefaultPropertyGetter(KtSourceElement ktSourceElement, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirTypeRef firTypeRef, FirPropertySymbol firPropertySymbol, FirDeclarationStatus firDeclarationStatus, FirPropertyAccessorSymbol firPropertyAccessorSymbol, FirResolvePhase firResolvePhase, FirDeclarationAttributes firDeclarationAttributes, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, firModuleData, firDeclarationOrigin, firTypeRef, firPropertySymbol, firDeclarationStatus, (i & 64) != 0 ? new FirPropertyAccessorSymbol() : firPropertyAccessorSymbol, (i & 128) != 0 ? FirResolvePhase.RAW_FIR : firResolvePhase, (i & 256) != 0 ? new FirDeclarationAttributes() : firDeclarationAttributes);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FirDefaultPropertyGetter(KtSourceElement ktSourceElement, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirTypeRef firTypeRef, Visibility visibility, FirPropertySymbol firPropertySymbol, Modality modality, EffectiveVisibility effectiveVisibility, boolean z, boolean z2, FirPropertyAccessorSymbol firPropertyAccessorSymbol, FirResolvePhase firResolvePhase, FirDeclarationAttributes firDeclarationAttributes) {
        this(ktSourceElement, firModuleData, firDeclarationOrigin, firTypeRef, firPropertySymbol, FirDefaultPropertyAccessorKt.createStatus(visibility, modality, effectiveVisibility, z, z2), firPropertyAccessorSymbol, firResolvePhase, firDeclarationAttributes);
        firModuleData.getClass();
        firDeclarationOrigin.getClass();
        firTypeRef.getClass();
        visibility.getClass();
        firPropertySymbol.getClass();
        firPropertyAccessorSymbol.getClass();
        firResolvePhase.getClass();
        firDeclarationAttributes.getClass();
    }
}
