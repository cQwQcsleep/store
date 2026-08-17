package javax.xml.catalog;

import defpackage.x0e;
import java.util.Locale;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class CatalogMessages {
    public static final String ERR_CIRCULAR_REFERENCE = "CircularReference";
    public static final String ERR_CREATING_URI = "FailedCreatingURI";
    public static final String ERR_INVALID_ARGUMENT = "InvalidArgument";
    public static final String ERR_INVALID_CATALOG = "InvalidCatalog";
    public static final String ERR_INVALID_ENTRY_TYPE = "InvalidEntryType";
    public static final String ERR_INVALID_PATH = "InvalidPath";
    public static final String ERR_NO_CATALOG = "NoCatalogFound";
    public static final String ERR_NO_MATCH = "NoMatchFound";
    public static final String ERR_NO_URI_MATCH = "NoMatchURIFound";
    public static final String ERR_NULL_ARGUMENT = "NullArgument";
    public static final String ERR_OTHER = "OtherError";
    public static final String ERR_PARSER_CONF = "ParserConf";
    public static final String ERR_PARSING_FAILED = "ParsingFailed";
    public static final String ERR_URI_NOTABSOLUTE = "UriNotAbsolute";
    public static final String ERR_URI_NOTVALIDURL = "UriNotValidUrl";
    static final String bundleName = CatalogMessages.class.getPackageName() + ".CatalogMessages";
    static ResourceBundle resourceBundle;

    public static String formatMessage(String str, Object[] objArr) {
        return formatMessage(str, objArr, Locale.getDefault());
    }

    public static void reportError(String str, Object[] objArr) {
        throw new CatalogException(formatMessage(str, objArr));
    }

    public static void reportIAE(String str, Object[] objArr, Throwable th) {
        throw new IllegalArgumentException(formatMessage(str, objArr), th);
    }

    public static void reportIAEOnNull(String str, String str2) {
        if (str2 != null) {
            return;
        }
        w01.a(formatMessage(ERR_INVALID_ARGUMENT, new Object[]{null, str}));
    }

    public static void reportNPEOnNull(String str, Object obj) {
        if (obj != null) {
            return;
        }
        x0e.a(formatMessage(ERR_NULL_ARGUMENT, new Object[]{str}));
    }

    public static void reportRunTimeError(String str, Throwable th) {
        throw new CatalogException(formatMessage(str, null), th);
    }

    public static String sanitize(String str) {
        if (str == null) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf("/");
        return (iLastIndexOf <= 0 || iLastIndexOf >= str.length()) ? str : str.substring(iLastIndexOf + 1);
    }

    public static String formatMessage(String str, Object[] objArr, Locale locale) {
        return SecuritySupport.getErrorMessage(locale, bundleName, str, objArr);
    }

    public static void reportError(String str) {
        reportError(str, null);
    }

    public static void reportRunTimeError(String str, Object[] objArr) {
        throw new CatalogException(formatMessage(str, objArr));
    }

    public static void reportRunTimeError(String str, Object[] objArr, Throwable th) {
        throw new CatalogException(formatMessage(str, objArr), th);
    }
}
