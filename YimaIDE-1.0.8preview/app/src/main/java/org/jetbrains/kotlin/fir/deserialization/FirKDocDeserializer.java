package org.jetbrains.kotlin.fir.deserialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.metadata.ProtoBuf;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\fJ\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\tH&J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u000bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/FirKDocDeserializer;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "loadPropertyKDoc", Argument.Delimiters.none, "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "loadFunctionKDoc", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;", "loadConstructorKDoc", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor;", "loadClassKDoc", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "Empty", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface FirKDocDeserializer extends FirSessionComponent {

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/FirKDocDeserializer$Empty;", "Lorg/jetbrains/kotlin/fir/deserialization/FirKDocDeserializer;", "<init>", "()V", "loadPropertyKDoc", Argument.Delimiters.none, "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "loadFunctionKDoc", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;", "loadConstructorKDoc", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor;", "loadClassKDoc", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Empty implements FirKDocDeserializer {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override // org.jetbrains.kotlin.fir.deserialization.FirKDocDeserializer
        public String loadClassKDoc(ProtoBuf.Class proto) {
            proto.getClass();
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.deserialization.FirKDocDeserializer
        public String loadConstructorKDoc(ProtoBuf.Constructor proto) {
            proto.getClass();
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.deserialization.FirKDocDeserializer
        public String loadFunctionKDoc(ProtoBuf.Function proto) {
            proto.getClass();
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.deserialization.FirKDocDeserializer
        public String loadPropertyKDoc(ProtoBuf.Property proto) {
            proto.getClass();
            return null;
        }
    }

    String loadClassKDoc(ProtoBuf.Class proto);

    String loadConstructorKDoc(ProtoBuf.Constructor proto);

    String loadFunctionKDoc(ProtoBuf.Function proto);

    String loadPropertyKDoc(ProtoBuf.Property proto);
}
