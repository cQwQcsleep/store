package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B=\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\u000e\u0010\u001f\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0014J\t\u0010 \u001a\u00020\bHÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\nHÆ\u0003J\t\u0010\"\u001a\u00020\fHÆ\u0003J\t\u0010#\u001a\u00020\u000eHÆ\u0003JV\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00028\u00002\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001¢\u0006\u0002\u0010%J\u0014\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0083\u0004J\n\u0010*\u001a\u00020+HÖ\u0081\u0004J\n\u0010,\u001a\u00020-HÖ\u0081\u0004R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0006\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006."}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtOffsetsOnlyDiagnosticWithParameters1;", "A", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters1;", "Lorg/jetbrains/kotlin/diagnostics/KtOffsetsOnlyDiagnostic;", "element", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "a", "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "positioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "<init>", "(Lorg/jetbrains/kotlin/AbstractKtSourceElement;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;)V", "getElement", "()Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "getA", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getSeverity", "()Lorg/jetbrains/kotlin/diagnostics/Severity;", "getFactory", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "getPositioningStrategy", "()Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "getContext", "()Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Lorg/jetbrains/kotlin/AbstractKtSourceElement;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;)Lorg/jetbrains/kotlin/diagnostics/KtOffsetsOnlyDiagnosticWithParameters1;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class KtOffsetsOnlyDiagnosticWithParameters1<A> extends KtDiagnosticWithParameters1<A> implements KtOffsetsOnlyDiagnostic {
    private final A a;
    private final DiagnosticBaseContext context;
    private final AbstractKtSourceElement element;
    private final KtDiagnosticFactory1<A> factory;
    private final AbstractSourceElementPositioningStrategy positioningStrategy;
    private final Severity severity;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtOffsetsOnlyDiagnosticWithParameters1(AbstractKtSourceElement abstractKtSourceElement, A a, Severity severity, KtDiagnosticFactory1<A> ktDiagnosticFactory1, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, DiagnosticBaseContext diagnosticBaseContext) {
        super(null);
        abstractKtSourceElement.getClass();
        severity.getClass();
        ktDiagnosticFactory1.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        diagnosticBaseContext.getClass();
        this.element = abstractKtSourceElement;
        this.a = a;
        this.severity = severity;
        this.factory = ktDiagnosticFactory1;
        this.positioningStrategy = abstractSourceElementPositioningStrategy;
        this.context = diagnosticBaseContext;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ KtOffsetsOnlyDiagnosticWithParameters1 copy$default(KtOffsetsOnlyDiagnosticWithParameters1 ktOffsetsOnlyDiagnosticWithParameters1, AbstractKtSourceElement abstractKtSourceElement, Object obj, Severity severity, KtDiagnosticFactory1 ktDiagnosticFactory1, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, DiagnosticBaseContext diagnosticBaseContext, int i, Object obj2) {
        if ((i & 1) != 0) {
            abstractKtSourceElement = ktOffsetsOnlyDiagnosticWithParameters1.element;
        }
        if ((i & 2) != 0) {
            obj = ktOffsetsOnlyDiagnosticWithParameters1.a;
        }
        if ((i & 4) != 0) {
            severity = ktOffsetsOnlyDiagnosticWithParameters1.severity;
        }
        if ((i & 8) != 0) {
            ktDiagnosticFactory1 = ktOffsetsOnlyDiagnosticWithParameters1.factory;
        }
        if ((i & 16) != 0) {
            abstractSourceElementPositioningStrategy = ktOffsetsOnlyDiagnosticWithParameters1.positioningStrategy;
        }
        if ((i & 32) != 0) {
            diagnosticBaseContext = ktOffsetsOnlyDiagnosticWithParameters1.context;
        }
        AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy2 = abstractSourceElementPositioningStrategy;
        DiagnosticBaseContext diagnosticBaseContext2 = diagnosticBaseContext;
        return ktOffsetsOnlyDiagnosticWithParameters1.copy(abstractKtSourceElement, obj, severity, ktDiagnosticFactory1, abstractSourceElementPositioningStrategy2, diagnosticBaseContext2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AbstractKtSourceElement getElement() {
        return this.element;
    }

    public final A component2() {
        return this.a;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Severity getSeverity() {
        return this.severity;
    }

    public final KtDiagnosticFactory1<A> component4() {
        return this.factory;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final AbstractSourceElementPositioningStrategy getPositioningStrategy() {
        return this.positioningStrategy;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final DiagnosticBaseContext getContext() {
        return this.context;
    }

    public final KtOffsetsOnlyDiagnosticWithParameters1<A> copy(AbstractKtSourceElement element, A a, Severity severity, KtDiagnosticFactory1<A> factory, AbstractSourceElementPositioningStrategy positioningStrategy, DiagnosticBaseContext context) {
        element.getClass();
        severity.getClass();
        factory.getClass();
        positioningStrategy.getClass();
        context.getClass();
        return new KtOffsetsOnlyDiagnosticWithParameters1<>(element, a, severity, factory, positioningStrategy, context);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KtOffsetsOnlyDiagnosticWithParameters1)) {
            return false;
        }
        KtOffsetsOnlyDiagnosticWithParameters1 ktOffsetsOnlyDiagnosticWithParameters1 = (KtOffsetsOnlyDiagnosticWithParameters1) other;
        return Intrinsics.areEqual(this.element, ktOffsetsOnlyDiagnosticWithParameters1.element) && Intrinsics.areEqual(this.a, ktOffsetsOnlyDiagnosticWithParameters1.a) && this.severity == ktOffsetsOnlyDiagnosticWithParameters1.severity && Intrinsics.areEqual(this.factory, ktOffsetsOnlyDiagnosticWithParameters1.factory) && Intrinsics.areEqual(this.positioningStrategy, ktOffsetsOnlyDiagnosticWithParameters1.positioningStrategy) && Intrinsics.areEqual(this.context, ktOffsetsOnlyDiagnosticWithParameters1.context);
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithParameters1, org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters1Marker
    public A getA() {
        return this.a;
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
        int iHashCode = this.element.hashCode() * 31;
        A a = this.a;
        return ((((((((iHashCode + (a == null ? 0 : a.hashCode())) * 31) + this.severity.hashCode()) * 31) + this.factory.hashCode()) * 31) + this.positioningStrategy.hashCode()) * 31) + this.context.hashCode();
    }

    public String toString() {
        return "KtOffsetsOnlyDiagnosticWithParameters1(element=" + this.element + ", a=" + this.a + ", severity=" + this.severity + ", factory=" + this.factory + ", positioningStrategy=" + this.positioningStrategy + ", context=" + this.context + ')';
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithParameters1, org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource, org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public KtDiagnosticFactory1<A> getFactory() {
        return this.factory;
    }
}
