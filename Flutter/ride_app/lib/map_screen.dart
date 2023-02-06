import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:slider_button/slider_button.dart';
import 'package:flutter/services.dart';
import 'package:location/location.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'finish_ride_screen.dart';

class MapScreen extends StatefulWidget {
  static const route = "/map_screen";

  const MapScreen({Key? key}) : super(key: key);

  @override
  State<MapScreen> createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  DateTime startTimestamp = DateTime.now();
  LocationData? _currentLocation;
  late final MapController _mapController;
  bool ridePaused = true;
  bool _hasPermission = false;
  final Location _locationService = Location();
  final List<LatLng> _points = [];
  LatLng startPoint = LatLng(0, 0);
  LatLng finishPoint = LatLng(0, 0);
  final _stopwatch = Stopwatch();

  @override
  void initState() {
    super.initState();
    _mapController = MapController();
    initLocationService();
  }

  void initLocationService() async {
    LocationData? location;
    bool serviceEnabled;
    bool serviceRequestResult;

    try {
      serviceEnabled = await _locationService.serviceEnabled();

      if (serviceEnabled) {
        final permission = await _locationService.requestPermission();
        _hasPermission = permission == PermissionStatus.granted;

        if (_hasPermission) {
          await _locationService.changeSettings(
            accuracy: LocationAccuracy.high,
            interval: 1000,
            distanceFilter: 5,
          );

          location = await _locationService.getLocation();
          _currentLocation = location;
          if (startPoint == LatLng(0, 0)) {
            startPoint = LatLng(location.latitude!, location.longitude!);
            _mapController.move(
              LatLng(startPoint.latitude, startPoint.longitude),
              _mapController.zoom,
            );
            debugPrint('start point: ${startPoint.toString()}');
          }
          _locationService.onLocationChanged
              .listen((LocationData result) async {
            if (mounted) {
              setState(() {
                if (_currentLocation == result) {
                  ridePaused = true;
                  debugPrint('ride paused');
                  debugPrint('${_currentLocation.toString()} ${result.toString()}');
                  _stopwatch.stop();
                } else {
                  _stopwatch.stop();
                  debugPrint('ride started');
                  debugPrint('${_currentLocation.toString()} ${result.toString()}');
                  ridePaused = false;
                  _currentLocation = result;
                  _points.add(LatLng(_currentLocation!.latitude!,
                      _currentLocation!.longitude!));
                  _mapController.move(
                    LatLng(_currentLocation!.latitude!,
                        _currentLocation!.longitude!),
                    _mapController.zoom,
                  );
                }
              });
            }
          });
        }
      } else {
        serviceRequestResult = await _locationService.requestService();
        if (serviceRequestResult) {
          initLocationService();
          return;
        }
      }
    } on PlatformException catch (e) {
      debugPrint(e.toString());
      if (e.code == 'PERMISSION_DENIED') {
        debugPrint(e.message);
      } else if (e.code == 'SERVICE_STATUS_ERROR') {
        debugPrint(e.message);
      }
      location = null;
    }
  }

  double _distance = 0.0;

