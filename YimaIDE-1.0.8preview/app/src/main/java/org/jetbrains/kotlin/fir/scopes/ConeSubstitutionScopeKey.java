package org.jetbrains.kotlin.fir.scopes;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.resolve.ScopeSessionKey;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.impl.FirClassSubstitutionScope;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¤\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¤\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u0007X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\t¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/ConeSubstitutionScopeKey;", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSessionKey;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassSubstitutionScope;", "<init>", "()V", "lookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "getLookupTag", "()Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "isFromExpectClass", Argument.Delimiters.none, "()Z", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "getSubstitutor", "()Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "derivedClassLookupTag", "getDerivedClassLookupTag", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConeSubstitutionScopeKey extends ScopeSessionKey<FirClass, FirClassSubstitutionScope> {
    public abstract ConeClassLikeLookupTag getDerivedClassLookupTag();

    public abstract ConeClassLikeLookupTag getLookupTag();

    public abstract ConeSubstitutor getSubstitutor();

    public abstract boolean isFromExpectClass();
}
