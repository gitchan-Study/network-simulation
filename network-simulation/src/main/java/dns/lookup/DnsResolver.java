package dns.lookup;

import dns.server.AuthoritativeServer;
import dns.server.DnsServer;
import dns.server.RootServer;
import dns.server.TldServer;

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

        // 계층 구조 설정
        DnsServer authoritative = new AuthoritativeServer("Authoritative DNS", "203.0.113.50");
        DnsServer tld = new TldServer("TLD .com DNS", authoritative);
        root = new RootServer("Root DNS", tld);
    }

    public String resolve(String domain) {
        System.out.println("=== DNS Resolution Start ===");
        System.out.println("[1] 요청 도메인: " + domain);

        // 1. 캐시 확인 (브라우저 캐시, OS 캐시, Hosts 파일을 단순화해서 표현)
        if (cache.containsKey(domain)) {
            String cached = cache.get(domain);
            System.out.println("[2] 캐시 조회 성공 ✅");
            System.out.println("    " + domain + " → " + cached + " (from cache)");
            System.out.println("=== DNS Resolution End ===\n");
            return cached;
        }
        System.out.println("[2] 캐시 조회 실패 ❌");
        System.out.println("    캐시에 없는 도메인, DNS 서버에 질의 시작");

        // 2. Root → TLD → Authoritative 서버 순서대로 질의 (모의 시뮬레이션)
        System.out.println("[3] 질의 경로:");
        System.out.println("    Root 서버  →  .com TLD 서버  →  권한 DNS 서버(" + domain + ")");
        transport.send("Root -> TLD(.com) -> Authoritative(" + domain + ")");

        // 3. 실제 객체를 통해 최종 응답 받기
        String ip = root.query(domain);

        // 4. 응답 결과를 로컬 캐시에 저장 (TTL 고려 생략)
        System.out.println("[4] 권한 DNS 응답 수신:");
        System.out.println("    " + domain + " → " + ip);
        cache.put(domain, ip);

        System.out.println("[5] 캐시에 저장 완료");
        System.out.println("    " + domain + " → " + ip);
        System.out.println("=== DNS Resolution End ===\n");

        return ip;
    }
}