  @override
  Widget build(BuildContext context) {
    File initialImage = ModalRoute.of(context)!.settings.arguments as File;
    final query = MediaQuery.of(context);
    LatLng currentLatLng;

    double speed = 0.0;

    if (_currentLocation != null) {
      currentLatLng = LatLng(_currentLocation!.latitude!, _currentLocation!.longitude!);
      speed = _currentLocation!.speed! * 3.6;
      _distance =
          const Distance().as(LengthUnit.Kilometer, startPoint, currentLatLng) /
              1000;
    } else {
      currentLatLng = LatLng(0, 0);
    }

    final markers = <Marker>[
      Marker(
        point: startPoint,
        builder: (ctx) => const Icon(
          Icons.circle,
          color: Colors.blue,
          size: 15,
        ),
      ),
      Marker(
        point: currentLatLng,
        builder: (ctx) => const Icon(
          Icons.pin_drop_rounded,
          color: Colors.blue,
          size: 30,
        ),
      ),
    ];

    return Scaffold(
      body: Stack(
        fit: StackFit.expand,
        children: [
          FlutterMap(
            mapController: _mapController,
            options: MapOptions(
              center: LatLng(currentLatLng.latitude, currentLatLng.longitude),
              zoom: 17,
              maxZoom: 17,
              minZoom: 5,
              interactiveFlags: InteractiveFlag.all,
            ),
            nonRotatedChildren: [
              AttributionWidget.defaultWidget(
                source: 'OpenStreetMap contributors',
                onSourceTapped: null,
              ),
            ],
            children: [
              TileLayer(
                urlTemplate: 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
                userAgentPackageName: 'com.sidharth',
              ),
              MarkerLayer(markers: markers),
              PolylineLayer(
                polylines: [
                  Polyline(
                    points: _points,
                    strokeWidth: 5,
                    color: Colors.blue,
                  ),
                ],
              ),
            ],
          ),
          Positioned(
            bottom: 0,
            child: Container(
              width: query.size.width,
              height: query.size.height * 0.3,
              decoration: const BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.only(
                  topLeft: Radius.circular(50),
                  topRight: Radius.circular(50),
                ),
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  Text(
                    ridePaused ? "Ride Paused" : "Ride In Progress",
                    style: TextStyle(
                      fontWeight: FontWeight.w700,
                      color: ridePaused ? Colors.yellow : Colors.deepPurple,
                      fontSize: 28,
                    ),
                  ),
                  Row(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    mainAxisAlignment: MainAxisAlignment.spaceAround,
                    children: [
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: [
                          const Text(
                            "Time",
                            style: TextStyle(
                              fontWeight: FontWeight.w600,
                              fontSize: 18,
                            ),
                          ),
                          const SizedBox(height: 5),
                          const Text(
                            "HH:MM:SS",
                            style: TextStyle(
                              fontSize: 18,
                            ),
                          ),
                          const SizedBox(height: 10),
                          Text(
                            _stopwatch.elapsed
                                .toString()
                                .split('.')
                                .first
                                .padLeft(8, '0'),
                            style: const TextStyle(
                              fontWeight: FontWeight.w600,
                              fontSize: 18,
                            ),
                          ),
                        ],
                      ),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: [
                          const Text(
                            "Distance",
                            style: TextStyle(
                              fontWeight: FontWeight.w600,
                              fontSize: 18,
                            ),
                          ),
                          const SizedBox(height: 5),
                          const Text(
                            "KMS",
                            style: TextStyle(
                              fontSize: 18,
                            ),
                          ),
                          const SizedBox(height: 10),
                          Text(
                            '$_distance',
                            style: const TextStyle(
                              fontWeight: FontWeight.w600,
                              fontSize: 18,
                            ),
                          ),
                        ],
                      ),
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: [
                          const Text(
                            "Avg Speed",
                            style: TextStyle(
                              fontWeight: FontWeight.w600,
                              fontSize: 18,
                            ),
                          ),
                          const SizedBox(height: 5),
                          const Text(
                            "kmph",
                            style: TextStyle(
                              fontSize: 18,
                            ),
                          ),
                          const SizedBox(height: 10),
                          Text(
                            speed.toStringAsFixed(2),
                            style: const TextStyle(
                              fontWeight: FontWeight.w600,
                              fontSize: 18,
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                  SliderButton(
                    action: () async {
                      String startTimeString =
                          '${startTimestamp.day.toString().padLeft(2, '0')}'
                          '${startTimestamp.month.toString().padLeft(2, '0')}'
                          '${startTimestamp.year},'
                          '${startTimestamp.hour}:'
                          '${startTimestamp.minute}:'
                          '${startTimestamp.second}';

                      DateTime et = DateTime.now();
                      String endTimeString =
                          '${et.day.toString().padLeft(2, '0')}'
                          '${et.month.toString().padLeft(2, '0')}'
                          '${et.year},'
                          '${et.hour}:'
                          '${et.minute}:'
                          '${et.second}';

                      String speedString = speed
                          .toStringAsFixed(2)
                          .toString()
                          .replaceAll('.', ':');

                      String distanceString =
                          _distance.toString().replaceAll('.', ':');

                      String rideTimeString = _stopwatch.elapsed
                          .toString()
                          .split('.')
                          .first
                          .padLeft(8, '0');

                      String startPointString =
                          '${startPoint.latitude},${startPoint.longitude}';

                      String endPointString =
                          '${currentLatLng.latitude},${currentLatLng.longitude}';

                      debugPrint('Before the ride odometer: $initialImage');
                      debugPrint('Start Timestamp: $startTimeString');
                      debugPrint('End Timestamp: $endTimeString');
                      debugPrint('Start Point: $startPointString');
                      debugPrint('Finish Point: $endPointString');
                      debugPrint('Average Speed: $speedString Km/h');
                      debugPrint('Total Distance Travelled: $distanceString');
                      debugPrint('Total Ride Time: $rideTimeString');

                      SharedPreferences pref =
                          await SharedPreferences.getInstance();
                      pref.setString('initialImage', initialImage.toString());
                      pref.setString('startTimeString', startTimeString);
                      pref.setString('endTimeString', endTimeString);
                      pref.setString('startPointString', startPointString);
                      pref.setString('endPointString', endPointString);
                      pref.setString('speedString', speedString);
                      pref.setString('distanceString', distanceString);
                      pref.setString('rideTimeString', rideTimeString);
                      _stopwatch.stop();

                      Map<String, String> args = {
                        'initialImage': initialImage.toString(),
                        'startTimeString': startTimeString,
                        'endTimeString': endTimeString,
                        'startPointString': startPointString,
                        'endPointString': endPointString,
                        'speedString': speedString,
                        'distanceString': distanceString,
                        'rideTimeString': rideTimeString,
                      };

                      if (!mounted) return;
                      Navigator.of(context).pushReplacementNamed(
                        FinishRideScreen.route,
                        arguments: args,
                      );
                    },
                    label: const Text(
                      "Slide to Finish",
                      style: TextStyle(
                          color: Color(0xff4a4a4a),
                          fontWeight: FontWeight.w500,
                          fontSize: 22),
                    ),
                    icon: const Icon(Icons.arrow_forward_rounded),
                  ),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}
