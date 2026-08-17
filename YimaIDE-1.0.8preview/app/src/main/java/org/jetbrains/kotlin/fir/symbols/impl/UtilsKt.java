package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0000\u001a\f\u0010\b\u001a\u00020\t*\u00020\nH\u0000¨\u0006\u000b"}, d2 = {"errorInLazyResolve", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", ModuleXmlParser.NAME, Argument.Delimiters.none, "actualClass", "Lkotlin/reflect/KClass;", "expected", "resolvedStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UtilsKt {
    public static final Void errorInLazyResolve(FirBasedSymbol<?> firBasedSymbol, String str, KClass<?> kClass, KClass<?> kClass2) {
        firBasedSymbol.getClass();
        str.getClass();
        kClass.getClass();
        kClass2.getClass();
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unexpected " + str + ". Expected is " + kClass2.getSimpleName() + ", but was " + kClass.getSimpleName(), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "firElement", firBasedSymbol.getFir());
        FirExceptionUtilsKt.withFirSymbolIdEntry(exceptionAttachmentBuilder, "firSymbol", firBasedSymbol);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public static final FirResolvedDeclarationStatus resolvedStatus(FirMemberDeclaration firMemberDeclaration) {
        firMemberDeclaration.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firMemberDeclaration, FirResolvePhase.STATUS);
        FirDeclarationStatus status = firMemberDeclaration.getStatus();
        if (status instanceof FirResolvedDeclarationStatus) {
            return (FirResolvedDeclarationStatus) status;
        }
        errorInLazyResolve(firMemberDeclaration.getSymbol(), "status", Reflection.getOrCreateKotlinClass(status.getClass()), Reflection.getOrCreateKotlinClass(FirResolvedDeclarationStatus.class));
        wq6.a();
        return null;
    }
}
