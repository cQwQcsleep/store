package org.jetbrains.kotlin.fir.serialization;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.constant.KClassValue;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/LocalClassIdOracle;", Argument.Delimiters.none, "<init>", "()V", "getLocalClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "klass", "Lorg/jetbrains/kotlin/constant/KClassValue$Value$LocalClass;", "Companion", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class LocalClassIdOracle {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final LocalClassIdOracle EMPTY = new LocalClassIdOracle() { // from class: org.jetbrains.kotlin.fir.serialization.LocalClassIdOracle$Companion$EMPTY$1
        @Override // org.jetbrains.kotlin.fir.serialization.LocalClassIdOracle
        public ClassId getLocalClassId(KClassValue.Value.LocalClass klass) {
            klass.getClass();
            return null;
        }
    };

    public abstract ClassId getLocalClassId(KClassValue.Value.LocalClass klass);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/LocalClassIdOracle$Companion;", Argument.Delimiters.none, "<init>", "()V", "EMPTY", "Lorg/jetbrains/kotlin/fir/serialization/LocalClassIdOracle;", "getEMPTY", "()Lorg/jetbrains/kotlin/fir/serialization/LocalClassIdOracle;", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LocalClassIdOracle getEMPTY() {
            return LocalClassIdOracle.EMPTY;
        }

        private Companion() {
        }
    }
}
