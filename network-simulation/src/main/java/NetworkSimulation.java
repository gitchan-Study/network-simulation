import dns.lookup.ClientDns;
import dns.lookup.DnsResolver;
import dns.lookup.UdpDnsTransport;

public class NetworkSimulation {

    public static void main(String[] args) {
        // 1. 사용자의 요청 생성
        // 1.1 DNS 요청 (= IP 주소 알아내기)
        DnsResolver resolver = new DnsResolver(new UdpDnsTransport());
        ClientDns clientDns = new ClientDns(resolver);

        clientDns.resolve("gitchan-coding.com"); // 없으니 서버에 질의
        clientDns.resolve("gitchan-coding.com"); // 같은 도메인 다시 요청 (이번엔 브라우저 캐시 hit)
        clientDns.resolve("myserver"); // hosts 파일에 있는 도메인
    }
}
