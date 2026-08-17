package com.sun.org.apache.xpath.internal.compiler;

import com.sun.org.apache.xml.internal.utils.ObjectVector;
import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import java.util.List;
import javax.xml.transform.TransformerException;
import jdk.xml.internal.XMLSecurityManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class Lexer {
    static final int TARGETEXTRA = 10000;
    private Compiler m_compiler;
    private int m_grpCount;
    private int m_grpCountLimit;
    PrefixResolver m_namespaceContext;
    private int m_opCount;
    private int m_opCountLimit;
    private int m_patternMapSize;
    XPathParser m_processor;
    XMLSecurityManager m_xmlSecMgr;
    private int[] m_patternMap = new int[100];
    private boolean isLiteral = false;

    public Lexer(Compiler compiler, PrefixResolver prefixResolver, XPathParser xPathParser, XMLSecurityManager xMLSecurityManager) {
        this.m_compiler = compiler;
        this.m_namespaceContext = prefixResolver;
        this.m_processor = xPathParser;
        this.m_xmlSecMgr = xMLSecurityManager;
        this.m_opCountLimit = xMLSecurityManager != null ? xMLSecurityManager.getLimit(XMLSecurityManager.Limit.XPATH_OP_LIMIT) : 0;
        this.m_grpCountLimit = xMLSecurityManager != null ? xMLSecurityManager.getLimit(XMLSecurityManager.Limit.XPATH_GROUP_LIMIT) : 0;
    }

    private final void addToTokenQueue(String str) {
        this.m_compiler.getTokenQueue().addElement(str);
    }

    private int getTokenQueuePosFromMap(int i) {
        int i2 = this.m_patternMap[i];
        return i2 >= 10000 ? i2 - 10000 : i2;
    }

    private void incrementCount() {
        this.m_opCount++;
        this.isLiteral = false;
    }

    private int mapNSTokens(String str, int i, int i2, int i3) throws TransformerException {
        String namespaceForPrefix;
        String strSubstring = (i < 0 || i2 < 0) ? "" : str.substring(i, i2);
        if (this.m_namespaceContext == null || strSubstring.equals("*") || strSubstring.equals("xmlns")) {
            namespaceForPrefix = strSubstring;
        } else {
            try {
                int length = strSubstring.length();
                PrefixResolver prefixResolver = this.m_namespaceContext;
                namespaceForPrefix = length > 0 ? prefixResolver.getNamespaceForPrefix(strSubstring) : prefixResolver.getNamespaceForPrefix(strSubstring);
            } catch (ClassCastException unused) {
                namespaceForPrefix = this.m_namespaceContext.getNamespaceForPrefix(strSubstring);
            }
        }
        if (namespaceForPrefix == null || namespaceForPrefix.length() <= 0) {
            this.m_processor.error("ER_PREFIX_MUST_RESOLVE", new String[]{strSubstring});
            return -1;
        }
        addToTokenQueue(namespaceForPrefix);
        addToTokenQueue(":");
        String strSubstring2 = str.substring(i2 + 1, i3);
        if (strSubstring2.length() <= 0) {
            return -1;
        }
        addToTokenQueue(strSubstring2);
        return -1;
    }

    private boolean mapPatternElemPos(int i, boolean z, boolean z2) {
        if (i != 0) {
            return z;
        }
        int i2 = this.m_patternMapSize;
        int[] iArr = this.m_patternMap;
        if (i2 >= iArr.length) {
            int length = iArr.length;
            int[] iArr2 = new int[i2 + 100];
            this.m_patternMap = iArr2;
            System.arraycopy(iArr, 0, iArr2, 0, length);
        }
        if (!z) {
            int[] iArr3 = this.m_patternMap;
            int i3 = this.m_patternMapSize - 1;
            iArr3[i3] = iArr3[i3] - 10000;
        }
        this.m_patternMap[this.m_patternMapSize] = (this.m_compiler.getTokenQueueSize() - (z2 ? 1 : 0)) + 10000;
        this.m_patternMapSize++;
        return false;
    }

    private char peekNext(String str, int i) {
        if (i < 0 || i >= str.length() - 1) {
            return (char) 0;
        }
        return str.charAt(i + 1);
    }

    private void recordTokenString(List<String> list) {
        int tokenQueuePosFromMap = getTokenQueuePosFromMap(this.m_patternMapSize - 1);
        int i = tokenQueuePosFromMap + 1;
        resetTokenMark(i);
        boolean zLookahead = this.m_processor.lookahead('(', 1);
        XPathParser xPathParser = this.m_processor;
        if (!zLookahead) {
            if (xPathParser.tokenIs('@')) {
                resetTokenMark(tokenQueuePosFromMap + 2);
                tokenQueuePosFromMap = i;
            }
            if (this.m_processor.lookahead(':', 1)) {
                tokenQueuePosFromMap += 2;
            }
            list.add((String) this.m_compiler.getTokenQueue().elementAt(tokenQueuePosFromMap));
            return;
        }
        int keywordToken = getKeywordToken(xPathParser.m_token);
        if (keywordToken == 35) {
            list.add(PsuedoNames.PSEUDONAME_ROOT);
            return;
        }
        if (keywordToken == 36) {
            list.add("*");
            return;
        }
        switch (keywordToken) {
            case OpCodes.NODETYPE_COMMENT /* 1030 */:
                list.add(PsuedoNames.PSEUDONAME_COMMENT);
                break;
            case OpCodes.NODETYPE_TEXT /* 1031 */:
                list.add(PsuedoNames.PSEUDONAME_TEXT);
                break;
            case OpCodes.NODETYPE_PI /* 1032 */:
                list.add("*");
                break;
            case OpCodes.NODETYPE_NODE /* 1033 */:
                list.add("*");
                break;
            default:
                list.add("*");
                break;
        }
    }

    private final void resetTokenMark(int i) {
        int tokenQueueSize = this.m_compiler.getTokenQueueSize();
        XPathParser xPathParser = this.m_processor;
        if (i <= 0) {
            i = 0;
        } else if (i <= tokenQueueSize) {
            i--;
        }
        xPathParser.m_queueMark = i;
        if (i >= tokenQueueSize) {
            xPathParser.m_token = null;
            xPathParser.m_tokenChar = (char) 0;
            return;
        }
        ObjectVector tokenQueue = this.m_compiler.getTokenQueue();
        XPathParser xPathParser2 = this.m_processor;
        int i2 = xPathParser2.m_queueMark;
        xPathParser2.m_queueMark = i2 + 1;
        xPathParser.m_token = (String) tokenQueue.elementAt(i2);
        XPathParser xPathParser3 = this.m_processor;
        xPathParser3.m_tokenChar = xPathParser3.m_token.charAt(0);
    }

    public final int getKeywordToken(String str) {
        try {
            Integer keyWord = Keywords.getKeyWord(str);
            if (keyWord != null) {
                return keyWord.intValue();
            }
            return 0;
        } catch (ClassCastException | NullPointerException unused) {
            return 0;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:101:0x0161 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:102:0x0163  */
    /* JADX WARN: Code duplicated, block: B:104:0x016a  */
    /* JADX WARN: Code duplicated, block: B:107:0x0170  */
    /* JADX WARN: Code duplicated, block: B:111:0x017c A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:123:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:126:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:153:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:155:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:158:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:160:0x0201  */
    /* JADX WARN: Code duplicated, block: B:161:0x0206  */
    /* JADX WARN: Code duplicated, block: B:163:0x0210  */
    /* JADX WARN: Code duplicated, block: B:59:0x00db  */
    /* JADX WARN: Code duplicated, block: B:68:0x00ed A[FALL_THROUGH, PHI: r11
      0x00ed: PHI (r11v5 boolean) = 
      (r11v1 boolean)
      (r11v1 boolean)
      (r11v12 boolean)
      (r11v1 boolean)
      (r11v1 boolean)
      (r11v1 boolean)
      (r11v1 boolean)
      (r11v1 boolean)
     binds: [B:11:0x003d, B:12:0x003f, B:62:0x00e2, B:17:0x0049, B:18:0x004b, B:19:0x004e, B:20:0x0051, B:21:0x0054] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:93:0x0142  */
    /* JADX WARN: Code duplicated, block: B:95:0x0148  */
    /* JADX WARN: Code duplicated, block: B:96:0x014d  */
    /* JADX WARN: Code duplicated, block: B:98:0x0158  */
    /* JADX WARN: Code duplicated, block: B:99:0x015a A[ADDED_TO_REGION] */
    /* JADX WARN: Failed to find 'out' block for switch in B:20:0x0051. Please report as an issue. */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1093)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:419)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:31)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:399)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:31)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:21)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    public void tokenize(java.lang.String r18, java.util.List<java.lang.String> r19) throws javax.xml.transform.TransformerException {
        /*
            Method dump skipped, instruction units count: 746
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.org.apache.xpath.internal.compiler.Lexer.tokenize(java.lang.String, java.util.List):void");
    }

    public void tokenize(String str) throws TransformerException {
        tokenize(str, null);
    }
}
