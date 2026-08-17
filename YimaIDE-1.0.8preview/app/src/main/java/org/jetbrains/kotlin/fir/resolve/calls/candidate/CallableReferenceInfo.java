package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import java.util.List;
import kotlin.Metadata;
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
import org.jetbrains.kotlin.fir.resolve.DoubleColonLHS;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0018¢\u0006\u0004\b\u0019\u0010\u001aJT\u0010!\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\r2\u0006\u0010$\u001a\u00020%2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010)H\u0016R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallableReferenceInfo;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "callSite", "Lorg/jetbrains/kotlin/fir/FirElement;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "explicitReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "containingFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "containingDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "lhs", "Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS;", "hasSyntheticOuterCall", Argument.Delimiters.none, "origin", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCallOrigin;", "callKind", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/declarations/FirFile;Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS;ZLorg/jetbrains/kotlin/fir/expressions/FirFunctionCallOrigin;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;)V", "getExpectedType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getLhs", "()Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS;", "getHasSyntheticOuterCall", "()Z", "copy", "typeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "argumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "implicitInvokeMode", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/ImplicitInvokeMode;", "candidateForCommonInvokeReceiver", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "containingCandidateForCollectionLiteral", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CallableReferenceInfo extends CallInfo {
    private final ConeKotlinType expectedType;
    private final boolean hasSyntheticOuterCall;
    private final DoubleColonLHS lhs;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallableReferenceInfo(FirElement firElement, Name name, FirExpression firExpression, FirSession firSession, FirFile firFile, List<? extends FirDeclaration> list, ConeKotlinType coneKotlinType, DoubleColonLHS doubleColonLHS, boolean z, FirFunctionCallOrigin firFunctionCallOrigin, CallKind callKind) {
        super(firElement, callKind, name, firExpression, FirEmptyArgumentList.INSTANCE, false, CollectionsKt.emptyList(), firSession, firFile, list, null, ResolutionMode.ContextIndependent.INSTANCE, firFunctionCallOrigin, ImplicitInvokeMode.None, null);
        firElement.getClass();
        name.getClass();
        firSession.getClass();
        firFile.getClass();
        list.getClass();
        firFunctionCallOrigin.getClass();
        callKind.getClass();
        this.expectedType = coneKotlinType;
        this.lhs = doubleColonLHS;
        this.hasSyntheticOuterCall = z;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo
    public CallableReferenceInfo copy(CallKind callKind, List<? extends FirTypeProjection> typeArguments, FirArgumentList argumentList, FirExpression explicitReceiver, Name name, ImplicitInvokeMode implicitInvokeMode, Candidate candidateForCommonInvokeReceiver, Candidate containingCandidateForCollectionLiteral) {
        callKind.getClass();
        typeArguments.getClass();
        argumentList.getClass();
        name.getClass();
        implicitInvokeMode.getClass();
        return new CallableReferenceInfo(getCallSite(), name, explicitReceiver, getSession(), getContainingFile(), getContainingDeclarations(), this.expectedType, this.lhs, this.hasSyntheticOuterCall, getOrigin(), callKind);
    }

    public final ConeKotlinType getExpectedType() {
        return this.expectedType;
    }

    public final boolean getHasSyntheticOuterCall() {
        return this.hasSyntheticOuterCall;
    }

    public final DoubleColonLHS getLhs() {
        return this.lhs;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo
    public /* bridge */ /* synthetic */ CallInfo copy(CallKind callKind, List list, FirArgumentList firArgumentList, FirExpression firExpression, Name name, ImplicitInvokeMode implicitInvokeMode, Candidate candidate, Candidate candidate2) {
        return copy(callKind, (List<? extends FirTypeProjection>) list, firArgumentList, firExpression, name, implicitInvokeMode, candidate, candidate2);
    }

    public /* synthetic */ CallableReferenceInfo(FirElement firElement, Name name, FirExpression firExpression, FirSession firSession, FirFile firFile, List list, ConeKotlinType coneKotlinType, DoubleColonLHS doubleColonLHS, boolean z, FirFunctionCallOrigin firFunctionCallOrigin, CallKind callKind, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firElement, name, firExpression, firSession, firFile, list, coneKotlinType, doubleColonLHS, z, (i & 512) != 0 ? FirFunctionCallOrigin.Regular : firFunctionCallOrigin, (i & 1024) != 0 ? CallKind.CallableReference.INSTANCE : callKind);
    }
}
