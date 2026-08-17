package androidx.compose.compiler.plugins.kotlin;

import kotlin.Metadata;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b5\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\bJ\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0014\u0010\u0014\u001a\u00020\u0007X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\u001d\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\rR\u0011\u0010\u001f\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0011\u0010!\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\rR\u0011\u0010#\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u0011\u0010%\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\rR\u0011\u0010'\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\rR\u0011\u0010)\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\rR\u0011\u0010+\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\rR\u0011\u0010-\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\rR\u0011\u0010/\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\rR\u0011\u00101\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\rR\u0011\u00103\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\rR\u0011\u00105\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\rR\u0011\u00107\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\rR\u0011\u00109\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\rR\u0011\u0010;\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\rR\u0011\u0010=\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\rR\u0011\u0010?\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\rR\u0011\u0010A\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\rR\u0011\u0010C\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\rR\u0011\u0010E\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\rR\u0011\u0010G\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\rR\u0011\u0010I\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\rR\u0011\u0010K\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bL\u0010\r¨\u0006M"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ComposeFqNames;", "", "<init>", "()V", "fqNameFor", "Lorg/jetbrains/kotlin/name/FqName;", "cname", "", "fqNameFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "internalFqNameFor", "composablesFqNameFor", "InternalPackage", "getInternalPackage", "()Lorg/jetbrains/kotlin/name/FqName;", "Composable", "getComposable", "ComposableTarget", "getComposableTarget", "ComposableTargetMarker", "getComposableTargetMarker", "ComposableTargetMarkerDescription", "getComposableTargetMarkerDescription", "()Ljava/lang/String;", "ComposableTargetMarkerDescriptionName", "Lorg/jetbrains/kotlin/name/Name;", "getComposableTargetMarkerDescriptionName", "()Lorg/jetbrains/kotlin/name/Name;", "ComposableTargetApplierArgument", "getComposableTargetApplierArgument", "ComposableOpenTarget", "getComposableOpenTarget", "ComposableOpenTargetIndexArgument", "getComposableOpenTargetIndexArgument", "ComposableInferredTarget", "getComposableInferredTarget", "ComposableInferredTargetSchemeArgument", "getComposableInferredTargetSchemeArgument", "CurrentComposerIntrinsic", "getCurrentComposerIntrinsic", "getCurrentComposerFullName", "getGetCurrentComposerFullName", "DisallowComposableCalls", "getDisallowComposableCalls", "ReadOnlyComposable", "getReadOnlyComposable", "ExplicitGroupsComposable", "getExplicitGroupsComposable", "NonRestartableComposable", "getNonRestartableComposable", "NonSkippableComposable", "getNonSkippableComposable", "DontMemoize", "getDontMemoize", "composableLambdaType", "getComposableLambdaType", "composableLambda", "getComposableLambda", "rememberComposableLambda", "getRememberComposableLambda", "composableLambdaFullName", "getComposableLambdaFullName", "remember", "getRemember", "cache", "getCache", "key", "getKey", "StableMarker", "getStableMarker", "Stable", "getStable", "Immutable", "getImmutable", "Composer", "getComposer", "StabilityInferred", "getStabilityInferred", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeFqNames {
    private static final FqName Composable;
    private static final FqName ComposableInferredTarget;
    private static final Name ComposableInferredTargetSchemeArgument;
    private static final FqName ComposableOpenTarget;
    private static final Name ComposableOpenTargetIndexArgument;
    private static final FqName ComposableTarget;
    private static final Name ComposableTargetApplierArgument;
    private static final FqName ComposableTargetMarker;
    private static final String ComposableTargetMarkerDescription;
    private static final Name ComposableTargetMarkerDescriptionName;
    private static final FqName Composer;
    private static final FqName CurrentComposerIntrinsic;
    private static final FqName DisallowComposableCalls;
    private static final FqName DontMemoize;
    private static final FqName ExplicitGroupsComposable;
    public static final ComposeFqNames INSTANCE;
    private static final FqName Immutable;
    private static final FqName InternalPackage;
    private static final FqName NonRestartableComposable;
    private static final FqName NonSkippableComposable;
    private static final FqName ReadOnlyComposable;
    private static final FqName StabilityInferred;
    private static final FqName Stable;
    private static final FqName StableMarker;
    private static final FqName cache;
    private static final FqName composableLambda;
    private static final FqName composableLambdaFullName;
    private static final FqName composableLambdaType;
    private static final FqName getCurrentComposerFullName;
    private static final FqName key;
    private static final FqName remember;
    private static final FqName rememberComposableLambda;

    static {
        ComposeFqNames composeFqNames = new ComposeFqNames();
        INSTANCE = composeFqNames;
        InternalPackage = ComposeFqNamesKt.access$getInternalRootFqName$p();
        ComposeClassIds composeClassIds = ComposeClassIds.INSTANCE;
        Composable = composeClassIds.getComposable().asSingleFqName();
        ComposableTarget = composeClassIds.getComposableTarget().asSingleFqName();
        ComposableTargetMarker = composeFqNames.fqNameFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("ComposableTargetMarker");
        ComposableTargetMarkerDescription = "description";
        Name nameIdentifier = Name.identifier("description");
        nameIdentifier.getClass();
        ComposableTargetMarkerDescriptionName = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("applier");
        nameIdentifier2.getClass();
        ComposableTargetApplierArgument = nameIdentifier2;
        ComposableOpenTarget = composeClassIds.getComposableOpenTarget().asSingleFqName();
        Name nameIdentifier3 = Name.identifier("index");
        nameIdentifier3.getClass();
        ComposableOpenTargetIndexArgument = nameIdentifier3;
        ComposableInferredTarget = composeClassIds.getComposableInferredTarget().asSingleFqName();
        Name nameIdentifier4 = Name.identifier("scheme");
        nameIdentifier4.getClass();
        ComposableInferredTargetSchemeArgument = nameIdentifier4;
        CurrentComposerIntrinsic = composeFqNames.fqNameFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("<get-currentComposer>");
        getCurrentComposerFullName = composeFqNames.composablesFqNameFor("<get-currentComposer>");
        DisallowComposableCalls = composeClassIds.getDisallowComposableCalls().asSingleFqName();
        ReadOnlyComposable = composeClassIds.getReadOnlyComposable().asSingleFqName();
        ExplicitGroupsComposable = composeFqNames.fqNameFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("ExplicitGroupsComposable");
        NonRestartableComposable = composeFqNames.fqNameFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("NonRestartableComposable");
        NonSkippableComposable = composeFqNames.fqNameFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("NonSkippableComposable");
        DontMemoize = composeFqNames.fqNameFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("DontMemoize");
        composableLambdaType = composeClassIds.getComposableLambda().asSingleFqName();
        ComposeCallableIds composeCallableIds = ComposeCallableIds.INSTANCE;
        composableLambda = composeCallableIds.getComposableLambda().asSingleFqName();
        rememberComposableLambda = composeCallableIds.getRememberComposableLambda().asSingleFqName();
        composableLambdaFullName = composeFqNames.internalFqNameFor("ComposableLambdaKt.composableLambda");
        remember = composeCallableIds.getRemember().asSingleFqName();
        cache = composeCallableIds.getCache().asSingleFqName();
        key = composeCallableIds.getKey().asSingleFqName();
        StableMarker = composeFqNames.fqNameFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("StableMarker");
        Stable = composeFqNames.fqNameFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("Stable");
        Immutable = composeFqNames.fqNameFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("Immutable");
        Composer = composeClassIds.getComposer().asSingleFqName();
        StabilityInferred = composeClassIds.getStabilityInferred().asSingleFqName();
    }

    private ComposeFqNames() {
    }

    private final FqName composablesFqNameFor(String cname) {
        return fqNameFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("ComposablesKt." + cname);
    }

    private final FqName internalFqNameFor(String cname) {
        return new FqName("androidx.compose.runtime.internal." + cname);
    }

    public final FqName fqNameFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(String cname) {
        cname.getClass();
        return new FqName("androidx.compose.runtime." + cname);
    }

    public final FqName getCache() {
        return cache;
    }

    public final FqName getComposable() {
        return Composable;
    }

    public final FqName getComposableInferredTarget() {
        return ComposableInferredTarget;
    }

    public final Name getComposableInferredTargetSchemeArgument() {
        return ComposableInferredTargetSchemeArgument;
    }

    public final FqName getComposableLambda() {
        return composableLambda;
    }

    public final FqName getComposableLambdaFullName() {
        return composableLambdaFullName;
    }

    public final FqName getComposableLambdaType() {
        return composableLambdaType;
    }

    public final FqName getComposableOpenTarget() {
        return ComposableOpenTarget;
    }

    public final Name getComposableOpenTargetIndexArgument() {
        return ComposableOpenTargetIndexArgument;
    }

    public final FqName getComposableTarget() {
        return ComposableTarget;
    }

    public final Name getComposableTargetApplierArgument() {
        return ComposableTargetApplierArgument;
    }

    public final FqName getComposableTargetMarker() {
        return ComposableTargetMarker;
    }

    public final String getComposableTargetMarkerDescription() {
        return ComposableTargetMarkerDescription;
    }

    public final Name getComposableTargetMarkerDescriptionName() {
        return ComposableTargetMarkerDescriptionName;
    }

    public final FqName getComposer() {
        return Composer;
    }

    public final FqName getCurrentComposerIntrinsic() {
        return CurrentComposerIntrinsic;
    }

    public final FqName getDisallowComposableCalls() {
        return DisallowComposableCalls;
    }

    public final FqName getDontMemoize() {
        return DontMemoize;
    }

    public final FqName getExplicitGroupsComposable() {
        return ExplicitGroupsComposable;
    }

    public final FqName getGetCurrentComposerFullName() {
        return getCurrentComposerFullName;
    }

    public final FqName getImmutable() {
        return Immutable;
    }

    public final FqName getInternalPackage() {
        return InternalPackage;
    }

    public final FqName getKey() {
        return key;
    }

    public final FqName getNonRestartableComposable() {
        return NonRestartableComposable;
    }

    public final FqName getNonSkippableComposable() {
        return NonSkippableComposable;
    }

    public final FqName getReadOnlyComposable() {
        return ReadOnlyComposable;
    }

    public final FqName getRemember() {
        return remember;
    }

    public final FqName getRememberComposableLambda() {
        return rememberComposableLambda;
    }

    public final FqName getStabilityInferred() {
        return StabilityInferred;
    }

    public final FqName getStable() {
        return Stable;
    }

    public final FqName getStableMarker() {
        return StableMarker;
    }
}
