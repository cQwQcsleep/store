package org.jetbrains.kotlin.fir.serialization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.library.metadata.KlibMetadataSerializationUtilsKt;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001aH\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010¨\u0006\u0011"}, d2 = {"serializeSingleFirFile", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$PackageFragment;", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "actualizedExpectDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "serializerExtension", "Lorg/jetbrains/kotlin/fir/serialization/FirKLibSerializerExtension;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "produceHeaderKlib", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-serialization"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirKlibSerializationKt {
    public static final ProtoBuf.PackageFragment serializeSingleFirFile(FirFile firFile, FirSession firSession, ScopeSession scopeSession, Set<? extends FirDeclaration> set, FirKLibSerializerExtension firKLibSerializerExtension, LanguageVersionSettings languageVersionSettings, boolean z) {
        firFile.getClass();
        firSession.getClass();
        scopeSession.getClass();
        firKLibSerializerExtension.getClass();
        languageVersionSettings.getClass();
        TypeApproximatorForMetadataSerializer typeApproximatorForMetadataSerializer = new TypeApproximatorForMetadataSerializer(firSession);
        ProtoBuf.Package packageBuild = FirElementSerializer.INSTANCE.createTopLevel(firSession, scopeSession, firKLibSerializerExtension, typeApproximatorForMetadataSerializer, languageVersionSettings, z).packagePartProto(firFile, set).build();
        ArrayList arrayList = new ArrayList();
        for (FirDeclaration firDeclaration : firFile.getDeclarations()) {
            FirClass firClass = firDeclaration instanceof FirClass ? (FirClass) firDeclaration : null;
            if (firClass != null) {
                TypeApproximatorForMetadataSerializer typeApproximatorForMetadataSerializer2 = typeApproximatorForMetadataSerializer;
                serializeSingleFirFile$makeClassProtoWithNested(firClass, set, z, firSession, scopeSession, firKLibSerializerExtension, typeApproximatorForMetadataSerializer2, languageVersionSettings, arrayList, firFile);
                typeApproximatorForMetadataSerializer = typeApproximatorForMetadataSerializer2;
            }
        }
        List<FirAnnotation> listNonSourceAnnotations = FirAnnotationUtilsKt.nonSourceAnnotations(firFile, firSession);
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = listNonSourceAnnotations.iterator();
        while (it.hasNext()) {
            ProtoBuf.Annotation annotationSerializeAnnotation = firKLibSerializerExtension.getAnnotationSerializer().serializeAnnotation((FirAnnotation) it.next());
            if (annotationSerializeAnnotation != null) {
                arrayList2.add(annotationSerializeAnnotation);
            }
        }
        packageBuild.getClass();
        FqName packageFqName = UtilsKt.getPackageFqName(firFile);
        boolean z2 = packageBuild.getFunctionList().isEmpty() && packageBuild.getPropertyList().isEmpty() && packageBuild.getTypeAliasList().isEmpty() && arrayList.isEmpty();
        FirElementAwareSerializableStringTable stringTable = firKLibSerializerExtension.getStringTable();
        stringTable.getClass();
        return KlibMetadataSerializationUtilsKt.buildKlibPackageFragment(packageBuild, arrayList, packageFqName, z2, stringTable, arrayList2);
    }

    public static /* synthetic */ ProtoBuf.PackageFragment serializeSingleFirFile$default(FirFile firFile, FirSession firSession, ScopeSession scopeSession, Set set, FirKLibSerializerExtension firKLibSerializerExtension, LanguageVersionSettings languageVersionSettings, boolean z, int i, Object obj) {
        if ((i & 64) != 0) {
            z = false;
        }
        return serializeSingleFirFile(firFile, firSession, scopeSession, set, firKLibSerializerExtension, languageVersionSettings, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void serializeSingleFirFile$makeClassProtoWithNested(FirClass firClass, Set<? extends FirDeclaration> set, boolean z, FirSession firSession, ScopeSession scopeSession, FirKLibSerializerExtension firKLibSerializerExtension, TypeApproximatorForMetadataSerializer typeApproximatorForMetadataSerializer, LanguageVersionSettings languageVersionSettings, List<Pair<ProtoBuf.Class, Integer>> list, FirFile firFile) {
        FirClass firClass2;
        if (SerializationUtilKt.isNotExpectOrShouldBeSerialized(firClass, set) && SerializationUtilKt.isNotPrivateOrShouldBeSerialized(firClass, z)) {
            FirElementSerializer firElementSerializerCreate = FirElementSerializer.INSTANCE.create(firSession, scopeSession, firClass, firKLibSerializerExtension, null, typeApproximatorForMetadataSerializer, languageVersionSettings, z);
            FirFile firFile2 = firFile;
            list.add(TuplesKt.to(firElementSerializerCreate.classProto(firClass, firFile2).build(), Integer.valueOf(firElementSerializerCreate.getStringTable().getFqNameIndex(firClass))));
            for (FirClassifierSymbol<?> firClassifierSymbol : firElementSerializerCreate.computeNestedClassifiersForClass(firClass.getSymbol())) {
                FirClassSymbol firClassSymbol = firClassifierSymbol instanceof FirClassSymbol ? (FirClassSymbol) firClassifierSymbol : null;
                if (firClassSymbol != null && (firClass2 = (FirClass) firClassSymbol.getFir()) != null) {
                    serializeSingleFirFile$makeClassProtoWithNested(firClass2, set, z, firSession, scopeSession, firKLibSerializerExtension, typeApproximatorForMetadataSerializer, languageVersionSettings, list, firFile2);
                }
                firFile2 = firFile;
            }
        }
    }
}
