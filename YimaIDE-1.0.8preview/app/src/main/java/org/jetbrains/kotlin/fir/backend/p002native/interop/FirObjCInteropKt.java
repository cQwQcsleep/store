package org.jetbrains.kotlin.fir.backend.p002native.interop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.p002native.interop.FirObjCInteropKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.NativeStandardInteropNames;
import org.jetbrains.kotlin.native.interop.ObjCMethodInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a \u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n\u001a\u0018\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006*\u00020\f2\u0006\u0010\u0007\u001a\u00020\b\u001a\u001c\u0010\r\u001a\u0004\u0018\u00010\u0005*\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u001a\u0010\r\u001a\u0004\u0018\u00010\u0005*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u0014\u0010\u0010\u001a\u00020\u0011*\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002\u001a\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0002\u0010\u0015\u001a\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u0017*\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002\u001a\u0014\u0010\u0018\u001a\u00020\u0014*\u00020\u00192\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u0014\u0010\u001a\u001a\u00020\u0014*\u00020\u00192\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u0014\u0010\u001b\u001a\u00020\u0014*\u00020\u00192\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u0012\u0010\u001c\u001a\u00020\u0014*\u00020\f2\u0006\u0010\u0007\u001a\u00020\b\u001a\u0016\u0010\u001d\u001a\u00020\u0014*\u0006\u0012\u0002\b\u00030\u001e2\u0006\u0010\u0007\u001a\u00020\b\u001a,\u0010\u001f\u001a\u00020\u0014*\u0006\u0012\u0002\b\u00030\u001e2\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00140!H\u0002\u001a\u001e\u0010#\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u0012\u0010$\u001a\u00020\u0014*\u00020%2\u0006\u0010\u0007\u001a\u00020\b\u001a\u0018\u0010&\u001a\u00020\u0014*\u0006\u0012\u0002\b\u00030\u001e2\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\"\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0(*\u0006\u0012\u0002\b\u00030\u001e2\u0006\u0010\u0007\u001a\u00020\b\u001a\u0016\u0010+\u001a\u00020\u0014*\u0006\u0012\u0002\b\u00030\u001e2\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010,\u001a\u00020\u0014*\u00020-2\u0006\u0010\u0007\u001a\u00020\b\u001a\u0016\u0010.\u001a\u00020\u0014*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\u0016\u0010/\u001a\u00020\u0014*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\u0016\u00100\u001a\u00020\u0014*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"cCallClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "cCallDirectClassId", "cGlobalAccessClassId", "getObjCMethodInfoFromOverriddenFunctions", "Lorg/jetbrains/kotlin/native/interop/ObjCMethodInfo;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getObjCInitMethod", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "decodeObjCMethodAnnotation", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "constStringArgument", Argument.Delimiters.none, "argumentName", "constBooleanArgumentOrNull", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Ljava/lang/String;)Ljava/lang/Boolean;", "constArgument", Argument.Delimiters.none, "hasObjCFactoryAnnotation", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "hasObjCMethodAnnotation", "isObjCClassMethod", "isObjCConstructor", "isObjCClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "selfOrAnySuperClass", "predicate", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "getInitMethodIfObjCConstructor", "isExternalObjCClassProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "isExternalObjCClass", "parentsWithSelf", "Lkotlin/sequences/Sequence;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "isKotlinObjCClass", "isObjCObjectType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "isCFunctionOrGlobalAccessor", "isVariadicObjCMethod", "isObjCMethod", "org.jetbrains.kotlin:fir-native"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirObjCInteropKt {
    private static final ClassId cCallClassId;
    private static final ClassId cCallDirectClassId;
    private static final ClassId cGlobalAccessClassId;

    static {
        FqName fqName = new FqName("kotlinx.cinterop.internal");
        Name nameIdentifier = Name.identifier("CCall");
        nameIdentifier.getClass();
        cCallClassId = new ClassId(fqName, nameIdentifier);
        FqName fqName2 = new FqName("kotlinx.cinterop.internal");
        Name nameIdentifier2 = Name.identifier("CCall.Direct");
        nameIdentifier2.getClass();
        cCallDirectClassId = new ClassId(fqName2, nameIdentifier2);
        FqName fqName3 = new FqName("kotlinx.cinterop.internal");
        Name nameIdentifier3 = Name.identifier("CGlobalAccess");
        nameIdentifier3.getClass();
        cGlobalAccessClassId = new ClassId(fqName3, nameIdentifier3);
    }

    public static FirClassLikeSymbol a(FirSession firSession, FirClassLikeSymbol firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return DeclarationUtilsKt.getContainingDeclaration((FirClassLikeSymbol<? extends FirClassLikeDeclaration>) firClassLikeSymbol, firSession);
    }

    public static boolean b(ConeClassLikeLookupTag coneClassLikeLookupTag) {
        coneClassLikeLookupTag.getClass();
        return Intrinsics.areEqual(coneClassLikeLookupTag.getClassId(), NativeStandardInteropNames.INSTANCE.getObjCObjectClassId());
    }

    private static final Object constArgument(FirAnnotation firAnnotation, String str) {
        FirExpression firExpression = firAnnotation.getArgumentMapping().getMapping().get(Name.identifier(str));
        FirLiteralExpression firLiteralExpression = firExpression instanceof FirLiteralExpression ? (FirLiteralExpression) firExpression : null;
        if (firLiteralExpression != null) {
            return firLiteralExpression.getValue();
        }
        return null;
    }

    private static final Boolean constBooleanArgumentOrNull(FirAnnotation firAnnotation, String str) {
        return (Boolean) constArgument(firAnnotation, str);
    }

    private static final String constStringArgument(FirAnnotation firAnnotation, String str) {
        Object objConstArgument = constArgument(firAnnotation, str);
        String str2 = objConstArgument instanceof String ? (String) objConstArgument : null;
        if (str2 != null) {
            return str2;
        }
        a11.a("Expected string constant value of argument '", str, "' at annotation ", firAnnotation);
        return null;
    }

    public static final ObjCMethodInfo decodeObjCMethodAnnotation(List<? extends FirAnnotation> list, FirSession firSession) {
        list.getClass();
        firSession.getClass();
        NativeStandardInteropNames nativeStandardInteropNames = NativeStandardInteropNames.INSTANCE;
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(list, nativeStandardInteropNames.getObjCMethodClassId(), firSession);
        if (annotationByClassId == null) {
            return null;
        }
        String strConstStringArgument = constStringArgument(annotationByClassId, "selector");
        String strConstStringArgument2 = constStringArgument(annotationByClassId, "encoding");
        Boolean boolConstBooleanArgumentOrNull = constBooleanArgumentOrNull(annotationByClassId, "isStret");
        boolean zBooleanValue = boolConstBooleanArgumentOrNull != null ? boolConstBooleanArgumentOrNull.booleanValue() : false;
        FirAnnotation annotationByClassId2 = FirAnnotationUtilsKt.getAnnotationByClassId(list, nativeStandardInteropNames.getObjCDirectClassId(), firSession);
        return new ObjCMethodInfo(strConstStringArgument, strConstStringArgument2, zBooleanValue, annotationByClassId2 != null ? constStringArgument(annotationByClassId2, "symbol") : null);
    }

    public static final FirFunctionSymbol<?> getInitMethodIfObjCConstructor(FirFunctionSymbol<?> firFunctionSymbol, FirSession firSession) {
        firFunctionSymbol.getClass();
        firSession.getClass();
        if (!(firFunctionSymbol instanceof FirConstructorSymbol)) {
            return firFunctionSymbol;
        }
        FirConstructorSymbol firConstructorSymbol = (FirConstructorSymbol) firFunctionSymbol;
        return isObjCConstructor(firConstructorSymbol, firSession) ? getObjCInitMethod(firConstructorSymbol, firSession) : firFunctionSymbol;
    }

    public static final FirFunctionSymbol<?> getObjCInitMethod(FirConstructorSymbol firConstructorSymbol, final FirSession firSession) {
        firConstructorSymbol.getClass();
        firSession.getClass();
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firConstructorSymbol.getResolvedAnnotationsWithClassIds(), NativeStandardInteropNames.INSTANCE.getObjCConstructorClassId(), firSession);
        if (annotationByClassId == null) {
            return null;
        }
        final String strConstStringArgument = constStringArgument(annotationByClassId, "initSelector");
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firConstructorSymbol);
        FirClassLikeSymbol<?> symbol = coneClassLikeLookupTagContainingClassLookupTag != null ? ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTagContainingClassLookupTag, firSession) : null;
        symbol.getClass();
        FirClassSymbol firClassSymbol = (FirClassSymbol) symbol;
        final ArrayList arrayList = new ArrayList();
        FirContainingNamesAwareScopeKt.processAllFunctions(FirDeclaredMemberScopeProviderKt.declaredMemberScope(firSession, (FirClassSymbol<?>) firClassSymbol, (FirResolvePhase) null), new Function1() { // from class: kb5
            public final Object invoke(Object obj) {
                return FirObjCInteropKt.getObjCInitMethod$lambda$0$0(firSession, strConstStringArgument, arrayList, (FirNamedFunctionSymbol) obj);
            }
        });
        FirFunctionSymbol<?> firFunctionSymbol = (FirFunctionSymbol) CollectionsKt.singleOrNull(arrayList);
        if (firFunctionSymbol != null) {
            return firFunctionSymbol;
        }
        StringBuilder sb = new StringBuilder("expected one init method for ");
        sb.append(firClassSymbol);
        int size = arrayList.size();
        sb.append(' ');
        sb.append(strConstStringArgument);
        sb.append(", got ");
        sb.append(size);
        throw new IllegalStateException(sb.toString().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getObjCInitMethod$lambda$0$0(FirSession firSession, String str, List list, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        ObjCMethodInfo objCMethodInfoDecodeObjCMethodAnnotation = decodeObjCMethodAnnotation(firNamedFunctionSymbol, firSession);
        if (Intrinsics.areEqual(objCMethodInfoDecodeObjCMethodAnnotation != null ? objCMethodInfoDecodeObjCMethodAnnotation.getSelector() : null, str)) {
            list.add(firNamedFunctionSymbol);
        }
        return Unit.INSTANCE;
    }

    public static final ObjCMethodInfo getObjCMethodInfoFromOverriddenFunctions(FirFunctionSymbol<?> firFunctionSymbol, FirSession firSession, ScopeSession scopeSession) {
        firFunctionSymbol.getClass();
        firSession.getClass();
        scopeSession.getClass();
        ObjCMethodInfo objCMethodInfoDecodeObjCMethodAnnotation = decodeObjCMethodAnnotation(firFunctionSymbol, firSession);
        if (objCMethodInfoDecodeObjCMethodAnnotation != null) {
            return objCMethodInfoDecodeObjCMethodAnnotation;
        }
        if (firFunctionSymbol instanceof FirNamedFunctionSymbol) {
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firFunctionSymbol);
            FirClassSymbol firClassSymbol = (FirClassSymbol) (coneClassLikeLookupTagContainingClassLookupTag != null ? ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTagContainingClassLookupTag, firSession) : null);
            if (firClassSymbol != null) {
                FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) firFunctionSymbol;
                for (FirNamedFunctionSymbol firNamedFunctionSymbol2 : FirTypeScopeKt.getDirectOverriddenSafe(FirKotlinScopeProviderKt.unsubstitutedScope((FirClassSymbol<?>) firClassSymbol, firSession, scopeSession, false, (FirResolvePhase) null), firNamedFunctionSymbol)) {
                    if (Intrinsics.areEqual(firNamedFunctionSymbol2, firFunctionSymbol)) {
                        wec.a("Function ", firNamedFunctionSymbol.getName(), "() is wrongly contained in its own getDirectOverriddenFunctions");
                        break;
                    }
                    ObjCMethodInfo objCMethodInfoFromOverriddenFunctions = getObjCMethodInfoFromOverriddenFunctions(firNamedFunctionSymbol2, firSession, scopeSession);
                    if (objCMethodInfoFromOverriddenFunctions != null) {
                        return objCMethodInfoFromOverriddenFunctions;
                    }
                }
            }
        }
        return null;
    }

    public static final boolean hasObjCFactoryAnnotation(FirFunction firFunction, FirSession firSession) {
        firFunction.getClass();
        firSession.getClass();
        return FirAnnotationUtilsKt.hasAnnotation(firFunction.getAnnotations(), NativeStandardInteropNames.INSTANCE.getObjCFactoryClassId(), firSession);
    }

    public static final boolean hasObjCMethodAnnotation(FirFunction firFunction, FirSession firSession) {
        firFunction.getClass();
        firSession.getClass();
        return FirAnnotationUtilsKt.hasAnnotation(firFunction.getAnnotations(), NativeStandardInteropNames.INSTANCE.getObjCMethodClassId(), firSession);
    }

    public static final boolean isCFunctionOrGlobalAccessor(FirFunctionSymbol<?> firFunctionSymbol, FirSession firSession) {
        firFunctionSymbol.getClass();
        firSession.getClass();
        return FirAnnotationUtilsKt.hasAnnotation(firFunctionSymbol, cCallClassId, firSession) || FirAnnotationUtilsKt.hasAnnotation(firFunctionSymbol, cCallDirectClassId, firSession) || FirAnnotationUtilsKt.hasAnnotation(firFunctionSymbol, cGlobalAccessClassId, firSession);
    }

    public static final boolean isExternalObjCClass(FirClassSymbol<?> firClassSymbol, FirSession firSession) {
        firClassSymbol.getClass();
        firSession.getClass();
        if (!isObjCClass(firClassSymbol, firSession)) {
            return false;
        }
        Sequence sequenceFilter = SequencesKt.filter(parentsWithSelf(firClassSymbol, firSession), new Function1<Object, Boolean>() { // from class: org.jetbrains.kotlin.fir.backend.native.interop.FirObjCInteropKt$isExternalObjCClass$$inlined$filterIsInstance$1
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m257invoke(Object obj) {
                return Boolean.valueOf(obj instanceof FirClassSymbol);
            }
        });
        sequenceFilter.getClass();
        Iterator it = sequenceFilter.iterator();
        while (it.hasNext()) {
            if (FirAnnotationUtilsKt.hasAnnotation((FirClassSymbol) it.next(), NativeStandardInteropNames.INSTANCE.getExternalObjCClassClassId(), firSession)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isExternalObjCClassProperty(FirProperty firProperty, FirSession firSession) {
        FirClassSymbol<?> classSymbol;
        firProperty.getClass();
        firSession.getClass();
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firProperty);
        return (coneClassLikeLookupTagContainingClassLookupTag == null || (classSymbol = ToSymbolUtilsKt.toClassSymbol(coneClassLikeLookupTagContainingClassLookupTag, firSession)) == null || !isExternalObjCClass(classSymbol, firSession)) ? false : true;
    }

    public static final boolean isKotlinObjCClass(FirClassSymbol<?> firClassSymbol, FirSession firSession) {
        firClassSymbol.getClass();
        firSession.getClass();
        return isObjCClass(firClassSymbol, firSession) && !isExternalObjCClass(firClassSymbol, firSession);
    }

    public static final boolean isObjCClass(FirClassSymbol<?> firClassSymbol, FirSession firSession) {
        firClassSymbol.getClass();
        firSession.getClass();
        return !Intrinsics.areEqual(firClassSymbol.getClassId().getPackageFqName(), NativeStandardInteropNames.INSTANCE.getCInteropPackage()) && selfOrAnySuperClass(firClassSymbol, firSession, new Function1() { // from class: jb5
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirObjCInteropKt.b((ConeClassLikeLookupTag) obj));
            }
        });
    }

    public static final boolean isObjCClassMethod(FirFunction firFunction, FirSession firSession) {
        firFunction.getClass();
        firSession.getClass();
        FirRegularClass containingClass = ResolveUtilsKt.getContainingClass(firFunction);
        return containingClass != null && isObjCClass(containingClass.getSymbol(), firSession);
    }

    public static final boolean isObjCConstructor(FirConstructorSymbol firConstructorSymbol, FirSession firSession) {
        firConstructorSymbol.getClass();
        firSession.getClass();
        return FirAnnotationUtilsKt.hasAnnotation(firConstructorSymbol.getResolvedAnnotationsWithClassIds(), NativeStandardInteropNames.INSTANCE.getObjCConstructorClassId(), firSession);
    }

    public static final boolean isObjCMethod(FirFunctionSymbol<?> firFunctionSymbol, FirSession firSession) {
        firFunctionSymbol.getClass();
        firSession.getClass();
        if (firFunctionSymbol instanceof FirConstructorSymbol) {
            FirConstructorSymbol firConstructorSymbol = (FirConstructorSymbol) firFunctionSymbol;
            if (isObjCConstructor(firConstructorSymbol, firSession)) {
                FirFunctionSymbol<?> objCInitMethod = getObjCInitMethod(firConstructorSymbol, firSession);
                return (objCInitMethod == null || decodeObjCMethodAnnotation(objCInitMethod, firSession) == null) ? false : true;
            }
        }
        return (firFunctionSymbol instanceof FirNamedFunctionSymbol) && (FirAnnotationUtilsKt.hasAnnotation(firFunctionSymbol, NativeStandardInteropNames.INSTANCE.getObjCFactoryClassId(), firSession) || decodeObjCMethodAnnotation(firFunctionSymbol, firSession) != null);
    }

    public static final boolean isObjCObjectType(FirTypeRef firTypeRef, FirSession firSession) {
        firTypeRef.getClass();
        firSession.getClass();
        FirClassLikeDeclaration firClassLikeDeclarationFirClassLike = DeclarationUtilsKt.firClassLike(firTypeRef, firSession);
        FirClassLikeSymbol<FirClassLikeDeclaration> symbol = firClassLikeDeclarationFirClassLike != null ? firClassLikeDeclarationFirClassLike.getSymbol() : null;
        return (symbol instanceof FirClassSymbol) && isObjCClass((FirClassSymbol) symbol, firSession);
    }

    public static final boolean isVariadicObjCMethod(FirFunctionSymbol<?> firFunctionSymbol, FirSession firSession) {
        firFunctionSymbol.getClass();
        firSession.getClass();
        List<FirValueParameterSymbol> valueParameterSymbols = firFunctionSymbol.getValueParameterSymbols();
        if ((valueParameterSymbols instanceof Collection) && valueParameterSymbols.isEmpty()) {
            return false;
        }
        Iterator<T> it = valueParameterSymbols.iterator();
        while (it.hasNext()) {
            if (((FirValueParameterSymbol) it.next()).isVararg()) {
                return isObjCMethod(firFunctionSymbol, firSession);
            }
        }
        return false;
    }

    public static final Sequence<FirClassLikeSymbol<FirClassLikeDeclaration>> parentsWithSelf(FirClassSymbol<?> firClassSymbol, final FirSession firSession) {
        firClassSymbol.getClass();
        firSession.getClass();
        return SequencesKt.generateSequence(firClassSymbol, new Function1() { // from class: ib5
            public final Object invoke(Object obj) {
                return FirObjCInteropKt.a(firSession, (FirClassLikeSymbol) obj);
            }
        });
    }

    private static final boolean selfOrAnySuperClass(FirClassSymbol<?> firClassSymbol, FirSession firSession, Function1<? super ConeClassLikeLookupTag, Boolean> function1) {
        if (((Boolean) function1.invoke(firClassSymbol.getLookupTag())).booleanValue()) {
            return true;
        }
        List listLookupSuperTypes$default = SupertypeUtilsKt.lookupSuperTypes$default(CollectionsKt.listOf(firClassSymbol), true, true, firSession, false, null, null, 96, null);
        if ((listLookupSuperTypes$default instanceof Collection) && listLookupSuperTypes$default.isEmpty()) {
            return false;
        }
        Iterator it = listLookupSuperTypes$default.iterator();
        while (it.hasNext()) {
            if (((Boolean) function1.invoke(((ConeClassLikeType) it.next()).getLookupTag())).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public static final ObjCMethodInfo decodeObjCMethodAnnotation(FirFunctionSymbol<?> firFunctionSymbol, FirSession firSession) {
        firFunctionSymbol.getClass();
        firSession.getClass();
        return decodeObjCMethodAnnotation(firFunctionSymbol.getResolvedAnnotationsWithClassIds(), firSession);
    }
}
