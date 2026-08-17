package org.jetbrains.kotlin.fir.deserialization;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class AbstractFirDeserializedSymbolProvider$typeAliasCache$1 extends FunctionReferenceImpl implements Function2<ClassId, FirNestedTypeAliasDeserializationContext, Pair<? extends FirTypeAliasSymbol, ? extends Function1<? super FirTypeAliasSymbol, ? extends Unit>>> {
    public AbstractFirDeserializedSymbolProvider$typeAliasCache$1(Object obj) {
        super(2, obj, AbstractFirDeserializedSymbolProvider.class, "findAndDeserializeTypeAlias", "findAndDeserializeTypeAlias(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/fir/deserialization/FirNestedTypeAliasDeserializationContext;)Lkotlin/Pair;", 0);
    }

    public final Pair<FirTypeAliasSymbol, Function1<FirTypeAliasSymbol, Unit>> invoke(ClassId classId, FirNestedTypeAliasDeserializationContext firNestedTypeAliasDeserializationContext) {
        classId.getClass();
        return ((AbstractFirDeserializedSymbolProvider) ((CallableReference) this).receiver).findAndDeserializeTypeAlias(classId, firNestedTypeAliasDeserializationContext);
    }
}
