package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirComposableSessionComponent;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionConfiguration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirEnumEntriesSupport;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001a\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00000\u0013H\u0017b\u0002\b\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\b\u0010\n¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirEnumEntriesSupport;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "isEnumEntriesAvailable", Argument.Delimiters.none, "()Z", "isEnumEntriesAvailable$delegate", "Lkotlin/Lazy;", "canSynthesizeEnumEntriesFor", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "createComposed", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirEnumEntriesSupport$Composed;", "components", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "Composed", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirEnumEntriesSupport implements FirComposableSessionComponent<FirEnumEntriesSupport> {

    /* JADX INFO: renamed from: isEnumEntriesAvailable$delegate, reason: from kotlin metadata */
    private final Lazy isEnumEntriesAvailable;
    private final FirSession session;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirEnumEntriesSupport$Composed;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirEnumEntriesSupport;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "components", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "canSynthesizeEnumEntriesFor", Argument.Delimiters.none, "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Composed extends FirEnumEntriesSupport implements FirComposableSessionComponent.Composed<FirEnumEntriesSupport> {
        private final List<FirEnumEntriesSupport> components;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public Composed(FirSession firSession, List<? extends FirEnumEntriesSupport> list) {
            super(firSession);
            firSession.getClass();
            list.getClass();
            this.components = list;
        }

        @Override // org.jetbrains.kotlin.fir.scopes.impl.FirEnumEntriesSupport
        public boolean canSynthesizeEnumEntriesFor(FirClass klass) {
            klass.getClass();
            List<FirEnumEntriesSupport> components = getComponents();
            if ((components instanceof Collection) && components.isEmpty()) {
                return true;
            }
            Iterator<T> it = components.iterator();
            while (it.hasNext()) {
                if (!((FirEnumEntriesSupport) it.next()).canSynthesizeEnumEntriesFor(klass)) {
                    return false;
                }
            }
            return true;
        }

        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        public List<FirEnumEntriesSupport> getComponents() {
            return this.components;
        }
    }

    public FirEnumEntriesSupport(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.isEnumEntriesAvailable = LazyKt.lazy(new Function0() { // from class: o55
            public final Object invoke() {
                return Boolean.valueOf(FirEnumEntriesSupport.a(this.b));
            }
        });
    }

    public static boolean a(FirEnumEntriesSupport firEnumEntriesSupport) {
        return FirSymbolProviderKt.getRegularClassSymbolByClassId(firEnumEntriesSupport.session, StandardClassIds.INSTANCE.getEnumEntries()) != null;
    }

    public boolean canSynthesizeEnumEntriesFor(FirClass klass) {
        klass.getClass();
        return ClassMembersKt.getHasEnumEntries(klass) && isEnumEntriesAvailable();
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public Composed createComposed(List<? extends FirEnumEntriesSupport> components) {
        components.getClass();
        return new Composed(this.session, components);
    }

    public final FirSession getSession() {
        return this.session;
    }

    public final boolean isEnumEntriesAvailable() {
        return ((Boolean) this.isEnumEntriesAvailable.getValue()).booleanValue();
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public /* bridge */ /* synthetic */ FirComposableSessionComponent.Composed createComposed(List list) {
        return createComposed((List<? extends FirEnumEntriesSupport>) list);
    }
}
