package extensions

/**
 * Map a Java array to another Java array
 */
inline fun <T, reified I> Array<T>.map(crossinline transform: (T) -> I) =
    Array(size) { transform(this[it]) }