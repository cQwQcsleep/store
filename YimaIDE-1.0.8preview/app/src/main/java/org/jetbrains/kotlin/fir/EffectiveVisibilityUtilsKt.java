package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.EffectiveVisibilityKt;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u0006¨\u0006\u000b"}, d2 = {"toEffectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "ownerSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "forClass", Argument.Delimiters.none, "checkPublishedApi", "owner", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "ownerIsPublishedApi", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EffectiveVisibilityUtilsKt {
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final EffectiveVisibility toEffectiveVisibility(Visibility visibility, ConeClassLikeLookupTag coneClassLikeLookupTag, boolean z, boolean z2) throws KotlinIllegalArgumentExceptionWithAttachments {
        visibility.getClass();
        EffectiveVisibility effectiveVisibilityOrNull = EffectiveVisibilityKt.toEffectiveVisibilityOrNull(visibility, coneClassLikeLookupTag, z, z2);
        if (effectiveVisibilityOrNull != null) {
            return effectiveVisibilityOrNull;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unknown visibility: " + visibility, (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirLookupTagEntry(exceptionAttachmentBuilder, "owner", coneClassLikeLookupTag);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public static /* synthetic */ EffectiveVisibility toEffectiveVisibility$default(Visibility visibility, FirClassLikeSymbol firClassLikeSymbol, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return toEffectiveVisibility(visibility, (FirClassLikeSymbol<?>) firClassLikeSymbol, z, z2);
    }

    public static /* synthetic */ EffectiveVisibility toEffectiveVisibility$default(Visibility visibility, ConeClassLikeLookupTag coneClassLikeLookupTag, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return toEffectiveVisibility(visibility, coneClassLikeLookupTag, z, z2);
    }

    public static final EffectiveVisibility toEffectiveVisibility(Visibility visibility, FirClassLikeSymbol<?> firClassLikeSymbol, boolean z, boolean z2) {
        visibility.getClass();
        return toEffectiveVisibility(visibility, firClassLikeSymbol != null ? firClassLikeSymbol.getLookupTag() : null, z, z2);
    }
}
