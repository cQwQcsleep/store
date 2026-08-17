package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.CheckersComponentInternal;
import org.jetbrains.kotlin.fir.analysis.cfa.AbstractFirPropertyInitializationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.cfa.FirControlFlowChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¼\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\bV\b&\u0018\u0000 Á\u00012\u00020\u0001:\u0002Á\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR$\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u0006j\u0002`\u00110\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR$\u0010\u0013\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0006j\u0002`\u00150\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\nR$\u0010\u0017\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00180\u0006j\u0002`\u00190\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\nR$\u0010\u001b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001c0\u0006j\u0002`\u001d0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\nR$\u0010\u001f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020 0\u0006j\u0002`!0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\nR$\u0010#\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020$0\u0006j\u0002`%0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\nR$\u0010'\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020(0\u0006j\u0002`)0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\nR$\u0010+\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020,0\u0006j\u0002`-0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\nR$\u0010/\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002000\u0006j\u0002`10\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\nR$\u00103\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002040\u0006j\u0002`50\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\nR$\u00107\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002080\u0006j\u0002`90\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\nR$\u0010;\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020<0\u0006j\u0002`=0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\nR$\u0010?\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020@0\u0006j\u0002`A0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\nR$\u0010C\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020D0\u0006j\u0002`E0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\nR$\u0010G\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020H0\u0006j\u0002`I0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\nR$\u0010K\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020L0\u0006j\u0002`M0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010\nR$\u0010O\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020P0\u0006j\u0002`Q0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\nR$\u0010S\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020T0\u0006j\u0002`U0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010\nR$\u0010W\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020X0\u0006j\u0002`Y0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\nR$\u0010[\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\\0\u0006j\u0002`]0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\nR\u001a\u0010_\u001a\b\u0012\u0004\u0012\u00020`0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\ba\u0010\nR\u001a\u0010b\u001a\b\u0012\u0004\u0012\u00020c0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\nR5\u0010e\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0012\n\u0004\bj\u0010k\u0012\u0004\bg\u0010\u0003\u001a\u0004\bh\u0010iR5\u0010m\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0012\n\u0004\bp\u0010k\u0012\u0004\bn\u0010\u0003\u001a\u0004\bo\u0010iR5\u0010q\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u0006j\u0002`\u00110f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0012\n\u0004\bt\u0010k\u0012\u0004\br\u0010\u0003\u001a\u0004\bs\u0010iR5\u0010u\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0006j\u0002`\u00150f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0012\n\u0004\bx\u0010k\u0012\u0004\bv\u0010\u0003\u001a\u0004\bw\u0010iR5\u0010y\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00180\u0006j\u0002`\u00190f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0012\n\u0004\b|\u0010k\u0012\u0004\bz\u0010\u0003\u001a\u0004\b{\u0010iR6\u0010}\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001c0\u0006j\u0002`\u001d0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0013\n\u0005\b\u0080\u0001\u0010k\u0012\u0004\b~\u0010\u0003\u001a\u0004\b\u007f\u0010iR9\u0010\u0081\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020 0\u0006j\u0002`!0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\b\u0084\u0001\u0010k\u0012\u0005\b\u0082\u0001\u0010\u0003\u001a\u0005\b\u0083\u0001\u0010iR9\u0010\u0085\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020$0\u0006j\u0002`%0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\b\u0088\u0001\u0010k\u0012\u0005\b\u0086\u0001\u0010\u0003\u001a\u0005\b\u0087\u0001\u0010iR9\u0010\u0089\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020(0\u0006j\u0002`)0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\b\u008c\u0001\u0010k\u0012\u0005\b\u008a\u0001\u0010\u0003\u001a\u0005\b\u008b\u0001\u0010iR9\u0010\u008d\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020,0\u0006j\u0002`-0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\b\u0090\u0001\u0010k\u0012\u0005\b\u008e\u0001\u0010\u0003\u001a\u0005\b\u008f\u0001\u0010iR9\u0010\u0091\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002000\u0006j\u0002`10f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\b\u0094\u0001\u0010k\u0012\u0005\b\u0092\u0001\u0010\u0003\u001a\u0005\b\u0093\u0001\u0010iR9\u0010\u0095\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002040\u0006j\u0002`50f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\b\u0098\u0001\u0010k\u0012\u0005\b\u0096\u0001\u0010\u0003\u001a\u0005\b\u0097\u0001\u0010iR9\u0010\u0099\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002080\u0006j\u0002`90f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\b\u009c\u0001\u0010k\u0012\u0005\b\u009a\u0001\u0010\u0003\u001a\u0005\b\u009b\u0001\u0010iR9\u0010\u009d\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020<0\u0006j\u0002`=0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\b \u0001\u0010k\u0012\u0005\b\u009e\u0001\u0010\u0003\u001a\u0005\b\u009f\u0001\u0010iR9\u0010¡\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020@0\u0006j\u0002`A0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\b¤\u0001\u0010k\u0012\u0005\b¢\u0001\u0010\u0003\u001a\u0005\b£\u0001\u0010iR9\u0010¥\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020D0\u0006j\u0002`E0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\b¨\u0001\u0010k\u0012\u0005\b¦\u0001\u0010\u0003\u001a\u0005\b§\u0001\u0010iR9\u0010©\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020H0\u0006j\u0002`I0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\b¬\u0001\u0010k\u0012\u0005\bª\u0001\u0010\u0003\u001a\u0005\b«\u0001\u0010iR9\u0010\u00ad\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020L0\u0006j\u0002`M0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\b°\u0001\u0010k\u0012\u0005\b®\u0001\u0010\u0003\u001a\u0005\b¯\u0001\u0010iR9\u0010±\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020P0\u0006j\u0002`Q0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\b´\u0001\u0010k\u0012\u0005\b²\u0001\u0010\u0003\u001a\u0005\b³\u0001\u0010iR9\u0010µ\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020T0\u0006j\u0002`U0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\b¸\u0001\u0010k\u0012\u0005\b¶\u0001\u0010\u0003\u001a\u0005\b·\u0001\u0010iR9\u0010¹\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020X0\u0006j\u0002`Y0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\b¼\u0001\u0010k\u0012\u0005\bº\u0001\u0010\u0003\u001a\u0005\b»\u0001\u0010iR9\u0010½\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\\0\u0006j\u0002`]0f8@X\u0081\u0084\u0002r\u0002\bl¢\u0006\u0015\n\u0005\bÀ\u0001\u0010k\u0012\u0005\b¾\u0001\u0010\u0003\u001a\u0005\b¿\u0001\u0010i¨\u0006Â\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", Argument.Delimiters.none, "<init>", "()V", "basicDeclarationCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "getBasicDeclarationCheckers", "()Ljava/util/Set;", "callableDeclarationCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCallableDeclarationChecker;", "getCallableDeclarationCheckers", "functionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionChecker;", "getFunctionCheckers", "simpleFunctionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirSimpleFunctionChecker;", "getSimpleFunctionCheckers", "propertyCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "getPropertyCheckers", "classLikeCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassLikeChecker;", "getClassLikeCheckers", "classCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "getClassCheckers", "regularClassCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "getRegularClassCheckers", "constructorCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirConstructorChecker;", "getConstructorCheckers", "fileCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFileChecker;", "getFileCheckers", "scriptCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirScriptChecker;", "getScriptCheckers", "replSnippetCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirReplSnippetChecker;", "getReplSnippetCheckers", "typeParameterCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeParameterChecker;", "getTypeParameterCheckers", "typeAliasCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeAliasChecker;", "getTypeAliasCheckers", "anonymousFunctionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnonymousFunctionChecker;", "getAnonymousFunctionCheckers", "propertyAccessorCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyAccessorChecker;", "getPropertyAccessorCheckers", "backingFieldCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBackingFieldChecker;", "getBackingFieldCheckers", "valueParameterCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirValueParameterChecker;", "getValueParameterCheckers", "enumEntryCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirEnumEntryChecker;", "getEnumEntryCheckers", "anonymousObjectCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnonymousObjectChecker;", "getAnonymousObjectCheckers", "anonymousInitializerCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnonymousInitializerChecker;", "getAnonymousInitializerCheckers", "receiverParameterCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirReceiverParameterChecker;", "getReceiverParameterCheckers", "controlFlowAnalyserCheckers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/cfa/FirControlFlowChecker;", "getControlFlowAnalyserCheckers", "variableAssignmentCfaBasedCheckers", "Lorg/jetbrains/kotlin/fir/analysis/cfa/AbstractFirPropertyInitializationChecker;", "getVariableAssignmentCfaBasedCheckers", "allBasicDeclarationCheckers", Argument.Delimiters.none, "getAllBasicDeclarationCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllBasicDeclarationCheckers$org_jetbrains_kotlin_checkers", "()[Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "allBasicDeclarationCheckers$delegate", "Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/fir/analysis/CheckersComponentInternal;", "allCallableDeclarationCheckers", "getAllCallableDeclarationCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllCallableDeclarationCheckers$org_jetbrains_kotlin_checkers", "allCallableDeclarationCheckers$delegate", "allFunctionCheckers", "getAllFunctionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllFunctionCheckers$org_jetbrains_kotlin_checkers", "allFunctionCheckers$delegate", "allSimpleFunctionCheckers", "getAllSimpleFunctionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllSimpleFunctionCheckers$org_jetbrains_kotlin_checkers", "allSimpleFunctionCheckers$delegate", "allPropertyCheckers", "getAllPropertyCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllPropertyCheckers$org_jetbrains_kotlin_checkers", "allPropertyCheckers$delegate", "allClassLikeCheckers", "getAllClassLikeCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllClassLikeCheckers$org_jetbrains_kotlin_checkers", "allClassLikeCheckers$delegate", "allClassCheckers", "getAllClassCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllClassCheckers$org_jetbrains_kotlin_checkers", "allClassCheckers$delegate", "allRegularClassCheckers", "getAllRegularClassCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllRegularClassCheckers$org_jetbrains_kotlin_checkers", "allRegularClassCheckers$delegate", "allConstructorCheckers", "getAllConstructorCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllConstructorCheckers$org_jetbrains_kotlin_checkers", "allConstructorCheckers$delegate", "allFileCheckers", "getAllFileCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllFileCheckers$org_jetbrains_kotlin_checkers", "allFileCheckers$delegate", "allScriptCheckers", "getAllScriptCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllScriptCheckers$org_jetbrains_kotlin_checkers", "allScriptCheckers$delegate", "allReplSnippetCheckers", "getAllReplSnippetCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllReplSnippetCheckers$org_jetbrains_kotlin_checkers", "allReplSnippetCheckers$delegate", "allTypeParameterCheckers", "getAllTypeParameterCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllTypeParameterCheckers$org_jetbrains_kotlin_checkers", "allTypeParameterCheckers$delegate", "allTypeAliasCheckers", "getAllTypeAliasCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllTypeAliasCheckers$org_jetbrains_kotlin_checkers", "allTypeAliasCheckers$delegate", "allAnonymousFunctionCheckers", "getAllAnonymousFunctionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllAnonymousFunctionCheckers$org_jetbrains_kotlin_checkers", "allAnonymousFunctionCheckers$delegate", "allPropertyAccessorCheckers", "getAllPropertyAccessorCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllPropertyAccessorCheckers$org_jetbrains_kotlin_checkers", "allPropertyAccessorCheckers$delegate", "allBackingFieldCheckers", "getAllBackingFieldCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllBackingFieldCheckers$org_jetbrains_kotlin_checkers", "allBackingFieldCheckers$delegate", "allValueParameterCheckers", "getAllValueParameterCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllValueParameterCheckers$org_jetbrains_kotlin_checkers", "allValueParameterCheckers$delegate", "allEnumEntryCheckers", "getAllEnumEntryCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllEnumEntryCheckers$org_jetbrains_kotlin_checkers", "allEnumEntryCheckers$delegate", "allAnonymousObjectCheckers", "getAllAnonymousObjectCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllAnonymousObjectCheckers$org_jetbrains_kotlin_checkers", "allAnonymousObjectCheckers$delegate", "allAnonymousInitializerCheckers", "getAllAnonymousInitializerCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllAnonymousInitializerCheckers$org_jetbrains_kotlin_checkers", "allAnonymousInitializerCheckers$delegate", "allReceiverParameterCheckers", "getAllReceiverParameterCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllReceiverParameterCheckers$org_jetbrains_kotlin_checkers", "allReceiverParameterCheckers$delegate", "Companion", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class DeclarationCheckers {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DeclarationCheckers EMPTY = new DeclarationCheckers() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers$Companion$EMPTY$1
    };
    private final Set<FirDeclarationChecker<FirDeclaration>> basicDeclarationCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirCallableDeclaration>> callableDeclarationCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirFunction>> functionCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirNamedFunction>> simpleFunctionCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirProperty>> propertyCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirClassLikeDeclaration>> classLikeCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirClass>> classCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirRegularClass>> regularClassCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirConstructor>> constructorCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirFile>> fileCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirScript>> scriptCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirReplSnippet>> replSnippetCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirTypeParameter>> typeParameterCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirTypeAlias>> typeAliasCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirAnonymousFunction>> anonymousFunctionCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirPropertyAccessor>> propertyAccessorCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirBackingField>> backingFieldCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirValueParameter>> valueParameterCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirEnumEntry>> enumEntryCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirAnonymousObject>> anonymousObjectCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirAnonymousInitializer>> anonymousInitializerCheckers = SetsKt.emptySet();
    private final Set<FirDeclarationChecker<FirReceiverParameter>> receiverParameterCheckers = SetsKt.emptySet();
    private final Set<FirControlFlowChecker> controlFlowAnalyserCheckers = SetsKt.emptySet();
    private final Set<AbstractFirPropertyInitializationChecker> variableAssignmentCfaBasedCheckers = SetsKt.emptySet();

    /* JADX INFO: renamed from: allBasicDeclarationCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allBasicDeclarationCheckers = LazyKt.lazy(new Function0() { // from class: vc3
        public final Object invoke() {
            return DeclarationCheckers.u(this.b);
        }
    });

    /* JADX INFO: renamed from: allCallableDeclarationCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allCallableDeclarationCheckers = LazyKt.lazy(new Function0() { // from class: xc3
        public final Object invoke() {
            return DeclarationCheckers.o(this.b);
        }
    });

    /* JADX INFO: renamed from: allFunctionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allFunctionCheckers = LazyKt.lazy(new Function0() { // from class: ad3
        public final Object invoke() {
            return DeclarationCheckers.a(this.b);
        }
    });

    /* JADX INFO: renamed from: allSimpleFunctionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allSimpleFunctionCheckers = LazyKt.lazy(new Function0() { // from class: bd3
        public final Object invoke() {
            return DeclarationCheckers.b(this.b);
        }
    });

    /* JADX INFO: renamed from: allPropertyCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allPropertyCheckers = LazyKt.lazy(new Function0() { // from class: cd3
        public final Object invoke() {
            return DeclarationCheckers.g(this.b);
        }
    });

    /* JADX INFO: renamed from: allClassLikeCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allClassLikeCheckers = LazyKt.lazy(new Function0() { // from class: dd3
        public final Object invoke() {
            return DeclarationCheckers.f(this.b);
        }
    });

    /* JADX INFO: renamed from: allClassCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allClassCheckers = LazyKt.lazy(new Function0() { // from class: ed3
        public final Object invoke() {
            return DeclarationCheckers.v(this.b);
        }
    });

    /* JADX INFO: renamed from: allRegularClassCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allRegularClassCheckers = LazyKt.lazy(new Function0() { // from class: fd3
        public final Object invoke() {
            return DeclarationCheckers.c(this.b);
        }
    });

    /* JADX INFO: renamed from: allConstructorCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allConstructorCheckers = LazyKt.lazy(new Function0() { // from class: hd3
        public final Object invoke() {
            return DeclarationCheckers.s(this.b);
        }
    });

    /* JADX INFO: renamed from: allFileCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allFileCheckers = LazyKt.lazy(new Function0() { // from class: id3
        public final Object invoke() {
            return DeclarationCheckers.n(this.b);
        }
    });

    /* JADX INFO: renamed from: allScriptCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allScriptCheckers = LazyKt.lazy(new Function0() { // from class: gd3
        public final Object invoke() {
            return DeclarationCheckers.r(this.b);
        }
    });

    /* JADX INFO: renamed from: allReplSnippetCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allReplSnippetCheckers = LazyKt.lazy(new Function0() { // from class: jd3
        public final Object invoke() {
            return DeclarationCheckers.j(this.b);
        }
    });

    /* JADX INFO: renamed from: allTypeParameterCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allTypeParameterCheckers = LazyKt.lazy(new Function0() { // from class: kd3
        public final Object invoke() {
            return DeclarationCheckers.i(this.b);
        }
    });

    /* JADX INFO: renamed from: allTypeAliasCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allTypeAliasCheckers = LazyKt.lazy(new Function0() { // from class: ld3
        public final Object invoke() {
            return DeclarationCheckers.k(this.b);
        }
    });

    /* JADX INFO: renamed from: allAnonymousFunctionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allAnonymousFunctionCheckers = LazyKt.lazy(new Function0() { // from class: md3
        public final Object invoke() {
            return DeclarationCheckers.l(this.b);
        }
    });

    /* JADX INFO: renamed from: allPropertyAccessorCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allPropertyAccessorCheckers = LazyKt.lazy(new Function0() { // from class: nd3
        public final Object invoke() {
            return DeclarationCheckers.e(this.b);
        }
    });

    /* JADX INFO: renamed from: allBackingFieldCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allBackingFieldCheckers = LazyKt.lazy(new Function0() { // from class: od3
        public final Object invoke() {
            return DeclarationCheckers.q(this.b);
        }
    });

    /* JADX INFO: renamed from: allValueParameterCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allValueParameterCheckers = LazyKt.lazy(new Function0() { // from class: pd3
        public final Object invoke() {
            return DeclarationCheckers.p(this.b);
        }
    });

    /* JADX INFO: renamed from: allEnumEntryCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allEnumEntryCheckers = LazyKt.lazy(new Function0() { // from class: qd3
        public final Object invoke() {
            return DeclarationCheckers.d(this.b);
        }
    });

    /* JADX INFO: renamed from: allAnonymousObjectCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allAnonymousObjectCheckers = LazyKt.lazy(new Function0() { // from class: wc3
        public final Object invoke() {
            return DeclarationCheckers.m(this.b);
        }
    });

    /* JADX INFO: renamed from: allAnonymousInitializerCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allAnonymousInitializerCheckers = LazyKt.lazy(new Function0() { // from class: yc3
        public final Object invoke() {
            return DeclarationCheckers.h(this.b);
        }
    });

    /* JADX INFO: renamed from: allReceiverParameterCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allReceiverParameterCheckers = LazyKt.lazy(new Function0() { // from class: zc3
        public final Object invoke() {
            return DeclarationCheckers.t(this.b);
        }
    });

    public static FirDeclarationChecker[] a(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(SetsKt.plus(declarationCheckers.getFunctionCheckers(), declarationCheckers.getCallableDeclarationCheckers()), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] b(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(SetsKt.plus(SetsKt.plus(declarationCheckers.getSimpleFunctionCheckers(), declarationCheckers.getFunctionCheckers()), declarationCheckers.getCallableDeclarationCheckers()), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] c(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(SetsKt.plus(SetsKt.plus(declarationCheckers.getRegularClassCheckers(), declarationCheckers.getClassCheckers()), declarationCheckers.getClassLikeCheckers()), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] d(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(SetsKt.plus(declarationCheckers.getEnumEntryCheckers(), declarationCheckers.getCallableDeclarationCheckers()), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] e(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(SetsKt.plus(SetsKt.plus(declarationCheckers.getPropertyAccessorCheckers(), declarationCheckers.getFunctionCheckers()), declarationCheckers.getCallableDeclarationCheckers()), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] f(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(declarationCheckers.getClassLikeCheckers(), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] g(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(SetsKt.plus(declarationCheckers.getPropertyCheckers(), declarationCheckers.getCallableDeclarationCheckers()), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllAnonymousFunctionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllAnonymousInitializerCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllAnonymousObjectCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllBackingFieldCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllBasicDeclarationCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllCallableDeclarationCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllClassCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllClassLikeCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllConstructorCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllEnumEntryCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllFileCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllFunctionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllPropertyAccessorCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllPropertyCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllReceiverParameterCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllRegularClassCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllReplSnippetCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllScriptCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllSimpleFunctionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllTypeAliasCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllTypeParameterCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllValueParameterCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    public static FirDeclarationChecker[] h(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(declarationCheckers.getAnonymousInitializerCheckers(), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] i(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(declarationCheckers.getTypeParameterCheckers(), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] j(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(declarationCheckers.getReplSnippetCheckers(), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] k(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(SetsKt.plus(declarationCheckers.getTypeAliasCheckers(), declarationCheckers.getClassLikeCheckers()), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] l(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(SetsKt.plus(SetsKt.plus(declarationCheckers.getAnonymousFunctionCheckers(), declarationCheckers.getFunctionCheckers()), declarationCheckers.getCallableDeclarationCheckers()), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] m(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(SetsKt.plus(SetsKt.plus(declarationCheckers.getAnonymousObjectCheckers(), declarationCheckers.getClassCheckers()), declarationCheckers.getClassLikeCheckers()), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] n(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(declarationCheckers.getFileCheckers(), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] o(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(declarationCheckers.getCallableDeclarationCheckers(), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] p(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(SetsKt.plus(declarationCheckers.getValueParameterCheckers(), declarationCheckers.getCallableDeclarationCheckers()), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] q(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(SetsKt.plus(declarationCheckers.getBackingFieldCheckers(), declarationCheckers.getCallableDeclarationCheckers()), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] r(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(declarationCheckers.getScriptCheckers(), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] s(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(SetsKt.plus(SetsKt.plus(declarationCheckers.getConstructorCheckers(), declarationCheckers.getFunctionCheckers()), declarationCheckers.getCallableDeclarationCheckers()), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] t(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(declarationCheckers.getReceiverParameterCheckers(), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] u(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) declarationCheckers.getBasicDeclarationCheckers().toArray(new FirDeclarationChecker[0]);
    }

    public static FirDeclarationChecker[] v(DeclarationCheckers declarationCheckers) {
        return (FirDeclarationChecker[]) SetsKt.plus(SetsKt.plus(declarationCheckers.getClassCheckers(), declarationCheckers.getClassLikeCheckers()), declarationCheckers.getBasicDeclarationCheckers()).toArray(new FirDeclarationChecker[0]);
    }

    public final FirDeclarationChecker<FirAnonymousFunction>[] getAllAnonymousFunctionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allAnonymousFunctionCheckers.getValue();
    }

    public final FirDeclarationChecker<FirAnonymousInitializer>[] getAllAnonymousInitializerCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allAnonymousInitializerCheckers.getValue();
    }

    public final FirDeclarationChecker<FirAnonymousObject>[] getAllAnonymousObjectCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allAnonymousObjectCheckers.getValue();
    }

    public final FirDeclarationChecker<FirBackingField>[] getAllBackingFieldCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allBackingFieldCheckers.getValue();
    }

    public final FirDeclarationChecker<FirDeclaration>[] getAllBasicDeclarationCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allBasicDeclarationCheckers.getValue();
    }

    public final FirDeclarationChecker<FirCallableDeclaration>[] getAllCallableDeclarationCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allCallableDeclarationCheckers.getValue();
    }

    public final FirDeclarationChecker<FirClass>[] getAllClassCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allClassCheckers.getValue();
    }

    public final FirDeclarationChecker<FirClassLikeDeclaration>[] getAllClassLikeCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allClassLikeCheckers.getValue();
    }

    public final FirDeclarationChecker<FirConstructor>[] getAllConstructorCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allConstructorCheckers.getValue();
    }

    public final FirDeclarationChecker<FirEnumEntry>[] getAllEnumEntryCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allEnumEntryCheckers.getValue();
    }

    public final FirDeclarationChecker<FirFile>[] getAllFileCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allFileCheckers.getValue();
    }

    public final FirDeclarationChecker<FirFunction>[] getAllFunctionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allFunctionCheckers.getValue();
    }

    public final FirDeclarationChecker<FirPropertyAccessor>[] getAllPropertyAccessorCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allPropertyAccessorCheckers.getValue();
    }

    public final FirDeclarationChecker<FirProperty>[] getAllPropertyCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allPropertyCheckers.getValue();
    }

    public final FirDeclarationChecker<FirReceiverParameter>[] getAllReceiverParameterCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allReceiverParameterCheckers.getValue();
    }

    public final FirDeclarationChecker<FirRegularClass>[] getAllRegularClassCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allRegularClassCheckers.getValue();
    }

    public final FirDeclarationChecker<FirReplSnippet>[] getAllReplSnippetCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allReplSnippetCheckers.getValue();
    }

    public final FirDeclarationChecker<FirScript>[] getAllScriptCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allScriptCheckers.getValue();
    }

    public final FirDeclarationChecker<FirNamedFunction>[] getAllSimpleFunctionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allSimpleFunctionCheckers.getValue();
    }

    public final FirDeclarationChecker<FirTypeAlias>[] getAllTypeAliasCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allTypeAliasCheckers.getValue();
    }

    public final FirDeclarationChecker<FirTypeParameter>[] getAllTypeParameterCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allTypeParameterCheckers.getValue();
    }

    public final FirDeclarationChecker<FirValueParameter>[] getAllValueParameterCheckers$org_jetbrains_kotlin_checkers() {
        return (FirDeclarationChecker[]) this.allValueParameterCheckers.getValue();
    }

    public Set<FirDeclarationChecker<FirAnonymousFunction>> getAnonymousFunctionCheckers() {
        return this.anonymousFunctionCheckers;
    }

    public Set<FirDeclarationChecker<FirAnonymousInitializer>> getAnonymousInitializerCheckers() {
        return this.anonymousInitializerCheckers;
    }

    public Set<FirDeclarationChecker<FirAnonymousObject>> getAnonymousObjectCheckers() {
        return this.anonymousObjectCheckers;
    }

    public Set<FirDeclarationChecker<FirBackingField>> getBackingFieldCheckers() {
        return this.backingFieldCheckers;
    }

    public Set<FirDeclarationChecker<FirDeclaration>> getBasicDeclarationCheckers() {
        return this.basicDeclarationCheckers;
    }

    public Set<FirDeclarationChecker<FirCallableDeclaration>> getCallableDeclarationCheckers() {
        return this.callableDeclarationCheckers;
    }

    public Set<FirDeclarationChecker<FirClass>> getClassCheckers() {
        return this.classCheckers;
    }

    public Set<FirDeclarationChecker<FirClassLikeDeclaration>> getClassLikeCheckers() {
        return this.classLikeCheckers;
    }

    public Set<FirDeclarationChecker<FirConstructor>> getConstructorCheckers() {
        return this.constructorCheckers;
    }

    public Set<FirControlFlowChecker> getControlFlowAnalyserCheckers() {
        return this.controlFlowAnalyserCheckers;
    }

    public Set<FirDeclarationChecker<FirEnumEntry>> getEnumEntryCheckers() {
        return this.enumEntryCheckers;
    }

    public Set<FirDeclarationChecker<FirFile>> getFileCheckers() {
        return this.fileCheckers;
    }

    public Set<FirDeclarationChecker<FirFunction>> getFunctionCheckers() {
        return this.functionCheckers;
    }

    public Set<FirDeclarationChecker<FirPropertyAccessor>> getPropertyAccessorCheckers() {
        return this.propertyAccessorCheckers;
    }

    public Set<FirDeclarationChecker<FirProperty>> getPropertyCheckers() {
        return this.propertyCheckers;
    }

    public Set<FirDeclarationChecker<FirReceiverParameter>> getReceiverParameterCheckers() {
        return this.receiverParameterCheckers;
    }

    public Set<FirDeclarationChecker<FirRegularClass>> getRegularClassCheckers() {
        return this.regularClassCheckers;
    }

    public Set<FirDeclarationChecker<FirReplSnippet>> getReplSnippetCheckers() {
        return this.replSnippetCheckers;
    }

    public Set<FirDeclarationChecker<FirScript>> getScriptCheckers() {
        return this.scriptCheckers;
    }

    public Set<FirDeclarationChecker<FirNamedFunction>> getSimpleFunctionCheckers() {
        return this.simpleFunctionCheckers;
    }

    public Set<FirDeclarationChecker<FirTypeAlias>> getTypeAliasCheckers() {
        return this.typeAliasCheckers;
    }

    public Set<FirDeclarationChecker<FirTypeParameter>> getTypeParameterCheckers() {
        return this.typeParameterCheckers;
    }

    public Set<FirDeclarationChecker<FirValueParameter>> getValueParameterCheckers() {
        return this.valueParameterCheckers;
    }

    public Set<AbstractFirPropertyInitializationChecker> getVariableAssignmentCfaBasedCheckers() {
        return this.variableAssignmentCfaBasedCheckers;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers$Companion;", Argument.Delimiters.none, "<init>", "()V", "EMPTY", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "getEMPTY", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DeclarationCheckers getEMPTY() {
            return DeclarationCheckers.EMPTY;
        }

        private Companion() {
        }
    }
}
