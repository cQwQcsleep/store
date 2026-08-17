package org.jetbrains.kotlin.descriptors;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.TypeCheckerState;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a$\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0006H\u0002\u001a*\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e¨\u0006\u0010"}, d2 = {"containerRelation", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility$Permissiveness;", "first", "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "second", "typeCheckerContextProvider", "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", "createTypeCheckerContext", "Lorg/jetbrains/kotlin/types/TypeCheckerState;", "toEffectiveVisibilityOrNull", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "container", "forClass", Argument.Delimiters.none, "ownerIsPublishedApi", "org.jetbrains.kotlin:compiler.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EffectiveVisibilityKt {
    public static final EffectiveVisibility.Permissiveness containerRelation(TypeConstructorMarker typeConstructorMarker, TypeConstructorMarker typeConstructorMarker2, TypeCheckerProviderContext typeCheckerProviderContext) {
        typeCheckerProviderContext.getClass();
        if (typeConstructorMarker == null || typeConstructorMarker2 == null) {
            return EffectiveVisibility.Permissiveness.UNKNOWN;
        }
        if (Intrinsics.areEqual(typeConstructorMarker, typeConstructorMarker2)) {
            return EffectiveVisibility.Permissiveness.SAME;
        }
        AbstractTypeChecker abstractTypeChecker = AbstractTypeChecker.INSTANCE;
        if (abstractTypeChecker.isSubtypeOfClass(createTypeCheckerContext(typeCheckerProviderContext), typeConstructorMarker, typeConstructorMarker2)) {
            return EffectiveVisibility.Permissiveness.LESS;
        }
        return abstractTypeChecker.isSubtypeOfClass(createTypeCheckerContext(typeCheckerProviderContext), typeConstructorMarker2, typeConstructorMarker) ? EffectiveVisibility.Permissiveness.MORE : EffectiveVisibility.Permissiveness.UNKNOWN;
    }

    private static final TypeCheckerState createTypeCheckerContext(TypeCheckerProviderContext typeCheckerProviderContext) {
        return TypeCheckerProviderContext.newTypeCheckerState$default(typeCheckerProviderContext, false, true, false, 4, (Object) null);
    }

    public static final EffectiveVisibility toEffectiveVisibilityOrNull(Visibility visibility, TypeConstructorMarker typeConstructorMarker, boolean z, boolean z2) {
        visibility.getClass();
        EffectiveVisibility effectiveVisibilityCustomEffectiveVisibility = visibility.customEffectiveVisibility();
        if (effectiveVisibilityCustomEffectiveVisibility != null) {
            return effectiveVisibilityCustomEffectiveVisibility;
        }
        Visibility visibilityNormalize = visibility.normalize();
        if (Intrinsics.areEqual(visibilityNormalize, Visibilities.PrivateToThis.INSTANCE) || Intrinsics.areEqual(visibilityNormalize, Visibilities.InvisibleFake.INSTANCE)) {
            return EffectiveVisibility.PrivateInClass.INSTANCE;
        }
        if (Intrinsics.areEqual(visibilityNormalize, Visibilities.Private.INSTANCE)) {
            return (typeConstructorMarker == null && z) ? EffectiveVisibility.PrivateInFile.INSTANCE : EffectiveVisibility.PrivateInClass.INSTANCE;
        }
        if (Intrinsics.areEqual(visibilityNormalize, Visibilities.Protected.INSTANCE)) {
            return new EffectiveVisibility.Protected(typeConstructorMarker);
        }
        if (Intrinsics.areEqual(visibilityNormalize, Visibilities.Internal.INSTANCE)) {
            if (z2) {
                return EffectiveVisibility.Public.INSTANCE;
            }
            if (!z2) {
                return EffectiveVisibility.Internal.INSTANCE;
            }
            bu8.a();
            return null;
        }
        if (Intrinsics.areEqual(visibilityNormalize, Visibilities.Public.INSTANCE)) {
            return EffectiveVisibility.Public.INSTANCE;
        }
        if (Intrinsics.areEqual(visibilityNormalize, Visibilities.Local.INSTANCE)) {
            return EffectiveVisibility.Local.INSTANCE;
        }
        if (Intrinsics.areEqual(visibilityNormalize, Visibilities.Unknown.INSTANCE)) {
            return EffectiveVisibility.Unknown.INSTANCE;
        }
        return null;
    }

    public static /* synthetic */ EffectiveVisibility toEffectiveVisibilityOrNull$default(Visibility visibility, TypeConstructorMarker typeConstructorMarker, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return toEffectiveVisibilityOrNull(visibility, typeConstructorMarker, z, z2);
    }
}
