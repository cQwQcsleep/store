package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollector;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionConversionKind;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirThisReceiverExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirExpressionStub;
import org.jetbrains.kotlin.fir.resolve.FirSamResolver;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.CallableReferenceAdaptation;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeSimpleLeafResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnosticKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.stages.TypeArgumentMapping;
import org.jetbrains.kotlin.fir.resolve.inference.InferenceComponents;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeVariable;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintStorage;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintSystemError;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¢\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0004Ë\u0001Ì\u0001Bq\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\u0010\t\u001a\u00060\nR\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0015\u001a\u00020\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0018\u0010(\u001a\u00020)2\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0007b\u0002\b*J\u001c\u0010@\u001a\u00020)2\u0006\u0010:\u001a\u0002092\f\u0010>\u001a\b\u0012\u0004\u0012\u00020=05J\u0014\u0010A\u001a\u00020)2\u0006\u0010:\u001a\u000209H\u0007b\u0002\b*J\u001f\u0010J\u001a\u00020)2\b\u0010G\u001a\u0004\u0018\u00010F2\u0006\u0010C\u001a\u00020BH\u0000¢\u0006\u0002\bKJ\u0016\u0010R\u001a\u00020)2\u0006\u0010S\u001a\u00020M2\u0006\u0010T\u001a\u00020NJ\u0016\u0010[\u001a\u00020)2\u0006\u0010\\\u001a\u00020M2\u0006\u0010]\u001a\u00020VJ8\u0010t\u001a\u00020)2\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\u0002052\"\u0010m\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020i0hj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020i`jJ0\u0010u\u001a\u00020)2\"\u0010m\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020i0hj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020i`jH\u0007b\u0002\b*J\u001a\u0010v\u001a\u00020)2\f\u0010w\u001a\b\u0012\u0004\u0012\u00020\u000205H\u0007b\u0002\b*J\u0011\u0010\u0087\u0001\u001a\u00020)2\b\u0010\u0088\u0001\u001a\u00030\u0085\u0001J\u001b\u0010\u008b\u0001\u001a\u00020)2\u0007\u0010\u008c\u0001\u001a\u00020M2\u0007\u0010\u008d\u0001\u001a\u00020MH\u0002J\u001a\u0010\u008e\u0001\u001a\u00020)2\b\u0010\u008c\u0001\u001a\u00030\u008f\u00012\u0007\u0010\u008d\u0001\u001a\u00020MJ\u001a\u0010\u0090\u0001\u001a\u00020)2\b\u0010\u008c\u0001\u001a\u00030\u0091\u00012\u0007\u0010\u008d\u0001\u001a\u00020MJ\u0011\u0010§\u0001\u001a\u00020)2\b\u0010¨\u0001\u001a\u00030¥\u0001J\t\u0010·\u0001\u001a\u0004\u0018\u00010MJ\t\u0010¸\u0001\u001a\u0004\u0018\u00010MJ\r\u0010\u00ad\u0001\u001a\b\u0012\u0004\u0012\u00020M05J\u0007\u0010º\u0001\u001a\u00020)J\r\u0010»\u0001\u001a\u00020\u0002*\u00020\u0002H\u0002J\u0007\u0010¾\u0001\u001a\u00020)J\u0017\u0010Å\u0001\u001a\u00020\u00132\n\u0010Æ\u0001\u001a\u0005\u0018\u00010Ç\u0001H\u0096\u0082\u0004J\u000b\u0010È\u0001\u001a\u00020yH\u0096\u0080\u0004J\f\u0010É\u0001\u001a\u00030Ê\u0001H\u0096\u0080\u0004R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0012\u0010\t\u001a\u00060\nR\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010$R\u0011\u0010\u0014\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010$R&\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\n\u0010%\u001a\u0006\u0012\u0002\b\u00030\u0004@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0014\u0010+\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010$R\u000e\u0010-\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010.\u001a\u00020/8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b0\u00101R\u001a\u00104\u001a\b\u0012\u0004\u0012\u000206058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u00108R\u001e\u0010:\u001a\u0002092\u0006\u0010%\u001a\u000209@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R*\u0010>\u001a\b\u0012\u0004\u0012\u00020=052\f\u0010%\u001a\b\u0012\u0004\u0012\u00020=05@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b?\u00108R\"\u0010C\u001a\u0004\u0018\u00010B2\b\u0010%\u001a\u0004\u0018\u00010B@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\"\u0010G\u001a\u0004\u0018\u00010F2\b\u0010%\u001a\u0004\u0018\u00010F@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR:\u0010O\u001a\u0010\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020N\u0018\u00010L2\u0014\u0010%\u001a\u0010\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020N\u0018\u00010L@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bP\u0010QR^\u0010X\u001a\"\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020V\u0018\u00010Uj\u0010\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020V\u0018\u0001`W2&\u0010%\u001a\"\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020V\u0018\u00010Uj\u0010\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020V\u0018\u0001`W@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bY\u0010ZR\u0011\u0010^\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b_\u0010$R\u0011\u0010`\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\ba\u0010$R\u0011\u0010b\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\bc\u0010$R\u0016\u0010d\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010e\u001a\b\u0012\u0004\u0012\u00020\u0002058F¢\u0006\u0006\u001a\u0004\bf\u00108R.\u0010g\u001a\"\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020i\u0018\u00010hj\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020i\u0018\u0001`jX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010k\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bl\u0010$R0\u0010m\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020i0hj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020i`j8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bn\u0010oR\u001a\u0010p\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010$\"\u0004\br\u0010sR\u001a\u0010x\u001a\u00020yX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010{\"\u0004\b|\u0010}R\u001e\u0010~\u001a\u00020\u007fX\u0086.¢\u0006\u0012\n\u0000\u001a\u0006\b\u0080\u0001\u0010\u0081\u0001\"\u0006\b\u0082\u0001\u0010\u0083\u0001R\u001a\u0010\u0084\u0001\u001a\t\u0012\u0005\u0012\u00030\u0085\u000105¢\u0006\t\n\u0000\u001a\u0005\b\u0086\u0001\u00108R\u001e\u0010\u0089\u0001\u001a\u0011\u0012\u0005\u0012\u00030\u008a\u0001\u0012\u0004\u0012\u00020M\u0018\u00010LX\u0082\u000e¢\u0006\u0002\n\u0000R#\u0010\u0092\u0001\u001a\u0012\u0012\u0005\u0012\u00030\u008a\u0001\u0012\u0004\u0012\u00020M\u0018\u00010\u0093\u00018F¢\u0006\u0007\u001a\u0005\b\u0094\u0001\u0010QR\u001a\u0010\u0095\u0001\u001a\t\u0012\u0004\u0012\u00020\u00020\u0096\u0001¢\u0006\t\n\u0000\u001a\u0005\b\u0097\u0001\u00108R\u001b\u0010\u0098\u0001\u001a\n\u0012\u0005\u0012\u00030\u0099\u00010\u0096\u0001¢\u0006\t\n\u0000\u001a\u0005\b\u009a\u0001\u00108R'\u0010\u009b\u0001\u001a\u0016\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020)0\u009c\u00010\u0096\u0001¢\u0006\t\n\u0000\u001a\u0005\b\u009d\u0001\u00108R#\u0010\u009f\u0001\u001a\u00030\u009e\u00012\u0007\u0010%\u001a\u00030\u009e\u0001@BX\u0086\u000e¢\u0006\n\n\u0000\u001a\u0006\b \u0001\u0010¡\u0001R\u0018\u0010¢\u0001\u001a\u00030\u009e\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b£\u0001\u0010¡\u0001R\u001d\u0010¤\u0001\u001a\t\u0012\u0005\u0012\u00030¥\u000105X\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b¦\u0001\u00108R\u0013\u0010©\u0001\u001a\u00020\u00138F¢\u0006\u0007\u001a\u0005\b©\u0001\u0010$R\u001f\u0010ª\u0001\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b«\u0001\u0010\u001a\"\u0005\b¬\u0001\u0010\u001cR&\u0010\u00ad\u0001\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u000105X\u0096\u000e¢\u0006\u0011\n\u0000\u001a\u0005\b®\u0001\u00108\"\u0006\b¯\u0001\u0010°\u0001R$\u0010±\u0001\u001a\u0004\u0018\u00010yX\u0086\u000e¢\u0006\u0015\n\u0003\u0010¶\u0001\u001a\u0006\b²\u0001\u0010³\u0001\"\u0006\b´\u0001\u0010µ\u0001R\u000f\u0010¹\u0001\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010¼\u0001\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u0013@BX\u0086\u000e¢\u0006\t\n\u0000\u001a\u0005\b½\u0001\u0010$R\u001d\u0010¿\u0001\u001a\u00020\u0013X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÀ\u0001\u0010$\"\u0005\bÁ\u0001\u0010sR\u001d\u0010Â\u0001\u001a\u00020yX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÃ\u0001\u0010{\"\u0005\bÄ\u0001\u0010}¨\u0006Í\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCallCandidate;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "dispatchReceiver", "givenExtensionReceiver", "explicitReceiverKind", "Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;", "constraintSystemFactory", "Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents$ConstraintSystemFactory;", "Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents;", "baseSystem", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintStorage;", "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "originScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "isFromCompanionObjectTypeScope", Argument.Delimiters.none, "isFromOriginalTypeInPresenceOfSmartCast", "bodyResolveContext", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents$ConstraintSystemFactory;Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintStorage;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/scopes/FirScope;ZZLorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;)V", "getDispatchReceiver", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "setDispatchReceiver", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;)V", "getGivenExtensionReceiver", "getExplicitReceiverKind", "()Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;", "getCallInfo", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "getOriginScope", "()Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "()Z", "value", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "updateSymbol", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate$UpdatingCandidateInvariants;", "usedOuterCs", "getUsedOuterCs", "systemInitialized", "system", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;", "getSystem", "()Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;", "system$delegate", "Lkotlin/Lazy;", AbstractDiagnosticCollector.SUPPRESS_ALL_ERRORS, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintSystemError;", "getErrors", "()Ljava/util/List;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "substitutor", "getSubstitutor", "()Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;", "freshVariables", "getFreshVariables", "initializeSubstitutorAndVariables", "updateSubstitutor", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "resultingTypeForCallableReference", "getResultingTypeForCallableReference", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceAdaptation;", "callableReferenceAdaptation", "getCallableReferenceAdaptation$org_jetbrains_kotlin_resolve", "()Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceAdaptation;", "initializeCallableReferenceAdaptation", "initializeCallableReferenceAdaptation$org_jetbrains_kotlin_resolve", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate$FunctionConversionDescription;", "argumentsWithFunctionKindConversion", "getArgumentsWithFunctionKindConversion", "()Ljava/util/Map;", "addFunctionKindConversionOfArgument", "element", "kind", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/fir/resolve/FirSamResolver$SamConversionInfo;", "Lkotlin/collections/HashMap;", "samConversionInfosOfArguments", "getSamConversionInfosOfArguments", "()Ljava/util/HashMap;", "setSamConversionOfArgument", "expression", "conversionInfo", "usesSamConversion", "getUsesSamConversion", "usesSamConversionOrSamConstructor", "getUsesSamConversionOrSamConstructor", "usesFunctionKindConversion", "getUsesFunctionKindConversion", "_arguments", "arguments", "getArguments", "_argumentMapping", "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lkotlin/collections/LinkedHashMap;", "argumentMappingInitialized", "getArgumentMappingInitialized", "argumentMapping", "getArgumentMapping", "()Ljava/util/LinkedHashMap;", "usesCoercionToUnitInLambda", "getUsesCoercionToUnitInLambda", "setUsesCoercionToUnitInLambda", "(Z)V", "initializeArgumentMapping", "updateArgumentMapping", "replaceArgumentPrefix", "newArgumentPrefix", "numDefaults", Argument.Delimiters.none, "getNumDefaults", "()I", "setNumDefaults", "(I)V", "typeArgumentMapping", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/TypeArgumentMapping;", "getTypeArgumentMapping", "()Lorg/jetbrains/kotlin/fir/resolve/calls/stages/TypeArgumentMapping;", "setTypeArgumentMapping", "(Lorg/jetbrains/kotlin/fir/resolve/calls/stages/TypeArgumentMapping;)V", "postponedAtoms", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedResolvedAtom;", "getPostponedAtoms", "addPostponedAtom", "atom", "_updatedArguments", "Lorg/jetbrains/kotlin/fir/FirElement;", "setUpdatedArgument", "old", "new", "setUpdatedArgumentFromContextSensitiveResolution", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "setUpdatedCollectionLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "argumentReplacements", Argument.Delimiters.none, "getArgumentReplacements", "postponedPCLACalls", Argument.Delimiters.none, "getPostponedPCLACalls", "lambdasAnalyzedWithPCLA", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "getLambdasAnalyzedWithPCLA", "onPCLACompletionResultsWritingCallbacks", "Lkotlin/Function1;", "getOnPCLACompletionResultsWritingCallbacks", "Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "lowestApplicability", "getLowestApplicability", "()Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "applicability", "getApplicability", "diagnostics", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "getDiagnostics", "addDiagnostic", "diagnostic", "isSuccessful", "chosenExtensionReceiver", "getChosenExtensionReceiver", "setChosenExtensionReceiver", "contextArguments", "getContextArguments", "setContextArguments", "(Ljava/util/List;)V", "expectedContextParameterCountForInvoke", "getExpectedContextParameterCountForInvoke", "()Ljava/lang/Integer;", "setExpectedContextParameterCountForInvoke", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "dispatchReceiverExpression", "chosenExtensionReceiverExpression", "sourcesWereUpdated", "updateSourcesOfReceivers", "tryToSetSourceForImplicitReceiver", "wasExpectedTypeAddedAsEqualityForSyntheticCall", "getWasExpectedTypeAddedAsEqualityForSyntheticCall", "markWasExpectedTypeAddedAsEqualityForSyntheticCall", "hasVisibleBackingField", "getHasVisibleBackingField", "setHasVisibleBackingField", "passedStages", "getPassedStages", "setPassedStages", "equals", "other", Argument.Delimiters.none, "hashCode", "toString", Argument.Delimiters.none, "FunctionConversionDescription", "UpdatingCandidateInvariants", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Candidate extends AbstractCallCandidate<ConeResolutionAtom> {
    private LinkedHashMap<ConeResolutionAtom, FirValueParameter> _argumentMapping;
    private List<? extends ConeResolutionAtom> _arguments;
    private Map<FirElement, FirExpression> _updatedArguments;
    private Map<FirExpression, FunctionConversionDescription> argumentsWithFunctionKindConversion;
    private final ConstraintStorage baseSystem;
    private final CallInfo callInfo;
    private CallableReferenceAdaptation callableReferenceAdaptation;
    private ConeResolutionAtom chosenExtensionReceiver;
    private final InferenceComponents.ConstraintSystemFactory constraintSystemFactory;
    private List<? extends ConeResolutionAtom> contextArguments;
    private final List<ResolutionDiagnostic> diagnostics;
    private ConeResolutionAtom dispatchReceiver;
    private Integer expectedContextParameterCountForInvoke;
    private final ExplicitReceiverKind explicitReceiverKind;
    private List<? extends ConeTypeVariable> freshVariables;
    private final ConeResolutionAtom givenExtensionReceiver;
    private boolean hasVisibleBackingField;
    private final boolean isFromCompanionObjectTypeScope;
    private final boolean isFromOriginalTypeInPresenceOfSmartCast;
    private final List<FirAnonymousFunction> lambdasAnalyzedWithPCLA;
    private CandidateApplicability lowestApplicability;
    private int numDefaults;
    private final List<Function1<ConeSubstitutor, Unit>> onPCLACompletionResultsWritingCallbacks;
    private final FirScope originScope;
    private int passedStages;
    private final List<ConePostponedResolvedAtom> postponedAtoms;
    private final List<ConeResolutionAtom> postponedPCLACalls;
    private ConeKotlinType resultingTypeForCallableReference;
    private HashMap<FirExpression, FirSamResolver.SamConversionInfo> samConversionInfosOfArguments;
    private boolean sourcesWereUpdated;
    private ConeSubstitutor substitutor;
    private FirBasedSymbol<?> symbol;

    /* JADX INFO: renamed from: system$delegate, reason: from kotlin metadata */
    private final Lazy system;
    private boolean systemInitialized;
    public TypeArgumentMapping typeArgumentMapping;
    private boolean usesCoercionToUnitInLambda;
    private boolean wasExpectedTypeAddedAsEqualityForSyntheticCall;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u000b\u001a\u00020\fJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate$FunctionConversionDescription;", Argument.Delimiters.none, "isFromSimpleToCustom", Argument.Delimiters.none, "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(ZLorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "()Z", "getExpectedType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "toKind", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionConversionKind$BetweenFunctionTypes;", "component1", "component2", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class FunctionConversionDescription {
        private final ConeKotlinType expectedType;
        private final boolean isFromSimpleToCustom;

        public FunctionConversionDescription(boolean z, ConeKotlinType coneKotlinType) {
            coneKotlinType.getClass();
            this.isFromSimpleToCustom = z;
            this.expectedType = coneKotlinType;
        }

        public static /* synthetic */ FunctionConversionDescription copy$default(FunctionConversionDescription functionConversionDescription, boolean z, ConeKotlinType coneKotlinType, int i, Object obj) {
            if ((i & 1) != 0) {
                z = functionConversionDescription.isFromSimpleToCustom;
            }
            if ((i & 2) != 0) {
                coneKotlinType = functionConversionDescription.expectedType;
            }
            return functionConversionDescription.copy(z, coneKotlinType);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsFromSimpleToCustom() {
            return this.isFromSimpleToCustom;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ConeKotlinType getExpectedType() {
            return this.expectedType;
        }

        public final FunctionConversionDescription copy(boolean isFromSimpleToCustom, ConeKotlinType expectedType) {
            expectedType.getClass();
            return new FunctionConversionDescription(isFromSimpleToCustom, expectedType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FunctionConversionDescription)) {
                return false;
            }
            FunctionConversionDescription functionConversionDescription = (FunctionConversionDescription) other;
            return this.isFromSimpleToCustom == functionConversionDescription.isFromSimpleToCustom && Intrinsics.areEqual(this.expectedType, functionConversionDescription.expectedType);
        }

        public final ConeKotlinType getExpectedType() {
            return this.expectedType;
        }

        public int hashCode() {
            return (Boolean.hashCode(this.isFromSimpleToCustom) * 31) + this.expectedType.hashCode();
        }

        public final boolean isFromSimpleToCustom() {
            return this.isFromSimpleToCustom;
        }

        public final FirFunctionConversionKind.BetweenFunctionTypes toKind() {
            return new FirFunctionConversionKind.BetweenFunctionTypes(this.isFromSimpleToCustom);
        }

        public String toString() {
            return "FunctionConversionDescription(isFromSimpleToCustom=" + this.isFromSimpleToCustom + ", expectedType=" + this.expectedType + ')';
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000Ê\u0001\u0002\b\u0003¨\u0006\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate$UpdatingCandidateInvariants;", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve", "Lkotlin/RequiresOptIn;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public @interface UpdatingCandidateInvariants {
    }

    public Candidate(FirBasedSymbol<?> firBasedSymbol, ConeResolutionAtom coneResolutionAtom, ConeResolutionAtom coneResolutionAtom2, ExplicitReceiverKind explicitReceiverKind, InferenceComponents.ConstraintSystemFactory constraintSystemFactory, ConstraintStorage constraintStorage, CallInfo callInfo, FirScope firScope, boolean z, boolean z2, final BodyResolveContext bodyResolveContext) {
        firBasedSymbol.getClass();
        explicitReceiverKind.getClass();
        constraintSystemFactory.getClass();
        constraintStorage.getClass();
        callInfo.getClass();
        bodyResolveContext.getClass();
        this.dispatchReceiver = coneResolutionAtom;
        this.givenExtensionReceiver = coneResolutionAtom2;
        this.explicitReceiverKind = explicitReceiverKind;
        this.constraintSystemFactory = constraintSystemFactory;
        this.baseSystem = constraintStorage;
        this.callInfo = callInfo;
        this.originScope = firScope;
        this.isFromCompanionObjectTypeScope = z;
        this.isFromOriginalTypeInPresenceOfSmartCast = z2;
        this.symbol = firBasedSymbol;
        this.system = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: cc1
            public final Object invoke() {
                return Candidate.a(this.b, bodyResolveContext);
            }
        });
        this.postponedAtoms = new ArrayList();
        this.postponedPCLACalls = new ArrayList();
        this.lambdasAnalyzedWithPCLA = new ArrayList();
        this.onPCLACompletionResultsWritingCallbacks = new ArrayList();
        this.lowestApplicability = CandidateApplicability.RESOLVED;
        this.diagnostics = new ArrayList();
        this.chosenExtensionReceiver = coneResolutionAtom2;
    }

    public static NewConstraintSystemImpl a(Candidate candidate, BodyResolveContext bodyResolveContext) {
        NewConstraintSystemImpl newConstraintSystemImplCreateConstraintSystem = candidate.constraintSystemFactory.createConstraintSystem();
        ConstraintStorage constraintStorageBaseConstraintStorageForCandidate = candidate.baseSystem.getUsesOuterCs() ? null : bodyResolveContext.getInferenceSession().baseConstraintStorageForCandidate(candidate, bodyResolveContext);
        if (constraintStorageBaseConstraintStorageForCandidate != null) {
            newConstraintSystemImplCreateConstraintSystem.setBaseSystem(constraintStorageBaseConstraintStorageForCandidate);
            newConstraintSystemImplCreateConstraintSystem.addOtherSystem(candidate.baseSystem);
        } else {
            newConstraintSystemImplCreateConstraintSystem.setBaseSystem(candidate.baseSystem);
        }
        candidate.systemInitialized = true;
        return newConstraintSystemImplCreateConstraintSystem;
    }

    private final void setUpdatedArgument(FirExpression old, FirExpression firExpression) {
        if (this._updatedArguments == null) {
            this._updatedArguments = new LinkedHashMap();
        }
        Map<FirElement, FirExpression> map = this._updatedArguments;
        map.getClass();
        if (map.put(old, firExpression) == null) {
            return;
        }
        xz8.a("We shouldn't put the value for ", old, " twice");
    }

    private final ConeResolutionAtom tryToSetSourceForImplicitReceiver(ConeResolutionAtom coneResolutionAtom) {
        FirExpression firExpressionTryToSetSourceForImplicitReceiver$tryToSetSourceForImplicitReceiver;
        return ((coneResolutionAtom instanceof ConeSimpleLeafResolutionAtom) && (firExpressionTryToSetSourceForImplicitReceiver$tryToSetSourceForImplicitReceiver = tryToSetSourceForImplicitReceiver$tryToSetSourceForImplicitReceiver(((ConeSimpleLeafResolutionAtom) coneResolutionAtom).getExpression(), this)) != null) ? new ConeSimpleLeafResolutionAtom(firExpressionTryToSetSourceForImplicitReceiver$tryToSetSourceForImplicitReceiver, false) : coneResolutionAtom;
    }

    private static final FirExpression tryToSetSourceForImplicitReceiver$tryToSetSourceForImplicitReceiver(FirExpression firExpression, Candidate candidate) {
        if (firExpression instanceof FirSmartCastExpression) {
            FirSmartCastExpression firSmartCastExpression = (FirSmartCastExpression) firExpression;
            FirExpression firExpressionTryToSetSourceForImplicitReceiver$tryToSetSourceForImplicitReceiver = tryToSetSourceForImplicitReceiver$tryToSetSourceForImplicitReceiver(firSmartCastExpression.getOriginalExpression(), candidate);
            if (firExpressionTryToSetSourceForImplicitReceiver$tryToSetSourceForImplicitReceiver == null) {
                return null;
            }
            firSmartCastExpression.replaceOriginalExpression(firExpressionTryToSetSourceForImplicitReceiver$tryToSetSourceForImplicitReceiver);
            return firExpression;
        }
        if (firExpression instanceof FirThisReceiverExpression) {
            FirThisReceiverExpression firThisReceiverExpression = (FirThisReceiverExpression) firExpression;
            if (firThisReceiverExpression.isImplicit()) {
                FirThisReceiverExpressionBuilder firThisReceiverExpressionBuilder = new FirThisReceiverExpressionBuilder();
                firThisReceiverExpressionBuilder.setConeTypeOrNull(firThisReceiverExpression.getConeTypeOrNull());
                firThisReceiverExpressionBuilder.getAnnotations().addAll(firThisReceiverExpression.getAnnotations());
                firThisReceiverExpressionBuilder.getTypeArguments().addAll(firThisReceiverExpression.getTypeArguments());
                firThisReceiverExpressionBuilder.setSource(firThisReceiverExpression.getSource());
                firThisReceiverExpressionBuilder.getNonFatalDiagnostics().addAll(firThisReceiverExpression.getNonFatalDiagnostics());
                firThisReceiverExpressionBuilder.setCalleeReference(firThisReceiverExpression.getCalleeReference());
                firThisReceiverExpressionBuilder.setImplicit(firThisReceiverExpression.isImplicit());
                KtSourceElement source = candidate.getCallInfo().getCallSite().getSource();
                firThisReceiverExpressionBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitReceiver.INSTANCE, null, 2, null) : null);
                return firThisReceiverExpressionBuilder.build();
            }
        }
        if (firExpression instanceof FirPropertyAccessExpression) {
            FirPropertyAccessExpression firPropertyAccessExpression = (FirPropertyAccessExpression) firExpression;
            KtSourceElement source2 = firPropertyAccessExpression.getSource();
            KtSourceElementKind kind = source2 != null ? source2.getKind() : null;
            KtFakeSourceElementKind.ImplicitContextParameterArgument implicitContextParameterArgument = KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE;
            if (Intrinsics.areEqual(kind, implicitContextParameterArgument)) {
                FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
                firPropertyAccessExpressionBuilder.setConeTypeOrNull(firPropertyAccessExpression.getConeTypeOrNull());
                firPropertyAccessExpressionBuilder.getAnnotations().addAll(firPropertyAccessExpression.getAnnotations());
                firPropertyAccessExpressionBuilder.getContextArguments().addAll(firPropertyAccessExpression.getContextArguments());
                firPropertyAccessExpressionBuilder.getTypeArguments().addAll(firPropertyAccessExpression.getTypeArguments());
                firPropertyAccessExpressionBuilder.setExplicitReceiver(firPropertyAccessExpression.getExplicitReceiver());
                firPropertyAccessExpressionBuilder.setDispatchReceiver(firPropertyAccessExpression.getDispatchReceiver());
                firPropertyAccessExpressionBuilder.setExtensionReceiver(firPropertyAccessExpression.getExtensionReceiver());
                firPropertyAccessExpressionBuilder.setSource(firPropertyAccessExpression.getSource());
                firPropertyAccessExpressionBuilder.getNonFatalDiagnostics().addAll(firPropertyAccessExpression.getNonFatalDiagnostics());
                firPropertyAccessExpressionBuilder.setContextSensitiveAlternative(firPropertyAccessExpression.getContextSensitiveAlternative());
                firPropertyAccessExpressionBuilder.setCalleeReference(firPropertyAccessExpression.getCalleeReference());
                KtSourceElement source3 = candidate.getCallInfo().getCallSite().getSource();
                firPropertyAccessExpressionBuilder.setSource(source3 != null ? KtSourceElementKt.fakeElement$default(source3, implicitContextParameterArgument, null, 2, null) : null);
                return firPropertyAccessExpressionBuilder.build();
            }
        }
        return null;
    }

    public final void addDiagnostic(ResolutionDiagnostic diagnostic) {
        diagnostic.getClass();
        getDiagnostics().add(diagnostic);
        if (diagnostic.getApplicability().compareTo(this.lowestApplicability) < 0) {
            this.lowestApplicability = diagnostic.getApplicability();
        }
    }

    public final void addFunctionKindConversionOfArgument(FirExpression element, FunctionConversionDescription kind) {
        element.getClass();
        kind.getClass();
        Map map = this.argumentsWithFunctionKindConversion;
        if (map == null) {
            map = new HashMap();
            this.argumentsWithFunctionKindConversion = map;
        }
        map.put(element, kind);
    }

    public final void addPostponedAtom(ConePostponedResolvedAtom atom) {
        atom.getClass();
        this.postponedAtoms.add(atom);
    }

    public final FirExpression chosenExtensionReceiverExpression() {
        FirExpression expression;
        ConeResolutionAtom chosenExtensionReceiver = getChosenExtensionReceiver();
        if (chosenExtensionReceiver == null || (expression = chosenExtensionReceiver.getExpression()) == null || (expression instanceof FirExpressionStub)) {
            return null;
        }
        return expression;
    }

    public final List<FirExpression> contextArguments() {
        List<ConeResolutionAtom> contextArguments = getContextArguments();
        if (contextArguments == null) {
            return CollectionsKt.emptyList();
        }
        List<ConeResolutionAtom> list = contextArguments;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(FirExpressionUtilKt.unwrapArgument(((ConeResolutionAtom) it.next()).getExpression()));
        }
        return arrayList;
    }

    public final FirExpression dispatchReceiverExpression() {
        FirExpression expression;
        ConeResolutionAtom dispatchReceiver = getDispatchReceiver();
        if (dispatchReceiver == null || (expression = dispatchReceiver.getExpression()) == null || (expression instanceof FirExpressionStub)) {
            return null;
        }
        return expression;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(Candidate.class, other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        return Intrinsics.areEqual(getSymbol(), ((Candidate) other).getSymbol());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCandidate
    /* JADX INFO: renamed from: getApplicability, reason: from getter */
    public CandidateApplicability getLowestApplicability() {
        return this.lowestApplicability;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate
    public LinkedHashMap<ConeResolutionAtom, FirValueParameter> getArgumentMapping() {
        LinkedHashMap<ConeResolutionAtom, FirValueParameter> linkedHashMap = this._argumentMapping;
        if (linkedHashMap != null) {
            return linkedHashMap;
        }
        k2d.a("Argument mapping is not initialized yet");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate
    public boolean getArgumentMappingInitialized() {
        return this._argumentMapping != null;
    }

    public final Map<FirElement, FirExpression> getArgumentReplacements() {
        return this._updatedArguments;
    }

    public final List<ConeResolutionAtom> getArguments() {
        List list = this._arguments;
        if (list != null) {
            return list;
        }
        k2d.a("Argument list is not initialized yet");
        return null;
    }

    public final Map<FirExpression, FunctionConversionDescription> getArgumentsWithFunctionKindConversion() {
        return this.argumentsWithFunctionKindConversion;
    }

    /* JADX INFO: renamed from: getCallableReferenceAdaptation$org_jetbrains_kotlin_resolve, reason: from getter */
    public final CallableReferenceAdaptation getCallableReferenceAdaptation() {
        return this.callableReferenceAdaptation;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate
    public List<ConeResolutionAtom> getContextArguments() {
        return this.contextArguments;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate
    public List<ResolutionDiagnostic> getDiagnostics() {
        return this.diagnostics;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate
    public List<ConstraintSystemError> getErrors() {
        return getSystem().getErrors();
    }

    public final Integer getExpectedContextParameterCountForInvoke() {
        return this.expectedContextParameterCountForInvoke;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate
    public ExplicitReceiverKind getExplicitReceiverKind() {
        return this.explicitReceiverKind;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final List<ConeTypeVariable> getFreshVariables() throws UninitializedPropertyAccessException {
        List list = this.freshVariables;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("freshVariables");
        return null;
    }

    public final ConeResolutionAtom getGivenExtensionReceiver() {
        return this.givenExtensionReceiver;
    }

    public final boolean getHasVisibleBackingField() {
        return this.hasVisibleBackingField;
    }

    public final List<FirAnonymousFunction> getLambdasAnalyzedWithPCLA() {
        return this.lambdasAnalyzedWithPCLA;
    }

    public final CandidateApplicability getLowestApplicability() {
        return this.lowestApplicability;
    }

    public final int getNumDefaults() {
        return this.numDefaults;
    }

    public final List<Function1<ConeSubstitutor, Unit>> getOnPCLACompletionResultsWritingCallbacks() {
        return this.onPCLACompletionResultsWritingCallbacks;
    }

    public final FirScope getOriginScope() {
        return this.originScope;
    }

    public final int getPassedStages() {
        return this.passedStages;
    }

    public final List<ConePostponedResolvedAtom> getPostponedAtoms() {
        return this.postponedAtoms;
    }

    public final List<ConeResolutionAtom> getPostponedPCLACalls() {
        return this.postponedPCLACalls;
    }

    public final ConeKotlinType getResultingTypeForCallableReference() {
        return this.resultingTypeForCallableReference;
    }

    public final HashMap<FirExpression, FirSamResolver.SamConversionInfo> getSamConversionInfosOfArguments() {
        return this.samConversionInfosOfArguments;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final ConeSubstitutor getSubstitutor() throws UninitializedPropertyAccessException {
        ConeSubstitutor coneSubstitutor = this.substitutor;
        if (coneSubstitutor != null) {
            return coneSubstitutor;
        }
        Intrinsics.throwUninitializedPropertyAccessException("substitutor");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCandidate
    public FirBasedSymbol<?> getSymbol() {
        return this.symbol;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate
    public NewConstraintSystemImpl getSystem() {
        return (NewConstraintSystemImpl) this.system.getValue();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final TypeArgumentMapping getTypeArgumentMapping() throws UninitializedPropertyAccessException {
        TypeArgumentMapping typeArgumentMapping = this.typeArgumentMapping;
        if (typeArgumentMapping != null) {
            return typeArgumentMapping;
        }
        Intrinsics.throwUninitializedPropertyAccessException("typeArgumentMapping");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate
    public boolean getUsedOuterCs() {
        return getSystem().getUsesOuterCs();
    }

    public final boolean getUsesCoercionToUnitInLambda() {
        return this.usesCoercionToUnitInLambda;
    }

    public final boolean getUsesFunctionKindConversion() {
        CallableReferenceAdaptation callableReferenceAdaptation;
        return this.argumentsWithFunctionKindConversion != null || ((callableReferenceAdaptation = this.callableReferenceAdaptation) != null && callableReferenceAdaptation.hasFunctionKindConversion());
    }

    public final boolean getUsesSamConversion() {
        return this.samConversionInfosOfArguments != null;
    }

    public final boolean getUsesSamConversionOrSamConstructor() {
        return getUsesSamConversion() || Intrinsics.areEqual(getSymbol().getOrigin(), FirDeclarationOrigin.SamConstructor.INSTANCE);
    }

    public final boolean getWasExpectedTypeAddedAsEqualityForSyntheticCall() {
        return this.wasExpectedTypeAddedAsEqualityForSyntheticCall;
    }

    public int hashCode() {
        return getSymbol().hashCode();
    }

    public final void initializeArgumentMapping(List<? extends ConeResolutionAtom> arguments, LinkedHashMap<ConeResolutionAtom, FirValueParameter> argumentMapping) {
        arguments.getClass();
        argumentMapping.getClass();
        if (this._argumentMapping != null) {
            w01.a("Argument mapping already initialized");
        } else {
            this._argumentMapping = argumentMapping;
            this._arguments = arguments;
        }
    }

    public final void initializeCallableReferenceAdaptation$org_jetbrains_kotlin_resolve(CallableReferenceAdaptation callableReferenceAdaptation, ConeKotlinType resultingTypeForCallableReference) {
        resultingTypeForCallableReference.getClass();
        if (this.callableReferenceAdaptation != null) {
            w01.a("callableReferenceAdaptation already initialized");
            return;
        }
        this.callableReferenceAdaptation = callableReferenceAdaptation;
        this.resultingTypeForCallableReference = resultingTypeForCallableReference;
        if (callableReferenceAdaptation != null) {
            this.numDefaults = callableReferenceAdaptation.getDefaults();
        }
    }

    public final void initializeSubstitutorAndVariables(ConeSubstitutor substitutor, List<? extends ConeTypeVariable> freshVariables) {
        substitutor.getClass();
        freshVariables.getClass();
        this.substitutor = substitutor;
        this.freshVariables = freshVariables;
    }

    /* JADX INFO: renamed from: isFromCompanionObjectTypeScope, reason: from getter */
    public final boolean getIsFromCompanionObjectTypeScope() {
        return this.isFromCompanionObjectTypeScope;
    }

    /* JADX INFO: renamed from: isFromOriginalTypeInPresenceOfSmartCast, reason: from getter */
    public final boolean getIsFromOriginalTypeInPresenceOfSmartCast() {
        return this.isFromOriginalTypeInPresenceOfSmartCast;
    }

    public final boolean isSuccessful() {
        if (ResolutionDiagnosticKt.getAllSuccessful(getDiagnostics())) {
            return (this.systemInitialized && getSystem().getHasContradiction()) ? false : true;
        }
        return false;
    }

    public final void markWasExpectedTypeAddedAsEqualityForSyntheticCall() {
        this.wasExpectedTypeAddedAsEqualityForSyntheticCall = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @UpdatingCandidateInvariants
    public final void replaceArgumentPrefix(List<? extends ConeResolutionAtom> newArgumentPrefix) {
        newArgumentPrefix.getClass();
        List<ConeResolutionAtom> listSubList = getArguments().subList(newArgumentPrefix.size(), getArguments().size());
        LinkedHashMap<ConeResolutionAtom, FirValueParameter> linkedHashMap = new LinkedHashMap<>();
        for (Pair pair : CollectionsKt.zip(getArguments(), newArgumentPrefix)) {
            linkedHashMap.put((ConeResolutionAtom) pair.component2(), MapsKt.getValue(getArgumentMapping(), (ConeResolutionAtom) pair.component1()));
        }
        for (ConeResolutionAtom coneResolutionAtom : listSubList) {
            FirValueParameter firValueParameter = getArgumentMapping().get(coneResolutionAtom);
            if (firValueParameter != null) {
                linkedHashMap.put(coneResolutionAtom, firValueParameter);
            }
        }
        this._arguments = CollectionsKt.plus(newArgumentPrefix, listSubList);
        this._argumentMapping = linkedHashMap;
    }

    public void setChosenExtensionReceiver(ConeResolutionAtom coneResolutionAtom) {
        this.chosenExtensionReceiver = coneResolutionAtom;
    }

    public void setContextArguments(List<? extends ConeResolutionAtom> list) {
        this.contextArguments = list;
    }

    public void setDispatchReceiver(ConeResolutionAtom coneResolutionAtom) {
        this.dispatchReceiver = coneResolutionAtom;
    }

    public final void setExpectedContextParameterCountForInvoke(Integer num) {
        this.expectedContextParameterCountForInvoke = num;
    }

    public final void setHasVisibleBackingField(boolean z) {
        this.hasVisibleBackingField = z;
    }

    public final void setNumDefaults(int i) {
        this.numDefaults = i;
    }

    public final void setPassedStages(int i) {
        this.passedStages = i;
    }

    public final void setSamConversionOfArgument(FirExpression expression, FirSamResolver.SamConversionInfo conversionInfo) {
        expression.getClass();
        conversionInfo.getClass();
        HashMap<FirExpression, FirSamResolver.SamConversionInfo> map = this.samConversionInfosOfArguments;
        if (map == null) {
            map = new HashMap<>();
            this.samConversionInfosOfArguments = map;
        }
        map.put(expression, conversionInfo);
    }

    public final void setTypeArgumentMapping(TypeArgumentMapping typeArgumentMapping) {
        typeArgumentMapping.getClass();
        this.typeArgumentMapping = typeArgumentMapping;
    }

    public final void setUpdatedArgumentFromContextSensitiveResolution(FirPropertyAccessExpression old, FirExpression firExpression) {
        old.getClass();
        firExpression.getClass();
        setUpdatedArgument(old, firExpression);
    }

    public final void setUpdatedCollectionLiteral(FirCollectionLiteral old, FirExpression firExpression) {
        old.getClass();
        firExpression.getClass();
        setUpdatedArgument(old, firExpression);
    }

    public final void setUsesCoercionToUnitInLambda(boolean z) {
        this.usesCoercionToUnitInLambda = z;
    }

    public String toString() {
        String str = isSuccessful() ? "OK" : "FAIL";
        StringBuilder sb = new StringBuilder();
        sb.append(this.passedStages);
        sb.append('/');
        sb.append(getCallInfo().getCallKind().getResolutionSequence().length);
        return str + '(' + sb.toString() + "): " + getSymbol();
    }

    @UpdatingCandidateInvariants
    public final void updateArgumentMapping(LinkedHashMap<ConeResolutionAtom, FirValueParameter> argumentMapping) {
        argumentMapping.getClass();
        this._argumentMapping = argumentMapping;
    }

    public final void updateSourcesOfReceivers() {
        if (this.sourcesWereUpdated) {
            w01.a("Failed requirement.");
            return;
        }
        this.sourcesWereUpdated = true;
        ConeResolutionAtom dispatchReceiver = getDispatchReceiver();
        ArrayList arrayList = null;
        setDispatchReceiver(dispatchReceiver != null ? tryToSetSourceForImplicitReceiver(dispatchReceiver) : null);
        ConeResolutionAtom chosenExtensionReceiver = getChosenExtensionReceiver();
        setChosenExtensionReceiver(chosenExtensionReceiver != null ? tryToSetSourceForImplicitReceiver(chosenExtensionReceiver) : null);
        List<ConeResolutionAtom> contextArguments = getContextArguments();
        if (contextArguments != null) {
            List<ConeResolutionAtom> list = contextArguments;
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(tryToSetSourceForImplicitReceiver((ConeResolutionAtom) it.next()));
            }
        }
        setContextArguments(arrayList);
    }

    @UpdatingCandidateInvariants
    public final void updateSubstitutor(ConeSubstitutor substitutor) {
        substitutor.getClass();
        this.substitutor = substitutor;
    }

    @UpdatingCandidateInvariants
    public final void updateSymbol(FirBasedSymbol<?> symbol) {
        symbol.getClass();
        this.symbol = symbol;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate
    public CallInfo getCallInfo() {
        return this.callInfo;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate
    public ConeResolutionAtom getChosenExtensionReceiver() {
        return this.chosenExtensionReceiver;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate
    public ConeResolutionAtom getDispatchReceiver() {
        return this.dispatchReceiver;
    }

    public /* synthetic */ Candidate(FirBasedSymbol firBasedSymbol, ConeResolutionAtom coneResolutionAtom, ConeResolutionAtom coneResolutionAtom2, ExplicitReceiverKind explicitReceiverKind, InferenceComponents.ConstraintSystemFactory constraintSystemFactory, ConstraintStorage constraintStorage, CallInfo callInfo, FirScope firScope, boolean z, boolean z2, BodyResolveContext bodyResolveContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firBasedSymbol, coneResolutionAtom, coneResolutionAtom2, explicitReceiverKind, constraintSystemFactory, constraintStorage, callInfo, firScope, (i & 256) != 0 ? false : z, (i & 512) != 0 ? false : z2, bodyResolveContext);
    }
}
