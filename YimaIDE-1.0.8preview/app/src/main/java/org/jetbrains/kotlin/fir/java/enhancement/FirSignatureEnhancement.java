package org.jetbrains.kotlin.fir.java.enhancement;

import defpackage.dwe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.UnaryOperator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.OperatorFunctionChecks;
import org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPrimaryConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReceiverParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyBuilderKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.java.FirJavaTypeConversionMode;
import org.jetbrains.kotlin.fir.java.JavaTypeConversionKt;
import org.jetbrains.kotlin.fir.java.JavaTypeParameterStack;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaClass;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaConstructor;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaField;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaFieldBuilder;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaMethod;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaTypeParameter;
import org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement;
import org.jetbrains.kotlin.fir.java.symbols.FirJavaOverriddenSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculatorKt;
import org.jetbrains.kotlin.fir.scopes.DeferredCallableCopyReturnType;
import org.jetbrains.kotlin.fir.scopes.jvm.SignatureUtilsKt;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionOverrideFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImplWithoutSource;
import org.jetbrains.kotlin.fir.types.jvm.FirJavaTypeRef;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.load.java.AbstractAnnotationTypeQualifierResolver;
import org.jetbrains.kotlin.load.java.AnnotationQualifierApplicabilityType;
import org.jetbrains.kotlin.load.java.FakePureImplementationsProvider;
import org.jetbrains.kotlin.load.java.JavaTypeQualifiersByElementType;
import org.jetbrains.kotlin.load.java.JvmAnnotationNames;
import org.jetbrains.kotlin.load.java.typeEnhancement.AbstractSignatureParts;
import org.jetbrains.kotlin.load.java.typeEnhancement.PredefinedEnhancementInfoKt;
import org.jetbrains.kotlin.load.java.typeEnhancement.PredefinedFunctionEnhancementInfo;
import org.jetbrains.kotlin.load.java.typeEnhancement.TypeEnhancementInfo;
import org.jetbrains.kotlin.load.kotlin.SignatureBuildingComponents;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.FqNamesUtilKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.ReturnValueStatus;
import org.jetbrains.kotlin.util.PrivateForInline;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ò\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u009e\u0001B@\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u001d\u0010\b\u001a\u0019\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000b0\t¢\u0006\u0002\b\f¢\u0006\u0004\b\r\u0010\u000eJ(\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020*2\u0010\b\u0002\u0010+\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000bJ\u000e\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-J4\u0010/\u001a\u0006\u0012\u0002\b\u0003002\n\u0010(\u001a\u0006\u0012\u0002\b\u0003002\b\u0010)\u001a\u0004\u0018\u00010*2\u0010\b\u0002\u0010+\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000bH\u0002J\u001e\u00101\u001a\u0006\u0012\u0002\b\u0003022\n\u00103\u001a\u0006\u0012\u0002\b\u0003022\u0006\u0010)\u001a\u00020*J\u000e\u00104\u001a\u0004\u0018\u00010\u001a*\u00020\nH\u0002J)\u00105\u001a\u0006\u0012\u0002\b\u0003022\n\u00106\u001a\u0006\u0012\u0002\b\u0003022\u0006\u0010)\u001a\u00020*H\u0001b\u0002\b8¢\u0006\u0002\b7J \u00109\u001a\b\u0012\u0002\b\u0003\u0018\u000100*\u00020:2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\n0\u000bH\u0002J;\u00105\u001a\u0006\u0012\u0002\b\u0003002\n\u00106\u001a\u0006\u0012\u0002\b\u0003002\b\u0010)\u001a\u0004\u0018\u00010*2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000bH\u0001b\u0002\b8¢\u0006\u0002\b7J\u0010\u0010<\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030=H\u0002J\u0010\u0010>\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030=H\u0002J>\u0010B\u001a\u0006\u0012\u0002\b\u0003002\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020F2\b\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010G\u001a\u00020\u00072\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000bH\u0002J(\u0010H\u001a\u00020I2\u0006\u00106\u001a\u00020I2\b\u0010J\u001a\u0004\u0018\u00010K2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\n0\u000bH\u0002J>\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020N2\u0006\u0010P\u001a\u00020Q2\n\u0010R\u001a\u0006\u0012\u0002\b\u0003002\u0006\u0010S\u001a\u00020T2\b\u0010U\u001a\u0004\u0018\u00010V2\u0006\u0010W\u001a\u00020XH\u0002J:\u0010Y\u001a\u0004\u0018\u00010V\"\b\b\u0000\u0010Z*\u00020[*\b\u0012\u0004\u0012\u0002HZ0\\2\u0006\u0010C\u001a\u00020D2\u0006\u0010S\u001a\u00020T2\n\u0010R\u001a\u0006\u0012\u0002\b\u000300H\u0002J\u001c\u0010]\u001a\u00020^*\b\u0012\u0004\u0012\u00020[0\u000b2\b\u0010U\u001a\u0004\u0018\u00010VH\u0002J\f\u0010_\u001a\u00020K*\u00020KH\u0002J\u0010\u0010`\u001a\u00020^2\u0006\u0010(\u001a\u00020DH\u0002J*\u0010a\u001a\u00020^2\u0006\u0010\u0002\u001a\u00020b2\u0018\u0010c\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020^0d\u0012\u0004\u0012\u00020^0\tH\u0002J6\u0010a\u001a\u00020^2\u0006\u0010\u0002\u001a\u00020b2\f\u0010e\u001a\b\u0012\u0004\u0012\u00020[0\u000b2\u0018\u0010c\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020^0d\u0012\u0004\u0012\u00020^0\tJ\u0097\u0001\u0010a\u001a\u00020\u00072\f\u0010e\u001a\b\u0012\u0004\u0012\u00020[0\u000b2\b\u0010f\u001a\u0004\u0018\u00010g23\u0010h\u001a/\u0012\n\u0012\b\u0012\u0004\u0012\u00020[0\u000b\u0012\u0006\u0012\u0004\u0018\u00010g\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\\\u0018\u00010\u000b0i¢\u0006\u0002\b\f2%\b\u0004\u0010j\u001a\u001f\u0012\u0004\u0012\u00020k\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\u000b\u0012\u0004\u0012\u00020\u00070i¢\u0006\u0002\b\f2\u0018\u0010c\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020^0d\u0012\u0004\u0012\u00020^0\tH\u0082\bJK\u0010l\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020[0\u000b26\u0010m\u001a2\u0012\u0013\u0012\u00110k¢\u0006\f\bn\u0012\b\b)\u0012\u0004\b\b(o\u0012\u0013\u0012\u00110p¢\u0006\f\bn\u0012\b\b)\u0012\u0004\b\b(q\u0012\u0004\u0012\u00020\u00070iH\u0082\bJ]\u0010r\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\\\u0018\u00010\u000b2\f\u0010e\u001a\b\u0012\u0004\u0012\u00020[0\u000b2\b\u0010f\u001a\u0004\u0018\u00010g2-\u0010h\u001a)\u0012\u0004\u0012\u00020k\u0012\u0004\u0012\u00020\u0014\u0012\u0006\u0012\u0004\u0018\u00010g\u0012\f\u0012\n\u0012\u0004\u0012\u00020Q\u0018\u00010\\0s¢\u0006\u0002\b\fH\u0002J:\u0010t\u001a\u00020^2\f\u0010e\u001a\b\u0012\u0004\u0012\u00020[0\u000b2\b\u0010f\u001a\u0004\u0018\u00010g2\u0018\u0010c\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020^0d\u0012\u0004\u0012\u00020^0\tH\u0002J:\u0010u\u001a\u00020\u00072\f\u0010e\u001a\b\u0012\u0004\u0012\u00020[0\u000b2\b\u0010f\u001a\u0004\u0018\u00010g2\u0018\u0010c\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020^0d\u0012\u0004\u0012\u00020^0\tH\u0002JC\u0010v\u001a\u00020^*\b\u0012\u0004\u0012\u00020[0\u000b2\u0012\u0010w\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0\\0\u000b2\u001a\b\u0004\u0010x\u001a\u0014\u0012\u0004\u0012\u00020y\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020Q0iH\u0082\bJ \u0010z\u001a\u00020Q2\u0006\u0010o\u001a\u00020y2\u0006\u0010{\u001a\u00020Q2\u0006\u0010|\u001a\u00020\u0007H\u0002J\u001a\u0010}\u001a\b\u0012\u0004\u0012\u00020~0\u000b2\f\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020~0\u000bJ\u0013\u0010\u0080\u0001\u001a\u0004\u0018\u00010 2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0012\u0010\u0081\u0001\u001a\u00020~2\u0007\u0010\u0082\u0001\u001a\u00020~H\u0002JG\u0010\u0083\u0001\u001a\u00020Q2\u0007\u0010\u0084\u0001\u001a\u00020D2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\n0\u000b2\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u001a2\b\u0010J\u001a\u0004\u0018\u00010K2\u0007\u0010\u0086\u0001\u001a\u00020N2\u0007\u0010\u0087\u0001\u001a\u00020pH\u0002J&\u0010\u0088\u0001\u001a\u00020Q2\u0006\u0010\u0002\u001a\u00020\n2\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u001a2\b\u0010J\u001a\u0004\u0018\u00010KH\u0002JF\u0010\u0088\u0001\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010Q\u0012\u0007\u0012\u0005\u0018\u00010\u008a\u00010\u0089\u00012\u0006\u0010\u0002\u001a\u00020\n2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\n0\u000b2\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u001a2\b\u0010J\u001a\u0004\u0018\u00010KH\u0002JQ\u0010\u008b\u0001\u001a\u00020Q*\u00020D2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\n0\u000b2\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u00012\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u001a2\b\u0010\u008e\u0001\u001a\u00030\u008f\u00012\n\u0010\u0090\u0001\u001a\u0005\u0018\u00010\u0091\u00012\u0007\u0010\u0092\u0001\u001a\u00020\u0007H\u0002Jc\u00105\u001a\u00020Q*\u00020\n2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\n0\u000b2\n\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u008d\u00012\u0007\u0010\u0094\u0001\u001a\u00020\u00072\t\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0096\u0001\u001a\u00030\u0097\u00012\b\u0010\u008e\u0001\u001a\u00030\u008f\u00012\n\u0010\u0090\u0001\u001a\u0005\u0018\u00010\u0091\u00012\u0007\u0010\u0092\u0001\u001a\u00020\u0007H\u0002J=\u00105\u001a\u00020Q*\u00030\u0098\u00012\u0007\u0010\u0099\u0001\u001a\u00020~2\r\u0010\u009a\u0001\u001a\b\u0012\u0004\u0012\u00020~0\u000b2\b\u0010\u009b\u0001\u001a\u00030\u009c\u00012\f\b\u0002\u0010\u0090\u0001\u001a\u0005\u0018\u00010\u0091\u0001H\u0002J!\u0010\u009d\u0001\u001a\u00020 *\u00020~2\b\u0010\u009b\u0001\u001a\u00030\u009c\u00012\b\u0010f\u001a\u0004\u0018\u00010gH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R%\u0010\b\u001a\u0019\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000b0\t¢\u0006\u0002\b\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0019\u001a\u0004\u0018\u00010\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001cR\u001d\u0010\u001f\u001a\u0004\u0018\u00010 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\u001e\u001a\u0004\b!\u0010\"R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010?\u001a\u0012\u0012\u0004\u0012\u00020\n0@j\b\u0012\u0004\u0012\u00020\n`AX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u009f\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/FirSignatureEnhancement;", Argument.Delimiters.none, "owner", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "enhanceClassHeaderOnly", Argument.Delimiters.none, "overridden", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/fir/FirSession;ZLkotlin/jvm/functions/Function1;)V", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "javaTypeParameterStack", "Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;", "getJavaTypeParameterStack", "()Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;", "typeQualifierResolver", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirAnnotationTypeQualifierResolver;", "contextQualifiers", "Lorg/jetbrains/kotlin/load/java/JavaTypeQualifiersByElementType;", "getContextQualifiers", "()Lorg/jetbrains/kotlin/load/java/JavaTypeQualifiersByElementType;", "contextQualifiers$delegate", "Lkotlin/Lazy;", "privateKtSuperClass", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getPrivateKtSuperClass", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "privateKtSuperClass$delegate", "enhancementsCache", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirEnhancedSymbolsStorage$EnhancementSymbolsCache;", "enhancedFunction", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "function", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "precomputedOverridden", "enhancedConstructor", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "constructor", "enhancedFunctionImpl", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "enhancedProperty", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "property", "computeDefaultQualifiers", "enhance", "original", "enhance$org_jetbrains_kotlin_fir_jvm", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "enhanceAccessorOrNull", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "overriddenProperties", "isEnhanceable", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "isEnhanceableIntersection", "overriddenComparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "enhanceMethod", "firMethod", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "methodId", "Lorg/jetbrains/kotlin/name/CallableId;", "isIntersectionOverride", "enhanceStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "predefinedEnhancementInfo", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/PredefinedFunctionEnhancementInfo;", "overriddenMembers", "buildEnhancedValueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "valueParameter", "enhancedReturnType", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "functionSymbol", "declarationOrigin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "typeParameterSubstitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "valueParameterKind", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameterKind;", "copyTypeParametersWithNewContainingDeclaration", "T", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", Argument.Delimiters.none, "replaceTypeParameterBounds", Argument.Delimiters.none, "useWarningsIfErrorModeIsNotEnabledYet", "updateIsOperatorFlagIfNeeded", "enhanceTypeParameterBounds", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;", "lock", "Lkotlin/Function0;", "typeParameters", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "round", "Lkotlin/Function2;", "updater", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter;", "iterateJavaTypeParameters", "action", "Lkotlin/ParameterName;", "typeParameter", Argument.Delimiters.none, "currentIndex", "performRoundOfBoundsResolution", "Lkotlin/Function3;", "enhanceTypeParameterBoundsSecondRound", "enhanceTypeParameterBoundsFirstRound", "replaceEnhancedBounds", "secondRoundBounds", "block", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "enhanceTypeParameterBound", "bound", "forceOnlyHeadTypeConstructor", "enhanceSuperTypes", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "nonEnhancedSuperTypes", "getPurelyImplementedSupertype", "enhanceSuperType", ModuleXmlParser.TYPE, "enhanceValueParameterType", "ownerFunction", "defaultQualifiers", "ownerParameter", "index", "enhanceReturnType", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/scopes/DeferredCallableCopyReturnType;", "enhanceValueParameter", "parameterContainer", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "typeInSignature", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirSignatureEnhancement$TypeInSignature;", "predefined", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/TypeEnhancementInfo;", "forAnnotationMember", "typeContainer", "isCovariant", "containerQualifiers", "containerApplicabilityType", "Lorg/jetbrains/kotlin/load/java/AnnotationQualifierApplicabilityType;", "Lorg/jetbrains/kotlin/fir/java/enhancement/EnhancementSignatureParts;", "typeRef", "typeRefsFromOverridden", "mode", "Lorg/jetbrains/kotlin/fir/java/FirJavaTypeConversionMode;", "toConeKotlinType", "TypeInSignature", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSignatureEnhancement {

    /* JADX INFO: renamed from: contextQualifiers$delegate, reason: from kotlin metadata */
    private final Lazy contextQualifiers;
    private final boolean enhanceClassHeaderOnly;
    private final FirEnhancedSymbolsStorage.EnhancementSymbolsCache enhancementsCache;
    private final Function1<FirCallableDeclaration, List<FirCallableDeclaration>> overridden;
    private final Comparator<FirCallableDeclaration> overriddenComparator;
    private final FirRegularClass owner;

    /* JADX INFO: renamed from: privateKtSuperClass$delegate, reason: from kotlin metadata */
    private final Lazy privateKtSuperClass;
    private final FirSession session;
    private final FirAnnotationTypeQualifierResolver typeQualifierResolver;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\"\u0018\u00002\u00020\u0001:\u0003\b\t\nB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/FirSignatureEnhancement$TypeInSignature;", Argument.Delimiters.none, "<init>", "()V", "getTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "member", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Return", "ReturnPossiblyDeferred", "ValueParameter", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class TypeInSignature {

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/FirSignatureEnhancement$TypeInSignature$Return;", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirSignatureEnhancement$TypeInSignature;", "<init>", "()V", "getTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "member", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Return extends TypeInSignature {
            public static final Return INSTANCE = new Return();

            private Return() {
            }

            @Override // org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement.TypeInSignature
            public FirTypeRef getTypeRef(FirCallableDeclaration member) {
                member.getClass();
                return member.getReturnTypeRef();
            }
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/FirSignatureEnhancement$TypeInSignature$ReturnPossiblyDeferred;", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirSignatureEnhancement$TypeInSignature;", "calc", "Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;", "<init>", "(Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;)V", "getTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "member", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class ReturnPossiblyDeferred extends TypeInSignature {
            private final CallableCopyTypeCalculator calc;

            public ReturnPossiblyDeferred(CallableCopyTypeCalculator callableCopyTypeCalculator) {
                callableCopyTypeCalculator.getClass();
                this.calc = callableCopyTypeCalculator;
            }

            @Override // org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement.TypeInSignature
            public FirTypeRef getTypeRef(FirCallableDeclaration member) {
                member.getClass();
                if (member.getOrigin() instanceof FirDeclarationOrigin.Java) {
                    return member.getReturnTypeRef();
                }
                FirTypeRef firTypeRefMo617computeReturnType = this.calc.mo617computeReturnType(member);
                if (firTypeRefMo617computeReturnType != null) {
                    return firTypeRefMo617computeReturnType;
                }
                FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
                firErrorTypeRefBuilder.setDiagnostic(new ConeSimpleDiagnostic("Could not resolve returnType for " + member, null, 2, null));
                return firErrorTypeRefBuilder.build();
            }
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/FirSignatureEnhancement$TypeInSignature$ValueParameter;", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirSignatureEnhancement$TypeInSignature;", "index", Argument.Delimiters.none, "<init>", "(I)V", "getIndex", "()I", "getTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "member", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class ValueParameter extends TypeInSignature {
            private final int index;

            public ValueParameter(int i) {
                this.index = i;
            }

            public final int getIndex() {
                return this.index;
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
            @Override // org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement.TypeInSignature
            public FirTypeRef getTypeRef(FirCallableDeclaration member) throws KotlinIllegalStateExceptionWithAttachments {
                member.getClass();
                List<FirValueParameter> contextParameters = member.getContextParameters();
                FirReceiverParameter receiverParameter = member.getReceiverParameter();
                if (this.index < contextParameters.size()) {
                    return contextParameters.get(this.index).getReturnTypeRef();
                }
                if (receiverParameter != null && this.index == contextParameters.size()) {
                    return receiverParameter.getTypeRef();
                }
                if (member instanceof FirProperty) {
                    return ((FirProperty) member).getReturnTypeRef();
                }
                if (member instanceof FirFunction) {
                    return ((FirFunction) member).getValueParameters().get((this.index - contextParameters.size()) - (receiverParameter != null ? 1 : 0)).getReturnTypeRef();
                }
                KotlinIllegalStateExceptionWithAttachments kotlinIllegalStateExceptionWithAttachments = new KotlinIllegalStateExceptionWithAttachments("Declaration is not a function");
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "member", member);
                kotlinIllegalStateExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalStateExceptionWithAttachments;
            }
        }

        public abstract FirTypeRef getTypeRef(FirCallableDeclaration member);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FirSignatureEnhancement(FirRegularClass firRegularClass, FirSession firSession, boolean z, Function1<? super FirCallableDeclaration, ? extends List<? extends FirCallableDeclaration>> function1) {
        firRegularClass.getClass();
        firSession.getClass();
        function1.getClass();
        this.owner = firRegularClass;
        this.session = firSession;
        this.enhanceClassHeaderOnly = z;
        this.overridden = function1;
        this.typeQualifierResolver = FirAnnotationTypeQualifierResolverKt.getJavaAnnotationTypeQualifierResolver(firSession);
        this.contextQualifiers = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: ae5
            public final Object invoke() {
                return FirSignatureEnhancement.a(this.b);
            }
        });
        this.privateKtSuperClass = LazyKt.lazy(new Function0() { // from class: be5
            public final Object invoke() {
                return FirSignatureEnhancement.b(this.b);
            }
        });
        this.enhancementsCache = (FirEnhancedSymbolsStorage.EnhancementSymbolsCache) SignatureEnhancementKt.getEnhancedSymbolStorage(firSession).getCacheByOwner().getValue(firRegularClass.getSymbol(), null);
        final Comparator comparator = new Comparator() { // from class: org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement$special$$inlined$compareByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((FirCallableDeclaration) t2).getContextParameters().size()), Integer.valueOf(((FirCallableDeclaration) t).getContextParameters().size()));
            }
        };
        this.overriddenComparator = new Comparator() { // from class: org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement$special$$inlined$thenByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparator.compare(t, t2);
                if (iCompare != 0) {
                    return iCompare;
                }
                return ComparisonsKt.compareValues(Boolean.valueOf(((FirCallableDeclaration) t2).getReceiverParameter() != null), Boolean.valueOf(((FirCallableDeclaration) t).getReceiverParameter() != null));
            }
        };
    }

    public static JavaTypeQualifiersByElementType a(FirSignatureEnhancement firSignatureEnhancement) {
        return firSignatureEnhancement.typeQualifierResolver.extractDefaultQualifiers(firSignatureEnhancement.owner);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ConeClassLikeType b(FirSignatureEnhancement firSignatureEnhancement) {
        Object obj;
        EffectiveVisibility effectiveVisibility;
        Iterator it = SupertypeUtilsKt.getSuperTypes$default(firSignatureEnhancement.owner.getSymbol(), firSignatureEnhancement.session, false, false, false, null, 22, null).iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol((ConeClassLikeType) next, firSignatureEnhancement.session);
            FirClassLikeDeclaration firClassLikeDeclaration = symbol != null ? (FirClassLikeDeclaration) symbol.getFir() : null;
            if (firClassLikeDeclaration != null && !(firClassLikeDeclaration.getOrigin() instanceof FirDeclarationOrigin.Java)) {
                FirDeclarationStatus status = firClassLikeDeclaration.getStatus();
                FirResolvedDeclarationStatus firResolvedDeclarationStatus = status instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status : null;
                if (firResolvedDeclarationStatus == null || (effectiveVisibility = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) {
                    effectiveVisibility = EffectiveVisibility.Local.INSTANCE;
                }
                if (effectiveVisibility.getPrivateApi()) {
                    obj = next;
                    break;
                }
            }
        }
        return (ConeClassLikeType) obj;
    }

    private final FirValueParameter buildEnhancedValueParameter(FirValueParameter valueParameter, FirResolvedTypeRef enhancedReturnType, FirFunctionSymbol<?> functionSymbol, FirDeclarationOrigin declarationOrigin, ConeSubstitutor typeParameterSubstitutor, FirValueParameterKind valueParameterKind) {
        FirExpression defaultValue;
        if (!(valueParameter.getDefaultValue() instanceof FirErrorExpression) && (defaultValue = valueParameter.getDefaultValue()) != null) {
            defaultValue.replaceConeTypeOrNull(enhancedReturnType.getConeType());
        }
        FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
        firValueParameterBuilder.setSource(valueParameter.getSource());
        firValueParameterBuilder.setContainingDeclarationSymbol(functionSymbol);
        firValueParameterBuilder.setModuleData(getModuleData());
        firValueParameterBuilder.setOrigin(declarationOrigin);
        firValueParameterBuilder.setReturnTypeRef(TypeUtilsKt.withReplacedConeType$default(enhancedReturnType, typeParameterSubstitutor != null ? typeParameterSubstitutor.substituteOrNull(enhancedReturnType.getConeType()) : null, null, 2, null));
        firValueParameterBuilder.setName(valueParameter.getName());
        firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
        firValueParameterBuilder.setDefaultValue(valueParameter.getDefaultValue());
        firValueParameterBuilder.setCrossinline(valueParameter.getIsCrossinline());
        firValueParameterBuilder.setNoinline(valueParameter.getIsNoinline());
        firValueParameterBuilder.setVararg(valueParameter.getIsVararg());
        firValueParameterBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
        CollectionsKt.addAll(firValueParameterBuilder.getAnnotations(), valueParameter.getAnnotations());
        firValueParameterBuilder.setValueParameterKind(valueParameterKind);
        return firValueParameterBuilder.mo288build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit c(FirSignatureEnhancement firSignatureEnhancement, Name name, FirJavaOverriddenSyntheticPropertySymbol firJavaOverriddenSyntheticPropertySymbol, FirFunctionSymbol firFunctionSymbol, FirNamedFunction firNamedFunction, FirFunctionSymbol firFunctionSymbol2, FirNamedFunction firNamedFunction2, FirVariable firVariable, List list, FirSyntheticPropertyBuilder firSyntheticPropertyBuilder) {
        firSyntheticPropertyBuilder.getClass();
        firSyntheticPropertyBuilder.setModuleData(firSignatureEnhancement.getModuleData());
        firSyntheticPropertyBuilder.setName(name);
        firSyntheticPropertyBuilder.setSymbol(new FirJavaOverriddenSyntheticPropertySymbol(firJavaOverriddenSyntheticPropertySymbol.getCallableId(), firJavaOverriddenSyntheticPropertySymbol.getGetterId()));
        FirNamedFunction firNamedFunction3 = (FirNamedFunction) (firFunctionSymbol != null ? (FirFunction) firFunctionSymbol.getFir() : null);
        if (firNamedFunction3 != null) {
            firNamedFunction = firNamedFunction3;
        }
        firSyntheticPropertyBuilder.setDelegateGetter(firNamedFunction);
        FirNamedFunction firNamedFunction4 = (FirNamedFunction) (firFunctionSymbol2 != null ? (FirFunction) firFunctionSymbol2.getFir() : null);
        if (firNamedFunction4 != null) {
            firNamedFunction2 = firNamedFunction4;
        }
        firSyntheticPropertyBuilder.setDelegateSetter(firNamedFunction2);
        FirSyntheticProperty firSyntheticProperty = (FirSyntheticProperty) firVariable;
        firSyntheticPropertyBuilder.setCustomStatus(firSignatureEnhancement.enhanceStatus(firSyntheticProperty.getStatus(), null, list));
        firSyntheticPropertyBuilder.setDeprecationsProvider(DeprecationUtilsKt.getDeprecationsProviderFromAccessors(firSignatureEnhancement.session, firSyntheticPropertyBuilder.getDelegateGetter(), firSyntheticPropertyBuilder.getDelegateSetter()));
        firSyntheticPropertyBuilder.setDispatchReceiverType(firSyntheticProperty.getDispatchReceiverType());
        return Unit.INSTANCE;
    }

    private final JavaTypeQualifiersByElementType computeDefaultQualifiers(FirCallableDeclaration firCallableDeclaration) {
        if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
            return null;
        }
        return AbstractAnnotationTypeQualifierResolver.extractAndMergeDefaultQualifiers$default(this.typeQualifierResolver, getContextQualifiers(), firCallableDeclaration.getAnnotations(), false, 4, (Object) null);
    }

    private final <T extends FirTypeParameterRef> ConeSubstitutor copyTypeParametersWithNewContainingDeclaration(List<T> list, FirFunction firFunction, FirDeclarationOrigin firDeclarationOrigin, FirFunctionSymbol<?> firFunctionSymbol) {
        FirTypeParameterRef firTypeParameterRefBuild;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<T> list2 = list;
        List<FirTypeParameterRef> typeParameters = firFunction.getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        for (FirTypeParameterRef firTypeParameterRef : typeParameters) {
            boolean z = firTypeParameterRef instanceof FirTypeParameter;
            if (z) {
                FirTypeParameter firTypeParameter = (FirTypeParameter) firTypeParameterRef;
                FirTypeParameterBuilder firTypeParameterBuilder = new FirTypeParameterBuilder();
                firTypeParameterBuilder.setSource(firTypeParameter.getSource());
                firTypeParameterBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(firTypeParameter));
                firTypeParameterBuilder.setModuleData(firTypeParameter.getModuleData());
                firTypeParameterBuilder.setOrigin(firTypeParameter.getOrigin());
                firTypeParameterBuilder.setAttributes(firTypeParameter.getAttributes().copy());
                firTypeParameterBuilder.setName(firTypeParameter.getName());
                firTypeParameterBuilder.setContainingDeclarationSymbol(firTypeParameter.getContainingDeclarationSymbol());
                firTypeParameterBuilder.setVariance(firTypeParameter.getVariance());
                firTypeParameterBuilder.setReified(firTypeParameter.getIsReified());
                firTypeParameterBuilder.getBounds().addAll(firTypeParameter.getBounds());
                firTypeParameterBuilder.getAnnotations().addAll(firTypeParameter.getAnnotations());
                firTypeParameterBuilder.setOrigin(firDeclarationOrigin);
                firTypeParameterBuilder.setSymbol(new FirTypeParameterSymbol());
                firTypeParameterBuilder.setContainingDeclarationSymbol(firFunctionSymbol);
                firTypeParameterRefBuild = firTypeParameterBuilder.mo288build();
            } else {
                firTypeParameterRefBuild = firTypeParameterRef;
            }
            if (z) {
                linkedHashMap.put(((FirTypeParameter) firTypeParameterRef).getSymbol(), new ConeTypeParameterTypeImpl(firTypeParameterRefBuild.getSymbol().getLookupTag(), false, null, 4, null));
            }
            firTypeParameterRefBuild.getClass();
            arrayList.add(firTypeParameterRefBuild);
        }
        CollectionsKt.addAll(list2, arrayList);
        if (linkedHashMap.isEmpty()) {
            return null;
        }
        return ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap, this.session, false, 4, null);
    }

    public static ConeKotlinType d(FirSignatureEnhancement firSignatureEnhancement, KtSourceElement ktSourceElement, FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        return JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible$default(firTypeRef, firSignatureEnhancement.session, firSignatureEnhancement.getJavaTypeParameterStack(), ktSourceElement, null, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:19:0x0053  */
    public final FirResolvedTypeRef enhance(FirCallableDeclaration firCallableDeclaration, List<? extends FirCallableDeclaration> list, FirAnnotationContainer firAnnotationContainer, boolean z, JavaTypeQualifiersByElementType javaTypeQualifiersByElementType, AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType, TypeInSignature typeInSignature, TypeEnhancementInfo typeEnhancementInfo, boolean z2) {
        AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType2;
        FirJavaTypeConversionMode firJavaTypeConversionMode;
        FirTypeRef typeRef = typeInSignature.getTypeRef(firCallableDeclaration);
        List<? extends FirCallableDeclaration> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(typeInSignature.getTypeRef((FirCallableDeclaration) it.next()));
        }
        if (z2) {
            annotationQualifierApplicabilityType2 = annotationQualifierApplicabilityType;
            if (annotationQualifierApplicabilityType2 == AnnotationQualifierApplicabilityType.VALUE_PARAMETER) {
                FirValueParameter firValueParameter = firAnnotationContainer instanceof FirValueParameter ? (FirValueParameter) firAnnotationContainer : null;
                if (Intrinsics.areEqual(firValueParameter != null ? firValueParameter.getName() : null, StandardNames.DEFAULT_VALUE_PARAMETER)) {
                    firJavaTypeConversionMode = FirJavaTypeConversionMode.ANNOTATION_CONSTRUCTOR_PARAMETER;
                } else {
                    firJavaTypeConversionMode = FirJavaTypeConversionMode.ANNOTATION_MEMBER;
                }
            } else {
                firJavaTypeConversionMode = FirJavaTypeConversionMode.ANNOTATION_MEMBER;
            }
        } else {
            firJavaTypeConversionMode = FirJavaTypeConversionMode.DEFAULT;
            annotationQualifierApplicabilityType2 = annotationQualifierApplicabilityType;
        }
        return enhance(new EnhancementSignatureParts(this.session, this.typeQualifierResolver, firAnnotationContainer, z, false, annotationQualifierApplicabilityType2, javaTypeQualifiersByElementType), typeRef, arrayList, firJavaTypeConversionMode, typeEnhancementInfo);
    }

    public static /* synthetic */ FirResolvedTypeRef enhance$default(FirSignatureEnhancement firSignatureEnhancement, EnhancementSignatureParts enhancementSignatureParts, FirTypeRef firTypeRef, List list, FirJavaTypeConversionMode firJavaTypeConversionMode, TypeEnhancementInfo typeEnhancementInfo, int i, Object obj) {
        if ((i & 8) != 0) {
            typeEnhancementInfo = null;
        }
        return firSignatureEnhancement.enhance(enhancementSignatureParts, firTypeRef, list, firJavaTypeConversionMode, typeEnhancementInfo);
    }

    private final FirFunctionSymbol<?> enhanceAccessorOrNull(FirNamedFunction firNamedFunction, List<? extends FirCallableDeclaration> list) {
        if (isEnhanceable(firNamedFunction.getSymbol())) {
            return enhancedFunction(firNamedFunction.getSymbol(), firNamedFunction.getName(), list);
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v4, types: [org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder] */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r5v19, types: [org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder] */
    /* JADX WARN: Type inference failed for: r5v20, types: [org.jetbrains.kotlin.fir.declarations.builder.FirPrimaryConstructorBuilder] */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v28 */
    /* JADX WARN: Type inference failed for: r7v24 */
    /* JADX WARN: Type inference failed for: r7v25 */
    /* JADX WARN: Type inference failed for: r7v26, types: [org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus] */
    /* JADX WARN: Type inference failed for: r7v30, types: [org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl] */
    /* JADX WARN: Type inference failed for: r7v32 */
    private final FirFunctionSymbol<?> enhanceMethod(FirFunction firMethod, CallableId methodId, Name name, boolean isIntersectionOverride, List<? extends FirCallableDeclaration> precomputedOverridden) throws KotlinIllegalArgumentExceptionWithAttachments {
        List<? extends FirCallableDeclaration> listEmptyList;
        FirNamedFunctionSymbol firIntersectionOverrideFunctionSymbol;
        ConeSubstitutor coneSubstitutorCopyTypeParametersWithNewContainingDeclaration;
        FirReceiverParameter firReceiverParameterBuild;
        ?? r10;
        FirFunctionSymbol<?> firFunctionSymbol;
        FirFunction firFunction;
        FirDeclarationOrigin firDeclarationOrigin;
        int i;
        FirFunctionSymbol<?> firFunctionSymbol2;
        ?? r5;
        ?? firDeclarationStatusImpl;
        FirResolvedDeclarationStatus firResolvedDeclarationStatus;
        List<FirValueParameter> contextParameters;
        final FirSignatureEnhancement firSignatureEnhancement = this;
        KtSourceElement source = firMethod.getSource();
        final KtSourceElement ktSourceElementFakeElement$default = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null;
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        ClassId classId = firSignatureEnhancement.owner.getSymbol().getClassId();
        Function1 function1 = new Function1() { // from class: ce5
            public final Object invoke(Object obj) {
                return FirSignatureEnhancement.d(this.b, ktSourceElementFakeElement$default, (FirTypeRef) obj);
            }
        };
        FirFunction firFunction2 = firMethod;
        PredefinedFunctionEnhancementInfo predefinedFunctionEnhancementInfo = (PredefinedFunctionEnhancementInfo) PredefinedEnhancementInfoKt.getPREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE().get(signatureBuildingComponents.signature(classId, SignatureUtilsKt.computeJvmDescriptor$default(firFunction2, null, false, function1, 3, null)));
        PredefinedFunctionEnhancementInfo predefinedFunctionEnhancementInfoUseWarningsIfErrorModeIsNotEnabledYet = predefinedFunctionEnhancementInfo != null ? firSignatureEnhancement.useWarningsIfErrorModeIsNotEnabledYet(predefinedFunctionEnhancementInfo) : null;
        if (predefinedFunctionEnhancementInfoUseWarningsIfErrorModeIsNotEnabledYet != null) {
            predefinedFunctionEnhancementInfoUseWarningsIfErrorModeIsNotEnabledYet.getParametersInfo().size();
            firFunction2.getValueParameters().size();
            Unit unit = Unit.INSTANCE;
        }
        JavaTypeQualifiersByElementType javaTypeQualifiersByElementTypeComputeDefaultQualifiers = computeDefaultQualifiers(firMethod);
        if (precomputedOverridden == null) {
            FirNamedFunction firNamedFunction = firFunction2 instanceof FirNamedFunction ? (FirNamedFunction) firFunction2 : null;
            listEmptyList = firNamedFunction != null ? (List) firSignatureEnhancement.overridden.invoke(firNamedFunction) : null;
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
        } else {
            listEmptyList = precomputedOverridden;
        }
        boolean z = firFunction2 instanceof FirNamedFunction;
        Pair<FirResolvedTypeRef, DeferredCallableCopyReturnType> pairEnhanceReturnType = z ? firSignatureEnhancement.enhanceReturnType(firFunction2, listEmptyList, javaTypeQualifiersByElementTypeComputeDefaultQualifiers, predefinedFunctionEnhancementInfoUseWarningsIfErrorModeIsNotEnabledYet) : TuplesKt.to(firFunction2.getReturnTypeRef(), null);
        FirResolvedTypeRef firResolvedTypeRefWithReplacedConeType$default = (FirTypeRef) pairEnhanceReturnType.component1();
        DeferredCallableCopyReturnType delegatingDeferredReturnTypeWithSubstitution = (DeferredCallableCopyReturnType) pairEnhanceReturnType.component2();
        List<? extends FirCallableDeclaration> list = listEmptyList;
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) CollectionsKt.minWithOrNull(list, firSignatureEnhancement.overriddenComparator);
        boolean z2 = false;
        int size = (firCallableDeclaration == null || (contextParameters = firCallableDeclaration.getContextParameters()) == null) ? 0 : contextParameters.size();
        int i2 = (firCallableDeclaration != null ? firCallableDeclaration.getReceiverParameter() : null) != null ? 1 : 0;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = firFunction2.getValueParameters().iterator();
        FirResolvedTypeRef firResolvedTypeRef = null;
        int i3 = 0;
        while (it.hasNext()) {
            int i4 = i3 + 1;
            ArrayList arrayList3 = arrayList;
            FirResolvedTypeRef firResolvedTypeRefEnhanceValueParameterType = firSignatureEnhancement.enhanceValueParameterType(firFunction2, listEmptyList, javaTypeQualifiersByElementTypeComputeDefaultQualifiers, predefinedFunctionEnhancementInfoUseWarningsIfErrorModeIsNotEnabledYet, (FirValueParameter) it.next(), i3);
            if (i3 < size) {
                arrayList3.add(firResolvedTypeRefEnhanceValueParameterType);
            } else if (i2 == 0 || i3 != size) {
                arrayList2.add(firResolvedTypeRefEnhanceValueParameterType);
            } else {
                firResolvedTypeRef = firResolvedTypeRefEnhanceValueParameterType;
            }
            arrayList = arrayList3;
            i3 = i4;
        }
        ArrayList arrayList4 = arrayList;
        FirDeclarationOrigin firDeclarationOrigin2 = isIntersectionOverride ? FirDeclarationOrigin.IntersectionOverride.INSTANCE : FirDeclarationOrigin.Enhancement.INSTANCE;
        if (firFunction2 instanceof FirConstructor) {
            FirConstructorSymbol firConstructorSymbol = new FirConstructorSymbol(methodId);
            FirConstructor firConstructor = (FirConstructor) firFunction2;
            if (firConstructor.getIsPrimary()) {
                ?? firPrimaryConstructorBuilder = new FirPrimaryConstructorBuilder();
                FirDeclarationStatus status = firConstructor.getStatus();
                if (status instanceof FirResolvedDeclarationStatus) {
                    firResolvedDeclarationStatus = (FirResolvedDeclarationStatus) status;
                } else {
                    firDeclarationStatusImpl = 0;
                }
                if (firDeclarationStatusImpl == 0) {
                    firDeclarationStatusImpl = firResolvedDeclarationStatus;
                    firDeclarationStatusImpl = new FirDeclarationStatusImpl(firFunction2.getStatus().getVisibility(), Modality.FINAL);
                    firDeclarationStatusImpl.setInner(firFunction2.getStatus().isInner());
                    firDeclarationStatusImpl.setHasStableParameterNames(firFunction2.getStatus().getHasStableParameterNames());
                }
                firDeclarationStatusImpl = firResolvedDeclarationStatus;
                firPrimaryConstructorBuilder.setStatus(firDeclarationStatusImpl);
                firPrimaryConstructorBuilder.setSymbol(firConstructorSymbol);
                firPrimaryConstructorBuilder.setDispatchReceiverType(firConstructor.getDispatchReceiverType());
                firPrimaryConstructorBuilder.setAttributes(firConstructor.getAttributes().copy());
                r5 = firPrimaryConstructorBuilder;
            } else {
                FirConstructorBuilder firConstructorBuilder = new FirConstructorBuilder();
                firConstructorBuilder.setStatus(firConstructor.getStatus());
                firConstructorBuilder.setSymbol(firConstructorSymbol);
                firConstructorBuilder.setDispatchReceiverType(firConstructor.getDispatchReceiverType());
                firConstructorBuilder.setAttributes(firConstructor.getAttributes().copy());
                r5 = firConstructorBuilder;
            }
            r5.setLocal(false);
            r5.setSource(firConstructor.getSource());
            r5.setModuleData(firSignatureEnhancement.getModuleData());
            r5.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
            r5.setOrigin(firDeclarationOrigin2);
            coneSubstitutorCopyTypeParametersWithNewContainingDeclaration = firSignatureEnhancement.copyTypeParametersWithNewContainingDeclaration(r5.getTypeParameters(), firFunction2, firDeclarationOrigin2, firConstructorSymbol);
            if (coneSubstitutorCopyTypeParametersWithNewContainingDeclaration == null || !(firResolvedTypeRefWithReplacedConeType$default instanceof FirResolvedTypeRef)) {
                firResolvedTypeRefWithReplacedConeType$default.getClass();
            } else {
                firResolvedTypeRefWithReplacedConeType$default = TypeUtilsKt.withReplacedConeType$default(firResolvedTypeRefWithReplacedConeType$default, coneSubstitutorCopyTypeParametersWithNewContainingDeclaration.substituteOrNull(firResolvedTypeRefWithReplacedConeType$default.getConeType()), null, 2, null);
            }
            r5.setReturnTypeRef(firResolvedTypeRefWithReplacedConeType$default);
            firSignatureEnhancement.replaceTypeParameterBounds(r5.getTypeParameters(), coneSubstitutorCopyTypeParametersWithNewContainingDeclaration);
            r10 = r5;
            firFunctionSymbol = firConstructorSymbol;
        } else {
            if (!z) {
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unknown Java method to enhance: " + firFunction2.getClass(), (Throwable) null);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "firMethod", firFunction2);
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
            boolean zAreEqual = Intrinsics.areEqual(ClassMembersKt.isJavaRecordComponent(firFunction2), Boolean.TRUE);
            FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
            FirNamedFunction firNamedFunction2 = (FirNamedFunction) firFunction2;
            firNamedFunctionBuilder.setSource(firNamedFunction2.getSource());
            firNamedFunctionBuilder.setModuleData(firSignatureEnhancement.getModuleData());
            firNamedFunctionBuilder.setOrigin(firDeclarationOrigin2);
            name.getClass();
            firNamedFunctionBuilder.setName(name);
            firNamedFunctionBuilder.setStatus(firSignatureEnhancement.enhanceStatus(firNamedFunction2.getStatus(), predefinedFunctionEnhancementInfoUseWarningsIfErrorModeIsNotEnabledYet, listEmptyList));
            firNamedFunctionBuilder.setLocal(false);
            if (isIntersectionOverride) {
                ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    arrayList5.add(((FirCallableDeclaration) it2.next()).getSymbol());
                }
                DeclarationSymbolMarker symbol = firNamedFunction2.getSymbol();
                FirIntersectionCallableSymbol firIntersectionCallableSymbol = symbol instanceof FirIntersectionCallableSymbol ? (FirIntersectionCallableSymbol) symbol : null;
                if (firIntersectionCallableSymbol != null && firIntersectionCallableSymbol.getContainsMultipleNonSubsumed()) {
                    z2 = true;
                }
                firIntersectionOverrideFunctionSymbol = new FirIntersectionOverrideFunctionSymbol(methodId, arrayList5, z2);
            } else {
                firIntersectionOverrideFunctionSymbol = new FirNamedFunctionSymbol(methodId);
            }
            firNamedFunctionBuilder.setSymbol(firIntersectionOverrideFunctionSymbol);
            firNamedFunctionBuilder.setResolvePhase(delegatingDeferredReturnTypeWithSubstitution == null ? FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES() : FirResolvePhase.IMPLICIT_TYPES_BODY_RESOLVE.getPrevious());
            coneSubstitutorCopyTypeParametersWithNewContainingDeclaration = firSignatureEnhancement.copyTypeParametersWithNewContainingDeclaration(firNamedFunctionBuilder.getTypeParameters(), firFunction2, firDeclarationOrigin2, firIntersectionOverrideFunctionSymbol);
            if (coneSubstitutorCopyTypeParametersWithNewContainingDeclaration != null && (firResolvedTypeRefWithReplacedConeType$default instanceof FirResolvedTypeRef)) {
                firResolvedTypeRefWithReplacedConeType$default = TypeUtilsKt.withReplacedConeType$default(firResolvedTypeRefWithReplacedConeType$default, coneSubstitutorCopyTypeParametersWithNewContainingDeclaration.substituteOrNull(firResolvedTypeRefWithReplacedConeType$default.getConeType()), null, 2, null);
            } else if (firResolvedTypeRefWithReplacedConeType$default == null) {
                firResolvedTypeRefWithReplacedConeType$default = FirImplicitTypeRefImplWithoutSource.INSTANCE;
            }
            firNamedFunctionBuilder.setReturnTypeRef(firResolvedTypeRefWithReplacedConeType$default);
            FirResolvedTypeRef firResolvedTypeRef2 = firResolvedTypeRef;
            FirResolvedTypeRef firResolvedTypeRefWithReplacedConeType$default2 = firResolvedTypeRef2 != null ? TypeUtilsKt.withReplacedConeType$default(firResolvedTypeRef2, coneSubstitutorCopyTypeParametersWithNewContainingDeclaration != null ? coneSubstitutorCopyTypeParametersWithNewContainingDeclaration.substituteOrNull(firResolvedTypeRef2.getConeType()) : null, null, 2, null) : null;
            if (firResolvedTypeRefWithReplacedConeType$default2 != null) {
                FirReceiverParameterBuilder firReceiverParameterBuilder = new FirReceiverParameterBuilder();
                firReceiverParameterBuilder.setTypeRef(firResolvedTypeRefWithReplacedConeType$default2);
                CollectionsKt.addAll(firReceiverParameterBuilder.getAnnotations(), ((FirValueParameter) CollectionsKt.first(firNamedFunction2.getValueParameters())).getAnnotations());
                KtSourceElement source2 = firResolvedTypeRefWithReplacedConeType$default2.getSource();
                firReceiverParameterBuilder.setSource(source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.ReceiverFromType.INSTANCE, null, 2, null) : null);
                firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
                firReceiverParameterBuilder.setModuleData(firSignatureEnhancement.getModuleData());
                firReceiverParameterBuilder.setOrigin(firDeclarationOrigin2);
                firReceiverParameterBuilder.setContainingDeclarationSymbol(firNamedFunctionBuilder.getSymbol());
                firReceiverParameterBuild = firReceiverParameterBuilder.mo288build();
            } else {
                firReceiverParameterBuild = null;
            }
            firNamedFunctionBuilder.setReceiverParameter(firReceiverParameterBuild);
            firSignatureEnhancement.replaceTypeParameterBounds(firNamedFunctionBuilder.getTypeParameters(), coneSubstitutorCopyTypeParametersWithNewContainingDeclaration);
            firNamedFunctionBuilder.setDispatchReceiverType(firNamedFunction2.getDispatchReceiverType());
            FirDeclarationAttributes firDeclarationAttributesCopy = firNamedFunction2.getAttributes().copy();
            if (delegatingDeferredReturnTypeWithSubstitution != null) {
                if (coneSubstitutorCopyTypeParametersWithNewContainingDeclaration != null) {
                    delegatingDeferredReturnTypeWithSubstitution = new DelegatingDeferredReturnTypeWithSubstitution(delegatingDeferredReturnTypeWithSubstitution, coneSubstitutorCopyTypeParametersWithNewContainingDeclaration);
                }
                CallableCopyTypeCalculatorKt.setDeferredCallableCopyReturnType(firDeclarationAttributesCopy, delegatingDeferredReturnTypeWithSubstitution);
            }
            firNamedFunctionBuilder.setAttributes(firDeclarationAttributesCopy);
            z2 = zAreEqual;
            firFunctionSymbol = firIntersectionOverrideFunctionSymbol;
            r10 = firNamedFunctionBuilder;
        }
        ConeSubstitutor coneSubstitutor = coneSubstitutorCopyTypeParametersWithNewContainingDeclaration;
        if (size > 0) {
            List<FirValueParameter> contextParameters2 = r10.getContextParameters();
            List listTake = CollectionsKt.take(firFunction2.getValueParameters(), size);
            Iterator it3 = arrayList4.iterator();
            int i5 = 10;
            ArrayList arrayList6 = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(listTake, 10), CollectionsKt.collectionSizeOrDefault(arrayList4, 10)));
            FirSignatureEnhancement firSignatureEnhancement2 = firSignatureEnhancement;
            FirFunctionSymbol<?> firFunctionSymbol3 = firFunctionSymbol;
            for (Iterator it4 = listTake.iterator(); it4.hasNext() && it3.hasNext(); it4 = it4) {
                FirDeclarationOrigin firDeclarationOrigin3 = firDeclarationOrigin2;
                FirFunctionSymbol<?> firFunctionSymbol4 = firFunctionSymbol3;
                arrayList6.add(firSignatureEnhancement2.buildEnhancedValueParameter((FirValueParameter) it4.next(), (FirResolvedTypeRef) it3.next(), firFunctionSymbol4, firDeclarationOrigin3, coneSubstitutor, FirValueParameterKind.ContextParameter));
                firSignatureEnhancement2 = this;
                firFunctionSymbol3 = firFunctionSymbol4;
                firDeclarationOrigin2 = firDeclarationOrigin3;
                firFunction2 = firFunction2;
                i5 = i5;
            }
            firFunction = firFunction2;
            firDeclarationOrigin = firDeclarationOrigin2;
            i = i5;
            firFunctionSymbol2 = firFunctionSymbol3;
            CollectionsKt.addAll(contextParameters2, arrayList6);
        } else {
            firFunction = firFunction2;
            firDeclarationOrigin = firDeclarationOrigin2;
            i = 10;
            firFunctionSymbol2 = firFunctionSymbol;
        }
        List<FirValueParameter> valueParameters = r10.getValueParameters();
        List listDrop = CollectionsKt.drop(firFunction.getValueParameters(), size + i2);
        Iterator it5 = listDrop.iterator();
        Iterator it6 = arrayList2.iterator();
        ArrayList arrayList7 = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(listDrop, i), CollectionsKt.collectionSizeOrDefault(arrayList2, i)));
        while (it5.hasNext() && it6.hasNext()) {
            arrayList7.add(buildEnhancedValueParameter((FirValueParameter) it5.next(), (FirResolvedTypeRef) it6.next(), firFunctionSymbol2, firDeclarationOrigin, coneSubstitutor, FirValueParameterKind.Regular));
        }
        CollectionsKt.addAll(valueParameters, arrayList7);
        CollectionsKt.addAll(r10.getAnnotations(), firFunction.getAnnotations());
        r10.setDeprecationsProvider(DeprecationUtilsKt.getDeprecationsProviderFromAnnotations$default(r10.getAnnotations(), this.session, true, null, 4, null));
        FirFunction firFunctionBuild = r10.mo288build();
        if (z2) {
            ClassMembersKt.setJavaRecordComponent(firFunctionBuild, Boolean.TRUE);
        }
        updateIsOperatorFlagIfNeeded(firFunctionBuild);
        return firFunctionBuild.getSymbol();
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0069  */
    /* JADX WARN: Code duplicated, block: B:33:0x006f  */
    private final Pair<FirResolvedTypeRef, DeferredCallableCopyReturnType> enhanceReturnType(final FirCallableDeclaration owner, final List<? extends FirCallableDeclaration> overriddenMembers, final JavaTypeQualifiersByElementType defaultQualifiers, final PredefinedFunctionEnhancementInfo predefinedEnhancementInfo) {
        TypeEnhancementInfo returnTypeInfo;
        boolean z = owner instanceof FirJavaField;
        final AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType = z ? AnnotationQualifierApplicabilityType.FIELD : AnnotationQualifierApplicabilityType.METHOD_RETURN_TYPE;
        boolean z2 = false;
        if (!z && this.owner.getClassKind() == ClassKind.ANNOTATION_CLASS) {
            z2 = true;
        }
        final boolean z3 = z2;
        List<? extends FirCallableDeclaration> list = overriddenMembers;
        if ((list instanceof Collection) && list.isEmpty()) {
            if (getPrivateKtSuperClass() != null) {
            }
            TypeInSignature.Return r7 = TypeInSignature.Return.INSTANCE;
            if (predefinedEnhancementInfo != null) {
                returnTypeInfo = predefinedEnhancementInfo.getReturnTypeInfo();
            } else {
                returnTypeInfo = null;
            }
            return TuplesKt.to(enhance(owner, overriddenMembers, owner, true, defaultQualifiers, annotationQualifierApplicabilityType, r7, returnTypeInfo, z3), null);
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (((FirCallableDeclaration) it.next()).getReturnTypeRef() instanceof FirImplicitTypeRef) {
            }
        }
        if (getPrivateKtSuperClass() != null || !(owner.getReturnTypeRef() instanceof FirImplicitTypeRef)) {
            TypeInSignature.Return r8 = TypeInSignature.Return.INSTANCE;
            if (predefinedEnhancementInfo != null) {
                returnTypeInfo = predefinedEnhancementInfo.getReturnTypeInfo();
            } else {
                returnTypeInfo = null;
            }
            return TuplesKt.to(enhance(owner, overriddenMembers, owner, true, defaultQualifiers, annotationQualifierApplicabilityType, r8, returnTypeInfo, z3), null);
        }
        return TuplesKt.to(null, new DeferredCallableCopyReturnType() { // from class: org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement$enhanceReturnType$deferredReturnTypeCalculation$1
            @Override // org.jetbrains.kotlin.fir.scopes.DeferredCallableCopyReturnType
            public ConeKotlinType computeReturnType(CallableCopyTypeCalculator calc) {
                calc.getClass();
                FirSignatureEnhancement firSignatureEnhancement = this.this$0;
                FirCallableDeclaration firCallableDeclaration = owner;
                List<FirCallableDeclaration> list2 = overriddenMembers;
                JavaTypeQualifiersByElementType javaTypeQualifiersByElementType = defaultQualifiers;
                AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType2 = annotationQualifierApplicabilityType;
                FirSignatureEnhancement.TypeInSignature.ReturnPossiblyDeferred returnPossiblyDeferred = new FirSignatureEnhancement.TypeInSignature.ReturnPossiblyDeferred(calc);
                PredefinedFunctionEnhancementInfo predefinedFunctionEnhancementInfo = predefinedEnhancementInfo;
                return firSignatureEnhancement.enhance(firCallableDeclaration, list2, firCallableDeclaration, true, javaTypeQualifiersByElementType, annotationQualifierApplicabilityType2, returnPossiblyDeferred, predefinedFunctionEnhancementInfo != null ? predefinedFunctionEnhancementInfo.getReturnTypeInfo() : null, z3).getConeType();
            }

            public String toString() {
                return "Deferred for Enhancement (Overriddens with Implicit Types)";
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0073  */
    private final FirDeclarationStatus enhanceStatus(FirDeclarationStatus original, PredefinedFunctionEnhancementInfo predefinedEnhancementInfo, List<? extends FirCallableDeclaration> overriddenMembers) {
        ReturnValueStatus returnValueStatus;
        ReturnValueStatus returnValueStatus2;
        ReturnValueStatus returnValueStatus3 = original.getReturnValueStatus();
        ReturnValueStatus returnValueStatus4 = ReturnValueStatus.Unspecified;
        if (returnValueStatus3 == returnValueStatus4) {
            if (predefinedEnhancementInfo != null && (returnValueStatus2 = predefinedEnhancementInfo.getReturnValueStatus()) != null) {
                ReturnValueStatus returnValueStatus5 = returnValueStatus2 != returnValueStatus4 ? returnValueStatus2 : null;
                if (returnValueStatus5 != null) {
                    return UtilsKt.copy(original, (8388575 & 1) != 0 ? original.getVisibility() : null, (8388575 & 2) != 0 ? original.getModality() : null, (8388575 & 4) != 0 ? original.isExpect() : false, (8388575 & 8) != 0 ? original.isActual() : false, (8388575 & 16) != 0 ? original.isOverride() : false, (8388575 & 32) != 0 ? original.isOperator() : false, (8388575 & 64) != 0 ? original.isInfix() : false, (8388575 & 128) != 0 ? original.isInline() : false, (8388575 & 256) != 0 ? original.isValue() : false, (8388575 & 512) != 0 ? original.isTailRec() : false, (8388575 & 1024) != 0 ? original.isExternal() : false, (8388575 & 2048) != 0 ? original.isConst() : false, (8388575 & 4096) != 0 ? original.isLateInit() : false, (8388575 & 8192) != 0 ? original.isInner() : false, (8388575 & 16384) != 0 ? original.isCompanion() : false, (8388575 & 32768) != 0 ? original.isData() : false, (8388575 & 65536) != 0 ? original.isSuspend() : false, (8388575 & 131072) != 0 ? original.isStatic() : false, (8388575 & 262144) != 0 ? original.isFromSealedClass() : false, (8388575 & 524288) != 0 ? original.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? original.isFun() : false, (8388575 & 2097152) != 0 ? original.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? original.getReturnValueStatus() : returnValueStatus5);
                }
            }
            Iterator<T> it = overriddenMembers.iterator();
            while (it.hasNext()) {
                ReturnValueStatus returnValueStatus6 = ((FirCallableDeclaration) it.next()).getStatus().getReturnValueStatus();
                if (returnValueStatus6 == ReturnValueStatus.Unspecified) {
                    returnValueStatus6 = null;
                }
                if (returnValueStatus6 != null) {
                    returnValueStatus = returnValueStatus6;
                    if (returnValueStatus != null) {
                        return UtilsKt.copy(original, (8388575 & 1) != 0 ? original.getVisibility() : null, (8388575 & 2) != 0 ? original.getModality() : null, (8388575 & 4) != 0 ? original.isExpect() : false, (8388575 & 8) != 0 ? original.isActual() : false, (8388575 & 16) != 0 ? original.isOverride() : false, (8388575 & 32) != 0 ? original.isOperator() : false, (8388575 & 64) != 0 ? original.isInfix() : false, (8388575 & 128) != 0 ? original.isInline() : false, (8388575 & 256) != 0 ? original.isValue() : false, (8388575 & 512) != 0 ? original.isTailRec() : false, (8388575 & 1024) != 0 ? original.isExternal() : false, (8388575 & 2048) != 0 ? original.isConst() : false, (8388575 & 4096) != 0 ? original.isLateInit() : false, (8388575 & 8192) != 0 ? original.isInner() : false, (8388575 & 16384) != 0 ? original.isCompanion() : false, (8388575 & 32768) != 0 ? original.isData() : false, (8388575 & 65536) != 0 ? original.isSuspend() : false, (8388575 & 131072) != 0 ? original.isStatic() : false, (8388575 & 262144) != 0 ? original.isFromSealedClass() : false, (8388575 & 524288) != 0 ? original.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? original.isFun() : false, (8388575 & 2097152) != 0 ? original.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? original.getReturnValueStatus() : returnValueStatus);
                    }
                }
            }
            returnValueStatus = null;
            if (returnValueStatus != null) {
                return UtilsKt.copy(original, (8388575 & 1) != 0 ? original.getVisibility() : null, (8388575 & 2) != 0 ? original.getModality() : null, (8388575 & 4) != 0 ? original.isExpect() : false, (8388575 & 8) != 0 ? original.isActual() : false, (8388575 & 16) != 0 ? original.isOverride() : false, (8388575 & 32) != 0 ? original.isOperator() : false, (8388575 & 64) != 0 ? original.isInfix() : false, (8388575 & 128) != 0 ? original.isInline() : false, (8388575 & 256) != 0 ? original.isValue() : false, (8388575 & 512) != 0 ? original.isTailRec() : false, (8388575 & 1024) != 0 ? original.isExternal() : false, (8388575 & 2048) != 0 ? original.isConst() : false, (8388575 & 4096) != 0 ? original.isLateInit() : false, (8388575 & 8192) != 0 ? original.isInner() : false, (8388575 & 16384) != 0 ? original.isCompanion() : false, (8388575 & 32768) != 0 ? original.isData() : false, (8388575 & 65536) != 0 ? original.isSuspend() : false, (8388575 & 131072) != 0 ? original.isStatic() : false, (8388575 & 262144) != 0 ? original.isFromSealedClass() : false, (8388575 & 524288) != 0 ? original.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? original.isFun() : false, (8388575 & 2097152) != 0 ? original.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? original.getReturnValueStatus() : returnValueStatus);
            }
        }
        return original;
    }

    private final FirTypeRef enhanceSuperType(FirTypeRef type) {
        return enhance$default(this, new EnhancementSignatureParts(this.session, this.typeQualifierResolver, (FirAnnotationContainer) null, false, false, AnnotationQualifierApplicabilityType.TYPE_USE, getContextQualifiers()), type, CollectionsKt.emptyList(), FirJavaTypeConversionMode.SUPERTYPE, null, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirResolvedTypeRef enhanceTypeParameterBound(FirTypeParameter typeParameter, FirResolvedTypeRef bound, boolean forceOnlyHeadTypeConstructor) {
        return enhance$default(this, new EnhancementSignatureParts(this.session, this.typeQualifierResolver, typeParameter, false, forceOnlyHeadTypeConstructor, AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS, getContextQualifiers()), bound, CollectionsKt.emptyList(), FirJavaTypeConversionMode.TYPE_PARAMETER_BOUND_AFTER_FIRST_ROUND, null, 8, null);
    }

    private final boolean enhanceTypeParameterBoundsFirstRound(final List<? extends FirTypeParameterRef> typeParameters, KtSourceElement source, Function1<? super Function0<Unit>, Unit> lock) {
        final List<List<FirResolvedTypeRef>> listPerformRoundOfBoundsResolution = performRoundOfBoundsResolution(typeParameters, source, FirSignatureEnhancement$enhanceTypeParameterBoundsFirstRound$1$1.INSTANCE);
        if (listPerformRoundOfBoundsResolution == null) {
            return false;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        lock.invoke(new Function0<Unit>() { // from class: org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement$enhanceTypeParameterBoundsFirstRound$$inlined$enhanceTypeParameterBounds$1
            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m551invoke() {
                Ref.BooleanRef booleanRef2 = booleanRef;
                List<FirTypeParameterRef> list = typeParameters;
                List list2 = listPerformRoundOfBoundsResolution;
                boolean z = false;
                int i = 0;
                for (FirTypeParameterRef firTypeParameterRef : list) {
                    if (firTypeParameterRef instanceof FirJavaTypeParameter) {
                        if (!((FirJavaTypeParameter) firTypeParameterRef).storeBoundsAfterFirstRound$org_jetbrains_kotlin_fir_jvm((List) list2.get(i))) {
                            booleanRef2.element = z;
                        }
                        i++;
                    }
                }
                z = true;
                booleanRef2.element = z;
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m551invoke();
                return Unit.INSTANCE;
            }
        });
        return booleanRef.element;
    }

    private final void enhanceTypeParameterBoundsSecondRound(final List<? extends FirTypeParameterRef> typeParameters, KtSourceElement source, Function1<? super Function0<Unit>, Unit> lock) {
        final List<List<FirResolvedTypeRef>> listPerformRoundOfBoundsResolution = performRoundOfBoundsResolution(typeParameters, source, FirSignatureEnhancement$enhanceTypeParameterBoundsSecondRound$1$secondRoundBounds$1.INSTANCE);
        if (listPerformRoundOfBoundsResolution == null) {
            listPerformRoundOfBoundsResolution = null;
        } else {
            int i = 0;
            int i2 = 0;
            for (FirTypeParameterRef firTypeParameterRef : typeParameters) {
                if (firTypeParameterRef instanceof FirJavaTypeParameter) {
                    final FirJavaTypeParameter firJavaTypeParameter = (FirJavaTypeParameter) firTypeParameterRef;
                    listPerformRoundOfBoundsResolution.get(i2).replaceAll(new UnaryOperator() { // from class: org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement$enhanceTypeParameterBoundsSecondRound$lambda$0$$inlined$replaceEnhancedBounds$1
                        @Override // java.util.function.Function
                        public final FirResolvedTypeRef apply(FirResolvedTypeRef firResolvedTypeRef) {
                            firResolvedTypeRef.getClass();
                            return this.enhanceTypeParameterBound(firJavaTypeParameter, firResolvedTypeRef, true);
                        }
                    });
                    i2++;
                }
            }
            for (FirTypeParameterRef firTypeParameterRef2 : typeParameters) {
                if (firTypeParameterRef2 instanceof FirJavaTypeParameter) {
                    final FirJavaTypeParameter firJavaTypeParameter2 = (FirJavaTypeParameter) firTypeParameterRef2;
                    listPerformRoundOfBoundsResolution.get(i).replaceAll(new UnaryOperator() { // from class: org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement$enhanceTypeParameterBoundsSecondRound$lambda$0$$inlined$replaceEnhancedBounds$2
                        @Override // java.util.function.Function
                        public final FirResolvedTypeRef apply(FirResolvedTypeRef firResolvedTypeRef) {
                            firResolvedTypeRef.getClass();
                            return this.enhanceTypeParameterBound(firJavaTypeParameter2, firResolvedTypeRef, false);
                        }
                    });
                    i++;
                }
            }
        }
        if (listPerformRoundOfBoundsResolution == null) {
            return;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        lock.invoke(new Function0<Unit>() { // from class: org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement$enhanceTypeParameterBoundsSecondRound$$inlined$enhanceTypeParameterBounds$1
            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m552invoke() {
                Ref.BooleanRef booleanRef2 = booleanRef;
                List<FirTypeParameterRef> list = typeParameters;
                List list2 = listPerformRoundOfBoundsResolution;
                boolean z = false;
                int i3 = 0;
                for (FirTypeParameterRef firTypeParameterRef3 : list) {
                    if (firTypeParameterRef3 instanceof FirJavaTypeParameter) {
                        if (!((FirJavaTypeParameter) firTypeParameterRef3).storeBoundsAfterSecondRound$org_jetbrains_kotlin_fir_jvm((List) list2.get(i3))) {
                            booleanRef2.element = z;
                        }
                        i3++;
                    }
                }
                z = true;
                booleanRef2.element = z;
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m552invoke();
                return Unit.INSTANCE;
            }
        });
    }

    private final FirResolvedTypeRef enhanceValueParameter(FirFunction firFunction, List<? extends FirCallableDeclaration> list, FirAnnotationContainer firAnnotationContainer, JavaTypeQualifiersByElementType javaTypeQualifiersByElementType, TypeInSignature typeInSignature, TypeEnhancementInfo typeEnhancementInfo, boolean z) {
        JavaTypeQualifiersByElementType javaTypeQualifiersByElementTypeExtractAndMergeDefaultQualifiers$default;
        return enhance(firFunction, list, firAnnotationContainer == null ? firFunction : firAnnotationContainer, false, (firAnnotationContainer == null || (javaTypeQualifiersByElementTypeExtractAndMergeDefaultQualifiers$default = AbstractAnnotationTypeQualifierResolver.extractAndMergeDefaultQualifiers$default(this.typeQualifierResolver, javaTypeQualifiersByElementType, firAnnotationContainer.getAnnotations(), false, 4, (Object) null)) == null) ? javaTypeQualifiersByElementType : javaTypeQualifiersByElementTypeExtractAndMergeDefaultQualifiers$default, AnnotationQualifierApplicabilityType.VALUE_PARAMETER, typeInSignature, typeEnhancementInfo, z);
    }

    private final FirResolvedTypeRef enhanceValueParameterType(FirFunction ownerFunction, List<? extends FirCallableDeclaration> overriddenMembers, JavaTypeQualifiersByElementType defaultQualifiers, PredefinedFunctionEnhancementInfo predefinedEnhancementInfo, FirValueParameter ownerParameter, int index) {
        List parametersInfo;
        return enhanceValueParameter(ownerFunction, overriddenMembers, ownerParameter, defaultQualifiers, new TypeInSignature.ValueParameter(index), (predefinedEnhancementInfo == null || (parametersInfo = predefinedEnhancementInfo.getParametersInfo()) == null) ? null : (TypeEnhancementInfo) CollectionsKt.getOrNull(parametersInfo, index), this.owner.getClassKind() == ClassKind.ANNOTATION_CLASS);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirNamedFunctionSymbol enhancedFunction$default(FirSignatureEnhancement firSignatureEnhancement, FirNamedFunctionSymbol firNamedFunctionSymbol, Name name, List list, int i, Object obj) {
        if ((i & 4) != 0) {
            list = null;
        }
        return firSignatureEnhancement.enhancedFunction(firNamedFunctionSymbol, name, list);
    }

    private final FirFunctionSymbol<?> enhancedFunctionImpl(FirFunctionSymbol<?> function, Name name, List<? extends FirCallableDeclaration> precomputedOverridden) {
        return this.enhancementsCache.getEnhancedFunctions().getValue(function, new FirEnhancedSymbolsStorage.FunctionEnhancementContext(this, name, precomputedOverridden));
    }

    private final JavaTypeQualifiersByElementType getContextQualifiers() {
        return (JavaTypeQualifiersByElementType) this.contextQualifiers.getValue();
    }

    private final JavaTypeParameterStack getJavaTypeParameterStack() {
        FirRegularClass firRegularClass = this.owner;
        if (firRegularClass instanceof FirJavaClass) {
            return this.enhanceClassHeaderOnly ? ((FirJavaClass) firRegularClass).getClassJavaTypeParameterStack() : ((FirJavaClass) firRegularClass).getJavaTypeParameterStack();
        }
        return JavaTypeParameterStack.INSTANCE.getEMPTY();
    }

    private final FirModuleData getModuleData() {
        return this.owner.getModuleData();
    }

    private final ConeKotlinType getPrivateKtSuperClass() {
        return (ConeKotlinType) this.privateKtSuperClass.getValue();
    }

    /* JADX WARN: Code duplicated, block: B:48:0x00a0  */
    private final ConeKotlinType getPurelyImplementedSupertype(FirSession session) {
        Object next;
        ClassId classId;
        ClassId purelyImplementedInterface;
        ArrayList arrayList;
        ClassId classId2;
        ConeClassLikeLookupTag lookupTag;
        Iterator<T> it = this.owner.getAnnotations().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            FirResolvedTypeRef annotationTypeRef = ((FirAnnotation) next).getAnnotationTypeRef();
            FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
            classId2 = (coneClassLikeType == null || (lookupTag = coneClassLikeType.getLookupTag()) == null) ? null : lookupTag.getClassId();
        } while (!Intrinsics.areEqual(classId2 != null ? classId2.asSingleFqName() : null, JvmAnnotationNames.PURELY_IMPLEMENTS_ANNOTATION));
        FirAnnotation firAnnotation = (FirAnnotation) next;
        if (firAnnotation != null) {
            Object objFirstOrNull = CollectionsKt.firstOrNull(firAnnotation.getArgumentMapping().getMapping().values());
            FirLiteralExpression firLiteralExpression = objFirstOrNull instanceof FirLiteralExpression ? (FirLiteralExpression) objFirstOrNull : null;
            if (firLiteralExpression != null) {
                Object value = firLiteralExpression.getValue();
                String str = value instanceof String ? (String) value : null;
                if (str == null) {
                    classId = null;
                } else {
                    if (StringsKt.isBlank(str) || !FqNamesUtilKt.isValidJavaFqName(str)) {
                        str = null;
                    }
                    if (str != null) {
                        classId = ClassId.Companion.topLevel(new FqName(str));
                    } else {
                        classId = null;
                    }
                }
            } else {
                classId = null;
            }
        } else {
            classId = null;
        }
        if (classId == null) {
            purelyImplementedInterface = FakePureImplementationsProvider.INSTANCE.getPurelyImplementedInterface(this.owner.getSymbol().getClassId());
            if (purelyImplementedInterface == null) {
                return null;
            }
        } else {
            purelyImplementedInterface = classId;
        }
        FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(session).getClassLikeSymbolByClassId(purelyImplementedInterface);
        if (classLikeSymbolByClassId == null) {
            return null;
        }
        List<FirTypeParameterSymbol> typeParameterSymbols = classLikeSymbolByClassId.getTypeParameterSymbols();
        List<FirTypeParameterRef> typeParameters = this.owner.getTypeParameters();
        int size = typeParameterSymbols.size();
        int size2 = typeParameters.size();
        if (size2 == size) {
            List<FirTypeParameterRef> list = typeParameters;
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList.add(new ConeTypeParameterTypeImpl(new ConeTypeParameterLookupTag(((FirTypeParameterRef) it2.next()).getSymbol()), false, null, 4, null));
            }
        } else {
            if (size2 != 1 || size <= 1 || classId != null) {
                return null;
            }
            ConeTypeParameterTypeImpl coneTypeParameterTypeImpl = new ConeTypeParameterTypeImpl(new ConeTypeParameterLookupTag(((FirTypeParameterRef) CollectionsKt.first(typeParameters)).getSymbol()), false, null, 4, null);
            IntRange intRange = new IntRange(1, size);
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
            IntIterator it3 = intRange.iterator();
            while (it3.hasNext()) {
                it3.nextInt();
                arrayList.add(coneTypeParameterTypeImpl);
            }
        }
        return new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(purelyImplementedInterface), (ConeTypeProjection[]) arrayList.toArray(new ConeTypeParameterTypeImpl[0]), false, null, 8, null);
    }

    private final boolean isEnhanceable(FirCallableSymbol<?> firCallableSymbol) {
        return (firCallableSymbol.getOrigin() instanceof FirDeclarationOrigin.Java) || isEnhanceableIntersection(firCallableSymbol) || getPrivateKtSuperClass() != null;
    }

    private final boolean isEnhanceableIntersection(FirCallableSymbol<?> firCallableSymbol) {
        if ((firCallableSymbol instanceof FirIntersectionCallableSymbol) && Intrinsics.areEqual(ClassMembersKt.dispatchReceiverClassLookupTagOrNull(firCallableSymbol), this.owner.getSymbol().getLookupTag())) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol.getFir();
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            if (symbol == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                return false;
            }
            if (symbol.getOrigin() instanceof FirDeclarationOrigin.Enhancement) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0034 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:14:0x0035 A[RETURN] */
    private final List<List<FirResolvedTypeRef>> performRoundOfBoundsResolution(List<? extends FirTypeParameterRef> typeParameters, KtSourceElement source, Function3<? super FirJavaTypeParameter, ? super JavaTypeParameterStack, ? super KtSourceElement, ? extends List<FirResolvedTypeRef>> round) {
        boolean z;
        ArrayList arrayList = new ArrayList(typeParameters.size());
        for (FirTypeParameterRef firTypeParameterRef : typeParameters) {
            if (firTypeParameterRef instanceof FirJavaTypeParameter) {
                List list = (List) round.invoke((FirJavaTypeParameter) firTypeParameterRef, getJavaTypeParameterStack(), source);
                if (list == null) {
                    z = false;
                    if (z) {
                        return arrayList;
                    }
                    return null;
                }
                arrayList.add(list);
            }
        }
        z = true;
        if (z) {
            return arrayList;
        }
        return null;
    }

    private final void replaceTypeParameterBounds(List<? extends FirTypeParameterRef> list, ConeSubstitutor coneSubstitutor) {
        for (FirTypeParameterRef firTypeParameterRef : list) {
            if (firTypeParameterRef instanceof FirTypeParameter) {
                FirTypeParameter firTypeParameter = (FirTypeParameter) firTypeParameterRef;
                List<FirTypeRef> bounds = firTypeParameter.getBounds();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(bounds, 10));
                for (FirTypeRef firTypeRef : bounds) {
                    arrayList.add(TypeUtilsKt.withReplacedConeType$default(firTypeRef, coneSubstitutor != null ? coneSubstitutor.substituteOrNull(FirTypeUtilsKt.getConeType(firTypeRef)) : null, null, 2, null));
                }
                firTypeParameter.replaceBounds(arrayList);
            }
        }
    }

    private final ConeKotlinType toConeKotlinType(FirTypeRef firTypeRef, FirJavaTypeConversionMode firJavaTypeConversionMode, KtSourceElement ktSourceElement) {
        return JavaTypeConversionKt.toConeKotlinTypeProbablyFlexible(firTypeRef, this.session, getJavaTypeParameterStack(), ktSourceElement, firJavaTypeConversionMode);
    }

    private final void updateIsOperatorFlagIfNeeded(FirFunction function) {
        if ((function instanceof FirNamedFunction) && OperatorFunctionChecks.INSTANCE.isOperator(function, this.session, null).getIsSuccess()) {
            FirNamedFunction firNamedFunction = (FirNamedFunction) function;
            FirDeclarationStatus status = firNamedFunction.getStatus();
            firNamedFunction.replaceStatus(UtilsKt.copy(status, (8388575 & 1) != 0 ? status.getVisibility() : null, (8388575 & 2) != 0 ? status.getModality() : null, (8388575 & 4) != 0 ? status.isExpect() : false, (8388575 & 8) != 0 ? status.isActual() : false, (8388575 & 16) != 0 ? status.isOverride() : false, (8388575 & 32) != 0 ? status.isOperator() : true, (8388575 & 64) != 0 ? status.isInfix() : false, (8388575 & 128) != 0 ? status.isInline() : false, (8388575 & 256) != 0 ? status.isValue() : false, (8388575 & 512) != 0 ? status.isTailRec() : false, (8388575 & 1024) != 0 ? status.isExternal() : false, (8388575 & 2048) != 0 ? status.isConst() : false, (8388575 & 4096) != 0 ? status.isLateInit() : false, (8388575 & 8192) != 0 ? status.isInner() : false, (8388575 & 16384) != 0 ? status.isCompanion() : false, (8388575 & 32768) != 0 ? status.isData() : false, (8388575 & 65536) != 0 ? status.isSuspend() : false, (8388575 & 131072) != 0 ? status.isStatic() : false, (8388575 & 262144) != 0 ? status.isFromSealedClass() : false, (8388575 & 524288) != 0 ? status.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? status.isFun() : false, (8388575 & 2097152) != 0 ? status.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? status.getReturnValueStatus() : null));
        }
    }

    private final PredefinedFunctionEnhancementInfo useWarningsIfErrorModeIsNotEnabledYet(PredefinedFunctionEnhancementInfo predefinedFunctionEnhancementInfo) {
        String errorsSinceLanguageVersion = predefinedFunctionEnhancementInfo.getErrorsSinceLanguageVersion();
        if (errorsSinceLanguageVersion != null) {
            LanguageVersion languageVersionFromVersionString = LanguageVersion.INSTANCE.fromVersionString(errorsSinceLanguageVersion);
            if (languageVersionFromVersionString == null) {
                dwe.a("Unexpected LV: ".concat(errorsSinceLanguageVersion));
                return null;
            }
            if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.session).getLanguageVersion().compareTo(languageVersionFromVersionString) < 0) {
                PredefinedFunctionEnhancementInfo warningModeClone = predefinedFunctionEnhancementInfo.getWarningModeClone();
                if (warningModeClone != null) {
                    return warningModeClone;
                }
                b88.a("For not null LV ", predefinedFunctionEnhancementInfo.getErrorsSinceLanguageVersion(), ", `warningModeClone` should not be null");
                return null;
            }
        }
        return predefinedFunctionEnhancementInfo;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @PrivateForInline
    public final FirVariableSymbol<?> enhance$org_jetbrains_kotlin_fir_jvm(FirVariableSymbol<?> original, final Name name) throws KotlinIllegalArgumentExceptionWithAttachments {
        original.getClass();
        name.getClass();
        final FirVariable firVariable = (FirVariable) original.getFir();
        if (firVariable instanceof FirEnumEntry) {
            FirEnumEntry firEnumEntry = (FirEnumEntry) firVariable;
            if (!(firEnumEntry.getReturnTypeRef() instanceof FirResolvedTypeRef)) {
                cpa.a("For Java enum entries we expect its type to be resolved, but got ", Reflection.getOrCreateKotlinClass(firEnumEntry.getReturnTypeRef().getClass()).getSimpleName());
                return null;
            }
        } else if (firVariable instanceof FirField) {
            FirField firField = (FirField) firVariable;
            if (firField.getReturnTypeRef() instanceof FirJavaTypeRef) {
                FirResolvedTypeRef firResolvedTypeRefEnhanceReturnType = enhanceReturnType(firVariable, computeDefaultQualifiers(firVariable), null);
                ConeKotlinType coneType = firResolvedTypeRefEnhanceReturnType.getConeType();
                if (ConeBuiltinTypeUtilsKt.isString(ConeTypeUtilsKt.lowerBoundIfFlexible(coneType)) && firVariable.getStatus().isStatic() && firField.getHasConstantInitializer()) {
                    firResolvedTypeRefEnhanceReturnType = TypeUtilsKt.withReplacedConeType$default(firResolvedTypeRefEnhanceReturnType, TypeUtilsKt.withNullability$default(coneType, false, TypeComponentsKt.getTypeContext(this.session), null, false, 12, null), null, 2, null);
                }
                CallableId callableId = original.getCallableId();
                callableId.getClass();
                FirFieldSymbol firFieldSymbol = new FirFieldSymbol(callableId);
                FirJavaFieldBuilder firJavaFieldBuilder = new FirJavaFieldBuilder();
                firJavaFieldBuilder.setContainingClassSymbol(this.owner.getSymbol());
                firJavaFieldBuilder.setSource(firField.getSource());
                firJavaFieldBuilder.setModuleData(getModuleData());
                firJavaFieldBuilder.setSymbol(firFieldSymbol);
                firJavaFieldBuilder.setName(name);
                firJavaFieldBuilder.setReturnTypeRef(firResolvedTypeRefEnhanceReturnType);
                firJavaFieldBuilder.setFromSource(original.getOrigin().getFromSource());
                firJavaFieldBuilder.setVar(firField.getIsVar());
                firJavaFieldBuilder.setAnnotationList(new FirDelegatedJavaAnnotationList(firVariable));
                firJavaFieldBuilder.setStatus(firField.getStatus());
                if (firVariable instanceof FirJavaField) {
                    FirJavaField firJavaField = (FirJavaField) firVariable;
                    firJavaFieldBuilder.setLazyInitializer(firJavaField.getLazyInitializer());
                    firJavaFieldBuilder.setLazyHasConstantInitializer(firJavaField.getLazyHasConstantInitializer());
                } else {
                    firJavaFieldBuilder.setInitializer(firField.getInitializer());
                }
                firJavaFieldBuilder.setDispatchReceiverType(firField.getDispatchReceiverType());
                firJavaFieldBuilder.setAttributes(firField.getAttributes().copy());
                firJavaFieldBuilder.mo288build();
                return firFieldSymbol;
            }
        } else {
            if (!(firVariable instanceof FirSyntheticProperty)) {
                if (original instanceof FirPropertySymbol) {
                    return original;
                }
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Can't make enhancement for " + original.getClass(), (Throwable) null);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "firElement", firVariable);
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
            FirSyntheticProperty firSyntheticProperty = (FirSyntheticProperty) firVariable;
            FirSyntheticPropertySymbol symbol = firSyntheticProperty.getSymbol();
            symbol.getClass();
            final FirJavaOverriddenSyntheticPropertySymbol firJavaOverriddenSyntheticPropertySymbol = (FirJavaOverriddenSyntheticPropertySymbol) symbol;
            final FirNamedFunction delegate = firSyntheticProperty.getGetter().getDelegate();
            final List<? extends FirCallableDeclaration> list = (List) this.overridden.invoke(firVariable);
            FirFunctionSymbol<?> firFunctionSymbolEnhanceAccessorOrNull = null;
            final FirFunctionSymbol<?> firFunctionSymbolEnhanceAccessorOrNull2 = enhanceAccessorOrNull(delegate, list);
            FirSyntheticPropertyAccessor setter = firSyntheticProperty.getSetter();
            FirNamedFunction delegate2 = setter != null ? setter.getDelegate() : null;
            if (delegate2 != null) {
                firFunctionSymbolEnhanceAccessorOrNull = enhanceAccessorOrNull(delegate2, list);
            }
            final FirFunctionSymbol<?> firFunctionSymbol = firFunctionSymbolEnhanceAccessorOrNull;
            if (firFunctionSymbolEnhanceAccessorOrNull2 != null || firFunctionSymbol != null) {
                final FirNamedFunction firNamedFunction = delegate2;
                return FirSyntheticPropertyBuilderKt.buildSyntheticProperty(new Function1() { // from class: zd5
                    public final Object invoke(Object obj) {
                        return FirSignatureEnhancement.c(this.b, name, firJavaOverriddenSyntheticPropertySymbol, firFunctionSymbolEnhanceAccessorOrNull2, delegate, firFunctionSymbol, firNamedFunction, firVariable, list, (FirSyntheticPropertyBuilder) obj);
                    }
                }).getSymbol();
            }
        }
        return original;
    }

    public final List<FirTypeRef> enhanceSuperTypes(List<? extends FirTypeRef> nonEnhancedSuperTypes) {
        nonEnhancedSuperTypes.getClass();
        ConeKotlinType purelyImplementedSupertype = getPurelyImplementedSupertype(getModuleData().getSession());
        ClassId classId = purelyImplementedSupertype != null ? ConeTypeUtilsKt.getClassId(purelyImplementedSupertype) : null;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List list = listCreateListBuilder;
        Iterator<T> it = nonEnhancedSuperTypes.iterator();
        while (it.hasNext()) {
            FirTypeRef firTypeRefEnhanceSuperType = enhanceSuperType((FirTypeRef) it.next());
            if (classId != null && Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(FirTypeUtilsKt.getConeType(firTypeRefEnhanceSuperType)), classId)) {
                firTypeRefEnhanceSuperType = null;
            }
            if (firTypeRefEnhanceSuperType != null) {
                list.add(firTypeRefEnhanceSuperType);
            }
        }
        if (purelyImplementedSupertype != null) {
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
            firResolvedTypeRefBuilder.setConeType(purelyImplementedSupertype);
            listCreateListBuilder.add(firResolvedTypeRefBuilder.build());
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    public final void enhanceTypeParameterBounds(FirTypeParameterRefsOwner owner, List<? extends FirTypeParameterRef> typeParameters, Function1<? super Function0<Unit>, Unit> lock) {
        owner.getClass();
        typeParameters.getClass();
        lock.getClass();
        if (typeParameters.isEmpty()) {
            return;
        }
        KtSourceElement source = owner.getSource();
        KtSourceElement ktSourceElementFakeElement$default = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null;
        if (enhanceTypeParameterBoundsFirstRound(typeParameters, ktSourceElementFakeElement$default, lock)) {
            enhanceTypeParameterBoundsSecondRound(typeParameters, ktSourceElementFakeElement$default, lock);
        }
    }

    public final FirConstructorSymbol enhancedConstructor(FirConstructorSymbol constructor) {
        constructor.getClass();
        FirFunctionSymbol<?> firFunctionSymbolEnhancedFunctionImpl = enhancedFunctionImpl(constructor, null, null);
        firFunctionSymbolEnhancedFunctionImpl.getClass();
        return (FirConstructorSymbol) firFunctionSymbolEnhancedFunctionImpl;
    }

    public final FirNamedFunctionSymbol enhancedFunction(FirNamedFunctionSymbol function, Name name, List<? extends FirCallableDeclaration> precomputedOverridden) {
        function.getClass();
        name.getClass();
        FirFunctionSymbol<?> firFunctionSymbolEnhancedFunctionImpl = enhancedFunctionImpl(function, name, precomputedOverridden);
        firFunctionSymbolEnhancedFunctionImpl.getClass();
        return (FirNamedFunctionSymbol) firFunctionSymbolEnhancedFunctionImpl;
    }

    public final FirVariableSymbol<?> enhancedProperty(FirVariableSymbol<?> property, Name name) {
        property.getClass();
        name.getClass();
        return this.enhancementsCache.getEnhancedVariables().getValue(property, TuplesKt.to(this, name));
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement$enhance$5, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass5 extends FunctionReferenceImpl implements Function1<Function0<? extends Unit>, Unit> {
        public AnonymousClass5(Object obj) {
            super(1, obj, FirJavaMethod.class, "withTypeParameterBoundsResolveLock", "withTypeParameterBoundsResolveLock$org_jetbrains_kotlin_fir_jvm(Lkotlin/jvm/functions/Function0;)V", 0);
        }

        public final void invoke(Function0<Unit> function0) {
            function0.getClass();
            ((FirJavaMethod) ((CallableReference) this).receiver).withTypeParameterBoundsResolveLock$org_jetbrains_kotlin_fir_jvm(function0);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Function0<Unit>) obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement$enhance$6, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass6 extends FunctionReferenceImpl implements Function1<Function0<? extends Unit>, Unit> {
        public AnonymousClass6(Object obj) {
            super(1, obj, FirJavaConstructor.class, "withTypeParameterBoundsResolveLock", "withTypeParameterBoundsResolveLock$org_jetbrains_kotlin_fir_jvm(Lkotlin/jvm/functions/Function0;)V", 0);
        }

        public final void invoke(Function0<Unit> function0) {
            function0.getClass();
            ((FirJavaConstructor) ((CallableReference) this).receiver).withTypeParameterBoundsResolveLock$org_jetbrains_kotlin_fir_jvm(function0);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Function0<Unit>) obj);
            return Unit.INSTANCE;
        }
    }

    private final void enhanceTypeParameterBounds(FirTypeParameterRefsOwner owner, Function1<? super Function0<Unit>, Unit> lock) {
        enhanceTypeParameterBounds(owner, owner.getTypeParameters(), lock);
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement$enhanceTypeParameterBounds$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass1 implements Function0<Unit> {
        final /* synthetic */ List<List<FirResolvedTypeRef>> $enhancedBounds;
        final /* synthetic */ Ref.BooleanRef $succeed;
        final /* synthetic */ List<FirTypeParameterRef> $typeParameters;
        final /* synthetic */ Function2<FirJavaTypeParameter, List<? extends FirResolvedTypeRef>, Boolean> $updater;
        final /* synthetic */ FirSignatureEnhancement this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Ref.BooleanRef booleanRef, FirSignatureEnhancement firSignatureEnhancement, List<? extends FirTypeParameterRef> list, Function2<? super FirJavaTypeParameter, ? super List<? extends FirResolvedTypeRef>, Boolean> function2, List<? extends List<FirResolvedTypeRef>> list2) {
            this.$succeed = booleanRef;
            this.this$0 = firSignatureEnhancement;
            this.$typeParameters = list;
            this.$updater = function2;
            this.$enhancedBounds = list2;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m550invoke() {
            Ref.BooleanRef booleanRef = this.$succeed;
            List<FirTypeParameterRef> list = this.$typeParameters;
            Function2<FirJavaTypeParameter, List<? extends FirResolvedTypeRef>, Boolean> function2 = this.$updater;
            List<List<FirResolvedTypeRef>> list2 = this.$enhancedBounds;
            boolean z = false;
            int i = 0;
            for (FirTypeParameterRef firTypeParameterRef : list) {
                if (firTypeParameterRef instanceof FirJavaTypeParameter) {
                    if (!((Boolean) function2.invoke((FirJavaTypeParameter) firTypeParameterRef, list2.get(i))).booleanValue()) {
                        booleanRef.element = z;
                    }
                    i++;
                }
            }
            z = true;
            booleanRef.element = z;
        }

        public /* bridge */ /* synthetic */ Object invoke() {
            m550invoke();
            return Unit.INSTANCE;
        }
    }

    public /* synthetic */ FirSignatureEnhancement(FirRegularClass firRegularClass, FirSession firSession, boolean z, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firRegularClass, firSession, (i & 4) != 0 ? false : z, function1);
    }

    private final FirResolvedTypeRef enhance(EnhancementSignatureParts enhancementSignatureParts, FirTypeRef firTypeRef, List<? extends FirTypeRef> list, FirJavaTypeConversionMode firJavaTypeConversionMode, TypeEnhancementInfo typeEnhancementInfo) {
        ConeKotlinType coneKotlinType = toConeKotlinType(firTypeRef, firJavaTypeConversionMode, firTypeRef.getSource());
        List<? extends FirTypeRef> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(toConeKotlinType((FirTypeRef) it.next(), firJavaTypeConversionMode, firTypeRef.getSource()));
        }
        Function1 function1ComputeIndexedQualifiers$default = AbstractSignatureParts.computeIndexedQualifiers$default(enhancementSignatureParts, coneKotlinType, arrayList, typeEnhancementInfo, false, 4, (Object) null);
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        ConeKotlinType coneKotlinTypeEnhance = JavaTypeUtilsKt.enhance(coneKotlinType, this.session, function1ComputeIndexedQualifiers$default);
        if (coneKotlinTypeEnhance != null) {
            coneKotlinType = coneKotlinTypeEnhance;
        }
        firResolvedTypeRefBuilder.setConeType(coneKotlinType);
        CollectionsKt.addAll(firResolvedTypeRefBuilder.getAnnotations(), firTypeRef.getAnnotations());
        firResolvedTypeRefBuilder.setSource(firTypeRef.getSource());
        return firResolvedTypeRefBuilder.build();
    }

    private final FirResolvedTypeRef enhanceReturnType(FirCallableDeclaration owner, JavaTypeQualifiersByElementType defaultQualifiers, PredefinedFunctionEnhancementInfo predefinedEnhancementInfo) {
        Object first = enhanceReturnType(owner, CollectionsKt.emptyList(), defaultQualifiers, predefinedEnhancementInfo).getFirst();
        first.getClass();
        return (FirResolvedTypeRef) first;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @PrivateForInline
    public final FirFunctionSymbol<?> enhance$org_jetbrains_kotlin_fir_jvm(FirFunctionSymbol<?> original, Name name, List<? extends FirCallableDeclaration> precomputedOverridden) throws KotlinIllegalArgumentExceptionWithAttachments {
        original.getClass();
        if (!isEnhanceable(original)) {
            return original;
        }
        FirFunction firFunction = (FirFunction) original.getFir();
        if (firFunction instanceof FirJavaMethod) {
            enhanceTypeParameterBounds(firFunction, new AnonymousClass5(firFunction));
        } else if (firFunction instanceof FirJavaConstructor) {
            enhanceTypeParameterBounds(firFunction, new AnonymousClass6(firFunction));
        }
        FirFunctionSymbol<?> firFunctionSymbolEnhanceMethod = enhanceMethod(firFunction, original.getCallableId(), name, original instanceof FirIntersectionOverrideFunctionSymbol, precomputedOverridden);
        InheritedKtPrivateClassDataKeyKt.setInheritedKtPrivateCls((FirCallableDeclaration) firFunctionSymbolEnhanceMethod.getFir(), getPrivateKtSuperClass());
        return firFunctionSymbolEnhanceMethod;
    }
}
