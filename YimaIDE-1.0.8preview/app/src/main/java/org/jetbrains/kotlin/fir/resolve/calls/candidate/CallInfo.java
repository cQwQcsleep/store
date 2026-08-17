package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirEmptyArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u0097\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000f\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u001e\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b \u0010!J\u0006\u0010D\u001a\u00020\u0000J\u0010\u0010E\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u000e\u0010F\u001a\u00020\u00002\u0006\u0010G\u001a\u00020\tJd\u0010H\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0018H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010,R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000f¢\u0006\b\n\u0000\u001a\u0004\b3\u0010.R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b<\u00105R\u0011\u0010=\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b=\u0010,R\u0014\u0010>\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b>\u0010,R\u0017\u0010?\u001a\b\u0012\u0004\u0012\u00020\t0\u000f8F¢\u0006\u0006\u001a\u0004\b@\u0010.R\u0017\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\u000f¢\u0006\b\n\u0000\u001a\u0004\bC\u0010.¨\u0006I"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCallInfo;", "callSite", "Lorg/jetbrains/kotlin/fir/FirElement;", "callKind", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "explicitReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "argumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "isUsedAsGetClassReceiver", Argument.Delimiters.none, "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "containingFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "containingDeclarations", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "candidateForCommonInvokeReceiver", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "resolutionMode", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "origin", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCallOrigin;", "implicitInvokeMode", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/ImplicitInvokeMode;", "containingCandidateForCollectionLiteral", "<init>", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;ZLjava/util/List;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/declarations/FirFile;Ljava/util/List;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCallOrigin;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/ImplicitInvokeMode;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;)V", "getCallSite", "()Lorg/jetbrains/kotlin/fir/FirElement;", "getCallKind", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getExplicitReceiver", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getArgumentList", "()Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "()Z", "getTypeArguments", "()Ljava/util/List;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getContainingFile", "()Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getContainingDeclarations", "getCandidateForCommonInvokeReceiver", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "getResolutionMode", "()Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCallOrigin;", "getImplicitInvokeMode", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/ImplicitInvokeMode;", "getContainingCandidateForCollectionLiteral", "isCollectionLiteralCall", "isImplicitInvoke", "arguments", "getArguments", "argumentAtoms", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "getArgumentAtoms", "replaceWithVariableAccess", "replaceExplicitReceiver", "withReceiverAsArgument", "receiverExpression", "copy", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class CallInfo extends AbstractCallInfo {
    private final List<ConeResolutionAtom> argumentAtoms;
    private final FirArgumentList argumentList;
    private final CallKind callKind;
    private final FirElement callSite;
    private final Candidate candidateForCommonInvokeReceiver;
    private final Candidate containingCandidateForCollectionLiteral;
    private final List<FirDeclaration> containingDeclarations;
    private final FirFile containingFile;
    private final FirExpression explicitReceiver;
    private final ImplicitInvokeMode implicitInvokeMode;
    private final boolean isUsedAsGetClassReceiver;
    private final Name name;
    private final FirFunctionCallOrigin origin;
    private final ResolutionMode resolutionMode;
    private final FirSession session;
    private final List<FirTypeProjection> typeArguments;

    /* JADX WARN: Multi-variable type inference failed */
    public CallInfo(FirElement firElement, CallKind callKind, Name name, FirExpression firExpression, FirArgumentList firArgumentList, boolean z, List<? extends FirTypeProjection> list, FirSession firSession, FirFile firFile, List<? extends FirDeclaration> list2, Candidate candidate, ResolutionMode resolutionMode, FirFunctionCallOrigin firFunctionCallOrigin, ImplicitInvokeMode implicitInvokeMode, Candidate candidate2) {
        firElement.getClass();
        callKind.getClass();
        name.getClass();
        firArgumentList.getClass();
        list.getClass();
        firSession.getClass();
        firFile.getClass();
        list2.getClass();
        resolutionMode.getClass();
        firFunctionCallOrigin.getClass();
        implicitInvokeMode.getClass();
        this.callSite = firElement;
        this.callKind = callKind;
        this.name = name;
        this.explicitReceiver = firExpression;
        this.argumentList = firArgumentList;
        this.isUsedAsGetClassReceiver = z;
        this.typeArguments = list;
        this.session = firSession;
        this.containingFile = firFile;
        this.containingDeclarations = list2;
        this.candidateForCommonInvokeReceiver = candidate;
        this.resolutionMode = resolutionMode;
        this.origin = firFunctionCallOrigin;
        this.implicitInvokeMode = implicitInvokeMode;
        this.containingCandidateForCollectionLiteral = candidate2;
        List<FirExpression> arguments = getArguments();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
        Iterator<T> it = arguments.iterator();
        while (it.hasNext()) {
            arrayList.add(ConeResolutionAtom.INSTANCE.createRawAtom((FirExpression) it.next()));
        }
        this.argumentAtoms = arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CallInfo copy$default(CallInfo callInfo, CallKind callKind, List list, FirArgumentList firArgumentList, FirExpression firExpression, Name name, ImplicitInvokeMode implicitInvokeMode, Candidate candidate, Candidate candidate2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: copy");
            return null;
        }
        if ((i & 1) != 0) {
            callKind = callInfo.callKind;
        }
        if ((i & 2) != 0) {
            list = callInfo.typeArguments;
        }
        if ((i & 4) != 0) {
            firArgumentList = callInfo.getArgumentList();
        }
        if ((i & 8) != 0) {
            firExpression = callInfo.getExplicitReceiver();
        }
        if ((i & 16) != 0) {
            name = callInfo.getName();
        }
        if ((i & 32) != 0) {
            implicitInvokeMode = callInfo.implicitInvokeMode;
        }
        if ((i & 64) != 0) {
            candidate = callInfo.candidateForCommonInvokeReceiver;
        }
        if ((i & 128) != 0) {
            candidate2 = callInfo.containingCandidateForCollectionLiteral;
        }
        Candidate candidate3 = candidate;
        Candidate candidate4 = candidate2;
        Name name2 = name;
        ImplicitInvokeMode implicitInvokeMode2 = implicitInvokeMode;
        return callInfo.copy(callKind, list, firArgumentList, firExpression, name2, implicitInvokeMode2, candidate3, candidate4);
    }

    public CallInfo copy(CallKind callKind, List<? extends FirTypeProjection> typeArguments, FirArgumentList argumentList, FirExpression explicitReceiver, Name name, ImplicitInvokeMode implicitInvokeMode, Candidate candidateForCommonInvokeReceiver, Candidate containingCandidateForCollectionLiteral) {
        callKind.getClass();
        typeArguments.getClass();
        argumentList.getClass();
        name.getClass();
        implicitInvokeMode.getClass();
        return new CallInfo(this.callSite, callKind, name, explicitReceiver, argumentList, this.isUsedAsGetClassReceiver, typeArguments, this.session, getContainingFile(), this.containingDeclarations, candidateForCommonInvokeReceiver, this.resolutionMode, this.origin, implicitInvokeMode, containingCandidateForCollectionLiteral);
    }

    public final List<ConeResolutionAtom> getArgumentAtoms() {
        return this.argumentAtoms;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallInfo
    public FirArgumentList getArgumentList() {
        return this.argumentList;
    }

    public final List<FirExpression> getArguments() {
        FirArgumentList originalArgumentList;
        List<FirExpression> arguments;
        FirArgumentList argumentList = getArgumentList();
        FirResolvedArgumentList firResolvedArgumentList = argumentList instanceof FirResolvedArgumentList ? (FirResolvedArgumentList) argumentList : null;
        return (firResolvedArgumentList == null || (originalArgumentList = firResolvedArgumentList.getOriginalArgumentList()) == null || (arguments = originalArgumentList.getArguments()) == null) ? getArgumentList().getArguments() : arguments;
    }

    public final CallKind getCallKind() {
        return this.callKind;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallInfo
    public final FirElement getCallSite() {
        return this.callSite;
    }

    public final Candidate getCandidateForCommonInvokeReceiver() {
        return this.candidateForCommonInvokeReceiver;
    }

    public final Candidate getContainingCandidateForCollectionLiteral() {
        return this.containingCandidateForCollectionLiteral;
    }

    public final List<FirDeclaration> getContainingDeclarations() {
        return this.containingDeclarations;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallInfo
    public FirFile getContainingFile() {
        return this.containingFile;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallInfo
    public FirExpression getExplicitReceiver() {
        return this.explicitReceiver;
    }

    public final ImplicitInvokeMode getImplicitInvokeMode() {
        return this.implicitInvokeMode;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallInfo
    public Name getName() {
        return this.name;
    }

    public final FirFunctionCallOrigin getOrigin() {
        return this.origin;
    }

    public final ResolutionMode getResolutionMode() {
        return this.resolutionMode;
    }

    public final FirSession getSession() {
        return this.session;
    }

    public final List<FirTypeProjection> getTypeArguments() {
        return this.typeArguments;
    }

    public final boolean isCollectionLiteralCall() {
        return this.containingCandidateForCollectionLiteral != null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallInfo
    public boolean isImplicitInvoke() {
        return this.implicitInvokeMode != ImplicitInvokeMode.None;
    }

    /* JADX INFO: renamed from: isUsedAsGetClassReceiver, reason: from getter */
    public final boolean getIsUsedAsGetClassReceiver() {
        return this.isUsedAsGetClassReceiver;
    }

    public final CallInfo replaceExplicitReceiver(FirExpression explicitReceiver) {
        return copy$default(this, null, null, null, explicitReceiver, null, null, null, null, 247, null);
    }

    public final CallInfo replaceWithVariableAccess() {
        return copy$default(this, CallKind.VariableAccess.INSTANCE, CollectionsKt.emptyList(), FirEmptyArgumentList.INSTANCE, null, null, null, null, null, 248, null);
    }

    public final CallInfo withReceiverAsArgument(FirExpression receiverExpression) {
        receiverExpression.getClass();
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        firArgumentListBuilder.getArguments().add(receiverExpression);
        CollectionsKt.addAll(firArgumentListBuilder.getArguments(), getArgumentList().getArguments());
        Unit unit = Unit.INSTANCE;
        return copy$default(this, null, null, firArgumentListBuilder.build(), null, null, ImplicitInvokeMode.ReceiverAsArgument, null, null, 219, null);
    }

    public /* synthetic */ CallInfo(FirElement firElement, CallKind callKind, Name name, FirExpression firExpression, FirArgumentList firArgumentList, boolean z, List list, FirSession firSession, FirFile firFile, List list2, Candidate candidate, ResolutionMode resolutionMode, FirFunctionCallOrigin firFunctionCallOrigin, ImplicitInvokeMode implicitInvokeMode, Candidate candidate2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firElement, callKind, name, firExpression, firArgumentList, z, list, firSession, firFile, list2, (i & 1024) != 0 ? null : candidate, resolutionMode, (i & 4096) != 0 ? FirFunctionCallOrigin.Regular : firFunctionCallOrigin, implicitInvokeMode, (i & 16384) != 0 ? null : candidate2);
    }
}
