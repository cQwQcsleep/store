package org.jetbrains.kotlin.fir.serialization;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.annotation.AnnotationRetention;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKindKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.constant.AnnotationValue;
import org.jetbrains.kotlin.constant.ConstantValue;
import org.jetbrains.kotlin.constant.EnumValue;
import org.jetbrains.kotlin.constant.IntValue;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.InlineClassRepresentation;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.MultiFieldValueClassRepresentation;
import org.jetbrains.kotlin.descriptors.ValueClassRepresentation;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DestructuringDeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirRetentionAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueClassRepresentationKt;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.SealedClassInheritorsKt;
import org.jetbrains.kotlin.fir.declarations.comparators.FirCallableDeclarationComparator;
import org.jetbrains.kotlin.fir.declarations.comparators.FirMemberDeclarationComparator;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyGetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertySetter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.deserialization.ProtoEnumFlagsUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.expressions.FirConstChecksKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionEvaluator;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationArgumentMappingBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt;
import org.jetbrains.kotlin.fir.expressions.impl.FirEmptyAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.extensions.FirTypeAttributeExtension;
import org.jetbrains.kotlin.fir.extensions.FirTypeAttributeExtensionKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirScriptDeclarationsScope;
import org.jetbrains.kotlin.fir.serialization.FirElementSerializer;
import org.jetbrains.kotlin.fir.serialization.constant.FirToConstantValueTransformerKt;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ArrayUtilsKt;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.CustomAnnotationTypeAttribute;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ParameterNameTypeAttribute;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitNullableAnyTypeRef;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.Flags;
import org.jetbrains.kotlin.metadata.deserialization.VersionRequirement;
import org.jetbrains.kotlin.metadata.deserialization.VersionSpecificBehaviorKt;
import org.jetbrains.kotlin.metadata.serialization.Interner;
import org.jetbrains.kotlin.metadata.serialization.MutableTypeTable;
import org.jetbrains.kotlin.metadata.serialization.MutableVersionRequirementTable;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.protobuf.ByteString;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;
import org.jetbrains.kotlin.resolve.RequireKotlinConstants;
import org.jetbrains.kotlin.serialization.deserialization.ProtoEnumFlags;
import org.jetbrains.kotlin.types.AbstractTypeApproximator;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ö\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 Â\u00012\u00020\u0001:\u0004Á\u0001Â\u0001Bk\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0012¢\u0006\u0004\b\u0018\u0010\u0019J\u001e\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010+J2\u0010&\u001a\u00020'2\u0006\u0010,\u001a\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00070/2\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010+H\u0007b\u0002\b0J8\u00101\u001a\u000202*\u00020'2\u0006\u00103\u001a\u00020\u00072\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010+2\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020205H\u0002J(\u00106\u001a\u00020'2\u0006\u0010,\u001a\u00020-2\u0006\u00107\u001a\u00020'2\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010+H\u0002J\u0018\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010)J\u0010\u0010=\u001a\u0002092\u0006\u0010:\u001a\u00020;H\u0002J\u000e\u0010>\u001a\u0002092\u0006\u0010?\u001a\u00020@J\u000e\u0010A\u001a\u0002092\u0006\u0010B\u001a\u00020CJ\u001c\u0010D\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030E0/2\n\u0010F\u001a\u0006\u0012\u0002\b\u00030GJ\u0012\u0010H\u001a\b\u0012\u0004\u0012\u00020I0/*\u00020;H\u0002J\u0012\u0010J\u001a\b\u0012\u0004\u0012\u00020K0/*\u00020;H\u0002JS\u0010L\u001a\b\u0012\u0004\u0012\u0002HM0/\"\n\b\u0000\u0010M\u0018\u0001*\u00020I\"\f\b\u0001\u0010N*\u0006\u0012\u0002\b\u00030O*\u00020;2$\u0010P\u001a \u0012\u0004\u0012\u00020R\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002HN\u0012\u0004\u0012\u00020205\u0012\u0004\u0012\u0002020QH\u0082\bJ\u001a\u0010S\u001a\b\u0012\u0004\u0012\u00020T0/*\u00020U2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010V\u001a\u0004\u0018\u00010W2\u0006\u0010X\u001a\u00020YJ\f\u0010Z\u001a\u00020[*\u00020YH\u0002J\u0010\u0010\\\u001a\u0004\u0018\u00010]2\u0006\u0010^\u001a\u00020_J\f\u0010Z\u001a\u00020[*\u00020_H\u0002J\u0012\u0010`\u001a\u00020\u00122\b\u0010^\u001a\u0004\u0018\u00010_H\u0002J\u0012\u0010a\u001a\u0004\u0018\u00010b2\u0006\u0010c\u001a\u00020dH\u0002J\u0010\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020hH\u0002J\u0010\u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020KH\u0002J \u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020o2\u0006\u0010p\u001a\u00020q2\u0006\u0010^\u001a\u00020_H\u0002J\u0018\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020o2\u0006\u0010r\u001a\u00020\u0012H\u0002J\u0010\u0010s\u001a\u00020\u00122\u0006\u0010t\u001a\u00020uH\u0002J\u0010\u0010v\u001a\u00020w2\u0006\u0010x\u001a\u00020\nH\u0002J\u0018\u0010y\u001a\u00020q2\u0006\u0010z\u001a\u00020{2\b\b\u0002\u0010|\u001a\u00020\u0012J\"\u0010y\u001a\u00020q2\u0006\u0010}\u001a\u00020~2\b\b\u0002\u0010|\u001a\u00020\u00122\b\b\u0002\u0010\u007f\u001a\u00020\u0012J\u001c\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0006\u0010z\u001a\u00020{2\b\b\u0002\u0010|\u001a\u00020\u0012H\u0002J>\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0006\u0010}\u001a\u00020~2\b\b\u0002\u0010|\u001a\u00020\u00122\u000b\b\u0002\u0010\u0082\u0001\u001a\u0004\u0018\u00010{2\t\b\u0002\u0010\u0083\u0001\u001a\u00020\u00122\b\b\u0002\u0010\u007f\u001a\u00020\u0012H\u0002JC\u0010\u0087\u0001\u001a\u00030\u0081\u00012\u0006\u0010}\u001a\u00020~2\u0006\u0010|\u001a\u00020\u00122\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010{2\u0007\u0010\u0083\u0001\u001a\u00020\u00122\t\b\u0002\u0010\u0088\u0001\u001a\u00020\u00122\b\b\u0002\u0010\u007f\u001a\u00020\u0012H\u0002J\u0019\u0010\u0089\u0001\u001a\u0004\u0018\u00010T2\f\u0010\u008a\u0001\u001a\u0007\u0012\u0002\b\u00030\u008b\u0001H\u0002JL\u0010\u008c\u0001\u001a\u0002022\u0007\u00107\u001a\u00030\u0081\u00012\f\u0010\u008d\u0001\u001a\u0007\u0012\u0002\b\u00030\u008e\u00012\u0011\u0010\u008f\u0001\u001a\f\u0012\u0007\b\u0001\u0012\u00030\u0091\u00010\u0090\u00012\u0007\u0010\u0092\u0001\u001a\u00020q2\b\b\u0002\u0010\u007f\u001a\u00020\u0012H\u0002¢\u0006\u0003\u0010\u0093\u0001J%\u0010\u008c\u0001\u001a\u0002022\u0007\u00107\u001a\u00030\u0081\u00012\u0007\u0010}\u001a\u00030\u0094\u00012\b\b\u0002\u0010\u007f\u001a\u00020\u0012H\u0002J\u001e\u0010\u0095\u0001\u001a\u00030\u0096\u00012\b\u0010\u0097\u0001\u001a\u00030\u0091\u00012\b\b\u0002\u0010\u007f\u001a\u00020\u0012H\u0002J\u001a\u0010\u0098\u0001\u001a\u00020q2\u0007\u0010\u0099\u0001\u001a\u00020U2\u0006\u0010X\u001a\u00020YH\u0002J\u001a\u0010\u009a\u0001\u001a\u00020\u00122\u0007\u0010\u0099\u0001\u001a\u00020U2\u0006\u0010X\u001a\u00020YH\u0002J\u0011\u0010\u009b\u0001\u001a\u00020\u00002\u0006\u00103\u001a\u00020\u0007H\u0002J\t\u0010 \u0001\u001a\u00020\u0012H\u0002J\u001d\u0010¡\u0001\u001a\b\u0012\u0004\u0012\u00020q0/*\u00020\u00102\b\u0010¢\u0001\u001a\u00030£\u0001H\u0002J\"\u0010¡\u0001\u001a\b\u0012\u0004\u0012\u00020q0/*\u00020\u00102\r\u0010¤\u0001\u001a\b\u0012\u0004\u0012\u00020T0/H\u0002J\u0017\u0010¥\u0001\u001a\u00020q*\u00020\u00102\b\u0010¦\u0001\u001a\u00030§\u0001H\u0002J\u0015\u0010¨\u0001\u001a\u0005\u0018\u00010©\u00012\u0007\u0010ª\u0001\u001a\u00020TH\u0002J\u0013\u0010«\u0001\u001a\u00030¬\u00012\u0007\u00103\u001a\u00030\u00ad\u0001H\u0002J\u0012\u0010«\u0001\u001a\u00030¬\u00012\u0006\u00103\u001a\u00020UH\u0002J\u0012\u0010®\u0001\u001a\u00020q2\u0007\u00103\u001a\u00030¯\u0001H\u0002J\u001c\u0010°\u0001\u001a\u00020q2\u0007\u00103\u001a\u00030¯\u00012\b\u0010±\u0001\u001a\u00030²\u0001H\u0002J\u0013\u0010®\u0001\u001a\u00020q2\b\u0010³\u0001\u001a\u00030²\u0001H\u0002J\u0013\u0010´\u0001\u001a\u00020q2\b\u0010µ\u0001\u001a\u00030¶\u0001H\u0002J\u0011\u0010·\u0001\u001a\u00020q2\u0006\u0010x\u001a\u00020\nH\u0002Jn\u0010¸\u0001\u001a\u000202\"\u0011\b\u0000\u0010¹\u0001*\n\u0012\u0005\u0012\u0003H¹\u00010º\u0001\"\u0018\b\u0001\u0010»\u0001*\u0011\u0012\u0005\u0012\u0003H¹\u0001\u0012\u0005\u0012\u0003H»\u00010¼\u0001*\u0003H»\u00012\u0006\u00103\u001a\u00020\u00072\"\u0010½\u0001\u001a\u001d\u0012\u0005\u0012\u0003H»\u0001\u0012\u0005\u0012\u00030¾\u0001\u0012\u0005\u0012\u0003H»\u00010Q¢\u0006\u0003\b¿\u0001H\u0082\b¢\u0006\u0003\u0010À\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0084\u0001\u001a\u00020\u0012*\u00020~8BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0015\u0010\u009c\u0001\u001a\u00030\u009d\u00018F¢\u0006\b\u001a\u0006\b\u009e\u0001\u0010\u009f\u0001¨\u0006Ã\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "currentDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "typeParameters", "Lorg/jetbrains/kotlin/metadata/serialization/Interner;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "extension", "Lorg/jetbrains/kotlin/fir/serialization/FirSerializerExtension;", "typeTable", "Lorg/jetbrains/kotlin/metadata/serialization/MutableTypeTable;", "versionRequirementTable", "Lorg/jetbrains/kotlin/metadata/serialization/MutableVersionRequirementTable;", "serializeTypeTableToFunction", Argument.Delimiters.none, "typeApproximator", "Lorg/jetbrains/kotlin/types/AbstractTypeApproximator;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "produceHeaderKlib", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/metadata/serialization/Interner;Lorg/jetbrains/kotlin/fir/serialization/FirSerializerExtension;Lorg/jetbrains/kotlin/metadata/serialization/MutableTypeTable;Lorg/jetbrains/kotlin/metadata/serialization/MutableVersionRequirementTable;ZLorg/jetbrains/kotlin/types/AbstractTypeApproximator;Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Z)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getTypeTable", "()Lorg/jetbrains/kotlin/metadata/serialization/MutableTypeTable;", "contractSerializer", "Lorg/jetbrains/kotlin/fir/serialization/FirContractSerializer;", "providedDeclarationsService", "Lorg/jetbrains/kotlin/fir/serialization/FirProvidedDeclarationsForMetadataService;", "stdLibCompilation", "metDefinitelyNotNullType", "packagePartProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Package$Builder;", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "actualizedExpectDeclarations", Argument.Delimiters.none, "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "declarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer$SensitiveApi;", "addDeclarationProto", Argument.Delimiters.none, "declaration", "onUnsupportedDeclaration", "Lkotlin/Function1;", "finalizePackagePartProto", "builder", "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class$Builder;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "containingFile", "classProtoImpl", "scriptProto", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "snippetProto", "snippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "computeNestedClassifiersForClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "memberDeclarations", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "constructors", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "collectDeclarations", "T", "S", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "processScope", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "nonSourceAnnotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "propertyProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "memberKind", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$MemberKind;", "functionProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function$Builder;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "shouldSetStableParameterNames", "typeAliasProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeAlias$Builder;", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "enumEntryProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$EnumEntry$Builder;", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "constructorProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor$Builder;", "constructor", "valueParameterProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$ValueParameter$Builder;", "parameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "index", Argument.Delimiters.none, "declaresDefaultValue", "shouldWriteAnnotationParameterDefaultValues", "version", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "typeParameterProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter$Builder;", "typeParameter", "typeId", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "toSuper", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "abbreviationOnly", "typeProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type$Builder;", "correspondingTypeRef", "isDefinitelyNotNullType", "containsCapturedTypes", "getContainsCapturedTypes", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "typeOrTypealiasProto", "isAbbreviation", "createAnnotationForCompilerDefinedTypeAttribute", "attribute", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "fillFromPossiblyInnerType", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "typeArgumentIndex", "(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type$Builder;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;IZ)V", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "typeArgument", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type$Argument$Builder;", "typeProjection", "getAccessorFlags", "accessor", "isDefaultAccessor", "createChildSerializer", "stringTable", "Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;", "getStringTable", "()Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;", "useTypeTable", "serializeVersionRequirements", "container", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "annotations", "writeVersionRequirement", "languageFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "serializeVersionRequirementFromRequireKotlin", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$VersionRequirement$Builder;", "annotation", "normalizeVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "getClassifierId", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "getScriptOrReplClassId", "containerClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "classId", "getSimpleNameIndex", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getTypeParameterId", "serializeCompilerPluginMetadata", "M", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableMessage;", "B", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$Builder;", "addCompilerPluginData", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$CompilerPluginData$Builder;", "Lkotlin/ExtensionFunctionType;", "(Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$Builder;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lkotlin/jvm/functions/Function2;)V", "SensitiveApi", "Companion", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirElementSerializer implements SessionAndScopeSessionHolder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FirContractSerializer contractSerializer;
    private final FirDeclaration currentDeclaration;
    private final FirSerializerExtension extension;
    private final LanguageVersionSettings languageVersionSettings;
    private boolean metDefinitelyNotNullType;
    private final boolean produceHeaderKlib;
    private final FirProvidedDeclarationsForMetadataService providedDeclarationsService;
    private final ScopeSession scopeSession;
    private final boolean serializeTypeTableToFunction;
    private final FirSession session;
    private final boolean stdLibCompilation;
    private final AbstractTypeApproximator typeApproximator;
    private final Interner<FirTypeParameter> typeParameters;
    private final MutableTypeTable typeTable;
    private final MutableVersionRequirementTable versionRequirementTable;

    @Retention(RetentionPolicy.RUNTIME)
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000Ê\u0001\u000e\b\u0003\u0012\n\b\u0004\u0012\u0006\b\n0\u00058\u0006¨\u0006\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer$SensitiveApi;", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-serialization", "Lkotlin/RequiresOptIn;", "level", "Lkotlin/RequiresOptIn$Level;", "ERROR"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public @interface SensitiveApi {
    }

    private FirElementSerializer(FirSession firSession, ScopeSession scopeSession, FirDeclaration firDeclaration, Interner<FirTypeParameter> interner, FirSerializerExtension firSerializerExtension, MutableTypeTable mutableTypeTable, MutableVersionRequirementTable mutableVersionRequirementTable, boolean z, AbstractTypeApproximator abstractTypeApproximator, LanguageVersionSettings languageVersionSettings, boolean z2) {
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.currentDeclaration = firDeclaration;
        this.typeParameters = interner;
        this.extension = firSerializerExtension;
        this.typeTable = mutableTypeTable;
        this.versionRequirementTable = mutableVersionRequirementTable;
        this.serializeTypeTableToFunction = z;
        this.typeApproximator = abstractTypeApproximator;
        this.languageVersionSettings = languageVersionSettings;
        this.produceHeaderKlib = z2;
        this.contractSerializer = new FirContractSerializer();
        this.providedDeclarationsService = FirProvidedDeclarationsForMetadataServiceKt.getProvidedDeclarationsForMetadataService(getSession());
        this.stdLibCompilation = ((Boolean) languageVersionSettings.getFlag(AnalysisFlags.getStdlibCompilation())).booleanValue();
    }

    private final void addDeclarationProto(ProtoBuf.Package.Builder builder, FirDeclaration firDeclaration, Set<? extends FirDeclaration> set, Function1<? super FirDeclaration, Unit> function1) {
        if (!(firDeclaration instanceof FirMemberDeclaration)) {
            function1.invoke(firDeclaration);
            return;
        }
        FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) firDeclaration;
        if (SerializationUtilKt.isNotExpectOrShouldBeSerialized(firMemberDeclaration, set) && SerializationUtilKt.isNotPrivateOrShouldBeSerialized(firMemberDeclaration, this.produceHeaderKlib)) {
            if (firMemberDeclaration instanceof FirProperty) {
                ProtoBuf.Property.Builder builderPropertyProto = propertyProto((FirProperty) firDeclaration);
                if (builderPropertyProto != null) {
                    builder.addProperty(builderPropertyProto);
                    return;
                }
                return;
            }
            if (firMemberDeclaration instanceof FirNamedFunction) {
                ProtoBuf.Function.Builder builderFunctionProto = functionProto((FirFunction) firDeclaration);
                if (builderFunctionProto != null) {
                    builder.addFunction(builderFunctionProto);
                    return;
                }
                return;
            }
            if (!(firMemberDeclaration instanceof FirTypeAlias)) {
                function1.invoke(firDeclaration);
                return;
            }
            ProtoBuf.TypeAlias.Builder builderTypeAliasProto = typeAliasProto((FirTypeAlias) firDeclaration);
            if (builderTypeAliasProto != null) {
                builder.addTypeAlias(builderTypeAliasProto);
            }
        }
    }

    public static Unit b(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ProtoBuf.Class.Builder classProtoImpl(FirClass klass) {
        Modality modality;
        Visibility visibilityNormalizeVisibility;
        Map<String, byte[]> mapFindMetadataExtensionsFor;
        FirRegularClassSymbol companionObjectSymbol;
        FirElementSerializer firElementSerializer = this;
        FirSession session = firElementSerializer.getSession();
        try {
            final ProtoBuf.Class.Builder builderNewBuilder = ProtoBuf.Class.newBuilder();
            FirRegularClass firRegularClass = klass instanceof FirRegularClass ? (FirRegularClass) klass : null;
            if (firRegularClass == null || (modality = firRegularClass.getStatus().getModality()) == null) {
                modality = Modality.FINAL;
            }
            boolean z = klass.getClassKind() == ClassKind.ENUM_CLASS && firElementSerializer.languageVersionSettings.supportsFeature(LanguageFeature.EnumEntries);
            boolean z2 = !FirAnnotationUtilsKt.nonSourceAnnotations(klass, firElementSerializer.getSession()).isEmpty() || firElementSerializer.extension.hasAdditionalAnnotations(klass);
            ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
            if (firRegularClass == null || (visibilityNormalizeVisibility = firElementSerializer.normalizeVisibility(firRegularClass)) == null) {
                visibilityNormalizeVisibility = Visibilities.Local.INSTANCE;
            }
            int classFlags = Flags.getClassFlags(z2, protoEnumFlags.visibility(visibilityNormalizeVisibility), protoEnumFlags.modality(modality), protoEnumFlags.classKind(klass.getClassKind(), firRegularClass != null && firRegularClass.getStatus().isCompanion()), firRegularClass != null && firRegularClass.getStatus().isInner(), firRegularClass != null && firRegularClass.getStatus().isData(), firRegularClass != null && firRegularClass.getStatus().isExternal(), firRegularClass != null && firRegularClass.getStatus().isExpect(), firRegularClass != null && (firRegularClass.getStatus().isInline() || firRegularClass.getStatus().isValue()), firRegularClass != null && firRegularClass.getStatus().isFun(), z);
            if (classFlags != builderNewBuilder.getFlags()) {
                builderNewBuilder.setFlags(classFlags);
            }
            builderNewBuilder.setFqName(getClassifierId(klass));
            for (FirTypeParameterRef firTypeParameterRef : klass.getTypeParameters()) {
                if (firTypeParameterRef instanceof FirTypeParameter) {
                    builderNewBuilder.addTypeParameter(firElementSerializer.typeParameterProto((FirTypeParameter) firTypeParameterRef));
                }
            }
            FirClassSymbol<FirClass> symbol = klass.getSymbol();
            ClassId classId = symbol.getClassId();
            StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
            if (!Intrinsics.areEqual(classId, standardClassIds.getAny()) && !Intrinsics.areEqual(classId, standardClassIds.getNothing())) {
                for (FirTypeRef firTypeRef : firElementSerializer.extension.getClassSupertypes(klass)) {
                    if (firElementSerializer.useTypeTable()) {
                        builderNewBuilder.addSupertypeId(typeId$default(firElementSerializer, firTypeRef, false, 2, null));
                    } else {
                        builderNewBuilder.addSupertype(typeProto$default(firElementSerializer, firTypeRef, false, 2, null));
                    }
                }
            }
            if (firRegularClass != null && firRegularClass.getClassKind() != ClassKind.ENUM_ENTRY) {
                for (FirConstructor firConstructor : firElementSerializer.constructors(firRegularClass)) {
                    if (SerializationUtilKt.isNotPrivateOrShouldBeSerialized(firConstructor, firElementSerializer.produceHeaderKlib)) {
                        builderNewBuilder.addConstructor(firElementSerializer.constructorProto(firConstructor));
                    }
                }
                Iterator it = CollectionsKt.sortedWith(firElementSerializer.providedDeclarationsService.getProvidedConstructors(symbol, firElementSerializer.getScopeSession()), FirCallableDeclarationComparator.INSTANCE).iterator();
                while (it.hasNext()) {
                    builderNewBuilder.addConstructor(firElementSerializer.constructorProto((FirConstructor) it.next()));
                }
            }
            List<FirCallableDeclaration> listPlus = CollectionsKt.plus(memberDeclarations(klass), CollectionsKt.sortedWith(firElementSerializer.providedDeclarationsService.getProvidedCallables(symbol, firElementSerializer.getScopeSession()), FirCallableDeclarationComparator.INSTANCE));
            for (FirCallableDeclaration firCallableDeclaration : listPlus) {
                if (!UtilsKt.isGeneratedStaticEnumMember(firCallableDeclaration, klass) && SerializationUtilKt.isNotPrivateOrShouldBeSerialized(firCallableDeclaration, firElementSerializer.produceHeaderKlib) && !Intrinsics.areEqual(firCallableDeclaration.getOrigin(), FirDeclarationOrigin.Enhancement.INSTANCE) && !Intrinsics.areEqual(firCallableDeclaration.getOrigin(), FirDeclarationOrigin.Synthetic.FakeHiddenInPreparationForNewJdk.INSTANCE)) {
                    if (firCallableDeclaration instanceof FirProperty) {
                        ProtoBuf.Property.Builder builderPropertyProto = firElementSerializer.propertyProto((FirProperty) firCallableDeclaration);
                        if (builderPropertyProto != null) {
                            builderNewBuilder.addProperty(builderPropertyProto);
                        }
                    } else if (firCallableDeclaration instanceof FirNamedFunction) {
                        ProtoBuf.Function.Builder builderFunctionProto = firElementSerializer.functionProto((FirFunction) firCallableDeclaration);
                        if (builderFunctionProto != null) {
                            builderNewBuilder.addFunction(builderFunctionProto);
                        }
                    } else if (firCallableDeclaration instanceof FirEnumEntry) {
                        builderNewBuilder.addEnumEntry(firElementSerializer.enumEntryProto((FirEnumEntry) firCallableDeclaration));
                    } else {
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            for (FirClassifierSymbol<?> firClassifierSymbol : firElementSerializer.computeNestedClassifiersForClass(symbol)) {
                if (firClassifierSymbol instanceof FirTypeAliasSymbol) {
                    ProtoBuf.TypeAlias.Builder builderTypeAliasProto = firElementSerializer.typeAliasProto((FirTypeAlias) ((FirTypeAliasSymbol) firClassifierSymbol).getFir());
                    if (builderTypeAliasProto != null) {
                        builderNewBuilder.addTypeAlias(builderTypeAliasProto);
                    }
                } else if (firClassifierSymbol instanceof FirRegularClassSymbol) {
                    builderNewBuilder.addNestedClassName(firElementSerializer.getSimpleNameIndex(((FirRegularClassSymbol) firClassifierSymbol).getName()));
                }
            }
            if ((klass instanceof FirRegularClass) && klass.getStatus().getModality() == Modality.SEALED) {
                Iterator<ClassId> it2 = SealedClassInheritorsKt.getSealedClassInheritors((FirRegularClass) klass, firElementSerializer.getSession()).iterator();
                while (it2.hasNext()) {
                    builderNewBuilder.addSealedSubclassFqName(firElementSerializer.getStringTable().getQualifiedClassNameIndex(it2.next()));
                }
            }
            FirRegularClass firRegularClass2 = (firRegularClass == null || (companionObjectSymbol = firRegularClass.getCompanionObjectSymbol()) == null) ? null : (FirRegularClass) companionObjectSymbol.getFir();
            if (firRegularClass2 != null) {
                builderNewBuilder.setCompanionObjectName(firElementSerializer.getSimpleNameIndex(firRegularClass2.getName()));
            }
            FirRegularClass firRegularClass3 = klass instanceof FirRegularClass ? (FirRegularClass) klass : null;
            ValueClassRepresentation<ConeRigidType> valueClassRepresentation = firRegularClass3 != null ? FirValueClassRepresentationKt.getValueClassRepresentation(firRegularClass3) : null;
            if (valueClassRepresentation instanceof InlineClassRepresentation) {
                builderNewBuilder.setInlineClassUnderlyingPropertyName(firElementSerializer.getSimpleNameIndex(((InlineClassRepresentation) valueClassRepresentation).getUnderlyingPropertyName()));
                Object obj = null;
                boolean z3 = false;
                for (Object obj2 : listPlus) {
                    FirCallableDeclaration firCallableDeclaration2 = (FirCallableDeclaration) obj2;
                    if ((firCallableDeclaration2 instanceof FirProperty) && ((FirProperty) firCallableDeclaration2).getReceiverParameter() == null && ((FirProperty) firCallableDeclaration2).getContextParameters().isEmpty() && Intrinsics.areEqual(((FirProperty) firCallableDeclaration2).getName(), ((InlineClassRepresentation) valueClassRepresentation).getUnderlyingPropertyName())) {
                        if (z3) {
                            throw new IllegalArgumentException("Collection contains more than one matching element.");
                        }
                        z3 = true;
                        obj = obj2;
                    }
                }
                if (!z3) {
                    throw new NoSuchElementException("Collection contains no element matching the predicate.");
                }
                if (!((FirCallableDeclaration) obj).getStatus().getVisibility().getIsPublicAPI()) {
                    if (firElementSerializer.useTypeTable()) {
                        builderNewBuilder.setInlineClassUnderlyingTypeId(typeId$default(firElementSerializer, ((InlineClassRepresentation) valueClassRepresentation).getUnderlyingType(), false, false, 6, null));
                        firElementSerializer = this;
                    } else {
                        firElementSerializer = this;
                        builderNewBuilder.setInlineClassUnderlyingType(typeProto$default(firElementSerializer, ((InlineClassRepresentation) valueClassRepresentation).getUnderlyingType(), false, null, false, false, 30, null));
                    }
                }
            } else if (!(valueClassRepresentation instanceof MultiFieldValueClassRepresentation) && valueClassRepresentation != null) {
                throw new NoWhenBranchMatchedException();
            }
            if (klass instanceof FirRegularClass) {
                for (FirValueParameter firValueParameter : ((FirRegularClass) klass).getContextParameters()) {
                    FirTypeRef returnTypeRef = firValueParameter.getReturnTypeRef();
                    if (firElementSerializer.useTypeTable()) {
                        builderNewBuilder.addContextReceiverTypeId(typeId$default(firElementSerializer, returnTypeRef, false, 2, null));
                    } else {
                        builderNewBuilder.addContextReceiverType(typeProto$default(firElementSerializer, firValueParameter.getReturnTypeRef(), false, 2, null));
                    }
                }
            }
            MutableVersionRequirementTable mutableVersionRequirementTable = firElementSerializer.versionRequirementTable;
            if (mutableVersionRequirementTable == null) {
                throw new IllegalStateException(("Version requirements must be serialized for classes: " + UtilsKt.render(klass)).toString());
            }
            builderNewBuilder.addAllVersionRequirement(firElementSerializer.serializeVersionRequirements(mutableVersionRequirementTable, klass));
            firElementSerializer.extension.serializeClass(klass, builderNewBuilder, firElementSerializer.versionRequirementTable, firElementSerializer);
            if (firElementSerializer.metDefinitelyNotNullType) {
                builderNewBuilder.addVersionRequirement(INSTANCE.writeLanguageVersionRequirement(LanguageFeature.DefinitelyNonNullableTypes, firElementSerializer.versionRequirementTable));
            }
            ProtoBuf.TypeTable typeTableSerialize = firElementSerializer.typeTable.serialize();
            if (typeTableSerialize != null) {
                builderNewBuilder.setTypeTable(typeTableSerialize);
                Unit unit2 = Unit.INSTANCE;
            }
            ProtoBuf.VersionRequirementTable versionRequirementTableSerialize = firElementSerializer.versionRequirementTable.serialize();
            if (versionRequirementTableSerialize != null) {
                builderNewBuilder.setVersionRequirementTable(versionRequirementTableSerialize);
                Unit unit3 = Unit.INSTANCE;
            }
            if (klass instanceof FirRegularClass) {
                Iterator<FirMetadataSerializerPlugin> it3 = FirMetadataSerializerPluginKt.getMetadataSerializerPlugins(FirExtensionServiceKt.getExtensionService(firElementSerializer.getSession())).iterator();
                while (it3.hasNext()) {
                    it3.next().registerProtoExtensions(((FirRegularClass) klass).getSymbol(), firElementSerializer.getStringTable(), new FirMetadataSerializerPlugin.ProtoRegistrar() { // from class: org.jetbrains.kotlin.fir.serialization.FirElementSerializer$classProtoImpl$1$protoRegistrar$1
                        @Override // org.jetbrains.kotlin.fir.serialization.FirMetadataSerializerPlugin.ProtoRegistrar
                        public <Type> void setExtension(GeneratedMessageLite.GeneratedExtension<ProtoBuf.Class, Type> extension, Type value) {
                            extension.getClass();
                            builderNewBuilder.setExtension(extension, value);
                        }
                    });
                }
            }
            FirAdditionalMetadataProvider additionalMetadataProvider = firElementSerializer.extension.getAdditionalMetadataProvider();
            if (additionalMetadataProvider != null && (mapFindMetadataExtensionsFor = additionalMetadataProvider.findMetadataExtensionsFor(klass)) != null) {
                for (Map.Entry<String, byte[]> entry : mapFindMetadataExtensionsFor.entrySet()) {
                    String key = entry.getKey();
                    byte[] value = entry.getValue();
                    ProtoBuf.CompilerPluginData.Builder builderNewBuilder2 = ProtoBuf.CompilerPluginData.newBuilder();
                    builderNewBuilder2.setPluginId(firElementSerializer.getStringTable().getStringIndex(key));
                    builderNewBuilder2.setData(ByteString.copyFrom(value));
                    builderNewBuilder.addCompilerPluginData(builderNewBuilder2);
                }
                Unit unit4 = Unit.INSTANCE;
            }
            return builderNewBuilder;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(klass, th);
            wq6.a();
            return null;
        }
    }

    private final ProtoBuf.Constructor.Builder constructorProto(FirConstructor constructor) {
        Map<String, byte[]> mapFindMetadataExtensionsFor;
        FirSession session = getSession();
        try {
            ProtoBuf.Constructor.Builder builderNewBuilder = ProtoBuf.Constructor.newBuilder();
            FirElementSerializer firElementSerializerCreateChildSerializer = createChildSerializer(constructor);
            int i = 0;
            boolean z = !FirAnnotationUtilsKt.nonSourceAnnotations(constructor, getSession()).isEmpty() || this.extension.hasAdditionalAnnotations(constructor);
            ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
            int constructorFlags = Flags.getConstructorFlags(z, protoEnumFlags.visibility(normalizeVisibility(constructor)), true ^ constructor.getIsPrimary(), shouldSetStableParameterNames(constructor), protoEnumFlags.returnValueStatus(constructor.getStatus().getReturnValueStatus()));
            if (constructorFlags != builderNewBuilder.getFlags()) {
                builderNewBuilder.setFlags(constructorFlags);
            }
            Iterator<T> it = constructor.getValueParameters().iterator();
            while (it.hasNext()) {
                builderNewBuilder.addValueParameter(firElementSerializerCreateChildSerializer.valueParameterProto((FirValueParameter) it.next(), i, constructor));
                i++;
            }
            MutableVersionRequirementTable mutableVersionRequirementTable = this.versionRequirementTable;
            if (mutableVersionRequirementTable != null) {
                builderNewBuilder.addAllVersionRequirement(serializeVersionRequirements(mutableVersionRequirementTable, constructor));
                if (firElementSerializerCreateChildSerializer.metDefinitelyNotNullType) {
                    builderNewBuilder.addVersionRequirement(writeVersionRequirement(mutableVersionRequirementTable, LanguageFeature.DefinitelyNonNullableTypes));
                }
            }
            this.extension.serializeConstructor(constructor, builderNewBuilder, firElementSerializerCreateChildSerializer);
            FirAdditionalMetadataProvider additionalMetadataProvider = this.extension.getAdditionalMetadataProvider();
            if (additionalMetadataProvider != null && (mapFindMetadataExtensionsFor = additionalMetadataProvider.findMetadataExtensionsFor(constructor)) != null) {
                for (Map.Entry<String, byte[]> entry : mapFindMetadataExtensionsFor.entrySet()) {
                    String key = entry.getKey();
                    byte[] value = entry.getValue();
                    ProtoBuf.CompilerPluginData.Builder builderNewBuilder2 = ProtoBuf.CompilerPluginData.newBuilder();
                    builderNewBuilder2.setPluginId(getStringTable().getStringIndex(key));
                    builderNewBuilder2.setData(ByteString.copyFrom(value));
                    builderNewBuilder.addCompilerPluginData(builderNewBuilder2);
                }
            }
            return builderNewBuilder;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(constructor, th);
            wq6.a();
            return null;
        }
    }

    private final List<FirConstructor> constructors(final FirClass firClass) {
        final List listCreateListBuilder = CollectionsKt.createListBuilder();
        FirTypeScope firTypeScopeUnsubstitutedScope = FirKotlinScopeProviderKt.unsubstitutedScope((SessionAndScopeSessionHolder) this, firClass, false, (FirResolvePhase) null);
        final Function1<FirConstructorSymbol, Unit> function1 = new Function1<FirConstructorSymbol, Unit>() { // from class: org.jetbrains.kotlin.fir.serialization.FirElementSerializer$constructors$$inlined$collectDeclarations$1
            /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
            /* JADX WARN: Code duplicated, block: B:12:0x0037 A[DONT_INVERT] */
            /* JADX WARN: Code duplicated, block: B:13:0x0039  */
            /* JADX WARN: Code duplicated, block: B:14:0x003e  */
            /* JADX WARN: Code duplicated, block: B:25:0x0067  */
            /* JADX WARN: Code duplicated, block: B:27:0x006d  */
            /* JADX WARN: Code duplicated, block: B:32:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Multi-variable type inference failed */
            public final void invoke(FirConstructorSymbol firConstructorSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
                ClassId classId;
                Modality modality;
                firConstructorSymbol.getClass();
                D fir = firConstructorSymbol.getFir();
                if (fir == 0) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.declarations.FirConstructor");
                    return;
                }
                FirConstructor firConstructor = (FirConstructor) fir;
                ConeClassLikeLookupTag coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull = ClassMembersKt.dispatchReceiverClassLookupTagOrNull(firConstructor);
                FirClass firClass2 = firClass;
                if (firClass2 instanceof FirRegularClass) {
                    if (((FirRegularClass) firClass2).getStatus().isData()) {
                        if (coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull != null) {
                            classId = coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull.getClassId();
                        } else {
                            classId = null;
                        }
                        if (Intrinsics.areEqual(classId, StandardClassIds.INSTANCE.getAny())) {
                            if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firConstructor)) {
                                return;
                            } else {
                                firConstructor.getStatus().isStatic();
                            }
                        } else if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firConstructor)) {
                            return;
                        } else {
                            firConstructor.getStatus().isStatic();
                        }
                    } else {
                        FirClass firClass3 = firClass;
                        if (firClass3.getStatus().isInline() || firClass3.getStatus().isValue()) {
                            if (coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull != null) {
                                classId = coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull.getClassId();
                            } else {
                                classId = null;
                            }
                            if (Intrinsics.areEqual(classId, StandardClassIds.INSTANCE.getAny()) || (modality = firConstructor.getStatus().getModality()) == null || modality == Modality.FINAL || firClass.getStatus().isExpect()) {
                                if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firConstructor)) {
                                    return;
                                } else {
                                    firConstructor.getStatus().isStatic();
                                }
                            }
                        } else if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firConstructor)) {
                            return;
                        } else {
                            firConstructor.getStatus().isStatic();
                        }
                    }
                } else if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firConstructor)) {
                    return;
                } else {
                    firConstructor.getStatus().isStatic();
                }
                listCreateListBuilder.add(firConstructor);
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
            /* JADX WARN: Multi-variable type inference failed */
            public /* bridge */ /* synthetic */ Object invoke(Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
                invoke((FirConstructorSymbol) obj);
                return Unit.INSTANCE;
            }
        };
        firTypeScopeUnsubstitutedScope.processDeclaredConstructors(new Function1() { // from class: i55
            public final Object invoke(Object obj) {
                return FirElementSerializer.constructors$lambda$0$0(function1, (FirConstructorSymbol) obj);
            }
        });
        for (FirDeclaration firDeclaration : firClass.getDeclarations()) {
            if ((firDeclaration instanceof FirConstructor) && ((FirMemberDeclaration) firDeclaration).getStatus().isStatic()) {
                listCreateListBuilder.add(firDeclaration);
            }
        }
        List listBuild = CollectionsKt.build(listCreateListBuilder);
        List<FirDeclaration> declarations = firClass.getDeclarations();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declarations) {
            if (obj instanceof FirConstructor) {
                arrayList.add(obj);
            }
        }
        Map mapMapToIndex = org.jetbrains.kotlin.utils.CollectionsKt.mapToIndex(arrayList);
        List listSortedWith = CollectionsKt.sortedWith(listBuild, new FirElementSerializer$collectDeclarations$$inlined$sortedBy$1(mapMapToIndex));
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : listSortedWith) {
            if (mapMapToIndex.containsKey((FirCallableDeclaration) obj2)) {
                arrayList2.add(obj2);
            } else {
                arrayList3.add(obj2);
            }
        }
        Pair pair = new Pair(arrayList2, arrayList3);
        return CollectionsKt.plus((List) pair.component1(), CollectionsKt.sortedWith((List) pair.component2(), FirCallableDeclarationComparator.INSTANCE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit constructors$lambda$0$0(Function1 function1, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        function1.invoke(firConstructorSymbol);
        return Unit.INSTANCE;
    }

    @JvmStatic
    public static final FirElementSerializer create(FirSession firSession, ScopeSession scopeSession, FirClass firClass, FirSerializerExtension firSerializerExtension, FirElementSerializer firElementSerializer, AbstractTypeApproximator abstractTypeApproximator, LanguageVersionSettings languageVersionSettings, boolean z) {
        return INSTANCE.create(firSession, scopeSession, firClass, firSerializerExtension, firElementSerializer, abstractTypeApproximator, languageVersionSettings, z);
    }

    private final FirAnnotation createAnnotationForCompilerDefinedTypeAttribute(ConeAttribute<?> attribute) {
        FirAnnotationArgumentMapping firAnnotationArgumentMappingBuild;
        ConeClassLikeLookupTagImpl lookupTag = TypeConstructionUtilsKt.toLookupTag((ClassId) MapsKt.getValue(CompilerConeAttributes.INSTANCE.getClassIdByCompilerAttributeKey(), attribute.getKey()));
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) this, (ConeClassLikeLookupTag) lookupTag);
        if ((regularClassSymbol != null ? FirRetentionAnnotationHelpersKt.getRetention(regularClassSymbol, getSession()) : null) == AnnotationRetention.SOURCE) {
            return null;
        }
        FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setConeType(new ConeClassLikeTypeImpl(lookupTag, new ConeTypeProjection[0], false, null, 8, null));
        firAnnotationBuilder.setAnnotationTypeRef(firResolvedTypeRefBuilder.build());
        if (attribute instanceof CompilerConeAttributes.ContextFunctionTypeParams) {
            FirAnnotationArgumentMappingBuilder firAnnotationArgumentMappingBuilder = new FirAnnotationArgumentMappingBuilder();
            firAnnotationArgumentMappingBuilder.getMapping().put(StandardNames.CONTEXT_FUNCTION_TYPE_PARAMETER_COUNT_NAME, FirConstExpressionBuilderKt.buildLiteralExpression$default(null, ConstantValueKind.Int.INSTANCE, Integer.valueOf(((CompilerConeAttributes.ContextFunctionTypeParams) attribute).getContextParameterNumber()), null, true, null, 40, null));
            firAnnotationArgumentMappingBuild = firAnnotationArgumentMappingBuilder.build();
        } else {
            firAnnotationArgumentMappingBuild = FirEmptyAnnotationArgumentMapping.INSTANCE;
        }
        firAnnotationBuilder.setArgumentMapping(firAnnotationArgumentMappingBuild);
        return firAnnotationBuilder.mo288build();
    }

    private final FirElementSerializer createChildSerializer(FirDeclaration declaration) {
        return new FirElementSerializer(getSession(), getScopeSession(), declaration, new Interner(this.typeParameters), this.extension, this.typeTable, this.versionRequirementTable, false, this.typeApproximator, this.languageVersionSettings, this.produceHeaderKlib);
    }

    @JvmStatic
    public static final FirElementSerializer createForLambda(FirSession firSession, ScopeSession scopeSession, FirSerializerExtension firSerializerExtension, AbstractTypeApproximator abstractTypeApproximator, LanguageVersionSettings languageVersionSettings) {
        return INSTANCE.createForLambda(firSession, scopeSession, firSerializerExtension, abstractTypeApproximator, languageVersionSettings);
    }

    @JvmStatic
    public static final FirElementSerializer createForScript(FirSession firSession, ScopeSession scopeSession, FirScript firScript, FirSerializerExtension firSerializerExtension, AbstractTypeApproximator abstractTypeApproximator, LanguageVersionSettings languageVersionSettings, boolean z) {
        return INSTANCE.createForScript(firSession, scopeSession, firScript, firSerializerExtension, abstractTypeApproximator, languageVersionSettings, z);
    }

    @JvmStatic
    public static final FirElementSerializer createForSnippet(FirSession firSession, ScopeSession scopeSession, FirReplSnippet firReplSnippet, FirSerializerExtension firSerializerExtension, AbstractTypeApproximator abstractTypeApproximator, LanguageVersionSettings languageVersionSettings, boolean z) {
        return INSTANCE.createForSnippet(firSession, scopeSession, firReplSnippet, firSerializerExtension, abstractTypeApproximator, languageVersionSettings, z);
    }

    @JvmStatic
    public static final FirElementSerializer createTopLevel(FirSession firSession, ScopeSession scopeSession, FirSerializerExtension firSerializerExtension, AbstractTypeApproximator abstractTypeApproximator, LanguageVersionSettings languageVersionSettings, boolean z) {
        return INSTANCE.createTopLevel(firSession, scopeSession, firSerializerExtension, abstractTypeApproximator, languageVersionSettings, z);
    }

    public static Unit e(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        throw new IllegalStateException(("Unsupported top-level declaration type: " + UtilsKt.render(firDeclaration)).toString());
    }

    private final ProtoBuf.EnumEntry.Builder enumEntryProto(FirEnumEntry enumEntry) {
        FirSession session = getSession();
        try {
            ProtoBuf.EnumEntry.Builder builderNewBuilder = ProtoBuf.EnumEntry.newBuilder();
            builderNewBuilder.setName(getSimpleNameIndex(enumEntry.getName()));
            this.extension.serializeEnumEntry(enumEntry, builderNewBuilder);
            return builderNewBuilder;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(enumEntry, th);
            wq6.a();
            return null;
        }
    }

    public static Unit f(ProtoBuf.VersionRequirement.Builder builder, int i) {
        builder.setVersionFull(i);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void fillFromPossiblyInnerType(ProtoBuf.Type.Builder builder, FirClassLikeSymbol<?> symbol, ConeTypeProjection[] typeArguments, int typeArgumentIndex, boolean abbreviationOnly) {
        ClassId outerClassId;
        FirClassLikeSymbol<?> symbol2;
        FirClassLikeDeclaration firClassLikeDeclaration = (FirClassLikeDeclaration) symbol.getFir();
        int classifierId = getClassifierId(firClassLikeDeclaration);
        if (firClassLikeDeclaration instanceof FirTypeAlias) {
            builder.setTypeAliasName(classifierId);
        } else {
            builder.setClassName(classifierId);
        }
        int size = firClassLikeDeclaration.getTypeParameters().size();
        int i = 0;
        int i2 = typeArgumentIndex;
        while (i < size && (firClassLikeDeclaration.getTypeParameters().get(i) instanceof FirTypeParameter)) {
            if (i2 >= typeArguments.length) {
                return;
            }
            builder.addArgument(typeArgument(typeArguments[i2], abbreviationOnly));
            i++;
            i2++;
        }
        if (!symbol.getRawStatus().isInner() || (outerClassId = symbol.getClassId().getOuterClassId()) == null || ((FirClassLikeDeclaration) symbol.getFir()).getIsLocal() || (symbol2 = ToSymbolUtilsKt.toSymbol((SessionHolder) this, (ConeClassLikeLookupTag) TypeConstructionUtilsKt.toLookupTag(outerClassId))) == null) {
            return;
        }
        ProtoBuf.Type.Builder builderNewBuilder = ProtoBuf.Type.newBuilder();
        builderNewBuilder.getClass();
        fillFromPossiblyInnerType$default(this, builderNewBuilder, symbol2, typeArguments, i2, false, 16, null);
        if (useTypeTable()) {
            builder.setOuterTypeId(this.typeTable.get(builderNewBuilder));
        } else {
            builder.setOuterType(builderNewBuilder);
        }
    }

    public static /* synthetic */ void fillFromPossiblyInnerType$default(FirElementSerializer firElementSerializer, ProtoBuf.Type.Builder builder, FirClassLikeSymbol firClassLikeSymbol, ConeTypeProjection[] coneTypeProjectionArr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            z = false;
        }
        firElementSerializer.fillFromPossiblyInnerType(builder, firClassLikeSymbol, coneTypeProjectionArr, i, z);
    }

    private final ProtoBuf.Package.Builder finalizePackagePartProto(FqName packageFqName, ProtoBuf.Package.Builder builder, Set<? extends FirDeclaration> actualizedExpectDeclarations) {
        ProtoBuf.VersionRequirementTable versionRequirementTableSerialize;
        this.extension.serializePackage(packageFqName, builder, this.versionRequirementTable, this);
        Iterator<FirDeclaration> it = this.providedDeclarationsService.getProvidedTopLevelDeclarations(packageFqName, getScopeSession()).iterator();
        while (it.hasNext()) {
            addDeclarationProto(builder, it.next(), actualizedExpectDeclarations, new Function1() { // from class: h55
                public final Object invoke(Object obj) {
                    return FirElementSerializer.e((FirDeclaration) obj);
                }
            });
        }
        ProtoBuf.TypeTable typeTableSerialize = this.typeTable.serialize();
        if (typeTableSerialize != null) {
            builder.setTypeTable(typeTableSerialize);
        }
        MutableVersionRequirementTable mutableVersionRequirementTable = this.versionRequirementTable;
        if (mutableVersionRequirementTable != null && (versionRequirementTableSerialize = mutableVersionRequirementTable.serialize()) != null) {
            builder.setVersionRequirementTable(versionRequirementTableSerialize);
        }
        return builder;
    }

    public static boolean g(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return coneKotlinType instanceof ConeCapturedType;
    }

    private final int getAccessorFlags(FirPropertyAccessor accessor, FirProperty property) {
        boolean z = !nonSourceAnnotations(accessor, getSession()).isEmpty() || this.extension.hasAdditionalAnnotations(accessor);
        ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
        ProtoBuf.Visibility visibility = protoEnumFlags.visibility(normalizeVisibility(accessor));
        Modality modality = property.getStatus().getModality();
        modality.getClass();
        return Flags.getAccessorFlags(z, visibility, protoEnumFlags.modality(modality), !isDefaultAccessor(accessor, property), accessor.getStatus().isExternal(), accessor.getStatus().isInline());
    }

    private final int getClassifierId(FirClassLikeDeclaration declaration) {
        FirScriptSymbol containingScriptSymbolAttr = ClassMembersKt.getContainingScriptSymbolAttr(declaration);
        return containingScriptSymbolAttr != null ? getScriptOrReplClassId(declaration, FirElementSerializerKt.scriptClassId(containingScriptSymbolAttr.getFir())) : getStringTable().getFqNameIndex(declaration);
    }

    private final boolean getContainsCapturedTypes(ConeKotlinType coneKotlinType) {
        return ConeTypeUtilsKt.contains(coneKotlinType, new Function1() { // from class: j55
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirElementSerializer.g((ConeKotlinType) obj));
            }
        });
    }

    private final int getScriptOrReplClassId(FirClassLikeDeclaration declaration, ClassId containerClassId) {
        Iterator it = declaration.getSymbol().getClassId().getRelativeClassName().pathSegments().iterator();
        while (it.hasNext()) {
            containerClassId = containerClassId.createNestedClassId((Name) it.next());
        }
        return getStringTable().getQualifiedClassNameIndex(containerClassId);
    }

    private final int getSimpleNameIndex(Name name) {
        FirElementAwareStringTable stringTable = getStringTable();
        String strAsString = name.asString();
        strAsString.getClass();
        return stringTable.getStringIndex(strAsString);
    }

    private final int getTypeParameterId(FirTypeParameter typeParameter) {
        return this.typeParameters.intern(typeParameter);
    }

    public static Unit h(ProtoBuf.VersionRequirement.Builder builder, int i) {
        builder.setVersion(i);
        return Unit.INSTANCE;
    }

    public static Unit i(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return Unit.INSTANCE;
    }

    private final boolean isDefaultAccessor(FirPropertyAccessor accessor, FirProperty property) {
        boolean z;
        if (property.getSymbol() instanceof FirLocalPropertySymbol) {
            return true;
        }
        List<FirValueParameter> valueParameters = accessor.getValueParameters();
        if ((valueParameters instanceof Collection) && valueParameters.isEmpty()) {
            z = false;
        } else {
            Iterator<T> it = valueParameters.iterator();
            while (true) {
                if (it.hasNext()) {
                    FirValueParameter firValueParameter = (FirValueParameter) it.next();
                    if (!FirAnnotationUtilsKt.nonSourceAnnotations(firValueParameter, getSession()).isEmpty() || this.extension.hasAdditionalAnnotations(firValueParameter)) {
                        z = true;
                    }
                } else {
                    z = false;
                }
            }
        }
        return (!(accessor instanceof FirDefaultPropertyAccessor) || z || !Intrinsics.areEqual(accessor.getStatus().getVisibility(), property.getStatus().getVisibility()) || accessor.getStatus().isExternal() || accessor.getStatus().isInline()) ? false : true;
    }

    private final List<FirCallableDeclaration> memberDeclarations(final FirClass firClass) {
        final List listCreateListBuilder = CollectionsKt.createListBuilder();
        FirTypeScope firTypeScopeUnsubstitutedScope = FirKotlinScopeProviderKt.unsubstitutedScope((SessionAndScopeSessionHolder) this, firClass, false, (FirResolvePhase) null);
        final Function1<FirCallableSymbol<?>, Unit> function1 = new Function1<FirCallableSymbol<?>, Unit>() { // from class: org.jetbrains.kotlin.fir.serialization.FirElementSerializer$memberDeclarations$$inlined$collectDeclarations$1
            /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
            /* JADX WARN: Code duplicated, block: B:12:0x0037 A[DONT_INVERT] */
            /* JADX WARN: Code duplicated, block: B:13:0x0039  */
            /* JADX WARN: Code duplicated, block: B:14:0x003e  */
            /* JADX WARN: Code duplicated, block: B:25:0x0067  */
            /* JADX WARN: Code duplicated, block: B:27:0x006d  */
            /* JADX WARN: Code duplicated, block: B:29:0x0077  */
            /* JADX WARN: Code duplicated, block: B:37:? A[RETURN, SYNTHETIC] */
            public final void invoke(FirCallableSymbol<?> firCallableSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
                ClassId classId;
                Modality modality;
                firCallableSymbol.getClass();
                Object fir = firCallableSymbol.getFir();
                if (fir == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration");
                    return;
                }
                FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) fir;
                ConeClassLikeLookupTag coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull = ClassMembersKt.dispatchReceiverClassLookupTagOrNull(firCallableDeclaration);
                FirClass firClass2 = firClass;
                if (firClass2 instanceof FirRegularClass) {
                    if (((FirRegularClass) firClass2).getStatus().isData()) {
                        if (coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull != null) {
                            classId = coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull.getClassId();
                        } else {
                            classId = null;
                        }
                        if (Intrinsics.areEqual(classId, StandardClassIds.INSTANCE.getAny())) {
                            if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                                return;
                            }
                            if (!firCallableDeclaration.getStatus().isStatic()) {
                                return;
                            }
                        } else {
                            if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                                return;
                            }
                            if (!firCallableDeclaration.getStatus().isStatic()) {
                                return;
                            }
                        }
                    } else {
                        FirClass firClass3 = firClass;
                        if (firClass3.getStatus().isInline() || firClass3.getStatus().isValue()) {
                            if (coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull != null) {
                                classId = coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull.getClassId();
                            } else {
                                classId = null;
                            }
                            if (Intrinsics.areEqual(classId, StandardClassIds.INSTANCE.getAny()) || (modality = firCallableDeclaration.getStatus().getModality()) == null || modality == Modality.FINAL || firClass.getStatus().isExpect()) {
                                if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                                    return;
                                }
                                if (!firCallableDeclaration.getStatus().isStatic()) {
                                    return;
                                }
                            }
                        } else {
                            if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                                return;
                            }
                            if (!firCallableDeclaration.getStatus().isStatic()) {
                                return;
                            }
                        }
                    }
                } else {
                    if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                        return;
                    }
                    if (!firCallableDeclaration.getStatus().isStatic() && !(firCallableDeclaration instanceof FirConstructor) && !Intrinsics.areEqual(coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull, firClass.getSymbol().getLookupTag())) {
                        return;
                    }
                }
                listCreateListBuilder.add(firCallableDeclaration);
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
            public /* bridge */ /* synthetic */ Object invoke(Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
                invoke((FirCallableSymbol<?>) obj);
                return Unit.INSTANCE;
            }
        };
        FirContainingNamesAwareScopeKt.processAllFunctions(firTypeScopeUnsubstitutedScope, new Function1() { // from class: d55
            public final Object invoke(Object obj) {
                return FirElementSerializer.memberDeclarations$lambda$0$0(function1, (FirNamedFunctionSymbol) obj);
            }
        });
        FirContainingNamesAwareScopeKt.processAllProperties(firTypeScopeUnsubstitutedScope, new Function1() { // from class: e55
            public final Object invoke(Object obj) {
                return FirElementSerializer.memberDeclarations$lambda$0$1(function1, (FirVariableSymbol) obj);
            }
        });
        for (FirDeclaration firDeclaration : firClass.getDeclarations()) {
            if ((firDeclaration instanceof FirCallableDeclaration) && ((FirMemberDeclaration) firDeclaration).getStatus().isStatic()) {
                listCreateListBuilder.add(firDeclaration);
            }
        }
        List listBuild = CollectionsKt.build(listCreateListBuilder);
        List<FirDeclaration> declarations = firClass.getDeclarations();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declarations) {
            if (obj instanceof FirCallableDeclaration) {
                arrayList.add(obj);
            }
        }
        Map mapMapToIndex = org.jetbrains.kotlin.utils.CollectionsKt.mapToIndex(arrayList);
        List listSortedWith = CollectionsKt.sortedWith(listBuild, new FirElementSerializer$collectDeclarations$$inlined$sortedBy$1(mapMapToIndex));
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : listSortedWith) {
            if (mapMapToIndex.containsKey((FirCallableDeclaration) obj2)) {
                arrayList2.add(obj2);
            } else {
                arrayList3.add(obj2);
            }
        }
        Pair pair = new Pair(arrayList2, arrayList3);
        return CollectionsKt.plus((List) pair.component1(), CollectionsKt.sortedWith((List) pair.component2(), FirCallableDeclarationComparator.INSTANCE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit memberDeclarations$lambda$0$0(Function1 function1, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        function1.invoke(firNamedFunctionSymbol);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit memberDeclarations$lambda$0$1(Function1 function1, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        function1.invoke(firVariableSymbol);
        return Unit.INSTANCE;
    }

    private final ProtoBuf.MemberKind memberKind(FirFunction firFunction) {
        FirDeclarationOrigin origin = firFunction.getOrigin();
        if (Intrinsics.areEqual(origin, FirDeclarationOrigin.Delegated.INSTANCE)) {
            return ProtoBuf.MemberKind.DELEGATION;
        }
        return origin instanceof FirDeclarationOrigin.Synthetic ? ProtoBuf.MemberKind.SYNTHESIZED : ProtoBuf.MemberKind.DECLARATION;
    }

    private final List<FirAnnotation> nonSourceAnnotations(FirPropertyAccessor firPropertyAccessor, FirSession firSession) {
        firPropertyAccessor.getClass();
        return FirAnnotationUtilsKt.nonSourceAnnotations(firPropertyAccessor, firSession);
    }

    private final Visibility normalizeVisibility(FirMemberDeclaration declaration) {
        return Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(declaration), Boolean.TRUE) ? Visibilities.Public.INSTANCE : declaration.getStatus().getVisibility().normalize();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit scriptProto$lambda$0$0$0(List list, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        list.add(firCallableSymbol.getFir());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit scriptProto$lambda$0$4$0(ProtoBuf.Class.Builder builder, FirElementSerializer firElementSerializer, FirClassifierSymbol firClassifierSymbol) {
        firClassifierSymbol.getClass();
        if (firClassifierSymbol instanceof FirRegularClassSymbol) {
            builder.addNestedClassName(firElementSerializer.getSimpleNameIndex(((FirRegularClassSymbol) firClassifierSymbol).getName()));
        }
        return Unit.INSTANCE;
    }

    private final ProtoBuf.VersionRequirement.Builder serializeVersionRequirementFromRequireKotlin(FirAnnotation annotation) {
        MatchResult matchResultMatchEntire;
        String str;
        Integer intOrNull;
        Name enumEntryName;
        Name enumEntryName2;
        Integer intOrNull2;
        Integer intOrNull3;
        Object objEvaluateToAnnotationValue = annotation != null ? FirToConstantValueTransformerKt.evaluateToAnnotationValue(this, annotation) : FirToConstantValueTransformerKt.toConstantValueImpl(this, annotation);
        if (!(objEvaluateToAnnotationValue instanceof AnnotationValue)) {
            objEvaluateToAnnotationValue = null;
        }
        AnnotationValue annotationValue = (AnnotationValue) objEvaluateToAnnotationValue;
        if (annotationValue == null) {
            return null;
        }
        Map<Name, ConstantValue<?>> argumentsMapping = annotationValue.getValue().getArgumentsMapping();
        RequireKotlinConstants requireKotlinConstants = RequireKotlinConstants.INSTANCE;
        ConstantValue<?> constantValue = argumentsMapping.get(requireKotlinConstants.getVERSION());
        String str2 = (String) (constantValue != null ? constantValue.getValue() : null);
        if (str2 == null || (matchResultMatchEntire = requireKotlinConstants.getVERSION_REGEX().matchEntire(str2)) == null || (str = (String) CollectionsKt.getOrNull(matchResultMatchEntire.getGroupValues(), 1)) == null || (intOrNull = StringsKt.toIntOrNull(str)) == null) {
            return null;
        }
        int iIntValue = intOrNull.intValue();
        String str3 = (String) CollectionsKt.getOrNull(matchResultMatchEntire.getGroupValues(), 2);
        int iIntValue2 = 0;
        int iIntValue3 = (str3 == null || (intOrNull3 = StringsKt.toIntOrNull(str3)) == null) ? 0 : intOrNull3.intValue();
        String str4 = (String) CollectionsKt.getOrNull(matchResultMatchEntire.getGroupValues(), 4);
        if (str4 != null && (intOrNull2 = StringsKt.toIntOrNull(str4)) != null) {
            iIntValue2 = intOrNull2.intValue();
        }
        final ProtoBuf.VersionRequirement.Builder builderNewBuilder = ProtoBuf.VersionRequirement.newBuilder();
        new VersionRequirement.Version(iIntValue, iIntValue3, iIntValue2).encode(new Function1() { // from class: z45
            public final Object invoke(Object obj) {
                return FirElementSerializer.h(builderNewBuilder, ((Integer) obj).intValue());
            }
        }, new Function1() { // from class: b55
            public final Object invoke(Object obj) {
                return FirElementSerializer.f(builderNewBuilder, ((Integer) obj).intValue());
            }
        });
        ConstantValue<?> constantValue2 = argumentsMapping.get(requireKotlinConstants.getMESSAGE());
        String str5 = (String) (constantValue2 != null ? constantValue2.getValue() : null);
        if (str5 != null) {
            builderNewBuilder.setMessage(getStringTable().getStringIndex(str5));
        }
        EnumValue enumValue = (EnumValue) argumentsMapping.get(requireKotlinConstants.getLEVEL());
        String strAsString = (enumValue == null || (enumEntryName2 = enumValue.getEnumEntryName()) == null) ? null : enumEntryName2.asString();
        if (strAsString != null) {
            int iHashCode = strAsString.hashCode();
            if (iHashCode == 66247144) {
                strAsString.equals("ERROR");
            } else if (iHashCode != 1842428796) {
                if (iHashCode == 2130809258 && strAsString.equals("HIDDEN")) {
                    builderNewBuilder.setLevel(ProtoBuf.VersionRequirement.Level.HIDDEN);
                }
            } else if (strAsString.equals("WARNING")) {
                builderNewBuilder.setLevel(ProtoBuf.VersionRequirement.Level.WARNING);
            }
        }
        EnumValue enumValue2 = (EnumValue) argumentsMapping.get(requireKotlinConstants.getVERSION_KIND());
        String strAsString2 = (enumValue2 == null || (enumEntryName = enumValue2.getEnumEntryName()) == null) ? null : enumEntryName.asString();
        if (strAsString2 != null) {
            int iHashCode2 = strAsString2.hashCode();
            if (iHashCode2 == -1625556271) {
                strAsString2.equals("LANGUAGE_VERSION");
            } else if (iHashCode2 != -1004593896) {
                if (iHashCode2 == 953725811 && strAsString2.equals("API_VERSION")) {
                    builderNewBuilder.setVersionKind(ProtoBuf.VersionRequirement.VersionKind.API_VERSION);
                }
            } else if (strAsString2.equals("COMPILER_VERSION")) {
                builderNewBuilder.setVersionKind(ProtoBuf.VersionRequirement.VersionKind.COMPILER_VERSION);
            }
        }
        ConstantValue<?> constantValue3 = argumentsMapping.get(requireKotlinConstants.getERROR_CODE());
        IntValue intValue = constantValue3 instanceof IntValue ? (IntValue) constantValue3 : null;
        Integer value = intValue != null ? intValue.getValue() : null;
        if (value != null && value.intValue() != -1) {
            builderNewBuilder.setErrorCode(value.intValue());
        }
        return builderNewBuilder;
    }

    private final List<Integer> serializeVersionRequirements(MutableVersionRequirementTable mutableVersionRequirementTable, List<? extends FirAnnotation> list) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            ClassId annotationClassId = FirAnnotationUtilsKt.toAnnotationClassId((FirAnnotation) obj, getSession());
            if (Intrinsics.areEqual(annotationClassId != null ? annotationClassId.asSingleFqName() : null, RequireKotlinConstants.INSTANCE.getFQ_NAME())) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ProtoBuf.VersionRequirement.Builder builderSerializeVersionRequirementFromRequireKotlin = serializeVersionRequirementFromRequireKotlin((FirAnnotation) it.next());
            if (builderSerializeVersionRequirementFromRequireKotlin != null) {
                arrayList2.add(builderSerializeVersionRequirementFromRequireKotlin);
            }
        }
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            arrayList3.add(Integer.valueOf(mutableVersionRequirementTable.get((ProtoBuf.VersionRequirement.Builder) it2.next())));
        }
        return arrayList3;
    }

    private final boolean shouldSetStableParameterNames(FirFunction function) {
        if (function == null || !function.getStatus().getHasStableParameterNames()) {
            return Intrinsics.areEqual(function != null ? function.getOrigin() : null, FirDeclarationOrigin.Delegated.INSTANCE);
        }
        return true;
    }

    private final boolean shouldWriteAnnotationParameterDefaultValues(BinaryVersion version) {
        if (version.getMajor() <= 2) {
            return version.getMajor() == 2 && version.getMinor() >= 2;
        }
        return true;
    }

    private final ProtoBuf.TypeAlias.Builder typeAliasProto(FirTypeAlias typeAlias) {
        Map<String, byte[]> mapFindMetadataExtensionsFor;
        FirSession session = getSession();
        try {
            ProtoBuf.TypeAlias.Builder builderNewBuilder = ProtoBuf.TypeAlias.newBuilder();
            FirElementSerializer firElementSerializerCreateChildSerializer = createChildSerializer(typeAlias);
            int typeAliasFlags = Flags.getTypeAliasFlags(!FirAnnotationUtilsKt.nonSourceAnnotations(typeAlias, getSession()).isEmpty() || this.extension.hasAdditionalAnnotations(typeAlias), ProtoEnumFlags.INSTANCE.visibility(normalizeVisibility(typeAlias)));
            if (typeAliasFlags != builderNewBuilder.getFlags()) {
                builderNewBuilder.setFlags(typeAliasFlags);
            }
            builderNewBuilder.setName(getSimpleNameIndex(typeAlias.getName()));
            for (FirTypeParameterRef firTypeParameterRef : typeAlias.getTypeParameters()) {
                if (firTypeParameterRef instanceof FirTypeParameter) {
                    builderNewBuilder.addTypeParameter(firElementSerializerCreateChildSerializer.typeParameterProto((FirTypeParameter) firTypeParameterRef));
                }
            }
            ConeClassLikeType expandedConeType = FirDeclarationUtilKt.getExpandedConeType(typeAlias);
            expandedConeType.getClass();
            if (useTypeTable()) {
                builderNewBuilder.setUnderlyingTypeId(typeId$default(firElementSerializerCreateChildSerializer, expandedConeType, false, true, 2, null));
            } else {
                builderNewBuilder.setUnderlyingType(typeProto$default(firElementSerializerCreateChildSerializer, expandedConeType, false, null, false, true, 14, null));
            }
            ConeClassLikeType coneClassLikeTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) this, expandedConeType);
            if (useTypeTable()) {
                builderNewBuilder.setExpandedTypeId(typeId$default(firElementSerializerCreateChildSerializer, coneClassLikeTypeFullyExpandedType, false, false, 6, null));
            } else {
                builderNewBuilder.setExpandedType(typeProto$default(firElementSerializerCreateChildSerializer, coneClassLikeTypeFullyExpandedType, false, null, false, false, 30, null));
            }
            MutableVersionRequirementTable mutableVersionRequirementTable = this.versionRequirementTable;
            if (mutableVersionRequirementTable != null) {
                builderNewBuilder.addAllVersionRequirement(serializeVersionRequirements(mutableVersionRequirementTable, typeAlias));
                if (firElementSerializerCreateChildSerializer.metDefinitelyNotNullType) {
                    builderNewBuilder.addVersionRequirement(INSTANCE.writeLanguageVersionRequirement(LanguageFeature.DefinitelyNonNullableTypes, this.versionRequirementTable));
                }
                if (FirDeclarationUtilKt.getClassId(typeAlias).isNestedClass()) {
                    builderNewBuilder.addVersionRequirement(INSTANCE.writeLanguageVersionRequirement(LanguageFeature.NestedTypeAliases, this.versionRequirementTable));
                }
            }
            this.extension.serializeTypeAlias(typeAlias, builderNewBuilder);
            FirAdditionalMetadataProvider additionalMetadataProvider = this.extension.getAdditionalMetadataProvider();
            if (additionalMetadataProvider != null && (mapFindMetadataExtensionsFor = additionalMetadataProvider.findMetadataExtensionsFor(typeAlias)) != null) {
                for (Map.Entry<String, byte[]> entry : mapFindMetadataExtensionsFor.entrySet()) {
                    String key = entry.getKey();
                    byte[] value = entry.getValue();
                    ProtoBuf.CompilerPluginData.Builder builderNewBuilder2 = ProtoBuf.CompilerPluginData.newBuilder();
                    builderNewBuilder2.setPluginId(getStringTable().getStringIndex(key));
                    builderNewBuilder2.setData(ByteString.copyFrom(value));
                    builderNewBuilder.addCompilerPluginData(builderNewBuilder2);
                }
            }
            return builderNewBuilder;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(typeAlias, th);
            wq6.a();
            return null;
        }
    }

    private final ProtoBuf.Type.Argument.Builder typeArgument(ConeTypeProjection typeProjection, boolean abbreviationOnly) {
        ProtoBuf.Type.Argument.Builder builderNewBuilder = ProtoBuf.Type.Argument.newBuilder();
        if (typeProjection instanceof ConeStarProjection) {
            builderNewBuilder.setProjection(ProtoBuf.Type.Argument.Projection.STAR);
        } else if (typeProjection instanceof ConeKotlinTypeProjection) {
            ProtoBuf.Type.Argument.Projection projection = ProtoEnumFlagsUtilsKt.projection(ProtoEnumFlags.INSTANCE, typeProjection.getKind());
            if (projection != builderNewBuilder.getProjection()) {
                builderNewBuilder.setProjection(projection);
            }
            if (useTypeTable()) {
                builderNewBuilder.setTypeId(typeId$default(this, ((ConeKotlinTypeProjection) typeProjection).getType(), false, abbreviationOnly, 2, null));
            } else {
                builderNewBuilder.setType(typeProto$default(this, ((ConeKotlinTypeProjection) typeProjection).getType(), false, null, false, abbreviationOnly, 14, null));
            }
        }
        builderNewBuilder.getClass();
        return builderNewBuilder;
    }

    public static /* synthetic */ int typeId$default(FirElementSerializer firElementSerializer, ConeKotlinType coneKotlinType, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return firElementSerializer.typeId(coneKotlinType, z, z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ProtoBuf.Type.Builder typeOrTypealiasProto(ConeKotlinType type, boolean toSuper, FirTypeRef correspondingTypeRef, boolean isDefinitelyNotNullType, boolean isAbbreviation, boolean abbreviationOnly) {
        List<FirTypeParameterRef> listEmptyList;
        LanguageVersion languageVersionFromVersionString;
        ProtoBuf.Type.Builder builderNewBuilder = ProtoBuf.Type.newBuilder();
        ArrayList arrayList = new ArrayList();
        if (type instanceof ConeDefinitelyNotNullType) {
            return typeProto$default(this, ((ConeDefinitelyNotNullType) type).getOriginal(), toSuper, correspondingTypeRef, true, false, 16, null);
        }
        if (type instanceof ConeErrorType) {
            builderNewBuilder.getClass();
            this.extension.serializeErrorType((ConeErrorType) type, builderNewBuilder);
            return builderNewBuilder;
        }
        if (type instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) type;
            ProtoBuf.Type.Builder builderTypeProto$default = typeProto$default(this, coneFlexibleType.getLowerBound(), false, null, false, false, 30, null);
            ProtoBuf.Type.Builder builderTypeProto$default2 = typeProto$default(this, coneFlexibleType.getUpperBound(), false, null, false, false, 30, null);
            this.extension.serializeFlexibleType(coneFlexibleType, builderTypeProto$default, builderTypeProto$default2);
            if (useTypeTable()) {
                builderTypeProto$default.setFlexibleUpperBoundId(this.typeTable.get(builderTypeProto$default2));
                return builderTypeProto$default;
            }
            builderTypeProto$default.setFlexibleUpperBound(builderTypeProto$default2);
            return builderTypeProto$default;
        }
        if (type instanceof ConeClassLikeType) {
            FunctionTypeKind functionTypeKindFunctionTypeKind$default = FunctionalTypeUtilsKt.functionTypeKind$default((ConeRigidType) type, getSession(), false, 2, (Object) null);
            if (!isAbbreviation && Intrinsics.areEqual(functionTypeKindFunctionTypeKind$default, FunctionTypeKind.SuspendFunction.INSTANCE)) {
                ProtoBuf.Type.Builder builderTypeProto$default3 = typeProto$default(this, SerializationUtilKt.suspendFunctionTypeToFunctionTypeWithContinuation(type, getSession(), StandardClassIds.INSTANCE.getContinuation()), false, null, false, false, 30, null);
                builderTypeProto$default3.setFlags(Flags.getTypeFlags(true, false));
                return builderTypeProto$default3;
            }
            if (!isAbbreviation && functionTypeKindFunctionTypeKind$default != null && !FunctionTypeKindKt.isBuiltin(functionTypeKindFunctionTypeKind$default) && (languageVersionFromVersionString = LanguageVersion.INSTANCE.fromVersionString(functionTypeKindFunctionTypeKind$default.getSerializeAsFunctionWithAnnotationUntil())) != null && this.languageVersionSettings.getLanguageVersion().compareTo(languageVersionFromVersionString) < 0) {
                return typeProto$default(this, FunctionalTypeUtilsKt.customFunctionTypeToSimpleFunctionType(type, getSession()), false, null, false, false, 30, null);
            }
            builderNewBuilder.getClass();
            fillFromPossiblyInnerType(builderNewBuilder, (ConeClassLikeType) type, abbreviationOnly);
        } else {
            if (!(type instanceof ConeTypeParameterType)) {
                if (type instanceof ConeIntersectionType) {
                    AbstractTypeApproximator abstractTypeApproximator = this.typeApproximator;
                    KotlinTypeMarker kotlinTypeMarkerApproximateToSuperType$default = toSuper ? AbstractTypeApproximator.approximateToSuperType$default(abstractTypeApproximator, type, TypeApproximatorConfiguration.PublicDeclaration.SaveAnonymousTypes.INSTANCE, (Map) null, 4, (Object) null) : AbstractTypeApproximator.approximateToSubType$default(abstractTypeApproximator, type, TypeApproximatorConfiguration.PublicDeclaration.SaveAnonymousTypes.INSTANCE, (Map) null, 4, (Object) null);
                    if (kotlinTypeMarkerApproximateToSuperType$default instanceof ConeKotlinType) {
                        Intrinsics.areEqual(kotlinTypeMarkerApproximateToSuperType$default, type);
                        return typeProto$default(this, (ConeKotlinType) kotlinTypeMarkerApproximateToSuperType$default, false, null, false, false, 30, null);
                    }
                    dt1.a("Approximation failed: ", ConeTypeUtilsKt.renderForDebugging(type));
                    return null;
                }
                if (type instanceof ConeIntegerLiteralType) {
                    sle.a("Integer literal types should not persist up to the serializer: ", ConeTypeUtilsKt.renderForDebugging(type));
                    return null;
                }
                if (type instanceof ConeCapturedType) {
                    sle.a("Captured types should not persist up to the serializer: ", ConeTypeUtilsKt.renderForDebugging(type));
                    return null;
                }
                pe1.a("Should not be here: ", type.getClass());
                return null;
            }
            FirTypeParameter firTypeParameter = (FirTypeParameter) ((ConeTypeParameterType) type).getLookupTag().getTypeParameterSymbol().getFir();
            FirDeclaration firDeclaration = this.currentDeclaration;
            FirMemberDeclaration firMemberDeclaration = firDeclaration instanceof FirMemberDeclaration ? (FirMemberDeclaration) firDeclaration : null;
            if (firMemberDeclaration == null || (listEmptyList = firMemberDeclaration.getTypeParameters()) == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            if (listEmptyList.contains(firTypeParameter)) {
                builderNewBuilder.setTypeParameterName(getSimpleNameIndex(firTypeParameter.getName()));
            } else {
                builderNewBuilder.setTypeParameter(getTypeParameterId(firTypeParameter));
            }
            if (isDefinitelyNotNullType) {
                this.metDefinitelyNotNullType = true;
                builderNewBuilder.setFlags(Flags.getTypeFlags(false, isDefinitelyNotNullType));
            }
        }
        ConeLookupTagBasedType coneLookupTagBasedType = (ConeLookupTagBasedType) type;
        if (coneLookupTagBasedType.getIsMarkedNullable() != builderNewBuilder.getNullable()) {
            builderNewBuilder.setNullable(coneLookupTagBasedType.getIsMarkedNullable());
        }
        ArrayList arrayList2 = new ArrayList();
        for (ParameterNameTypeAttribute parameterNameTypeAttribute : CollectionsKt.sortedWith(type.getAttributes(), new Comparator() { // from class: org.jetbrains.kotlin.fir.serialization.FirElementSerializer$typeOrTypealiasProto$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Boolean.valueOf(!(((ConeAttribute) t) instanceof CustomAnnotationTypeAttribute)), Boolean.valueOf(!(((ConeAttribute) t2) instanceof CustomAnnotationTypeAttribute)));
            }
        })) {
            if (parameterNameTypeAttribute instanceof CustomAnnotationTypeAttribute) {
                arrayList.addAll(FirAnnotationUtilsKt.nonSourceAnnotations((List<? extends FirAnnotation>) ((CustomAnnotationTypeAttribute) parameterNameTypeAttribute).getAnnotations(), getSession()));
            } else if (parameterNameTypeAttribute instanceof ParameterNameTypeAttribute) {
                arrayList.addAll(FirAnnotationUtilsKt.nonSourceAnnotations((List<? extends FirAnnotation>) parameterNameTypeAttribute.getAnnotations(), getSession()));
            } else if (CompilerConeAttributes.INSTANCE.getClassIdByCompilerAttributeKey().containsKey(parameterNameTypeAttribute.getKey())) {
                org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(arrayList, createAnnotationForCompilerDefinedTypeAttribute(parameterNameTypeAttribute));
            } else {
                arrayList2.add(parameterNameTypeAttribute);
            }
        }
        for (FirTypeAttributeExtension firTypeAttributeExtension : FirTypeAttributeExtensionKt.getTypeAttributeExtensions(FirExtensionServiceKt.getExtensionService(getSession()))) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(arrayList, firTypeAttributeExtension.convertAttributeToAnnotation((ConeAttribute) it.next()));
            }
        }
        this.extension.serializeTypeAnnotations(arrayList, builderNewBuilder);
        return builderNewBuilder;
    }

    public static /* synthetic */ ProtoBuf.Type.Builder typeOrTypealiasProto$default(FirElementSerializer firElementSerializer, ConeKotlinType coneKotlinType, boolean z, FirTypeRef firTypeRef, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        if ((i & 16) != 0) {
            z3 = false;
        }
        if ((i & 32) != 0) {
            z4 = false;
        }
        return firElementSerializer.typeOrTypealiasProto(coneKotlinType, z, firTypeRef, z2, z3, z4);
    }

    private final ProtoBuf.TypeParameter.Builder typeParameterProto(FirTypeParameter typeParameter) {
        FirSession session = getSession();
        try {
            ProtoBuf.TypeParameter.Builder builderNewBuilder = ProtoBuf.TypeParameter.newBuilder();
            builderNewBuilder.setId(getTypeParameterId(typeParameter));
            builderNewBuilder.setName(getSimpleNameIndex(typeParameter.getName()));
            if (typeParameter.getIsReified() != builderNewBuilder.getReified()) {
                builderNewBuilder.setReified(typeParameter.getIsReified());
            }
            ProtoBuf.TypeParameter.Variance variance = ProtoEnumFlags.INSTANCE.variance(typeParameter.getVariance());
            if (variance != builderNewBuilder.getVariance()) {
                builderNewBuilder.setVariance(variance);
            }
            this.extension.serializeTypeParameter(typeParameter, builderNewBuilder);
            List<FirTypeRef> bounds = typeParameter.getBounds();
            if (bounds.size() != 1 || !(CollectionsKt.single(bounds) instanceof FirImplicitNullableAnyTypeRef)) {
                for (FirTypeRef firTypeRef : bounds) {
                    if (useTypeTable()) {
                        builderNewBuilder.addUpperBoundId(typeId$default(this, firTypeRef, false, 2, null));
                    } else {
                        builderNewBuilder.addUpperBound(typeProto$default(this, firTypeRef, false, 2, null));
                    }
                }
            }
            return builderNewBuilder;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(typeParameter, th);
            wq6.a();
            return null;
        }
    }

    private final ProtoBuf.Type.Builder typeProto(ConeKotlinType type, boolean toSuper, FirTypeRef correspondingTypeRef, boolean isDefinitelyNotNullType, boolean abbreviationOnly) {
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(this, type);
        ConeKotlinType abbreviatedTypeOrSelf = AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(type);
        ConeKotlinType coneKotlinType = !abbreviationOnly ? coneKotlinTypeFullyExpandedType : abbreviatedTypeOrSelf;
        ProtoBuf.Type.Builder builderTypeOrTypealiasProto$default = typeOrTypealiasProto$default(this, coneKotlinType, toSuper, correspondingTypeRef, isDefinitelyNotNullType, false, abbreviationOnly, 16, null);
        if (!Intrinsics.areEqual(abbreviatedTypeOrSelf, coneKotlinType) && !builderTypeOrTypealiasProto$default.hasAbbreviatedType() && !getContainsCapturedTypes(abbreviatedTypeOrSelf)) {
            ProtoBuf.Type.Builder builderTypeOrTypealiasProto = typeOrTypealiasProto(abbreviatedTypeOrSelf, toSuper, correspondingTypeRef, isDefinitelyNotNullType, true, false);
            if (useTypeTable()) {
                builderTypeOrTypealiasProto$default.setAbbreviatedTypeId(this.typeTable.get(builderTypeOrTypealiasProto));
                return builderTypeOrTypealiasProto$default;
            }
            builderTypeOrTypealiasProto$default.setAbbreviatedType(builderTypeOrTypealiasProto);
        }
        return builderTypeOrTypealiasProto$default;
    }

    public static /* synthetic */ ProtoBuf.Type.Builder typeProto$default(FirElementSerializer firElementSerializer, ConeKotlinType coneKotlinType, boolean z, FirTypeRef firTypeRef, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            firTypeRef = null;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        if ((i & 16) != 0) {
            z3 = false;
        }
        return firElementSerializer.typeProto(coneKotlinType, z, firTypeRef, z2, z3);
    }

    private final boolean useTypeTable() {
        return this.extension.shouldUseTypeTable();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final ProtoBuf.ValueParameter.Builder valueParameterProto(FirValueParameter parameter, boolean declaresDefaultValue) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirElementSerializer firElementSerializer;
        FirValueParameter firValueParameter;
        FirTypeRef delegatedTypeRef;
        ConeKotlinType coneTypeOrNull;
        ProtoBuf.ValueParameter.Builder builderNewBuilder = ProtoBuf.ValueParameter.newBuilder();
        int valueParameterFlags = Flags.getValueParameterFlags(!FirAnnotationUtilsKt.nonSourceAnnotations(parameter, getSession()).isEmpty() || this.extension.hasAdditionalAnnotations(parameter), declaresDefaultValue, parameter.getIsCrossinline(), parameter.getIsNoinline());
        if (valueParameterFlags != builderNewBuilder.getFlags()) {
            builderNewBuilder.setFlags(valueParameterFlags);
        }
        builderNewBuilder.setName(getSimpleNameIndex(parameter.getName()));
        if (useTypeTable()) {
            builderNewBuilder.setTypeId(typeId$default(this, parameter.getReturnTypeRef(), false, 2, null));
        } else {
            builderNewBuilder.setType(typeProto$default(this, parameter.getReturnTypeRef(), false, 2, null));
        }
        if (parameter.getIsVararg()) {
            FirResolvedTypeRef returnTypeRef = parameter.getReturnTypeRef();
            FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
            ConeAttributes attributes = (firResolvedTypeRef == null || (delegatedTypeRef = firResolvedTypeRef.getDelegatedTypeRef()) == null || (coneTypeOrNull = FirTypeUtilsKt.getConeTypeOrNull(delegatedTypeRef)) == null) ? null : coneTypeOrNull.getAttributes();
            ConeKotlinType coneKotlinTypeVarargElementType = ArrayUtilsKt.varargElementType(FirTypeUtilsKt.getConeType(parameter.getReturnTypeRef()));
            if (attributes != null) {
                attributes.getClass();
                coneKotlinTypeVarargElementType = TypeUtilsKt.withAttributes(coneKotlinTypeVarargElementType, attributes);
            }
            ConeKotlinType coneKotlinType = coneKotlinTypeVarargElementType;
            if (useTypeTable()) {
                firElementSerializer = this;
                builderNewBuilder.setVarargElementTypeId(typeId$default(firElementSerializer, coneKotlinType, false, false, 6, null));
            } else {
                firElementSerializer = this;
                builderNewBuilder.setVarargElementType(typeProto$default(firElementSerializer, coneKotlinType, false, null, false, false, 30, null));
            }
        } else {
            firElementSerializer = this;
        }
        if (firElementSerializer.shouldWriteAnnotationParameterDefaultValues(firElementSerializer.extension.getMetadataVersion()) && DeclarationUtilsKt.isAnnotationConstructor(parameter.getContainingDeclarationSymbol(), firElementSerializer.getSession())) {
            firValueParameter = parameter;
            FirEvaluatorResult firEvaluatorResultEvaluateParameterDefaultValue$default = FirExpressionEvaluator.evaluateParameterDefaultValue$default(FirExpressionEvaluator.INSTANCE, firValueParameter, firElementSerializer.getSession(), null, 4, null);
            FirEvaluatorResult.Evaluated evaluated = firEvaluatorResultEvaluateParameterDefaultValue$default instanceof FirEvaluatorResult.Evaluated ? (FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluateParameterDefaultValue$default : null;
            FirExpression firExpression = (FirExpression) (evaluated != null ? evaluated.getResult() : null);
            if (firExpression != null) {
                ConstantValue<?> constantValueEvaluateToAnnotationValue = firExpression instanceof FirAnnotation ? FirToConstantValueTransformerKt.evaluateToAnnotationValue(firElementSerializer, (FirAnnotation) firExpression) : FirToConstantValueTransformerKt.toConstantValueImpl(firElementSerializer, firExpression);
                ConstantValue<?> constantValue = constantValueEvaluateToAnnotationValue != null ? constantValueEvaluateToAnnotationValue : null;
                if (constantValue != null) {
                    builderNewBuilder.setAnnotationParameterDefaultValue(firElementSerializer.extension.getAnnotationSerializer().valueProto$org_jetbrains_kotlin_fir_serialization(constantValue));
                }
            }
        } else {
            firValueParameter = parameter;
        }
        firElementSerializer.extension.serializeValueParameter(firValueParameter, builderNewBuilder);
        return builderNewBuilder;
    }

    private final int writeVersionRequirement(MutableVersionRequirementTable mutableVersionRequirementTable, LanguageFeature languageFeature) {
        return INSTANCE.writeLanguageVersionRequirement(languageFeature, mutableVersionRequirementTable);
    }

    public final ProtoBuf.Class.Builder classProto(FirClass klass, FirFile containingFile) {
        klass.getClass();
        return containingFile == null ? classProtoImpl(klass) : classProtoImpl(klass);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<FirClassifierSymbol<?>> computeNestedClassifiersForClass(FirClassSymbol<?> classSymbol) {
        classSymbol.getClass();
        FirNestedClassifierScope firNestedClassifierScopeNestedClassifierScope = FirDeclaredMemberScopeProviderKt.nestedClassifierScope(getSession(), (FirClass) classSymbol.getFir());
        if (firNestedClassifierScopeNestedClassifierScope == null) {
            return CollectionsKt.emptyList();
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List<FirDeclaration> declarations = ((FirClass) classSymbol.getFir()).getDeclarations();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declarations) {
            if (obj instanceof FirClassLikeDeclaration) {
                arrayList.add(obj);
            }
        }
        final Map mapMapToIndex = org.jetbrains.kotlin.utils.CollectionsKt.mapToIndex(arrayList);
        Set<Name> classifierNames = firNestedClassifierScopeNestedClassifierScope.getClassifierNames();
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = classifierNames.iterator();
        while (it.hasNext()) {
            FirClassifierSymbol<?> singleClassifier = FirScopeKt.getSingleClassifier(firNestedClassifierScopeNestedClassifierScope, (Name) it.next());
            FirClassLikeDeclaration firClassLikeDeclaration = (FirClassLikeDeclaration) (singleClassifier != null ? singleClassifier.getFir() : null);
            if (firClassLikeDeclaration != null) {
                arrayList2.add(firClassLikeDeclaration);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj2 : arrayList2) {
            if (mapMapToIndex.containsKey((FirClassLikeDeclaration) obj2)) {
                arrayList3.add(obj2);
            } else {
                arrayList4.add(obj2);
            }
        }
        Pair pair = new Pair(arrayList3, arrayList4);
        List list = (List) pair.component1();
        List list2 = (List) pair.component2();
        List listSortedWith = CollectionsKt.sortedWith(list, new Comparator() { // from class: org.jetbrains.kotlin.fir.serialization.FirElementSerializer$computeNestedClassifiersForClass$lambda$0$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues((Integer) MapsKt.getValue(mapMapToIndex, (FirClassLikeDeclaration) t), (Integer) MapsKt.getValue(mapMapToIndex, (FirClassLikeDeclaration) t2));
            }
        });
        List list3 = listCreateListBuilder;
        Iterator it2 = listSortedWith.iterator();
        while (it2.hasNext()) {
            list3.add(((FirClassLikeDeclaration) it2.next()).getSymbol());
        }
        Iterator it3 = CollectionsKt.sortedWith(list2, FirMemberDeclarationComparator.INSTANCE).iterator();
        while (it3.hasNext()) {
            list3.add(((FirClassLikeDeclaration) it3.next()).getSymbol());
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ProtoBuf.Function.Builder functionProto(FirFunction function) {
        Visibility visibilityNormalizeVisibility;
        Modality modality;
        Name name;
        Map<String, byte[]> mapFindMetadataExtensionsFor;
        ProtoBuf.TypeTable typeTableSerialize;
        FirDeclarationStatus status;
        function.getClass();
        FirSession session = getSession();
        try {
            ProtoBuf.Function.Builder builderNewBuilder = ProtoBuf.Function.newBuilder();
            FirNamedFunction firNamedFunction = function instanceof FirNamedFunction ? (FirNamedFunction) function : null;
            FirElementSerializer firElementSerializerCreateChildSerializer = createChildSerializer(function);
            int i = 0;
            boolean z = !FirAnnotationUtilsKt.nonSourceAnnotations(function, getSession()).isEmpty() || this.extension.hasAdditionalAnnotations(function);
            ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
            if (firNamedFunction == null || (visibilityNormalizeVisibility = normalizeVisibility(firNamedFunction)) == null) {
                visibilityNormalizeVisibility = Visibilities.Local.INSTANCE;
            }
            ProtoBuf.Visibility visibility = protoEnumFlags.visibility(visibilityNormalizeVisibility);
            if (firNamedFunction == null || (modality = firNamedFunction.getStatus().getModality()) == null) {
                modality = Modality.FINAL;
            }
            int functionFlags = Flags.getFunctionFlags(z, visibility, protoEnumFlags.modality(modality), memberKind(function), firNamedFunction != null && firNamedFunction.getStatus().isOperator(), firNamedFunction != null && firNamedFunction.getStatus().isInfix(), firNamedFunction != null && firNamedFunction.getStatus().isInline(), firNamedFunction != null && firNamedFunction.getStatus().isTailRec(), firNamedFunction != null && firNamedFunction.getStatus().isExternal(), function.getStatus().isSuspend(), firNamedFunction != null && firNamedFunction.getStatus().isExpect(), shouldSetStableParameterNames(function), function.getStatus().isStatic(), protoEnumFlags.returnValueStatus((firNamedFunction == null || (status = firNamedFunction.getStatus()) == null) ? null : status.getReturnValueStatus()));
            if (functionFlags != builderNewBuilder.getFlags()) {
                builderNewBuilder.setFlags(functionFlags);
            }
            if (function instanceof FirNamedFunction) {
                name = ((FirNamedFunction) function).getName();
            } else {
                if (!(function instanceof FirAnonymousFunction)) {
                    throw new AssertionError("Unsupported function: " + UtilsKt.render(function));
                }
                name = ((FirAnonymousFunction) function).getIsLambda() ? SpecialNames.ANONYMOUS : SpecialNames.NO_NAME_PROVIDED;
            }
            builderNewBuilder.setName(getSimpleNameIndex(name));
            if (useTypeTable()) {
                builderNewBuilder.setReturnTypeId(firElementSerializerCreateChildSerializer.typeId(function.getReturnTypeRef(), true));
            } else {
                builderNewBuilder.setReturnType(firElementSerializerCreateChildSerializer.typeProto(function.getReturnTypeRef(), true));
            }
            Iterator<FirTypeParameter> it = ((FirTypeParametersOwner) function).getTypeParameters().iterator();
            while (it.hasNext()) {
                builderNewBuilder.addTypeParameter(firElementSerializerCreateChildSerializer.typeParameterProto(it.next()));
            }
            for (FirValueParameter firValueParameter : function.getContextParameters()) {
                FirTypeRef returnTypeRef = firValueParameter.getReturnTypeRef();
                if (useTypeTable()) {
                    builderNewBuilder.addContextReceiverTypeId(typeId$default(firElementSerializerCreateChildSerializer, returnTypeRef, false, 2, null));
                } else {
                    builderNewBuilder.addContextReceiverType(typeProto$default(firElementSerializerCreateChildSerializer, firValueParameter.getReturnTypeRef(), false, 2, null));
                }
                builderNewBuilder.addContextParameter(firElementSerializerCreateChildSerializer.valueParameterProto(firValueParameter, false));
            }
            FirReceiverParameter receiverParameter = function.getReceiverParameter();
            if (receiverParameter != null) {
                FirTypeRef typeRef = receiverParameter.getTypeRef();
                if (useTypeTable()) {
                    builderNewBuilder.setReceiverTypeId(typeId$default(firElementSerializerCreateChildSerializer, typeRef, false, 2, null));
                } else {
                    builderNewBuilder.setReceiverType(typeProto$default(firElementSerializerCreateChildSerializer, typeRef, false, 2, null));
                }
            }
            Iterator<T> it2 = function.getValueParameters().iterator();
            while (it2.hasNext()) {
                builderNewBuilder.addValueParameter(firElementSerializerCreateChildSerializer.valueParameterProto((FirValueParameter) it2.next(), i, function));
                i++;
            }
            this.contractSerializer.serializeContractOfFunctionIfAny(function, builderNewBuilder, firElementSerializerCreateChildSerializer);
            this.extension.serializeFunction(function, builderNewBuilder, this.versionRequirementTable, firElementSerializerCreateChildSerializer);
            if (this.serializeTypeTableToFunction && (typeTableSerialize = this.typeTable.serialize()) != null) {
                builderNewBuilder.setTypeTable(typeTableSerialize);
            }
            MutableVersionRequirementTable mutableVersionRequirementTable = this.versionRequirementTable;
            if (mutableVersionRequirementTable != null) {
                builderNewBuilder.addAllVersionRequirement(serializeVersionRequirements(mutableVersionRequirementTable, function));
                if (firElementSerializerCreateChildSerializer.metDefinitelyNotNullType) {
                    builderNewBuilder.addVersionRequirement(writeVersionRequirement(mutableVersionRequirementTable, LanguageFeature.DefinitelyNonNullableTypes));
                }
            }
            FirAdditionalMetadataProvider additionalMetadataProvider = this.extension.getAdditionalMetadataProvider();
            if (additionalMetadataProvider != null && (mapFindMetadataExtensionsFor = additionalMetadataProvider.findMetadataExtensionsFor(function)) != null) {
                for (Map.Entry<String, byte[]> entry : mapFindMetadataExtensionsFor.entrySet()) {
                    String key = entry.getKey();
                    byte[] value = entry.getValue();
                    ProtoBuf.CompilerPluginData.Builder builderNewBuilder2 = ProtoBuf.CompilerPluginData.newBuilder();
                    builderNewBuilder2.setPluginId(getStringTable().getStringIndex(key));
                    builderNewBuilder2.setData(ByteString.copyFrom(value));
                    builderNewBuilder.addCompilerPluginData(builderNewBuilder2);
                }
            }
            return builderNewBuilder;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(function, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    public final FirElementAwareStringTable getStringTable() {
        return this.extension.getStringTable();
    }

    public final MutableTypeTable getTypeTable() {
        return this.typeTable;
    }

    public final ProtoBuf.Package.Builder packagePartProto(FirFile file, Set<? extends FirDeclaration> actualizedExpectDeclarations) {
        file.getClass();
        ProtoBuf.Package.Builder builderNewBuilder = ProtoBuf.Package.newBuilder();
        for (FirDeclaration firDeclaration : file.getDeclarations()) {
            builderNewBuilder.getClass();
            addDeclarationProto(builderNewBuilder, firDeclaration, actualizedExpectDeclarations, new Function1() { // from class: a55
                public final Object invoke(Object obj) {
                    return FirElementSerializer.b((FirDeclaration) obj);
                }
            });
        }
        FqName packageFqName = UtilsKt.getPackageFqName(file);
        builderNewBuilder.getClass();
        return finalizePackagePartProto(packageFqName, builderNewBuilder, actualizedExpectDeclarations);
    }

    public final ProtoBuf.Property.Builder propertyProto(FirProperty property) {
        Modality modality;
        int i;
        boolean z;
        boolean z2;
        Map<String, byte[]> mapFindMetadataExtensionsFor;
        FirBackingField backingField;
        FirBackingField backingField2;
        List<FirAnnotation> listNonSourceAnnotations;
        property.getClass();
        FirSession session = getSession();
        try {
            ProtoBuf.Property.Builder builderNewBuilder = ProtoBuf.Property.newBuilder();
            FirElementSerializer firElementSerializerCreateChildSerializer = createChildSerializer(property);
            boolean z3 = !FirAnnotationUtilsKt.nonSourceAnnotations(property, getSession()).isEmpty() || !((backingField = property.getBackingField()) == null || (listNonSourceAnnotations = FirAnnotationUtilsKt.nonSourceAnnotations(backingField, getSession())) == null || !(listNonSourceAnnotations.isEmpty() ^ true)) || this.extension.hasAdditionalAnnotations(property) || ((backingField2 = property.getBackingField()) != null && this.extension.hasAdditionalAnnotations(backingField2));
            Modality modality2 = property.getStatus().getModality();
            modality2.getClass();
            ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
            int accessorFlags = Flags.getAccessorFlags(z3, protoEnumFlags.visibility(normalizeVisibility(property)), protoEnumFlags.modality(modality2), false, false, false);
            FirPropertyAccessor getter = property.getGetter();
            if (getter != null) {
                modality = modality2;
                i = accessorFlags;
            } else if (Intrinsics.areEqual(property.getOrigin(), FirDeclarationOrigin.Delegated.INSTANCE)) {
                modality = modality2;
                i = accessorFlags;
                getter = new FirDefaultPropertyGetter(null, property.getModuleData(), property.getOrigin(), property.getReturnTypeRef(), property.getStatus().getVisibility(), property.getSymbol(), modality, null, false, false, null, null, null, 8064, null);
            } else {
                modality = modality2;
                i = accessorFlags;
                getter = null;
            }
            if (getter != null) {
                int accessorFlags2 = getAccessorFlags(getter, property);
                if (accessorFlags2 != i) {
                    builderNewBuilder.setGetterFlags(accessorFlags2);
                }
                ProtoBuf.Contract.Builder builderBuildAccessorContractProtoIfAny = this.contractSerializer.buildAccessorContractProtoIfAny(getter, firElementSerializerCreateChildSerializer);
                if (builderBuildAccessorContractProtoIfAny != null) {
                    builderNewBuilder.setGetterContract(builderBuildAccessorContractProtoIfAny);
                }
                z = true;
            } else {
                z = false;
            }
            FirPropertyAccessor setter = property.getSetter();
            if (setter == null) {
                setter = (Intrinsics.areEqual(property.getOrigin(), FirDeclarationOrigin.Delegated.INSTANCE) && property.getIsVar()) ? new FirDefaultPropertySetter(null, property.getModuleData(), property.getOrigin(), property.getReturnTypeRef(), property.getStatus().getVisibility(), property.getSymbol(), modality, null, false, false, null, null, null, null, null, 32640, null) : null;
            }
            if (setter != null) {
                int accessorFlags3 = getAccessorFlags(setter, property);
                if (accessorFlags3 != i) {
                    builderNewBuilder.setSetterFlags(accessorFlags3);
                }
                if (Flags.IS_NOT_DEFAULT.get(accessorFlags3).booleanValue()) {
                    FirElementSerializer firElementSerializerCreateChildSerializer2 = firElementSerializerCreateChildSerializer.createChildSerializer(setter);
                    Iterator<T> it = setter.getValueParameters().iterator();
                    int i2 = 0;
                    while (it.hasNext()) {
                        builderNewBuilder.setSetterValueParameter(firElementSerializerCreateChildSerializer2.valueParameterProto((FirValueParameter) it.next(), i2, setter));
                        i2++;
                    }
                }
                ProtoBuf.Contract.Builder builderBuildAccessorContractProtoIfAny2 = this.contractSerializer.buildAccessorContractProtoIfAny(setter, firElementSerializerCreateChildSerializer);
                if (builderBuildAccessorContractProtoIfAny2 != null) {
                    builderNewBuilder.setSetterContract(builderBuildAccessorContractProtoIfAny2);
                }
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z4 = property.getStatus().isConst() || (!property.getIsVar() && FirConstChecksKt.canBeUsedForConstVal(TypeExpansionUtilsKt.fullyExpandedType(this, FirTypeUtilsKt.getConeType(property.getReturnTypeRef()))) && FirToConstantValueTransformerKt.hasConstantValue(property.getSymbol().getResolvedInitializer(), getSession()));
            ProtoEnumFlags protoEnumFlags2 = ProtoEnumFlags.INSTANCE;
            int propertyFlags = Flags.getPropertyFlags(z3, protoEnumFlags2.visibility(normalizeVisibility(property)), protoEnumFlags2.modality(modality), memberKind(property), property.getIsVar(), z, z2, z4, property.getStatus().isConst(), property.getStatus().isLateInit(), property.getStatus().isExternal(), property.getDelegateFieldSymbol() != null, property.getStatus().isExpect(), property.getStatus().isStatic(), protoEnumFlags2.returnValueStatus(property.getStatus().getReturnValueStatus()));
            if (propertyFlags != builderNewBuilder.getFlags()) {
                builderNewBuilder.setFlags(propertyFlags);
            }
            builderNewBuilder.setName(getSimpleNameIndex(property.getName()));
            if (useTypeTable()) {
                builderNewBuilder.setReturnTypeId(firElementSerializerCreateChildSerializer.typeId(property.getReturnTypeRef(), true));
            } else {
                builderNewBuilder.setReturnType(firElementSerializerCreateChildSerializer.typeProto(property.getReturnTypeRef(), true));
            }
            Iterator<FirTypeParameter> it2 = property.getTypeParameters().iterator();
            while (it2.hasNext()) {
                builderNewBuilder.addTypeParameter(firElementSerializerCreateChildSerializer.typeParameterProto(it2.next()));
            }
            for (FirValueParameter firValueParameter : property.getContextParameters()) {
                FirTypeRef returnTypeRef = firValueParameter.getReturnTypeRef();
                if (useTypeTable()) {
                    builderNewBuilder.addContextReceiverTypeId(typeId$default(firElementSerializerCreateChildSerializer, returnTypeRef, false, 2, null));
                } else {
                    builderNewBuilder.addContextReceiverType(typeProto$default(firElementSerializerCreateChildSerializer, firValueParameter.getReturnTypeRef(), false, 2, null));
                }
                builderNewBuilder.addContextParameter(firElementSerializerCreateChildSerializer.valueParameterProto(firValueParameter, false));
            }
            FirReceiverParameter receiverParameter = property.getReceiverParameter();
            if (receiverParameter != null) {
                FirTypeRef typeRef = receiverParameter.getTypeRef();
                if (useTypeTable()) {
                    builderNewBuilder.setReceiverTypeId(typeId$default(firElementSerializerCreateChildSerializer, typeRef, false, 2, null));
                } else {
                    builderNewBuilder.setReceiverType(typeProto$default(firElementSerializerCreateChildSerializer, typeRef, false, 2, null));
                }
            }
            MutableVersionRequirementTable mutableVersionRequirementTable = this.versionRequirementTable;
            if (mutableVersionRequirementTable != null) {
                builderNewBuilder.addAllVersionRequirement(serializeVersionRequirements(mutableVersionRequirementTable, property));
                if (firElementSerializerCreateChildSerializer.metDefinitelyNotNullType) {
                    builderNewBuilder.addVersionRequirement(writeVersionRequirement(mutableVersionRequirementTable, LanguageFeature.DefinitelyNonNullableTypes));
                }
            }
            this.extension.serializeProperty(property, builderNewBuilder, this.versionRequirementTable, firElementSerializerCreateChildSerializer);
            FirAdditionalMetadataProvider additionalMetadataProvider = this.extension.getAdditionalMetadataProvider();
            if (additionalMetadataProvider != null && (mapFindMetadataExtensionsFor = additionalMetadataProvider.findMetadataExtensionsFor(property)) != null) {
                for (Map.Entry<String, byte[]> entry : mapFindMetadataExtensionsFor.entrySet()) {
                    String key = entry.getKey();
                    byte[] value = entry.getValue();
                    ProtoBuf.CompilerPluginData.Builder builderNewBuilder2 = ProtoBuf.CompilerPluginData.newBuilder();
                    builderNewBuilder2.setPluginId(getStringTable().getStringIndex(key));
                    builderNewBuilder2.setData(ByteString.copyFrom(value));
                    builderNewBuilder.addCompilerPluginData(builderNewBuilder2);
                }
            }
            return builderNewBuilder;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(property, th);
            wq6.a();
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:64:0x00c1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:76:0x0067 A[SYNTHETIC] */
    public final ProtoBuf.Class.Builder scriptProto(FirScript script) {
        ProtoBuf.Property.Builder builderPropertyProto;
        ProtoBuf.Function.Builder builderFunctionProto;
        script.getClass();
        FirSession session = getSession();
        try {
            final ProtoBuf.Class.Builder builderNewBuilder = ProtoBuf.Class.newBuilder();
            boolean zHasAdditionalAnnotations = this.extension.hasAdditionalAnnotations(script);
            ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
            int classFlags = Flags.getClassFlags(zHasAdditionalAnnotations, protoEnumFlags.visibility(Visibilities.Public.INSTANCE), protoEnumFlags.modality(Modality.FINAL), protoEnumFlags.classKind(ClassKind.CLASS, false), false, false, false, false, false, false, false);
            if (classFlags != builderNewBuilder.getFlags()) {
                builderNewBuilder.setFlags(classFlags);
            }
            builderNewBuilder.setFqName(getClassifierId(FirElementSerializerKt.scriptClassId(script)));
            FirScriptDeclarationsScope firScriptDeclarationsScope = new FirScriptDeclarationsScope(getSession(), script);
            final List listCreateListBuilder = CollectionsKt.createListBuilder();
            FirContainingNamesAwareScopeKt.processAllCallables(firScriptDeclarationsScope, new Function1() { // from class: f55
                public final Object invoke(Object obj) {
                    return FirElementSerializer.scriptProto$lambda$0$0$0(listCreateListBuilder, (FirCallableSymbol) obj);
                }
            });
            for (FirCallableDeclaration firCallableDeclaration : CollectionsKt.build(listCreateListBuilder)) {
                if (firCallableDeclaration instanceof FirProperty) {
                    if (Intrinsics.areEqual(((FirProperty) firCallableDeclaration).getOrigin(), FirDeclarationOrigin.ScriptCustomization.ResultProperty.INSTANCE)) {
                        FirTypeRef returnTypeRef = ((FirProperty) firCallableDeclaration).getReturnTypeRef();
                        if (!FirTypeUtilsKt.isUnit(returnTypeRef) && !FirTypeUtilsKt.isNothing(returnTypeRef) && !FirTypeUtilsKt.isNullableNothing(returnTypeRef)) {
                            builderPropertyProto = propertyProto((FirProperty) firCallableDeclaration);
                            if (builderPropertyProto != null) {
                                builderNewBuilder.addProperty(builderPropertyProto);
                            }
                        }
                    } else if (!Intrinsics.areEqual(((FirProperty) firCallableDeclaration).getName(), SpecialNames.UNDERSCORE_FOR_UNUSED_VAR) || DestructuringDeclarationAttributesKt.getDestructuringDeclarationContainerVariable((FirProperty) firCallableDeclaration) == null) {
                        builderPropertyProto = propertyProto((FirProperty) firCallableDeclaration);
                        if (builderPropertyProto != null) {
                            builderNewBuilder.addProperty(builderPropertyProto);
                        }
                    }
                } else if ((firCallableDeclaration instanceof FirNamedFunction) && (builderFunctionProto = functionProto((FirFunction) firCallableDeclaration)) != null) {
                    builderNewBuilder.addFunction(builderFunctionProto);
                }
            }
            for (Name name : firScriptDeclarationsScope.getClassifierNames()) {
                final Function1 function1 = new Function1() { // from class: g55
                    public final Object invoke(Object obj) {
                        return FirElementSerializer.scriptProto$lambda$0$4$0(builderNewBuilder, this, (FirClassifierSymbol) obj);
                    }
                };
                firScriptDeclarationsScope.processClassifiersByNameWithSubstitution(name, new Function2<FirClassifierSymbol<?>, ConeSubstitutor, Unit>() { // from class: org.jetbrains.kotlin.fir.serialization.FirElementSerializer$scriptProto$lambda$0$4$$inlined$processClassifiersByName$1
                    public final void invoke(FirClassifierSymbol<?> firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
                        firClassifierSymbol.getClass();
                        coneSubstitutor.getClass();
                        function1.invoke(firClassifierSymbol);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((FirClassifierSymbol<?>) obj, (ConeSubstitutor) obj2);
                        return Unit.INSTANCE;
                    }
                });
            }
            MutableVersionRequirementTable mutableVersionRequirementTable = this.versionRequirementTable;
            if (mutableVersionRequirementTable == null) {
                throw new IllegalStateException(("Version requirements must be serialized for scripts: " + UtilsKt.render(script)).toString());
            }
            builderNewBuilder.addAllVersionRequirement(serializeVersionRequirements(mutableVersionRequirementTable, script));
            this.extension.serializeScript(script, builderNewBuilder, this.versionRequirementTable, this);
            if (this.metDefinitelyNotNullType) {
                builderNewBuilder.addVersionRequirement(INSTANCE.writeLanguageVersionRequirement(LanguageFeature.DefinitelyNonNullableTypes, this.versionRequirementTable));
            }
            ProtoBuf.TypeTable typeTableSerialize = this.typeTable.serialize();
            if (typeTableSerialize != null) {
                builderNewBuilder.setTypeTable(typeTableSerialize);
            }
            ProtoBuf.VersionRequirementTable versionRequirementTableSerialize = this.versionRequirementTable.serialize();
            if (versionRequirementTableSerialize != null) {
                builderNewBuilder.setVersionRequirementTable(versionRequirementTableSerialize);
            }
            return builderNewBuilder;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(script, th);
            wq6.a();
            return null;
        }
    }

    public final ProtoBuf.Class.Builder snippetProto(FirReplSnippet snippet) {
        snippet.getClass();
        FirSession session = getSession();
        try {
            if (this.versionRequirementTable != null) {
                ProtoBuf.Class.Builder builderClassProtoImpl = classProtoImpl(snippet.getSnippetClass());
                this.extension.serializeSnippet(snippet, builderClassProtoImpl, this.versionRequirementTable, this);
                return builderClassProtoImpl;
            }
            throw new IllegalStateException(("Version requirements must be serialized for snippets: " + UtilsKt.render(snippet)).toString());
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(snippet, th);
            wq6.a();
            return null;
        }
    }

    public final int typeId(FirTypeRef typeRef, boolean toSuper) {
        typeRef.getClass();
        if (typeRef instanceof FirResolvedTypeRef) {
            return typeId$default(this, ((FirResolvedTypeRef) typeRef).getConeType(), toSuper, false, 4, null);
        }
        return -1;
    }

    @Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J>\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007b\u0002\b\u0012J4\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007b\u0002\b\u0012JP\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007b\u0002\b\u0012JF\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007b\u0002\b\u0012JF\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007b\u0002\b\u0012J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J0\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)2\u0006\u0010\"\u001a\u00020#H\u0002¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer$Companion;", Argument.Delimiters.none, "<init>", "()V", "createTopLevel", "Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "extension", "Lorg/jetbrains/kotlin/fir/serialization/FirSerializerExtension;", "typeApproximator", "Lorg/jetbrains/kotlin/types/AbstractTypeApproximator;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "produceHeaderKlib", Argument.Delimiters.none, "Lkotlin/jvm/JvmStatic;", "createForLambda", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "parentSerializer", "createForScript", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "createForSnippet", "snippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "writeLanguageVersionRequirement", Argument.Delimiters.none, "languageFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "versionRequirementTable", "Lorg/jetbrains/kotlin/metadata/serialization/MutableVersionRequirementTable;", "writeVersionRequirement", "major", "minor", "patch", "versionKind", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$VersionRequirement$VersionKind;", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ FirElementSerializer createTopLevel$default(Companion companion, FirSession firSession, ScopeSession scopeSession, FirSerializerExtension firSerializerExtension, AbstractTypeApproximator abstractTypeApproximator, LanguageVersionSettings languageVersionSettings, boolean z, int i, Object obj) {
            if ((i & 32) != 0) {
                z = false;
            }
            return companion.createTopLevel(firSession, scopeSession, firSerializerExtension, abstractTypeApproximator, languageVersionSettings, z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int writeLanguageVersionRequirement(LanguageFeature languageFeature, MutableVersionRequirementTable versionRequirementTable) {
            LanguageVersion sinceVersion = languageFeature.getSinceVersion();
            sinceVersion.getClass();
            return writeVersionRequirement(sinceVersion.getMajor(), sinceVersion.getMinor(), 0, ProtoBuf.VersionRequirement.VersionKind.LANGUAGE_VERSION, versionRequirementTable);
        }

        private final int writeVersionRequirement(int major, int minor, int patch, ProtoBuf.VersionRequirement.VersionKind versionKind, MutableVersionRequirementTable versionRequirementTable) {
            final ProtoBuf.VersionRequirement.Builder builderNewBuilder = ProtoBuf.VersionRequirement.newBuilder();
            new VersionRequirement.Version(major, minor, patch).encode(new Function1() { // from class: k55
                public final Object invoke(Object obj) {
                    return FirElementSerializer.Companion.writeVersionRequirement$lambda$0$0(builderNewBuilder, ((Integer) obj).intValue());
                }
            }, new Function1() { // from class: l55
                public final Object invoke(Object obj) {
                    return FirElementSerializer.Companion.writeVersionRequirement$lambda$0$1(builderNewBuilder, ((Integer) obj).intValue());
                }
            });
            if (versionKind != builderNewBuilder.getDefaultInstanceForType().getVersionKind()) {
                builderNewBuilder.setVersionKind(versionKind);
            }
            return versionRequirementTable.get(builderNewBuilder);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit writeVersionRequirement$lambda$0$0(ProtoBuf.VersionRequirement.Builder builder, int i) {
            builder.setVersion(i);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit writeVersionRequirement$lambda$0$1(ProtoBuf.VersionRequirement.Builder builder, int i) {
            builder.setVersionFull(i);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        public final FirElementSerializer create(FirSession session, ScopeSession scopeSession, FirClass klass, FirSerializerExtension extension, FirElementSerializer parentSerializer, AbstractTypeApproximator typeApproximator, LanguageVersionSettings languageVersionSettings, boolean produceHeaderKlib) {
            FirElementSerializer firElementSerializerCreateTopLevel;
            session.getClass();
            scopeSession.getClass();
            klass.getClass();
            extension.getClass();
            typeApproximator.getClass();
            languageVersionSettings.getClass();
            ClassId outerClassId = klass.getSymbol().getClassId().getOuterClassId();
            if (outerClassId == null || klass.getIsLocal()) {
                firElementSerializerCreateTopLevel = createTopLevel(session, scopeSession, extension, typeApproximator, languageVersionSettings, produceHeaderKlib);
            } else {
                FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(session).getClassLikeSymbolByClassId(outerClassId);
                classLikeSymbolByClassId.getClass();
                E fir = classLikeSymbolByClassId.getFir();
                fir.getClass();
                firElementSerializerCreateTopLevel = parentSerializer == null ? create(session, scopeSession, (FirRegularClass) fir, extension, null, typeApproximator, languageVersionSettings, produceHeaderKlib) : parentSerializer;
            }
            FirElementSerializer firElementSerializer = new FirElementSerializer(session, scopeSession, klass, new Interner(firElementSerializerCreateTopLevel.typeParameters), extension, new MutableTypeTable(), (outerClassId == null || VersionSpecificBehaviorKt.isKotlin1Dot4OrLater(extension.getMetadataVersion())) ? new MutableVersionRequirementTable() : firElementSerializerCreateTopLevel.versionRequirementTable, false, typeApproximator, languageVersionSettings, produceHeaderKlib, null);
            for (FirTypeParameterRef firTypeParameterRef : klass.getTypeParameters()) {
                if (firTypeParameterRef instanceof FirTypeParameter) {
                    firElementSerializer.typeParameters.intern(firTypeParameterRef);
                }
            }
            return firElementSerializer;
        }

        @JvmStatic
        public final FirElementSerializer createForLambda(FirSession session, ScopeSession scopeSession, FirSerializerExtension extension, AbstractTypeApproximator typeApproximator, LanguageVersionSettings languageVersionSettings) {
            session.getClass();
            scopeSession.getClass();
            extension.getClass();
            typeApproximator.getClass();
            languageVersionSettings.getClass();
            return new FirElementSerializer(session, scopeSession, null, new Interner((Interner) null, 1, (DefaultConstructorMarker) null), extension, new MutableTypeTable(), null, true, typeApproximator, languageVersionSettings, false, null);
        }

        @JvmStatic
        public final FirElementSerializer createForScript(FirSession session, ScopeSession scopeSession, FirScript script, FirSerializerExtension extension, AbstractTypeApproximator typeApproximator, LanguageVersionSettings languageVersionSettings, boolean produceHeaderKlib) {
            session.getClass();
            scopeSession.getClass();
            script.getClass();
            extension.getClass();
            typeApproximator.getClass();
            languageVersionSettings.getClass();
            return new FirElementSerializer(session, scopeSession, script, new Interner((Interner) null, 1, (DefaultConstructorMarker) null), extension, new MutableTypeTable(), new MutableVersionRequirementTable(), false, typeApproximator, languageVersionSettings, produceHeaderKlib, null);
        }

        @JvmStatic
        public final FirElementSerializer createForSnippet(FirSession session, ScopeSession scopeSession, FirReplSnippet snippet, FirSerializerExtension extension, AbstractTypeApproximator typeApproximator, LanguageVersionSettings languageVersionSettings, boolean produceHeaderKlib) {
            session.getClass();
            scopeSession.getClass();
            snippet.getClass();
            extension.getClass();
            typeApproximator.getClass();
            languageVersionSettings.getClass();
            return new FirElementSerializer(session, scopeSession, snippet, new Interner((Interner) null, 1, (DefaultConstructorMarker) null), extension, new MutableTypeTable(), new MutableVersionRequirementTable(), false, typeApproximator, languageVersionSettings, produceHeaderKlib, null);
        }

        @JvmStatic
        public final FirElementSerializer createTopLevel(FirSession session, ScopeSession scopeSession, FirSerializerExtension extension, AbstractTypeApproximator typeApproximator, LanguageVersionSettings languageVersionSettings, boolean produceHeaderKlib) {
            session.getClass();
            scopeSession.getClass();
            extension.getClass();
            typeApproximator.getClass();
            languageVersionSettings.getClass();
            return new FirElementSerializer(session, scopeSession, null, new Interner((Interner) null, 1, (DefaultConstructorMarker) null), extension, new MutableTypeTable(), new MutableVersionRequirementTable(), false, typeApproximator, languageVersionSettings, produceHeaderKlib, null);
        }

        private Companion() {
        }
    }

    public static /* synthetic */ int typeId$default(FirElementSerializer firElementSerializer, FirTypeRef firTypeRef, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return firElementSerializer.typeId(firTypeRef, z);
    }

    private final ProtoBuf.MemberKind memberKind(FirProperty firProperty) {
        return Intrinsics.areEqual(firProperty.getOrigin(), FirDeclarationOrigin.Delegated.INSTANCE) ? ProtoBuf.MemberKind.DELEGATION : ProtoBuf.MemberKind.DECLARATION;
    }

    public final int typeId(ConeKotlinType type, boolean toSuper, boolean abbreviationOnly) {
        type.getClass();
        return this.typeTable.get(typeProto$default(this, type, toSuper, null, false, abbreviationOnly, 12, null));
    }

    public static /* synthetic */ ProtoBuf.Type.Builder typeProto$default(FirElementSerializer firElementSerializer, FirTypeRef firTypeRef, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return firElementSerializer.typeProto(firTypeRef, z);
    }

    private final Visibility normalizeVisibility(FirPropertyAccessor declaration) {
        return Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(declaration), Boolean.TRUE) ? Visibilities.Public.INSTANCE : declaration.getStatus().getVisibility().normalize();
    }

    private final int getClassifierId(ClassId classId) {
        return getStringTable().getQualifiedClassNameIndex(classId);
    }

    @SensitiveApi
    public final ProtoBuf.Package.Builder packagePartProto(FqName packageFqName, List<? extends FirDeclaration> declarations, Set<? extends FirDeclaration> actualizedExpectDeclarations) {
        packageFqName.getClass();
        declarations.getClass();
        ProtoBuf.Package.Builder builderNewBuilder = ProtoBuf.Package.newBuilder();
        for (FirDeclaration firDeclaration : declarations) {
            builderNewBuilder.getClass();
            addDeclarationProto(builderNewBuilder, firDeclaration, actualizedExpectDeclarations, new Function1() { // from class: c55
                public final Object invoke(Object obj) {
                    return FirElementSerializer.i((FirDeclaration) obj);
                }
            });
        }
        builderNewBuilder.getClass();
        return finalizePackagePartProto(packageFqName, builderNewBuilder, actualizedExpectDeclarations);
    }

    public /* synthetic */ FirElementSerializer(FirSession firSession, ScopeSession scopeSession, FirDeclaration firDeclaration, Interner interner, FirSerializerExtension firSerializerExtension, MutableTypeTable mutableTypeTable, MutableVersionRequirementTable mutableVersionRequirementTable, boolean z, AbstractTypeApproximator abstractTypeApproximator, LanguageVersionSettings languageVersionSettings, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, scopeSession, firDeclaration, interner, firSerializerExtension, mutableTypeTable, mutableVersionRequirementTable, z, abstractTypeApproximator, languageVersionSettings, z2);
    }

    private final ProtoBuf.Type.Builder typeProto(FirTypeRef typeRef, boolean toSuper) {
        return typeProto$default(this, FirTypeUtilsKt.getConeType(typeRef), toSuper, typeRef, false, false, 24, null);
    }

    private final List<Integer> serializeVersionRequirements(MutableVersionRequirementTable mutableVersionRequirementTable, FirAnnotationContainer firAnnotationContainer) {
        return serializeVersionRequirements(mutableVersionRequirementTable, firAnnotationContainer.getAnnotations());
    }

    private final void fillFromPossiblyInnerType(ProtoBuf.Type.Builder builder, ConeClassLikeType type, boolean abbreviationOnly) {
        FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) this, type.getLookupTag());
        if (symbol != null) {
            fillFromPossiblyInnerType(builder, symbol, type.getTypeArguments(), 0, abbreviationOnly);
            return;
        }
        builder.setClassName(getClassifierId(type.getLookupTag().getClassId()));
        ConeTypeProjection[] typeArguments = type.getTypeArguments();
        for (ConeTypeProjection coneTypeProjection : typeArguments) {
            builder.addArgument(typeArgument(coneTypeProjection, abbreviationOnly));
        }
    }

    private final ProtoBuf.ValueParameter.Builder valueParameterProto(FirValueParameter parameter, int index, FirFunction function) {
        FirSession session = getSession();
        try {
            return valueParameterProto(parameter, (this.stdLibCompilation && (function instanceof FirConstructor) && Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(FirTypeUtilsKt.getConeType(((FirConstructor) function).getReturnTypeRef())), StandardClassIds.INSTANCE.getEnum())) ? false : DeclarationUtilsKt.itOrExpectHasDefaultParameterValue(function, index));
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(parameter, th);
            wq6.a();
            return null;
        }
    }
}
