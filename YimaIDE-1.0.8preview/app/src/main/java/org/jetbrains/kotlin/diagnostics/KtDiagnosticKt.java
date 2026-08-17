package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachementBuilderUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"CHECK_PSI_CONSISTENCY_IN_DIAGNOSTICS", Argument.Delimiters.none, "checkPsiTypeConsistency", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/KtPsiDiagnostic;", "org.jetbrains.kotlin:frontend.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtDiagnosticKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final void checkPsiTypeConsistency(KtPsiDiagnostic ktPsiDiagnostic) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (ktPsiDiagnostic.getFactory().getPsiType().isInstance(ktPsiDiagnostic.getPsiElement())) {
            return;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(Reflection.getOrCreateKotlinClass(ktPsiDiagnostic.getPsiElement().getClass()) + " is not a subtype of " + ktPsiDiagnostic.getFactory().getPsiType() + " for factory " + ktPsiDiagnostic.getFactory());
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        ExceptionAttachementBuilderUtilsKt.withPsiEntry(exceptionAttachmentBuilder, "psi", ktPsiDiagnostic.getPsiElement());
        ExceptionAttachementBuilderUtilsKt.withPsiEntry(exceptionAttachmentBuilder, "file", ktPsiDiagnostic.getPsiFile());
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }
}
