package org.bouncycastle.tsp.ers;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import org.bouncycastle.asn1.tsp.ArchiveTimeStamp;
import org.bouncycastle.asn1.tsp.PartialHashtree;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.StoreException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ERSArchiveTimeStamp {
    private final ArchiveTimeStamp archiveTimeStamp;
    private final DigestCalculator digCalc;
    private final byte[] previousChainsDigest;
    private ERSRootNodeCalculator rootNodeCalculator;
    private final TimeStampToken timeStampToken;

    public ERSArchiveTimeStamp(ArchiveTimeStamp archiveTimeStamp, DigestCalculatorProvider digestCalculatorProvider) throws ERSException, TSPException {
        this.rootNodeCalculator = new BinaryTreeRootCalculator();
        this.previousChainsDigest = null;
        try {
            this.archiveTimeStamp = archiveTimeStamp;
            this.timeStampToken = new TimeStampToken(archiveTimeStamp.getTimeStamp());
            this.digCalc = digestCalculatorProvider.get(archiveTimeStamp.getDigestAlgorithmIdentifier());
        } catch (OperatorCreationException e) {
            throw new ERSException(e.getMessage(), e);
        } catch (IOException e2) {
            throw new ERSException(e2.getMessage(), e2);
        }
    }

    public static ERSArchiveTimeStamp fromTimeStampToken(TimeStampToken timeStampToken, DigestCalculatorProvider digestCalculatorProvider) throws ERSException, TSPException {
        return new ERSArchiveTimeStamp(new ArchiveTimeStamp(timeStampToken.toCMSSignedData().toASN1Structure()), digestCalculatorProvider);
    }

    public void checkContainsHashValue(boolean z, byte[] bArr, DigestCalculator digestCalculator) throws ArchiveTimeStampValidationException {
        PartialHashtree[] reducedHashTree = this.archiveTimeStamp.getReducedHashTree();
        if (reducedHashTree == null) {
            if (!Arrays.areEqual(bArr, this.timeStampToken.getTimeStampInfo().getMessageImprintDigest())) {
                throw new ArchiveTimeStampValidationException("object hash not found in wrapped timestamp");
            }
            return;
        }
        PartialHashtree partialHashtree = reducedHashTree[0];
        if (z || !partialHashtree.containsHash(bArr)) {
            if (partialHashtree.getValueCount() <= 1 || !Arrays.areEqual(bArr, ERSUtil.calculateBranchHash(digestCalculator, partialHashtree.getValues()))) {
                throw new ArchiveTimeStampValidationException("object hash not found");
            }
        }
    }

    public void checkTimeStampValid(TimeStampToken timeStampToken, byte[] bArr) throws ArchiveTimeStampValidationException {
        if (bArr != null && !Arrays.areEqual(bArr, timeStampToken.getTimeStampInfo().getMessageImprintDigest())) {
            throw new ArchiveTimeStampValidationException("timestamp hash does not match root");
        }
    }

    public AlgorithmIdentifier getDigestAlgorithmIdentifier() {
        return this.archiveTimeStamp.getDigestAlgorithmIdentifier();
    }

    public byte[] getEncoded() throws IOException {
        return this.archiveTimeStamp.getEncoded();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.bouncycastle.util.StoreException */
    public Date getExpiryTime() throws StoreException {
        X509CertificateHolder signingCertificate = getSigningCertificate();
        if (signingCertificate != null) {
            return signingCertificate.getNotAfter();
        }
        return null;
    }

    public Date getGenTime() {
        return this.timeStampToken.getTimeStampInfo().getGenTime();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.bouncycastle.util.StoreException */
    public X509CertificateHolder getSigningCertificate() throws StoreException {
        Store certificates = this.timeStampToken.getCertificates();
        if (certificates == null) {
            return null;
        }
        Collection matches = certificates.getMatches(this.timeStampToken.getSID());
        if (matches.isEmpty()) {
            return null;
        }
        return (X509CertificateHolder) matches.iterator().next();
    }

    public TimeStampToken getTimeStampToken() {
        return this.timeStampToken;
    }

    public boolean isContaining(ERSData eRSData, Date date) throws ERSException {
        if (this.timeStampToken.getTimeStampInfo().getGenTime().after(date)) {
            throw new ArchiveTimeStampValidationException("timestamp generation time is in the future");
        }
        try {
            validatePresent(eRSData, date);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public ArchiveTimeStamp toASN1Structure() {
        return this.archiveTimeStamp;
    }

    public void validate(SignerInformationVerifier signerInformationVerifier) throws TSPException {
        this.timeStampToken.validate(signerInformationVerifier);
    }

    public void validatePresent(boolean z, byte[] bArr, Date date) throws ERSException {
        if (this.timeStampToken.getTimeStampInfo().getGenTime().after(date)) {
            throw new ArchiveTimeStampValidationException("timestamp generation time is in the future");
        }
        checkContainsHashValue(z, bArr, this.digCalc);
        if (this.archiveTimeStamp.getReducedHashTree() != null) {
            bArr = this.rootNodeCalculator.recoverRootHash(this.digCalc, this.archiveTimeStamp.getReducedHashTree());
        }
        checkTimeStampValid(this.timeStampToken, bArr);
    }

    public void validatePresent(ERSData eRSData, Date date) throws ERSException {
        validatePresent(eRSData instanceof ERSDataGroup, eRSData.getHash(this.digCalc, this.previousChainsDigest), date);
    }

    public ERSArchiveTimeStamp(ArchiveTimeStamp archiveTimeStamp, DigestCalculator digestCalculator) throws ERSException, TSPException {
        this.rootNodeCalculator = new BinaryTreeRootCalculator();
        this.previousChainsDigest = null;
        try {
            this.archiveTimeStamp = archiveTimeStamp;
            this.timeStampToken = new TimeStampToken(archiveTimeStamp.getTimeStamp());
            this.digCalc = digestCalculator;
        } catch (IOException e) {
            throw new ERSException(e.getMessage(), e);
        }
    }

    public ERSArchiveTimeStamp(byte[] bArr, ArchiveTimeStamp archiveTimeStamp, DigestCalculatorProvider digestCalculatorProvider) throws ERSException, TSPException {
        this.rootNodeCalculator = new BinaryTreeRootCalculator();
        this.previousChainsDigest = bArr;
        try {
            this.archiveTimeStamp = archiveTimeStamp;
            this.timeStampToken = new TimeStampToken(archiveTimeStamp.getTimeStamp());
            this.digCalc = digestCalculatorProvider.get(archiveTimeStamp.getDigestAlgorithmIdentifier());
        } catch (OperatorCreationException e) {
            throw new ERSException(e.getMessage(), e);
        } catch (IOException e2) {
            throw new ERSException(e2.getMessage(), e2);
        }
    }

    public ERSArchiveTimeStamp(byte[] bArr, DigestCalculatorProvider digestCalculatorProvider) throws ERSException, TSPException {
        this(ArchiveTimeStamp.getInstance(bArr), digestCalculatorProvider);
    }
}
