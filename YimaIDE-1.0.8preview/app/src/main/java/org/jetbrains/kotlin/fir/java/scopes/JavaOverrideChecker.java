package org.jetbrains.kotlin.fir.java.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.java.JavaTypeConversionKt;
import org.jetbrains.kotlin.fir.java.JavaTypeParameterStack;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaClass;
import org.jetbrains.kotlin.fir.java.enhancement.JavaTypeUtilsKt;
import org.jetbrains.kotlin.fir.java.scopes.JavaOverrideChecker;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.impl.FirOverrideUtilsKt;
import org.jetbrains.kotlin.fir.scopes.jvm.SignatureUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeRawType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B3\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ \u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J8\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u000bH\u0002J\u0016\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$J\u0014\u0010&\u001a\u00020\u000b*\u00020\u00182\u0006\u0010'\u001a\u00020\u000bH\u0002J\u0014\u0010(\u001a\u00020\u000b*\u00020$2\u0006\u0010)\u001a\u00020\u0018H\u0002J \u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u001e\u0010.\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u00180/*\b\u0012\u0004\u0012\u00020201H\u0002J\u001a\u00103\u001a\u00020\u0018*\u0002002\f\u00104\u001a\b\u0012\u0004\u0012\u00020005H\u0002J\u000e\u00106\u001a\u00020\u000b*\u0004\u0018\u00010\u001dH\u0002J\f\u00106\u001a\u00020\u000b*\u00020\u0018H\u0002J\f\u00106\u001a\u00020\u000b*\u000207H\u0002J\u001a\u00108\u001a\u000209*\u00020\u001d2\f\u0010:\u001a\b\u0012\u0004\u0012\u0002020;H\u0002J\u001a\u00108\u001a\u000209*\u00020\u00182\f\u0010:\u001a\b\u0012\u0004\u0012\u0002020;H\u0002J\u001a\u00108\u001a\u000209*\u0002072\f\u0010:\u001a\b\u0012\u0004\u0012\u0002020;H\u0002J \u0010<\u001a\u00020\u001b2\u0006\u0010=\u001a\u0002072\u0006\u0010>\u001a\u0002072\u0006\u0010?\u001a\u00020\u000bH\u0014J\u0018\u0010@\u001a\u00020\u000b2\u0006\u0010=\u001a\u00020$2\u0006\u0010>\u001a\u00020$H\u0016J\u0014\u0010A\u001a\u00020\u000b*\u00020$2\u0006\u0010B\u001a\u00020$H\u0002J\u0012\u0010C\u001a\b\u0012\u0004\u0012\u00020\u001d0\b*\u00020$H\u0002J\u0018\u0010D\u001a\u00020\u000b2\u0006\u0010=\u001a\u0002072\u0006\u0010>\u001a\u00020EH\u0016J\u0010\u0010F\u001a\u00020\u000b2\u0006\u0010G\u001a\u00020$H\u0002J$\u0010H\u001a\u00020I2\u0010\u0010J\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030K012\b\u0010L\u001a\u0004\u0018\u00010MH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006N"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/scopes/JavaOverrideChecker;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirAbstractOverrideChecker;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "javaClassForTypeParameterStack", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;", "baseScopes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "considerReturnTypeKinds", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;Ljava/util/List;Z)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "context", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "javaTypeParameterStack", "Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;", "getJavaTypeParameterStack", "()Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;", "isEqualTypes", "candidateType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "baseType", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "candidateTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "baseTypeRef", "forceBoxCandidateType", "forceBoxBaseType", "dontComparePrimitivity", "doesReturnTypesHaveSameKind", "candidate", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "base", "isPrimitiveInJava", "isReturnType", "hasPrimitiveReturnTypeInJvm", "returnType", "isEqualArrayElementTypeProjections", "candidateTypeProjection", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "baseTypeProjection", "buildErasure", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "findFirstBoundForErasure", "visited", Argument.Delimiters.none, "isTypeParameterDependent", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "extractTypeParametersTo", Argument.Delimiters.none, CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, Argument.Delimiters.none, "buildTypeParametersSubstitutorIfCompatible", "overrideCandidate", "baseDeclaration", "checkReifiednessIsSame", "isOverriddenFunction", "hasSameValueParameterTypes", "other", "collectValueParameterTypes", "isOverriddenProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "forceSingleValueParameterBoxing", "function", "chooseIntersectionVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "overrides", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "dispatchClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaOverrideChecker extends FirAbstractOverrideChecker implements SessionHolder {
    private final List<FirTypeScope> baseScopes;
    private final boolean considerReturnTypeKinds;
    private final ConeTypeContext context;
    private final FirJavaClass javaClassForTypeParameterStack;
    private final FirSession session;

    /* JADX WARN: Multi-variable type inference failed */
    public JavaOverrideChecker(FirSession firSession, FirJavaClass firJavaClass, List<? extends FirTypeScope> list, boolean z) {
        firSession.getClass();
        this.session = firSession;
        this.javaClassForTypeParameterStack = firJavaClass;
        this.baseScopes = list;
        this.considerReturnTypeKinds = z;
        this.context = TypeComponentsKt.getTypeContext(getSession());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction b(Ref.BooleanRef booleanRef, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (!Intrinsics.areEqual(ClassMembersKt.containingClassLookupTag((FirCallableDeclaration) firNamedFunctionSymbol.getFir()), TypeConstructionUtilsKt.toLookupTag(StandardClassIds.INSTANCE.getMutableCollection()))) {
            return ProcessorAction.NEXT;
        }
        booleanRef.element = true;
        return ProcessorAction.STOP;
    }

    private final Map<FirTypeParameterSymbol, ConeKotlinType> buildErasure(Collection<? extends FirTypeParameterRef> collection) {
        Collection<? extends FirTypeParameterRef> collection2 = collection;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(collection2, 10)), 16));
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            FirTypeParameterSymbol symbol = ((FirTypeParameterRef) it.next()).getSymbol();
            Pair pair = TuplesKt.to(symbol, findFirstBoundForErasure(symbol, new HashSet()));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction c(JavaOverrideChecker javaOverrideChecker, FirNamedFunction firNamedFunction, Ref.BooleanRef booleanRef, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        FirTypeRef returnTypeRef = ((FirNamedFunction) firNamedFunctionSymbol.getFir()).getReturnTypeRef();
        FirSession session = javaOverrideChecker.getSession();
        JavaTypeParameterStack javaTypeParameterStack = javaOverrideChecker.getJavaTypeParameterStack();
        KtSourceElement source = firNamedFunction.getSource();
        if (javaOverrideChecker.isPrimitiveInJava(JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(returnTypeRef, session, javaTypeParameterStack, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null, null, 8, null), true)) {
            return ProcessorAction.NEXT;
        }
        booleanRef.element = true;
        return ProcessorAction.STOP;
    }

    private final List<FirTypeRef> collectValueParameterTypes(FirNamedFunction firNamedFunction) {
        FirTypeRef typeRef;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List list = listCreateListBuilder;
        Iterator<T> it = firNamedFunction.getContextParameters().iterator();
        while (it.hasNext()) {
            list.add(((FirValueParameter) it.next()).getReturnTypeRef());
        }
        FirReceiverParameter receiverParameter = firNamedFunction.getReceiverParameter();
        if (receiverParameter != null && (typeRef = receiverParameter.getTypeRef()) != null) {
            listCreateListBuilder.add(typeRef);
        }
        Iterator<T> it2 = firNamedFunction.getValueParameters().iterator();
        while (it2.hasNext()) {
            list.add(((FirValueParameter) it2.next()).getReturnTypeRef());
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void extractTypeParametersTo(ConeKotlinType coneKotlinType, Collection<FirTypeParameterRef> collection) {
        if (coneKotlinType instanceof ConeFlexibleType) {
            extractTypeParametersTo(((ConeFlexibleType) coneKotlinType).getLowerBound(), collection);
            return;
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            extractTypeParametersTo(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal(), collection);
            return;
        }
        if (coneKotlinType instanceof ConeTypeParameterType) {
            collection.add(((ConeTypeParameterType) coneKotlinType).getLookupTag().getTypeParameterSymbol().getFir());
            return;
        }
        if (coneKotlinType instanceof ConeClassLikeType) {
            for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType.getTypeArguments()) {
                if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                    extractTypeParametersTo(coneKotlinTypeProjection.getType(), collection);
                }
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v7, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    private final ConeKotlinType findFirstBoundForErasure(FirTypeParameterSymbol firTypeParameterSymbol, Set<FirTypeParameterSymbol> set) throws KotlinIllegalArgumentExceptionWithAttachments {
        boolean zContains = set.contains(firTypeParameterSymbol);
        set.add(firTypeParameterSymbol);
        FirTypeRef firTypeRef = (FirTypeRef) CollectionsKt.firstOrNull(((FirTypeParameter) firTypeParameterSymbol.getFir()).getBounds());
        if (firTypeRef != null) {
            FirSession session = getSession();
            JavaTypeParameterStack javaTypeParameterStack = getJavaTypeParameterStack();
            KtSourceElement source = firTypeParameterSymbol.getSource();
            ConeKotlinType coneKotlinTypeProbablyFlexible$default = JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(firTypeRef, session, javaTypeParameterStack, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null, null, 8, null);
            ConeSimpleKotlinType coneSimpleKotlinTypeUnwrapLowerBound = ConeTypeUtilsKt.unwrapLowerBound(coneKotlinTypeProbablyFlexible$default);
            return (zContains || !(coneSimpleKotlinTypeUnwrapLowerBound instanceof ConeTypeParameterType)) ? coneKotlinTypeProbablyFlexible$default : findFirstBoundForErasure(((ConeTypeParameterType) coneSimpleKotlinTypeUnwrapLowerBound).getLookupTag().getSymbol(), set);
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Bound element is not found", (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "typeParameterRef", firTypeParameterSymbol.getFir());
        FirTypeParameter firTypeParameter = (FirTypeParameter) firTypeParameterSymbol.getFir();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "typeParameter", firTypeParameter);
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "containingDeclaration", firTypeParameter.getContainingDeclarationSymbol().getFir());
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    private final boolean forceSingleValueParameterBoxing(FirNamedFunction function) {
        FirValueParameter firValueParameter;
        if (!Intrinsics.areEqual(function.getName().asString(), "remove") || function.getReceiverParameter() != null || !function.getContextParameters().isEmpty() || (firValueParameter = (FirValueParameter) CollectionsKt.singleOrNull(function.getValueParameters())) == null) {
            return false;
        }
        FirTypeRef returnTypeRef = firValueParameter.getReturnTypeRef();
        FirSession session = getSession();
        JavaTypeParameterStack javaTypeParameterStack = getJavaTypeParameterStack();
        KtSourceElement source = function.getSource();
        if (!ConeBuiltinTypeUtilsKt.isInt(ConeTypeUtilsKt.lowerBoundIfFlexible(TypeExpansionUtilsKt.fullyExpandedType(this, JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(returnTypeRef, session, javaTypeParameterStack, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null, null, 8, null))))) {
            return false;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        List<FirTypeScope> list = this.baseScopes;
        if (list != null) {
            FirTypeScopeKt.processOverriddenFunctions(list, function.getSymbol(), (Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction>) new Function1() { // from class: zj7
                public final Object invoke(Object obj) {
                    return JavaOverrideChecker.b(booleanRef, (FirNamedFunctionSymbol) obj);
                }
            });
        }
        return booleanRef.element;
    }

    private final JavaTypeParameterStack getJavaTypeParameterStack() {
        JavaTypeParameterStack javaTypeParameterStack;
        FirJavaClass firJavaClass = this.javaClassForTypeParameterStack;
        return (firJavaClass == null || (javaTypeParameterStack = firJavaClass.getJavaTypeParameterStack()) == null) ? JavaTypeParameterStack.INSTANCE.getEMPTY() : javaTypeParameterStack;
    }

    private final boolean hasPrimitiveReturnTypeInJvm(final FirNamedFunction firNamedFunction, ConeKotlinType coneKotlinType) {
        if (!isPrimitiveInJava(coneKotlinType, true)) {
            return false;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        List<FirTypeScope> list = this.baseScopes;
        if (list != null) {
            FirTypeScopeKt.processOverriddenFunctions(list, firNamedFunction.getSymbol(), (Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction>) new Function1() { // from class: yj7
                public final Object invoke(Object obj) {
                    return JavaOverrideChecker.c(this.b, firNamedFunction, booleanRef, (FirNamedFunctionSymbol) obj);
                }
            });
        }
        return !booleanRef.element;
    }

    private final boolean hasSameValueParameterTypes(FirNamedFunction firNamedFunction, FirNamedFunction firNamedFunction2) {
        FirTypeRef firTypeRef;
        boolean z;
        FirTypeRef firTypeRef2;
        JavaOverrideChecker javaOverrideChecker = this;
        FirNamedFunction firNamedFunction3 = firNamedFunction2;
        List<FirTypeRef> listCollectValueParameterTypes = javaOverrideChecker.collectValueParameterTypes(firNamedFunction3);
        List<FirValueParameter> valueParameters = firNamedFunction.getValueParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameters, 10));
        Iterator<T> it = valueParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirValueParameter) it.next()).getReturnTypeRef());
        }
        if (arrayList.size() != listCollectValueParameterTypes.size()) {
            return false;
        }
        ConeSubstitutor coneSubstitutorBuildTypeParametersSubstitutorIfCompatible$default = FirAbstractOverrideChecker.buildTypeParametersSubstitutorIfCompatible$default(javaOverrideChecker, firNamedFunction, firNamedFunction3, false, 4, null);
        boolean zForceSingleValueParameterBoxing = forceSingleValueParameterBoxing(firNamedFunction);
        boolean zForceSingleValueParameterBoxing2 = javaOverrideChecker.forceSingleValueParameterBoxing(firNamedFunction3);
        JavaOverrideChecker javaOverrideChecker2 = javaOverrideChecker;
        FirCallableDeclaration firCallableDeclaration = firNamedFunction3;
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            javaOverrideChecker2 = this;
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        List<FirTypeRef> listCollectValueParameterTypes2 = javaOverrideChecker2.collectValueParameterTypes((FirNamedFunction) firCallableDeclaration);
        FirCallableDeclaration firCallableDeclaration2 = firNamedFunction;
        JavaOverrideChecker javaOverrideChecker3 = javaOverrideChecker2;
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr2 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration2) || (firCallableDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration2) : null;
            if (originalForSubstitutionOverrideAttr2 == null) {
                originalForSubstitutionOverrideAttr2 = ClassMembersKt.isIntersectionOverride(firCallableDeclaration2) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration2) : null;
            }
            if (originalForSubstitutionOverrideAttr2 == null) {
                break;
            }
            javaOverrideChecker3 = this;
            firCallableDeclaration2 = originalForSubstitutionOverrideAttr2;
        }
        List<FirValueParameter> valueParameters2 = ((FirNamedFunction) firCallableDeclaration2).getValueParameters();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameters2, 10));
        Iterator<T> it2 = valueParameters2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((FirValueParameter) it2.next()).getReturnTypeRef());
        }
        int size = arrayList.size();
        int i = 0;
        JavaOverrideChecker javaOverrideChecker4 = javaOverrideChecker3;
        while (i < size) {
            FirTypeRef firTypeRef3 = (FirTypeRef) arrayList.get(i);
            FirTypeRef firTypeRef4 = listCollectValueParameterTypes.get(i);
            FirTypeRef firTypeRef5 = (FirTypeRef) CollectionsKt.getOrNull(listCollectValueParameterTypes2, i);
            if (firTypeRef5 == null || !javaOverrideChecker4.isTypeParameterDependent(firTypeRef5) || (firTypeRef2 = (FirTypeRef) CollectionsKt.getOrNull(arrayList2, i)) == null || !javaOverrideChecker4.isTypeParameterDependent(firTypeRef2)) {
                firTypeRef = firTypeRef4;
                z = false;
            } else {
                z = true;
                firTypeRef = firTypeRef4;
            }
            if (!javaOverrideChecker4.isEqualTypes(firTypeRef3, firTypeRef, coneSubstitutorBuildTypeParametersSubstitutorIfCompatible$default, zForceSingleValueParameterBoxing, zForceSingleValueParameterBoxing2, z)) {
                return false;
            }
            i++;
            javaOverrideChecker4 = this;
        }
        return true;
    }

    private final boolean isEqualArrayElementTypeProjections(ConeTypeProjection candidateTypeProjection, ConeTypeProjection baseTypeProjection, ConeSubstitutor substitutor) {
        if ((candidateTypeProjection instanceof ConeKotlinTypeProjection) && (baseTypeProjection instanceof ConeKotlinTypeProjection)) {
            return isEqualTypes(((ConeKotlinTypeProjection) candidateTypeProjection).getType(), ((ConeKotlinTypeProjection) baseTypeProjection).getType(), substitutor);
        }
        return (candidateTypeProjection instanceof ConeStarProjection) && (baseTypeProjection instanceof ConeStarProjection);
    }

    private final boolean isEqualTypes(ConeKotlinType candidateType, ConeKotlinType baseType, ConeSubstitutor substitutor) {
        if (candidateType instanceof ConeRawType) {
            return Intrinsics.areEqual(SignatureUtilsKt.computeJvmDescriptorRepresentation$default(candidateType, null, 1, null), SignatureUtilsKt.computeJvmDescriptorRepresentation$default(baseType, null, 1, null));
        }
        if (candidateType instanceof ConeFlexibleType) {
            return isEqualTypes(((ConeFlexibleType) candidateType).getLowerBound(), baseType, substitutor);
        }
        if (baseType instanceof ConeFlexibleType) {
            return isEqualTypes(candidateType, ((ConeFlexibleType) baseType).getLowerBound(), substitutor);
        }
        if (!(candidateType instanceof ConeClassLikeType) || !(baseType instanceof ConeClassLikeType)) {
            ConeTypeContext coneTypeContext = this.context;
            return coneTypeContext.areEqualTypeConstructors(coneTypeContext.typeConstructor(substitutor.substituteOrSelf(candidateType)), coneTypeContext.typeConstructor(substitutor.substituteOrSelf(baseType)));
        }
        ClassId classId = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) this, (ConeClassLikeType) candidateType).getLookupTag().getClassId();
        ClassId onlyToMutable = JavaTypeUtilsKt.readOnlyToMutable(classId);
        if (onlyToMutable != null) {
            classId = onlyToMutable;
        }
        ClassId classId2 = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) this, (ConeClassLikeType) baseType).getLookupTag().getClassId();
        ClassId onlyToMutable2 = JavaTypeUtilsKt.readOnlyToMutable(classId2);
        if (onlyToMutable2 != null) {
            classId2 = onlyToMutable2;
        }
        if (!Intrinsics.areEqual(classId, classId2)) {
            return false;
        }
        if (!Intrinsics.areEqual(classId, StandardClassIds.INSTANCE.getArray())) {
            return true;
        }
        int length = candidateType.getTypeArguments().length;
        int length2 = baseType.getTypeArguments().length;
        return isEqualArrayElementTypeProjections((ConeTypeProjection) ArraysKt.single(candidateType.getTypeArguments()), (ConeTypeProjection) ArraysKt.single(baseType.getTypeArguments()), substitutor);
    }

    private final boolean isPrimitiveInJava(ConeKotlinType coneKotlinType, boolean z) {
        if (this.context.isNullableType(coneKotlinType) || CompilerConeAttributesKt.getHasEnhancedNullability(coneKotlinType)) {
            return false;
        }
        return ConeBuiltinTypeUtilsKt.isPrimitiveOrNullablePrimitive(coneKotlinType) || (z && ConeBuiltinTypeUtilsKt.isUnit(coneKotlinType));
    }

    private final boolean isTypeParameterDependent(FirCallableDeclaration firCallableDeclaration) {
        if (!firCallableDeclaration.getTypeParameters().isEmpty() || isTypeParameterDependent(firCallableDeclaration.getReturnTypeRef())) {
            return true;
        }
        FirReceiverParameter receiverParameter = firCallableDeclaration.getReceiverParameter();
        if (isTypeParameterDependent(receiverParameter != null ? receiverParameter.getTypeRef() : null)) {
            return true;
        }
        if (!(firCallableDeclaration instanceof FirNamedFunction)) {
            return false;
        }
        List<FirValueParameter> valueParameters = ((FirNamedFunction) firCallableDeclaration).getValueParameters();
        if ((valueParameters instanceof Collection) && valueParameters.isEmpty()) {
            return false;
        }
        Iterator<T> it = valueParameters.iterator();
        while (it.hasNext()) {
            if (isTypeParameterDependent(((FirValueParameter) it.next()).getReturnTypeRef())) {
                return true;
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractOverrideChecker
    public ConeSubstitutor buildTypeParametersSubstitutorIfCompatible(FirCallableDeclaration overrideCandidate, FirCallableDeclaration baseDeclaration, boolean checkReifiednessIsSame) {
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        FirResolvePhase firResolvePhase = FirResolvePhase.TYPES;
        FirLazyDeclarationResolverKt.lazyResolveToPhase(overrideCandidate, firResolvePhase);
        FirLazyDeclarationResolverKt.lazyResolveToPhase(baseDeclaration, firResolvePhase);
        if (!isTypeParameterDependent(overrideCandidate) && !isTypeParameterDependent(baseDeclaration)) {
            return ConeSubstitutor.Empty.INSTANCE;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        extractTypeParametersTo(overrideCandidate, linkedHashSet);
        extractTypeParametersTo(baseDeclaration, linkedHashSet);
        return ConeSubstitutorByMapKt.substitutorByMap$default(buildErasure(linkedHashSet), getSession(), false, 4, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.scopes.FirOverrideChecker
    public Visibility chooseIntersectionVisibility(Collection<? extends FirCallableSymbol<?>> overrides, FirRegularClassSymbol dispatchClassSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        Object next;
        FirClassLikeSymbol<?> symbol;
        overrides.getClass();
        Visibility visibility = null;
        if ((dispatchClassSymbol != null ? (FirRegularClass) dispatchClassSymbol.getFir() : null) instanceof FirJavaClass) {
            Iterator<T> it = overrides.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                FirCallableSymbol firCallableSymbol = (FirCallableSymbol) next;
                if (!FirOverrideUtilsKt.isAbstractAccordingToRawStatus(firCallableSymbol)) {
                    ConeClassLikeLookupTag coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull = ClassMembersKt.dispatchReceiverClassLookupTagOrNull((FirCallableSymbol<?>) firCallableSymbol);
                    if (((coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull == null || (symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) this, coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull)) == null) ? null : FirHelpersKt.getClassKind(symbol)) == ClassKind.CLASS) {
                        break;
                    }
                }
            }
            FirCallableSymbol firCallableSymbol2 = (FirCallableSymbol) next;
            if (firCallableSymbol2 != null) {
                return firCallableSymbol2.getRawStatus().getVisibility();
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : overrides) {
            FirCallableSymbol firCallableSymbol3 = (FirCallableSymbol) obj;
            if (!FirOverrideUtilsKt.isAbstractAccordingToRawStatus(firCallableSymbol3) && !Intrinsics.areEqual(firCallableSymbol3.getCallableId(), StandardClassIds.Callables.INSTANCE.getClone())) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            Visibility visibility2 = Visibilities.Private.INSTANCE;
            Iterator<? extends FirCallableSymbol<?>> it2 = overrides.iterator();
            while (it2.hasNext()) {
                Object fir = it2.next().getFir();
                fir.getClass();
                Visibility visibility3 = ((FirMemberDeclaration) fir).getStatus().getVisibility();
                Integer numCompare = Visibilities.INSTANCE.compare(visibility3, visibility2);
                if (numCompare != null) {
                    if (numCompare.intValue() > 0) {
                        visibility2 = visibility3;
                    }
                }
            }
            visibility = visibility2;
        } else {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                linkedHashSet.add(((FirCallableSymbol) it3.next()).getRawStatus().getVisibility());
            }
            visibility = (Visibility) CollectionsKt.singleOrNull(linkedHashSet);
        }
        return visibility == null ? Visibilities.Unknown.INSTANCE : visibility;
    }

    public final boolean doesReturnTypesHaveSameKind(FirNamedFunction candidate, FirNamedFunction base) {
        candidate.getClass();
        base.getClass();
        FirTypeRef returnTypeRef = candidate.getReturnTypeRef();
        FirTypeRef returnTypeRef2 = base.getReturnTypeRef();
        FirSession session = getSession();
        JavaTypeParameterStack javaTypeParameterStack = getJavaTypeParameterStack();
        KtSourceElement source = returnTypeRef.getSource();
        ConeKotlinType coneKotlinTypeProbablyFlexible$default = JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(returnTypeRef, session, javaTypeParameterStack, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null, null, 8, null);
        FirSession session2 = getSession();
        JavaTypeParameterStack javaTypeParameterStack2 = getJavaTypeParameterStack();
        KtSourceElement source2 = returnTypeRef2.getSource();
        ConeKotlinType coneKotlinTypeProbablyFlexible$default2 = JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(returnTypeRef2, session2, javaTypeParameterStack2, source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null, null, 8, null);
        boolean zHasPrimitiveReturnTypeInJvm = hasPrimitiveReturnTypeInJvm(candidate, coneKotlinTypeProbablyFlexible$default);
        if (zHasPrimitiveReturnTypeInJvm != hasPrimitiveReturnTypeInJvm(base, coneKotlinTypeProbablyFlexible$default2)) {
            return false;
        }
        if (zHasPrimitiveReturnTypeInJvm) {
            return Intrinsics.areEqual(ConeTypeUtilsKt.getClassLikeLookupTagIfAny(coneKotlinTypeProbablyFlexible$default), ConeTypeUtilsKt.getClassLikeLookupTagIfAny(coneKotlinTypeProbablyFlexible$default2));
        }
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirOverrideChecker
    public boolean isOverriddenFunction(FirNamedFunction overrideCandidate, FirNamedFunction baseDeclaration) {
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        if (overrideCandidate.getStatus().isStatic() != baseDeclaration.getStatus().isStatic() || Visibilities.INSTANCE.isPrivate(baseDeclaration.getStatus().getVisibility())) {
            return false;
        }
        FirResolvePhase firResolvePhase = FirResolvePhase.TYPES;
        FirLazyDeclarationResolverKt.lazyResolveToPhase(overrideCandidate, firResolvePhase);
        FirLazyDeclarationResolverKt.lazyResolveToPhase(baseDeclaration, firResolvePhase);
        if (hasSameValueParameterTypes(overrideCandidate, baseDeclaration)) {
            return (Intrinsics.areEqual(overrideCandidate.getOrigin(), FirDeclarationOrigin.Java.Source.INSTANCE) && Intrinsics.areEqual(baseDeclaration.getOrigin(), FirDeclarationOrigin.Source.INSTANCE)) || !this.considerReturnTypeKinds || doesReturnTypesHaveSameKind(overrideCandidate, baseDeclaration);
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirOverrideChecker
    public boolean isOverriddenProperty(FirCallableDeclaration overrideCandidate, FirProperty baseDeclaration) {
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        if (baseDeclaration.getStatus().getModality() == Modality.FINAL || Visibilities.INSTANCE.isPrivate(baseDeclaration.getStatus().getVisibility())) {
            return false;
        }
        FirResolvePhase firResolvePhase = FirResolvePhase.TYPES;
        FirLazyDeclarationResolverKt.lazyResolveToPhase(overrideCandidate, firResolvePhase);
        FirLazyDeclarationResolverKt.lazyResolveToPhase(baseDeclaration, firResolvePhase);
        FirReceiverParameter receiverParameter = baseDeclaration.getReceiverParameter();
        FirTypeRef typeRef = receiverParameter != null ? receiverParameter.getTypeRef() : null;
        if (overrideCandidate instanceof FirNamedFunction) {
            if (typeRef == null) {
                return ((FirNamedFunction) overrideCandidate).getValueParameters().isEmpty();
            }
            FirNamedFunction firNamedFunction = (FirNamedFunction) overrideCandidate;
            if (firNamedFunction.getValueParameters().size() != 1) {
                return false;
            }
            return isEqualTypes(typeRef, ((FirValueParameter) CollectionsKt.single(firNamedFunction.getValueParameters())).getReturnTypeRef(), ConeSubstitutor.Empty.INSTANCE, false, false, false);
        }
        if (!(overrideCandidate instanceof FirProperty)) {
            return false;
        }
        FirReceiverParameter receiverParameter2 = ((FirProperty) overrideCandidate).getReceiverParameter();
        FirTypeRef typeRef2 = receiverParameter2 != null ? receiverParameter2.getTypeRef() : null;
        if (typeRef == null) {
            return typeRef2 == null;
        }
        if (typeRef2 == null) {
            return false;
        }
        return isEqualTypes(typeRef, typeRef2, ConeSubstitutor.Empty.INSTANCE, false, false, false);
    }

    private final void extractTypeParametersTo(FirTypeRef firTypeRef, Collection<FirTypeParameterRef> collection) {
        if (firTypeRef instanceof FirResolvedTypeRef) {
            extractTypeParametersTo(((FirResolvedTypeRef) firTypeRef).getConeType(), collection);
        }
    }

    private final void extractTypeParametersTo(FirCallableDeclaration firCallableDeclaration, Collection<FirTypeParameterRef> collection) {
        FirTypeRef typeRef;
        CollectionsKt.addAll(collection, firCallableDeclaration.getTypeParameters());
        extractTypeParametersTo(firCallableDeclaration.getReturnTypeRef(), collection);
        FirReceiverParameter receiverParameter = firCallableDeclaration.getReceiverParameter();
        if (receiverParameter != null && (typeRef = receiverParameter.getTypeRef()) != null) {
            extractTypeParametersTo(typeRef, collection);
        }
        if (firCallableDeclaration instanceof FirNamedFunction) {
            Iterator<T> it = ((FirNamedFunction) firCallableDeclaration).getValueParameters().iterator();
            while (it.hasNext()) {
                extractTypeParametersTo(((FirValueParameter) it.next()).getReturnTypeRef(), collection);
            }
        }
    }

    private final boolean isTypeParameterDependent(ConeKotlinType coneKotlinType) {
        if (coneKotlinType instanceof ConeFlexibleType) {
            return isTypeParameterDependent(((ConeFlexibleType) coneKotlinType).getLowerBound());
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return isTypeParameterDependent(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
        }
        if (coneKotlinType instanceof ConeTypeParameterType) {
            return true;
        }
        if (coneKotlinType instanceof ConeClassLikeType) {
            for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType.getTypeArguments()) {
                if ((coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) && isTypeParameterDependent(coneKotlinTypeProjection.getType())) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean isTypeParameterDependent(FirTypeRef firTypeRef) {
        return (firTypeRef instanceof FirResolvedTypeRef) && isTypeParameterDependent(((FirResolvedTypeRef) firTypeRef).getConeType());
    }

    private final boolean isEqualTypes(FirTypeRef candidateTypeRef, FirTypeRef baseTypeRef, ConeSubstitutor substitutor, boolean forceBoxCandidateType, boolean forceBoxBaseType, boolean dontComparePrimitivity) {
        FirSession session = getSession();
        JavaTypeParameterStack javaTypeParameterStack = getJavaTypeParameterStack();
        KtSourceElement source = candidateTypeRef.getSource();
        ConeKotlinType coneKotlinTypeProbablyFlexible$default = JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(candidateTypeRef, session, javaTypeParameterStack, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null, null, 8, null);
        FirSession session2 = getSession();
        JavaTypeParameterStack javaTypeParameterStack2 = getJavaTypeParameterStack();
        KtSourceElement source2 = baseTypeRef.getSource();
        ConeKotlinType coneKotlinTypeProbablyFlexible$default2 = JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(baseTypeRef, session2, javaTypeParameterStack2, source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null, null, 8, null);
        return (dontComparePrimitivity || (!forceBoxCandidateType && isPrimitiveInJava(coneKotlinTypeProbablyFlexible$default, false)) == (!forceBoxBaseType && isPrimitiveInJava(coneKotlinTypeProbablyFlexible$default2, false))) && isEqualTypes(coneKotlinTypeProbablyFlexible$default, coneKotlinTypeProbablyFlexible$default2, substitutor);
    }
}
