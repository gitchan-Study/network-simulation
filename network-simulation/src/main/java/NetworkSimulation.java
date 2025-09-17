import dns.lookup.ClientDns;
import dns.lookup.DnsResolver;
import dns.lookup.UdpDnsTransport;
import dns.server.DnsTopology;
import dns.server.RootServer;

public class NetworkSimulation {

    public static void main(String[] args) {
        // -------------------------
        // 🌐 1. 응용 계층 (브라우저/OS)
        // -------------------------
        // 사용자가 브라우저 주소창에 "gitchan-coding.com" 입력
        // → 브라우저/OS가 DNS 조회를 시작
        //
        // (1) 브라우저 캐시 확인
        // (2) hosts 파일 확인
        // (3) 없으면 로컬 리졸버(DnsResolver)에게 질의
        // -------------------------

        // DNS 서버 계층 구조 생성 (Root → TLD → Authoritative)
        RootServer root = DnsTopology.build();

        // 로컬 리졸버 (UDP 기반)
        DnsResolver resolver = new DnsResolver(new UdpDnsTransport(root));

        // 클라이언트 DNS (브라우저/OS) 객체
        ClientDns clientDns = new ClientDns(resolver);

        // 테스트 시나리오
        clientDns.lookup("gitchan-coding.com"); // 캐시/hosts 없음 → 서버 질의
        clientDns.lookup("gitchan-coding.com"); // 이번엔 브라우저 캐시 hit
        clientDns.lookup("myserver");           // hosts 파일 hit}
    }
}
