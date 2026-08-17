package org.jetbrains.kotlin.fir.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.builtins.PrimitiveType;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.ValueClassKind;
import org.jetbrains.kotlin.descriptors.ValueClassRepresentationKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.ValueClassesUtilsKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.TypeWillChangeAttributeKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.FqNameUnsafe;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.types.TypeCheckerState;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext;
import org.jetbrains.kotlin.types.model.CaptureStatus;
import org.jetbrains.kotlin.types.model.CapturedTypeConstructorMarker;
import org.jetbrains.kotlin.types.model.CapturedTypeMarker;
import org.jetbrains.kotlin.types.model.FlexibleTypeMarker;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.SimpleTypeMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentMarker;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.kotlin.types.model.TypeSystemContext;
import org.jetbrains.kotlin.types.model.TypeSystemContextKt;
import org.jetbrains.kotlin.types.model.TypeSystemOptimizationContext;
import org.jetbrains.kotlin.types.model.TypeVariableTypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeVariance;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¬\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004J\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010\f\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010\r\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010\u000e\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010\u000f\u001a\u00020\n*\u00020\u000bH\u0016J\u0012\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016*\u00020\u0018H\u0016J\u001c\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001a*\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u000bH\u0016J\f\u0010\u001c\u001a\u00020\n*\u00020\u0018H\u0016J\u000e\u0010\u001d\u001a\u0004\u0018\u00010\u001e*\u00020\u001fH\u0016J\u000e\u0010 \u001a\u0004\u0018\u00010!*\u00020\u001fH\u0016J\f\u0010\"\u001a\u00020\n*\u00020\u001fH\u0016J\f\u0010#\u001a\u00020\n*\u00020\u001fH\u0016J\u000e\u0010$\u001a\u0004\u0018\u00010%*\u00020&H\u0016J\f\u0010'\u001a\u00020\n*\u00020\u001fH\u0016J\f\u0010(\u001a\u00020\u001e*\u00020&H\u0016J\f\u0010)\u001a\u00020\u001e*\u00020&H\u0016J\u000e\u0010*\u001a\u0004\u0018\u00010+*\u00020,H\u0016J\u000e\u0010-\u001a\u0004\u0018\u00010.*\u00020\u0018H\u0016J\f\u0010/\u001a\u00020\n*\u00020\u001fH\u0016J\u0014\u00100\u001a\u00020\u001e*\u00020\u00182\u0006\u00101\u001a\u00020\nH\u0016J\f\u00102\u001a\u000203*\u00020\u0018H\u0016J\f\u00102\u001a\u000204*\u000205H\u0016J\f\u00106\u001a\u000207*\u000205H\u0016J\f\u00108\u001a\u00020\n*\u000205H\u0016J\f\u00109\u001a\u00020:*\u00020;H\u0016J\f\u0010<\u001a\u00020=*\u00020\u001fH\u0016J\u0014\u0010>\u001a\u00020:*\u00020\u001f2\u0006\u0010?\u001a\u00020=H\u0016J\u0012\u0010@\u001a\b\u0012\u0004\u0012\u00020:0\u001a*\u00020\u001fH\u0016J\f\u0010A\u001a\u00020B*\u00020\u001fH\u0016J\u000e\u0010C\u001a\u0004\u0018\u00010B*\u000205H\u0016J\f\u0010D\u001a\u00020\n*\u00020EH\u0016J\f\u0010F\u001a\u00020G*\u00020EH\u0016J\u000e\u0010H\u001a\u0004\u0018\u00010B*\u00020EH\u0016J\u0014\u0010I\u001a\u00020J*\u00020E2\u0006\u0010K\u001a\u00020\u001fH\u0016J\f\u0010L\u001a\u00020=*\u00020\u000bH\u0016J\u0014\u0010M\u001a\u00020\u0011*\u00020\u000b2\u0006\u0010?\u001a\u00020=H\u0016J\u0012\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00110\u001a*\u00020\u000bH\u0016J\u0012\u0010O\u001a\b\u0012\u0002\b\u0003\u0018\u00010P*\u00020\u000bH\u0016J\u0012\u0010Q\u001a\b\u0012\u0004\u0012\u00020B0\u0016*\u00020\u000bH\u0016J\f\u0010R\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010S\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010T\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010F\u001a\u00020G*\u00020UH\u0016J\f\u0010V\u001a\u00020=*\u00020UH\u0016J\u0014\u0010W\u001a\u00020B*\u00020U2\u0006\u0010?\u001a\u00020=H\u0016J\u0012\u0010X\u001a\b\u0012\u0004\u0012\u00020B0\u001a*\u00020UH\u0016J\f\u0010Y\u001a\u00020\u0011*\u00020UH\u0016J\u0016\u0010Z\u001a\u00020\n*\u00020U2\b\u0010[\u001a\u0004\u0018\u00010\u000bH\u0016J\u0018\u0010\\\u001a\u00020\n2\u0006\u0010]\u001a\u00020\u000b2\u0006\u0010^\u001a\u00020\u000bH\u0016J\f\u0010_\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010`\u001a\u00020\n*\u00020\u000bH\u0016J\u0012\u0010a\u001a\u0004\u0018\u00010B2\u0006\u0010b\u001a\u00020\u001fH\u0016J\u001a\u0010c\u001a\u0004\u0018\u00010\u001e2\u0006\u0010b\u001a\u00020\u00182\u0006\u0010d\u001a\u000207H\u0016J\f\u0010e\u001a\u00020B*\u00020\u0018H\u0016J\u0018\u0010f\u001a\u00020\n2\u0006\u0010g\u001a\u00020\u00182\u0006\u0010h\u001a\u00020\u0018H\u0016J\f\u0010i\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010j\u001a\u00020\n*\u00020\u000bH\u0016J\f\u0010k\u001a\u00020\n*\u00020\u000bH\u0016J\u001c\u0010l\u001a\u00020B*\u00020\u001f2\u0006\u0010m\u001a\u00020n2\u0006\u0010K\u001a\u00020\u001fH\u0016J\f\u0010o\u001a\u00020\n*\u00020\u0018H\u0016J\f\u0010p\u001a\u00020\n*\u00020,H\u0016J\u0016\u0010q\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030r0\u001a*\u00020\u001fH\u0016J\f\u0010s\u001a\u00020\n*\u00020\u0018H\u0016J\f\u0010t\u001a\u00020\n*\u00020\u0018H\u0016J\f\u0010u\u001a\u00020\n*\u00020\u0018H\u0016J\f\u0010v\u001a\u000203*\u00020\u000bH\u0016J\u0016\u0010w\u001a\u00020x2\f\u0010y\u001a\b\u0012\u0004\u0012\u00020,0\u0016H\u0016J\u0016\u0010w\u001a\u00020B2\f\u0010y\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0016H\u0016J\u0014\u0010z\u001a\u00020\n*\u00020\u001f2\u0006\u0010{\u001a\u00020\nH\u0016J\f\u0010z\u001a\u00020\n*\u00020\u001fH\u0016J\u000e\u0010|\u001a\u0004\u0018\u00010}*\u00020\u000bH\u0002J\b\u0010~\u001a\u00020\u0017H\u0016J\u0011\u0010\u007f\u001a\u00020\u00172\u0007\u0010\u0080\u0001\u001a\u00020\u001fH\u0016J\r\u0010\u0081\u0001\u001a\u00020\n*\u00020\u001fH\u0016J\r\u0010\u0082\u0001\u001a\u00020\n*\u00020\u000bH\u0016J\u0017\u0010\u0083\u0001\u001a\u00020\n*\u00020\u001f2\b\u0010\u0084\u0001\u001a\u00030\u0085\u0001H\u0016J\u0019\u0010\u0086\u0001\u001a\u0004\u0018\u00010n*\u00020\u001f2\b\u0010\u0084\u0001\u001a\u00030\u0085\u0001H\u0016J\u000f\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0011*\u00020\u000bH\u0016J\r\u0010\u0088\u0001\u001a\u00020\n*\u00020\u000bH\u0016J\r\u0010\u0089\u0001\u001a\u00020\n*\u00020\u000bH\u0016J:\u0010\u008a\u0001\u001a\u00020\n*\u00020\u000b2\u001b\u0010\u008b\u0001\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0005\u0012\u00030\u008d\u0001\u0012\u0004\u0012\u00020\u00180\u008c\u00010\u001a2\u000e\u0010\u008e\u0001\u001a\t\u0012\u0004\u0012\u00020\u000b0\u008f\u0001H\u0002J#\u0010\u0090\u0001\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0005\u0012\u00030\u008d\u0001\u0012\u0004\u0012\u00020\u001e0\u008c\u0001\u0018\u00010\u001a*\u00020\u000bH\u0016J\r\u0010\u0091\u0001\u001a\u00020\n*\u00020\u000bH\u0016J\r\u0010\u0092\u0001\u001a\u00020B*\u00020UH\u0016J\u0015\u0010\u0093\u0001\u001a\t\u0012\u0005\u0012\u00030\u0094\u00010\u001a*\u00020\u0011H\u0082\bJ\u000f\u0010\u0095\u0001\u001a\u0004\u0018\u00010B*\u00020\u001fH\u0016J\u0010\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u0001*\u00020\u000bH\u0016J\u0010\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u0097\u0001*\u00020\u000bH\u0016J\r\u0010\u0099\u0001\u001a\u00020\n*\u00020\u000bH\u0016J\u0010\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u009b\u0001*\u00020\u000bH\u0016J\u000e\u0010\u009c\u0001\u001a\u00030\u008d\u0001*\u00020UH\u0016J\r\u0010\u009d\u0001\u001a\u00020\n*\u00020UH\u0016J\r\u0010\u009e\u0001\u001a\u00020\n*\u00020\u001fH\u0016J\f\u0010\"\u001a\u00020\n*\u00020\u000bH\u0016J\u0012\u0010\u009f\u0001\u001a\u00030 \u00012\u0006\u0010b\u001a\u00020\u0018H\u0016J\r\u0010¡\u0001\u001a\u00020\n*\u00020\u001fH\u0016R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006¢\u0001À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "Lorg/jetbrains/kotlin/types/model/TypeSystemContext;", "Lorg/jetbrains/kotlin/types/model/TypeSystemOptimizationContext;", "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "isIntegerLiteralTypeConstructor", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "isIntegerLiteralConstantTypeConstructor", "isIntegerConstantOperatorTypeConstructor", "isLocalType", "isAnonymous", "typeParameter", "Lorg/jetbrains/kotlin/fir/symbols/ConeTypeParameterLookupTag;", "Lorg/jetbrains/kotlin/types/model/TypeVariableTypeConstructorMarker;", "getTypeParameter", "(Lorg/jetbrains/kotlin/types/model/TypeVariableTypeConstructorMarker;)Lorg/jetbrains/kotlin/fir/symbols/ConeTypeParameterLookupTag;", "possibleIntegerTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "fastCorrespondingSupertypes", Argument.Delimiters.none, "constructor", "isIntegerLiteralType", "asRigidType", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "asFlexibleType", "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "isError", "isUninferredParameter", "asDynamicType", "Lorg/jetbrains/kotlin/fir/types/ConeDynamicType;", "Lorg/jetbrains/kotlin/types/model/FlexibleTypeMarker;", "isRawType", "upperBound", "lowerBound", "asCapturedType", "Lorg/jetbrains/kotlin/fir/types/ConeCapturedType;", "Lorg/jetbrains/kotlin/types/model/SimpleTypeMarker;", "asDefinitelyNotNullType", "Lorg/jetbrains/kotlin/fir/types/ConeDefinitelyNotNullType;", "isMarkedNullable", "withNullability", "nullable", "typeConstructor", "Lorg/jetbrains/kotlin/fir/types/ConeTypeConstructorMarker;", "Lorg/jetbrains/kotlin/fir/types/ConeCapturedTypeConstructor;", "Lorg/jetbrains/kotlin/types/model/CapturedTypeMarker;", "captureStatus", "Lorg/jetbrains/kotlin/types/model/CaptureStatus;", "isOldCapturedType", "projection", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "Lorg/jetbrains/kotlin/types/model/CapturedTypeConstructorMarker;", "argumentsCount", Argument.Delimiters.none, "getArgument", "index", "getArguments", "asTypeArgument", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "lowerType", "isStarProjection", "Lorg/jetbrains/kotlin/types/model/TypeArgumentMarker;", "getVariance", "Lorg/jetbrains/kotlin/types/model/TypeVariance;", "getType", "replaceType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinTypeProjection;", "newType", "parametersCount", "getParameter", "getParameters", "toClassLikeSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "supertypes", "isIntersection", "isClassTypeConstructor", "isInterface", "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "upperBoundCount", "getUpperBound", "getUpperBounds", "getTypeConstructor", "hasRecursiveBounds", "selfConstructor", "areEqualTypeConstructors", "c1", "c2", "isDenotable", "isCommonFinalClassConstructor", "captureFromExpression", ModuleXmlParser.TYPE, "captureFromArguments", "status", "asArgumentList", "identicalArguments", "a", "b", "isAnyConstructor", "isNothingConstructor", "isArrayConstructor", "withNewTypeSince", "languageFeature", Argument.Delimiters.none, "isSingleClassifierType", "isPrimitiveType", "getAttributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "isStubType", "isStubTypeForVariableInSubtyping", "isStubTypeForBuilderInference", "unwrapStubTypeVariableConstructor", "intersectTypes", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "types", "isNullableType", "considerTypeVariableBounds", "toFirRegularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "nullableAnyType", "arrayType", "componentType", "isArrayOrNullableArray", "isFinalClassOrEnumEntryOrAnnotationClassConstructor", "hasAnnotation", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "getAnnotationFirstArgumentValue", "getTypeParameterClassifier", "isInlineClass", "isMultiFieldValueClass", "isMultiFieldValueClassRecursionAware", "fields", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/name/Name;", "visited", Argument.Delimiters.none, "getValueClassProperties", "isInnerClass", "getRepresentativeUpperBound", "bounds", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getUnsubstitutedUnderlyingType", "getPrimitiveType", "Lorg/jetbrains/kotlin/builtins/PrimitiveType;", "getPrimitiveArrayType", "isUnderKotlinPackage", "getClassFqNameUnsafe", "Lorg/jetbrains/kotlin/name/FqNameUnsafe;", "getName", "isReified", "isInterfaceOrAnnotationClass", "substitutionSupertypePolicy", "Lorg/jetbrains/kotlin/types/TypeCheckerState$SupertypesPolicy;", "isTypeVariableType", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface ConeTypeContext extends TypeSystemCommonBackendContext, TypeCheckerProviderContext, TypeSystemContext, TypeSystemOptimizationContext {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProjectionKind.values().length];
            try {
                iArr[ProjectionKind.STAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ProjectionKind.IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ProjectionKind.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ProjectionKind.INVARIANT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static boolean hasRecursiveBounds$lambda$0$0(ConeTypeContext coneTypeContext, TypeParameterMarker typeParameterMarker, ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return Intrinsics.areEqual(coneTypeContext.typeConstructor(coneKotlinType), coneTypeContext.m678getTypeConstructor(typeParameterMarker));
    }

    private default boolean isMultiFieldValueClassRecursionAware(TypeConstructorMarker typeConstructorMarker, List<? extends Pair<Name, ? extends RigidTypeMarker>> list, Set<TypeConstructorMarker> set) {
        RigidTypeMarker rigidTypeMarker;
        List<Pair<Name, ConeRigidType>> valueClassProperties;
        if (list.size() > 1) {
            return true;
        }
        Pair pair = (Pair) CollectionsKt.singleOrNull(list);
        if (pair == null || (rigidTypeMarker = (RigidTypeMarker) pair.getSecond()) == null || !set.add(typeConstructorMarker)) {
            return false;
        }
        ConeTypeConstructorMarker coneTypeConstructorMarkerM691typeConstructor = m691typeConstructor(rigidTypeMarker);
        return (isNullableType(rigidTypeMarker) || (valueClassProperties = getValueClassProperties(coneTypeConstructorMarkerM691typeConstructor)) == null || !isMultiFieldValueClassRecursionAware(coneTypeConstructorMarkerM691typeConstructor, valueClassProperties, set)) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private default FirRegularClass toFirRegularClass(TypeConstructorMarker typeConstructorMarker) {
        FirClassLikeSymbol<?> classLikeSymbol = toClassLikeSymbol(typeConstructorMarker);
        FirClassLikeDeclaration firClassLikeDeclaration = classLikeSymbol != null ? (FirClassLikeDeclaration) classLikeSymbol.getFir() : null;
        if (firClassLikeDeclaration instanceof FirRegularClass) {
            return (FirRegularClass) firClassLikeDeclaration;
        }
        return null;
    }

    default boolean areEqualTypeConstructors(TypeConstructorMarker c1, TypeConstructorMarker c2) {
        c1.getClass();
        c2.getClass();
        return Intrinsics.areEqual(c1, c2);
    }

    default int argumentsCount(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return ((ConeKotlinType) kotlinTypeMarker).getTypeArguments().length;
        }
        w01.a("Failed requirement.");
        return 0;
    }

    /* JADX INFO: renamed from: arrayType, reason: merged with bridge method [inline-methods] */
    default ConeClassLikeType m664arrayType(KotlinTypeMarker componentType) {
        componentType.getClass();
        if (componentType instanceof ConeKotlinType) {
            return ArrayUtilsKt.createArrayType$default((ConeTypeProjection) componentType, false, false, 2, null);
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: asArgumentList, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m665asArgumentList(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        if (rigidTypeMarker instanceof ConeKotlinType) {
            return (ConeKotlinType) rigidTypeMarker;
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: asCapturedType, reason: merged with bridge method [inline-methods] */
    default ConeCapturedType m666asCapturedType(SimpleTypeMarker simpleTypeMarker) {
        simpleTypeMarker.getClass();
        if (simpleTypeMarker instanceof ConeCapturedType) {
            return (ConeCapturedType) simpleTypeMarker;
        }
        return null;
    }

    /* JADX INFO: renamed from: asDefinitelyNotNullType, reason: merged with bridge method [inline-methods] */
    default ConeDefinitelyNotNullType m667asDefinitelyNotNullType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        if (rigidTypeMarker instanceof ConeDefinitelyNotNullType) {
            return (ConeDefinitelyNotNullType) rigidTypeMarker;
        }
        return null;
    }

    /* JADX INFO: renamed from: asDynamicType, reason: merged with bridge method [inline-methods] */
    default ConeDynamicType m668asDynamicType(FlexibleTypeMarker flexibleTypeMarker) {
        flexibleTypeMarker.getClass();
        if (flexibleTypeMarker instanceof ConeDynamicType) {
            return (ConeDynamicType) flexibleTypeMarker;
        }
        return null;
    }

    /* JADX INFO: renamed from: asFlexibleType, reason: merged with bridge method [inline-methods] */
    default ConeFlexibleType m669asFlexibleType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeFlexibleType) {
            return (ConeFlexibleType) kotlinTypeMarker;
        }
        return null;
    }

    /* JADX INFO: renamed from: asRigidType, reason: merged with bridge method [inline-methods] */
    default ConeRigidType m670asRigidType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (!(kotlinTypeMarker instanceof ConeKotlinType)) {
            w01.a("Failed requirement.");
            return null;
        }
        ConeKotlinType coneKotlinType = (ConeKotlinType) kotlinTypeMarker;
        if (coneKotlinType instanceof ConeClassLikeType) {
            return TypeExpansionUtilsKt.fullyExpandedType$default((ConeClassLikeType) kotlinTypeMarker, getSession(), (Function1) null, 2, (Object) null);
        }
        if (coneKotlinType instanceof ConeRigidType) {
            return (ConeRigidType) kotlinTypeMarker;
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            return null;
        }
        bu8.a();
        return null;
    }

    /* JADX INFO: renamed from: asTypeArgument, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m671asTypeArgument(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return (ConeKotlinType) kotlinTypeMarker;
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: captureFromArguments, reason: merged with bridge method [inline-methods] */
    default ConeRigidType m672captureFromArguments(RigidTypeMarker type, CaptureStatus status) {
        type.getClass();
        status.getClass();
        if (type instanceof ConeRigidType) {
            return TypeUtilsKt.captureFromArgumentsInternal(this, (ConeRigidType) type, status);
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: captureFromExpression, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m673captureFromExpression(KotlinTypeMarker type) {
        type.getClass();
        if (type instanceof ConeKotlinType) {
            return TypeUtilsKt.captureFromExpressionInternal(this, (ConeKotlinType) type);
        }
        w01.a("Failed requirement.");
        return null;
    }

    default CaptureStatus captureStatus(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        if (capturedTypeMarker instanceof ConeCapturedType) {
            return ((ConeCapturedType) capturedTypeMarker).getConstructor().getCaptureStatus();
        }
        w01.a("Failed requirement.");
        return null;
    }

    default List<ConeClassLikeType> fastCorrespondingSupertypes(RigidTypeMarker rigidTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        rigidTypeMarker.getClass();
        typeConstructorMarker.getClass();
        if (rigidTypeMarker instanceof ConeKotlinType) {
            return FirCorrespondingSupertypesCacheKt.getCorrespondingSupertypesCache(getSession()).getCorrespondingSupertypes((ConeKotlinType) rigidTypeMarker, typeConstructorMarker);
        }
        w01.a("Failed requirement.");
        return null;
    }

    default Object getAnnotationFirstArgumentValue(KotlinTypeMarker kotlinTypeMarker, FqName fqName) {
        Object next;
        ClassId classId;
        kotlinTypeMarker.getClass();
        fqName.getClass();
        if (!(kotlinTypeMarker instanceof ConeKotlinType)) {
            w01.a("Failed requirement.");
            return null;
        }
        Iterator<T> it = CustomAnnotationTypeAttributeKt.getCustomAnnotations((ConeKotlinType) kotlinTypeMarker).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            classId = ConeTypeUtilsKt.getClassId(TypeExpansionUtilsKt.fullyExpandedType$default(FirTypeUtilsKt.getResolvedType((FirAnnotation) next), getSession(), (Function1) null, 2, (Object) null));
        } while (!Intrinsics.areEqual(classId != null ? classId.asSingleFqName() : null, fqName));
        FirAnnotation firAnnotation = (FirAnnotation) next;
        if (firAnnotation == null) {
            return null;
        }
        if (firAnnotation instanceof FirAnnotationCall) {
            FirLazyDeclarationResolverKt.lazyResolveToPhase(((FirAnnotationCall) firAnnotation).getContainingDeclarationSymbol(), FirResolvePhase.ANNOTATION_ARGUMENTS);
        }
        FirAnnotationContainer expression = (FirExpression) CollectionsKt.firstOrNull(firAnnotation.getArgumentMapping().getMapping().values());
        if (expression == null) {
            return null;
        }
        if (expression instanceof FirVarargArgumentsExpression) {
            expression = (FirExpression) CollectionsKt.firstOrNull(((FirVarargArgumentsExpression) expression).getArguments());
        } else if (expression instanceof FirCollectionLiteral) {
            expression = (FirExpression) CollectionsKt.firstOrNull(((FirCall) expression).getArgumentList().getArguments());
        } else if (expression instanceof FirNamedArgumentExpression) {
            expression = ((FirNamedArgumentExpression) expression).getExpression();
        }
        if (expression == null) {
            return null;
        }
        FirLiteralExpression firLiteralExpression = expression instanceof FirLiteralExpression ? (FirLiteralExpression) expression : null;
        if (firLiteralExpression != null) {
            return firLiteralExpression.getValue();
        }
        return null;
    }

    /* JADX INFO: renamed from: getArgument, reason: merged with bridge method [inline-methods] */
    default ConeTypeProjection m674getArgument(KotlinTypeMarker kotlinTypeMarker, int i) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            ConeTypeProjection coneTypeProjection = (ConeTypeProjection) ArraysKt.getOrNull(((ConeKotlinType) kotlinTypeMarker).getTypeArguments(), i);
            return coneTypeProjection == null ? ConeStarProjection.INSTANCE : coneTypeProjection;
        }
        w01.a("Failed requirement.");
        return null;
    }

    default List<ConeTypeProjection> getArguments(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return ArraysKt.toList(((ConeKotlinType) kotlinTypeMarker).getTypeArguments());
        }
        w01.a("Failed requirement.");
        return null;
    }

    default List<ConeAttribute<?>> getAttributes(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return CollectionsKt.toList(((ConeKotlinType) kotlinTypeMarker).getAttributes());
        }
        w01.a("Failed requirement.");
        return null;
    }

    default FqNameUnsafe getClassFqNameUnsafe(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        if (typeConstructorMarker instanceof ConeClassLikeLookupTag) {
            return ((ConeClassLikeLookupTag) typeConstructorMarker).getClassId().asSingleFqName().toUnsafe();
        }
        return null;
    }

    default Name getName(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        return ((ConeTypeParameterLookupTag) typeParameterMarker).getName();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getParameter, reason: merged with bridge method [inline-methods] */
    default ConeTypeParameterLookupTag m675getParameter(TypeConstructorMarker typeConstructorMarker, int i) {
        typeConstructorMarker.getClass();
        FirClassLikeSymbol<?> classLikeSymbol = toClassLikeSymbol(typeConstructorMarker);
        if (classLikeSymbol instanceof FirAnonymousObjectSymbol) {
            return ((FirAnonymousObject) ((FirAnonymousObjectSymbol) classLikeSymbol).getFir()).getTypeParameters().get(i).getSymbol().getLookupTag();
        }
        if (classLikeSymbol instanceof FirRegularClassSymbol) {
            return ((FirRegularClass) ((FirRegularClassSymbol) classLikeSymbol).getFir()).getTypeParameters().get(i).getSymbol().getLookupTag();
        }
        if (classLikeSymbol instanceof FirTypeAliasSymbol) {
            return ((FirTypeAlias) ((FirTypeAliasSymbol) classLikeSymbol).getFir()).getTypeParameters().get(i).getSymbol().getLookupTag();
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unexpected FirClassLikeSymbol " + classLikeSymbol + " for " + Reflection.getOrCreateKotlinClass(typeConstructorMarker.getClass()), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirLookupTagEntry(exceptionAttachmentBuilder, "lookupTag", typeConstructorMarker instanceof ConeClassLikeLookupTag ? (ConeClassLikeLookupTag) typeConstructorMarker : null);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    /* JADX WARN: Multi-variable type inference failed */
    default List<ConeTypeParameterLookupTag> getParameters(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        FirClassLikeSymbol<?> classLikeSymbol = toClassLikeSymbol(typeConstructorMarker);
        if (classLikeSymbol instanceof FirAnonymousObjectSymbol) {
            List<FirTypeParameterRef> typeParameters = ((FirAnonymousObject) ((FirAnonymousObjectSymbol) classLikeSymbol).getFir()).getTypeParameters();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
            Iterator<T> it = typeParameters.iterator();
            while (it.hasNext()) {
                arrayList.add(((FirTypeParameterRef) it.next()).getSymbol().getLookupTag());
            }
            return arrayList;
        }
        if (classLikeSymbol instanceof FirRegularClassSymbol) {
            List<FirTypeParameterRef> typeParameters2 = ((FirRegularClass) ((FirRegularClassSymbol) classLikeSymbol).getFir()).getTypeParameters();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters2, 10));
            Iterator<T> it2 = typeParameters2.iterator();
            while (it2.hasNext()) {
                arrayList2.add(((FirTypeParameterRef) it2.next()).getSymbol().getLookupTag());
            }
            return arrayList2;
        }
        if (!(classLikeSymbol instanceof FirTypeAliasSymbol)) {
            return CollectionsKt.emptyList();
        }
        List<FirTypeParameterRef> typeParameters3 = ((FirTypeAlias) ((FirTypeAliasSymbol) classLikeSymbol).getFir()).getTypeParameters();
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters3, 10));
        Iterator<T> it3 = typeParameters3.iterator();
        while (it3.hasNext()) {
            arrayList3.add(((FirTypeParameterRef) it3.next()).getSymbol().getLookupTag());
        }
        return arrayList3;
    }

    default PrimitiveType getPrimitiveArrayType(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        FqNameUnsafe classFqNameUnsafe = getClassFqNameUnsafe(typeConstructorMarker);
        if (classFqNameUnsafe != null) {
            return (PrimitiveType) StandardNames.FqNames.arrayClassFqNameToPrimitiveType.get(classFqNameUnsafe);
        }
        return null;
    }

    default PrimitiveType getPrimitiveType(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        FqNameUnsafe classFqNameUnsafe = getClassFqNameUnsafe(typeConstructorMarker);
        if (classFqNameUnsafe != null) {
            return (PrimitiveType) StandardNames.FqNames.fqNameToPrimitiveType.get(classFqNameUnsafe);
        }
        return null;
    }

    /* JADX INFO: renamed from: getRepresentativeUpperBound, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m676getRepresentativeUpperBound(TypeParameterMarker typeParameterMarker) {
        ConeKotlinType coneType;
        typeParameterMarker.getClass();
        if (typeParameterMarker instanceof ConeTypeParameterLookupTag) {
            FirTypeRef firTypeRef = (FirTypeRef) CollectionsKt.getOrNull(((ConeTypeParameterLookupTag) typeParameterMarker).getSymbol().getResolvedBounds(), 0);
            return (firTypeRef == null || (coneType = FirTypeUtilsKt.getConeType(firTypeRef)) == null) ? getSession().getBuiltinTypes().getNullableAnyType().getConeType() : coneType;
        }
        w01.a("Failed requirement.");
        return null;
    }

    FirSession getSession();

    /* JADX INFO: renamed from: getType, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m677getType(TypeArgumentMarker typeArgumentMarker) {
        typeArgumentMarker.getClass();
        if (typeArgumentMarker instanceof ConeTypeProjection) {
            return ConeTypeProjectionKt.getType((ConeTypeProjection) typeArgumentMarker);
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: getTypeConstructor, reason: merged with bridge method [inline-methods] */
    default ConeTypeParameterLookupTag m678getTypeConstructor(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        if (typeParameterMarker instanceof ConeTypeParameterLookupTag) {
            return (ConeTypeParameterLookupTag) typeParameterMarker;
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: getTypeParameter, reason: merged with bridge method [inline-methods] */
    default ConeTypeParameterLookupTag m679getTypeParameter(TypeVariableTypeConstructorMarker typeVariableTypeConstructorMarker) {
        typeVariableTypeConstructorMarker.getClass();
        if (!(typeVariableTypeConstructorMarker instanceof ConeTypeVariableTypeConstructor)) {
            w01.a("Failed requirement.");
            return null;
        }
        TypeParameterMarker originalTypeParameter = ((ConeTypeVariableTypeConstructor) typeVariableTypeConstructorMarker).getOriginalTypeParameter();
        if (originalTypeParameter != null) {
            return (ConeTypeParameterLookupTag) originalTypeParameter;
        }
        return null;
    }

    /* JADX INFO: renamed from: getTypeParameterClassifier, reason: merged with bridge method [inline-methods] */
    default ConeTypeParameterLookupTag m680getTypeParameterClassifier(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        if (typeConstructorMarker instanceof ConeTypeParameterLookupTag) {
            return (ConeTypeParameterLookupTag) typeConstructorMarker;
        }
        return null;
    }

    /* JADX INFO: renamed from: getUnsubstitutedUnderlyingType, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m681getUnsubstitutedUnderlyingType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return ValueClassesUtilsKt.unsubstitutedUnderlyingTypeForInlineClass((ConeKotlinType) kotlinTypeMarker, getSession());
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: getUpperBound, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m682getUpperBound(TypeParameterMarker typeParameterMarker, int i) {
        typeParameterMarker.getClass();
        if (typeParameterMarker instanceof ConeTypeParameterLookupTag) {
            return FirTypeUtilsKt.getConeType(((ConeTypeParameterLookupTag) typeParameterMarker).getSymbol().getResolvedBounds().get(i));
        }
        w01.a("Failed requirement.");
        return null;
    }

    default List<ConeKotlinType> getUpperBounds(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        if (!(typeParameterMarker instanceof ConeTypeParameterLookupTag)) {
            w01.a("Failed requirement.");
            return null;
        }
        List<FirResolvedTypeRef> resolvedBounds = ((ConeTypeParameterLookupTag) typeParameterMarker).getSymbol().getResolvedBounds();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(resolvedBounds, 10));
        Iterator<T> it = resolvedBounds.iterator();
        while (it.hasNext()) {
            arrayList.add(FirTypeUtilsKt.getConeType((FirTypeRef) it.next()));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    default List<Pair<Name, ConeRigidType>> getValueClassProperties(TypeConstructorMarker typeConstructorMarker) {
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        FirRegularClass firRegularClass;
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny;
        List<FirValueParameterSymbol> valueParameterSymbols;
        typeConstructorMarker.getClass();
        FirClassLikeSymbol<?> classLikeSymbol = toClassLikeSymbol(typeConstructorMarker);
        if (classLikeSymbol != null && (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(classLikeSymbol, getSession())) != null && (firRegularClass = (FirRegularClass) firRegularClassSymbolFullyExpandedClass.getFir()) != null && ((firRegularClass.getStatus().isInline() || firRegularClass.getStatus().isValue()) && (firConstructorSymbolPrimaryConstructorIfAny = DeclarationUtilsKt.primaryConstructorIfAny(firRegularClass, getSession())) != null && (valueParameterSymbols = firConstructorSymbolPrimaryConstructorIfAny.getValueParameterSymbols()) != null)) {
            List<FirValueParameterSymbol> list = valueParameterSymbols;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (FirValueParameterSymbol firValueParameterSymbol : list) {
                Name name = firValueParameterSymbol.getName();
                ConeKotlinType resolvedReturnType = firValueParameterSymbol.getResolvedReturnType();
                resolvedReturnType.getClass();
                arrayList.add(TuplesKt.to(name, (ConeRigidType) resolvedReturnType));
            }
            if (!arrayList.isEmpty()) {
                return arrayList;
            }
        }
        return null;
    }

    default TypeVariance getVariance(TypeArgumentMarker typeArgumentMarker) {
        typeArgumentMarker.getClass();
        if (!(typeArgumentMarker instanceof ConeKotlinTypeProjection)) {
            w01.a("Failed requirement.");
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[((ConeKotlinTypeProjection) typeArgumentMarker).getKind().ordinal()];
        if (i == 1) {
            k2d.a("Nekorrektno (c) Stas");
            return null;
        }
        if (i == 2) {
            return TypeVariance.IN;
        }
        if (i == 3) {
            return TypeVariance.OUT;
        }
        if (i == 4) {
            return TypeVariance.INV;
        }
        bu8.a();
        return null;
    }

    default boolean hasAnnotation(KotlinTypeMarker kotlinTypeMarker, FqName fqName) {
        kotlinTypeMarker.getClass();
        fqName.getClass();
        if (!(kotlinTypeMarker instanceof ConeKotlinType)) {
            w01.a("Failed requirement.");
            return false;
        }
        KClass<? extends ConeAttribute<?>> kClass = (KClass) CompilerConeAttributes.INSTANCE.getCompilerAttributeKeyByFqName().get(fqName);
        if (kClass != null) {
            return ((ConeKotlinType) kotlinTypeMarker).getAttributes().contains(kClass);
        }
        ParameterNameTypeAttribute.Companion companion = ParameterNameTypeAttribute.Companion;
        if (Intrinsics.areEqual(fqName, companion.getANNOTATION_CLASS_ID().asSingleFqName())) {
            return ((ConeKotlinType) kotlinTypeMarker).getAttributes().contains(companion.getKEY());
        }
        List<FirAnnotation> customAnnotations = CustomAnnotationTypeAttributeKt.getCustomAnnotations((ConeKotlinType) kotlinTypeMarker);
        if ((customAnnotations instanceof Collection) && customAnnotations.isEmpty()) {
            return false;
        }
        Iterator<T> it = customAnnotations.iterator();
        while (it.hasNext()) {
            ClassId classId = ConeTypeUtilsKt.getClassId(TypeExpansionUtilsKt.fullyExpandedType$default(FirTypeUtilsKt.getResolvedType((FirAnnotation) it.next()), getSession(), (Function1) null, 2, (Object) null));
            if (Intrinsics.areEqual(classId != null ? classId.asSingleFqName() : null, fqName)) {
                return true;
            }
        }
        return false;
    }

    default boolean hasRecursiveBounds(final TypeParameterMarker typeParameterMarker, TypeConstructorMarker typeConstructorMarker) {
        typeParameterMarker.getClass();
        if (!(typeParameterMarker instanceof ConeTypeParameterLookupTag)) {
            w01.a("Failed requirement.");
            return false;
        }
        ConeTypeParameterLookupTag coneTypeParameterLookupTag = (ConeTypeParameterLookupTag) typeParameterMarker;
        FirLazyDeclarationResolverKt.lazyResolveToPhase(coneTypeParameterLookupTag.getTypeParameterSymbol(), FirResolvePhase.TYPES);
        List<FirResolvedTypeRef> resolvedBounds = coneTypeParameterLookupTag.getSymbol().getResolvedBounds();
        if ((resolvedBounds instanceof Collection) && resolvedBounds.isEmpty()) {
            return false;
        }
        Iterator<T> it = resolvedBounds.iterator();
        while (it.hasNext()) {
            FirTypeRef firTypeRef = (FirTypeRef) it.next();
            if (ConeTypeUtilsKt.contains(FirTypeUtilsKt.getConeType(firTypeRef), new Function1() { // from class: jq2
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ConeTypeContext.hasRecursiveBounds$lambda$0$0(this.b, typeParameterMarker, (ConeKotlinType) obj));
                }
            }) && (typeConstructorMarker == null || Intrinsics.areEqual(typeConstructor(FirTypeUtilsKt.getConeType(firTypeRef)), typeConstructorMarker))) {
                return true;
            }
        }
        return false;
    }

    default boolean identicalArguments(RigidTypeMarker a, RigidTypeMarker b) {
        a.getClass();
        b.getClass();
        if (!(a instanceof ConeRigidType)) {
            w01.a("Failed requirement.");
            return false;
        }
        if (b instanceof ConeRigidType) {
            return ((ConeRigidType) a).getTypeArguments() == ((ConeRigidType) b).getTypeArguments();
        }
        w01.a("Failed requirement.");
        return false;
    }

    /* JADX INFO: renamed from: intersectTypes, reason: collision with other method in class */
    default ConeSimpleKotlinType m683intersectTypes(Collection<? extends SimpleTypeMarker> types) {
        types.getClass();
        ConeKotlinType coneKotlinTypeIntersectTypes = ConeTypeIntersector.INSTANCE.intersectTypes((ConeInferenceContext) this, types);
        coneKotlinTypeIntersectTypes.getClass();
        return (ConeSimpleKotlinType) coneKotlinTypeIntersectTypes;
    }

    default boolean isAnonymous(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        if (typeConstructorMarker instanceof ConeClassLikeLookupTag) {
            return TypeUtilsKt.isAnonymousClass((ConeClassLikeLookupTag) typeConstructorMarker);
        }
        return false;
    }

    default boolean isAnyConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return (typeConstructorMarker instanceof ConeClassLikeLookupTag) && Intrinsics.areEqual(((ConeClassLikeLookupTag) typeConstructorMarker).getClassId(), StandardClassIds.INSTANCE.getAny());
    }

    default boolean isArrayConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return (typeConstructorMarker instanceof ConeClassLikeLookupTag) && Intrinsics.areEqual(((ConeClassLikeLookupTag) typeConstructorMarker).getClassId(), StandardClassIds.INSTANCE.getArray());
    }

    default boolean isArrayOrNullableArray(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return Intrinsics.areEqual(ConeTypeUtilsKt.getClassId((ConeKotlinType) kotlinTypeMarker), StandardClassIds.INSTANCE.getArray());
        }
        w01.a("Failed requirement.");
        return false;
    }

    default boolean isClassTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return (typeConstructorMarker instanceof ConeClassLikeLookupTag) || (typeConstructorMarker instanceof ConeStubTypeConstructor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    default boolean isCommonFinalClassConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        FirClassLikeSymbol<?> classLikeSymbol = toClassLikeSymbol(typeConstructorMarker);
        if (classLikeSymbol == null) {
            return false;
        }
        if (classLikeSymbol instanceof FirAnonymousObjectSymbol) {
            return true;
        }
        FirRegularClassSymbol firRegularClassSymbol = classLikeSymbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) classLikeSymbol : null;
        if (firRegularClassSymbol == null) {
            return false;
        }
        FirRegularClass firRegularClass = (FirRegularClass) firRegularClassSymbol.getFir();
        return (firRegularClass.getStatus().getModality() != Modality.FINAL || firRegularClass.getClassKind() == ClassKind.ENUM_ENTRY || firRegularClass.getClassKind() == ClassKind.ANNOTATION_CLASS) ? false : true;
    }

    default boolean isDenotable(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        if (!(typeConstructorMarker instanceof ConeTypeConstructorMarker)) {
            w01.a("Failed requirement.");
            return false;
        }
        ConeTypeConstructorMarker coneTypeConstructorMarker = (ConeTypeConstructorMarker) typeConstructorMarker;
        if (coneTypeConstructorMarker instanceof ConeClassifierLookupTag) {
            return !(typeConstructorMarker instanceof ConeClassLikeErrorLookupTag);
        }
        if (!(coneTypeConstructorMarker instanceof ConeStubTypeConstructor) && !(coneTypeConstructorMarker instanceof ConeCapturedTypeConstructor) && !(coneTypeConstructorMarker instanceof ConeTypeVariableTypeConstructor) && !(coneTypeConstructorMarker instanceof ConeIntegerLiteralType) && !(coneTypeConstructorMarker instanceof ConeIntersectionType)) {
            bu8.a();
        }
        return false;
    }

    default boolean isError(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if ((kotlinTypeMarker instanceof ConeErrorType) || isError(typeConstructor(kotlinTypeMarker))) {
            return true;
        }
        if ((kotlinTypeMarker instanceof ConeClassLikeType) && (((ConeClassLikeType) kotlinTypeMarker).getLookupTag() instanceof ConeClassLikeErrorLookupTag)) {
            return true;
        }
        return (kotlinTypeMarker instanceof ConeDefinitelyNotNullType) && (((ConeDefinitelyNotNullType) kotlinTypeMarker).getOriginal() instanceof ConeErrorType);
    }

    default boolean isFinalClassOrEnumEntryOrAnnotationClassConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        FirRegularClass firRegularClass = toFirRegularClass(typeConstructorMarker);
        if (firRegularClass == null) {
            return false;
        }
        ClassKind classKind = firRegularClass.getClassKind();
        if (classKind == ClassKind.ENUM_ENTRY || classKind == ClassKind.ANNOTATION_CLASS || classKind == ClassKind.OBJECT) {
            return true;
        }
        return classKind == ClassKind.CLASS && firRegularClass.getSymbol().getResolvedStatus().getModality() == Modality.FINAL;
    }

    default boolean isInlineClass(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        List<Pair<Name, ConeRigidType>> valueClassProperties = getValueClassProperties(typeConstructorMarker);
        return valueClassProperties != null && ValueClassRepresentationKt.valueClassLoweringKind(this, valueClassProperties) == ValueClassKind.Inline;
    }

    default boolean isInnerClass(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        FirRegularClass firRegularClass = toFirRegularClass(typeConstructorMarker);
        return firRegularClass != null && firRegularClass.getStatus().isInner();
    }

    default boolean isIntegerConstantOperatorTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return typeConstructorMarker instanceof ConeIntegerConstantOperatorType;
    }

    default boolean isIntegerLiteralConstantTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return typeConstructorMarker instanceof ConeIntegerLiteralConstantType;
    }

    default boolean isIntegerLiteralType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return rigidTypeMarker instanceof ConeIntegerLiteralType;
    }

    default boolean isIntegerLiteralTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return typeConstructorMarker instanceof ConeIntegerLiteralType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    default boolean isInterface(TypeConstructorMarker typeConstructorMarker) {
        FirClassLikeSymbol<?> classLikeSymbol;
        typeConstructorMarker.getClass();
        ConeClassLikeLookupTag coneClassLikeLookupTag = typeConstructorMarker instanceof ConeClassLikeLookupTag ? (ConeClassLikeLookupTag) typeConstructorMarker : null;
        FirClassLikeDeclaration firClassLikeDeclaration = (coneClassLikeLookupTag == null || (classLikeSymbol = toClassLikeSymbol(coneClassLikeLookupTag)) == null) ? null : (FirClassLikeDeclaration) classLikeSymbol.getFir();
        FirClass firClass = firClassLikeDeclaration instanceof FirClass ? (FirClass) firClassLikeDeclaration : null;
        return (firClass != null ? firClass.getClassKind() : null) == ClassKind.INTERFACE;
    }

    default boolean isInterfaceOrAnnotationClass(KotlinTypeMarker kotlinTypeMarker) {
        ClassKind classKind;
        kotlinTypeMarker.getClass();
        FirRegularClass firRegularClass = toFirRegularClass(typeConstructor(kotlinTypeMarker));
        if (firRegularClass == null || (classKind = firRegularClass.getClassKind()) == null) {
            return false;
        }
        return classKind == ClassKind.ANNOTATION_CLASS || classKind == ClassKind.INTERFACE;
    }

    default boolean isIntersection(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return typeConstructorMarker instanceof ConeIntersectionType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    default boolean isLocalType(TypeConstructorMarker typeConstructorMarker) {
        FirClassLikeSymbol<?> symbol;
        typeConstructorMarker.getClass();
        return (typeConstructorMarker instanceof ConeClassLikeLookupTag) && (symbol = ToSymbolUtilsKt.toSymbol((ConeClassLikeLookupTag) typeConstructorMarker, getSession())) != null && ((FirClassLikeDeclaration) symbol.getFir()).getIsLocal();
    }

    default boolean isMarkedNullable(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return ConeTypeUtilsKt.isMarkedNullable(TypeExpansionUtilsKt.fullyExpandedType$default((ConeKotlinType) kotlinTypeMarker, getSession(), (Function1) null, 2, (Object) null));
        }
        w01.a("Failed requirement.");
        return false;
    }

    default boolean isMultiFieldValueClass(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        List<Pair<Name, ConeRigidType>> valueClassProperties = getValueClassProperties(typeConstructorMarker);
        if (valueClassProperties == null) {
            return false;
        }
        return isMultiFieldValueClassRecursionAware(typeConstructorMarker, valueClassProperties, new HashSet());
    }

    default boolean isNothingConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return (typeConstructorMarker instanceof ConeClassLikeLookupTag) && Intrinsics.areEqual(((ConeClassLikeLookupTag) typeConstructorMarker).getClassId(), StandardClassIds.INSTANCE.getNothing());
    }

    default boolean isNullableType(KotlinTypeMarker kotlinTypeMarker, boolean z) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return TypeUtilsKt.canBeNull$default((ConeKotlinType) kotlinTypeMarker, getSession(), z, null, 4, null);
        }
        w01.a("Failed requirement.");
        return false;
    }

    default boolean isOldCapturedType(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return false;
    }

    default boolean isPrimitiveType(SimpleTypeMarker simpleTypeMarker) {
        simpleTypeMarker.getClass();
        if (simpleTypeMarker instanceof ConeClassLikeType) {
            return ConeBuiltinTypeUtilsKt.isPrimitive((ConeKotlinType) simpleTypeMarker);
        }
        return false;
    }

    default boolean isRawType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return TypeUtilsKt.isRaw((ConeKotlinType) kotlinTypeMarker);
        }
        w01.a("Failed requirement.");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    default boolean isReified(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        if (typeParameterMarker instanceof ConeTypeParameterLookupTag) {
            return ((FirTypeParameter) ((ConeTypeParameterLookupTag) typeParameterMarker).getTypeParameterSymbol().getFir()).getIsReified();
        }
        w01.a("Failed requirement.");
        return false;
    }

    default boolean isSingleClassifierType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        if (isError((KotlinTypeMarker) rigidTypeMarker)) {
            return false;
        }
        if (!(rigidTypeMarker instanceof ConeRigidType)) {
            w01.a("Failed requirement.");
            return false;
        }
        ConeRigidType coneRigidType = (ConeRigidType) rigidTypeMarker;
        if (coneRigidType instanceof ConeLookupTagBasedType) {
            return m691typeConstructor(rigidTypeMarker) instanceof ConeClassifierLookupTag;
        }
        if (coneRigidType instanceof ConeCapturedType) {
            return true;
        }
        if ((coneRigidType instanceof ConeTypeVariableType) || (coneRigidType instanceof ConeIntersectionType)) {
            return false;
        }
        if ((coneRigidType instanceof ConeIntegerLiteralType) || (coneRigidType instanceof ConeStubType) || (coneRigidType instanceof ConeDefinitelyNotNullType)) {
            return true;
        }
        bu8.a();
        return false;
    }

    default boolean isStarProjection(TypeArgumentMarker typeArgumentMarker) {
        typeArgumentMarker.getClass();
        return typeArgumentMarker instanceof ConeStarProjection;
    }

    default boolean isStubType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return rigidTypeMarker instanceof ConeStubType;
    }

    default boolean isStubTypeForBuilderInference(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return false;
    }

    default boolean isStubTypeForVariableInSubtyping(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return rigidTypeMarker instanceof ConeStubTypeForTypeVariableInSubtyping;
    }

    default boolean isTypeVariableType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return kotlinTypeMarker instanceof ConeTypeVariableType;
    }

    default boolean isUnderKotlinPackage(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        FqNameUnsafe classFqNameUnsafe = getClassFqNameUnsafe(typeConstructorMarker);
        return classFqNameUnsafe != null && classFqNameUnsafe.startsWith(StandardClassIds.INSTANCE.getBASE_KOTLIN_PACKAGE().shortName());
    }

    default boolean isUninferredParameter(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return (kotlinTypeMarker instanceof ConeErrorType) && ((ConeErrorType) kotlinTypeMarker).getIsUninferredParameter();
    }

    /* JADX INFO: renamed from: lowerBound, reason: merged with bridge method [inline-methods] */
    default ConeRigidType m686lowerBound(FlexibleTypeMarker flexibleTypeMarker) {
        flexibleTypeMarker.getClass();
        if (flexibleTypeMarker instanceof ConeFlexibleType) {
            return ((ConeFlexibleType) flexibleTypeMarker).getLowerBound();
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: lowerType, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m687lowerType(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        if (capturedTypeMarker instanceof ConeCapturedType) {
            return ((ConeCapturedType) capturedTypeMarker).getConstructor().getLowerType();
        }
        w01.a("Failed requirement.");
        return null;
    }

    @Override // 
    /* JADX INFO: renamed from: nullableAnyType */
    default ConeClassLikeType mo649nullableAnyType() {
        return getSession().getBuiltinTypes().getNullableAnyType().getConeType();
    }

    /* JADX WARN: Multi-variable type inference failed */
    default int parametersCount(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        if (!(typeConstructorMarker instanceof ConeTypeConstructorMarker)) {
            w01.a("Failed requirement.");
            return 0;
        }
        ConeTypeConstructorMarker coneTypeConstructorMarker = (ConeTypeConstructorMarker) typeConstructorMarker;
        if (!(coneTypeConstructorMarker instanceof ConeCapturedTypeConstructor) && !(coneTypeConstructorMarker instanceof ConeTypeVariableTypeConstructor) && !(coneTypeConstructorMarker instanceof ConeIntersectionType)) {
            if (coneTypeConstructorMarker instanceof ConeClassifierLookupTag) {
                FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol((ConeClassifierLookupTag) typeConstructorMarker, getSession());
                if (symbol instanceof FirAnonymousObjectSymbol) {
                    return ((FirAnonymousObject) ((FirAnonymousObjectSymbol) symbol).getFir()).getTypeParameters().size();
                }
                if (symbol instanceof FirRegularClassSymbol) {
                    return ((FirRegularClass) ((FirRegularClassSymbol) symbol).getFir()).getTypeParameters().size();
                }
                if (symbol instanceof FirTypeAliasSymbol) {
                    return ((FirTypeAlias) ((FirTypeAliasSymbol) symbol).getFir()).getTypeParameters().size();
                }
                if (!(symbol instanceof FirTypeParameterSymbol) && symbol != null) {
                    bu8.a();
                }
                return 0;
            }
            if ((coneTypeConstructorMarker instanceof ConeIntegerLiteralType) || (coneTypeConstructorMarker instanceof ConeStubTypeConstructor)) {
                return 0;
            }
            bu8.a();
        }
        return 0;
    }

    default Collection<ConeClassLikeType> possibleIntegerTypes(RigidTypeMarker rigidTypeMarker) {
        Collection<ConeClassLikeType> possibleTypes;
        rigidTypeMarker.getClass();
        ConeIntegerLiteralType coneIntegerLiteralType = rigidTypeMarker instanceof ConeIntegerLiteralType ? (ConeIntegerLiteralType) rigidTypeMarker : null;
        return (coneIntegerLiteralType == null || (possibleTypes = coneIntegerLiteralType.getPossibleTypes()) == null) ? CollectionsKt.emptyList() : possibleTypes;
    }

    /* JADX INFO: renamed from: projection, reason: merged with bridge method [inline-methods] */
    default ConeTypeProjection m688projection(CapturedTypeConstructorMarker capturedTypeConstructorMarker) {
        capturedTypeConstructorMarker.getClass();
        if (capturedTypeConstructorMarker instanceof ConeCapturedTypeConstructor) {
            return ((ConeCapturedTypeConstructor) capturedTypeConstructorMarker).getProjection();
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: replaceType, reason: merged with bridge method [inline-methods] */
    default ConeKotlinTypeProjection m689replaceType(TypeArgumentMarker typeArgumentMarker, KotlinTypeMarker kotlinTypeMarker) {
        typeArgumentMarker.getClass();
        kotlinTypeMarker.getClass();
        if (!(typeArgumentMarker instanceof ConeKotlinTypeProjection)) {
            w01.a("Failed requirement.");
            return null;
        }
        if (!(kotlinTypeMarker instanceof ConeKotlinType)) {
            w01.a("Failed requirement.");
            return null;
        }
        ConeKotlinTypeProjection coneKotlinTypeProjection = (ConeKotlinTypeProjection) typeArgumentMarker;
        if (coneKotlinTypeProjection instanceof ConeKotlinType) {
            return (ConeKotlinTypeProjection) kotlinTypeMarker;
        }
        if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjectionOut) {
            return new ConeKotlinTypeProjectionOut((ConeKotlinType) kotlinTypeMarker);
        }
        if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjectionIn) {
            return new ConeKotlinTypeProjectionIn((ConeKotlinType) kotlinTypeMarker);
        }
        if (coneKotlinTypeProjection instanceof ConeKotlinTypeConflictingProjection) {
            return new ConeKotlinTypeConflictingProjection((ConeKotlinType) kotlinTypeMarker);
        }
        bu8.a();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    default TypeCheckerState.SupertypesPolicy substitutionSupertypePolicy(RigidTypeMarker type) {
        final ConeSubstitutor coneSubstitutorSubstitutorByMap$default;
        Object coneType;
        FirClassLikeSymbol<?> symbol;
        type.getClass();
        if (argumentsCount(type) == 0) {
            return TypeCheckerState.SupertypesPolicy.LowerIfFlexible.INSTANCE;
        }
        if (!(type instanceof ConeKotlinType)) {
            w01.a("Failed requirement.");
            return null;
        }
        FirClassLikeDeclaration firClassLikeDeclaration = (!(type instanceof ConeClassLikeType) || (symbol = ToSymbolUtilsKt.toSymbol(((ConeClassLikeType) type).getLookupTag(), getSession())) == null) ? null : (FirClassLikeDeclaration) symbol.getFir();
        if (firClassLikeDeclaration != null) {
            List<Pair> listZip = CollectionsKt.zip(firClassLikeDeclaration.getTypeParameters(), ((ConeKotlinType) type).getTypeArguments());
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listZip, 10)), 16));
            for (Pair pair : listZip) {
                FirTypeParameterRef firTypeParameterRef = (FirTypeParameterRef) pair.component1();
                ConeKotlinTypeProjection coneKotlinTypeProjection = (ConeTypeProjection) pair.component2();
                FirTypeParameterSymbol symbol2 = firTypeParameterRef.getSymbol();
                ConeKotlinTypeProjection coneKotlinTypeProjection2 = coneKotlinTypeProjection instanceof ConeKotlinTypeProjection ? coneKotlinTypeProjection : null;
                if (coneKotlinTypeProjection2 == null || (coneType = coneKotlinTypeProjection2.getType()) == null) {
                    coneType = getSession().getBuiltinTypes().getNullableAnyType().getConeType();
                }
                Pair pair2 = TuplesKt.to(symbol2, coneType);
                linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
            }
            coneSubstitutorSubstitutorByMap$default = ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap, getSession(), false, 4, null);
        } else {
            coneSubstitutorSubstitutorByMap$default = ConeSubstitutor.Empty.INSTANCE;
        }
        return new TypeCheckerState.SupertypesPolicy.DoCustomTransform() { // from class: org.jetbrains.kotlin.fir.types.ConeTypeContext.substitutionSupertypePolicy.1
            /* JADX INFO: renamed from: transformType, reason: merged with bridge method [inline-methods] */
            public ConeRigidType m696transformType(TypeCheckerState state, KotlinTypeMarker type2) {
                state.getClass();
                type2.getClass();
                ConeKotlinType coneKotlinTypeLowerBoundIfFlexible = ConeTypeContext.this.lowerBoundIfFlexible(type2);
                if (!(coneKotlinTypeLowerBoundIfFlexible instanceof ConeRigidType)) {
                    w01.a("Failed requirement.");
                    return null;
                }
                ConeKotlinType coneKotlinTypeSubstituteOrSelf = coneSubstitutorSubstitutorByMap$default.substituteOrSelf(coneKotlinTypeLowerBoundIfFlexible);
                coneKotlinTypeSubstituteOrSelf.getClass();
                return (ConeRigidType) coneKotlinTypeSubstituteOrSelf;
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    default Collection<ConeKotlinType> supertypes(TypeConstructorMarker typeConstructorMarker) {
        Collection<ConeKotlinType> collectionListOf;
        typeConstructorMarker.getClass();
        if (!(typeConstructorMarker instanceof ConeTypeConstructorMarker)) {
            w01.a("Failed requirement.");
            return null;
        }
        ConeTypeConstructorMarker coneTypeConstructorMarker = (ConeTypeConstructorMarker) typeConstructorMarker;
        if (coneTypeConstructorMarker instanceof ConeStubTypeConstructor) {
            return CollectionsKt.listOf(getSession().getBuiltinTypes().getNullableAnyType().getConeType());
        }
        if (coneTypeConstructorMarker instanceof ConeTypeVariableTypeConstructor) {
            return CollectionsKt.emptyList();
        }
        if (!(coneTypeConstructorMarker instanceof ConeClassifierLookupTag)) {
            if (coneTypeConstructorMarker instanceof ConeCapturedTypeConstructor) {
                List<ConeKotlinType> supertypes = ((ConeCapturedTypeConstructor) typeConstructorMarker).getSupertypes();
                if (supertypes == null) {
                    supertypes = CollectionsKt.emptyList();
                }
                return supertypes;
            }
            if (coneTypeConstructorMarker instanceof ConeIntersectionType) {
                return ((ConeIntersectionType) typeConstructorMarker).getIntersectedTypes();
            }
            if (coneTypeConstructorMarker instanceof ConeIntegerLiteralType) {
                return ((ConeIntegerLiteralType) typeConstructorMarker).getSupertypes();
            }
            bu8.a();
            return null;
        }
        FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol((ConeClassifierLookupTag) typeConstructorMarker, getSession());
        if (symbol != null) {
            FirLazyDeclarationResolverKt.lazyResolveToPhase(symbol, FirResolvePhase.TYPES);
        }
        if (symbol instanceof FirTypeParameterSymbol) {
            List<FirResolvedTypeRef> resolvedBounds = ((FirTypeParameterSymbol) symbol).getResolvedBounds();
            collectionListOf = new ArrayList<>(CollectionsKt.collectionSizeOrDefault(resolvedBounds, 10));
            Iterator<T> it = resolvedBounds.iterator();
            while (it.hasNext()) {
                collectionListOf.add(((FirResolvedTypeRef) it.next()).getConeType());
            }
        } else if (symbol instanceof FirClassSymbol) {
            collectionListOf = FirDeclarationUtilKt.getSuperConeTypes((FirClass) ((FirClassSymbol) symbol).getFir());
        } else if (symbol instanceof FirTypeAliasSymbol) {
            collectionListOf = CollectionsKt.listOfNotNull(FirDeclarationUtilKt.getExpandedConeType((FirTypeAlias) ((FirTypeAliasSymbol) symbol).getFir()));
        } else {
            if (symbol != null) {
                bu8.a();
                return null;
            }
            collectionListOf = CollectionsKt.listOf(getSession().getBuiltinTypes().getAnyType().getConeType());
        }
        return collectionListOf;
    }

    default FirClassLikeSymbol<?> toClassLikeSymbol(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        ConeClassLikeLookupTag coneClassLikeLookupTag = typeConstructorMarker instanceof ConeClassLikeLookupTag ? (ConeClassLikeLookupTag) typeConstructorMarker : null;
        if (coneClassLikeLookupTag != null) {
            return ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTag, getSession());
        }
        return null;
    }

    /* JADX INFO: renamed from: typeConstructor, reason: merged with bridge method [inline-methods] */
    default ConeTypeConstructorMarker m691typeConstructor(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        if (rigidTypeMarker instanceof ConeRigidType) {
            return ConeTypeUtilsKt.getConstructor((ConeRigidType) rigidTypeMarker);
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: unwrapStubTypeVariableConstructor, reason: merged with bridge method [inline-methods] */
    default ConeTypeConstructorMarker m692unwrapStubTypeVariableConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        if (!(typeConstructorMarker instanceof ConeTypeConstructorMarker)) {
            w01.a("Failed requirement.");
            return null;
        }
        if (!(typeConstructorMarker instanceof ConeStubTypeConstructor)) {
            return (ConeTypeConstructorMarker) typeConstructorMarker;
        }
        ConeStubTypeConstructor coneStubTypeConstructor = (ConeStubTypeConstructor) typeConstructorMarker;
        if (!coneStubTypeConstructor.isTypeVariableInSubtyping() && !coneStubTypeConstructor.isForFixation()) {
            return coneStubTypeConstructor.getVariable().getTypeConstructor();
        }
        return (ConeTypeConstructorMarker) typeConstructorMarker;
    }

    /* JADX INFO: renamed from: upperBound, reason: merged with bridge method [inline-methods] */
    default ConeRigidType m693upperBound(FlexibleTypeMarker flexibleTypeMarker) {
        flexibleTypeMarker.getClass();
        if (flexibleTypeMarker instanceof ConeFlexibleType) {
            return ((ConeFlexibleType) flexibleTypeMarker).getUpperBound();
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    default int upperBoundCount(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        if (typeParameterMarker instanceof ConeTypeParameterLookupTag) {
            return ((FirTypeParameter) ((ConeTypeParameterLookupTag) typeParameterMarker).getSymbol().getFir()).getBounds().size();
        }
        w01.a("Failed requirement.");
        return 0;
    }

    /* JADX INFO: renamed from: withNewTypeSince, reason: merged with bridge method [inline-methods] */
    default ConeKotlinType m694withNewTypeSince(KotlinTypeMarker kotlinTypeMarker, Object obj, KotlinTypeMarker kotlinTypeMarker2) {
        kotlinTypeMarker.getClass();
        obj.getClass();
        kotlinTypeMarker2.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return TypeWillChangeAttributeKt.withNewTypeSince((ConeKotlinType) kotlinTypeMarker, (LanguageFeature) obj, (ConeKotlinType) kotlinTypeMarker2);
        }
        w01.a("Failed requirement.");
        return null;
    }

    /* JADX INFO: renamed from: withNullability, reason: merged with bridge method [inline-methods] */
    default ConeRigidType m695withNullability(RigidTypeMarker rigidTypeMarker, boolean z) {
        rigidTypeMarker.getClass();
        if (!(rigidTypeMarker instanceof ConeKotlinType)) {
            w01.a("Failed requirement.");
            return null;
        }
        ConeKotlinType coneKotlinTypeWithNullability$default = TypeUtilsKt.withNullability$default(TypeExpansionUtilsKt.fullyExpandedType$default((ConeKotlinType) rigidTypeMarker, getSession(), (Function1) null, 2, (Object) null), z, TypeComponentsKt.getTypeContext(getSession()), null, false, 12, null);
        coneKotlinTypeWithNullability$default.getClass();
        return (ConeRigidType) coneKotlinTypeWithNullability$default;
    }

    /* JADX INFO: renamed from: intersectTypes, reason: collision with other method in class */
    /* bridge */ /* synthetic */ default SimpleTypeMarker m685intersectTypes(Collection collection) {
        return m683intersectTypes((Collection<? extends SimpleTypeMarker>) collection);
    }

    /* JADX INFO: renamed from: intersectTypes, reason: collision with other method in class */
    /* bridge */ /* synthetic */ default KotlinTypeMarker m684intersectTypes(Collection collection) {
        return intersectTypes((Collection<? extends KotlinTypeMarker>) collection);
    }

    default ConeKotlinType intersectTypes(Collection<? extends KotlinTypeMarker> types) {
        types.getClass();
        return ConeTypeIntersector.INSTANCE.intersectTypes((ConeInferenceContext) this, types);
    }

    /* JADX INFO: renamed from: typeConstructor, reason: merged with bridge method [inline-methods] */
    default ConeCapturedTypeConstructor m690typeConstructor(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        if (capturedTypeMarker instanceof ConeCapturedType) {
            return ((ConeCapturedType) capturedTypeMarker).getConstructor();
        }
        w01.a("Failed requirement.");
        return null;
    }

    default boolean isNullableType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return isNullableType(kotlinTypeMarker, true);
    }

    default boolean isError(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    default TypeVariance getVariance(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        if (typeParameterMarker instanceof ConeTypeParameterLookupTag) {
            return TypeSystemContextKt.convertVariance(((FirTypeParameter) ((ConeTypeParameterLookupTag) typeParameterMarker).getSymbol().getFir()).getVariance());
        }
        w01.a("Failed requirement.");
        return null;
    }
}
