package org.jetbrains.kotlin.utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.transform.OutputKeys;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.IntRange;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0013\u0012\n\u0010\u0002\u001a\u00020\u0003\"\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0006\u0010\t\u001a\u00020\u0004¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/utils/JsMetadataVersion;", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "numbers", "", "", "<init>", "([I)V", "isCompatibleWithCurrentCompilerVersion", "", "toInteger", "Companion", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JsMetadataVersion extends BinaryVersion {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final JsMetadataVersion INSTANCE = new JsMetadataVersion(1, 2, 6);
    public static final JsMetadataVersion INVALID_VERSION = new JsMetadataVersion(new int[0]);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsMetadataVersion(int... iArr) {
        super(Arrays.copyOf(iArr, iArr.length));
        iArr.getClass();
    }

    @Override // org.jetbrains.kotlin.metadata.deserialization.BinaryVersion
    public boolean isCompatibleWithCurrentCompilerVersion() {
        return isCompatibleTo(INSTANCE);
    }

    public final int toInteger() {
        return (getPatch() << 16) + (Math.min(getMinor(), 255) << 8) + Math.min(getMajor(), 255);
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fR\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/utils/JsMetadataVersion$Companion;", "", "<init>", "()V", "INSTANCE", "Lorg/jetbrains/kotlin/utils/JsMetadataVersion;", "INVALID_VERSION", "fromInteger", OutputKeys.VERSION, "", "readFrom", "stream", "Ljava/io/InputStream;", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final JsMetadataVersion fromInteger(int version) {
            return new JsMetadataVersion(version & 255, (version >> 8) & 255, version >> 16);
        }

        public final JsMetadataVersion readFrom(InputStream stream) throws IOException {
            stream.getClass();
            DataInputStream dataInputStream = new DataInputStream(stream);
            int i = dataInputStream.readInt();
            if (i != JsMetadataVersion.INSTANCE.getNumbers().length) {
                return JsMetadataVersion.INVALID_VERSION;
            }
            IntRange intRange = new IntRange(1, i);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
            IntIterator it = intRange.iterator();
            while (it.hasNext()) {
                it.nextInt();
                arrayList.add(Integer.valueOf(dataInputStream.readInt()));
            }
            int[] intArray = CollectionsKt.toIntArray(arrayList);
            return new JsMetadataVersion(Arrays.copyOf(intArray, intArray.length));
        }

        private Companion() {
        }
    }
}
