package org.jetbrains.kotlin.fir.backend.generators;

import com.intellij.psi.tree.TokenSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.Fir2IrBuiltinSymbolsContainer;
import org.jetbrains.kotlin.fir.backend.Fir2IrClassifierStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrConfiguration;
import org.jetbrains.kotlin.fir.backend.Fir2IrConversionScope;
import org.jetbrains.kotlin.fir.backend.Fir2IrConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.Fir2IrImplicitCastInserter;
import org.jetbrains.kotlin.fir.backend.Fir2IrIrGeneratedDeclarationsRegistrar;
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClasses;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverterKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisitor;
import org.jetbrains.kotlin.fir.backend.FirDeclarationsContentCleaner;
import org.jetbrains.kotlin.fir.backend.FirProviderWithGeneratedFiles;
import org.jetbrains.kotlin.fir.backend.generators.ClassMemberGenerator;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.backend.utils.OffsetUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DestructuringDeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.comparators.FirCallableDeclarationComparator;
import org.jetbrains.kotlin.fir.declarations.comparators.FirMemberDeclarationComparator;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyGetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertySetter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirReplExpressionReference;
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtensionKt;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.extensions.GeneratedDeclarationsUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.builders.Scope;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrFactoryHelpersKt;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrMutableAnnotationContainer;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrScript;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.expressions.IrBlockBody;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.expressions.IrFieldAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrSyntheticBodyKind;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrDelegatingConstructorCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrSetFieldImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.resolve.DataClassResolver;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009a\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000b\u001a\u0002H\f\"\b\b\u0000\u0010\f*\u00020\r2\u0006\u0010\u000e\u001a\u0002H\fH\u0002¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J1\u0010\u0016\u001a\u0002H\f\"\b\b\u0000\u0010\f*\u00020\u00172\u0006\u0010\u0018\u001a\u0002H\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0015¢\u0006\u0002\u0010\u001cJ\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001e*\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#J\u0016\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020(J\u001e\u0010)\u001a\u00020\u0011*\u00020 2\u0006\u0010\"\u001a\u00020#2\b\u0010*\u001a\u0004\u0018\u00010+H\u0002J.\u0010,\u001a\u00020\u0011*\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/2\u0006\u00100\u001a\u00020 2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0002J\u0014\u00105\u001a\u000206*\u0002062\u0006\u0010\u000e\u001a\u00020\rH\u0002J!\u00107\u001a\u000208*\u0002092\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;H\u0000¢\u0006\u0002\b=J\u0014\u0010>\u001a\u00020\u0011*\u00020?2\u0006\u0010@\u001a\u00020AH\u0002R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010B\u001a\u000204*\u00020C8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bB\u0010DR\u0018\u0010E\u001a\u000204*\u00020C8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bE\u0010DR\u0012\u0010F\u001a\u00020GX\u0096\u0005¢\u0006\u0006\u001a\u0004\bH\u0010IR\u0012\u0010J\u001a\u00020KX\u0096\u0005¢\u0006\u0006\u001a\u0004\bL\u0010MR\u0012\u0010N\u001a\u00020OX\u0096\u0005¢\u0006\u0006\u001a\u0004\bP\u0010QR\u0012\u0010R\u001a\u00020SX\u0096\u0005¢\u0006\u0006\u001a\u0004\bT\u0010UR\u0012\u0010V\u001a\u00020WX\u0096\u0005¢\u0006\u0006\u001a\u0004\bX\u0010YR\u0012\u0010Z\u001a\u00020[X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\\\u0010]R\u0012\u0010^\u001a\u00020_X\u0096\u0005¢\u0006\u0006\u001a\u0004\b`\u0010aR\u0012\u0010b\u001a\u00020cX\u0096\u0005¢\u0006\u0006\u001a\u0004\bd\u0010eR\u0012\u0010f\u001a\u00020gX\u0096\u0005¢\u0006\u0006\u001a\u0004\bh\u0010iR\u0012\u0010j\u001a\u00020kX\u0096\u0005¢\u0006\u0006\u001a\u0004\bl\u0010mR\u0012\u0010n\u001a\u00020oX\u0096\u0005¢\u0006\u0006\u001a\u0004\bp\u0010qR\u0012\u0010r\u001a\u00020sX\u0096\u0005¢\u0006\u0006\u001a\u0004\bt\u0010uR\u0012\u0010v\u001a\u00020wX\u0096\u0005¢\u0006\u0006\u001a\u0004\bx\u0010yR\u001a\u0010z\u001a\n\u0012\u0004\u0012\u00020|\u0018\u00010{X\u0096\u0005¢\u0006\u0006\u001a\u0004\b}\u0010~R\u0015\u0010\u007f\u001a\u00030\u0080\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001R\u0016\u0010\u0083\u0001\u001a\u00030\u0084\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0016\u0010\u0087\u0001\u001a\u00030\u0088\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0089\u0001\u0010\u008a\u0001R\u001d\u0010\u008b\u0001\u001a\n\u0012\u0005\u0012\u00030\u008d\u00010\u008c\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0016\u0010\u0090\u0001\u001a\u00030\u0091\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001R\u0016\u0010\u0094\u0001\u001a\u00030\u0095\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001R\u0016\u0010\u0098\u0001\u001a\u00030\u0099\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009a\u0001\u0010\u009b\u0001R\u0016\u0010\u009c\u0001\u001a\u00030\u009d\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009e\u0001\u0010\u009f\u0001R\u0016\u0010 \u0001\u001a\u00030¡\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¢\u0001\u0010£\u0001R\u0018\u0010¤\u0001\u001a\u0005\u0018\u00010¥\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¦\u0001\u0010§\u0001R\u0016\u0010¨\u0001\u001a\u00030©\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bª\u0001\u0010«\u0001R\u0016\u0010¬\u0001\u001a\u00030\u00ad\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b®\u0001\u0010¯\u0001R\u0016\u0010°\u0001\u001a\u00030±\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b²\u0001\u0010³\u0001¨\u0006´\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/generators/ClassMemberGenerator;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "visitor", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisitor;", "conversionScope", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;", "cleaner", "Lorg/jetbrains/kotlin/fir/backend/FirDeclarationsContentCleaner;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisitor;Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;Lorg/jetbrains/kotlin/fir/backend/FirDeclarationsContentCleaner;)V", "applyParentFromStackTo", "T", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "declaration", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;)Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "convertClassContent", Argument.Delimiters.none, "irClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "convertFunctionContent", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "irFunction", "firFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "containingClass", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "convertBody", "Lorg/jetbrains/kotlin/ir/expressions/IrBlockBody;", "convertPropertyContent", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "irProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "convertFieldContent", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "irField", "field", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "initializeBackingField", "initializerExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setPropertyAccessorContent", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "correspondingProperty", "fieldType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "isDefault", Argument.Delimiters.none, "setReceiver", "Lorg/jetbrains/kotlin/ir/expressions/IrFieldAccessExpression;", "toIrDelegatingConstructorCall", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "startOffset", Argument.Delimiters.none, "endOffset", "toIrDelegatingConstructorCall$org_jetbrains_kotlin_fir2ir", "setDefaultValue", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "firValueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "isAnnotationConstructor", "Lorg/jetbrains/kotlin/ir/IrElement;", "(Lorg/jetbrains/kotlin/ir/IrElement;)Z", "isDataClassCopy", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClassMemberGenerator implements Fir2IrComponents {
    private final Fir2IrComponents c;
    private final FirDeclarationsContentCleaner cleaner;
    private final Fir2IrConversionScope conversionScope;
    private final Fir2IrVisitor visitor;

    public ClassMemberGenerator(Fir2IrComponents fir2IrComponents, Fir2IrVisitor fir2IrVisitor, Fir2IrConversionScope fir2IrConversionScope, FirDeclarationsContentCleaner firDeclarationsContentCleaner) {
        fir2IrComponents.getClass();
        fir2IrVisitor.getClass();
        fir2IrConversionScope.getClass();
        firDeclarationsContentCleaner.getClass();
        this.c = fir2IrComponents;
        this.visitor = fir2IrVisitor;
        this.conversionScope = fir2IrConversionScope;
        this.cleaner = firDeclarationsContentCleaner;
    }

    private final <T extends IrDeclaration> T applyParentFromStackTo(T declaration) {
        return (T) this.conversionScope.applyParentFromStackTo(declaration);
    }

    private final IrBlockBody convertBody(IrFunction irFunction, FirFunction firFunction) {
        FirBlock body = firFunction != null ? firFunction.getBody() : null;
        if (body == null) {
            return null;
        }
        if (!getConfiguration().getSkipBodies()) {
            return this.visitor.convertToIrBlockBody$org_jetbrains_kotlin_fir2ir(body);
        }
        IrBlockBody irBlockBodyCreateBlockBody = irFunction.getFactory().createBlockBody(irFunction.getStartOffset(), irFunction.getEndOffset());
        irBlockBodyCreateBlockBody.getStatements().add(new IrReturnImpl(irFunction.getStartOffset(), irFunction.getEndOffset(), getBuiltins().getNothingType(), irFunction.getSymbol(), BuildersKt.IrErrorExpressionImpl(irFunction.getStartOffset(), irFunction.getEndOffset(), getBuiltins().getNothingType(), "skipBodies")));
        return irBlockBodyCreateBlockBody;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:43:0x0085  */
    public static final IrExpression convertFunctionContent$lambda$0$3(FirFunction firFunction, FirClass firClass, FirDelegatedConstructorCall firDelegatedConstructorCall, ClassMemberGenerator classMemberGenerator) {
        FirElement firElement;
        TokenSet tokenSet;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        if (((FirConstructor) firFunction).getIsPrimary()) {
            firClass.getClass();
            firElement = firClass;
        } else {
            firElement = firDelegatedConstructorCall;
        }
        if (firElement instanceof FirNamedFunction) {
            tokenSet = OffsetUtilsKt.FUNCTION_KEYWORD_TOKENS;
        } else if (firElement instanceof FirConstructor) {
            tokenSet = OffsetUtilsKt.CONSTRUCTOR_KEYWORD_TOKENS;
        } else {
            tokenSet = firElement instanceof FirVariable ? KtTokens.VAL_VAR : null;
        }
        KtSourceElement source = firElement.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, tokenSet)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        i = startOffset;
                        endOffset = source != null ? source.getEndOffset() : -1;
                    }
                }
            }
        }
        return classMemberGenerator.toIrDelegatingConstructorCall$org_jetbrains_kotlin_fir2ir(firDelegatedConstructorCall, i, endOffset);
    }

    private final void initializeBackingField(IrProperty irProperty, FirProperty firProperty, FirExpression firExpression) {
        IrDeclarationParent backingField = irProperty.getBackingField();
        if (backingField == null) {
            return;
        }
        IrClass parent = backingField.getParent();
        IrClass irClass = parent instanceof IrClass ? parent : null;
        boolean z = (irClass != null ? irClass.getKind() : null) == ClassKind.ANNOTATION_CLASS;
        if (!getConfiguration().getSkipBodies() || z || firProperty.getStatus().isConst()) {
            Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
            fir2IrConversionScope.get_parentStack().add(backingField);
            fir2IrConversionScope.getScopeStack().add(new Scope(backingField.getSymbol()));
            try {
                getDeclarationStorage().enterScope(irProperty.getSymbol());
                if (backingField.getInitializer() == null && firExpression != null) {
                    IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
                    boolean z2 = firProperty.getDelegate() != null;
                    backingField.setInitializer(IrFactoryHelpersKt.createExpressionBody(irFactoryImpl, this.visitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir(firExpression, z2, z2 ? null : FirTypeUtilsKt.getConeType(firProperty.getReturnTypeRef()))));
                }
                getDeclarationStorage().leaveScope(irProperty.getSymbol());
                Unit unit = Unit.INSTANCE;
                fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
                fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
            } catch (Throwable th) {
                fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
                fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
                throw th;
            }
        }
        FirBackingField backingField2 = firProperty.getBackingField();
        if (backingField2 != null) {
            getAnnotationGenerator().generate((IrField) backingField, backingField2);
        }
    }

    private final boolean isAnnotationConstructor(IrElement irElement) {
        return (irElement instanceof IrConstructor) && IrUtilsKt.isAnnotationClass(IrUtilsKt.getParentAsClass((IrDeclaration) irElement));
    }

    private final boolean isDataClassCopy(IrElement irElement) {
        if (!(irElement instanceof IrSimpleFunction)) {
            return false;
        }
        IrSimpleFunction irSimpleFunction = (IrSimpleFunction) irElement;
        if (!DataClassResolver.INSTANCE.isCopy(irSimpleFunction.getName())) {
            return false;
        }
        IrClass parent = irSimpleFunction.getParent();
        return (parent instanceof IrClass) && parent.isData();
    }

    /* JADX WARN: Code duplicated, block: B:38:0x007f  */
    private final void setDefaultValue(IrValueParameter irValueParameter, FirValueParameter firValueParameter) {
        FirExpression defaultValue;
        IrExpressionBody irExpressionBodyCreateExpressionBody = null;
        if (((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).getFlag(AnalysisFlags.getStdlibCompilation())).booleanValue() && FirModuleDataKt.getModuleData(getSession()).getIsCommon()) {
            IrConstructor irConstructorParent = this.conversionScope.parent();
            IrConstructor irConstructor = irConstructorParent instanceof IrConstructor ? irConstructorParent : null;
            IrDeclarationParent parent = irConstructor != null ? irConstructor.getParent() : null;
            IrClass irClass = parent instanceof IrClass ? (IrClass) parent : null;
            if (Intrinsics.areEqual(irClass != null ? AdditionalIrUtilsKt.getClassId(irClass) : null, StandardClassIds.INSTANCE.getEnum())) {
                return;
            }
        }
        FirEvaluatorResult evaluatedInitializer = DeclarationAttributesKt.getEvaluatedInitializer(firValueParameter);
        if (evaluatedInitializer == null) {
            defaultValue = firValueParameter.getDefaultValue();
        } else {
            if (evaluatedInitializer instanceof FirEvaluatorResult.CompileTimeException) {
            } else {
                if (evaluatedInitializer instanceof FirEvaluatorResult.Evaluated) {
                    FirElement result = ((FirEvaluatorResult.Evaluated) evaluatedInitializer).getResult();
                    if (!(result instanceof FirExpression)) {
                        result = null;
                    }
                    defaultValue = (FirExpression) result;
                }
                if (defaultValue == null) {
                    defaultValue = firValueParameter.getDefaultValue();
                }
            }
            defaultValue = null;
            if (defaultValue == null) {
                defaultValue = firValueParameter.getDefaultValue();
            }
        }
        FirExpression firExpression = defaultValue;
        if (firExpression != null) {
            if (!getConfiguration().getSkipBodies() || !isDataClassCopy(irValueParameter.getParent())) {
                irExpressionBodyCreateExpressionBody = (!getConfiguration().getSkipBodies() || isAnnotationConstructor(irValueParameter.getParent())) ? IrFactoryHelpersKt.createExpressionBody(irValueParameter.getFactory(), Fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this.visitor, firExpression, false, FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()), 2, null)) : IrFactoryHelpersKt.createExpressionBody(irValueParameter.getFactory(), BuildersKt.IrErrorExpressionImpl(irValueParameter.getStartOffset(), irValueParameter.getEndOffset(), getBuiltins().getNothingType(), "skipBodies"));
            }
            irValueParameter.setDefaultValue(irExpressionBodyCreateExpressionBody);
        }
    }

    private final void setPropertyAccessorContent(IrSimpleFunction irSimpleFunction, FirPropertyAccessor firPropertyAccessor, IrProperty irProperty, IrType irType, boolean z) {
        IrSetFieldImpl irReturnImpl;
        Object next;
        Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
        fir2IrConversionScope.getFunctionStack().add(irSimpleFunction);
        try {
            applyParentFromStackTo(irSimpleFunction);
            convertFunctionContent(irSimpleFunction, firPropertyAccessor, null);
            if (z) {
                Fir2IrConversionScope fir2IrConversionScope2 = this.conversionScope;
                fir2IrConversionScope2.get_parentStack().add(irSimpleFunction);
                if (irSimpleFunction != null) {
                    fir2IrConversionScope2.getScopeStack().add(new Scope(irSimpleFunction.getSymbol()));
                }
                try {
                    getDeclarationStorage().enterScope(irSimpleFunction.getSymbol());
                    IrField backingField = irProperty.getBackingField();
                    IrFieldSymbol symbol = backingField != null ? backingField.getSymbol() : null;
                    if (symbol != null && !getConfiguration().getSkipBodies()) {
                        IrFactory factory = irSimpleFunction.getFactory();
                        int startOffset = irSimpleFunction.getStartOffset();
                        int endOffset = irSimpleFunction.getEndOffset();
                        if (AdditionalIrUtilsKt.isSetter(irSimpleFunction)) {
                            irReturnImpl = BuildersKt.IrSetFieldImpl$default(irSimpleFunction.getStartOffset(), irSimpleFunction.getEndOffset(), symbol, getBuiltins().getUnitType(), (IrStatementOrigin) null, (IrClassSymbol) null, 48, (Object) null);
                            setReceiver(irReturnImpl, irSimpleFunction);
                            int startOffset2 = irReturnImpl.getStartOffset();
                            int endOffset2 = irReturnImpl.getEndOffset();
                            Iterator it = irSimpleFunction.getParameters().iterator();
                            do {
                                if (!it.hasNext()) {
                                    throw new NoSuchElementException("Collection contains no element matching the predicate.");
                                }
                                next = it.next();
                            } while (((IrValueParameter) next).getKind() != IrParameterKind.Regular);
                            irReturnImpl.setValue(BuildersKt.IrGetValueImpl$default(startOffset2, endOffset2, irType, ((IrValueParameter) next).getSymbol(), (IrStatementOrigin) null, 16, (Object) null));
                            Unit unit = Unit.INSTANCE;
                        } else {
                            irReturnImpl = new IrReturnImpl(irSimpleFunction.getStartOffset(), irSimpleFunction.getEndOffset(), getBuiltins().getNothingType(), irSimpleFunction.getSymbol(), setReceiver(BuildersKt.IrGetFieldImpl$default(irSimpleFunction.getStartOffset(), irSimpleFunction.getEndOffset(), symbol, irType, (IrStatementOrigin) null, (IrClassSymbol) null, 48, (Object) null), irSimpleFunction));
                        }
                        irSimpleFunction.setBody(IrFactoryHelpersKt.createBlockBody(factory, startOffset, endOffset, CollectionsKt.listOf(irReturnImpl)));
                    }
                    getDeclarationStorage().leaveScope(irSimpleFunction.getSymbol());
                    Unit unit2 = Unit.INSTANCE;
                    if (irSimpleFunction != null) {
                        fir2IrConversionScope2.getScopeStack().remove(fir2IrConversionScope2.getScopeStack().size() - 1);
                    }
                    fir2IrConversionScope2.get_parentStack().remove(fir2IrConversionScope2.get_parentStack().size() - 1);
                } catch (Throwable th) {
                    if (irSimpleFunction != null) {
                        fir2IrConversionScope2.getScopeStack().remove(fir2IrConversionScope2.getScopeStack().size() - 1);
                    }
                    fir2IrConversionScope2.get_parentStack().remove(fir2IrConversionScope2.get_parentStack().size() - 1);
                    throw th;
                }
            }
            Unit unit3 = Unit.INSTANCE;
            fir2IrConversionScope.getFunctionStack().remove(fir2IrConversionScope.getFunctionStack().size() - 1);
        } catch (Throwable th2) {
            fir2IrConversionScope.getFunctionStack().remove(fir2IrConversionScope.getFunctionStack().size() - 1);
            throw th2;
        }
    }

    private final IrFieldAccessExpression setReceiver(IrFieldAccessExpression irFieldAccessExpression, IrDeclaration irDeclaration) {
        IrValueParameter dispatchReceiverParameter;
        if ((irDeclaration instanceof IrFunction) && (dispatchReceiverParameter = ((IrFunction) irDeclaration).getDispatchReceiverParameter()) != null) {
            irFieldAccessExpression.setReceiver(BuildersKt.IrGetValueImpl$default(irFieldAccessExpression.getStartOffset(), irFieldAccessExpression.getEndOffset(), dispatchReceiverParameter.getSymbol(), (IrStatementOrigin) null, 8, (Object) null));
        }
        return irFieldAccessExpression;
    }

    public final void convertClassContent(IrClass irClass, FirClass klass) {
        Object next;
        IrConstructor irConstructor;
        irClass.getClass();
        klass.getClass();
        Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
        fir2IrConversionScope.getContainingFirClassStack().add(klass);
        try {
            getDeclarationStorage().enterScope(irClass.getSymbol());
            Fir2IrConversionScope fir2IrConversionScope2 = this.conversionScope;
            fir2IrConversionScope2.getClassStack().add(irClass);
            try {
                List listCreateListBuilder = CollectionsKt.createListBuilder();
                listCreateListBuilder.addAll(klass.getDeclarations());
                if ((klass instanceof FirRegularClass) && !FirDeclarationGenerationExtensionKt.getDeclarationGenerators(FirExtensionServiceKt.getExtensionService(getSession())).isEmpty()) {
                    listCreateListBuilder.addAll(CollectionsKt.sortedWith(GeneratedDeclarationsUtilsKt.generatedMembers((FirRegularClass) klass, getSession()), FirCallableDeclarationComparator.INSTANCE));
                    listCreateListBuilder.addAll(CollectionsKt.sortedWith(GeneratedDeclarationsUtilsKt.generatedNestedClassifiers((FirRegularClass) klass, getSession()), FirMemberDeclarationComparator.INSTANCE));
                }
                List<FirDeclaration> listBuild = CollectionsKt.build(listCreateListBuilder);
                Iterator it = listBuild.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    FirDeclaration firDeclaration = (FirDeclaration) next;
                    if ((firDeclaration instanceof FirConstructor) && ((FirConstructor) firDeclaration).getIsPrimary()) {
                        break;
                    }
                }
                FirConstructor firConstructor = (FirConstructor) next;
                if (firConstructor != null) {
                    IrConstructorSymbol cachedIrConstructorSymbol = getDeclarationStorage().getCachedIrConstructorSymbol(firConstructor);
                    cachedIrConstructorSymbol.getClass();
                    irConstructor = (IrConstructor) cachedIrConstructorSymbol.getOwner();
                } else {
                    irConstructor = null;
                }
                if (irConstructor != null) {
                    Fir2IrDeclarationStorage declarationStorage = getDeclarationStorage();
                    declarationStorage.enterScope(irConstructor.getSymbol());
                    declarationStorage.putParametersInScope(irConstructor, firConstructor);
                    convertFunctionContent(irConstructor, firConstructor, klass);
                    this.cleaner.cleanConstructor(firConstructor);
                }
                for (FirDeclaration firDeclaration2 : listBuild) {
                    if (!(firDeclaration2 instanceof FirTypeAlias) && (!(firDeclaration2 instanceof FirConstructor) || !((FirConstructor) firDeclaration2).getIsPrimary())) {
                        if ((firDeclaration2 instanceof FirRegularClass) && Intrinsics.areEqual(((FirMemberDeclaration) firDeclaration2).getStatus().getVisibility(), Visibilities.Local.INSTANCE)) {
                            IrClass irClass2 = getClassifierStorage().getIrClass((FirClass) firDeclaration2);
                            irClass2.setParent(irClass);
                            Fir2IrConversionScope fir2IrConversionScope3 = this.conversionScope;
                            fir2IrConversionScope3.get_parentStack().add(irClass2);
                            fir2IrConversionScope3.getScopeStack().add(new Scope(irClass2.getSymbol()));
                            try {
                                convertClassContent(irClass2, (FirClass) firDeclaration2);
                                Unit unit = Unit.INSTANCE;
                                fir2IrConversionScope3.getScopeStack().remove(fir2IrConversionScope3.getScopeStack().size() - 1);
                                fir2IrConversionScope3.get_parentStack().remove(fir2IrConversionScope3.get_parentStack().size() - 1);
                            } catch (Throwable th) {
                                fir2IrConversionScope3.getScopeStack().remove(fir2IrConversionScope3.getScopeStack().size() - 1);
                                fir2IrConversionScope3.get_parentStack().remove(fir2IrConversionScope3.get_parentStack().size() - 1);
                                throw th;
                            }
                        } else {
                            firDeclaration2.accept(this.visitor, null);
                        }
                    }
                }
                getAnnotationGenerator().generate((IrMutableAnnotationContainer) irClass, (FirAnnotationContainer) klass);
                if (irConstructor != null) {
                    getDeclarationStorage().leaveScope(irConstructor.getSymbol());
                }
                Unit unit2 = Unit.INSTANCE;
                fir2IrConversionScope2.getClassStack().remove(fir2IrConversionScope2.getClassStack().size() - 1);
                getDeclarationStorage().leaveScope(irClass.getSymbol());
                fir2IrConversionScope.getContainingFirClassStack().remove(fir2IrConversionScope.getContainingFirClassStack().size() - 1);
            } catch (Throwable th2) {
                fir2IrConversionScope2.getClassStack().remove(fir2IrConversionScope2.getClassStack().size() - 1);
                throw th2;
            }
        } catch (Throwable th3) {
            fir2IrConversionScope.getContainingFirClassStack().remove(fir2IrConversionScope.getContainingFirClassStack().size() - 1);
            throw th3;
        }
    }

    public final IrField convertFieldContent(IrField irField, FirField field) {
        irField.getClass();
        field.getClass();
        Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
        fir2IrConversionScope.get_parentStack().add(irField);
        if (irField != null) {
            fir2IrConversionScope.getScopeStack().add(new Scope(irField.getSymbol()));
        }
        try {
            getDeclarationStorage().enterScope(irField.getSymbol());
            FirExpression initializer = field.getInitializer();
            if (irField.getInitializer() == null && initializer != null && !getConfiguration().getSkipBodies()) {
                irField.setInitializer(IrFactoryHelpersKt.createExpressionBody(IrFactoryImpl.INSTANCE, Fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this.visitor, initializer, false, null, 6, null)));
            }
            getDeclarationStorage().leaveScope(irField.getSymbol());
            Unit unit = Unit.INSTANCE;
            return irField;
        } finally {
            if (irField != null) {
                fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
            }
            fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
        }
    }

    /* JADX WARN: Code duplicated, block: B:109:0x0208 A[Catch: all -> 0x0034, TryCatch #0 {all -> 0x0034, blocks: (B:7:0x0026, B:9:0x002a, B:15:0x0045, B:16:0x0054, B:18:0x005a, B:20:0x0069, B:22:0x006f, B:24:0x0075, B:26:0x0079, B:30:0x0080, B:31:0x008e, B:33:0x0094, B:35:0x00b4, B:37:0x00be, B:38:0x00c1, B:39:0x00c2, B:14:0x0037, B:40:0x00c9, B:42:0x00cd, B:44:0x00d1, B:46:0x00db, B:48:0x00e1, B:50:0x00eb, B:52:0x010d, B:54:0x0128, B:56:0x012e, B:58:0x0137, B:60:0x013d, B:62:0x0147, B:67:0x0156, B:70:0x0167, B:72:0x016d, B:75:0x0177, B:77:0x017d, B:80:0x0187, B:82:0x018d, B:86:0x0198, B:88:0x019e, B:93:0x01ad, B:96:0x01b5, B:90:0x01a5, B:63:0x014c, B:65:0x0150, B:97:0x01c8, B:99:0x01d1, B:102:0x01db, B:103:0x01ea, B:106:0x01f8, B:107:0x01fc, B:109:0x0208, B:142:0x02c3, B:144:0x02c7, B:146:0x02d0, B:147:0x02db, B:110:0x020d, B:112:0x0211, B:114:0x0217, B:116:0x0227, B:117:0x0248, B:119:0x0250, B:121:0x025a, B:123:0x025e, B:126:0x026c, B:128:0x0272, B:129:0x027d, B:130:0x0285, B:133:0x0291, B:135:0x0297, B:136:0x02a2, B:137:0x02aa, B:138:0x02b2, B:139:0x02b9, B:140:0x02ba, B:34:0x00af), top: B:156:0x0026, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:95:0x01b4  */
    public final <T extends IrFunction> T convertFunctionContent(T irFunction, final FirFunction firFunction, final FirClass containingClass) {
        FirElement firElement;
        int endOffset;
        Integer numStartOffsetSkippingComments;
        irFunction.getClass();
        Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
        fir2IrConversionScope.get_parentStack().add(irFunction);
        if (irFunction != null) {
            fir2IrConversionScope.getScopeStack().add(new Scope(irFunction.getSymbol()));
        }
        if (firFunction != null) {
            try {
                if (!(irFunction instanceof IrConstructor) || !((IrConstructor) irFunction).isPrimary()) {
                    Fir2IrDeclarationStorage declarationStorage = getDeclarationStorage();
                    declarationStorage.enterScope(irFunction.getSymbol());
                    declarationStorage.putParametersInScope(irFunction, firFunction);
                }
                List parameters = irFunction.getParameters();
                ArrayList arrayList = new ArrayList();
                for (Object obj : parameters) {
                    if (((IrValueParameter) obj).getKind() == IrParameterKind.Regular) {
                        arrayList.add(obj);
                    }
                }
                boolean z = (containingClass != null ? containingClass.getClassKind() : null) == ClassKind.ANNOTATION_CLASS && (irFunction instanceof IrConstructor);
                for (Pair pair : CollectionsKt.zip(arrayList, firFunction.getValueParameters())) {
                    IrValueParameter irValueParameter = (IrValueParameter) pair.component1();
                    FirValueParameter firValueParameter = (FirValueParameter) pair.component2();
                    Fir2IrVisitor fir2IrVisitor = this.visitor;
                    boolean z2 = fir2IrVisitor._annotationMode;
                    fir2IrVisitor._annotationMode = z;
                    try {
                        setDefaultValue(irValueParameter, firValueParameter);
                        Unit unit = Unit.INSTANCE;
                        fir2IrVisitor._annotationMode = z2;
                        this.cleaner.cleanValueParameter(firValueParameter);
                    } catch (Throwable th) {
                        fir2IrVisitor._annotationMode = z2;
                        throw th;
                    }
                }
                getAnnotationGenerator().generate((IrMutableAnnotationContainer) irFunction, (FirAnnotationContainer) firFunction);
            } catch (Throwable th2) {
                if (irFunction != null) {
                    fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
                }
                fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
                throw th2;
            }
        }
        if (!(firFunction instanceof FirConstructor) || !(irFunction instanceof IrConstructor) || firFunction.getStatus().isExpect() || irFunction.isExternal()) {
            if (!(irFunction instanceof IrConstructor) && !irFunction.isExpect()) {
                if (Intrinsics.areEqual(irFunction.getOrigin(), IrDeclarationOrigin.Companion.getENUM_CLASS_SPECIAL_MEMBER())) {
                    irFunction.setBody(BuildersKt.IrSyntheticBodyImpl(irFunction.getStartOffset(), irFunction.getEndOffset(), (IrSyntheticBodyKind) MapsKt.getValue(Fir2IrDeclarationStorage.INSTANCE.getENUM_SYNTHETIC_NAMES$org_jetbrains_kotlin_fir2ir(), irFunction.getName())));
                } else if ((irFunction.getParent() instanceof IrClass) && IrUtilsKt.getParentAsClass(irFunction).isData()) {
                    if (!(irFunction instanceof IrSimpleFunction)) {
                        throw new IllegalArgumentException("Failed requirement.");
                    }
                    DataClassResolver dataClassResolver = DataClassResolver.INSTANCE;
                    if (dataClassResolver.isComponentLike(irFunction.getName())) {
                        if ((firFunction != null ? firFunction.getBody() : null) == null) {
                            getDataClassMembersGenerator().registerCopyOrComponentFunction((IrSimpleFunction) irFunction);
                        } else {
                            irFunction.setBody(convertBody(irFunction, firFunction));
                        }
                    } else if (!dataClassResolver.isCopy(irFunction.getName())) {
                        irFunction.setBody(convertBody(irFunction, firFunction));
                    } else if ((firFunction != null ? firFunction.getBody() : null) == null) {
                        getDataClassMembersGenerator().registerCopyOrComponentFunction((IrSimpleFunction) irFunction);
                    } else {
                        irFunction.setBody(convertBody(irFunction, firFunction));
                    }
                } else {
                    irFunction.setBody(convertBody(irFunction, firFunction));
                }
            }
        } else if (!getConfiguration().getSkipBodies()) {
            IrBlockBody irBlockBodyCreateBlockBody = irFunction.getFactory().createBlockBody(irFunction.getStartOffset(), irFunction.getEndOffset());
            final FirDelegatedConstructorCall delegatedConstructor = ((FirConstructor) firFunction).getDelegatedConstructor();
            IrClass parent = irFunction.getParent();
            parent.getClass();
            IrClass irClass = parent;
            if (delegatedConstructor != null) {
                irBlockBodyCreateBlockBody.getStatements().add((IrExpression) this.conversionScope.forDelegatingConstructorCall$org_jetbrains_kotlin_fir2ir((IrConstructor) irFunction, irClass, new Function0() { // from class: gw1
                    public final Object invoke() {
                        return ClassMemberGenerator.convertFunctionContent$lambda$0$3(firFunction, containingClass, delegatedConstructor, this);
                    }
                }));
            }
            if (delegatedConstructor != null && !delegatedConstructor.getIsThis()) {
                if (((FirConstructor) firFunction).getIsPrimary()) {
                    containingClass.getClass();
                    firElement = containingClass;
                } else {
                    firElement = firFunction;
                }
                List statements = irBlockBodyCreateBlockBody.getStatements();
                TokenSet tokenSet = firElement instanceof FirNamedFunction ? OffsetUtilsKt.FUNCTION_KEYWORD_TOKENS : firElement instanceof FirConstructor ? OffsetUtilsKt.CONSTRUCTOR_KEYWORD_TOKENS : null;
                KtSourceElement source = firElement.getSource();
                int i = -1;
                if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                    endOffset = -1;
                } else if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                    endOffset = -1;
                } else if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                    endOffset = -1;
                } else {
                    int startOffset = (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, tokenSet)) == null) ? source != null ? source.getStartOffset() : -1 : numStartOffsetSkippingComments.intValue();
                    endOffset = source != null ? source.getEndOffset() : -1;
                    i = startOffset;
                }
                statements.add(BuildersKt.IrInstanceInitializerCallImpl(i, endOffset, irClass.getSymbol(), getBuiltins().getUnitType()));
            }
            FirBlock body = ((FirConstructor) firFunction).getBody();
            IrBlockBody irBlockBodyConvertToIrBlockBody$org_jetbrains_kotlin_fir2ir = body != null ? this.visitor.convertToIrBlockBody$org_jetbrains_kotlin_fir2ir(body) : null;
            if (irBlockBodyConvertToIrBlockBody$org_jetbrains_kotlin_fir2ir != null) {
                CollectionsKt.addAll(irBlockBodyCreateBlockBody.getStatements(), irBlockBodyConvertToIrBlockBody$org_jetbrains_kotlin_fir2ir.getStatements());
            }
            if (!irBlockBodyCreateBlockBody.getStatements().isEmpty()) {
                irFunction.setBody(irBlockBodyCreateBlockBody);
            } else if (Intrinsics.areEqual(containingClass != null ? FirDeclarationUtilKt.getClassId(containingClass) : null, StandardClassIds.INSTANCE.getAny())) {
                irFunction.setBody(irBlockBodyCreateBlockBody);
            }
        }
        if (firFunction != null && (!(irFunction instanceof IrConstructor) || !((IrConstructor) irFunction).isPrimary())) {
            getDeclarationStorage().leaveScope(irFunction.getSymbol());
        }
        Unit unit2 = Unit.INSTANCE;
        if (irFunction != null) {
            fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
        }
        fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
        return irFunction;
    }

    /* JADX WARN: Code duplicated, block: B:40:0x0078  */
    /* JADX WARN: Code duplicated, block: B:59:0x00db  */
    public final IrProperty convertPropertyContent(IrProperty irProperty, FirProperty property) {
        FirExpression initializer;
        IrSimpleFunction setter;
        IrType irType;
        IrType type;
        IrType type2;
        irProperty.getClass();
        property.getClass();
        FirBackingField backingField = property.getBackingField();
        if (backingField == null || (initializer = backingField.getInitializer()) == null) {
            initializer = property.getInitializer();
        }
        FirExpression delegate = property.getDelegate();
        IrType irType$default = Fir2IrTypeConverterKt.toIrType$default(this, property.getReturnTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null);
        if (initializer == null) {
            initializer = delegate;
        }
        if (initializer == null || (initializer instanceof FirReplExpressionReference)) {
            initializer = null;
        }
        initializeBackingField(irProperty, property, initializer);
        boolean z = (property.getGetter() instanceof FirDefaultPropertyGetter) || (property.getGetter() == null && (irProperty.getParent() instanceof IrScript) && DestructuringDeclarationAttributesKt.getDestructuringDeclarationContainerVariable(property) != null);
        IrSimpleFunction getter = irProperty.getGetter();
        if (getter != null) {
            FirPropertyAccessor getter2 = property.getGetter();
            IrField backingField2 = irProperty.getBackingField();
            if (backingField2 == null || (type2 = backingField2.getType()) == null) {
                type2 = irType$default;
            } else {
                if (!DeclarationAttributesKt.getHasExplicitBackingField(property)) {
                    type2 = null;
                }
                if (type2 == null) {
                    type2 = irType$default;
                }
            }
            setPropertyAccessorContent(getter, getter2, irProperty, type2, z);
        }
        if (Intrinsics.areEqual(irProperty.getOrigin(), IrDeclarationOrigin.Companion.getENUM_CLASS_SPECIAL_MEMBER())) {
            IrSyntheticBodyKind irSyntheticBodyKind = (IrSyntheticBodyKind) MapsKt.getValue(Fir2IrDeclarationStorage.INSTANCE.getENUM_SYNTHETIC_NAMES$org_jetbrains_kotlin_fir2ir(), irProperty.getName());
            IrSimpleFunction getter3 = irProperty.getGetter();
            getter3.getClass();
            getter3.setBody(BuildersKt.IrSyntheticBodyImpl(irProperty.getStartOffset(), irProperty.getEndOffset(), irSyntheticBodyKind));
        }
        if (property.getIsVar() && (setter = irProperty.getSetter()) != null) {
            FirPropertyAccessor setter2 = property.getSetter();
            IrField backingField3 = irProperty.getBackingField();
            if (backingField3 == null || (type = backingField3.getType()) == null) {
                irType = irType$default;
            } else {
                IrType irType2 = DeclarationAttributesKt.getHasExplicitBackingField(property) ? type : null;
                if (irType2 == null) {
                    irType = irType$default;
                } else {
                    irType = irType2;
                }
            }
            setPropertyAccessorContent(setter, setter2, irProperty, irType, property.getSetter() instanceof FirDefaultPropertySetter);
        }
        getAnnotationGenerator().generate(irProperty, property);
        return irProperty;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AdapterGenerator getAdapterGenerator() {
        return this.c.getAdapterGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AnnotationGenerator getAnnotationGenerator() {
        return this.c.getAnnotationGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrIrGeneratedDeclarationsRegistrar getAnnotationsFromPluginRegistrar() {
        return this.c.getAnnotationsFromPluginRegistrar();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrBuiltinSymbolsContainer getBuiltins() {
        return this.c.getBuiltins();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public CallAndReferenceGenerator getCallGenerator() {
        return this.c.getCallGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrCallableDeclarationsGenerator getCallablesGenerator() {
        return this.c.getCallablesGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifierStorage getClassifierStorage() {
        return this.c.getClassifierStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifiersGenerator getClassifiersGenerator() {
        return this.c.getClassifiersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConfiguration getConfiguration() {
        return this.c.getConfiguration();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConverter getConverter() {
        return this.c.getConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDataClassMembersGenerator getDataClassMembersGenerator() {
        return this.c.getDataClassMembersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDeclarationStorage getDeclarationStorage() {
        return this.c.getDeclarationStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrExtensions getExtensions() {
        return this.c.getExtensions();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Set<FirFile> getFilesBeingCompiled() {
        return this.c.getFilesBeingCompiled();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public FirProviderWithGeneratedFiles getFirProvider() {
        return this.c.getFirProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrImplicitCastInserter getImplicitCastInserter() {
        return this.c.getImplicitCastInserter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public KotlinMangler.IrMangler getIrMangler() {
        return this.c.getIrMangler();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public List<IrProvider> getIrProviders() {
        return this.c.getIrProviders();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyDeclarationsGenerator getLazyDeclarationsGenerator() {
        return this.c.getLazyDeclarationsGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyFakeOverrideGenerator getLazyFakeOverrideGenerator() {
        return this.c.getLazyFakeOverrideGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrLock getLock() {
        return this.c.getLock();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.c.getScopeSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.c.getSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
        return this.c.getSpecialAnnotationsProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrSymbolsMappingForLazyClasses getSymbolsMappingForLazyClasses() {
        return this.c.getSymbolsMappingForLazyClasses();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrTypeConverter getTypeConverter() {
        return this.c.getTypeConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrVisibilityConverter getVisibilityConverter() {
        return this.c.getVisibilityConverter();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public final IrExpression toIrDelegatingConstructorCall$org_jetbrains_kotlin_fir2ir(FirDelegatedConstructorCall firDelegatedConstructorCall, int i, int i2) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrSymbol irSymbol;
        int i3;
        int i4;
        ConeKotlinTypeProjection[] coneKotlinTypeProjectionArr;
        IrDelegatingConstructorCallImpl irDelegatingConstructorCallImplIrEnumConstructorCallImplWithShape$default;
        firDelegatedConstructorCall.getClass();
        IrType irType$default = Fir2IrTypeConverterKt.toIrType$default(this, firDelegatedConstructorCall.getConstructedTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null);
        FirConstructorSymbol resolvedConstructorSymbol$default = FirReferenceUtilsKt.toResolvedConstructorSymbol$default(firDelegatedConstructorCall.getCalleeReference(), false, 1, null);
        if (resolvedConstructorSymbol$default == null) {
            return BuildersKt.IrErrorCallExpressionImpl(i, i2, irType$default, "Cannot find delegated constructor call");
        }
        FirCallableSymbol<?> firCallableSymbolUnwrapCallRepresentative = ScopeUtilsKt.unwrapCallRepresentative(this, ScopeUtilsKt.unwrapCallRepresentative(this, resolvedConstructorSymbol$default, ClassMembersKt.containingClassLookupTag(resolvedConstructorSymbol$default)), ConeTypeUtilsKt.getClassLikeLookupTagIfAny(resolvedConstructorSymbol$default.getResolvedReturnType()));
        if (!(firCallableSymbolUnwrapCallRepresentative instanceof FirConstructorSymbol)) {
            k2d.a("Check failed.");
            return null;
        }
        IrSymbol irFunctionSymbol$default = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(getDeclarationStorage(), (FirFunctionSymbol) firCallableSymbolUnwrapCallRepresentative, null, false, 6, null);
        irFunctionSymbol$default.getClass();
        IrSymbol irSymbol2 = (IrConstructorSymbol) irFunctionSymbol$default;
        ConeKotlinTypeProjection[] typeArguments = TypeExpansionUtilsKt.fullyExpandedType(this, FirTypeUtilsKt.getConeType(firDelegatedConstructorCall.getConstructedTypeRef())).getTypeArguments();
        FirConstructor firConstructor = (FirConstructor) ((FirConstructorSymbol) firCallableSymbolUnwrapCallRepresentative).getFir();
        if ((firConstructor.getStatus().isFromEnumClass() || FirTypeUtilsKt.isEnum(firConstructor.getReturnTypeRef())) && firDelegatedConstructorCall.isSuper()) {
            irSymbol = irSymbol2;
            i3 = 0;
            i4 = 1;
            coneKotlinTypeProjectionArr = typeArguments;
            irDelegatingConstructorCallImplIrEnumConstructorCallImplWithShape$default = BuildersKt.IrEnumConstructorCallImplWithShape$default(i, i2, irType$default, irSymbol, firConstructor.getTypeParameters().size(), firConstructor.getValueParameters().size(), firConstructor.getContextParameters().size(), firConstructor.getDispatchReceiverType() != null, FirDeclarationUtilKt.isExtension(firConstructor), (IrStatementOrigin) null, 512, (Object) null);
        } else {
            i3 = 0;
            i4 = 1;
            coneKotlinTypeProjectionArr = typeArguments;
            irDelegatingConstructorCallImplIrEnumConstructorCallImplWithShape$default = BuildersKt.IrDelegatingConstructorCallImplWithShape$default(i, i2, getBuiltins().getUnitType(), irSymbol2, firConstructor.getTypeParameters().size(), firConstructor.getValueParameters().size() + firConstructor.getContextParameters().size(), firConstructor.getContextParameters().size(), firConstructor.getDispatchReceiverType() != null, FirDeclarationUtilKt.isExtension(firConstructor), (IrStatementOrigin) null, 512, (Object) null);
            irSymbol = irSymbol2;
        }
        IrDelegatingConstructorCallImpl irDelegatingConstructorCallImpl = irDelegatingConstructorCallImplIrEnumConstructorCallImplWithShape$default;
        if (!firConstructor.getTypeParameters().isEmpty()) {
            if ((coneKotlinTypeProjectionArr.length == 0 ? i4 : i3) == 0) {
                int length = coneKotlinTypeProjectionArr.length;
                for (int i5 = i3; i5 < length; i5++) {
                    ConeKotlinTypeProjection coneKotlinTypeProjection = coneKotlinTypeProjectionArr[i5];
                    if (i5 >= firConstructor.getTypeParameters().size()) {
                        break;
                    }
                    List typeArguments2 = irDelegatingConstructorCallImpl.getTypeArguments();
                    coneKotlinTypeProjection.getClass();
                    typeArguments2.set(i5, Fir2IrTypeConverterKt.toIrType$default(this, coneKotlinTypeProjection.getType(), (ConversionTypeOrigin) null, 2, (Object) null));
                }
            }
        }
        CallAndReferenceGenerator callGenerator = getCallGenerator();
        callGenerator.getDeclarationStorage().enterScope(irSymbol);
        IrExpression irExpressionApplyReceiversAndArguments$default = CallAndReferenceGenerator.applyReceiversAndArguments$default(callGenerator, irDelegatingConstructorCallImpl, firDelegatedConstructorCall, firCallableSymbolUnwrapCallRepresentative, null, null, 8, null);
        callGenerator.getDeclarationStorage().leaveScope(irSymbol);
        return irExpressionApplyReceiversAndArguments$default;
    }
}
