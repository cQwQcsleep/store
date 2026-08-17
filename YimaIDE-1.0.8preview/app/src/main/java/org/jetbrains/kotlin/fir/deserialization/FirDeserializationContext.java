package org.jetbrains.kotlin.fir.deserialization;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.metadata.deserialization.TypeTable;
import org.jetbrains.kotlin.metadata.deserialization.VersionRequirementTable;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 L2\u00020\u0001:\u0001LB{\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\u0004\b\u001c\u0010\u001dJv\u0010=\u001a\u00020\u00002\f\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\u001a2\n\u0010@\u001a\u0006\u0012\u0002\b\u00030A2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010B\u001a\u00020CR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0013\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b(\u0010'R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0011\u00107\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0017\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\b\n\u0000\u001a\u0004\b<\u00106R\u0011\u0010D\u001a\u00020E¢\u0006\b\n\u0000\u001a\u0004\bF\u0010GR\u0013\u0010H\u001a\u0004\u0018\u00010I¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010K¨\u0006M"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;", Argument.Delimiters.none, "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "typeTable", "Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;", "versionRequirementTable", "Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirementTable;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "relativeClassName", "typeDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer;", "annotationDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer;", "constDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirConstDeserializer;", "kdocDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirKDocDeserializer;", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "outerClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "outerTypeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "<init>", "(Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirementTable;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer;Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer;Lorg/jetbrains/kotlin/fir/deserialization/FirConstDeserializer;Lorg/jetbrains/kotlin/fir/deserialization/FirKDocDeserializer;Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Ljava/util/List;)V", "getNameResolver", "()Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "getTypeTable", "()Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;", "getVersionRequirementTable", "()Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirementTable;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getRelativeClassName", "getTypeDeserializer", "()Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer;", "getAnnotationDeserializer", "()Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer;", "getConstDeserializer", "()Lorg/jetbrains/kotlin/fir/deserialization/FirConstDeserializer;", "getKdocDeserializer", "()Lorg/jetbrains/kotlin/fir/deserialization/FirKDocDeserializer;", "getContainerSource", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "getOuterClassSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getOuterTypeParameters", "()Ljava/util/List;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "allTypeParameters", "getAllTypeParameters", "childContext", "typeParameterProtos", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter;", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "capturesTypeParameters", Argument.Delimiters.none, "memberDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirMemberDeserializer;", "getMemberDeserializer", "()Lorg/jetbrains/kotlin/fir/deserialization/FirMemberDeserializer;", "dispatchReceiver", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "getDispatchReceiver", "()Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Companion", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeserializationContext {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<FirTypeParameterSymbol> allTypeParameters;
    private final AnnotationDeserializer annotationDeserializer;
    private final FirConstDeserializer constDeserializer;
    private final DeserializedContainerSource containerSource;
    private final ConeClassLikeType dispatchReceiver;
    private final FirKDocDeserializer kdocDeserializer;
    private final FirMemberDeserializer memberDeserializer;
    private final FirModuleData moduleData;
    private final NameResolver nameResolver;
    private final FirRegularClassSymbol outerClassSymbol;
    private final List<FirTypeParameterSymbol> outerTypeParameters;
    private final FqName packageFqName;
    private final FqName relativeClassName;
    private final FirSession session;
    private final FirTypeDeserializer typeDeserializer;
    private final TypeTable typeTable;
    private final VersionRequirementTable versionRequirementTable;

    public FirDeserializationContext(NameResolver nameResolver, TypeTable typeTable, VersionRequirementTable versionRequirementTable, FirModuleData firModuleData, FqName fqName, FqName fqName2, FirTypeDeserializer firTypeDeserializer, AnnotationDeserializer annotationDeserializer, FirConstDeserializer firConstDeserializer, FirKDocDeserializer firKDocDeserializer, DeserializedContainerSource deserializedContainerSource, FirRegularClassSymbol firRegularClassSymbol, List<FirTypeParameterSymbol> list) {
        nameResolver.getClass();
        typeTable.getClass();
        versionRequirementTable.getClass();
        firModuleData.getClass();
        fqName.getClass();
        firTypeDeserializer.getClass();
        annotationDeserializer.getClass();
        firConstDeserializer.getClass();
        firKDocDeserializer.getClass();
        list.getClass();
        this.nameResolver = nameResolver;
        this.typeTable = typeTable;
        this.versionRequirementTable = versionRequirementTable;
        this.moduleData = firModuleData;
        this.packageFqName = fqName;
        this.relativeClassName = fqName2;
        this.typeDeserializer = firTypeDeserializer;
        this.annotationDeserializer = annotationDeserializer;
        this.constDeserializer = firConstDeserializer;
        this.kdocDeserializer = firKDocDeserializer;
        this.containerSource = deserializedContainerSource;
        this.outerClassSymbol = firRegularClassSymbol;
        this.outerTypeParameters = list;
        this.session = firModuleData.getSession();
        List<FirTypeParameterSymbol> listPlus = CollectionsKt.plus(firTypeDeserializer.getOwnTypeParameters(), list);
        this.allTypeParameters = listPlus;
        this.memberDeserializer = new FirMemberDeserializer(this);
        this.dispatchReceiver = fqName2 != null ? ScopeUtilsKt.defaultType(new ClassId(fqName, fqName2, false), listPlus) : null;
    }

    public static /* synthetic */ FirDeserializationContext childContext$default(FirDeserializationContext firDeserializationContext, List list, FirBasedSymbol firBasedSymbol, NameResolver nameResolver, TypeTable typeTable, FqName fqName, DeserializedContainerSource deserializedContainerSource, FirRegularClassSymbol firRegularClassSymbol, AnnotationDeserializer annotationDeserializer, FirConstDeserializer firConstDeserializer, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            nameResolver = firDeserializationContext.nameResolver;
        }
        NameResolver nameResolver2 = nameResolver;
        if ((i & 8) != 0) {
            typeTable = firDeserializationContext.typeTable;
        }
        return firDeserializationContext.childContext(list, firBasedSymbol, nameResolver2, typeTable, (i & 16) != 0 ? firDeserializationContext.relativeClassName : fqName, (i & 32) != 0 ? firDeserializationContext.containerSource : deserializedContainerSource, (i & 64) != 0 ? firDeserializationContext.outerClassSymbol : firRegularClassSymbol, (i & 128) != 0 ? firDeserializationContext.annotationDeserializer : annotationDeserializer, (i & 256) != 0 ? firDeserializationContext.constDeserializer : firConstDeserializer, (i & 512) != 0 ? true : z);
    }

    public final FirDeserializationContext childContext(List<ProtoBuf.TypeParameter> typeParameterProtos, FirBasedSymbol<?> containingDeclarationSymbol, NameResolver nameResolver, TypeTable typeTable, FqName relativeClassName, DeserializedContainerSource containerSource, FirRegularClassSymbol outerClassSymbol, AnnotationDeserializer annotationDeserializer, FirConstDeserializer constDeserializer, boolean capturesTypeParameters) {
        typeParameterProtos.getClass();
        containingDeclarationSymbol.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        annotationDeserializer.getClass();
        constDeserializer.getClass();
        return new FirDeserializationContext(nameResolver, typeTable, this.versionRequirementTable, this.moduleData, this.packageFqName, relativeClassName, this.typeDeserializer.forChildContext(typeParameterProtos, containingDeclarationSymbol, nameResolver, typeTable, annotationDeserializer), annotationDeserializer, constDeserializer, this.kdocDeserializer, containerSource, outerClassSymbol, capturesTypeParameters ? this.allTypeParameters : CollectionsKt.emptyList());
    }

    public final List<FirTypeParameterSymbol> getAllTypeParameters() {
        return this.allTypeParameters;
    }

    public final AnnotationDeserializer getAnnotationDeserializer() {
        return this.annotationDeserializer;
    }

    public final FirConstDeserializer getConstDeserializer() {
        return this.constDeserializer;
    }

    public final DeserializedContainerSource getContainerSource() {
        return this.containerSource;
    }

    public final ConeClassLikeType getDispatchReceiver() {
        return this.dispatchReceiver;
    }

    public final FirKDocDeserializer getKdocDeserializer() {
        return this.kdocDeserializer;
    }

    public final FirMemberDeserializer getMemberDeserializer() {
        return this.memberDeserializer;
    }

    public final FirModuleData getModuleData() {
        return this.moduleData;
    }

    public final NameResolver getNameResolver() {
        return this.nameResolver;
    }

    public final FirRegularClassSymbol getOuterClassSymbol() {
        return this.outerClassSymbol;
    }

    public final List<FirTypeParameterSymbol> getOuterTypeParameters() {
        return this.outerTypeParameters;
    }

    public final FqName getPackageFqName() {
        return this.packageFqName;
    }

    public final FqName getRelativeClassName() {
        return this.relativeClassName;
    }

    public final FirSession getSession() {
        return this.session;
    }

    public final FirTypeDeserializer getTypeDeserializer() {
        return this.typeDeserializer;
    }

    public final TypeTable getTypeTable() {
        return this.typeTable;
    }

    public final VersionRequirementTable getVersionRequirementTable() {
        return this.versionRequirementTable;
    }

    @Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JP\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J`\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u0092\u0001\u0010!\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\u00072\b\u0010'\u001a\u0004\u0018\u00010\u00072\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 2\f\u0010+\u001a\b\u0012\u0002\b\u0003\u0018\u00010,H\u0002¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext$Companion;", Argument.Delimiters.none, "<init>", "()V", "createForPackage", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "packageProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Package;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "annotationDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer;", "flexibleTypeFactory", "Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;", "constDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirConstDeserializer;", "kdocDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirKDocDeserializer;", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "createForClass", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "outerClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "outerClassEffectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "createRootContext", "typeTable", "Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;", "versionRequirementTable", "Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirementTable;", "packageFqName", "relativeClassName", "typeParameterProtos", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter;", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final FirDeserializationContext createRootContext(NameResolver nameResolver, TypeTable typeTable, FirModuleData moduleData, VersionRequirementTable versionRequirementTable, AnnotationDeserializer annotationDeserializer, FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory, FirConstDeserializer constDeserializer, FirKDocDeserializer kdocDeserializer, FqName packageFqName, FqName relativeClassName, List<ProtoBuf.TypeParameter> typeParameterProtos, DeserializedContainerSource containerSource, FirRegularClassSymbol outerClassSymbol, EffectiveVisibility outerClassEffectiveVisibility, FirBasedSymbol<?> containingDeclarationSymbol) {
            return new FirDeserializationContext(nameResolver, typeTable, versionRequirementTable, moduleData, packageFqName, relativeClassName, new FirTypeDeserializer(moduleData, nameResolver, typeTable, annotationDeserializer, flexibleTypeFactory, typeParameterProtos, null, containingDeclarationSymbol), annotationDeserializer, constDeserializer, kdocDeserializer, containerSource, outerClassSymbol, CollectionsKt.emptyList());
        }

        public final FirDeserializationContext createForClass(ClassId classId, ProtoBuf.Class classProto, NameResolver nameResolver, FirModuleData moduleData, AnnotationDeserializer annotationDeserializer, FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory, FirConstDeserializer constDeserializer, FirKDocDeserializer kdocDeserializer, DeserializedContainerSource containerSource, FirRegularClassSymbol outerClassSymbol, EffectiveVisibility outerClassEffectiveVisibility) {
            classId.getClass();
            classProto.getClass();
            nameResolver.getClass();
            moduleData.getClass();
            annotationDeserializer.getClass();
            flexibleTypeFactory.getClass();
            constDeserializer.getClass();
            kdocDeserializer.getClass();
            outerClassSymbol.getClass();
            outerClassEffectiveVisibility.getClass();
            ProtoBuf.TypeTable typeTable = classProto.getTypeTable();
            typeTable.getClass();
            TypeTable typeTable2 = new TypeTable(typeTable);
            VersionRequirementTable.Companion companion = VersionRequirementTable.Companion;
            ProtoBuf.VersionRequirementTable versionRequirementTable = classProto.getVersionRequirementTable();
            versionRequirementTable.getClass();
            VersionRequirementTable versionRequirementTableCreate = companion.create(versionRequirementTable);
            FqName packageFqName = classId.getPackageFqName();
            FqName relativeClassName = classId.getRelativeClassName();
            List<ProtoBuf.TypeParameter> typeParameterList = classProto.getTypeParameterList();
            typeParameterList.getClass();
            return createRootContext(nameResolver, typeTable2, moduleData, versionRequirementTableCreate, annotationDeserializer, flexibleTypeFactory, constDeserializer, kdocDeserializer, packageFqName, relativeClassName, typeParameterList, containerSource, outerClassSymbol, outerClassEffectiveVisibility, outerClassSymbol);
        }

        public final FirDeserializationContext createForPackage(FqName fqName, ProtoBuf.Package packageProto, NameResolver nameResolver, FirModuleData moduleData, AnnotationDeserializer annotationDeserializer, FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory, FirConstDeserializer constDeserializer, FirKDocDeserializer kdocDeserializer, DeserializedContainerSource containerSource) {
            fqName.getClass();
            packageProto.getClass();
            nameResolver.getClass();
            moduleData.getClass();
            annotationDeserializer.getClass();
            flexibleTypeFactory.getClass();
            constDeserializer.getClass();
            kdocDeserializer.getClass();
            ProtoBuf.TypeTable typeTable = packageProto.getTypeTable();
            typeTable.getClass();
            TypeTable typeTable2 = new TypeTable(typeTable);
            VersionRequirementTable.Companion companion = VersionRequirementTable.Companion;
            ProtoBuf.VersionRequirementTable versionRequirementTable = packageProto.getVersionRequirementTable();
            versionRequirementTable.getClass();
            return createRootContext(nameResolver, typeTable2, moduleData, companion.create(versionRequirementTable), annotationDeserializer, flexibleTypeFactory, constDeserializer, kdocDeserializer, fqName, null, CollectionsKt.emptyList(), containerSource, null, EffectiveVisibility.Public.INSTANCE, null);
        }

        private Companion() {
        }
    }
}
