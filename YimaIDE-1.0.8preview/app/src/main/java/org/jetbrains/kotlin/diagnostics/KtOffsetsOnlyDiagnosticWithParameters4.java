package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0004\b\u0003\u0010\u00042\u001a\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u00052\u00020\u0006Bg\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00028\u0000\u0012\u0006\u0010\n\u001a\u00028\u0001\u0012\u0006\u0010\u000b\u001a\u00028\u0002\u0012\u0006\u0010\f\u001a\u00028\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010'\u001a\u00020\bHÆ\u0003J\u000e\u0010(\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000e\u0010)\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000e\u0010*\u001a\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000e\u0010+\u001a\u00028\u0003HÆ\u0003¢\u0006\u0002\u0010\u001aJ\t\u0010,\u001a\u00020\u000eHÆ\u0003J!\u0010-\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0010HÆ\u0003J\t\u0010.\u001a\u00020\u0012HÆ\u0003J\t\u0010/\u001a\u00020\u0014HÆ\u0003J\u0098\u0001\u00100\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00002\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00028\u00002\b\b\u0002\u0010\n\u001a\u00028\u00012\b\b\u0002\u0010\u000b\u001a\u00028\u00022\b\b\u0002\u0010\f\u001a\u00028\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2 \b\u0002\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014HÆ\u0001¢\u0006\u0002\u00101J\u0014\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u000105HÖ\u0083\u0004J\n\u00106\u001a\u000207HÖ\u0081\u0004J\n\u00108\u001a\u000209HÖ\u0081\u0004R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\t\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\n\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001c\u0010\u001aR\u0016\u0010\u000b\u001a\u00028\u0002X\u0096\u0004¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001d\u0010\u001aR\u0016\u0010\f\u001a\u00028\u0003X\u0096\u0004¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001e\u0010\u001aR\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R,\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010\u0011\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&¨\u0006:"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtOffsetsOnlyDiagnosticWithParameters4;", "A", "B", "C", "D", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters4;", "Lorg/jetbrains/kotlin/diagnostics/KtOffsetsOnlyDiagnostic;", "element", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "a", "b", "c", "d", "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;", "positioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "<init>", "(Lorg/jetbrains/kotlin/AbstractKtSourceElement;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;)V", "getElement", "()Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "getA", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getB", "getC", "getD", "getSeverity", "()Lorg/jetbrains/kotlin/diagnostics/Severity;", "getFactory", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;", "getPositioningStrategy", "()Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "getContext", "()Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Lorg/jetbrains/kotlin/AbstractKtSourceElement;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;)Lorg/jetbrains/kotlin/diagnostics/KtOffsetsOnlyDiagnosticWithParameters4;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class KtOffsetsOnlyDiagnosticWithParameters4<A, B, C, D> extends KtDiagnosticWithParameters4<A, B, C, D> implements KtOffsetsOnlyDiagnostic {
    private final A a;
    private final B b;
    private final C c;
    private final DiagnosticBaseContext context;
    private final D d;
    private final AbstractKtSourceElement element;
    private final KtDiagnosticFactory4<A, B, C, D> factory;
    private final AbstractSourceElementPositioningStrategy positioningStrategy;
    private final Severity severity;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtOffsetsOnlyDiagnosticWithParameters4(AbstractKtSourceElement abstractKtSourceElement, A a, B b, C c, D d, Severity severity, KtDiagnosticFactory4<A, B, C, D> ktDiagnosticFactory4, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, DiagnosticBaseContext diagnosticBaseContext) {
        super(null);
        abstractKtSourceElement.getClass();
        severity.getClass();
        ktDiagnosticFactory4.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        diagnosticBaseContext.getClass();
        this.element = abstractKtSourceElement;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.severity = severity;
        this.factory = ktDiagnosticFactory4;
        this.positioningStrategy = abstractSourceElementPositioningStrategy;
        this.context = diagnosticBaseContext;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ KtOffsetsOnlyDiagnosticWithParameters4 copy$default(KtOffsetsOnlyDiagnosticWithParameters4 ktOffsetsOnlyDiagnosticWithParameters4, AbstractKtSourceElement abstractKtSourceElement, Object obj, Object obj2, Object obj3, Object obj4, Severity severity, KtDiagnosticFactory4 ktDiagnosticFactory4, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, DiagnosticBaseContext diagnosticBaseContext, int i, Object obj5) {
        if ((i & 1) != 0) {
            abstractKtSourceElement = ktOffsetsOnlyDiagnosticWithParameters4.element;
        }
        if ((i & 2) != 0) {
            obj = ktOffsetsOnlyDiagnosticWithParameters4.a;
        }
        if ((i & 4) != 0) {
            obj2 = ktOffsetsOnlyDiagnosticWithParameters4.b;
        }
        if ((i & 8) != 0) {
            obj3 = ktOffsetsOnlyDiagnosticWithParameters4.c;
        }
        if ((i & 16) != 0) {
            obj4 = ktOffsetsOnlyDiagnosticWithParameters4.d;
        }
        if ((i & 32) != 0) {
            severity = ktOffsetsOnlyDiagnosticWithParameters4.severity;
        }
        if ((i & 64) != 0) {
            ktDiagnosticFactory4 = ktOffsetsOnlyDiagnosticWithParameters4.factory;
        }
        if ((i & 128) != 0) {
            abstractSourceElementPositioningStrategy = ktOffsetsOnlyDiagnosticWithParameters4.positioningStrategy;
        }
        if ((i & 256) != 0) {
            diagnosticBaseContext = ktOffsetsOnlyDiagnosticWithParameters4.context;
        }
        AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy2 = abstractSourceElementPositioningStrategy;
        DiagnosticBaseContext diagnosticBaseContext2 = diagnosticBaseContext;
        Severity severity2 = severity;
        KtDiagnosticFactory4 ktDiagnosticFactory5 = ktDiagnosticFactory4;
        Object obj6 = obj4;
        Object obj7 = obj2;
        return ktOffsetsOnlyDiagnosticWithParameters4.copy(abstractKtSourceElement, obj, obj7, obj3, obj6, severity2, ktDiagnosticFactory5, abstractSourceElementPositioningStrategy2, diagnosticBaseContext2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AbstractKtSourceElement getElement() {
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

    public final D component5() {
        return this.d;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Severity getSeverity() {
        return this.severity;
    }

    public final KtDiagnosticFactory4<A, B, C, D> component7() {
        return this.factory;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final AbstractSourceElementPositioningStrategy getPositioningStrategy() {
        return this.positioningStrategy;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final DiagnosticBaseContext getContext() {
        return this.context;
    }

    public final KtOffsetsOnlyDiagnosticWithParameters4<A, B, C, D> copy(AbstractKtSourceElement element, A a, B b, C c, D d, Severity severity, KtDiagnosticFactory4<A, B, C, D> factory, AbstractSourceElementPositioningStrategy positioningStrategy, DiagnosticBaseContext context) {
        element.getClass();
        severity.getClass();
        factory.getClass();
        positioningStrategy.getClass();
        context.getClass();
        return new KtOffsetsOnlyDiagnosticWithParameters4<>(element, a, b, c, d, severity, factory, positioningStrategy, context);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KtOffsetsOnlyDiagnosticWithParameters4)) {
            return false;
        }
        KtOffsetsOnlyDiagnosticWithParameters4 ktOffsetsOnlyDiagnosticWithParameters4 = (KtOffsetsOnlyDiagnosticWithParameters4) other;
        return Intrinsics.areEqual(this.element, ktOffsetsOnlyDiagnosticWithParameters4.element) && Intrinsics.areEqual(this.a, ktOffsetsOnlyDiagnosticWithParameters4.a) && Intrinsics.areEqual(this.b, ktOffsetsOnlyDiagnosticWithParameters4.b) && Intrinsics.areEqual(this.c, ktOffsetsOnlyDiagnosticWithParameters4.c) && Intrinsics.areEqual(this.d, ktOffsetsOnlyDiagnosticWithParameters4.d) && this.severity == ktOffsetsOnlyDiagnosticWithParameters4.severity && Intrinsics.areEqual(this.factory, ktOffsetsOnlyDiagnosticWithParameters4.factory) && Intrinsics.areEqual(this.positioningStrategy, ktOffsetsOnlyDiagnosticWithParameters4.positioningStrategy) && Intrinsics.areEqual(this.context, ktOffsetsOnlyDiagnosticWithParameters4.context);
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithParameters4, org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters4Marker
    public A getA() {
        return this.a;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithParameters4, org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters4Marker
    public B getB() {
        return this.b;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithParameters4, org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters4Marker
    public C getC() {
        return this.c;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public DiagnosticBaseContext getContext() {
        return this.context;
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithParameters4, org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters4Marker
    public D getD() {
        return this.d;
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
        int iHashCode2 = (iHashCode + (a == null ? 0 : a.hashCode())) * 31;
        B b = this.b;
        int iHashCode3 = (iHashCode2 + (b == null ? 0 : b.hashCode())) * 31;
        C c = this.c;
        int iHashCode4 = (iHashCode3 + (c == null ? 0 : c.hashCode())) * 31;
        D d = this.d;
        return ((((((((iHashCode4 + (d != null ? d.hashCode() : 0)) * 31) + this.severity.hashCode()) * 31) + this.factory.hashCode()) * 31) + this.positioningStrategy.hashCode()) * 31) + this.context.hashCode();
    }

    public String toString() {
        return "KtOffsetsOnlyDiagnosticWithParameters4(element=" + this.element + ", a=" + this.a + ", b=" + this.b + ", c=" + this.c + ", d=" + this.d + ", severity=" + this.severity + ", factory=" + this.factory + ", positioningStrategy=" + this.positioningStrategy + ", context=" + this.context + ')';
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithParameters4, org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource, org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public KtDiagnosticFactory4<A, B, C, D> getFactory() {
        return this.factory;
    }
}
