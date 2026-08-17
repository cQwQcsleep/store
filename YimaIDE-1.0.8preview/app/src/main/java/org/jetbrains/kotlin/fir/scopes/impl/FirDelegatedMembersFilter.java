package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirComposableSessionComponent;
import org.jetbrains.kotlin.fir.SessionConfiguration;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\r\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H&J\u001a\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00000\u000bH\u0017b\u0002\b\f¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirDelegatedMembersFilter;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "<init>", "()V", "shouldNotGenerateDelegatedMember", Argument.Delimiters.none, "memberSymbolFromSuperInterface", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "createComposed", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirDelegatedMembersFilter$Composed;", "components", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "Default", "Composed", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirDelegatedMembersFilter implements FirComposableSessionComponent<FirDelegatedMembersFilter> {

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirDelegatedMembersFilter$Composed;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirDelegatedMembersFilter;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "components", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "shouldNotGenerateDelegatedMember", Argument.Delimiters.none, "memberSymbolFromSuperInterface", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Composed extends FirDelegatedMembersFilter implements FirComposableSessionComponent.Composed<FirDelegatedMembersFilter> {
        private final List<FirDelegatedMembersFilter> components;

        /* JADX WARN: Multi-variable type inference failed */
        public Composed(List<? extends FirDelegatedMembersFilter> list) {
            list.getClass();
            this.components = list;
        }

        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        public List<FirDelegatedMembersFilter> getComponents() {
            return this.components;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.impl.FirDelegatedMembersFilter
        public boolean shouldNotGenerateDelegatedMember(FirCallableSymbol<?> memberSymbolFromSuperInterface) {
            memberSymbolFromSuperInterface.getClass();
            List<FirDelegatedMembersFilter> components = getComponents();
            if ((components instanceof Collection) && components.isEmpty()) {
                return false;
            }
            Iterator<T> it = components.iterator();
            while (it.hasNext()) {
                if (((FirDelegatedMembersFilter) it.next()).shouldNotGenerateDelegatedMember(memberSymbolFromSuperInterface)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirDelegatedMembersFilter$Default;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirDelegatedMembersFilter;", "<init>", "()V", "shouldNotGenerateDelegatedMember", Argument.Delimiters.none, "memberSymbolFromSuperInterface", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Default extends FirDelegatedMembersFilter {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.fir.scopes.impl.FirDelegatedMembersFilter
        public boolean shouldNotGenerateDelegatedMember(FirCallableSymbol<?> memberSymbolFromSuperInterface) {
            memberSymbolFromSuperInterface.getClass();
            return false;
        }
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public Composed createComposed(List<? extends FirDelegatedMembersFilter> components) {
        components.getClass();
        return new Composed(components);
    }

    public abstract boolean shouldNotGenerateDelegatedMember(FirCallableSymbol<?> memberSymbolFromSuperInterface);

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public /* bridge */ /* synthetic */ FirComposableSessionComponent.Composed createComposed(List list) {
        return createComposed((List<? extends FirDelegatedMembersFilter>) list);
    }
}
