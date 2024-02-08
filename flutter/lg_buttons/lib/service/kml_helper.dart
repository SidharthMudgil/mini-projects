String _getTags() {
  int heading = 0;
  String kml = '';

  for (var i = 0; i <= 36; i++) {
    heading += 10;
    kml += '''
<gx:FlyTo>
  <gx:duration>1.2</gx:duration>
  <gx:flyToMode>smooth</gx:flyToMode>
  <LookAt>
    <longitude>76.6084</longitude>
    <latitude>28.8958</latitude>
    <heading>$heading</heading>
    <tilt>70</tilt>
    <range>2000</range>
    <gx:fovy>60</gx:fovy> 
    <altitude>3341.7995674</altitude> 
    <gx:altitudeMode>absolute</gx:altitudeMode>
  </LookAt>
</gx:FlyTo>
  ''';
  }

  return kml;
}

String orbit() {
  return '''
<?xml version="1.0" encoding="UTF-8"?>
<kml xmlns="http://www.opengis.net/kml/2.2" xmlns:gx="http://www.google.com/kml/ext/2.2" xmlns:kml="http://www.opengis.net/kml/2.2" xmlns:atom="http://www.w3.org/2005/Atom">  
  <gx:Tour>
    <name>Orbit</name>
      <gx:Playlist>
        ${_getTags()}
      </gx:Playlist>
  </gx:Tour>
</kml>
  ''';
}

String balloon() {
  return '''
<?xml version="1.0" encoding="UTF-8"?>
<kml xmlns="http://www.opengis.net/kml/2.2" xmlns:gx="http://www.google.com/kml/ext/2.2" xmlns:kml="http://www.opengis.net/kml/2.2" xmlns:atom="http://www.w3.org/2005/Atom">
<Document>
 <name>Task2</name>
 <Style id="weather_style">
   <BalloonStyle>
     <textColor>ffffffff</textColor>
     <text>
        <h1>Sidharth Mudgil</h1>
        <h2>Rohtak, Haryana</h2>
     </text>
     <bgColor>ff15151a</bgColor>
   </BalloonStyle>
 </Style>
 <Placemark id="ww">
   <description>
   </description>
   <LookAt>
     <longitude>76.6084</longitude>
     <latitude>28.8958</latitude>
     <heading>0</heading>
     <tilt>70</tilt>
     <range>4000000</range>
   </LookAt>
   <styleUrl>#weather_style</styleUrl>
   <gx:balloonVisibility>1</gx:balloonVisibility>
   <Point>
     <coordinates>76.6084,28.8958,0</coordinates>
   </Point>
 </Placemark>
</Document>
</kml>
''';
}