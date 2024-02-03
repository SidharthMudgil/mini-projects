import 'package:ssh2/ssh2.dart';

class LGService {
  late SSHClient _client;
  static LGService? instance;
  String _host;
  String _username;
  String _password;
  int _port;
  int _slaves;

  String get _dataSlave {
    if (_slaves == 1) {
      return "slave_1";
    } else {
      return "slave_${(_slaves / 2) + 1}";
    }
  }

  String get _lookAt {
    return "<LookAt><longitude>${76.6084}</longitude><latitude>${28.8958}</latitude><range>${3000}</range><tilt>${0}</tilt><heading>${0}</heading><gx:altitudeMode>relativeToGround</gx:altitudeMode></LookAt>";
  }

  factory LGService({
    required String host,
    required int port,
    required String username,
    required String password,
    required int slaves,
  }) {
    if (instance == null ||
        instance!._host != host ||
        instance!._username != username ||
        instance!._port != port ||
        instance!._password != password ||
        instance!._slaves != slaves) {
      instance = LGService._internal(
        host: host,
        port: port,
        username: username,
        password: password,
        slaves: slaves,
      );
    }
    return instance!;
  }

  LGService._internal({
    required String host,
    required int port,
    required String username,
    required String password,
    required int slaves,
  })  : _host = host,
        _port = port,
        _username = username,
        _password = password,
        _slaves = slaves {
    _client = SSHClient(
        host: host, port: port, username: username, passwordOrKey: password);
  }

  static Future<bool> isConnected() {
    return instance?._client.isConnected() ?? Future(() => false);
  }

  Future<bool> connect() async {
    try {
      await _client.connect();
      return true;
    } catch (e) {
      return false;
    }
  }

  Future<bool> disconnect() async {
    try {
      await _client.disconnect();
      return true;
    } catch (e) {
      return false;
    }
  }

  Future<bool> _execute(String query) async {
    try {
      await _client.execute(query);
      return true;
    } catch (e) {
      return false;
    }
  }

  Future<bool> moveToHomeCity() async {
    try {
      return await _execute('echo "flytoview=$_lookAt" > /tmp/query.txt');
    } catch (e) {
      return false;
    }
  }

  Future<bool> moveOrbitMyCity() async {
    try {
      const query = 'echo "playtour=Orbit" > /tmp/query.txt';
      // const query = 'echo "flytoview=${}" > /tmp/query.txt';
      return await _execute(query);
    } catch (e) {
      return false;
    }
  }

  Future<bool> showBubble() async {
    try {
      const image = '''<kml xmlns="http://www.opengis.net/kml/2.2" 
    xmlns:atom="http://www.w3.org/2005/Atom"
    xmlns:gx="http://www.google.com/kml/ext/2.2">
    <Document>
        <name>LG Buttons</name>
        <Folder>
            <name>Logo</name>
            <ScreenOverlay>
                <name>Logo</name>
                <html><![CDATA[
                    <h1>Sidharth Mudgil</h1>
                    <h2>Rohtak, Haryana</h2>
                ]]></html>
                <overlayXY x="0" y="1" xunits="fraction" yunits="fraction"/>
                <screenXY x="0.02" y="0.95" xunits="fraction" yunits="fraction"/>
                <rotationXY x="0" y="0" xunits="fraction" yunits="fraction"/>
                <size x="0.3" y="0.3" xunits="fraction" yunits="fraction"/>
            </ScreenOverlay>
        </Folder>
    </Document>
</kml>''';
      return await _execute(
          "chmod 777 /var/www/html/kml/$_dataSlave.kml; echo '$image' > /var/www/html/kml/$_dataSlave.kml");
    } catch (e) {
      return false;
    }
  }

  Future<bool> rebootLG() async {
    try {
      bool res = true;
      for (var i = _slaves; i >= 1; i--) {
        res = res &&
            await _execute(
                'sshpass -p $_password ssh -t lg$i "echo $_password | sudo -S reboot"');
      }
      return res;
    } catch (error) {
      return false;
    }
  }
}
