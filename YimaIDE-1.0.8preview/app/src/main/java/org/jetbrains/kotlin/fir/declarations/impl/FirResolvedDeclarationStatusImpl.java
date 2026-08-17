package org.jetbrains.kotlin.fir.declarations.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\b\u0016\u0018\u0000 \u00122\u00020\u00012\u00020\u0002:\u0001\u0012B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nB)\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\t\u0010\rR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/impl/FirResolvedDeclarationStatusImpl;", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirDeclarationStatusImpl;", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "effectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/Visibility;Lorg/jetbrains/kotlin/descriptors/Modality;Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;)V", "flags", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/descriptors/Visibility;Lorg/jetbrains/kotlin/descriptors/Modality;Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;I)V", "getEffectiveVisibility", "()Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "getModality", "()Lorg/jetbrains/kotlin/descriptors/Modality;", "Companion", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirResolvedDeclarationStatusImpl extends FirDeclarationStatusImpl implements FirResolvedDeclarationStatus {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final FirResolvedDeclarationStatus DEFAULT_STATUS_FOR_STATUSLESS_DECLARATIONS = new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL, EffectiveVisibility.Public.INSTANCE);
    private final EffectiveVisibility effectiveVisibility;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FirResolvedDeclarationStatusImpl(Visibility visibility, Modality modality, EffectiveVisibility effectiveVisibility, int i) {
        this(visibility, modality, effectiveVisibility);
        visibility.getClass();
        modality.getClass();
        effectiveVisibility.getClass();
        setFlags(i);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus
    public EffectiveVisibility getEffectiveVisibility() {
        return this.effectiveVisibility;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl, org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
    public Modality getModality() {
        Modality modality = super.getModality();
        modality.getClass();
        return modality;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/impl/FirResolvedDeclarationStatusImpl$Companion;", Argument.Delimiters.none, "<init>", "()V", "DEFAULT_STATUS_FOR_STATUSLESS_DECLARATIONS", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "getDEFAULT_STATUS_FOR_STATUSLESS_DECLARATIONS", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirResolvedDeclarationStatus getDEFAULT_STATUS_FOR_STATUSLESS_DECLARATIONS() {
            return FirResolvedDeclarationStatusImpl.DEFAULT_STATUS_FOR_STATUSLESS_DECLARATIONS;
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirResolvedDeclarationStatusImpl(Visibility visibility, Modality modality, EffectiveVisibility effectiveVisibility) {
        super(visibility, modality);
        visibility.getClass();
        modality.getClass();
        effectiveVisibility.getClass();
        this.effectiveVisibility = effectiveVisibility;
    }
}
