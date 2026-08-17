package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.psi.search.GlobalSearchScope;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0001H\u0096\u0002J\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0001H\u0096\u0002J\t\u0010\u000e\u001a\u00020\u0001H\u0096\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\n¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/PsiBasedProjectFileSearchScope;", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "psiSearchScope", "Lcom/intellij/psi/search/GlobalSearchScope;", "<init>", "(Lcom/intellij/psi/search/GlobalSearchScope;)V", "getPsiSearchScope", "()Lcom/intellij/psi/search/GlobalSearchScope;", "isEmpty", Argument.Delimiters.none, "()Z", "minus", "other", "plus", "not", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PsiBasedProjectFileSearchScope implements AbstractProjectFileSearchScope {
    private final GlobalSearchScope psiSearchScope;

    public PsiBasedProjectFileSearchScope(GlobalSearchScope globalSearchScope) {
        globalSearchScope.getClass();
        this.psiSearchScope = globalSearchScope;
    }

    public final GlobalSearchScope getPsiSearchScope() {
        return this.psiSearchScope;
    }

    @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope
    public boolean isEmpty() {
        return Intrinsics.areEqual(this.psiSearchScope, GlobalSearchScope.EMPTY_SCOPE);
    }

    @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope
    public AbstractProjectFileSearchScope minus(AbstractProjectFileSearchScope other) {
        other.getClass();
        GlobalSearchScope globalSearchScopeIntersectWith = this.psiSearchScope.intersectWith(GlobalSearchScope.notScope(VfsBasedProjectEnvironmentKt.asPsiSearchScope(other)));
        globalSearchScopeIntersectWith.getClass();
        return new PsiBasedProjectFileSearchScope(globalSearchScopeIntersectWith);
    }

    @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope
    public AbstractProjectFileSearchScope not() {
        GlobalSearchScope globalSearchScopeNotScope = GlobalSearchScope.notScope(this.psiSearchScope);
        globalSearchScopeNotScope.getClass();
        return new PsiBasedProjectFileSearchScope(globalSearchScopeNotScope);
    }

    @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope
    public AbstractProjectFileSearchScope plus(AbstractProjectFileSearchScope other) {
        other.getClass();
        GlobalSearchScope globalSearchScopeUniteWith = this.psiSearchScope.uniteWith(VfsBasedProjectEnvironmentKt.asPsiSearchScope(other));
        globalSearchScopeUniteWith.getClass();
        return new PsiBasedProjectFileSearchScope(globalSearchScopeUniteWith);
    }
}
