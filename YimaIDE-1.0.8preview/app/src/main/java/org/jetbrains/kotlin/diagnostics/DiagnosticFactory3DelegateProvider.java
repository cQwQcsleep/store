package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.DummyDelegate;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\u00020\u0004B+\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ?\u0010\u000f\u001a\"\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00110\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0086\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory3DelegateProvider;", "A", "B", "C", Argument.Delimiters.none, "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "positioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "psiType", "Lkotlin/reflect/KClass;", "container", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "<init>", "(Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lkotlin/reflect/KClass;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;)V", "provideDelegate", "Lkotlin/properties/ReadOnlyProperty;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "thisRef", "prop", "Lkotlin/reflect/KProperty;", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DiagnosticFactory3DelegateProvider<A, B, C> {
    private final KtDiagnosticsContainer container;
    private final AbstractSourceElementPositioningStrategy positioningStrategy;
    private final KClass<?> psiType;
    private final Severity severity;

    public DiagnosticFactory3DelegateProvider(Severity severity, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, KClass<?> kClass, KtDiagnosticsContainer ktDiagnosticsContainer) {
        severity.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        kClass.getClass();
        ktDiagnosticsContainer.getClass();
        this.severity = severity;
        this.positioningStrategy = abstractSourceElementPositioningStrategy;
        this.psiType = kClass;
        this.container = ktDiagnosticsContainer;
    }

    public final ReadOnlyProperty<Object, KtDiagnosticFactory3<A, B, C>> provideDelegate(Object thisRef, KProperty<?> prop) {
        prop.getClass();
        return new DummyDelegate(new KtDiagnosticFactory3(prop.getName(), this.severity, this.positioningStrategy, this.psiType, this.container.getRendererFactory()));
    }
}
