package org.jetbrains.kotlin.incremental;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.text.Charsets;
import org.jetbrains.kotlin.build.report.metrics.BuildAttribute;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0000\u001a\u001e\u0010\u0005\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH\u0002\u001a\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\nH\u0002*\f\b\u0000\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\u000b"}, d2 = {"RebuildReason", "Lorg/jetbrains/kotlin/build/report/metrics/BuildAttribute;", "computeHashedConfigurationInputs", "Lorg/jetbrains/kotlin/incremental/HashedConfigurationInputs;", "Lorg/jetbrains/kotlin/incremental/ConfigurationInputs;", "computeConfigurationInputsHash", "", "configurationInputsSnapshot", "", "", "", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class HashedConfigurationInputsKt {
    private static final byte[] computeConfigurationInputsHash(Map<String, String> map) throws NoSuchAlgorithmException {
        SortedMap sortedMap = MapsKt.toSortedMap(map);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        for (Map.Entry entry : sortedMap.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            str.getClass();
            Charset charset = Charsets.UTF_8;
            byte[] bytes = str.getBytes(charset);
            bytes.getClass();
            messageDigest.update(bytes);
            if (str2 == null) {
                str2 = "";
            }
            byte[] bytes2 = str2.getBytes(charset);
            bytes2.getClass();
            messageDigest.update(bytes2);
        }
        byte[] bArrDigest = messageDigest.digest();
        bArrDigest.getClass();
        return bArrDigest;
    }

    public static final HashedConfigurationInputs computeHashedConfigurationInputs(ConfigurationInputs configurationInputs) {
        configurationInputs.getClass();
        return new HashedConfigurationInputs(MapsKt.mapOf(new Pair[]{TuplesKt.to(BuildAttribute.INCREMENTAL_COMPILATION_CONFIGURATION_CHANGED, computeConfigurationInputsHash(configurationInputs.getIcConfigurationInputsSnapshot())), TuplesKt.to(BuildAttribute.COMPILER_ARGS_CHANGED, computeConfigurationInputsHash(configurationInputs.getCompilerArgumentsInputsSnapshot()))}));
    }

    private static final byte[] computeConfigurationInputsHash(List<String> list) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            byte[] bytes = ((String) it.next()).getBytes(Charsets.UTF_8);
            bytes.getClass();
            messageDigest.update(bytes);
        }
        byte[] bArrDigest = messageDigest.digest();
        bArrDigest.getClass();
        return bArrDigest;
    }
}
