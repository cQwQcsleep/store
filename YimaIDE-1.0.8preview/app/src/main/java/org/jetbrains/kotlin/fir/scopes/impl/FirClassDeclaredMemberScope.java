package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.utils.SmartList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\r\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H'b\u0002\b\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassDeclaredMemberScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;)V", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "scopeOwnerLookupNames", Argument.Delimiters.none, Argument.Delimiters.none, "getScopeOwnerLookupNames", "()Ljava/util/List;", "withReplacedSessionOrNull", "newSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirClassDeclaredMemberScope extends FirContainingNamesAwareScope {
    private final ClassId classId;
    private final List<String> scopeOwnerLookupNames;

    public FirClassDeclaredMemberScope(ClassId classId) {
        classId.getClass();
        this.classId = classId;
        this.scopeOwnerLookupNames = new SmartList(classId.asFqNameString());
    }

    public final ClassId getClassId() {
        return this.classId;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public List<String> getScopeOwnerLookupNames() {
        return this.scopeOwnerLookupNames;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public abstract FirClassDeclaredMemberScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession);
}
