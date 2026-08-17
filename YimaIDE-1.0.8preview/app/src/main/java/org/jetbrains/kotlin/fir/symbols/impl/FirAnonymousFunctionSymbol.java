package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRange;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.InlineStatus;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionWithoutNameSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "<init>", "()V", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/FirLabel;", "getLabel", "()Lorg/jetbrains/kotlin/fir/FirLabel;", "isLambda", Argument.Delimiters.none, "()Z", "inlineStatus", "Lorg/jetbrains/kotlin/fir/declarations/InlineStatus;", "getInlineStatus", "()Lorg/jetbrains/kotlin/fir/declarations/InlineStatus;", "invocationKind", "Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "getInvocationKind", "()Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "resolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "getResolvedTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnonymousFunctionSymbol extends FirFunctionWithoutNameSymbol<FirAnonymousFunction> {
    /* JADX WARN: Illegal instructions before constructor call */
    public FirAnonymousFunctionSymbol() {
        Name nameIdentifier = Name.identifier("anonymous");
        nameIdentifier.getClass();
        super(nameIdentifier, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final InlineStatus getInlineStatus() {
        return ((FirAnonymousFunction) getFir()).getInlineStatus();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final EventOccurrencesRange getInvocationKind() {
        return ((FirAnonymousFunction) getFir()).getInvocationKind();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirLabel getLabel() {
        return ((FirAnonymousFunction) getFir()).getLabel();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public final FirResolvedTypeRef getResolvedTypeRef() throws KotlinIllegalStateExceptionWithAttachments {
        FirResolvedTypeRef typeRef = ((FirAnonymousFunction) getFir()).getTypeRef();
        if (typeRef instanceof FirResolvedTypeRef) {
            return typeRef;
        }
        KotlinIllegalStateExceptionWithAttachments kotlinIllegalStateExceptionWithAttachments = new KotlinIllegalStateExceptionWithAttachments("Type of the lambda is not resolved");
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirSymbolEntry(exceptionAttachmentBuilder, "lambda", this);
        kotlinIllegalStateExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalStateExceptionWithAttachments;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isLambda() {
        return ((FirAnonymousFunction) getFir()).isLambda();
    }
}
