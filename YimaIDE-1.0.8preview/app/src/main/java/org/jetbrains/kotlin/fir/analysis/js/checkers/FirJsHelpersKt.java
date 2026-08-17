package org.jetbrains.kotlin.fir.analysis.js.checkers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirWebCommonHelpersKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.js.PredefinedAnnotation;
import org.jetbrains.kotlin.js.common.IdentifierPolicyKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.JsStandardClassIds;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001f\u0010\u0005\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\b\u001a\u001f\u0010\t\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\nR\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u000b\u001a\u0018\u0010\f\u001a\u0004\u0018\u00010\r*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r\u001a\u0016\u0010\u0010\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0016\u0010\u0011\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0016\u0010\u0015\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0016\u0010\u0016\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0018*\u0006\u0012\u0002\b\u00030\u0002H\u0000\u001a\u001f\u0010\u0019\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\b\u001a\u001f\u0010\u001a\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\b\u001a\u001f\u0010\u0015\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\b\u001a\u001f\u0010\u0016\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\b\u001a\u001f\u0010\u0010\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\b\u001a\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u001c*\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a \u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u00022\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\"\u001c\u0010\u0012\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\"\u001c\u0010\u0014\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0013¨\u0006 "}, d2 = {"isEffectivelyExternalMember", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isEffectivelyExternal", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "isOverridingExternalWithOptionalParams", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;)Z", "getJsName", Argument.Delimiters.none, "sanitizeName", ModuleXmlParser.NAME, "isLibraryObject", "isPresentInGeneratedCode", "isExpect", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "isActual", "isPredefinedObject", "isExportedObject", "getContainingFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "isNativeObject", "isNativeInterface", "superClassNotAny", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "getRootClassLikeSymbolOrSelf", "symbol", "org.jetbrains.kotlin:checkers.js"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsHelpersKt {
    public static final FirFile getContainingFile(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        if (firBasedSymbol instanceof FirCallableSymbol) {
            FirCallableSymbol<?> firCallableSymbol = (FirCallableSymbol) firBasedSymbol;
            return FirProviderKt.getFirProvider(firCallableSymbol.getModuleData().getSession()).getFirCallableContainerFile(firCallableSymbol);
        }
        if (!(firBasedSymbol instanceof FirClassLikeSymbol)) {
            return null;
        }
        FirClassLikeSymbol<?> firClassLikeSymbol = (FirClassLikeSymbol) firBasedSymbol;
        return FirProviderKt.getFirProvider(firClassLikeSymbol.getModuleData().getSession()).getFirClassifierContainerFileIfAny(firClassLikeSymbol);
    }

    public static final String getJsName(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        firBasedSymbol.getClass();
        firSession.getClass();
        return FirHelpersKt.getAnnotationStringParameter(firBasedSymbol, JsStandardClassIds.Annotations.JsName, firSession);
    }

    public static final FirBasedSymbol<?> getRootClassLikeSymbolOrSelf(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        FirBasedSymbol<?> rootClassLikeSymbolOrSelf;
        firBasedSymbol.getClass();
        firSession.getClass();
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firBasedSymbol);
        return (containingClassSymbol == null || (rootClassLikeSymbolOrSelf = getRootClassLikeSymbolOrSelf(containingClassSymbol, firSession)) == null) ? firBasedSymbol : rootClassLikeSymbolOrSelf;
    }

    public static final boolean isActual(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        if (firBasedSymbol instanceof FirCallableSymbol) {
            return ((FirCallableSymbol) firBasedSymbol).getRawStatus().isActual();
        }
        if (firBasedSymbol instanceof FirClassSymbol) {
            return ((FirClassLikeSymbol) firBasedSymbol).getRawStatus().isActual();
        }
        return false;
    }

    public static final boolean isEffectivelyExternal(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol) {
        checkerContext.getClass();
        firBasedSymbol.getClass();
        return FirWebCommonHelpersKt.isEffectivelyExternal(firBasedSymbol, checkerContext.getSession());
    }

    public static final boolean isEffectivelyExternalMember(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        firBasedSymbol.getClass();
        firSession.getClass();
        return (firBasedSymbol.getFir() instanceof FirMemberDeclaration) && FirWebCommonHelpersKt.isEffectivelyExternal(firBasedSymbol, firSession);
    }

    public static final boolean isExpect(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        if (firBasedSymbol instanceof FirCallableSymbol) {
            return ((FirCallableSymbol) firBasedSymbol).getRawStatus().isExpect();
        }
        if (firBasedSymbol instanceof FirClassSymbol) {
            return ((FirClassLikeSymbol) firBasedSymbol).getRawStatus().isExpect();
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final boolean isExportedObject(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirFileSymbol symbol;
        firBasedSymbol.getClass();
        firSession.getClass();
        FirDeclaration fir = firBasedSymbol.getFir();
        if (fir instanceof FirMemberDeclaration) {
            Visibility visibility = ((FirMemberDeclaration) fir).getStatus().getVisibility();
            if (!Intrinsics.areEqual(visibility, Visibilities.Public.INSTANCE) && !Intrinsics.areEqual(visibility, Visibilities.Protected.INSTANCE)) {
                return false;
            }
        }
        if (FirHelpersKt.hasAnnotationOrInsideAnnotatedClass(firBasedSymbol, JsStandardClassIds.Annotations.JsExportIgnore, firSession)) {
            return false;
        }
        ClassId classId = JsStandardClassIds.Annotations.JsExport;
        if (FirHelpersKt.hasAnnotationOrInsideAnnotatedClass(firBasedSymbol, classId, firSession) || Intrinsics.areEqual(FirHelpersKt.getAnnotationBooleanParameter(firBasedSymbol, JsStandardClassIds.Annotations.JsImplicitExport, firSession), Boolean.TRUE) || FirHelpersKt.hasAnnotationOrInsideAnnotatedClass(firBasedSymbol, JsStandardClassIds.Annotations.JsExportDefault, firSession)) {
            return true;
        }
        FirFile containingFile = getContainingFile(firBasedSymbol);
        if (containingFile == null || (symbol = containingFile.getSymbol()) == null) {
            return false;
        }
        return FirAnnotationUtilsKt.hasAnnotation(symbol, classId, firSession);
    }

    public static final boolean isLibraryObject(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol) {
        checkerContext.getClass();
        firBasedSymbol.getClass();
        return isLibraryObject(firBasedSymbol, checkerContext.getSession());
    }

    public static final boolean isNativeInterface(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol) {
        checkerContext.getClass();
        firBasedSymbol.getClass();
        return FirWebCommonHelpersKt.isNativeInterface(firBasedSymbol, checkerContext.getSession());
    }

    public static final boolean isNativeObject(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol) {
        checkerContext.getClass();
        firBasedSymbol.getClass();
        return FirWebCommonHelpersKt.isNativeObject(firBasedSymbol, checkerContext.getSession());
    }

    public static final boolean isOverridingExternalWithOptionalParams(CheckerContext checkerContext, FirFunctionSymbol<?> firFunctionSymbol) {
        List<FirNamedFunctionSymbol> listDirectOverriddenFunctionsSafe;
        checkerContext.getClass();
        firFunctionSymbol.getClass();
        if (!ClassMembersKt.isSubstitutionOrIntersectionOverride(firFunctionSymbol) && firFunctionSymbol.getResolvedStatus().getModality() == Modality.ABSTRACT) {
            return false;
        }
        FirNamedFunctionSymbol firNamedFunctionSymbol = firFunctionSymbol instanceof FirNamedFunctionSymbol ? (FirNamedFunctionSymbol) firFunctionSymbol : null;
        if (firNamedFunctionSymbol != null && (listDirectOverriddenFunctionsSafe = FirHelpersKt.directOverriddenFunctionsSafe(checkerContext, firNamedFunctionSymbol)) != null) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : listDirectOverriddenFunctionsSafe) {
                if (isEffectivelyExternal(checkerContext, (FirNamedFunctionSymbol) obj)) {
                    arrayList.add(obj);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                List<FirValueParameterSymbol> valueParameterSymbols = ((FirNamedFunctionSymbol) it.next()).getValueParameterSymbols();
                if (!(valueParameterSymbols instanceof Collection) || !valueParameterSymbols.isEmpty()) {
                    Iterator<T> it2 = valueParameterSymbols.iterator();
                    while (it2.hasNext()) {
                        if (((FirValueParameterSymbol) it2.next()).getHasDefaultValue()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static final boolean isPredefinedObject(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        firBasedSymbol.getClass();
        firSession.getClass();
        if (((firBasedSymbol.getFir() instanceof FirMemberDeclaration) && isExpect(firBasedSymbol)) || isEffectivelyExternalMember(firBasedSymbol, firSession)) {
            return true;
        }
        Iterator it = PredefinedAnnotation.getEntries().iterator();
        while (it.hasNext()) {
            if (FirHelpersKt.hasAnnotationOrInsideAnnotatedClass(firBasedSymbol, ((PredefinedAnnotation) it.next()).getClassId(), firSession)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isPresentInGeneratedCode(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        firBasedSymbol.getClass();
        firSession.getClass();
        return (FirWebCommonHelpersKt.isNativeObject(firBasedSymbol, firSession) || isLibraryObject(firBasedSymbol, firSession)) ? false : true;
    }

    public static final String sanitizeName(String str) {
        str.getClass();
        if (str.length() == 0) {
            return InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER;
        }
        char cFirst = StringsKt.first(str);
        if (!IdentifierPolicyKt.isES5IdentifierStart(cFirst)) {
            cFirst = '_';
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cFirst);
        String strDrop = StringsKt.drop(str, 1);
        ArrayList arrayList = new ArrayList(strDrop.length());
        for (int i = 0; i < strDrop.length(); i++) {
            char cCharAt = strDrop.charAt(i);
            if (!IdentifierPolicyKt.isES5IdentifierPart(cCharAt)) {
                cCharAt = '_';
            }
            arrayList.add(Character.valueOf(cCharAt));
        }
        sb.append(CollectionsKt.joinToString$default(arrayList, Argument.Delimiters.none, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        return sb.toString();
    }

    public static final ConeClassLikeType superClassNotAny(FirClass firClass, FirSession firSession) {
        Object obj;
        Object next;
        FirClassLikeSymbol<?> symbol;
        firClass.getClass();
        firSession.getClass();
        List<ConeClassLikeType> superConeTypes = FirDeclarationUtilKt.getSuperConeTypes(firClass);
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : superConeTypes) {
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) obj2;
            if (!ConeBuiltinTypeUtilsKt.isAny(coneClassLikeType) && !ConeBuiltinTypeUtilsKt.isNullableAny(coneClassLikeType)) {
                arrayList.add(obj2);
            }
        }
        Iterator it = arrayList.iterator();
        do {
            obj = null;
            if (it.hasNext()) {
                next = it.next();
                symbol = ToSymbolUtilsKt.toSymbol((ConeClassLikeType) next, firSession);
            }
            return (ConeClassLikeType) obj;
        } while ((symbol != null ? FirHelpersKt.getClassKind(symbol) : null) != ClassKind.CLASS);
        obj = next;
        return (ConeClassLikeType) obj;
    }

    public static final boolean isLibraryObject(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        firBasedSymbol.getClass();
        firSession.getClass();
        return FirHelpersKt.hasAnnotationOrInsideAnnotatedClass(firBasedSymbol, JsStandardClassIds.Annotations.JsLibrary, firSession);
    }

    public static final boolean isPredefinedObject(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol) {
        checkerContext.getClass();
        firBasedSymbol.getClass();
        return isPredefinedObject(firBasedSymbol, checkerContext.getSession());
    }

    public static final boolean isExportedObject(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol) {
        checkerContext.getClass();
        firBasedSymbol.getClass();
        return isExportedObject(firBasedSymbol, checkerContext.getSession());
    }
}
