package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirComparisonExpression;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirElvisExpression;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirJump;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirStringConcatenationCall;
import org.jetbrains.kotlin.fir.expressions.FirThrowExpression;
import org.jetbrains.kotlin.fir.expressions.FirTryExpression;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ò\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0002\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\u000b\u001a\u00020\f*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\r\u001a\u0012\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\r\u001a\u0012\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0012\u001a\u0012\u0010\u0013\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0015\u001a\u0012\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0018\u001a\u0016\u0010\u0019\u001a\u00020\u001a*\u00020\u00022\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u001b\u001a\u0012\u0010\u001c\u001a\u00020\u001d*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u001e\u001a\u0012\u0010\u001f\u001a\u00020 *\u00020\u00022\u0006\u0010\u0007\u001a\u00020!\u001a\u0012\u0010\"\u001a\u00020#*\u00020\u00022\u0006\u0010\u0007\u001a\u00020$\u001a\u0012\u0010%\u001a\u00020&*\u00020\u00022\u0006\u0010\u0007\u001a\u00020'\u001a\u0012\u0010(\u001a\u00020)*\u00020\u00022\u0006\u0010\u0007\u001a\u00020'\u001a\u0012\u0010*\u001a\u00020+*\u00020\u00022\u0006\u0010\u0007\u001a\u00020,\u001a\u0012\u0010-\u001a\u00020.*\u00020\u00022\u0006\u0010\u0007\u001a\u00020,\u001a\u0012\u0010/\u001a\u000200*\u00020\u00022\u0006\u0010\u0007\u001a\u000201\u001a\u0012\u00102\u001a\u000203*\u00020\u00022\u0006\u0010\u0007\u001a\u000204\u001a\u0012\u00105\u001a\u000206*\u00020\u00022\u0006\u0010\u0007\u001a\u000204\u001a\u0012\u00107\u001a\u000208*\u00020\u00022\u0006\u0010\u0007\u001a\u000209\u001a\u0012\u0010:\u001a\u00020;*\u00020\u00022\u0006\u0010\u0007\u001a\u000209\u001a\u0012\u0010<\u001a\u00020=*\u00020\u00022\u0006\u0010\u0007\u001a\u000209\u001a*\u0010>\u001a\u00020?*\u00020\u00022\u0006\u0010\u0007\u001a\u00020@2\n\u0010A\u001a\u0006\u0012\u0002\b\u00030B2\n\u0010C\u001a\u0006\u0012\u0002\b\u00030B\u001a\u0012\u0010D\u001a\u00020E*\u00020\u00022\u0006\u0010\u0007\u001a\u00020@\u001a\u0012\u0010F\u001a\u00020G*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0018\u001a\u0012\u0010H\u001a\u00020I*\u00020\u00022\u0006\u0010\u0007\u001a\u00020J\u001a\u0012\u0010K\u001a\u00020L*\u00020\u00022\u0006\u0010\u0007\u001a\u00020J\u001a\u0012\u0010M\u001a\u00020N*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0018\u001a\u0012\u0010O\u001a\u00020P*\u00020\u00022\u0006\u0010\u0007\u001a\u00020J\u001a\u0012\u0010Q\u001a\u00020R*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0018\u001a\u001a\u0010S\u001a\u00020T*\u00020\u00022\u0006\u0010\u0007\u001a\u0002012\u0006\u0010U\u001a\u00020\b\u001a\u001a\u0010V\u001a\u00020W*\u00020\u00022\u0006\u0010\u0007\u001a\u0002012\u0006\u0010U\u001a\u00020\b\u001a\u0012\u0010X\u001a\u00020Y*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010Z\u001a\u00020[*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\\\u001a\u00020]*\u00020\u00022\u0006\u0010\u0007\u001a\u00020^\u001a\u001e\u0010_\u001a\u00020`*\u00020\u00022\u0006\u0010\u0007\u001a\u00020^2\n\u0010a\u001a\u0006\u0012\u0002\b\u00030B\u001a\u0012\u0010b\u001a\u00020c*\u00020\u00022\u0006\u0010\u0007\u001a\u00020^\u001a\u0012\u0010d\u001a\u00020e*\u00020\u00022\u0006\u0010\u0007\u001a\u00020^\u001a\u0012\u0010f\u001a\u00020g*\u00020\u00022\u0006\u0010\u0007\u001a\u00020h\u001a\u0012\u0010i\u001a\u00020j*\u00020\u00022\u0006\u0010\u0007\u001a\u00020k\u001a\u0012\u0010l\u001a\u00020m*\u00020\u00022\u0006\u0010\u0007\u001a\u00020n\u001a\u0012\u0010o\u001a\u00020p*\u00020\u00022\u0006\u0010\u0007\u001a\u00020q\u001a\u0012\u0010r\u001a\u00020s*\u00020\u00022\u0006\u0010\u0007\u001a\u00020t\u001a\u0012\u0010u\u001a\u00020v*\u00020\u00022\u0006\u0010\u0007\u001a\u00020w\u001a\u0012\u0010x\u001a\u00020y*\u00020\u00022\u0006\u0010\u0007\u001a\u00020w\u001a\u0012\u0010z\u001a\u00020{*\u00020\u00022\u0006\u0010\u0007\u001a\u00020w\u001a\u0012\u0010|\u001a\u00020}*\u00020\u00022\u0006\u0010\u0007\u001a\u00020~\u001a\u0013\u0010\u007f\u001a\u00030\u0080\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020w\u001a\u0014\u0010\u0081\u0001\u001a\u00030\u0082\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020,\u001a\u0014\u0010\u0083\u0001\u001a\u00030\u0084\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020,\u001a\u0015\u0010\u0085\u0001\u001a\u00030\u0086\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030\u0087\u0001\u001a\u0015\u0010\u0088\u0001\u001a\u00030\u0089\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030\u008a\u0001\u001a\u0016\u0010\u008b\u0001\u001a\u00030\u008c\u0001*\u00020\u00022\b\u0010\u008d\u0001\u001a\u00030\u008e\u0001\u001a\u0015\u0010\u008f\u0001\u001a\u00030\u008e\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030\u0090\u0001\u001a\u0015\u0010\u0091\u0001\u001a\u00030\u0092\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030\u0093\u0001\u001a\u0015\u0010\u0094\u0001\u001a\u00030\u0095\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030\u0090\u0001\u001a\u0015\u0010\u0096\u0001\u001a\u00030\u0097\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030\u0090\u0001\u001a\u0015\u0010\u0098\u0001\u001a\u00030\u0099\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030\u0093\u0001\u001a\u0015\u0010\u009a\u0001\u001a\u00030\u009b\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030\u0090\u0001\u001a\u0015\u0010\u009c\u0001\u001a\u00030\u009d\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030\u0090\u0001\u001a\u0014\u0010\u009e\u0001\u001a\u00030\u009f\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020@\u001a\u0014\u0010 \u0001\u001a\u00030¡\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020@\u001a\u0015\u0010¢\u0001\u001a\u00030£\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030¤\u0001\u001a\u0015\u0010¥\u0001\u001a\u00030¦\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030¤\u0001\u001a\u0015\u0010§\u0001\u001a\u00030¨\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030©\u0001\u001a&\u0010ª\u0001\u001a\u00030«\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030¬\u00012\u000f\u0010\u00ad\u0001\u001a\n\u0012\u0005\u0012\u00030¯\u00010®\u0001\u001a\u0015\u0010°\u0001\u001a\u00030±\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030²\u0001\u001a\u0015\u0010³\u0001\u001a\u00030´\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030©\u0001\u001a\u0015\u0010µ\u0001\u001a\u00030¶\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030©\u0001\u001a\u0015\u0010·\u0001\u001a\u00030¸\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030¹\u0001\u001a\u0015\u0010º\u0001\u001a\u00030»\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030¼\u0001\u001a\u0015\u0010½\u0001\u001a\u00030¾\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030¿\u0001\u001a\u0015\u0010À\u0001\u001a\u00030Á\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030¿\u0001\u001a\u0015\u0010Â\u0001\u001a\u00030Ã\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030Ä\u0001\u001a\u0015\u0010Å\u0001\u001a\u00030Æ\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030Ä\u0001\u001a\u0015\u0010Ç\u0001\u001a\u00030È\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030É\u0001\u001a\u0015\u0010Ê\u0001\u001a\u00030Ë\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030É\u0001\u001a\u0015\u0010Ì\u0001\u001a\u00030Í\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030Î\u0001\u001a\u0015\u0010Ï\u0001\u001a\u00030Ð\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030Î\u0001\u001a\u0015\u0010Ñ\u0001\u001a\u00030Ò\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030Ó\u0001\u001a\u0015\u0010Ô\u0001\u001a\u00030Õ\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030Ö\u0001\u001a\u0015\u0010×\u0001\u001a\u00030Ø\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030Ö\u0001\u001a\u0015\u0010Ù\u0001\u001a\u00030Ú\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030Ö\u0001\u001a\u0015\u0010Û\u0001\u001a\u00030Ü\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030Ö\u0001\u001a\u0015\u0010Ý\u0001\u001a\u00030Þ\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030ß\u0001\u001a\u0015\u0010à\u0001\u001a\u00030á\u0001*\u00020\u00022\u0007\u0010\u0007\u001a\u00030â\u0001¨\u0006ã\u0001"}, d2 = {"createStubNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StubNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphBuilder;", "createFakeExpressionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FakeExpressionEnterNode;", "createLoopExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopExitNode;", "fir", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "createLoopEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopEnterNode;", "createInitBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockEnterNode;", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "createInitBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockExitNode;", "createTypeOperatorCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TypeOperatorCallNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "createEqualityOperatorCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EqualityOperatorCallNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "createWhenBranchConditionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionExitNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenBranch;", "createJumpNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/JumpNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirJump;", "createCheckNotNullCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CheckNotNullCallNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "createQualifiedAccessNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/QualifiedAccessNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "createResolvedQualifierNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ResolvedQualifierNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "createBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BlockEnterNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "createBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BlockExitNode;", "createPropertyInitializerExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerExitNode;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "createPropertyInitializerEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerEnterNode;", "createDelegateExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegateExpressionExitNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "createFieldInitializerExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FieldInitializerExitNode;", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "createFieldInitializerEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FieldInitializerEnterNode;", "createFunctionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionEnterNode;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "createFunctionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionExitNode;", "createLocalFunctionDeclarationNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LocalFunctionDeclarationNode;", "createBooleanOperatorExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "leftOperandNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "rightOperandNode", "createBooleanOperatorEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorEnterNode;", "createWhenBranchConditionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionEnterNode;", "createWhenEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenEnterNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "createWhenExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenExitNode;", "createWhenBranchResultExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultExitNode;", "createWhenSyntheticElseBranchNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenSyntheticElseBranchNode;", "createWhenBranchResultEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultEnterNode;", "createLoopConditionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionExitNode;", "loop", "createLoopConditionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionEnterNode;", "createLoopBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockEnterNode;", "createLoopBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockExitNode;", "createFunctionCallArgumentsEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsEnterNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "createFunctionCallArgumentsExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsExitNode;", "explicitReceiverExitNode", "createFunctionCallEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallEnterNode;", "createFunctionCallExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallExitNode;", "createCallableReferenceNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CallableReferenceNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "createGetClassCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/GetClassCallNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "createDelegatedConstructorCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegatedConstructorCallNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "createStringConcatenationCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StringConcatenationCallNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "createVariableAssignmentNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "createElvisLhsIsNotNullNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsIsNotNullNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "createElvisRhsEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisRhsEnterNode;", "createElvisLhsExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsExitNode;", "createWhenSubjectExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenSubjectExpressionExitNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;", "createElvisExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisExitNode;", "createVariableDeclarationEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationEnterNode;", "createVariableDeclarationExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationExitNode;", "createLiteralExpressionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LiteralExpressionNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "createThrowExceptionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ThrowExceptionNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "createFinallyBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockExitNode;", "enterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockEnterNode;", "createFinallyBlockEnterNode", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "createCatchClauseExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseExitNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirCatch;", "createTryMainBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockExitNode;", "createTryMainBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockEnterNode;", "createCatchClauseEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseEnterNode;", "createTryExpressionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionEnterNode;", "createTryExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionExitNode;", "createBooleanOperatorExitLeftOperandNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitLeftOperandNode;", "createBooleanOperatorEnterRightOperandNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorEnterRightOperandNode;", "createExitSafeCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitSafeCallNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "createEnterSafeCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterSafeCallNode;", "createPostponedLambdaExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PostponedLambdaExitNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "createSplitPostponedLambdasNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/SplitPostponedLambdasNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "lambdas", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "createMergePostponedLambdaExitsNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/MergePostponedLambdaExitsNode;", "Lorg/jetbrains/kotlin/fir/FirElement;", "createAnonymousFunctionCaptureNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionCaptureNode;", "createAnonymousFunctionExpressionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionExpressionNode;", "createAnonymousObjectEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousObjectEnterNode;", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "createAnonymousObjectExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousObjectExpressionExitNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousObjectExpression;", "createScriptEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ScriptEnterNode;", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "createScriptExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ScriptExitNode;", "createCodeFragmentEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CodeFragmentEnterNode;", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "createCodeFragmentExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CodeFragmentExitNode;", "createFileEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FileEnterNode;", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "createFileExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FileExitNode;", "createClassEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ClassEnterNode;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "createClassExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ClassExitNode;", "createLocalClassExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LocalClassExitNode;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "createEnterValueParameterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterValueParameterNode;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "createEnterDefaultArgumentsNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterDefaultArgumentsNode;", "createExitDefaultArgumentsNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitDefaultArgumentsNode;", "createExitValueParameterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitValueParameterNode;", "createComparisonExpressionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ComparisonExpressionNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "createSmartCastExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/SmartCastExpressionExitNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ControlFlowGraphNodeBuilderKt {
    public static final AnonymousFunctionCaptureNode createAnonymousFunctionCaptureNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirAnonymousFunctionExpression firAnonymousFunctionExpression) {
        controlFlowGraphBuilder.getClass();
        firAnonymousFunctionExpression.getClass();
        return new AnonymousFunctionCaptureNode(controlFlowGraphBuilder.getCurrentGraph(), firAnonymousFunctionExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final AnonymousFunctionExpressionNode createAnonymousFunctionExpressionNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirAnonymousFunctionExpression firAnonymousFunctionExpression) {
        controlFlowGraphBuilder.getClass();
        firAnonymousFunctionExpression.getClass();
        return new AnonymousFunctionExpressionNode(controlFlowGraphBuilder.getCurrentGraph(), firAnonymousFunctionExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final AnonymousObjectEnterNode createAnonymousObjectEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirAnonymousObject firAnonymousObject) {
        controlFlowGraphBuilder.getClass();
        firAnonymousObject.getClass();
        return new AnonymousObjectEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firAnonymousObject, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final AnonymousObjectExpressionExitNode createAnonymousObjectExpressionExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirAnonymousObjectExpression firAnonymousObjectExpression) {
        controlFlowGraphBuilder.getClass();
        firAnonymousObjectExpression.getClass();
        return new AnonymousObjectExpressionExitNode(controlFlowGraphBuilder.getCurrentGraph(), firAnonymousObjectExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final BlockEnterNode createBlockEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirBlock firBlock) {
        controlFlowGraphBuilder.getClass();
        firBlock.getClass();
        return new BlockEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firBlock, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final BlockExitNode createBlockExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirBlock firBlock) {
        controlFlowGraphBuilder.getClass();
        firBlock.getClass();
        return new BlockExitNode(controlFlowGraphBuilder.getCurrentGraph(), firBlock, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final BooleanOperatorEnterNode createBooleanOperatorEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirBooleanOperatorExpression firBooleanOperatorExpression) {
        controlFlowGraphBuilder.getClass();
        firBooleanOperatorExpression.getClass();
        return new BooleanOperatorEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firBooleanOperatorExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final BooleanOperatorEnterRightOperandNode createBooleanOperatorEnterRightOperandNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirBooleanOperatorExpression firBooleanOperatorExpression) {
        controlFlowGraphBuilder.getClass();
        firBooleanOperatorExpression.getClass();
        return new BooleanOperatorEnterRightOperandNode(controlFlowGraphBuilder.getCurrentGraph(), firBooleanOperatorExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final BooleanOperatorExitLeftOperandNode createBooleanOperatorExitLeftOperandNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirBooleanOperatorExpression firBooleanOperatorExpression) {
        controlFlowGraphBuilder.getClass();
        firBooleanOperatorExpression.getClass();
        return new BooleanOperatorExitLeftOperandNode(controlFlowGraphBuilder.getCurrentGraph(), firBooleanOperatorExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final BooleanOperatorExitNode createBooleanOperatorExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirBooleanOperatorExpression firBooleanOperatorExpression, CFGNode<?> cFGNode, CFGNode<?> cFGNode2) {
        controlFlowGraphBuilder.getClass();
        firBooleanOperatorExpression.getClass();
        cFGNode.getClass();
        cFGNode2.getClass();
        return new BooleanOperatorExitNode(controlFlowGraphBuilder.getCurrentGraph(), firBooleanOperatorExpression, cFGNode, cFGNode2, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final CallableReferenceNode createCallableReferenceNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirCallableReferenceAccess firCallableReferenceAccess) {
        controlFlowGraphBuilder.getClass();
        firCallableReferenceAccess.getClass();
        return new CallableReferenceNode(controlFlowGraphBuilder.getCurrentGraph(), firCallableReferenceAccess, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final CatchClauseEnterNode createCatchClauseEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirCatch firCatch) {
        controlFlowGraphBuilder.getClass();
        firCatch.getClass();
        return new CatchClauseEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firCatch, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final CatchClauseExitNode createCatchClauseExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirCatch firCatch) {
        controlFlowGraphBuilder.getClass();
        firCatch.getClass();
        return new CatchClauseExitNode(controlFlowGraphBuilder.getCurrentGraph(), firCatch, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final CheckNotNullCallNode createCheckNotNullCallNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirCheckNotNullCall firCheckNotNullCall) {
        controlFlowGraphBuilder.getClass();
        firCheckNotNullCall.getClass();
        return new CheckNotNullCallNode(controlFlowGraphBuilder.getCurrentGraph(), firCheckNotNullCall, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final ClassEnterNode createClassEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirClass firClass) {
        controlFlowGraphBuilder.getClass();
        firClass.getClass();
        return new ClassEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firClass, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final ClassExitNode createClassExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirClass firClass) {
        controlFlowGraphBuilder.getClass();
        firClass.getClass();
        return new ClassExitNode(controlFlowGraphBuilder.getCurrentGraph(), firClass, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final CodeFragmentEnterNode createCodeFragmentEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirCodeFragment firCodeFragment) {
        controlFlowGraphBuilder.getClass();
        firCodeFragment.getClass();
        return new CodeFragmentEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firCodeFragment, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final CodeFragmentExitNode createCodeFragmentExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirCodeFragment firCodeFragment) {
        controlFlowGraphBuilder.getClass();
        firCodeFragment.getClass();
        return new CodeFragmentExitNode(controlFlowGraphBuilder.getCurrentGraph(), firCodeFragment, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final ComparisonExpressionNode createComparisonExpressionNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirComparisonExpression firComparisonExpression) {
        controlFlowGraphBuilder.getClass();
        firComparisonExpression.getClass();
        return new ComparisonExpressionNode(controlFlowGraphBuilder.getCurrentGraph(), firComparisonExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final DelegateExpressionExitNode createDelegateExpressionExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirExpression firExpression) {
        controlFlowGraphBuilder.getClass();
        firExpression.getClass();
        return new DelegateExpressionExitNode(controlFlowGraphBuilder.getCurrentGraph(), firExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final DelegatedConstructorCallNode createDelegatedConstructorCallNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirDelegatedConstructorCall firDelegatedConstructorCall) {
        controlFlowGraphBuilder.getClass();
        firDelegatedConstructorCall.getClass();
        return new DelegatedConstructorCallNode(controlFlowGraphBuilder.getCurrentGraph(), firDelegatedConstructorCall, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final ElvisExitNode createElvisExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirElvisExpression firElvisExpression) {
        controlFlowGraphBuilder.getClass();
        firElvisExpression.getClass();
        return new ElvisExitNode(controlFlowGraphBuilder.getCurrentGraph(), firElvisExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final ElvisLhsExitNode createElvisLhsExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirElvisExpression firElvisExpression) {
        controlFlowGraphBuilder.getClass();
        firElvisExpression.getClass();
        return new ElvisLhsExitNode(controlFlowGraphBuilder.getCurrentGraph(), firElvisExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final ElvisLhsIsNotNullNode createElvisLhsIsNotNullNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirElvisExpression firElvisExpression) {
        controlFlowGraphBuilder.getClass();
        firElvisExpression.getClass();
        return new ElvisLhsIsNotNullNode(controlFlowGraphBuilder.getCurrentGraph(), firElvisExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final ElvisRhsEnterNode createElvisRhsEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirElvisExpression firElvisExpression) {
        controlFlowGraphBuilder.getClass();
        firElvisExpression.getClass();
        return new ElvisRhsEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firElvisExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final EnterDefaultArgumentsNode createEnterDefaultArgumentsNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirValueParameter firValueParameter) {
        controlFlowGraphBuilder.getClass();
        firValueParameter.getClass();
        return new EnterDefaultArgumentsNode(controlFlowGraphBuilder.getCurrentGraph(), firValueParameter, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final EnterSafeCallNode createEnterSafeCallNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirSafeCallExpression firSafeCallExpression) {
        controlFlowGraphBuilder.getClass();
        firSafeCallExpression.getClass();
        return new EnterSafeCallNode(controlFlowGraphBuilder.getCurrentGraph(), firSafeCallExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final EnterValueParameterNode createEnterValueParameterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirValueParameter firValueParameter) {
        controlFlowGraphBuilder.getClass();
        firValueParameter.getClass();
        return new EnterValueParameterNode(controlFlowGraphBuilder.getCurrentGraph(), firValueParameter, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final EqualityOperatorCallNode createEqualityOperatorCallNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirEqualityOperatorCall firEqualityOperatorCall) {
        controlFlowGraphBuilder.getClass();
        firEqualityOperatorCall.getClass();
        return new EqualityOperatorCallNode(controlFlowGraphBuilder.getCurrentGraph(), firEqualityOperatorCall, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final ExitDefaultArgumentsNode createExitDefaultArgumentsNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirValueParameter firValueParameter) {
        controlFlowGraphBuilder.getClass();
        firValueParameter.getClass();
        return new ExitDefaultArgumentsNode(controlFlowGraphBuilder.getCurrentGraph(), firValueParameter, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final ExitSafeCallNode createExitSafeCallNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirSafeCallExpression firSafeCallExpression) {
        controlFlowGraphBuilder.getClass();
        firSafeCallExpression.getClass();
        return new ExitSafeCallNode(controlFlowGraphBuilder.getCurrentGraph(), firSafeCallExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final ExitValueParameterNode createExitValueParameterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirValueParameter firValueParameter) {
        controlFlowGraphBuilder.getClass();
        firValueParameter.getClass();
        return new ExitValueParameterNode(controlFlowGraphBuilder.getCurrentGraph(), firValueParameter, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final FakeExpressionEnterNode createFakeExpressionEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder) {
        controlFlowGraphBuilder.getClass();
        return new FakeExpressionEnterNode(controlFlowGraphBuilder.getCurrentGraph(), controlFlowGraphBuilder.getLevelCounter());
    }

    public static final FieldInitializerEnterNode createFieldInitializerEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirField firField) {
        controlFlowGraphBuilder.getClass();
        firField.getClass();
        return new FieldInitializerEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firField, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final FieldInitializerExitNode createFieldInitializerExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirField firField) {
        controlFlowGraphBuilder.getClass();
        firField.getClass();
        return new FieldInitializerExitNode(controlFlowGraphBuilder.getCurrentGraph(), firField, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final FileEnterNode createFileEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirFile firFile) {
        controlFlowGraphBuilder.getClass();
        firFile.getClass();
        return new FileEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firFile, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final FileExitNode createFileExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirFile firFile) {
        controlFlowGraphBuilder.getClass();
        firFile.getClass();
        return new FileExitNode(controlFlowGraphBuilder.getCurrentGraph(), firFile, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final FinallyBlockEnterNode createFinallyBlockEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirTryExpression firTryExpression) {
        controlFlowGraphBuilder.getClass();
        firTryExpression.getClass();
        return new FinallyBlockEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firTryExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final FinallyBlockExitNode createFinallyBlockExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FinallyBlockEnterNode finallyBlockEnterNode) {
        controlFlowGraphBuilder.getClass();
        finallyBlockEnterNode.getClass();
        return new FinallyBlockExitNode(controlFlowGraphBuilder.getCurrentGraph(), finallyBlockEnterNode.getFir(), finallyBlockEnterNode, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final FunctionCallArgumentsEnterNode createFunctionCallArgumentsEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirFunctionCall firFunctionCall) {
        controlFlowGraphBuilder.getClass();
        firFunctionCall.getClass();
        return new FunctionCallArgumentsEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firFunctionCall, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final FunctionCallArgumentsExitNode createFunctionCallArgumentsExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirFunctionCall firFunctionCall, CFGNode<?> cFGNode) {
        controlFlowGraphBuilder.getClass();
        firFunctionCall.getClass();
        cFGNode.getClass();
        return new FunctionCallArgumentsExitNode(controlFlowGraphBuilder.getCurrentGraph(), firFunctionCall, cFGNode, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final FunctionCallEnterNode createFunctionCallEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirFunctionCall firFunctionCall) {
        controlFlowGraphBuilder.getClass();
        firFunctionCall.getClass();
        return new FunctionCallEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firFunctionCall, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final FunctionCallExitNode createFunctionCallExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirFunctionCall firFunctionCall) {
        controlFlowGraphBuilder.getClass();
        firFunctionCall.getClass();
        return new FunctionCallExitNode(controlFlowGraphBuilder.getCurrentGraph(), firFunctionCall, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final FunctionEnterNode createFunctionEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirFunction firFunction) {
        controlFlowGraphBuilder.getClass();
        firFunction.getClass();
        return new FunctionEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firFunction, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final FunctionExitNode createFunctionExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirFunction firFunction) {
        controlFlowGraphBuilder.getClass();
        firFunction.getClass();
        return new FunctionExitNode(controlFlowGraphBuilder.getCurrentGraph(), firFunction, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final GetClassCallNode createGetClassCallNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirGetClassCall firGetClassCall) {
        controlFlowGraphBuilder.getClass();
        firGetClassCall.getClass();
        return new GetClassCallNode(controlFlowGraphBuilder.getCurrentGraph(), firGetClassCall, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final InitBlockEnterNode createInitBlockEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirAnonymousInitializer firAnonymousInitializer) {
        controlFlowGraphBuilder.getClass();
        firAnonymousInitializer.getClass();
        return new InitBlockEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firAnonymousInitializer, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final InitBlockExitNode createInitBlockExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirAnonymousInitializer firAnonymousInitializer) {
        controlFlowGraphBuilder.getClass();
        firAnonymousInitializer.getClass();
        return new InitBlockExitNode(controlFlowGraphBuilder.getCurrentGraph(), firAnonymousInitializer, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final JumpNode createJumpNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirJump<?> firJump) {
        controlFlowGraphBuilder.getClass();
        firJump.getClass();
        return new JumpNode(controlFlowGraphBuilder.getCurrentGraph(), firJump, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final LiteralExpressionNode createLiteralExpressionNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirLiteralExpression firLiteralExpression) {
        controlFlowGraphBuilder.getClass();
        firLiteralExpression.getClass();
        return new LiteralExpressionNode(controlFlowGraphBuilder.getCurrentGraph(), firLiteralExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final LocalClassExitNode createLocalClassExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirRegularClass firRegularClass) {
        controlFlowGraphBuilder.getClass();
        firRegularClass.getClass();
        return new LocalClassExitNode(controlFlowGraphBuilder.getCurrentGraph(), firRegularClass, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final LocalFunctionDeclarationNode createLocalFunctionDeclarationNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirFunction firFunction) {
        controlFlowGraphBuilder.getClass();
        firFunction.getClass();
        return new LocalFunctionDeclarationNode(controlFlowGraphBuilder.getCurrentGraph(), firFunction, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final LoopBlockEnterNode createLoopBlockEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirLoop firLoop) {
        controlFlowGraphBuilder.getClass();
        firLoop.getClass();
        return new LoopBlockEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firLoop, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final LoopBlockExitNode createLoopBlockExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirLoop firLoop) {
        controlFlowGraphBuilder.getClass();
        firLoop.getClass();
        return new LoopBlockExitNode(controlFlowGraphBuilder.getCurrentGraph(), firLoop, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final LoopConditionEnterNode createLoopConditionEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirExpression firExpression, FirLoop firLoop) {
        controlFlowGraphBuilder.getClass();
        firExpression.getClass();
        firLoop.getClass();
        return new LoopConditionEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firExpression, firLoop, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final LoopConditionExitNode createLoopConditionExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirExpression firExpression, FirLoop firLoop) {
        controlFlowGraphBuilder.getClass();
        firExpression.getClass();
        firLoop.getClass();
        return new LoopConditionExitNode(controlFlowGraphBuilder.getCurrentGraph(), firExpression, firLoop, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final LoopEnterNode createLoopEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirLoop firLoop) {
        controlFlowGraphBuilder.getClass();
        firLoop.getClass();
        return new LoopEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firLoop, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final LoopExitNode createLoopExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirLoop firLoop) {
        controlFlowGraphBuilder.getClass();
        firLoop.getClass();
        return new LoopExitNode(controlFlowGraphBuilder.getCurrentGraph(), firLoop, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final MergePostponedLambdaExitsNode createMergePostponedLambdaExitsNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirElement firElement) {
        controlFlowGraphBuilder.getClass();
        firElement.getClass();
        return new MergePostponedLambdaExitsNode(controlFlowGraphBuilder.getCurrentGraph(), firElement, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final PostponedLambdaExitNode createPostponedLambdaExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirAnonymousFunctionExpression firAnonymousFunctionExpression) {
        controlFlowGraphBuilder.getClass();
        firAnonymousFunctionExpression.getClass();
        return new PostponedLambdaExitNode(controlFlowGraphBuilder.getCurrentGraph(), firAnonymousFunctionExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final PropertyInitializerEnterNode createPropertyInitializerEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirProperty firProperty) {
        controlFlowGraphBuilder.getClass();
        firProperty.getClass();
        return new PropertyInitializerEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firProperty, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final PropertyInitializerExitNode createPropertyInitializerExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirProperty firProperty) {
        controlFlowGraphBuilder.getClass();
        firProperty.getClass();
        return new PropertyInitializerExitNode(controlFlowGraphBuilder.getCurrentGraph(), firProperty, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final QualifiedAccessNode createQualifiedAccessNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        controlFlowGraphBuilder.getClass();
        firQualifiedAccessExpression.getClass();
        return new QualifiedAccessNode(controlFlowGraphBuilder.getCurrentGraph(), firQualifiedAccessExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final ResolvedQualifierNode createResolvedQualifierNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirResolvedQualifier firResolvedQualifier) {
        controlFlowGraphBuilder.getClass();
        firResolvedQualifier.getClass();
        return new ResolvedQualifierNode(controlFlowGraphBuilder.getCurrentGraph(), firResolvedQualifier, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final ScriptEnterNode createScriptEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirScript firScript) {
        controlFlowGraphBuilder.getClass();
        firScript.getClass();
        return new ScriptEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firScript, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final ScriptExitNode createScriptExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirScript firScript) {
        controlFlowGraphBuilder.getClass();
        firScript.getClass();
        return new ScriptExitNode(controlFlowGraphBuilder.getCurrentGraph(), firScript, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final SmartCastExpressionExitNode createSmartCastExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirSmartCastExpression firSmartCastExpression) {
        controlFlowGraphBuilder.getClass();
        firSmartCastExpression.getClass();
        return new SmartCastExpressionExitNode(controlFlowGraphBuilder.getCurrentGraph(), firSmartCastExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final SplitPostponedLambdasNode createSplitPostponedLambdasNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirStatement firStatement, List<? extends FirAnonymousFunction> list) {
        controlFlowGraphBuilder.getClass();
        firStatement.getClass();
        list.getClass();
        return new SplitPostponedLambdasNode(controlFlowGraphBuilder.getCurrentGraph(), firStatement, list, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final StringConcatenationCallNode createStringConcatenationCallNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirStringConcatenationCall firStringConcatenationCall) {
        controlFlowGraphBuilder.getClass();
        firStringConcatenationCall.getClass();
        return new StringConcatenationCallNode(controlFlowGraphBuilder.getCurrentGraph(), firStringConcatenationCall, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final StubNode createStubNode(ControlFlowGraphBuilder controlFlowGraphBuilder) {
        controlFlowGraphBuilder.getClass();
        return new StubNode(controlFlowGraphBuilder.getCurrentGraph(), controlFlowGraphBuilder.getLevelCounter());
    }

    public static final ThrowExceptionNode createThrowExceptionNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirThrowExpression firThrowExpression) {
        controlFlowGraphBuilder.getClass();
        firThrowExpression.getClass();
        return new ThrowExceptionNode(controlFlowGraphBuilder.getCurrentGraph(), firThrowExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final TryExpressionEnterNode createTryExpressionEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirTryExpression firTryExpression) {
        controlFlowGraphBuilder.getClass();
        firTryExpression.getClass();
        return new TryExpressionEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firTryExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final TryExpressionExitNode createTryExpressionExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirTryExpression firTryExpression) {
        controlFlowGraphBuilder.getClass();
        firTryExpression.getClass();
        return new TryExpressionExitNode(controlFlowGraphBuilder.getCurrentGraph(), firTryExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final TryMainBlockEnterNode createTryMainBlockEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirTryExpression firTryExpression) {
        controlFlowGraphBuilder.getClass();
        firTryExpression.getClass();
        return new TryMainBlockEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firTryExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final TryMainBlockExitNode createTryMainBlockExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirTryExpression firTryExpression) {
        controlFlowGraphBuilder.getClass();
        firTryExpression.getClass();
        return new TryMainBlockExitNode(controlFlowGraphBuilder.getCurrentGraph(), firTryExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final TypeOperatorCallNode createTypeOperatorCallNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirTypeOperatorCall firTypeOperatorCall) {
        controlFlowGraphBuilder.getClass();
        firTypeOperatorCall.getClass();
        return new TypeOperatorCallNode(controlFlowGraphBuilder.getCurrentGraph(), firTypeOperatorCall, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final VariableAssignmentNode createVariableAssignmentNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirVariableAssignment firVariableAssignment) {
        controlFlowGraphBuilder.getClass();
        firVariableAssignment.getClass();
        return new VariableAssignmentNode(controlFlowGraphBuilder.getCurrentGraph(), firVariableAssignment, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final VariableDeclarationEnterNode createVariableDeclarationEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirProperty firProperty) {
        controlFlowGraphBuilder.getClass();
        firProperty.getClass();
        return new VariableDeclarationEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firProperty, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final VariableDeclarationExitNode createVariableDeclarationExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirProperty firProperty) {
        controlFlowGraphBuilder.getClass();
        firProperty.getClass();
        return new VariableDeclarationExitNode(controlFlowGraphBuilder.getCurrentGraph(), firProperty, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final WhenBranchConditionEnterNode createWhenBranchConditionEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirWhenBranch firWhenBranch) {
        controlFlowGraphBuilder.getClass();
        firWhenBranch.getClass();
        return new WhenBranchConditionEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firWhenBranch, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final WhenBranchConditionExitNode createWhenBranchConditionExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirWhenBranch firWhenBranch) {
        controlFlowGraphBuilder.getClass();
        firWhenBranch.getClass();
        return new WhenBranchConditionExitNode(controlFlowGraphBuilder.getCurrentGraph(), firWhenBranch, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final WhenBranchResultEnterNode createWhenBranchResultEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirWhenBranch firWhenBranch) {
        controlFlowGraphBuilder.getClass();
        firWhenBranch.getClass();
        return new WhenBranchResultEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firWhenBranch, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final WhenBranchResultExitNode createWhenBranchResultExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirWhenBranch firWhenBranch) {
        controlFlowGraphBuilder.getClass();
        firWhenBranch.getClass();
        return new WhenBranchResultExitNode(controlFlowGraphBuilder.getCurrentGraph(), firWhenBranch, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final WhenEnterNode createWhenEnterNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirWhenExpression firWhenExpression) {
        controlFlowGraphBuilder.getClass();
        firWhenExpression.getClass();
        return new WhenEnterNode(controlFlowGraphBuilder.getCurrentGraph(), firWhenExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final WhenExitNode createWhenExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirWhenExpression firWhenExpression) {
        controlFlowGraphBuilder.getClass();
        firWhenExpression.getClass();
        return new WhenExitNode(controlFlowGraphBuilder.getCurrentGraph(), firWhenExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final WhenSubjectExpressionExitNode createWhenSubjectExpressionExitNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirWhenSubjectExpression firWhenSubjectExpression) {
        controlFlowGraphBuilder.getClass();
        firWhenSubjectExpression.getClass();
        return new WhenSubjectExpressionExitNode(controlFlowGraphBuilder.getCurrentGraph(), firWhenSubjectExpression, controlFlowGraphBuilder.getLevelCounter());
    }

    public static final WhenSyntheticElseBranchNode createWhenSyntheticElseBranchNode(ControlFlowGraphBuilder controlFlowGraphBuilder, FirWhenExpression firWhenExpression) {
        controlFlowGraphBuilder.getClass();
        firWhenExpression.getClass();
        return new WhenSyntheticElseBranchNode(controlFlowGraphBuilder.getCurrentGraph(), firWhenExpression, controlFlowGraphBuilder.getLevelCounter());
    }
}
