package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.StringArray;
import com.sun.jna.Structure;
import com.sun.jna.Union;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APITypeMapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface WinCrypt {
    public static final int CERT_CHAIN_POLICY_BASE = 1;
    public static final int CERT_CLOSE_STORE_CHECK_FLAG = 2;
    public static final int CERT_CLOSE_STORE_FORCE_FLAG = 1;
    public static final int CERT_COMPARE_NAME_STR_W = 8;
    public static final int CERT_COMPARE_SHIFT = 16;
    public static final int CERT_FIND_SUBJECT_STR = 524295;
    public static final int CERT_FIND_SUBJECT_STR_W = 524295;
    public static final int CERT_INFO_SUBJECT_FLAG = 7;
    public static final int CERT_OID_NAME_STR = 2;
    public static final int CERT_QUERY_CONTENT_CERT = 1;
    public static final int CERT_QUERY_CONTENT_CERT_PAIR = 13;
    public static final int CERT_QUERY_CONTENT_CRL = 3;
    public static final int CERT_QUERY_CONTENT_CTL = 2;
    public static final int CERT_QUERY_CONTENT_FLAG_ALL = 16382;
    public static final int CERT_QUERY_CONTENT_FLAG_CERT = 2;
    public static final int CERT_QUERY_CONTENT_FLAG_CERT_PAIR = 8192;
    public static final int CERT_QUERY_CONTENT_FLAG_CRL = 8;
    public static final int CERT_QUERY_CONTENT_FLAG_CTL = 4;
    public static final int CERT_QUERY_CONTENT_FLAG_PFX = 4096;
    public static final int CERT_QUERY_CONTENT_FLAG_PFX_AND_LOAD = 16384;
    public static final int CERT_QUERY_CONTENT_FLAG_PKCS10 = 2048;
    public static final int CERT_QUERY_CONTENT_FLAG_PKCS7_SIGNED = 256;
    public static final int CERT_QUERY_CONTENT_FLAG_PKCS7_SIGNED_EMBED = 1024;
    public static final int CERT_QUERY_CONTENT_FLAG_PKCS7_UNSIGNED = 512;
    public static final int CERT_QUERY_CONTENT_FLAG_SERIALIZED_CERT = 32;
    public static final int CERT_QUERY_CONTENT_FLAG_SERIALIZED_CRL = 128;
    public static final int CERT_QUERY_CONTENT_FLAG_SERIALIZED_CTL = 64;
    public static final int CERT_QUERY_CONTENT_FLAG_SERIALIZED_STORE = 16;
    public static final int CERT_QUERY_CONTENT_PFX = 12;
    public static final int CERT_QUERY_CONTENT_PFX_AND_LOAD = 14;
    public static final int CERT_QUERY_CONTENT_PKCS10 = 11;
    public static final int CERT_QUERY_CONTENT_PKCS7_SIGNED = 8;
    public static final int CERT_QUERY_CONTENT_PKCS7_SIGNED_EMBED = 10;
    public static final int CERT_QUERY_CONTENT_PKCS7_UNSIGNED = 9;
    public static final int CERT_QUERY_CONTENT_SERIALIZED_CERT = 5;
    public static final int CERT_QUERY_CONTENT_SERIALIZED_CRL = 7;
    public static final int CERT_QUERY_CONTENT_SERIALIZED_CTL = 6;
    public static final int CERT_QUERY_CONTENT_SERIALIZED_STORE = 4;
    public static final int CERT_QUERY_FORMAT_ASN_ASCII_HEX_ENCODED = 3;
    public static final int CERT_QUERY_FORMAT_BASE64_ENCODED = 2;
    public static final int CERT_QUERY_FORMAT_BINARY = 1;
    public static final int CERT_QUERY_FORMAT_FLAG_ALL = 14;
    public static final int CERT_QUERY_FORMAT_FLAG_ASN_ASCII_HEX_ENCODED = 8;
    public static final int CERT_QUERY_FORMAT_FLAG_BASE64_ENCODED = 4;
    public static final int CERT_QUERY_FORMAT_FLAG_BINARY = 2;
    public static final int CERT_QUERY_OBJECT_BLOB = 2;
    public static final int CERT_QUERY_OBJECT_FILE = 1;
    public static final int CERT_SIMPLE_NAME_STR = 1;
    public static final int CERT_X500_NAME_STR = 3;
    public static final int CERT_XML_NAME_STR = 4;
    public static final int CRYPTPROTECT_AUDIT = 16;
    public static final int CRYPTPROTECT_CRED_REGENERATE = 128;
    public static final int CRYPTPROTECT_CRED_SYNC = 8;
    public static final int CRYPTPROTECT_LOCAL_MACHINE = 4;
    public static final int CRYPTPROTECT_NO_RECOVERY = 32;
    public static final int CRYPTPROTECT_PROMPT_ON_PROTECT = 2;
    public static final int CRYPTPROTECT_PROMPT_ON_UNPROTECT = 1;
    public static final int CRYPTPROTECT_PROMPT_REQUIRE_STRONG = 16;
    public static final int CRYPTPROTECT_PROMPT_RESERVED = 4;
    public static final int CRYPTPROTECT_PROMPT_STRONG = 8;
    public static final int CRYPTPROTECT_UI_FORBIDDEN = 1;
    public static final int CRYPTPROTECT_VERIFY_PROTECTION = 64;
    public static final int CRYPT_ASN_ENCODING = 1;
    public static final int CRYPT_EXPORTABLE = 1;
    public static final int CRYPT_E_ASN1_BADARGS = -2146881271;
    public static final int CRYPT_E_ASN1_BADPDU = -2146881272;
    public static final int CRYPT_E_ASN1_BADREAL = -2146881270;
    public static final int CRYPT_E_ASN1_BADTAG = -2146881269;
    public static final int CRYPT_E_ASN1_CHOICE = -2146881268;
    public static final int CRYPT_E_ASN1_CONSTRAINT = -2146881275;
    public static final int CRYPT_E_ASN1_CORRUPT = -2146881277;
    public static final int CRYPT_E_ASN1_EOD = -2146881278;
    public static final int CRYPT_E_ASN1_ERROR = -2146881280;
    public static final int CRYPT_E_ASN1_EXTENDED = -2146881023;
    public static final int CRYPT_E_ASN1_INTERNAL = -2146881279;
    public static final int CRYPT_E_ASN1_LARGE = -2146881276;
    public static final int CRYPT_E_ASN1_MEMORY = -2146881274;
    public static final int CRYPT_E_ASN1_NOEOD = -2146881022;
    public static final int CRYPT_E_ASN1_NYI = -2146881228;
    public static final int CRYPT_E_ASN1_OVERFLOW = -2146881273;
    public static final int CRYPT_E_ASN1_PDU_TYPE = -2146881229;
    public static final int CRYPT_E_ASN1_RULE = -2146881267;
    public static final int CRYPT_E_ASN1_UTF8 = -2146881266;
    public static final int CRYPT_MACHINE_KEYSET = 32;
    public static final int CRYPT_NDR_ENCODING = 2;
    public static final int CRYPT_USER_KEYSET = 4096;
    public static final int CRYPT_USER_PROTECTED = 2;
    public static final HCERTCHAINENGINE HCCE_CURRENT_USER = new HCERTCHAINENGINE(Pointer.createConstant(0));
    public static final HCERTCHAINENGINE HCCE_LOCAL_MACHINE = new HCERTCHAINENGINE(Pointer.createConstant(1));
    public static final HCERTCHAINENGINE HCCE_SERIAL_LOCAL_MACHINE = new HCERTCHAINENGINE(Pointer.createConstant(2));
    public static final int PKCS12_ALLOW_OVERWRITE_KEY = 16384;
    public static final int PKCS12_ALWAYS_CNG_KSP = 512;
    public static final int PKCS12_INCLUDE_EXTENDED_PROPERTIES = 16;
    public static final int PKCS12_NO_PERSIST_KEY = 32768;
    public static final int PKCS12_PREFER_CNG_KSP = 256;
    public static final int PKCS_7_ASN_ENCODING = 65536;
    public static final int PKCS_7_NDR_ENCODING = 131072;
    public static final int PP_CLIENT_HWND = 1;
    public static final int USAGE_MATCH_TYPE_AND = 0;
    public static final int USAGE_MATCH_TYPE_OR = 1;
    public static final int X509_ASN_ENCODING = 1;
    public static final int X509_NDR_ENCODING = 2;
    public static final String szOID_RSA_SHA1RSA = "1.2.840.113549.1.1.5";

    @Structure.FieldOrder({"cbSize", "TrustStatus", "cChain", "rgpChain", "cLowerQualityChainContext", "rgpLowerQualityChainContext", "fHasRevocationFreshnessTime", "dwRevocationFreshnessTime", "dwCreateFlags", "ChainId"})
    public static class CERT_CHAIN_CONTEXT extends Structure {
        public Guid.GUID ChainId;
        public CERT_TRUST_STATUS TrustStatus;
        public int cChain;
        public int cLowerQualityChainContext;
        public int cbSize;
        public int dwCreateFlags;
        public int dwRevocationFreshnessTime;
        public boolean fHasRevocationFreshnessTime;
        public Pointer rgpChain;
        public Pointer rgpLowerQualityChainContext;

        public static class ByReference extends CERT_CHAIN_CONTEXT implements Structure.ByReference {
        }

        public CERT_CHAIN_CONTEXT() {
            super(W32APITypeMapper.DEFAULT);
        }

        public CERT_SIMPLE_CHAIN[] getRgpChain() {
            int i = this.cChain;
            CERT_SIMPLE_CHAIN[] cert_simple_chainArr = new CERT_SIMPLE_CHAIN[i];
            for (int i2 = 0; i2 < i; i2++) {
                CERT_SIMPLE_CHAIN cert_simple_chain = (CERT_SIMPLE_CHAIN) Structure.newInstance(CERT_SIMPLE_CHAIN.class, this.rgpChain.getPointer(Native.POINTER_SIZE * i2));
                cert_simple_chainArr[i2] = cert_simple_chain;
                cert_simple_chain.read();
            }
            return cert_simple_chainArr;
        }

        public CERT_CHAIN_CONTEXT[] getRgpLowerQualityChainContext() {
            int i = this.cLowerQualityChainContext;
            CERT_CHAIN_CONTEXT[] cert_chain_contextArr = new CERT_CHAIN_CONTEXT[i];
            for (int i2 = 0; i2 < i; i2++) {
                CERT_CHAIN_CONTEXT cert_chain_context = (CERT_CHAIN_CONTEXT) Structure.newInstance(CERT_CHAIN_CONTEXT.class, this.rgpLowerQualityChainContext.getPointer(Native.POINTER_SIZE * i2));
                cert_chain_contextArr[i2] = cert_chain_context;
                cert_chain_context.read();
            }
            return cert_chain_contextArr;
        }
    }

    @Structure.FieldOrder({"cbSize", "RequestedUsage", "RequestedIssuancePolicy", "dwUrlRetrievalTimeout", "fCheckRevocationFreshnessTime", "dwRevocationFreshnessTime", "pftCacheResync", "pStrongSignPara", "dwStrongSignFlags"})
    public static class CERT_CHAIN_PARA extends Structure {
        public CERT_USAGE_MATCH RequestedIssuancePolicy;
        public CERT_USAGE_MATCH RequestedUsage;
        public int cbSize;
        public int dwRevocationFreshnessTime;
        public int dwStrongSignFlags;
        public int dwUrlRetrievalTimeout;
        public boolean fCheckRevocationFreshnessTime;
        public CERT_STRONG_SIGN_PARA.ByReference pStrongSignPara;
        public WinBase.FILETIME.ByReference pftCacheResync;

        public static class ByReference extends CERT_CHAIN_PARA implements Structure.ByReference {
        }

        public CERT_CHAIN_PARA() {
            super(W32APITypeMapper.DEFAULT);
        }
    }

    @Structure.FieldOrder({"cbSize", "dwFlags", "pvExtraPolicyPara"})
    public static class CERT_CHAIN_POLICY_PARA extends Structure {
        public int cbSize;
        public int dwFlags;
        public Pointer pvExtraPolicyPara;

        public static class ByReference extends CERT_CHAIN_POLICY_PARA implements Structure.ByReference {
        }
    }

    @Structure.FieldOrder({"cbSize", "dwError", "lChainIndex", "lElementIndex", "pvExtraPolicyStatus"})
    public static class CERT_CHAIN_POLICY_STATUS extends Structure {
        public int cbSize;
        public int dwError;
        public int lChainIndex;
        public int lElementIndex;
        public Pointer pvExtraPolicyStatus;

        public static class ByReference extends CERT_CHAIN_POLICY_STATUS implements Structure.ByReference {
        }
    }

    @Structure.FieldOrder({"dwCertEncodingType", "pbCertEncoded", "cbCertEncoded", "pCertInfo", "hCertStore"})
    public static class CERT_CONTEXT extends Structure {
        public int cbCertEncoded;
        public int dwCertEncodingType;
        public HCERTSTORE hCertStore;
        public CERT_INFO.ByReference pCertInfo;
        public Pointer pbCertEncoded;

        public static class ByReference extends CERT_CONTEXT implements Structure.ByReference {
        }
    }

    @Structure.FieldOrder({"pszObjId", "fCritical", "Value"})
    public static class CERT_EXTENSION extends Structure {
        public DATA_BLOB Value;
        public boolean fCritical;
        public String pszObjId;

        public static class ByReference extends CERT_EXTENSION implements Structure.ByReference {
        }

        public CERT_EXTENSION() {
            super(W32APITypeMapper.ASCII);
        }
    }

    @Structure.FieldOrder({"cExtension", "rgExtension"})
    public static class CERT_EXTENSIONS extends Structure {
        public int cExtension;
        public Pointer rgExtension;

        public static class ByReference extends CERT_EXTENSIONS implements Structure.ByReference {
        }

        public CERT_EXTENSION[] getRgExtension() {
            if (this.cExtension == 0) {
                return new CERT_EXTENSION[0];
            }
            CERT_EXTENSION[] cert_extensionArr = (CERT_EXTENSION[]) ((CERT_EXTENSION) Structure.newInstance(CERT_EXTENSION.class, this.rgExtension)).toArray(this.cExtension);
            cert_extensionArr[0].read();
            return cert_extensionArr;
        }
    }

    @Structure.FieldOrder({"dwVersion", "SerialNumber", "SignatureAlgorithm", "Issuer", "NotBefore", "NotAfter", "Subject", "SubjectPublicKeyInfo", "IssuerUniqueId", "SubjectUniqueId", "cExtension", "rgExtension"})
    public static class CERT_INFO extends Structure {
        public DATA_BLOB Issuer;
        public CRYPT_BIT_BLOB IssuerUniqueId;
        public WinBase.FILETIME NotAfter;
        public WinBase.FILETIME NotBefore;
        public DATA_BLOB SerialNumber;
        public CRYPT_ALGORITHM_IDENTIFIER SignatureAlgorithm;
        public DATA_BLOB Subject;
        public CERT_PUBLIC_KEY_INFO SubjectPublicKeyInfo;
        public CRYPT_BIT_BLOB SubjectUniqueId;
        public int cExtension;
        public int dwVersion;
        public Pointer rgExtension;

        public static class ByReference extends CERT_INFO implements Structure.ByReference {
        }

        public CERT_EXTENSION[] getRgExtension() {
            if (this.cExtension == 0) {
                return new CERT_EXTENSION[0];
            }
            CERT_EXTENSION[] cert_extensionArr = (CERT_EXTENSION[]) ((CERT_EXTENSION) Structure.newInstance(CERT_EXTENSION.class, this.rgExtension)).toArray(this.cExtension);
            cert_extensionArr[0].read();
            return cert_extensionArr;
        }
    }

    @Structure.FieldOrder({"Algorithm", "PublicKey"})
    public static class CERT_PUBLIC_KEY_INFO extends Structure {
        public CRYPT_ALGORITHM_IDENTIFIER Algorithm;
        public CRYPT_BIT_BLOB PublicKey;

        public static class ByReference extends CERT_PUBLIC_KEY_INFO implements Structure.ByReference {
        }
    }

    @Structure.FieldOrder({"cbSize", "pBaseCRLContext", "pDeltaCRLContext", "pCrlEntry", "fDeltaCrlEntry"})
    public static class CERT_REVOCATION_CRL_INFO extends Structure {
        public int cbSize;
        public boolean fDeltaCrlEntry;
        public CRL_CONTEXT.ByReference pBaseCRLContext;
        public CRL_ENTRY.ByReference pCrlEntry;
        public CRL_CONTEXT.ByReference pDeltaCRLContext;

        public static class ByReference extends CERT_REVOCATION_CRL_INFO implements Structure.ByReference {
        }

        public CERT_REVOCATION_CRL_INFO() {
            super(W32APITypeMapper.DEFAULT);
        }
    }

    @Structure.FieldOrder({"cbSize", "dwRevocationResult", "pszRevocationOid", "pvOidSpecificInfo", "fHasFreshnessTime", "dwFreshnessTime", "pCrlInfo"})
    public static class CERT_REVOCATION_INFO extends Structure {
        public int cbSize;
        public int dwFreshnessTime;
        public int dwRevocationResult;
        public boolean fHasFreshnessTime;
        public CERT_REVOCATION_CRL_INFO.ByReference pCrlInfo;
        public String pszRevocationOid;
        public Pointer pvOidSpecificInfo;

        public static class ByReference extends CERT_REVOCATION_INFO implements Structure.ByReference {
        }

        public CERT_REVOCATION_INFO() {
            super(W32APITypeMapper.ASCII);
        }
    }

    @Structure.FieldOrder({"cbSize", "TrustStatus", "cElement", "rgpElement", "pTrustListInfo", "fHasRevocationFreshnessTime", "dwRevocationFreshnessTime"})
    public static class CERT_SIMPLE_CHAIN extends Structure {
        public CERT_TRUST_STATUS TrustStatus;
        public int cElement;
        public int cbSize;
        public int dwRevocationFreshnessTime;
        public boolean fHasRevocationFreshnessTime;
        public CERT_TRUST_LIST_INFO.ByReference pTrustListInfo;
        public Pointer rgpElement;

        public static class ByReference extends CERT_SIMPLE_CHAIN implements Structure.ByReference {
        }

        public CERT_SIMPLE_CHAIN() {
            super(W32APITypeMapper.DEFAULT);
        }

        public CERT_CHAIN_ELEMENT[] getRgpElement() {
            int i = this.cElement;
            CERT_CHAIN_ELEMENT[] cert_chain_elementArr = new CERT_CHAIN_ELEMENT[i];
            for (int i2 = 0; i2 < i; i2++) {
                CERT_CHAIN_ELEMENT cert_chain_element = (CERT_CHAIN_ELEMENT) Structure.newInstance(CERT_CHAIN_ELEMENT.class, this.rgpElement.getPointer(Native.POINTER_SIZE * i2));
                cert_chain_elementArr[i2] = cert_chain_element;
                cert_chain_element.read();
            }
            return cert_chain_elementArr;
        }
    }

    @Structure.FieldOrder({"cbSize", "dwInfoChoice", "DUMMYUNIONNAME"})
    public static class CERT_STRONG_SIGN_PARA extends Structure {
        public DUMMYUNION DUMMYUNIONNAME;
        public int cbSize;
        public int dwInfoChoice;

        public static class ByReference extends CERT_CHAIN_PARA implements Structure.ByReference {
        }

        public class DUMMYUNION extends Union {
            CERT_STRONG_SIGN_SERIALIZED_INFO.ByReference pSerializedInfo;
            WTypes.LPSTR pszOID;
            Pointer pvInfo;

            public DUMMYUNION() {
            }
        }
    }

    @Structure.FieldOrder({"dwFlags", "pwszCNGSignHashAlgids", "pwszCNGPubKeyMinBitLengths"})
    public static class CERT_STRONG_SIGN_SERIALIZED_INFO extends Structure {
        public int dwFlags;
        public String pwszCNGPubKeyMinBitLengths;
        public String pwszCNGSignHashAlgids;

        public static class ByReference extends CERT_CHAIN_PARA implements Structure.ByReference {
        }

        public CERT_STRONG_SIGN_SERIALIZED_INFO() {
            super(W32APITypeMapper.UNICODE);
        }
    }

    @Structure.FieldOrder({"cbSize", "pCtlEntry", "pCtlContext"})
    public static class CERT_TRUST_LIST_INFO extends Structure {
        public int cbSize;
        public CTL_CONTEXT.ByReference pCtlContext;
        public CTL_ENTRY.ByReference pCtlEntry;

        public static class ByReference extends CERT_TRUST_LIST_INFO implements Structure.ByReference {
        }
    }

    @Structure.FieldOrder({"dwErrorStatus", "dwInfoStatus"})
    public static class CERT_TRUST_STATUS extends Structure {
        public int dwErrorStatus;
        public int dwInfoStatus;

        public static class ByReference extends CERT_TRUST_STATUS implements Structure.ByReference {
        }
    }

    @Structure.FieldOrder({"dwType", "Usage"})
    public static class CERT_USAGE_MATCH extends Structure {
        public CTL_USAGE Usage;
        public int dwType;

        public static class ByReference extends CERT_USAGE_MATCH implements Structure.ByReference {
        }
    }

    @Structure.FieldOrder({"dwCertEncodingType", "pbCrlEncoded", "cbCrlEncoded", "pCrlInfo", "hCertStore"})
    public static class CRL_CONTEXT extends Structure {
        public int cbCrlEncoded;
        public int dwCertEncodingType;
        public HCERTSTORE hCertStore;
        public CRL_INFO.ByReference pCrlInfo;
        public Pointer pbCrlEncoded;

        public static class ByReference extends CRL_CONTEXT implements Structure.ByReference {
        }
    }

    @Structure.FieldOrder({"SerialNumber", "RevocationDate", "cExtension", "rgExtension"})
    public static class CRL_ENTRY extends Structure {
        public WinBase.FILETIME RevocationDate;
        public DATA_BLOB SerialNumber;
        public int cExtension;
        public Pointer rgExtension;

        public static class ByReference extends CRL_ENTRY implements Structure.ByReference {
        }

        public CERT_EXTENSION[] getRgExtension() {
            if (this.cExtension == 0) {
                return new CERT_EXTENSION[0];
            }
            CERT_EXTENSION[] cert_extensionArr = (CERT_EXTENSION[]) ((CERT_EXTENSION) Structure.newInstance(CERT_EXTENSION.class, this.rgExtension)).toArray(this.cExtension);
            cert_extensionArr[0].read();
            return cert_extensionArr;
        }
    }

    @Structure.FieldOrder({"dwVersion", "SignatureAlgorithm", "Issuer", "ThisUpdate", "NextUpdate", "cCRLEntry", "rgCRLEntry", "cExtension", "rgExtension"})
    public static class CRL_INFO extends Structure {
        public DATA_BLOB Issuer;
        public WinBase.FILETIME NextUpdate;
        public CRYPT_ALGORITHM_IDENTIFIER SignatureAlgorithm;
        public WinBase.FILETIME ThisUpdate;
        public int cCRLEntry;
        public int cExtension;
        public int dwVersion;
        public Pointer rgCRLEntry;
        public Pointer rgExtension;

        public static class ByReference extends CRL_INFO implements Structure.ByReference {
        }

        public CRL_ENTRY[] getRgCRLEntry() {
            if (this.cCRLEntry == 0) {
                return new CRL_ENTRY[0];
            }
            CRL_ENTRY[] crl_entryArr = (CRL_ENTRY[]) ((CRL_ENTRY) Structure.newInstance(CRL_ENTRY.class, this.rgCRLEntry)).toArray(this.cCRLEntry);
            crl_entryArr[0].read();
            return crl_entryArr;
        }

        public CERT_EXTENSION[] getRgExtension() {
            if (this.cExtension == 0) {
                return new CERT_EXTENSION[0];
            }
            CERT_EXTENSION[] cert_extensionArr = (CERT_EXTENSION[]) ((CERT_EXTENSION) Structure.newInstance(CERT_EXTENSION.class, this.rgExtension)).toArray(this.cExtension);
            cert_extensionArr[0].read();
            return cert_extensionArr;
        }
    }

    @Structure.FieldOrder({"pszObjId", "Parameters"})
    public static class CRYPT_ALGORITHM_IDENTIFIER extends Structure {
        public DATA_BLOB Parameters;
        public String pszObjId;

        public static class ByReference extends CRYPT_ALGORITHM_IDENTIFIER implements Structure.ByReference {
        }

        public CRYPT_ALGORITHM_IDENTIFIER() {
            super(W32APITypeMapper.ASCII);
        }
    }

    @Structure.FieldOrder({"pszObjId", "cValue", "rgValue"})
    public static class CRYPT_ATTRIBUTE extends Structure {
        public int cValue;
        public String pszObjId;
        public DATA_BLOB.ByReference rgValue;

        public static class ByReference extends CRYPT_ATTRIBUTE implements Structure.ByReference {
        }

        public CRYPT_ATTRIBUTE() {
            super(W32APITypeMapper.ASCII);
        }

        public DATA_BLOB[] getRgValue() {
            return (DATA_BLOB[]) this.rgValue.toArray(this.cValue);
        }
    }

    @Structure.FieldOrder({"cbData", "pbData", "cUnusedBits"})
    public static class CRYPT_BIT_BLOB extends Structure {
        public int cUnusedBits;
        public int cbData;
        public Pointer pbData;

        public static class ByReference extends CRYPT_BIT_BLOB implements Structure.ByReference {
        }
    }

    @Structure.FieldOrder({"pwszContainerName", "pwszProvName", "dwProvType", "dwFlags", "cProvParam", "rgProvParam", "dwKeySpec"})
    public static class CRYPT_KEY_PROV_INFO extends Structure {
        public int cProvParam;
        public int dwFlags;
        public int dwKeySpec;
        public int dwProvType;
        public String pwszContainerName;
        public String pwszProvName;
        public Pointer rgProvParam;

        public static class ByReference extends CRYPT_KEY_PROV_INFO implements Structure.ByReference {
        }

        public CRYPT_KEY_PROV_INFO() {
            super(W32APITypeMapper.UNICODE);
        }

        public CRYPT_KEY_PROV_PARAM[] getRgProvParam() {
            int i = this.cProvParam;
            CRYPT_KEY_PROV_PARAM[] crypt_key_prov_paramArr = new CRYPT_KEY_PROV_PARAM[i];
            for (int i2 = 0; i2 < i; i2++) {
                CRYPT_KEY_PROV_PARAM crypt_key_prov_param = (CRYPT_KEY_PROV_PARAM) Structure.newInstance(CRYPT_KEY_PROV_PARAM.class, this.rgProvParam.getPointer(Native.POINTER_SIZE * i2));
                crypt_key_prov_paramArr[i2] = crypt_key_prov_param;
                crypt_key_prov_param.read();
            }
            return crypt_key_prov_paramArr;
        }
    }

    @Structure.FieldOrder({"dwParam", "pbData", "cbData", "dwFlags"})
    public static class CRYPT_KEY_PROV_PARAM extends Structure {
        public int cbData;
        public int dwFlags;
        public int dwParam;
        public Pointer pbData;

        public static class ByReference extends CRYPT_KEY_PROV_PARAM implements Structure.ByReference {
        }
    }

    @Structure.FieldOrder({"cbSize", "dwMsgEncodingType", "pSigningCert", "HashAlgorithm", "pvHashAuxInfo", "cMsgCert", "rgpMsgCert", "cMsgCrl", "rgpMsgCrl", "cAuthAttr", "rgAuthAttr", "cUnauthAttr", "rgUnauthAttr", "dwFlags", "dwInnerContentType", "HashEncryptionAlgorithm", "pvHashEncryptionAuxInfo"})
    public static class CRYPT_SIGN_MESSAGE_PARA extends Structure {
        public CRYPT_ALGORITHM_IDENTIFIER HashAlgorithm;
        public CRYPT_ALGORITHM_IDENTIFIER HashEncryptionAlgorithm;
        public int cAuthAttr;
        public int cMsgCert;
        public int cMsgCrl;
        public int cUnauthAttr;
        public int cbSize;
        public int dwFlags;
        public int dwInnerContentType;
        public int dwMsgEncodingType;
        public CERT_CONTEXT.ByReference pSigningCert;
        public Pointer pvHashAuxInfo;
        public Pointer pvHashEncryptionAuxInfo;
        public Pointer rgpMsgCert = null;
        public Pointer rgpMsgCrl = null;
        public Pointer rgAuthAttr = null;
        public Pointer rgUnauthAttr = null;

        public static class ByReference extends CRYPT_SIGN_MESSAGE_PARA implements Structure.ByReference {
        }

        public CRYPT_ATTRIBUTE[] getRgAuthAttr() {
            return this.cAuthAttr == 0 ? new CRYPT_ATTRIBUTE[0] : (CRYPT_ATTRIBUTE[]) ((CRYPT_ATTRIBUTE) Structure.newInstance(CRYPT_ATTRIBUTE.class, this.rgAuthAttr)).toArray(this.cAuthAttr);
        }

        public CRYPT_ATTRIBUTE[] getRgUnauthAttr() {
            return this.cUnauthAttr == 0 ? new CRYPT_ATTRIBUTE[0] : (CRYPT_ATTRIBUTE[]) ((CRYPT_ATTRIBUTE) Structure.newInstance(CRYPT_ATTRIBUTE.class, this.rgUnauthAttr)).toArray(this.cUnauthAttr);
        }

        public CERT_CONTEXT[] getRgpMsgCert() {
            int i = this.cMsgCrl;
            CERT_CONTEXT[] cert_contextArr = new CERT_CONTEXT[i];
            for (int i2 = 0; i2 < i; i2++) {
                CERT_CONTEXT cert_context = (CERT_CONTEXT) Structure.newInstance(CERT_CONTEXT.class, this.rgpMsgCert.getPointer(Native.POINTER_SIZE * i2));
                cert_contextArr[i2] = cert_context;
                cert_context.read();
            }
            return cert_contextArr;
        }

        public CRL_CONTEXT[] getRgpMsgCrl() {
            int i = this.cMsgCrl;
            CRL_CONTEXT[] crl_contextArr = new CRL_CONTEXT[i];
            for (int i2 = 0; i2 < i; i2++) {
                CRL_CONTEXT crl_context = (CRL_CONTEXT) Structure.newInstance(CRL_CONTEXT.class, this.rgpMsgCrl.getPointer(Native.POINTER_SIZE * i2));
                crl_contextArr[i2] = crl_context;
                crl_context.read();
            }
            return crl_contextArr;
        }
    }

    @Structure.FieldOrder({"cbSize", "dwMsgAndCertEncodingType", "hCryptProv", "pfnGetSignerCertificate", "pvGetArg", "pStrongSignPara"})
    public static class CRYPT_VERIFY_MESSAGE_PARA extends Structure {
        public int cbSize;
        public int dwMsgAndCertEncodingType;
        public HCRYPTPROV_LEGACY hCryptProv;
        public CERT_STRONG_SIGN_PARA.ByReference pStrongSignPara;
        public CryptGetSignerCertificateCallback pfnGetSignerCertificate;
        public Pointer pvGetArg;

        public static class ByReference extends CRYPT_SIGN_MESSAGE_PARA implements Structure.ByReference {
        }

        @Override // com.sun.jna.Structure
        public void write() {
            this.cbSize = size();
            super.write();
        }
    }

    @Structure.FieldOrder({"dwMsgAndCertEncodingType", "pbCtlEncoded", "cbCtlEncoded", "pCtlInfo", "hCertStore", "hCryptMsg", "pbCtlContent", "cbCtlContent"})
    public static class CTL_CONTEXT extends Structure {
        public int cbCtlContent;
        public int cbCtlEncoded;
        public int dwMsgAndCertEncodingType;
        public HCERTSTORE hCertStore;
        public HCRYPTMSG hCryptMsg;
        public CTL_INFO.ByReference pCtlInfo;
        public Pointer pbCtlContent;
        public Pointer pbCtlEncoded;

        public static class ByReference extends CTL_CONTEXT implements Structure.ByReference {
        }
    }

    @Structure.FieldOrder({"SubjectIdentifier", "cAttribute", "rgAttribute"})
    public static class CTL_ENTRY extends Structure {
        public DATA_BLOB SubjectIdentifier;
        public int cAttribute;
        public Pointer rgAttribute;

        public static class ByReference extends CTL_ENTRY implements Structure.ByReference {
        }

        public CRYPT_ATTRIBUTE[] getRgAttribute() {
            if (this.cAttribute == 0) {
                return new CRYPT_ATTRIBUTE[0];
            }
            CRYPT_ATTRIBUTE[] crypt_attributeArr = (CRYPT_ATTRIBUTE[]) ((CRYPT_ATTRIBUTE) Structure.newInstance(CRYPT_ATTRIBUTE.class, this.rgAttribute)).toArray(this.cAttribute);
            crypt_attributeArr[0].read();
            return crypt_attributeArr;
        }
    }

    @Structure.FieldOrder({"dwVersion", "SubjectUsage", "ListIdentifier", "SequenceNumber", "ThisUpdate", "NextUpdate", "SubjectAlgorithm", "cCTLEntry", "rgCTLEntry", "cExtension", "rgExtension"})
    public static class CTL_INFO extends Structure {
        public DATA_BLOB ListIdentifier;
        public WinBase.FILETIME NextUpdate;
        public DATA_BLOB SequenceNumber;
        public CRYPT_ALGORITHM_IDENTIFIER SubjectAlgorithm;
        public CTL_USAGE SubjectUsage;
        public WinBase.FILETIME ThisUpdate;
        public int cCTLEntry;
        public int cExtension;
        public int dwVersion;
        public Pointer rgCTLEntry;
        public Pointer rgExtension;

        public static class ByReference extends CTL_INFO implements Structure.ByReference {
        }

        public CTL_ENTRY[] getRgCTLEntry() {
            if (this.cCTLEntry == 0) {
                return new CTL_ENTRY[0];
            }
            CTL_ENTRY[] ctl_entryArr = (CTL_ENTRY[]) ((CTL_ENTRY) Structure.newInstance(CTL_ENTRY.class, this.rgCTLEntry)).toArray(this.cCTLEntry);
            ctl_entryArr[0].read();
            return ctl_entryArr;
        }

        public CERT_EXTENSION[] getRgExtension() {
            if (this.cExtension == 0) {
                return new CERT_EXTENSION[0];
            }
            CERT_EXTENSION[] cert_extensionArr = (CERT_EXTENSION[]) ((CERT_EXTENSION) Structure.newInstance(CERT_EXTENSION.class, this.rgExtension)).toArray(this.cExtension);
            cert_extensionArr[0].read();
            return cert_extensionArr;
        }
    }

    @Structure.FieldOrder({"cUsageIdentifier", "rgpszUsageIdentifier"})
    public static class CTL_USAGE extends Structure {
        public int cUsageIdentifier;
        public Pointer rgpszUsageIdentifier;

        public static class ByReference extends CTL_USAGE implements Structure.ByReference {
        }

        public String[] getRgpszUsageIdentier() {
            int i = this.cUsageIdentifier;
            return i == 0 ? new String[0] : this.rgpszUsageIdentifier.getStringArray(0L, i);
        }

        public void setRgpszUsageIdentier(String[] strArr) {
            if (strArr == null || strArr.length == 0) {
                this.cUsageIdentifier = 0;
                this.rgpszUsageIdentifier = null;
            } else {
                this.cUsageIdentifier = strArr.length;
                this.rgpszUsageIdentifier = new StringArray(strArr);
            }
        }
    }

    public interface CryptGetSignerCertificateCallback extends StdCallLibrary.StdCallCallback {
        CERT_CONTEXT.ByReference callback(Pointer pointer, int i, CERT_INFO cert_info, HCERTSTORE hcertstore);
    }

    public static class HCERTCHAINENGINE extends WinNT.HANDLE {
        public HCERTCHAINENGINE() {
        }

        public HCERTCHAINENGINE(Pointer pointer) {
            super(pointer);
        }
    }

    public static class HCERTSTORE extends WinNT.HANDLE {
        public HCERTSTORE() {
        }

        public HCERTSTORE(Pointer pointer) {
            super(pointer);
        }
    }

    public static class HCRYPTMSG extends WinNT.HANDLE {
        public HCRYPTMSG() {
        }

        public HCRYPTMSG(Pointer pointer) {
            super(pointer);
        }
    }

    public static class HCRYPTPROV_LEGACY extends BaseTSD.ULONG_PTR {
        public HCRYPTPROV_LEGACY() {
        }

        public HCRYPTPROV_LEGACY(long j) {
            super(j);
        }
    }

    @Structure.FieldOrder({"cbSize", "pCertContext", "TrustStatus", "pRevocationInfo", "pIssuanceUsage", "pApplicationUsage", "pwszExtendedErrorInfo"})
    public static class CERT_CHAIN_ELEMENT extends Structure {
        public CERT_TRUST_STATUS TrustStatus;
        public int cbSize;
        public CTL_USAGE.ByReference pApplicationUsage;
        public CERT_CONTEXT.ByReference pCertContext;
        public CTL_USAGE.ByReference pIssuanceUsage;
        public CERT_REVOCATION_INFO.ByReference pRevocationInfo;
        public String pwszExtendedErrorInfo;

        public static class ByReference extends CERT_CHAIN_ELEMENT implements Structure.ByReference {
        }

        public CERT_CHAIN_ELEMENT(Pointer pointer) {
            super(pointer, 0, W32APITypeMapper.UNICODE);
        }

        public CERT_CHAIN_ELEMENT() {
            super(W32APITypeMapper.UNICODE);
        }
    }

    @Structure.FieldOrder({"cbSize", "dwPromptFlags", "hwndApp", "szPrompt"})
    public static class CRYPTPROTECT_PROMPTSTRUCT extends Structure {
        public int cbSize;
        public int dwPromptFlags;
        public WinDef.HWND hwndApp;
        public String szPrompt;

        public CRYPTPROTECT_PROMPTSTRUCT(Pointer pointer) {
            super(pointer, 0, W32APITypeMapper.DEFAULT);
            read();
        }

        public CRYPTPROTECT_PROMPTSTRUCT() {
            super(W32APITypeMapper.DEFAULT);
        }
    }

    @Structure.FieldOrder({"cbData", "pbData"})
    public static class DATA_BLOB extends Structure {
        public int cbData;
        public Pointer pbData;

        public static class ByReference extends DATA_BLOB implements Structure.ByReference {
        }

        public DATA_BLOB(byte[] bArr) {
            if (bArr.length <= 0) {
                this.pbData = new Memory(1L);
                this.cbData = 0;
            } else {
                Memory memory = new Memory(bArr.length);
                this.pbData = memory;
                memory.write(0L, bArr, 0, bArr.length);
                this.cbData = bArr.length;
            }
        }

        public byte[] getData() {
            Pointer pointer = this.pbData;
            if (pointer == null) {
                return null;
            }
            return pointer.getByteArray(0L, this.cbData);
        }

        public DATA_BLOB(Pointer pointer) {
            super(pointer);
            read();
        }

        public DATA_BLOB() {
        }

        public DATA_BLOB(String str) {
            this(Native.toByteArray(str));
        }
    }
}
