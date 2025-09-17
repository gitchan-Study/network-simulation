package dns.server;

import java.util.HashMap;
import java.util.Map;

// -------------------------
// Root DNS Server
// -------------------------
// - 전 세계 13개 루트 서버 집합을 단순화한 모델
// - TLD(.com, .org, .net 등)에 따라 적절한 TLD 서버로 위임
// -------------------------
public class RootServer extends DnsServer {

    private Map<String, TldServer> tldServers = new HashMap<>();

    public RootServer(String name) {
        super(name);
    }

    // TLD 서버 등록
    public void addTldServer(String tld, TldServer server) {
        tldServers.put(tld, server);
    }

    @Override
    public String query(String domain) {
        // 도메인에서 TLD 추출
        String[] parts = domain.split("\\.");
        String tld = parts[parts.length - 1]; // ex: "com"

        TldServer tldServer = tldServers.get(tld);
        if (tldServer == null) {
            System.out.println("🌐 [" + name + "] ❌ 지원하지 않는 TLD: ." + tld);
            return null;
        }

        System.out.println("🌐 [" + name + "] ." + tld + " TLD 서버 주소 알려줌");
        return tldServer.query(domain);
    }
}
