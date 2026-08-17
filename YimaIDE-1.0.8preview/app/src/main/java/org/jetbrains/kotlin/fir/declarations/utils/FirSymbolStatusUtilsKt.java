package org.jetbrains.kotlin.fir.declarations.utils;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\"\u001a\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u001a\u0010\u0005\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007\"\u001a\u0010\b\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\u0007\"\u001a\u0010\t\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007\"\u001a\u0010\n\u001a\u00020\u000b*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u001a\u0010\u000e\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\"\u001a\u0010\u0012\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0007\"\u001a\u0010\u0013\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0007\"\u001a\u0010\u0014\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0007\"\u001a\u0010\u0015\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0007\"\u001a\u0010\u0016\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0007\"\u001a\u0010\u0017\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0007\"\u001a\u0010\u0018\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0007\"\u001a\u0010\u0019\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0007\"\u001a\u0010\u001a\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0007\"\u001a\u0010\u001b\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0007\"\u001a\u0010\u001c\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0007\"\u001a\u0010\u001d\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0007\"\u001a\u0010\u001e\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0007\"\u001a\u0010\u001f\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0007\"\u001a\u0010 \u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b \u0010\u0007\"\u001a\u0010!\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b!\u0010\u0007\"\u001a\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010#\"\u001a\u0010\u0005\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0005\u0010$\"\u001a\u0010\t\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010$\"\u001a\u0010\n\u001a\u00020\u000b*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b\f\u0010%\"\u001a\u0010\u000e\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010&\"\u001a\u0010\u0012\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010$\"\u001a\u0010\u0013\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010$\"\u001a\u0010\u0014\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010$\"\u001a\u0010\u0015\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0015\u0010$\"'\u0010\u0019\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002X\u0087\u0004r\u0002\b)¢\u0006\f\u0012\u0004\b'\u0010(\u001a\u0004\b\u0019\u0010$\"'\u0010*\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002X\u0087\u0004r\u0002\b)¢\u0006\f\u0012\u0004\b+\u0010(\u001a\u0004\b*\u0010$\" \u0010,\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\f\u0012\u0004\b-\u0010(\u001a\u0004\b,\u0010$\"\u001a\u0010\u001b\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001b\u0010$\"\u001a\u0010\u001f\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001f\u0010$\"\u001a\u0010 \u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b \u0010$\"\u001a\u0010!\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b!\u0010$\"\u001a\u0010.\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b.\u0010$\"\u001a\u0010/\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b/\u0010$\"\u001a\u00100\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b0\u0010$\"\u001a\u00101\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\"8Æ\u0002¢\u0006\u0006\u001a\u0004\b1\u0010$\"\u001b\u00102\u001a\u00020\u0006*\b\u0012\u0002\b\u0003\u0018\u0001038F¢\u0006\u0006\u001a\u0004\b2\u00104\"\u001a\u00105\u001a\u00020\u0006*\u0006\u0012\u0002\b\u0003068Æ\u0002¢\u0006\u0006\u001a\u0004\b5\u00107\"\u001a\u00108\u001a\u00020\u0006*\u0006\u0012\u0002\b\u0003068Æ\u0002¢\u0006\u0006\u001a\u0004\b8\u00107\"\u001a\u00109\u001a\u00020\u0006*\u0006\u0012\u0002\b\u0003068Æ\u0002¢\u0006\u0006\u001a\u0004\b9\u00107\"\u001a\u0010:\u001a\u00020\u0006*\u0006\u0012\u0002\b\u0003068Æ\u0002¢\u0006\u0006\u001a\u0004\b:\u00107\"\u001a\u0010;\u001a\u00020\u0006*\u0006\u0012\u0002\b\u0003068Æ\u0002¢\u0006\u0006\u001a\u0004\b;\u00107\"\u0019\u0010<\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b<\u0010\u0007\"\u0019\u0010=\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b=\u0010\u0007\"\u0019\u0010>\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b>\u0010\u0007\"\u0019\u0010?\u001a\u00020\u0006*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b?\u0010\u0007¨\u0006@"}, d2 = {"modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getModality", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Lorg/jetbrains/kotlin/descriptors/Modality;", "isAbstract", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Z", "isOpen", "isFinal", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "getVisibility", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Lorg/jetbrains/kotlin/descriptors/Visibility;", "effectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "getEffectiveVisibility", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "isActual", "isExpect", "isInner", "isStatic", "isOverride", "isOperator", "isInfix", "isInline", "isTailRec", "isExternal", "isSuspend", "isConst", "isLateInit", "isFromSealedClass", "isFromEnumClass", "isFun", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)Lorg/jetbrains/kotlin/descriptors/Modality;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)Z", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)Lorg/jetbrains/kotlin/descriptors/Visibility;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "isInline$annotations", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)V", "Lorg/jetbrains/kotlin/fir/declarations/utils/SuspiciousValueClassCheck;", "isValue", "isValue$annotations", "isInlineOrValue", "isInlineOrValue$annotations", "isCompanion", "isData", "isSealed", "isLocal", "isLocalClassLike", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "isClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)Z", "isInterface", "isAnnotationClass", "isEnumClass", "isEnumEntry", "isExtension", "isCompanionExtension", "isInstanceExtension", "isCompanionBlockMember", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSymbolStatusUtilsKt {
    public static final EffectiveVisibility getEffectiveVisibility(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getResolvedStatus().getEffectiveVisibility();
    }

    public static final Modality getModality(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getResolvedStatus().getModality();
    }

    public static final Visibility getVisibility(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getResolvedStatus().getVisibility();
    }

    public static final boolean isAbstract(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getResolvedStatus().getModality() == Modality.ABSTRACT;
    }

    public static final boolean isActual(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getRawStatus().isActual();
    }

    public static final boolean isAnnotationClass(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return firClassSymbol.getClassKind() == ClassKind.ANNOTATION_CLASS;
    }

    public static final boolean isClass(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return firClassSymbol.getClassKind() == ClassKind.CLASS;
    }

    public static final boolean isCompanion(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getRawStatus().isCompanion();
    }

    public static final boolean isCompanionBlockMember(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return FirStatusUtilsKt.isCompanionBlockMember((FirCallableDeclaration) firCallableSymbol.getFir());
    }

    public static final boolean isCompanionExtension(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return FirStatusUtilsKt.isCompanionExtension((FirCallableDeclaration) firCallableSymbol.getFir());
    }

    public static final boolean isConst(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getRawStatus().isConst();
    }

    public static final boolean isData(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getRawStatus().isData();
    }

    public static final boolean isEnumClass(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return firClassSymbol.getClassKind() == ClassKind.ENUM_CLASS;
    }

    public static final boolean isEnumEntry(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return firClassSymbol.getClassKind() == ClassKind.ENUM_ENTRY;
    }

    public static final boolean isExpect(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getRawStatus().isExpect();
    }

    public static final boolean isExtension(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return FirDeclarationUtilKt.isExtension((FirCallableDeclaration) firCallableSymbol.getFir());
    }

    public static final boolean isExternal(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getRawStatus().isExternal();
    }

    public static final boolean isFinal(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getResolvedStatus().getModality() == Modality.FINAL;
    }

    public static final boolean isFromEnumClass(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getRawStatus().isFromEnumClass();
    }

    public static final boolean isFromSealedClass(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getRawStatus().isFromSealedClass();
    }

    public static final boolean isFun(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getRawStatus().isFun();
    }

    public static final boolean isInfix(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getResolvedStatus().isInfix();
    }

    public static final boolean isInline(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getRawStatus().isInline();
    }

    @SuspiciousValueClassCheck
    public static /* synthetic */ void isInline$annotations(FirClassLikeSymbol firClassLikeSymbol) {
    }

    public static final boolean isInlineOrValue(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getRawStatus().isInline() || firClassLikeSymbol.getRawStatus().isValue();
    }

    public static /* synthetic */ void isInlineOrValue$annotations(FirClassLikeSymbol firClassLikeSymbol) {
    }

    public static final boolean isInner(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getRawStatus().isInner();
    }

    public static final boolean isInstanceExtension(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return FirDeclarationUtilKt.isInstanceExtension((FirCallableDeclaration) firCallableSymbol.getFir());
    }

    public static final boolean isInterface(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return firClassSymbol.getClassKind() == ClassKind.INTERFACE;
    }

    public static final boolean isLateInit(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getRawStatus().isLateInit();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isLocal(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return ((FirClassLikeDeclaration) firClassLikeSymbol.getFir()).getIsLocal();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isLocalClassLike(FirBasedSymbol<?> firBasedSymbol) {
        FirClassLikeSymbol firClassLikeSymbol = firBasedSymbol instanceof FirClassLikeSymbol ? (FirClassLikeSymbol) firBasedSymbol : null;
        return firClassLikeSymbol != null && ((FirClassLikeDeclaration) firClassLikeSymbol.getFir()).getIsLocal();
    }

    public static final boolean isOpen(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getResolvedStatus().getModality() == Modality.OPEN;
    }

    public static final boolean isOperator(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getResolvedStatus().isOperator();
    }

    public static final boolean isOverride(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getResolvedStatus().isOverride();
    }

    public static final boolean isSealed(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getResolvedStatus().getModality() == Modality.SEALED;
    }

    public static final boolean isStatic(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getRawStatus().isStatic();
    }

    public static final boolean isSuspend(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getRawStatus().isSuspend();
    }

    public static final boolean isTailRec(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return firCallableSymbol.getRawStatus().isTailRec();
    }

    public static final boolean isValue(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getRawStatus().isValue();
    }

    @SuspiciousValueClassCheck
    public static /* synthetic */ void isValue$annotations(FirClassLikeSymbol firClassLikeSymbol) {
    }

    public static final EffectiveVisibility getEffectiveVisibility(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getResolvedStatus().getEffectiveVisibility();
    }

    public static final Modality getModality(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getResolvedStatus().getModality();
    }

    public static final Visibility getVisibility(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getRawStatus().getVisibility();
    }

    public static final boolean isActual(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getRawStatus().isActual();
    }

    public static final boolean isExpect(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getRawStatus().isExpect();
    }

    public static final boolean isExternal(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getRawStatus().isExternal();
    }

    public static final boolean isFromEnumClass(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getRawStatus().isFromEnumClass();
    }

    public static final boolean isFromSealedClass(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getRawStatus().isFromSealedClass();
    }

    public static final boolean isFun(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getRawStatus().isFun();
    }

    public static final boolean isInline(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getRawStatus().isInline();
    }

    public static final boolean isInner(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getRawStatus().isInner();
    }

    public static final boolean isStatic(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getRawStatus().isStatic();
    }

    public static final boolean isAbstract(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getResolvedStatus().getModality() == Modality.ABSTRACT;
    }

    public static final boolean isFinal(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return firClassLikeSymbol.getResolvedStatus().getModality() == Modality.FINAL;
    }
}
