package org.jetbrains.kotlin.fir.java;

import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.load.java.structure.JavaTypeParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000 \t2\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001:\u0001\tB\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u0003H¦\u0002¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/structure/JavaTypeParameter;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "<init>", "()V", "get", "javaTypeParameter", "Companion", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class JavaTypeParameterStack implements Iterable<Map.Entry<? extends JavaTypeParameter, ? extends FirTypeParameterSymbol>>, KMappedMarker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final JavaTypeParameterStack EMPTY = new JavaTypeParameterStack() { // from class: org.jetbrains.kotlin.fir.java.JavaTypeParameterStack$Companion$EMPTY$1
        @Override // org.jetbrains.kotlin.fir.java.JavaTypeParameterStack
        public FirTypeParameterSymbol get(JavaTypeParameter javaTypeParameter) {
            javaTypeParameter.getClass();
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.java.JavaTypeParameterStack, java.lang.Iterable
        public Iterator<Map.Entry<? extends JavaTypeParameter, ? extends FirTypeParameterSymbol>> iterator() {
            return MapsKt.emptyMap().entrySet().iterator();
        }
    };

    public abstract FirTypeParameterSymbol get(JavaTypeParameter javaTypeParameter);

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<? extends JavaTypeParameter, ? extends FirTypeParameterSymbol>> iterator() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack$Companion;", Argument.Delimiters.none, "<init>", "()V", "EMPTY", "Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;", "getEMPTY", "()Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final JavaTypeParameterStack getEMPTY() {
            return JavaTypeParameterStack.EMPTY;
        }

        private Companion() {
        }
    }
}
