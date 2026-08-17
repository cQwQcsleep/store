package org.jetbrains.kotlin.fir.backend.jvm;

import defpackage.f2f;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.backend.jvm.metadata.BuiltinsSerializer;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.backend.FirMetadataSource;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.comparators.FirMemberDeclarationComparator;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirCloneableSymbolProvider;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.serialization.FirAdditionalMetadataProvider;
import org.jetbrains.kotlin.fir.serialization.FirElementSerializer;
import org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase;
import org.jetbrains.kotlin.fir.serialization.TypeApproximatorForMetadataSerializer;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.builtins.BuiltInsBinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.serialization.deserialization.builtins.BuiltInSerializerProtocol;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u0015\u0016B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J(\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e0\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\rH\u0016J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u000fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirBuiltInsSerializer;", "Lorg/jetbrains/kotlin/backend/jvm/metadata/BuiltinsSerializer;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "serialize", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/name/FqName;", Argument.Delimiters.none, "filesMetadata", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$File;", "serializeEmptyPackage", "fqName", "PackageSerializer", "FirBuiltInsSerializerExtension", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirBuiltInsSerializer implements BuiltinsSerializer {
    private final ScopeSession scopeSession;
    private final FirSession session;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirBuiltInsSerializer$FirBuiltInsSerializerExtension;", "Lorg/jetbrains/kotlin/fir/serialization/FirSerializerExtensionBase;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "additionalMetadataProvider", "Lorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;", "getAdditionalMetadataProvider", "()Lorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;", "shouldUseTypeTable", Argument.Delimiters.none, "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FirBuiltInsSerializerExtension extends FirSerializerExtensionBase {
        private final ScopeSession scopeSession;
        private final FirSession session;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FirBuiltInsSerializerExtension(FirSession firSession, ScopeSession scopeSession) {
            super(BuiltInSerializerProtocol.INSTANCE, null, 2, null);
            firSession.getClass();
            scopeSession.getClass();
            this.session = firSession;
            this.scopeSession = scopeSession;
        }

        @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
        public FirAdditionalMetadataProvider getAdditionalMetadataProvider() {
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
        public BinaryVersion getMetadataVersion() {
            return BuiltInsBinaryVersion.INSTANCE;
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
        /* JADX INFO: renamed from: shouldUseTypeTable */
        public boolean getUseTypeTable() {
            return true;
        }
    }

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u0006\u0010#\u001a\u00020$J\u001e\u0010%\u001a\u00020&2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010'\u001a\u00020\"H\u0002J\b\u0010(\u001a\u00020&H\u0002J\b\u0010)\u001a\u00020&H\u0002J\b\u0010*\u001a\u00020$H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\n \u001a*\u0004\u0018\u00010\u00190\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirBuiltInsSerializer$PackageSerializer;", Argument.Delimiters.none, "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "classes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "members", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/jvm/FirBuiltInsSerializer;Lorg/jetbrains/kotlin/name/FqName;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getClasses", "()Ljava/util/List;", "getMembers", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$PackageFragment$Builder;", "kotlin.jvm.PlatformType", "extension", "Lorg/jetbrains/kotlin/fir/backend/jvm/FirBuiltInsSerializer$FirBuiltInsSerializerExtension;", "typeApproximator", "Lorg/jetbrains/kotlin/fir/serialization/TypeApproximatorForMetadataSerializer;", "getTypeApproximator", "()Lorg/jetbrains/kotlin/fir/serialization/TypeApproximatorForMetadataSerializer;", "rootSerializer", "Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer;", "serialize", Argument.Delimiters.none, "serializeClasses", Argument.Delimiters.none, "parentSerializer", "serializeMembers", "serializeStringTable", "serializeToByteArray", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class PackageSerializer {
        private final List<FirRegularClass> classes;
        private final FirBuiltInsSerializerExtension extension;
        private final List<FirMemberDeclaration> members;
        private final FqName packageFqName;
        private final ProtoBuf.PackageFragment.Builder proto;
        private final FirElementSerializer rootSerializer;
        private final ScopeSession scopeSession;
        private final FirSession session;
        final /* synthetic */ FirBuiltInsSerializer this$0;
        private final TypeApproximatorForMetadataSerializer typeApproximator;

        /* JADX WARN: Multi-variable type inference failed */
        public PackageSerializer(FirBuiltInsSerializer firBuiltInsSerializer, FqName fqName, List<? extends FirRegularClass> list, List<? extends FirMemberDeclaration> list2, FirSession firSession, ScopeSession scopeSession) {
            fqName.getClass();
            list.getClass();
            list2.getClass();
            firSession.getClass();
            scopeSession.getClass();
            this.this$0 = firBuiltInsSerializer;
            this.packageFqName = fqName;
            this.classes = list;
            this.members = list2;
            this.session = firSession;
            this.scopeSession = scopeSession;
            this.proto = ProtoBuf.PackageFragment.newBuilder();
            FirBuiltInsSerializerExtension firBuiltInsSerializerExtension = new FirBuiltInsSerializerExtension(firSession, scopeSession);
            this.extension = firBuiltInsSerializerExtension;
            TypeApproximatorForMetadataSerializer typeApproximatorForMetadataSerializer = new TypeApproximatorForMetadataSerializer(firSession);
            this.typeApproximator = typeApproximatorForMetadataSerializer;
            this.rootSerializer = FirElementSerializer.Companion.createTopLevel$default(FirElementSerializer.INSTANCE, firSession, scopeSession, firBuiltInsSerializerExtension, typeApproximatorForMetadataSerializer, FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession), false, 32, null);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        /* JADX WARN: Multi-variable type inference failed */
        private final void serializeClasses(List<? extends FirRegularClass> classes, FirElementSerializer parentSerializer) throws KotlinIllegalArgumentExceptionWithAttachments {
            FirElementSerializer firElementSerializer;
            for (FirRegularClass firRegularClass : CollectionsKt.sortedWith(classes, FirMemberDeclarationComparator.INSTANCE)) {
                List<FirClassifierSymbol<?>> listComputeNestedClassifiersForClass = parentSerializer.computeNestedClassifiersForClass(firRegularClass.getSymbol());
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listComputeNestedClassifiersForClass, 10));
                Iterator<T> it = listComputeNestedClassifiersForClass.iterator();
                while (it.hasNext()) {
                    E fir = ((FirClassifierSymbol) it.next()).getFir();
                    fir.getClass();
                    arrayList.add((FirRegularClass) fir);
                }
                if (arrayList.isEmpty()) {
                    firElementSerializer = parentSerializer;
                } else {
                    FirElementSerializer.Companion companion = FirElementSerializer.INSTANCE;
                    FirSession firSession = this.session;
                    firElementSerializer = parentSerializer;
                    serializeClasses(arrayList, companion.create(firSession, this.scopeSession, firRegularClass, this.extension, firElementSerializer, this.typeApproximator, FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession), (128 & 128) != 0 ? false : false));
                }
                this.proto.addClass_(firElementSerializer.classProto(firRegularClass, FirProviderKt.getFirProvider(this.session).getFirClassifierContainerFileIfAny(firRegularClass.getSymbol())).build());
                parentSerializer = firElementSerializer;
            }
        }

        private final void serializeMembers() {
            this.proto.setPackage(this.rootSerializer.packagePartProto(this.packageFqName, CollectionsKt.sortedWith(this.members, FirMemberDeclarationComparator.INSTANCE), null).build());
        }

        private final void serializeStringTable() {
            Pair pairBuildProto = this.extension.getStringTable().buildProto();
            ProtoBuf.StringTable stringTable = (ProtoBuf.StringTable) pairBuildProto.component1();
            ProtoBuf.QualifiedNameTable qualifiedNameTable = (ProtoBuf.QualifiedNameTable) pairBuildProto.component2();
            this.proto.setStrings(stringTable);
            this.proto.setQualifiedNames(qualifiedNameTable);
        }

        private final byte[] serializeToByteArray() throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            int[] array = this.extension.getMetadataVersion().toArray();
            dataOutputStream.writeInt(array.length);
            for (int i : array) {
                dataOutputStream.writeInt(i);
            }
            this.proto.build().writeTo(byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArray.getClass();
            return byteArray;
        }

        public final List<FirRegularClass> getClasses() {
            return this.classes;
        }

        public final List<FirMemberDeclaration> getMembers() {
            return this.members;
        }

        public final FqName getPackageFqName() {
            return this.packageFqName;
        }

        public final ScopeSession getScopeSession() {
            return this.scopeSession;
        }

        public final FirSession getSession() {
            return this.session;
        }

        public final TypeApproximatorForMetadataSerializer getTypeApproximator() {
            return this.typeApproximator;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        public final byte[] serialize() throws KotlinIllegalArgumentExceptionWithAttachments {
            serializeClasses(this.classes, this.rootSerializer);
            serializeMembers();
            serializeStringTable();
            return serializeToByteArray();
        }
    }

    @Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"org/jetbrains/kotlin/fir/backend/jvm/FirBuiltInsSerializer$serialize$PackageContent", Argument.Delimiters.none, "<init>", "()V", "classes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "getClasses", "()Ljava/util/List;", "members", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "getMembers", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class PackageContent {
        private final List<FirRegularClass> classes = new ArrayList();
        private final List<FirMemberDeclaration> members = new ArrayList();

        public final List<FirRegularClass> getClasses() {
            return this.classes;
        }

        public final List<FirMemberDeclaration> getMembers() {
            return this.members;
        }
    }

    public FirBuiltInsSerializer(FirSession firSession, ScopeSession scopeSession) {
        firSession.getClass();
        scopeSession.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
    }

    public final ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    public final FirSession getSession() {
        return this.session;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.backend.jvm.metadata.BuiltinsSerializer
    public List<Pair<FqName, byte[]>> serialize(List<? extends MetadataSource.File> filesMetadata) throws KotlinIllegalArgumentExceptionWithAttachments {
        filesMetadata.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<? extends MetadataSource.File> list = filesMetadata;
        ArrayList<FirFile> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (MetadataSource.File file : list) {
            file.getClass();
            arrayList.add(((FirMetadataSource.File) file).getFir());
        }
        for (FirFile firFile : arrayList) {
            FqName packageFqName = UtilsKt.getPackageFqName(firFile);
            Object packageContent = linkedHashMap.get(packageFqName);
            if (packageContent == null) {
                packageContent = new PackageContent();
                linkedHashMap.put(packageFqName, packageContent);
            }
            PackageContent packageContent2 = (PackageContent) packageContent;
            for (FirDeclaration firDeclaration : firFile.getDeclarations()) {
                if ((firDeclaration instanceof FirCallableDeclaration) || (firDeclaration instanceof FirTypeAlias)) {
                    packageContent2.getMembers().add(firDeclaration);
                } else {
                    if (!(firDeclaration instanceof FirRegularClass)) {
                        f2f.a("Unexpected declaration: ", UtilsKt.render(firDeclaration));
                        return null;
                    }
                    packageContent2.getClasses().add(firDeclaration);
                }
            }
        }
        FqName fqName = StandardNames.BUILT_INS_PACKAGE_FQ_NAME;
        Object packageContent3 = linkedHashMap.get(fqName);
        if (packageContent3 == null) {
            packageContent3 = new PackageContent();
            linkedHashMap.put(fqName, packageContent3);
        }
        List<FirRegularClass> classes = ((PackageContent) packageContent3).getClasses();
        FirSession firSession = this.session;
        FirClassLikeSymbol<?> classLikeSymbolByClassId = new FirCloneableSymbolProvider(firSession, FirModuleDataKt.getModuleData(firSession), FirKotlinScopeProviderKt.getKotlinScopeProvider(this.session)).getClassLikeSymbolByClassId(StandardClassIds.INSTANCE.getCloneable());
        classLikeSymbolByClassId.getClass();
        E fir = classLikeSymbolByClassId.getFir();
        fir.getClass();
        classes.add((FirRegularClass) fir);
        ArrayList arrayList2 = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            FqName fqName2 = (FqName) entry.getKey();
            PackageContent packageContent4 = (PackageContent) entry.getValue();
            FirBuiltInsSerializer firBuiltInsSerializer = this;
            arrayList2.add(new Pair(fqName2, new PackageSerializer(firBuiltInsSerializer, fqName2, packageContent4.getClasses(), packageContent4.getMembers(), this.session, this.scopeSession).serialize()));
            this = firBuiltInsSerializer;
        }
        return CollectionsKt.toList(arrayList2);
    }

    @Override // org.jetbrains.kotlin.backend.jvm.metadata.BuiltinsSerializer
    public byte[] serializeEmptyPackage(FqName fqName) {
        fqName.getClass();
        return new PackageSerializer(this, fqName, CollectionsKt.emptyList(), CollectionsKt.emptyList(), this.session, this.scopeSession).serialize();
    }
}
