package org.jetbrains.kotlin.fir.declarations.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.name.WebCommonStandardClassIds;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0016\u0010\u0004\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u001f\u0010\u0007\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\nR\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u000b\u001a\u0016\u0010\f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0016\u0010\r\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0016\u0010\u000e\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006\"\u001c\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\u000f"}, d2 = {"isExternal", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "isEffectivelyExternal", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isEffectivelyExternalOrOverridingExternal", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Z", "isNativeObject", "isNativeInterface", "isExplicitlyMarkedAsExported", "org.jetbrains.kotlin:checkers.web.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWebCommonHelpersKt {
    public static final boolean isEffectivelyExternal(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        FirPropertySymbol firPropertySymbol;
        FirPropertyAccessorSymbol getterSymbol;
        FirPropertyAccessorSymbol setterSymbol;
        firBasedSymbol.getClass();
        firSession.getClass();
        if ((firBasedSymbol.getFir() instanceof FirMemberDeclaration) && isExternal(firBasedSymbol)) {
            return true;
        }
        if ((firBasedSymbol instanceof FirPropertyAccessorSymbol) && isEffectivelyExternal(((FirPropertyAccessorSymbol) firBasedSymbol).getPropertySymbol(), firSession)) {
            return true;
        }
        if ((firBasedSymbol instanceof FirPropertySymbol) && (getterSymbol = (firPropertySymbol = (FirPropertySymbol) firBasedSymbol).getGetterSymbol()) != null && getterSymbol.getRawStatus().isExternal() && (!firPropertySymbol.isVar() || ((setterSymbol = firPropertySymbol.getSetterSymbol()) != null && setterSymbol.getRawStatus().isExternal()))) {
            return true;
        }
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firBasedSymbol);
        return containingClassSymbol != null && isEffectivelyExternal(containingClassSymbol, firSession);
    }

    public static final boolean isEffectivelyExternalOrOverridingExternal(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol) {
        checkerContext.getClass();
        firCallableSymbol.getClass();
        if (isEffectivelyExternal(firCallableSymbol, checkerContext.getSession())) {
            return true;
        }
        List<FirCallableSymbol<?>> listDirectOverriddenSymbolsSafe = FirHelpersKt.directOverriddenSymbolsSafe(checkerContext, firCallableSymbol);
        if ((listDirectOverriddenSymbolsSafe instanceof Collection) && listDirectOverriddenSymbolsSafe.isEmpty()) {
            return false;
        }
        Iterator<T> it = listDirectOverriddenSymbolsSafe.iterator();
        while (it.hasNext()) {
            if (isEffectivelyExternalOrOverridingExternal(checkerContext, (FirCallableSymbol) it.next())) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isExplicitlyMarkedAsExported(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        firBasedSymbol.getClass();
        firSession.getClass();
        StandardClassIds$Annotations standardClassIds$Annotations = StandardClassIds$Annotations.INSTANCE;
        if (FirAnnotationUtilsKt.hasAnnotation(firBasedSymbol, standardClassIds$Annotations.getJsExportIgnore(), firSession)) {
            return false;
        }
        return FirAnnotationUtilsKt.hasAnnotation(firBasedSymbol, standardClassIds$Annotations.getJsExport(), firSession) || FirAnnotationUtilsKt.hasAnnotation(firBasedSymbol, standardClassIds$Annotations.getJsExportDefault(), firSession);
    }

    private static final boolean isExternal(FirBasedSymbol<?> firBasedSymbol) {
        if (firBasedSymbol instanceof FirCallableSymbol) {
            return ((FirCallableSymbol) firBasedSymbol).getRawStatus().isExternal();
        }
        if (firBasedSymbol instanceof FirClassSymbol) {
            return ((FirClassLikeSymbol) firBasedSymbol).getRawStatus().isExternal();
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final boolean isNativeInterface(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) throws KotlinIllegalArgumentExceptionWithAttachments {
        firBasedSymbol.getClass();
        firSession.getClass();
        if (!isNativeObject(firBasedSymbol, firSession)) {
            return false;
        }
        FirDeclaration fir = firBasedSymbol.getFir();
        FirClass firClass = fir instanceof FirClass ? (FirClass) fir : null;
        return firClass != null && firClass.getClassKind() == ClassKind.INTERFACE;
    }

    public static final boolean isNativeObject(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        firBasedSymbol.getClass();
        firSession.getClass();
        ClassId classId = WebCommonStandardClassIds.Annotations.JsNative;
        if (FirHelpersKt.hasAnnotationOrInsideAnnotatedClass(firBasedSymbol, classId, firSession) || isEffectivelyExternal(firBasedSymbol, firSession)) {
            return true;
        }
        if (firBasedSymbol instanceof FirPropertyAccessorSymbol) {
            return FirHelpersKt.hasAnnotationOrInsideAnnotatedClass(((FirPropertyAccessorSymbol) firBasedSymbol).getPropertySymbol(), classId, firSession);
        }
        return false;
    }
}
