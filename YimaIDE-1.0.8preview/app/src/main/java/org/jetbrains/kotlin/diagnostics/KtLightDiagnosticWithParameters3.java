package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u00020\u0005BY\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00028\u0000\u0012\u0006\u0010\t\u001a\u00028\u0001\u0012\u0006\u0010\n\u001a\u00028\u0002\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010$\u001a\u00020\u0007HÆ\u0003J\u000e\u0010%\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000e\u0010&\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000e\u0010'\u001a\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\u0018J\t\u0010(\u001a\u00020\fHÆ\u0003J\u001b\u0010)\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u000eHÆ\u0003J\t\u0010*\u001a\u00020\u0010HÆ\u0003J\t\u0010+\u001a\u00020\u0012HÆ\u0003J\u0082\u0001\u0010,\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00028\u00002\b\b\u0002\u0010\t\u001a\u00028\u00012\b\b\u0002\u0010\n\u001a\u00028\u00022\b\b\u0002\u0010\u000b\u001a\u00020\f2\u001a\b\u0002\u0010\r\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012HÆ\u0001¢\u0006\u0002\u0010-J\u0014\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u000101HÖ\u0083\u0004J\n\u00102\u001a\u000203HÖ\u0081\u0004J\n\u00104\u001a\u000205HÖ\u0081\u0004R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\b\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\t\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001a\u0010\u0018R\u0016\u0010\n\u001a\u00028\u0002X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001b\u0010\u0018R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR&\u0010\r\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0014\u0010\u0011\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u00066"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtLightDiagnosticWithParameters3;", "A", "B", "C", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters3;", "Lorg/jetbrains/kotlin/diagnostics/KtLightDiagnostic;", "element", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "a", "b", "c", "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "positioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "<init>", "(Lorg/jetbrains/kotlin/KtLightSourceElement;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;)V", "getElement", "()Lorg/jetbrains/kotlin/KtLightSourceElement;", "getA", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getB", "getC", "getSeverity", "()Lorg/jetbrains/kotlin/diagnostics/Severity;", "getFactory", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "getPositioningStrategy", "()Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "getContext", "()Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Lorg/jetbrains/kotlin/KtLightSourceElement;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;)Lorg/jetbrains/kotlin/diagnostics/KtLightDiagnosticWithParameters3;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class KtLightDiagnosticWithParameters3<A, B, C> extends KtDiagnosticWithParameters3<A, B, C> implements KtLightDiagnostic {
    private final A a;
    private final B b;
    private final C c;
    private final DiagnosticBaseContext context;
    private final KtLightSourceElement element;
    private final KtDiagnosticFactory3<A, B, C> factory;
    private final AbstractSourceElementPositioningStrategy positioningStrategy;
    private final Severity severity;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtLightDiagnosticWithParameters3(KtLightSourceElement ktLightSourceElement, A a, B b, C c, Severity severity, KtDiagnosticFactory3<A, B, C> ktDiagnosticFactory3, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, DiagnosticBaseContext diagnosticBaseContext) {
        super(null);
        ktLightSourceElement.getClass();
        severity.getClass();
        ktDiagnosticFactory3.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        diagnosticBaseContext.getClass();
        this.element = ktLightSourceElement;
        this.a = a;
        this.b = b;
        this.c = c;
        this.severity = severity;
        this.factory = ktDiagnosticFactory3;
        this.positioningStrategy = abstractSourceElementPositioningStrategy;
        this.context = diagnosticBaseContext;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ KtLightDiagnosticWithParameters3 copy$default(KtLightDiagnosticWithParameters3 ktLightDiagnosticWithParameters3, KtLightSourceElement ktLightSourceElement, Object obj, Object obj2, Object obj3, Severity severity, KtDiagnosticFactory3 ktDiagnosticFactory3, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, DiagnosticBaseContext diagnosticBaseContext, int i, Object obj4) {
        if ((i & 1) != 0) {
            ktLightSourceElement = ktLightDiagnosticWithParameters3.element;
        }
        if ((i & 2) != 0) {
            obj = ktLightDiagnosticWithParameters3.a;
        }
        if ((i & 4) != 0) {
            obj2 = ktLightDiagnosticWithParameters3.b;
        }
        if ((i & 8) != 0) {
            obj3 = ktLightDiagnosticWithParameters3.c;
        }
        if ((i & 16) != 0) {
            severity = ktLightDiagnosticWithParameters3.severity;
        }
        if ((i & 32) != 0) {
            ktDiagnosticFactory3 = ktLightDiagnosticWithParameters3.factory;
        }
        if ((i & 64) != 0) {
            abstractSourceElementPositioningStrategy = ktLightDiagnosticWithParameters3.positioningStrategy;
        }
        if ((i & 128) != 0) {
            diagnosticBaseContext = ktLightDiagnosticWithParameters3.context;
        }
        AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy2 = abstractSourceElementPositioningStrategy;
        DiagnosticBaseContext diagnosticBaseContext2 = diagnosticBaseContext;
        Severity severity2 = severity;
        KtDiagnosticFactory3 ktDiagnosticFactory4 = ktDiagnosticFactory3;
        return ktLightDiagnosticWithParameters3.copy(ktLightSourceElement, obj, obj2, obj3, severity2, ktDiagnosticFactory4, abstractSourceElementPositioningStrategy2, diagnosticBaseContext2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final KtLightSourceElement getElement() {
        return this.element;
    }

    public final A component2() {
        return this.a;
    }

    public final B component3() {
        return this.b;
    }

    public final C component4() {
        return this.c;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Severity getSeverity() {
        return this.severity;
    }

    public final KtDiagnosticFactory3<A, B, C> component6() {
        return this.factory;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final AbstractSourceElementPositioningStrategy getPositioningStrategy() {
        return this.positioningStrategy;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final DiagnosticBaseContext getContext() {
        return this.context;
    }

    public final KtLightDiagnosticWithParameters3<A, B, C> copy(KtLightSourceElement element, A a, B b, C c, Severity severity, KtDiagnosticFactory3<A, B, C> factory, AbstractSourceElementPositioningStrategy positioningStrategy, DiagnosticBaseContext context) {
        element.getClass();
        severity.getClass();
        factory.getClass();
        positioningStrategy.getClass();
        context.getClass();
        return new KtLightDiagnosticWithParameters3<>(element, a, b, c, severity, factory, positioningStrategy, context);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KtLightDiagnosticWithParameters3)) {
            return false;
        }
        KtLightDiagnosticWithParameters3 ktLightDiagnosticWithParameters3 = (KtLightDiagnosticWithParameters3) other;
        return Intrinsics.areEqual(this.element, ktLightDiagnosticWithParameters3.element) && Intrinsics.areEqual(this.a, ktLightDiagnosticWithParameters3.a) && Intrinsics.areEqual(this.b, ktLightDiagnosticWithParameters3.b) && Intrinsics.areEqual(this.c, ktLightDiagnosticWithParameters3.c) && this.severity == ktLightDiagnosticWithParameters3.severity && Intrinsics.areEqual(this.factory, ktLightDiagnosticWithParameters3.factory) && Intrinsics.areEqual(this.positioningStrategy, ktLightDiagnosticWithParameters3.positioningStrategy) && Intrinsics.areEqual(this.context, ktLightDiagnosticWithParameters3.context);
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithParameters3, org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters3Marker
    public A getA() {
        return this.a;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithParameters3, org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters3Marker
    public B getB() {
        return this.b;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithParameters3, org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters3Marker
    public C getC() {
        return this.c;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public DiagnosticBaseContext getContext() {
        return this.context;
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
        int iHashCode2 = (iHashCode + (a == null ? 0 : a.hashCode())) * 31;
        B b = this.b;
        int iHashCode3 = (iHashCode2 + (b == null ? 0 : b.hashCode())) * 31;
        C c = this.c;
        return ((((((((iHashCode3 + (c != null ? c.hashCode() : 0)) * 31) + this.severity.hashCode()) * 31) + this.factory.hashCode()) * 31) + this.positioningStrategy.hashCode()) * 31) + this.context.hashCode();
    }

    public String toString() {
        return "KtLightDiagnosticWithParameters3(element=" + this.element + ", a=" + this.a + ", b=" + this.b + ", c=" + this.c + ", severity=" + this.severity + ", factory=" + this.factory + ", positioningStrategy=" + this.positioningStrategy + ", context=" + this.context + ')';
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource
    public KtLightSourceElement getElement() {
        return this.element;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithParameters3, org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource, org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public KtDiagnosticFactory3<A, B, C> getFactory() {
        return this.factory;
    }
}
