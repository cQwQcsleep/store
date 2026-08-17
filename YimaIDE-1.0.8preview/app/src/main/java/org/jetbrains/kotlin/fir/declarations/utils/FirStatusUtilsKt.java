package org.jetbrains.kotlin.fir.declarations.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0016\u0010\u0005\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007\"\u0016\u0010\b\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\u0007\"\u0016\u0010\t\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007\"\u0016\u0010\n\u001a\u00020\u000b*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u0016\u0010\u000e\u001a\u00020\u000f*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\"\u0016\u0010\u0012\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0007\"\u0016\u0010\u0013\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0007\"\u0016\u0010\u0014\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0007\"\u0016\u0010\u0015\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0007\"\u0016\u0010\u0016\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0007\"\u0016\u0010\u0017\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0007\"\u0016\u0010\u0018\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0007\"\u0016\u0010\u0019\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0007\"\u0016\u0010\u001a\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0007\"#\u0010\u001b\u001a\u00020\u0006*\u00020\u001c8Æ\u0002X\u0087\u0004r\u0002\b ¢\u0006\f\u0012\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001f\"\u001c\u0010!\u001a\u00020\u0006*\u00020\u001c8Æ\u0002¢\u0006\f\u0012\u0004\b\"\u0010\u001e\u001a\u0004\b!\u0010\u001f\"\u0016\u0010#\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b#\u0010\u0007\"\u0016\u0010$\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b$\u0010\u0007\"\u0016\u0010%\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b%\u0010\u0007\"\u0016\u0010&\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b&\u0010\u0007\"\u0016\u0010'\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b'\u0010\u0007\"\u0016\u0010(\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b(\u0010\u0007\"\u0016\u0010)\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b)\u0010\u0007\"\u0016\u0010*\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b*\u0010\u0007\"\u0016\u0010+\u001a\u00020\u0006*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b,\u0010\u0007\"\u0016\u0010-\u001a\u00020\u0006*\u00020\u001c8Æ\u0002¢\u0006\u0006\u001a\u0004\b-\u0010\u001f\"\u0016\u0010.\u001a\u00020\u0006*\u00020\u001c8Æ\u0002¢\u0006\u0006\u001a\u0004\b.\u0010\u001f\"\u0016\u0010/\u001a\u00020\u0006*\u0002008Æ\u0002¢\u0006\u0006\u001a\u0004\b/\u00101\"\u0016\u00102\u001a\u00020\u0006*\u0002008Æ\u0002¢\u0006\u0006\u001a\u0004\b3\u00101\"\u0016\u00102\u001a\u00020\u0006*\u0002048Æ\u0002¢\u0006\u0006\u001a\u0004\b3\u00105\"\u0016\u00106\u001a\u00020\u0006*\u0002008Æ\u0002¢\u0006\u0006\u001a\u0004\b6\u00101\"\u0016\u00107\u001a\u00020\u0006*\u0002008Æ\u0002¢\u0006\u0006\u001a\u0004\b7\u00101\"\u0016\u00108\u001a\u00020\u0006*\u0002098Æ\u0002¢\u0006\u0006\u001a\u0004\b:\u0010;\"\u0016\u00108\u001a\u00020\u0006*\u00020<8Æ\u0002¢\u0006\u0006\u001a\u0004\b:\u0010=\"\u0015\u0010>\u001a\u00020\u0006*\u00020?8F¢\u0006\u0006\u001a\u0004\b>\u0010@\"\u0015\u0010A\u001a\u00020\u0006*\u00020?8F¢\u0006\u0006\u001a\u0004\bA\u0010@¨\u0006B"}, d2 = {"modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "getModality", "(Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;)Lorg/jetbrains/kotlin/descriptors/Modality;", "isAbstract", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;)Z", "isOpen", "isFinal", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "getVisibility", "(Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;)Lorg/jetbrains/kotlin/descriptors/Visibility;", "effectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "getEffectiveVisibility", "(Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;)Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "isOverridable", "isActual", "isExpect", "isInner", "isStatic", "isOverride", "isOperator", "isInfix", "isInline", "isValue", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "isValue$annotations", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Z", "Lorg/jetbrains/kotlin/fir/declarations/utils/SuspiciousValueClassCheck;", "isInlineOrValue", "isInlineOrValue$annotations", "isTailRec", "isExternal", "isSuspend", "isConst", "isLateInit", "isFromSealedClass", "isFromEnumClass", "isFun", "hasStableParameterNames", "getHasStableParameterNames", "isInterface", "isEnumClass", "isSealed", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Z", "canHaveAbstractDeclaration", "getCanHaveAbstractDeclaration", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)Z", "isCompanion", "isData", "hasBody", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "getHasBody", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Z", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "(Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;)Z", "isCompanionBlockMember", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Z", "isCompanionExtension", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirStatusUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean getCanHaveAbstractDeclaration(FirRegularClassSymbol firRegularClassSymbol) {
        firRegularClassSymbol.getClass();
        FirRegularClass firRegularClass = (FirRegularClass) firRegularClassSymbol.getFir();
        return firRegularClass.getClassKind() == ClassKind.INTERFACE || firRegularClass.getStatus().getModality() == Modality.ABSTRACT || firRegularClass.getStatus().getModality() == Modality.SEALED || firRegularClass.getClassKind() == ClassKind.ENUM_CLASS;
    }

    public static final EffectiveVisibility getEffectiveVisibility(FirMemberDeclaration firMemberDeclaration) {
        EffectiveVisibility effectiveVisibility;
        firMemberDeclaration.getClass();
        FirDeclarationStatus status = firMemberDeclaration.getStatus();
        FirResolvedDeclarationStatus firResolvedDeclarationStatus = status instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status : null;
        return (firResolvedDeclarationStatus == null || (effectiveVisibility = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) ? EffectiveVisibility.Local.INSTANCE : effectiveVisibility;
    }

    public static final boolean getHasBody(FirFunction firFunction) {
        firFunction.getClass();
        return firFunction.getBody() != null;
    }

    public static final boolean getHasStableParameterNames(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().getHasStableParameterNames();
    }

    public static final Modality getModality(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().getModality();
    }

    public static final Visibility getVisibility(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().getVisibility();
    }

    public static final boolean isAbstract(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().getModality() == Modality.ABSTRACT;
    }

    public static final boolean isActual(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isActual();
    }

    public static final boolean isCompanion(FirRegularClass firRegularClass) {
        firRegularClass.getClass();
        return firRegularClass.getStatus().isCompanion();
    }

    public static final boolean isCompanionBlockMember(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return firCallableDeclaration.getStatus().isStatic() && ClassMembersKt.getContainingClassForStaticMemberAttr(firCallableDeclaration) != null;
    }

    public static final boolean isCompanionExtension(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return firCallableDeclaration.getStatus().isStatic() && firCallableDeclaration.getReceiverParameter() != null;
    }

    public static final boolean isConst(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isConst();
    }

    public static final boolean isData(FirRegularClass firRegularClass) {
        firRegularClass.getClass();
        return firRegularClass.getStatus().isData();
    }

    public static final boolean isEnumClass(FirClass firClass) {
        firClass.getClass();
        return firClass.getClassKind() == ClassKind.ENUM_CLASS;
    }

    public static final boolean isExpect(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isExpect();
    }

    public static final boolean isExternal(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isExternal();
    }

    public static final boolean isFinal(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        Modality modality = firMemberDeclaration.getStatus().getModality();
        return modality == null || modality == Modality.FINAL;
    }

    public static final boolean isFromEnumClass(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isFromEnumClass();
    }

    public static final boolean isFromSealedClass(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isFromSealedClass();
    }

    public static final boolean isFun(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isFun();
    }

    public static final boolean isInfix(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isInfix();
    }

    public static final boolean isInline(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isInline();
    }

    public static final boolean isInlineOrValue(FirClass firClass) {
        firClass.getClass();
        return firClass.getStatus().isInline() || firClass.getStatus().isValue();
    }

    public static /* synthetic */ void isInlineOrValue$annotations(FirClass firClass) {
    }

    public static final boolean isInner(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isInner();
    }

    public static final boolean isInterface(FirClass firClass) {
        firClass.getClass();
        return firClass.getClassKind() == ClassKind.INTERFACE;
    }

    public static final boolean isLateInit(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isLateInit();
    }

    public static final boolean isOpen(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().getModality() == Modality.OPEN;
    }

    public static final boolean isOperator(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isOperator();
    }

    public static final boolean isOverridable(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return (firMemberDeclaration.getStatus().getModality() == Modality.FINAL || Intrinsics.areEqual(firMemberDeclaration.getStatus().getVisibility(), Visibilities.Private.INSTANCE)) ? false : true;
    }

    public static final boolean isOverride(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isOverride();
    }

    public static final boolean isSealed(FirRegularClass firRegularClass) {
        firRegularClass.getClass();
        return firRegularClass.getStatus().getModality() == Modality.SEALED;
    }

    public static final boolean isStatic(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isStatic();
    }

    public static final boolean isSuspend(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isSuspend();
    }

    public static final boolean isTailRec(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        return firMemberDeclaration.getStatus().isTailRec();
    }

    public static final boolean isValue(FirClass firClass) {
        firClass.getClass();
        return firClass.getStatus().isValue();
    }

    @SuspiciousValueClassCheck
    public static /* synthetic */ void isValue$annotations(FirClass firClass) {
    }

    public static final boolean getHasBody(FirPropertyAccessor firPropertyAccessor) {
        firPropertyAccessor.getClass();
        return firPropertyAccessor.getBody() != null;
    }

    public static final boolean getCanHaveAbstractDeclaration(FirRegularClass firRegularClass) {
        firRegularClass.getClass();
        return firRegularClass.getClassKind() == ClassKind.INTERFACE || firRegularClass.getStatus().getModality() == Modality.ABSTRACT || firRegularClass.getStatus().getModality() == Modality.SEALED || firRegularClass.getClassKind() == ClassKind.ENUM_CLASS;
    }
}
