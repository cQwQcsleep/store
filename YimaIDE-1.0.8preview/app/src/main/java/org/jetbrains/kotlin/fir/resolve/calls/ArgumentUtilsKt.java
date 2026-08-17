package org.jetbrains.kotlin.fir.resolve.calls;

import defpackage.f2f;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.TypeUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirInaccessibleReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.InaccessibleReceiverKind;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ArgumentUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirThisOwnerSymbol;
import org.jetbrains.kotlin.fir.types.ArrayUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeKindServiceKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeSystemCommonSuperTypesContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u001a\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b\u001a \u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u001c\u0010\u000b\u001a\u00020\f*\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a\u001f\u0010\u0011\u001a\u00020\f*\u0004\u0018\u00010\u0006H\u0000\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\u0010\u0000(\u0000\u001a\f\u0010\u0013\u001a\u00020\u0014*\u00020\u0012H\u0000ò\u0001\u0004\n\u00020\u0012¨\u0006\u0015"}, d2 = {"prepareCapturedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "argumentType", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getExpectedType", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "parameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "captureFromTypeParameterUpperBoundIfNeeded", "expectedType", "hasSupertypeWithGivenClassId", Argument.Delimiters.none, "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "context", "Lorg/jetbrains/kotlin/types/model/TypeSystemCommonSuperTypesContext;", "isInaccessibleAndInapplicable", "Lorg/jetbrains/kotlin/fir/expressions/FirInaccessibleReceiverExpression;", "toInaccessibleReceiverDiagnostic", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ArgumentUtilsKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[InaccessibleReceiverKind.values().length];
            try {
                iArr[InaccessibleReceiverKind.OuterClassOfNonInner.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[InaccessibleReceiverKind.ClassHeader.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[InaccessibleReceiverKind.SecondaryConstructor.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final ConeKotlinType captureFromTypeParameterUpperBoundIfNeeded(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirSession firSession) {
        ConeKotlinType coneKotlinTypeM673captureFromExpression;
        ConeDefinitelyNotNullType coneDefinitelyNotNullTypeCreate$default;
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        firSession.getClass();
        ClassId classId = ConeTypeUtilsKt.getClassId(ConeTypeUtilsKt.upperBoundIfFlexible(coneKotlinType2));
        if (classId != null) {
            ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
            Object obj = null;
            ConeTypeParameterType coneTypeParameterType = coneRigidTypeLowerBoundIfFlexible instanceof ConeTypeParameterType ? (ConeTypeParameterType) coneRigidTypeLowerBoundIfFlexible : null;
            if (coneTypeParameterType != null) {
                ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(firSession);
                Iterator<T> it = TypeUtilsKt.collectUpperBounds(coneTypeParameterType, TypeComponentsKt.getTypeContext(firSession)).iterator();
                boolean z = false;
                Object obj2 = null;
                while (true) {
                    if (!it.hasNext()) {
                        if (!z) {
                            break;
                        }
                        obj = obj2;
                        break;
                    }
                    Object next = it.next();
                    if (hasSupertypeWithGivenClassId((ConeClassLikeType) next, classId, typeContext)) {
                        if (z) {
                            break;
                        }
                        z = true;
                        obj2 = next;
                    }
                }
                ConeClassLikeType coneClassLikeType = (ConeClassLikeType) obj;
                if (coneClassLikeType != null && (coneKotlinTypeM673captureFromExpression = typeContext.m673captureFromExpression((KotlinTypeMarker) coneClassLikeType)) != null) {
                    return (!(coneKotlinType instanceof ConeDefinitelyNotNullType) || (coneDefinitelyNotNullTypeCreate$default = org.jetbrains.kotlin.fir.types.TypeUtilsKt.create$default(ConeDefinitelyNotNullType.INSTANCE, coneKotlinTypeM673captureFromExpression, TypeComponentsKt.getTypeContext(firSession), false, 4, null)) == null) ? coneKotlinTypeM673captureFromExpression : coneDefinitelyNotNullTypeCreate$default;
                }
            }
        }
        return coneKotlinType;
    }

    public static final ConeKotlinType getExpectedType(FirExpression firExpression, FirSession firSession, FirValueParameter firValueParameter) {
        firExpression.getClass();
        firSession.getClass();
        firValueParameter.getClass();
        ConeKotlinType coneKotlinTypeVarargElementType = ((firExpression instanceof FirSpreadArgumentExpression) || (firExpression instanceof FirNamedArgumentExpression)) ? false : firValueParameter.getIsVararg() ? ArrayUtilsKt.varargElementType(FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef())) : FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef());
        return !FirFunctionTypeKindServiceKt.getFunctionTypeService(firSession).hasExtensionKinds() ? coneKotlinTypeVarargElementType : new FunctionTypeKindSubstitutor(firSession).substituteOrSelf(coneKotlinTypeVarargElementType);
    }

    private static final boolean hasSupertypeWithGivenClassId(ConeKotlinType coneKotlinType, final ClassId classId, final TypeSystemCommonSuperTypesContext typeSystemCommonSuperTypesContext) {
        return typeSystemCommonSuperTypesContext.anySuperTypeConstructor(coneKotlinType, new Function1() { // from class: se0
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ArgumentUtilsKt.hasSupertypeWithGivenClassId$lambda$0$0(typeSystemCommonSuperTypesContext, classId, (RigidTypeMarker) obj));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasSupertypeWithGivenClassId$lambda$0$0(TypeSystemCommonSuperTypesContext typeSystemCommonSuperTypesContext, ClassId classId, RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = typeSystemCommonSuperTypesContext.typeConstructor(rigidTypeMarker);
        return (typeConstructorMarkerTypeConstructor instanceof ConeClassLikeLookupTag) && Intrinsics.areEqual(((ConeClassLikeLookupTag) typeConstructorMarkerTypeConstructor).getClassId(), classId);
    }

    public static final boolean isInaccessibleAndInapplicable(FirExpression firExpression) {
        return (firExpression instanceof FirInaccessibleReceiverExpression) && !((FirInaccessibleReceiverExpression) firExpression).getKind().getProducesApplicableCandidate();
    }

    public static final ConeKotlinType prepareCapturedType(ConeKotlinType coneKotlinType, FirSession firSession) {
        ConeKotlinType coneKotlinTypeM673captureFromExpression;
        coneKotlinType.getClass();
        firSession.getClass();
        return (org.jetbrains.kotlin.fir.types.TypeUtilsKt.isRaw(coneKotlinType) || (coneKotlinTypeM673captureFromExpression = TypeComponentsKt.getTypeContext(firSession).m673captureFromExpression((KotlinTypeMarker) TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, firSession, (Function1) null, 2, (Object) null))) == null) ? coneKotlinType : coneKotlinTypeM673captureFromExpression;
    }

    public static final ResolutionDiagnostic toInaccessibleReceiverDiagnostic(FirInaccessibleReceiverExpression firInaccessibleReceiverExpression) {
        firInaccessibleReceiverExpression.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[firInaccessibleReceiverExpression.getKind().ordinal()];
        if (i == 1) {
            FirThisOwnerSymbol<?> boundSymbol = firInaccessibleReceiverExpression.getCalleeReference().getBoundSymbol();
            boundSymbol.getClass();
            return new InaccessibleOuterClassReceiver((FirClassSymbol) boundSymbol);
        }
        if (i == 2) {
            return InaccessibleFromClassHeader.INSTANCE;
        }
        if (i != 3) {
            bu8.a();
            return null;
        }
        f2f.a("Should not be called for ", firInaccessibleReceiverExpression.getKind());
        return null;
    }
}
