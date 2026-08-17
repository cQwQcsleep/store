package org.jetbrains.kotlin.fir.session.environment;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\bf\u0018\u00002\u00020\u0001:\u0002\t\nJ\u0011\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H¦\u0002J\u0011\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H¦\u0002J\t\u0010\b\u001a\u00020\u0000H¦\u0002R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", Argument.Delimiters.none, "isEmpty", Argument.Delimiters.none, "()Z", "minus", "other", "plus", "not", "EMPTY", "ANY", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface AbstractProjectFileSearchScope {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0096\u0002J\u0011\u0010\t\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0096\u0002J\t\u0010\n\u001a\u00020\u0001H\u0096\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope$ANY;", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "<init>", "()V", "isEmpty", Argument.Delimiters.none, "()Z", "minus", "other", "plus", "not", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ANY implements AbstractProjectFileSearchScope {
        public static final ANY INSTANCE = new ANY();
        private static final boolean isEmpty = false;

        private ANY() {
        }

        @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope
        public boolean isEmpty() {
            return isEmpty;
        }

        @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope
        public AbstractProjectFileSearchScope minus(AbstractProjectFileSearchScope other) {
            other.getClass();
            throw new IllegalStateException("Operation not implemented");
        }

        @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope
        public AbstractProjectFileSearchScope not() {
            return EMPTY.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope
        public AbstractProjectFileSearchScope plus(AbstractProjectFileSearchScope other) {
            other.getClass();
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0096\u0002J\u0011\u0010\t\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0096\u0002J\t\u0010\n\u001a\u00020\u0001H\u0096\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope$EMPTY;", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "<init>", "()V", "isEmpty", Argument.Delimiters.none, "()Z", "minus", "other", "plus", "not", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class EMPTY implements AbstractProjectFileSearchScope {
        public static final EMPTY INSTANCE = new EMPTY();
        private static final boolean isEmpty = true;

        private EMPTY() {
        }

        @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope
        public boolean isEmpty() {
            return isEmpty;
        }

        @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope
        public AbstractProjectFileSearchScope minus(AbstractProjectFileSearchScope other) {
            other.getClass();
            return this;
        }

        @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope
        public AbstractProjectFileSearchScope not() {
            return ANY.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope
        public AbstractProjectFileSearchScope plus(AbstractProjectFileSearchScope other) {
            other.getClass();
            return other;
        }
    }

    boolean isEmpty();

    AbstractProjectFileSearchScope minus(AbstractProjectFileSearchScope other);

    AbstractProjectFileSearchScope not();

    AbstractProjectFileSearchScope plus(AbstractProjectFileSearchScope other);
}
