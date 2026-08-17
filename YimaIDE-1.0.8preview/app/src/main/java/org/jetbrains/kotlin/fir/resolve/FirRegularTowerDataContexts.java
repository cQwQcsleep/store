package org.jetbrains.kotlin.fir.resolve;

import java.util.EnumMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataContext;
import org.jetbrains.kotlin.fir.scopes.impl.FirLocalScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\u0018\u0000  2\u00020\u0001:\u0001 B9\b\u0002\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\u000bBe\b\u0016\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\n\u0010\u0012J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0005J\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0004J\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0005R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirRegularTowerDataContexts;", Argument.Delimiters.none, "modeMap", "Ljava/util/EnumMap;", "Lorg/jetbrains/kotlin/fir/resolve/FirTowerDataMode;", "Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;", "primaryConstructorPureParametersScope", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirLocalScope;", "primaryConstructorAllParametersScope", "activeMode", "<init>", "(Ljava/util/EnumMap;Lorg/jetbrains/kotlin/fir/scopes/impl/FirLocalScope;Lorg/jetbrains/kotlin/fir/scopes/impl/FirLocalScope;Lorg/jetbrains/kotlin/fir/resolve/FirTowerDataMode;)V", "regular", "forNestedClasses", "forCompanionObject", "forCompanionBlock", "forConstructorHeaders", "forEnumEntries", "(Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;Lorg/jetbrains/kotlin/fir/scopes/impl/FirLocalScope;Lorg/jetbrains/kotlin/fir/scopes/impl/FirLocalScope;)V", "getPrimaryConstructorPureParametersScope", "()Lorg/jetbrains/kotlin/fir/scopes/impl/FirLocalScope;", "getPrimaryConstructorAllParametersScope", "getActiveMode", "()Lorg/jetbrains/kotlin/fir/resolve/FirTowerDataMode;", "currentContext", "getCurrentContext", "()Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;", "replaceCurrentlyActiveContext", "newContext", "replaceTowerDataMode", "newMode", "replaceAndSetActiveRegularContext", "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirRegularTowerDataContexts {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FirTowerDataMode activeMode;
    private final EnumMap<FirTowerDataMode, FirTowerDataContext> modeMap;
    private final FirLocalScope primaryConstructorAllParametersScope;
    private final FirLocalScope primaryConstructorPureParametersScope;

    public /* synthetic */ FirRegularTowerDataContexts(FirTowerDataContext firTowerDataContext, FirTowerDataContext firTowerDataContext2, FirTowerDataContext firTowerDataContext3, FirTowerDataContext firTowerDataContext4, FirTowerDataContext firTowerDataContext5, FirTowerDataContext firTowerDataContext6, FirLocalScope firLocalScope, FirLocalScope firLocalScope2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firTowerDataContext, (i & 2) != 0 ? null : firTowerDataContext2, (i & 4) != 0 ? null : firTowerDataContext3, (i & 8) != 0 ? null : firTowerDataContext4, (i & 16) != 0 ? null : firTowerDataContext5, (i & 32) != 0 ? null : firTowerDataContext6, (i & 64) != 0 ? null : firLocalScope, (i & 128) != 0 ? null : firLocalScope2);
    }

    public final FirTowerDataMode getActiveMode() {
        return this.activeMode;
    }

    public final FirTowerDataContext getCurrentContext() {
        return this.modeMap.get(this.activeMode);
    }

    public final FirLocalScope getPrimaryConstructorAllParametersScope() {
        return this.primaryConstructorAllParametersScope;
    }

    public final FirLocalScope getPrimaryConstructorPureParametersScope() {
        return this.primaryConstructorPureParametersScope;
    }

    public final FirRegularTowerDataContexts replaceAndSetActiveRegularContext(FirTowerDataContext newContext) {
        newContext.getClass();
        EnumMap enumMap = new EnumMap(FirTowerDataMode.class);
        enumMap.putAll(this.modeMap);
        FirTowerDataMode firTowerDataMode = FirTowerDataMode.REGULAR;
        enumMap.put(firTowerDataMode, newContext);
        return new FirRegularTowerDataContexts(enumMap, this.primaryConstructorPureParametersScope, this.primaryConstructorAllParametersScope, firTowerDataMode);
    }

    public final FirRegularTowerDataContexts replaceCurrentlyActiveContext(FirTowerDataContext newContext) {
        newContext.getClass();
        EnumMap enumMap = new EnumMap(FirTowerDataMode.class);
        enumMap.putAll(this.modeMap);
        enumMap.put(this.activeMode, newContext);
        return new FirRegularTowerDataContexts(enumMap, this.primaryConstructorPureParametersScope, this.primaryConstructorAllParametersScope, this.activeMode);
    }

    public final FirRegularTowerDataContexts replaceTowerDataMode(FirTowerDataMode newMode) {
        newMode.getClass();
        return newMode == this.activeMode ? this : new FirRegularTowerDataContexts(this.modeMap, this.primaryConstructorPureParametersScope, this.primaryConstructorAllParametersScope, newMode);
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JN\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u0007H\u0002¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirRegularTowerDataContexts$Companion;", Argument.Delimiters.none, "<init>", "()V", "enumMap", "Ljava/util/EnumMap;", "Lorg/jetbrains/kotlin/fir/resolve/FirTowerDataMode;", "Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;", "regular", "forNestedClasses", "forCompanionObject", "forCompanionBlock", "forConstructorHeaders", "forEnumEntries", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final EnumMap<FirTowerDataMode, FirTowerDataContext> enumMap(FirTowerDataContext regular, FirTowerDataContext forNestedClasses, FirTowerDataContext forCompanionObject, FirTowerDataContext forCompanionBlock, FirTowerDataContext forConstructorHeaders, FirTowerDataContext forEnumEntries) {
            EnumMap<FirTowerDataMode, FirTowerDataContext> enumMap = new EnumMap<>(FirTowerDataMode.class);
            enumMap.put(FirTowerDataMode.REGULAR, regular);
            enumMap.put(FirTowerDataMode.NESTED_CLASS, forNestedClasses);
            enumMap.put(FirTowerDataMode.COMPANION_OBJECT, forCompanionObject);
            enumMap.put(FirTowerDataMode.COMPANION_BLOCK, forCompanionBlock);
            enumMap.put(FirTowerDataMode.CONSTRUCTOR_HEADER, forConstructorHeaders);
            enumMap.put(FirTowerDataMode.ENUM_ENTRY, forEnumEntries);
            return enumMap;
        }

        private Companion() {
        }
    }

    private FirRegularTowerDataContexts(EnumMap<FirTowerDataMode, FirTowerDataContext> enumMap, FirLocalScope firLocalScope, FirLocalScope firLocalScope2, FirTowerDataMode firTowerDataMode) {
        this.modeMap = enumMap;
        this.primaryConstructorPureParametersScope = firLocalScope;
        this.primaryConstructorAllParametersScope = firLocalScope2;
        this.activeMode = firTowerDataMode;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FirRegularTowerDataContexts(FirTowerDataContext firTowerDataContext, FirTowerDataContext firTowerDataContext2, FirTowerDataContext firTowerDataContext3, FirTowerDataContext firTowerDataContext4, FirTowerDataContext firTowerDataContext5, FirTowerDataContext firTowerDataContext6, FirLocalScope firLocalScope, FirLocalScope firLocalScope2) {
        this(INSTANCE.enumMap(firTowerDataContext, firTowerDataContext2, firTowerDataContext3, firTowerDataContext4, firTowerDataContext5, firTowerDataContext6), firLocalScope, firLocalScope2, FirTowerDataMode.REGULAR);
        firTowerDataContext.getClass();
    }
}
