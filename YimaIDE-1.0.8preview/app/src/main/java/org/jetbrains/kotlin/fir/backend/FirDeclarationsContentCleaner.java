package org.jetbrains.kotlin.fir.backend;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirExpressionStubBuilder;
import org.jetbrains.kotlin.fir.serialization.constant.FirToConstantValueTransformerKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 #2\u00020\u0001:\u0003#$%B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H&J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016H&J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019H&J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001cH&J\u0010\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001fH&J\u0010\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"H&\u0082\u0001\u0002&'¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirDeclarationsContentCleaner;", Argument.Delimiters.none, "<init>", "()V", "cleanFile", Argument.Delimiters.none, "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "cleanClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "cleanAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "cleanConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "cleanNamedFunction", "namedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "cleanAnonymousFunction", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "cleanValueParameter", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "cleanProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "cleanEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "cleanAnonymousInitializer", "anonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "Companion", "DoNothing", "CleanBodies", "Lorg/jetbrains/kotlin/fir/backend/FirDeclarationsContentCleaner$CleanBodies;", "Lorg/jetbrains/kotlin/fir/backend/FirDeclarationsContentCleaner$DoNothing;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirDeclarationsContentCleaner {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020)H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirDeclarationsContentCleaner$CleanBodies;", "Lorg/jetbrains/kotlin/fir/backend/FirDeclarationsContentCleaner;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "cleanFile", Argument.Delimiters.none, "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "cleanClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "cleanAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "cleanConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "cleanNamedFunction", "namedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "cleanAnonymousFunction", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "cleanValueParameter", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "cleanProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "cleanPropertyAccessor", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "cleanEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "cleanAnonymousInitializer", "anonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CleanBodies extends FirDeclarationsContentCleaner {
        private final FirSession session;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CleanBodies(FirSession firSession) {
            super(null);
            firSession.getClass();
            this.session = firSession;
        }

        private final void cleanPropertyAccessor(FirPropertyAccessor propertyAccessor) {
            propertyAccessor.replaceControlFlowGraphReference(null);
            propertyAccessor.replaceBody(null);
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanAnonymousFunction(FirAnonymousFunction anonymousFunction) {
            anonymousFunction.getClass();
            anonymousFunction.replaceControlFlowGraphReference(null);
            anonymousFunction.replaceBody(null);
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanAnonymousInitializer(FirAnonymousInitializer anonymousInitializer) {
            anonymousInitializer.getClass();
            anonymousInitializer.replaceControlFlowGraphReference(null);
            anonymousInitializer.replaceBody(null);
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanAnonymousObject(FirAnonymousObject anonymousObject) {
            anonymousObject.getClass();
            anonymousObject.replaceControlFlowGraphReference(null);
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanClass(FirRegularClass regularClass) {
            regularClass.getClass();
            regularClass.replaceControlFlowGraphReference(null);
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanConstructor(FirConstructor constructor) {
            constructor.getClass();
            constructor.replaceControlFlowGraphReference(null);
            constructor.replaceBody(null);
            constructor.replaceDelegatedConstructor(null);
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanEnumEntry(FirEnumEntry enumEntry) {
            enumEntry.getClass();
            enumEntry.replaceInitializer(null);
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanFile(FirFile file) {
            file.getClass();
            file.replaceControlFlowGraphReference(null);
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanNamedFunction(FirNamedFunction namedFunction) {
            namedFunction.getClass();
            namedFunction.replaceControlFlowGraphReference(null);
            namedFunction.replaceBody(null);
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanProperty(FirProperty property) {
            FirExpression firExpressionMo288build;
            property.getClass();
            DeclarationAttributesKt.setHasBackingFieldAttr(property, Boolean.valueOf(DeclarationAttributesKt.getHasBackingField(property)));
            FirExpression initializer = property.getInitializer();
            if (initializer != null) {
                if (FirToConstantValueTransformerKt.hasConstantValue(initializer, this.session)) {
                    FirExpressionStubBuilder firExpressionStubBuilder = new FirExpressionStubBuilder();
                    firExpressionStubBuilder.setSource(initializer.getSource());
                    firExpressionStubBuilder.setConeTypeOrNull(FirTypeUtilsKt.getResolvedType(initializer));
                    firExpressionMo288build = firExpressionStubBuilder.mo288build();
                } else {
                    firExpressionMo288build = null;
                }
                property.replaceInitializer(firExpressionMo288build);
            }
            property.replaceControlFlowGraphReference(null);
            FirPropertyAccessor getter = property.getGetter();
            if (getter != null) {
                cleanPropertyAccessor(getter);
            }
            FirPropertyAccessor setter = property.getSetter();
            if (setter != null) {
                cleanPropertyAccessor(setter);
            }
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanValueParameter(FirValueParameter valueParameter) {
            valueParameter.getClass();
            valueParameter.replaceControlFlowGraphReference(null);
            FirExpression defaultValue = valueParameter.getDefaultValue();
            if (defaultValue != null) {
                FirExpressionStubBuilder firExpressionStubBuilder = new FirExpressionStubBuilder();
                firExpressionStubBuilder.setSource(defaultValue.getSource());
                firExpressionStubBuilder.setConeTypeOrNull(FirTypeUtilsKt.getResolvedType(defaultValue));
                valueParameter.replaceInitializer(firExpressionStubBuilder.mo288build());
            }
        }

        public final FirSession getSession() {
            return this.session;
        }
    }

    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"H\u0016¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirDeclarationsContentCleaner$DoNothing;", "Lorg/jetbrains/kotlin/fir/backend/FirDeclarationsContentCleaner;", "<init>", "()V", "cleanFile", Argument.Delimiters.none, "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "cleanClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "cleanAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "cleanConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "cleanNamedFunction", "namedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "cleanAnonymousFunction", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "cleanValueParameter", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "cleanProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "cleanEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "cleanAnonymousInitializer", "anonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DoNothing extends FirDeclarationsContentCleaner {
        public static final DoNothing INSTANCE = new DoNothing();

        private DoNothing() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanAnonymousFunction(FirAnonymousFunction anonymousFunction) {
            anonymousFunction.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanAnonymousInitializer(FirAnonymousInitializer anonymousInitializer) {
            anonymousInitializer.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanAnonymousObject(FirAnonymousObject anonymousObject) {
            anonymousObject.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanClass(FirRegularClass regularClass) {
            regularClass.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanConstructor(FirConstructor constructor) {
            constructor.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanEnumEntry(FirEnumEntry enumEntry) {
            enumEntry.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanFile(FirFile file) {
            file.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanNamedFunction(FirNamedFunction namedFunction) {
            namedFunction.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanProperty(FirProperty property) {
            property.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner
        public void cleanValueParameter(FirValueParameter valueParameter) {
            valueParameter.getClass();
        }
    }

    public /* synthetic */ FirDeclarationsContentCleaner(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract void cleanAnonymousFunction(FirAnonymousFunction anonymousFunction);

    public abstract void cleanAnonymousInitializer(FirAnonymousInitializer anonymousInitializer);

    public abstract void cleanAnonymousObject(FirAnonymousObject anonymousObject);

    public abstract void cleanClass(FirRegularClass regularClass);

    public abstract void cleanConstructor(FirConstructor constructor);

    public abstract void cleanEnumEntry(FirEnumEntry enumEntry);

    public abstract void cleanFile(FirFile file);

    public abstract void cleanNamedFunction(FirNamedFunction namedFunction);

    public abstract void cleanProperty(FirProperty property);

    public abstract void cleanValueParameter(FirValueParameter valueParameter);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0004\u001a\u00020\u0005R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirDeclarationsContentCleaner$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/backend/FirDeclarationsContentCleaner;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;)Lorg/jetbrains/kotlin/fir/backend/FirDeclarationsContentCleaner;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirDeclarationsContentCleaner create(Fir2IrComponents fir2IrComponents) {
            fir2IrComponents.getClass();
            Fir2IrConfiguration configuration = fir2IrComponents.getConfiguration();
            return (configuration.getAllowNonCachedDeclarations() || configuration.getSkipBodies() || ((Boolean) configuration.getLanguageVersionSettings().getFlag(AnalysisFlags.INSTANCE.getHierarchicalMultiplatformCompilation())).booleanValue()) ? DoNothing.INSTANCE : new CleanBodies(fir2IrComponents.getSession());
        }

        private Companion() {
        }
    }

    private FirDeclarationsContentCleaner() {
    }
}
