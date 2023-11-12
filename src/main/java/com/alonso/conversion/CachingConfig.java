package com.alonso.conversion;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import jakarta.validation.constraints.NotNull;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Function;

@Configuration
@EnableCaching
public class CachingConfig {

    private static final String FIXED_KEY = "FiscalDataCacheMONO";

    @Bean("caffeineCacheManager")
    public CacheManager cacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(
                Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(3))
                .initialCapacity(30)
                .maximumSize(800)
        );
        return caffeineCacheManager;
    }

    public static <T> Function<String, Mono<T>> ofMonoAsyncLoadingCache(@NotNull Duration duration, @NotNull Function<String, Mono<T>> fn) {
        final AsyncLoadingCache<String, T> cache = Caffeine.newBuilder()
                .expireAfterWrite(duration.multipliedBy(2))
                .refreshAfterWrite(duration)
                .buildAsync((k, e) ->
                        fn.apply(k)
                                .subscribeOn(Schedulers.fromExecutor(e))
                                .toFuture());

        return (k) -> Mono.fromFuture(cache.get(k));
    }

    public static <T> Mono<T> ofMonoFixedKey(@NotNull Duration duration, @NotNull Mono<T> mono) {
        Function<String, Mono<T>> monoFn = ofMonoAsyncLoadingCache(duration, key -> mono);
        return Mono.defer(() -> monoFn.apply(FIXED_KEY));
    }

}