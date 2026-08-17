package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0012\u0010\f\u001a\u00020\t2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eR\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", Argument.Delimiters.none, "<init>", "()V", "callableCopyTypeCalculator", "Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;", "getCallableCopyTypeCalculator", "()Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;", "tryCalculateReturnTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "tryCalculateReturnType", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ReturnTypeCalculator {
    public abstract CallableCopyTypeCalculator getCallableCopyTypeCalculator();

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final FirResolvedTypeRef tryCalculateReturnType(FirCallableDeclaration declaration) throws KotlinIllegalArgumentExceptionWithAttachments {
        declaration.getClass();
        FirResolvedTypeRef firResolvedTypeRefTryCalculateReturnTypeOrNull = tryCalculateReturnTypeOrNull(declaration);
        if (firResolvedTypeRefTryCalculateReturnTypeOrNull != null) {
            return firResolvedTypeRefTryCalculateReturnTypeOrNull;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(Reflection.getOrCreateKotlinClass(getClass()).getSimpleName() + ": Return type cannot be calculated for " + Reflection.getOrCreateKotlinClass(declaration.getClass()).getSimpleName(), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "declaration", declaration);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public abstract FirResolvedTypeRef tryCalculateReturnTypeOrNull(FirCallableDeclaration declaration);

    public final FirResolvedTypeRef tryCalculateReturnType(FirCallableSymbol<?> symbol) {
        symbol.getClass();
        return tryCalculateReturnType((FirCallableDeclaration) symbol.getFir());
    }
}
