package org.eclipse.tm4e.core.registry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.jdom2.JDOMConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IThemeSource {

    public enum ContentType {
        JSON,
        YAML,
        XML
    }

    static IThemeSource fromFile(final File file, final ContentType contentType, final Charset charset) {
        final String path = file.getPath();
        if (contentType == null) {
            contentType = guessFileFormat(path);
        }
        return new IThemeSource() { // from class: org.eclipse.tm4e.core.registry.IThemeSource.2
            @Override // org.eclipse.tm4e.core.registry.IThemeSource
            public ContentType getContentType() {
                return contentType;
            }

            @Override // org.eclipse.tm4e.core.registry.IThemeSource
            public String getFilePath() {
                return path;
            }

            @Override // org.eclipse.tm4e.core.registry.IThemeSource
            public Reader getReader() throws IOException {
                FileInputStream fileInputStream = new FileInputStream(file);
                Charset charset2 = charset;
                if (charset2 == null) {
                    charset2 = StandardCharsets.UTF_8;
                }
                return new BufferedReader(new InputStreamReader(fileInputStream, charset2));
            }
        };
    }

    static IThemeSource fromInputStream(InputStream inputStream, final String str, Charset charset) {
        final ContentType contentTypeGuessFileFormat = guessFileFormat(str);
        try {
            if (charset == null) {
                charset = StandardCharsets.UTF_8;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
            try {
                final StringBuilder sb = new StringBuilder();
                char[] cArr = new char[16384];
                while (true) {
                    int i = bufferedReader.read(cArr);
                    if (i == -1) {
                        IThemeSource iThemeSource = new IThemeSource() { // from class: org.eclipse.tm4e.core.registry.IThemeSource.1
                            @Override // org.eclipse.tm4e.core.registry.IThemeSource
                            public ContentType getContentType() {
                                return contentTypeGuessFileFormat;
                            }

                            @Override // org.eclipse.tm4e.core.registry.IThemeSource
                            public String getFilePath() {
                                return str;
                            }

                            @Override // org.eclipse.tm4e.core.registry.IThemeSource
                            public Reader getReader() {
                                return new StringReader(sb.toString());
                            }
                        };
                        bufferedReader.close();
                        return iThemeSource;
                    }
                    if (i > 0) {
                        sb.append(cArr, 0, i);
                    }
                    rc6.a(e);
                    return null;
                }
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Exception e) {
            rc6.a(e);
            return null;
        }
    }

    static IThemeSource fromResource(final Class<?> cls, final String str, final ContentType contentType, final Charset charset) {
        if (contentType == null) {
            contentType = guessFileFormat(str);
        }
        return new IThemeSource() { // from class: org.eclipse.tm4e.core.registry.IThemeSource.3
            @Override // org.eclipse.tm4e.core.registry.IThemeSource
            public ContentType getContentType() {
                return contentType;
            }

            @Override // org.eclipse.tm4e.core.registry.IThemeSource
            public String getFilePath() {
                return str;
            }

            @Override // org.eclipse.tm4e.core.registry.IThemeSource
            public Reader getReader() {
                InputStream resourceAsStream = cls.getResourceAsStream(str);
                Charset charset2 = charset;
                if (charset2 == null) {
                    charset2 = StandardCharsets.UTF_8;
                }
                return new BufferedReader(new InputStreamReader(resourceAsStream, charset2));
            }
        };
    }

    static IThemeSource fromString(final ContentType contentType, final String str) {
        return new IThemeSource() { // from class: org.eclipse.tm4e.core.registry.IThemeSource.4
            @Override // org.eclipse.tm4e.core.registry.IThemeSource
            public ContentType getContentType() {
                return contentType;
            }

            @Override // org.eclipse.tm4e.core.registry.IThemeSource
            public String getFilePath() {
                return "string." + contentType.name().toLowerCase();
            }

            @Override // org.eclipse.tm4e.core.registry.IThemeSource
            public Reader getReader() {
                return new StringReader(str);
            }
        };
    }

    private static ContentType guessFileFormat(String str) {
        byte b = 1;
        String lowerCase = str.substring(str.lastIndexOf(46) + 1).trim().toLowerCase();
        lowerCase.getClass();
        switch (lowerCase.hashCode()) {
            case -1723969078:
                b = !lowerCase.equals("yaml-tmtheme") ? (byte) -1 : (byte) 0;
                break;
            case -1192850704:
                if (!lowerCase.equals("tmtheme")) {
                    b = -1;
                }
                break;
            case 118807:
                b = !lowerCase.equals(JDOMConstants.NS_PREFIX_XML) ? (byte) -1 : (byte) 2;
                break;
            case 119768:
                b = !lowerCase.equals("yml") ? (byte) -1 : (byte) 3;
                break;
            case 3271912:
                b = !lowerCase.equals("json") ? (byte) -1 : (byte) 4;
                break;
            case 3701415:
                b = !lowerCase.equals("yaml") ? (byte) -1 : (byte) 5;
                break;
            case 106756366:
                b = !lowerCase.equals("plist") ? (byte) -1 : (byte) 6;
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
            case 3:
            case 5:
                return ContentType.YAML;
            case 1:
            case 2:
            case 6:
                return ContentType.XML;
            case 4:
                return ContentType.JSON;
            default:
                w01.a("Unsupported file type: ".concat(str));
                return null;
        }
    }

    default ContentType getContentType() {
        return guessFileFormat(getFilePath());
    }

    String getFilePath();

    Reader getReader() throws IOException;

    static IThemeSource fromResource(Class<?> cls, String str) {
        return fromResource(cls, str, null, null);
    }

    static IThemeSource fromFile(File file) {
        return fromFile(file, null, null);
    }
}
