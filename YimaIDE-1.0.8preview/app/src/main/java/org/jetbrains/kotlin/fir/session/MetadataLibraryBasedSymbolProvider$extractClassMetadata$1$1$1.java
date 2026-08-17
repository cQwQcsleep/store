package org.jetbrains.kotlin.fir.session;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.deserialization.FirDeserializationContext;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class MetadataLibraryBasedSymbolProvider$extractClassMetadata$1$1$1 extends FunctionReferenceImpl implements Function2<ClassId, FirDeserializationContext, FirRegularClassSymbol> {
    public MetadataLibraryBasedSymbolProvider$extractClassMetadata$1$1$1(Object obj) {
        super(2, obj, MetadataLibraryBasedSymbolProvider.class, "getClass", "getClass(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", 0);
    }

    public final FirRegularClassSymbol invoke(ClassId classId, FirDeserializationContext firDeserializationContext) {
        classId.getClass();
        return ((MetadataLibraryBasedSymbolProvider) ((CallableReference) this).receiver).getClass(classId, firDeserializationContext);
    }
}
