package com.google.crypto.tink.util;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.kotlin.psi.KtCodeFragment;
import org.joda.time.Instant;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public class KeysDownloader {
    private final Executor backgroundExecutor;
    private long cacheExpirationDurationInMillis;
    private String cachedData;
    private long cachedTimeInMillis;
    private final Object fetchDataLock;
    private final HttpTransport httpTransport;
    private final Object instanceStateLock;
    private Runnable pendingRefreshRunnable;
    private final String url;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final NetHttpTransport DEFAULT_HTTP_TRANSPORT = new NetHttpTransport.Builder().build();
    private static final Executor DEFAULT_BACKGROUND_EXECUTOR = Executors.newCachedThreadPool();
    private static final Pattern MAX_AGE_PATTERN = Pattern.compile("\\s*max-age\\s*=\\s*(\\d+)\\s*");

    public static class Builder {
        private String url;
        private HttpTransport httpTransport = KeysDownloader.DEFAULT_HTTP_TRANSPORT;
        private Executor executor = KeysDownloader.DEFAULT_BACKGROUND_EXECUTOR;

        public KeysDownloader build() {
            if (this.url != null) {
                return new KeysDownloader(this.executor, this.httpTransport, this.url);
            }
            w01.a("must provide a url with {#setUrl}");
            return null;
        }

        public Builder setExecutor(Executor executor) {
            this.executor = executor;
            return this;
        }

        public Builder setHttpTransport(HttpTransport httpTransport) {
            this.httpTransport = httpTransport;
            return this;
        }

        public Builder setUrl(String str) {
            this.url = str;
            return this;
        }
    }

    public KeysDownloader(Executor executor, HttpTransport httpTransport, String str) {
        validate(str);
        this.backgroundExecutor = executor;
        this.httpTransport = httpTransport;
        this.instanceStateLock = new Object();
        this.fetchDataLock = new Object();
        this.url = str;
        this.cachedTimeInMillis = Long.MIN_VALUE;
        this.cacheExpirationDurationInMillis = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String fetchAndCacheData() throws IOException {
        long currentTimeInMillis = getCurrentTimeInMillis();
        HttpResponse httpResponseExecute = this.httpTransport.createRequestFactory().buildGetRequest(new GenericUrl(this.url)).execute();
        if (httpResponseExecute.getStatusCode() != 200) {
            e44.a("Unexpected status code = ", httpResponseExecute.getStatusCode());
            return null;
        }
        InputStream content = httpResponseExecute.getContent();
        try {
            String str = readerToString(new InputStreamReader(content, UTF_8));
            content.close();
            synchronized (this.instanceStateLock) {
                this.cachedTimeInMillis = currentTimeInMillis;
                this.cacheExpirationDurationInMillis = getExpirationDurationInSeconds(httpResponseExecute.getHeaders()) * 1000;
                this.cachedData = str;
            }
            return str;
        } catch (Throwable th) {
            content.close();
            throw th;
        }
    }

    private boolean hasNonExpiredDataCached() {
        long currentTimeInMillis = getCurrentTimeInMillis();
        long j = this.cachedTimeInMillis;
        return j + this.cacheExpirationDurationInMillis > currentTimeInMillis && !((j > currentTimeInMillis ? 1 : (j == currentTimeInMillis ? 0 : -1)) > 0);
    }

    private Runnable newRefreshRunnable() {
        return new Runnable() { // from class: com.google.crypto.tink.util.KeysDownloader.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (KeysDownloader.this.fetchDataLock) {
                    try {
                        try {
                            KeysDownloader.this.fetchAndCacheData();
                            synchronized (KeysDownloader.this.instanceStateLock) {
                                try {
                                    if (KeysDownloader.this.pendingRefreshRunnable == this) {
                                        KeysDownloader.this.pendingRefreshRunnable = null;
                                    }
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                        } catch (IOException unused) {
                            synchronized (KeysDownloader.this.instanceStateLock) {
                                try {
                                    if (KeysDownloader.this.pendingRefreshRunnable == this) {
                                        KeysDownloader.this.pendingRefreshRunnable = null;
                                    }
                                } catch (Throwable th2) {
                                    throw th2;
                                }
                            }
                        } catch (Throwable th3) {
                            synchronized (KeysDownloader.this.instanceStateLock) {
                                try {
                                    if (KeysDownloader.this.pendingRefreshRunnable == this) {
                                        KeysDownloader.this.pendingRefreshRunnable = null;
                                    }
                                    throw th3;
                                } catch (Throwable th4) {
                                    throw th4;
                                }
                            }
                        }
                    } catch (Throwable th5) {
                        throw th5;
                    }
                }
            }
        };
    }

    private static String readerToString(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = bufferedReader.read();
            if (i == -1) {
                return sb.toString();
            }
            sb.append((char) i);
        }
    }

    private boolean shouldProactivelyRefreshDataInBackground() {
        return this.cachedTimeInMillis + (this.cacheExpirationDurationInMillis / 2) <= getCurrentTimeInMillis();
    }

    private static void validate(String str) {
        try {
            if (new URL(str).getProtocol().toLowerCase(Locale.US).equals("https")) {
            } else {
                throw new IllegalArgumentException("url must point to a HTTPS server");
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String download() throws IOException {
        synchronized (this.instanceStateLock) {
            try {
                if (hasNonExpiredDataCached()) {
                    if (shouldProactivelyRefreshDataInBackground()) {
                        refreshInBackground();
                    }
                    return this.cachedData;
                }
                synchronized (this.fetchDataLock) {
                    synchronized (this.instanceStateLock) {
                        if (hasNonExpiredDataCached()) {
                            return this.cachedData;
                        }
                        return fetchAndCacheData();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public long getCurrentTimeInMillis() {
        return Instant.now().getMillis();
    }

    public long getExpirationDurationInSeconds(HttpHeaders httpHeaders) {
        long jLongValue;
        if (httpHeaders.getCacheControl() == null) {
            jLongValue = 0;
            break;
        }
        String[] strArrSplit = httpHeaders.getCacheControl().split(KtCodeFragment.IMPORT_SEPARATOR);
        int length = strArrSplit.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                jLongValue = 0;
                break;
            }
            Matcher matcher = MAX_AGE_PATTERN.matcher(strArrSplit[i]);
            if (matcher.matches()) {
                jLongValue = Long.valueOf(matcher.group(1)).longValue();
                break;
            }
            i++;
        }
        if (httpHeaders.getAge() != null) {
            jLongValue -= httpHeaders.getAge().longValue();
        }
        return Math.max(0L, jLongValue);
    }

    public HttpTransport getHttpTransport() {
        return this.httpTransport;
    }

    public String getUrl() {
        return this.url;
    }

    public void refreshInBackground() {
        Runnable runnableNewRefreshRunnable = newRefreshRunnable();
        synchronized (this.instanceStateLock) {
            try {
                if (this.pendingRefreshRunnable != null) {
                    return;
                }
                this.pendingRefreshRunnable = runnableNewRefreshRunnable;
                try {
                    this.backgroundExecutor.execute(runnableNewRefreshRunnable);
                } catch (Throwable th) {
                    synchronized (this.instanceStateLock) {
                        try {
                            if (this.pendingRefreshRunnable == runnableNewRefreshRunnable) {
                                this.pendingRefreshRunnable = null;
                            }
                            throw th;
                        } catch (Throwable th2) {
                            throw th2;
                        }
                    }
                }
            } catch (Throwable th3) {
                throw th3;
            }
        }
    }
}
