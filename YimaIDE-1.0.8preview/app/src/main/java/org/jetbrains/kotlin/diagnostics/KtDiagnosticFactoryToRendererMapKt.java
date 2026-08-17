package org.jetbrains.kotlin.diagnostics;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMapKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¨\u0006\b"}, d2 = {"KtDiagnosticFactoryToRendererMap", "Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", ModuleXmlParser.NAME, Argument.Delimiters.none, "init", "Lkotlin/Function1;", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtDiagnosticFactoryToRendererMapKt {
    public static final Lazy<KtDiagnosticFactoryToRendererMap> KtDiagnosticFactoryToRendererMap(final String str, final Function1<? super KtDiagnosticFactoryToRendererMap, Unit> function1) {
        str.getClass();
        function1.getClass();
        return LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new Function0() { // from class: ke8
            public final Object invoke() {
                return KtDiagnosticFactoryToRendererMapKt.a(str, function1);
            }
        });
    }

    public static KtDiagnosticFactoryToRendererMap a(String str, Function1 function1) {
        KtDiagnosticFactoryToRendererMap ktDiagnosticFactoryToRendererMap = new KtDiagnosticFactoryToRendererMap(str);
        function1.invoke(ktDiagnosticFactoryToRendererMap);
        return ktDiagnosticFactoryToRendererMap;
    }
}
