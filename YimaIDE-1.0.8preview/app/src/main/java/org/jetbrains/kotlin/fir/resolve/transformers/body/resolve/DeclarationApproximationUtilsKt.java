package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.DeclarationApproximationUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.util.AttributeArrayOwner;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aK\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u000b\u001aA\u0010\u0000\u001a\u00020\f*\u00020\f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"approximateDeclarationType", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "holder", "containingCallableVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "isLocal", Argument.Delimiters.none, "isInlineFunction", "stripEnhancedNullability", "approximateLocalTypes", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;Lorg/jetbrains/kotlin/descriptors/Visibility;ZZZZ)Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/descriptors/Visibility;ZZZ)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeclarationApproximationUtilsKt {
    public static boolean a(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        AttributeArrayOwner attributes = coneKotlinType.getAttributes();
        if ((attributes instanceof Collection) && ((Collection) attributes).isEmpty()) {
            return false;
        }
        Iterator it = attributes.iterator();
        while (it.hasNext()) {
            if (!((ConeAttribute) it.next()).getKeepInInferredDeclarationType()) {
                return true;
            }
        }
        return false;
    }

    public static final ConeKotlinType approximateDeclarationType(SessionHolder sessionHolder, ConeKotlinType coneKotlinType, Visibility visibility, boolean z, boolean z2, boolean z3) {
        TypeApproximatorConfiguration.LocalDeclaration localDeclaration;
        sessionHolder.getClass();
        coneKotlinType.getClass();
        if (z) {
            localDeclaration = TypeApproximatorConfiguration.LocalDeclaration.INSTANCE;
        } else if (TypeUtilsKt.shouldApproximateLocalTypesOfNonLocalDeclaration(visibility, z2)) {
            localDeclaration = (z3 || LanguageVersionUtilsKt.isEnabled(sessionHolder, LanguageFeature.ApproximateLocalTypesInPublicDeclarations)) ? TypeApproximatorConfiguration.PublicDeclaration.ApproximateLocalAndAnonymousTypes.INSTANCE : TypeApproximatorConfiguration.PublicDeclaration.ApproximateAnonymousTypes.INSTANCE;
        } else {
            localDeclaration = TypeApproximatorConfiguration.PublicDeclaration.SaveAnonymousTypes.INSTANCE;
        }
        ConeKotlinType coneKotlinTypeApproximateToSuperType = TypeComponentsKt.getTypeApproximator(sessionHolder.getSession()).approximateToSuperType(coneKotlinType, localDeclaration);
        if (coneKotlinTypeApproximateToSuperType != null) {
            coneKotlinType = coneKotlinTypeApproximateToSuperType;
        }
        return ConeTypeUtilsKt.contains(coneKotlinType, new Function1() { // from class: rc3
            public final Object invoke(Object obj) {
                return Boolean.valueOf(DeclarationApproximationUtilsKt.a((ConeKotlinType) obj));
            }
        }) ? new UnnecessaryAttributesRemover(sessionHolder.getSession()).substituteOrSelf(coneKotlinType) : coneKotlinType;
    }

    public static /* synthetic */ FirResolvedTypeRef approximateDeclarationType$default(SessionHolder sessionHolder, FirResolvedTypeRef firResolvedTypeRef, Visibility visibility, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        if ((i & 8) != 0) {
            z2 = false;
        }
        if ((i & 16) != 0) {
            z3 = true;
        }
        if ((i & 32) != 0) {
            z4 = false;
        }
        return approximateDeclarationType(sessionHolder, firResolvedTypeRef, visibility, z, z2, z3, z4);
    }

    public static /* synthetic */ ConeKotlinType approximateDeclarationType$default(SessionHolder sessionHolder, ConeKotlinType coneKotlinType, Visibility visibility, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 8) != 0) {
            z2 = false;
        }
        if ((i & 16) != 0) {
            z3 = false;
        }
        return approximateDeclarationType(sessionHolder, coneKotlinType, visibility, z, z2, z3);
    }

    public static final FirResolvedTypeRef approximateDeclarationType(SessionHolder sessionHolder, FirResolvedTypeRef firResolvedTypeRef, Visibility visibility, boolean z, boolean z2, boolean z3, boolean z4) {
        sessionHolder.getClass();
        firResolvedTypeRef.getClass();
        FirResolvedTypeRef firResolvedTypeRefWithReplacedConeType$default = TypeUtilsKt.withReplacedConeType$default(firResolvedTypeRef, approximateDeclarationType(sessionHolder, firResolvedTypeRef.getConeType(), visibility, z, z2, z4), null, 2, null);
        return z3 ? TypeUtilsKt.withoutEnhancedNullability(firResolvedTypeRefWithReplacedConeType$default) : firResolvedTypeRefWithReplacedConeType$default;
    }
}
