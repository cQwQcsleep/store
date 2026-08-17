package org.jetbrains.kotlin.fir.serialization;

import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.serialization.FirSerializerExtension;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.serialization.MutableVersionRequirementTable;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0016J*\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&H\u0016J(\u0010'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020)2\u0006\u0010!\u001a\u00020*2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J(\u0010+\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020-2\u0006\u0010!\u001a\u00020*2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J(\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u0002002\u0006\u0010!\u001a\u00020*2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J \u00101\u001a\u00020\u001e2\u0006\u00102\u001a\u0002032\u0006\u0010!\u001a\u0002042\u0006\u0010%\u001a\u00020&H\u0016J*\u00105\u001a\u00020\u001e2\u0006\u00106\u001a\u0002072\u0006\u0010!\u001a\u0002082\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&H\u0016J*\u00109\u001a\u00020\u001e2\u0006\u0010:\u001a\u00020;2\u0006\u0010!\u001a\u00020<2\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&H\u0016J\u0018\u0010=\u001a\u00020\u001e2\u0006\u0010>\u001a\u00020?2\u0006\u0010!\u001a\u00020@H\u0016J\u0018\u0010A\u001a\u00020\u001e2\u0006\u0010B\u001a\u00020C2\u0006\u0010!\u001a\u00020DH\u0016J \u0010E\u001a\u00020\u001e2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020IH\u0016J\u001e\u0010K\u001a\u00020\u001e2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020N0M2\u0006\u0010!\u001a\u00020IH\u0016J\u0018\u0010O\u001a\u00020\u001e2\u0006\u0010P\u001a\u00020Q2\u0006\u0010!\u001a\u00020RH\u0016J\u0018\u0010S\u001a\u00020\u001e2\u0006\u0010T\u001a\u00020U2\u0006\u0010!\u001a\u00020VH\u0016J\u0016\u0010W\u001a\b\u0012\u0004\u0012\u00020X0M2\u0006\u0010(\u001a\u00020)H\u0016J\u000e\u0010Y\u001a\u00020\u001b2\u0006\u0010Z\u001a\u00020[J\u0014\u0010\\\u001a\b\u0012\u0004\u0012\u00020N0M2\u0006\u0010Z\u001a\u00020[J\u0018\u0010]\u001a\u00020\u001e2\u0006\u0010F\u001a\u00020^2\u0006\u0010_\u001a\u00020IH\u0016R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006`"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirSerializerExtension;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "<init>", "()V", "stringTable", "Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;", "getStringTable", "()Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "annotationSerializer", "Lorg/jetbrains/kotlin/fir/serialization/FirAnnotationSerializer;", "getAnnotationSerializer", "()Lorg/jetbrains/kotlin/fir/serialization/FirAnnotationSerializer;", "annotationSerializer$delegate", "Lkotlin/Lazy;", "additionalMetadataProvider", "Lorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;", "getAdditionalMetadataProvider", "()Lorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;", "localClassIdOracle", "Lorg/jetbrains/kotlin/fir/serialization/LocalClassIdOracle;", "getLocalClassIdOracle", "()Lorg/jetbrains/kotlin/fir/serialization/LocalClassIdOracle;", "shouldUseTypeTable", Argument.Delimiters.none, "shouldUseNormalizedVisibility", "serializePackage", Argument.Delimiters.none, "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Package$Builder;", "versionRequirementTable", "Lorg/jetbrains/kotlin/metadata/serialization/MutableVersionRequirementTable;", "childSerializer", "Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer;", "serializeClass", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class$Builder;", "serializeScript", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "serializeSnippet", "snippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "serializeConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor$Builder;", "serializeFunction", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function$Builder;", "serializeProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", "serializeEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$EnumEntry$Builder;", "serializeValueParameter", "parameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$ValueParameter$Builder;", "serializeFlexibleType", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "lowerProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type$Builder;", "upperProto", "serializeTypeAnnotations", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "serializeTypeParameter", "typeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter$Builder;", "serializeTypeAlias", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeAlias$Builder;", "getClassSupertypes", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "hasAdditionalAnnotations", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getAnnotationsGeneratedByPlugins", "serializeErrorType", "Lorg/jetbrains/kotlin/fir/types/ConeErrorType;", "builder", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirSerializerExtension implements SessionAndScopeSessionHolder {

    /* JADX INFO: renamed from: annotationSerializer$delegate, reason: from kotlin metadata */
    private final Lazy annotationSerializer = LazyKt.lazy(new Function0() { // from class: cd5
        public final Object invoke() {
            return FirSerializerExtension.b(this.b);
        }
    });

    public static FirAnnotationSerializer b(FirSerializerExtension firSerializerExtension) {
        return new FirAnnotationSerializer(firSerializerExtension.getSession(), firSerializerExtension.getScopeSession(), firSerializerExtension.getStringTable(), firSerializerExtension.getLocalClassIdOracle());
    }

    public abstract FirAdditionalMetadataProvider getAdditionalMetadataProvider();

    public final FirAnnotationSerializer getAnnotationSerializer() {
        return (FirAnnotationSerializer) this.annotationSerializer.getValue();
    }

    public final List<FirAnnotation> getAnnotationsGeneratedByPlugins(FirDeclaration declaration) {
        List<FirAnnotation> listFindGeneratedAnnotationsFor;
        declaration.getClass();
        FirAdditionalMetadataProvider additionalMetadataProvider = getAdditionalMetadataProvider();
        return (additionalMetadataProvider == null || (listFindGeneratedAnnotationsFor = additionalMetadataProvider.findGeneratedAnnotationsFor(declaration)) == null) ? CollectionsKt.emptyList() : listFindGeneratedAnnotationsFor;
    }

    public List<FirTypeRef> getClassSupertypes(FirClass klass) {
        klass.getClass();
        return klass.getSuperTypeRefs();
    }

    public LocalClassIdOracle getLocalClassIdOracle() {
        return LocalClassIdOracle.INSTANCE.getEMPTY();
    }

    public abstract BinaryVersion getMetadataVersion();

    public abstract FirElementAwareStringTable getStringTable();

    public final boolean hasAdditionalAnnotations(FirDeclaration declaration) {
        declaration.getClass();
        FirAdditionalMetadataProvider additionalMetadataProvider = getAdditionalMetadataProvider();
        if (additionalMetadataProvider != null) {
            return additionalMetadataProvider.hasGeneratedAnnotationsFor(declaration);
        }
        return false;
    }

    public void serializeClass(FirClass klass, ProtoBuf.Class.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        klass.getClass();
        proto.getClass();
        versionRequirementTable.getClass();
        childSerializer.getClass();
    }

    public void serializeConstructor(FirConstructor constructor, ProtoBuf.Constructor.Builder proto, FirElementSerializer childSerializer) {
        constructor.getClass();
        proto.getClass();
        childSerializer.getClass();
    }

    public void serializeEnumEntry(FirEnumEntry enumEntry, ProtoBuf.EnumEntry.Builder proto) {
        enumEntry.getClass();
        proto.getClass();
    }

    public void serializeErrorType(ConeErrorType type, ProtoBuf.Type.Builder builder) {
        type.getClass();
        builder.getClass();
        throw new IllegalStateException(("Cannot serialize error type: " + type).toString());
    }

    public void serializeFlexibleType(ConeFlexibleType type, ProtoBuf.Type.Builder lowerProto, ProtoBuf.Type.Builder upperProto) {
        type.getClass();
        lowerProto.getClass();
        upperProto.getClass();
    }

    public void serializeFunction(FirFunction function, ProtoBuf.Function.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        function.getClass();
        proto.getClass();
        childSerializer.getClass();
    }

    public void serializePackage(FqName packageFqName, ProtoBuf.Package.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        packageFqName.getClass();
        proto.getClass();
        childSerializer.getClass();
    }

    public void serializeProperty(FirProperty property, ProtoBuf.Property.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        property.getClass();
        proto.getClass();
        childSerializer.getClass();
    }

    public void serializeScript(FirScript script, ProtoBuf.Class.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        script.getClass();
        proto.getClass();
        versionRequirementTable.getClass();
        childSerializer.getClass();
    }

    public void serializeSnippet(FirReplSnippet snippet, ProtoBuf.Class.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        snippet.getClass();
        proto.getClass();
        versionRequirementTable.getClass();
        childSerializer.getClass();
    }

    public void serializeTypeAlias(FirTypeAlias typeAlias, ProtoBuf.TypeAlias.Builder proto) {
        typeAlias.getClass();
        proto.getClass();
        Iterator<FirAnnotation> it = FirAnnotationUtilsKt.nonSourceAnnotations(typeAlias, getSession()).iterator();
        while (it.hasNext()) {
            ProtoBuf.Annotation annotationSerializeAnnotation = getAnnotationSerializer().serializeAnnotation(it.next());
            if (annotationSerializeAnnotation != null) {
                proto.addAnnotation(annotationSerializeAnnotation);
            }
        }
    }

    public void serializeTypeAnnotations(List<? extends FirAnnotation> annotations, ProtoBuf.Type.Builder proto) {
        annotations.getClass();
        proto.getClass();
    }

    public void serializeTypeParameter(FirTypeParameter typeParameter, ProtoBuf.TypeParameter.Builder proto) {
        typeParameter.getClass();
        proto.getClass();
    }

    public void serializeValueParameter(FirValueParameter parameter, ProtoBuf.ValueParameter.Builder proto) {
        parameter.getClass();
        proto.getClass();
    }

    public boolean shouldUseNormalizedVisibility() {
        return false;
    }

    public boolean shouldUseTypeTable() {
        return false;
    }
}
