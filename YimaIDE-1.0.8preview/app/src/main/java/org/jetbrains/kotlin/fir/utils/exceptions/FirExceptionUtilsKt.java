package org.jetbrains.kotlin.fir.utils.exceptions;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirElementWithResolveState;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.renderer.ConeTypeRenderer;
import org.jetbrains.kotlin.fir.renderer.ConeTypeRendererForDebugging;
import org.jetbrains.kotlin.fir.renderer.FirDeclarationRendererWithAttributes;
import org.jetbrains.kotlin.fir.renderer.FirRenderer;
import org.jetbrains.kotlin.fir.renderer.FirResolvePhaseRenderer;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a \u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t\u001a\u001c\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u001a\u001c\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u001a\u001c\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u001a\u001e\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t\u001a\u001c\u0010\u0014\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016¨\u0006\u0017"}, d2 = {"withFirEntry", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/utils/exceptions/ExceptionAttachmentBuilder;", ModuleXmlParser.NAME, Argument.Delimiters.none, "fir", "Lorg/jetbrains/kotlin/fir/FirElement;", "withFirSymbolIdEntry", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "withFirLookupTagEntry", "lookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassifierLookupTag;", "withSourceEntry", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "withModuleDataEntry", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "withFirSymbolEntry", "withConeTypeEntry", "coneType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExceptionUtilsKt {
    public static String a(String str, FirElement firElement) {
        firElement.getClass();
        return str;
    }

    public static String c(FirModuleData firModuleData) {
        firModuleData.getClass();
        return ("Name: " + firModuleData.getName() + ", ").concat("Platform: " + firModuleData.getPlatform());
    }

    public static String d(KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        return ktSourceElement.getElementTextInContextForDebug();
    }

    public static String e(ConeClassifierLookupTag coneClassifierLookupTag) {
        coneClassifierLookupTag.getClass();
        if (coneClassifierLookupTag instanceof ConeClassLikeLookupTag) {
            return ((ConeClassLikeLookupTag) coneClassifierLookupTag).getClassId().asString();
        }
        String strAsString = coneClassifierLookupTag.getName().asString();
        strAsString.getClass();
        return strAsString;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public static String f(ConeKotlinType coneKotlinType) throws UninitializedPropertyAccessException {
        coneKotlinType.getClass();
        StringBuilder sb = new StringBuilder();
        ConeTypeRenderer.render$default(new ConeTypeRendererForDebugging(sb), coneKotlinType, null, 2, null);
        return sb.toString();
    }

    public static final void withConeTypeEntry(ExceptionAttachmentBuilder exceptionAttachmentBuilder, String str, ConeKotlinType coneKotlinType) {
        exceptionAttachmentBuilder.getClass();
        str.getClass();
        exceptionAttachmentBuilder.withEntry(str, coneKotlinType, new Function1() { // from class: a65
            public final Object invoke(Object obj) {
                return FirExceptionUtilsKt.f((ConeKotlinType) obj);
            }
        });
    }

    public static final void withFirEntry(ExceptionAttachmentBuilder exceptionAttachmentBuilder, String str, FirElement firElement) {
        Object obj;
        KtSourceElementKind kind;
        exceptionAttachmentBuilder.getClass();
        str.getClass();
        if (firElement == null) {
            return;
        }
        String simpleName = null;
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.constructor-impl(FirRenderer.renderElementAsString$default(new FirRenderer(null, null, null, null, null, null, null, new FirDeclarationRendererWithAttributes(), null, null, null, null, new FirResolvePhaseRenderer(), null, null, null, null, null, null, null, null, false, false, false, 16772991, null), firElement, false, 2, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        final Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th2);
            exceptionAttachmentBuilder.withEntry(str + "Error", th2, new Function1() { // from class: x55
                public final Object invoke(Object obj2) {
                    return FirExceptionUtilsKt.withFirEntry$lambda$1$0(th2, (Throwable) obj2);
                }
            });
        }
        if (Result.isFailure-impl(obj)) {
            obj = "<error>";
        }
        final String str2 = (String) obj;
        exceptionAttachmentBuilder.withEntry(str, firElement, new Function1() { // from class: y55
            public final Object invoke(Object obj2) {
                return FirExceptionUtilsKt.a(str2, (FirElement) obj2);
            }
        });
        String str3 = str + "ElementKind";
        KtSourceElement source = firElement.getSource();
        if (source != null && (kind = source.getKind()) != null) {
            simpleName = Reflection.getOrCreateKotlinClass(kind.getClass()).getSimpleName();
        }
        exceptionAttachmentBuilder.withEntry(str3, simpleName);
        if (firElement instanceof FirElementWithResolveState) {
            withModuleDataEntry(exceptionAttachmentBuilder, str + "ModuleData", ((FirElementWithResolveState) firElement).getModuleData());
        }
        withSourceEntry(exceptionAttachmentBuilder, str + "Source", firElement.getSource());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String withFirEntry$lambda$1$0(Throwable th, Throwable th2) {
        th2.getClass();
        return ExceptionsKt.stackTraceToString(th);
    }

    public static final void withFirLookupTagEntry(ExceptionAttachmentBuilder exceptionAttachmentBuilder, String str, ConeClassifierLookupTag coneClassifierLookupTag) {
        exceptionAttachmentBuilder.getClass();
        str.getClass();
        exceptionAttachmentBuilder.withEntry(str, coneClassifierLookupTag, new Function1() { // from class: z55
            public final Object invoke(Object obj) {
                return FirExceptionUtilsKt.e((ConeClassifierLookupTag) obj);
            }
        });
    }

    public static final void withFirSymbolEntry(ExceptionAttachmentBuilder exceptionAttachmentBuilder, String str, FirBasedSymbol<?> firBasedSymbol) {
        exceptionAttachmentBuilder.getClass();
        str.getClass();
        firBasedSymbol.getClass();
        withFirEntry(exceptionAttachmentBuilder, str + "Fir", firBasedSymbol.getFir());
    }

    public static final void withFirSymbolIdEntry(ExceptionAttachmentBuilder exceptionAttachmentBuilder, String str, FirBasedSymbol<?> firBasedSymbol) {
        exceptionAttachmentBuilder.getClass();
        str.getClass();
        if (firBasedSymbol instanceof FirClassifierSymbol) {
            withFirLookupTagEntry(exceptionAttachmentBuilder, str, ((FirClassifierSymbol) firBasedSymbol).getLookupTag());
        } else if (firBasedSymbol instanceof FirCallableSymbol) {
            exceptionAttachmentBuilder.withEntry(str, String.valueOf(((FirCallableSymbol) firBasedSymbol).getCallableId()));
        } else {
            exceptionAttachmentBuilder.withEntry(str, String.valueOf(firBasedSymbol));
        }
    }

    public static final void withModuleDataEntry(ExceptionAttachmentBuilder exceptionAttachmentBuilder, String str, FirModuleData firModuleData) {
        exceptionAttachmentBuilder.getClass();
        str.getClass();
        exceptionAttachmentBuilder.withEntry(str, firModuleData, new Function1() { // from class: v55
            public final Object invoke(Object obj) {
                return FirExceptionUtilsKt.c((FirModuleData) obj);
            }
        });
    }

    public static final void withSourceEntry(ExceptionAttachmentBuilder exceptionAttachmentBuilder, String str, KtSourceElement ktSourceElement) {
        exceptionAttachmentBuilder.getClass();
        str.getClass();
        exceptionAttachmentBuilder.withEntry(str, ktSourceElement, new Function1() { // from class: w55
            public final Object invoke(Object obj) {
                return FirExceptionUtilsKt.d((KtSourceElement) obj);
            }
        });
    }
}
