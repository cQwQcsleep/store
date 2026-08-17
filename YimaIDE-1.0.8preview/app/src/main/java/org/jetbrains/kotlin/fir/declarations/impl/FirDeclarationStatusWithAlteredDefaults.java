package org.jetbrains.kotlin.fir.declarations.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tB3\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\b\u0010\fJ \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0006\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/impl/FirDeclarationStatusWithAlteredDefaults;", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirDeclarationStatusImpl;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "defaultVisibility", "defaultModality", "<init>", "(Lorg/jetbrains/kotlin/descriptors/Visibility;Lorg/jetbrains/kotlin/descriptors/Modality;Lorg/jetbrains/kotlin/descriptors/Visibility;Lorg/jetbrains/kotlin/descriptors/Modality;)V", "flags", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/descriptors/Visibility;Lorg/jetbrains/kotlin/descriptors/Modality;Lorg/jetbrains/kotlin/descriptors/Visibility;Lorg/jetbrains/kotlin/descriptors/Modality;I)V", "getDefaultVisibility", "()Lorg/jetbrains/kotlin/descriptors/Visibility;", "getDefaultModality", "()Lorg/jetbrains/kotlin/descriptors/Modality;", "resolved", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirResolvedDeclarationStatusImpl;", "effectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeclarationStatusWithAlteredDefaults extends FirDeclarationStatusImpl {
    private final Modality defaultModality;
    private final Visibility defaultVisibility;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirDeclarationStatusWithAlteredDefaults(Visibility visibility, Modality modality, Visibility visibility2, Modality modality2) {
        super(visibility, modality);
        visibility.getClass();
        visibility2.getClass();
        modality2.getClass();
        this.defaultVisibility = visibility2;
        this.defaultModality = modality2;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl, org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public Modality getDefaultModality() {
        return this.defaultModality;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl, org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public Visibility getDefaultVisibility() {
        return this.defaultVisibility;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl
    public FirResolvedDeclarationStatusImpl resolved(Visibility visibility, Modality modality, EffectiveVisibility effectiveVisibility) {
        visibility.getClass();
        modality.getClass();
        effectiveVisibility.getClass();
        return new FirResolvedDeclarationStatusWithAlteredDefaults(visibility, modality, getDefaultVisibility(), getDefaultModality(), effectiveVisibility, getFlags());
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FirDeclarationStatusWithAlteredDefaults(Visibility visibility, Modality modality, Visibility visibility2, Modality modality2, int i) {
        this(visibility, modality, visibility2, modality2);
        visibility.getClass();
        visibility2.getClass();
        modality2.getClass();
        setFlags(i);
    }
}
