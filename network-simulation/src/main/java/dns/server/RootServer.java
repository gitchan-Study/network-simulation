package dns.server;

// -------------------------
// Root DNS Server
// -------------------------
// - 최상위 DNS 서버
// - "어떤 TLD(.com, .net 등)가 담당하는지" 알려줌
// -------------------------
public class RootServer extends DnsServer {
    DnsServer tldServer;

    public RootServer(String name, DnsServer tldServer) {
        super(name);
        this.tldServer = tldServer;
    }

    @Override
    public String query(String domain) {
        System.out.println("🌐 [" + name + "] .com 도메인 담당 서버 알려줌");
        return tldServer.query(domain);
    }
}
