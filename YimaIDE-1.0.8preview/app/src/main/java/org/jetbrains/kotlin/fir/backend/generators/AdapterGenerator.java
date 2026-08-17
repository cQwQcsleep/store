package org.jetbrains.kotlin.fir.backend.generators;

import defpackage.f2f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.UtilsKt;
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
import org.jetbrains.kotlin.fir.backend.FirProviderWithGeneratedFiles;
import org.jetbrains.kotlin.fir.backend.generators.AdapterGenerator;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.backend.utils.IrElementsCreationUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.OffsetUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.VariousUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionConversionKind;
import org.jetbrains.kotlin.fir.expressions.FirFunctionTypeConversionExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.SubstitutionUtilsKt;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirResolvedCallableReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.FirSamResolver;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ResolvedCallArgument;
import org.jetbrains.kotlin.fir.resolve.calls.stages.FirFakeArgumentForCallableReference;
import org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeRawType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.builders.Scope;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrFactoryHelpersKt;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.expressions.IrBlock;
import org.jetbrains.kotlin.ir.expressions.IrBlockBody;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionsKt;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperator;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrBlockImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrFunctionReferenceImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrTypeOperatorCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrVarargImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrWhenImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrSimpleFunctionSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrValueParameterSymbolImpl;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypeArgument;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypeProjection;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.types.impl.IrSimpleTypeImplKt;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IrTypeUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009c\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J%\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0011J\u0018\u0010\u0012\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0013\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J/\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u001aJ \u0010\u001b\u001a\u0004\u0018\u00010\u0016*\u00020\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001c\u001a\u00020\nH\u0002JD\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\b\u0010#\u001a\u0004\u0018\u00010\u00162\b\u0010$\u001a\u0004\u0018\u00010\u0016H\u0002J0\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010\r\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0002J\u001c\u00100\u001a\u000201*\u0002022\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0002JD\u00103\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010'\u001a\u00020(2\u0006\u00104\u001a\u00020\u000e2\b\u0010#\u001a\u0004\u0018\u00010\u00162\b\u0010$\u001a\u0004\u0018\u00010\u0016H\u0002J\u0019\u00105\u001a\u00020\u0016*\u00020\u00162\u0006\u00106\u001a\u000207H\u0000¢\u0006\u0002\b8J\u0014\u00109\u001a\u00020\u0016*\u00020\u00162\u0006\u00106\u001a\u000207H\u0002J \u0010:\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u00162\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<H\u0002J\u0016\u0010>\u001a\u0004\u0018\u00010<*\u00020<2\u0006\u0010?\u001a\u00020@H\u0002J\u000e\u0010>\u001a\u0004\u0018\u00010A*\u00020AH\u0002J\u001a\u0010B\u001a\u00020<*\u00020C2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020F0EH\u0002J\u0017\u0010G\u001a\u0004\u0018\u00010<2\u0006\u0010H\u001a\u00020<H\u0000¢\u0006\u0002\bIJ\u001c\u0010J\u001a\u00020\u0016*\u00020\u00162\u0006\u00106\u001a\u0002072\u0006\u0010.\u001a\u00020KH\u0002J\u001a\u0010L\u001a\u0004\u0018\u00010M2\u0006\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020<H\u0002J8\u0010Q\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010R\u001a\u00020+2\u0006\u0010S\u001a\u00020\n2\u0006\u0010T\u001a\u00020MH\u0002J(\u0010U\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010'\u001a\u00020(2\u0006\u0010T\u001a\u00020MH\u0002J\"\u0010V\u001a\u00020\u00162\u0006\u0010W\u001a\u00020\f2\n\u0010X\u001a\u0006\u0012\u0002\b\u00030Y2\u0006\u0010Z\u001a\u00020+J\u0014\u0010[\u001a\u00020+*\u00020\u000e2\u0006\u0010\\\u001a\u00020 H\u0002J,\u0010]\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\n\u0010X\u001a\u0006\u0012\u0002\b\u00030Y2\u0006\u0010Z\u001a\u00020+H\u0002R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010^\u001a\u00020\u0000X\u0096\u0005¢\u0006\u0006\u001a\u0004\b_\u0010`R\u0012\u0010a\u001a\u00020bX\u0096\u0005¢\u0006\u0006\u001a\u0004\bc\u0010dR\u0012\u0010e\u001a\u00020fX\u0096\u0005¢\u0006\u0006\u001a\u0004\bg\u0010hR\u0012\u0010i\u001a\u00020jX\u0096\u0005¢\u0006\u0006\u001a\u0004\bk\u0010lR\u0012\u0010m\u001a\u00020nX\u0096\u0005¢\u0006\u0006\u001a\u0004\bo\u0010pR\u0012\u0010q\u001a\u00020rX\u0096\u0005¢\u0006\u0006\u001a\u0004\bs\u0010tR\u0012\u0010u\u001a\u00020vX\u0096\u0005¢\u0006\u0006\u001a\u0004\bw\u0010xR\u0012\u0010y\u001a\u00020zX\u0096\u0005¢\u0006\u0006\u001a\u0004\b{\u0010|R\u0013\u0010}\u001a\u00020~X\u0096\u0005¢\u0006\u0007\u001a\u0005\b\u007f\u0010\u0080\u0001R\u0016\u0010\u0081\u0001\u001a\u00030\u0082\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0016\u0010\u0085\u0001\u001a\u00030\u0086\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0016\u0010\u0089\u0001\u001a\u00030\u008a\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0016\u0010\u008d\u0001\u001a\u00030\u008e\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R\u001e\u0010\u0091\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u0092\u0001\u0018\u00010EX\u0096\u0005¢\u0006\b\u001a\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0016\u0010\u0095\u0001\u001a\u00030\u0096\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001R\u0016\u0010\u0099\u0001\u001a\u00030\u009a\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001R\u0016\u0010\u009d\u0001\u001a\u00030\u009e\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009f\u0001\u0010 \u0001R\u001d\u0010¡\u0001\u001a\n\u0012\u0005\u0012\u00030£\u00010¢\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¤\u0001\u0010¥\u0001R\u0016\u0010¦\u0001\u001a\u00030§\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¨\u0001\u0010©\u0001R\u0016\u0010ª\u0001\u001a\u00030«\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¬\u0001\u0010\u00ad\u0001R\u0016\u0010®\u0001\u001a\u00030¯\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b°\u0001\u0010±\u0001R\u0016\u0010²\u0001\u001a\u00030³\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b´\u0001\u0010µ\u0001R\u0016\u0010¶\u0001\u001a\u00030·\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¸\u0001\u0010¹\u0001R\u0018\u0010º\u0001\u001a\u0005\u0018\u00010»\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¼\u0001\u0010½\u0001R\u0016\u0010¾\u0001\u001a\u00030¿\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÀ\u0001\u0010Á\u0001R\u0016\u0010Â\u0001\u001a\u00030Ã\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÄ\u0001\u0010Å\u0001R\u0016\u0010Æ\u0001\u001a\u00030Ç\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÈ\u0001\u0010É\u0001¨\u0006Ê\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "conversionScope", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;)V", "samResolver", "Lorg/jetbrains/kotlin/fir/resolve/FirSamResolver;", "needToGenerateAdaptedCallableReference", Argument.Delimiters.none, "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/ir/types/IrSimpleType;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "needToGenerateAdaptedCallableReference$org_jetbrains_kotlin_fir2ir", "needSuspendConversion", "needCoercionToUnit", "hasVarargOrDefaultArguments", "generateAdaptedCallableReference", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "explicitReceiverExpression", "adapteeSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrFunctionSymbol;", "generateAdaptedCallableReference$org_jetbrains_kotlin_fir2ir", "findBoundReceiver", "isDispatch", "createAdapterFunctionForCallableReference", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "startOffset", Argument.Delimiters.none, "endOffset", "firAdaptee", "boundDispatchReceiver", "boundExtensionReceiver", "createAdapterParameter", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "adapterFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/ir/types/IrType;", "origin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "kind", "Lorg/jetbrains/kotlin/ir/declarations/IrParameterKind;", "toIrGetValue", "Lorg/jetbrains/kotlin/ir/expressions/IrGetValue;", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", "createAdapteeCallForCallableReference", "adaptedType", "applyFunctionTypeConversion", "argument", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionTypeConversionExpression;", "applyFunctionTypeConversion$org_jetbrains_kotlin_fir2ir", "applySamConversion", "castArgumentToFunctionalInterfaceForSamType", "argumentConeType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "samType", "removeExternalProjections", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "starProjectionTypeRepresentation", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "containingParameterSet", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/ConeTypeParameterLookupTag;", "getFunctionTypeForPossibleSamType", "parameterType", "getFunctionTypeForPossibleSamType$org_jetbrains_kotlin_fir2ir", "applyConversionBetweenFunctionTypes", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionConversionKind$BetweenFunctionTypes;", "findInvokeSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "expectedFunctionalType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "argumentType", "createAdapterFunctionForArgument", "adapterParameterType", "argumentIsNullable", "invokeSymbol", "createAdapteeCallForArgument", "generateFunInterfaceConstructorReference", "callableReference", "callableSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "irReferenceType", "getArgumentTypeAt", "index", "generateFunInterfaceConstructorAdapter", "adapterGenerator", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AdapterGenerator implements Fir2IrComponents {
    private final Fir2IrComponents c;
    private final Fir2IrConversionScope conversionScope;
    private final FirSamResolver samResolver;

    public AdapterGenerator(Fir2IrComponents fir2IrComponents, Fir2IrConversionScope fir2IrConversionScope) {
        fir2IrComponents.getClass();
        fir2IrConversionScope.getClass();
        this.c = fir2IrComponents;
        this.conversionScope = fir2IrConversionScope;
        this.samResolver = new FirSamResolver(getSession(), getScopeSession(), null, 4, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:40:0x00aa  */
    private final IrExpression applyConversionBetweenFunctionTypes(IrExpression irExpression, FirFunctionTypeConversionExpression firFunctionTypeConversionExpression, FirFunctionConversionKind.BetweenFunctionTypes betweenFunctionTypes) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrSimpleFunctionSymbol irSimpleFunctionSymbolFindInvokeSymbol;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firFunctionTypeConversionExpression);
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(this, FirTypeUtilsKt.getResolvedType(firFunctionTypeConversionExpression.getExpression()));
        if (TypeUtilsKt.isSubtypeOf$default(coneKotlinTypeFullyExpandedType, resolvedType, getSession(), false, 4, null)) {
            k2d.a("Check failed.");
            return null;
        }
        if (!betweenFunctionTypes.getIsFromSimpleToCustom()) {
            k2d.a("Check failed.");
            return null;
        }
        ConeClassLikeType coneClassLikeTypeCustomFunctionTypeToSimpleFunctionType = FunctionalTypeUtilsKt.customFunctionTypeToSimpleFunctionType(resolvedType, getSession());
        if (!FunctionalTypeUtilsKt.isSuspendOrKSuspendFunctionType(resolvedType, getSession()) || (irSimpleFunctionSymbolFindInvokeSymbol = findInvokeSymbol(coneClassLikeTypeCustomFunctionTypeToSimpleFunctionType, coneKotlinTypeFullyExpandedType)) == null) {
            return irExpression;
        }
        IrType irType$default = Fir2IrTypeConverterKt.toIrType$default(this, resolvedType, (ConversionTypeOrigin) null, 2, (Object) null);
        irType$default.getClass();
        IrSimpleType irSimpleType = (IrSimpleType) irType$default;
        KtSourceElement source = firFunctionTypeConversionExpression.getSource();
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
            startOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
                startOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                    startOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                        startOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source != null ? source.getEndOffset() : -1;
                    }
                }
            }
        }
        IrSimpleFunction irSimpleFunctionCreateAdapterFunctionForArgument = createAdapterFunctionForArgument(startOffset, endOffset, irSimpleType, Fir2IrTypeConverterKt.toIrType$default(this, coneClassLikeTypeCustomFunctionTypeToSimpleFunctionType, (ConversionTypeOrigin) null, 2, (Object) null), ConeTypeUtilsKt.isMarkedNullable(coneKotlinTypeFullyExpandedType), irSimpleFunctionSymbolFindInvokeSymbol);
        IrSimpleFunctionSymbol symbol = irSimpleFunctionCreateAdapterFunctionForArgument.getSymbol();
        int size = irSimpleFunctionCreateAdapterFunctionForArgument.getTypeParameters().size();
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        IrFunctionReferenceImpl IrFunctionReferenceImpl = BuildersKt.IrFunctionReferenceImpl(startOffset, endOffset, irSimpleType, symbol, size, (IrFunctionSymbol) null, companion.getSUSPEND_CONVERSION());
        IrBlockImpl IrBlockImpl = BuildersKt.IrBlockImpl(startOffset, endOffset, irSimpleType, companion.getSUSPEND_CONVERSION());
        IrBlockImpl.getStatements().add(irSimpleFunctionCreateAdapterFunctionForArgument);
        List statements = IrBlockImpl.getStatements();
        IrFunctionReferenceImpl.getArguments().set(0, irExpression);
        statements.add(IrFunctionReferenceImpl);
        return IrBlockImpl;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IrExpression applySamConversion(IrExpression irExpression, FirFunctionTypeConversionExpression firFunctionTypeConversionExpression) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firFunctionTypeConversionExpression);
        ConeKotlinType coneKotlinTypeRemoveExternalProjections = removeExternalProjections(resolvedType, TypeComponentsKt.getTypeContext(getSession()));
        if (coneKotlinTypeRemoveExternalProjections != null) {
            resolvedType = coneKotlinTypeRemoveExternalProjections;
        }
        IrType irType = Fir2IrTypeConverterKt.toIrType(this, resolvedType, ConversionTypeOrigin.DEFAULT);
        if (irExpression instanceof IrBlock) {
            IrBlock irBlock = (IrBlock) irExpression;
            IrStatementOrigin origin = irBlock.getOrigin();
            IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
            if (Intrinsics.areEqual(origin, companion.getADAPTED_FUNCTION_REFERENCE()) || Intrinsics.areEqual(irBlock.getOrigin(), companion.getSUSPEND_CONVERSION())) {
                int lastIndex = CollectionsKt.getLastIndex(irBlock.getStatements());
                Object obj = irBlock.getStatements().get(lastIndex);
                obj.getClass();
                IrTypeOperatorCallImpl irTypeOperatorCallImplApplySamConversion$generateSamConversion = applySamConversion$generateSamConversion((IrExpression) obj, irType, this, firFunctionTypeConversionExpression, resolvedType);
                irBlock.getStatements().set(lastIndex, irTypeOperatorCallImplApplySamConversion$generateSamConversion);
                irExpression.setType(irTypeOperatorCallImplApplySamConversion$generateSamConversion.getType());
                return irExpression;
            }
        }
        return applySamConversion$generateSamConversion(irExpression, irType, this, firFunctionTypeConversionExpression, resolvedType);
    }

    private static final IrTypeOperatorCallImpl applySamConversion$generateSamConversion(IrExpression irExpression, IrType irType, AdapterGenerator adapterGenerator, FirFunctionTypeConversionExpression firFunctionTypeConversionExpression, ConeKotlinType coneKotlinType) {
        return BuildersKt.IrTypeOperatorCallImpl(irExpression.getStartOffset(), irExpression.getEndOffset(), irType, IrTypeOperator.SAM_CONVERSION, irType, adapterGenerator.castArgumentToFunctionalInterfaceForSamType(irExpression, FirTypeUtilsKt.getResolvedType(firFunctionTypeConversionExpression.getExpression()), coneKotlinType));
    }

    public static IrExpression b(FirCallableReferenceAccess firCallableReferenceAccess, AdapterGenerator adapterGenerator, FirFunction firFunction, IrFunctionSymbol irFunctionSymbol, IrExpression irExpression, IrExpression irExpression2, IrFunction irFunction, IrSimpleType irSimpleType, int i, int i2) {
        IrConstructorCallImpl irConstructorCallImplIrCallImpl;
        int i3;
        int i4;
        Ref.IntRef intRef;
        Ref.IntRef intRef2;
        IrVarargImpl IrVarargImpl;
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag;
        FirRegularClassSymbol regularClassSymbol;
        AdapterGenerator adapterGenerator2 = adapterGenerator;
        int i5 = 0;
        IrType irType = null;
        ConeSubstitutor coneSubstitutorCreateConeSubstitutorFromTypeArguments$default = SubstitutionUtilsKt.createConeSubstitutorFromTypeArguments$default(firCallableReferenceAccess, adapterGenerator2.getSession(), false, 2, null);
        if (coneSubstitutorCreateConeSubstitutorFromTypeArguments$default == null) {
            coneSubstitutorCreateConeSubstitutorFromTypeArguments$default = ConeSubstitutor.Empty.INSTANCE;
        }
        ConeSubstitutor coneSubstitutor = coneSubstitutorCreateConeSubstitutorFromTypeArguments$default;
        IrType irType$default = Fir2IrTypeConverterKt.toIrType$default(adapterGenerator2, coneSubstitutor.substituteOrSelf(FirTypeUtilsKt.getConeType(firFunction.getReturnTypeRef())), (ConversionTypeOrigin) null, 2, (Object) null);
        if (irFunctionSymbol instanceof IrConstructorSymbol) {
            irConstructorCallImplIrCallImpl = BuildersKt.fromSymbolOwner$default(IrConstructorCallImpl.Companion, i, i2, irType$default, (IrConstructorSymbol) irFunctionSymbol, (IrStatementOrigin) null, 16, (Object) null);
        } else {
            if (!(irFunctionSymbol instanceof IrSimpleFunctionSymbol)) {
                bu8.a();
                return null;
            }
            irConstructorCallImplIrCallImpl = BuildersKt.IrCallImpl(i, i2, irType$default, (IrSimpleFunctionSymbol) irFunctionSymbol, firCallableReferenceAccess.getTypeArguments().size(), (IrStatementOrigin) null, (IrClassSymbol) null);
        }
        IrConstructorCallImpl irConstructorCallImpl = irConstructorCallImplIrCallImpl;
        Ref.IntRef intRef3 = new Ref.IntRef();
        Ref.IntRef intRef4 = new Ref.IntRef();
        if (irExpression != null || irExpression2 != null) {
            i3 = i2;
            i4 = i;
            IrValueParameter irValueParameter = (IrValueParameter) irFunction.getParameters().get(0);
            irConstructorCallImpl.getArguments().set(0, BuildersKt.IrGetValueImpl(i4, i3, irValueParameter.getType(), irValueParameter.getSymbol(), IrStatementOrigin.Companion.getADAPTED_FUNCTION_REFERENCE()));
            intRef4.element = 1;
        } else if (!(firCallableReferenceAccess.getExplicitReceiver() instanceof FirResolvedQualifier) || (((firFunction instanceof FirConstructor) && ((coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firFunction)) == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) adapterGenerator2, coneClassLikeLookupTagContainingClassLookupTag)) == null || !regularClassSymbol.getRawStatus().isInner())) || firFunction.getStatus().isStatic())) {
            i3 = i2;
            i4 = i;
        } else {
            IrValueParameter irValueParameter2 = (IrValueParameter) irFunction.getParameters().get(0);
            i4 = i;
            i3 = i2;
            irConstructorCallImpl.getArguments().set(0, BuildersKt.IrGetValueImpl$default(i4, i2, irValueParameter2.getType(), irValueParameter2.getSymbol(), (IrStatementOrigin) null, 16, (Object) null));
            intRef4.element = 1;
        }
        FirNamedReference calleeReference = firCallableReferenceAccess.getCalleeReference();
        FirResolvedCallableReference firResolvedCallableReference = calleeReference instanceof FirResolvedCallableReference ? (FirResolvedCallableReference) calleeReference : null;
        Map<FirValueParameter, ResolvedCallArgument<FirExpression>> mappedArguments = firResolvedCallableReference != null ? firResolvedCallableReference.getMappedArguments() : null;
        for (Object obj : firFunction.getValueParameters()) {
            int i6 = i5 + 1;
            if (i5 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirValueParameter firValueParameter = (FirValueParameter) obj;
            ConeKotlinType varargElementType = VariousUtilsKt.getVarargElementType(firValueParameter);
            IrType irType$default2 = varargElementType != null ? Fir2IrTypeConverterKt.toIrType$default((Fir2IrComponents) adapterGenerator2, varargElementType, (ConversionTypeOrigin) irType, 2, (Object) irType) : irType;
            IrSimpleType irType$default3 = Fir2IrTypeConverterKt.toIrType$default((Fir2IrComponents) adapterGenerator2, coneSubstitutor.substituteOrSelf(FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef())), (ConversionTypeOrigin) irType, 2, (Object) irType);
            IrType irType2 = mappedArguments != null ? (ResolvedCallArgument) mappedArguments.get(firValueParameter) : irType;
            if (irType2 instanceof ResolvedCallArgument.VarargArgument) {
                ResolvedCallArgument.VarargArgument varargArgument = (ResolvedCallArgument.VarargArgument) irType2;
                if (varargArgument.getArguments().isEmpty()) {
                    IrVarargImpl = null;
                } else {
                    if (irType$default2 == null || !IrTypeUtilsKt.isTypeParameter(irType$default2) || !(irType$default3 instanceof IrSimpleType) || IrTypeUtilsKt.isPrimitiveArray(irType$default3)) {
                        irType$default2.getClass();
                    } else {
                        irType$default2 = adapterGenerator2.getArgumentTypeAt(irSimpleType, i5);
                        IrSimpleType irSimpleType2 = irType$default3;
                        irType$default3 = IrSimpleTypeImplKt.IrSimpleTypeImpl$default(irSimpleType2.getClassifier(), irSimpleType2.getNullability(), CollectionsKt.listOf(IrSimpleTypeImplKt.makeTypeProjection(irType$default2, Variance.OUT_VARIANCE)), irType$default3.getAnnotations(), (KotlinType) null, 16, (Object) null);
                    }
                    IrVarargImpl = BuildersKt.IrVarargImpl(i4, i3, irType$default3, irType$default2);
                    Iterator it = varargArgument.getArguments().iterator();
                    while (it.hasNext()) {
                        Ref.IntRef intRef5 = intRef4;
                        IrExpressionsKt.addElement(IrVarargImpl, createAdapteeCallForCallableReference$lambda$0$buildIrGetValueArgument(intRef3, adapterGenerator2, irFunction, intRef5, i4, i3, (FirExpression) it.next()));
                        i4 = i;
                        i3 = i2;
                        intRef4 = intRef5;
                        adapterGenerator2 = adapterGenerator;
                    }
                }
                intRef = intRef4;
                irConstructorCallImpl.getArguments().set(i5 + intRef.element, IrVarargImpl);
            } else {
                intRef = intRef4;
                if (Intrinsics.areEqual(irType2, ResolvedCallArgument.DefaultArgument.INSTANCE)) {
                    irConstructorCallImpl.getArguments().set(i5 + intRef.element, null);
                } else if (irType2 instanceof ResolvedCallArgument.SimpleArgument) {
                    i3 = i2;
                    IrType irType3 = irType$default2;
                    intRef2 = intRef;
                    IrGetValue irGetValueCreateAdapteeCallForCallableReference$lambda$0$buildIrGetValueArgument = createAdapteeCallForCallableReference$lambda$0$buildIrGetValueArgument(intRef3, adapterGenerator, irFunction, intRef2, i, i3, (FirExpression) ((ResolvedCallArgument.SimpleArgument) irType2).getCallArgument());
                    i4 = i;
                    if (firValueParameter.getIsVararg()) {
                        IrMemberAccessExpression.ValueArgumentsList arguments = irConstructorCallImpl.getArguments();
                        int i7 = i5 + intRef2.element;
                        irType3.getClass();
                        arguments.set(i7, BuildersKt.IrVarargImpl(i4, i3, irType$default3, irType3, CollectionsKt.listOf(BuildersKt.IrSpreadElementImpl(i4, i3, irGetValueCreateAdapteeCallForCallableReference$lambda$0$buildIrGetValueArgument))));
                    } else {
                        irConstructorCallImpl.getArguments().set(i5 + intRef2.element, irGetValueCreateAdapteeCallForCallableReference$lambda$0$buildIrGetValueArgument);
                    }
                } else {
                    i4 = i;
                    i3 = i2;
                    intRef2 = intRef;
                    if (irType2 != null) {
                        bu8.a();
                        return null;
                    }
                }
                adapterGenerator2 = adapterGenerator;
                intRef4 = intRef2;
                i5 = i6;
                irType = null;
            }
            i4 = i;
            i3 = i2;
            intRef2 = intRef;
            adapterGenerator2 = adapterGenerator;
            intRef4 = intRef2;
            i5 = i6;
            irType = null;
        }
        return adapterGenerator.getCallGenerator().applyTypeArguments$org_jetbrains_kotlin_fir2ir(irConstructorCallImpl, firCallableReferenceAccess);
    }

    private final IrExpression castArgumentToFunctionalInterfaceForSamType(IrExpression argument, ConeKotlinType argumentConeType, ConeKotlinType samType) {
        ConeKotlinType coneKotlinTypeApproximateForIrOrSelf;
        ConeKotlinType functionTypeForPossibleSamType$org_jetbrains_kotlin_fir2ir = getFunctionTypeForPossibleSamType$org_jetbrains_kotlin_fir2ir(samType);
        return (functionTypeForPossibleSamType$org_jetbrains_kotlin_fir2ir == null || (coneKotlinTypeApproximateForIrOrSelf = Fir2IrTypeConverterKt.approximateForIrOrSelf(this, functionTypeForPossibleSamType$org_jetbrains_kotlin_fir2ir)) == null || TypeUtilsKt.isSubtypeOf$default(Fir2IrTypeConverterKt.approximateForIrOrSelf(this, argumentConeType), coneKotlinTypeApproximateForIrOrSelf, getSession(), false, 4, null)) ? argument : IrExpressionsKt.implicitCastTo(argument, Fir2IrTypeConverterKt.toIrType$default(this, coneKotlinTypeApproximateForIrOrSelf, (ConversionTypeOrigin) null, 2, (Object) null));
    }

    private final IrExpression createAdapteeCallForArgument(int startOffset, int endOffset, IrFunction adapterFunction, IrSimpleFunctionSymbol invokeSymbol) {
        IrCallImpl irCallImplIrCallImpl$default = BuildersKt.IrCallImpl$default(startOffset, endOffset, adapterFunction.getReturnType(), invokeSymbol, 0, (IrStatementOrigin) null, (IrClassSymbol) null, 96, (Object) null);
        Iterator it = adapterFunction.getParameters().iterator();
        int i = 0;
        while (it.hasNext()) {
            irCallImplIrCallImpl$default.getArguments().set(i, toIrGetValue((IrValueParameter) it.next(), startOffset, endOffset));
            i++;
        }
        return irCallImplIrCallImpl$default;
    }

    private final IrExpression createAdapteeCallForCallableReference(final FirCallableReferenceAccess callableReferenceAccess, final FirFunction firAdaptee, final IrFunctionSymbol adapteeSymbol, final IrFunction adapterFunction, final IrSimpleType adaptedType, final IrExpression boundDispatchReceiver, final IrExpression boundExtensionReceiver) {
        return OffsetUtilsKt.convertWithOffsets((FirQualifiedAccessExpression) callableReferenceAccess, new Function2() { // from class: iv
            public final Object invoke(Object obj, Object obj2) {
                return AdapterGenerator.b(callableReferenceAccess, this, firAdaptee, adapteeSymbol, boundDispatchReceiver, boundExtensionReceiver, adapterFunction, adaptedType, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
            }
        });
    }

    private static final IrGetValue createAdapteeCallForCallableReference$lambda$0$buildIrGetValueArgument(Ref.IntRef intRef, AdapterGenerator adapterGenerator, IrFunction irFunction, Ref.IntRef intRef2, int i, int i2, FirExpression firExpression) {
        FirFakeArgumentForCallableReference firFakeArgumentForCallableReference = firExpression instanceof FirFakeArgumentForCallableReference ? (FirFakeArgumentForCallableReference) firExpression : null;
        int index = firFakeArgumentForCallableReference != null ? firFakeArgumentForCallableReference.getIndex() : intRef.element;
        intRef.element++;
        return adapterGenerator.toIrGetValue((IrValueDeclaration) irFunction.getParameters().get(index + intRef2.element), i, i2);
    }

    private final IrSimpleFunction createAdapterFunctionForArgument(final int startOffset, final int endOffset, IrSimpleType type, IrType adapterParameterType, final boolean argumentIsNullable, final IrSimpleFunctionSymbol invokeSymbol) {
        final IrType typeOrFail = IrTypesKt.getTypeOrFail((IrTypeArgument) CollectionsKt.last(type.getArguments()));
        List listDropLast = CollectionsKt.dropLast(type.getArguments(), 1);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDropLast, 10));
        Iterator it = listDropLast.iterator();
        while (it.hasNext()) {
            arrayList.add(IrTypesKt.getTypeOrFail((IrTypeArgument) it.next()));
        }
        IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
        IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
        IrDeclarationOrigin adapter_for_suspend_conversion = companion.getADAPTER_FOR_SUSPEND_CONVERSION();
        Name nameIdentifier = Name.identifier(Scope.inventNameForTemporary$default(this.conversionScope.scope(), "suspendConversion", (String) null, 2, (Object) null));
        nameIdentifier.getClass();
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.LOCAL;
        descriptorVisibility.getClass();
        final IrSimpleFunction irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactoryImpl, startOffset, endOffset, adapter_for_suspend_conversion, nameIdentifier, descriptorVisibility, false, false, typeOrFail, Modality.FINAL, new IrSimpleFunctionSymbolImpl((FunctionDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), false, true, false, false, false, (DeserializedContainerSource) null, false, 98304, (Object) null);
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List list = listCreateListBuilder;
        Name nameIdentifier2 = Name.identifier("$callee");
        nameIdentifier2.getClass();
        list.add(createAdapterParameter(irSimpleFunctionCreateSimpleFunction$default, nameIdentifier2, adapterParameterType, companion.getADAPTER_PARAMETER_FOR_SUSPEND_CONVERSION(), IrParameterKind.ExtensionReceiver));
        int i = 0;
        for (Object obj : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            IrType irType = (IrType) obj;
            Name nameIdentifier3 = Name.identifier("p" + i);
            nameIdentifier3.getClass();
            list.add(createAdapterParameter(irSimpleFunctionCreateSimpleFunction$default, nameIdentifier3, irType, IrDeclarationOrigin.Companion.getADAPTER_PARAMETER_FOR_SUSPEND_CONVERSION(), IrParameterKind.Regular));
            i = i2;
        }
        irSimpleFunctionCreateSimpleFunction$default.setParameters(CollectionsKt.build(listCreateListBuilder));
        irSimpleFunctionCreateSimpleFunction$default.setBody(IrFactoryHelpersKt.createBlockBody(IrFactoryImpl.INSTANCE, startOffset, endOffset, new Function1() { // from class: gv
            public final Object invoke(Object obj2) {
                return AdapterGenerator.createAdapterFunctionForArgument$lambda$1$1(this.b, startOffset, endOffset, irSimpleFunctionCreateSimpleFunction$default, invokeSymbol, argumentIsNullable, typeOrFail, (IrBlockBody) obj2);
            }
        }));
        IrDeclarationParent irDeclarationParentParent = this.conversionScope.parent();
        irDeclarationParentParent.getClass();
        irSimpleFunctionCreateSimpleFunction$default.setParent(irDeclarationParentParent);
        return irSimpleFunctionCreateSimpleFunction$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createAdapterFunctionForArgument$lambda$1$1(AdapterGenerator adapterGenerator, int i, int i2, IrSimpleFunction irSimpleFunction, IrSimpleFunctionSymbol irSimpleFunctionSymbol, boolean z, IrType irType, IrBlockBody irBlockBody) {
        irBlockBody.getClass();
        IrWhenImpl irWhenImplCreateAdapteeCallForArgument = adapterGenerator.createAdapteeCallForArgument(i, i2, irSimpleFunction, irSimpleFunctionSymbol);
        if (z) {
            irWhenImplCreateAdapteeCallForArgument = IrElementsCreationUtilsKt.createWhenForSafeFall(adapterGenerator, irWhenImplCreateAdapteeCallForArgument.getType(), ((IrValueParameter) irSimpleFunction.getParameters().get(0)).getSymbol(), irWhenImplCreateAdapteeCallForArgument);
        }
        IrWhenImpl irWhenImpl = irWhenImplCreateAdapteeCallForArgument;
        if (IrTypePredicatesKt.isUnit(irType)) {
            irBlockBody.getStatements().add(irWhenImpl);
        } else {
            irBlockBody.getStatements().add(new IrReturnImpl(i, i2, adapterGenerator.getBuiltins().getNothingType(), irSimpleFunction.getSymbol(), irWhenImpl));
        }
        return Unit.INSTANCE;
    }

    private final IrSimpleFunction createAdapterFunctionForCallableReference(FirCallableReferenceAccess callableReferenceAccess, int startOffset, int endOffset, FirFunction firAdaptee, IrSimpleType type, IrExpression boundDispatchReceiver, IrExpression boundExtensionReceiver) {
        IrFunction irFunction;
        IrType typeOrNull = IrTypesKt.getTypeOrNull((IrTypeArgument) CollectionsKt.last(type.getArguments()));
        typeOrNull.getClass();
        List listDropLast = CollectionsKt.dropLast(type.getArguments(), 1);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDropLast, 10));
        Iterator it = listDropLast.iterator();
        while (it.hasNext()) {
            IrType typeOrNull2 = IrTypesKt.getTypeOrNull((IrTypeArgument) it.next());
            typeOrNull2.getClass();
            arrayList.add(typeOrNull2);
        }
        firAdaptee.getClass();
        Name name = firAdaptee instanceof FirConstructor ? SpecialNames.INIT : firAdaptee.getSymbol().getName();
        IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
        IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
        IrDeclarationOrigin adapter_for_callable_reference = companion.getADAPTER_FOR_CALLABLE_REFERENCE();
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.LOCAL;
        descriptorVisibility.getClass();
        int i = 0;
        IrFunction irFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactoryImpl, startOffset, endOffset, adapter_for_callable_reference, name, descriptorVisibility, firAdaptee.getStatus().isInline(), false, typeOrNull, Modality.FINAL, new IrSimpleFunctionSymbolImpl((FunctionDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), false, firAdaptee.getStatus().isSuspend() || IrTypeUtilsKt.isSuspendFunction(type), firAdaptee.getStatus().isOperator(), firAdaptee.getStatus().isInfix(), false, (DeserializedContainerSource) null, false, 98304, (Object) null);
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        IrExpression irExpression = boundDispatchReceiver == null ? boundExtensionReceiver : boundDispatchReceiver;
        if (irExpression == null) {
            irFunction = irFunctionCreateSimpleFunction$default;
        } else {
            if (boundDispatchReceiver != null && boundExtensionReceiver != null) {
                f2f.a("Bound callable references can't have both receivers: ", UtilsKt.render(callableReferenceAccess));
                return null;
            }
            Name nameIdentifier = Name.identifier("receiver");
            nameIdentifier.getClass();
            IrValueParameter irValueParameterCreateAdapterParameter = createAdapterParameter(irFunctionCreateSimpleFunction$default, nameIdentifier, irExpression.getType(), companion.getADAPTER_PARAMETER_FOR_CALLABLE_REFERENCE(), IrParameterKind.ExtensionReceiver);
            irFunction = irFunctionCreateSimpleFunction$default;
            listCreateListBuilder.add(irValueParameterCreateAdapterParameter);
        }
        List list = listCreateListBuilder;
        Iterator it2 = arrayList.iterator();
        while (true) {
            int i2 = i;
            if (!it2.hasNext()) {
                IrFunction irFunction2 = irFunction;
                irFunction2.setParameters(CollectionsKt.build(listCreateListBuilder));
                IrDeclarationParent irDeclarationParentParent = this.conversionScope.parent();
                irDeclarationParentParent.getClass();
                irFunction2.setParent(irDeclarationParentParent);
                return irFunction2;
            }
            Object next = it2.next();
            i = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Name nameIdentifier2 = Name.identifier("p" + i2);
            nameIdentifier2.getClass();
            IrFunction irFunction3 = irFunction;
            list.add(createAdapterParameter(irFunction3, nameIdentifier2, (IrType) next, IrDeclarationOrigin.Companion.getADAPTER_PARAMETER_FOR_CALLABLE_REFERENCE(), IrParameterKind.Regular));
            irFunction = irFunction3;
        }
    }

    private final IrValueParameter createAdapterParameter(IrFunction adapterFunction, Name name, IrType type, IrDeclarationOrigin origin, IrParameterKind kind) {
        IrValueParameter irValueParameterCreateValueParameter = IrFactoryImpl.INSTANCE.createValueParameter(adapterFunction.getStartOffset(), adapterFunction.getEndOffset(), origin, kind, name, type, false, new IrValueParameterSymbolImpl((ParameterDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), (IrType) null, false, false, false);
        irValueParameterCreateValueParameter.setParent(adapterFunction);
        return irValueParameterCreateValueParameter;
    }

    public static IrBlockImpl d(AdapterGenerator adapterGenerator, FirFunctionSymbol firFunctionSymbol, IrType irType, int i, int i2) {
        IrElement irElementGenerateFunInterfaceConstructorAdapter = adapterGenerator.generateFunInterfaceConstructorAdapter(i, i2, firFunctionSymbol, irType);
        IrSimpleFunctionSymbol symbol = irElementGenerateFunInterfaceConstructorAdapter.getSymbol();
        int size = irElementGenerateFunInterfaceConstructorAdapter.getTypeParameters().size();
        IrSimpleFunctionSymbol symbol2 = irElementGenerateFunInterfaceConstructorAdapter.getSymbol();
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        return BuildersKt.IrBlockImpl(i, i2, irType, companion.getFUN_INTERFACE_CONSTRUCTOR_REFERENCE(), CollectionsKt.listOf(new IrElement[]{irElementGenerateFunInterfaceConstructorAdapter, BuildersKt.IrFunctionReferenceImpl(i, i2, irType, symbol, size, symbol2, companion.getFUN_INTERFACE_CONSTRUCTOR_REFERENCE())}));
    }

    public static IrBlockImpl e(final AdapterGenerator adapterGenerator, FirCallableReferenceAccess firCallableReferenceAccess, IrExpression irExpression, FirFunction firFunction, IrSimpleType irSimpleType, IrFunctionSymbol irFunctionSymbol, final IrType irType, final int i, final int i2) {
        IrExpression irExpressionFindBoundReceiver = adapterGenerator.findBoundReceiver(firCallableReferenceAccess, irExpression, true);
        IrExpression irExpressionFindBoundReceiver2 = adapterGenerator.findBoundReceiver(firCallableReferenceAccess, irExpression, false);
        firFunction.getClass();
        final IrSimpleFunction irSimpleFunctionCreateAdapterFunctionForCallableReference = adapterGenerator.createAdapterFunctionForCallableReference(firCallableReferenceAccess, i, i2, firFunction, irSimpleType, irExpressionFindBoundReceiver, irExpressionFindBoundReceiver2);
        IrExpression irExpression2 = irExpressionFindBoundReceiver;
        final IrExpression irExpressionCreateAdapteeCallForCallableReference = adapterGenerator.createAdapteeCallForCallableReference(firCallableReferenceAccess, firFunction, irFunctionSymbol, irSimpleFunctionCreateAdapterFunctionForCallableReference, irSimpleType, irExpression2, irExpressionFindBoundReceiver2);
        irSimpleFunctionCreateAdapterFunctionForCallableReference.setBody(IrFactoryHelpersKt.createBlockBody(IrFactoryImpl.INSTANCE, i, i2, new Function1() { // from class: hv
            public final Object invoke(Object obj) {
                return AdapterGenerator.generateAdaptedCallableReference$lambda$0$0(irType, adapterGenerator, irExpressionCreateAdapteeCallForCallableReference, i, i2, irSimpleFunctionCreateAdapterFunctionForCallableReference, (IrBlockBody) obj);
            }
        }));
        if (!irSimpleFunctionCreateAdapterFunctionForCallableReference.getTypeParameters().isEmpty()) {
            StringBuilder sb = new StringBuilder("Internal error: function adapter ");
            sb.append(irSimpleFunctionCreateAdapterFunctionForCallableReference.getSymbol());
            sb.append(" has unexpected type parameters: ");
            List typeParameters = irSimpleFunctionCreateAdapterFunctionForCallableReference.getTypeParameters();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
            Iterator it = typeParameters.iterator();
            while (it.hasNext()) {
                arrayList.add(((IrTypeParameter) it.next()).getSymbol());
            }
            ywd.a(sb, arrayList, "\nThey should already be used to determine exact return type and value parameters types");
            return null;
        }
        IrSimpleFunctionSymbol symbol = irSimpleFunctionCreateAdapterFunctionForCallableReference.getSymbol();
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        IrFunctionReferenceImpl IrFunctionReferenceImpl = BuildersKt.IrFunctionReferenceImpl(i, i2, irSimpleType, symbol, 0, (IrFunctionSymbol) null, companion.getADAPTED_FUNCTION_REFERENCE());
        if (irExpression2 == null) {
            irExpression2 = irExpressionFindBoundReceiver2;
        }
        if (irExpression2 != null) {
            IrFunctionReferenceImpl.getArguments().set(0, irExpression2);
        }
        IrFunctionReferenceImpl.setReflectionTarget(irFunctionSymbol);
        IrBlockImpl IrBlockImpl = BuildersKt.IrBlockImpl(i, i2, irSimpleType, companion.getADAPTED_FUNCTION_REFERENCE());
        IrBlockImpl.getStatements().add(irSimpleFunctionCreateAdapterFunctionForCallableReference);
        IrBlockImpl.getStatements().add(IrFunctionReferenceImpl);
        return IrBlockImpl;
    }

    private final IrExpression findBoundReceiver(FirCallableReferenceAccess firCallableReferenceAccess, IrExpression irExpression, boolean z) {
        if ((z ? firCallableReferenceAccess.getDispatchReceiver() : firCallableReferenceAccess.getExtensionReceiver()) == null) {
            return null;
        }
        return getCallGenerator().findIrReceiver$org_jetbrains_kotlin_fir2ir(firCallableReferenceAccess, irExpression, z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IrSimpleFunctionSymbol findInvokeSymbol(ConeClassLikeType expectedFunctionalType, ConeKotlinType argumentType) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (FunctionalTypeUtilsKt.findSubtypeOfBasicFunctionType(argumentType, getSession(), expectedFunctionalType) == null) {
            return null;
        }
        FirFunctionSymbol firFunctionSymbolFindBaseInvokeSymbol = FunctionalTypeUtilsKt.isSomeFunctionType(expectedFunctionalType, getSession()) ? FunctionalTypeUtilsKt.findBaseInvokeSymbol(this, expectedFunctionalType) : FunctionalTypeUtilsKt.findContributedInvokeSymbol(this, expectedFunctionalType, expectedFunctionalType, true);
        if (firFunctionSymbolFindBaseInvokeSymbol != null) {
            IrSimpleFunctionSymbol irFunctionSymbol$default = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(getDeclarationStorage(), firFunctionSymbolFindBaseInvokeSymbol, null, false, 6, null);
            if (irFunctionSymbol$default instanceof IrSimpleFunctionSymbol) {
                return irFunctionSymbol$default;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit generateAdaptedCallableReference$lambda$0$0(IrType irType, AdapterGenerator adapterGenerator, IrExpression irExpression, int i, int i2, IrSimpleFunction irSimpleFunction, IrBlockBody irBlockBody) {
        irBlockBody.getClass();
        if (irType == null || !IrTypePredicatesKt.isUnit(irType)) {
            irBlockBody.getStatements().add(new IrReturnImpl(i, i2, adapterGenerator.getBuiltins().getNothingType(), irSimpleFunction.getSymbol(), irExpression));
        } else {
            irBlockBody.getStatements().add(Fir2IrImplicitCastInserter.INSTANCE.coerceToUnitIfNeeded$org_jetbrains_kotlin_fir2ir(adapterGenerator, irExpression));
        }
        return Unit.INSTANCE;
    }

    private final IrSimpleFunction generateFunInterfaceConstructorAdapter(int startOffset, int endOffset, FirFunctionSymbol<?> callableSymbol, IrType irReferenceType) {
        IrSimpleType irSimpleType = irReferenceType instanceof IrSimpleType ? (IrSimpleType) irReferenceType : null;
        if (irSimpleType == null) {
            pe1.a("Class type expected: ", RenderIrElementKt.render$default(irReferenceType, (DumpIrTreeOptions) null, 1, (Object) null));
            return null;
        }
        IrType argumentTypeAt = getArgumentTypeAt(irSimpleType, 1);
        IrType argumentTypeAt2 = getArgumentTypeAt(irSimpleType, 0);
        FirValueParameterSymbol firValueParameterSymbol = (FirValueParameterSymbol) CollectionsKt.singleOrNull(callableSymbol.getValueParameterSymbols());
        if (firValueParameterSymbol == null) {
            pe1.a("Single value parameter expected: ", callableSymbol.getValueParameterSymbols());
            return null;
        }
        IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
        IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
        IrDeclarationOrigin adapter_for_fun_interface_constructor = companion.getADAPTER_FOR_FUN_INTERFACE_CONSTRUCTOR();
        Name name = callableSymbol.getName();
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.LOCAL;
        descriptorVisibility.getClass();
        IrSimpleFunction irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactoryImpl, startOffset, endOffset, adapter_for_fun_interface_constructor, name, descriptorVisibility, false, false, argumentTypeAt, Modality.FINAL, new IrSimpleFunctionSymbolImpl((FunctionDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), false, false, false, false, false, (DeserializedContainerSource) null, false, 98304, (Object) null);
        IrValueParameter irValueParameterCreateAdapterParameter = createAdapterParameter(irSimpleFunctionCreateSimpleFunction$default, firValueParameterSymbol.getName(), argumentTypeAt2, companion.getADAPTER_PARAMETER_FOR_CALLABLE_REFERENCE(), IrParameterKind.Regular);
        irSimpleFunctionCreateSimpleFunction$default.setParameters(CollectionsKt.listOf(irValueParameterCreateAdapterParameter));
        IrType nothingType = this.c.getBuiltins().getNothingType();
        IrSimpleFunctionSymbol symbol = irSimpleFunctionCreateSimpleFunction$default.getSymbol();
        IrTypeOperator irTypeOperator = IrTypeOperator.SAM_CONVERSION;
        IrCallImpl irCallImplIrCallImplWithShape$default = BuildersKt.IrCallImplWithShape$default(startOffset, endOffset, argumentTypeAt2, getBuiltins().getCheckNotNullSymbol(), 1, 1, 0, false, false, IrStatementOrigin.Companion.getEXCLEXCL(), (IrClassSymbol) null, 1024, (Object) null);
        irCallImplIrCallImplWithShape$default.getTypeArguments().set(0, argumentTypeAt2);
        irCallImplIrCallImplWithShape$default.getArguments().set(0, BuildersKt.IrGetValueImpl$default(startOffset, endOffset, irValueParameterCreateAdapterParameter.getSymbol(), (IrStatementOrigin) null, 8, (Object) null));
        Unit unit = Unit.INSTANCE;
        irSimpleFunctionCreateSimpleFunction$default.setBody(IrFactoryHelpersKt.createBlockBody(irFactoryImpl, startOffset, endOffset, CollectionsKt.listOf(new IrReturnImpl(startOffset, endOffset, nothingType, symbol, BuildersKt.IrTypeOperatorCallImpl(startOffset, endOffset, argumentTypeAt, irTypeOperator, argumentTypeAt, irCallImplIrCallImplWithShape$default)))));
        IrDeclarationParent irDeclarationParentParent = this.conversionScope.parent();
        irDeclarationParentParent.getClass();
        irSimpleFunctionCreateSimpleFunction$default.setParent(irDeclarationParentParent);
        return irSimpleFunctionCreateSimpleFunction$default;
    }

    private final IrType getArgumentTypeAt(IrSimpleType irSimpleType, int i) {
        Object obj = irSimpleType.getArguments().get(i);
        IrTypeProjection irTypeProjection = obj instanceof IrTypeProjection ? (IrTypeProjection) obj : null;
        if (irTypeProjection != null) {
            return irTypeProjection.getType();
        }
        oeg.a("Type projection expected at argument ", i, ": ", RenderIrElementKt.render$default(irSimpleType, (DumpIrTreeOptions) null, 1, (Object) null));
        return null;
    }

    private final boolean hasVarargOrDefaultArguments(FirCallableReferenceAccess callableReferenceAccess) {
        FirNamedReference calleeReference = callableReferenceAccess.getCalleeReference();
        FirResolvedCallableReference firResolvedCallableReference = calleeReference instanceof FirResolvedCallableReference ? (FirResolvedCallableReference) calleeReference : null;
        if (firResolvedCallableReference == null) {
            return false;
        }
        Map<FirValueParameter, ResolvedCallArgument<FirExpression>> mappedArguments = firResolvedCallableReference.getMappedArguments();
        if (mappedArguments.isEmpty()) {
            return false;
        }
        Iterator<Map.Entry<FirValueParameter, ResolvedCallArgument<FirExpression>>> it = mappedArguments.entrySet().iterator();
        while (it.hasNext()) {
            ResolvedCallArgument<FirExpression> value = it.next().getValue();
            if ((value instanceof ResolvedCallArgument.VarargArgument) || (value instanceof ResolvedCallArgument.DefaultArgument)) {
                return true;
            }
        }
        return false;
    }

    private final boolean needCoercionToUnit(IrSimpleType type, FirFunction function) {
        IrType typeOrNull = IrTypesKt.getTypeOrNull((IrTypeArgument) CollectionsKt.last(type.getArguments()));
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(function.getReturnTypeRef());
        return (typeOrNull == null || !IrTypePredicatesKt.isUnit(typeOrNull) || ConeBuiltinTypeUtilsKt.isUnit(coneType) || (ToSymbolUtilsKt.toSymbol(this, coneType) instanceof FirTypeParameterSymbol)) ? false : true;
    }

    private final boolean needSuspendConversion(IrSimpleType type, FirFunction function) {
        return IrTypeUtilsKt.isSuspendFunction(type) && !function.getStatus().isSuspend();
    }

    private final ConeRigidType removeExternalProjections(ConeRigidType coneRigidType) {
        ConeKotlinType type;
        RigidTypeMarker rigidTypeMarkerUpperBoundIfFlexible;
        if (!(coneRigidType instanceof ConeClassLikeType)) {
            return coneRigidType;
        }
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
        ConeKotlinTypeProjection[] typeArguments = coneRigidType.getTypeArguments();
        if (typeArguments.length == 0) {
            return coneRigidType;
        }
        ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneRigidType;
        List<ConeTypeParameterLookupTag> parameters = typeContext.getParameters(coneClassLikeType.getLookupTag());
        Set<ConeTypeParameterLookupTag> set = CollectionsKt.toSet(parameters);
        int length = typeArguments.length;
        ConeKotlinType[] coneKotlinTypeArr = new ConeKotlinType[length];
        for (int i = 0; i < length; i++) {
            ConeKotlinTypeProjection coneKotlinTypeProjection = typeArguments[i];
            ConeTypeParameterLookupTag coneTypeParameterLookupTag = (ConeTypeParameterLookupTag) CollectionsKt.getOrNull(parameters, i);
            if (coneTypeParameterLookupTag == null) {
                return null;
            }
            if (coneKotlinTypeProjection.getKind() == ProjectionKind.IN && this.c.getConfiguration().getCarefulApproximationOfContravariantProjectionForSam()) {
                ConeKotlinType coneKotlinType = (ConeKotlinType) CollectionsKt.singleOrNull(typeContext.getUpperBounds(coneTypeParameterLookupTag));
                if (coneKotlinType != null && (rigidTypeMarkerUpperBoundIfFlexible = typeContext.upperBoundIfFlexible(coneKotlinType)) != null) {
                    type = (ConeRigidType) rigidTypeMarkerUpperBoundIfFlexible;
                    if (!ConeBuiltinTypeUtilsKt.isNullableAny(type)) {
                    }
                }
                return null;
            }
            type = coneKotlinTypeProjection instanceof ConeKotlinTypeProjection ? coneKotlinTypeProjection.getType() : starProjectionTypeRepresentation(coneTypeParameterLookupTag.getTypeParameterSymbol(), set);
            coneKotlinTypeArr[i] = type;
        }
        return ConeTypeUtilsKt.withArguments(coneClassLikeType, coneKotlinTypeArr);
    }

    private final ConeKotlinType starProjectionTypeRepresentation(FirTypeParameterSymbol firTypeParameterSymbol, final Set<ConeTypeParameterLookupTag> set) {
        final ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
        return new AbstractConeSubstitutor(typeContext) { // from class: org.jetbrains.kotlin.fir.backend.generators.AdapterGenerator$starProjectionTypeRepresentation$substitutor$1
            @Override // org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor, org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor
            public ConeTypeProjection substituteArgument(ConeTypeProjection projection, int index) {
                projection.getClass();
                return ((projection instanceof ConeTypeParameterType) && set.contains(((ConeTypeParameterType) projection).getLookupTag())) ? ConeStarProjection.INSTANCE : super.substituteArgument(projection, index);
            }

            @Override // org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor
            public ConeKotlinType substituteType(ConeKotlinType type) {
                type.getClass();
                return null;
            }
        }.substituteOrSelf(((FirResolvedTypeRef) CollectionsKt.first(firTypeParameterSymbol.getResolvedBounds())).getConeType());
    }

    private final IrGetValue toIrGetValue(IrValueDeclaration irValueDeclaration, int i, int i2) {
        return BuildersKt.IrGetValueImpl$default(i, i2, irValueDeclaration.getType(), irValueDeclaration.getSymbol(), (IrStatementOrigin) null, 16, (Object) null);
    }

    public final IrExpression applyFunctionTypeConversion$org_jetbrains_kotlin_fir2ir(IrExpression irExpression, FirFunctionTypeConversionExpression firFunctionTypeConversionExpression) {
        irExpression.getClass();
        firFunctionTypeConversionExpression.getClass();
        FirFunctionConversionKind kind = firFunctionTypeConversionExpression.getKind();
        if (kind instanceof FirFunctionConversionKind.BetweenFunctionTypes) {
            return applyConversionBetweenFunctionTypes(irExpression, firFunctionTypeConversionExpression, (FirFunctionConversionKind.BetweenFunctionTypes) kind);
        }
        if (kind instanceof FirFunctionConversionKind.Sam) {
            return applySamConversion(irExpression, firFunctionTypeConversionExpression);
        }
        bu8.a();
        return null;
    }

    public final IrExpression generateAdaptedCallableReference$org_jetbrains_kotlin_fir2ir(final FirCallableReferenceAccess callableReferenceAccess, final IrExpression explicitReceiverExpression, final IrFunctionSymbol adapteeSymbol, final IrSimpleType type) {
        FirBasedSymbol<?> resolvedSymbol;
        callableReferenceAccess.getClass();
        adapteeSymbol.getClass();
        type.getClass();
        FirResolvedNamedReference resolvedCallableReference = ReferenceUtilsKt.toResolvedCallableReference(callableReferenceAccess);
        FirDeclaration fir = (resolvedCallableReference == null || (resolvedSymbol = resolvedCallableReference.getResolvedSymbol()) == null) ? null : resolvedSymbol.getFir();
        final FirFunction firFunction = fir instanceof FirFunction ? (FirFunction) fir : null;
        final IrType typeOrNull = IrTypesKt.getTypeOrNull((IrTypeArgument) CollectionsKt.last(type.getArguments()));
        return OffsetUtilsKt.convertWithOffsets((FirQualifiedAccessExpression) callableReferenceAccess, new Function2() { // from class: ev
            public final Object invoke(Object obj, Object obj2) {
                return AdapterGenerator.e(this.b, callableReferenceAccess, explicitReceiverExpression, firFunction, type, adapteeSymbol, typeOrNull, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
            }
        });
    }

    public final IrExpression generateFunInterfaceConstructorReference(FirCallableReferenceAccess callableReference, final FirFunctionSymbol<?> callableSymbol, final IrType irReferenceType) {
        callableReference.getClass();
        callableSymbol.getClass();
        irReferenceType.getClass();
        return OffsetUtilsKt.convertWithOffsets((FirQualifiedAccessExpression) callableReference, new Function2() { // from class: fv
            public final Object invoke(Object obj, Object obj2) {
                return AdapterGenerator.d(this.b, callableSymbol, irReferenceType, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
            }
        });
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

    public final ConeKotlinType getFunctionTypeForPossibleSamType$org_jetbrains_kotlin_fir2ir(ConeKotlinType parameterType) {
        parameterType.getClass();
        FirSamResolver.SamConversionInfo samInfoForPossibleSamType = this.samResolver.getSamInfoForPossibleSamType(parameterType);
        if (samInfoForPossibleSamType != null) {
            return samInfoForPossibleSamType.getFunctionalType();
        }
        return null;
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

    public final boolean needToGenerateAdaptedCallableReference$org_jetbrains_kotlin_fir2ir(FirCallableReferenceAccess callableReferenceAccess, IrSimpleType type, FirFunction function) {
        callableReferenceAccess.getClass();
        type.getClass();
        function.getClass();
        return needSuspendConversion(type, function) || needCoercionToUnit(type, function) || hasVarargOrDefaultArguments(callableReferenceAccess);
    }

    private final ConeKotlinType removeExternalProjections(ConeKotlinType coneKotlinType, ConeInferenceContext coneInferenceContext) {
        ConeRigidType lowerBound;
        ConeRigidType upperBound;
        if (coneKotlinType instanceof ConeRigidType) {
            return removeExternalProjections((ConeRigidType) coneKotlinType);
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
            ConeRigidType coneRigidTypeRemoveExternalProjections = removeExternalProjections(coneFlexibleType.getLowerBound());
            if (coneFlexibleType.getIsTrivial()) {
                if (coneRigidTypeRemoveExternalProjections == null) {
                    return null;
                }
                return TypeUtilsKt.coneFlexibleOrSimpleType(coneInferenceContext, coneRigidTypeRemoveExternalProjections, TypeUtilsKt.withNullability$default(coneRigidTypeRemoveExternalProjections, true, coneInferenceContext, null, true, 4, null), true);
            }
            ConeRigidType coneRigidTypeRemoveExternalProjections2 = removeExternalProjections(coneFlexibleType.getUpperBound());
            if (coneRigidTypeRemoveExternalProjections == null && coneRigidTypeRemoveExternalProjections2 == null) {
                return null;
            }
            if (!(coneFlexibleType instanceof ConeRawType)) {
                if (coneRigidTypeRemoveExternalProjections == null) {
                    coneRigidTypeRemoveExternalProjections = coneFlexibleType.getLowerBound();
                }
                if (coneRigidTypeRemoveExternalProjections2 == null) {
                    coneRigidTypeRemoveExternalProjections2 = coneFlexibleType.getUpperBound();
                }
                return TypeUtilsKt.coneFlexibleOrSimpleType(coneInferenceContext, coneRigidTypeRemoveExternalProjections, coneRigidTypeRemoveExternalProjections2, false);
            }
            ConeRawType.Companion companion = ConeRawType.INSTANCE;
            if (coneRigidTypeRemoveExternalProjections == null || (lowerBound = ConeTypeUtilsKt.lowerBoundIfFlexible(coneRigidTypeRemoveExternalProjections)) == null) {
                lowerBound = coneFlexibleType.getLowerBound();
            }
            if (coneRigidTypeRemoveExternalProjections2 == null || (upperBound = ConeTypeUtilsKt.upperBoundIfFlexible(coneRigidTypeRemoveExternalProjections2)) == null) {
                upperBound = coneFlexibleType.getUpperBound();
            }
            return companion.create(lowerBound, upperBound);
        }
        bu8.a();
        return null;
    }
}
