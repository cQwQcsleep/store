package org.jetbrains.kotlin.diagnostics;

import com.intellij.openapi.util.TextRange;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u001b\u001a\u00020\u0018R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\u0082\u0001\u0002\u001c\u001d¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", Argument.Delimiters.none, "<init>", "()V", "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "getSeverity", "()Lorg/jetbrains/kotlin/diagnostics/Severity;", "factory", "Lorg/jetbrains/kotlin/diagnostics/AbstractKtDiagnosticFactory;", "getFactory", "()Lorg/jetbrains/kotlin/diagnostics/AbstractKtDiagnosticFactory;", "isValid", Argument.Delimiters.none, "()Z", "firstRange", "Lcom/intellij/openapi/util/TextRange;", "getFirstRange", "()Lcom/intellij/openapi/util/TextRange;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "getContext", "()Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "factoryName", Argument.Delimiters.none, "getFactoryName", "()Ljava/lang/String;", "renderMessage", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithSource;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithoutSource;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class KtDiagnostic {
    public /* synthetic */ KtDiagnostic(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract DiagnosticBaseContext getContext();

    public abstract AbstractKtDiagnosticFactory getFactory();

    public final String getFactoryName() {
        return getFactory().getName();
    }

    public abstract TextRange getFirstRange();

    public abstract Severity getSeverity();

    public abstract boolean isValid();

    public final String renderMessage() {
        return getFactory().getKtRenderer().render(this);
    }

    private KtDiagnostic() {
    }
}
