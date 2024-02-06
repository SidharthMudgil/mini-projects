import 'package:lg_buttons/service/kml_helper.dart';
import 'package:ssh2/ssh2.dart';

class LGService {
  late SSHClient _client;
  static LGService? instance;
  String _host;
  String _username;
  String _password;
  int _port;
  int _slaves;

  String get _lookAt {
    return "<LookAt><longitude>${76.6084}</longitude><latitude>${28.8958}</latitude><range>${3000}</range><tilt>${0}</tilt><gx:fovy>60</gx:fovy><heading>${0}</heading><gx:altitudeMode>relativeToGround</gx:altitudeMode></LookAt>";
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
      await _execute("echo '${orbit()}' > /var/www/html/homecity.kml");
      await  _execute('echo "http://lg1:81/homecity.kml" >> /var/www/html/kmls.txt');
      return await _execute("echo 'playtour=Orbit' > /tmp/query.txt");
    } catch (e) {
      return false;
    }
  }

  Future<bool> showBubble() async {
    try {
      moveToHomeCity();
      await _execute("echo '${balloon()}' > /var/www/html/bubble.kml");
      await  _execute('echo "http://lg1:81/bubble.kml" >> /var/www/html/kmls.txt');
      return true;
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
