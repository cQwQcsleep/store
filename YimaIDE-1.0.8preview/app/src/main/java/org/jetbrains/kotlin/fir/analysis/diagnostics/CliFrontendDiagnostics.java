package org.jetbrains.kotlin.fir.analysis.diagnostics;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryDslKt;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMap;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMapKt;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;
import org.jetbrains.kotlin.diagnostics.rendering.BaseSourcelessDiagnosticRendererFactory;
import org.jetbrains.kotlin.fir.analysis.diagnostics.CliFrontendDiagnostics;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001eB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007R\u001b\u0010\r\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\t\u001a\u0004\b\u000e\u0010\u0007R\u001b\u0010\u0010\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\t\u001a\u0004\b\u0011\u0010\u0007R\u001b\u0010\u0013\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\t\u001a\u0004\b\u0014\u0010\u0007R\u001b\u0010\u0016\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0017\u0010\u0007R\u001b\u0010\u0019\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\t\u001a\u0004\b\u001a\u0010\u0007¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/CliFrontendDiagnostics;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "<init>", "()V", "OPT_IN_REQUIREMENT_MARKER_IS_UNRESOLVED", "Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "getOPT_IN_REQUIREMENT_MARKER_IS_UNRESOLVED", "()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "OPT_IN_REQUIREMENT_MARKER_IS_UNRESOLVED$delegate", "Lkotlin/properties/ReadOnlyProperty;", "NOT_AN_OPT_IN_REQUIREMENT_MARKER", "getNOT_AN_OPT_IN_REQUIREMENT_MARKER", "NOT_AN_OPT_IN_REQUIREMENT_MARKER$delegate", "OPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED", "getOPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED", "OPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED$delegate", "OPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED_ERROR", "getOPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED_ERROR", "OPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED_ERROR$delegate", "CONTEXT_PARAMETERS_ARE_DEPRECATED", "getCONTEXT_PARAMETERS_ARE_DEPRECATED", "CONTEXT_PARAMETERS_ARE_DEPRECATED$delegate", "MISSING_DIAGNOSTIC_NAME", "getMISSING_DIAGNOSTIC_NAME", "MISSING_DIAGNOSTIC_NAME$delegate", "ERROR_SEVERITY_CHANGED", "getERROR_SEVERITY_CHANGED", "ERROR_SEVERITY_CHANGED$delegate", "getRendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "Messages", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliFrontendDiagnostics extends KtDiagnosticsContainer {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;

    /* JADX INFO: renamed from: CONTEXT_PARAMETERS_ARE_DEPRECATED$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty CONTEXT_PARAMETERS_ARE_DEPRECATED;

    /* JADX INFO: renamed from: ERROR_SEVERITY_CHANGED$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty ERROR_SEVERITY_CHANGED;
    public static final CliFrontendDiagnostics INSTANCE;

    /* JADX INFO: renamed from: MISSING_DIAGNOSTIC_NAME$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty MISSING_DIAGNOSTIC_NAME;

    /* JADX INFO: renamed from: NOT_AN_OPT_IN_REQUIREMENT_MARKER$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty NOT_AN_OPT_IN_REQUIREMENT_MARKER;

    /* JADX INFO: renamed from: OPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty OPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED;

    /* JADX INFO: renamed from: OPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED_ERROR$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty OPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED_ERROR;

    /* JADX INFO: renamed from: OPT_IN_REQUIREMENT_MARKER_IS_UNRESOLVED$delegate, reason: from kotlin metadata */
    private static final ReadOnlyProperty OPT_IN_REQUIREMENT_MARKER_IS_UNRESOLVED;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/diagnostics/CliFrontendDiagnostics$Messages;", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseSourcelessDiagnosticRendererFactory;", "<init>", "()V", "MAP", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "getMAP", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "MAP$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Messages extends BaseSourcelessDiagnosticRendererFactory {
        public static final Messages INSTANCE = new Messages();

        /* JADX INFO: renamed from: MAP$delegate, reason: from kotlin metadata */
        private static final Lazy MAP = KtDiagnosticFactoryToRendererMapKt.KtDiagnosticFactoryToRendererMap("FrontendCli", new Function1() { // from class: uz1
            public final Object invoke(Object obj) {
                return CliFrontendDiagnostics.Messages.a((KtDiagnosticFactoryToRendererMap) obj);
            }
        });

        private Messages() {
        }

        public static Unit a(KtDiagnosticFactoryToRendererMap ktDiagnosticFactoryToRendererMap) {
            ktDiagnosticFactoryToRendererMap.getClass();
            CliFrontendDiagnostics cliFrontendDiagnostics = CliFrontendDiagnostics.INSTANCE;
            ktDiagnosticFactoryToRendererMap.put(cliFrontendDiagnostics.getOPT_IN_REQUIREMENT_MARKER_IS_UNRESOLVED(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliFrontendDiagnostics.getNOT_AN_OPT_IN_REQUIREMENT_MARKER(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliFrontendDiagnostics.getOPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliFrontendDiagnostics.getOPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED_ERROR(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliFrontendDiagnostics.getCONTEXT_PARAMETERS_ARE_DEPRECATED(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliFrontendDiagnostics.getMISSING_DIAGNOSTIC_NAME(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            ktDiagnosticFactoryToRendererMap.put(cliFrontendDiagnostics.getERROR_SEVERITY_CHANGED(), BaseSourcelessDiagnosticRendererFactory.MESSAGE_PLACEHOLDER);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory
        public KtDiagnosticFactoryToRendererMap getMAP() {
            return (KtDiagnosticFactoryToRendererMap) MAP.getValue();
        }
    }

    static {
        KProperty<?>[] kPropertyArr = {new PropertyReference1Impl<>(CliFrontendDiagnostics.class, "OPT_IN_REQUIREMENT_MARKER_IS_UNRESOLVED", "getOPT_IN_REQUIREMENT_MARKER_IS_UNRESOLVED()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliFrontendDiagnostics.class, "NOT_AN_OPT_IN_REQUIREMENT_MARKER", "getNOT_AN_OPT_IN_REQUIREMENT_MARKER()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliFrontendDiagnostics.class, "OPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED", "getOPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliFrontendDiagnostics.class, "OPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED_ERROR", "getOPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED_ERROR()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliFrontendDiagnostics.class, "CONTEXT_PARAMETERS_ARE_DEPRECATED", "getCONTEXT_PARAMETERS_ARE_DEPRECATED()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliFrontendDiagnostics.class, "MISSING_DIAGNOSTIC_NAME", "getMISSING_DIAGNOSTIC_NAME()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0), new PropertyReference1Impl<>(CliFrontendDiagnostics.class, "ERROR_SEVERITY_CHANGED", "getERROR_SEVERITY_CHANGED()Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", 0)};
        $$delegatedProperties = kPropertyArr;
        CliFrontendDiagnostics cliFrontendDiagnostics = new CliFrontendDiagnostics();
        INSTANCE = cliFrontendDiagnostics;
        OPT_IN_REQUIREMENT_MARKER_IS_UNRESOLVED = KtDiagnosticFactoryDslKt.warningWithoutSource(cliFrontendDiagnostics).provideDelegate(cliFrontendDiagnostics, kPropertyArr[0]);
        NOT_AN_OPT_IN_REQUIREMENT_MARKER = KtDiagnosticFactoryDslKt.warningWithoutSource(cliFrontendDiagnostics).provideDelegate(cliFrontendDiagnostics, kPropertyArr[1]);
        OPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED = KtDiagnosticFactoryDslKt.warningWithoutSource(cliFrontendDiagnostics).provideDelegate(cliFrontendDiagnostics, kPropertyArr[2]);
        OPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED_ERROR = KtDiagnosticFactoryDslKt.errorWithoutSource(cliFrontendDiagnostics).provideDelegate(cliFrontendDiagnostics, kPropertyArr[3]);
        CONTEXT_PARAMETERS_ARE_DEPRECATED = KtDiagnosticFactoryDslKt.errorWithoutSource(cliFrontendDiagnostics).provideDelegate(cliFrontendDiagnostics, kPropertyArr[4]);
        MISSING_DIAGNOSTIC_NAME = KtDiagnosticFactoryDslKt.errorWithoutSource(cliFrontendDiagnostics).provideDelegate(cliFrontendDiagnostics, kPropertyArr[5]);
        ERROR_SEVERITY_CHANGED = KtDiagnosticFactoryDslKt.errorWithoutSource(cliFrontendDiagnostics).provideDelegate(cliFrontendDiagnostics, kPropertyArr[6]);
    }

    private CliFrontendDiagnostics() {
    }

    public final KtSourcelessDiagnosticFactory getCONTEXT_PARAMETERS_ARE_DEPRECATED() {
        return (KtSourcelessDiagnosticFactory) CONTEXT_PARAMETERS_ARE_DEPRECATED.getValue(this, $$delegatedProperties[4]);
    }

    public final KtSourcelessDiagnosticFactory getERROR_SEVERITY_CHANGED() {
        return (KtSourcelessDiagnosticFactory) ERROR_SEVERITY_CHANGED.getValue(this, $$delegatedProperties[6]);
    }

    public final KtSourcelessDiagnosticFactory getMISSING_DIAGNOSTIC_NAME() {
        return (KtSourcelessDiagnosticFactory) MISSING_DIAGNOSTIC_NAME.getValue(this, $$delegatedProperties[5]);
    }

    public final KtSourcelessDiagnosticFactory getNOT_AN_OPT_IN_REQUIREMENT_MARKER() {
        return (KtSourcelessDiagnosticFactory) NOT_AN_OPT_IN_REQUIREMENT_MARKER.getValue(this, $$delegatedProperties[1]);
    }

    public final KtSourcelessDiagnosticFactory getOPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED() {
        return (KtSourcelessDiagnosticFactory) OPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED.getValue(this, $$delegatedProperties[2]);
    }

    public final KtSourcelessDiagnosticFactory getOPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED_ERROR() {
        return (KtSourcelessDiagnosticFactory) OPT_IN_REQUIREMENT_MARKER_IS_DEPRECATED_ERROR.getValue(this, $$delegatedProperties[3]);
    }

    public final KtSourcelessDiagnosticFactory getOPT_IN_REQUIREMENT_MARKER_IS_UNRESOLVED() {
        return (KtSourcelessDiagnosticFactory) OPT_IN_REQUIREMENT_MARKER_IS_UNRESOLVED.getValue(this, $$delegatedProperties[0]);
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer
    public BaseDiagnosticRendererFactory getRendererFactory() {
        return Messages.INSTANCE;
    }
}
