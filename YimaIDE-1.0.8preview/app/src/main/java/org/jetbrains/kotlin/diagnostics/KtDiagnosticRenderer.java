package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&J\u001f\u0010\u000b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\f2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0002\u0010\rR\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\u000e\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticRenderer;", Argument.Delimiters.none, "<init>", "()V", "message", Argument.Delimiters.none, "getMessage", "()Ljava/lang/String;", "render", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "renderParameters", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;)[Ljava/lang/Object;", "Lorg/jetbrains/kotlin/diagnostics/AbstractKtDiagnosticWithParametersRenderer;", "Lorg/jetbrains/kotlin/diagnostics/SimpleKtDiagnosticRenderer;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class KtDiagnosticRenderer {
    public /* synthetic */ KtDiagnosticRenderer(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract String getMessage();

    public abstract String render(KtDiagnostic diagnostic);

    public abstract Object[] renderParameters(KtDiagnostic diagnostic);

    private KtDiagnosticRenderer() {
    }
}
