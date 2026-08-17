package org.bouncycastle.tsp;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.LocaleUtil;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.ess.ESSCertID;
import org.bouncycastle.asn1.ess.ESSCertIDv2;
import org.bouncycastle.asn1.ess.SigningCertificate;
import org.bouncycastle.asn1.ess.SigningCertificateV2;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.tsp.Accuracy;
import org.bouncycastle.asn1.tsp.MessageImprint;
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSAttributeTableGenerationException;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.SignerInfoGenerator;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.util.CollectionStore;
import org.bouncycastle.util.Store;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TimeStampTokenGenerator {
    public static final int R_HUNDREDTHS_OF_SECONDS = 2;
    public static final int R_MICROSECONDS = 2;
    public static final int R_MILLISECONDS = 3;
    public static final int R_SECONDS = 0;
    public static final int R_TENTHS_OF_SECONDS = 1;
    private int accuracyMicros;
    private int accuracyMillis;
    private int accuracySeconds;
    private List attrCerts;
    private List certs;
    private List crls;
    private Locale locale;
    boolean ordering;
    private Map otherRevoc;
    private int resolution;
    private SignerInfoGenerator signerInfoGen;
    GeneralName tsa;
    private ASN1ObjectIdentifier tsaPolicyOID;

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.bouncycastle.tsp.TSPException */
    public TimeStampTokenGenerator(final SignerInfoGenerator signerInfoGenerator, DigestCalculator digestCalculator, ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z) throws TSPException, IllegalArgumentException {
        SignerInfoGenerator signerInfoGenerator2;
        this.resolution = 0;
        this.locale = null;
        this.accuracySeconds = -1;
        this.accuracyMillis = -1;
        this.accuracyMicros = -1;
        this.ordering = false;
        this.tsa = null;
        this.certs = new ArrayList();
        this.crls = new ArrayList();
        this.attrCerts = new ArrayList();
        this.otherRevoc = new HashMap();
        this.signerInfoGen = signerInfoGenerator;
        this.tsaPolicyOID = aSN1ObjectIdentifier;
        if (!signerInfoGenerator.hasAssociatedCertificate()) {
            w01.a("SignerInfoGenerator must have an associated certificate");
            throw null;
        }
        X509CertificateHolder associatedCertificate = signerInfoGenerator.getAssociatedCertificate();
        TSPUtil.validateCertificate(associatedCertificate);
        try {
            OutputStream outputStream = digestCalculator.getOutputStream();
            outputStream.write(associatedCertificate.getEncoded());
            outputStream.close();
            if (digestCalculator.getAlgorithmIdentifier().getAlgorithm().equals(OIWObjectIdentifiers.idSHA1)) {
                final ESSCertID eSSCertID = new ESSCertID(digestCalculator.getDigest(), z ? new IssuerSerial(new GeneralNames(new GeneralName(associatedCertificate.getIssuer())), associatedCertificate.getSerialNumber()) : null);
                signerInfoGenerator2 = new SignerInfoGenerator(signerInfoGenerator, new CMSAttributeTableGenerator() { // from class: org.bouncycastle.tsp.TimeStampTokenGenerator.1
                    public AttributeTable getAttributes(Map map) throws CMSAttributeTableGenerationException {
                        AttributeTable attributes = signerInfoGenerator.getSignedAttributeTableGenerator().getAttributes(map);
                        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = PKCSObjectIdentifiers.id_aa_signingCertificate;
                        return attributes.get(aSN1ObjectIdentifier2) == null ? attributes.add(aSN1ObjectIdentifier2, new SigningCertificate(eSSCertID)) : attributes;
                    }
                }, signerInfoGenerator.getUnsignedAttributeTableGenerator());
            } else {
                final ESSCertIDv2 eSSCertIDv2 = new ESSCertIDv2(new AlgorithmIdentifier(digestCalculator.getAlgorithmIdentifier().getAlgorithm()), digestCalculator.getDigest(), z ? new IssuerSerial(new GeneralNames(new GeneralName(associatedCertificate.getIssuer())), new ASN1Integer(associatedCertificate.getSerialNumber())) : null);
                signerInfoGenerator2 = new SignerInfoGenerator(signerInfoGenerator, new CMSAttributeTableGenerator() { // from class: org.bouncycastle.tsp.TimeStampTokenGenerator.2
                    public AttributeTable getAttributes(Map map) throws CMSAttributeTableGenerationException {
                        AttributeTable attributes = signerInfoGenerator.getSignedAttributeTableGenerator().getAttributes(map);
                        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = PKCSObjectIdentifiers.id_aa_signingCertificateV2;
                        return attributes.get(aSN1ObjectIdentifier2) == null ? attributes.add(aSN1ObjectIdentifier2, new SigningCertificateV2(eSSCertIDv2)) : attributes;
                    }
                }, signerInfoGenerator.getUnsignedAttributeTableGenerator());
            }
            this.signerInfoGen = signerInfoGenerator2;
        } catch (IOException e) {
            throw new TSPException("Exception processing certificate.", e);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.bouncycastle.tsp.TSPException */
    /* JADX WARN: Code duplicated, block: B:21:0x006a A[PHI: r0
      0x006a: PHI (r0v5 int) = (r0v4 int), (r0v7 int) binds: [B:23:0x0078, B:20:0x0068] A[DONT_GENERATE, DONT_INLINE]] */
    private ASN1GeneralizedTime createGeneralizedTime(Date date) throws TSPException {
        int i;
        SimpleDateFormat simpleDateFormat = this.locale == null ? new SimpleDateFormat("yyyyMMddHHmmss.SSS", LocaleUtil.EN_Locale) : new SimpleDateFormat("yyyyMMddHHmmss.SSS", this.locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, Constants.HASIDCALL_INDEX_SIG));
        StringBuilder sb = new StringBuilder(simpleDateFormat.format(date));
        int iIndexOf = sb.indexOf(com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_THIS);
        if (iIndexOf < 0) {
            sb.append(Constants.HASIDCALL_INDEX_SIG);
            return new ASN1GeneralizedTime(sb.toString());
        }
        int i2 = this.resolution;
        if (i2 == 1) {
            i = iIndexOf + 2;
            if (sb.length() > i) {
                sb.delete(i, sb.length());
            }
        } else if (i2 == 2) {
            i = iIndexOf + 3;
            if (sb.length() > i) {
                sb.delete(i, sb.length());
            }
        } else if (i2 != 3) {
            throw new TSPException("unknown time-stamp resolution: " + this.resolution);
        }
        while (sb.charAt(sb.length() - 1) == '0') {
            sb.deleteCharAt(sb.length() - 1);
        }
        if (sb.length() - 1 == iIndexOf) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(Constants.HASIDCALL_INDEX_SIG);
        return new ASN1GeneralizedTime(sb.toString());
    }

    public void addAttributeCertificates(Store store) {
        this.attrCerts.addAll(store.getMatches(null));
    }

    public void addCRLs(Store store) {
        this.crls.addAll(store.getMatches(null));
    }

    public void addCertificates(Store store) {
        this.certs.addAll(store.getMatches(null));
    }

    public void addOtherRevocationInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier, Store store) {
        this.otherRevoc.put(aSN1ObjectIdentifier, store.getMatches(null));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.bouncycastle.tsp.TSPException */
    public TimeStampToken generate(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date, Extensions extensions) throws TSPException {
        MessageImprint messageImprint = new MessageImprint(timeStampRequest.getMessageImprintAlgID(), timeStampRequest.getMessageImprintDigest());
        int i = this.accuracySeconds;
        Accuracy accuracy = (i > 0 || this.accuracyMillis > 0 || this.accuracyMicros > 0) ? new Accuracy(i > 0 ? new ASN1Integer(this.accuracySeconds) : null, this.accuracyMillis > 0 ? new ASN1Integer(this.accuracyMillis) : null, this.accuracyMicros > 0 ? new ASN1Integer(this.accuracyMicros) : null) : null;
        boolean z = this.ordering;
        ASN1Boolean aSN1Boolean = z ? ASN1Boolean.getInstance(z) : null;
        ASN1Integer aSN1Integer = timeStampRequest.getNonce() != null ? new ASN1Integer(timeStampRequest.getNonce()) : null;
        ASN1ObjectIdentifier reqPolicy = this.tsaPolicyOID;
        if (timeStampRequest.getReqPolicy() != null) {
            reqPolicy = timeStampRequest.getReqPolicy();
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier = reqPolicy;
        Extensions extensions2 = timeStampRequest.getExtensions();
        if (extensions != null) {
            ExtensionsGenerator extensionsGenerator = new ExtensionsGenerator();
            if (extensions2 != null) {
                Enumeration enumerationOids = extensions2.oids();
                while (enumerationOids.hasMoreElements()) {
                    extensionsGenerator.addExtension(extensions2.getExtension(ASN1ObjectIdentifier.getInstance(enumerationOids.nextElement())));
                }
            }
            Enumeration enumerationOids2 = extensions.oids();
            while (enumerationOids2.hasMoreElements()) {
                extensionsGenerator.addExtension(extensions.getExtension(ASN1ObjectIdentifier.getInstance(enumerationOids2.nextElement())));
            }
            extensions2 = extensionsGenerator.generate();
        }
        Extensions extensions3 = extensions2;
        TSTInfo tSTInfo = new TSTInfo(aSN1ObjectIdentifier, messageImprint, new ASN1Integer(bigInteger), this.resolution == 0 ? this.locale == null ? new ASN1GeneralizedTime(date) : new ASN1GeneralizedTime(date, this.locale) : createGeneralizedTime(date), accuracy, aSN1Boolean, aSN1Integer, this.tsa, extensions3);
        try {
            CMSSignedDataGenerator cMSSignedDataGenerator = new CMSSignedDataGenerator();
            if (timeStampRequest.getCertReq()) {
                cMSSignedDataGenerator.addCertificates(new CollectionStore(this.certs));
                cMSSignedDataGenerator.addAttributeCertificates(new CollectionStore(this.attrCerts));
            }
            cMSSignedDataGenerator.addCRLs(new CollectionStore(this.crls));
            if (!this.otherRevoc.isEmpty()) {
                for (ASN1ObjectIdentifier aSN1ObjectIdentifier2 : this.otherRevoc.keySet()) {
                    cMSSignedDataGenerator.addOtherRevocationInfo(aSN1ObjectIdentifier2, new CollectionStore((Collection) this.otherRevoc.get(aSN1ObjectIdentifier2)));
                }
            }
            cMSSignedDataGenerator.addSignerInfoGenerator(this.signerInfoGen);
            return new TimeStampToken(cMSSignedDataGenerator.generate(new CMSProcessableByteArray(PKCSObjectIdentifiers.id_ct_TSTInfo, tSTInfo.getEncoded("DER")), true));
        } catch (IOException e) {
            throw new TSPException("Exception encoding info", e);
        } catch (CMSException e2) {
            throw new TSPException("Error generating time-stamp token", e2);
        }
    }

    public void setAccuracyMicros(int i) {
        this.accuracyMicros = i;
    }

    public void setAccuracyMillis(int i) {
        this.accuracyMillis = i;
    }

    public void setAccuracySeconds(int i) {
        this.accuracySeconds = i;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setOrdering(boolean z) {
        this.ordering = z;
    }

    public void setResolution(int i) {
        this.resolution = i;
    }

    public void setTSA(GeneralName generalName) {
        this.tsa = generalName;
    }

    public TimeStampTokenGenerator(SignerInfoGenerator signerInfoGenerator, DigestCalculator digestCalculator, ASN1ObjectIdentifier aSN1ObjectIdentifier) throws TSPException, IllegalArgumentException {
        this(signerInfoGenerator, digestCalculator, aSN1ObjectIdentifier, false);
    }

    public TimeStampToken generate(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date) throws TSPException {
        return generate(timeStampRequest, bigInteger, date, null);
    }
}
