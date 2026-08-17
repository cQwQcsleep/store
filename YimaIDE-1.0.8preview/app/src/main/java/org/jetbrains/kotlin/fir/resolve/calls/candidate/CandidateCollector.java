package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionResultOverridesOtherToPreserveCompatibility;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStageRunner;
import org.jetbrains.kotlin.fir.resolve.calls.tower.TowerGroup;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicabilityKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u001b\u001a\u00020\u001cJ \u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!H\u0016J\u000e\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u001aJ\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0$J\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000e0$J\u0010\u0010&\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010'\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b(\u0010\u0018R\u0017\u0010)\u001a\u00020\u00158F¢\u0006\f\u0012\u0004\b*\u0010+\u001a\u0004\b)\u0010\u0018¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;", Argument.Delimiters.none, "components", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "resolutionStageRunner", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStageRunner;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStageRunner;)V", "getComponents", "()Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "groupNumbers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", "candidates", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "value", "Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "currentApplicability", "getCurrentApplicability", "()Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "bestGroup", Argument.Delimiters.none, "metInapplicableCandidate", "getMetInapplicableCandidate", "()Z", "forwardedDiagnostics", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "newDataSet", Argument.Delimiters.none, "consumeCandidate", "group", "candidate", "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "addForwardedDiagnostic", "diagnostic", Argument.Delimiters.none, "bestCandidates", "shouldStopAtTheGroup", "shouldStopResolve", "getShouldStopResolve", "isSuccess", "isSuccess$annotations", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class CandidateCollector {
    private TowerGroup bestGroup;
    private final List<Candidate> candidates;
    private final BodyResolveComponents components;
    private CandidateApplicability currentApplicability;
    private final List<ResolutionDiagnostic> forwardedDiagnostics;
    private final List<TowerGroup> groupNumbers;
    private boolean metInapplicableCandidate;
    private final ResolutionStageRunner resolutionStageRunner;

    public CandidateCollector(BodyResolveComponents bodyResolveComponents, ResolutionStageRunner resolutionStageRunner) {
        bodyResolveComponents.getClass();
        resolutionStageRunner.getClass();
        this.components = bodyResolveComponents;
        this.resolutionStageRunner = resolutionStageRunner;
        this.groupNumbers = new ArrayList();
        this.candidates = new ArrayList();
        this.currentApplicability = CandidateApplicability.HIDDEN;
        this.bestGroup = TowerGroup.INSTANCE.getLast();
        this.forwardedDiagnostics = new ArrayList();
    }

    public static /* synthetic */ void isSuccess$annotations() {
    }

    public final void addForwardedDiagnostic(ResolutionDiagnostic diagnostic) {
        diagnostic.getClass();
        this.forwardedDiagnostics.add(diagnostic);
    }

    public final List<Candidate> bestCandidates() {
        return this.candidates;
    }

    public CandidateApplicability consumeCandidate(TowerGroup group, Candidate candidate, ResolutionContext context) {
        group.getClass();
        candidate.getClass();
        context.getClass();
        CandidateApplicability candidateApplicabilityProcessCandidate$default = ResolutionStageRunner.processCandidate$default(this.resolutionStageRunner, candidate, context, false, false, 12, null);
        if (candidateApplicabilityProcessCandidate$default == CandidateApplicability.INAPPLICABLE) {
            this.metInapplicableCandidate = true;
        }
        if (candidateApplicabilityProcessCandidate$default.compareTo(this.currentApplicability) > 0 || (candidateApplicabilityProcessCandidate$default == this.currentApplicability && group.compareTo(this.bestGroup) < 0)) {
            if (candidateApplicabilityProcessCandidate$default.compareTo(CandidateApplicability.RESOLVED_LOW_PRIORITY) >= 0) {
                this.candidates.clear();
            }
            CandidateApplicability candidateApplicability = this.currentApplicability;
            if (candidateApplicability == CandidateApplicability.RESOLVED_NEED_PRESERVE_COMPATIBILITY && candidateApplicabilityProcessCandidate$default.compareTo(candidateApplicability) > 0) {
                candidate.addDiagnostic(ResolutionResultOverridesOtherToPreserveCompatibility.INSTANCE);
            }
            this.currentApplicability = candidateApplicabilityProcessCandidate$default;
            this.bestGroup = group;
        }
        if ((candidateApplicabilityProcessCandidate$default == this.currentApplicability && Intrinsics.areEqual(group, this.bestGroup)) || (this.currentApplicability == CandidateApplicability.INAPPLICABLE_ARGUMENTS_MAPPING_ERROR && candidateApplicabilityProcessCandidate$default == CandidateApplicability.INAPPLICABLE_WRONG_RECEIVER)) {
            this.candidates.add(candidate);
        }
        return candidateApplicabilityProcessCandidate$default;
    }

    public final List<ResolutionDiagnostic> forwardedDiagnostics() {
        return this.forwardedDiagnostics;
    }

    public final BodyResolveComponents getComponents() {
        return this.components;
    }

    public final CandidateApplicability getCurrentApplicability() {
        return this.currentApplicability;
    }

    public final boolean getMetInapplicableCandidate() {
        return this.metInapplicableCandidate;
    }

    public final boolean getShouldStopResolve() {
        return CandidateApplicabilityKt.getShouldStopResolve(this.currentApplicability);
    }

    public final boolean isSuccess() {
        return CandidateApplicabilityKt.isSuccess(this.currentApplicability);
    }

    public final void newDataSet() {
        this.groupNumbers.clear();
        this.candidates.clear();
        this.currentApplicability = CandidateApplicability.HIDDEN;
        this.bestGroup = TowerGroup.INSTANCE.getLast();
        this.forwardedDiagnostics.clear();
        this.metInapplicableCandidate = false;
    }

    public boolean shouldStopAtTheGroup(TowerGroup group) {
        group.getClass();
        return getShouldStopResolve() && this.bestGroup.compareTo(group) < 0;
    }
}
