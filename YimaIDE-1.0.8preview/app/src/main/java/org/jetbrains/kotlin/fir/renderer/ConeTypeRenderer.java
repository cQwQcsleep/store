package org.jetbrains.kotlin.fir.renderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.renderer.ConeTypeRenderer;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributes;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeCapturedTypeConstructor;
import org.jetbrains.kotlin.fir.types.ConeClassLikeErrorLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntegerConstantOperatorType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralConstantType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeConflictingProjection;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionIn;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeStubTypeConstructor;
import org.jetbrains.kotlin.fir.types.ConeTypeConstructorMarker;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableTypeConstructor;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.addToStdlib.CountingIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007JA\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u001a2\u0019\b\u0002\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00160\u001a¢\u0006\u0002\b\u001eH\u0016J\u0018\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010 \u001a\u00020!J\u001a\u0010\"\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020#2\b\b\u0002\u0010 \u001a\u00020!H\u0002J\u0010\u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u001dH\u0014J\f\u0010&\u001a\u00020\u0005*\u00020'H\u0002J\f\u0010(\u001a\u00020\u0005*\u00020'H\u0002J\f\u0010)\u001a\u00020\u0005*\u00020'H\u0002J\u0018\u0010*\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020+2\u0006\u0010 \u001a\u00020!H\u0014J\u000e\u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020.J$\u0010/\u001a\u00020!2\u0006\u00100\u001a\u0002012\b\b\u0002\u00102\u001a\u00020!2\b\b\u0002\u00103\u001a\u00020!H\u0016J\f\u00104\u001a\u00020\u0016*\u00020#H\u0002J\f\u00105\u001a\u00020\u0005*\u000206H\u0002J\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u00107\u001a\u000206H\u0014J\f\u00108\u001a\u00020\u0016*\u00020\u0018H\u0014J\f\u00109\u001a\u00020\u0016*\u00020\u0018H\u0004J\f\u0010\u001f\u001a\u00020\u0016*\u00020\u001dH\u0004J\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020:H\u0014J\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020;H\u0014J\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020<H\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00060\tj\u0002`\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006="}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRenderer;", Argument.Delimiters.none, "attributeRenderer", "Lorg/jetbrains/kotlin/fir/renderer/ConeAttributeRenderer;", "renderCapturedDetails", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/renderer/ConeAttributeRenderer;Z)V", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getBuilder", "()Ljava/lang/StringBuilder;", "setBuilder", "(Ljava/lang/StringBuilder;)V", "idRenderer", "Lorg/jetbrains/kotlin/fir/renderer/ConeIdRenderer;", "getIdRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/ConeIdRenderer;", "setIdRenderer", "(Lorg/jetbrains/kotlin/fir/renderer/ConeIdRenderer;)V", "renderAsPossibleFunctionType", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "functionClassKindExtractor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "renderType", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "Lkotlin/ExtensionFunctionType;", "render", "nullabilityMarker", Argument.Delimiters.none, "renderFunctionType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "couldBenefitFromParenthesizing", "projection", "isFunctionType", "Lorg/jetbrains/kotlin/name/ClassId;", "isNonSuspendFunctionType", "isSuspendFunctionType", "renderSimpleType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "renderConstructor", "constructor", "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "renderDiagnostic", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "prefix", "suffix", "renderTypeArguments", "renderForSameLookupTags", "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "flexibleType", "renderAttributes", "renderNonCompilerAttributes", "Lorg/jetbrains/kotlin/fir/types/ConeIntegerLiteralType;", "Lorg/jetbrains/kotlin/fir/types/ConeDefinitelyNotNullType;", "Lorg/jetbrains/kotlin/fir/types/ConeIntersectionType;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ConeTypeRenderer {
    private final ConeAttributeRenderer attributeRenderer;
    public StringBuilder builder;
    public ConeIdRenderer idRenderer;
    private boolean renderCapturedDetails;

    public /* synthetic */ ConeTypeRenderer(ConeAttributeRenderer coneAttributeRenderer, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ConeAttributeRenderer.ToString.INSTANCE : coneAttributeRenderer, (i & 2) != 0 ? false : z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public static Unit a(ConeTypeRenderer coneTypeRenderer, ConeTypeProjection coneTypeProjection) throws UninitializedPropertyAccessException {
        coneTypeProjection.getClass();
        coneTypeRenderer.render(coneTypeProjection);
        return Unit.INSTANCE;
    }

    private final boolean isFunctionType(ClassId classId) {
        return isNonSuspendFunctionType(classId) || isSuspendFunctionType(classId);
    }

    private final boolean isNonSuspendFunctionType(ClassId classId) {
        return StringsKt.toIntOrNull(StringsKt.removePrefix(classId.asString(), StandardClassIds.INSTANCE.getFunction().asString())) != null;
    }

    private final boolean isSuspendFunctionType(ClassId classId) {
        return StringsKt.toIntOrNull(StringsKt.removePrefix(classId.asString(), StandardClassIds.INSTANCE.getSuspendFunction().asString())) != null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public static /* synthetic */ void render$default(ConeTypeRenderer coneTypeRenderer, ConeKotlinType coneKotlinType, String str, int i, Object obj) throws UninitializedPropertyAccessException {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: render");
            return;
        }
        if ((i & 2) != 0) {
            str = ((coneKotlinType instanceof ConeFlexibleType) || !ConeTypeUtilsKt.isMarkedNullable(coneKotlinType)) ? Argument.Delimiters.none : "?";
        }
        coneTypeRenderer.render(coneKotlinType, str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void renderAsPossibleFunctionType$default(final ConeTypeRenderer coneTypeRenderer, ConeKotlinType coneKotlinType, Function1 function1, Function1 function2, int i, Object obj) throws UninitializedPropertyAccessException {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: renderAsPossibleFunctionType");
            return;
        }
        if ((i & 4) != 0) {
            function2 = new Function1() { // from class: nq2
                public final Object invoke(Object obj2) {
                    return ConeTypeRenderer.a(this.b, (ConeTypeProjection) obj2);
                }
            };
        }
        coneTypeRenderer.renderAsPossibleFunctionType(coneKotlinType, function1, function2);
    }

    public static /* synthetic */ String renderDiagnostic$default(ConeTypeRenderer coneTypeRenderer, ConeDiagnostic coneDiagnostic, String str, String str2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: renderDiagnostic");
            return null;
        }
        if ((i & 2) != 0) {
            str = Argument.Delimiters.none;
        }
        if ((i & 4) != 0) {
            str2 = Argument.Delimiters.none;
        }
        return coneTypeRenderer.renderDiagnostic(coneDiagnostic, str, str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final boolean renderForSameLookupTags(ConeFlexibleType coneFlexibleType) throws UninitializedPropertyAccessException {
        if (coneFlexibleType.getIsTrivial() && !(coneFlexibleType.getLowerBound() instanceof ConeDefinitelyNotNullType)) {
            render(coneFlexibleType.getLowerBound(), "!");
            return true;
        }
        if (!(coneFlexibleType.getLowerBound() instanceof ConeLookupTagBasedType) || !(coneFlexibleType.getUpperBound() instanceof ConeLookupTagBasedType) || !Intrinsics.areEqual(((ConeLookupTagBasedType) coneFlexibleType.getLowerBound()).getLookupTag(), ((ConeLookupTagBasedType) coneFlexibleType.getUpperBound()).getLookupTag()) || ((ConeLookupTagBasedType) coneFlexibleType.getLowerBound()).getIsMarkedNullable() || !((ConeLookupTagBasedType) coneFlexibleType.getUpperBound()).getIsMarkedNullable()) {
            return false;
        }
        if ((coneFlexibleType.getLowerBound() instanceof ConeClassLikeType) && coneFlexibleType.getLowerBound().getTypeArguments().length != 0) {
            return false;
        }
        if ((coneFlexibleType.getUpperBound() instanceof ConeClassLikeType) && coneFlexibleType.getUpperBound().getTypeArguments().length != 0) {
            return false;
        }
        render(coneFlexibleType.getLowerBound(), "!");
        return true;
    }

    private final void renderFunctionType(ConeClassLikeType type, String nullabilityMarker) {
        ConeTypeProjection coneTypeProjection;
        if (!Intrinsics.areEqual(nullabilityMarker, Argument.Delimiters.none)) {
            getBuilder().append('(');
        }
        if (isSuspendFunctionType(ConeTypeUtilsKt.getClassId(type))) {
            getBuilder().append("suspend ");
        }
        CountingIterator countingIteratorWithSeenElementsCounting = AddToStdlibKt.withSeenElementsCounting(ArrayIteratorKt.iterator(type.getTypeArguments()));
        if (CompilerConeAttributesKt.getHasContextParameters(type)) {
            getBuilder().append("context(");
            int contextParameterNumberForFunctionType = CompilerConeAttributesKt.getContextParameterNumberForFunctionType(type) - 1;
            for (int i = 0; i < contextParameterNumberForFunctionType; i++) {
                ConeTypeProjection coneTypeProjection2 = (ConeTypeProjection) AddToStdlibKt.nextOrNull(countingIteratorWithSeenElementsCounting);
                if (coneTypeProjection2 != null) {
                    render(coneTypeProjection2);
                }
                getBuilder().append(", ");
            }
            ConeTypeProjection coneTypeProjection3 = (ConeTypeProjection) AddToStdlibKt.nextOrNull(countingIteratorWithSeenElementsCounting);
            if (coneTypeProjection3 != null) {
                render(coneTypeProjection3);
            }
            getBuilder().append(") ");
        }
        if (CompilerConeAttributesKt.isExtensionFunctionType(type)) {
            ConeTypeProjection coneTypeProjection4 = (ConeTypeProjection) AddToStdlibKt.nextOrNull(countingIteratorWithSeenElementsCounting);
            if (coneTypeProjection4 != null) {
                if (couldBenefitFromParenthesizing(coneTypeProjection4)) {
                    getBuilder().append('(');
                }
                render(coneTypeProjection4);
                if (couldBenefitFromParenthesizing(coneTypeProjection4)) {
                    getBuilder().append(')');
                }
            }
            getBuilder().append(".");
        }
        getBuilder().append('(');
        while (countingIteratorWithSeenElementsCounting.getNumberOfElementsSeen() < type.getTypeArguments().length - 2) {
            ConeTypeProjection coneTypeProjection5 = (ConeTypeProjection) AddToStdlibKt.nextOrNull(countingIteratorWithSeenElementsCounting);
            if (coneTypeProjection5 != null) {
                render(coneTypeProjection5);
            }
            getBuilder().append(", ");
        }
        if (countingIteratorWithSeenElementsCounting.getNumberOfElementsSeen() == type.getTypeArguments().length - 2 && (coneTypeProjection = (ConeTypeProjection) AddToStdlibKt.nextOrNull(countingIteratorWithSeenElementsCounting)) != null) {
            render(coneTypeProjection);
        }
        getBuilder().append(") -> ");
        ConeTypeProjection coneTypeProjection6 = (ConeTypeProjection) AddToStdlibKt.nextOrNull(countingIteratorWithSeenElementsCounting);
        if (coneTypeProjection6 != null) {
            render(coneTypeProjection6);
        }
        if (Intrinsics.areEqual(nullabilityMarker, Argument.Delimiters.none)) {
            return;
        }
        getBuilder().append(')');
        getBuilder().append(nullabilityMarker);
    }

    private final void renderTypeArguments(ConeClassLikeType coneClassLikeType) {
        if (coneClassLikeType.getTypeArguments().length == 0) {
            return;
        }
        getBuilder().append("<");
        ConeTypeProjection[] typeArguments = coneClassLikeType.getTypeArguments();
        int length = typeArguments.length;
        for (int i = 0; i < length; i++) {
            ConeTypeProjection coneTypeProjection = typeArguments[i];
            if (i > 0) {
                getBuilder().append(", ");
            }
            render(coneTypeProjection);
        }
        getBuilder().append(">");
    }

    public boolean couldBenefitFromParenthesizing(ConeTypeProjection projection) {
        ConeKotlinType coneKotlinType;
        ClassId classId;
        projection.getClass();
        return ((projection instanceof ConeKotlinType) && !(projection instanceof ConeDefinitelyNotNullType) && ((classId = ConeTypeUtilsKt.getClassId((coneKotlinType = (ConeKotlinType) projection))) == null || !isFunctionType(classId) || ConeTypeUtilsKt.isMarkedNullable(coneKotlinType))) ? false : true;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final StringBuilder getBuilder() throws UninitializedPropertyAccessException {
        StringBuilder sb = this.builder;
        if (sb != null) {
            return sb;
        }
        Intrinsics.throwUninitializedPropertyAccessException("builder");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final ConeIdRenderer getIdRenderer() throws UninitializedPropertyAccessException {
        ConeIdRenderer coneIdRenderer = this.idRenderer;
        if (coneIdRenderer != null) {
            return coneIdRenderer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("idRenderer");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final void render(ConeKotlinType type, String nullabilityMarker) throws UninitializedPropertyAccessException {
        ClassId classId;
        type.getClass();
        nullabilityMarker.getClass();
        boolean z = type instanceof ConeFlexibleType;
        if (!z && !(type instanceof ConeDefinitelyNotNullType) && ((classId = ConeTypeUtilsKt.getClassId(type)) == null || !isFunctionType(classId))) {
            renderAttributes(type);
        }
        if (type instanceof ConeDefinitelyNotNullType) {
            render((ConeDefinitelyNotNullType) type);
        } else if (type instanceof ConeIntersectionType) {
            render((ConeIntersectionType) type);
        } else if (type instanceof ConeDynamicType) {
            getBuilder().append("dynamic");
        } else {
            if (!z) {
                if (!(type instanceof ConeSimpleKotlinType)) {
                    bu8.a();
                    return;
                }
                if (type instanceof ConeClassLikeType) {
                    ConeClassLikeType coneClassLikeType = (ConeClassLikeType) type;
                    if (isFunctionType(ConeTypeUtilsKt.getClassId(coneClassLikeType))) {
                        renderFunctionType(coneClassLikeType, nullabilityMarker);
                        return;
                    }
                }
                renderSimpleType((ConeSimpleKotlinType) type, nullabilityMarker);
                return;
            }
            render((ConeFlexibleType) type);
        }
        getBuilder().append(nullabilityMarker);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public void renderAsPossibleFunctionType(ConeKotlinType type, Function1<? super ConeKotlinType, ? extends FunctionTypeKind> functionClassKindExtractor, Function1<? super ConeTypeProjection, Unit> renderType) throws UninitializedPropertyAccessException {
        ConeTypeProjection coneTypeProjection;
        type.getClass();
        functionClassKindExtractor.getClass();
        renderType.getClass();
        FunctionTypeKind functionTypeKind = (FunctionTypeKind) functionClassKindExtractor.invoke(type);
        if (functionTypeKind == null || functionTypeKind.isReflectType()) {
            renderType.invoke(type);
            return;
        }
        renderNonCompilerAttributes(type);
        if (ConeTypeUtilsKt.isMarkedNullable(type)) {
            getBuilder().append("(");
        }
        String prefixForTypeRender = functionTypeKind.getPrefixForTypeRender();
        if (prefixForTypeRender != null) {
            getBuilder().append(prefixForTypeRender);
            getBuilder().append(Argument.Delimiters.space);
        }
        List<ConeTypeProjection> listEmptyList = CollectionsKt.emptyList();
        List listAsList = ArraysKt.asList(type.getTypeArguments());
        int contextParameterNumberForFunctionType = CompilerConeAttributesKt.getContextParameterNumberForFunctionType(type);
        int i = 0;
        if (contextParameterNumberForFunctionType > 0 && listAsList.size() >= contextParameterNumberForFunctionType + 1) {
            listEmptyList = listAsList.subList(0, contextParameterNumberForFunctionType);
            listAsList = listAsList.subList(contextParameterNumberForFunctionType, listAsList.size());
        }
        if (!CompilerConeAttributesKt.isExtensionFunctionType(type) || listAsList.size() < 2 || Intrinsics.areEqual(CollectionsKt.first(listAsList), ConeStarProjection.INSTANCE)) {
            coneTypeProjection = null;
        } else {
            coneTypeProjection = (ConeTypeProjection) CollectionsKt.first(listAsList);
            listAsList = listAsList.subList(1, listAsList.size());
        }
        if (!listEmptyList.isEmpty()) {
            getBuilder().append("context(");
            int i2 = 0;
            for (ConeTypeProjection coneTypeProjection2 : listEmptyList) {
                int i3 = i2 + 1;
                if (i2 != 0) {
                    getBuilder().append(", ");
                }
                render(coneTypeProjection2);
                i2 = i3;
            }
            getBuilder().append(") ");
        }
        List<ConeTypeProjection> listSubList = listAsList.subList(0, listAsList.size() - 1);
        ConeTypeProjection coneTypeProjection3 = (ConeTypeProjection) CollectionsKt.last(listAsList);
        if (coneTypeProjection != null) {
            render(coneTypeProjection);
            getBuilder().append(".");
        }
        getBuilder().append("(");
        for (ConeTypeProjection coneTypeProjection4 : listSubList) {
            int i4 = i + 1;
            if (i != 0) {
                getBuilder().append(", ");
            }
            render(coneTypeProjection4);
            i = i4;
        }
        getBuilder().append(") -> ");
        render(coneTypeProjection3);
        if (ConeTypeUtilsKt.isMarkedNullable(type)) {
            getBuilder().append(")?");
        }
    }

    public void renderAttributes(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        if (CollectionsKt.any(coneKotlinType.getAttributes())) {
            getBuilder().append(this.attributeRenderer.render(coneKotlinType.getAttributes()));
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final void renderConstructor(TypeConstructorMarker constructor) throws UninitializedPropertyAccessException {
        Unit unit;
        constructor.getClass();
        if (!(constructor instanceof ConeTypeConstructorMarker)) {
            w01.a("Failed requirement.");
            return;
        }
        ConeTypeConstructorMarker coneTypeConstructorMarker = (ConeTypeConstructorMarker) constructor;
        if (coneTypeConstructorMarker instanceof ConeTypeVariableTypeConstructor) {
            getBuilder().append("TypeVariable(");
            getBuilder().append(((ConeTypeVariableTypeConstructor) constructor).getName());
            getBuilder().append(")");
            return;
        }
        if (coneTypeConstructorMarker instanceof ConeCapturedTypeConstructor) {
            getBuilder().append("CapturedType(");
            ConeCapturedTypeConstructor coneCapturedTypeConstructor = (ConeCapturedTypeConstructor) constructor;
            render(coneCapturedTypeConstructor.getProjection());
            getBuilder().append(")");
            if (this.renderCapturedDetails) {
                StringBuilder builder = getBuilder();
                StringBuilder sb = new StringBuilder(" with lowerType=");
                ConeKotlinType lowerType = coneCapturedTypeConstructor.getLowerType();
                if (lowerType != null) {
                    render$default(this, lowerType, null, 2, null);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                sb.append(unit);
                sb.append(", supertypes=[");
                builder.append(sb.toString());
                this.renderCapturedDetails = false;
                List<ConeKotlinType> supertypes = coneCapturedTypeConstructor.getSupertypes();
                if (supertypes != null) {
                    Iterator<T> it = supertypes.iterator();
                    while (it.hasNext()) {
                        render$default(this, (ConeKotlinType) it.next(), null, 2, null);
                    }
                }
                this.renderCapturedDetails = true;
                getBuilder().append("]");
            }
            Unit unit2 = Unit.INSTANCE;
            return;
        }
        if (coneTypeConstructorMarker instanceof ConeClassLikeErrorLookupTag) {
            getBuilder().append(renderDiagnostic$default(this, ((ConeClassLikeErrorLookupTag) constructor).getDiagnostic(), "ERROR CLASS: ", null, 4, null));
            return;
        }
        if (coneTypeConstructorMarker instanceof ConeClassLikeLookupTag) {
            getIdRenderer().renderClassId(((ConeClassLikeLookupTag) constructor).getClassId());
            Unit unit3 = Unit.INSTANCE;
            return;
        }
        if (coneTypeConstructorMarker instanceof ConeClassifierLookupTag) {
            getBuilder().append(((ConeClassifierLookupTag) constructor).getName().asString());
            return;
        }
        if (coneTypeConstructorMarker instanceof ConeStubTypeConstructor) {
            getBuilder().append("Stub (subtyping): " + ((ConeStubTypeConstructor) constructor).getVariable());
            return;
        }
        if (coneTypeConstructorMarker instanceof ConeIntegerLiteralType) {
            render((ConeIntegerLiteralType) constructor);
            Unit unit4 = Unit.INSTANCE;
        } else if (coneTypeConstructorMarker instanceof ConeIntersectionType) {
            k2d.a("`renderConstructor` mustn't be called with an intersection type argument. Call `render` to simply render the type or filter out intersection types on the call-site.");
        } else {
            bu8.a();
        }
    }

    public String renderDiagnostic(ConeDiagnostic diagnostic, String prefix, String suffix) {
        diagnostic.getClass();
        prefix.getClass();
        suffix.getClass();
        return prefix + diagnostic.getReason() + suffix;
    }

    public final void renderNonCompilerAttributes(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        Map classIdByCompilerAttributeKey = CompilerConeAttributes.INSTANCE.getClassIdByCompilerAttributeKey();
        ConeAttributes attributes = coneKotlinType.getAttributes();
        ArrayList arrayList = new ArrayList();
        for (ConeAttribute<?> coneAttribute : attributes) {
            if (!classIdByCompilerAttributeKey.containsKey(coneAttribute.getKey())) {
                arrayList.add(coneAttribute);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        getBuilder().append(this.attributeRenderer.render(arrayList));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public void renderSimpleType(ConeSimpleKotlinType type, String nullabilityMarker) throws UninitializedPropertyAccessException {
        type.getClass();
        nullabilityMarker.getClass();
        boolean z = false;
        if (type instanceof ConeClassLikeType) {
            if (!(type.getTypeArguments().length == 0)) {
                z = true;
            }
        }
        renderConstructor(ConeTypeUtilsKt.getConstructor(type));
        if (z) {
            renderTypeArguments((ConeClassLikeType) type);
        }
        getBuilder().append(nullabilityMarker);
    }

    public final void setBuilder(StringBuilder sb) {
        sb.getClass();
        this.builder = sb;
    }

    public final void setIdRenderer(ConeIdRenderer coneIdRenderer) {
        coneIdRenderer.getClass();
        this.idRenderer = coneIdRenderer;
    }

    public ConeTypeRenderer(ConeAttributeRenderer coneAttributeRenderer, boolean z) {
        coneAttributeRenderer.getClass();
        this.attributeRenderer = coneAttributeRenderer;
        this.renderCapturedDetails = z;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ConeTypeRenderer() {
        ConeAttributeRenderer coneAttributeRenderer = null;
        this(coneAttributeRenderer, false, 3, coneAttributeRenderer);
    }

    public void render(ConeFlexibleType flexibleType) {
        flexibleType.getClass();
        if (renderForSameLookupTags(flexibleType)) {
            return;
        }
        getBuilder().append("ft<");
        render$default(this, flexibleType.getLowerBound(), null, 2, null);
        getBuilder().append(", ");
        render$default(this, flexibleType.getUpperBound(), null, 2, null);
        getBuilder().append(">");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final void render(ConeTypeProjection coneTypeProjection) throws UninitializedPropertyAccessException {
        coneTypeProjection.getClass();
        if (Intrinsics.areEqual(coneTypeProjection, ConeStarProjection.INSTANCE)) {
            getBuilder().append("*");
            return;
        }
        if (coneTypeProjection instanceof ConeKotlinTypeConflictingProjection) {
            getBuilder().append("CONFLICTING-PROJECTION ");
            render$default(this, ((ConeKotlinTypeConflictingProjection) coneTypeProjection).getType(), null, 2, null);
            return;
        }
        if (coneTypeProjection instanceof ConeKotlinTypeProjectionIn) {
            getBuilder().append("in ");
            render$default(this, ((ConeKotlinTypeProjectionIn) coneTypeProjection).getType(), null, 2, null);
        } else if (coneTypeProjection instanceof ConeKotlinTypeProjectionOut) {
            getBuilder().append("out ");
            render$default(this, ((ConeKotlinTypeProjectionOut) coneTypeProjection).getType(), null, 2, null);
        } else if (coneTypeProjection instanceof ConeKotlinType) {
            render$default(this, (ConeKotlinType) coneTypeProjection, null, 2, null);
        } else {
            bu8.a();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public void render(ConeIntegerLiteralType type) throws UninitializedPropertyAccessException {
        type.getClass();
        if (type instanceof ConeIntegerLiteralConstantType) {
            getBuilder().append("ILT: " + ((ConeIntegerLiteralConstantType) type).getValue());
            return;
        }
        if (type instanceof ConeIntegerConstantOperatorType) {
            getBuilder().append("IOT");
        } else {
            bu8.a();
        }
    }

    public void render(ConeDefinitelyNotNullType type) {
        type.getClass();
        render$default(this, type.getOriginal(), null, 2, null);
        getBuilder().append(" & Any");
    }

    public void render(ConeIntersectionType type) {
        type.getClass();
        getBuilder().append("it(");
        int i = 0;
        for (ConeKotlinType coneKotlinType : type.getIntersectedTypes()) {
            int i2 = i + 1;
            if (i > 0) {
                getBuilder().append(" & ");
            }
            render$default(this, coneKotlinType, null, 2, null);
            i = i2;
        }
        getBuilder().append(")");
    }
}
