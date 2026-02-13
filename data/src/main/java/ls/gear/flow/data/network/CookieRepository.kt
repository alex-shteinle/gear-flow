package ls.gear.flow.data.network

interface CookieRepository {
    fun save(cookies: Set<String>)
    fun get(): Set<String>
}
