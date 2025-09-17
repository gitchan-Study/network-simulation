import dns.lookup.ClientDns;
import dns.lookup.DnsResolver;
import dns.lookup.UdpDnsTransport;
import dns.server.DnsTopology;
import dns.server.RootServer;
import tcp.TcpClient;
import tcp.TcpHeader;
import tcp.TcpServer;

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
        clientDns.lookup("myserver");           // hosts 파일 hit

        // =====================================================
        // 🌐 2. TCP 3-way Handshake (전송 계층)
        // =====================================================
        // 브라우저가 알아낸 서버 IP 주소로 연결을 시작
        //  - Client: 브라우저/OS (에페메럴 포트 할당, ISN 선택)
        //  - Server: 웹 서버 (고정 포트 80, ISN 선택)
        // Handshake 단계:
        //   (1) Client → Server : SYN
        //   (2) Server → Client : SYN + ACK
        //   (3) Client → Server : ACK
        // =====================================================

        TcpClient client = new TcpClient(); // 클라이언트 컴퓨터 모형
        TcpServer server = new TcpServer(); // 서버 컴퓨터 모형

        // 1. 클라이언트가 SYN 전송
        int dstPort = 80;
        TcpHeader syn = client.sendSyn(dstPort);

        // 2. 서버가 SYN+ACK 응답
        TcpHeader synAck = server.sendSynAck(syn);

        // 3. 클라이언트가 최종 ACK 전송
        TcpHeader ack = client.sendAck(dstPort, synAck.seq);

        System.out.println("\n✅ TCP 3-way handshake 완료 (연결 ESTABLISHED)");
    }
}
