package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J\t\u0010\u001c\u001a\u00020\nHÆ\u0003J\t\u0010\u001d\u001a\u00020\fHÆ\u0003J;\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0014\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0083\u0004J\n\u0010#\u001a\u00020$HÖ\u0081\u0004J\n\u0010%\u001a\u00020&HÖ\u0081\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtOffsetsOnlySimpleDiagnostic;", "Lorg/jetbrains/kotlin/diagnostics/KtSimpleDiagnostic;", "Lorg/jetbrains/kotlin/diagnostics/KtOffsetsOnlyDiagnostic;", "element", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "positioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "<init>", "(Lorg/jetbrains/kotlin/AbstractKtSourceElement;Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;)V", "getElement", "()Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "getSeverity", "()Lorg/jetbrains/kotlin/diagnostics/Severity;", "getFactory", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "getPositioningStrategy", "()Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "getContext", "()Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class KtOffsetsOnlySimpleDiagnostic extends KtSimpleDiagnostic implements KtOffsetsOnlyDiagnostic {
    private final DiagnosticBaseContext context;
    private final AbstractKtSourceElement element;
    private final KtDiagnosticFactory0 factory;
    private final AbstractSourceElementPositioningStrategy positioningStrategy;
    private final Severity severity;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtOffsetsOnlySimpleDiagnostic(AbstractKtSourceElement abstractKtSourceElement, Severity severity, KtDiagnosticFactory0 ktDiagnosticFactory0, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, DiagnosticBaseContext diagnosticBaseContext) {
        super(null);
        abstractKtSourceElement.getClass();
        severity.getClass();
        ktDiagnosticFactory0.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        diagnosticBaseContext.getClass();
        this.element = abstractKtSourceElement;
        this.severity = severity;
        this.factory = ktDiagnosticFactory0;
        this.positioningStrategy = abstractSourceElementPositioningStrategy;
        this.context = diagnosticBaseContext;
    }

    public static /* synthetic */ KtOffsetsOnlySimpleDiagnostic copy$default(KtOffsetsOnlySimpleDiagnostic ktOffsetsOnlySimpleDiagnostic, AbstractKtSourceElement abstractKtSourceElement, Severity severity, KtDiagnosticFactory0 ktDiagnosticFactory0, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, DiagnosticBaseContext diagnosticBaseContext, int i, Object obj) {
        if ((i & 1) != 0) {
            abstractKtSourceElement = ktOffsetsOnlySimpleDiagnostic.element;
        }
        if ((i & 2) != 0) {
            severity = ktOffsetsOnlySimpleDiagnostic.severity;
        }
        if ((i & 4) != 0) {
            ktDiagnosticFactory0 = ktOffsetsOnlySimpleDiagnostic.factory;
        }
        if ((i & 8) != 0) {
            abstractSourceElementPositioningStrategy = ktOffsetsOnlySimpleDiagnostic.positioningStrategy;
        }
        if ((i & 16) != 0) {
            diagnosticBaseContext = ktOffsetsOnlySimpleDiagnostic.context;
        }
        DiagnosticBaseContext diagnosticBaseContext2 = diagnosticBaseContext;
        KtDiagnosticFactory0 ktDiagnosticFactory1 = ktDiagnosticFactory0;
        return ktOffsetsOnlySimpleDiagnostic.copy(abstractKtSourceElement, severity, ktDiagnosticFactory1, abstractSourceElementPositioningStrategy, diagnosticBaseContext2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AbstractKtSourceElement getElement() {
        return this.element;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Severity getSeverity() {
        return this.severity;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final KtDiagnosticFactory0 getFactory() {
        return this.factory;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final AbstractSourceElementPositioningStrategy getPositioningStrategy() {
        return this.positioningStrategy;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final DiagnosticBaseContext getContext() {
        return this.context;
    }

    public final KtOffsetsOnlySimpleDiagnostic copy(AbstractKtSourceElement element, Severity severity, KtDiagnosticFactory0 factory, AbstractSourceElementPositioningStrategy positioningStrategy, DiagnosticBaseContext context) {
        element.getClass();
        severity.getClass();
        factory.getClass();
        positioningStrategy.getClass();
        context.getClass();
        return new KtOffsetsOnlySimpleDiagnostic(element, severity, factory, positioningStrategy, context);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KtOffsetsOnlySimpleDiagnostic)) {
            return false;
        }
        KtOffsetsOnlySimpleDiagnostic ktOffsetsOnlySimpleDiagnostic = (KtOffsetsOnlySimpleDiagnostic) other;
        return Intrinsics.areEqual(this.element, ktOffsetsOnlySimpleDiagnostic.element) && this.severity == ktOffsetsOnlySimpleDiagnostic.severity && Intrinsics.areEqual(this.factory, ktOffsetsOnlySimpleDiagnostic.factory) && Intrinsics.areEqual(this.positioningStrategy, ktOffsetsOnlySimpleDiagnostic.positioningStrategy) && Intrinsics.areEqual(this.context, ktOffsetsOnlySimpleDiagnostic.context);
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public DiagnosticBaseContext getContext() {
        return this.context;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource
    public AbstractKtSourceElement getElement() {
        return this.element;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource
    public AbstractSourceElementPositioningStrategy getPositioningStrategy() {
        return this.positioningStrategy;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource, org.jetbrains.kotlin.diagnostics.KtDiagnostic, org.jetbrains.kotlin.diagnostics.DiagnosticMarker, org.jetbrains.kotlin.diagnostics.UnboundDiagnostic
    public Severity getSeverity() {
        return this.severity;
    }

    public int hashCode() {
        return (((((((this.element.hashCode() * 31) + this.severity.hashCode()) * 31) + this.factory.hashCode()) * 31) + this.positioningStrategy.hashCode()) * 31) + this.context.hashCode();
    }

    public String toString() {
        return "KtOffsetsOnlySimpleDiagnostic(element=" + this.element + ", severity=" + this.severity + ", factory=" + this.factory + ", positioningStrategy=" + this.positioningStrategy + ", context=" + this.context + ')';
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtSimpleDiagnostic, org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource, org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public KtDiagnosticFactory0 getFactory() {
        return this.factory;
    }
}
