package org.jetbrains.kotlin.fir.java.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializationUtilKt;
import org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationBuilder;
import org.jetbrains.kotlin.fir.java.JavaUtilsKt;
import org.jetbrains.kotlin.fir.java.deserialization.JvmBinaryAnnotationDeserializer;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.load.kotlin.AbstractBinaryClassAnnotationLoaderKt;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder$Result$KotlinClass;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinaryClass;
import org.jetbrains.kotlin.load.kotlin.MemberSignature;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.Flags;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.metadata.deserialization.ProtoBufUtilKt;
import org.jetbrains.kotlin.metadata.deserialization.ProtoTypeTableUtilKt;
import org.jetbrains.kotlin.metadata.deserialization.TypeTable;
import org.jetbrains.kotlin.metadata.jvm.JvmProtoBuf;
import org.jetbrains.kotlin.metadata.jvm.deserialization.ClassMapperLite;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmFlags;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmMemberSignature;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmProtoBufUtil;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;
import org.jetbrains.kotlin.protobuf.MessageLite;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.util.MetadataHelpersKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ú\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0001H\u0016J\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u001e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u001e\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u001e\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010(\u001a\u00020)2\u0006\u0010\u001f\u001a\u00020 H\u0016J0\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010/\u001a\u000200H\u0016J0\u00101\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u00102\u001a\u0002032\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010/\u001a\u000200H\u0016J:\u00104\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010/\u001a\u000200H\u0016J0\u0010<\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u00105\u001a\u0002062\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010/\u001a\u000200H\u0016J0\u0010=\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u00105\u001a\u0002062\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010/\u001a\u000200H\u0016J8\u0010>\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u00105\u001a\u0002062\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010/\u001a\u0002002\u0006\u0010?\u001a\u00020@H\u0016J8\u0010A\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u00105\u001a\u0002062\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010/\u001a\u0002002\u0006\u0010B\u001a\u00020@H\u0016JR\u0010C\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010/\u001a\u0002002\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020@H\u0016J&\u0010K\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020O2\u0006\u0010\u001f\u001a\u00020 H\u0016J8\u0010P\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010D\u001a\u00020E2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010/\u001a\u0002002\u0006\u0010H\u001a\u00020IH\u0016J4\u0010Q\u001a\u0004\u0018\u00010R2\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u00105\u001a\u0002062\u0006\u0010S\u001a\u00020T2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010/\u001a\u000200H\u0016J\u0010\u0010X\u001a\u0002092\u0006\u0010Y\u001a\u00020@H\u0002J\u001a\u0010Z\u001a\u00020@2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010[\u001a\u00020EH\u0002J6\u0010\\\u001a\u0004\u0018\u00010:2\u0006\u0010]\u001a\u00020E2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010/\u001a\u0002002\b\b\u0002\u0010H\u001a\u00020I2\b\b\u0002\u0010^\u001a\u000209H\u0002J \u0010_\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010`\u001a\u00020:2\b\b\u0002\u0010a\u001a\u000209H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u0004\u0018\u00010\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0015\u0010\u000fR\u0018\u00108\u001a\u000209*\u00020:8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b8\u0010;R\u0018\u0010U\u001a\u000209*\u00020V8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bU\u0010W¨\u0006b"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/deserialization/JvmBinaryAnnotationDeserializer;", "Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "kotlinBinaryClass", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass;", "kotlinClassFinder", "Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder;", "byteContent", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass;Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder;[B)V", "annotationInfo", "Lorg/jetbrains/kotlin/fir/java/deserialization/MemberAnnotations;", "getAnnotationInfo", "()Lorg/jetbrains/kotlin/fir/java/deserialization/MemberAnnotations;", "annotationInfo$delegate", "Lkotlin/Lazy;", "annotationsLoader", "Lorg/jetbrains/kotlin/fir/java/deserialization/AnnotationsLoader;", "annotationInfoForDefaultImpls", "getAnnotationInfoForDefaultImpls", "annotationInfoForDefaultImpls$delegate", "inheritAnnotationInfo", Argument.Delimiters.none, "parent", "loadClassAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "loadTypeAliasAnnotations", "aliasProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeAlias;", "loadTypeAnnotations", "typeProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;", "loadTypeParameterAnnotations", "typeParameterProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter;", "loadConstructorAnnotations", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "constructorProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor;", "typeTable", "Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;", "loadFunctionAnnotations", "functionProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;", "loadPropertyAnnotations", "propertyProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "containingClassProto", "isDelegated", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/kotlin/MemberSignature;", "(Lorg/jetbrains/kotlin/load/kotlin/MemberSignature;)Z", "loadPropertyBackingFieldAnnotations", "loadPropertyDelegatedFieldAnnotations", "loadPropertyGetterAnnotations", "getterFlags", Argument.Delimiters.none, "loadPropertySetterAnnotations", "setterFlags", "loadValueParameterAnnotations", "callableProto", "Lorg/jetbrains/kotlin/protobuf/MessageLite;", "valueParameterProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$ValueParameter;", "kind", "Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer$CallableKind;", "parameterIndex", "loadEnumEntryAnnotations", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "enumEntryProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$EnumEntry;", "loadExtensionReceiverParameterAnnotations", "loadAnnotationPropertyDefaultValue", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "expectedPropertyType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "isSignedNumber", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "(Lorg/jetbrains/kotlin/types/ConstantValueKind;)Z", "noAnnotationsInBytecode", "flags", "computeJvmParameterIndexShift", "message", "getCallableSignature", "proto", "requireHasFieldFlagForField", "findJvmBinaryClassAndLoadMemberAnnotations", "memberSignature", "searchInDefaultImpls", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmBinaryAnnotationDeserializer extends AnnotationDeserializer {

    /* JADX INFO: renamed from: annotationInfo$delegate, reason: from kotlin metadata */
    private final Lazy annotationInfo;

    /* JADX INFO: renamed from: annotationInfoForDefaultImpls$delegate, reason: from kotlin metadata */
    private final Lazy annotationInfoForDefaultImpls;
    private final AnnotationsLoader annotationsLoader;
    private final byte[] byteContent;
    private final KotlinJvmBinaryClass kotlinBinaryClass;
    private final FirSession session;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnnotationDeserializer.CallableKind.values().length];
            try {
                iArr[AnnotationDeserializer.CallableKind.PROPERTY_GETTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AnnotationDeserializer.CallableKind.PROPERTY_SETTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AnnotationDeserializer.CallableKind.PROPERTY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public JvmBinaryAnnotationDeserializer(FirSession firSession, KotlinJvmBinaryClass kotlinJvmBinaryClass, final KotlinClassFinder kotlinClassFinder, byte[] bArr) {
        firSession.getClass();
        kotlinJvmBinaryClass.getClass();
        kotlinClassFinder.getClass();
        this.session = firSession;
        this.kotlinBinaryClass = kotlinJvmBinaryClass;
        this.byteContent = bArr;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        this.annotationInfo = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: jv7
            public final Object invoke() {
                return JvmBinaryAnnotationDeserializer.a(this.b, kotlinClassFinder);
            }
        });
        this.annotationsLoader = new AnnotationsLoader(firSession, kotlinClassFinder);
        this.annotationInfoForDefaultImpls = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: kv7
            public final Object invoke() {
                return JvmBinaryAnnotationDeserializer.b(this.b, kotlinClassFinder);
            }
        });
    }

    public static MemberAnnotations a(JvmBinaryAnnotationDeserializer jvmBinaryAnnotationDeserializer, KotlinClassFinder kotlinClassFinder) {
        return JvmBinaryAnnotationDeserializerKt.loadMemberAnnotations(jvmBinaryAnnotationDeserializer.session, jvmBinaryAnnotationDeserializer.kotlinBinaryClass, jvmBinaryAnnotationDeserializer.byteContent, kotlinClassFinder);
    }

    public static MemberAnnotations b(JvmBinaryAnnotationDeserializer jvmBinaryAnnotationDeserializer, KotlinClassFinder kotlinClassFinder) {
        ClassId classId = jvmBinaryAnnotationDeserializer.kotlinBinaryClass.getClassId();
        Name nameIdentifier = Name.identifier("DefaultImpls");
        nameIdentifier.getClass();
        KotlinClassFinder.Result resultFindKotlinClassOrContent = kotlinClassFinder.findKotlinClassOrContent(classId.createNestedClassId(nameIdentifier), MetadataHelpersKt.toJvmMetadataVersion(FirLanguageSettingsComponentKt.getLanguageVersionSettings(jvmBinaryAnnotationDeserializer.session).getLanguageVersion()));
        KotlinClassFinder$Result$KotlinClass kotlinClassFinder$Result$KotlinClass = resultFindKotlinClassOrContent instanceof KotlinClassFinder$Result$KotlinClass ? (KotlinClassFinder$Result$KotlinClass) resultFindKotlinClassOrContent : null;
        if (kotlinClassFinder$Result$KotlinClass == null) {
            return null;
        }
        return JvmBinaryAnnotationDeserializerKt.loadMemberAnnotations(jvmBinaryAnnotationDeserializer.session, kotlinClassFinder$Result$KotlinClass.getKotlinJvmBinaryClass(), kotlinClassFinder$Result$KotlinClass.getByteContent(), kotlinClassFinder);
    }

    private final int computeJvmParameterIndexShift(ProtoBuf.Class classProto, MessageLite message) {
        if (message instanceof ProtoBuf.Function) {
            return ProtoTypeTableUtilKt.hasReceiver((ProtoBuf.Function) message) ? 1 : 0;
        }
        if (message instanceof ProtoBuf.Property) {
            return ProtoTypeTableUtilKt.hasReceiver((ProtoBuf.Property) message) ? 1 : 0;
        }
        if (!(message instanceof ProtoBuf.Constructor)) {
            o1c.a("Unsupported message: ", message.getClass());
            return 0;
        }
        Flags.FlagField flagField = Flags.CLASS_KIND;
        classProto.getClass();
        ProtoBuf.Class.Kind kind = (ProtoBuf.Class.Kind) flagField.get(classProto.getFlags());
        if (kind == null) {
            kind = ProtoBuf.Class.Kind.CLASS;
        }
        Boolean bool = Flags.IS_INNER.get(classProto.getFlags());
        bool.getClass();
        boolean zBooleanValue = bool.booleanValue();
        if (kind == ProtoBuf.Class.Kind.ENUM_CLASS) {
            return 2;
        }
        return zBooleanValue ? 1 : 0;
    }

    private final List<FirAnnotation> findJvmBinaryClassAndLoadMemberAnnotations(MemberSignature memberSignature, boolean searchInDefaultImpls) {
        MemberAnnotations annotationInfo;
        if (searchInDefaultImpls) {
            annotationInfo = getAnnotationInfoForDefaultImpls();
            if (annotationInfo == null) {
                return CollectionsKt.emptyList();
            }
        } else {
            annotationInfo = getAnnotationInfo();
        }
        List<FirAnnotation> list = annotationInfo.getMemberAnnotations().get(memberSignature);
        return list == null ? CollectionsKt.emptyList() : list;
    }

    public static /* synthetic */ List findJvmBinaryClassAndLoadMemberAnnotations$default(JvmBinaryAnnotationDeserializer jvmBinaryAnnotationDeserializer, MemberSignature memberSignature, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return jvmBinaryAnnotationDeserializer.findJvmBinaryClassAndLoadMemberAnnotations(memberSignature, z);
    }

    private final MemberAnnotations getAnnotationInfo() {
        return (MemberAnnotations) this.annotationInfo.getValue();
    }

    private final MemberAnnotations getAnnotationInfoForDefaultImpls() {
        return (MemberAnnotations) this.annotationInfoForDefaultImpls.getValue();
    }

    private final MemberSignature getCallableSignature(MessageLite proto, NameResolver nameResolver, TypeTable typeTable, AnnotationDeserializer.CallableKind kind, boolean requireHasFieldFlagForField) {
        if (proto instanceof ProtoBuf.Constructor) {
            MemberSignature.Companion companion = MemberSignature.Companion;
            JvmMemberSignature.Method jvmConstructorSignature = JvmProtoBufUtil.INSTANCE.getJvmConstructorSignature((ProtoBuf.Constructor) proto, nameResolver, typeTable);
            if (jvmConstructorSignature == null) {
                return null;
            }
            return companion.fromJvmMemberSignature(jvmConstructorSignature);
        }
        if (proto instanceof ProtoBuf.Function) {
            JvmMemberSignature.Method jvmMethodSignature = JvmProtoBufUtil.INSTANCE.getJvmMethodSignature((ProtoBuf.Function) proto, nameResolver, typeTable);
            if (jvmMethodSignature == null) {
                return null;
            }
            return MemberSignature.Companion.fromJvmMemberSignature(jvmMethodSignature);
        }
        if (proto instanceof ProtoBuf.Property) {
            GeneratedMessageLite.GeneratedExtension generatedExtension = JvmProtoBuf.propertySignature;
            generatedExtension.getClass();
            JvmProtoBuf.JvmPropertySignature jvmPropertySignature = (JvmProtoBuf.JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull((GeneratedMessageLite.ExtendableMessage) proto, generatedExtension);
            if (jvmPropertySignature == null) {
                return null;
            }
            int i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return null;
                    }
                    return AbstractBinaryClassAnnotationLoaderKt.getPropertySignature$default((ProtoBuf.Property) proto, nameResolver, typeTable, true, false, requireHasFieldFlagForField, 16, (Object) null);
                }
                if (!jvmPropertySignature.hasSetter()) {
                    return null;
                }
                MemberSignature.Companion companion2 = MemberSignature.Companion;
                JvmProtoBuf.JvmMethodSignature setter = jvmPropertySignature.getSetter();
                setter.getClass();
                return companion2.fromMethod(nameResolver, setter);
            }
            if (jvmPropertySignature.hasGetter()) {
                MemberSignature.Companion companion3 = MemberSignature.Companion;
                JvmProtoBuf.JvmMethodSignature getter = jvmPropertySignature.getGetter();
                getter.getClass();
                return companion3.fromMethod(nameResolver, getter);
            }
        }
        return null;
    }

    public static /* synthetic */ MemberSignature getCallableSignature$default(JvmBinaryAnnotationDeserializer jvmBinaryAnnotationDeserializer, MessageLite messageLite, NameResolver nameResolver, TypeTable typeTable, AnnotationDeserializer.CallableKind callableKind, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            callableKind = AnnotationDeserializer.CallableKind.OTHERS;
        }
        AnnotationDeserializer.CallableKind callableKind2 = callableKind;
        if ((i & 16) != 0) {
            z = false;
        }
        return jvmBinaryAnnotationDeserializer.getCallableSignature(messageLite, nameResolver, typeTable, callableKind2, z);
    }

    private final boolean isDelegated(MemberSignature memberSignature) {
        return StringsKt.contains$default(memberSignature.getSignature(), "$delegate", false, 2, (Object) null);
    }

    private final boolean isSignedNumber(ConstantValueKind constantValueKind) {
        return (constantValueKind instanceof ConstantValueKind.Byte) || (constantValueKind instanceof ConstantValueKind.Short) || (constantValueKind instanceof ConstantValueKind.Int) || (constantValueKind instanceof ConstantValueKind.Long);
    }

    private final boolean noAnnotationsInBytecode(int flags) {
        return !Flags.HAS_ANNOTATIONS.get(flags).booleanValue();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public void inheritAnnotationInfo(AnnotationDeserializer parent) {
        parent.getClass();
        if (parent instanceof JvmBinaryAnnotationDeserializer) {
            getAnnotationInfo().getMemberAnnotations().putAll(((JvmBinaryAnnotationDeserializer) parent).getAnnotationInfo().getMemberAnnotations());
        }
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public FirExpression loadAnnotationPropertyDefaultValue(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, FirTypeRef expectedPropertyType, NameResolver nameResolver, TypeTable typeTable) {
        propertyProto.getClass();
        expectedPropertyType.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        MemberSignature callableSignature$default = getCallableSignature$default(this, propertyProto, nameResolver, typeTable, AnnotationDeserializer.CallableKind.PROPERTY_GETTER, false, 16, null);
        if (callableSignature$default == null) {
            return null;
        }
        FirExpression firExpression = getAnnotationInfo().getAnnotationMethodsDefaultValues().get(callableSignature$default);
        if (!(firExpression instanceof FirLiteralExpression) || !ConeBuiltinTypeUtilsKt.isUnsignedType(FirTypeUtilsKt.getConeType(expectedPropertyType))) {
            return firExpression;
        }
        FirLiteralExpression firLiteralExpression = (FirLiteralExpression) firExpression;
        return isSignedNumber(firLiteralExpression.getKind()) ? JavaUtilsKt.createConstantIfAny(firLiteralExpression.getValue(), this.session, true) : firExpression;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadClassAnnotations(ProtoBuf.Class classProto, NameResolver nameResolver) {
        classProto.getClass();
        nameResolver.getClass();
        FirSession firSession = this.session;
        List annotationList = classProto.getAnnotationList();
        annotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataGuarded$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataGuarded$default(firSession, annotationList, nameResolver, LanguageFeature.AnnotationsInMetadata, null, 16, null);
        if (listLoadAnnotationsFromMetadataGuarded$default != null) {
            return listLoadAnnotationsFromMetadataGuarded$default;
        }
        Integer numValueOf = Integer.valueOf(classProto.getFlags());
        Flags.BooleanFlagField booleanFlagField = Flags.IS_VALUE_CLASS;
        booleanFlagField.getClass();
        if (booleanFlagField.get(numValueOf.intValue()).booleanValue()) {
            numValueOf = null;
        }
        if (numValueOf != null && noAnnotationsInBytecode(numValueOf.intValue())) {
            return CollectionsKt.emptyList();
        }
        final ArrayList arrayList = new ArrayList();
        this.kotlinBinaryClass.loadClassAnnotations(new KotlinJvmBinaryClass.AnnotationVisitor() { // from class: org.jetbrains.kotlin.fir.java.deserialization.JvmBinaryAnnotationDeserializer.loadClassAnnotations.2
            public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(ClassId classId, SourceElement source) {
                classId.getClass();
                source.getClass();
                return JvmBinaryAnnotationDeserializer.this.annotationsLoader.loadAnnotationIfNotSpecial$org_jetbrains_kotlin_fir_jvm(classId, arrayList);
            }

            public void visitEnd() {
            }
        }, this.byteContent);
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadConstructorAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Constructor constructorProto, NameResolver nameResolver, TypeTable typeTable) {
        MemberSignature callableSignature$default;
        constructorProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        FirSession firSession = this.session;
        List annotationList = constructorProto.getAnnotationList();
        annotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataGuarded$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataGuarded$default(firSession, annotationList, nameResolver, LanguageFeature.AnnotationsInMetadata, null, 16, null);
        if (listLoadAnnotationsFromMetadataGuarded$default != null) {
            return listLoadAnnotationsFromMetadataGuarded$default;
        }
        if (!noAnnotationsInBytecode(constructorProto.getFlags()) && (callableSignature$default = getCallableSignature$default(this, constructorProto, nameResolver, typeTable, null, false, 24, null)) != null) {
            return findJvmBinaryClassAndLoadMemberAnnotations$default(this, callableSignature$default, false, 2, null);
        }
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadEnumEntryAnnotations(ClassId classId, ProtoBuf.EnumEntry enumEntryProto, NameResolver nameResolver) {
        classId.getClass();
        enumEntryProto.getClass();
        nameResolver.getClass();
        FirSession firSession = this.session;
        List annotationList = enumEntryProto.getAnnotationList();
        annotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataGuarded$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataGuarded$default(firSession, annotationList, nameResolver, LanguageFeature.AnnotationsInMetadata, null, 16, null);
        return listLoadAnnotationsFromMetadataGuarded$default != null ? listLoadAnnotationsFromMetadataGuarded$default : findJvmBinaryClassAndLoadMemberAnnotations$default(this, MemberSignature.Companion.fromFieldNameAndDesc(nameResolver.getString(enumEntryProto.getName()), ClassMapperLite.mapClass(classId.asString())), false, 2, null);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadExtensionReceiverParameterAnnotations(DeserializedContainerSource containerSource, MessageLite callableProto, NameResolver nameResolver, TypeTable typeTable, AnnotationDeserializer.CallableKind kind) {
        NameResolver nameResolver2;
        callableProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        kind.getClass();
        if (callableProto instanceof ProtoBuf.Function) {
            FirSession firSession = this.session;
            List extensionReceiverAnnotationList = ((ProtoBuf.Function) callableProto).getExtensionReceiverAnnotationList();
            extensionReceiverAnnotationList.getClass();
            nameResolver2 = nameResolver;
            List<FirAnnotation> listLoadAnnotationsFromMetadataGuarded$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataGuarded$default(firSession, extensionReceiverAnnotationList, nameResolver2, LanguageFeature.AnnotationsInMetadata, null, 16, null);
            if (listLoadAnnotationsFromMetadataGuarded$default != null) {
                return listLoadAnnotationsFromMetadataGuarded$default;
            }
        } else {
            nameResolver2 = nameResolver;
            if (callableProto instanceof ProtoBuf.Property) {
                FirSession firSession2 = this.session;
                List extensionReceiverAnnotationList2 = ((ProtoBuf.Property) callableProto).getExtensionReceiverAnnotationList();
                extensionReceiverAnnotationList2.getClass();
                List<FirAnnotation> listLoadAnnotationsFromMetadataGuarded$default2 = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataGuarded$default(firSession2, extensionReceiverAnnotationList2, nameResolver2, LanguageFeature.AnnotationsInMetadata, null, 16, null);
                if (listLoadAnnotationsFromMetadataGuarded$default2 != null) {
                    return listLoadAnnotationsFromMetadataGuarded$default2;
                }
            }
        }
        MemberSignature callableSignature$default = getCallableSignature$default(this, callableProto, nameResolver2, typeTable, kind, false, 16, null);
        return callableSignature$default == null ? CollectionsKt.emptyList() : findJvmBinaryClassAndLoadMemberAnnotations$default(this, MemberSignature.Companion.fromMethodSignatureAndParameterIndex(callableSignature$default, 0), false, 2, null);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadFunctionAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Function functionProto, NameResolver nameResolver, TypeTable typeTable) {
        MemberSignature callableSignature$default;
        functionProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        FirSession firSession = this.session;
        List annotationList = functionProto.getAnnotationList();
        annotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataGuarded$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataGuarded$default(firSession, annotationList, nameResolver, LanguageFeature.AnnotationsInMetadata, null, 16, null);
        if (listLoadAnnotationsFromMetadataGuarded$default != null) {
            return listLoadAnnotationsFromMetadataGuarded$default;
        }
        if (!noAnnotationsInBytecode(functionProto.getFlags()) && (callableSignature$default = getCallableSignature$default(this, functionProto, nameResolver, typeTable, null, false, 24, null)) != null) {
            return findJvmBinaryClassAndLoadMemberAnnotations$default(this, callableSignature$default, false, 2, null);
        }
        return CollectionsKt.emptyList();
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0087  */
    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadPropertyAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, ProtoBuf.Class containingClassProto, NameResolver nameResolver, TypeTable typeTable) {
        MemberSignature propertySignature$default;
        boolean zBooleanValue;
        propertyProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        FirSession firSession = this.session;
        List annotationList = propertyProto.getAnnotationList();
        annotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataGuarded = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataGuarded(firSession, annotationList, nameResolver, LanguageFeature.AnnotationsInMetadata, AnnotationUseSiteTarget.PROPERTY);
        if (listLoadAnnotationsFromMetadataGuarded != null) {
            return listLoadAnnotationsFromMetadataGuarded;
        }
        if (!noAnnotationsInBytecode(propertyProto.getFlags()) && (propertySignature$default = AbstractBinaryClassAnnotationLoaderKt.getPropertySignature$default(propertyProto, nameResolver, typeTable, false, true, false, 40, (Object) null)) != null) {
            boolean z = false;
            boolean z2 = containingClassProto != null && Flags.CLASS_KIND.get(containingClassProto.getFlags()) == ProtoBuf.Class.Kind.INTERFACE;
            Integer num = null;
            if ((containingClassProto != null && containingClassProto.hasExtension(JvmProtoBuf.jvmClassFlags)) && containingClassProto != null) {
                num = (Integer) containingClassProto.getExtension(JvmProtoBuf.jvmClassFlags);
            }
            if (num != null) {
                Boolean bool = JvmFlags.INSTANCE.getIS_COMPILED_IN_COMPATIBILITY_MODE().get(num.intValue());
                if (bool != null) {
                    zBooleanValue = bool.booleanValue();
                } else {
                    zBooleanValue = true;
                }
            } else {
                zBooleanValue = true;
            }
            if (z2 && zBooleanValue) {
                z = true;
            }
            List<FirAnnotation> listFindJvmBinaryClassAndLoadMemberAnnotations = findJvmBinaryClassAndLoadMemberAnnotations(propertySignature$default, z);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listFindJvmBinaryClassAndLoadMemberAnnotations, 10));
            for (FirAnnotation firAnnotation : listFindJvmBinaryClassAndLoadMemberAnnotations) {
                FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
                firAnnotationBuilder.setAnnotationTypeRef(firAnnotation.getAnnotationTypeRef());
                firAnnotationBuilder.setArgumentMapping(firAnnotation.getArgumentMapping());
                firAnnotationBuilder.setUseSiteTarget(AnnotationUseSiteTarget.PROPERTY);
                arrayList.add(firAnnotationBuilder.mo288build());
            }
            return arrayList;
        }
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadPropertyBackingFieldAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable) {
        propertyProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        MemberSignature propertySignature$default = AbstractBinaryClassAnnotationLoaderKt.getPropertySignature$default(propertyProto, nameResolver, typeTable, true, false, false, 48, (Object) null);
        if (propertySignature$default != null && !isDelegated(propertySignature$default)) {
            FirSession firSession = this.session;
            List backingFieldAnnotationList = propertyProto.getBackingFieldAnnotationList();
            backingFieldAnnotationList.getClass();
            List<FirAnnotation> listLoadAnnotationsFromMetadataGuarded = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataGuarded(firSession, backingFieldAnnotationList, nameResolver, LanguageFeature.AnnotationsInMetadata, AnnotationUseSiteTarget.FIELD);
            if (listLoadAnnotationsFromMetadataGuarded != null) {
                return listLoadAnnotationsFromMetadataGuarded;
            }
            if (noAnnotationsInBytecode(propertyProto.getFlags())) {
                return CollectionsKt.emptyList();
            }
            List<FirAnnotation> listFindJvmBinaryClassAndLoadMemberAnnotations$default = findJvmBinaryClassAndLoadMemberAnnotations$default(this, propertySignature$default, false, 2, null);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listFindJvmBinaryClassAndLoadMemberAnnotations$default, 10));
            for (FirAnnotation firAnnotation : listFindJvmBinaryClassAndLoadMemberAnnotations$default) {
                FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
                firAnnotationBuilder.setAnnotationTypeRef(firAnnotation.getAnnotationTypeRef());
                firAnnotationBuilder.setArgumentMapping(firAnnotation.getArgumentMapping());
                firAnnotationBuilder.setUseSiteTarget(AnnotationUseSiteTarget.FIELD);
                arrayList.add(firAnnotationBuilder.mo288build());
            }
            return arrayList;
        }
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadPropertyDelegatedFieldAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable) {
        propertyProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        MemberSignature propertySignature$default = AbstractBinaryClassAnnotationLoaderKt.getPropertySignature$default(propertyProto, nameResolver, typeTable, true, false, false, 48, (Object) null);
        if (propertySignature$default != null && isDelegated(propertySignature$default)) {
            FirSession firSession = this.session;
            List delegateFieldAnnotationList = propertyProto.getDelegateFieldAnnotationList();
            delegateFieldAnnotationList.getClass();
            List<FirAnnotation> listLoadAnnotationsFromMetadataGuarded = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataGuarded(firSession, delegateFieldAnnotationList, nameResolver, LanguageFeature.AnnotationsInMetadata, AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD);
            if (listLoadAnnotationsFromMetadataGuarded != null) {
                return listLoadAnnotationsFromMetadataGuarded;
            }
            if (noAnnotationsInBytecode(propertyProto.getFlags())) {
                return CollectionsKt.emptyList();
            }
            List<FirAnnotation> listFindJvmBinaryClassAndLoadMemberAnnotations$default = findJvmBinaryClassAndLoadMemberAnnotations$default(this, propertySignature$default, false, 2, null);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listFindJvmBinaryClassAndLoadMemberAnnotations$default, 10));
            for (FirAnnotation firAnnotation : listFindJvmBinaryClassAndLoadMemberAnnotations$default) {
                FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
                firAnnotationBuilder.setAnnotationTypeRef(firAnnotation.getAnnotationTypeRef());
                firAnnotationBuilder.setArgumentMapping(firAnnotation.getArgumentMapping());
                firAnnotationBuilder.setUseSiteTarget(AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD);
                arrayList.add(firAnnotationBuilder.mo288build());
            }
            return arrayList;
        }
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadPropertyGetterAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable, int getterFlags) {
        MemberSignature callableSignature$default;
        propertyProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        FirSession firSession = this.session;
        List getterAnnotationList = propertyProto.getGetterAnnotationList();
        getterAnnotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataGuarded$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataGuarded$default(firSession, getterAnnotationList, nameResolver, LanguageFeature.AnnotationsInMetadata, null, 16, null);
        if (listLoadAnnotationsFromMetadataGuarded$default != null) {
            return listLoadAnnotationsFromMetadataGuarded$default;
        }
        if (!noAnnotationsInBytecode(getterFlags) && (callableSignature$default = getCallableSignature$default(this, propertyProto, nameResolver, typeTable, AnnotationDeserializer.CallableKind.PROPERTY_GETTER, false, 16, null)) != null) {
            return findJvmBinaryClassAndLoadMemberAnnotations$default(this, callableSignature$default, false, 2, null);
        }
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadPropertySetterAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable, int setterFlags) {
        MemberSignature callableSignature$default;
        propertyProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        FirSession firSession = this.session;
        List setterAnnotationList = propertyProto.getSetterAnnotationList();
        setterAnnotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataGuarded$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataGuarded$default(firSession, setterAnnotationList, nameResolver, LanguageFeature.AnnotationsInMetadata, null, 16, null);
        if (listLoadAnnotationsFromMetadataGuarded$default != null) {
            return listLoadAnnotationsFromMetadataGuarded$default;
        }
        if (!noAnnotationsInBytecode(setterFlags) && (callableSignature$default = getCallableSignature$default(this, propertyProto, nameResolver, typeTable, AnnotationDeserializer.CallableKind.PROPERTY_SETTER, false, 16, null)) != null) {
            return findJvmBinaryClassAndLoadMemberAnnotations$default(this, callableSignature$default, false, 2, null);
        }
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadTypeAliasAnnotations(ProtoBuf.TypeAlias aliasProto, NameResolver nameResolver) {
        aliasProto.getClass();
        nameResolver.getClass();
        FirSession firSession = this.session;
        List annotationList = aliasProto.getAnnotationList();
        annotationList.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromMetadata$default(firSession, annotationList, nameResolver, null, 8, null);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadTypeAnnotations(ProtoBuf.Type typeProto, NameResolver nameResolver) {
        typeProto.getClass();
        nameResolver.getClass();
        FirSession firSession = this.session;
        List annotationList = typeProto.getAnnotationList();
        annotationList.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromMetadata$default(firSession, annotationList, nameResolver, null, 8, null);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadTypeParameterAnnotations(ProtoBuf.TypeParameter typeParameterProto, NameResolver nameResolver) {
        typeParameterProto.getClass();
        nameResolver.getClass();
        FirSession firSession = this.session;
        List annotationList = typeParameterProto.getAnnotationList();
        annotationList.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromMetadata$default(firSession, annotationList, nameResolver, null, 8, null);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadValueParameterAnnotations(DeserializedContainerSource containerSource, MessageLite callableProto, ProtoBuf.ValueParameter valueParameterProto, ProtoBuf.Class classProto, NameResolver nameResolver, TypeTable typeTable, AnnotationDeserializer.CallableKind kind, int parameterIndex) {
        MemberSignature callableSignature$default;
        callableProto.getClass();
        valueParameterProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        kind.getClass();
        FirSession firSession = this.session;
        List annotationList = valueParameterProto.getAnnotationList();
        annotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataGuarded$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataGuarded$default(firSession, annotationList, nameResolver, LanguageFeature.AnnotationsInMetadata, null, 16, null);
        if (listLoadAnnotationsFromMetadataGuarded$default != null) {
            return listLoadAnnotationsFromMetadataGuarded$default;
        }
        if (!noAnnotationsInBytecode(valueParameterProto.getFlags()) && (callableSignature$default = getCallableSignature$default(this, callableProto, nameResolver, typeTable, kind, false, 16, null)) != null) {
            return findJvmBinaryClassAndLoadMemberAnnotations$default(this, MemberSignature.Companion.fromMethodSignatureAndParameterIndex(callableSignature$default, parameterIndex + computeJvmParameterIndexShift(classProto, callableProto)), false, 2, null);
        }
        return CollectionsKt.emptyList();
    }
}
