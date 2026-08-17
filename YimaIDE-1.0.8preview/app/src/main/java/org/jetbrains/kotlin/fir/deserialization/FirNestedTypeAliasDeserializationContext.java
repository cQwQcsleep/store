package org.jetbrains.kotlin.fir.deserialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.scopes.FirScopeProvider;
import org.jetbrains.kotlin.metadata.ProtoBuf;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/FirNestedTypeAliasDeserializationContext;", Argument.Delimiters.none, "memberDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirMemberDeserializer;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeAlias;", "scopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/deserialization/FirMemberDeserializer;Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeAlias;Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;)V", "getMemberDeserializer", "()Lorg/jetbrains/kotlin/fir/deserialization/FirMemberDeserializer;", "getProto", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeAlias;", "getScopeProvider", "()Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNestedTypeAliasDeserializationContext {
    private final FirMemberDeserializer memberDeserializer;
    private final ProtoBuf.TypeAlias proto;
    private final FirScopeProvider scopeProvider;

    public FirNestedTypeAliasDeserializationContext(FirMemberDeserializer firMemberDeserializer, ProtoBuf.TypeAlias typeAlias, FirScopeProvider firScopeProvider) {
        firMemberDeserializer.getClass();
        typeAlias.getClass();
        firScopeProvider.getClass();
        this.memberDeserializer = firMemberDeserializer;
        this.proto = typeAlias;
        this.scopeProvider = firScopeProvider;
    }

    public final FirMemberDeserializer getMemberDeserializer() {
        return this.memberDeserializer;
    }

    public final ProtoBuf.TypeAlias getProto() {
        return this.proto;
    }

    public final FirScopeProvider getScopeProvider() {
        return this.scopeProvider;
    }
}
