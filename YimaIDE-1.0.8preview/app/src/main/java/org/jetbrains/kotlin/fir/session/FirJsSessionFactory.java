package org.jetbrains.kotlin.fir.session;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirIdentityLessPlatformDeterminer;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformDiagnosticSuppressor;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsIdentityLessPlatformDeterminer;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsModuleKind;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsPlatformDiagnosticSuppressor;
import org.jetbrains.kotlin.fir.checkers.CheckersContainersKt;
import org.jetbrains.kotlin.fir.declarations.FirTypeSpecificityComparatorProvider;
import org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer;
import org.jetbrains.kotlin.fir.scopes.FirDefaultImportsProviderHolder;
import org.jetbrains.kotlin.fir.scopes.impl.FirEnumEntriesSupport;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;
import org.jetbrains.kotlin.js.config.ModuleKind;
import org.jetbrains.kotlin.js.resolve.JsDefaultImportsProvider;
import org.jetbrains.kotlin.js.resolve.JsTypeSpecificityComparatorWithoutDelegate;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0014J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0014J\u0014\u0010\f\u001a\u00020\r*\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0014J\f\u0010\u0010\u001a\u00020\r*\u00020\u0011H\u0016J\f\u0010\u0012\u001a\u00020\r*\u00020\u0011H\u0016J\u0014\u0010\u0013\u001a\u00020\r*\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0014\u0010\u0014\u001a\u00020\r*\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirJsSessionFactory;", "Lorg/jetbrains/kotlin/fir/session/AbstractFirKlibSessionFactory;", "Lorg/jetbrains/kotlin/fir/session/FirJsSessionFactory$Context;", "<init>", "()V", "createLibraryContext", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "createFlexibleTypeFactory", "Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "registerLibrarySessionComponents", Argument.Delimiters.none, "c", "createSourceContext", "registerPlatformCheckers", "Lorg/jetbrains/kotlin/fir/session/FirSessionConfigurator;", "registerExtraPlatformCheckers", "registerSourceSessionComponents", "registerJsComponents", "moduleKind", "Lorg/jetbrains/kotlin/js/config/ModuleKind;", "Context", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsSessionFactory extends AbstractFirKlibSessionFactory<Context> {
    public static final FirJsSessionFactory INSTANCE = new FirJsSessionFactory();

    private FirJsSessionFactory() {
    }

    @Override // org.jetbrains.kotlin.fir.session.AbstractFirKlibSessionFactory
    public FirTypeDeserializer.FlexibleTypeFactory createFlexibleTypeFactory(FirSession session) {
        session.getClass();
        return new JsFlexibleTypeFactory(session);
    }

    @Override // org.jetbrains.kotlin.fir.session.AbstractFirKlibSessionFactory
    public Context createLibraryContext(CompilerConfiguration configuration) {
        configuration.getClass();
        return new Context(configuration);
    }

    @Override // org.jetbrains.kotlin.fir.session.AbstractFirKlibSessionFactory
    public Context createSourceContext(CompilerConfiguration configuration) {
        configuration.getClass();
        return new Context(configuration);
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerExtraPlatformCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
    }

    public final void registerJsComponents(FirSession firSession, ModuleKind moduleKind) {
        firSession.getClass();
        firSession.register((KClass<? extends FirEnumEntriesSupport>) Reflection.getOrCreateKotlinClass(FirEnumEntriesSupport.class), new FirEnumEntriesSupport(firSession));
        firSession.register((KClass<? extends FirTypeSpecificityComparatorProvider>) Reflection.getOrCreateKotlinClass(FirTypeSpecificityComparatorProvider.class), FirTypeSpecificityComparatorProvider.INSTANCE.of(new JsTypeSpecificityComparatorWithoutDelegate(TypeComponentsKt.getTypeContext(firSession))));
        firSession.register(Reflection.getOrCreateKotlinClass(FirPlatformDiagnosticSuppressor.class), new FirJsPlatformDiagnosticSuppressor());
        firSession.register(Reflection.getOrCreateKotlinClass(FirIdentityLessPlatformDeterminer.class), FirJsIdentityLessPlatformDeterminer.INSTANCE);
        if (moduleKind != null) {
            firSession.register(Reflection.getOrCreateKotlinClass(FirJsModuleKind.class), new FirJsModuleKind(moduleKind));
        }
        firSession.register((KClass<? extends FirDefaultImportsProviderHolder>) Reflection.getOrCreateKotlinClass(FirDefaultImportsProviderHolder.class), FirDefaultImportsProviderHolder.INSTANCE.of(JsDefaultImportsProvider.INSTANCE));
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerLibrarySessionComponents(FirSession firSession, Context context) {
        firSession.getClass();
        context.getClass();
        registerJsComponents(firSession, context.getModuleKind());
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerPlatformCheckers(FirSessionConfigurator firSessionConfigurator) {
        firSessionConfigurator.getClass();
        CheckersContainersKt.registerJsCheckers(firSessionConfigurator);
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public void registerSourceSessionComponents(FirSession firSession, Context context) {
        firSession.getClass();
        context.getClass();
        registerJsComponents(firSession, context.getModuleKind());
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirJsSessionFactory$Context;", Argument.Delimiters.none, "moduleKind", "Lorg/jetbrains/kotlin/js/config/ModuleKind;", "<init>", "(Lorg/jetbrains/kotlin/js/config/ModuleKind;)V", "compilerConfiguration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "getModuleKind", "()Lorg/jetbrains/kotlin/js/config/ModuleKind;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Context {
        private final ModuleKind moduleKind;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Context(CompilerConfiguration compilerConfiguration) {
            this(JSConfigurationKeysKt.getModuleKind(compilerConfiguration));
            compilerConfiguration.getClass();
        }

        public final ModuleKind getModuleKind() {
            return this.moduleKind;
        }

        public Context(ModuleKind moduleKind) {
            this.moduleKind = moduleKind;
        }
    }
}
