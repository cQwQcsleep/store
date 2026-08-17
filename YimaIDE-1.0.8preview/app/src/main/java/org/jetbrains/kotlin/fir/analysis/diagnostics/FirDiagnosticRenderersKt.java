package org.jetbrains.kotlin.fir.analysis.diagnostics;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRendererKt;
import org.jetbrains.kotlin.diagnostics.rendering.RenderingContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirDiagnosticRenderersKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u001aT\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0005¨\u0006\u000b"}, d2 = {"joinToString", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", Argument.Delimiters.none, "T", "separator", Argument.Delimiters.none, "prefix", "postfix", "limit", Argument.Delimiters.none, "truncated", "org.jetbrains.kotlin:diagnostic-renderers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDiagnosticRenderersKt {
    public static String a(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, final DiagnosticParameterRenderer diagnosticParameterRenderer, Iterable iterable, final RenderingContext renderingContext) {
        iterable.getClass();
        renderingContext.getClass();
        return CollectionsKt.joinToString(iterable, charSequence, charSequence2, charSequence3, i, charSequence4, new Function1() { // from class: r45
            public final Object invoke(Object obj) {
                return FirDiagnosticRenderersKt.joinToString$lambda$0$0(diagnosticParameterRenderer, renderingContext, obj);
            }
        });
    }

    public static final <T> DiagnosticParameterRenderer<Iterable<? extends T>> joinToString(final DiagnosticParameterRenderer<? super T> diagnosticParameterRenderer, final CharSequence charSequence, final CharSequence charSequence2, final CharSequence charSequence3, final int i, final CharSequence charSequence4) {
        diagnosticParameterRenderer.getClass();
        charSequence.getClass();
        charSequence2.getClass();
        charSequence3.getClass();
        charSequence4.getClass();
        return DiagnosticParameterRendererKt.ContextDependentRenderer(new Function2() { // from class: q45
            public final Object invoke(Object obj, Object obj2) {
                return FirDiagnosticRenderersKt.a(charSequence, charSequence2, charSequence3, i, charSequence4, diagnosticParameterRenderer, (Iterable) obj, (RenderingContext) obj2);
            }
        });
    }

    public static /* synthetic */ DiagnosticParameterRenderer joinToString$default(DiagnosticParameterRenderer diagnosticParameterRenderer, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charSequence = ", ";
        }
        if ((i2 & 2) != 0) {
            charSequence2 = Argument.Delimiters.none;
        }
        if ((i2 & 4) != 0) {
            charSequence3 = Argument.Delimiters.none;
        }
        if ((i2 & 8) != 0) {
            i = -1;
        }
        if ((i2 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence5 = charSequence4;
        CharSequence charSequence6 = charSequence3;
        return joinToString(diagnosticParameterRenderer, charSequence, charSequence2, charSequence6, i, charSequence5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence joinToString$lambda$0$0(DiagnosticParameterRenderer diagnosticParameterRenderer, RenderingContext renderingContext, Object obj) {
        return diagnosticParameterRenderer.render(obj, renderingContext);
    }
}
