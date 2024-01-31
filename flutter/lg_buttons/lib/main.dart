import 'package:flutter/material.dart';
import 'package:lg_buttons/screens/connection_screen.dart';
import 'package:lg_buttons/screens/home_screen.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {

  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'LG Face',
      scrollBehavior:
          ScrollConfiguration.of(context).copyWith(scrollbars: false),
      initialRoute: HomeScreen.route,
      routes: {
        HomeScreen.route: (context) {
          return const HomeScreen();
        },
        ConnectionScreen.route: (context) {
          return const ConnectionScreen();
        },
      },
      onUnknownRoute: (settings) {
        return MaterialPageRoute(builder: (context) => const HomeScreen());
      },
    );
  }
}
