import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:sidharth/home_screen.dart';

import 'finish_ride_screen.dart';
import 'map_screen.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Sidharth Mudgil',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      routes: {
        // '/': (context) => const MapScreen(),
        '/': (context) => const HomeScreen(),
        MapScreen.route: (context) => const MapScreen(),
        FinishRideScreen.route: (context) => const FinishRideScreen(),
      },
    );
  }
}