import 'dart:io';

class RideModel {
  final File _beforeRide;
  final File _afterRide;
  final String _startTimestamp;
  final String _endTimestamp;
  final String _totalRideTime;
  final String _totalDistanceTravelled;
  final String _averageSpeed;
  final String _startPoint;
  final String _finishPoint;

  const RideModel(
      {required beforeRide,
      required afterRide,
      required startTimestamp,
      required endTimestamp,
      required totalRideTime,
      required totalDistanceTravelled,
      required averageSpeed,
      required startPoint,
      required finishPoint})
      : _beforeRide = beforeRide,
        _afterRide = afterRide,
        _startTimestamp = startTimestamp,
        _endTimestamp = endTimestamp,
        _totalRideTime = totalRideTime,
        _totalDistanceTravelled = totalDistanceTravelled,
        _averageSpeed = averageSpeed,
        _startPoint = startPoint,
        _finishPoint = finishPoint;

  String get finishPoint => _finishPoint;

  String get startPoint => _startPoint;

  String get averageSpeed => _averageSpeed;

  String get totalDistanceTravelled => _totalDistanceTravelled;

  String get totalRideTime => _totalRideTime;

  String get endTimestamp => _endTimestamp;

  String get startTimestamp => _startTimestamp;

  File get afterRide => _afterRide;

  File get beforeRide => _beforeRide;
}
