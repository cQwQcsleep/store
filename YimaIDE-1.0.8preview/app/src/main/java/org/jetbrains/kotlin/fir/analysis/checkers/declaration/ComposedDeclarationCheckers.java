package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.CheckersComponentInternal;
import org.jetbrains.kotlin.fir.analysis.cfa.AbstractFirPropertyInitializationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.cfa.FirControlFlowChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.ComposedDeclarationCheckers;
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
@Metadata(d1 = {"\u0000Ö\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\nJ\u0018\u0010\u0087\u0001\u001a\u00030\u0088\u00012\u0007\u0010\u0089\u0001\u001a\u00020\u0001H\u0007b\u0003\b\u008a\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u000fj\u0002`\u00110\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00150\u000fj\u0002`\u00160\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R$\u0010\u0018\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00190\u000fj\u0002`\u001a0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0013R$\u0010\u001c\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001d0\u000fj\u0002`\u001e0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0013R$\u0010 \u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020!0\u000fj\u0002`\"0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0013R$\u0010$\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020%0\u000fj\u0002`&0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u0013R$\u0010(\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020)0\u000fj\u0002`*0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010\u0013R$\u0010,\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020-0\u000fj\u0002`.0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u0013R$\u00100\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002010\u000fj\u0002`20\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u0010\u0013R$\u00104\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002050\u000fj\u0002`60\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u0010\u0013R$\u00108\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002090\u000fj\u0002`:0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010\u0013R$\u0010<\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020=0\u000fj\u0002`>0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010\u0013R$\u0010@\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020A0\u000fj\u0002`B0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bC\u0010\u0013R$\u0010D\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020E0\u000fj\u0002`F0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bG\u0010\u0013R$\u0010H\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020I0\u000fj\u0002`J0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bK\u0010\u0013R$\u0010L\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020M0\u000fj\u0002`N0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bO\u0010\u0013R$\u0010P\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020Q0\u000fj\u0002`R0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bS\u0010\u0013R$\u0010T\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020U0\u000fj\u0002`V0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bW\u0010\u0013R$\u0010X\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020Y0\u000fj\u0002`Z0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b[\u0010\u0013R$\u0010\\\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020]0\u000fj\u0002`^0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b_\u0010\u0013R$\u0010`\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020a0\u000fj\u0002`b0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bc\u0010\u0013R$\u0010d\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020e0\u000fj\u0002`f0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bg\u0010\u0013R\u001a\u0010h\u001a\b\u0012\u0004\u0012\u00020i0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bj\u0010\u0013R\u001a\u0010k\u001a\b\u0012\u0004\u0012\u00020l0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bm\u0010\u0013R\u001e\u0010n\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u000fj\u0002`\u00110oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010p\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00150\u000fj\u0002`\u00160oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010q\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00190\u000fj\u0002`\u001a0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010r\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001d0\u000fj\u0002`\u001e0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010s\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020!0\u000fj\u0002`\"0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010t\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020%0\u000fj\u0002`&0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010u\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020)0\u000fj\u0002`*0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010v\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020-0\u000fj\u0002`.0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010w\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002010\u000fj\u0002`20oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010x\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002050\u000fj\u0002`60oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010y\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002090\u000fj\u0002`:0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010z\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020=0\u000fj\u0002`>0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010{\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020A0\u000fj\u0002`B0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010|\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020E0\u000fj\u0002`F0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010}\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020I0\u000fj\u0002`J0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010~\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020M0\u000fj\u0002`N0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u007f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020Q0\u000fj\u0002`R0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0080\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020U0\u000fj\u0002`V0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0081\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020Y0\u000fj\u0002`Z0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0082\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020]0\u000fj\u0002`^0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0083\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020a0\u000fj\u0002`b0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0084\u0001\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020e0\u000fj\u0002`f0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020i0oX\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020l0oX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u008b\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/ComposedDeclarationCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "predicate", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirCheckerWithMppKind;", Argument.Delimiters.none, "<init>", "(Lkotlin/jvm/functions/Function1;)V", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "getPredicate", "()Lkotlin/jvm/functions/Function1;", "basicDeclarationCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "getBasicDeclarationCheckers", "()Ljava/util/Set;", "callableDeclarationCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCallableDeclarationChecker;", "getCallableDeclarationCheckers", "functionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionChecker;", "getFunctionCheckers", "simpleFunctionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirSimpleFunctionChecker;", "getSimpleFunctionCheckers", "propertyCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "getPropertyCheckers", "classLikeCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassLikeChecker;", "getClassLikeCheckers", "classCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "getClassCheckers", "regularClassCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "getRegularClassCheckers", "constructorCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirConstructorChecker;", "getConstructorCheckers", "fileCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFileChecker;", "getFileCheckers", "scriptCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirScriptChecker;", "getScriptCheckers", "replSnippetCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirReplSnippetChecker;", "getReplSnippetCheckers", "typeParameterCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeParameterChecker;", "getTypeParameterCheckers", "typeAliasCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeAliasChecker;", "getTypeAliasCheckers", "anonymousFunctionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnonymousFunctionChecker;", "getAnonymousFunctionCheckers", "propertyAccessorCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyAccessorChecker;", "getPropertyAccessorCheckers", "backingFieldCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBackingFieldChecker;", "getBackingFieldCheckers", "valueParameterCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirValueParameterChecker;", "getValueParameterCheckers", "enumEntryCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirEnumEntryChecker;", "getEnumEntryCheckers", "anonymousObjectCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnonymousObjectChecker;", "getAnonymousObjectCheckers", "anonymousInitializerCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnonymousInitializerChecker;", "getAnonymousInitializerCheckers", "receiverParameterCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirReceiverParameterChecker;", "getReceiverParameterCheckers", "controlFlowAnalyserCheckers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/cfa/FirControlFlowChecker;", "getControlFlowAnalyserCheckers", "variableAssignmentCfaBasedCheckers", "Lorg/jetbrains/kotlin/fir/analysis/cfa/AbstractFirPropertyInitializationChecker;", "getVariableAssignmentCfaBasedCheckers", "_basicDeclarationCheckers", Argument.Delimiters.none, "_callableDeclarationCheckers", "_functionCheckers", "_simpleFunctionCheckers", "_propertyCheckers", "_classLikeCheckers", "_classCheckers", "_regularClassCheckers", "_constructorCheckers", "_fileCheckers", "_scriptCheckers", "_replSnippetCheckers", "_typeParameterCheckers", "_typeAliasCheckers", "_anonymousFunctionCheckers", "_propertyAccessorCheckers", "_backingFieldCheckers", "_valueParameterCheckers", "_enumEntryCheckers", "_anonymousObjectCheckers", "_anonymousInitializerCheckers", "_receiverParameterCheckers", "_controlFlowAnalyserCheckers", "_variableAssignmentCfaBasedCheckers", "register", Argument.Delimiters.none, "checkers", "Lorg/jetbrains/kotlin/fir/analysis/CheckersComponentInternal;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ComposedDeclarationCheckers extends DeclarationCheckers {
    private final Set<FirDeclarationChecker<FirAnonymousFunction>> _anonymousFunctionCheckers;
    private final Set<FirDeclarationChecker<FirAnonymousInitializer>> _anonymousInitializerCheckers;
    private final Set<FirDeclarationChecker<FirAnonymousObject>> _anonymousObjectCheckers;
    private final Set<FirDeclarationChecker<FirBackingField>> _backingFieldCheckers;
    private final Set<FirDeclarationChecker<FirDeclaration>> _basicDeclarationCheckers;
    private final Set<FirDeclarationChecker<FirCallableDeclaration>> _callableDeclarationCheckers;
    private final Set<FirDeclarationChecker<FirClass>> _classCheckers;
    private final Set<FirDeclarationChecker<FirClassLikeDeclaration>> _classLikeCheckers;
    private final Set<FirDeclarationChecker<FirConstructor>> _constructorCheckers;
    private final Set<FirControlFlowChecker> _controlFlowAnalyserCheckers;
    private final Set<FirDeclarationChecker<FirEnumEntry>> _enumEntryCheckers;
    private final Set<FirDeclarationChecker<FirFile>> _fileCheckers;
    private final Set<FirDeclarationChecker<FirFunction>> _functionCheckers;
    private final Set<FirDeclarationChecker<FirPropertyAccessor>> _propertyAccessorCheckers;
    private final Set<FirDeclarationChecker<FirProperty>> _propertyCheckers;
    private final Set<FirDeclarationChecker<FirReceiverParameter>> _receiverParameterCheckers;
    private final Set<FirDeclarationChecker<FirRegularClass>> _regularClassCheckers;
    private final Set<FirDeclarationChecker<FirReplSnippet>> _replSnippetCheckers;
    private final Set<FirDeclarationChecker<FirScript>> _scriptCheckers;
    private final Set<FirDeclarationChecker<FirNamedFunction>> _simpleFunctionCheckers;
    private final Set<FirDeclarationChecker<FirTypeAlias>> _typeAliasCheckers;
    private final Set<FirDeclarationChecker<FirTypeParameter>> _typeParameterCheckers;
    private final Set<FirDeclarationChecker<FirValueParameter>> _valueParameterCheckers;
    private final Set<AbstractFirPropertyInitializationChecker> _variableAssignmentCfaBasedCheckers;
    private final Function1<FirCheckerWithMppKind, Boolean> predicate;

    /* JADX WARN: Multi-variable type inference failed */
    public ComposedDeclarationCheckers(Function1<? super FirCheckerWithMppKind, Boolean> function1) {
        function1.getClass();
        this.predicate = function1;
        this._basicDeclarationCheckers = new LinkedHashSet();
        this._callableDeclarationCheckers = new LinkedHashSet();
        this._functionCheckers = new LinkedHashSet();
        this._simpleFunctionCheckers = new LinkedHashSet();
        this._propertyCheckers = new LinkedHashSet();
        this._classLikeCheckers = new LinkedHashSet();
        this._classCheckers = new LinkedHashSet();
        this._regularClassCheckers = new LinkedHashSet();
        this._constructorCheckers = new LinkedHashSet();
        this._fileCheckers = new LinkedHashSet();
        this._scriptCheckers = new LinkedHashSet();
        this._replSnippetCheckers = new LinkedHashSet();
        this._typeParameterCheckers = new LinkedHashSet();
        this._typeAliasCheckers = new LinkedHashSet();
        this._anonymousFunctionCheckers = new LinkedHashSet();
        this._propertyAccessorCheckers = new LinkedHashSet();
        this._backingFieldCheckers = new LinkedHashSet();
        this._valueParameterCheckers = new LinkedHashSet();
        this._enumEntryCheckers = new LinkedHashSet();
        this._anonymousObjectCheckers = new LinkedHashSet();
        this._anonymousInitializerCheckers = new LinkedHashSet();
        this._receiverParameterCheckers = new LinkedHashSet();
        this._controlFlowAnalyserCheckers = new LinkedHashSet();
        this._variableAssignmentCfaBasedCheckers = new LinkedHashSet();
    }

    public static boolean w(MppCheckerKind mppCheckerKind, FirCheckerWithMppKind firCheckerWithMppKind) {
        firCheckerWithMppKind.getClass();
        return firCheckerWithMppKind.getMppKind() == mppCheckerKind;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirAnonymousFunction>> getAnonymousFunctionCheckers() {
        return this._anonymousFunctionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirAnonymousInitializer>> getAnonymousInitializerCheckers() {
        return this._anonymousInitializerCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirAnonymousObject>> getAnonymousObjectCheckers() {
        return this._anonymousObjectCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirBackingField>> getBackingFieldCheckers() {
        return this._backingFieldCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirDeclaration>> getBasicDeclarationCheckers() {
        return this._basicDeclarationCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirCallableDeclaration>> getCallableDeclarationCheckers() {
        return this._callableDeclarationCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirClass>> getClassCheckers() {
        return this._classCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirClassLikeDeclaration>> getClassLikeCheckers() {
        return this._classLikeCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirConstructor>> getConstructorCheckers() {
        return this._constructorCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirControlFlowChecker> getControlFlowAnalyserCheckers() {
        return this._controlFlowAnalyserCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirEnumEntry>> getEnumEntryCheckers() {
        return this._enumEntryCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirFile>> getFileCheckers() {
        return this._fileCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirFunction>> getFunctionCheckers() {
        return this._functionCheckers;
    }

    public final Function1<FirCheckerWithMppKind, Boolean> getPredicate() {
        return this.predicate;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirPropertyAccessor>> getPropertyAccessorCheckers() {
        return this._propertyAccessorCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirProperty>> getPropertyCheckers() {
        return this._propertyCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirReceiverParameter>> getReceiverParameterCheckers() {
        return this._receiverParameterCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirRegularClass>> getRegularClassCheckers() {
        return this._regularClassCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirReplSnippet>> getReplSnippetCheckers() {
        return this._replSnippetCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirScript>> getScriptCheckers() {
        return this._scriptCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirNamedFunction>> getSimpleFunctionCheckers() {
        return this._simpleFunctionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirTypeAlias>> getTypeAliasCheckers() {
        return this._typeAliasCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirTypeParameter>> getTypeParameterCheckers() {
        return this._typeParameterCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirValueParameter>> getValueParameterCheckers() {
        return this._valueParameterCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<AbstractFirPropertyInitializationChecker> getVariableAssignmentCfaBasedCheckers() {
        return this._variableAssignmentCfaBasedCheckers;
    }

    @CheckersComponentInternal
    public final void register(DeclarationCheckers checkers) {
        checkers.getClass();
        Set<FirDeclarationChecker<FirDeclaration>> basicDeclarationCheckers = checkers.getBasicDeclarationCheckers();
        Collection collection = this._basicDeclarationCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function1 = this.predicate;
        for (Object obj : basicDeclarationCheckers) {
            if (((Boolean) function1.invoke(obj)).booleanValue()) {
                collection.add(obj);
            }
        }
        Set<FirDeclarationChecker<FirCallableDeclaration>> callableDeclarationCheckers = checkers.getCallableDeclarationCheckers();
        Collection collection2 = this._callableDeclarationCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function2 = this.predicate;
        for (Object obj2 : callableDeclarationCheckers) {
            if (((Boolean) function2.invoke(obj2)).booleanValue()) {
                collection2.add(obj2);
            }
        }
        Set<FirDeclarationChecker<FirFunction>> functionCheckers = checkers.getFunctionCheckers();
        Collection collection3 = this._functionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function3 = this.predicate;
        for (Object obj3 : functionCheckers) {
            if (((Boolean) function3.invoke(obj3)).booleanValue()) {
                collection3.add(obj3);
            }
        }
        Set<FirDeclarationChecker<FirNamedFunction>> simpleFunctionCheckers = checkers.getSimpleFunctionCheckers();
        Collection collection4 = this._simpleFunctionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function4 = this.predicate;
        for (Object obj4 : simpleFunctionCheckers) {
            if (((Boolean) function4.invoke(obj4)).booleanValue()) {
                collection4.add(obj4);
            }
        }
        Set<FirDeclarationChecker<FirProperty>> propertyCheckers = checkers.getPropertyCheckers();
        Collection collection5 = this._propertyCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function5 = this.predicate;
        for (Object obj5 : propertyCheckers) {
            if (((Boolean) function5.invoke(obj5)).booleanValue()) {
                collection5.add(obj5);
            }
        }
        Set<FirDeclarationChecker<FirClassLikeDeclaration>> classLikeCheckers = checkers.getClassLikeCheckers();
        Collection collection6 = this._classLikeCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function6 = this.predicate;
        for (Object obj6 : classLikeCheckers) {
            if (((Boolean) function6.invoke(obj6)).booleanValue()) {
                collection6.add(obj6);
            }
        }
        Set<FirDeclarationChecker<FirClass>> classCheckers = checkers.getClassCheckers();
        Collection collection7 = this._classCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function7 = this.predicate;
        for (Object obj7 : classCheckers) {
            if (((Boolean) function7.invoke(obj7)).booleanValue()) {
                collection7.add(obj7);
            }
        }
        Set<FirDeclarationChecker<FirRegularClass>> regularClassCheckers = checkers.getRegularClassCheckers();
        Collection collection8 = this._regularClassCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function8 = this.predicate;
        for (Object obj8 : regularClassCheckers) {
            if (((Boolean) function8.invoke(obj8)).booleanValue()) {
                collection8.add(obj8);
            }
        }
        Set<FirDeclarationChecker<FirConstructor>> constructorCheckers = checkers.getConstructorCheckers();
        Collection collection9 = this._constructorCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function9 = this.predicate;
        for (Object obj9 : constructorCheckers) {
            if (((Boolean) function9.invoke(obj9)).booleanValue()) {
                collection9.add(obj9);
            }
        }
        Set<FirDeclarationChecker<FirFile>> fileCheckers = checkers.getFileCheckers();
        Collection collection10 = this._fileCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function10 = this.predicate;
        for (Object obj10 : fileCheckers) {
            if (((Boolean) function10.invoke(obj10)).booleanValue()) {
                collection10.add(obj10);
            }
        }
        Set<FirDeclarationChecker<FirScript>> scriptCheckers = checkers.getScriptCheckers();
        Collection collection11 = this._scriptCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function11 = this.predicate;
        for (Object obj11 : scriptCheckers) {
            if (((Boolean) function11.invoke(obj11)).booleanValue()) {
                collection11.add(obj11);
            }
        }
        Set<FirDeclarationChecker<FirReplSnippet>> replSnippetCheckers = checkers.getReplSnippetCheckers();
        Collection collection12 = this._replSnippetCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function12 = this.predicate;
        for (Object obj12 : replSnippetCheckers) {
            if (((Boolean) function12.invoke(obj12)).booleanValue()) {
                collection12.add(obj12);
            }
        }
        Set<FirDeclarationChecker<FirTypeParameter>> typeParameterCheckers = checkers.getTypeParameterCheckers();
        Collection collection13 = this._typeParameterCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function13 = this.predicate;
        for (Object obj13 : typeParameterCheckers) {
            if (((Boolean) function13.invoke(obj13)).booleanValue()) {
                collection13.add(obj13);
            }
        }
        Set<FirDeclarationChecker<FirTypeAlias>> typeAliasCheckers = checkers.getTypeAliasCheckers();
        Collection collection14 = this._typeAliasCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function14 = this.predicate;
        for (Object obj14 : typeAliasCheckers) {
            if (((Boolean) function14.invoke(obj14)).booleanValue()) {
                collection14.add(obj14);
            }
        }
        Set<FirDeclarationChecker<FirAnonymousFunction>> anonymousFunctionCheckers = checkers.getAnonymousFunctionCheckers();
        Collection collection15 = this._anonymousFunctionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function15 = this.predicate;
        for (Object obj15 : anonymousFunctionCheckers) {
            if (((Boolean) function15.invoke(obj15)).booleanValue()) {
                collection15.add(obj15);
            }
        }
        Set<FirDeclarationChecker<FirPropertyAccessor>> propertyAccessorCheckers = checkers.getPropertyAccessorCheckers();
        Collection collection16 = this._propertyAccessorCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function16 = this.predicate;
        for (Object obj16 : propertyAccessorCheckers) {
            if (((Boolean) function16.invoke(obj16)).booleanValue()) {
                collection16.add(obj16);
            }
        }
        Set<FirDeclarationChecker<FirBackingField>> backingFieldCheckers = checkers.getBackingFieldCheckers();
        Collection collection17 = this._backingFieldCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function17 = this.predicate;
        for (Object obj17 : backingFieldCheckers) {
            if (((Boolean) function17.invoke(obj17)).booleanValue()) {
                collection17.add(obj17);
            }
        }
        Set<FirDeclarationChecker<FirValueParameter>> valueParameterCheckers = checkers.getValueParameterCheckers();
        Collection collection18 = this._valueParameterCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function18 = this.predicate;
        for (Object obj18 : valueParameterCheckers) {
            if (((Boolean) function18.invoke(obj18)).booleanValue()) {
                collection18.add(obj18);
            }
        }
        Set<FirDeclarationChecker<FirEnumEntry>> enumEntryCheckers = checkers.getEnumEntryCheckers();
        Collection collection19 = this._enumEntryCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function19 = this.predicate;
        for (Object obj19 : enumEntryCheckers) {
            if (((Boolean) function19.invoke(obj19)).booleanValue()) {
                collection19.add(obj19);
            }
        }
        Set<FirDeclarationChecker<FirAnonymousObject>> anonymousObjectCheckers = checkers.getAnonymousObjectCheckers();
        Collection collection20 = this._anonymousObjectCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function20 = this.predicate;
        for (Object obj20 : anonymousObjectCheckers) {
            if (((Boolean) function20.invoke(obj20)).booleanValue()) {
                collection20.add(obj20);
            }
        }
        Set<FirDeclarationChecker<FirAnonymousInitializer>> anonymousInitializerCheckers = checkers.getAnonymousInitializerCheckers();
        Collection collection21 = this._anonymousInitializerCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function21 = this.predicate;
        for (Object obj21 : anonymousInitializerCheckers) {
            if (((Boolean) function21.invoke(obj21)).booleanValue()) {
                collection21.add(obj21);
            }
        }
        Set<FirDeclarationChecker<FirReceiverParameter>> receiverParameterCheckers = checkers.getReceiverParameterCheckers();
        Collection collection22 = this._receiverParameterCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function22 = this.predicate;
        for (Object obj22 : receiverParameterCheckers) {
            if (((Boolean) function22.invoke(obj22)).booleanValue()) {
                collection22.add(obj22);
            }
        }
        Set<FirControlFlowChecker> controlFlowAnalyserCheckers = checkers.getControlFlowAnalyserCheckers();
        Collection collection23 = this._controlFlowAnalyserCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function23 = this.predicate;
        for (Object obj23 : controlFlowAnalyserCheckers) {
            if (((Boolean) function23.invoke(obj23)).booleanValue()) {
                collection23.add(obj23);
            }
        }
        Set<AbstractFirPropertyInitializationChecker> variableAssignmentCfaBasedCheckers = checkers.getVariableAssignmentCfaBasedCheckers();
        Collection collection24 = this._variableAssignmentCfaBasedCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function24 = this.predicate;
        for (Object obj24 : variableAssignmentCfaBasedCheckers) {
            if (((Boolean) function24.invoke(obj24)).booleanValue()) {
                collection24.add(obj24);
            }
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ComposedDeclarationCheckers(final MppCheckerKind mppCheckerKind) {
        this((Function1<? super FirCheckerWithMppKind, Boolean>) new Function1() { // from class: pm2
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ComposedDeclarationCheckers.w(mppCheckerKind, (FirCheckerWithMppKind) obj));
            }
        });
        mppCheckerKind.getClass();
    }
}
