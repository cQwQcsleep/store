package androidx.compose.compiler.plugins.kotlin;

import kotlin.Metadata;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b*\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0015\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\tJ\u0015\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\u0015\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000eR\u0011\u0010\u0017\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000eR\u0011\u0010\u0019\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000eR\u0011\u0010\u001b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u000eR\u0011\u0010\u001d\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u000eR\u0011\u0010\u001f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u000eR\u0011\u0010!\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u000eR\u0011\u0010#\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u000eR\u0011\u0010%\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u000eR\u0011\u0010'\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u000eR\u0011\u0010)\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u000eR\u0011\u0010+\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u000eR\u0011\u0010-\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u000eR\u0011\u0010/\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u000e¨\u00061"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ComposeClassIds;", "", "<init>", "()V", "classIdFor", "Lorg/jetbrains/kotlin/name/ClassId;", "cname", "", "internalClassIdFor", "internalClassIdFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "toolingClassIdFor", "toolingClassIdFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "Composable", "getComposable", "()Lorg/jetbrains/kotlin/name/ClassId;", "ComposableInferredTarget", "getComposableInferredTarget", "ComposableLambda", "getComposableLambda", "ComposableOpenTarget", "getComposableOpenTarget", "ComposableTarget", "getComposableTarget", "ComposableTargetMarker", "getComposableTargetMarker", "ComposeVersion", "getComposeVersion", "Composer", "getComposer", "DisallowComposableCalls", "getDisallowComposableCalls", "FunctionKeyMetaClass", "getFunctionKeyMetaClass", "FunctionKeyMeta", "getFunctionKeyMeta", "LiveLiteralFileInfo", "getLiveLiteralFileInfo", "LiveLiteralInfo", "getLiveLiteralInfo", "NoLiveLiterals", "getNoLiveLiterals", "ReadOnlyComposable", "getReadOnlyComposable", "State", "getState", "StabilityInferred", "getStabilityInferred", "SourceInformation", "getSourceInformation", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeClassIds {
    private static final ClassId Composable;
    private static final ClassId ComposableInferredTarget;
    private static final ClassId ComposableLambda;
    private static final ClassId ComposableOpenTarget;
    private static final ClassId ComposableTarget;
    private static final ClassId ComposableTargetMarker;
    private static final ClassId ComposeVersion;
    private static final ClassId Composer;
    private static final ClassId DisallowComposableCalls;
    private static final ClassId FunctionKeyMeta;
    private static final ClassId FunctionKeyMetaClass;
    public static final ComposeClassIds INSTANCE;
    private static final ClassId LiveLiteralFileInfo;
    private static final ClassId LiveLiteralInfo;
    private static final ClassId NoLiveLiterals;
    private static final ClassId ReadOnlyComposable;
    private static final ClassId SourceInformation;
    private static final ClassId StabilityInferred;
    private static final ClassId State;

    static {
        ComposeClassIds composeClassIds = new ComposeClassIds();
        INSTANCE = composeClassIds;
        Composable = composeClassIds.classIdFor("Composable");
        ComposableInferredTarget = composeClassIds.classIdFor("ComposableInferredTarget");
        ComposableLambda = composeClassIds.internalClassIdFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("ComposableLambda");
        ComposableOpenTarget = composeClassIds.classIdFor("ComposableOpenTarget");
        ComposableTarget = composeClassIds.classIdFor("ComposableTarget");
        ComposableTargetMarker = composeClassIds.classIdFor("ComposableTargetMarker");
        ComposeVersion = composeClassIds.classIdFor("ComposeVersion");
        Composer = composeClassIds.classIdFor("Composer");
        DisallowComposableCalls = composeClassIds.classIdFor("DisallowComposableCalls");
        FunctionKeyMetaClass = composeClassIds.internalClassIdFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("FunctionKeyMetaClass");
        FunctionKeyMeta = composeClassIds.internalClassIdFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("FunctionKeyMeta");
        LiveLiteralFileInfo = composeClassIds.internalClassIdFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("LiveLiteralFileInfo");
        LiveLiteralInfo = composeClassIds.internalClassIdFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("LiveLiteralInfo");
        NoLiveLiterals = composeClassIds.classIdFor("NoLiveLiterals");
        ReadOnlyComposable = composeClassIds.classIdFor("ReadOnlyComposable");
        State = composeClassIds.classIdFor("State");
        StabilityInferred = composeClassIds.internalClassIdFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("StabilityInferred");
        SourceInformation = composeClassIds.toolingClassIdFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("SourceInformation");
    }

    private ComposeClassIds() {
    }

    private final ClassId classIdFor(String cname) {
        FqName fqNameAccess$getRootFqName$p = ComposeFqNamesKt.access$getRootFqName$p();
        Name nameIdentifier = Name.identifier(cname);
        nameIdentifier.getClass();
        return new ClassId(fqNameAccess$getRootFqName$p, nameIdentifier);
    }

    public final ClassId getComposable() {
        return Composable;
    }

    public final ClassId getComposableInferredTarget() {
        return ComposableInferredTarget;
    }

    public final ClassId getComposableLambda() {
        return ComposableLambda;
    }

    public final ClassId getComposableOpenTarget() {
        return ComposableOpenTarget;
    }

    public final ClassId getComposableTarget() {
        return ComposableTarget;
    }

    public final ClassId getComposableTargetMarker() {
        return ComposableTargetMarker;
    }

    public final ClassId getComposeVersion() {
        return ComposeVersion;
    }

    public final ClassId getComposer() {
        return Composer;
    }

    public final ClassId getDisallowComposableCalls() {
        return DisallowComposableCalls;
    }

    public final ClassId getFunctionKeyMeta() {
        return FunctionKeyMeta;
    }

    public final ClassId getFunctionKeyMetaClass() {
        return FunctionKeyMetaClass;
    }

    public final ClassId getLiveLiteralFileInfo() {
        return LiveLiteralFileInfo;
    }

    public final ClassId getLiveLiteralInfo() {
        return LiveLiteralInfo;
    }

    public final ClassId getNoLiveLiterals() {
        return NoLiveLiterals;
    }

    public final ClassId getReadOnlyComposable() {
        return ReadOnlyComposable;
    }

    public final ClassId getSourceInformation() {
        return SourceInformation;
    }

    public final ClassId getStabilityInferred() {
        return StabilityInferred;
    }

    public final ClassId getState() {
        return State;
    }

    public final ClassId internalClassIdFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(String cname) {
        cname.getClass();
        FqName fqNameAccess$getInternalRootFqName$p = ComposeFqNamesKt.access$getInternalRootFqName$p();
        Name nameIdentifier = Name.identifier(cname);
        nameIdentifier.getClass();
        return new ClassId(fqNameAccess$getInternalRootFqName$p, nameIdentifier);
    }

    public final ClassId toolingClassIdFor$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(String cname) {
        cname.getClass();
        FqName fqNameAccess$getToolingFqName$p = ComposeFqNamesKt.access$getToolingFqName$p();
        Name nameIdentifier = Name.identifier(cname);
        nameIdentifier.getClass();
        return new ClassId(fqNameAccess$getToolingFqName$p, nameIdentifier);
    }
}
