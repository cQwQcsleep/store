package org.jetbrains.kotlin.fir.backend.jvm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.ClassBuilderMode;
import org.jetbrains.kotlin.codegen.serialization.JvmSerializationBindings;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.JvmDefaultMode;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.constant.KClassValue;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.jvm.FirJvmSerializerExtension;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.java.JavaUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.serialization.FirAdditionalMetadataProvider;
import org.jetbrains.kotlin.fir.serialization.FirElementAwareStringTable;
import org.jetbrains.kotlin.fir.serialization.FirElementSerializer;
import org.jetbrains.kotlin.fir.serialization.FirSerializerExtension;
import org.jetbrains.kotlin.fir.serialization.LocalClassIdOracle;
import org.jetbrains.kotlin.fir.serialization.SerializationUtilKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRawType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.jvm.JvmProtoBuf;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmFlags;
import org.jetbrains.kotlin.metadata.serialization.MutableVersionRequirementTable;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;
import org.jetbrains.kotlin.serialization.DescriptorSerializer;
import org.jetbrains.kotlin.types.AbstractTypeApproximator;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ð\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u008a\u00012\u00020\u0001:\u0002\u008a\u0001B\u007f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\r\u0012\u0006\u0010\u0013\u001a\u00020\r\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b¢\u0006\u0004\b\u001c\u0010\u001dBG\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u001e\u001a\u00020\u001f\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010 \u001a\u00020!\u0012\u0006\u0010\"\u001a\u00020#\u0012\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0004\b\u001c\u0010$J\b\u00105\u001a\u00020\rH\u0016J(\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020AH\u0016J\u0010\u0010B\u001a\u00020\r2\u0006\u0010:\u001a\u00020CH\u0002J(\u0010D\u001a\u0002092\u0006\u0010E\u001a\u00020F2\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020AH\u0016J(\u0010G\u001a\u0002092\u0006\u0010H\u001a\u00020I2\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020AH\u0016J\u0018\u0010J\u001a\u0002092\u0006\u0010<\u001a\u00020=2\u0006\u0010@\u001a\u00020AH\u0002J \u0010K\u001a\u0002092\u0006\u0010:\u001a\u00020;2\u0006\u0010L\u001a\u00020=2\u0006\u0010>\u001a\u00020?H\u0002J*\u0010M\u001a\u0002092\u0006\u0010N\u001a\u00020O2\u0006\u0010<\u001a\u00020P2\b\u0010>\u001a\u0004\u0018\u00010?2\u0006\u0010@\u001a\u00020AH\u0016J \u0010Q\u001a\u0002092\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020UH\u0016J\u001e\u0010W\u001a\u0002092\f\u0010X\u001a\b\u0012\u0004\u0012\u00020Y0\u00072\u0006\u0010<\u001a\u00020UH\u0016J\u0018\u0010Z\u001a\u0002092\u0006\u0010[\u001a\u00020\\2\u0006\u0010<\u001a\u00020]H\u0016J \u0010^\u001a\u0002092\u0006\u0010_\u001a\u00020`2\u0006\u0010<\u001a\u00020a2\u0006\u0010@\u001a\u00020AH\u0016J*\u0010b\u001a\u0002092\u0006\u0010c\u001a\u00020d2\u0006\u0010<\u001a\u00020e2\b\u0010>\u001a\u0004\u0018\u00010?2\u0006\u0010@\u001a\u00020AH\u0016J \u0010f\u001a\u000209*\u00020?2\u0012\u0010g\u001a\u000e\u0012\u0004\u0012\u00020i\u0012\u0004\u0012\u0002090hH\u0002J\f\u0010j\u001a\u00020\r*\u00020dH\u0002J*\u0010k\u001a\u0002092\u0006\u0010l\u001a\u00020\b2\u0006\u0010<\u001a\u00020m2\b\u0010>\u001a\u0004\u0018\u00010?2\u0006\u0010@\u001a\u00020AH\u0016J\f\u0010n\u001a\u00020\r*\u00020\bH\u0002J\u0016\u0010o\u001a\b\u0012\u0004\u0012\u00020p0\u00072\u0006\u0010:\u001a\u00020;H\u0016J\u0018\u0010q\u001a\u0002092\u0006\u0010r\u001a\u00020s2\u0006\u0010<\u001a\u00020tH\u0016J\u0018\u0010u\u001a\u0002092\u0006\u0010R\u001a\u00020v2\u0006\u0010L\u001a\u00020UH\u0016J\u0018\u0010w\u001a\u0002092\u0006\u0010x\u001a\u00020y2\u0006\u0010<\u001a\u00020zH\u0016JB\u0010{\u001a\u0004\u0018\u0001H|\"\b\b\u0000\u0010}*\u00020~\"\b\b\u0001\u0010|*\u00020~2\u0013\u0010\u007f\u001a\u000f\u0012\u0004\u0012\u0002H}\u0012\u0004\u0012\u0002H|0\u0080\u00012\u0007\u0010\u0081\u0001\u001a\u0002H}H\u0002¢\u0006\u0003\u0010\u0082\u0001JG\u0010\u0083\u0001\u001a\u0002092\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u00012\u0014\u0010\u0086\u0001\u001a\u000f\u0012\u0005\u0012\u00030\u0087\u0001\u0012\u0004\u0012\u0002090h2\u001a\b\u0002\u0010\u0088\u0001\u001a\u0013\u0012\u0007\u0012\u0005\u0018\u00010\u0089\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010hH\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00101\u001a\u0002028TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\u0014\u00106\u001a\u00020\r8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b6\u00107¨\u0006\u008b\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmSerializerExtension;", "Lorg/jetbrains/kotlin/fir/serialization/FirSerializerExtension;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "bindings", "Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings;", "localDelegatedProperties", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "globalBindings", "useTypeTable", Argument.Delimiters.none, "moduleName", Argument.Delimiters.none, "classBuilderMode", "Lorg/jetbrains/kotlin/codegen/ClassBuilderMode;", "isParamAssertionsDisabled", "unifiedNullChecks", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "jvmDefaultMode", "Lorg/jetbrains/kotlin/config/JvmDefaultMode;", "stringTable", "Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;", "additionalMetadataProvider", "Lorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings;Ljava/util/List;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings;ZLjava/lang/String;Lorg/jetbrains/kotlin/codegen/ClassBuilderMode;ZZLorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;Lorg/jetbrains/kotlin/config/JvmDefaultMode;Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;Lorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;)V", "state", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "approximator", "Lorg/jetbrains/kotlin/types/AbstractTypeApproximator;", "components", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings;Lorg/jetbrains/kotlin/codegen/state/GenerationState;Ljava/util/List;Lorg/jetbrains/kotlin/types/AbstractTypeApproximator;Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "getStringTable", "()Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;", "getAdditionalMetadataProvider", "()Lorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;", "signatureSerializer", "Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmSignatureSerializer;", "localClassIdOracle", "Lorg/jetbrains/kotlin/fir/serialization/LocalClassIdOracle;", "getLocalClassIdOracle", "()Lorg/jetbrains/kotlin/fir/serialization/LocalClassIdOracle;", "shouldUseTypeTable", "isOptionalAnnotationClassSerialization", "()Z", "serializeClass", Argument.Delimiters.none, "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class$Builder;", "versionRequirementTable", "Lorg/jetbrains/kotlin/metadata/serialization/MutableVersionRequirementTable;", "childSerializer", "Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer;", "isInCompatibilityMode", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "serializeScript", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "serializeSnippet", "snippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "processScriptOrSnippet", "writeVersionRequirementForJvmDefaultIfNeeded", "builder", "serializePackage", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Package$Builder;", "serializeFlexibleType", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "lowerProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type$Builder;", "upperProto", "serializeTypeAnnotations", "annotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "serializeTypeParameter", "typeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter$Builder;", "serializeConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor$Builder;", "serializeFunction", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function$Builder;", "writeInlineParameterNullCheckRequirement", "add", "Lkotlin/Function1;", Argument.Delimiters.none, "needsInlineParameterNullCheckRequirement", "serializeProperty", "property", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", "isJvmFieldPropertyInInterfaceCompanion", "getClassSupertypes", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "serializeValueParameter", "parameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$ValueParameter$Builder;", "serializeErrorType", "Lorg/jetbrains/kotlin/fir/types/ConeErrorType;", "serializeEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$EnumEntry$Builder;", "getBinding", "V", "K", Argument.Delimiters.none, "slice", "Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings$SerializationMappingSlice;", "key", "(Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings$SerializationMappingSlice;Ljava/lang/Object;)Ljava/lang/Object;", "serializeAnnotations", "declaration", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "addAnnotation", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;", "matchUseSiteTarget", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "Companion", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirJvmSerializerExtension extends FirSerializerExtension {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final JvmSerializationBindings.SerializationMappingSlice<FirVariable, Method> DELEGATE_METHOD_FOR_FIR_VARIABLE;
    private static final JvmSerializationBindings.SerializationMappingSlice<FirProperty, Pair<Type, String>> FIELD_FOR_PROPERTY;
    private static final JvmSerializationBindings.SerializationMappingSlice<FirFunction, Method> METHOD_FOR_FIR_FUNCTION;
    private static final JvmSerializationBindings.SerializationMappingSlice<FirVariable, Method> SYNTHETIC_METHOD_FOR_FIR_VARIABLE;
    private final FirAdditionalMetadataProvider additionalMetadataProvider;
    private final JvmSerializationBindings bindings;
    private final ClassBuilderMode classBuilderMode;
    private final JvmSerializationBindings globalBindings;
    private final boolean isParamAssertionsDisabled;
    private final JvmDefaultMode jvmDefaultMode;
    private final List<FirProperty> localDelegatedProperties;
    private final BinaryVersion metadataVersion;
    private final String moduleName;
    private final ScopeSession scopeSession;
    private final FirSession session;
    private final FirJvmSignatureSerializer signatureSerializer;
    private final FirElementAwareStringTable stringTable;
    private final boolean unifiedNullChecks;
    private final boolean useTypeTable;

    static {
        JvmSerializationBindings.SerializationMappingSlice<FirFunction, Method> serializationMappingSliceCreate = JvmSerializationBindings.SerializationMappingSlice.create();
        serializationMappingSliceCreate.getClass();
        METHOD_FOR_FIR_FUNCTION = serializationMappingSliceCreate;
        JvmSerializationBindings.SerializationMappingSlice<FirProperty, Pair<Type, String>> serializationMappingSliceCreate2 = JvmSerializationBindings.SerializationMappingSlice.create();
        serializationMappingSliceCreate2.getClass();
        FIELD_FOR_PROPERTY = serializationMappingSliceCreate2;
        JvmSerializationBindings.SerializationMappingSlice<FirVariable, Method> serializationMappingSliceCreate3 = JvmSerializationBindings.SerializationMappingSlice.create();
        serializationMappingSliceCreate3.getClass();
        SYNTHETIC_METHOD_FOR_FIR_VARIABLE = serializationMappingSliceCreate3;
        JvmSerializationBindings.SerializationMappingSlice<FirVariable, Method> serializationMappingSliceCreate4 = JvmSerializationBindings.SerializationMappingSlice.create();
        serializationMappingSliceCreate4.getClass();
        DELEGATE_METHOD_FOR_FIR_VARIABLE = serializationMappingSliceCreate4;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FirJvmSerializerExtension(FirSession firSession, JvmSerializationBindings jvmSerializationBindings, GenerationState generationState, List<? extends FirProperty> list, AbstractTypeApproximator abstractTypeApproximator, Fir2IrComponents fir2IrComponents, FirElementAwareStringTable firElementAwareStringTable) {
        this(firSession, jvmSerializationBindings, list, fir2IrComponents.getScopeSession(), generationState.getGlobalSerializationBindings(), generationState.getConfig().getUseTypeTableInSerializer(), generationState.getModuleName(), generationState.getClassBuilderMode(), generationState.getConfig().getIsParamAssertionsDisabled(), generationState.getConfig().getUnifiedNullChecks(), generationState.getConfig().getMetadataVersion(), generationState.getConfig().getJvmDefaultMode(), firElementAwareStringTable, fir2IrComponents.getAnnotationsFromPluginRegistrar().createAdditionalMetadataProvider());
        firSession.getClass();
        jvmSerializationBindings.getClass();
        generationState.getClass();
        list.getClass();
        abstractTypeApproximator.getClass();
        fir2IrComponents.getClass();
        firElementAwareStringTable.getClass();
    }

    private final <K, V> V getBinding(JvmSerializationBindings.SerializationMappingSlice<K, V> slice, K key) {
        V v = (V) this.bindings.get(slice, key);
        return v == null ? (V) this.globalBindings.get(slice, key) : v;
    }

    private final boolean isInCompatibilityMode(FirRegularClass klass) {
        if (this.jvmDefaultMode != JvmDefaultMode.ENABLE || FirAnnotationUtilsKt.hasAnnotation((FirDeclaration) klass, JvmStandardClassIds.INSTANCE.getJVM_DEFAULT_WITHOUT_COMPATIBILITY_CLASS_ID(), getSession())) {
            return this.jvmDefaultMode == JvmDefaultMode.NO_COMPATIBILITY && FirAnnotationUtilsKt.hasAnnotation((FirDeclaration) klass, JvmStandardClassIds.INSTANCE.getJVM_DEFAULT_WITH_COMPATIBILITY_CLASS_ID(), getSession());
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isJvmFieldPropertyInInterfaceCompanion(FirProperty firProperty) {
        FirRegularClassSymbol regularClassSymbolByClassId;
        ConeClassLikeLookupTag classLikeLookupTagIfAny;
        if (!JavaUtilsKt.hasJvmFieldAnnotation(firProperty, getSession())) {
            return false;
        }
        ConeSimpleKotlinType dispatchReceiverType = firProperty.getDispatchReceiverType();
        FirRegularClass firRegularClass = null;
        FirRegularClassSymbol regularClassSymbol = (dispatchReceiverType == null || (classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(dispatchReceiverType)) == null) ? null : ToSymbolUtilsKt.toRegularClassSymbol(classLikeLookupTagIfAny, getSession());
        if (regularClassSymbol != null && regularClassSymbol.getRawStatus().isCompanion() && !((FirClassLikeDeclaration) regularClassSymbol.getFir()).getIsLocal()) {
            ClassId outerClassId = regularClassSymbol.getClassId().getOuterClassId();
            if (outerClassId != null && (regularClassSymbolByClassId = FirSymbolProviderKt.getRegularClassSymbolByClassId(getSession(), outerClassId)) != null) {
                firRegularClass = (FirRegularClass) regularClassSymbolByClassId.getFir();
            }
            if (firRegularClass != null && (firRegularClass.getClassKind() == ClassKind.INTERFACE || firRegularClass.getClassKind() == ClassKind.ANNOTATION_CLASS)) {
                return true;
            }
        }
        return false;
    }

    private final boolean needsInlineParameterNullCheckRequirement(FirFunction firFunction) {
        FirReceiverParameter receiverParameter;
        FirTypeRef typeRef;
        ConeKotlinType coneType;
        if (!(firFunction instanceof FirNamedFunction) || !firFunction.getStatus().isInline() || firFunction.getStatus().isSuspend() || this.isParamAssertionsDisabled || Visibilities.INSTANCE.isPrivate(firFunction.getStatus().getVisibility())) {
            return false;
        }
        FirNamedFunction firNamedFunction = (FirNamedFunction) firFunction;
        List<FirValueParameter> valueParameters = firNamedFunction.getValueParameters();
        if ((valueParameters instanceof Collection) && valueParameters.isEmpty()) {
            receiverParameter = firNamedFunction.getReceiverParameter();
            return receiverParameter != null ? false : false;
        }
        Iterator<T> it = valueParameters.iterator();
        while (it.hasNext()) {
            if (FunctionalTypeUtilsKt.isSomeFunctionType(FirTypeUtilsKt.getConeType(((FirValueParameter) it.next()).getReturnTypeRef()), getSession())) {
            }
        }
        receiverParameter = firNamedFunction.getReceiverParameter();
        if (receiverParameter != null || (typeRef = receiverParameter.getTypeRef()) == null || (coneType = FirTypeUtilsKt.getConeType(typeRef)) == null || !FunctionalTypeUtilsKt.isSomeFunctionType(coneType, getSession())) {
            return false;
        }
        return true;
    }

    private final void processScriptOrSnippet(ProtoBuf.Class.Builder proto, FirElementSerializer childSerializer) {
        ProtoBuf.Property propertyBuild;
        if (!Intrinsics.areEqual(this.moduleName, "main")) {
            proto.setExtension(JvmProtoBuf.classModuleName, Integer.valueOf(this.stringTable.getStringIndex(this.moduleName)));
        }
        for (FirProperty firProperty : this.localDelegatedProperties) {
            GeneratedMessageLite.GeneratedExtension generatedExtension = JvmProtoBuf.classLocalVariable;
            ProtoBuf.Property.Builder builderPropertyProto = childSerializer.propertyProto(firProperty);
            if (builderPropertyProto != null && (propertyBuild = builderPropertyProto.build()) != null) {
                proto.addExtension(generatedExtension, propertyBuild);
            }
        }
    }

    private final void serializeAnnotations(FirAnnotationContainer declaration, Function1<? super ProtoBuf.Annotation, Unit> addAnnotation, Function1<? super AnnotationUseSiteTarget, Boolean> matchUseSiteTarget) {
        if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).supportsFeature(LanguageFeature.AnnotationsInMetadata) || CollectionsKt.contains(this.localDelegatedProperties, declaration) || isOptionalAnnotationClassSerialization()) {
            List<FirAnnotation> listAllRequiredAnnotations = declaration != null ? SerializationUtilKt.allRequiredAnnotations(declaration, getSession(), getAdditionalMetadataProvider()) : null;
            if (listAllRequiredAnnotations == null) {
                listAllRequiredAnnotations = CollectionsKt.emptyList();
            }
            for (FirAnnotation firAnnotation : listAllRequiredAnnotations) {
                if (matchUseSiteTarget == null || ((Boolean) matchUseSiteTarget.invoke(firAnnotation.getUseSiteTarget())).booleanValue()) {
                    ProtoBuf.Annotation annotationSerializeAnnotation = getAnnotationSerializer().serializeAnnotation(firAnnotation);
                    if (annotationSerializeAnnotation != null) {
                        addAnnotation.invoke(annotationSerializeAnnotation);
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void serializeAnnotations$default(FirJvmSerializerExtension firJvmSerializerExtension, FirAnnotationContainer firAnnotationContainer, Function1 function1, Function1 function2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: serializeAnnotations");
            return;
        }
        if ((i & 4) != 0) {
            function2 = null;
        }
        firJvmSerializerExtension.serializeAnnotations(firAnnotationContainer, function1, function2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean serializeProperty$lambda$3$0(AnnotationUseSiteTarget annotationUseSiteTarget) {
        return annotationUseSiteTarget != AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean serializeProperty$lambda$3$1(AnnotationUseSiteTarget annotationUseSiteTarget) {
        return annotationUseSiteTarget == AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD;
    }

    private final void writeInlineParameterNullCheckRequirement(MutableVersionRequirementTable mutableVersionRequirementTable, Function1<? super Integer, Unit> function1) {
        if (this.unifiedNullChecks) {
            function1.invoke(Integer.valueOf(DescriptorSerializer.Companion.writeVersionRequirement(1, 3, 50, ProtoBuf.VersionRequirement.VersionKind.COMPILER_VERSION, mutableVersionRequirementTable)));
        }
    }

    private final void writeVersionRequirementForJvmDefaultIfNeeded(FirClass klass, ProtoBuf.Class.Builder builder, MutableVersionRequirementTable versionRequirementTable) {
        if ((klass instanceof FirRegularClass) && ((FirRegularClass) klass).getClassKind() == ClassKind.INTERFACE && this.jvmDefaultMode == JvmDefaultMode.NO_COMPATIBILITY) {
            builder.addVersionRequirement(DescriptorSerializer.Companion.writeVersionRequirement(1, 4, 0, ProtoBuf.VersionRequirement.VersionKind.COMPILER_VERSION, versionRequirementTable));
        }
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public FirAdditionalMetadataProvider getAdditionalMetadataProvider() {
        return this.additionalMetadataProvider;
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public List<FirTypeRef> getClassSupertypes(FirClass klass) {
        klass.getClass();
        if (this.classBuilderMode != ClassBuilderMode.KAPT3) {
            return super.getClassSupertypes(klass);
        }
        List<FirTypeRef> classSupertypes = super.getClassSupertypes(klass);
        List arrayList = new ArrayList();
        for (Object obj : classSupertypes) {
            if (!(FirTypeUtilsKt.getConeType((FirTypeRef) obj) instanceof ConeErrorType)) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            arrayList = CollectionsKt.listOf(getSession().getBuiltinTypes().getAnyType());
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public LocalClassIdOracle getLocalClassIdOracle() {
        return new LocalClassIdOracle() { // from class: org.jetbrains.kotlin.fir.backend.jvm.FirJvmSerializerExtension$localClassIdOracle$1
            @Override // org.jetbrains.kotlin.fir.serialization.LocalClassIdOracle
            public ClassId getLocalClassId(KClassValue.Value.LocalClass klass) {
                FqName localClassJvmType;
                klass.getClass();
                Object firClassSymbol = klass.getFirClassSymbol();
                FirClassSymbol firClassSymbol2 = firClassSymbol instanceof FirClassSymbol ? (FirClassSymbol) firClassSymbol : null;
                if (firClassSymbol2 == null || (localClassJvmType = ClassMembersKt.getLocalClassJvmType((FirClassSymbol<?>) firClassSymbol2)) == null) {
                    return null;
                }
                return new ClassId(localClassJvmType.parent(), FqName.Companion.topLevel(localClassJvmType.shortName()), true);
            }
        };
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public BinaryVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public final FirElementAwareStringTable getStringTable() {
        return this.stringTable;
    }

    public boolean isOptionalAnnotationClassSerialization() {
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeClass(FirClass klass, ProtoBuf.Class.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        ProtoBuf.Property propertyBuild;
        klass.getClass();
        proto.getClass();
        versionRequirementTable.getClass();
        childSerializer.getClass();
        if (!Intrinsics.areEqual(this.moduleName, "main")) {
            proto.setExtension(JvmProtoBuf.classModuleName, Integer.valueOf(this.stringTable.getStringIndex(this.moduleName)));
        }
        for (FirProperty firProperty : this.localDelegatedProperties) {
            GeneratedMessageLite.GeneratedExtension generatedExtension = JvmProtoBuf.classLocalVariable;
            ProtoBuf.Property.Builder builderPropertyProto = childSerializer.propertyProto(firProperty);
            if (builderPropertyProto != null && (propertyBuild = builderPropertyProto.build()) != null) {
                proto.addExtension(generatedExtension, propertyBuild);
            }
        }
        writeVersionRequirementForJvmDefaultIfNeeded(klass, proto, versionRequirementTable);
        if (this.jvmDefaultMode.isEnabled() && (klass instanceof FirRegularClass)) {
            FirRegularClass firRegularClass = (FirRegularClass) klass;
            if (firRegularClass.getClassKind() == ClassKind.INTERFACE) {
                proto.setExtension(JvmProtoBuf.jvmClassFlags, Integer.valueOf(JvmFlags.INSTANCE.getClassFlags(true, isInCompatibilityMode(firRegularClass))));
            }
        }
        serializeAnnotations$default(this, klass, new AnonymousClass1(proto), null, 4, null);
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeConstructor(FirConstructor constructor, ProtoBuf.Constructor.Builder proto, FirElementSerializer childSerializer) {
        JvmProtoBuf.JvmMethodSignature jvmMethodSignatureMethodSignature;
        constructor.getClass();
        proto.getClass();
        childSerializer.getClass();
        Method method = (Method) getBinding(METHOD_FOR_FIR_FUNCTION, constructor);
        if (method != null && (jvmMethodSignatureMethodSignature = this.signatureSerializer.methodSignature(constructor, (Name) null, method)) != null) {
            proto.setExtension(JvmProtoBuf.constructorSignature, jvmMethodSignatureMethodSignature);
        }
        serializeAnnotations$default(this, constructor, new C00121(proto), null, 4, null);
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeEnumEntry(FirEnumEntry enumEntry, ProtoBuf.EnumEntry.Builder proto) {
        enumEntry.getClass();
        proto.getClass();
        serializeAnnotations$default(this, enumEntry, new C00131(proto), null, 4, null);
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeErrorType(ConeErrorType type, ProtoBuf.Type.Builder builder) {
        type.getClass();
        builder.getClass();
        if (this.classBuilderMode == ClassBuilderMode.KAPT3) {
            builder.setClassName(this.stringTable.getStringIndex("error/NonExistentClass"));
        } else {
            super.serializeErrorType(type, builder);
        }
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeFlexibleType(ConeFlexibleType type, ProtoBuf.Type.Builder lowerProto, ProtoBuf.Type.Builder upperProto) {
        type.getClass();
        lowerProto.getClass();
        upperProto.getClass();
        lowerProto.setFlexibleTypeCapabilitiesId(this.stringTable.getStringIndex("kotlin.jvm.PlatformType"));
        if (type instanceof ConeRawType) {
            GeneratedMessageLite.GeneratedExtension generatedExtension = JvmProtoBuf.isRaw;
            Boolean bool = Boolean.TRUE;
            lowerProto.setExtension(generatedExtension, bool);
            upperProto.setExtension(generatedExtension, bool);
        }
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeFunction(FirFunction function, ProtoBuf.Function.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        function.getClass();
        proto.getClass();
        childSerializer.getClass();
        Method method = (Method) getBinding(METHOD_FOR_FIR_FUNCTION, function);
        if (method != null) {
            FirJvmSignatureSerializer firJvmSignatureSerializer = this.signatureSerializer;
            FirNamedFunction firNamedFunction = function instanceof FirNamedFunction ? (FirNamedFunction) function : null;
            JvmProtoBuf.JvmMethodSignature jvmMethodSignatureMethodSignature = firJvmSignatureSerializer.methodSignature(function, firNamedFunction != null ? firNamedFunction.getName() : null, method);
            if (jvmMethodSignatureMethodSignature != null) {
                proto.setExtension(JvmProtoBuf.methodSignature, jvmMethodSignatureMethodSignature);
            }
        }
        if (needsInlineParameterNullCheckRequirement(function) && versionRequirementTable != null) {
            writeInlineParameterNullCheckRequirement(versionRequirementTable, new C00141(proto));
        }
        serializeAnnotations$default(this, function, new AnonymousClass2(proto), null, 4, null);
        FirReceiverParameter receiverParameter = function.getReceiverParameter();
        if (receiverParameter != null) {
            serializeAnnotations$default(this, receiverParameter, new FirJvmSerializerExtension$serializeFunction$3$1(proto), null, 4, null);
        }
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializePackage(FqName packageFqName, ProtoBuf.Package.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        ProtoBuf.Property propertyBuild;
        packageFqName.getClass();
        proto.getClass();
        childSerializer.getClass();
        if (!Intrinsics.areEqual(this.moduleName, "main")) {
            proto.setExtension(JvmProtoBuf.packageModuleName, Integer.valueOf(this.stringTable.getStringIndex(this.moduleName)));
        }
        for (FirProperty firProperty : this.localDelegatedProperties) {
            GeneratedMessageLite.GeneratedExtension generatedExtension = JvmProtoBuf.packageLocalVariable;
            ProtoBuf.Property.Builder builderPropertyProto = childSerializer.propertyProto(firProperty);
            if (builderPropertyProto != null && (propertyBuild = builderPropertyProto.build()) != null) {
                proto.addExtension(generatedExtension, propertyBuild);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeProperty(FirProperty property, ProtoBuf.Property.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        FirJvmSerializerExtension firJvmSerializerExtension;
        Type type;
        String descriptor;
        Type type2;
        property.getClass();
        proto.getClass();
        childSerializer.getClass();
        FirPropertyAccessor getter = property.getGetter();
        FirPropertyAccessor setter = property.getSetter();
        Method method = getter == null ? null : (Method) getBinding(METHOD_FOR_FIR_FUNCTION, getter);
        Method method2 = setter == null ? null : (Method) getBinding(METHOD_FOR_FIR_FUNCTION, setter);
        Pair pair = (Pair) getBinding(FIELD_FOR_PROPERTY, property);
        Method method3 = (Method) getBinding(SYNTHETIC_METHOD_FOR_FIR_VARIABLE, property);
        Method method4 = (Method) getBinding(DELEGATE_METHOD_FOR_FIR_VARIABLE, property);
        property.getDelegate();
        JvmProtoBuf.JvmPropertySignature jvmPropertySignaturePropertySignature = this.signatureSerializer.propertySignature(property.getName(), pair != null ? (String) pair.getSecond() : null, (pair == null || (type2 = (Type) pair.getFirst()) == null) ? null : type2.getDescriptor(), method3 != null ? this.signatureSerializer.methodSignature((Object) null, (Name) null, method3) : null, method4 != null ? this.signatureSerializer.methodSignature((Object) null, (Name) null, method4) : null, method != null ? this.signatureSerializer.methodSignature((Object) null, (Name) null, method) : null, method2 != null ? this.signatureSerializer.methodSignature((Object) null, (Name) null, method2) : null, (pair == null || (type = (Type) pair.getFirst()) == null || (descriptor = type.getDescriptor()) == null) ? false : this.signatureSerializer.requiresPropertySignature(property, descriptor));
        if (jvmPropertySignaturePropertySignature != null) {
            proto.setExtension(JvmProtoBuf.propertySignature, jvmPropertySignaturePropertySignature);
        }
        if (isJvmFieldPropertyInInterfaceCompanion(property) && versionRequirementTable != null) {
            proto.setExtension(JvmProtoBuf.flags, Integer.valueOf(JvmFlags.INSTANCE.getPropertyFlags(true)));
        }
        if (((getter != null && needsInlineParameterNullCheckRequirement(getter)) || (setter != null && needsInlineParameterNullCheckRequirement(setter))) && versionRequirementTable != null) {
            writeInlineParameterNullCheckRequirement(versionRequirementTable, new C00152(proto));
        }
        serializeAnnotations$default(this, getter, new AnonymousClass3(proto), null, 4, null);
        serializeAnnotations$default(this, setter, new AnonymousClass4(proto), null, 4, null);
        serializeAnnotations$default(this, property, new AnonymousClass5(proto), null, 4, null);
        FirReceiverParameter receiverParameter = property.getReceiverParameter();
        if (receiverParameter != null) {
            firJvmSerializerExtension = this;
            serializeAnnotations$default(firJvmSerializerExtension, receiverParameter, new FirJvmSerializerExtension$serializeProperty$6$1(proto), null, 4, null);
        } else {
            firJvmSerializerExtension = this;
        }
        FirBackingField backingField = property.getBackingField();
        if (backingField != null) {
            firJvmSerializerExtension.serializeAnnotations(backingField, new FirJvmSerializerExtension$serializeProperty$7$1(proto), new Function1() { // from class: y95
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(FirJvmSerializerExtension.serializeProperty$lambda$3$0((AnnotationUseSiteTarget) obj));
                }
            });
            firJvmSerializerExtension.serializeAnnotations(backingField, new FirJvmSerializerExtension$serializeProperty$7$3(proto), new Function1() { // from class: z95
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(FirJvmSerializerExtension.serializeProperty$lambda$3$1((AnnotationUseSiteTarget) obj));
                }
            });
        }
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeScript(FirScript script, ProtoBuf.Class.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        script.getClass();
        proto.getClass();
        versionRequirementTable.getClass();
        childSerializer.getClass();
        processScriptOrSnippet(proto, childSerializer);
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeSnippet(FirReplSnippet snippet, ProtoBuf.Class.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        snippet.getClass();
        proto.getClass();
        versionRequirementTable.getClass();
        childSerializer.getClass();
        processScriptOrSnippet(proto, childSerializer);
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeTypeAnnotations(List<? extends FirAnnotation> annotations, ProtoBuf.Type.Builder proto) {
        annotations.getClass();
        proto.getClass();
        Iterator<? extends FirAnnotation> it = annotations.iterator();
        while (it.hasNext()) {
            ProtoBuf.Annotation annotationSerializeAnnotation = getAnnotationSerializer().serializeAnnotation(it.next());
            if (annotationSerializeAnnotation != null) {
                proto.addAnnotation(annotationSerializeAnnotation);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeTypeParameter(FirTypeParameter typeParameter, ProtoBuf.TypeParameter.Builder proto) {
        typeParameter.getClass();
        proto.getClass();
        Iterator<FirAnnotation> it = FirAnnotationUtilsKt.nonSourceAnnotations(typeParameter, getSession()).iterator();
        while (it.hasNext()) {
            ProtoBuf.Annotation annotationSerializeAnnotation = getAnnotationSerializer().serializeAnnotation(it.next());
            if (annotationSerializeAnnotation != null) {
                proto.addAnnotation(annotationSerializeAnnotation);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeValueParameter(FirValueParameter parameter, ProtoBuf.ValueParameter.Builder proto) {
        parameter.getClass();
        proto.getClass();
        serializeAnnotations$default(this, parameter, new C00161(proto), null, 4, null);
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    /* JADX INFO: renamed from: shouldUseTypeTable, reason: from getter */
    public boolean getUseTypeTable() {
        return this.useTypeTable;
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR)\u0010\n\u001a\u001a\u0012\u0004\u0012\u00020\u000b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\tR\u001d\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\t¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmSerializerExtension$Companion;", Argument.Delimiters.none, "<init>", "()V", "METHOD_FOR_FIR_FUNCTION", "Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings$SerializationMappingSlice;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/org/objectweb/asm/commons/Method;", "getMETHOD_FOR_FIR_FUNCTION", "()Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings$SerializationMappingSlice;", "FIELD_FOR_PROPERTY", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lkotlin/Pair;", "Lorg/jetbrains/org/objectweb/asm/Type;", Argument.Delimiters.none, "getFIELD_FOR_PROPERTY", "SYNTHETIC_METHOD_FOR_FIR_VARIABLE", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "getSYNTHETIC_METHOD_FOR_FIR_VARIABLE", "DELEGATE_METHOD_FOR_FIR_VARIABLE", "getDELEGATE_METHOD_FOR_FIR_VARIABLE", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final JvmSerializationBindings.SerializationMappingSlice<FirVariable, Method> getDELEGATE_METHOD_FOR_FIR_VARIABLE() {
            return FirJvmSerializerExtension.DELEGATE_METHOD_FOR_FIR_VARIABLE;
        }

        public final JvmSerializationBindings.SerializationMappingSlice<FirProperty, Pair<Type, String>> getFIELD_FOR_PROPERTY() {
            return FirJvmSerializerExtension.FIELD_FOR_PROPERTY;
        }

        public final JvmSerializationBindings.SerializationMappingSlice<FirFunction, Method> getMETHOD_FOR_FIR_FUNCTION() {
            return FirJvmSerializerExtension.METHOD_FOR_FIR_FUNCTION;
        }

        public final JvmSerializationBindings.SerializationMappingSlice<FirVariable, Method> getSYNTHETIC_METHOD_FOR_FIR_VARIABLE() {
            return FirJvmSerializerExtension.SYNTHETIC_METHOD_FOR_FIR_VARIABLE;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.backend.jvm.FirJvmSerializerExtension$serializeClass$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public AnonymousClass1(Object obj) {
            super(1, obj, ProtoBuf.Class.Builder.class, "addAnnotation", "addAnnotation(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ProtoBuf.Annotation) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ProtoBuf.Annotation annotation) {
            ((ProtoBuf.Class.Builder) ((AdaptedFunctionReference) this).receiver).addAnnotation(annotation);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.backend.jvm.FirJvmSerializerExtension$serializeConstructor$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00121 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public C00121(Object obj) {
            super(1, obj, ProtoBuf.Constructor.Builder.class, "addAnnotation", "addAnnotation(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ProtoBuf.Annotation) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ProtoBuf.Annotation annotation) {
            ((ProtoBuf.Constructor.Builder) ((AdaptedFunctionReference) this).receiver).addAnnotation(annotation);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.backend.jvm.FirJvmSerializerExtension$serializeEnumEntry$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00131 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public C00131(Object obj) {
            super(1, obj, ProtoBuf.EnumEntry.Builder.class, "addAnnotation", "addAnnotation(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$EnumEntry$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ProtoBuf.Annotation) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ProtoBuf.Annotation annotation) {
            ((ProtoBuf.EnumEntry.Builder) ((AdaptedFunctionReference) this).receiver).addAnnotation(annotation);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.backend.jvm.FirJvmSerializerExtension$serializeFunction$2, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass2 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public AnonymousClass2(Object obj) {
            super(1, obj, ProtoBuf.Function.Builder.class, "addAnnotation", "addAnnotation(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ProtoBuf.Annotation) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ProtoBuf.Annotation annotation) {
            ((ProtoBuf.Function.Builder) ((AdaptedFunctionReference) this).receiver).addAnnotation(annotation);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.backend.jvm.FirJvmSerializerExtension$serializeProperty$3, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass3 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public AnonymousClass3(Object obj) {
            super(1, obj, ProtoBuf.Property.Builder.class, "addGetterAnnotation", "addGetterAnnotation(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ProtoBuf.Annotation) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ProtoBuf.Annotation annotation) {
            ((ProtoBuf.Property.Builder) ((AdaptedFunctionReference) this).receiver).addGetterAnnotation(annotation);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.backend.jvm.FirJvmSerializerExtension$serializeProperty$4, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass4 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public AnonymousClass4(Object obj) {
            super(1, obj, ProtoBuf.Property.Builder.class, "addSetterAnnotation", "addSetterAnnotation(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ProtoBuf.Annotation) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ProtoBuf.Annotation annotation) {
            ((ProtoBuf.Property.Builder) ((AdaptedFunctionReference) this).receiver).addSetterAnnotation(annotation);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.backend.jvm.FirJvmSerializerExtension$serializeProperty$5, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass5 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public AnonymousClass5(Object obj) {
            super(1, obj, ProtoBuf.Property.Builder.class, "addAnnotation", "addAnnotation(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ProtoBuf.Annotation) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ProtoBuf.Annotation annotation) {
            ((ProtoBuf.Property.Builder) ((AdaptedFunctionReference) this).receiver).addAnnotation(annotation);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.backend.jvm.FirJvmSerializerExtension$serializeValueParameter$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00161 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public C00161(Object obj) {
            super(1, obj, ProtoBuf.ValueParameter.Builder.class, "addAnnotation", "addAnnotation(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$ValueParameter$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ProtoBuf.Annotation) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ProtoBuf.Annotation annotation) {
            ((ProtoBuf.ValueParameter.Builder) ((AdaptedFunctionReference) this).receiver).addAnnotation(annotation);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.backend.jvm.FirJvmSerializerExtension$serializeFunction$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00141 extends AdaptedFunctionReference implements Function1<Integer, Unit> {
        public C00141(Object obj) {
            super(1, obj, ProtoBuf.Function.Builder.class, "addVersionRequirement", "addVersionRequirement(I)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i) {
            ((ProtoBuf.Function.Builder) ((AdaptedFunctionReference) this).receiver).addVersionRequirement(i);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.backend.jvm.FirJvmSerializerExtension$serializeProperty$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00152 extends AdaptedFunctionReference implements Function1<Integer, Unit> {
        public C00152(Object obj) {
            super(1, obj, ProtoBuf.Property.Builder.class, "addVersionRequirement", "addVersionRequirement(I)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i) {
            ((ProtoBuf.Property.Builder) ((AdaptedFunctionReference) this).receiver).addVersionRequirement(i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FirJvmSerializerExtension(FirSession firSession, JvmSerializationBindings jvmSerializationBindings, List<? extends FirProperty> list, ScopeSession scopeSession, JvmSerializationBindings jvmSerializationBindings2, boolean z, String str, ClassBuilderMode classBuilderMode, boolean z2, boolean z3, BinaryVersion binaryVersion, JvmDefaultMode jvmDefaultMode, FirElementAwareStringTable firElementAwareStringTable, FirAdditionalMetadataProvider firAdditionalMetadataProvider) {
        firSession.getClass();
        jvmSerializationBindings.getClass();
        list.getClass();
        scopeSession.getClass();
        jvmSerializationBindings2.getClass();
        str.getClass();
        classBuilderMode.getClass();
        binaryVersion.getClass();
        jvmDefaultMode.getClass();
        firElementAwareStringTable.getClass();
        this.session = firSession;
        this.bindings = jvmSerializationBindings;
        this.localDelegatedProperties = list;
        this.scopeSession = scopeSession;
        this.globalBindings = jvmSerializationBindings2;
        this.useTypeTable = z;
        this.moduleName = str;
        this.classBuilderMode = classBuilderMode;
        this.isParamAssertionsDisabled = z2;
        this.unifiedNullChecks = z3;
        this.metadataVersion = binaryVersion;
        this.jvmDefaultMode = jvmDefaultMode;
        this.stringTable = firElementAwareStringTable;
        this.additionalMetadataProvider = firAdditionalMetadataProvider;
        this.signatureSerializer = new FirJvmSignatureSerializer(firElementAwareStringTable);
    }
}
