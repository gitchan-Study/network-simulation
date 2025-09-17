import dns.lookup.ClientDns;
import dns.lookup.DnsResolver;
import dns.lookup.UdpDnsTransport;
import dns.server.DnsTopology;
import dns.server.RootServer;
import transport.TcpClient;
import transport.TcpHeader;
import transport.TcpServer;

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
        // DNS 조회로 웹 서버의 IP 주소를 알아낸 후,
        // 이제 TCP 연결을 맺어야 HTTP 요청을 전송할 수 있다.
        //
        // Handshake의 목적:
        //   - 클라이언트와 서버가 서로의 초기 시퀀스 번호(ISN)를 교환
        //   - 연결 상태를 동기화하여 "ESTABLISHED" 상태로 진입
        //
        // 참여자 역할:
        //   - Client: 브라우저/OS (임시 포트=에페메럴 포트 할당, ISN 무작위 선택)
        //   - Server: 웹 서버 (고정 포트=80, ISN 무작위 선택)
        //
        // Handshake 단계 (페이로드 없음):
        //   (1) Client → Server : SYN
        //       - "새로운 연결을 시작하고 싶다"
        //   (2) Server → Client : SYN + ACK
        //       - "나도 연결 시작할게, 그리고 네 SYN 받았어"
        //   (3) Client → Server : ACK
        //       - "네 SYN도 잘 받았어, 이제 연결 완료"
        // =====================================================

        TcpClient client = new TcpClient(); // 클라이언트 컴퓨터 모형
        TcpServer server = new TcpServer(); // 서버 컴퓨터 모형

        // 1. 클라이언트 → 서버 (SYN)
        int dstPort = 80; // HTTP 서비스 포트
        TcpHeader syn = client.sendSyn(dstPort);

        // 2. 서버 → 클라이언트 (SYN+ACK)
        TcpHeader synAck = server.sendSynAck(syn);

        // 3. 클라이언트 → 서버 (ACK)
        TcpHeader ack = client.sendAck(dstPort, synAck.seq);

        // 최종 연결 상태
        System.out.println("\n✅ TCP 3-way handshake 완료 → 연결 ESTABLISHED");
    }
}
