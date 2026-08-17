package org.jetbrains.kotlin.fir.resolve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.diagnostics.ConeCannotInferTypeParameterType;
import org.jetbrains.kotlin.fir.diagnostics.ConeIntermediateDiagnostic;
import org.jetbrains.kotlin.fir.resolve.FirSamResolverKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDelegatedMemberScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeKindServiceKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a%\u0010\u0002\u001a\u00020\u0003*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\t\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0002\u001a\u001f\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u0011H\u0002R\u00020\u0010j\u0006\u0010\u0005\u001a\u00020\u0010¢\u0006\u0002\u0010\u0012\u001a#\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0014*\u00020\u0011H\u0002R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0015\u001a-\u0010\u0016\u001a\u0004\u0018\u00010\u000f*\u00020\u00112\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0014H\u0002R\u00020\u0010j\u0006\u0010\u0005\u001a\u00020\u0010¢\u0006\u0002\u0010\u0018\u001a\f\u0010\u0019\u001a\u00020\u000b*\u00020\u0011H\u0002\u001a\u0014\u0010\u001e\u001a\u00020\u000b*\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u000bH\u0002\u001a\u0014\u0010\"\u001a\u00020#*\u00020\u000f2\u0006\u0010$\u001a\u00020%H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\u001a\u001a\u00020\u000b*\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d\"\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0014X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010&\u001a\u00020'*\u00020%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b(\u0010)¨\u0006,"}, d2 = {"SAM_PARAMETER_NAME", "Lorg/jetbrains/kotlin/name/Name;", "buildSubstitutorWithUpperBounds", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "c", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;)Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "containsReferenceToOtherTypeParameter", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "owner", "getSingleAbstractMethodOrNull", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "computeSamCandidateNames", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Ljava/util/Set;", "findSingleAbstractMethodByNames", "samCandidateNames", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Ljava/util/Set;)Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "hasMoreThenOneAbstractFunctionOrHasAbstractProperty", "resolvedIsAbstract", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "getResolvedIsAbstract", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Z", "isPublicInObject", "checkOnlyName", "PUBLIC_METHOD_NAMES_IN_OBJECT", Argument.Delimiters.none, "getFunctionTypeForAbstractMethod", "Lorg/jetbrains/kotlin/fir/types/ConeLookupTagBasedType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "samConstructorStorage", "Lorg/jetbrains/kotlin/fir/resolve/FirSamConstructorStorage;", "getSamConstructorStorage", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/resolve/FirSamConstructorStorage;", "samConstructorStorage$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSamResolverKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirSamResolverKt.class, "samConstructorStorage", "getSamConstructorStorage(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/resolve/FirSamConstructorStorage;", 1)};
    private static final Set<String> PUBLIC_METHOD_NAMES_IN_OBJECT;
    private static final Name SAM_PARAMETER_NAME;
    private static final ArrayMapAccessor samConstructorStorage$delegate;

    static {
        Name nameIdentifier = Name.identifier("function");
        nameIdentifier.getClass();
        SAM_PARAMETER_NAME = nameIdentifier;
        PUBLIC_METHOD_NAMES_IN_OBJECT = SetsKt.setOf(new String[]{"equals", "hashCode", "getClass", "wait", "notify", "notifyAll", "toString"});
        samConstructorStorage$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirSamConstructorStorage.class), (Object) null, 2, (Object) null);
    }

    public static boolean a(FirTypeParameterRefsOwner firTypeParameterRefsOwner, ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        if (!(coneKotlinType instanceof ConeTypeParameterType)) {
            return false;
        }
        List<FirTypeParameterRef> typeParameters = firTypeParameterRefsOwner.getTypeParameters();
        if ((typeParameters instanceof Collection) && typeParameters.isEmpty()) {
            return false;
        }
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((FirTypeParameterRef) it.next()).getSymbol(), ((ConeTypeParameterType) coneKotlinType).getLookupTag().getTypeParameterSymbol())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit b(Ref.ObjectRef objectRef, Ref.BooleanRef booleanRef, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        FirNamedFunction firNamedFunction = (FirNamedFunction) firNamedFunctionSymbol.getFir();
        if (!getResolvedIsAbstract(firNamedFunction) || isPublicInObject(firNamedFunction, false)) {
            return Unit.INSTANCE;
        }
        if (objectRef.element != null) {
            booleanRef.element = true;
        } else {
            objectRef.element = firNamedFunction;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final ConeSubstitutor buildSubstitutorWithUpperBounds(SessionHolder sessionHolder, FirTypeParameterRefsOwner firTypeParameterRefsOwner, ConeClassLikeType coneClassLikeType) {
        ConeKotlinType coneTypeOrNull;
        if (firTypeParameterRefsOwner.getTypeParameters().isEmpty()) {
            return ConeSubstitutor.Empty.INSTANCE;
        }
        List listZip = CollectionsKt.zip(firTypeParameterRefsOwner.getTypeParameters(), coneClassLikeType.getTypeArguments());
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listZip, 10)), 16));
        Iterator it = listZip.iterator();
        while (true) {
            ConeKotlinType coneErrorType = null;
            if (!it.hasNext()) {
                return ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap, sessionHolder.getSession(), false, 4, null);
            }
            Pair pair = (Pair) it.next();
            FirTypeParameterRef firTypeParameterRef = (FirTypeParameterRef) pair.component1();
            ConeKotlinType type = ConeTypeProjectionKt.getType((ConeTypeProjection) pair.component2());
            if (type == null) {
                FirTypeRef firTypeRef = (FirTypeRef) CollectionsKt.firstOrNull(((FirTypeParameter) firTypeParameterRef.getSymbol().getFir()).getBounds());
                if (firTypeRef != null && (coneTypeOrNull = FirTypeUtilsKt.getConeTypeOrNull(firTypeRef)) != null) {
                    if (containsReferenceToOtherTypeParameter(coneTypeOrNull, firTypeParameterRefsOwner)) {
                        coneErrorType = new ConeErrorType(new ConeCannotInferTypeParameterType(firTypeParameterRef.getSymbol(), "Parameter " + firTypeParameterRef.getSymbol().getName() + " has a cycle in its upper bounds"), false, null, null, null, null, null, 126, null);
                    } else {
                        coneErrorType = coneTypeOrNull;
                    }
                }
                type = coneErrorType == null ? sessionHolder.getSession().getBuiltinTypes().getNullableAnyType().getConeType() : coneErrorType;
            }
            Pair pair2 = new Pair(firTypeParameterRef.getSymbol(), type);
            linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit c(Ref.BooleanRef booleanRef, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if ((firVariableSymbol instanceof FirPropertySymbol) && getResolvedIsAbstract((FirCallableDeclaration) ((FirPropertySymbol) firVariableSymbol).getFir())) {
            booleanRef.element = true;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final Set<Name> computeSamCandidateNames(SessionHolder sessionHolder, FirRegularClass firRegularClass) {
        List listLookupSuperTypes$default = SupertypeUtilsKt.lookupSuperTypes$default(firRegularClass, true, true, sessionHolder.getSession(), false, null, 32, null);
        List listMutableListOf = CollectionsKt.mutableListOf(new FirRegularClass[]{firRegularClass});
        Iterator it = listLookupSuperTypes$default.iterator();
        while (it.hasNext()) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(sessionHolder, ((ConeClassLikeType) it.next()).getLookupTag());
            FirRegularClass firRegularClass2 = regularClassSymbol != null ? (FirRegularClass) regularClassSymbol.getFir() : null;
            if (firRegularClass2 != null) {
                listMutableListOf.add(firRegularClass2);
            }
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it2 = listMutableListOf.iterator();
        while (it2.hasNext()) {
            for (FirDeclaration firDeclaration : ((FirRegularClass) it2.next()).getDeclarations()) {
                if (firDeclaration instanceof FirProperty) {
                    if (getResolvedIsAbstract((FirCallableDeclaration) firDeclaration)) {
                        linkedHashSet.add(((FirProperty) firDeclaration).getName());
                    }
                } else if ((firDeclaration instanceof FirNamedFunction) && getResolvedIsAbstract((FirCallableDeclaration) firDeclaration)) {
                    linkedHashSet.add(((FirNamedFunction) firDeclaration).getName());
                }
            }
        }
        return linkedHashSet;
    }

    private static final boolean containsReferenceToOtherTypeParameter(ConeKotlinType coneKotlinType, final FirTypeParameterRefsOwner firTypeParameterRefsOwner) {
        return ConeTypeUtilsKt.contains(coneKotlinType, new Function1() { // from class: oc5
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirSamResolverKt.a(firTypeParameterRefsOwner, (ConeKotlinType) obj));
            }
        });
    }

    private static final FirNamedFunction findSingleAbstractMethodByNames(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirRegularClass firRegularClass, Set<Name> set) {
        Object obj;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        FirTypeScope firTypeScopeUnsubstitutedScope = FirKotlinScopeProviderKt.unsubstitutedScope(sessionAndScopeSessionHolder, (FirClass) firRegularClass, false, (FirResolvePhase) null);
        for (Name name : set) {
            if (booleanRef.element) {
                break;
            }
            firTypeScopeUnsubstitutedScope.processPropertiesByName(name, new Function1() { // from class: mc5
                public final Object invoke(Object obj2) {
                    return FirSamResolverKt.c(booleanRef, (FirVariableSymbol) obj2);
                }
            });
            if (booleanRef.element) {
                break;
            }
            firTypeScopeUnsubstitutedScope.processFunctionsByName(name, new Function1() { // from class: nc5
                public final Object invoke(Object obj2) {
                    return FirSamResolverKt.b(objectRef, booleanRef, (FirNamedFunctionSymbol) obj2);
                }
            });
        }
        if (booleanRef.element || (obj = objectRef.element) == null || !((FirNamedFunction) obj).getTypeParameters().isEmpty()) {
            return null;
        }
        return (FirNamedFunction) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConeLookupTagBasedType getFunctionTypeForAbstractMethod(FirNamedFunction firNamedFunction, FirSession firSession) {
        ConeKotlinType coneType;
        FirTypeRef typeRef;
        List<FirValueParameter> valueParameters = firNamedFunction.getValueParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameters, 10));
        Iterator<T> it = valueParameters.iterator();
        while (true) {
            coneType = null;
            if (!it.hasNext()) {
                break;
            }
            FirValueParameter firValueParameter = (FirValueParameter) it.next();
            FirResolvedTypeRef returnTypeRef = firValueParameter.getReturnTypeRef();
            FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
            ConeKotlinType coneType2 = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            coneType = coneType2 != null ? coneType2 : null;
            if (coneType == null) {
                coneType = new ConeErrorType(new ConeIntermediateDiagnostic("No type for parameter " + firValueParameter), false, null, null, null, null, null, 126, null);
            }
            arrayList.add(coneType);
        }
        List<FirValueParameter> contextParameters = firNamedFunction.getContextParameters();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameters, 10));
        for (FirValueParameter firValueParameter2 : contextParameters) {
            FirResolvedTypeRef returnTypeRef2 = firValueParameter2.getReturnTypeRef();
            FirResolvedTypeRef firResolvedTypeRef2 = returnTypeRef2 instanceof FirResolvedTypeRef ? returnTypeRef2 : null;
            ConeKotlinType coneType3 = firResolvedTypeRef2 != null ? firResolvedTypeRef2.getConeType() : null;
            if (coneType3 == null) {
                coneType3 = null;
            }
            if (coneType3 == null) {
                coneType3 = new ConeErrorType(new ConeIntermediateDiagnostic("No type for context receiver " + firValueParameter2), false, null, null, null, null, null, 126, null);
            }
            arrayList2.add(coneType3);
        }
        FunctionTypeKind.Function functionExtractSingleSpecialKindForFunction = FirFunctionTypeKindServiceKt.getFunctionTypeService(firSession).extractSingleSpecialKindForFunction(firNamedFunction.getSymbol());
        if (functionExtractSingleSpecialKindForFunction == null) {
            functionExtractSingleSpecialKindForFunction = FunctionTypeKind.Function.INSTANCE;
        }
        FirReceiverParameter receiverParameter = firNamedFunction.getReceiverParameter();
        if (receiverParameter != null && (typeRef = receiverParameter.getTypeRef()) != null) {
            coneType = FirTypeUtilsKt.getConeType(typeRef);
        }
        return ResolveUtilsKt.createFunctionType(functionExtractSingleSpecialKindForFunction, arrayList, coneType, FirTypeUtilsKt.getConeType(firNamedFunction.getReturnTypeRef()), arrayList2);
    }

    private static final boolean getResolvedIsAbstract(FirCallableDeclaration firCallableDeclaration) {
        return firCallableDeclaration.getSymbol().getResolvedStatus().getModality() == Modality.ABSTRACT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirSamConstructorStorage getSamConstructorStorage(FirSession firSession) {
        return (FirSamConstructorStorage) samConstructorStorage$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirNamedFunction getSingleAbstractMethodOrNull(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirRegularClass firRegularClass) {
        if (firRegularClass.getClassKind() != ClassKind.INTERFACE || hasMoreThenOneAbstractFunctionOrHasAbstractProperty(firRegularClass)) {
            return null;
        }
        return findSingleAbstractMethodByNames(sessionAndScopeSessionHolder, firRegularClass, computeSamCandidateNames(sessionAndScopeSessionHolder, firRegularClass));
    }

    private static final boolean hasMoreThenOneAbstractFunctionOrHasAbstractProperty(FirRegularClass firRegularClass) {
        boolean z = false;
        for (FirDeclaration firDeclaration : firRegularClass.getDeclarations()) {
            if ((firDeclaration instanceof FirProperty) && getResolvedIsAbstract((FirCallableDeclaration) firDeclaration)) {
                return true;
            }
            if ((firDeclaration instanceof FirNamedFunction) && getResolvedIsAbstract((FirCallableDeclaration) firDeclaration) && !isPublicInObject((FirNamedFunction) firDeclaration, true)) {
                if (z) {
                    return true;
                }
                z = true;
            }
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0034, code lost:
    
        if (r5.equals("getClass") != false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
    
        if (r5.equals("notifyAll") != false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
    
        if (r5.equals("hashCode") != false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00ac, code lost:
    
        if (r5.equals("notify") != false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00d7, code lost:
    
        if (r5.equals("toString") != false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00e1, code lost:
    
        return r4.getValueParameters().isEmpty();
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final boolean isPublicInObject(FirNamedFunction firNamedFunction, boolean z) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (!org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.isJavaOrEnhancement(firNamedFunction) || !PUBLIC_METHOD_NAMES_IN_OBJECT.contains(firNamedFunction.getName().asString())) {
            return false;
        }
        if (z) {
            return true;
        }
        String strAsString = firNamedFunction.getName().asString();
        switch (strAsString.hashCode()) {
            case -1776922004:
                break;
            case -1295482945:
                if (strAsString.equals("equals")) {
                    FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.singleOrNull(firNamedFunction.getValueParameters());
                    return firValueParameter != null && FirDelegatedMemberScopeKt.hasTypeOf(firValueParameter, StandardClassIds.INSTANCE.getAny(), true);
                }
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unexpected method name", (Throwable) null);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                exceptionAttachmentBuilder.withEntry("methodName", firNamedFunction.getName().asString());
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            case -1039689911:
                break;
            case 3641717:
                if (strAsString.equals("wait")) {
                    int size = firNamedFunction.getValueParameters().size();
                    if (size == 0) {
                        return true;
                    }
                    if (size == 1) {
                        return FirDelegatedMemberScopeKt.hasTypeOf(firNamedFunction.getValueParameters().get(0), StandardClassIds.INSTANCE.getLong(), false);
                    }
                    if (size != 2) {
                        return false;
                    }
                    FirValueParameter firValueParameter2 = firNamedFunction.getValueParameters().get(0);
                    StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
                    return FirDelegatedMemberScopeKt.hasTypeOf(firValueParameter2, standardClassIds.getLong(), false) && FirDelegatedMemberScopeKt.hasTypeOf(firNamedFunction.getValueParameters().get(1), standardClassIds.getInt(), false);
                }
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments2 = new KotlinIllegalArgumentExceptionWithAttachments("Unexpected method name", (Throwable) null);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder2 = new ExceptionAttachmentBuilder();
                exceptionAttachmentBuilder2.withEntry("methodName", firNamedFunction.getName().asString());
                kotlinIllegalArgumentExceptionWithAttachments2.withAttachment("info.txt", exceptionAttachmentBuilder2.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments2;
            case 147696667:
                break;
            case 1902066072:
                break;
            case 1950568386:
                break;
            default:
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments3 = new KotlinIllegalArgumentExceptionWithAttachments("Unexpected method name", (Throwable) null);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder3 = new ExceptionAttachmentBuilder();
                exceptionAttachmentBuilder3.withEntry("methodName", firNamedFunction.getName().asString());
                kotlinIllegalArgumentExceptionWithAttachments3.withAttachment("info.txt", exceptionAttachmentBuilder3.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments3;
        }
    }
}
