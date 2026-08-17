package org.jetbrains.kotlin.fir.session;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticsContainer;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.SessionConfiguration;
import org.jetbrains.kotlin.fir.analysis.CheckersComponent;
import org.jetbrains.kotlin.fir.analysis.CheckersComponentKt;
import org.jetbrains.kotlin.fir.analysis.checkers.LanguageVersionSettingsCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FilteredDeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FilteredExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FilteredTypeCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirComposedDiagnosticRendererFactoryKt;
import org.jetbrains.kotlin.fir.analysis.extensions.FirAdditionalCheckersExtension;
import org.jetbrains.kotlin.fir.analysis.extensions.FirAdditionalCheckersExtensionKt;
import org.jetbrains.kotlin.fir.extensions.BunchOfRegisteredExtensions;
import org.jetbrains.kotlin.fir.extensions.FirExtension;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarKt;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.session.FirSessionConfigurator;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0011J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0012J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0013J\u001e\u0010\u0014\u001a\u00020\f2\u000e\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u0017J\u001f\u0010\u0019\u001a\u00020\f2\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001c0\u001b\"\u00020\u001c¢\u0006\u0002\u0010\u001dJ\u001a\u0010\u001e\u001a\u00020\f2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0 H\u0007b\u0002\b!J\f\u0010\"\u001a\u00020\fH\u0007b\u0002\b!R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirSessionConfigurator;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "registeredExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/BunchOfRegisteredExtensions;", "filterPlatformSpecificCheckers", Argument.Delimiters.none, "registerExtensions", Argument.Delimiters.none, "extensions", "useCheckers", "checkers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/LanguageVersionSettingsCheckers;", "registerComponent", "componentKey", "Lkotlin/reflect/KClass;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "componentValue", "registerDiagnosticContainers", "diagnosticContainers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "([Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;)V", "withOnlyPlatformSpecificCheckersEnabledInMetadataCompilation", "block", "Lkotlin/Function0;", "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "configure", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSessionConfigurator {
    private boolean filterPlatformSpecificCheckers;
    private final List<BunchOfRegisteredExtensions> registeredExtensions;
    private final FirSession session;

    public FirSessionConfigurator(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.registeredExtensions = CollectionsKt.mutableListOf(new BunchOfRegisteredExtensions[]{BunchOfRegisteredExtensions.INSTANCE.empty()});
    }

    public static boolean a(FirExpressionChecker firExpressionChecker) {
        firExpressionChecker.getClass();
        return firExpressionChecker.getPlatformSpecificCheckerEnabledInMetadataCompilation();
    }

    public static boolean b(FirDeclarationChecker firDeclarationChecker) {
        firDeclarationChecker.getClass();
        return firDeclarationChecker.getPlatformSpecificCheckerEnabledInMetadataCompilation();
    }

    public static boolean c(FirTypeChecker firTypeChecker) {
        firTypeChecker.getClass();
        return firTypeChecker.getPlatformSpecificCheckerEnabledInMetadataCompilation();
    }

    @SessionConfiguration
    public final void configure() {
        CheckersComponent nullableCheckersComponent;
        Iterator<T> it = this.registeredExtensions.iterator();
        if (!it.hasNext()) {
            c41.a("Empty collection can't be reduced.");
            return;
        }
        Object next = it.next();
        while (it.hasNext()) {
            next = ((BunchOfRegisteredExtensions) next).plus((BunchOfRegisteredExtensions) it.next());
        }
        BunchOfRegisteredExtensions bunchOfRegisteredExtensions = (BunchOfRegisteredExtensions) next;
        if (this.session.getKind() == FirSession.Kind.Library) {
            Map<KClass<? extends FirExtension>, List<FirExtension.Factory<FirExtension>>> extensions = bunchOfRegisteredExtensions.getExtensions();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<KClass<? extends FirExtension>, List<FirExtension.Factory<FirExtension>>> entry : extensions.entrySet()) {
                if (FirExtensionRegistrar.INSTANCE.getALLOWED_EXTENSIONS_FOR_LIBRARY_SESSION$org_jetbrains_kotlin_entrypoint().contains(entry.getKey())) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
            bunchOfRegisteredExtensions = new BunchOfRegisteredExtensions(linkedHashMap, CollectionsKt.emptyList());
        }
        FirExtensionRegistrarKt.registerExtensions(FirExtensionServiceKt.getExtensionService(this.session), bunchOfRegisteredExtensions);
        if (this.session.getKind() != FirSession.Kind.Source || (nullableCheckersComponent = CheckersComponentKt.getNullableCheckersComponent(this.session)) == null) {
            return;
        }
        Iterator<T> it2 = FirAdditionalCheckersExtensionKt.getAdditionalCheckers(FirExtensionServiceKt.getExtensionService(this.session)).iterator();
        while (it2.hasNext()) {
            nullableCheckersComponent.register((FirAdditionalCheckersExtension) it2.next());
        }
    }

    public final void registerComponent(KClass<? extends FirSessionComponent> componentKey, FirSessionComponent componentValue) {
        componentKey.getClass();
        componentValue.getClass();
        this.session.register(componentKey, componentValue);
    }

    public final void registerDiagnosticContainers(KtDiagnosticsContainer... diagnosticContainers) {
        diagnosticContainers.getClass();
        FirComposedDiagnosticRendererFactoryKt.getRegisteredDiagnosticFactoriesStorage(this.session).registerDiagnosticContainers((KtDiagnosticsContainer[]) Arrays.copyOf(diagnosticContainers, diagnosticContainers.length));
    }

    public final void registerExtensions(BunchOfRegisteredExtensions extensions) {
        extensions.getClass();
        this.registeredExtensions.add(extensions);
    }

    public final void useCheckers(ExpressionCheckers checkers) {
        checkers.getClass();
        if (this.filterPlatformSpecificCheckers) {
            checkers = new FilteredExpressionCheckers(checkers, new Function1() { // from class: dd5
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(FirSessionConfigurator.a((FirExpressionChecker) obj));
                }
            });
        }
        CheckersComponentKt.getCheckersComponent(this.session).register(checkers);
    }

    @SessionConfiguration
    public final void withOnlyPlatformSpecificCheckersEnabledInMetadataCompilation(Function0<Unit> block) {
        block.getClass();
        boolean z = this.filterPlatformSpecificCheckers;
        this.filterPlatformSpecificCheckers = true;
        try {
            block.invoke();
        } finally {
            this.filterPlatformSpecificCheckers = z;
        }
    }

    public final void useCheckers(DeclarationCheckers checkers) {
        checkers.getClass();
        if (this.filterPlatformSpecificCheckers) {
            checkers = new FilteredDeclarationCheckers(checkers, new Function1() { // from class: ed5
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(FirSessionConfigurator.b((FirDeclarationChecker) obj));
                }
            });
        }
        CheckersComponentKt.getCheckersComponent(this.session).register(checkers);
    }

    public final void useCheckers(TypeCheckers checkers) {
        checkers.getClass();
        if (this.filterPlatformSpecificCheckers) {
            checkers = new FilteredTypeCheckers(checkers, new Function1() { // from class: fd5
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(FirSessionConfigurator.c((FirTypeChecker) obj));
                }
            });
        }
        CheckersComponentKt.getCheckersComponent(this.session).register(checkers);
    }

    public final void useCheckers(LanguageVersionSettingsCheckers checkers) {
        checkers.getClass();
        CheckersComponentKt.getCheckersComponent(this.session).register(checkers);
    }
}
