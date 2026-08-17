package org.jetbrains.kotlin.fir.declarations.impl;

import kotlin.Lazy;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bR&\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006X\u0087\u0004r\u0002\b\u0010¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\t8VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/impl/FirResolvedDeclarationStatusWithLazyEffectiveVisibility;", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirDeclarationStatusImpl;", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "lazyEffectiveVisibility", "Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/Visibility;Lorg/jetbrains/kotlin/descriptors/Modality;Lkotlin/Lazy;)V", "getLazyEffectiveVisibility$annotations", "()V", "getLazyEffectiveVisibility", "()Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "effectiveVisibility", "getEffectiveVisibility$annotations", "getEffectiveVisibility", "()Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "getModality", "()Lorg/jetbrains/kotlin/descriptors/Modality;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirResolvedDeclarationStatusWithLazyEffectiveVisibility extends FirDeclarationStatusImpl implements FirResolvedDeclarationStatus {
    private final Lazy<EffectiveVisibility> lazyEffectiveVisibility;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FirResolvedDeclarationStatusWithLazyEffectiveVisibility(Visibility visibility, Modality modality, Lazy<? extends EffectiveVisibility> lazy) {
        super(visibility, modality);
        visibility.getClass();
        modality.getClass();
        lazy.getClass();
        this.lazyEffectiveVisibility = lazy;
    }

    public static /* synthetic */ void getEffectiveVisibility$annotations() {
    }

    @FirImplementationDetail
    public static /* synthetic */ void getLazyEffectiveVisibility$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus
    public EffectiveVisibility getEffectiveVisibility() {
        return (EffectiveVisibility) this.lazyEffectiveVisibility.getValue();
    }

    public final Lazy<EffectiveVisibility> getLazyEffectiveVisibility() {
        return this.lazyEffectiveVisibility;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl, org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public Modality getModality() {
        Modality modality = super.getModality();
        modality.getClass();
        return modality;
    }
}
