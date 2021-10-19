data class HostConfig(
    var ip: String,
    var port: Int,
    var typeConnection: String
) {
    override fun toString(): String {
        return "ip: $ip\t\tport: $port\t\ttype connection: $typeConnection"
    }
}