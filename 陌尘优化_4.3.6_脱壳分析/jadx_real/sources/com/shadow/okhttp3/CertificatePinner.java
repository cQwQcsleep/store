package com.shadow.okhttp3;

import com.shadow.kotlin.collections.ArraysKt;
import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.collections.EmptyList;
import com.shadow.kotlin.collections.EmptySet;
import com.shadow.kotlin.collections.MapsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import com.shadow.kotlin.ranges.RangesKt;
import com.shadow.kotlin.text.StringsKt;
import com.shadow.kotlin.text.StringsKt__StringsJVMKt;
import com.shadow.okhttp3.internal.HostnamesKt;
import com.shadow.okhttp3.internal.tls.CertificateChainCleaner;
import com.shadow.okio.ByteString;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.jvm.functions.Function0;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class CertificatePinner {
    public static final Companion Companion = new Companion(null);
    public static final CertificatePinner DEFAULT = new Builder().build();
    private final CertificateChainCleaner certificateChainCleaner;
    private final Set<Pin> pins;

    public static final class Builder {
        private final List<Pin> pins = new ArrayList();

        public final Builder add(String str, String... strArr) {
            CloseableKt.checkNotNullParameter(str, "pattern");
            CloseableKt.checkNotNullParameter(strArr, "pins");
            for (String str2 : strArr) {
                this.pins.add(new Pin(str, str2));
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object, java.util.Set] */
        /* JADX WARN: Type inference failed for: r2v6, types: [java.util.AbstractCollection, java.util.LinkedHashSet] */
        public final CertificatePinner build() {
            EmptySet emptySet;
            List<Pin> list = this.pins;
            CloseableKt.checkNotNullParameter(list, "<this>");
            int size = list.size();
            if (size == 0) {
                emptySet = EmptySet.INSTANCE;
            } else if (size != 1) {
                ?? linkedHashSet = new LinkedHashSet(MapsKt.b(list.size()));
                CollectionsKt.toCollection(list, linkedHashSet);
                emptySet = linkedHashSet;
            } else {
                ?? Singleton = Collections.singleton(list.get(0));
                CloseableKt.checkNotNullExpressionValue(Singleton, "singleton(...)");
                emptySet = Singleton;
            }
            return new CertificatePinner(emptySet, null, 2, false ? 1 : 0);
        }

        public final List<Pin> getPins() {
            return this.pins;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String pin(Certificate certificate) {
            CloseableKt.checkNotNullParameter(certificate, "certificate");
            if (!(certificate instanceof X509Certificate)) {
                throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
            }
            return "sha256/" + sha256Hash((X509Certificate) certificate).base64();
        }

        public final ByteString sha1Hash(X509Certificate x509Certificate) {
            CloseableKt.checkNotNullParameter(x509Certificate, "<this>");
            ByteString.Companion companion = ByteString.Companion;
            byte[] encoded = x509Certificate.getPublicKey().getEncoded();
            CloseableKt.checkNotNullExpressionValue(encoded, "publicKey.encoded");
            return ByteString.Companion.of$default(companion, encoded, 0, 0, 3, null).sha1();
        }

        public final ByteString sha256Hash(X509Certificate x509Certificate) {
            CloseableKt.checkNotNullParameter(x509Certificate, "<this>");
            ByteString.Companion companion = ByteString.Companion;
            byte[] encoded = x509Certificate.getPublicKey().getEncoded();
            CloseableKt.checkNotNullExpressionValue(encoded, "publicKey.encoded");
            return ByteString.Companion.of$default(companion, encoded, 0, 0, 3, null).sha256();
        }

        private Companion() {
        }
    }

    public static final class Pin {
        private final ByteString hash;
        private final String hashAlgorithm;
        private final String pattern;

        public Pin(String str, String str2) {
            CloseableKt.checkNotNullParameter(str, "pattern");
            CloseableKt.checkNotNullParameter(str2, "pin");
            if ((!StringsKt.r(str, "*.", false) || StringsKt.i(str, "*", 1, false, 4) != -1) && ((!StringsKt.r(str, "**.", false) || StringsKt.i(str, "*", 2, false, 4) != -1) && StringsKt.i(str, "*", 0, false, 6) != -1)) {
                throw new IllegalArgumentException("Unexpected pattern: ".concat(str).toString());
            }
            String canonicalHost = HostnamesKt.toCanonicalHost(str);
            if (canonicalHost == null) {
                throw new IllegalArgumentException("Invalid pattern: ".concat(str));
            }
            this.pattern = canonicalHost;
            if (StringsKt.r(str2, "sha1/", false)) {
                this.hashAlgorithm = "sha1";
                ByteString.Companion companion = ByteString.Companion;
                String strSubstring = str2.substring(5);
                CloseableKt.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                ByteString byteStringDecodeBase64 = companion.decodeBase64(strSubstring);
                if (byteStringDecodeBase64 == null) {
                    throw new IllegalArgumentException("Invalid pin hash: ".concat(str2));
                }
                this.hash = byteStringDecodeBase64;
                return;
            }
            if (!StringsKt.r(str2, "sha256/", false)) {
                throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': ".concat(str2));
            }
            this.hashAlgorithm = "sha256";
            ByteString.Companion companion2 = ByteString.Companion;
            String strSubstring2 = str2.substring(7);
            CloseableKt.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String).substring(startIndex)");
            ByteString byteStringDecodeBase642 = companion2.decodeBase64(strSubstring2);
            if (byteStringDecodeBase642 == null) {
                throw new IllegalArgumentException("Invalid pin hash: ".concat(str2));
            }
            this.hash = byteStringDecodeBase642;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Pin)) {
                return false;
            }
            Pin pin = (Pin) obj;
            return CloseableKt.areEqual(this.pattern, pin.pattern) && CloseableKt.areEqual(this.hashAlgorithm, pin.hashAlgorithm) && CloseableKt.areEqual(this.hash, pin.hash);
        }

        public final ByteString getHash() {
            return this.hash;
        }

        public final String getHashAlgorithm() {
            return this.hashAlgorithm;
        }

        public final String getPattern() {
            return this.pattern;
        }

        public int hashCode() {
            return this.hash.hashCode() + ((this.hashAlgorithm.hashCode() + (this.pattern.hashCode() * 31)) * 31);
        }

        public final boolean matchesCertificate(X509Certificate x509Certificate) {
            CloseableKt.checkNotNullParameter(x509Certificate, "certificate");
            String str = this.hashAlgorithm;
            if (CloseableKt.areEqual(str, "sha256")) {
                return CloseableKt.areEqual(this.hash, CertificatePinner.Companion.sha256Hash(x509Certificate));
            }
            if (CloseableKt.areEqual(str, "sha1")) {
                return CloseableKt.areEqual(this.hash, CertificatePinner.Companion.sha1Hash(x509Certificate));
            }
            return false;
        }

        public final boolean matchesHostname(String str) {
            CloseableKt.checkNotNullParameter(str, "hostname");
            if (StringsKt.r(this.pattern, "**.", false)) {
                int length = this.pattern.length() - 3;
                int length2 = str.length() - length;
                if (!StringsKt__StringsJVMKt.regionMatches(str, str.length() - length, this.pattern, 3, length, false)) {
                    return false;
                }
                if (length2 != 0 && str.charAt(length2 - 1) != '.') {
                    return false;
                }
            } else {
                if (!StringsKt.r(this.pattern, "*.", false)) {
                    return str.equals(this.pattern);
                }
                int length3 = this.pattern.length() - 1;
                int length4 = str.length() - length3;
                if (!StringsKt__StringsJVMKt.regionMatches(str, str.length() - length3, this.pattern, 1, length3, false) || StringsKt.j(str, '.', length4 - 1, 4) != -1) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return this.hashAlgorithm + '/' + this.hash.base64();
        }
    }

    public CertificatePinner(Set<Pin> set, CertificateChainCleaner certificateChainCleaner) {
        CloseableKt.checkNotNullParameter(set, "pins");
        this.pins = set;
        this.certificateChainCleaner = certificateChainCleaner;
    }

    public static final String pin(Certificate certificate) {
        return Companion.pin(certificate);
    }

    public static final ByteString sha1Hash(X509Certificate x509Certificate) {
        return Companion.sha1Hash(x509Certificate);
    }

    public static final ByteString sha256Hash(X509Certificate x509Certificate) {
        return Companion.sha256Hash(x509Certificate);
    }

    public final void check(final String str, final List<? extends Certificate> list) throws SSLPeerUnverifiedException {
        CloseableKt.checkNotNullParameter(str, "hostname");
        CloseableKt.checkNotNullParameter(list, "peerCertificates");
        check$okhttp(str, new Function0<List<? extends X509Certificate>>() { // from class: com.shadow.okhttp3.CertificatePinner.check.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            public final List<X509Certificate> invoke() {
                List<Certificate> listClean;
                CertificateChainCleaner certificateChainCleaner$okhttp = CertificatePinner.this.getCertificateChainCleaner$okhttp();
                if (certificateChainCleaner$okhttp == null || (listClean = certificateChainCleaner$okhttp.clean(list, str)) == null) {
                    listClean = list;
                }
                ArrayList arrayList = new ArrayList(CollectionsKt.b(listClean));
                for (Certificate certificate : listClean) {
                    CloseableKt.checkNotNull(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                    arrayList.add((X509Certificate) certificate);
                }
                return arrayList;
            }
        });
    }

    public final void check$okhttp(String str, Function0<? extends List<? extends X509Certificate>> function0) throws SSLPeerUnverifiedException {
        CloseableKt.checkNotNullParameter(str, "hostname");
        CloseableKt.checkNotNullParameter(function0, "cleanedPeerCertificatesFn");
        List<Pin> listFindMatchingPins = findMatchingPins(str);
        if (listFindMatchingPins.isEmpty()) {
            return;
        }
        List<X509Certificate> list = (List) function0.invoke();
        for (X509Certificate x509Certificate : list) {
            ByteString byteStringSha256Hash = null;
            ByteString byteStringSha1Hash = null;
            for (Pin pin : listFindMatchingPins) {
                String hashAlgorithm = pin.getHashAlgorithm();
                if (CloseableKt.areEqual(hashAlgorithm, "sha256")) {
                    if (byteStringSha256Hash == null) {
                        byteStringSha256Hash = Companion.sha256Hash(x509Certificate);
                    }
                    if (CloseableKt.areEqual(pin.getHash(), byteStringSha256Hash)) {
                        return;
                    }
                } else {
                    if (!CloseableKt.areEqual(hashAlgorithm, "sha1")) {
                        throw new AssertionError("unsupported hashAlgorithm: " + pin.getHashAlgorithm());
                    }
                    if (byteStringSha1Hash == null) {
                        byteStringSha1Hash = Companion.sha1Hash(x509Certificate);
                    }
                    if (CloseableKt.areEqual(pin.getHash(), byteStringSha1Hash)) {
                        return;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder("Certificate pinning failure!\n  Peer certificate chain:");
        for (X509Certificate x509Certificate2 : list) {
            sb.append("\n    ");
            sb.append(Companion.pin(x509Certificate2));
            sb.append(": ");
            sb.append(x509Certificate2.getSubjectDN().getName());
        }
        sb.append("\n  Pinned certificates for ");
        sb.append(str);
        sb.append(":");
        for (Pin pin2 : listFindMatchingPins) {
            sb.append("\n    ");
            sb.append(pin2);
        }
        String string = sb.toString();
        CloseableKt.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        throw new SSLPeerUnverifiedException(string);
    }

    public boolean equals(Object obj) {
        if (obj instanceof CertificatePinner) {
            CertificatePinner certificatePinner = (CertificatePinner) obj;
            if (CloseableKt.areEqual(certificatePinner.pins, this.pins) && CloseableKt.areEqual(certificatePinner.certificateChainCleaner, this.certificateChainCleaner)) {
                return true;
            }
        }
        return false;
    }

    public final List<Pin> findMatchingPins(String str) {
        CloseableKt.checkNotNullParameter(str, "hostname");
        Set<Pin> set = this.pins;
        List arrayList = EmptyList.INSTANCE;
        for (Object obj : set) {
            if (((Pin) obj).matchesHostname(str)) {
                if (arrayList.isEmpty()) {
                    arrayList = new ArrayList();
                }
                RangesKt.asMutableList(arrayList).add(obj);
            }
        }
        return arrayList;
    }

    public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
        return this.certificateChainCleaner;
    }

    public final Set<Pin> getPins() {
        return this.pins;
    }

    public int hashCode() {
        int iHashCode = (this.pins.hashCode() + 1517) * 41;
        CertificateChainCleaner certificateChainCleaner = this.certificateChainCleaner;
        return iHashCode + (certificateChainCleaner != null ? certificateChainCleaner.hashCode() : 0);
    }

    public final CertificatePinner withCertificateChainCleaner$okhttp(CertificateChainCleaner certificateChainCleaner) {
        CloseableKt.checkNotNullParameter(certificateChainCleaner, "certificateChainCleaner");
        return CloseableKt.areEqual(this.certificateChainCleaner, certificateChainCleaner) ? this : new CertificatePinner(this.pins, certificateChainCleaner);
    }

    public final void check(String str, Certificate... certificateArr) throws SSLPeerUnverifiedException {
        CloseableKt.checkNotNullParameter(str, "hostname");
        CloseableKt.checkNotNullParameter(certificateArr, "peerCertificates");
        int length = certificateArr.length;
        check(str, length != 0 ? length != 1 ? ArraysKt.g(certificateArr) : CollectionsKt.d(certificateArr[0]) : EmptyList.INSTANCE);
    }

    public /* synthetic */ CertificatePinner(Set set, CertificateChainCleaner certificateChainCleaner, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(set, (i & 2) != 0 ? null : certificateChainCleaner);
    }
}
