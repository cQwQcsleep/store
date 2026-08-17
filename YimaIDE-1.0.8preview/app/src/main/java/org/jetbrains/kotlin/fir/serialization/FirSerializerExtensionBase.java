package org.jetbrains.kotlin.fir.serialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.constant.ConstantValue;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.serialization.constant.FirToConstantValueTransformerKt;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.Flags;
import org.jetbrains.kotlin.metadata.serialization.MutableVersionRequirementTable;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;
import org.jetbrains.kotlin.serialization.SerializerExtensionProtocol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J*\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J(\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0014\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J(\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0014\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J \u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0014\u001a\u00020$2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J*\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0014\u001a\u00020(2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J*\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+2\u0006\u0010\u0014\u001a\u00020,2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010-\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+H\u0002J\u0018\u0010.\u001a\u00020\u00112\u0006\u0010/\u001a\u0002002\u0006\u0010\u0014\u001a\u000201H\u0016J\u0018\u00102\u001a\u00020\u00112\u0006\u00103\u001a\u0002042\u0006\u0010\u0014\u001a\u000205H\u0016J\u001e\u00106\u001a\u00020\u00112\f\u00107\u001a\b\u0012\u0004\u0012\u000209082\u0006\u0010\u0014\u001a\u00020:H\u0016J\u0018\u0010;\u001a\u00020\u00112\u0006\u0010<\u001a\u00020=2\u0006\u0010\u0014\u001a\u00020>H\u0016Jv\u0010?\u001a\u00020\u0011\"\u000e\b\u0000\u0010@*\b\u0012\u0004\u0012\u0002H@0A\"\u0014\b\u0001\u0010B*\u000e\u0012\u0004\u0012\u0002H@\u0012\u0004\u0012\u0002HB0C*\u00020D2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H@\u0012\u0004\u0012\u0002HB0C2\u001a\u0010E\u001a\u0016\u0012\u0004\u0012\u0002H@\u0012\n\u0012\b\u0012\u0004\u0012\u00020G08\u0018\u00010F2\u0012\u0010H\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020\u00110IH\u0002J|\u0010?\u001a\u00020\u0011\"\u000e\b\u0000\u0010@*\b\u0012\u0004\u0012\u0002H@0A\"\u0014\b\u0001\u0010B*\u000e\u0012\u0004\u0012\u0002H@\u0012\u0004\u0012\u0002HB0C*\b\u0012\u0004\u0012\u000209082\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H@\u0012\u0004\u0012\u0002HB0C2\u001a\u0010E\u001a\u0016\u0012\u0004\u0012\u0002H@\u0012\n\u0012\b\u0012\u0004\u0012\u00020G08\u0018\u00010F2\u0012\u0010H\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020\u00110IH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006J"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirSerializerExtensionBase;", "Lorg/jetbrains/kotlin/fir/serialization/FirSerializerExtension;", "protocol", "Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;", "annotationsInMetadataLanguageFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "<init>", "(Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;Lorg/jetbrains/kotlin/config/LanguageFeature;)V", "getProtocol", "()Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;", "getAnnotationsInMetadataLanguageFeature", "()Lorg/jetbrains/kotlin/config/LanguageFeature;", "stringTable", "Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareSerializableStringTable;", "getStringTable", "()Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareSerializableStringTable;", "serializePackage", Argument.Delimiters.none, "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Package$Builder;", "versionRequirementTable", "Lorg/jetbrains/kotlin/metadata/serialization/MutableVersionRequirementTable;", "childSerializer", "Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer;", "serializeClass", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class$Builder;", "serializeScript", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "serializeConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor$Builder;", "serializeFunction", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function$Builder;", "serializeProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", "absentInitializerGuard", "serializeEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$EnumEntry$Builder;", "serializeValueParameter", "parameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$ValueParameter$Builder;", "serializeTypeAnnotations", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type$Builder;", "serializeTypeParameter", "typeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter$Builder;", "serializeAnnotations", "MessageType", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableMessage;", "BuilderType", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableBuilder;", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "extension", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$GeneratedExtension;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;", "addAnnotation", "Lkotlin/Function1;", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirSerializerExtensionBase extends FirSerializerExtension {
    private final LanguageFeature annotationsInMetadataLanguageFeature;
    private final SerializerExtensionProtocol protocol;
    private final FirElementAwareSerializableStringTable stringTable;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnnotationUseSiteTarget.values().length];
            try {
                iArr[AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public FirSerializerExtensionBase(SerializerExtensionProtocol serializerExtensionProtocol, LanguageFeature languageFeature) {
        serializerExtensionProtocol.getClass();
        this.protocol = serializerExtensionProtocol;
        this.annotationsInMetadataLanguageFeature = languageFeature;
        this.stringTable = new FirElementAwareSerializableStringTable();
    }

    private final void absentInitializerGuard(FirProperty property) {
        if (property.getStatus().isConst()) {
            if (((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).getFlag(AnalysisFlags.getMetadataCompilation())).booleanValue() || !FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).supportsFeature(LanguageFeature.IntrinsicConstEvaluation)) {
                dt1.a("Const property has no const initializer expression. Got ", UtilsKt.render(property));
            }
        }
    }

    private final <MessageType extends GeneratedMessageLite.ExtendableMessage<MessageType>, BuilderType extends GeneratedMessageLite.ExtendableBuilder<MessageType, BuilderType>> void serializeAnnotations(FirAnnotationContainer firAnnotationContainer, GeneratedMessageLite.ExtendableBuilder<MessageType, BuilderType> extendableBuilder, GeneratedMessageLite.GeneratedExtension<MessageType, List<ProtoBuf.Annotation>> generatedExtension, Function1<? super ProtoBuf.Annotation, Unit> function1) {
        SerializationUtilKt.serializeAnnotations(firAnnotationContainer, getSession(), getAdditionalMetadataProvider(), getAnnotationSerializer(), extendableBuilder, generatedExtension, function1, this.annotationsInMetadataLanguageFeature);
    }

    public final LanguageFeature getAnnotationsInMetadataLanguageFeature() {
        return this.annotationsInMetadataLanguageFeature;
    }

    public final SerializerExtensionProtocol getProtocol() {
        return this.protocol;
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public /* bridge */ /* synthetic */ FirElementAwareStringTable getStringTable() {
        return this.stringTable;
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeClass(FirClass klass, ProtoBuf.Class.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        klass.getClass();
        proto.getClass();
        versionRequirementTable.getClass();
        childSerializer.getClass();
        serializeAnnotations((FirAnnotationContainer) klass, (GeneratedMessageLite.ExtendableBuilder) proto, this.protocol.getClassAnnotation(), (Function1<? super ProtoBuf.Annotation, Unit>) new AnonymousClass1(proto));
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeConstructor(FirConstructor constructor, ProtoBuf.Constructor.Builder proto, FirElementSerializer childSerializer) {
        constructor.getClass();
        proto.getClass();
        childSerializer.getClass();
        serializeAnnotations((FirAnnotationContainer) constructor, (GeneratedMessageLite.ExtendableBuilder) proto, this.protocol.getConstructorAnnotation(), (Function1<? super ProtoBuf.Annotation, Unit>) new C00681(proto));
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeEnumEntry(FirEnumEntry enumEntry, ProtoBuf.EnumEntry.Builder proto) {
        enumEntry.getClass();
        proto.getClass();
        serializeAnnotations((FirAnnotationContainer) enumEntry, (GeneratedMessageLite.ExtendableBuilder) proto, this.protocol.getEnumEntryAnnotation(), (Function1<? super ProtoBuf.Annotation, Unit>) new C00691(proto));
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeFunction(FirFunction function, ProtoBuf.Function.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        function.getClass();
        proto.getClass();
        childSerializer.getClass();
        serializeAnnotations((FirAnnotationContainer) function, (GeneratedMessageLite.ExtendableBuilder) proto, this.protocol.getFunctionAnnotation(), (Function1<? super ProtoBuf.Annotation, Unit>) new C00701(proto));
        FirReceiverParameter receiverParameter = function.getReceiverParameter();
        if (receiverParameter != null) {
            serializeAnnotations((FirAnnotationContainer) receiverParameter, (GeneratedMessageLite.ExtendableBuilder) proto, this.protocol.getFunctionExtensionReceiverAnnotation(), (Function1<? super ProtoBuf.Annotation, Unit>) new AnonymousClass2(proto));
        }
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializePackage(FqName packageFqName, ProtoBuf.Package.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        packageFqName.getClass();
        proto.getClass();
        childSerializer.getClass();
        proto.setExtension(this.protocol.getPackageFqName(), Integer.valueOf(this.stringTable.getPackageFqNameIndex(packageFqName)));
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeProperty(FirProperty property, ProtoBuf.Property.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        property.getClass();
        proto.getClass();
        childSerializer.getClass();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        FirBackingField backingField = property.getBackingField();
        List<FirAnnotation> listAllRequiredAnnotations = backingField != null ? SerializationUtilKt.allRequiredAnnotations(backingField, getSession(), getAdditionalMetadataProvider()) : null;
        if (listAllRequiredAnnotations == null) {
            listAllRequiredAnnotations = CollectionsKt.emptyList();
        }
        for (FirAnnotation firAnnotation : listAllRequiredAnnotations) {
            AnnotationUseSiteTarget useSiteTarget = firAnnotation.getUseSiteTarget();
            ((useSiteTarget == null ? -1 : WhenMappings.$EnumSwitchMapping$0[useSiteTarget.ordinal()]) == 1 ? arrayList2 : arrayList).add(firAnnotation);
        }
        serializeAnnotations((FirAnnotationContainer) property, (GeneratedMessageLite.ExtendableBuilder) proto, this.protocol.getPropertyAnnotation(), (Function1<? super ProtoBuf.Annotation, Unit>) new C00711(proto));
        serializeAnnotations((List<? extends FirAnnotation>) arrayList, (GeneratedMessageLite.ExtendableBuilder) proto, this.protocol.getPropertyBackingFieldAnnotation(), (Function1<? super ProtoBuf.Annotation, Unit>) new C00722(proto));
        serializeAnnotations((List<? extends FirAnnotation>) arrayList2, (GeneratedMessageLite.ExtendableBuilder) proto, this.protocol.getPropertyDelegatedFieldAnnotation(), (Function1<? super ProtoBuf.Annotation, Unit>) new AnonymousClass3(proto));
        FirPropertyAccessor getter = property.getGetter();
        if (getter != null) {
            serializeAnnotations((FirAnnotationContainer) getter, (GeneratedMessageLite.ExtendableBuilder) proto, this.protocol.getPropertyGetterAnnotation(), (Function1<? super ProtoBuf.Annotation, Unit>) new AnonymousClass4(proto));
        }
        FirPropertyAccessor setter = property.getSetter();
        if (setter != null) {
            serializeAnnotations((FirAnnotationContainer) setter, (GeneratedMessageLite.ExtendableBuilder) proto, this.protocol.getPropertySetterAnnotation(), (Function1<? super ProtoBuf.Annotation, Unit>) new AnonymousClass5(proto));
        }
        FirReceiverParameter receiverParameter = property.getReceiverParameter();
        if (receiverParameter != null) {
            serializeAnnotations((FirAnnotationContainer) receiverParameter, (GeneratedMessageLite.ExtendableBuilder) proto, this.protocol.getPropertyExtensionReceiverAnnotation(), (Function1<? super ProtoBuf.Annotation, Unit>) new AnonymousClass6(proto));
        }
        if (Flags.HAS_CONSTANT.get(proto.getFlags()).booleanValue()) {
            FirEvaluatorResult evaluatedInitializer = DeclarationAttributesKt.getEvaluatedInitializer(property);
            FirEvaluatorResult.Evaluated evaluated = evaluatedInitializer instanceof FirEvaluatorResult.Evaluated ? (FirEvaluatorResult.Evaluated) evaluatedInitializer : null;
            FirElement result = evaluated != null ? evaluated.getResult() : null;
            FirExpression firExpression = result instanceof FirExpression ? (FirExpression) result : null;
            if (firExpression != null) {
                ConstantValue<?> constantValueEvaluateToAnnotationValue = firExpression instanceof FirAnnotation ? FirToConstantValueTransformerKt.evaluateToAnnotationValue(this, (FirAnnotation) firExpression) : FirToConstantValueTransformerKt.toConstantValueImpl(this, firExpression);
                ConstantValue<?> constantValue = constantValueEvaluateToAnnotationValue != null ? constantValueEvaluateToAnnotationValue : null;
                if (constantValue != null && proto.setExtension(this.protocol.getCompileTimeValue(), getAnnotationSerializer().valueProto$org_jetbrains_kotlin_fir_serialization(constantValue).build()) != null) {
                    return;
                }
            }
            absentInitializerGuard(property);
        }
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeScript(FirScript script, ProtoBuf.Class.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        script.getClass();
        proto.getClass();
        versionRequirementTable.getClass();
        childSerializer.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeTypeAnnotations(List<? extends FirAnnotation> annotations, ProtoBuf.Type.Builder proto) {
        annotations.getClass();
        proto.getClass();
        serializeAnnotations(annotations, (GeneratedMessageLite.ExtendableBuilder) proto, this.protocol.getTypeAnnotation(), (Function1<? super ProtoBuf.Annotation, Unit>) new C00731(proto));
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeTypeParameter(FirTypeParameter typeParameter, ProtoBuf.TypeParameter.Builder proto) {
        typeParameter.getClass();
        proto.getClass();
        serializeAnnotations((FirAnnotationContainer) typeParameter, (GeneratedMessageLite.ExtendableBuilder) proto, this.protocol.getTypeParameterAnnotation(), (Function1<? super ProtoBuf.Annotation, Unit>) new C00741(proto));
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeValueParameter(FirValueParameter parameter, ProtoBuf.ValueParameter.Builder proto) {
        parameter.getClass();
        proto.getClass();
        serializeAnnotations((FirAnnotationContainer) parameter, (GeneratedMessageLite.ExtendableBuilder) proto, this.protocol.getParameterAnnotation(), (Function1<? super ProtoBuf.Annotation, Unit>) new C00751(proto));
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public final FirElementAwareSerializableStringTable getStringTable() {
        return this.stringTable;
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase$serializeClass$1, reason: invalid class name */
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

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase$serializeConstructor$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00681 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public C00681(Object obj) {
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

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase$serializeEnumEntry$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00691 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public C00691(Object obj) {
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

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase$serializeFunction$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00701 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public C00701(Object obj) {
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

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase$serializeFunction$2, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass2 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public AnonymousClass2(Object obj) {
            super(1, obj, ProtoBuf.Function.Builder.class, "addExtensionReceiverAnnotation", "addExtensionReceiverAnnotation(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ProtoBuf.Annotation) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ProtoBuf.Annotation annotation) {
            ((ProtoBuf.Function.Builder) ((AdaptedFunctionReference) this).receiver).addExtensionReceiverAnnotation(annotation);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase$serializeProperty$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00711 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public C00711(Object obj) {
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

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase$serializeProperty$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00722 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public C00722(Object obj) {
            super(1, obj, ProtoBuf.Property.Builder.class, "addBackingFieldAnnotation", "addBackingFieldAnnotation(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ProtoBuf.Annotation) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ProtoBuf.Annotation annotation) {
            ((ProtoBuf.Property.Builder) ((AdaptedFunctionReference) this).receiver).addBackingFieldAnnotation(annotation);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase$serializeProperty$3, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass3 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public AnonymousClass3(Object obj) {
            super(1, obj, ProtoBuf.Property.Builder.class, "addDelegateFieldAnnotation", "addDelegateFieldAnnotation(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ProtoBuf.Annotation) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ProtoBuf.Annotation annotation) {
            ((ProtoBuf.Property.Builder) ((AdaptedFunctionReference) this).receiver).addDelegateFieldAnnotation(annotation);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase$serializeProperty$4, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass4 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public AnonymousClass4(Object obj) {
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

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase$serializeProperty$5, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass5 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public AnonymousClass5(Object obj) {
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

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase$serializeProperty$6, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass6 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public AnonymousClass6(Object obj) {
            super(1, obj, ProtoBuf.Property.Builder.class, "addExtensionReceiverAnnotation", "addExtensionReceiverAnnotation(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ProtoBuf.Annotation) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ProtoBuf.Annotation annotation) {
            ((ProtoBuf.Property.Builder) ((AdaptedFunctionReference) this).receiver).addExtensionReceiverAnnotation(annotation);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase$serializeTypeAnnotations$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00731 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public C00731(Object obj) {
            super(1, obj, ProtoBuf.Type.Builder.class, "addAnnotation", "addAnnotation(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ProtoBuf.Annotation) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ProtoBuf.Annotation annotation) {
            ((ProtoBuf.Type.Builder) ((AdaptedFunctionReference) this).receiver).addAnnotation(annotation);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase$serializeTypeParameter$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00741 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public C00741(Object obj) {
            super(1, obj, ProtoBuf.TypeParameter.Builder.class, "addAnnotation", "addAnnotation(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter$Builder;", 8);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ProtoBuf.Annotation) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(ProtoBuf.Annotation annotation) {
            ((ProtoBuf.TypeParameter.Builder) ((AdaptedFunctionReference) this).receiver).addAnnotation(annotation);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase$serializeValueParameter$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00751 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
        public C00751(Object obj) {
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

    public /* synthetic */ FirSerializerExtensionBase(SerializerExtensionProtocol serializerExtensionProtocol, LanguageFeature languageFeature, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(serializerExtensionProtocol, (i & 2) != 0 ? null : languageFeature);
    }

    private final <MessageType extends GeneratedMessageLite.ExtendableMessage<MessageType>, BuilderType extends GeneratedMessageLite.ExtendableBuilder<MessageType, BuilderType>> void serializeAnnotations(List<? extends FirAnnotation> list, GeneratedMessageLite.ExtendableBuilder<MessageType, BuilderType> extendableBuilder, GeneratedMessageLite.GeneratedExtension<MessageType, List<ProtoBuf.Annotation>> generatedExtension, Function1<? super ProtoBuf.Annotation, Unit> function1) {
        SerializationUtilKt.serializeAnnotations(list, getSession(), getAnnotationSerializer(), extendableBuilder, generatedExtension, function1, this.annotationsInMetadataLanguageFeature);
    }
}
