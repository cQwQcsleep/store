package com.sun.xml.internal.stream;

import com.sun.org.apache.xerces.internal.impl.PropertyManager;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.util.URI;
import com.sun.org.apache.xerces.internal.util.XMLResourceIdentifierImpl;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLEntityStorage {
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String WARN_ON_DUPLICATE_ENTITYDEF = "http://apache.org/xml/features/warn-on-duplicate-entitydef";
    private static String gEscapedUserDir;
    private static String gUserDir;
    protected Entity.ScannedEntity fCurrentEntity;
    private XMLEntityManager fEntityManager;
    protected XMLErrorReporter fErrorReporter;
    protected PropertyManager fPropertyManager;
    protected boolean fWarnDuplicateEntityDef;
    private static boolean[] gNeedEscaping = new boolean[128];
    private static char[] gAfterEscaping1 = new char[128];
    private static char[] gAfterEscaping2 = new char[128];
    private static char[] gHexChs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    protected Map<String, Entity> fEntities = new HashMap();
    protected boolean fInExternalSubset = false;

    static {
        for (int i = 0; i <= 31; i++) {
            gNeedEscaping[i] = true;
            char[] cArr = gAfterEscaping1;
            char[] cArr2 = gHexChs;
            cArr[i] = cArr2[i >> 4];
            gAfterEscaping2[i] = cArr2[i & 15];
        }
        gNeedEscaping[127] = true;
        gAfterEscaping1[127] = '7';
        gAfterEscaping2[127] = 'F';
        char[] cArr3 = {' ', '<', '>', '#', '%', '\"', '{', '}', '|', '\\', '^', '~', '[', ']', '`'};
        for (int i2 = 0; i2 < 15; i2++) {
            char c = cArr3[i2];
            gNeedEscaping[c] = true;
            char[] cArr4 = gAfterEscaping1;
            char[] cArr5 = gHexChs;
            cArr4[c] = cArr5[c >> 4];
            gAfterEscaping2[c] = cArr5[c & 15];
        }
    }

    public XMLEntityStorage(PropertyManager propertyManager) {
        this.fPropertyManager = propertyManager;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x006a A[Catch: Exception -> 0x0080, TryCatch #0 {Exception -> 0x0080, blocks: (B:10:0x0016, B:12:0x001c, B:15:0x0023, B:24:0x007a, B:18:0x002f, B:20:0x0036, B:22:0x0047, B:23:0x006a), top: B:31:0x0016, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:28:0x0083 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:29:0x0084  */
    public static String expandSystemId(String str, String str2) {
        URI uri;
        URI uri2;
        URI uri3;
        if (str == null || str.length() == 0) {
            return str;
        }
        try {
            new URI(str);
            return str;
        } catch (URI.MalformedURIException unused) {
            String strFixURI = fixURI(str);
            if (str2 != null) {
                try {
                    if (str2.length() == 0 || str2.equals(str)) {
                        uri2 = new URI("file", "", getUserDir(), null, null);
                    } else {
                        try {
                            uri2 = new URI(fixURI(str2));
                        } catch (URI.MalformedURIException unused2) {
                            if (str2.indexOf(58) != -1) {
                                uri = new URI("file", "", fixURI(str2), null, null);
                            } else {
                                uri = new URI("file", "", getUserDir() + fixURI(str2), null, null);
                            }
                            uri2 = uri;
                        }
                    }
                    uri3 = new URI(uri2, strFixURI);
                } catch (Exception unused3) {
                    uri3 = null;
                    if (uri3 == null) {
                        return str;
                    }
                    return uri3.toString();
                }
            } else {
                uri2 = new URI("file", "", getUserDir(), null, null);
                uri3 = new URI(uri2, strFixURI);
            }
            if (uri3 == null) {
                return str;
            }
            return uri3.toString();
        }
    }

    public static String fixURI(String str) {
        String strReplace = str.replace(File.separatorChar, '/');
        if (strReplace.length() < 2) {
            return strReplace;
        }
        char cCharAt = strReplace.charAt(1);
        if (cCharAt != ':') {
            return (cCharAt == '/' && strReplace.charAt(0) == '/') ? "file:".concat(strReplace) : strReplace;
        }
        char upperCase = Character.toUpperCase(strReplace.charAt(0));
        return (upperCase < 'A' || upperCase > 'Z') ? strReplace : PsuedoNames.PSEUDONAME_ROOT.concat(strReplace);
    }

    private static synchronized String getUserDir() {
        char cCharAt;
        char upperCase;
        String systemProperty = "";
        try {
            systemProperty = SecuritySupport.getSystemProperty("user.dir");
        } catch (SecurityException unused) {
        }
        if (systemProperty.length() == 0) {
            return "";
        }
        if (systemProperty.equals(gUserDir)) {
            return gEscapedUserDir;
        }
        gUserDir = systemProperty;
        String strReplace = systemProperty.replace(File.separatorChar, '/');
        int length = strReplace.length();
        StringBuilder sb = new StringBuilder(length * 3);
        if (length >= 2 && strReplace.charAt(1) == ':' && (upperCase = Character.toUpperCase(strReplace.charAt(0))) >= 'A' && upperCase <= 'Z') {
            sb.append('/');
        }
        int i = 0;
        while (i < length && (cCharAt = strReplace.charAt(i)) < 128) {
            if (gNeedEscaping[cCharAt]) {
                sb.append('%');
                sb.append(gAfterEscaping1[cCharAt]);
                sb.append(gAfterEscaping2[cCharAt]);
            } else {
                sb.append(cCharAt);
            }
            i++;
        }
        if (i < length) {
            try {
                for (byte b : strReplace.substring(i).getBytes("UTF-8")) {
                    if (b < 0) {
                        int i2 = b + 256;
                        sb.append('%');
                        sb.append(gHexChs[i2 >> 4]);
                        sb.append(gHexChs[i2 & 15]);
                    } else if (gNeedEscaping[b]) {
                        sb.append('%');
                        sb.append(gAfterEscaping1[b]);
                        sb.append(gAfterEscaping2[b]);
                    } else {
                        sb.append((char) b);
                    }
                }
            } catch (UnsupportedEncodingException unused2) {
                return strReplace;
            }
        }
        if (!strReplace.endsWith(PsuedoNames.PSEUDONAME_ROOT)) {
            sb.append('/');
        }
        String string = sb.toString();
        gEscapedUserDir = string;
        return string;
    }

    public void addExternalEntity(String str, String str2, String str3, String str4) {
        Entity.ScannedEntity scannedEntity;
        XMLResourceIdentifier xMLResourceIdentifier;
        if (this.fEntities.containsKey(str)) {
            if (this.fWarnDuplicateEntityDef) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[]{str}, (short) 0);
            }
        } else {
            if (str4 == null && (scannedEntity = this.fCurrentEntity) != null && (xMLResourceIdentifier = scannedEntity.entityLocation) != null) {
                str4 = xMLResourceIdentifier.getExpandedSystemId();
            }
            this.fCurrentEntity = this.fEntityManager.getCurrentEntity();
            this.fEntities.put(str, new Entity.ExternalEntity(str, new XMLResourceIdentifierImpl(str2, str3, str4, expandSystemId(str3, str4)), null, this.fInExternalSubset));
        }
    }

    public void addInternalEntity(String str, String str2) {
        if (!this.fEntities.containsKey(str)) {
            this.fEntities.put(str, new Entity.InternalEntity(str, str2, this.fInExternalSubset));
        } else if (this.fWarnDuplicateEntityDef) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[]{str}, (short) 0);
        }
    }

    public void addUnparsedEntity(String str, String str2, String str3, String str4, String str5) {
        this.fCurrentEntity = this.fEntityManager.getCurrentEntity();
        if (!this.fEntities.containsKey(str)) {
            this.fEntities.put(str, new Entity.ExternalEntity(str, new XMLResourceIdentifierImpl(str2, str3, str4, null), str5, this.fInExternalSubset));
        } else if (this.fWarnDuplicateEntityDef) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[]{str}, (short) 0);
        }
    }

    public void endExternalSubset() {
        this.fInExternalSubset = false;
    }

    public Map<String, Entity> getEntities() {
        return this.fEntities;
    }

    public Entity getEntity(String str) {
        return this.fEntities.get(str);
    }

    public boolean isDeclaredEntity(String str) {
        return this.fEntities.get(str) != null;
    }

    public boolean isEntityDeclInExternalSubset(String str) {
        Entity entity = this.fEntities.get(str);
        if (entity == null) {
            return false;
        }
        return entity.isEntityDeclInExternalSubset();
    }

    public boolean isExternalEntity(String str) {
        Entity entity = this.fEntities.get(str);
        if (entity == null) {
            return false;
        }
        return entity.isExternal();
    }

    public boolean isUnparsedEntity(String str) {
        Entity entity = this.fEntities.get(str);
        if (entity == null) {
            return false;
        }
        return entity.isUnparsed();
    }

    public void reset(XMLComponentManager xMLComponentManager) throws XMLConfigurationException {
        this.fWarnDuplicateEntityDef = xMLComponentManager.getFeature(WARN_ON_DUPLICATE_ENTITYDEF, false);
        this.fErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fEntities.clear();
        this.fCurrentEntity = null;
    }

    public void startExternalSubset() {
        this.fInExternalSubset = true;
    }

    public XMLEntityStorage(XMLEntityManager xMLEntityManager) {
        this.fEntityManager = xMLEntityManager;
    }

    public void reset() {
        this.fEntities.clear();
        this.fCurrentEntity = null;
    }

    public void reset(PropertyManager propertyManager) {
        this.fErrorReporter = (XMLErrorReporter) propertyManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fEntities.clear();
        this.fCurrentEntity = null;
    }

    public static String expandSystemId(String str) {
        return expandSystemId(str, null);
    }
}
