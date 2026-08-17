package org.jetbrains.kotlin.diagnostics;

import java.util.Arrays;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.UnboundDiagnostic;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticRenderer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u0000 \u001d*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001\u001dB\u001b\b\u0004\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB\u0011\b\u0014\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\nJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u0005J\u0014\u0010\u0018\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0013J\u0013\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u0002¢\u0006\u0002\u0010\u001bJ\n\u0010\u001c\u001a\u00020\u0005H\u0096\u0080\u0004R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory;", "D", "Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;", Argument.Delimiters.none, "_name", Argument.Delimiters.none, "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/diagnostics/Severity;)V", "(Lorg/jetbrains/kotlin/diagnostics/Severity;)V", "getSeverity", "()Lorg/jetbrains/kotlin/diagnostics/Severity;", ModuleXmlParser.NAME, "getName", "()Ljava/lang/String;", "initializeName", Argument.Delimiters.none, "defaultRenderer", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticRenderer;", "getDefaultRenderer", "()Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticRenderer;", "setDefaultRenderer", "(Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticRenderer;)V", "initDefaultRenderer", "cast", "diagnostic", "(Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;)Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;", "toString", "Companion", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class DiagnosticFactory<D extends UnboundDiagnostic> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String _name;
    private DiagnosticRenderer<? super D> defaultRenderer;
    private final Severity severity;

    public DiagnosticFactory(String str, Severity severity) {
        severity.getClass();
        this._name = str;
        this.severity = severity;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final D cast(UnboundDiagnostic diagnostic) {
        diagnostic.getClass();
        if (diagnostic.getFactory() == this) {
            return diagnostic;
        }
        StringBuilder sb = new StringBuilder("Factory mismatch: expected ");
        sb.append(this);
        ywd.a(sb, " but was ", diagnostic.getFactory());
        return null;
    }

    public DiagnosticRenderer<D> getDefaultRenderer() {
        return this.defaultRenderer;
    }

    public String getName() {
        String str = this._name;
        str.getClass();
        return str;
    }

    public Severity getSeverity() {
        return this.severity;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void initDefaultRenderer(DiagnosticRenderer<?> defaultRenderer) {
        setDefaultRenderer(defaultRenderer);
    }

    public final void initializeName(String name) {
        name.getClass();
        this._name = name;
    }

    public void setDefaultRenderer(DiagnosticRenderer<? super D> diagnosticRenderer) {
        this.defaultRenderer = diagnosticRenderer;
    }

    public String toString() {
        String str = this._name;
        return str == null ? "<Anonymous DiagnosticFactory>" : str;
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JG\u0010\u0004\u001a\u0002H\u0005\"\b\b\u0001\u0010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\"\u0010\b\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00050\n0\t\"\n\u0012\u0006\b\u0001\u0012\u0002H\u00050\nH\u0007b\u0002\b\f¢\u0006\u0002\u0010\u000bJ3\u0010\u0004\u001a\u0002H\u0005\"\b\b\u0001\u0010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0014\u0010\b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00050\n0\r¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory$Companion;", Argument.Delimiters.none, "<init>", "()V", "cast", "D", "Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;", "diagnostic", "factories", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory;", "(Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;[Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory;)Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;", "Ljava/lang/SafeVarargs;", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;Ljava/util/Collection;)Lorg/jetbrains/kotlin/diagnostics/UnboundDiagnostic;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final <D extends UnboundDiagnostic> D cast(UnboundDiagnostic diagnostic, Collection<? extends DiagnosticFactory<? extends D>> factories) {
            diagnostic.getClass();
            factories.getClass();
            for (DiagnosticFactory<? extends D> diagnosticFactory : factories) {
                if (diagnostic.getFactory() == diagnosticFactory) {
                    return (D) diagnosticFactory.cast(diagnostic);
                }
            }
            StringBuilder sb = new StringBuilder("Factory mismatch: expected one of ");
            sb.append(factories);
            qk5.a(sb, " but was ", diagnostic.getFactory());
            return null;
        }

        private Companion() {
        }

        @SafeVarargs
        public final <D extends UnboundDiagnostic> D cast(UnboundDiagnostic diagnostic, DiagnosticFactory<? extends D>... factories) {
            diagnostic.getClass();
            factories.getClass();
            return (D) cast(diagnostic, CollectionsKt.listOf(Arrays.copyOf(factories, factories.length)));
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DiagnosticFactory(Severity severity) {
        this(null, severity);
        severity.getClass();
    }
}
