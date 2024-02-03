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

  String get _dataBalloon {
    return '''<?xml version="1.0" encoding="UTF-8"?>
    <kml xmlns="http://www.opengis.net/kml/2.2" xmlns:gx="http://www.google.com/kml/ext/2.2" xmlns:kml="http://www.opengis.net/kml/2.2" xmlns:atom="http://www.w3.org/2005/Atom">
    <Document>
    <name>Weather Data</name>
    <Style id="weather_style">
    <BalloonStyle>
    <textColor>ffffffff</textColor>
    <text>
    <h1>Sidharth Mudgil</h1>
    <h2>Jhajjar, Haryana</h2>
    </text>
    <bgColor>ff15151a</bgColor>
    </BalloonStyle>
    </Style>
    <Placemark id="ww">
    <description>
    </description>
    <LookAt>
    <longitude>${12}</longitude>
    <latitude>${2}</latitude>
    <heading>${1}</heading>
    <tilt>${10}</tilt>
    <range>${1}</range>
    </LookAt>
    <styleUrl>#weather_style</styleUrl>
    <gx:balloonVisibility>1</gx:balloonVisibility>
    <Point>
    </Point>
    </Placemark>
    </Document>
    </kml>''';
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
      const query =
          'echo "flytoview=<LookAt><longitude>${120}</longitude><latitude>${21}</latitude><range>${10}</range><tilt>${0}</tilt><heading>${0}</heading><gx:altitudeMode>relativeToGround</gx:altitudeMode></LookAt>" > /tmp/query.txt';
      return await _execute(query);
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
      final query = "echo '$_dataBalloon' > /var/www/html/kml/$_dataSlave.kml";
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