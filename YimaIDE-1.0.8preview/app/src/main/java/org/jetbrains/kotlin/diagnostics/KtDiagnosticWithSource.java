package org.jetbrains.kotlin.diagnostics;

import com.intellij.openapi.util.TextRange;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\u00020\u0002B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u0012X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f\u0082\u0001\u0005 !\"#$¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithSource;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticMarker;", "<init>", "()V", "element", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "getElement", "()Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;", "getFactory", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;", "positioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "getPositioningStrategy", "()Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "getSeverity", "()Lorg/jetbrains/kotlin/diagnostics/Severity;", "textRanges", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "getTextRanges", "()Ljava/util/List;", "isValid", Argument.Delimiters.none, "()Z", "firstRange", "getFirstRange", "()Lcom/intellij/openapi/util/TextRange;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters1;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters2;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters3;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters4;", "Lorg/jetbrains/kotlin/diagnostics/KtSimpleDiagnostic;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class KtDiagnosticWithSource extends KtDiagnostic implements DiagnosticMarker {
    private KtDiagnosticWithSource() {
        super(null);
    }

    public abstract AbstractKtSourceElement getElement();

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public abstract KtDiagnosticFactoryN getFactory();

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public final TextRange getFirstRange() {
        return DiagnosticRangeUtils.firstRange(getTextRanges());
    }

    public abstract AbstractSourceElementPositioningStrategy getPositioningStrategy();

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnostic, org.jetbrains.kotlin.diagnostics.DiagnosticMarker, org.jetbrains.kotlin.diagnostics.UnboundDiagnostic
    public abstract Severity getSeverity();

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticMarker, org.jetbrains.kotlin.diagnostics.UnboundDiagnostic
    public final List<TextRange> getTextRanges() {
        return getPositioningStrategy().markDiagnostic(this);
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public final boolean isValid() {
        return getPositioningStrategy().isValid(getElement());
    }

    public /* synthetic */ KtDiagnosticWithSource(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
