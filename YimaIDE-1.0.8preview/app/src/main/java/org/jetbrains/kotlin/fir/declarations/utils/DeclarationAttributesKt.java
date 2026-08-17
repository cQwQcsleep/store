package org.jetbrains.kotlin.fir.declarations.utils;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.SourceFile;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyBackingField;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.impl.FirPropertyFromParameterResolvedNamedReference;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Î\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u000e\u0010\u008f\u0001\u001a\u0005\u0018\u00010\u0090\u0001*\u00020\u0003\u001a\u0012\u0010\u0094\u0001\u001a\t\u0012\u0005\u0012\u00030\u0085\u00010p*\u00020*\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0006\"3\u0010\t\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\b\u001a\u0004\b\t\u0010\u0004\"\u0004\b\n\u0010\u0006\"3\u0010\f\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\b\u001a\u0004\b\r\u0010\u0004\"\u0004\b\u000e\u0010\u0006\"3\u0010\u0011\u001a\u0004\u0018\u00010\u0010*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00108F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0016\u0010\b\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\"3\u0010\u0018\u001a\u0004\u0018\u00010\u0017*\u00020\u00192\b\u0010\u0000\u001a\u0004\u0018\u00010\u00178F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\b\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\"3\u0010 \u001a\u0004\u0018\u00010\u001f*\u00020!2\b\u0010\u0000\u001a\u0004\u0018\u00010\u001f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b&\u0010\b\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%\"K\u0010)\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020(\u0018\u00010'*\u00020*2\u0014\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020(\u0018\u00010'8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b/\u0010\b\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.\"3\u00101\u001a\u0004\u0018\u000100*\u00020*2\b\u0010\u0000\u001a\u0004\u0018\u0001008F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b6\u0010\b\u001a\u0004\b2\u00103\"\u0004\b4\u00105\"3\u00108\u001a\u0004\u0018\u000107*\u0002092\b\u0010\u0000\u001a\u0004\u0018\u0001078F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b>\u0010\b\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=\"3\u0010?\u001a\u0004\u0018\u00010\u001f*\u00020@2\b\u0010\u0000\u001a\u0004\u0018\u00010\u001f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010\b\u001a\u0004\bA\u0010B\"\u0004\bC\u0010D\"3\u0010F\u001a\u0004\u0018\u00010\u0001*\u00020*2\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bJ\u0010\b\u001a\u0004\bF\u0010G\"\u0004\bH\u0010I\"3\u0010K\u001a\u0004\u0018\u00010\u0001*\u00020*2\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bM\u0010\b\u001a\u0004\bK\u0010G\"\u0004\bL\u0010I\"\u001b\u0010K\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030N8F¢\u0006\u0006\u001a\u0004\bK\u0010O\"U\u0010R\u001a\u0010\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020\u0003\u0018\u00010P*\u00020S2\u0014\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020\u0003\u0018\u00010P8F@FX\u0087\u008e\u0002r\u0002\b[¢\u0006\u0018\n\u0004\bZ\u0010\b\u0012\u0004\bT\u0010U\u001a\u0004\bV\u0010W\"\u0004\bX\u0010Y\"=\u0010\\\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0087\u008e\u0002r\u0002\b[¢\u0006\u0018\n\u0004\ba\u0010\b\u0012\u0004\b]\u0010^\u001a\u0004\b_\u0010\u0004\"\u0004\b`\u0010\u0006\"=\u0010b\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0087\u008e\u0002r\u0002\b[¢\u0006\u0018\n\u0004\be\u0010\b\u0012\u0004\bc\u0010^\u001a\u0004\bb\u0010\u0004\"\u0004\bd\u0010\u0006\"3\u0010f\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bh\u0010\b\u001a\u0004\bf\u0010\u0004\"\u0004\bg\u0010\u0006\"3\u0010j\u001a\u0004\u0018\u00010i*\u00020*2\b\u0010\u0000\u001a\u0004\u0018\u00010i8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bo\u0010\b\u001a\u0004\bk\u0010l\"\u0004\bm\u0010n\"?\u0010r\u001a\n\u0012\u0004\u0012\u00020q\u0018\u00010p*\u00020*2\u000e\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020q\u0018\u00010p8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bw\u0010\b\u001a\u0004\bs\u0010t\"\u0004\bu\u0010v\"\u001b\u0010\u0018\u001a\u0004\u0018\u00010\u0017*\u0006\u0012\u0002\b\u00030x8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010y\"\u0015\u0010\f\u001a\u00020\u0001*\u00020Q8F¢\u0006\u0006\u001a\u0004\b\r\u0010z\"\u001d\u0010j\u001a\u0004\u0018\u00010i*\b\u0012\u0004\u0012\u00020*0N8F¢\u0006\u0006\u001a\u0004\bk\u0010{\"!\u0010r\u001a\b\u0012\u0004\u0012\u00020q0p*\b\u0012\u0004\u0012\u00020*0N8F¢\u0006\u0006\u001a\u0004\bs\u0010|\"8\u0010~\u001a\u0004\u0018\u00010}*\u00020\u007f2\b\u0010\u0000\u001a\u0004\u0018\u00010}8F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b\u0084\u0001\u0010\b\u001a\u0006\b\u0080\u0001\u0010\u0081\u0001\"\u0006\b\u0082\u0001\u0010\u0083\u0001\"V\u0010\u0086\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u0085\u0001\u0018\u00010p\"\u000e\b\u0000\u0010\u0087\u0001*\u00020**\u00030\u0088\u0001*\u0003H\u0087\u00012\u000f\u0010\u0000\u001a\u000b\u0012\u0005\u0012\u00030\u0085\u0001\u0018\u00010p8F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u008b\u0001\u0010\b\u001a\u0005\b\u0089\u0001\u0010t\"\u0005\b\u008a\u0001\u0010v\"\u0018\u0010\u008c\u0001\u001a\u00020\u0001*\u00020\u00038F¢\u0006\b\u001a\u0006\b\u008d\u0001\u0010\u008e\u0001\"\u0017\u0010\u008c\u0001\u001a\u00020\u0001*\u00020Q8F¢\u0006\u0007\u001a\u0005\b\u008d\u0001\u0010z\"\u0018\u0010\u0091\u0001\u001a\u00020\u0001*\u00020\u00038F¢\u0006\b\u001a\u0006\b\u0091\u0001\u0010\u008e\u0001\"\u0017\u0010\u0091\u0001\u001a\u00020\u0001*\u00020Q8F¢\u0006\u0007\u001a\u0005\b\u0091\u0001\u0010z\"\u0018\u0010\u0092\u0001\u001a\u00020\u0001*\u00020\u00038F¢\u0006\b\u001a\u0006\b\u0093\u0001\u0010\u008e\u0001\"\u0017\u0010\u0092\u0001\u001a\u00020\u0001*\u00020Q8F¢\u0006\u0007\u001a\u0005\b\u0093\u0001\u0010z\"\u001b\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u0096\u0001*\u00020Q8F¢\u0006\b\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001\"\u001b\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u0096\u0001*\u00020\u00038F¢\u0006\b\u001a\u0006\b\u0097\u0001\u0010\u0099\u0001¨\u0006\u009a\u0001"}, d2 = {"<set-?>", Argument.Delimiters.none, "isFromVararg", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", "setFromVararg", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Ljava/lang/Boolean;)V", "isFromVararg$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "isReferredViaField", "setReferredViaField", "isReferredViaField$delegate", "fromPrimaryConstructor", "getFromPrimaryConstructor", "setFromPrimaryConstructor", "fromPrimaryConstructor$delegate", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "componentFunctionSymbol", "getComponentFunctionSymbol", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "setComponentFunctionSymbol", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)V", "componentFunctionSymbol$delegate", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "sourceElement", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "getSourceElement", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;)Lorg/jetbrains/kotlin/descriptors/SourceElement;", "setSourceElement", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;Lorg/jetbrains/kotlin/descriptors/SourceElement;)V", "sourceElement$delegate", Argument.Delimiters.none, "moduleName", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "getModuleName", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Ljava/lang/String;", "setModuleName", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Ljava/lang/String;)V", "moduleName$delegate", Argument.Delimiters.none, Argument.Delimiters.none, "compilerPluginMetadata", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getCompilerPluginMetadata", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/util/Map;", "setCompilerPluginMetadata", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Ljava/util/Map;)V", "compilerPluginMetadata$delegate", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "originalReplSnippetSymbol", "getOriginalReplSnippetSymbol", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "setOriginalReplSnippetSymbol", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;)V", "originalReplSnippetSymbol$delegate", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "lambdaArgumentParent", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "getLambdaArgumentParent", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "setLambdaArgumentParent", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "lambdaArgumentParent$delegate", "fileNameForPluginGeneratedCallable", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "getFileNameForPluginGeneratedCallable", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Ljava/lang/String;", "setFileNameForPluginGeneratedCallable", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Ljava/lang/String;)V", "fileNameForPluginGeneratedCallable$delegate", "isScriptTopLevelDeclaration", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/Boolean;", "setScriptTopLevelDeclaration", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Ljava/lang/Boolean;)V", "isScriptTopLevelDeclaration$delegate", "isReplSnippetDeclaration", "setReplSnippetDeclaration", "isReplSnippetDeclaration$delegate", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Ljava/lang/Boolean;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "replSnippetDelegatedPropertyCopies", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "getReplSnippetDelegatedPropertyCopies$annotations", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "getReplSnippetDelegatedPropertyCopies", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Ljava/util/Map;", "setReplSnippetDelegatedPropertyCopies", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Ljava/util/Map;)V", "replSnippetDelegatedPropertyCopies$delegate", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "hasBackingFieldAttr", "getHasBackingFieldAttr$annotations", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "getHasBackingFieldAttr", "setHasBackingFieldAttr", "hasBackingFieldAttr$delegate", "isDelegatedPropertyAttr", "isDelegatedPropertyAttr$annotations", "setDelegatedPropertyAttr", "isDelegatedPropertyAttr$delegate", "isDeserializedPropertyFromAnnotation", "setDeserializedPropertyFromAnnotation", "isDeserializedPropertyFromAnnotation$delegate", "Lorg/jetbrains/kotlin/descriptors/SourceFile;", "klibSourceFile", "getKlibSourceFile", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Lorg/jetbrains/kotlin/descriptors/SourceFile;", "setKlibSourceFile", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/descriptors/SourceFile;)V", "klibSourceFile$delegate", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "klibFileAnnotations", "getKlibFileAnnotations", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/util/List;", "setKlibFileAnnotations", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Ljava/util/List;)V", "klibFileAnnotations$delegate", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)Lorg/jetbrains/kotlin/descriptors/SourceElement;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;)Z", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Lorg/jetbrains/kotlin/descriptors/SourceFile;", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Ljava/util/List;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;", "evaluatedInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "getEvaluatedInitializer", "(Lorg/jetbrains/kotlin/fir/declarations/FirVariable;)Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;", "setEvaluatedInitializer", "(Lorg/jetbrains/kotlin/fir/declarations/FirVariable;Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;)V", "evaluatedInitializer$delegate", "Lorg/jetbrains/kotlin/fir/declarations/utils/DanglingTypeConstraint;", "danglingTypeConstraints", "T", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;", "getDanglingTypeConstraints", "setDanglingTypeConstraints", "danglingTypeConstraints$delegate", "hasExplicitBackingField", "getHasExplicitBackingField", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Z", "getExplicitBackingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "isDelegatedProperty", "hasBackingField", "getHasBackingField", "getDanglingTypeConstraintsOrEmpty", "correspondingValueParameterFromPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "getCorrespondingValueParameterFromPrimaryConstructor", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeclarationAttributesKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "isFromVararg", "isFromVararg(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "isReferredViaField", "isReferredViaField(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "fromPrimaryConstructor", "getFromPrimaryConstructor(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "componentFunctionSymbol", "getComponentFunctionSymbol(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "sourceElement", "getSourceElement(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;)Lorg/jetbrains/kotlin/descriptors/SourceElement;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "moduleName", "getModuleName(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Ljava/lang/String;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "compilerPluginMetadata", "getCompilerPluginMetadata(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/util/Map;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "originalReplSnippetSymbol", "getOriginalReplSnippetSymbol(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "lambdaArgumentParent", "getLambdaArgumentParent(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "fileNameForPluginGeneratedCallable", "getFileNameForPluginGeneratedCallable(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Ljava/lang/String;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "isScriptTopLevelDeclaration", "isScriptTopLevelDeclaration(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "isReplSnippetDeclaration", "isReplSnippetDeclaration(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "replSnippetDelegatedPropertyCopies", "getReplSnippetDelegatedPropertyCopies(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Ljava/util/Map;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "hasBackingFieldAttr", "getHasBackingFieldAttr(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "isDelegatedPropertyAttr", "isDelegatedPropertyAttr(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "isDeserializedPropertyFromAnnotation", "isDeserializedPropertyFromAnnotation(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "klibSourceFile", "getKlibSourceFile(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Lorg/jetbrains/kotlin/descriptors/SourceFile;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "klibFileAnnotations", "getKlibFileAnnotations(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/util/List;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "evaluatedInitializer", "getEvaluatedInitializer(Lorg/jetbrains/kotlin/fir/declarations/FirVariable;)Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;", 1), new MutablePropertyReference1Impl<>(DeclarationAttributesKt.class, "danglingTypeConstraints", "getDanglingTypeConstraints(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/util/List;", 1)};
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor compilerPluginMetadata$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor componentFunctionSymbol$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor danglingTypeConstraints$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor evaluatedInitializer$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor fileNameForPluginGeneratedCallable$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor fromPrimaryConstructor$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor hasBackingFieldAttr$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isDelegatedPropertyAttr$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isDeserializedPropertyFromAnnotation$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isFromVararg$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isReferredViaField$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isReplSnippetDeclaration$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isScriptTopLevelDeclaration$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor klibFileAnnotations$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor klibSourceFile$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor lambdaArgumentParent$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor moduleName$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor originalReplSnippetSymbol$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor replSnippetDelegatedPropertyCopies$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor sourceElement$delegate;

    static {
        FirDeclarationDataRegistry firDeclarationDataRegistry = FirDeclarationDataRegistry.INSTANCE;
        isFromVararg$delegate = firDeclarationDataRegistry.data(IsFromVarargKey.INSTANCE);
        isReferredViaField$delegate = firDeclarationDataRegistry.data(IsReferredViaField.INSTANCE);
        fromPrimaryConstructor$delegate = firDeclarationDataRegistry.data(IsFromPrimaryConstructor.INSTANCE);
        componentFunctionSymbol$delegate = firDeclarationDataRegistry.data(ComponentFunctionSymbolKey.INSTANCE);
        sourceElement$delegate = firDeclarationDataRegistry.data(SourceElementKey.INSTANCE);
        moduleName$delegate = firDeclarationDataRegistry.data(ModuleNameKey.INSTANCE);
        compilerPluginMetadata$delegate = firDeclarationDataRegistry.data(CompilerPluginMetadata.INSTANCE);
        originalReplSnippetSymbol$delegate = firDeclarationDataRegistry.data(OriginalReplSnippet.INSTANCE);
        lambdaArgumentParent$delegate = firDeclarationDataRegistry.data(LambdaArgumentHoldsInTruths.INSTANCE);
        fileNameForPluginGeneratedCallable$delegate = firDeclarationDataRegistry.data(FileNameForPluginGeneratedCallable.INSTANCE);
        isScriptTopLevelDeclaration$delegate = firDeclarationDataRegistry.data(ScriptTopLevelDeclaration.INSTANCE);
        isReplSnippetDeclaration$delegate = firDeclarationDataRegistry.data(ReplSnippetTopLevelDeclaration.INSTANCE);
        replSnippetDelegatedPropertyCopies$delegate = firDeclarationDataRegistry.data(ReplPropertyCopy.INSTANCE);
        hasBackingFieldAttr$delegate = firDeclarationDataRegistry.data(HasBackingFieldKey.INSTANCE);
        isDelegatedPropertyAttr$delegate = firDeclarationDataRegistry.data(IsDelegatedProperty.INSTANCE);
        isDeserializedPropertyFromAnnotation$delegate = firDeclarationDataRegistry.data(IsDeserializedPropertyFromAnnotation.INSTANCE);
        klibSourceFile$delegate = firDeclarationDataRegistry.data(KlibSourceFile.INSTANCE);
        klibFileAnnotations$delegate = firDeclarationDataRegistry.data(KlibFileAnnotationsKey.INSTANCE);
        evaluatedInitializer$delegate = firDeclarationDataRegistry.data(EvaluatedValue.INSTANCE);
        danglingTypeConstraints$delegate = firDeclarationDataRegistry.data(DanglingTypeConstraintsKey.INSTANCE);
    }

    public static final Map<String, byte[]> getCompilerPluginMetadata(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return (Map) compilerPluginMetadata$delegate.getValue(firDeclaration, $$delegatedProperties[6]);
    }

    public static final FirNamedFunctionSymbol getComponentFunctionSymbol(FirProperty firProperty) {
        firProperty.getClass();
        return (FirNamedFunctionSymbol) componentFunctionSymbol$delegate.getValue(firProperty, $$delegatedProperties[3]);
    }

    public static final FirValueParameterSymbol getCorrespondingValueParameterFromPrimaryConstructor(FirProperty firProperty) {
        firProperty.getClass();
        if (!Intrinsics.areEqual(getFromPrimaryConstructor(firProperty), Boolean.TRUE)) {
            return null;
        }
        FirExpression initializer = firProperty.getInitializer();
        FirPropertyAccessExpression firPropertyAccessExpression = initializer instanceof FirPropertyAccessExpression ? (FirPropertyAccessExpression) initializer : null;
        if (firPropertyAccessExpression == null) {
            return null;
        }
        FirNamedReference calleeReference = firPropertyAccessExpression.getCalleeReference();
        FirPropertyFromParameterResolvedNamedReference firPropertyFromParameterResolvedNamedReference = calleeReference instanceof FirPropertyFromParameterResolvedNamedReference ? (FirPropertyFromParameterResolvedNamedReference) calleeReference : null;
        if (firPropertyFromParameterResolvedNamedReference == null) {
            return null;
        }
        FirBasedSymbol<?> resolvedSymbol = firPropertyFromParameterResolvedNamedReference.getResolvedSymbol();
        if (resolvedSymbol instanceof FirValueParameterSymbol) {
            return (FirValueParameterSymbol) resolvedSymbol;
        }
        return null;
    }

    public static final <T extends FirDeclaration & FirTypeParameterRefsOwner> List<DanglingTypeConstraint> getDanglingTypeConstraints(T t) {
        t.getClass();
        return (List) danglingTypeConstraints$delegate.getValue(t, $$delegatedProperties[19]);
    }

    public static final List<DanglingTypeConstraint> getDanglingTypeConstraintsOrEmpty(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        List<DanglingTypeConstraint> danglingTypeConstraints = ((firDeclaration instanceof FirRegularClass) || (firDeclaration instanceof FirNamedFunction) || (firDeclaration instanceof FirAnonymousFunction) || (firDeclaration instanceof FirProperty)) ? getDanglingTypeConstraints(firDeclaration) : null;
        return danglingTypeConstraints == null ? CollectionsKt.emptyList() : danglingTypeConstraints;
    }

    public static final FirEvaluatorResult getEvaluatedInitializer(FirVariable firVariable) {
        firVariable.getClass();
        return (FirEvaluatorResult) evaluatedInitializer$delegate.getValue(firVariable, $$delegatedProperties[18]);
    }

    public static final FirBackingField getExplicitBackingField(FirProperty firProperty) {
        firProperty.getClass();
        if (getHasExplicitBackingField(firProperty)) {
            return firProperty.getBackingField();
        }
        return null;
    }

    public static final String getFileNameForPluginGeneratedCallable(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return (String) fileNameForPluginGeneratedCallable$delegate.getValue(firCallableDeclaration, $$delegatedProperties[9]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean getFromPrimaryConstructor(FirPropertySymbol firPropertySymbol) {
        firPropertySymbol.getClass();
        Boolean fromPrimaryConstructor = getFromPrimaryConstructor((FirProperty) firPropertySymbol.getFir());
        if (fromPrimaryConstructor != null) {
            return fromPrimaryConstructor.booleanValue();
        }
        return false;
    }

    public static final boolean getHasBackingField(FirProperty firProperty) {
        FirPropertyAccessor setter;
        firProperty.getClass();
        Boolean hasBackingFieldAttr = getHasBackingFieldAttr(firProperty);
        if (hasBackingFieldAttr != null) {
            return hasBackingFieldAttr.booleanValue();
        }
        Modality modality = firProperty.getStatus().getModality();
        Modality modality2 = Modality.ABSTRACT;
        if (modality == modality2 || firProperty.getStatus().isExpect() || firProperty.getDelegate() != null) {
            return false;
        }
        if (getHasExplicitBackingField(firProperty)) {
            return true;
        }
        if (firProperty.getSymbol() instanceof FirSyntheticPropertySymbol) {
            return false;
        }
        FirDeclarationOrigin origin = firProperty.getOrigin();
        if ((origin instanceof FirDeclarationOrigin.SubstitutionOverride) || Intrinsics.areEqual(origin, FirDeclarationOrigin.IntersectionOverride.INSTANCE) || Intrinsics.areEqual(origin, FirDeclarationOrigin.Delegated.INSTANCE)) {
            return false;
        }
        FirPropertyAccessor getter = firProperty.getGetter();
        if (getter == null) {
            return true;
        }
        if (firProperty.getIsVar() && firProperty.getSetter() == null) {
            return true;
        }
        FirPropertyAccessor setter2 = firProperty.getSetter();
        if (setter2 != null && setter2.getBody() == null && (setter = firProperty.getSetter()) != null && setter.getStatus().getModality() != modality2) {
            return true;
        }
        if (getter.getBody() == null && getter.getStatus().getModality() != modality2) {
            return true;
        }
        return Intrinsics.areEqual(isReferredViaField(firProperty), Boolean.TRUE);
    }

    public static final Boolean getHasBackingFieldAttr(FirProperty firProperty) {
        firProperty.getClass();
        return (Boolean) hasBackingFieldAttr$delegate.getValue(firProperty, $$delegatedProperties[13]);
    }

    @FirImplementationDetail
    public static /* synthetic */ void getHasBackingFieldAttr$annotations(FirProperty firProperty) {
    }

    public static final boolean getHasExplicitBackingField(FirProperty firProperty) {
        firProperty.getClass();
        return (firProperty.getBackingField() == null || (firProperty.getBackingField() instanceof FirDefaultPropertyBackingField)) ? false : true;
    }

    public static final List<FirAnnotation> getKlibFileAnnotations(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return (List) klibFileAnnotations$delegate.getValue(firDeclaration, $$delegatedProperties[17]);
    }

    public static final SourceFile getKlibSourceFile(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return (SourceFile) klibSourceFile$delegate.getValue(firDeclaration, $$delegatedProperties[16]);
    }

    public static final FirQualifiedAccessExpression getLambdaArgumentParent(FirAnonymousFunction firAnonymousFunction) {
        firAnonymousFunction.getClass();
        return (FirQualifiedAccessExpression) lambdaArgumentParent$delegate.getValue(firAnonymousFunction, $$delegatedProperties[8]);
    }

    public static final String getModuleName(FirRegularClass firRegularClass) {
        firRegularClass.getClass();
        return (String) moduleName$delegate.getValue(firRegularClass, $$delegatedProperties[5]);
    }

    public static final FirReplSnippetSymbol getOriginalReplSnippetSymbol(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return (FirReplSnippetSymbol) originalReplSnippetSymbol$delegate.getValue(firDeclaration, $$delegatedProperties[7]);
    }

    public static final Map<FirPropertySymbol, FirProperty> getReplSnippetDelegatedPropertyCopies(FirFunction firFunction) {
        firFunction.getClass();
        return (Map) replSnippetDelegatedPropertyCopies$delegate.getValue(firFunction, $$delegatedProperties[12]);
    }

    @FirImplementationDetail
    public static /* synthetic */ void getReplSnippetDelegatedPropertyCopies$annotations(FirFunction firFunction) {
    }

    public static final SourceElement getSourceElement(FirClassLikeDeclaration firClassLikeDeclaration) {
        firClassLikeDeclaration.getClass();
        return (SourceElement) sourceElement$delegate.getValue(firClassLikeDeclaration, $$delegatedProperties[4]);
    }

    public static final boolean isDelegatedProperty(FirProperty firProperty) {
        firProperty.getClass();
        Boolean boolIsDelegatedPropertyAttr = isDelegatedPropertyAttr(firProperty);
        if (boolIsDelegatedPropertyAttr != null) {
            return boolIsDelegatedPropertyAttr.booleanValue();
        }
        return firProperty.getDelegate() != null;
    }

    public static final Boolean isDelegatedPropertyAttr(FirProperty firProperty) {
        firProperty.getClass();
        return (Boolean) isDelegatedPropertyAttr$delegate.getValue(firProperty, $$delegatedProperties[14]);
    }

    @FirImplementationDetail
    public static /* synthetic */ void isDelegatedPropertyAttr$annotations(FirProperty firProperty) {
    }

    public static final Boolean isDeserializedPropertyFromAnnotation(FirProperty firProperty) {
        firProperty.getClass();
        return (Boolean) isDeserializedPropertyFromAnnotation$delegate.getValue(firProperty, $$delegatedProperties[15]);
    }

    public static final Boolean isFromVararg(FirProperty firProperty) {
        firProperty.getClass();
        return (Boolean) isFromVararg$delegate.getValue(firProperty, $$delegatedProperties[0]);
    }

    public static final Boolean isReferredViaField(FirProperty firProperty) {
        firProperty.getClass();
        return (Boolean) isReferredViaField$delegate.getValue(firProperty, $$delegatedProperties[1]);
    }

    public static final Boolean isReplSnippetDeclaration(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return (Boolean) isReplSnippetDeclaration$delegate.getValue(firDeclaration, $$delegatedProperties[11]);
    }

    public static final Boolean isScriptTopLevelDeclaration(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return (Boolean) isScriptTopLevelDeclaration$delegate.getValue(firDeclaration, $$delegatedProperties[10]);
    }

    public static final void setCompilerPluginMetadata(FirDeclaration firDeclaration, Map<String, byte[]> map) {
        firDeclaration.getClass();
        compilerPluginMetadata$delegate.setValue(firDeclaration, $$delegatedProperties[6], map);
    }

    public static final void setComponentFunctionSymbol(FirProperty firProperty, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firProperty.getClass();
        componentFunctionSymbol$delegate.setValue(firProperty, $$delegatedProperties[3], firNamedFunctionSymbol);
    }

    public static final <T extends FirDeclaration & FirTypeParameterRefsOwner> void setDanglingTypeConstraints(T t, List<DanglingTypeConstraint> list) {
        t.getClass();
        danglingTypeConstraints$delegate.setValue(t, $$delegatedProperties[19], list);
    }

    public static final void setDelegatedPropertyAttr(FirProperty firProperty, Boolean bool) {
        firProperty.getClass();
        isDelegatedPropertyAttr$delegate.setValue(firProperty, $$delegatedProperties[14], bool);
    }

    public static final void setDeserializedPropertyFromAnnotation(FirProperty firProperty, Boolean bool) {
        firProperty.getClass();
        isDeserializedPropertyFromAnnotation$delegate.setValue(firProperty, $$delegatedProperties[15], bool);
    }

    public static final void setEvaluatedInitializer(FirVariable firVariable, FirEvaluatorResult firEvaluatorResult) {
        firVariable.getClass();
        evaluatedInitializer$delegate.setValue(firVariable, $$delegatedProperties[18], firEvaluatorResult);
    }

    public static final void setFileNameForPluginGeneratedCallable(FirCallableDeclaration firCallableDeclaration, String str) {
        firCallableDeclaration.getClass();
        fileNameForPluginGeneratedCallable$delegate.setValue(firCallableDeclaration, $$delegatedProperties[9], str);
    }

    public static final void setFromPrimaryConstructor(FirProperty firProperty, Boolean bool) {
        firProperty.getClass();
        fromPrimaryConstructor$delegate.setValue(firProperty, $$delegatedProperties[2], bool);
    }

    public static final void setFromVararg(FirProperty firProperty, Boolean bool) {
        firProperty.getClass();
        isFromVararg$delegate.setValue(firProperty, $$delegatedProperties[0], bool);
    }

    public static final void setHasBackingFieldAttr(FirProperty firProperty, Boolean bool) {
        firProperty.getClass();
        hasBackingFieldAttr$delegate.setValue(firProperty, $$delegatedProperties[13], bool);
    }

    public static final void setKlibFileAnnotations(FirDeclaration firDeclaration, List<? extends FirAnnotation> list) {
        firDeclaration.getClass();
        klibFileAnnotations$delegate.setValue(firDeclaration, $$delegatedProperties[17], list);
    }

    public static final void setKlibSourceFile(FirDeclaration firDeclaration, SourceFile sourceFile) {
        firDeclaration.getClass();
        klibSourceFile$delegate.setValue(firDeclaration, $$delegatedProperties[16], sourceFile);
    }

    public static final void setLambdaArgumentParent(FirAnonymousFunction firAnonymousFunction, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        firAnonymousFunction.getClass();
        lambdaArgumentParent$delegate.setValue(firAnonymousFunction, $$delegatedProperties[8], firQualifiedAccessExpression);
    }

    public static final void setModuleName(FirRegularClass firRegularClass, String str) {
        firRegularClass.getClass();
        moduleName$delegate.setValue(firRegularClass, $$delegatedProperties[5], str);
    }

    public static final void setOriginalReplSnippetSymbol(FirDeclaration firDeclaration, FirReplSnippetSymbol firReplSnippetSymbol) {
        firDeclaration.getClass();
        originalReplSnippetSymbol$delegate.setValue(firDeclaration, $$delegatedProperties[7], firReplSnippetSymbol);
    }

    public static final void setReferredViaField(FirProperty firProperty, Boolean bool) {
        firProperty.getClass();
        isReferredViaField$delegate.setValue(firProperty, $$delegatedProperties[1], bool);
    }

    public static final void setReplSnippetDeclaration(FirDeclaration firDeclaration, Boolean bool) {
        firDeclaration.getClass();
        isReplSnippetDeclaration$delegate.setValue(firDeclaration, $$delegatedProperties[11], bool);
    }

    public static final void setReplSnippetDelegatedPropertyCopies(FirFunction firFunction, Map<FirPropertySymbol, FirProperty> map) {
        firFunction.getClass();
        replSnippetDelegatedPropertyCopies$delegate.setValue(firFunction, $$delegatedProperties[12], map);
    }

    public static final void setScriptTopLevelDeclaration(FirDeclaration firDeclaration, Boolean bool) {
        firDeclaration.getClass();
        isScriptTopLevelDeclaration$delegate.setValue(firDeclaration, $$delegatedProperties[10], bool);
    }

    public static final void setSourceElement(FirClassLikeDeclaration firClassLikeDeclaration, SourceElement sourceElement) {
        firClassLikeDeclaration.getClass();
        sourceElement$delegate.setValue(firClassLikeDeclaration, $$delegatedProperties[4], sourceElement);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final SourceElement getSourceElement(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        return getSourceElement((FirClassLikeDeclaration) firClassLikeSymbol.getFir());
    }

    public static final List<FirAnnotation> getKlibFileAnnotations(FirBasedSymbol<? extends FirDeclaration> firBasedSymbol) {
        firBasedSymbol.getClass();
        List<FirAnnotation> klibFileAnnotations = getKlibFileAnnotations(firBasedSymbol.getFir());
        return klibFileAnnotations == null ? CollectionsKt.emptyList() : klibFileAnnotations;
    }

    public static final SourceFile getKlibSourceFile(FirBasedSymbol<? extends FirDeclaration> firBasedSymbol) {
        firBasedSymbol.getClass();
        return getKlibSourceFile(firBasedSymbol.getFir());
    }

    public static final Boolean isReplSnippetDeclaration(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        return isReplSnippetDeclaration(firBasedSymbol.getFir());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean getHasExplicitBackingField(FirPropertySymbol firPropertySymbol) {
        firPropertySymbol.getClass();
        return getHasExplicitBackingField((FirProperty) firPropertySymbol.getFir());
    }

    public static final Boolean getFromPrimaryConstructor(FirProperty firProperty) {
        firProperty.getClass();
        return (Boolean) fromPrimaryConstructor$delegate.getValue(firProperty, $$delegatedProperties[2]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isDelegatedProperty(FirPropertySymbol firPropertySymbol) {
        firPropertySymbol.getClass();
        return isDelegatedProperty((FirProperty) firPropertySymbol.getFir());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirValueParameterSymbol getCorrespondingValueParameterFromPrimaryConstructor(FirPropertySymbol firPropertySymbol) {
        firPropertySymbol.getClass();
        return getCorrespondingValueParameterFromPrimaryConstructor((FirProperty) firPropertySymbol.getFir());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean getHasBackingField(FirPropertySymbol firPropertySymbol) {
        firPropertySymbol.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firPropertySymbol, FirResolvePhase.BODY_RESOLVE);
        return getHasBackingField((FirProperty) firPropertySymbol.getFir());
    }
}
