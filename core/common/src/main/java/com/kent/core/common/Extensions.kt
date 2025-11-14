package com.kent.core.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Common extensions and utilities
 */

fun <T> Flow<T>.handleErrors(
    onError: (Throwable) -> Unit
): Flow<T> = catch { e -> onError(e) }

fun <T, R> Flow<T>.mapSafely(
    transform: (T) -> R
): Flow<Result<R>> = map { value ->
    Result.runCatching { transform(value) }
}

