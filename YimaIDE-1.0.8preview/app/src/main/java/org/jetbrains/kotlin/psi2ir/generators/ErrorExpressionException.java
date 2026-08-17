package org.jetbrains.kotlin.psi2ir.generators;

import kotlin.Metadata;
import org.jetbrains.kotlin.psi.KtElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/psi2ir/generators/ErrorExpressionException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "ktElement", "Lorg/jetbrains/kotlin/psi/KtElement;", "cause", "", "<init>", "(Lorg/jetbrains/kotlin/psi/KtElement;Ljava/lang/Throwable;)V", "getKtElement", "()Lorg/jetbrains/kotlin/psi/KtElement;", "org.jetbrains.kotlin:ir.psi2ir"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ErrorExpressionException extends RuntimeException {
    private final KtElement ktElement;

    public ErrorExpressionException(KtElement ktElement, Throwable th) {
        ktElement.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append(th != null ? th.getMessage() : null);
        sb.append(": ");
        sb.append(ktElement.getClass().getSimpleName());
        sb.append(":\n");
        sb.append(ktElement.getText());
        super(sb.toString(), th);
        this.ktElement = ktElement;
    }

    public final KtElement getKtElement() {
        return this.ktElement;
    }
}
