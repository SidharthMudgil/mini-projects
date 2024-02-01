import 'package:ssh2/ssh2.dart';

class LGService {
  late SSHClient _client;
  static LGService? instance;
  String _host;
  String _username;
  String _password;
  int _port;
  int _slaves;

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
      const query =
          'echo "flytoview=<LookAt><longitude>${120}</longitude><latitude>${21}</latitude><range>${10}</range><tilt>${0}</tilt><heading>${0}</heading><gx:altitudeMode>relativeToGround</gx:altitudeMode></LookAt>" > /tmp/query.txt';
      return await _execute(query);
    } catch (e) {
      return false;
    }
  }

  Future<bool> moveOrbitMyCity() async {
    try {
      const query = "";
      return await _execute(query);
    } catch (e) {
      return false;
    }
  }

  Future<bool> showBubble() async {
    try {
      const query = "";
      return await _execute(query);
    } catch (e) {
      return false;
    }
  }

  Future<bool> rebootLG() async {
    try {
      bool res = true;
      for (var i = 1; i <= _slaves; i++) {
        res = res &&
            await _execute(
                'sshpass -p $_password ssh -t lg$i "echo $_password | sudo -S reboot');
      }
      return res;
    } catch (error) {
      return false;
    }
  }
}
