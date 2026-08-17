package androidx.compose.compiler.plugins.kotlin;

import kotlin.Metadata;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b%\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0014\u0010\u0012\u001a\u00020\u0013X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0014\u0010\u001e\u001a\u00020\u0013X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0014\u0010 \u001a\u00020\u0013X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015R\u0014\u0010\"\u001a\u00020\u0013X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015R\u0014\u0010$\u001a\u00020\u0013X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0015R\u0014\u0010&\u001a\u00020\u0013X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0015R\u0014\u0010(\u001a\u00020\u0013X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0015R\u0014\u0010*\u001a\u00020\u0013X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0015R\u0011\u0010,\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0007R\u0011\u0010.\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0007R\u0014\u00100\u001a\u00020\u0013X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0015R\u0014\u00102\u001a\u00020\u0013X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0015R\u0011\u00104\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0007R\u0011\u00106\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0007¨\u00068"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ComposeNames;", "", "<init>", "()V", "ComposerParameter", "Lorg/jetbrains/kotlin/name/Name;", "getComposerParameter", "()Lorg/jetbrains/kotlin/name/Name;", "ChangedParameter", "getChangedParameter", "ForceParameter", "getForceParameter", "DefaultParameter", "getDefaultParameter", "StabilityFlag", "getStabilityFlag", "StabilityFlagProperty", "getStabilityFlagProperty", "StabilityFlagPropertyGetter", "", "getStabilityFlagPropertyGetter", "()Ljava/lang/String;", "JoinKey", "getJoinKey", "StartRestartGroup", "getStartRestartGroup", "EndRestartGroup", "getEndRestartGroup", "UpdateScope", "getUpdateScope", "SourceInformation", "getSourceInformation", "SourceInformationMarkerStart", "getSourceInformationMarkerStart", "IsTraceInProgress", "getIsTraceInProgress", "TraceEventStart", "getTraceEventStart", "TraceEventEnd", "getTraceEventEnd", "SourceInformationMarkerEnd", "getSourceInformationMarkerEnd", "UpdateChangedFlags", "getUpdateChangedFlags", "CurrentMarker", "getCurrentMarker", "EndToMarker", "getEndToMarker", "RememberComposableLambda", "getRememberComposableLambda", "RememberComposableLambdaN", "getRememberComposableLambdaN", "DefaultImpls", "getDefaultImpls", "ShouldExecute", "getShouldExecute", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposeNames {
    private static final Name ChangedParameter;
    private static final Name ComposerParameter;
    private static final Name CurrentMarker;
    private static final Name DefaultImpls;
    private static final Name DefaultParameter;
    private static final Name EndRestartGroup;
    private static final Name EndToMarker;
    private static final Name ForceParameter;
    public static final ComposeNames INSTANCE = new ComposeNames();
    private static final String IsTraceInProgress;
    private static final Name JoinKey;
    private static final String RememberComposableLambda;
    private static final String RememberComposableLambdaN;
    private static final Name ShouldExecute;
    private static final String SourceInformation;
    private static final String SourceInformationMarkerEnd;
    private static final String SourceInformationMarkerStart;
    private static final Name StabilityFlag;
    private static final Name StabilityFlagProperty;
    private static final String StabilityFlagPropertyGetter;
    private static final Name StartRestartGroup;
    private static final String TraceEventEnd;
    private static final String TraceEventStart;
    private static final String UpdateChangedFlags;
    private static final Name UpdateScope;

    static {
        Name nameIdentifier = Name.identifier("$composer");
        nameIdentifier.getClass();
        ComposerParameter = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("$changed");
        nameIdentifier2.getClass();
        ChangedParameter = nameIdentifier2;
        Name nameIdentifier3 = Name.identifier("$force");
        nameIdentifier3.getClass();
        ForceParameter = nameIdentifier3;
        Name nameIdentifier4 = Name.identifier("$default");
        nameIdentifier4.getClass();
        DefaultParameter = nameIdentifier4;
        Name nameIdentifier5 = Name.identifier("$stable");
        nameIdentifier5.getClass();
        StabilityFlag = nameIdentifier5;
        Name nameIdentifier6 = Name.identifier("$stableprop");
        nameIdentifier6.getClass();
        StabilityFlagProperty = nameIdentifier6;
        StabilityFlagPropertyGetter = "$stableprop_getter";
        Name nameIdentifier7 = Name.identifier("joinKey");
        nameIdentifier7.getClass();
        JoinKey = nameIdentifier7;
        Name nameIdentifier8 = Name.identifier("startRestartGroup");
        nameIdentifier8.getClass();
        StartRestartGroup = nameIdentifier8;
        Name nameIdentifier9 = Name.identifier("endRestartGroup");
        nameIdentifier9.getClass();
        EndRestartGroup = nameIdentifier9;
        Name nameIdentifier10 = Name.identifier("updateScope");
        nameIdentifier10.getClass();
        UpdateScope = nameIdentifier10;
        SourceInformation = "sourceInformation";
        SourceInformationMarkerStart = "sourceInformationMarkerStart";
        IsTraceInProgress = "isTraceInProgress";
        TraceEventStart = "traceEventStart";
        TraceEventEnd = "traceEventEnd";
        SourceInformationMarkerEnd = "sourceInformationMarkerEnd";
        UpdateChangedFlags = "updateChangedFlags";
        Name nameIdentifier11 = Name.identifier("currentMarker");
        nameIdentifier11.getClass();
        CurrentMarker = nameIdentifier11;
        Name nameIdentifier12 = Name.identifier("endToMarker");
        nameIdentifier12.getClass();
        EndToMarker = nameIdentifier12;
        RememberComposableLambda = "rememberComposableLambda";
        RememberComposableLambdaN = "rememberComposableLambdaN";
        Name nameIdentifier13 = Name.identifier("ComposeDefaultImpls");
        nameIdentifier13.getClass();
        DefaultImpls = nameIdentifier13;
        Name nameIdentifier14 = Name.identifier("shouldExecute");
        nameIdentifier14.getClass();
        ShouldExecute = nameIdentifier14;
    }

    private ComposeNames() {
    }

    public final Name getChangedParameter() {
        return ChangedParameter;
    }

    public final Name getComposerParameter() {
        return ComposerParameter;
    }

    public final Name getCurrentMarker() {
        return CurrentMarker;
    }

    public final Name getDefaultImpls() {
        return DefaultImpls;
    }

    public final Name getDefaultParameter() {
        return DefaultParameter;
    }

    public final Name getEndRestartGroup() {
        return EndRestartGroup;
    }

    public final Name getEndToMarker() {
        return EndToMarker;
    }

    public final Name getForceParameter() {
        return ForceParameter;
    }

    public final String getIsTraceInProgress() {
        return IsTraceInProgress;
    }

    public final Name getJoinKey() {
        return JoinKey;
    }

    public final String getRememberComposableLambda() {
        return RememberComposableLambda;
    }

    public final String getRememberComposableLambdaN() {
        return RememberComposableLambdaN;
    }

    public final Name getShouldExecute() {
        return ShouldExecute;
    }

    public final String getSourceInformation() {
        return SourceInformation;
    }

    public final String getSourceInformationMarkerEnd() {
        return SourceInformationMarkerEnd;
    }

    public final String getSourceInformationMarkerStart() {
        return SourceInformationMarkerStart;
    }

    public final Name getStabilityFlag() {
        return StabilityFlag;
    }

    public final Name getStabilityFlagProperty() {
        return StabilityFlagProperty;
    }

    public final String getStabilityFlagPropertyGetter() {
        return StabilityFlagPropertyGetter;
    }

    public final Name getStartRestartGroup() {
        return StartRestartGroup;
    }

    public final String getTraceEventEnd() {
        return TraceEventEnd;
    }

    public final String getTraceEventStart() {
        return TraceEventStart;
    }

    public final String getUpdateChangedFlags() {
        return UpdateChangedFlags;
    }

    public final Name getUpdateScope() {
        return UpdateScope;
    }
}
