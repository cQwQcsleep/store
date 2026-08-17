package org.jetbrains.kotlin.fir.declarations.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002¨\u0006\u000b"}, d2 = {"createStatus", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirDeclarationStatusImpl;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "effectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "isInline", Argument.Delimiters.none, "isOverride", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDefaultPropertyAccessorKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final FirDeclarationStatusImpl createStatus(Visibility visibility, Modality modality, EffectiveVisibility effectiveVisibility, boolean z, boolean z2) {
        FirDeclarationStatusImpl firResolvedDeclarationStatusImpl;
        if (effectiveVisibility == null) {
            firResolvedDeclarationStatusImpl = new FirDeclarationStatusImpl(visibility, modality);
        } else {
            if (modality == null) {
                modality = Modality.FINAL;
            }
            firResolvedDeclarationStatusImpl = new FirResolvedDeclarationStatusImpl(visibility, modality, effectiveVisibility);
        }
        firResolvedDeclarationStatusImpl.setInline(z);
        firResolvedDeclarationStatusImpl.setOverride(z2);
        return firResolvedDeclarationStatusImpl;
    }
}
