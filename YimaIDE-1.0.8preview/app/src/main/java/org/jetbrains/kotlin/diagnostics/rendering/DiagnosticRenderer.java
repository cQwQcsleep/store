package org.jetbrains.kotlin.diagnostics.rendering;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.UnboundDiagnostic;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u00022\u00020\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0007J\u001f\u0010\b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00030\t2\u0006\u0010\u0006\u001a\u00028\u0000H&¢\u0006\u0002\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticRenderer;", "D", "Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;", Argument.Delimiters.none, "render", Argument.Delimiters.none, "diagnostic", "(Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;)Ljava/lang/String;", "renderParameters", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;)[Ljava/lang/Object;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface DiagnosticRenderer<D extends UnboundDiagnostic> {
    String render(D diagnostic);

    Object[] renderParameters(D diagnostic);
}
