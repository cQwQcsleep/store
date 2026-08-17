package org.jetbrains.kotlin.fir.deserialization;

import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.VersionRequirement;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;
import org.jetbrains.kotlin.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001aT\u0010\b\u001a\u00020\t\"\u000e\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u000b*\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u0002H\n2\u001d\u0010\r\u001a\u0019\u0012\u0004\u0012\u0002H\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00010\u000e¢\u0006\u0002\b\u0010H\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u0011\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0012"}, d2 = {CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement;", "Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement$Companion;", "proto", "Lorg/jetbrains/kotlin/protobuf/MessageLiteOrBuilder;", "context", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;", "deserializeCompilerPluginMetadata", Argument.Delimiters.none, "M", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableMessage;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getCompilerPluginMetadataList", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$CompilerPluginData;", "Lkotlin/ExtensionFunctionType;", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableMessage;Lkotlin/jvm/functions/Function1;)V", "org.jetbrains.kotlin:fir-deserialization"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeserializationUtilsKt {
    public static final List<VersionRequirement> create(VersionRequirement.Companion companion, MessageLiteOrBuilder messageLiteOrBuilder, FirDeserializationContext firDeserializationContext) {
        companion.getClass();
        messageLiteOrBuilder.getClass();
        firDeserializationContext.getClass();
        return companion.create(messageLiteOrBuilder, firDeserializationContext.getNameResolver(), firDeserializationContext.getVersionRequirementTable());
    }

    public static final <M extends GeneratedMessageLite.ExtendableMessage<M>> void deserializeCompilerPluginMetadata(FirDeclaration firDeclaration, FirDeserializationContext firDeserializationContext, M m, Function1<? super M, ? extends List<ProtoBuf.CompilerPluginData>> function1) {
        firDeclaration.getClass();
        firDeserializationContext.getClass();
        m.getClass();
        function1.getClass();
        Object objInvoke = function1.invoke(m);
        if (((List) objInvoke).isEmpty()) {
            objInvoke = null;
        }
        List list = (List) objInvoke;
        if (list != null) {
            List<ProtoBuf.CompilerPluginData> list2 = list;
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list2, 10)), 16));
            for (ProtoBuf.CompilerPluginData compilerPluginData : list2) {
                linkedHashMap.put(firDeserializationContext.getNameResolver().getString(compilerPluginData.getPluginId()), compilerPluginData.getData().toByteArray());
            }
            DeclarationAttributesKt.setCompilerPluginMetadata(firDeclaration, linkedHashMap);
        }
    }
}
