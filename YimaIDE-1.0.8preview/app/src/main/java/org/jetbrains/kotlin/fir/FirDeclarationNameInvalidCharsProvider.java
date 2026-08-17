package org.jetbrains.kotlin.fir;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0003\u000e\u000f\u0010B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\fH\u0017b\u0002\b\rR\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0002\n\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirDeclarationNameInvalidCharsProvider;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "<init>", "()V", "invalidChars", Argument.Delimiters.none, Argument.Delimiters.none, "getInvalidChars", "()Ljava/util/Set;", "createComposed", "Lorg/jetbrains/kotlin/fir/FirDeclarationNameInvalidCharsProvider$Composed;", "components", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "Composed", "Simple", "Companion", "Lorg/jetbrains/kotlin/fir/FirDeclarationNameInvalidCharsProvider$Simple;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirDeclarationNameInvalidCharsProvider implements FirComposableSessionComponent<FirDeclarationNameInvalidCharsProvider> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0010\f\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirDeclarationNameInvalidCharsProvider$Composed;", "Lorg/jetbrains/kotlin/fir/FirDeclarationNameInvalidCharsProvider;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "components", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "invalidChars", Argument.Delimiters.none, Argument.Delimiters.none, "getInvalidChars", "()Ljava/util/Set;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Composed extends FirDeclarationNameInvalidCharsProvider implements FirComposableSessionComponent.Composed<FirDeclarationNameInvalidCharsProvider> {
        private final List<FirDeclarationNameInvalidCharsProvider> components;
        private final Set<Character> invalidChars;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public Composed(List<? extends FirDeclarationNameInvalidCharsProvider> list) {
            super(null);
            list.getClass();
            this.components = list;
            List<FirDeclarationNameInvalidCharsProvider> components = getComponents();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator<T> it = components.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(linkedHashSet, ((FirDeclarationNameInvalidCharsProvider) it.next()).getInvalidChars());
            }
            this.invalidChars = linkedHashSet;
        }

        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        public List<FirDeclarationNameInvalidCharsProvider> getComponents() {
            return this.components;
        }

        @Override // org.jetbrains.kotlin.fir.FirDeclarationNameInvalidCharsProvider
        public Set<Character> getInvalidChars() {
            return this.invalidChars;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\f\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirDeclarationNameInvalidCharsProvider$Simple;", "Lorg/jetbrains/kotlin/fir/FirDeclarationNameInvalidCharsProvider;", "invalidChars", Argument.Delimiters.none, Argument.Delimiters.none, "<init>", "(Ljava/util/Set;)V", "getInvalidChars", "()Ljava/util/Set;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Simple extends FirDeclarationNameInvalidCharsProvider {
        private final Set<Character> invalidChars;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Simple(Set<Character> set) {
            super(null);
            set.getClass();
            this.invalidChars = set;
        }

        @Override // org.jetbrains.kotlin.fir.FirDeclarationNameInvalidCharsProvider
        public Set<Character> getInvalidChars() {
            return this.invalidChars;
        }
    }

    public /* synthetic */ FirDeclarationNameInvalidCharsProvider(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public Composed createComposed(List<? extends FirDeclarationNameInvalidCharsProvider> components) {
        components.getClass();
        return new Composed(components);
    }

    public abstract Set<Character> getInvalidChars();

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\f\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirDeclarationNameInvalidCharsProvider$Companion;", Argument.Delimiters.none, "<init>", "()V", "of", "Lorg/jetbrains/kotlin/fir/FirDeclarationNameInvalidCharsProvider;", "invalidChars", Argument.Delimiters.none, Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirDeclarationNameInvalidCharsProvider of(Set<Character> invalidChars) {
            invalidChars.getClass();
            return new Simple(invalidChars);
        }

        private Companion() {
        }
    }

    private FirDeclarationNameInvalidCharsProvider() {
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public /* bridge */ /* synthetic */ FirComposableSessionComponent.Composed createComposed(List list) {
        return createComposed((List<? extends FirDeclarationNameInvalidCharsProvider>) list);
    }
}
