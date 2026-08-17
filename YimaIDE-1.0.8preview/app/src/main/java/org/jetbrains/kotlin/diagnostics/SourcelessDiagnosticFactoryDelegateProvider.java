package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.DummyDelegate;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\n0\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/SourcelessDiagnosticFactoryDelegateProvider;", Argument.Delimiters.none, "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "container", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "<init>", "(Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;)V", "provideDelegate", "Lkotlin/properties/ReadOnlyProperty;", "Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "thisRef", "prop", "Lkotlin/reflect/KProperty;", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SourcelessDiagnosticFactoryDelegateProvider {
    private final KtDiagnosticsContainer container;
    private final Severity severity;

    public SourcelessDiagnosticFactoryDelegateProvider(Severity severity, KtDiagnosticsContainer ktDiagnosticsContainer) {
        severity.getClass();
        ktDiagnosticsContainer.getClass();
        this.severity = severity;
        this.container = ktDiagnosticsContainer;
    }

    public final ReadOnlyProperty<Object, KtSourcelessDiagnosticFactory> provideDelegate(Object thisRef, KProperty<?> prop) {
        prop.getClass();
        return new DummyDelegate(new KtSourcelessDiagnosticFactory(prop.getName(), this.severity, this.container.getRendererFactory()));
    }
}
