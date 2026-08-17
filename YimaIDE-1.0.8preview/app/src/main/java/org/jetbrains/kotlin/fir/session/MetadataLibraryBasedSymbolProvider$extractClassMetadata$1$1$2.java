package org.jetbrains.kotlin.fir.session;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.deserialization.FirNestedTypeAliasDeserializationContext;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class MetadataLibraryBasedSymbolProvider$extractClassMetadata$1$1$2 extends FunctionReferenceImpl implements Function2<ClassId, FirNestedTypeAliasDeserializationContext, FirTypeAliasSymbol> {
    public MetadataLibraryBasedSymbolProvider$extractClassMetadata$1$1$2(Object obj) {
        super(2, obj, MetadataLibraryBasedSymbolProvider.class, "getTypeAlias", "getTypeAlias(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/fir/deserialization/FirNestedTypeAliasDeserializationContext;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", 0);
    }

    public final FirTypeAliasSymbol invoke(ClassId classId, FirNestedTypeAliasDeserializationContext firNestedTypeAliasDeserializationContext) {
        classId.getClass();
        return ((MetadataLibraryBasedSymbolProvider) ((CallableReference) this).receiver).getTypeAlias(classId, firNestedTypeAliasDeserializationContext);
    }
}
