package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.scopes.FirOverrideChecker;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0000\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00062\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006¨\u0006\u0007"}, d2 = {"similarFunctionsOrBothProperties", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", "overrideCandidate", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "baseDeclaration", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AbstractFirOverrideScopeKt {
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final boolean similarFunctionsOrBothProperties(FirOverrideChecker firOverrideChecker, FirCallableDeclaration firCallableDeclaration, FirCallableDeclaration firCallableDeclaration2) throws KotlinIllegalArgumentExceptionWithAttachments {
        firOverrideChecker.getClass();
        firCallableDeclaration.getClass();
        firCallableDeclaration2.getClass();
        if (Intrinsics.areEqual(firCallableDeclaration.getOrigin(), FirDeclarationOrigin.DynamicScope.INSTANCE)) {
            return false;
        }
        if (firCallableDeclaration instanceof FirNamedFunction) {
            if (firCallableDeclaration2 instanceof FirNamedFunction) {
                return firOverrideChecker.isOverriddenFunction((FirNamedFunction) firCallableDeclaration, (FirNamedFunction) firCallableDeclaration2);
            }
            if (firCallableDeclaration2 instanceof FirProperty) {
                return firOverrideChecker.isOverriddenProperty(firCallableDeclaration, (FirProperty) firCallableDeclaration2);
            }
            return false;
        }
        if (firCallableDeclaration instanceof FirConstructor) {
            return false;
        }
        if (firCallableDeclaration instanceof FirProperty) {
            return (firCallableDeclaration2 instanceof FirProperty) && firOverrideChecker.isOverriddenProperty(firCallableDeclaration, (FirProperty) firCallableDeclaration2);
        }
        if (firCallableDeclaration instanceof FirField) {
            return firCallableDeclaration2 instanceof FirField;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unknown fir callable type", (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "overrideCandidate", firCallableDeclaration);
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "baseDeclaration", firCallableDeclaration2);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public static final boolean similarFunctionsOrBothProperties(FirOverrideChecker firOverrideChecker, FirCallableSymbol<?> firCallableSymbol, FirCallableSymbol<?> firCallableSymbol2) {
        firOverrideChecker.getClass();
        firCallableSymbol.getClass();
        firCallableSymbol2.getClass();
        return similarFunctionsOrBothProperties(firOverrideChecker, (FirCallableDeclaration) firCallableSymbol.getFir(), (FirCallableDeclaration) firCallableSymbol2.getFir());
    }
}
