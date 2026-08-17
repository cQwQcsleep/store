package androidx.compose.compiler.plugins.kotlin;

import kotlin.Metadata;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b(\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0015\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\tR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\fR\u0011\u0010\u0015\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\fR\u0011\u0010\u0017\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\fR\u0011\u0010\u0019\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\fR\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\fR\u0011\u0010\u001b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\fR\u0011\u0010\u001d\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\fR\u0011\u0010\u001f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\fR\u0011\u0010!\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\fR\u0011\u0010#\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\fR\u0011\u0010%\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\fR\u0011\u0010'\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\fR\u0011\u0010)\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\fR\u0011\u0010+\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\fR\u0011\u0010-\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\f¨\u0006/"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ComposeCallableIds;", "", "<init>", "()V", "topLevelCallableId", "Lorg/jetbrains/kotlin/name/CallableId;", "name", "", "internalTopLevelCallableId", "internalTopLevelCallableId$org_jetbrains_kotlin_kotlin_compose_compiler_plugin", "cache", "getCache", "()Lorg/jetbrains/kotlin/name/CallableId;", "key", "getKey", "composableLambda", "getComposableLambda", "composableLambdaInstance", "getComposableLambdaInstance", "composableLambdaN", "getComposableLambdaN", "composableLambdaNInstance", "getComposableLambdaNInstance", "currentComposer", "getCurrentComposer", "isLiveLiteralsEnabled", "isTraceInProgress", "liveLiteral", "getLiveLiteral", "remember", "getRemember", "sourceInformation", "getSourceInformation", "sourceInformationMarkerEnd", "getSourceInformationMarkerEnd", "sourceInformationMarkerStart", "getSourceInformationMarkerStart", "traceEventEnd", "getTraceEventEnd", "traceEventStart", "getTraceEventStart", "updateChangedFlags", "getUpdateChangedFlags", "rememberComposableLambda", "getRememberComposableLambda", "rememberComposableLambdaN", "getRememberComposableLambdaN", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeCallableIds {
    public static final ComposeCallableIds INSTANCE;
    private static final CallableId cache;
    private static final CallableId composableLambda;
    private static final CallableId composableLambdaInstance;
    private static final CallableId composableLambdaN;
    private static final CallableId composableLambdaNInstance;
    private static final CallableId currentComposer;
    private static final CallableId isLiveLiteralsEnabled;
    private static final CallableId isTraceInProgress;
    private static final CallableId key;
    private static final CallableId liveLiteral;
    private static final CallableId remember;
    private static final CallableId rememberComposableLambda;
    private static final CallableId rememberComposableLambdaN;
    private static final CallableId sourceInformation;
    private static final CallableId sourceInformationMarkerEnd;
    private static final CallableId sourceInformationMarkerStart;
    private static final CallableId traceEventEnd;
    private static final CallableId traceEventStart;
    private static final CallableId updateChangedFlags;

    static {
        ComposeCallableIds composeCallableIds = new ComposeCallableIds();
        INSTANCE = composeCallableIds;
        cache = composeCallableIds.topLevelCallableId("cache");
        key = composeCallableIds.topLevelCallableId("key");
        composableLambda = composeCallableIds.internalTopLevelCallableId$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("composableLambda");
        composableLambdaInstance = composeCallableIds.internalTopLevelCallableId$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("composableLambdaInstance");
        composableLambdaN = composeCallableIds.internalTopLevelCallableId$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("composableLambdaN");
        composableLambdaNInstance = composeCallableIds.internalTopLevelCallableId$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("composableLambdaNInstance");
        currentComposer = composeCallableIds.topLevelCallableId("currentComposer");
        isLiveLiteralsEnabled = composeCallableIds.internalTopLevelCallableId$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("isLiveLiteralsEnabled");
        ComposeNames composeNames = ComposeNames.INSTANCE;
        isTraceInProgress = composeCallableIds.topLevelCallableId(composeNames.getIsTraceInProgress());
        liveLiteral = composeCallableIds.internalTopLevelCallableId$org_jetbrains_kotlin_kotlin_compose_compiler_plugin("liveLiteral");
        remember = composeCallableIds.topLevelCallableId("remember");
        sourceInformation = composeCallableIds.topLevelCallableId(composeNames.getSourceInformation());
        sourceInformationMarkerEnd = composeCallableIds.topLevelCallableId(composeNames.getSourceInformationMarkerEnd());
        sourceInformationMarkerStart = composeCallableIds.topLevelCallableId(composeNames.getSourceInformationMarkerStart());
        traceEventEnd = composeCallableIds.topLevelCallableId(composeNames.getTraceEventEnd());
        traceEventStart = composeCallableIds.topLevelCallableId(composeNames.getTraceEventStart());
        updateChangedFlags = composeCallableIds.topLevelCallableId(composeNames.getUpdateChangedFlags());
        rememberComposableLambda = composeCallableIds.internalTopLevelCallableId$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(composeNames.getRememberComposableLambda());
        rememberComposableLambdaN = composeCallableIds.internalTopLevelCallableId$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(composeNames.getRememberComposableLambdaN());
    }

    private ComposeCallableIds() {
    }

    private final CallableId topLevelCallableId(String name) {
        FqName fqNameAccess$getRootFqName$p = ComposeFqNamesKt.access$getRootFqName$p();
        Name nameIdentifier = Name.identifier(name);
        nameIdentifier.getClass();
        return new CallableId(fqNameAccess$getRootFqName$p, nameIdentifier);
    }

    public final CallableId getCache() {
        return cache;
    }

    public final CallableId getComposableLambda() {
        return composableLambda;
    }

    public final CallableId getComposableLambdaInstance() {
        return composableLambdaInstance;
    }

    public final CallableId getComposableLambdaN() {
        return composableLambdaN;
    }

    public final CallableId getComposableLambdaNInstance() {
        return composableLambdaNInstance;
    }

    public final CallableId getCurrentComposer() {
        return currentComposer;
    }

    public final CallableId getKey() {
        return key;
    }

    public final CallableId getLiveLiteral() {
        return liveLiteral;
    }

    public final CallableId getRemember() {
        return remember;
    }

    public final CallableId getRememberComposableLambda() {
        return rememberComposableLambda;
    }

    public final CallableId getRememberComposableLambdaN() {
        return rememberComposableLambdaN;
    }

    public final CallableId getSourceInformation() {
        return sourceInformation;
    }

    public final CallableId getSourceInformationMarkerEnd() {
        return sourceInformationMarkerEnd;
    }

    public final CallableId getSourceInformationMarkerStart() {
        return sourceInformationMarkerStart;
    }

    public final CallableId getTraceEventEnd() {
        return traceEventEnd;
    }

    public final CallableId getTraceEventStart() {
        return traceEventStart;
    }

    public final CallableId getUpdateChangedFlags() {
        return updateChangedFlags;
    }

    public final CallableId internalTopLevelCallableId$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(String name) {
        name.getClass();
        FqName fqNameAccess$getInternalRootFqName$p = ComposeFqNamesKt.access$getInternalRootFqName$p();
        Name nameIdentifier = Name.identifier(name);
        nameIdentifier.getClass();
        return new CallableId(fqNameAccess$getInternalRootFqName$p, nameIdentifier);
    }

    public final CallableId isLiveLiteralsEnabled() {
        return isLiveLiteralsEnabled;
    }

    public final CallableId isTraceInProgress() {
        return isTraceInProgress;
    }
}
