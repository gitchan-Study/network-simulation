package dns.lookup;

import dns.server.DnsServer;
import dns.server.RootServer;

import java.util.HashMap;
import java.util.Map;

// -------------------------
// DnsResolver (로컬 리졸버 역할)
// -------------------------
// - ISP DNS, 구글 8.8.8.8, 클라우드플레어 1.1.1.1 같은 DNS 서버 역할
// - 내부적으로 Root → TLD → Authoritative 서버 계층을 순서대로 탐색
// - 캐시를 두어 같은 요청이 오면 바로 응답
// -------------------------
public class DnsResolver {

    private DnsTransport transport;
    private Map<String, String> cache = new HashMap<>(); // 로컬 리졸버 내부 캐시
    private DnsServer root;

    public DnsResolver(DnsTransport transport) {
        this.transport = transport;
        root = new RootServer("Root DNS");
    }

    public String resolve(String domain) {
        System.out.println("=== DNS Resolution Start ===");
        System.out.println("[1] 요청 도메인: " + domain);

        // 1. 캐시 확인
        if (cache.containsKey(domain)) {
            String ip = cache.get(domain);
            System.out.println("[2] 캐시 hit ✅ " + domain + " → " + ip);
            System.out.println("=== DNS Resolution End ===\n");
            return ip;
        }
        System.out.println("[2] 캐시 miss ❌ DNS 서버 탐색 시작");

        // 2. Transport를 통해 DNS 계층에 질의
        String ip = transport.send(domain);

        // 3. 캐시에 저장
        if (ip != null) {
            cache.put(domain, ip);
            System.out.println("[3] 최종 결과 캐시에 저장: " + domain + " → " + ip);
        }

        System.out.println("=== DNS Resolution End ===\n");
        return ip;
    }
}
